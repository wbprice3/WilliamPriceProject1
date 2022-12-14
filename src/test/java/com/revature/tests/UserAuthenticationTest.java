package com.revature.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.models.Employee;
import com.revature.repository.EmployeeRepository;
import com.revature.service.EmployeeService;

@TestInstance(Lifecycle.PER_CLASS)
public class UserAuthenticationTest {

	@InjectMocks
	private EmployeeService employeeService;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@BeforeAll
	public void setUp() {
		employeeService = new EmployeeService();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void userAuthenticationTest() {
		List<Employee> dummyEmployees = Arrays.asList(
				new Employee (1, "Username1", "Password1", "Employee"),
				new Employee (2, "Samantha", "Mittens", "Employee"),
				new Employee (3, "Franklin", "Waco", "Employee"),
				new Employee (4, "William", "Delilah", "Employee")
				);
		Mockito.when(this.employeeRepository.findAll()).thenReturn(dummyEmployees);
		boolean authorized = this.employeeService.userAuthentication("William","Delilah");
		Assertions.assertEquals(authorized, true);
		
	}
	
	@Test
	public void userAuthenticationTest2() {
		List<Employee> dummyEmployees = Arrays.asList(
				new Employee (1, "Username1", "Password1", "Employee"),
				new Employee (2, "Samantha", "Mittens", "Employee"),
				new Employee (3, "Franklin", "Waco", "Employee"),
				new Employee (4, "William", "Delilah", "Employee")
				);
		Mockito.when(this.employeeRepository.findAll()).thenReturn(dummyEmployees);
		boolean authorized = this.employeeService.userAuthentication("William","Waco");
		Assertions.assertEquals(authorized, false);
		
	}
}