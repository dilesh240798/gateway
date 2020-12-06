package com.scb.scroe.gateway.ip88transaction;


import com.scb.scroe.gateway.constant.BatchConstant;
import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CheckFileTasklet implements Tasklet, StepExecutionListener {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        String filePath = jobParameters.getString(BatchConstant.IP88TRANSACTION_SRC_FILEPATH);
        //System.out.println(filePath);
        //System.out.println(Files.exists(Paths.get(filePath)));
        if (!Files.exists(Paths.get(filePath))) {
            chunkContext.getStepContext().getStepExecution().setExitStatus(new ExitStatus("END"));
        }
        
        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
    	//System.out.println("In CheckFileTasklet");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (stepExecution.getExitStatus().equals(new ExitStatus("END"))) {
            return new ExitStatus("END");
        }
        return null;
    }
}
