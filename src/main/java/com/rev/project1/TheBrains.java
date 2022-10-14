package com.rev.project1;

import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.models.Tickets;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.TicketRepository;
import com.revature.models.Employee;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class TheBrains {

	public static String recEmp;
	public static String recTick;
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(8000);
		TicketRepository tickRep = new TicketRepository();
		EmployeeRepository empRep = new EmployeeRepository();
		
			app.get("/hello",  (Context ctx) -> {
				ctx.res().getWriter().write("Hello, client");
		});
		
		app.post("/new-ticket", ctx -> {
			
			Tickets receivedTicket = ctx.bodyAsClass(Tickets.class);
			tickRep.save(receivedTicket);
			recTick = receivedTicket.toString();
			System.out.println(receivedTicket);
			ctx.status(HttpStatus.CREATED_201);
		});
		
		app.post("/new-employee", ctx -> {
			Employee receivedEmployee = ctx.bodyAsClass(Employee.class);
			
			empRep.save(receivedEmployee);
			
			System.out.println(receivedEmployee);
			recEmp = receivedEmployee.toString();
			ctx.status(HttpStatus.CREATED_201);
		
		});
		
		app.get("/tickets",  (Context ctx) -> {
			ctx.json(tickRep.findAll());
	});
		
		app.get("/employees",  (Context ctx) -> {
			ctx.json(empRep.findAll());
	});
		
	}//End of MAIN
	
}//End of Class
