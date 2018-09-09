package com.hai.commons.process.quartz.task.service.impl;

import com.hai.commons.process.quartz.model.bean.TblTimedTaskEntity;
import com.hai.commons.process.quartz.model.dto.TimedTaskDto;
import com.hai.commons.process.quartz.task.dao.TimedTaskDao;
import com.hai.commons.process.quartz.task.service.ITimedTaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by o-zhengzhenhai on 2018/8/24.
 */
@Service
public class TimedTaskServiceImpl implements ITimedTaskService {

    @Autowired
    private TimedTaskDao timedTaskDao;

    @Override
    public TimedTaskDto addTask(TimedTaskDto timedTaskDto) {
        TblTimedTaskEntity taskEntity = new TblTimedTaskEntity();
        BeanUtils.copyProperties(timedTaskDto,taskEntity);
        timedTaskDao.addTask(taskEntity);
        return timedTaskDto;
    }

    @Override
    public void deleteTask(String taskId) {
        timedTaskDao.deleteTaskById(taskId);
    }

    @Override
    public void updateTask(TimedTaskDto timedTaskDto){
        timedTaskDao.deleteTaskById(timedTaskDto.getTaskId());
        addTask(timedTaskDto);
    }

    @Override
    public TblTimedTaskEntity getTaskById(String taskId) {

        return timedTaskDao.getTaskById(taskId);
    }

    @Override
    public List<TblTimedTaskEntity> getAllTasks() {
        return timedTaskDao.queryAllTasks();
    }
}
