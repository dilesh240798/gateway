package com.scb.scroe.gateway.ip88merch.model;

public enum MDRType {
	PERCENTAGE,FIXED,PERCENTAGE_PLUS_FIXED,GREATER_OF_PERCENT_OR_FIXED,LOWER_OF_PERCENT_OR_FIXED;
	
	public static MDRType value(String val) {
		switch(val) {
		case "Percentage" :
			return PERCENTAGE;
		case "Fixed" :
			return FIXED;
		case "Percentage + Fixed" :
			return PERCENTAGE_PLUS_FIXED;
		case "Greater of Percentage or Fixed" :
			return GREATER_OF_PERCENT_OR_FIXED;
		case "Lower of Percentage or Fixed" :
			return LOWER_OF_PERCENT_OR_FIXED;
		default:
			return null;
		}		
}
}
