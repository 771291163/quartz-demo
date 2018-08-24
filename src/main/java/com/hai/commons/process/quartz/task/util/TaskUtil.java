package com.hai.commons.process.quartz.task.util;


import com.hai.commons.process.quartz.model.dto.TaskTypeDto;
import com.hai.commons.process.quartz.model.dto.TaskType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by o-zhengzhenhai on 2018/8/23.
 */
@Component
public class TaskUtil {

    /**
     * 从xml中获取支持的任务类型集合
     * @return
     */
    public List<TaskType> getTaskTypes(){
        TaskTypeDto list = (TaskTypeDto) XsteamUtil.toBean(TaskTypeDto.class, this.getClass().getResourceAsStream("/task/taskServiceTaskType.xml"));
        return list.getTaskTypeList();
    }

    /**
     * 根据任务类型编号获取该任务类型的对象
     * @param taskTypeId
     * @return
     */
    public TaskType getTaskTypeById(int taskTypeId){
        for (TaskType taskType: this.getTaskTypes()) {
            if(taskTypeId == taskType.getTaskTypeId()){
                return taskType;
            }
        }
        return null;
    }

    /**
     * 生成调度任务ID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 根据任务ID获取触发器名称
     * @param taskId
     * @return
     */
    public static String getJobName(String taskId){
        return "job_" + taskId;
    }

    /**
     * 根据任务ID获取触发器名称
     * @param taskId
     * @return
     */
    public static String getGroupName(String taskId){
        return  "group_" + taskId;
    }

    /**
     * 根据任务ID获取触发器名称
     * @param taskId
     * @return
     */
    public static String getTriggerName(String taskId){
        return  "trigger_" + taskId;
    }
}
