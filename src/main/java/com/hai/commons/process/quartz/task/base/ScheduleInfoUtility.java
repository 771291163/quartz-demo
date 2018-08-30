package com.hai.commons.process.quartz.task.base;

import java.text.MessageFormat;

/**
 * Created by o-zhengzhenhai on 2018/8/30.
 */
public class ScheduleInfoUtility {

    private boolean isDay;

    private boolean isWeek;

    private boolean isMonth;

    private boolean isLastDayOfMonth;

    private String dayOfWeek = null;

    private String dayOfMonth = null;

    private String seconds = "0";

    private String minutes = "0";

    private String hours = "0";

    /**
     * 时间格式必须为"hh:mm:ss" ,传入该方法后进行解析，即可设置表达式所需的时分秒信息
     * @param timeStr
     */
    public void setTimeValue(String timeStr){
        if(null != timeStr && timeStr.length() == 8){
            String[] str = timeStr.split(":");
            hours = str[0];
            minutes = str[1];
            seconds = str[2];
        }
    }

    /**
     * 获取时分秒信息
     * @return hh:mm:ss
     */
    public String getTimeValue(){
        return hours + ":" + minutes + ":" + seconds;
    }

    /**
     * 通过构造器设定
     * @param formatStr 表达式
     */
    public ScheduleInfoUtility(String formatStr){
        String[] scheduleElement = formatStr.split(" ");
        seconds = scheduleElement[0];
        minutes = scheduleElement[1];
        hours = scheduleElement[2];
        if("*".equals(scheduleElement[3])){
            isDay = true;
        }else if ("?".equals(scheduleElement[3])){
            isWeek = true;
            dayOfWeek = scheduleElement[5];
        }else {
            isMonth = true;
            dayOfMonth = scheduleElement[3];
        }
    }

    /**
     * 月末任务构造
     * @param isLastDayOfMonth 是否月末任务
     * @param timeStr 执行时间： hh:mm:ss
     */
    public ScheduleInfoUtility(boolean isLastDayOfMonth, String timeStr) {
        this.isLastDayOfMonth = isLastDayOfMonth;
        this.setTimeValue(timeStr);
    }

    /**
     * 通过构造器设定所有的信息
     * @param isDay 是否按天
     * @param isWeek 是否按周
     * @param isMonth 是否按月
     * @param dayOfWeek 指定周中的某一天
     * @param dayOfMonth 指定月中的某一天
     * @param timeStr 包含时分秒的字符串，格式: "hh:mm:ss"
     */
    public ScheduleInfoUtility(boolean isDay, boolean isWeek, boolean isMonth, String dayOfWeek, String dayOfMonth, String timeStr) {
        this.isDay = isDay;
        this.isWeek = isWeek;
        this.isMonth = isMonth;
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = dayOfMonth;
        this.setTimeValue(timeStr);
    }

    public String getCronExpression() {
        String formatStr = null;
        String timeStr = "{0} {1} {2}";
        String dateStr = "{0} {1} {2}";
        String formatTimeStr = MessageFormat.format(timeStr, seconds, minutes, hours);
        if(isDay){
            formatStr = formatTimeStr + " " + MessageFormat.format(dateStr, "*", "*", "?");
        }else if(isWeek && null != dayOfWeek){
            formatStr = formatTimeStr + " " + MessageFormat.format(dateStr,"?", "*", dayOfWeek);
        }else if(isMonth && null != dayOfMonth ){
            formatStr = formatTimeStr + " " + MessageFormat.format(dateStr,dayOfMonth, "*", "?");
        }else if(isLastDayOfMonth){
            formatStr = formatTimeStr + " " + MessageFormat.format(dateStr,"L", "*", "?");
        }else {
            return "0 0 0 * * ?";
        }
        return formatStr;
    }
}
