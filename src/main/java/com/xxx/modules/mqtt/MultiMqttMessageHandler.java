package com.xxx.modules.mqtt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class MultiMqttMessageHandler extends AbstractMessageHandler implements Lifecycle {

	private final AtomicBoolean running = new AtomicBoolean();
	private volatile Map<Integer, MessageHandler> mqttHandlerMap;
	private Integer handlerCount = 1;


	@Autowired
	private MqttConfig senderConfig;


	public void setMqttHandlerMap(Map<Integer, MessageHandler> mqttHandlerMap){
		this.mqttHandlerMap = mqttHandlerMap;
	}

	@Override
	public void start() {
		if (!this.running.getAndSet(true)) {
			doStart();
		}
	}


	private void doStart(){
		mqttHandlerMap = new ConcurrentHashMap<>();
		for(int i=0;i<handlerCount;i++){
			mqttHandlerMap.put(i, senderConfig.createMqttOutbound());
		}
	}

	@Override
	public void stop() {
		if (this.running.getAndSet(false)) {
			doStop();
		}
	}

	private void doStop(){
		for(Map.Entry<Integer, MessageHandler> e : mqttHandlerMap.entrySet()){
			MessageHandler handler = e.getValue();
			((MyMqttPahoMessageHandler)handler).doStop();
		}
	}

	@Override
	public boolean isRunning() {
		return this.running.get();
	}

	@Override
	protected void handleMessageInternal(Message<?> message) {
		Random random = new Random();
//		System.out.println(message+"1232");
		MyMqttPahoMessageHandler messageHandler = (MyMqttPahoMessageHandler)mqttHandlerMap.get(random.nextInt(handlerCount));
		messageHandler.handleMessageInternal(message);
	}

}
