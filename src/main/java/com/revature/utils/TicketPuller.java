package com.revature.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Tickets;

public class TicketPuller {

	
	
	public List<Tickets> PullTickets(String uName) {

		// Make necessary Objects
		List<Tickets> ticklist = new ArrayList<>();
		ResultSet set = null;
		PreparedStatement stmt = null;
		Connection conn = null;

		try {

			conn = ConnectionFactory.getConnection();
			final String SQL = "SELECT * FROM ticket WHERE ticket_submitter = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1,uName);
			set = stmt.executeQuery();
			while (set.next()) {
				ticklist.add(
						new Tickets (set.getInt(1), set.getFloat(2), set.getString(3), set.getString(4), set.getString(5)));
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

		return ticklist;
	}
}
