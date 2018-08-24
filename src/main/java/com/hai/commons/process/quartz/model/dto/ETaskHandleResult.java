package com.hai.commons.process.quartz.model.dto;

/**
 * Created by o-zhengzhenhai on 2018/8/23.
 */
public enum ETaskHandleResult {

    /**
     * 成功
     */
    success(2000),

    /**
     * 参数异常
     */
    parameterFail(4001),

    /**
     * 数据库异常
     */
    dbProcessFail(4002),

    /**
     * Quartz框架处理定时任务异常
     */
    quartzException(4003),;

    /**
     * 枚举值
     */
    private int value;

    private ETaskHandleResult(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
