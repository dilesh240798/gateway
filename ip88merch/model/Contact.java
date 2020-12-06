package com.scb.scroe.gateway.ip88merch.model;

import java.math.BigInteger;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {
	
	@Size(max = 140, message = "Contact Name format incorrect")
	public String contactName;
	
	@Size(max = 50, message = "Contact Designation format incorrect")
	public String designation;
	
	public AuthorizedSignatory authorizedSignatory;
	
	public EnableNotifications enableNotifications;
	
	@Digits(integer = 20, fraction = 0, message = "Contact - Work Phone format incorrect")
	public BigInteger workPhone;
	
	@Digits(integer = 20, fraction = 0, message = "Contact - Mobile Phone format incorrect")
	public BigInteger mobilePhone;
	
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	public String email;
	
	@Size(max = 100, message = "Contact - street format incorrect")
	public String street;
	
	@Size(max = 100, message = "Contact - town format incorrect")
	public String town;
	
	@Size(max = 10, message = "Contact - zip pin format incorrect")
	public String zip_pin;
	
	@Size(max = 100, message = "Contact - state format incorrect")
	public String state;
	
	@Size(max = 2, message = "Contact - country code format incorrect")
	public String country;
	
}
