package com.hai.commons.process.quartz.task;

import com.hai.commons.process.quartz.model.dto.ETaskHandleResult;
import com.hai.commons.process.quartz.model.dto.TimedTaskAddDto;
import com.hai.commons.process.quartz.model.dto.TimedTaskDto;
import org.quartz.SchedulerException;

import java.util.Date;
import java.util.List;

/**
 * Created by o-zhengzhenhai on 2018/8/23.
 */
public interface IQuartzHandler {

    /**
     * 系统启动后初始化数据库中的调度任务
     */
    void iniTask() throws SchedulerException;

    /**
     * 增加一个定时任务
     * @param timedTaskDto
     * @return 下次执行时间
     */
    Date addTask(TimedTaskDto timedTaskDto) throws SchedulerException;

    /**
     * 删除一个调度任务
     * @param taskId
     * @return
     */
    ETaskHandleResult deleteTask(String taskId);

    /**
     * 暂停一个调度任务
     * @param taskId
     * @return
     */
    ETaskHandleResult pauseTask(String taskId);

    /**
     * 恢复一个调度任务
     * @param taskId
     * @return
     */
    ETaskHandleResult resumeTask(String taskId);

    /**
     * 更新调度任务数据
     * @param timedTaskDto
     * @return 下次执行时间
     */
    Date updateTask(TimedTaskDto timedTaskDto);

    /**
     * 获取上一次执行时间
     * @param taskId
     * @return
     */
    Date getPreviousFireDate(String taskId);

    /**
     * 获取下一次执行时间
     * @param taskId
     * @return
     */
    Date getNextFireDate(String taskId);

    List<String> getJobNames() throws SchedulerException;
}
