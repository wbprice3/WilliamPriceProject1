package com.revature.models;

import java.util.Objects;

public class Tickets {
	
	private int ticketNumb;
	private String ticketDesc;
	private String ticketStatus;
	
	
	public Tickets() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Tickets(int ticketNumb, String ticketDesc, String ticketStatus) {
		super();
		this.ticketNumb = ticketNumb;
		this.ticketDesc = ticketDesc;
		this.ticketStatus = ticketStatus;
	}
	public int getTicketNumb() {
		return ticketNumb;
	}
	public void setTicketNumb(int ticketNumb) {
		this.ticketNumb = ticketNumb;
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
		return Objects.hash(ticketDesc, ticketNumb, ticketStatus);
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
		return Objects.equals(ticketDesc, other.ticketDesc) && ticketNumb == other.ticketNumb
				&& Objects.equals(ticketStatus, other.ticketStatus);
	}
	@Override
	public String toString() {
		return "Tickets [ticketNumb=" + ticketNumb + ", ticketDesc=" + ticketDesc + ", ticketStatus=" + ticketStatus
				+ "]";
	}
	
	
}
