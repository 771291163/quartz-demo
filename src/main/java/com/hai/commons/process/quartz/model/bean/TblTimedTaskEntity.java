package com.hai.commons.process.quartz.model.bean;

import java.util.Date;

/**
 * Created by o-zhengzhenhai on 2018/8/24.
 */
public class TblTimedTaskEntity {

    /**
     * 主键
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务状态
     */
    private int taskStatus;

    /**
     * 任务类型
     */
    private int taskType;

    /**
     * 表达式
     */
    private String cronExpression;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 执行时间
     */
    private String exeTime;

    /**
     * 执行周期单位
     */
    private int taskCycle;

    /**
     * 执行周期值
     */
    private int taskCycleVal;

    /**
     * 参数
     */
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

    public int getTaskCycleVal() {
        return taskCycleVal;
    }

    public void setTaskCycleVal(int taskCycleVal) {
        this.taskCycleVal = taskCycleVal;
    }

    public Object getTaskParameter() {
        return taskParameter;
    }

    public void setTaskParameter(Object taskParameter) {
        this.taskParameter = taskParameter;
    }
}
