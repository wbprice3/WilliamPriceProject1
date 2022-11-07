package com.revature.models;

import java.util.Objects;

public class AccountModel {

	private int idNum;
	private String accountType;
	private String fullName;
	private String email;
	private String password;
	
	public AccountModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountModel(int idNum, String accountType, String fullName, String email, String password) {
		super();
		this.idNum = idNum;
		this.accountType = accountType;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "AccountModel [idNum=" + idNum + ", AccountType=" + accountType + ", FullName=" + fullName + ", Email="
				+ email + ", Password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountType, email, fullName, password, idNum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountModel other = (AccountModel) obj;
		return Objects.equals(accountType, other.accountType) && Objects.equals(email, other.email)
				&& Objects.equals(fullName, other.fullName) && Objects.equals(password, other.password)
				&& idNum == other.idNum;
	}

	public int getIdNum() {
		return idNum;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
