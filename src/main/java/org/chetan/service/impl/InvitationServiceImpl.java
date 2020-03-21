package org.chetan.service.impl;

import java.util.List;

import org.chetan.dao.InvitationDao;
import org.chetan.model.Invitation;
import org.chetan.model.User;
import org.chetan.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("invitationServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor=Exception.class)
public class InvitationServiceImpl implements InvitationService
{
	
	@Autowired
	InvitationDao invitationDaoImpl ;
	
	@Override
	public void create(Invitation invitation) 
	{
		System.out.println("InvitaionSrviceImpl 's create(Invitation invitation) ");
		invitationDaoImpl.create(invitation);
	}

	@Override
	public void update(Invitation invitation) 
	{
		System.out.println("InvitaionSrviceImpl 's update(Invitation invitation) ");
		invitationDaoImpl.update(invitation);
	}

	@Override
	public Invitation edit(long inviteId)
	{
		System.out.println("InvitaionSrviceImpl 's edit(long inviteId)");
	  return invitationDaoImpl.edit(inviteId) ;
	}

	@Override
	public void delete(long inviteId) 
	{
		// TODO Auto-generated method stub
		System.out.println("InvitaionSrviceImpl 's delete(long inviteId) ");
		invitationDaoImpl.delete(inviteId);
		
	}

	@Override
	public Invitation find(long inviteId)
	{
		System.out.println("InvitaionSrviceImpl 's find(long inviteId)");
		return invitationDaoImpl.find(inviteId) ;
	}

	@Override
	public Invitation find(User invitationFromUser, User invitationToUser)
	{
		System.out.println("InvitaionSrviceImpl 's find(User invitationFromUser, User invitationToUser)");
		return invitationDaoImpl.find(invitationFromUser, invitationToUser) ;
	}

	@Override
	public List<Invitation> getAll()
	{
		System.out.println("InvitaionSrviceImpl 's getAll()");
		return invitationDaoImpl.getAll() ;
	}

	@Override
	public boolean isInvitationExist(User invitationFromUser,User invitationToUser)
	{
		System.out.println("InvitaionSrviceImpl 's isInvitationExist(invitationFromUser,invitationToUser");
		return invitationDaoImpl.isInvitationExist(invitationFromUser, invitationToUser) ;
		
	}

	@Override
	public List<Invitation> getAllPendingInvitationsOfLoggedUser(User invitationToUser)			
	{
		System.out.println("InvitaionSrviceImpl 's getAllPendingInvitationsOfLoggedUser(User invitationToUser)");
		return invitationDaoImpl.getAllPendingInvitationsOfLoggedUser(invitationToUser);
	}

	@Override
	public boolean isLoggedUserHasPendingInvitations(User invitationToUser) 
	{
		System.out.println("InvitaionSrviceImpl 's isLoggedUserHasPendingInvitations");
		return invitationDaoImpl.isLoggedUserHasPendingInvitations(invitationToUser) ;
	}

	@Override
	public boolean isLoggedUserHasSentAnyBFRequests(User invitationFromUser)
	{
		System.out.println("InvitaionSrviceImpl 's isLoggedUserHasSentAnyBFRequests(User invitationFromUser)");
		return invitationDaoImpl.isLoggedUserHasSentAnyBFRequests(invitationFromUser) ;
	}

	@Override
	public List<Invitation> getAllBFRequestsSentByLoggedUser(User invitationFromUser)
	{
		System.out.println("InvitaionSrviceImpl 's getAllBFRequestsSentByLoggedUser(User invitationFromUser)");
		return invitationDaoImpl.getAllBFRequestsSentByLoggedUser(invitationFromUser) ;
	}

}
