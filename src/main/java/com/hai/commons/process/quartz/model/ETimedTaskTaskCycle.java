package com.hai.commons.process.quartz.model;

/**
 * Created by o-zhengzhenhai on 2018/8/23.
 */
public enum ETimedTaskTaskCycle {

    /**
     * 按日执行
     */
    day(1),

    /**
     * 按周执行
     */
    week(2),

    /**
     * 按月执行
     */
    month(3),

    /**
     * 间隔时间 - 间隔X秒,X取决于taskCycleValue
     */
    interval_second(4),

    /**
     * 间隔时间 - 间隔X分,X取决于taskCycleValue
     */
    interval_minute(5),

    /**
     * 间隔时间 - 间隔X小时,X取决于taskCycleValue
     */
    interval_hour(6),

    /**
     * 间隔时间 - 间隔X天,X取决于taskCycleValue
     */
    interval_day(7),

    /**
     * 间隔时间 - 间隔X月,X取决于taskCycleValue
     */
    interval_month(8),

    /**
     * 立即执行
     */
    startNow(9),

    /**
     * 执行一次
     */
    onceExe(10),

    /**
     * 月末执行
     */
    lastDayOfMonth(11),;

    private int value;

    private ETimedTaskTaskCycle(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * 判断指定的value是否合法
     * @param aValue
     * @return
     */
    public static boolean isValidValue(int aValue){
        for (ETimedTaskTaskCycle item:values()) {
            if(item.getValue() == aValue){
                return true;
            }
        }
        return false;
    }

    /**
     * 通過枚舉值獲取枚舉
     * @param aValue
     * @return
     */
    public static ETimedTaskTaskCycle getEnumu(int aValue) {
        if (!isValidValue(aValue)) {
            return null;
        }
        for (ETimedTaskTaskCycle item : values()) {
            if (item.getValue() == aValue) {
                return item;
            }
        }
        return null;
    }

}
