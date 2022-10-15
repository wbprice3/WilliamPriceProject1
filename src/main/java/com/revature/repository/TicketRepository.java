package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revature.models.Tickets;
import com.revature.utils.ConnectionFactory;

public class TicketRepository {

		public void save(Tickets ticket) {

			PreparedStatement stmt = null;
			final String SQL = "INSERT INTO ticket values(default, ?, ?, ?,?)";

			try (Connection conn = ConnectionFactory.getConnection()) {
				stmt = conn.prepareStatement(SQL);
				stmt.setFloat(1, ticket.getTicketAmount());
				stmt.setString(2, ticket.getTicketDesc());
				stmt.setString(3, ticket.getTicketStatus());
				stmt.setString(4, ticket.getTickSubmitter());
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

		public List<Tickets> findAll() {

			// Make necessary Objects
			List<Tickets> ticklist = new ArrayList<>();
			ResultSet set = null;
			Statement stmt = null;
			Connection conn = null;

			try {

				conn = ConnectionFactory.getConnection();
				
				stmt = conn.createStatement();

				set = stmt.executeQuery("SELECT * FROM ticket WHERE ticket_status = 'Pending'");
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

		public void updateTicket(String command, int ticketNum) {

			Connection conn = null;
			PreparedStatement stmt = null;
			final String SQL = "UPDATE ticket SET ticket_status = ? WHERE ticket_num = ? ";
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.prepareStatement(SQL);
				stmt.setInt(2, ticketNum);
				stmt.setString(1, command);
				stmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				try {
					conn.close();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		public void deleteById(int id)
	    {
			/*
			 * This method uses a PreparedStatement. A PreparedStatement is a special type of Statement
			 * that protects against SQL injection. SQL injection occurs when a user of an application with
			 * malicious intent enters SQL as input, which is then executed against your DB.
			 * 
			 * PreparedStatements are precompiled - this is how they prevent SQL injection as the parameter(s)
			 * is just replaced with the actual value before the query is executed. Parameters are denoted with
			 * the "?" syntax. Each parameter has its own index, starting from 1.
			 */
	        String sqlCommand = "DELETE FROM ticket where ticket_num = ?";

	        try(Connection conn = ConnectionFactory.getConnection();
	        		PreparedStatement stmt = conn.prepareStatement(sqlCommand);) {
	           
	        	stmt.setInt(1, id);
	            stmt.executeUpdate();
	        }catch(SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
