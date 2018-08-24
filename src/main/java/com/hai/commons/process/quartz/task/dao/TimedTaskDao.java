package com.hai.commons.process.quartz.task.dao;

import com.hai.commons.process.quartz.model.bean.TblTimedTaskEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by o-zhengzhenhai on 2018/8/24.
 */
public interface TimedTaskDao {

    @Insert("insert into TBL_TIMEDTASK_ENTITY " +
            "(TASK_ID,TASK_NAME,TASK_STATUS,TASK_TYPE,CRON_EXPRESSION,CREATOR,CREATE_TIME,START_TIME,END_TIME,EXE_TIME,TASK_CYCLE,TASK_CYCLE_VALUE,TASK_PARAMETER) " +
            "values" +
            "(#{taskId},#{taskName},#{taskStatus},#{taskType},#{cronExpression}.#{creator},#{createTime},#{startTime},#{endTime},#{exeTime},#{taskCycle},#{taskCycleValue},#{taskParameter})")
    public void addTask(TblTimedTaskEntity tblTimedTaskEntity);

    @Delete("delete from TBL_TIMEDTASK_ENTITY where task_id=#{0}")
    public void deleteTaskById(String taskId);

    /*@Update("update TBL_TIMEDTASK_ENTITY set username=#{username},password=#{password},status=#{status} where id=#{id}")
    public void updateTask(TblTimedTaskEntity tblTimedTaskEntity);*/

    @Select("select * from TBL_TIMEDTASK_ENTITY where task_id=#{0}")
    public TblTimedTaskEntity getTaskById(String taskId);

    @Select("select * from TBL_TIMEDTASK_ENTITY")
    public List<TblTimedTaskEntity> queryAllTasks();

}
