package com.hai.commons.process.quartz.controller;

import com.hai.commons.process.quartz.model.dto.TimedTaskAddDto;
import com.hai.commons.process.quartz.model.dto.TimedTaskDto;
import com.hai.commons.process.quartz.task.ITaskHandler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by o-zhengzhenhai on 2018/8/30.
 */
@RestController
public class TaskController {

    @Autowired
    private ITaskHandler taskHandler;

    @PostMapping("add")
    public TimedTaskDto addTask(@RequestBody TimedTaskAddDto timedTaskAddDto){
        try {
            return taskHandler.addTask(timedTaskAddDto);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("getJobNames")
    public List<String> getJobNames(){
        try {
            return taskHandler.getJobNames();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
