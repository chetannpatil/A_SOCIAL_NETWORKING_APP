package org.chetan.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.chetan.dao.InvitationDao;
import org.chetan.model.Invitation;
import org.chetan.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("invitationDaoImpl")
public class InvitationDaoImpl implements InvitationDao
{

	@Autowired
	SessionFactory sessionFactory;	
	private Session getSession()
	{
		System.out.println("invitaionDAoImpl getSession()");
		return sessionFactory.getCurrentSession() ;
	}	
	@Override
	public void create(Invitation invitation)
	{
		System.out.println("InvitaionDaoimp  create(Invitation invitation)");
		getSession().save(invitation) ;		
	}

	@Override
	public void update(Invitation invitation)
	{
		// TODO Auto-generated method stub
		System.out.println("InvitaionDaoimp update(Invitation invitation)");
		getSession().update(invitation);
		
	}

	@Override
	public Invitation edit(long inviteId)
	{
		// TODO Auto-generated method stub
		System.out.println("InvitaionDaoimp edit(long inviteId)");
		//return null;
		return find(inviteId);
	}

	@Override
	public void delete(long inviteId)
	{
		// TODO Auto-generated method stub
		System.out.println("InvitaionDaoimp delete(long inviteId)");
		//getSession().delete(inviteId);
		//Query  q = getSession().createQuery("delete from INVITATION where inviteId = :id");
		Query q = getSession().getNamedQuery("del");
		//q.setParameter("id",inviteId);
		//q.list();
		q.setLong(0, inviteId);
		q.executeUpdate();
		//q.list(); dost work ...ecpetion
		
	}

	@Override
	public Invitation find(long inviteId)
	{
		System.out.println("InvitaionDaoimp find(long inviteId)");
	/*	Query q = getSession().createQuery("from Invitation wehre inviteId = :t");
		q.setParameter(0, inviteId);
		List<Invitation> invtnsList = (List<Invitation>)q.list() ;
		if(invtnsList == null || invtnsList.size() == 0)
			throw new IllegalArgumentException("Coulnt find Invittion for inviteId = "+inviteId);
		return invtnsList.get(0) ;*/
		
		return getSession().get(Invitation.class, inviteId) ;
		
	}

	@Override
	public Invitation find(User invitationFromUser,User invitationToUser)
	{
		System.out.println("InvitaionDaoimp find(User invitationFromUser)");
		Query q = getSession().createQuery("from Invitation where invitationFromUser = :f and invitationToUser = :t");
		q.setParameter("f", invitationFromUser);
		q.setParameter("t", invitationToUser) ;
		
		List<Invitation> invtnsList = (List<Invitation>)q.list() ;
		
		if(invtnsList == null || invtnsList.size() == 0)
		{
			String excStr = "User = "+
		invitationFromUser.getFirstName()+" has not yet sent request to "+invitationToUser.getFirstName();
			throw new IllegalArgumentException(excStr) ;
		}
		
		return invtnsList.get(0) ;
	}

	@Override
	public List<Invitation> getAll() 
	{
		System.out.println("InvitaionDaoimp getAll() ");
		Query q  = getSession().createQuery("from Invitation");
		List<Invitation> invtList = (List<Invitation>)q.list() ;
		
		return invtList ;
	}

	@Override
	public boolean isInvitationExist(User invitationFromUser,User invitationToUser)			
	{
		System.out.println("InvitaionDaoimp isInvitationExist(User invitationFromUser,User invitationToUser)	");
		Query q = getSession().createQuery("from Invitation where invitationFromUser = :f and invitationToUser = :t");
		q.setParameter("f", invitationFromUser);
		q.setParameter("t", invitationToUser) ;		
		List<Invitation> invtnsList = (List<Invitation>)q.list() ;		
		if(invtnsList == null || invtnsList.size() == 0)
			return false ;
		else
		{
			for(Invitation invtn :invtnsList)
			{
				System.out.println("for intn = "+invtn);
				if( (invtn.getInvitationFromUser().equals(invitationFromUser) == true) 
						&& ( invtn.getInvitationToUser().equals(invitationToUser) == true) )
				{
					return true ;
				}
			}
			
		}
		return false ;	
		/*if(invtnsList == null || invtnsList.size() == 0)
		{
			String excStr = "User = "+
		invitationFromUser.getFirstName()+" has not yet sent request to "+invitationToUser.getFirstName();
			throw new IllegalArgumentException(excStr) ;
		}*/
	}

	

	@Override
	public boolean isLoggedUserHasPendingInvitations(User invitationToUser)
	{
		System.out.println("InvDaoImpl isLoggedUserHasPendingInvitations(User invitationToUser)");
		//loggedUsersPendingInvitationsQuery
		Query q = getSession().getNamedQuery("loggedUsersPendingInvitationsQuery");
		
		q.setLong(0,invitationToUser.getUserId());
	
		List<Invitation> pendInvList = (List<Invitation>) q.list();
		if(pendInvList == null || pendInvList.size() == 0)
			return false ;
		else
			return true ;
	}

	@Override
    public List<Invitation> getAllPendingInvitationsOfLoggedUser(User invitationToUser) 			
    {
		System.out.println("InvtDaoImpl getAllPendingInvitationsOfLoggedUser()");
		
		Query q = getSession().getNamedQuery("loggedUsersPendingInvitationsQuery");
		System.out.println("IndAoimpl tousers id = "+invitationToUser.getUserId());
		
		q.setLong(0,invitationToUser.getUserId());
		
		List<Invitation> pendInvList = ((List<Invitation>) q.list());
		
		List<Invitation> retList = new ArrayList<Invitation>(pendInvList);
	/*	System.out.println("InvDAoImpl pendiList size = "+pendInvList.size());
		System.out.println("InvDAoImpl clals of pend lsit = "+pendInvList.getClass());
		System.out.println("InvDAoImpl elemnt inside AL =  "+pendInvList.get(0));
		if( pendInvList.get(0) instanceof Invitation)
			System.out.println("yes elemnt is invta type");
		else
			System.out.println(" no elemnt is not invta type");
		for(Object i:pendInvList)
		{
			System.out.println("----for invt ofinDaoIMpl  invt = "+i);
			if( i instanceof ArrayList)
			{
				ArrayList a = (ArrayList)i;
				System.out.println("----for invt ofinDaoIMpl  invt al = "+a);
			}
			
		}*/
		//return pendInvList ;
		return retList ;
	}
	@Override
	public boolean isLoggedUserHasSentAnyBFRequests(User invitationFromUser) 
	{
		System.out.println("InvtDaoImpl isLoggedUserHasSentAnyBFRequests(User invitationFromUser) ");
		Query q = getSession().createQuery("from Invitation where invitationFromUser = :ifu and isInvitationAccepted = false");
		q.setParameter("ifu", invitationFromUser) ;
		
		List<Invitation> ls =(List<Invitation>) q.list();
		
		if(ls == null || ls.size() == 0)
			return false ;
		else
			return true ;
		
			
	}
	@Override
	public List<Invitation> getAllBFRequestsSentByLoggedUser(User invitationFromUser) 
	{
		System.out.println("InvtDaoImpl getAllBFRequestsSentByLoggedUser(User invitationFromUser)  ");
		Query q = getSession().createQuery("from Invitation where invitationFromUser = :ifu  and isInvitationAccepted = false");
		q.setParameter("ifu", invitationFromUser) ;
		
		List<Invitation> allBFRequsls =(List<Invitation>) q.list();
		
		return allBFRequsls ;
		
	}

	

}
