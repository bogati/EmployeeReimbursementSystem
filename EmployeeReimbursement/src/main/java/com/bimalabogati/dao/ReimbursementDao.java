package com.bimalabogati.dao;

import java.util.List;

import com.bimalabogati.models.Reimbursement;

public interface ReimbursementDao {
	
	public Boolean insertReimbursement(Reimbursement rein);
	public Boolean insertReimbursementAllValues(Reimbursement rein);
	public List<Reimbursement> viewPastTickets();//same as reimbursement id 
	public Boolean updateReimbursement(Reimbursement rein);
	public List<Reimbursement> viewPastTicketsById(Integer user_id);
	public List<Reimbursement> selectPendingTickets();

}
