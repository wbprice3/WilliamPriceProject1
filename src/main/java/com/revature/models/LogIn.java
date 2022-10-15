package com.revature.models;

import java.util.Objects;

public class LogIn {
	
	private String Username;
	private String Password;
	
	public LogIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LogIn(String Username, String Password) {
		super();
		this.Username = Username;
		this.Password = Password;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String Username) {
		this.Username = Username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Password, Username);
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
		return Objects.equals(Password, other.Password) && Objects.equals(Username, other.Username);
	}

	@Override
	public String toString() {
		return "[LogIn [Username=" + Username + ", Password=" + Password + "]";
	}
	
	
}


