package com.scb.scroe.gateway.ip88merch.model;

public enum KYCCompleted {

	Yes, No;
	public static KYCCompleted value(String val) {
		switch(val) {
		case "Yes":
			return Yes;
		case "No":
			return No;
		default:
			return null;
		}
}
}
