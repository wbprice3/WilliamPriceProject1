package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revature.models.Employee;
import com.revature.utils.ConnectionFactory;

public class EmployeeRepository {

		public void save(Employee employee) {

			PreparedStatement stmt = null;
			final String SQL = "INSERT INTO employee values(default, ?, ?, ?)";

			try (Connection conn = ConnectionFactory.getConnection()) {
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, employee.getUsername());
				stmt.setString(2, employee.getPassword());
				stmt.setString(3, employee.getRole());
				stmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		public List<Employee> findAll() {

			// Make necessary Objects
			List<Employee> employees = new ArrayList<>();
			ResultSet set = null;
			Statement stmt = null;
			Connection conn = null;

			try {

				conn = ConnectionFactory.getConnection();
				
				stmt = conn.createStatement();

				set = stmt.executeQuery("SELECT * FROM employee");

				while (set.next()) {
					employees.add(
							new Employee (set.getInt(1), set.getString(2), set.getString(3), set.getString(4)));
				}
			}

			catch (SQLException e) {
				e.printStackTrace();
			}

			finally {

				try {
					conn.close();
					set.close();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return employees;
		}

		public boolean update(Employee employee) {

			Connection conn = null;
			PreparedStatement stmt = null;
			final String SQL = "UPDATE employee SET employee_uname = ? , "
					+ "employee_pword = ?, employee_role = ?";
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, employee.getUsername());
				stmt.setString(2, employee.getPassword());
				stmt.setString(3, employee.getRole());
				stmt.execute(SQL);

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					conn.close();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return true;

		}
		
		public void deleteById(int id)
	    {
	        String sqlCommand = "DELETE FROM employee where employee_idnum = ?";

	        try(Connection conn = ConnectionFactory.getConnection();
	        		PreparedStatement stmt = conn.prepareStatement(sqlCommand);) {
	           
	        	stmt.setInt(1, id);
	            stmt.executeUpdate();
	        }catch(SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
