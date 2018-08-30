package com.hai.commons.process.quartz.task;

import com.hai.commons.process.quartz.model.dto.ETaskHandleResult;
import com.hai.commons.process.quartz.model.dto.TimedTaskAddDto;
import com.hai.commons.process.quartz.model.dto.TimedTaskDto;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * Created by o-zhengzhenhai on 2018/8/23.
 */
public interface ITaskHandler {

    /**
     * 增加一个定时任务
     * @param timedTaskAddDto
     * @return
     */
    TimedTaskDto addTask(TimedTaskAddDto timedTaskAddDto) throws SchedulerException;

    /**
     * 更新调度任务数据
     * @param timedTaskDto
     * @return
     */
    ETaskHandleResult updateTask(TimedTaskDto timedTaskDto);

    /**
     * 删除一个调度任务
     * @param taskId
     * @return
     */
    ETaskHandleResult deleteTask(String taskId);

    /**
     * 根据任务ID获取调度任务
     * @param taskId
     * @return
     */
    TimedTaskDto getTaskByTaskId(String taskId);

    /**
     * 获取全部调度任务
     * @return
     */
    List<TimedTaskDto> getAllTasks();

    List<String> getJobNames() throws SchedulerException;
}
