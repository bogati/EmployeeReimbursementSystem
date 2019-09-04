package com.bimalabogati.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bimalabogati.dao.ReimbursementDaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * Servlet implementation class ViewPendingRequestsJSONServlet
 */
public class ViewPendingRequestsJSONServlet extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(ViewJSONmyticketsServlet.class);
	private static ObjectMapper om = new ObjectMapper();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPendingRequestsJSONServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("entered in pending all tickets servlet");

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(om.writeValueAsString(new ReimbursementDaoImpl().selectPendingTickets()));
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
