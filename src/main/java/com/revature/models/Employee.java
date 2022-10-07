package com.revature.models;

import java.util.Objects;

public class Employee {

	private int idNum;
	private String username;
	private String password;
	private String role = "Employee";
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int idNum, String username, String password) {
		super();
		this.idNum = idNum;
		this.username = username;
		this.password = password;
		
	}

	public int getIdNum() {
		return idNum;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public int hashCode() {
		return Objects.hash(idNum, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return idNum == other.idNum && Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Employee [idNum=" + idNum + ", username=" + username + ", password=" + password + ", role=" + role
				+ "]";
	}
}
