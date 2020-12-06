package com.scb.scroe.gateway.ip88merch.model;

public enum AuthorizedSignatory {
	Yes, No;
	public static AuthorizedSignatory value(String val) {
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
