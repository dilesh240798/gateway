package com.scb.scroe.gateway.ip88transaction.step1;

import com.scb.scroe.gateway.ip88transaction.exception.MandatoryFieldMissingException;
import org.springframework.batch.item.ItemProcessor;

import com.scb.scroe.gateway.ip88transaction.model.ServiceType;
import com.scb.scroe.gateway.ip88transaction.model.TransactionEntry;
import com.scb.scroe.gateway.ip88transaction.model.TransactionStatus;

public class TransactionValidationProcessor implements ItemProcessor<TransactionEntry, TransactionEntry>{
	@Override
	public TransactionEntry process(TransactionEntry item) throws Exception {
		try {
			return validations(item);
		}
		catch(MandatoryFieldMissingException e) {
			item.setTransactionStatus(TransactionStatus.RJCT);
			return item;
		}
	}
	private TransactionEntry validations(TransactionEntry entry) {
		if (entry.getServiceType().equals(ServiceType.CROSSBORDER)){
			nullCheck(entry.getOfiCode(),"OFI Code cannot be empty for CrossBorder Transactions");
			nullCheck(entry.getOfiAccountNumber(),"OFI Account Number cannot be empty for CrossBorder Transactions");
			nullCheck(entry.getOfiAccountType(),"OFI Account Type cannot be empty for CrossBorder Transactions");
			nullCheck(entry.getOfiAccountName(),"OFI Account Name cannot be empty for CrossBorder Transactions");
			nullCheck(entry.getCurrency(),"Currency Type cannot be empty for CrossBorder Transactions");
			nullCheck(entry.getForeignExchangeNumber(),"Foreign Exchange Reference Number cannot be empty for CrossBorder Transactions");
			nullCheck(entry.getForeignAmount(),"Foreign amount cannot be empty for CrossBorder Transactions");
			nullCheck(entry.getForeignExchangeRate(),"Foreign exhange rate cannot be empty for CrossBorder Transactions");
		}
		return null;
	}
	private void stringCheck(String string, String message) {
		if (string == null || string.length() == 0)
            throw new MandatoryFieldMissingException(message);
	}
	private void nullCheck(Object object, String message) {
		if (object == null)
            throw new MandatoryFieldMissingException(message);		
	}
}
