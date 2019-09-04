package com.bimalabogati.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bimalabogati.dao.ReimbursementDaoImpl;
import com.bimalabogati.services.ReimbursementServices;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Servlet implementation class ViewPastTicketsServlet
 */
public class ViewJSONmyticketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private static Logger logger = Logger.getLogger(ViewJSONmyticketsServlet.class);
	private static ObjectMapper om = new ObjectMapper();
	
    public ViewJSONmyticketsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		

		//RequestDispatcher rq = request.getRequestDispatcher("webcontents/viewtable.html");
		//rq.forward(request, response);
		
		//final String URI = request.getRequestURI();
		System.out.println("entered in jsonmyticket servlet");
	
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		Integer employee_id = Integer.parseInt(request.getParameter("reimb_author"));
		System.out.println(employee_id);

		out.println(om.writeValueAsString(new ReimbursementDaoImpl().viewPastTicketsById(employee_id)));
		
		//System.out.println("forwarded successfully!");
		//System.out.println("The URI is"+URI);

		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
