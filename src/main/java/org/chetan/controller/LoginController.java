package org.chetan.controller;

import java.util.Set;

import javax.validation.Valid;

import org.chetan.model.User;
import org.chetan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/BestFriend/login")
public class LoginController
{

	@Autowired
	UserService userServiceImpl ;
	
	
	//@PathVariable("email") String email,@PathVariable("password") String password,
	//@ModelAttribute("userBean")@Valid User userBean,BindingResult br,
/*	@RequestMapping(value="login/signIn",method=RequestMethod.POST)
	public String login(@RequestParam("email") String email,@RequestParam("password") String password,Model m)
	{
		System.out.println("LC's login()");
		if(br.hasErrors() == true )
		{
			System.out.println("br has errors so returing to home pge ");
			m.addAttribute("userBean", userBean);
			return "Home";
		}
		
			try
			{
				System.out.println("after validation in try ");
				//after validation get dao to login
				User userBeanOriginal = userServiceImpl.find(userBean.getEmail()) ;
				//check for paswrd
				if(userBeanOriginal.getPassword().equals(userBean.getPassword()) == false )
					throw new IllegalArgumentException("Email-id OR/AND password is/are not correct");
				if(userBeanOriginal.isActiveMember() == false)
				{
					System.out.println(userBean.getEmail()+" is not active member");
					String inActiveAcStr = "Your account "+userBean.getEmail()+" is not yet activated"
							+" You click on the link we have sent to activate"
							+" then log-in";
					throw new IllegalArgumentException(inActiveAcStr);
				}			
					m.addAttribute("userBean", userBean) ;
					return "Main";	
			}
			catch(RuntimeException e)
			{
				System.out.println("LC's login's ctach-RuntimeException");
				e.printStackTrace();
				m.addAttribute("loginErrorMessage", e.getLocalizedMessage());
				return "Home" ;
			}
			catch(Exception e)
			{
				System.out.println("LC's login's ctach Exception");
				e.printStackTrace();
				m.addAttribute("errorMessage", e.getLocalizedMessage());
				return "Home" ;
			}	
		}
		
			if(email == null || email.trim().length() == 0)
				throw new IllegalArgumentException("PLEASE ENTER EMAIL ID TO LOGIN");
			if(password == null || password.trim().length() == 0)
				throw new IllegalArgumentException("PLEASE ENTER PASSWORD OF "+email+" TO LOGIN");
			
		
		
	}*/
	
	@RequestMapping("login/showMyBfs")
	public String showAllMyBestFriends()
	{
		System.out.println("LC 's showAllMyBestFriends()");
		return "";
	}
/*	@RequestMapping("login/searchBFs")
	public String searchBfs(@RequestParam("searchStr") String searchStr,Model m)
	{
		try
		{
			System.out.println("LC try of serachBfs()");
			Set<User> searchResultSet = userServiceImpl.searchAllMatchingUsers(searchStr);
			m.addAttribute("searchResultSet", searchResultSet);
			return "Main";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Error";
		}
	}*/
	
/*	@RequestMapping("login/showOneUserProfile")
	public String showOneUserProfile(@ModelAttribute("userBean") User userBean,Model m)
	{
		System.out.println("LC's showOneUserProfile");
		m.addAttribute("userBean", userBean);
		return "Main";
	}*/
}
