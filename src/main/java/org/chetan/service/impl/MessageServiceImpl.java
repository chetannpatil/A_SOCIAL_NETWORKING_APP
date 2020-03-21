package org.chetan.service.impl;

import java.util.List;

import org.chetan.dao.MessageDao;
import org.chetan.model.Message;
import org.chetan.model.User;
import org.chetan.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("messageServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class MessageServiceImpl implements MessageService
{
	@Autowired
	MessageDao messageDaoImpl ;

	@Override
	public void create(Message message)
	{
		// TODO Auto-generated method stub
		System.out.println("MessageServiceImpl  create(Message message)");
		messageDaoImpl.create(message);
		
	}

	@Override
	public void update(Message message)
	{
		System.out.println("MessageServiceImpl  update(Message message)");
		messageDaoImpl.update(message);
		
	}

	@Override
	public Message edit(long messageId)
	{
		System.out.println("MessageServiceImpl  edit(long messageId)");
		return messageDaoImpl.edit(messageId);
	}

	@Override
	public void delete(long messageId)
	{
		System.out.println("MessageServiceImpl  delete(long messageId)");
		messageDaoImpl.delete(messageId);
		
	}

	@Override
	public Message find(long messageId) 
	{
		System.out.println("MessageServiceImpl find(long messageId) ");
		return messageDaoImpl.find(messageId) ;
	}

	@Override
	public List<Message> getAll()
	{
		System.out.println("MessageServiceImpl getAll()");
		return messageDaoImpl.getAll() ;
	}

	@Override
	public List<Message> findAll(User messageToUser) 
	{
		System.out.println("MessageServiceImpl findAll(User messageToUser) ");
		return messageDaoImpl.findAll(messageToUser) ;
	}

	@Override
	public List<Message> getAllMessagesOfLUSB(User loggedUserBean) 
	{
		System.out.println("MessageServiceImpl getAllMessagesOfLUSB(User loggedUserBean)  ");
		
		return messageDaoImpl.getAllMessagesOfLUSB(loggedUserBean);
	}

}
