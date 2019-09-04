package com.bimalabogati.services;

import java.io.InputStream;

import com.bimalabogati.dao.ReimbursementDaoImpl;
import com.bimalabogati.models.Reimbursement;

public class ReimbursementServices {
	//This is only for the employee who submits the ticket this info will go into reimbursement table 
			// There will be other attributes that will come from management that will also go to reimbursement table
			public boolean submitReimbursementRequest(Double reimb_amount,String reimb_description, InputStream reimb_receipt,
					Integer reimb_author, Integer reimb_type_id)
			{
				if(new ReimbursementDaoImpl().insertReimbursement(new Reimbursement(reimb_amount,reimb_description,reimb_receipt,
						reimb_author,reimb_type_id))) {
					return true;
				}
				return false;
				
			}
			public boolean updateReimbursementRequest(Integer reimb_resolver, Integer reimb_author, Integer reimb_id,Integer status_id) {
				
				if (new ReimbursementDaoImpl().updateReimbursement(new Reimbursement(reimb_resolver,reimb_author,reimb_id,status_id))) {
					return true;
				}
				
				return false;
			}
			
			public boolean selectAllReimbursements( ) {
				
				if (new ReimbursementDaoImpl().viewPastTickets() !=null) {
					//return true when the object is not null 
					return true;
				}
				
				
				return false;
				
					
			}
			

}
