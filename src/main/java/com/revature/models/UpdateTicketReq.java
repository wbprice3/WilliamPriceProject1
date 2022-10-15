package com.revature.models;

import java.util.Objects;

public class UpdateTicketReq {

	private int TicketNumber;
	private String Command;
	public UpdateTicketReq() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateTicketReq(int ticketNumber, String command) {
		super();
		this.TicketNumber = ticketNumber;
		this.Command = command;
	}
	public int getTicketNumber() {
		return TicketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		TicketNumber = ticketNumber;
	}
	public String getCommand() {
		return Command;
	}
	public void setCommand(String command) {
		Command = command;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Command, TicketNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateTicketReq other = (UpdateTicketReq) obj;
		return Objects.equals(Command, other.Command) && TicketNumber == other.TicketNumber;
	}
	@Override
	public String toString() {
		return "UpdateTicketReq [TicketNumber=" + TicketNumber + ", Command=" + Command + "]";
	}
	
	
}
