package org.chetan.dao.impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.chetan.dao.UserDao;
import org.chetan.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;


//SELECT * FROM "PUBLIC"."USER" where email like '%b%'  or password  like '%a%'
//@NamedQuery(name="searchAll",query="from user where firstName like '%?%' ")
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao
{

	@Autowired
	SessionFactory sessionFactory;
	
	private static EntityManager em;
	
	private Session getSession()
	{
		System.out.println("UserDAoImpl getSession()");
		return sessionFactory.getCurrentSession() ;
	}
	@Override
	public void create(User user) 
	{
		// TODO Auto-generated method stub
		System.out.println("UserDAoImpl create(User user) ");	
		getSession().save(user);
		
	}

	@Override
	public void update(User user)
	{
		// TODO Auto-generated method stub
		System.out.println("UserDAoImpl updte(User user) ");	
		getSession().update(user);
		
	}

	@Override
	public User edit(long userId) 
	{
		// TODO Auto-generated method stub
		System.out.println("UserDAoImpl edit(long userid) ");
		
		return find(userId) ;
	}

	@Override
	public void delete(long userId) {
		// TODO Auto-generated method stub
		System.out.println("UserDAoImpl delete(long userId) ");	
		//User user = find(userId) ;
		getSession().delete(userId);
		
	}

	@Override
	public User find(long userId)
	{
		// TODO Auto-generated method stub
		System.out.println("UserDAoImpl find(long userId) ");	
		return getSession().get(User.class, userId) ;
	}

	@Override
	public User find(String email)
	{
		Query q = getSession().createQuery("from User where email = :e");
		q.setParameter("e", email);
		List<User> userLs = q.list();
		if(userLs == null || userLs.size() == 0 )
		{
			String errorStr = "Emai ID "+email+" is not registered yet with us."
					          +" Please first register & then confirm by clicking on link sent by us "
					          +" & then log-In ,TQ";
			throw new IllegalArgumentException(errorStr) ;
		}
			
		return userLs.get(0) ;
	}
	@Override
	public List<User> getAll() 
	{
		System.out.println("UserDAoImpl getAll ");
		//return getSession().createCriteria(User.class).list();
		Query q = getSession().createQuery("from User");
		List<User> allUsersList = (List<User>)q.list(); 
		return allUsersList ;
	}
	
	@Override
	public Set<User> searchAllMatchingUsers(String searchStr) 
	{
		
	  System.out.println("UserDaoImpl searchAllmatchingUsers()");
	  Query q = getSession().getNamedQuery("searchAll");	 
	//	q.setString(0,"%"+str+"%");
		//q.setString(1, "%"+str+"%");
	//	q.setString(2, "%"+str+"%");
		// q.setString(0, arg1)
		 for(int i=0;i<7 ;i++)
		 {
			 ////select * from User where UPPER(state) like upper('%kar%')
			 System.out.println("UserDaoImpl searchAllmatchingUsers() forloop");
			 q.setString(i,"%"+searchStr+"%");
			 // q.setString(i,"UPPER(%"+searchStr+"%)");
		 }
		 q.setString(7, "%"+searchStr+"%");
	  List<User> searchList = (List<User>)q.list();	  
	  Set<User> resultSet = new LinkedHashSet<User>(searchList);
	  return resultSet ;	  
	}
	@Override
	public Set<User> advancedSearch(String firstName, String lastName,
			String email,String city, String state, String country) 
	{
		System.out.println("------Usrdaoimpl advancedSearch()");
		String qStr = "from User where firstName = :fn and lastName = :ln and email = :e "
				+ "and city = :c and state = :st and country = :c";
		
	
		Query q = getSession().createQuery(qStr);
		q.setParameter("fn", firstName);
		q.setParameter("ln", lastName);
		q.setParameter("e", email);
		
		q.setParameter("c", city);
		q.setParameter("st", state);
		q.setParameter("c", country);
		
		List<User> ls = (List<User>)q.list();
		Set<User> resuSet = new LinkedHashSet<User>(ls);
		return resuSet ;
	}
	
	@Override
	public User updateProfilePic(long userId, MultipartFile profilePic) throws IOException, 
	SerialException, SQLException
	{

		
		System.out.println("\n UserDaoImpl-updateProfilePic -userBean=\n  "+userId);
		
		Session session = getSession();
		
		User olduser = session.get(User.class, userId);
				 
		//olduser.setDp(null);
		olduser.setProfilePic(null);
		
		session.update(olduser);
		System.out.println("\n UserDaoImpl-updateProfilePic -olduser updated to null DP =\n  "+olduser);
		
		//LOGGER.info("\n UserDaoImpl-updateProfilePic -olduser updated to null DP =\n  "+olduser);
		
		User newuser = session.get(User.class, userId);
		
		//newuser.setProfilePic(userBean.getProfilePic());
		
		 byte[] byteArray = profilePic.getBytes();
			
		 System.out.println("\n byteArray = \n"+byteArray);
		 
		 //Blob dp = new SerialBlob(byteArray);
		 
		 //newuser.setDp(dp);
		 
		 newuser.setProfilePic(byteArray);
		 
		
		
		 System.out.println("\n UserDaoImpl-updateProfilePic -after setting base64 user  =\n  "+newuser);
			
		session.update(newuser);
		
		//LOGGER.info("\n UserDaoImpl-updateProfilePic -updated newdp   =\n  ");
		
		 User updateduser = session.get(User.class,userId);
		 
		 //LOGGER.info("\n UserDaoImpl-updateProfilePic -updateduser  =\n  "+updateduser);
		
		 System.out.println("\n UserDaoImpl-updateProfilePic -updateduser  =\n  "+updateduser);
		 
		 return updateduser ;
	}
	
	
	/*@Override
	public Blob loadDP(long userId) 
	{
		System.out.println("------Usrdaoimpl loadDP(long userId) ");
		Blob b =  em.find(User.class, userId).getDp();
		return b ;
	}*/

	@Override
	public boolean removeDP(long userId)
	{
		 System.out.println("\n UserDaoImpl-removeDP -  =\n  "+userId);
		 
		 User user = getSession().get(User.class, userId);
		 
		 user.setProfilePic(null);
		 
		 //call localapi to update
		 
		 update(user);
		 
		 //check
		 User user2 = find(userId);
		 
		 if(user2.getProfilePic() != null)
		 {
			 System.out.println("\n dp is notnull \n");
			 
			 return false ;
		 }
		 else
		 {
			 System.out.println("\n dp is null \n");
			 return true;
		 }
			
	}

	

}
