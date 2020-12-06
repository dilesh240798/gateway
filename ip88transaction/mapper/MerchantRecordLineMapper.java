package com.scb.scroe.gateway.ip88transaction.mapper;

import org.springframework.batch.item.file.mapping.DefaultLineMapper;

import com.scb.scroe.gateway.ip88merch.model.InputLine;

public class MerchantRecordLineMapper<T> extends DefaultLineMapper<T> {

	@Override
	public T mapLine(String line, int lineNumber) throws Exception {
		InputLine.inputRow = line;
		InputLine.inputRowNumber = lineNumber;
		return super.mapLine(line, lineNumber);
	}
}
