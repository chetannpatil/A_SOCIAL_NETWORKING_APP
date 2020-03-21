package org.chetan.service;

import java.util.List;

import org.chetan.model.Message;
import org.chetan.model.User;
import org.springframework.stereotype.Service;


public interface MessageService
{

	void create(Message message);
	void update(Message message);
	Message edit(long messageId);
	void delete(long messageId);
	Message find(long messageId);
	List<Message> getAll();
	List<Message> findAll(User messageToUser);
	List<Message> getAllMessagesOfLUSB(User loggedUserBean);
}
