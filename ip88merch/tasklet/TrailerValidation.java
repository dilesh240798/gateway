package com.scb.scroe.gateway.ip88merch.tasklet;

import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.scb.scroe.gateway.ip88merch.constant.MerchConstant;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.scb.scroe.gateway.utils.ReaderWriterUtils;
import com.scb.scroe.gateway.constant.BatchConstant;

public class TrailerValidation implements Tasklet, StepExecutionListener{
	private StepExecution stepExecution;

	@Override
	public void beforeStep(StepExecution stepExecution) {	
	}
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if (stepExecution.getExitStatus().getExitCode().equals(BatchConstant.EXIT_STATUS_END))
            return new ExitStatus(BatchConstant.EXIT_STATUS_END);
        return null;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		String fileName = this.stepExecution.getJobParameters().getString(BatchConstant.IP88MERCH_SRC_FILEPATH);
		Path path =Paths.get (fileName);
        Optional<String> lastLine = ReaderWriterUtils.readLastLineFromFile(path);
        if (!lastLine.isPresent()) return terminateJob();
        BigInteger nLines = ReaderWriterUtils.countNumberOfLinesInFile(path);
        if (!isTrailerValid(lastLine.get(), nLines)) return terminateJob();
        return RepeatStatus.FINISHED;
	}
	private boolean isTrailerValid(String trailer, BigInteger nLines) {
        String[] trailerValues = trailer.split(MerchConstant.IP88MERCH_DELIMITER);
        if (trailerValues.length != 2) return false;
        if (trailerValues[0].toCharArray()[trailerValues[0].length() - 1] != 'T')
            return false;
        Long val = 0L;
        try {
            val = Long.valueOf(trailerValues[1]);
        } catch (Exception e) {
            return false;
        }
        this.stepExecution.getJobExecution().getExecutionContext().putLong(MerchConstant.TOTAL_ENTRIES, val);
        return nLines.subtract(BigInteger.ONE).equals(BigInteger.valueOf(val));
    }

    private RepeatStatus terminateJob() {
        this.stepExecution.setExitStatus(new ExitStatus(BatchConstant.EXIT_STATUS_END));
        return RepeatStatus.FINISHED;
    }

		
}
