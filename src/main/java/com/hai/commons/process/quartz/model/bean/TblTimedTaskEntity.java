package com.hai.commons.process.quartz.model.bean;

import java.util.Date;

/**
 * Created by o-zhengzhenhai on 2018/8/24.
 */
public class TblTimedTaskEntity {

    private String taskId;

    private String taskName;

    private int taskStatus;

    private int taskType;

    private String cronExpression;

    private String creator;

    private Date createTime;

    private Date startTime;

    private Date endTime;

    private String exeTime;

    private int taskCycle;

    private int taskCycleValue;

    private Object taskParameter;

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public int getTaskCycle() {
        return taskCycle;
    }

    public void setTaskCycle(int taskCycle) {
        this.taskCycle = taskCycle;
    }

    public int getTaskCycleValue() {
        return taskCycleValue;
    }

    public void setTaskCycleValue(int taskCycleValue) {
        this.taskCycleValue = taskCycleValue;
    }

    public Object getTaskParameter() {
        return taskParameter;
    }

    public void setTaskParameter(Object taskParameter) {
        this.taskParameter = taskParameter;
    }
}
