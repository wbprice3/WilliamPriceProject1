package com.rev.project1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.models.Tickets;
import com.revature.models.Employee;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class TheBrains {

	public static String recEmp;
	public static String recTick;
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
			recTick = receivedTicket.toString();
			System.out.println(receivedTicket);
			
			try (
					BufferedWriter myWriter = Files.newBufferedWriter(Path.of("tickets.txt"), StandardOpenOption.APPEND)){myWriter.write(recTick + "\n");
					}catch (IOException e) {
						e.printStackTrace();
					}
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
			recEmp = receivedEmployee.toString();
			
		//	File myFile = new File("employees.txt");
			
/*			try (FileWriter fileWriter = new FileWriter(myFile);BufferedWriter writer = new BufferedWriter(fileWriter)){
			//FileOutputStream fos = new FileOutputStream(myFile); //deals in bytes
			 //deals in characters
			; //Deals in lines
			
			
			writer.write(recEmp);
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
*/			
			
			try (
					BufferedWriter myWriter = Files.newBufferedWriter(Path.of("employees.txt"), StandardOpenOption.APPEND)){myWriter.write(recEmp + "\n");
					}catch (IOException e) {
						e.printStackTrace();
					}
			ctx.status(HttpStatus.CREATED_201);
		
		});
		
		
		
	}//End of MAIN
	
}//End of Class
