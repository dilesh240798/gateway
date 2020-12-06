package com.scb.scroe.gateway.ip88transaction;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.scb.scroe.gateway.constant.BatchConstant;

public class DeleteFileTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        String filePath = jobParameters.getString(BatchConstant.IP88TRANSACTION_SRC_FILEPATH);
        //Files.delete(Paths.get(filePath));
        return RepeatStatus.FINISHED;
    }
}