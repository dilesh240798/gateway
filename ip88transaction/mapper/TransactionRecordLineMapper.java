package com.scb.scroe.gateway.ip88transaction.mapper;

import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import com.scb.scroe.gateway.ip88transaction.model.InputLine;
public class TransactionRecordLineMapper<T> extends DefaultLineMapper<T>{
	
	@Override
	public T mapLine(String line, int lineNumber) throws Exception {
		InputLine.inputRow = line;
		InputLine.inputRowNumber = lineNumber;
		return super.mapLine(line, lineNumber);
	}
}
