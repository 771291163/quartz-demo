package com.hai.commons.process.quartz.task.impl;

import com.hai.commons.process.quartz.job.RAMJob;
import com.hai.commons.process.quartz.model.ETimedTaskTaskCycle;
import com.hai.commons.process.quartz.model.TimedTaskConstant;
import com.hai.commons.process.quartz.model.dto.ETaskHandleResult;
import com.hai.commons.process.quartz.model.dto.TaskType;
import com.hai.commons.process.quartz.model.dto.TaskTypeDto;
import com.hai.commons.process.quartz.model.dto.TimedTaskDto;
import com.hai.commons.process.quartz.task.IQuartzHandler;
import com.hai.commons.process.quartz.task.util.TaskUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by o-zhengzhenhai on 2018/8/23.
 */
@Service
public class QuartzHandlerImpl implements IQuartzHandler{

    private Scheduler scheduler;

    @Autowired
    private TaskUtil taskUtil;

    /**
     * Quartz框架是否初始化
     */
    private AtomicBoolean isInitQuartz = new AtomicBoolean(false);

    @Override
    public void iniTask() throws SchedulerException {
        if (!isInitQuartz.get()){//没有初始化Quartz框架信息
            //加载调度配置文件
            try{
                startScheduler();
            }catch (SchedulerException e){
                //处理异常
            }
            isInitQuartz.set(true);
            return;
        }
        if(!scheduler.isStarted()){//调度器没有启动
            scheduler.start();
            isInitQuartz.set(true);
        }
    }

    /**
     * 加载Quartz配置文件启动scheduler对象
     */
    private void startScheduler() throws SchedulerException {
        //1.创建Scheduler的工厂
        SchedulerFactory sf = new StdSchedulerFactory();
        //2.从工厂中获取调度器实例
        scheduler = sf.getScheduler();
        scheduler.start();
    }

    @Override
    public Date addTask(TimedTaskDto timedTaskDto) throws ClassNotFoundException, SchedulerException {
        Date taskDate = null;
        String taskId = timedTaskDto.getTaskId();
        String jobName = TaskUtil.getJobName(taskId);
        String groupName = TaskUtil.getGroupName(taskId);
        String triggerName = TaskUtil.getTriggerName(taskId);
        int taskTypeId = timedTaskDto.getTaskType();
        TaskType taskType = taskUtil.getTaskTypeById(taskTypeId);
        if (null == taskType || ObjectUtils.isEmpty(taskType.getCallBackClass())){
            return taskDate;
        }
        String callBackClassName = taskType.getCallBackClass();
        Date startTime = timedTaskDto.getStartTime();
        Date endTime = timedTaskDto.getEndTime();
        Trigger trigger = getTrigger(timedTaskDto,triggerName,groupName,startTime,endTime);
        Class<Job> jobClass = null;
        try{
            jobClass = (Class<Job>) Class.forName(callBackClassName);
        }catch (ClassCastException e){
            //處理異常
        }
        Map<String,Object> map = new HashMap<>();
        map.put(TimedTaskConstant.TASK_ID,taskId);
        if(null != timedTaskDto.getTaskPrarmeter()){
            map.put(TimedTaskConstant.TASK_PARAMETER,timedTaskDto.getTaskPrarmeter());
        }
        JobDataMap jobDataMap = new JobDataMap(map);
        JobDetail job = newJob(jobClass).withIdentity(jobName,groupName).usingJobData(jobDataMap).requestRecovery(true).build();
        this.deleteTask(taskId);
        return scheduler.scheduleJob(job, trigger);
    }

    /**
     * 获取触发器<br>
     *     <li>如果taskCycle是日、月、周,采用cronSchedule生成触发器;</li>
     *     <li>如果taskCycle是间隔秒、间隔分、间隔小时采用simpleSchedule生成触发器;</li>
     *     <li>如果是间隔日、间隔月采用calendarIntervalSchedule生成触发器</li>
     *
     * @param timedTaskDto
     * @param triggerName
     * @param groupName
     * @param startTime
     * @param endTime
     * @return
     */
    private Trigger getTrigger(TimedTaskDto timedTaskDto, String triggerName, String groupName, Date startTime, Date endTime) {
        Trigger trigger = null;
        ETimedTaskTaskCycle taskCycle = ETimedTaskTaskCycle.getEnumu(timedTaskDto.getTaskCycle().intValue());
        int taskCycleValue = timedTaskDto.getTaskCycleVal() == null ? 0 : timedTaskDto.getTaskCycleVal();
        switch (taskCycle){
            case day:
            case month:
            case week:
            case lastDayOfMonth:
                trigger = newTrigger().withIdentity(triggerName,groupName).withSchedule(
                        cronSchedule(timedTaskDto.getCronExpression())).startAt(startTime).endAt(endTime).build();
                break;
            case interval_second:
                trigger = newTrigger().withIdentity(triggerName,groupName).withSchedule(
                        simpleSchedule().withIntervalInSeconds(taskCycleValue).repeatForever()).startAt(startTime).endAt(endTime).build();
                break;
            case interval_minute:
                trigger = newTrigger().withIdentity(triggerName,groupName).withSchedule(
                        simpleSchedule().withIntervalInMinutes(taskCycleValue).repeatForever()).startAt(startTime).endAt(endTime).build();
                break;
            case interval_hour:
                trigger = newTrigger().withIdentity(triggerName,groupName).withSchedule(
                        simpleSchedule().withIntervalInHours(taskCycleValue).repeatForever()).startAt(startTime).endAt(endTime).build();
                break;

            case interval_day:
                trigger = newTrigger().withIdentity(triggerName,groupName).withSchedule(
                        calendarIntervalSchedule().withIntervalInDays(taskCycleValue)).startAt(startTime).endAt(endTime).build();
                break;
            case interval_month:
                trigger = newTrigger().withIdentity(triggerName,groupName).withSchedule(
                        calendarIntervalSchedule().withIntervalInMonths(taskCycleValue)).startAt(startTime).endAt(endTime).build();
                break;
            case startNow:
                trigger = newTrigger().withIdentity(triggerName,groupName).startNow().build();
                break;
            case onceExe:
                trigger = newTrigger().withIdentity(triggerName,groupName).startAt(startTime).withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(0)).build();
                break;
           default:
               trigger = newTrigger().withIdentity(triggerName,groupName).withSchedule(
                       cronSchedule(timedTaskDto.getCronExpression())).startAt(startTime).endAt(endTime).build();
               break;
        }
        return trigger;
    }

    @Override
    public ETaskHandleResult deleteTask(String taskId) {
        return null;
    }

    @Override
    public ETaskHandleResult pauseTask(String taskId) {
        return null;
    }

    @Override
    public ETaskHandleResult resumeTask(String taskId) {
        return null;
    }

    @Override
    public Date updateTask(TimedTaskDto timedTaskDto) {
        return null;
    }

    @Override
    public Date getPreviousFireDate(String taskId) {
        return null;
    }

    @Override
    public Date getNextFireDate(String taskId) {
        return null;
    }
}
