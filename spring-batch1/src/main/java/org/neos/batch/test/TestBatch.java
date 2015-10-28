package org.neos.batch.test;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBatch {
	
	public static void main(String[] args) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
	
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("userJob.xml");
	
	JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");
	Job job = (Job) ctx.getBean("processFileJob");
	
	JobExecution execution = jobLauncher.run(job, new JobParameters());
	System.out.println("Exit Status : " + execution.getStatus());

	System.out.println("Done-------------------------------------");
	ctx.close();
	}

}
