package com.scb.scroe.gateway.ip88transaction.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.scb.scroe.gateway.ip88transaction.model.AccountType;
import com.scb.scroe.gateway.ip88transaction.model.ServiceType;
import com.scb.scroe.gateway.ip88transaction.model.SettlementCycleValue;
import com.scb.scroe.gateway.ip88transaction.model.TransactionEntry;
import com.scb.scroe.gateway.ip88transaction.model.TransactionHeader;
import com.scb.scroe.gateway.ip88transaction.model.TransactionRecord;
import com.scb.scroe.gateway.ip88transaction.model.TransactionStatus;
import com.scb.scroe.gateway.ip88transaction.model.TransactionTrailer;

public class TransactionRecordFieldSetMapper implements FieldSetMapper<TransactionRecord> {
	@Override
	public TransactionRecord mapFieldSet(FieldSet fieldSet) throws BindException {
		if (fieldSet.readString(0).equalsIgnoreCase("02")) {
			TransactionEntry record = new TransactionEntry();
			SettlementCycleValue setcycleval = SettlementCycleValue.value(fieldSet.readString(1));
			record.setSettlementCycleValue(setcycleval);			
			record.setInitiationDate(LocalDateTime.parse(fieldSet.readString(2), DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
			record.setMessageId(fieldSet.readString(3));
			record.setBusinessMessageId(fieldSet.readString(4));
			ServiceType serviceType = ServiceType.value(fieldSet.readString(5));
			record.setServiceType(serviceType);
			record.setSchemeId(fieldSet.readString(6));
			record.setOfiCode(fieldSet.readString(7));
			record.setOfiAccountNumber(fieldSet.readString(8));
			AccountType ofiAccountType = AccountType.value(fieldSet.readString(9));
			record.setOfiAccountType(ofiAccountType);
			record.setOfiAccountName(fieldSet.readString(10));
			record.setRfiCode(fieldSet.readString(11));
			record.setRfiAccountNumber(fieldSet.readString(12));
			AccountType rfiAccountType = AccountType.value(fieldSet.readString(13));
			record.setRfiAccountType(rfiAccountType);
			record.setRfiAccountName(fieldSet.readString(14));
			record.setTransactionAmount(fieldSet.readString(15));
			record.setCurrency(fieldSet.readString(16));
			record.setForeignExchangeNumber(fieldSet.readString(17));
			record.setForeignAmount(fieldSet.readString(18));
			record.setForeignExchangeRate(fieldSet.readString(19));
			TransactionStatus transactionStatus = TransactionStatus.value(fieldSet.readString(20));
			record.setTransactionStatus(transactionStatus);
			record.setTransactionSubCode(fieldSet.readString(21));
			record.setCompletedDate(LocalDate.parse(fieldSet.readString(22), DateTimeFormatter.BASIC_ISO_DATE));
			return record;
		}

		else if (fieldSet.readString(0).equals("03")) {
			TransactionTrailer trailer = new TransactionTrailer();
			trailer.setTotalRecords(fieldSet.readInt(1));
			return trailer;
		}

		else {
			TransactionHeader header = new TransactionHeader();
			header.setDate(LocalDate.parse(fieldSet.readString(1), DateTimeFormatter.BASIC_ISO_DATE));
			return header;
		}
		}
}