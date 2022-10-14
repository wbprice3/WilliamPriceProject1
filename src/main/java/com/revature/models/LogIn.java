package com.revature.models;

import java.util.Objects;

public class LogIn {
	
	private String eUsername;
	private String ePassword;
	
	public LogIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LogIn(String eUsername, String ePassword) {
		super();
		this.eUsername = eUsername;
		this.ePassword = ePassword;
	}

	public String geteUsername() {
		return eUsername;
	}

	public void seteUsername(String eUsername) {
		this.eUsername = eUsername;
	}

	public String getePassword() {
		return ePassword;
	}

	public void setePassword(String ePassword) {
		this.ePassword = ePassword;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ePassword, eUsername);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogIn other = (LogIn) obj;
		return Objects.equals(ePassword, other.ePassword) && Objects.equals(eUsername, other.eUsername);
	}

	@Override
	public String toString() {
		return "[LogIn [eUsername=" + eUsername + ", ePassword=" + ePassword + "]";
	}
	
	
}


