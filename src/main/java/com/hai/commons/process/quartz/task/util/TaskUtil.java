package com.hai.commons.process.quartz.task.util;


import com.hai.commons.process.quartz.model.ETimedTaskTaskCycle;
import com.hai.commons.process.quartz.model.dto.TaskTypeDto;
import com.hai.commons.process.quartz.model.dto.TaskType;
import com.hai.commons.process.quartz.model.dto.TimedTaskDto;
import com.hai.commons.process.quartz.task.base.ScheduleInfoUtility;
import org.quartz.CronTrigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.util.Date;
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

    /**
     * 校验表达式的合法性
     * @param cronExpression
     * @return
     */
    public boolean isValidCronExpression(String cronExpression) {
        boolean result = false;
        CronTriggerImpl trigger = new CronTriggerImpl();
        try {
            trigger.setCronExpression(cronExpression);
            Date firstFireTime = trigger.computeFirstFireTime(null);
            if (null != firstFireTime && firstFireTime.after(new Date())){
                result = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getCronExpression(TimedTaskDto timedTaskDto) {
            String cronExpression = null;
            if(ObjectUtils.isEmpty(timedTaskDto)){
                return cronExpression;
            }
            int taskCycleValue = timedTaskDto.getTaskCycleVal() == null ? 0:timedTaskDto.getTaskCycleVal();
            ETimedTaskTaskCycle taskCycle = ETimedTaskTaskCycle.getEnumu(timedTaskDto.getTaskCycle().intValue());
            if(taskCycle == ETimedTaskTaskCycle.interval_second || taskCycle == ETimedTaskTaskCycle.interval_minute ||
                    taskCycle == ETimedTaskTaskCycle.interval_hour || taskCycle == ETimedTaskTaskCycle.interval_day ||
                    taskCycle == ETimedTaskTaskCycle.interval_month || taskCycle == ETimedTaskTaskCycle.onceExe ||
                    taskCycle == ETimedTaskTaskCycle.startNow){
                return cronExpression;
            }
            boolean isDay = false;
            boolean isWeek = false;
            boolean isMonth = false;
            boolean isLastDayOfMonth = false;
            String dayOfWeek = null;
            String dayOfMonth = null;
            String timeStr = null;
            if(taskCycle == ETimedTaskTaskCycle.day){
                isDay = true;
            }else if(taskCycle == ETimedTaskTaskCycle.week){
                isWeek = true;
                dayOfWeek = String.valueOf(taskCycleValue);
            }else if (taskCycle == ETimedTaskTaskCycle.month){
                isMonth = true;
                dayOfMonth = String.valueOf(taskCycleValue);
            }else if(taskCycle == ETimedTaskTaskCycle.lastDayOfMonth){
                isLastDayOfMonth = true;
            }
            timeStr = timedTaskDto.getExeTime();
            ScheduleInfoUtility utility = null;
            if(isLastDayOfMonth){
                utility = new ScheduleInfoUtility(isLastDayOfMonth, timeStr);
            }else{
                utility = new ScheduleInfoUtility(isDay,isWeek,isMonth,dayOfWeek,dayOfMonth,timeStr);
            }
            cronExpression = utility.getCronExpression();
            return cronExpression;
    }
}
