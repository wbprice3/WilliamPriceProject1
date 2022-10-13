package com.revature.models;

import java.util.Objects;

public class Tickets {
	
	private float ticketAmount;
	private String ticketDesc;
	private String ticketStatus = "Pending";
	static int counter = 0;
	
	public Tickets() {
		super();
		Tickets.counter = Tickets.counter + 1;
	}
	
	public Tickets(float ticketAmount, String ticketDesc, String ticketStatus) {
		super();
		this.ticketAmount = ticketAmount;
		this.ticketDesc = ticketDesc;
		this.ticketStatus = ticketStatus;
	}
	
	public Tickets( int ticketNumber, float ticketAmount, String ticketDesc, String ticketStatus) {
	}
	public float getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(float ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	public String getTicketDesc() {
		return ticketDesc;
	}
	public void setTicketDesc(String ticketDesc) {
		this.ticketDesc = ticketDesc;
	}
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ticketDesc, ticketAmount, ticketStatus);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tickets other = (Tickets) obj;
		return Objects.equals(ticketDesc, other.ticketDesc) && ticketAmount == other.ticketAmount
				&& Objects.equals(ticketStatus, other.ticketStatus);
	}
	@Override
	public String toString() {
		return "Ticket Number: "+counter +" [Amount= $" + ticketAmount + ", Description= " + ticketDesc + ", Status= " + ticketStatus
				+ "]";
	}
	
	
}
