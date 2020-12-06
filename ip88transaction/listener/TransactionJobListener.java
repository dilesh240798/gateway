package com.scb.scroe.gateway.ip88transaction.listener;

//import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.scb.scroe.gateway.ip88transaction.constant.TransactionConstant;

//@Log4j2
public class TransactionJobListener implements JobExecutionListener {

    public void beforeJob(JobExecution jobExecution) {
        jobExecution.getExecutionContext().put(TransactionConstant.IP88TRANSACTION_TOT_BAL_KEY, BigDecimal.ZERO);
        jobExecution.getExecutionContext().put(TransactionConstant.LINE_NUMBER, 0);
        jobExecution.getExecutionContext().put(TransactionConstant.TRAILER_FOUND, false);
        jobExecution.getExecutionContext().put(TransactionConstant.HEADER_FOUND, false);
        //log.info("Initialized file total balance as 0 for this execution");
    }

    public void afterJob(JobExecution jobExecution) {

    }
}