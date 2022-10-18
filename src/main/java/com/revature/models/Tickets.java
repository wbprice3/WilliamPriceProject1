package com.revature.models;

import java.util.Objects;

public class Tickets {
	
	private int	ticketNumber;
	private float ticketAmount;
	private String ticketDesc = null;
	private String ticketStatus = "Pending";
	private String tickSubmitter;
		
	public Tickets() {
		super();
	}
	
	/*
	 * public Tickets(float ticketAmount, String ticketDesc, String ticketStatus) {
	 * super(); this.ticketAmount = ticketAmount; this.ticketDesc = ticketDesc;
	 * this.ticketStatus = ticketStatus; }
	 */
	
	public String getTickSubmitter() {
		return tickSubmitter;
	}

	public void setTickSubmitter(String tickSubmitter) {
		this.tickSubmitter = tickSubmitter;
	}

	public Tickets( int ticketNumber, float ticketAmount, String ticketDesc, String ticketStatus) {
		this.ticketNumber = ticketNumber;
		this.ticketAmount = ticketAmount;
		this.ticketDesc = ticketDesc;
		this.ticketStatus = ticketStatus;
	}
	
	public Tickets( int ticketNumber, double ticketAmount, String ticketDesc, String ticketStatus, String tickSubmitter) {
		this.ticketNumber = ticketNumber;
		this.ticketAmount = (float) ticketAmount;
		this.ticketDesc = ticketDesc;
		this.ticketStatus = ticketStatus;
		this.tickSubmitter = tickSubmitter;
	}
	
	
	public Tickets(int i, double d, String string, String string2) {
		this.ticketNumber = i;
		this.ticketAmount = (float) d;
		this.ticketDesc = string;
		this.ticketStatus = string2;
		
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
		return Objects.hash(ticketAmount, ticketDesc, ticketNumber, ticketStatus, tickSubmitter);
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
