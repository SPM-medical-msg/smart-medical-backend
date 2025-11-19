package com.xxx.modules.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.*;

@Slf4j
public class TimeUtil {



    private static final String YYYY_MM_DD = "yyyyMMdd";

    public static String dateRandom18() {

        //获取当前年月日
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat(YYYY_MM_DD);
        String date = yyyyMMdd.format(new Date());

        //获取时间戳后6位
        String timeMillis = String.valueOf(System.currentTimeMillis());
        String fiveNumber = timeMillis.substring(timeMillis.length() - 6);

        //获取随机数后4位
        String tempRandom = String.valueOf(Math.random());
        String number = tempRandom.substring(tempRandom.length() - 4);

        //生成18位随机时间戳数字
        return date + fiveNumber + number;
    }
    public static List<String> pastDay(){
        ArrayList<String> pastDaysList = new ArrayList<>();
        try {
            //我这里传来的时间是个string类型的，所以要先转为date类型的。
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(getCurrentDay());
            for (int i = 6; i >= 0; i--) {
                pastDaysList.add(getPastDate(i,date));
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        return pastDaysList;
    }
    public static String getPastDate(int past,Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - past);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(today);
        return result;
    }



    /**
     * 获取当前具体时间
     * @return
     */
    public static String getCurrentTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 获取当天日期
     */
    public static String getCurrentDay(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }


    /**
     * 获取当天月份
     */
    public static int getCurrentMonth(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }


    /**
     * 获取当天年份
     */
    public static String getCurrentYear(){
        return new SimpleDateFormat("yyyy").format(new Date());
    }



    /**
     * 计算两个时间相差的秒数(endTime为大时间,startTime为小时间,这样传入相差的秒数是正整数,如果反了,则是负数)
     * @param startTime(小一点的时间)
     * @param endTime(大一点的时间)
     * @return
     * @throws ParseException
     */
    public static long getStartTimeAndEndTimeGapSecond(String startTime, String  endTime) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long eTime = df.parse(endTime).getTime();
        long sTime = df.parse(startTime).getTime();
        return (eTime - sTime) / 1000;
    }


    public static boolean compareTime(String time1,String time2) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date now = df.parse(df.format(new Date()));
        Date begin = df.parse(time1);
        Date end = df.parse(time2);
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(now);
        Calendar beginTime = Calendar.getInstance();
        beginTime.setTime(begin);
        Calendar endTime = Calendar.getInstance();
        endTime.setTime(end);
        return nowTime.before(endTime) && nowTime.after(beginTime);
    }

    /**
     * 返回值为1,代表传入的时间大于当前时间,返回值为0,代表传入的时间和当前时间相等,返回值为-1,代表传入的时间小于当前时间
     * @param otherTime
     * @return
     * @throws ParseException
     */
    public static int compareNowTimeAndOtherTime(String otherTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(otherTime);
        Date date2 = new Date();
//        log.info("当前时间{}",sdf.format(date2));
        return date2.compareTo(date1);
    }


    /**
     * 获取今天是星期几
     * @return
     */
    public static int getCurrentWeekDay(){
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK) -1;
//        System.out.println(weekday);
        if (weekday == 0){
            return 7;
        }else {
            return weekday;
        }
    }


    /**
     * 获取多少s之前的时间
     * @param time
     * @param seconds
     * @return
     */
    public static String getBeforeSecondsTime(String time, int seconds) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= dateFormat.parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return dateFormat.format(calendar.getTime());
    }


    public static String getTimeBeforeFiveMinute(Integer min){
        Date date =  new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        System.out.println("现在时间："+df.format(date));
//        System.out.println("昨天该时间：" + );
        return df.format(new Date(date.getTime() - 60 * 1000 * min));

    }
    public static String getRandomString(int length){
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM";
        //由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //长度为几就循环几次
        for(int i=0; i<length; ++i){
            //产生0-61的数字
            int number=random.nextInt(52);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

    public static void main(String[] args) throws ParseException {

        String startTime = "2023-10-23 21:00:00";
        String endTime = "2023-10-23 21:02:30";
        String dateTime="2023-10-31 21:02:30";
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= format.parse(dateTime);
        System.out.println(date);

        String beforeSecondsTime = getBeforeSecondsTime(dateTime, 1800);
        System.out.println(beforeSecondsTime);


    }



    /**
     * 方法1：使用Java内置本地化输出中文星期
     * @param dateStr 格式必须为 yyyy-MM-dd（例如 "2023-10-01"）
     * @return 中文星期名称（如 "星期一"）
     * @throws DateTimeParseException 输入格式不合法或日期无效时抛出异常
     */
    public static String getWeekdayFromString(String dateStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.CHINA);
    }

    /**
     * 方法2：手动映射星期名称（不依赖本地化）
     * @param dateStr 格式必须为 yyyy-MM-dd
     * @return 中文星期名称
     */
    public static String getWeekdayManual(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        String[] weekDays = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        return weekDays[date.getDayOfWeek().getValue() - 1];
    }
// 在TimeUtil类中添加
    /**
     * 获取当前是星期几（中文）
     * @return 星期一到星期日
     */
    public static String getCurrentWeekDayInChinese() {
        int weekDayNum = getCurrentWeekDay(); // 使用已有方法
        String[] weekDays = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        return weekDays[weekDayNum - 1];
    }

    /**
     * 获取当前小时
     * @return 0-23
     */
    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}
