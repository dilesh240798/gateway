package com.scb.scroe.gateway.ip88transaction.model;

public abstract class TransactionRecord {
    TransactionRecordType recordType;

    public TransactionRecordType getRecordType() {
        return recordType;
    }
}