package com.scb.scroe.gateway.ip88transaction.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
public class TransactionTrailer extends TransactionRecord {

    @Positive
    public int totalRecords;

//    public BigDecimal reportDate;

    public TransactionTrailer() {
        this.recordType = TransactionRecordType.T;
    }

}
