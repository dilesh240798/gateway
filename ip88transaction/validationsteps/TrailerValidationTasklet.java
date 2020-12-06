package com.scb.scroe.gateway.ip88transaction.validationsteps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Optional;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
//import org.apache.commons.io.input.ReversedLinesFileReader;

import com.scb.scroe.gateway.constant.BatchConstant;
import com.scb.scroe.gateway.ip88transaction.constant.TransactionConstant;

public class TrailerValidationTasklet implements Tasklet, StepExecutionListener {

	private StepExecution stepExecution;

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext) throws Exception {
		String fileName = chunkContext.getStepContext().getStepExecution().getJobParameters().getString(BatchConstant.IP88TRANSACTION_SRC_FILEPATH);
		
	    BufferedReader input = new BufferedReader(new FileReader(fileName));
	    String last = null;
	    String line;

	    while ((line = input.readLine()) != null) { 
	        last = line;
	    }
		
	    Optional<String> lastLine = Optional.ofNullable(last);
		
        if (!lastLine.isPresent()) return terminateJob();
        BigInteger nLines = BigInteger.ZERO;
        FileChannel channel = FileChannel.open(Paths.get(fileName), StandardOpenOption.READ);
        ByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        while (byteBuffer.hasRemaining()) {
            byte currentByte = byteBuffer.get();
            if (currentByte == '\n')
                nLines = nLines.add(BigInteger.ONE);
        }
        channel.close();
        if (!isTrailerValid(lastLine.get(), nLines)) return terminateJob();
        return RepeatStatus.FINISHED;
	}


    private boolean isTrailerValid(String trailer, BigInteger nLines) {
        String[] trailerValues = trailer.split("[|]");
        if (trailerValues.length != 2) return false;
      
//        if (trailerValues[0] != "03")
            //return false;
        Long val = 0L;
        try {
            val = Long.valueOf(trailerValues[1]);
        } catch (Exception e) {
            return false;
        }
        this.stepExecution.getJobExecution().getExecutionContext().putLong(TransactionConstant.TRANSACTION_TOTAL_ENTRIES, val);
        System.out.println("Trailervalidations Working");
        return nLines.subtract(BigInteger.ONE).equals(BigInteger.valueOf(val));
    }

    private RepeatStatus terminateJob() {
        this.stepExecution.setExitStatus(new ExitStatus(BatchConstant.EXIT_STATUS_END));
        return RepeatStatus.FINISHED;
    }
    
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if (stepExecution.getExitStatus().getExitCode().equals(BatchConstant.EXIT_STATUS_END))
            return new ExitStatus(BatchConstant.EXIT_STATUS_END);
        return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
		
	}

}
