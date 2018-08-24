package com.hai.commons.process.quartz.model.dto;

import java.util.Date;

/**
 * Created by o-zhengzhenhai on 2018/8/23.
 */
public class TimedTaskAddDto {

    private String taskName;//任务名称

    private int taskType;//任务类型

    private String creater;//创建者

    private Date startTime;//开始时间

    private Date endTime;//结束时间

    private String exeTime;//执行时间 HH:mm:ss

    private Integer taskCycle;//任务周期单位

    private Integer taskCycleVal;//任务周期值

    private Object taskPrarmeter;// 待转化对象

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getExeTime() {
        return exeTime;
    }

    public void setExeTime(String exeTime) {
        this.exeTime = exeTime;
    }

    public Integer getTaskCycle() {
        return taskCycle;
    }

    public void setTaskCycle(Integer taskCycle) {
        this.taskCycle = taskCycle;
    }

    public Integer getTaskCycleVal() {
        return taskCycleVal;
    }

    public void setTaskCycleVal(Integer taskCycleVal) {
        this.taskCycleVal = taskCycleVal;
    }

    public Object getTaskPrarmeter() {
        return taskPrarmeter;
    }

    public void setTaskPrarmeter(Object taskPrarmeter) {
        this.taskPrarmeter = taskPrarmeter;
    }
}
