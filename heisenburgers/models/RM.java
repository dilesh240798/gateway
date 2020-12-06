package com.scb.heisenberg.heisenburgers.models;

import java.util.Set;

public class RM {

	private long id;
	private long rmtlid;
	private String rmname;
	private String rmtlname;
	private String email;
	private long contact;
	private String password;
	private String role;
	private Set<Client> setOfClient;
	private Set<Notification> setOfNotification;
	private String token;

	public long getRmtlid() {
		return rmtlid;
	}

	public void setRmtlid(long rmtlid) {
		this.rmtlid = rmtlid;
	}

	public String getRmname() {
		return rmname;
	}

	public void setRmname(String rmname) {
		this.rmname = rmname;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRmtlId() {
		return rmtlid;
	}

	public void setRmtlId(long rmtlid) {
		this.rmtlid = rmtlid;
	}

	public String getRmName() {
		return rmname;
	}

	public void setRmName(String name) {
		this.rmname = name;
	}

	public String getEmail() {
		return email;
	}

	public String getRmtlname() {
		return rmtlname;
	}

	public void setRmtlname(String rmtlname) {
		this.rmtlname = rmtlname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Client> getSetOfClient() {
		return setOfClient;
	}

	public void setSetOfClient(Set<Client> setOfClient) {
		this.setOfClient = setOfClient;
	}

	public Set<Notification> getSetOfNotification() {
		return setOfNotification;
	}

	public void setSetOfNotification(Set<Notification> setOfNotification) {
		this.setOfNotification = setOfNotification;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

}
