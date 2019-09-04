package com.bimalabogati.dao;

import static com.bimalabogati.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import com.bimalabogati.models.Reimbursement;
import com.bimalabogati.services.UsersService;
import com.bimalabogati.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private static Logger logger = Logger.getLogger(UsersService.class);
	
public Boolean insertReimbursementAllValues(Reimbursement rein) {
		PreparedStatement ps = null;
	
		try(Connection conn = ConnectionUtil.getConnection()){
		
			ps = conn.prepareStatement("INSERT INTO ERS_REIMBURSEMENT VALUES(ers_reimb_seq.nextval,CURRENT_TIMESTAMP,(null),?,?,?,?,?,?,?)");
			
			ps.setDouble(1, rein.getReimb_amount());
			ps.setString(2, rein.getReimb_submitted());
			ps.setString(3, rein.getReimb_resolved());
			ps.setString(4,rein.getReimb_description());
			ps.setBinaryStream(5,rein.getReimb_receipt());
			ps.setInt(6,rein.getReimb_author());
			ps.setInt(7, rein.getReimb_resolver());
			ps.setInt(8, rein.getReimb_status_id());
			ps.setInt(9, rein.getReimb_type_id());
			ps.executeQuery();
			logger.info("reimbursement successfuly submitted for employee id number " + rein.getReimb_author());

			return true;
			
		}catch(SQLException e){
			e.printStackTrace();

			logger.error("New reimbursement insert FAILED on Employee id number: " + rein.getReimb_author());
			
		}finally{

			close(ps);
		}

		
		return false;
	}
public Boolean insertReimbursement(Reimbursement rein) {
	PreparedStatement ps = null;

	try(Connection conn = ConnectionUtil.getConnection()){
	
		ps = conn.prepareStatement("INSERT INTO ERS_REIMBURSEMENT VALUES(ers_reimb_seq.nextval,?,CURRENT_TIMESTAMP,(null),?,?,?,(null),(null),?)");
		
		//the first value in the reimbursement table is reimbursement id that is auto-generated 
		//the second value is the reimbursement amount
		ps.setDouble(1, rein.getReimb_amount());
		// the third value is the time when reimbursement was submitted, send as CURRENT_TIMESTAMP
		//ps.setString(2, rein.getReimb_submitted());
		//The fourth value in the database is time of resolve which we will set null now 
		//ps.setString(3, rein.getReimb_resolved()); this value is going to be set to null 
		//the fifth value in db is reimbursement description 
		ps.setString(2,rein.getReimb_description());
		//the sixth value to be inserted is image which is null right now - we will implement later
		ps.setBlob(3,rein.getReimb_receipt());
		//ps.setBinaryStream(56,rein.getReimb_receipt());
		ps.setInt(4,rein.getReimb_author());
		//we do not know the resolver yet so send the value later and so is the case with the status
		//ps.setInt(7, rein.getReimb_resolver());
		//ps.setInt(8, rein.getReimb_status_id());
		ps.setInt(5, rein.getReimb_type_id());
		ps.executeQuery();
		
		logger.info("reimbursement successfuly submitted for employee id number " + rein.getReimb_author());

		return true;
		
	}catch(SQLException e){
		e.printStackTrace();

		logger.error("New reimbursement insert FAILED on Employee id number: " + rein.getReimb_author());
		
	}finally{

		close(ps);
	}

	
	return false;
}



public List<Reimbursement> viewPastTicketsById(Integer user_id) {
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Reimbursement> reimb = new ArrayList<>();
	
	try(Connection conn = ConnectionUtil.getConnection()){
		ps = conn.prepareStatement("select * from ers_reimbursement where reimb_author = ?");
		ps.setInt(1, user_id);
		rs = ps.executeQuery();
		
		/*while(rs.next()) {
			System.out.println("the data from db is "+rs.getInt(1));
			System.out.println("the data from db is "+rs.getString(2));
			System.out.println("the data from db is "+rs.getString(3));
		}*/
		
	while(rs.next()) {
			
		reimb.add(new Reimbursement(
				rs.getInt(1),
				rs.getDouble(2),
				rs.getString(3),
				rs.getString(4),
				rs.getString(5),
				rs.getBinaryStream(6),
				rs.getInt(7),
				rs.getInt(8),
				rs.getInt(9),
				rs.getInt(10)	
		
				));
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(rs);
		close(ps);
	}
	

	return reimb;

}
@Override
public Boolean updateReimbursement(Reimbursement rein) {
	
	PreparedStatement ps = null;

	try(Connection conn = ConnectionUtil.getConnection()){
		
	
		ps = conn.prepareStatement("UPDATE ERS_REIMBURSEMENT SET reimb_resolved =SYSTIMESTAMP, reimb_author= ?, reimb_resolver= ? ,reimb_status_id= ? where reimb_id=? ");
		ps.setInt(1,rein.getReimb_author());
		ps.setInt(2,rein.getReimb_resolver());
		ps.setInt(3, rein.getReimb_status_id());
		ps.setInt(4, rein.getReimb_id());
		//ps.setInt(5, rein.getReimb_author());
		ps.executeQuery();
		return true;
	}
		catch (SQLException e) {
			e.printStackTrace();
			
			} finally {
			close(ps);
			}
		return false;
	
	}


public List<Reimbursement> viewPastTickets() {
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Reimbursement> reimb = new ArrayList<>();
	
	try(Connection conn = ConnectionUtil.getConnection()){
		ps = conn.prepareStatement("select * from ers_reimbursement");
		
		rs = ps.executeQuery();
		
	while(rs.next()) {
			
		reimb.add(new Reimbursement(
				rs.getInt(1),
				rs.getDouble(2),
				rs.getString(3),
				rs.getString(4),
				rs.getString(5),
				rs.getBinaryStream(6),
				rs.getInt(7),
				rs.getInt(8),
				rs.getInt(9),
				rs.getInt(10)	
		
				));
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(rs);
		close(ps);
	}
	

	return reimb;
}
@Override
public List<Reimbursement> selectPendingTickets() {
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Reimbursement> reimb = new ArrayList<>();
	
	try(Connection conn = ConnectionUtil.getConnection()){
		ps = conn.prepareStatement("select * from ers_reimbursement where reimb_status_id = 1");
		
		rs = ps.executeQuery();
		
	while(rs.next()) {
			
		reimb.add(new Reimbursement(
				rs.getInt(1),
				rs.getDouble(2),
				rs.getString(3),
				rs.getString(4),
				rs.getString(5),
				rs.getBinaryStream(6),
				rs.getInt(7),
				rs.getInt(8),
				rs.getInt(9),
				rs.getInt(10)	
		
				));
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(rs);
		close(ps);
	}
	

	return reimb;
}	


} //end of the class 



