package com.scb.scroe.gateway.ip88transaction.step1;
import com.scb.scroe.gateway.entity.IP88TransactionLandingEntity;
import com.scb.scroe.gateway.ip88transaction.constant.TransactionConstant;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

import com.scb.scroe.gateway.ip88transaction.model.InputLine;
import com.scb.scroe.gateway.constant.BatchConstant;
import com.scb.scroe.gateway.ip88transaction.model.TransactionEntry;
import com.scb.scroe.gateway.ip88transaction.model.TransactionRecord;
import com.scb.scroe.gateway.ip88transaction.model.TransactionRecordType;

public class TransactionCompositeProcessor implements ItemProcessor<TransactionRecord, IP88TransactionLandingEntity>{

	private JobExecution jobExecution;
	@Override
	public IP88TransactionLandingEntity process(TransactionRecord record) throws Exception {
		
		if(record.getRecordType().equals(TransactionRecordType.D)) {
            
			jobExecution.getExecutionContext().get(TransactionConstant.VALUE_DATE);
            TransactionEntry entry = (TransactionEntry) record;
            return IP88TransactionLandingEntity.builder()
            		.jobId(this.jobExecution.getJobParameters().getString(BatchConstant.JOB_ID))
            		.settlementCycleValue(entry.settlementCycleValue)
                    .initiationDate(entry.initiationDate)
                    .messageId(entry.messageId)
                    .businessMessageId(entry.businessMessageId)
                    .serviceType(entry.serviceType)
                    .ofiCode(entry.ofiCode)
                    .ofiAccountNumber(entry.ofiAccountNumber)
                    .ofiAccountType(entry.ofiAccountType)
                    .ofiAccountName(entry.ofiAccountName)
                    .rfiCode(entry.rfiCode)
                    .rfiAccountNumber(entry.rfiAccountNumber)
                    .rfiAccountType(entry.rfiAccountType)
                    .rfiAccountName(entry.rfiAccountName)
                    .transactionAmount(entry.transactionAmount)
                    .currency(entry.currency)
                    .foreignExchangeNumber(entry.foreignExchangeNumber)
                    .foreignAmount(entry.foreignAmount)
                    .foreignExchangeRate(entry.foreignExchangeRate)
                    .transactionStatus(entry.transactionStatus)
                    .transactionSubCode(entry.transactionSubCode)
                    .completedDate(entry.completedDate)
                    .inputRow(InputLine.inputRow)
                    .inputRowNumber(InputLine.inputRowNumber)
                    .build();
        }
        return null;
	}
	
	@BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        jobExecution = stepExecution.getJobExecution();
    }

}
