package com.scb.scroe.gateway.ip88merch.model;

public enum Status {
	Active, Inactive;
	
	public static Status value(String val) {
		switch(val) {
		case "Active":
			return Active;
		case "Inactive" :
			return Inactive;
		default:{
			return null;
		}
	}

}
}
