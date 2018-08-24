package com.hai.commons.process.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by o-zhengzhenhai on 2018/8/17.
 */
public class RAMJob implements Job {

    private static Logger _log = LoggerFactory.getLogger(RAMJob.class);

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        _log.info("Say hello to Quartz" + new Date());
    }

}