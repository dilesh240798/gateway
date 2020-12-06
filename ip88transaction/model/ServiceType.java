package com.scb.scroe.gateway.ip88transaction.model;

public enum ServiceType {
	PAY2PROXY, PAY2ACCOUNT, REVERSE_CT, DUITNOW_POS, CROSSBORDER, 
	DUITNOW_P2P, REQUEST2PROXY060, REQUEST2PROXY070,REQUEST2PROXY012, 
	REAL_TIME_DEBIT210, REAL_TIME_DEBIT080, REAL_TIME_DEBIT230;

	public static ServiceType value(String val) {
		switch (val) {
		case "110":
			return PAY2PROXY;
		case "010":
			return PAY2ACCOUNT;
		case "011":
			return REVERSE_CT;
		case "030":
			return DUITNOW_POS;
		case "031":
			return CROSSBORDER;
		case "041":
			return DUITNOW_P2P;
		case "060":
			return REQUEST2PROXY060;
		case "070":
			return REQUEST2PROXY070;
		case "012":
			return REQUEST2PROXY012;
		case "210":
			return REAL_TIME_DEBIT210;
		case "080":
			return REAL_TIME_DEBIT080;
		case "230":
			return REAL_TIME_DEBIT230;
		default:
			return null;
		// throw exception here
		}	
	}
//	public static String databaseColumn(ServiceType serviceType) {
//		
//	}
}
