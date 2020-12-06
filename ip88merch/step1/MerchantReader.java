package com.scb.scroe.gateway.ip88merch.step1;

import java.nio.charset.Charset;

import com.scb.scroe.gateway.ip88merch.constant.MerchConstant;
import com.scb.scroe.gateway.ip88transaction.mapper.MerchantRecordFieldSetMapper;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

import com.scb.scroe.gateway.constant.BatchConstant;
import com.scb.scroe.gateway.ip88merch.model.MerchRecord;

public class MerchantReader extends FlatFileItemReader<MerchRecord> {

	public MerchantReader(JobParameters jobParameters, Long totalEntries) {
		super();
        DefaultLineMapper<MerchRecord> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer(MerchConstant.IP88MERCH_DELIMITER));
        lineMapper.setFieldSetMapper(new MerchantRecordFieldSetMapper());
        this.setName(MerchConstant.IP88MERCH_READER);
        this.setLineMapper(lineMapper);
        this.setEncoding(Charset.defaultCharset().name());
        this.setLinesToSkip(MerchConstant.IP88MERCH_LINES_TO_SKIP);
        this.setStrict(true);
//      this.setSkippedLinesCallback(new DefaultSkipLineCallback());
	}
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		
        JobParameters jobParameters = stepExecution.getJobParameters();
        String filePath = jobParameters.getString(BatchConstant.IP88MERCH_SRC_FILEPATH);
        this.setResource(new FileSystemResource(filePath));
	}
}
