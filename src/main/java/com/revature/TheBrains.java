package com.revature;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.models.Tickets;
import com.revature.models.updateRequest;
import com.revature.repository.TicketRepository;
import com.revature.service.EmployeeService;
import com.revature.service.TicketService;
import com.revature.models.Employee;
import com.revature.models.LogIn;

import io.javalin.Javalin;
import io.javalin.http.Context;
import jakarta.servlet.http.Cookie;


public class TheBrains {

	public static boolean authenticated = false;
	static String recUserName;
	static String recPassword;
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
	
		EmployeeService empService = new EmployeeService();
		TicketService tickService = new TicketService();
		//EmployeeRepository empRep = new EmployeeRepository();
		Javalin app = Javalin.create().start(8000);
		TicketRepository tickRep = new TicketRepository();
	
		
		
		
		
			app.get("/hello",  (Context ctx) -> {
				ctx.res().getWriter().write("Hello, client");
		});
		
		
			
			// *********THE NEW EMPLOYEE PAGE IS FINISHED ********
			app.post("/new-employee", ctx -> {
								exists = true;
				Employee receivedEmployee = ctx.bodyAsClass(Employee.class);
				recEmp = receivedEmployee.toString();
				newUserName = receivedEmployee.getUsername();
				exists = empService.usernameExists(newUserName);
				
				if (exists == false) {
				empService.saveEmployee(receivedEmployee);
				
				ctx.status(HttpStatus.CREATED_201);}
				
				else 
					ctx.status(HttpStatus.BAD_REQUEST_400);
				
				
			});
			
			app.after("/new-employee*", ctx -> {
				if(exists == false) {
			    ctx.result("You have created the new User: "+ newUserName);}
				else ctx.result("Invalid Request\nThe Username (" + newUserName +") already exists in the database.");
			});
			// ************ABOVE CODE IS FINAL AND UNIT TESTED *********	
			
			
			// ************LOG IN PAGE IS FINISHED **************
			app.post("/login", ctx -> {
				
				LogIn creds = ctx.bodyAsClass(LogIn.class);
				recUserName = creds.getUsername();
				recPassword = creds.getPassword();
				authenticated = empService.userAuthentication(recUserName,recPassword);
				userRole = empService.getUsersRole(recUserName);
				if (authenticated == true) {
					Cookie userCookie = new Cookie ("authenticated","true");
					userCookie.setHttpOnly(true);
					loggedInAs = creds.getUsername();
					ctx.status(HttpStatus.ACCEPTED_202);
					ctx.res().addCookie(userCookie);
				}
				else ctx.status(HttpStatus.BAD_REQUEST_400);
			});
			
			app.after("/login*", ctx -> {
				if(authenticated == true) {
			    ctx.result("You are logged in as Username: "+ loggedInAs);}
				else ctx.result("Invalid Username/Password.");
			});
				
			// *********ABOVE CODE IS FINISHED AND UNIT TESTED ********
			
						
			
			
			app.post("/new-ticket", ctx -> {
				if(authenticated == true) {
				Tickets receivedTicket = ctx.bodyAsClass(Tickets.class);
				receivedTicket.setTickSubmitter(loggedInAs);
				recTick = receivedTicket.toString();
				if ((receivedTicket.getTicketDesc().equals(""))||(receivedTicket.getTicketAmount()== 0.0)) {
					inputBad = true;
					ctx.status(HttpStatus.BAD_REQUEST_400);
				}else {
					inputBad = false;
					tickRep.save(receivedTicket);
					ctx.status(HttpStatus.CREATED_201);}}
				});
		
			app.after("/new-ticket*", ctx -> {
				if(inputBad == true) {
					ctx.result("Tickets Require A Valid Amount and Description");}
				else if (authenticated == false) {
					ctx.result("You Must Be Logged In To Create A New Ticket!");
				}
				else ctx.result("New Ticket Created");
			});
		
		
			// *****UPDATED
			app.get("/pending_tickets",  (Context ctx) -> {
				if(authenticated == true) {
				if(userRole.equals("Manager")) {
					ctx.json(tickService.getPendingTickets());}
				else if (userRole.equals("Employee")){
					ctx.status(HttpStatus.BAD_REQUEST_400);;}}
			});
		
			app.after("/pending_tickets*", ctx -> {
				if(authenticated == false) {
					ctx.result("You are not logged in!");
					ctx.status(HttpStatus.BAD_REQUEST_400);
				}
				if((authenticated == true)&&(userRole.equals("Employee"))) {
					ctx.result("You are not authorized to view this page");
					ctx.status(HttpStatus.BAD_REQUEST_400);
				}
				
			});
		
		
			// ****UPDATED & TESTED
			app.get("/completed_tickets",  (Context ctx) -> {
				if (authenticated == true) {
				if(userRole.equals("Manager")) {
					ctx.json(tickService.getCompletedTickets());}
				else if (userRole.equals("Employee")){
					ctx.status(HttpStatus.BAD_REQUEST_400);;}
				}
			});
		
			app.after("/completed_tickets*", ctx -> {
				if(userRole.equals("Employee")) {
					ctx.result("You are not authorized to view this page");}
				if(authenticated == false) {
				ctx.result("You are not logged in");
				ctx.status(HttpStatus.BAD_REQUEST_400);}
			});
			// *****COMPLETE
		
			
			// *******UPDATED & TESTED
			app.get("/employee_tickets",  (Context ctx) -> {
				if(authenticated == true) {
				ctx.json(tickService.ticketPuller(loggedInAs));}
				else ctx.result("You Are Not Logged In");
			});
		
		
			app.post("/updateTicketStatus", ctx -> {
				if((authenticated == true) && (userRole.equals("Manager"))) {
					updateRequest upReq = ctx.bodyAsClass(updateRequest.class);
					recReq = upReq.toString();
					String updateCommand =upReq.getNewStatus();
					ticketNum = upReq.getTicketNumber();
					String shouldUpdate = tickService.getTicketStatus(ticketNum);
					if (!shouldUpdate.equals("Pending")) {
						updated = false;
						ctx.status(HttpStatus.BAD_REQUEST_400);
					}
					
				else {
						tickRep.updateTicket(updateCommand, ticketNum);
						updated = true;
					ctx.status(HttpStatus.ACCEPTED_202);}}
				else ctx.status(HttpStatus.BAD_REQUEST_400);
			});
	
			app.after("/updateTicketStatus*", ctx -> {
				if(authenticated == false) {
					ctx.result("You are not logged in");
					ctx.status(HttpStatus.BAD_REQUEST_400);}
				else if((updated == true)&&(userRole.equals("Manager"))) {
					ctx.result("Ticket Status Updated");}
				else if((authenticated == true) &&(updated == false)&&(userRole.equals("Manager"))) {
					ctx.result("Ticket Status Already Changed To : " + tickService.getTicketStatus(ticketNum));
					ctx.status(HttpStatus.BAD_REQUEST_400);}
				
				
				else if ((authenticated == true) && (userRole.equals("Employee"))){
					ctx.result("You Do Not Have Permission To Update Ticket Statuses");
				}
			});
			
			app.get("/logout",(Context ctx) -> {
				
				if (authenticated == true) {
					authenticated = false;
					Cookie [] cookies = ctx.req().getCookies();
					for (Cookie cookie : cookies) {
					if(cookie.getName().equals("authenticated")) {
						
						cookie.setValue("false");
						ctx.res().addCookie(cookie);
						ctx.result("UserName: " + loggedInAs + " Has Been Logged Out.");
					}
				}} else if (authenticated == false) {ctx.result("You Must Be Logged In Before You Can Log Out!");}
					
			});
	}//End of MAIN
	
}//End of Class
