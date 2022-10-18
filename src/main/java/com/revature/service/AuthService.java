package com.revature.service;

import java.util.List;

import com.revature.models.Employee;
import com.revature.repository.EmployeeRepository;

public class AuthService {

	private EmployeeRepository empRep;
	boolean authenticated;
	
	public AuthService() {
		this.empRep =  new EmployeeRepository();
	}


}
