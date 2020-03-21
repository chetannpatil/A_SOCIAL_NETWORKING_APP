package org.chetan.service.impl;

import java.util.List;

import org.chetan.dao.WallDao;
import org.chetan.model.User;
import org.chetan.model.Wall;
import org.chetan.service.WallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("wallServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class WallServiceImpl implements WallService
{

	//inst var og wallDaoImpl
	@Autowired
	WallDao wallDaoImpl ;
	
	@Override
	public void create(Wall wall) 
	{
		System.out.println("------WallServiceIMpl create(Wall wall) ");
		wallDaoImpl.create(wall);
	}

	@Override
	public void update(Wall wall) 
	{
		System.out.println("------WallServiceIMpl update(Wall wall)  ");
		wallDaoImpl.update(wall);
	}

	@Override
	public int delete(long wallId)
	{
		System.out.println("------WallServiceIMpl delete(long wallId)");
		return wallDaoImpl.delete(wallId);
	}

	@Override
	public Wall find(long wallId) 
	{
		System.out.println("------WallServiceIMpl find(long wallId)  ");
		return wallDaoImpl.find(wallId);
	}

	@Override
	public List<Wall> getAllWalls(User wallToUser)
	{
		System.out.println("------WallServiceIMpl getAllWalls(User wallToUser)");
		return wallDaoImpl.getAllWalls(wallToUser) ;
	}



}
