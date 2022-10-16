package com.revature.models;

import java.util.Objects;

import com.revature.models.Tickets;

public class updateRequest {
	Tickets Tick = new Tickets();
	private String NewStatus;
	private int TicketNumber;
	public updateRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public updateRequest(String NewStatus, int TicketNumber) {
		super();
		this.NewStatus =  NewStatus;
		this.TicketNumber = TicketNumber;
		if ((NewStatus.equals("Approved"))||(NewStatus.equals("Denied"))) {
			
		}
	}
	public String getNewStatus() {
		return NewStatus;
	}
	public void setNewStatus(String NewStatus) {
		this.NewStatus = NewStatus;
		
		
	}
	public int getTicketNumber() {
		return TicketNumber;
	}
	public void setTicketNumber(int TicketNumber) {
		this.TicketNumber = TicketNumber;
	}
	@Override
	public int hashCode() {
		return Objects.hash(NewStatus, TicketNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		updateRequest other = (updateRequest) obj;
		return Objects.equals(NewStatus, other.NewStatus) && TicketNumber == other.TicketNumber;
	}
	@Override
	public String toString() {
		return "updateRequest [NewStatus=" + NewStatus + ", TicketNumber=" + TicketNumber + "]";
	}
	
	
}
