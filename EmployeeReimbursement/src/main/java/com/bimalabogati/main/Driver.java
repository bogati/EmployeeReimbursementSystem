package com.bimalabogati.main;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import com.bimalabogati.dao.ReimbursementDaoImpl;
import com.bimalabogati.models.Reimbursement;
import com.bimalabogati.util.ConnectionUtil;


public class Driver {

	public static void main(String[] args) {
		
		
		
		Logger logger = Logger.getLogger(Driver.class);
		System.out.println("===START===");
		//logger.warn("Starting application...");
		//logger.warn("checking the connection to database");
		Connection conn = ConnectionUtil.getConnection();
		// if connection is not equal to null then print connection is successful
		//logger.warn("Connection to database successful !");
		System.out.println(conn);
		
		ReimbursementDaoImpl dao_reim = new ReimbursementDaoImpl();
		List<Reimbursement> reim_list = dao_reim.viewPastTicketsById(200);
		System.out.println("the size of the list is "+reim_list.size());
		Reimbursement obj = reim_list.get(1);
		System.out.println(obj.getReimb_description());
		System.out.println(obj.getReimb_amount());
		
		

		logger.warn("Ending application...");
		System.out.println("===END===");
		
		
		
	}

}
