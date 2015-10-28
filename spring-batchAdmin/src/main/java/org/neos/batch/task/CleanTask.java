package org.neos.batch.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class CleanTask implements Tasklet,InitializingBean  {

	private Resource resourceOut;
	
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("CLEANING.........");
		
		 if(resourceOut.getFile().exists()){
			  System.out.println("BORRANDOOOOOO...");
			  resourceOut.getFile().delete();
		  }
		return RepeatStatus.FINISHED;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setResourceOut(Resource resourceOut) {
		this.resourceOut = resourceOut;
	}
	

}
