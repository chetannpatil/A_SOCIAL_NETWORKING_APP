package org.chetan.service;

import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.chetan.model.User;

public interface UserService
{

	void create(User user);
	void update(User user);
	User edit(long userId);
	void delete(long userId);
	User find(long userId);
	User find(String email);
	List<User> getAll();
	Set<User> searchAllMatchingUsers(String searchStr);
	Set<User> advancedSearch(String firstName,String lastName,String email,
			String city,String state,String country);	
	//Blob loadDP(long userId);
}
