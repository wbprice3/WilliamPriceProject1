package com.revature.service;

import java.util.Collections;
import java.util.List;

import com.revature.models.AccountModel;
import com.revature.models.Employee;
import com.revature.repository.EmployeeRepository;

public class EmployeeService {

	private EmployeeRepository empRep = new EmployeeRepository();
	String role;
	
	public EmployeeService() {
		this.empRep =  new EmployeeRepository();
	}

	public List<AccountModel> sortEmployeeByName(){
		List<AccountModel> retrievedEmps = this.empRep.findAll();
		Collections.sort(retrievedEmps, (e1, e2) -> e1.getEmail().compareTo(e2.getEmail()));
		return retrievedEmps;
	}
	
	public boolean usernameExists(String userName){
		
		List<AccountModel> retrievedEmps = empRep.findAll();
		for (AccountModel e : retrievedEmps) {
			if(e.getEmail().equals(userName)) {
				return true;
				
			} 
		}
		return false;
	}
	
	public boolean userAuthentication(String email, String password) {
		List<AccountModel> retrievedAccs = this.empRep.findAll();
		for (AccountModel e : retrievedAccs) {
			if ((e.getEmail().equals(email))&&(e.getPassword().equals(password))) {
				return  true;
				
			}
				
		}
		return false;
	}
	
	
	public String getUsersRole(String email) {
		
		List<AccountModel> retrievedEmps = this.empRep.findAll();
		for (AccountModel e : retrievedEmps) {
			if (e.getEmail().equals(email)){
				role = e.getAccountType();
			}
		}
		return role;
		}
	
	public void saveEmployee(AccountModel accModel) {
		this.empRep.save(accModel);
	};
		
}//End of Class
