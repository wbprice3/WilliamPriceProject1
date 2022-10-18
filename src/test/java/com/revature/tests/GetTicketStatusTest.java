package com.revature.tests;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.models.Tickets;
import com.revature.repository.TicketRepository;
import com.revature.service.TicketService;

@TestInstance(Lifecycle.PER_CLASS)
class GetTicketStatusTest {

	@InjectMocks
	private TicketService ticketService;
	
	@Mock
	private TicketRepository ticketRepository;
	
	@BeforeAll
	public void setUp() {
		ticketService = new TicketService();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void getTicketStatusTest() {
		List<Tickets> dummyTickets = Arrays.asList(
				new Tickets (1, 12.99, "Lunch", "Pending"),
				new Tickets (2, 18.99, "Dinner", "Denied"),
				new Tickets (3, 14.99, "Gas", "Approved"),
				new Tickets (4, 10.99, "Hotel", "Pending")
				);
		Mockito.when(this.ticketRepository.findAll()).thenReturn(dummyTickets);
		String status = this.ticketService.getTicketStatus(2);
		Assertions.assertEquals(status, "Denied");
		
	}
	
	@Test
	public void getTicketStatusTest2() {
		List<Tickets> dummyTickets = Arrays.asList(
				new Tickets (1, 12.99, "Lunch", "Pending"),
				new Tickets (2, 18.99, "Dinner", "Denied"),
				new Tickets (3, 14.99, "Gas", "Approved"),
				new Tickets (4, 10.99, "Hotel", "Pending")
				);
		Mockito.when(this.ticketRepository.findAll()).thenReturn(dummyTickets);
		String status = this.ticketService.getTicketStatus(3);
		Assertions.assertEquals(status, "Approved");
		
	}
	
	@Test
	public void getTicketStatusTest3() {
		List<Tickets> dummyTickets = Arrays.asList(
				new Tickets (1, 12.99, "Lunch", "Pending"),
				new Tickets (2, 18.99, "Dinner", "Denied"),
				new Tickets (3, 14.99, "Gas", "Approved"),
				new Tickets (4, 10.99, "Hotel", "Pending")
				);
		Mockito.when(this.ticketRepository.findAll()).thenReturn(dummyTickets);
		String status = this.ticketService.getTicketStatus(4);
		Assertions.assertEquals(status, "Pending");
		
	}
}