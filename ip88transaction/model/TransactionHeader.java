package com.scb.scroe.gateway.ip88transaction.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class TransactionHeader extends TransactionRecord {
    public LocalDate date;
    //public LocalDateTime time;

    public TransactionHeader() {
        this.recordType = TransactionRecordType.H;
    }
}
