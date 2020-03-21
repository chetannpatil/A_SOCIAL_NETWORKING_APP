package org.chetan.service.impl;

import java.util.List;
import java.util.Set;
import org.chetan.dao.UserDao;
import org.chetan.model.User;
import org.chetan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

        //userServiceImpl
@Service("userServiceImpl")
@Transactional(propagation=Propagation.SUPPORTS ,rollbackFor=Exception.class)
public class UserServiceImpl implements UserService
{

	@Autowired
	UserDao userDaoImpl ;
	@Override
	public void create(User user) 
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl create()");
		userDaoImpl.create(user);
		
	}

	@Override
	public void update(User user)
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl update(User user)");
		userDaoImpl.update(user);
		
	}

	@Override
	public User edit(long userId) 
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl create()");
		return userDaoImpl.edit(userId) ;
	}

	@Override
	public void delete(long userId) 
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl delete(long userId)");
		userDaoImpl.delete(userId);
		
	}

	@Override
	public User find(long userId) 
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImplfind(long userId)");
		return userDaoImpl.find(userId) ;
	}
	
	@Override
	public User find(String email)
	{
		
		return userDaoImpl.find(email) ;
	}
	@Override
	public List<User> getAll() 
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl getAll()");
		return userDaoImpl.getAll();
	}

	@Override
	public Set<User> searchAllMatchingUsers(String searchStr) 
	{
	
     return userDaoImpl.searchAllMatchingUsers(searchStr) ;
	}

	@Override
	public Set<User> advancedSearch(String firstName, String lastName,
			String email, String city, String state, String country)
	{
		System.out.println("Userservicimpl advcserch()");
		return userDaoImpl.advancedSearch(firstName, lastName, email,city, state, country);
	}

/*	@Override
	public Blob loadDP(long userId) 
	{
		System.out.println("Userservicimpl advcserch() loadDP(long userId) ");
		 
		return userDaoImpl.loadDP(userId);
	}*/

	

}
