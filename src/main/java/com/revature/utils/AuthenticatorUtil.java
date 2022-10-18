package com.revature.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticatorUtil {

	

	public String StatusChecker(int ticketNum) {

		// Make necessary Objects
	
		String tickStatus = null;
		ResultSet set = null;
		PreparedStatement stmt = null;
		Connection conn = null;

		try {

			conn = ConnectionFactory.getConnection();
			final String SQL = "SELECT ticket_status FROM ticket WHERE ticket_num = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, ticketNum);
			set = stmt.executeQuery();

			while (set.next()) {
				tickStatus = set.getString(1);					
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
		return tickStatus;
	}

}
