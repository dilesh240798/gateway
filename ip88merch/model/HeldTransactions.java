package com.scb.scroe.gateway.ip88merch.model;

public enum HeldTransactions {

	Hold, Release, Cancel;
	public static HeldTransactions value(String val) {
		switch(val) {
		case"Hold":
			return Hold;
		case "Release":
			return Release;
		case "Cancel":
			return Cancel;
		default:
			return null;
		}
		}
}
