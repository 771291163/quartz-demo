package com.hai.commons.process.quartz.task.service;

import com.hai.commons.process.quartz.model.dto.TimedTaskDto;

import java.util.List;

/**
 * Created by o-zhengzhenhai on 2018/8/24.
 */
public interface ITimedTaskService {

    TimedTaskDto addTask(TimedTaskDto timedTaskDto);

    void deleteTask(String taskId);

    void updateTask(TimedTaskDto timedTaskDto);

    TimedTaskDto getTaskById(String taskId);

    List<TimedTaskDto> getAllTasks();
}
