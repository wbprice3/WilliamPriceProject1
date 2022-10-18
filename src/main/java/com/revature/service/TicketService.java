package com.revature.service;

import java.util.ArrayList;
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
	
	public List<Tickets> getPendingTickets() {
		List<Tickets> ticketList = tickRep.findAll();
		List<Tickets> pendingTickets = new ArrayList<>();
		for (Tickets t : ticketList) {
			if(t.getTicketStatus().equals("Pending")) {
				pendingTickets.add(t);
			}
			
		}
		return pendingTickets;
	}
	
	public List<Tickets> getCompletedTickets() {
		List<Tickets> ticketList = tickRep.findAll();
		List<Tickets> completedTickets = new ArrayList<>();
		for (Tickets t : ticketList) {
			if(!t.getTicketStatus().equals("Pending")) {
				completedTickets.add(t);
			}
			
		}
		return completedTickets;
	}
	
	public List<Tickets> ticketPuller(String uName){
		List<Tickets> ticketList = tickRep.findAll();
		List<Tickets> pulledTickets = new ArrayList<>();
		for (Tickets t : ticketList) {
			if(t.getTickSubmitter().equals(uName)){
				pulledTickets.add(t);
			}
			
		}
		return pulledTickets;
	}
}
