package com.xxx.modules.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.modules.entity.Alarm;
import com.xxx.modules.entity.Drug;
import com.xxx.modules.mapper.AlarmMapper;
import com.xxx.modules.mapper.DrugMapper;
import com.xxx.modules.mapper.AlarmMapper;
import com.xxx.modules.mapper.DrugMapper;
import com.xxx.modules.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class SpringTaskJob {

    @Autowired
    private DrugMapper drugMapper;
    @Autowired
    private AlarmMapper alarmMapper;

    /**
     * Cron表达式网址:http://cron.ciding.cc/
     * cron = 0/1 * * * * ?(每1s执行一次)
     * cron = 0/30 * * * * ?(每30s执行一次)
     * cron = 0 0/1 * * * ?(每一分钟执行一次)
     * cron = 59 59 23 * * ?(每天晚上23:59:59执行一次)
     * cron = 0 0 0 * * ?(每天00:00:00执行一次)
     */

    @Scheduled(cron ="0/1 * * * * ?")
    public void task(){
        List<Drug> list = drugMapper.selectList(null);
        for (Drug drug:list){
            if (drug.getCount() <=20){
                QueryWrapper<Alarm> wrapper = new QueryWrapper<>();
                wrapper.eq("drug_id",drug.getId()).eq("status",1);
                Alarm alarm = alarmMapper.selectOne(wrapper);
                if (alarm == null){
                    Alarm alarm1 = new Alarm();
                    alarm1.setUpdateTime(TimeUtil.getCurrentTime());
                    alarm1.setCreateTime(TimeUtil.getCurrentTime());
                    alarm1.setDrugId(drug.getId());
                    alarm1.setMessageInfo("商品"+drug.getDrugName()+"库存仅剩余"+drug.getCount()+"个,请尽快补货!");
                    alarm1.setStatus(1);
                    alarmMapper.insert(alarm1);
                }else {
                    alarm.setUpdateTime(TimeUtil.getCurrentTime());
                    alarm.setMessageInfo("商品"+drug.getDrugName()+"库存仅剩余"+drug.getCount()+"个,请尽快补货!");
                    alarm.setStatus(1);
                    alarmMapper.updateById(alarm);
                }
            }
        }
    }

    public static long calculateDaysDifference(String dateStr1, String dateStr2) {
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 解析字符串为LocalDate对象
        LocalDate date1 = LocalDate.parse(dateStr1, formatter);
        LocalDate date2 = LocalDate.parse(dateStr2, formatter);

        // 计算天数差（date2 - date1）
        return ChronoUnit.DAYS.between(date1, date2);
    }



    /**
     * 返回值为1,代表传入的时间大于当前时间,返回值为0,代表传入的时间和当前时间相等,返回值为-1,代表传入的时间小于当前时间
     * @param otherTime
     * @return
     * @throws ParseException
     */
    public static int compareNowTimeAndOtherTime(String otherTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = sdf.parse(otherTime);
            Date date2 = new Date();
//        log.info("当前时间{}",sdf.format(date2));
            return date2.compareTo(date1);
        }catch (Exception e){
            e.printStackTrace();
            return -10;
        }

    }

}
