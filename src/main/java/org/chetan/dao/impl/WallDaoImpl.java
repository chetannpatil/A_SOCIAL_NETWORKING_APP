package org.chetan.dao.impl;

import java.util.List;

import org.chetan.dao.WallDao;
import org.chetan.model.User;
import org.chetan.model.Wall;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("wallDaoImpl")
public class WallDaoImpl implements WallDao
{

	@Autowired
	SessionFactory sessionFactory ;
	
	private Session getSession()
	{
	 System.out.println("---WallAoIMpl  ");		
	 return	sessionFactory.getCurrentSession() ;
	}
	@Override
	public void create(Wall wall)
	{
		System.out.println("---WallAoIMpl create(Wall wall) ");
		getSession().save(wall);
		
	}

	@Override
	public void update(Wall wall) 
	{
		System.out.println("---WallAoIMpl  update(Wall wall) ");
		getSession().update(wall);
		
	}

	@Override
	public int delete(long wallId)
	{
		System.out.println("---WallAoIMpl delete(long wallId) ");
		//getSession().delete(wallId);
		Query  q = getSession().createQuery("delete from Wall where wallId = :wid");
		q.setParameter("wid", wallId);
		
		return q.executeUpdate();
		
		
	}

	@Override
	public Wall find(long wallId)
	{
		System.out.println("---WallAoIMpl  find(long wallId)");
		return getSession().get(Wall.class, wallId);
	}

	@Override
	public List<Wall> getAllWalls(User wallToUser) 
	{
		System.out.println("---WallAoIMpl getAllWalls(User user)  ");
		Query  q = getSession().createQuery("from Wall where wallToUser = :wtu");
		q.setParameter("wtu", wallToUser);
		
		List<Wall> allWallsOfUser = (List<Wall>)q.list() ;
		
		return allWallsOfUser ;
	}

}
