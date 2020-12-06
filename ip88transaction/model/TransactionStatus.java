package com.scb.scroe.gateway.ip88transaction.model;

public enum TransactionStatus {
	ACTC, ACSP,RJCT;
	public static TransactionStatus value (String val) {
		switch(val) {
		case "ACTC" :
			return ACTC;
		case "ACSP":
			return ACSP;
		case "RJCT":
			return RJCT;
		}
		return null;
	}
}
