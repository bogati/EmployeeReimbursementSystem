package com.bimalabogati.web;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bimalabogati.*;
import com.bimalabogati.services.UsersService;
import com.bimalabogati.util.HtmlTemplate;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("entered into the register servlet");
		Integer empl_id =  Integer.parseInt(request.getParameter("employee_id"));
		String usr_name = request.getParameter("User_Name");
		String usr_pwd = request.getParameter("Password");
		String fname = request.getParameter("First_Name");
		String lname = request.getParameter("Last_Name");
		String email = request.getParameter("email");
		Integer emp_role_id =  Integer.parseInt(request.getParameter("employee_role_id"));
		String role = request.getParameter("employee_role");
		
		System.out.println(empl_id);
		System.out.println(usr_name);
		System.out.println(usr_pwd);
		System.out.println(fname);
		System.out.println(lname);
		System.out.println(email);
		System.out.println(emp_role_id);
		System.out.println(role);
		
		
		PrintWriter out = HtmlTemplate.getHtmlOut(response);
		
		//confirm that the user did not enter information maliciously
		//(make sure that they didn't input nulls or empty strings)
		if(usr_name==null||usr_pwd==null)
		 {
			//System.out.println("The execution is inside null use case");
			response.sendError(418);
			return;
		}
		System.out.println("after the if loop right after checking the conditions");
		System.out.println(empl_id);
		System.out.println(usr_name);
		System.out.println(usr_pwd);
		System.out.println(fname);
		System.out.println(lname);
		System.out.println(email);
		System.out.println(emp_role_id);
		System.out.println(role);
		//confirm that the passwords match
		System.out.println("after the if loop right after checking the conditions after pritnting values");
		
		
		//confirm username doesn't exist
		
		if(new UsersService().usernameExists(usr_name)) {
			System.out.println("entered inside the second if");
			out.println(
					"<h3 style = 'color:maroon'>"
					+ usr_name.toUpperCase() + " Username Already Exists!"
					+ "</h3>"
					);
			HtmlTemplate.goBack(out);
			return;
		}
		System.out.println("after the second if loop right after checking the conditions usernamexist");
		
				
		
		if(new UsersService().registerUser(empl_id,usr_name,usr_pwd,fname,lname,email,emp_role_id)) {
			out.println(
					"<h3 style = 'color:maroon'>"
					+ usr_name.toUpperCase() + " CREATED! Please hit goback button to go back and sign in"
					+"please use the username and password that you just used to sign in"
					+ "</h3>"
					);
			System.out.println("after the if loop right after checking the conditions registeruser");
			HtmlTemplate.goBack(out);
			
			return;
		}else {
			out.println(
					"<h3>Something went wrong....</h3>"
					);
			HtmlTemplate.goBack(out);
			return;
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

