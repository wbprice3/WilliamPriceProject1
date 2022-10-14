package com.revature.models;

import java.util.Objects;

public class Tickets {
	
	private int	ticketNumber;
	private float ticketAmount;
	private String ticketDesc;
	private String ticketStatus = "Pending";
	
	
	public Tickets() {
		super();
	}
	
	/*
	 * public Tickets(float ticketAmount, String ticketDesc, String ticketStatus) {
	 * super(); this.ticketAmount = ticketAmount; this.ticketDesc = ticketDesc;
	 * this.ticketStatus = ticketStatus; }
	 */
	
	public Tickets( int ticketNumber, float ticketAmount, String ticketDesc, String ticketStatus) {
		this.ticketNumber = ticketNumber;
		this.ticketAmount = ticketAmount;
		this.ticketDesc = ticketDesc;
		this.ticketStatus = ticketStatus;
	}
	
	
	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
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
		return Objects.hash(ticketAmount, ticketDesc, ticketNumber, ticketStatus);
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
		return Float.floatToIntBits(ticketAmount) == Float.floatToIntBits(other.ticketAmount)
				&& Objects.equals(ticketDesc, other.ticketDesc) && ticketNumber == other.ticketNumber
				&& Objects.equals(ticketStatus, other.ticketStatus);
	}

	@Override
	public String toString() {
		return "Tickets [ticketNumber=" + ticketNumber + ", ticketAmount=" + ticketAmount + ", ticketDesc=" + ticketDesc
				+ ", ticketStatus=" + ticketStatus + "]";
	}
	
	
	
	
}
