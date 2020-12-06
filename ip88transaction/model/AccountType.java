package com.scb.scroe.gateway.ip88transaction.model;

public enum AccountType {
	SVGS,CACC,CCRD,DFLT,	WALL;
	public static AccountType value(String val) {
		switch(val) {
		case "SVGS":
			return SVGS;
		case "CACC":
			return CACC;
		case "CCRD":
			return CCRD;
		case "DFLT":
			return DFLT;
		case "WALL":
			return WALL;
		}
		return null;
	}
}