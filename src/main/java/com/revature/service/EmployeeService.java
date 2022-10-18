package com.revature.service;

import java.util.Collections;
import java.util.List;

import com.revature.models.Employee;
import com.revature.repository.EmployeeRepository;

public class EmployeeService {

	private EmployeeRepository empRep = new EmployeeRepository();
	String role;
	
	public EmployeeService() {
		this.empRep =  new EmployeeRepository();
	}

	public List<Employee> sortEmployeeByName(){
		List<Employee> retrievedEmps = this.empRep.findAll();
		Collections.sort(retrievedEmps, (e1, e2) -> e1.getUsername().compareTo(e2.getUsername()));
		return retrievedEmps;
	}
	
	public boolean usernameExists(String userName){
		
		List<Employee> retrievedEmps = empRep.findAll();
		for (Employee e : retrievedEmps) {
			if(e.getUsername().equals(userName)) {
				return true;
				
			} 
		}
		return false;
	}
	
	public boolean userAuthentication(String userName, String password) {
		List<Employee> retrievedEmps = this.empRep.findAll();
		for (Employee e : retrievedEmps) {
			if ((e.getUsername().equals(userName))&&(e.getPassword().equals(password))) {
				return  true;
				
			}
				
		}
		return false;
	}
	
	
	public String getUsersRole(String userName) {
		
		List<Employee> retrievedEmps = this.empRep.findAll();
		for (Employee e : retrievedEmps) {
			if (e.getUsername().equals(userName)){
				role = e.getRole();
			}
		}
		return role;
		}
	
	public void saveEmployee(Employee employee) {
		this.empRep.save(employee);
	};
		
}//End of Class
