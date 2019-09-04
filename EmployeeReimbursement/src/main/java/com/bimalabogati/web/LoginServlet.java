package com.bimalabogati.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;



import com.bimalabogati.*;
import com.bimalabogati.services.UsersService;
import com.bimalabogati.util.HtmlTemplate;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;



/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(LoginServlet.class);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("entered inside the login servlet inside do get method");
		String username = request.getParameter("User_Name");
		String password = request.getParameter("Password");
		System.out.println("print the username"+username);
		System.out.println("print the password"+password);
		
		logger.info("Login instantiated with username :" + username);
		if(new UsersService().confirmLogin(username,password) ){
			HttpSession session = request.getSession(); 
			//gets the session. Creates one if it does not exist
			session.setAttribute("User_Name", username);
			RequestDispatcher rq = request.getRequestDispatcher("webcontents/home.html");
			rq.forward(request, response);
			logger.info(username + ":logged in successfully!");
		}else {
			PrintWriter out = HtmlTemplate.getHtmlOut(response);
			logger.warn(username + ":suffered a failed login attempt!");
			out.println("<h3 style:'color:red'>Invalid credentials,please register the for the account or enter valid credentials !</h3>");
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
