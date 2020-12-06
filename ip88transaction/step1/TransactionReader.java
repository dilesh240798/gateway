package com.scb.scroe.gateway.ip88transaction.step1;

import java.nio.charset.Charset;

import com.scb.scroe.gateway.ip88transaction.callback.DefaultSkipLineCallback;
import com.scb.scroe.gateway.ip88transaction.constant.TransactionConstant;
import com.scb.scroe.gateway.ip88transaction.mapper.TransactionRecordFieldSetMapper;
import com.scb.scroe.gateway.ip88transaction.mapper.TransactionRecordLineMapper;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

import com.scb.scroe.gateway.constant.BatchConstant;
import com.scb.scroe.gateway.ip88transaction.model.TransactionRecord;


public class TransactionReader extends FlatFileItemReader<TransactionRecord> {
	
    public TransactionReader() {
        super();
        
        TransactionRecordLineMapper<TransactionRecord> lineMapper = new TransactionRecordLineMapper<TransactionRecord>();
       
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer(TransactionConstant.IP88TRANSACTION_DELIMITER));
       
        lineMapper.setFieldSetMapper(new TransactionRecordFieldSetMapper());
       
        this.setName(TransactionConstant.IP88TRANSACTION_READER);
        this.setLineMapper(lineMapper);
        this.setEncoding(Charset.defaultCharset().name());
        this.setLinesToSkip(TransactionConstant.IP88TRANSACTION_LINES_TO_SKIP);
        this.setStrict(true);
        this.setSkippedLinesCallback(new DefaultSkipLineCallback());
    }
    
    public TransactionReader(String filePath) {
        super();
        
        TransactionRecordLineMapper<TransactionRecord> lineMapper = new TransactionRecordLineMapper<TransactionRecord>();
       
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer(TransactionConstant.IP88TRANSACTION_DELIMITER));
       
        lineMapper.setFieldSetMapper(new TransactionRecordFieldSetMapper());
       
        this.setName(TransactionConstant.IP88TRANSACTION_READER);
        this.setLineMapper(lineMapper);
        this.setEncoding(Charset.defaultCharset().name());
        this.setLinesToSkip(TransactionConstant.IP88TRANSACTION_LINES_TO_SKIP);
        this.setStrict(true);
        this.setSkippedLinesCallback(new DefaultSkipLineCallback());
        this.setResource(new FileSystemResource(filePath));
    }

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
    	
        JobParameters jobParameters = stepExecution.getJobParameters();
        String filePath = jobParameters.getString(BatchConstant.IP88TRANSACTION_SRC_FILEPATH);
        this.setResource(new FileSystemResource(filePath));
    }
}

