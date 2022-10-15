package com.revature.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserNameExists {
	public String CheckUserName(String uName) {

		// Make necessary Objects
	
		String uname = null;
		ResultSet set = null;
		PreparedStatement stmt = null;
		Connection conn = null;

		try {

			conn = ConnectionFactory.getConnection();
			
			
			final String SQL = "SELECT employee_uname FROM employee WHERE employee_uname = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, uName);
			set = stmt.executeQuery();

			while (set.next()) {
				uname = set.getString(1);
							
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

		return uname;
	}
	
	
}


