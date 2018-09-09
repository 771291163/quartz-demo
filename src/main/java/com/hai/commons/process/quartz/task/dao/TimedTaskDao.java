package com.hai.commons.process.quartz.task.dao;

import com.hai.commons.process.quartz.model.bean.TblTimedTaskEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by o-zhengzhenhai on 2018/8/24.
 */
@Repository
@Mapper
public interface TimedTaskDao {

    @Insert("insert into tbl_timedtask_entity " +
            "(TASK_ID,TASK_NAME,TASK_STATUS,TASK_TYPE,CRON_EXPRESSION,CREATOR,CREATE_TIME,START_TIME,END_TIME,EXE_TIME,TASK_CYCLE,TASK_CYCLE_VALUE,TASK_PARAMETER) " +
            "values" +
            "(#{taskId},#{taskName},#{taskStatus},#{taskType},#{cronExpression},#{creator},#{createTime},#{startTime},#{endTime},#{exeTime},#{taskCycle}," +
            "#{taskCycleVal},#{taskParameter})")
    void addTask(TblTimedTaskEntity tblTimedTaskEntity);

    @Delete("delete from tbl_timedtask_entity where task_id = #{taskId}")
    void deleteTaskById(@Param("taskId") String taskId);

    /*@Update("update TBL_TIMEDTASK_ENTITY set username=#{username},password=#{password},status=#{status} where id=#{id}")
    void updateTask(TblTimedTaskEntity tblTimedTaskEntity);*/

    @Select("select * from tbl_timedtask_entity where task_id = #{taskId}")
    @Results({
            @Result(property = "taskId", column = "TASK_ID"),
            @Result(property = "taskName", column = "TASK_NAME"),
            @Result(property = "taskStatus", column = "TASK_STATUS"),
            @Result(property = "taskType", column = "TASK_TYPE"),
            @Result(property = "cronExpression", column = "CRON_EXPRESSION"),
            @Result(property = "creator", column = "CREATOR"),
            @Result(property = "createTime", column = "CREATE_TIME"),
            @Result(property = "startTime", column = "START_TIME"),
            @Result(property = "endTime", column = "END_TIME"),
            @Result(property = "exeTime", column = "EXE_TIME"),
            @Result(property = "taskCycle", column = "TASK_CYCLE"),
            @Result(property = "taskCycleVal", column = "TASK_CYCLE_VALUE")
    })
    TblTimedTaskEntity getTaskById(@Param("taskId") String taskId);

    @Select("select * from tbl_timedtask_entity")
    @Results({
            @Result(property = "taskId", column = "TASK_ID"),
            @Result(property = "taskName", column = "TASK_NAME"),
            @Result(property = "taskStatus", column = "TASK_STATUS"),
            @Result(property = "taskType", column = "TASK_TYPE"),
            @Result(property = "cronExpression", column = "CRON_EXPRESSION"),
            @Result(property = "creator", column = "CREATOR"),
            @Result(property = "createTime", column = "CREATE_TIME"),
            @Result(property = "startTime", column = "START_TIME"),
            @Result(property = "endTime", column = "END_TIME"),
            @Result(property = "exeTime", column = "EXE_TIME"),
            @Result(property = "taskCycle", column = "TASK_CYCLE"),
            @Result(property = "taskCycleVal", column = "TASK_CYCLE_VALUE")
    })
    List<TblTimedTaskEntity> queryAllTasks();

}
