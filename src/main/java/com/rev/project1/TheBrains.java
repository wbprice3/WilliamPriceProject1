package com.rev.project1;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.models.Tickets;
import com.revature.models.Employee;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class TheBrains {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(8000);

		app.get("/hello",  (Context ctx) -> {
			
			ctx.res().getWriter().write("Hello, client");
		});
		
		app.post("/new-ticket", ctx -> {
			/*
			 * If a client is making a POST request, this means there must be something 
			 * in the HTTP request body that we can extract.
			 * 
			 * Note that we can extract the body as a String, or we can immediately marshal
			 * the body into a Java object.
			 * 
			 */
			Tickets receivedTicket = ctx.bodyAsClass(Tickets.class);
			System.out.println(receivedTicket);
			ctx.status(HttpStatus.CREATED_201);
		});
		
		app.post("/new-employee", ctx -> {
			/*
			 * If a client is making a POST request, this means there must be something 
			 * in the HTTP request body that we can extract.
			 * 
			 * Note that we can extract the body as a String, or we can immediately marshal
			 * the body into a Java object.
			 * 
			 */
			Employee receivedEmployee = ctx.bodyAsClass(Employee.class);
			System.out.println(receivedEmployee);
			ctx.status(HttpStatus.CREATED_201);
		});
	
	}//End of MAIN

}//End of Class
