package com.scb.scroe.gateway.ip88transaction.model;

public enum TransactionRecordType {
    H, T, D;

    public static TransactionRecordType value(String type) {
        switch (type) {
            case "01":
                return H;
            case "03":
                return T;
            case "02":
                return D;
            default:
            	return H;
                //throw new Exception(IETMessage.HTD_ACCEPTED_ONLY);
        }
    }
}