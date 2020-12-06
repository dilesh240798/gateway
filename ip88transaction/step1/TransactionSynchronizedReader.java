package com.scb.scroe.gateway.ip88transaction.step1;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;

import com.scb.scroe.gateway.constant.BatchConstant;
import com.scb.scroe.gateway.ip88transaction.model.TransactionRecord;

public class TransactionSynchronizedReader extends SynchronizedItemStreamReader<TransactionRecord> {
	
	public TransactionSynchronizedReader() {super();}
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		String fileName = stepExecution.getJobParameters()
									   .getString(BatchConstant.IP88TRANSACTION_SRC_FILEPATH);
		
        this.setDelegate(new TransactionReader(fileName));
	}
}
