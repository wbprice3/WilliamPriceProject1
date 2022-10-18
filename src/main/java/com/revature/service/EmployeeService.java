package com.revature.service;

import java.util.Collections;
import java.util.List;

import com.revature.models.Employee;
import com.revature.repository.EmployeeRepository;

public class EmployeeService {

	private EmployeeRepository empRep;
	boolean exists;
	
	public EmployeeService() {
		this.empRep = new EmployeeRepository();
	}

	public List<Employee> sortEmployeeByName(){
		List<Employee> retrievedEmps = this.empRep.findAll();
		Collections.sort(retrievedEmps, (e1, e2) -> e1.getUsername().compareTo(e2.getUsername()));
		return retrievedEmps;
	}
	
	public boolean usernameExists(String userName){
		
		List<Employee> retrievedEmps = this.empRep.findAll();
		for (Employee e : retrievedEmps) {
			if(e.getUsername().equals(userName)) {
				exists = true;
				
			} else {
				exists = false;
				
			}
		//Collections.sort(retrievedPeople, (p1, p2) -> p1.getPerson_name().compareTo(p2.getPerson_name()));
		
	}
		return exists;
	}

}//End of Class
