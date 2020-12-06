package com.scb.scroe.gateway.ip88merch.tasklet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;

import com.scb.scroe.gateway.ip88merch.constant.MerchConstant;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import static java.time.temporal.ChronoField.*;
import com.scb.scroe.gateway.constant.BatchConstant;

public class HeaderValidation implements Tasklet , StepExecutionListener{
	private StepExecution stepExecution;
	@Override
	public void beforeStep(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
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
        BufferedReader in = new BufferedReader(new FileReader(Paths.get(fileName).toFile()));
        Optional<String> header = in.lines().findFirst();
        in.close();
        if (!header.isPresent()) return terminateJob();
        if (!isHeaderValid(header.get())) {
            return terminateJob();
        }
        return RepeatStatus.FINISHED;
	}
	private boolean isHeaderValid(String header) {
        String[] headerValues = header.split(MerchConstant.IP88MERCH_DELIMITER);
        if (headerValues.length != 6) return false;
        if (!(headerValues[0].charAt(headerValues[0].length() - 1) == 'H' &&
                headerValues[1].equals("MY") &&
                headerValues[2].equals("MRF")))
            return false;
        try {
            LocalDate _ld = LocalDate.parse(headerValues[4], new DateTimeFormatterBuilder()
                    .appendValue(YEAR, 4)
                    .appendLiteral("-")
                    .appendValue(MONTH_OF_YEAR, 2)
                    .appendLiteral("-")
                    .appendValue(DAY_OF_MONTH, 2)
                    .toFormatter());
            LocalDateTime _ldt = LocalDateTime.parse(headerValues[5], new DateTimeFormatterBuilder()
                    .appendValue(YEAR, 2)
                    .appendValue(MONTH_OF_YEAR, 2)
                    .appendValue(DAY_OF_MONTH, 2)
                    .appendValue(HOUR_OF_DAY, 2)
                    .appendValue(MINUTE_OF_HOUR, 2)
                    .appendValue(SECOND_OF_MINUTE, 2)
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
