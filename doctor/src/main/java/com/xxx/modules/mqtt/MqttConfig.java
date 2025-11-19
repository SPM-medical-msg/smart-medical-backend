package com.xxx.modules.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.modules.entity.*;
import com.xxx.modules.mapper.*;
import com.xxx.modules.utils.TimeUtil;import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import javax.annotation.Resource;

@Configuration
@IntegrationComponentScan
@Slf4j
public class MqttConfig {
    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.url}")
    private String hostUrl;

    @Value("${spring.mqtt.client.id}")
    private String clientId;

    @Value("${spring.mqtt.default.topic}")
    private String defaultTopic;

    @Value("${spring.mqtt.completionTimeout}")
    private int completionTimeout; // 连接超时

    @Resource
    private MqttGateway mqttGateway;








    /**
     * 创建MQTT连接信息
     * @return
     */
    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
        mqttConnectOptions.setMaxInflight(50);
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setKeepAliveInterval(60);
        return mqttConnectOptions;
    }


    /**
     * MQTT推送消息客户端连接池创建并注入连接信息
     * @return
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
        //return factory;
    }


    public MessageHandler createMqttOutbound() {
        String tempId = MqttAsyncClient.generateClientId();
        MyMqttPahoMessageHandler messageHandler = new MyMqttPahoMessageHandler(clientId + tempId,
            mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(defaultTopic);
        messageHandler.setDefaultQos(0);
        messageHandler.onInit();
        return messageHandler;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        return new MultiMqttMessageHandler();
    }
    //输出通道
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }



    //接收通道
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }


    //配置client,监听的topic
    @Bean
    public MessageProducer inbound() {
        //监听全部消息
        MyMqttPahoMessageDrivenChannelAdapter adapter = new MyMqttPahoMessageDrivenChannelAdapter(null,clientId ,
            mqttClientFactory(),
                "CHAT/SRV000/#","CHAT/GROUP/#","LOGIN/CHAT/#");
        adapter.setCompletionTimeout(completionTimeout);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Autowired
    private FriendMessageMapper friendMessageMapper;
    @Autowired
    private FriendMapper friendMapper;
    //通过通道获取数据
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            //获取订阅消息
            try {
                log.info( message.getPayload().toString());
                JSONObject jsonObject = JSON.parseObject((String) message.getPayload());
                String topic = (String) message.getHeaders().get("mqtt_receivedTopic");
                assert topic != null;
                String loginName = topic.substring(topic.lastIndexOf("/") + 1); //获取当前登录人

                //单聊
                if (topic.startsWith("CHAT/SRV000")){

                    //单聊
                    Integer sendUserId = jsonObject.getInteger("sendUserId");
                    Integer receiveUserId = jsonObject.getInteger("receiveUserId");
                    Integer type = jsonObject.getInteger("type");
                    Integer strategyId = jsonObject.getInteger("strategyId");
                    String content = jsonObject.getString("content");
                    FriendMessage friendMessage = new FriendMessage();
                    friendMessage.setSendUserId(sendUserId);
                    friendMessage.setReceiveUserId(receiveUserId);
                    friendMessage.setContent(content);
                    friendMessage.setType(type);
                    friendMessage.setCreateTime(TimeUtil.getCurrentTime());
                    friendMessage.setUpdateTime(TimeUtil.getCurrentTime());
                    friendMessage.setStatus(1);
                    friendMessage.setIsView(1);
                    friendMessage.setFileUrl(jsonObject.getString("fileUrl"));
                    friendMessage.setStrategyId(jsonObject.getInteger("strategyId"));
                    if (strategyId != null) {
                        friendMessage.setStrategyId(strategyId);
                    }
                    friendMessageMapper.insert(friendMessage);


                    QueryWrapper<Friend> wrapper = new QueryWrapper<>();
                    wrapper.eq("user_id",sendUserId).eq("friend_id",receiveUserId).or().eq("user_id",receiveUserId).eq("friend_id",sendUserId);
                    Friend friend = new Friend();
                    friend.setUpdateTime(TimeUtil.getCurrentTime());
                    if (type == 1){
                        friend.setNewMessage(content);
                    }
                    if (type == 2){
                        friend.setNewMessage("[图片]");
                    }
                    if (type == 3){
                        friend.setNewMessage("[文件]");
                    }
                    if (type == 4){
                        friend.setNewMessage("[语音]");
                    }
                    if (type == 5){

                        friend.setNewMessage("[帖子]");
                    }
                    friendMapper.update(friend,wrapper);
                    jsonObject.put("newMessage",friend.getNewMessage());





                    //发给自己
                    mqttGateway.sendToMqtt("BACK/SRV000/"+sendUserId+"/"+receiveUserId,jsonObject.toString());
                    //发给别人
                    mqttGateway.sendToMqtt("BACK/SRV000/" + receiveUserId+"/" + sendUserId,jsonObject.toString());

                }

            }catch (Exception e){
                e.printStackTrace();
            }
        };
    }
}
