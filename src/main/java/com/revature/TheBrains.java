package com.revature;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.models.Tickets;
import com.revature.models.updateRequest;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.TicketRepository;
import com.revature.service.EmployeeService;
import com.revature.utils.AuthenticatorUtil;
import com.revature.utils.TicketPuller;
import com.revature.models.Employee;
import com.revature.models.LogIn;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class TheBrains {

	public static boolean authenticated;
	public static String newUserName;
	public static String recEmp;
	public static String recTick;
	public static String recCreds;
	public static String recReq;
	public static String loggedInAs;
	public static boolean exists;
	public static String userRole;
	public static boolean updated;
	static int ticketNum;
	static boolean inputBad;
	
	
	public static void main(String[] args) {
	
		
		Javalin app = Javalin.create().start(8000);
		TicketRepository tickRep = new TicketRepository();
		EmployeeRepository empRep = new EmployeeRepository();
		AuthenticatorUtil authUtil = new AuthenticatorUtil();
		EmployeeService empService = new EmployeeService();
		TicketPuller tP = new TicketPuller();
		
		
		
		
			app.get("/hello",  (Context ctx) -> {
				ctx.res().getWriter().write("Hello, client");
		});
		
		app.post("/new-ticket", ctx -> {
			
			Tickets receivedTicket = ctx.bodyAsClass(Tickets.class);
			receivedTicket.setTickSubmitter(loggedInAs);
			recTick = receivedTicket.toString();
			if ((receivedTicket.getTicketDesc().equals(""))||(receivedTicket.getTicketAmount()== 0.0)) {
				inputBad = true;
				ctx.status(HttpStatus.BAD_REQUEST_400);
			}else {
				inputBad = false;
			tickRep.save(receivedTicket);
			ctx.status(HttpStatus.CREATED_201);}
		});
		
		app.after("/new-ticket*", ctx -> {
			if(inputBad == true) {
		    ctx.result("Tickets Require A Valid Amount and Description");}
			else ctx.result("New Ticket Created");
		});
		
		app.post("/new-employee", ctx -> {
			exists = true;
			Employee receivedEmployee = ctx.bodyAsClass(Employee.class);
			recEmp = receivedEmployee.toString();
			newUserName = receivedEmployee.getUsername();
			exists = empService.usernameExists(newUserName);
			System.out.println(newUserName);
			System.out.println(exists);
			
			if (exists == false) {
			empRep.save(receivedEmployee);
			ctx.status(HttpStatus.CREATED_201);}
			
			else ctx.status(HttpStatus.BAD_REQUEST_400);
		});
		
		app.after("/new-employee*", ctx -> {
			if(exists == false) {
		    ctx.result("You have created the new User: "+ newUserName);}
			else ctx.result("Invalid Request\nThe Username (" + newUserName +") already exists in the database.");
		});
		
				
		app.post("/login", ctx -> {
			LogIn creds = ctx.bodyAsClass(LogIn.class);
			recCreds = creds.toString();
			authenticated = recCreds.equals(authUtil.Authenticator(creds.getUsername()));
			userRole = authUtil.GetUserRole(creds.getUsername());
			if (authenticated == true) {
			loggedInAs = creds.getUsername();
			ctx.status(HttpStatus.ACCEPTED_202);
			}
			else ctx.status(HttpStatus.BAD_REQUEST_400);
		});
		
		app.after("/login*", ctx -> {
			if(authenticated == true) {
		    ctx.result("You are logged in as Username: "+ loggedInAs);}
			else ctx.result("Invalid Username/Password.");
		});
		
		app.get("/pending_tickets",  (Context ctx) -> {
			if(userRole.equals("Manager")) {
			ctx.json(tickRep.findAllPending());}
			else if (userRole.equals("Employee")){
				ctx.status(HttpStatus.BAD_REQUEST_400);;}
		});
		
		app.get("/completed_tickets",  (Context ctx) -> {
			if(userRole.equals("Manager")) {
			ctx.json(tickRep.findAllComplete());}
			else if (userRole.equals("Employee")){
				ctx.status(HttpStatus.BAD_REQUEST_400);;}
		});
		
		app.after("/completed_tickets*", ctx -> {
			if(userRole.equals("Employee")) {
		    ctx.result("You are not authorized to view this page");}
		});
		
		app.after("/pending_tickets*", ctx -> {
			if(userRole.equals("Employee")) {
		    ctx.result("You are not authorized to view this page");}
		});
			
	
		app.get("/employee_tickets",  (Context ctx) -> {
			ctx.json(tP.PullTickets(loggedInAs));
			
	});
		
		app.get("/employees",  (Context ctx) -> {
			ctx.json(empRep.findAll());
			System.out.println(empRep.findAll());
	});
		
		app.post("/updateTicketStatus", ctx -> {
			if (userRole.equals("Manager")) {
			updateRequest upReq = ctx.bodyAsClass(updateRequest.class);
			recReq = upReq.toString();
			String updateCommand =upReq.getNewStatus();
			ticketNum = upReq.getTicketNumber();
			String shouldUpdate = authUtil.StatusChecker(ticketNum);
			if (!shouldUpdate.equals("Pending")) {
				updated = false;
				ctx.status(HttpStatus.BAD_REQUEST_400);
			}
			else {
			tickRep.updateTicket(updateCommand, ticketNum);
			updated = true;}
			ctx.status(HttpStatus.ACCEPTED_202);}
			else ctx.status(HttpStatus.BAD_REQUEST_400);
		});
	
		app.after("/updateTicketStatus*", ctx -> {
			if((updated == true)&&(userRole.equals("Manager"))) {
		    ctx.result("Ticket Status Updated");}
			else if((updated == false)&&(userRole.equals("Manager"))) {
			    ctx.result("Ticket Status Already Changed To : " + authUtil.StatusChecker(ticketNum));
				ctx.status(HttpStatus.BAD_REQUEST_400);}
			else ctx.result("You Do Not Have Permission To Update Ticket Statuses");
		});
	}//End of MAIN
	
}//End of Class
