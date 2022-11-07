package com.revature.models;

import java.util.Objects;

public class LogIn {

private String email;
private String password;

public LogIn() {
	super();
	// TODO Auto-generated constructor stub
}

public LogIn(String email, String password) {
	super();
	this.email = email;
	this.password = password;
}

@Override
public String toString() {
	return "LogIn [email=" + email + ", password=" + password + "]";
}

@Override
public int hashCode() {
	return Objects.hash(email, password);
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
	return Objects.equals(email, other.email) && Objects.equals(password, other.password);
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
