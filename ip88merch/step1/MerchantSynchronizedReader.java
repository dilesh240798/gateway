package com.scb.scroe.gateway.ip88merch.step1;

import com.scb.scroe.gateway.ip88merch.constant.MerchConstant;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import com.scb.scroe.gateway.ip88merch.model.MerchRecord;

public class MerchantSynchronizedReader extends SynchronizedItemStreamReader<MerchRecord>{
	 public MerchantSynchronizedReader() {
	        super();
	    }
	    @BeforeStep
	    public void beforeStep(StepExecution stepExecution) {
	        JobParameters parameters = stepExecution.getJobParameters();
	        this.setDelegate(new MerchantReader(parameters, stepExecution.getJobExecution().getExecutionContext().getLong(MerchConstant.TOTAL_ENTRIES)));
	    }
}
