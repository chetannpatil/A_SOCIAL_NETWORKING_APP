package org.chetan.dao;

import java.util.List;
import org.chetan.model.Invitation;
import org.chetan.model.User;

public interface InvitationDao 
{

	void create(Invitation invitation);
	void update(Invitation invitation);
	Invitation edit(long inviteId);
	void delete(long inviteId);
	Invitation find(long inviteId);
	//User find(String email);
	 Invitation find(User invitationFromUser,User invitationToUser);
	
	boolean isInvitationExist(User invitationFromUser,User invitationToUser);
	List<Invitation> getAll();
	List<Invitation> getAllPendingInvitationsOfLoggedUser(User invitationToUser);
	
		
	boolean isLoggedUserHasPendingInvitations(User invitationToUser) ;
	
	boolean isLoggedUserHasSentAnyBFRequests(User invitationFromUser);
	
	List<Invitation> getAllBFRequestsSentByLoggedUser(User invitationFromUser);	
	
	
}
