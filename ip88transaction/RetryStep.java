package com.scb.scroe.gateway.ip88transaction;

import com.scb.scroe.gateway.ip88transaction.exception.TransactionException;
import org.springframework.batch.core.listener.StepListenerFailedException;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;

public class RetryStep implements RetryPolicy {

	@Override
	public boolean canRetry(RetryContext context) {
		Throwable t = context.getLastThrowable();
		System.out.println("Into retry");
	    if (t instanceof NullPointerException && context.getRetryCount() <= 3) {
	        return true;
	    } else  if (t instanceof StepListenerFailedException && context.getRetryCount() <= 3){
	        return true;
	    }else  if (t instanceof FlatFileParseException && context.getRetryCount() <= 3){
	        System.out.println("Retrying FaltFileParseException");
	    	return true;
	    } else  if (t instanceof TransactionException && context.getRetryCount() <= 3){
	        System.out.println("Retrying FaltFileParseException");
	    	return true;
	    } else {
	        return false;
	    }
	}

	@Override
	public RetryContext open(RetryContext parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close(RetryContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerThrowable(RetryContext context, Throwable throwable) {
		// TODO Auto-generated method stub

	}

}
