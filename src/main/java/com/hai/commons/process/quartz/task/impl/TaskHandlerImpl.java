package com.hai.commons.process.quartz.task.impl;

import com.hai.commons.process.quartz.model.bean.TblTimedTaskEntity;
import com.hai.commons.process.quartz.model.dto.ETaskHandleResult;
import com.hai.commons.process.quartz.model.dto.TimedTaskAddDto;
import com.hai.commons.process.quartz.model.dto.TimedTaskDto;
import com.hai.commons.process.quartz.task.IQuartzHandler;
import com.hai.commons.process.quartz.task.ITaskHandler;
import com.hai.commons.process.quartz.task.service.ITimedTaskService;
import com.hai.commons.process.quartz.task.util.TaskUtil;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private ITimedTaskService timedTaskService;

    @Autowired
    private TaskUtil taskUtil;

    @Override
    public TimedTaskDto addTask(TimedTaskAddDto timedTaskAddDto) throws SchedulerException {
        /**
         * 1.校验入参参数是否合法
         * 2.封装TimedTaskDto类,将该任务存入数据库并向quartz框架注册该任务
         * 3.封装出参
         */
        TimedTaskDto timedTaskDto = new TimedTaskDto();
        BeanUtils.copyProperties(timedTaskAddDto,timedTaskDto);
        timedTaskDto.setTaskId(taskUtil.getUUID());
        Date nextFireTime = quartzHandler.addTask(timedTaskDto);
        timedTaskDto.setNextFireTime(nextFireTime);
        return timedTaskService.addTask(timedTaskDto);
    }

    @Override
    public ETaskHandleResult updateTask(TimedTaskDto timedTaskDto) {
        quartzHandler.updateTask(timedTaskDto);
        timedTaskService.updateTask(timedTaskDto);
        return ETaskHandleResult.success;
    }

    @Override
    public ETaskHandleResult deleteTask(String taskId) {
        timedTaskService.deleteTask(taskId);
        return quartzHandler.deleteTask(taskId);

    }

    @Override
    public TimedTaskDto getTaskByTaskId(String taskId) {
        TimedTaskDto timedTaskDto = new TimedTaskDto();
        TblTimedTaskEntity tblTimedTaskEntity = timedTaskService.getTaskById(taskId);
        BeanUtils.copyProperties(tblTimedTaskEntity,timedTaskDto);
        return timedTaskDto;
    }

    @Override
    public List<TimedTaskDto> getAllTasks() {
        List<TimedTaskDto> timedTaskDtos = new ArrayList<>();
        List<TblTimedTaskEntity> timedTaskEntities = timedTaskService.getAllTasks();
        for (TblTimedTaskEntity timedTaskEntity: timedTaskEntities) {
            TimedTaskDto timedTaskDto = new TimedTaskDto();
            BeanUtils.copyProperties(timedTaskEntity, timedTaskDto);
            timedTaskDtos.add(timedTaskDto);
        }
        return timedTaskDtos;
    }

}
