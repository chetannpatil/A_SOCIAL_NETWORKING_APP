package org.chetan.dao.impl;

import java.util.List;

import org.chetan.dao.MessageDao;
import org.chetan.model.Message;
import org.chetan.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("messageDaoImpl")
public class MessageDaoImpl implements MessageDao 
{

	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession()
	{
		System.out.println("MessageDAoImpl getSession()");
		return sessionFactory.getCurrentSession() ;
	}
	
	@Override
	public void create(Message message)
	{
		// TODO Auto-generated method stub
		System.out.println("MessageDaoimpl create(Message message) ");
		 getSession().save(message) ;	
		
	}

	@Override
	public void update(Message message)
	{
		// TODO Auto-generated method stub
		System.out.println("MessageDaoimpl update(Message message)");
		getSession().update(message);
		
	}

	@Override
	public Message edit(long messageId) 	
	{
		// TODO Auto-generated method stub
		System.out.println("MessageDaoimpl  edit(long messageId) 	");
		return find(messageId) ;
		
	}

	@Override
	public void delete(long messageId)
	{
		System.out.println("MessageDaoimpl delete(long messageId)");
		//getSession().delete(messageId);
		Query q = getSession().createQuery("delete from Message where messageId = :mid");
		q.setParameter("mid", messageId);
		q.executeUpdate() ;
	
		
	}

	@Override
	public Message find(long messageId)
	{
		System.out.println("MessageDaoimpl find(long messageId)");
		return getSession().get(Message.class, messageId) ;
		
	}

	@Override
	public List<Message> getAll() 
	{
		System.out.println("MessageDaoimpl getAll() ");
		Query q = getSession().createQuery("from Message");
		List<Message> allMessagesList = (List<Message>)q.list();
		
		return allMessagesList ;
		
	}

	@Override
	public List<Message> findAll(User messageToUser)
	{
		System.out.println("MessageDaoimpl find(User messageToUser)");
		Query q = getSession().createQuery("From Message where messageToUser = :mtu");
		q.setParameter("mtu", messageToUser);
		List<Message> allMessagesList = (List<Message>)q.list() ;
		
		return allMessagesList ;
	}

	@Override
	public List<Message> getAllMessagesOfLUSB(User loggedUserBean)
	{
		System.out.println("MessageDaoimpl getAllMessagesOfLUSB(User loggedUserBean)");
	/*	Query q = getSession().createQuery("from Message where messageFromUser = :mfu or messageToUser = :mtu");
		q.setParameter("mfu", loggedUserBean);
		q.setParameter("mtu", loggedUserBean);*/
		
		Query  q = getSession().createQuery("from Message where messageOwnerUser = :ow");
		q.setParameter("ow", loggedUserBean);
		List<Message> allLUSBMessagesList = (List<Message>)q.list();
		
		return allLUSBMessagesList ;
	}

	

}
