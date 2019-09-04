package com.bimalabogati.services;

import java.io.InputStream;

import org.apache.log4j.Logger;

import com.bimalabogati.dao.ReimbursementDaoImpl;
import com.bimalabogati.dao.UserDao;
import com.bimalabogati.dao.UserDaoImpl;
import com.bimalabogati.models.Reimbursement;
import com.bimalabogati.models.User;



public class UsersService {
	/*When this method is called, the string to be hashed is passed 
	in as a char array hashThePassword(stringPassedIn.toCharArray())
	*/
	public static String passwordHash(char[] passIn)
	    {
	        String returnVariable = "";
	        for(char i: passIn)
	        {
	            returnVariable += (7 * 31 + i);
	        }
	        return returnVariable;
	    }
	
	
	private static Logger frogger = Logger.getLogger(UsersService.class);
	
		public boolean confirmLogin(String username, String password) {
		UserDao ud = new UserDaoImpl();
		User usr = null;
			
			if((usr=ud.selectUserByUsername(username.toLowerCase()))!=null){
				if(usr.getPassword().equals(password)) {
					return true;
				}
			}
			return false;
		}
		public Integer confirmLoginManagement(String username, String password) {
			UserDao ud = new UserDaoImpl();
			User usr = null;
			if(((usr=ud.selectUserByUsername(username.toLowerCase()))!=null)&(usr.getUser_role_id()) != 1) {
				// Throw you are not in the Management please use your credentials for login 
				return 5;
				
			}
				
			else if((usr=ud.selectUserByUsername(username.toLowerCase()))!=null){
					if((usr.getPassword()).equals(password)) {
						//Throw username and password matched in the Servlet 
						return 1;
					}
				}
			
			//Throw invalid user name and password message in the Servlet
				return 2;
			
			}
		
		public boolean usernameExists(String username) {
			if(new UserDaoImpl().selectUserByUsername(username)!=null) {
				return true;
			}
			return false;
		}
		
		
		public boolean registerUser(Integer user_Id, String user_name, String password, String first_name,String last_name,
				   String email, Integer user_role_id)
		{
			if(new UserDaoImpl().insertUser(new User(user_Id, user_name, password, first_name,last_name,
					   email, user_role_id))) {
				return true;
			}
			return false;
		}
		
		
}


