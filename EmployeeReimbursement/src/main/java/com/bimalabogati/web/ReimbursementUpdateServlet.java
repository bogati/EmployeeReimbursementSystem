package com.bimalabogati.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bimalabogati.services.ReimbursementServices;
import com.bimalabogati.services.UsersService;
import com.bimalabogati.util.HtmlTemplate;

/**
 * Servlet implementation class ReimbursementUpdateServlet
 */
public class ReimbursementUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("entered into the ReimbursementUpdateServlets servlet");
		
		Integer reimb_resolver = Integer.parseInt(request.getParameter("reimb_resolver"));
		Integer reimb_author = Integer.parseInt(request.getParameter("reimb_author"));
		Integer reimb_id = Integer.parseInt(request.getParameter("reimb_id"));
		
		Integer status_id = Integer.parseInt(request.getParameter("reimb_status_id"));
		
		
		System.out.println(reimb_resolver);
		System.out.println(reimb_author);
		System.out.println(status_id); // updateReimbursementRequest
		
		
		PrintWriter out = HtmlTemplate.getHtmlOut(response);
		
		if(new ReimbursementServices().updateReimbursementRequest(reimb_resolver,reimb_author,reimb_id,status_id )) {
			out.println(
					"<h3 style = 'color:maroon'>"
					+ "reimbursement updated for employee with id " + reimb_author 
					+ "</h3>"
					);
			
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
