package com.scb.scroe.gateway.ip88merch.model;

public enum Month {
	JANUARY,FEBRUARY,MARCH,APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,NOVEMBER,DECEMBER;
	
	public static Month value(String val) {
		switch(val) {
		case "Jan":
			return JANUARY;
		case "Feb":
			return FEBRUARY;
		case "Mar":
			return MARCH;
		case "Apr":
			return APRIL;
		case "May":
			return MAY;
		case "Jun":
			return JUNE;
		case "Jul":
			return JULY;
		case "Aug":
			return AUGUST;
		case "Sep":
			return SEPTEMBER;
		case "Oct":
			return OCTOBER;
		case "Nov":
			return NOVEMBER;
		case "Dec":
			return DECEMBER;
		default:
			return null;
		}
	}
}
