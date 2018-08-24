package com.hai.commons.process.quartz.task.impl;

import com.hai.commons.process.quartz.model.dto.ETaskHandleResult;
import com.hai.commons.process.quartz.model.dto.TimedTaskAddDto;
import com.hai.commons.process.quartz.model.dto.TimedTaskDto;
import com.hai.commons.process.quartz.task.IQuartzHandler;
import com.hai.commons.process.quartz.task.ITaskHandler;
import com.hai.commons.process.quartz.task.util.TaskUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by o-zhengzhenhai on 2018/8/23.
 */
@Service
public class TaskHandlerImpl implements ITaskHandler {

    @Autowired
    private IQuartzHandler quartzHandler;

    @Autowired
    private TaskUtil taskUtil;

    @Override
    public TimedTaskDto addTask(TimedTaskAddDto timedTaskAddDto) {
        /**
         * 1.校验入参参数是否合法
         * 2.封装TimedTaskDto类,将该任务存入数据库并向quartz框架注册该任务
         * 3.封装出参
         */
        TimedTaskDto timedTaskDto = new TimedTaskDto();
        BeanUtils.copyProperties(timedTaskAddDto,timedTaskDto);
        timedTaskDto.setTaskId(taskUtil.getUUID());
        //TODO 数据存入数据库
        Date nextFireTime = quartzHandler.addTask(timedTaskDto);
        timedTaskDto.setNextFireTime(nextFireTime);
        return timedTaskDto;
    }

    @Override
    public ETaskHandleResult updateTask(TimedTaskDto timedTaskDto) {
        quartzHandler.updateTask(timedTaskDto);
        return ETaskHandleResult.success;
    }

    @Override
    public ETaskHandleResult deleteTask(String taskId) {
        return null;
    }

    @Override
    public TimedTaskDto getTaskByTaskId(String taskId) {
        return null;
    }

    @Override
    public List<TimedTaskDto> getAllTasks() {
        return null;
    }
}
