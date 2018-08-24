package com.hai.commons.process.quartz.model.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

//访问类型，通过字段
@XmlAccessorType(XmlAccessType.FIELD)
@XStreamAlias("JobXmlInfo")
public class TaskType implements Serializable {

    @XStreamAsAttribute
    @XStreamAlias("type")
    private int taskTypeId;//任務類型ID

    @XStreamAsAttribute
    @XStreamAlias("desc")
    private String taskTypeDesc;//任务类型描述

    @XStreamAsAttribute
    @XStreamAlias("classFullName")
    private String callBackClass;//任务回调类全路径

    public int getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(int taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public String getTaskTypeDesc() {
        return taskTypeDesc;
    }

    public void setTaskTypeDesc(String taskTypeDesc) {
        this.taskTypeDesc = taskTypeDesc;
    }

    public String getCallBackClass() {
        return callBackClass;
    }

    public void setCallBackClass(String callBackClass) {
        this.callBackClass = callBackClass;
    }

    @Override
    public String toString() {
        return "TaskTypeDto{" +
                "taskTypeId=" + taskTypeId +
                ", taskTypeDesc='" + taskTypeDesc + '\'' +
                ", callBackClass='" + callBackClass + '\'' +
                '}';
    }
}