package com.hai.commons.process.quartz.model.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by o-zhengzhenhai on 2018/8/23.
 */
@XStreamAlias("JOB")
public class TaskTypeDto {

    @XStreamImplicit(itemFieldName="JobXmlInfo")
    private List<TaskType> taskTypeList;

    public List<TaskType> getTaskTypeList() {
        return taskTypeList;
    }

    public void setTaskTypeList(List<TaskType> taskTypeList) {
        this.taskTypeList = taskTypeList;
    }
}
