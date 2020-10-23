package org.chetan.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialException;

import org.chetan.model.User;
import org.springframework.web.multipart.MultipartFile;

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
	//User updateProfilePic(User userBean);
	User updateProfilePic(long userId, MultipartFile profilePic) throws IOException, SerialException, SQLException;
	boolean removeDP(long userId);
}
