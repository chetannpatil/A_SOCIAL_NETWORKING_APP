package org.chetan.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
//@NamedNativeQuery(name ="loggedUsersPendingInvitationsQuery",
//query ="select * from INVITATION where invitationToUser_userid = ? and isInvitationAccepted = false",resultClass=Invitation.class)
//@NamedNativeQuery(name="delete",query="delete from INVITATION where inviteId = ?")
@Entity



@NamedNativeQueries({@NamedNativeQuery(name ="loggedUsersPendingInvitationsQuery",query ="select * from INVITATION where invitationToUser_userid = ? and isInvitationAccepted = false",resultClass=Invitation.class)
,@NamedNativeQuery(name="del",query="delete from INVITATION where inviteId = ?")})
//@Table(name = "INVITATION")
public class Invitation
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long inviteId ;
	
	@ManyToOne
	private User invitationFromUser ;
	
	@ManyToOne
	private User invitationToUser ;
	
	private boolean isInvitationAccepted ;	
	
	private Date dateOfInvitationAccepted ;

	@Override
	public String toString()
	{		
		return "Invitation [inviteId=" + inviteId + ", invitationFromUser="
				+ invitationFromUser + ", isInvitationAccepted="
				+ isInvitationAccepted + ", dateOfInvitationAccepted="
				+ dateOfInvitationAccepted + "]";
	}
	





	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((invitationFromUser == null) ? 0 : invitationFromUser
						.hashCode());
		result = prime
				* result
				+ ((invitationToUser == null) ? 0 : invitationToUser.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invitation other = (Invitation) obj;
		if (invitationFromUser == null)
		{
			if (other.invitationFromUser != null)
				return false;
		}
		else if (!invitationFromUser.equals(other.invitationFromUser))
			return false;
		if (invitationToUser == null) 
		{
			if (other.invitationToUser != null)
				return false;
		} else if (!invitationToUser.equals(other.invitationToUser))
			return false;
		return true;
	}






	public long getInviteId() 
	{
		System.out.println("Invitatin getInviteId()");
		return inviteId;
	}


	public void setInviteId(long inviteId) 
	{
		System.out.println("Invitatin setInviteId()");
		this.inviteId = inviteId;
	}


	public User getInvitationFromUser() {
		return invitationFromUser;
	}


	public void setInvitationFromUser(User invitationFromUser) {
		this.invitationFromUser = invitationFromUser;
	}


	


	public boolean isInvitationAccepted()
	{
		return isInvitationAccepted;
	}


	public void setInvitationAccepted(boolean isInvitationAccepted) {
		this.isInvitationAccepted = isInvitationAccepted;
	}


	public Date getDateOfInvitationAccepted() {
		return dateOfInvitationAccepted;
	}


	public void setDateOfInvitationAccepted(Date dateOfInvitationAccepted) {
		this.dateOfInvitationAccepted = dateOfInvitationAccepted;
	}
	public User getInvitationToUser() {
		return invitationToUser;
	}
	public void setInvitationToUser(User invitationToUser) {
		this.invitationToUser = invitationToUser;
	}


	
	
	
	
	
}
