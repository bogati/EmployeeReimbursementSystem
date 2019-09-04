package com.bimalabogati.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;


import com.bimalabogati.services.UsersService;
import com.bimalabogati.util.HtmlTemplate;


import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;



/**
 * Servlet implementation class LoginServlet
 */

public class LoginServletManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(LoginServletManagement.class);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("entered inside the login servlet inside do get method");
		String username = request.getParameter("User_Name");
		String password = request.getParameter("Password");
		System.out.println("print the username"+username);
		System.out.println("print the password"+password);
		
		logger.info("Login instantiated with username :" + username);
		if(new UsersService().confirmLoginManagement(username,password) == 1) {
			HttpSession session = request.getSession(); 
			//gets the session. Creates one if it does not exist
			session.setAttribute("User_Name", username);
			RequestDispatcher rq = request.getRequestDispatcher("webcontents/ManagementHome.html");
			rq.forward(request, response);
			logger.info(username + ":logged in successfully!");
		}
		else if (new UsersService().confirmLoginManagement(username,password) == 5) { 	
			PrintWriter out = HtmlTemplate.getHtmlOut(response);
			
			logger.warn("Hi " + username + ":suffered a failed login attempt!");
			out.println("<h3 style:'color:red'>\"You are not in Management according to our company's database !</h3>");
			out.println("<h3 style:'color:green'>\"Please use your Employee's login using employee login !</h3>");
			HtmlTemplate.goBack(out);

			
		}
		else {
			PrintWriter out = HtmlTemplate.getHtmlOut(response);
			logger.warn(username + ":suffered a failed login attempt!");
			out.println("<h3 style:'color:red'>Access Denied, please register the for the account !</h3>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
