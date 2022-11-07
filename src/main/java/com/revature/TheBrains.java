package com.revature;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.models.Tickets;
import com.revature.models.updateRequest;
import com.revature.repository.TicketRepository;
import com.revature.service.EmployeeService;
import com.revature.service.TicketService;
import com.revature.models.AccountModel;
import com.revature.models.Employee;
import com.revature.models.LogIn;

import io.javalin.Javalin;
import io.javalin.http.Context;
import jakarta.servlet.http.Cookie;


public class TheBrains {

	public static boolean authenticated = false;
	static String recUserName;
	static String recPassword;
	private static String newUserName;
	public static String recEmp;
	public static String recTick;
	public static String recCreds;
	public static String recReq;
	private static String loggedInAs;
	private static boolean exists;
	private static String userRole;
	private static boolean updated;
	static int ticketNum;
	static boolean inputBad;
	private static Cookie userCookie = new Cookie("Authenticated", "false");
	
	
	public static void main(String[] args) {
	
		EmployeeService empService = new EmployeeService();
		TicketService tickService = new TicketService();
		//Javalin app = Javalin.create().start(8000);
		TicketRepository tickRep = new TicketRepository();
		
		Javalin app = Javalin.create(config -> {
		    config.plugins.enableCors(cors -> {
		        cors.add(it -> {
		            it.anyHost();
		            it.allowCredentials = true;
		            it.exposeHeader("x-server");
		        });
		    });
		});
	
		app.start(8000);
		
		
		
		
		
		
			
			// *********THE NEW EMPLOYEE PAGE IS FINISHED ********
			app.post("/register", ctx -> {
								exists = true;
				AccountModel receivedModel = ctx.bodyAsClass(AccountModel.class);
				String recModel = receivedModel.toString();
			//	newUserName = receivedModel.getEmail();
			//	exists = empService.usernameExists(newUserName);
				
			//	if (exists == false) {
				empService.saveEmployee(receivedModel);
				System.out.println(receivedModel);
				ctx.status(HttpStatus.CREATED_201);});
			
			
				
			//	else 
			//		ctx.status(HttpStatus.BAD_REQUEST_400);
				
				
		//	});
			
			app.after("/new-employee*", ctx -> {
				if(exists == false) {
			    ctx.result("You have created the new User: "+ newUserName);}
				else ctx.result("Invalid Request\nThe Username (" + newUserName +") already exists in the database.");
			});
			// ************ABOVE CODE IS FINAL AND UNIT TESTED *********	
			
			
			// ************LOG IN PAGE IS FINISHED **************
			app.before("/login*", ctx -> {
				if(userCookie.getValue().equals("True")) {
			    ctx.result("The Current User "+ loggedInAs + "Must Log Out First ");}
			});
			
			app.post("/login", ctx -> {
				
				LogIn creds = ctx.bodyAsClass(LogIn.class);
				recUserName = creds.getEmail();
				recPassword = creds.getPassword();
				authenticated = empService.userAuthentication(recUserName,recPassword);
				userRole = empService.getUsersRole(recUserName);
				System.out.println(creds);
				if (authenticated == true){
					System.out.println("Authenticated");
					
					userCookie.setValue("True");
					userCookie.setHttpOnly(true);
					loggedInAs = creds.getEmail();
					ctx.status(HttpStatus.ACCEPTED_202);
					ctx.res().addCookie(userCookie);
				}
				else {
					ctx.status(HttpStatus.BAD_REQUEST_400);
					System.out.println("Not Authenticated");
				}
			});
			
			app.after("/login*", ctx -> {
				if(authenticated == true) {
			    ctx.result("You are logged in as Username: "+ loggedInAs);}
				else ctx.result("Invalid Username/Password.");
			});
				
			// *********ABOVE CODE IS FINISHED AND UNIT TESTED ********
			
						
			
			
			app.post("/new-ticket", ctx -> {
				if(userCookie.getValue().equals("True")) {
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
				if(userCookie.getValue().equals("True")) {
					if(userRole.equals("Manager")) {
					if (tickService.getPendingTickets().size()==0) {
						ctx.result("No Pending Tickets");
					}else 
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
				if(userCookie.getValue().equals("True")) {
					if(userRole.equals("Manager")) {
					ctx.json(tickService.getCompletedTickets());}
				else if (userRole.equals("Employee")){
					ctx.status(HttpStatus.BAD_REQUEST_400);;}
				}
			});
		
			app.after("/completed_tickets*", ctx -> {
				if(userCookie.getValue().equals("True")) {
				if(userRole.equals("Employee")) {
					ctx.result("You are not authorized to view this page");}
				}
				if(authenticated == false) {
				ctx.result("You are not logged in");
				ctx.status(HttpStatus.BAD_REQUEST_400);}
			});
			// *****COMPLETE
		
			
			// *******UPDATED & TESTED
			app.get("/employee_tickets",  (Context ctx) -> {
				if(userCookie.getValue().equals("True")) {
				ctx.json(tickService.ticketPuller(loggedInAs));}
				else ctx.result("You Are Not Logged In");
			});
		
		
			app.post("/updateTicketStatus", ctx -> {
				if((userCookie.getValue().equals("True")) && (userRole.equals("Manager"))) {
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
				if(userCookie.getValue().equals("True")) {
					authenticated = false;
					userCookie.setValue("false");
					ctx.res().addCookie(userCookie);
					ctx.result("UserName: " + loggedInAs + " Has Been Logged Out.");
					}
				 else if (authenticated == false) {ctx.result("You Must Be Logged In Before You Can Log Out!");}
					
			});
	}//End of MAIN
	
}//End of Class
