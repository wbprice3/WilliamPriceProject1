package com.rev.project1;

import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.models.Tickets;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.TicketRepository;
import com.revature.utils.AuthenticatorUtil;
import com.revature.models.Employee;
import com.revature.models.LogIn;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class TheBrains {

	public static boolean authenticated;
	public static String recEmp;
	public static String recTick;
	public static String recCreds;
	public static String loggedInAs;
	public static void main(String[] args) {
	
		
		Javalin app = Javalin.create().start(8000);
		TicketRepository tickRep = new TicketRepository();
		EmployeeRepository empRep = new EmployeeRepository();
		AuthenticatorUtil authUtil = new AuthenticatorUtil();
		
		
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
			recEmp = receivedEmployee.toString();
			System.out.println(recEmp);
			ctx.status(HttpStatus.CREATED_201);
			
		});
		
				
		app.post("/login", ctx -> {
			LogIn creds = ctx.bodyAsClass(LogIn.class);
			recCreds = creds.toString();
			authenticated = recCreds.equals(authUtil.Authenticator(creds.getUsername()));			
			System.out.println(recCreds);
			System.out.println(authUtil.Authenticator(creds.getUsername()));
			if (authenticated == true) {
			loggedInAs = creds.getUsername();
			ctx.status(HttpStatus.ACCEPTED_202);
			}
			else ctx.status(HttpStatus.BAD_REQUEST_400);
		});
		
		app.after("/login*", ctx -> {
			System.out.println(authenticated);
			if(authenticated == true) {
		    ctx.result("You are logged in as Username: "+ loggedInAs);}
			else ctx.result("Invalid Username/Password.");
		});
		
		app.get("/tickets",  (Context ctx) -> {
			ctx.json(tickRep.findAll());
	});
		
		app.get("/employees",  (Context ctx) -> {
			ctx.json(empRep.findAll());
			System.out.println(empRep.findAll());
	});
		
	}//End of MAIN
	
}//End of Class
