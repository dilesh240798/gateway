package com.scb.scroe.gateway.ip88transaction.validationsteps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;
import static java.time.temporal.ChronoField.*;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.scb.scroe.gateway.constant.BatchConstant;


public class HeaderValidationTasklet implements Tasklet, StepExecutionListener {

	private StepExecution stepExecution;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if (stepExecution.getExitStatus().getExitCode().equals(BatchConstant.EXIT_STATUS_END))
            return new ExitStatus(BatchConstant.EXIT_STATUS_END);
        return null;
	}

	@Override
	public void beforeStep(StepExecution stepExceution) {
		this.stepExecution = stepExceution;
		
	}

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		String filePath = chunkContext.getStepContext().getStepExecution().getJobParameters().getString(BatchConstant.IP88TRANSACTION_SRC_FILEPATH);
		BufferedReader in = new BufferedReader(new FileReader(Paths.get(filePath).toFile()));
		Optional<String> header = in.lines().findFirst();
        in.close();
        System.out.println(header);
        System.out.println(header.get());
        System.out.println("read the header");
        if (!header.isPresent()) return terminateJob();
        System.out.println("it is presnt");
        if (!isHeaderValid(header.get())) {
            return terminateJob();
        }
        return RepeatStatus.FINISHED;
	}
	
	private boolean isHeaderValid(String header) {
		System.out.println(header);
        String[] headerValues = header.split("[|]");
        System.out.println(headerValues[0]);
        System.out.println("is headervalid");
        System.out.println(headerValues);
        System.out.println(headerValues.length);
        if (headerValues.length != 2) return false;
        System.out.println("No length error");
        if (headerValues[0].equalsIgnoreCase("01"))
            return false;
        System.out.println("1st value correct");
        try {
        	System.out.println("Trying");
            LocalDate hdate = LocalDate.parse(headerValues[1], new DateTimeFormatterBuilder()
                    .appendValue(YEAR, 4)  
                    .appendValue(MONTH_OF_YEAR, 2)
                    .appendValue(DAY_OF_MONTH, 2)
                    .toFormatter());            
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private RepeatStatus terminateJob() {
        this.stepExecution.setExitStatus(new ExitStatus(BatchConstant.EXIT_STATUS_END));
        return RepeatStatus.FINISHED;
    }

}
