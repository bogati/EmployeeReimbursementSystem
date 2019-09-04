package com.bimalabogati.dao;
//package com.bimalabogati.*;

import static com.bimalabogati.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.bimalabogati.models.User;
import com.bimalabogati.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {
	


		private final static Logger logger = Logger.getLogger(UserDaoImpl.class);

		@Override
		public User selectUserById(Integer id) {
			User usr = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			try(Connection conn = ConnectionUtil.getConnection()){
				ps = conn.prepareStatement("SELECT * FROM ERS_USERS WHERE  ers_users_id= ?");
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				while(rs.next()){
					usr = new User(
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6),
								rs.getInt(7)
							);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				close(rs);
				close(ps);
			}
			return usr;
		}

		@Override
		public Boolean insertUser(User usr) {
			PreparedStatement ps = null;
			try(Connection conn = ConnectionUtil.getConnection()){
			
				ps = conn.prepareStatement("INSERT INTO ERS_USERS VALUES(?,?,?,?,?,?,?)");
			
				ps.setInt(1, usr.getUser_Id());
				ps.setString(2, usr.getUser_name());
				ps.setString(3, usr.getPassword());
				ps.setString(4, usr.getFirst_name());
				ps.setString(5, usr.getLast_name());
				ps.setString(6, usr.getEmail());
				ps.setInt(7, usr.getUser_role_id());

				ps.executeQuery();
				logger.info("New owner insert SUCCESS on user: " + usr.getUser_name());

				return true;
				
			}catch(SQLException e){
				e.printStackTrace();

				logger.error("New owner insert FAILED on user: " + usr.getUser_name());
				
			}finally{

				close(ps);
			}

			
			return false;
		}



		@Override
		public User selectUserByUsername(String username) {
			User usr = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			try(Connection conn = ConnectionUtil.getConnection()){
				ps = conn.prepareStatement("SELECT * FROM ERS_USERS WHERE LOWER(ers_username) = ?");
				ps.setString(1, username);
				rs = ps.executeQuery();
				
				while(rs.next()){
					usr = new User(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getInt(7)
						);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				close(rs);
				close(ps);
			}
			return usr;
		}

		@Override
		public List<User> selectAllUsers() {
			// TODO Auto-generated method stub
			return null;
		}
	}



