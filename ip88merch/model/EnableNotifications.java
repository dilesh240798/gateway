package com.scb.scroe.gateway.ip88merch.model;

public enum EnableNotifications {
	Yes, No;
	public static EnableNotifications value(String val) {
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
