package com.revature.service;

import java.util.List;

import com.revature.models.Tickets;
import com.revature.repository.TicketRepository;

public class TicketService {

	private TicketRepository tickRep = new TicketRepository();
	
	public TicketService() {
		this.tickRep = new TicketRepository();
	}
	
	
	public String getTicketStatus(int tickNum) {
		List<Tickets> ticketList = tickRep.findAll();
		for (Tickets t : ticketList) {
			if(t.getTicketNumber() == tickNum) {
				return t.getTicketStatus();
			}
			
		}
		return "Not A Valid Ticket Number";
	}
}
