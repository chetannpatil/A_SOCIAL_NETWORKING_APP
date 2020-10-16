package org.chetan.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.chetan.util.RemoveExtraSpacesFromALine;

@Entity
//@Table(name = "MESSAGE")
public class Message 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long messageId ;
	
	@ManyToOne
	private User messageFromUser ;
	
	@ManyToOne
	private User messageToUser ;
	
	private String message;
	
	private Date dateOfMessageCreated;

	@OneToOne
	private User messageOwnerUser ;
	
	public Message() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public User getMessageOwnerUser() {
		return messageOwnerUser;
	}

	public void setMessageOwnerUser(User messageOwnerUser) {
		this.messageOwnerUser = messageOwnerUser;
	}

	public Message(long messageId, User messageFromUser, User messageToUser,
			String message, Date dateOfMessageCreated) {
		super();
		this.messageId = messageId;
		this.messageFromUser = messageFromUser;
		this.messageToUser = messageToUser;
		this.message = message;
		this.dateOfMessageCreated = dateOfMessageCreated;
	}

	public long getMessageId() 
	{
		return messageId;
	}

	public void setMessageId(long messageId) 
	{
		this.messageId = messageId;
	}

	public User getMessageFromUser() 
	{
		return messageFromUser;
	}

	public void setMessageFromUser(User messageFromUser) 
	{
		this.messageFromUser = messageFromUser;
	}

	public User getMessageToUser()
	{
		return messageToUser;
	}

	public void setMessageToUser(User messageToUser) 
	{
		this.messageToUser = messageToUser;
	}

	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message) 
	{
		message = RemoveExtraSpacesFromALine.removeExtraSpace(message) ;
		this.message = message;
	}

	public Date getDateOfMessageCreated() 
	{
		return dateOfMessageCreated;
	}

	public void setDateOfMessageCreated(Date dateOfMessageCreated) 
	{
		this.dateOfMessageCreated = dateOfMessageCreated;
	}
	

	@Override
	public String toString()
	{
		return "Message [messageId=" + messageId + ", messageFromUser="
				+ messageFromUser + ", messageToUser=" + messageToUser
				+ ", message=" + message + ", dateOfMessageCreated="
				+ dateOfMessageCreated + "]";
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((dateOfMessageCreated == null) ? 0 : dateOfMessageCreated
						.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result
				+ ((messageFromUser == null) ? 0 : messageFromUser.hashCode());
		result = prime * result + (int) (messageId ^ (messageId >>> 32));
		result = prime * result
				+ ((messageToUser == null) ? 0 : messageToUser.hashCode());
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
		Message other = (Message) obj;
		if (dateOfMessageCreated == null) {
			if (other.dateOfMessageCreated != null)
				return false;
		} else if (!dateOfMessageCreated.equals(other.dateOfMessageCreated))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageFromUser == null) {
			if (other.messageFromUser != null)
				return false;
		} else if (!messageFromUser.equals(other.messageFromUser))
			return false;
		if (messageId != other.messageId)
			return false;
		if (messageToUser == null) {
			if (other.messageToUser != null)
				return false;
		} else if (!messageToUser.equals(other.messageToUser))
			return false;
		return true;
	}
	
}
