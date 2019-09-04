package com.bimalabogati.dao;

import java.util.List;

import com.bimalabogati.models.User;

public interface UserDao {

	User selectUserById(Integer id);

	Boolean insertUser(User own);

	List<User> selectAllUsers();

	User selectUserByUsername(String username);

}
