package com.hai.commons.process.quartz.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by o-zhengzhenhai on 2018/8/23.
 */
public class TimedTaskDto implements Serializable {

    private String taskId;//UUID

    private String taskName;//任务名称

    private int taskStatus;//任务状态

    private int taskType;//任务类型

    private String cronExpression;//执行表达式

    private String creater;//创建者

    private Date createTime;//创建时间

    private Date startTime;//开始时间

    private Date endTime;//结束时间

    private String exeTime;//执行时间 HH:mm:ss

    private Integer taskCycle;//任务周期单位

    private Integer taskCycleVal;//任务周期值

    private Object taskPrarmeter;// 待转化对象

    private Date previousFireTime;// 上次执行时间

    private Date nextFireTime;// 下次执行时间

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getPreviousFireTime() {
        return previousFireTime;
    }

    public void setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    public Date getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }
}
