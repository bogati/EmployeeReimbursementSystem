package com.bimalabogati.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bimalabogati.services.ReimbursementServices;
import com.bimalabogati.services.UsersService;
import com.bimalabogati.util.HtmlTemplate;

/**
 * Servlet implementation class SubmitRequest
 */
@MultipartConfig
public class SubmitRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.print("Entered into the SubmitRequest Servlet");
		//know how this method that was originally created works 
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = HtmlTemplate.getHtmlOut(response);
		
	
		Double amount = Double.parseDouble(request.getParameter("reimb_amount"));
	
		
		String description = request.getParameter("reimb_description");
		//look into this later for the datatype changed it to InputStream ,this is stored as BLOB in database
		
		
		InputStream reimb_receipt =null;
		
		try {
			if(request.getPart("reimb_receipt") !=null) {
				Part parsefile = request.getPart("reimb_receipt");//this var comes from form data 
				String fileName = Paths.get(parsefile.getSubmittedFileName()).getFileName().toString();
				reimb_receipt = parsefile.getInputStream();
			
			
			
		}
		}catch(javax.servlet.ServletException e) {
			
		}
		
		
		Integer author = Integer.parseInt(request.getParameter("reimb_author"));
		
	
		Integer type_id = Integer.parseInt(request.getParameter("reimb_type_id"));
		
		if(new ReimbursementServices().submitReimbursementRequest(amount,description,reimb_receipt,author,type_id )) {
			out.println(
					"<h3 style = 'color:maroon'>"
					+ "the reimbursement request with the employee id" + author + " has been created!"
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
		
		doGet(request, response);
	}

}
