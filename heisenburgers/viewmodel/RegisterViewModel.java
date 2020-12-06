package com.scb.heisenberg.heisenburgers.viewmodel;

public class RegisterViewModel {

	private long id;
	private long rmtlid;
	private String rmname;
	private String rmtlname;
	private String email;
	private long contact;
	private String password;
	private String role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getRmtlname() {
		return rmtlname;
	}

	public void setRmtlname(String rmtlname) {
		this.rmtlname = rmtlname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
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

}
