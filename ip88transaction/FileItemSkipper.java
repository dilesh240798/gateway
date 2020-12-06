package com.scb.scroe.gateway.ip88transaction;

import java.io.FileNotFoundException;

import com.scb.scroe.gateway.ip88transaction.exception.TransactionException;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;

public class FileItemSkipper implements SkipPolicy {
    
    
 
    @Override
    public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
        if (exception instanceof FileNotFoundException) {
            return false;
        } 
        else if (exception instanceof TransactionException && skipCount <= 5) {
        	StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("An error occured while processing the enum ");
//            logger.error("{}", errorMessage.toString());
            System.out.println(errorMessage);
        	return true;
        }else if (exception instanceof FlatFileParseException && skipCount <= 5) {
            FlatFileParseException ffpe = (FlatFileParseException) exception;
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("An error occured while processing the " + ffpe.getLineNumber()
                    + " line of the file. Below was the faulty " + "input.\n");
            errorMessage.append(ffpe.getInput() + "\n");
//            logger.error("{}", errorMessage.toString());
            System.out.println(errorMessage);
            return true;
        } 
        else {
            return false;
        }
    }
 
}