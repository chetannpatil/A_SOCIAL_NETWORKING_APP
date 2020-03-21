package org.chetan.dao;

import java.util.List;
import org.chetan.model.Message;
import org.chetan.model.User;


public interface MessageDao 
{

	void create(Message message);
	void update(Message message);
	Message edit(long messageId);
	void delete(long messageId);
	Message find(long messageId);
	List<Message> findAll(User messageToUser);
	List<Message> getAll();	
	List<Message> getAllMessagesOfLUSB(User loggedUserBean);
	
}
