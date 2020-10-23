package org.chetan.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chetan.model.Invitation;
import org.chetan.model.Message;
import org.chetan.model.User;
import org.chetan.model.Wall;
import org.chetan.service.InvitationService;
import org.chetan.service.MessageService;
import org.chetan.service.UserService;
import org.chetan.service.WallService;
import org.chetan.util.DirtyCheck;
import org.chetan.util.DuplicateUserException;
import org.chetan.util.RemoveExtraSpacesFromALine;
import org.chetan.util.SortByDateOfMessageSent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller("/BestFriend")
public class BestFriendController
{	
	

	@Autowired
	UserService userServiceImpl ;
	
	@Autowired
	InvitationService invitationServiceImpl ;
	
	@Autowired
	MessageService messageServiceImpl ;
	
	@Autowired
	WallService wallServiceImpl ;
	
	private static final Logger LOGGER = LogManager.getLogger(BestFriendController.class);
	
	//local
	private User updateProfilePic(long userId,MultipartFile profilePic) throws IOException,
	SerialException, SQLException
	{
		LOGGER.info("\n UserProfileController -updateProfilePic -  \n");
		
		System.out.println("\n UserProfileController -updateProfilePic -  \n");
		
		//User updatedProfile = userProfileService.updateProfilePic(userBean);
		
		User updatedProfile = userServiceImpl.updateProfilePic(userId,profilePic);
		
		return updatedProfile;
	}
	
	private User getBase64ImageSetUser(User user)
	{
		System.out.println("\n UserProfileController -getBase64ImageSetUser- - User =  \n"+user);
		
		LOGGER.info("\n UserProfileController -getBase64ImageSetUser- - User =  \n"+user);
		
		if(user.getProfilePic() == null)
		{
			System.out.println("\n UserProfileController -getBase64ImageSetUser requested user donesnt have dp ...sorry..\n");
			
			LOGGER.info("\n requested user donesnt have dp ...sorry..\n");
			
			return user;
		}
		System.out.println("\n user- has dp already "
				+ "so setting baseimage , byetarray = \n"+user.getProfilePic());
		
		LOGGER.info("\n user- has dp already "
				+ "so setting baseimage , byetarray = \n"+user.getProfilePic());
		
		
		//set base64image also
				 String base64Image = Base64.getEncoder().encodeToString(user.getProfilePic());
		
				 user.setBase64Image(base64Image);
		System.out.println("\n UserProfileController-getBase64ImageSetUser -afterseeting base64image "
				+ "= \n"+user);		 
		LOGGER.info("\n UserProfileController-getBase64ImageSetUser -afterseeting base64image "
				+ "= \n"+user);
				 return user;
	}
	
	private Collection<User> getBase64ImageSetUsers(Collection<User> usersCollection) 
	{
			System.out.println("\n HC-getBase64ImageSetUsers -usersCollection =  \n"+usersCollection);
			
			LOGGER.info("HC-searchBfs - getBase64ImageSetUsers  - usersCollection = \n"+usersCollection);
			
			
			Collection<User> base64ImageSetUsers = new ArrayList<User>();
			for(User user:usersCollection)
			{
				System.out.println("\n HC-getBase64ImageSetUsers for user = \n"+user);
				
				LOGGER.info("\n  HC-getBase64ImageSetUsers- for user = \n"+user);
				
				user = getBase64ImageSetUser(user);
				
				base64ImageSetUsers.add(user);
			}
			
			System.out.println("\n returning base64ImageSetUsers = \n"+base64ImageSetUsers);
			
			LOGGER.info("\n  HC-getBase64ImageSetUsers- returning base64ImageSetUsers = \n"+base64ImageSetUsers);
			
			return base64ImageSetUsers;
	}
	
	private Collection<Invitation> getBase64ImageSetUsersInvitations(Collection<Invitation> invitations)
	{
		LOGGER.info("HC-getBase64ImageSetUsersInvitations - invitationsSet = \n "+invitations);
		
		System.out.println("HC-getBase64ImageSetUsersInvitations - invitationsSet = \n "+invitations);
		
		Collection<Invitation> invitationsColl = new ArrayList<Invitation>();
		
		for(Invitation invitation :invitations)
		{
			System.out.println("for invitation = \n"+invitation);
			
			User invitationFromUser = invitation.getInvitationFromUser();
			
			User invitationToUser = invitation.getInvitationToUser();
			
			//setbase64image
			
			invitationFromUser = getBase64ImageSetUser(invitationFromUser);
			
			invitationToUser = getBase64ImageSetUser(invitationToUser);
			
			//add back from and to user to invitation
			invitation.setInvitationFromUser(invitationFromUser);
			
			invitation.setInvitationToUser(invitationToUser);
			
			System.out.println("\n after setting base64image to from and TO users , invation = \n"+invitation);
			//add resultant invitation to new coll
			
			invitationsColl.add(invitation);
			
		}
		
		return invitationsColl;
	}
	
	private Collection<Wall> getBase64ImageSetUsersWalls(List<Wall> walls)
	{
		System.out.println("\n HC- getBase64ImageSetUsersWalls - walls = \n"+walls);
		
		Collection<Wall> wallsColl = new ArrayList<Wall>();
		
		for(Wall wall :walls)
		{
			System.out.println("\n for wall = \n"+wall);
			
			User wallFromUser = wall.getWallFromUser();
			
			User wallToUser = wall.getWallToUser();
			
			//wall.get
			wallFromUser = getBase64ImageSetUser(wallFromUser);
			
			wallToUser = getBase64ImageSetUser(wallToUser);
			
			//setting back
			wall.setWallFromUser(wallFromUser);
			
			wall.setWallToUser(wallToUser);
			
			System.out.println("after setting wall = \n "+wall);
			wallsColl.add(wall);
			
		}
		return wallsColl;
	}
	private Wall getBase64ImageSetUsersWall(Wall wall)
	{
		System.out.println("\n HC- getBase64ImageSetUsersWall -wall = \n"+wall);
		Set<User> likedUsersSet = wall.getLikedUsersSet();
		
		Set<User> hatedUsersSet = wall.getHatedUsersSet();
		
		Collection<User> base64ImageSetLikedUsers = getBase64ImageSetUsers(likedUsersSet);
		
		Collection<User> base64ImageSetHatedUsers = getBase64ImageSetUsers(hatedUsersSet);
		
		
		//set back to wall
		
		
//		wall.setLikedUsersSet(new TreeSet<User>(base64ImageSetLikedUsers));
//		
//		wall.setHatedUsersSet(new TreeSet<User>(base64ImageSetHatedUsers));
		
        wall.setLikedUsersSet(new LinkedHashSet<User>(base64ImageSetLikedUsers));
		
		wall.setHatedUsersSet(new LinkedHashSet<User>(base64ImageSetHatedUsers));
		System.out.println("\n returning wall = \n"+wall);
		
		return wall;
		
	}
	
	private Collection<Message> getBase64ImageSetUsersMessages(List<Message> messages)
	{
		System.out.println("\n HC-getBase64ImageSetUsersMessages = messages =  \n"+messages);
		
		Collection<Message> messagesColl = new ArrayList<Message>();
		for(Message message :messages)
		{
			System.out.println("\n for msg = \n"+message);
			
			User messageFromUser = message.getMessageFromUser();
			
			User messageToUser = message.getMessageToUser();
			
			//setbase64image
			messageFromUser = getBase64ImageSetUser(messageFromUser);
			
			messageToUser = getBase64ImageSetUser(messageToUser);
			
			//addback
			
			message.setMessageFromUser(messageFromUser);
			
			message.setMessageToUser(messageToUser);
			
			messagesColl.add(message);
			
		}
		
		System.out.println("\n HC-getBase64ImageSetUsersMessages - returing messagesColl= \n"+messagesColl);
		
		return messagesColl ;
	}
	
		
	
	private  Set<Invitation> loadMyBFRequests(HttpSession hs)
	{
		//System.out.println(".");		
		System.out.println("--HC's staic loadMyBFRequests(HttpSession hs) ");
		
		User loggedUserBean = (User) hs.getAttribute("loggedUserBean");
		//System.out.println("LUSB invtna collc size  = "+loggedUserBean.getInvitationsSet().size());
	
		Set<Invitation> allInvtSet = new LinkedHashSet<Invitation>();
		boolean isLUHasPendingInvitations = invitationServiceImpl.isLoggedUserHasPendingInvitations(loggedUserBean);
		if(isLUHasPendingInvitations == true )
		{
			
		List<Invitation> pendingInvOfLoggUBList = invitationServiceImpl.getAllPendingInvitationsOfLoggedUser(loggedUserBean);
		System.out.println("----------LUS has pinvts total = "+pendingInvOfLoggUBList.size());
		
		 allInvtSet = new LinkedHashSet<Invitation>(pendingInvOfLoggUBList);		
		}
		else
		{
			System.out.println("LUSb have no Pend invtns");			
			//String noPeningInvitationsStr = "You do not have any pending invitations now.";		
		}
		return allInvtSet ;		
	}
	
	//load all bf reqs sent by LUSB
	private Set<Invitation> loadBFRequestsSentByMe(HttpSession hs)
	{
		System.out.println("in hc loadBFRequestsSentByMe(HttpSession hs)");
		Set<Invitation> allBFReqSentByLUSBSet = new LinkedHashSet<Invitation>() ;
		User loggedUserBean = (User)hs.getAttribute("loggedUserBean");
		if(invitationServiceImpl.isLoggedUserHasSentAnyBFRequests(loggedUserBean) == true)
		{
		List<Invitation> allBFReqSentByLUSBList = invitationServiceImpl.getAllBFRequestsSentByLoggedUser(loggedUserBean);
		allBFReqSentByLUSBSet.addAll(allBFReqSentByLUSBList);	
		}		
		return allBFReqSentByLUSBSet ;		
	}
	
	@RequestMapping("/")
	public String showHome(Model m)
	{
		System.out.println("in HC showHome()");
	
		return "Home";
		
	}
	
	//unUsed
	private Model loadWallsToModel2(User user, Model model)
	{
		System.out.println("\n HC- getWallsLoadedModel\n");
		
		List<Wall> allWallsList = loadAllWalls(user);
		
		Wall wallBean = new Wall() ;
		
		model.addAttribute("wallBean", wallBean);
		
		model.addAttribute("allWallsList", allWallsList);
		
		System.out.println("\n returnig wallsLoaded model = \n"+model);
		
		return model;
	}
	
	private void loadWallsToModel(User user, Model model)
	{
		System.out.println("\n HC- getWallsLoadedModel\n");
		
		List<Wall> allWallsList = loadAllWalls(user);
		
		Wall wallBean = new Wall() ;
		
		model.addAttribute("wallBean", wallBean);
		
		model.addAttribute("allWallsList", allWallsList);
		
		System.out.println("\n returnig wallsLoaded model = \n"+model);
		
		//return model;
	}
	
	
	//tomain
	@RequestMapping("/toMain")
	public String toMain(Model m,HttpSession hs)
	{
		System.out.println("HC's toMain()");
		try
		{
			User loggedUserBean = (User)hs.getAttribute("loggedUserBean");
			
//			List<Wall> allWallsList = loadAllWalls(loggedUserBean);
//			
//			Wall wallBean = new Wall() ;
//			
//			m.addAttribute("wallBean", wallBean);
//			
//			m.addAttribute("allWallsList", allWallsList);
			
			loadWallsToModel(loggedUserBean, m);
			
			
			return "Main";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
		
	}
		
	@RequestMapping("/signOut")
	public String toHome(HttpSession hs,Model m)
	{
		try
		{
			User loggedUserBean = (User) hs.getAttribute("loggedUserBean");
			String nameStr = loggedUserBean.getFirstName()+"  "+loggedUserBean.getLastName();
			hs.invalidate();
			String signOutStr = "Thank you "+nameStr.toUpperCase()
					+ ", you have been signed out successfully. Have a nice time :)";
			m.addAttribute("signOutMessage", signOutStr);
			return "Home";
		}
		catch(Exception e)
		{
			m.addAttribute("ErrorMessage", "Your session has been timed-out,Please log-in again");
			return "Error";
		}
		
		
	}
	//regView        /openRegisterView
	@RequestMapping("/openRegisterView")
	public String showRegisterView(Model m)
	{
		System.out.println("HC.s showRegisterView()");
		
		User userBean = new User();
		m.addAttribute("userBean", userBean);
		return "Register";
	}
	
	//submiting regstrton form
	@RequestMapping("/register")
	public String register(@ModelAttribute("userBean")@Valid User userBean,BindingResult br,Model m)
	{
		System.out.println("HC's register()");
		if(br.hasErrors() == true )
		{
			System.out.println("there are error in Br so returning to restrion page br = ");
			System.out.println("br = "+br.toString());
			m.addAttribute("userBean", userBean);
		
			 return "Register";	 			
		}
		else
		{
			try
			{			
				if(userBean != null)
				 {
					//buziness case validation dupliate user 
					try
					{
						List<User> usersList =  userServiceImpl.getAll();
						for(User userObj : usersList)
						{
							if(userObj.getEmail().trim().equalsIgnoreCase(userBean.getEmail().trim()) == true)
							{
								String exceptionMessageStr = "User name (email id) not available";
										//+" You please enter any other email id TQ";
								throw new DuplicateUserException(exceptionMessageStr);
							}
								
						}
					//password validations
						boolean matchingBool = User.isPasswordMatchingRepeatPassword(userBean.getPassword(), userBean.getRepeatPassword());
						if(matchingBool == false )
						{
							System.out.println("paswd didnt match so returning to regstrn page");
							m.addAttribute("userBean", userBean);						
						
							throw new IllegalArgumentException("Password does not match");
						}			
					
					}
					catch(DuplicateUserException e)
					{
						e.printStackTrace();
						m.addAttribute("dupUserErrorMessage", e.getLocalizedMessage());
						return "Register";	
					}
					catch (RuntimeException e) 
					{
						// TODO: handle exception
						m.addAttribute("pswErrorMessage",e.getLocalizedMessage());
						return "Register";
					}
					
				
				 }	
					System.out.println("No errors in Br so moving to sccss page & ub = ");
					//System.out.println(userBean);
					System.out.println("fname= "+userBean.getFirstName());
					System.out.println("fname length = "+userBean.getFirstName().length());
					//userBean.setDp(dp);
					userServiceImpl.create(userBean);
					String successMessageStr ="Registration successfull,thank u so much"
							+" for joining us.";
							
					m.addAttribute("successMessage",successMessageStr);
					StringBuilder userNameSb = new StringBuilder(userBean.getFirstName().trim().toUpperCase());						
					if(userBean.getLastName() != null && userBean.getLastName().trim().length() > 0)
					{
						userNameSb.append(" ");
						userNameSb.append(userBean.getLastName().trim().toUpperCase());
					}		
				 	
					m.addAttribute("newUserName",userNameSb.toString());
					String noteStr = "We have mailed you 1 link, Please click that to complete registration";
					 noteStr = noteStr +" & Also note that Your email id "+userBean.getEmail()+" will be user name to log in"
							 +" to your account ,All the best ";
					m.addAttribute("noteMessage", noteStr);
					///email to user & send activation link with useriD attached to linkat the end
					m.addAttribute("confirmRegLink", "move to confirm ur registrion");
					m.addAttribute("userId", userBean.getUserId());
					return "Success";			
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("catching in HC's registr()");
				m.addAttribute("errorMessage", e.getLocalizedMessage());
				return "Error";
			}
		}
	}
	

	
	
	//to confirm registrtion {userId} 
	//@PathVariable("userId") 
	@RequestMapping(value = "/activateUser",method =RequestMethod.POST)
	public String confirmRegistration(@RequestParam("userId") long userId,Model m)
	{
		System.out.println("HC's confirmregistr() with userId = "+userId);
		//long userId =19 ;
		//update USER set isactivemember = true where userid = 18
		//http://localho@PathVariable("userId") st:8080/BestFriend/activateUser
		try
		{
			System.out.println("finding authrised suer ");
			User userBean = userServiceImpl.find(userId);
			System.out.println("setting users activation oto true");
			if(userBean == null)
				throw new IllegalArgumentException("You are not registered user,Please register soon");
			if(userBean.isActiveMember() == true)
			{
				String redundantClickStr = "Your have already successfully activated your account."
						+" Do not waste your time in clicking same link again & again."
						+" You just log-in  & enjoy.";
				throw new IllegalArgumentException(redundantClickStr);
			}
			
		//if evrything okay take user to confirm rgesitrio window	
				userBean.setActiveMember(true);
				userServiceImpl.update(userBean);
				String successStr = "Congo "+userBean.getFirstName()+" "+userBean.getLastName()
						+" Your account is activated now,"+
						" You can login & find out your best friends & Enjoy TQ."
						+" For suggestions & complaints mail us at chetu.is@gmail.com";
				m.addAttribute("SuccessMessage", successStr);
			//	return "Success";
				return "Home";
			
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			System.out.println("catch of HC's confrmregistion()");
			return "Error";
		}		
	}
	
	//load all walls
	private List<Wall> loadAllWalls(User user)
	{
		System.out.println("-------HC' s  loadAllWalls(User user)");
		List<Wall> allWallsList = new ArrayList<Wall>();
		allWallsList = wallServiceImpl.getAllWalls(user);	
		
		Collection<Wall> base64ImageSetUsersWalls =  getBase64ImageSetUsersWalls(allWallsList);
		
		
		List<Wall> walls = new ArrayList<Wall>(base64ImageSetUsersWalls);
		
	/*	Wall [] wa = new Wall [tempWallsList1.size()];
		int k= 0;
		for(Wall w :tempWallsList1)
		{
			wa[k++] = w;
		}*/
		//
	//	List<Wall> allWallsList = new ArrayList<Wall>();
		
		/*for(int i = (wa.length-1);i >= 0;i--)
		{
			System.out.print("i = "+i+" elent = "+wa[i]);
			allWallsList.add(wa[i]);
			
		}*/
//		Collections.reverse(allWallsList);
		
		//Collections.reverse(Collections.list(base64ImageSetUsersWalls));
//		return allWallsList ;
		
		Collections.reverse(walls);
		
		return walls ;
		
	}
	

	@RequestMapping(value="signIn",method=RequestMethod.POST)
	public String login(@RequestParam("email") String email,@RequestParam("password") String password,Model m,HttpSession hs)
	{		
		System.out.println("HC's login()");
			try
			{
				if(email == null || email.trim().length() == 0)
					throw new IllegalArgumentException("Please enter email-id to login.");
				m.addAttribute("email", email);
				if(password == null || password.trim().length() == 0)
					throw new IllegalArgumentException("Please enter password for "+email+" to login.");
				m.addAttribute("password", password);
				
				//after validation get dao to login
				User userBeanOriginal = userServiceImpl.find(email) ;
				//check for paswrd
				if(userBeanOriginal.getEmail().equals(email) == false )
					throw new IllegalArgumentException("Incorrect email-id");
				if(userBeanOriginal.getPassword().equals(password) == false )
					throw new IllegalArgumentException("Incorrect password");
				if(userBeanOriginal.isActiveMember() == false)
				{
					System.out.println(email+" is not active member");
					String inActiveAcStr = "Your account "+email+" is not yet activated."
							+" Please click on the link we have sent to activate,"
							+" then log-in";
					throw new IllegalArgumentException(inActiveAcStr);
				}	
		
				//call local api to set base64image 
				userBeanOriginal = getBase64ImageSetUser(userBeanOriginal);
				
								
				hs.setAttribute("loggedUserBean", userBeanOriginal);
				
					m.addAttribute("userBean", userBeanOriginal) ;
					
//					List<Wall> allWallsList = loadAllWalls(userBeanOriginal);
//					Wall wallBean = new Wall() ;
//					m.addAttribute("wallBean", wallBean);
//					m.addAttribute("allWallsList", allWallsList);
					
					//return "Main"	
					
					//return toMain(m, userBeanOriginal);
					
					return toMain(m, hs);
			}
			catch(RuntimeException e)
			{
				System.out.println("LC's login's ctach-RuntimeException "+e.getLocalizedMessage());
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
		
	@RequestMapping("/TestRegConfirmation")
	public String testReg(@RequestParam("userId") long userId,Model m)
	{
		System.out.println("tetsig cnfrim Hc testRg() userid = "+userId);
		try
		{
			m.addAttribute("userId", userId);
			return "TestRegConfirmation";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
		
	}
	
	//wallMessage selfWallIt @ModelAttribute("wallBean") Wall wallBean
	@RequestMapping(value = "/selfWallIt",method = RequestMethod.POST)
	public String postWall(@RequestParam("wallMessageStr") String wallMessageStr,Model m,HttpSession hs)
			
	{
		System.out.println("HC post selfwall()");
		//lUSBStr
		String wallStatusStr ="";
		
		try
		{			
			User loggedUserBean = (User)hs.getAttribute("loggedUserBean");					
				if(wallMessageStr == null || wallMessageStr.trim().length() == 0)
				{
					wallStatusStr = "No worth of walling a blank,we do not support you,Sorry";
				}
				else if(DirtyCheck.isMessageDirty(wallMessageStr) == true )
				{
					wallStatusStr = "We do not support dirty walls.";
				}
				else
				{
					Wall wallBean = new Wall();
					wallBean.setWallMessage(wallMessageStr);
					wallBean.setDateOfWallCreated(new Date());
					wallBean.setWallFromUser(loggedUserBean);
					wallBean.setWallToUser(loggedUserBean);
					if(loggedUserBean.getWallsList().add(wallBean) == true)
					{
						wallServiceImpl.create(wallBean);
						userServiceImpl.update(loggedUserBean);
					}
					else
					{
					wallStatusStr = "Unfortunatelly we could not wall your message to your own wall,Sorry";
					}
				}	
				//wallStatusStr = "Unfortunatelly we could not wall your message,Sorry";					
						
			m.addAttribute("wallStatusMessage", wallStatusStr);
			
//			List<Wall> allWallsList = loadAllWalls(loggedUserBean);
//			
//			m.addAttribute("allWallsList", allWallsList);
//			return "Main"
			
			return toMain(m, hs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}		
	}
	
    //Otherswall it
	@RequestMapping(value = "/othersWallIt",method = RequestMethod.POST)
    public String postOnOthersWall(@RequestParam("wallMessageStr") String wallMessageStr,Model m,HttpSession hs,
    		@RequestParam("makeBFMessage") String makeBFMessage,
    		@RequestParam("bfReqSentStatusMessage") String bfReqSentStatusMessage,
    		@RequestParam("oneUsersEmail") String oneUsersEmail)
    {
    	System.out.println("HC post otherswall()");
		//lUSBStr
		String wallStatusStr ="";
		User oneUserBean = userServiceImpl.find(oneUsersEmail) ;
		User loggedUserBean = (User)hs.getAttribute("loggedUserBean");
		try
		{
			if(wallMessageStr == null || wallMessageStr.trim().length() == 0)
			{
				wallStatusStr = "No worth of walling a blank,Sorry";
			}
			else if(DirtyCheck.isMessageDirty(wallMessageStr) == true )
			{
				wallStatusStr = "We do not support dirty walls.";
			}
	    	else
			{	 
	    		Wall wallBean = new Wall();
			    wallBean.setWallMessage(wallMessageStr);
				wallBean.setWallToUser(oneUserBean);
				wallBean.setWallFromUser(loggedUserBean);
				wallBean.setDateOfWallCreated(new Date());
				if(oneUserBean.getWallsList().add(wallBean) == true)
				{
					wallServiceImpl.create(wallBean);
					userServiceImpl.update(oneUserBean);
				}
				else
				{
				wallStatusStr = "Unfortunatelly we could not wall your message to"
						+oneUserBean.getFirstName()+" "+oneUserBean.getLastName()+
						" 's wall,Sorry,Please re-try after sometime after checking your internet connection";
				}			
				
			}
			List<Wall> allWallsList = loadAllWalls(oneUserBean);
			
			//need to loop ober waalsiist and upadet base64image for each user?
			    for(Wall wall :allWallsList)
				{
				System.out.println("\n HC- postOnOthersWall - for wall = \n"+wall);
				
				System.out.println("\n walll from ueser = \n "+wall.getWallFromUser());
				
				System.out.println("\n walll to ueser = \n "+wall.getWallToUser());
				}
						
		
			
			
			
			//call local api to set base64image to oneUserBean
			oneUserBean = getBase64ImageSetUser(oneUserBean);
			
			
			 
			
			m.addAttribute("allWallsList", allWallsList);
			m.addAttribute("wallStatusMessage", wallStatusStr);			
			m.addAttribute("oneUserBean", oneUserBean);			
			m.addAttribute("bfReqSentStatusMessage", bfReqSentStatusMessage);
			m.addAttribute("makeBFMessage", makeBFMessage);
			
			return "OneUsersMain";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}    
    }	
    
    
	@RequestMapping("/searchBFs")
	public String searchBfs(@RequestParam("searchStr") String searchStr,Model m,HttpSession hs)
	{
		try
		{
			String noMatchingRecordsStr = "";
			User loggedUserBean =(User) hs.getAttribute("loggedUserBean");
			if(searchStr != null && searchStr.trim().length() > 0)
			{
				searchStr=searchStr.trim().toUpperCase();
				searchStr = RemoveExtraSpacesFromALine.removeExtraSpace(searchStr) ;
				System.out.println("HC try of serachBfs() with serachStr = "+searchStr);	
			
				Set<User> searchResultSet = userServiceImpl.searchAllMatchingUsers(searchStr);
			
			searchResultSet.remove(loggedUserBean);
			
			LOGGER.info("HC-searchBfs - b4 setting base64image  - searchResultSet = \n"+searchResultSet);
			
			Collection<User> base64ImageSetUsers = getBase64ImageSetUsers(searchResultSet);
			
			//Set<User> base64ImageSetUsersSet = new TreeSet<User>();
			//for each user set base64image
				/*
				 * for(User user:searchResultSet) {
				 * System.out.println("\n calling local api to set base64image to user =  \n"
				 * +user);
				 * 
				 * 
				 * user = getBase64ImageSetUser(user);
				 * 
				 * System.out.println("after adding base64image ,user =\n"+user);
				 * base64ImageSetUsersSet.add(user);
				 * 
				 * }
				 */
			LOGGER.info("HC-searchBfs - after  setting base64image  - searchResultSet = \n"+searchResultSet);
			System.out.println("\nbase64ImageSetUsersSet  = \n"+base64ImageSetUsers);
			
			//pass base64ImageSetUsersSet to view
			m.addAttribute("searchResultSet", base64ImageSetUsers);
			
			//String zeroResultsStr = "";
			if(searchResultSet.size() == 0)
			{
				System.out.println("Zero matching records found for   "+searchStr);
				noMatchingRecordsStr="Zero matching records found for "+searchStr+".Please try something else, good luck";
				m.addAttribute("noMatchingRecordsMessage", noMatchingRecordsStr);
			}
			}
			if(searchStr != null && searchStr.trim().length() == 0)
			{
				noMatchingRecordsStr = "You will get best friends only if you enter something & then press search,T.Q";
				m.addAttribute("noMatchingRecordsMessage",noMatchingRecordsStr );	
			}
		
			//List<Wall> allWallsList = loadAllWalls(loggedUserBean)
			
			//needs to create local api to add users of base64image ?
			
			
			
			
			
//			Wall wallBean = new Wall() ;
//			m.addAttribute("wallBean", wallBean);
//			m.addAttribute("allWallsList", allWallsList);
//			return "Main"
			
			return toMain(m, hs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
	}
	
	//@ModelAttribute
	@RequestMapping("/showOneUserProfile")
	public String showOneUserProfile(@RequestParam("oneUserEmail") String oneUserEmail,Model m,HttpSession hs)
	{
		System.out.println("HC's showOneUserProfile");
		User loggedUserBean = (User)hs.getAttribute("loggedUserBean");
		
		//if(oneUserEmail != null && oneUserEmail.trim().length() > 0)
		//{
			try
			{
				
				System.out.println("oneUserEmail = "+oneUserEmail);
				User oneUserBean = userServiceImpl.find(oneUserEmail);
				
				String unFriendStr ="";
				//unFriendStr = "UnFriend";
				//m.addAttribute("unFriendMessage", unFriendStr);
				if(oneUserBean != null)
				{
					System.out.println("aftr finding oneuserbean = "+oneUserBean);
					
					
					
					String bfReqSentStatusStr = "";
					
					//check whetehr already 1usb is frn of logged user
					Set<User> loggedUsersFriendsSet = loggedUserBean.getFriendsSet() ;
					boolean isOneUBIsALoggedUsersBF = false ;
					for(User u : loggedUsersFriendsSet)
					{
						System.out.println("for eahc  LUB s frnd =  "+u);
						if(u.equals(oneUserBean) == true )
						{
							System.out.println("yes if yield turue for usr = "+u);
							isOneUBIsALoggedUsersBF = true ;
						}
					}
					
					//refactor as loggedUsersFriendsSet.conatins(oneUserBean) ?
					
					
					
					
					
					//if 1usb is alrdy a bf of LUb 
					if(isOneUBIsALoggedUsersBF == true )
					{
						System.out.println("yes iusb = "+oneUserBean.getFirstName()+" is alrdy bf of LogUSB");
						bfReqSentStatusStr = "My BF";
						m.addAttribute("bfReqSentStatusMessage",bfReqSentStatusStr);
						unFriendStr = "UnFriend";
						m.addAttribute("unFriendMessage", unFriendStr);
					}
					else if(isOneUBIsALoggedUsersBF == false )
					{
						System.out.println("no user is not bf of LUSB ");
						boolean loggedUserHasPendingRequestFromUser = false ;
						loggedUserHasPendingRequestFromUser = invitationServiceImpl.isInvitationExist(oneUserBean, loggedUserBean);
						
						if(loggedUserHasPendingRequestFromUser == true)
						{
							System.out.println("yes LUSB has pending requst frm user ");
							bfReqSentStatusStr = "You have BF request from "+oneUserBean.getFirstName()+" "
									+oneUserBean.getLastName()+".Please check your MyBFRequests section & accept/reject.";
							m.addAttribute("bfReqSentStatusMessage",bfReqSentStatusStr);
							
						}
						else if(loggedUserHasPendingRequestFromUser == false )
						{
							System.out.println("\n no ,LUSB not have pending requst frm user \n");
							boolean alreadyBFRequestSent = false ;
							alreadyBFRequestSent = invitationServiceImpl.isInvitationExist(loggedUserBean,oneUserBean);
							if(alreadyBFRequestSent == true )
							{
							System.out.println("yes loggedsuer "+loggedUserBean.getFirstName()+" has sent req to "+oneUserBean.getFirstName());
							bfReqSentStatusStr = "B.F request already sent";				
							m.addAttribute("bfReqSentStatusMessage",bfReqSentStatusStr);
							}
							else if(alreadyBFRequestSent == false ) 
							{
							//Invitation existingInvitation = invitationServiceImpl.find(loggedUserBean, oneUserBean);
							System.out.println("NO loggedsuer "+loggedUserBean.getFirstName()+" not yet sent req to "+oneUserBean.getFirstName());
							String makeBFStr = "Make B.F";	
							m.addAttribute("makeBFMessage", makeBFStr);
							}
						}							
					}
					
					oneUserBean = getBase64ImageSetUser(oneUserBean);
					
					m.addAttribute("oneUserBean", oneUserBean);
					
					
					List<Wall> allWallsList = loadAllWalls(oneUserBean);
					Wall wallBean = new Wall() ;
					m.addAttribute("wallBean", wallBean);
					m.addAttribute("allWallsList", allWallsList);
					
					return "OneUsersMain";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println(e.getLocalizedMessage());
				m.addAttribute("errorMessage", e.getLocalizedMessage());
				return "Error";
			}
			
		//return "Main";
		
		return toMain(m, hs);
	}
	
	

	//send Bf request
	@RequestMapping("/makeBF")
	public String sendBFRequest(@RequestParam("oneUserEmail") String oneUserEmail,Model m,HttpSession hs)
	{
		System.out.println("HCs sendBFRequest()");
		try
		{
			//get Loggeduserbean 
			User loggedUserBean = (User) hs.getAttribute("loggedUserBean");
			
			System.out.println("oneUserEmail = "+oneUserEmail);
			
			User oneUserBean = userServiceImpl.find(oneUserEmail);
			
			//check wether loggeduser has already sent bf request
			//Set<Invitation> invitationSet = oneUserBean.getInvitationsSet();
			
			boolean alreadyBFRequestSent = false ;
			alreadyBFRequestSent = invitationServiceImpl.isInvitationExist(loggedUserBean,oneUserBean);
			
			String bfReqSentStatusStr = "";
			if(alreadyBFRequestSent == false )
			{
			//Invitation existingInvitation = invitationServiceImpl.find(loggedUserBean, oneUserBean);
			System.out.println("NO loggedsuer "+loggedUserBean.getFirstName()+" not yet sent req to "+oneUserBean.getFirstName());
			//String makeBFStr = "Make B.F";	
			//m.addAttribute("makeBFMessage", makeBFStr);
			
			Invitation addInvitation = new Invitation();
			
			addInvitation.setInvitationFromUser(loggedUserBean);
			///add to user
			addInvitation.setInvitationToUser(oneUserBean);			
			
			if(oneUserBean.getInvitationsSet().add(addInvitation) == true)
			{
				System.out.println("yes invtion added to onusrban = "+oneUserBean);
			    bfReqSentStatusStr = "BF request sent successfully";
				m.addAttribute("bfReqSentStatusMessage", bfReqSentStatusStr);
				System.out.println("updating oneuserbean to = "+oneUserBean);
				userServiceImpl.update(oneUserBean);
				invitationServiceImpl.create(addInvitation);				
			}
			else
			{
              bfReqSentStatusStr = "Sorry ,unfortunatelly could not send BF request."
              		+ "Please try again after sometime";
			  m.addAttribute("bfReqSentStatusMessage",bfReqSentStatusStr);
			}			
			}
			else if(alreadyBFRequestSent == true )
			{
		        bfReqSentStatusStr = "BF request already sent."
	              		+ "Please kindly wait for "+oneUserBean.getFirstName()
	              		+" "+oneUserBean.getLastName()+" to accept/reject you.";
				  m.addAttribute("bfReqSentStatusMessage",bfReqSentStatusStr);
			}
			//load all walls & add to Model
			List<Wall> allWallsList = loadAllWalls(oneUserBean);
			
			//add oneusers dp
			oneUserBean = getBase64ImageSetUser(oneUserBean);
			
			m.addAttribute("allWallsList", allWallsList);
			m.addAttribute("oneUserBean", oneUserBean);
			return "OneUsersMain";
		}
		catch(Exception e)
		{
			System.out.println("HC s sendBFrequ() "+e.getLocalizedMessage());
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
	}
	
	
	//showMyBFRequests
	@RequestMapping("/showMyBFRequests")
	public String showMyBFRequests(HttpSession hs,Model m)
	{
		System.out.println("------HC s showMyBFRequests()");
		String noPeningInvitationsStr ="";
		//String bfRequestsSentByYouStr = "";
		
		try
		{
			Set<Invitation> allInvtSet = loadMyBFRequests(hs);
			if(allInvtSet == null || allInvtSet.size() == 0)
			{
				noPeningInvitationsStr = "You do not have any pending invitations now.";
				m.addAttribute("noPendingInvitationsMessage", noPeningInvitationsStr);
			}
			else
			{
				Collection<Invitation> base64ImageSetUsersInvitations = getBase64ImageSetUsersInvitations(allInvtSet);
				
				System.out.println("after setting image ");
//				m.addAttribute("allPendingInvtSet", allInvtSet);
				
				System.out.println("\n after recvng baseimage all invtions are \n");
				
				for(Invitation invitation :base64ImageSetUsersInvitations)
				{
					System.out.println("for invation = \n "+invitation.getInviteId());
					
					System.out.println("fromuser iamge = \n "+invitation.getInvitationFromUser().getBase64Image());
					
					System.out.println("to user iamge = \n "+invitation.getInvitationToUser().getBase64Image());
				}
				
				
				m.addAttribute("allPendingInvtSet", base64ImageSetUsersInvitations);
				
			}
			
			//retive all LUSB sent  bf requsts
			//boolean isThereAnyPendingBFRequSentByLUSB = false ;
		//	isThereAnyPendingBFRequSentByLUSB = invitationServiceImpl.isLoggedUserHasSentAnyBFRequests(logg)
			
			Set<Invitation> allBFReqSentByLUSBSet = loadBFRequestsSentByMe(hs);
			
			Collection<Invitation> base64ImageUsersColl = getBase64ImageSetUsersInvitations(allBFReqSentByLUSBSet);
			
			System.out.println("\n after recvng baseimage all invtions are \n");
			
			for(Invitation invitation :base64ImageUsersColl)
			{
				System.out.println("for invation = \n "+invitation.getInviteId());
				
				System.out.println("fromuser iamge = \n "+invitation.getInvitationFromUser().getBase64Image());
				
				System.out.println("to user iamge = \n "+invitation.getInvitationToUser().getBase64Image());
			}
			//m.addAttribute("allBFReqSentByLUSBSet", allBFReqSentByLUSBSet)
			
			m.addAttribute("allBFReqSentByLUSBSet", base64ImageUsersColl);
			
			return "LoggedUsersPendingInvitations";
		}
		catch(Exception e)
		{			
			System.out.println("---hc s showMyBFRequests catch");
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			e.printStackTrace();
			return "Error";
		}
	}
 
	

	//acceptAsBF
	@RequestMapping(value = "/acceptAsBF",method = RequestMethod.POST)
	public String acceptAsBF(@RequestParam("inviteId") long inviteId,Model m,HttpSession hs)
	{
		System.out.println("--HC' acceptAsbf() ");
		try
		{
			//System.out.println("----- invitationFromUser = "+bfSenderUserId);
			User loggedUserBean = (User) hs.getAttribute("loggedUserBean");
			//get sender 
//--->>>		User senderUserBean = userServiceImpl.find(bfSenderUserId) ;
			String bfAcceptedStr="";
			String noPeningInvitationsStr = "";
		//	boolean isInvitationExist = invitationServiceImpl.isInvitationExist(senderUserBean, loggedUserBean);
		//inviteId
			Invitation 	acceptBFInvitation = invitationServiceImpl.find(inviteId);
			User senderUserBean = acceptBFInvitation.getInvitationFromUser() ;
			
			System.out.println("---sender identified by invtion obj = "+senderUserBean);
			if(acceptBFInvitation != null)
			{
				if(acceptBFInvitation.isInvitationAccepted() == false )
				{					
					
                   if( senderUserBean.getFriendsSet().add(loggedUserBean) == true )
                   {
                	   if( loggedUserBean.getFriendsSet().add(senderUserBean) == true )
                	   {
                		   acceptBFInvitation.setInvitationAccepted(true);
                		   
       					   acceptBFInvitation.setDateOfInvitationAccepted(new Date());
       					
       					   invitationServiceImpl.update(acceptBFInvitation); 
       					   
       					//save both sender & accept user beans
       					
       					userServiceImpl.update(senderUserBean);
       					userServiceImpl.update(loggedUserBean);
       					
       					bfAcceptedStr = "You have accepted "+senderUserBean.getFirstName()+" "+senderUserBean.getLastName()
       							+" as your Best Friend for life time,TQ";
                	   }                	  
                   }	        
					
				}
				else if(acceptBFInvitation.isInvitationAccepted() == true )
				{
					System.out.println("bf alrdy scactprtd ps calm down ");
					bfAcceptedStr ="We understand your exitement when you saw bf request from "+senderUserBean.getFirstName()+" "
							+senderUserBean.getLastName()+".But you have made him/her your BF already by accepting."
							+"Please do not waste time in clicking same button again & again."
							+"Just go to home & find "+senderUserBean.getFirstName()+" "+senderUserBean.getLastName()
							+" in your My BestFriends option & chat with him/her & enjoy.";
				}
			}
			else 
			{
				//if(acceptBFInvitation == null)
				bfAcceptedStr ="No such invitation to accept.";
			}
			//load rest of invtitions & send control back to sme page:)	
			Set<Invitation> allInvtSet = loadMyBFRequests(hs);
			if(allInvtSet == null || allInvtSet.size() == 0)
			{
				noPeningInvitationsStr = "You do not have any pending invitations now.";
				m.addAttribute("noPendingInvitationsMessage", noPeningInvitationsStr);
			}
			else
			{
				Collection<Invitation> base64ImageSetUsersReceivedInvitations = getBase64ImageSetUsersInvitations(allInvtSet);
				
				//m.addAttribute("allPendingInvtSet", allInvtSet);
				
				m.addAttribute("allPendingInvtSet", base64ImageSetUsersReceivedInvitations);
			}	
			
			//load all rq sent by LUSB			
			Set<Invitation> allBFReqSentByLUSBSet = loadBFRequestsSentByMe(hs);
			
//			m.addAttribute("allBFReqSentByLUSBSet", allBFReqSentByLUSBSet);
			
			//setbase64image
			Collection<Invitation> base64ImageSetUsersSentInvitations = getBase64ImageSetUsersInvitations(allBFReqSentByLUSBSet);
			

			
			m.addAttribute("allBFReqSentByLUSBSet", base64ImageSetUsersSentInvitations);
			
			m.addAttribute("bfAcceptedMessage",bfAcceptedStr);
			
			return "LoggedUsersPendingInvitations";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}		
	}

	//@RequestParam("inviteId") long inviteId
		//rejectBF
		@RequestMapping(value = "/rejectBF",method = RequestMethod.POST)
		public String rejectBF(@RequestParam("inviteId") long inviteId,Model m,HttpSession hs)
		{
			System.out.println("----HC's rejectF() ");
			try
			{
				User loggedUserBean = (User) hs.getAttribute("loggedUserBean");
				//get sender 
	//-->			User senderUserBean = userServiceImpl.find(bfSenderUserId) ;
		
				Invitation rejectBFInvitation = invitationServiceImpl.find(inviteId);
				
			//	boolean isInvitationExist = invitationServiceImpl.isInvitationExist(senderUserBean, loggedUserBean);
				String bfAcceptedStr="";
				if(rejectBFInvitation != null)
				{
					User senderUserBean = rejectBFInvitation.getInvitationFromUser() ;
					if(rejectBFInvitation.isInvitationAccepted() == false )
					{
						System.out.println("--not yet accpeted ");
						invitationServiceImpl.delete(rejectBFInvitation.getInviteId());
						System.out.println("invitedid = "+rejectBFInvitation.getInviteId()+" delted");
						bfAcceptedStr = "You successfully rejected "+senderUserBean.getFirstName()+" "
						+senderUserBean.getLastName()+" 's BF request. If you have done this mistakenly"
								+ " do not worry just go to "+senderUserBean.getFirstName()+" "
								+senderUserBean.getLastName()+" 's Profile & send brand new request.";							
					}
					else if(rejectBFInvitation.isInvitationAccepted() == true)
					{
						System.out.println("alrdy acprtd cant reject now");
						bfAcceptedStr = "You are too late to reject "+senderUserBean.getFirstName()+" "
						                +senderUserBean.getLastName()+" 's BF request. [Reason : You "
						                 + "had accpted his/her BF request. And "+senderUserBean.getFirstName()+" "
						                 +senderUserBean.getLastName()+" is your BF for life time.";							
					}
				}
				else
				{
				bfAcceptedStr = "No such invitation to reject";
				System.out.println("no request yet ");	
				}
			
				m.addAttribute("bfAcceptedMessage", bfAcceptedStr);
				
				//load rest of invtitions & send control back to sme page:)	
				Set<Invitation> allInvtSet = loadMyBFRequests(hs);
				if(allInvtSet == null || allInvtSet.size() == 0)
				{
					String noPeningInvitationsStr = "You do not have any pending invitations now.";
					m.addAttribute("noPendingInvitationsMessage", noPeningInvitationsStr);
				}
				else
				{
					Collection<Invitation> base64ImageSetUsersReceivedInvitations = getBase64ImageSetUsersInvitations(allInvtSet);
					
//					m.addAttribute("allPendingInvtSet", allInvtSet);
					
					m.addAttribute("allPendingInvtSet", base64ImageSetUsersReceivedInvitations);
				}
				//load all se tbf reqtss
				Set<Invitation> allBFReqSentByLUSBSet = loadBFRequestsSentByMe(hs);
				
				Collection<Invitation> base64ImageSetUsersSentInvitations = getBase64ImageSetUsersInvitations(allBFReqSentByLUSBSet);
				
				m.addAttribute("allBFReqSentByLUSBSet", base64ImageSetUsersSentInvitations);
				
//				m.addAttribute("allBFReqSentByLUSBSet", allBFReqSentByLUSBSet);
				
				m.addAttribute("bfAcceptedMessage", bfAcceptedStr);
				
				return "LoggedUsersPendingInvitations";
			}
			catch(Exception  e)
			{
				System.out.println("catch pf HC's areject bf");
				e.printStackTrace();
				System.out.println(e.getLocalizedMessage());
				m.addAttribute("errorMessage", e.getLocalizedMessage());
				return "Error";
			}		
		}
	
	
	
	
	
	//showMyBfs
	@RequestMapping("/showMyBfs")
	public String showMyBfs(Model m,HttpSession hs)
	{
		System.out.println("HC's showMyBfs()");
		try
		{
			User loggedUserBean = (User)hs.getAttribute("loggedUserBean");			
			System.out.println("lusb frnds set size= "+loggedUserBean.getFriendsSet().size());
	
			if(loggedUserBean.getFriendsSet().size() > 0)
			{
				//create on emap to hold usb & date of nvt acpetd
				//Map<User, Date> allBFsMap = new LinkedHashMap<User, Date>();
				
				Map<User, Date> allBFsMap = new TreeMap<User, Date>();
				
			   for(User u : loggedUserBean.getFriendsSet())
			   {
				   System.out.println("----------for user = "+u);
				//   boolean requestFromLUBToBF = false ;
				 
				   Invitation inv = null ;
				   if(invitationServiceImpl.isInvitationExist(loggedUserBean, u) == true )
				   {
					   System.out.println("----yes LUSB was sent bf req to BF..");
					  inv = invitationServiceImpl.find(loggedUserBean, u);				   
				   }
				   else 
				   {
					   System.out.println("bf was sent requst to LuSb");
					   inv = invitationServiceImpl.find(u,loggedUserBean);					   
				   }
				   if(inv != null)
				   {
					   //setbase64image for user
					   u = getBase64ImageSetUser(u);
					   
					   allBFsMap.put(u, inv.getDateOfInvitationAccepted()); 
				   }				   
			   }		
				
			   
			   m.addAttribute("loggedUsersBFsMap",allBFsMap); 							   
			}
			else
			{
				System.out.println("LUSB has no bf s yet");
				String noBFsYetStr = "You do not have any Best Friends."
						+" Send new BF requests / wait for your previously sent requests to get approved T.Q";
				m.addAttribute("noBFsYetMessage",noBFsYetStr);
			}		
			return "LoggedUsersBFs";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("cathc of HCs showMyBfs() = "+e.getLocalizedMessage());
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}		
	}
	//showOneBFProfile
	@RequestMapping("/showOneBFProfile")
	public String showOneBFProfile(@RequestParam("oneUserEmail") String oneUserEmail,Model m)
			
	{
		System.out.println("HC 's showOneBFProfile()");
		try
		{
			String unFriendStr ="";
			User oneUserBean = userServiceImpl.find(oneUserEmail);
			
			
					
			String bfReqSentStatusStr = "My BF";
			m.addAttribute("bfReqSentStatusMessage",bfReqSentStatusStr);
			
			//loading walls of oneBF
	
			List<Wall> allWallsList = loadAllWalls(oneUserBean);
			Wall wallBean = new Wall() ;
			m.addAttribute("wallBean", wallBean);
			m.addAttribute("allWallsList", allWallsList);
						
			unFriendStr = "UnFriend";
			m.addAttribute("unFriendMessage", unFriendStr);
			
			oneUserBean = getBase64ImageSetUser(oneUserBean);
			
			m.addAttribute("oneUserBean", oneUserBean);	
			
			
			return "OneUsersMain";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("cathc of HCs sho1bfprfil() = "+e.getLocalizedMessage());
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}	
	}
	
	
	//LoggedUsers Account Details
	@RequestMapping("/toAccount")
	public String showMyAccountDetails(Model m)
	{
		//User loggedUserBean = (User) hs.getAttribute("loggedUserBean");
		//m.addAttribute("loggedUserBean", loggedUserBean) ;
		System.out.println("-----HC's showMyAccountDetails()");
		
		try
		{
			return "LoggedUsersAccountDetails";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
	}
	
	//changeMyPassword
	@RequestMapping("/openChangeMyPasswordView")
	public String openChangeMyPasswordView(Model m)
	{
		System.out.println("HC's openChangeMyPasswordView");
		try
		{
			return "LoggedUsersChangePassword";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
		//return "LoggedUsersChangePassword";
	}
	
	//changeMyPassword
	@RequestMapping(value = "/changeMyPassword",method = RequestMethod.POST)
	public String changeMyPassword(@RequestParam("currentPassword") String currentPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("reEnterNewPassword") String reEnterNewPassword,
			Model m,HttpSession hs)
	{
		System.out.println("HC's changeMyPassword()");
		try
		{
			String changePasswordStatusStr = "";
			User loggedUserBean = (User) hs.getAttribute("loggedUserBean");
			if(loggedUserBean.getPassword().equals(currentPassword) == true)
			{
				System.out.println("yes changer is guinei Ld user");
				if(newPassword == null || newPassword.trim().length() == 0)
				{
					changePasswordStatusStr = "Please enter new Password";
				}
				else if(reEnterNewPassword == null || reEnterNewPassword.trim().length() == 0)
				{
					changePasswordStatusStr = "Please re-enter the same password which you just entered in "
							+ "the new password field.";
				}
				else if(newPassword.equals(reEnterNewPassword) == false)
				{
					changePasswordStatusStr = "New password & re-entered passwords are not matching"
							+".Please kindly enter same passwords in the both fields."
							+ " [do not copy paste]";
				}
				else if(newPassword.equals(reEnterNewPassword) == true)
				{
					System.out.println("yes new & renew ps r okay tq new passwrdleht= "+newPassword.trim().length());
					loggedUserBean.setPassword(newPassword.trim());
					
					userServiceImpl.update(loggedUserBean);
					changePasswordStatusStr = "Your password is changed successfully.";
					
					m.addAttribute("changePasswordStatusMessage", changePasswordStatusStr);
					
					return "LoggedUsersAccountDetails";
				}
			}
			else if(loggedUserBean.getPassword().equals(currentPassword) == false)
			{
				changePasswordStatusStr = "You are not the owner of this profile."
						+" Enter right current password if I am wrong.";
			}
			System.out.println("-----somthing is msiing so returning to change psrd view  ");
			System.out.println("str = "+changePasswordStatusStr.toUpperCase());
			m.addAttribute("changePasswordStatusMessage", changePasswordStatusStr);
			return "LoggedUsersChangePassword";
			
			
		}
		catch(Exception e)
		{
			System.out.println("----------catch of HC's changeMyPassword");
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}		
	}
	
	//editMyAccountDetails
	@RequestMapping("/editMyAccountDetails")
	public String editMyAccountDetails(Model m,HttpSession hs)
	{
		System.out.println("---HC editMyAccountDetails()");
		try
		{
			
			User loggedUserBean = (User)hs.getAttribute("loggedUserBean");
			System.out.println("going to LoggedUsersEditAccount  adding  LUSB to model LUSB id = "+loggedUserBean.getUserId());
			
			m.addAttribute("updatedLoggedUserBean", loggedUserBean);
			
			return "LoggedUsersEditAccount";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("--- catch()...HC' editMyAccountDetails 's ");
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
	}
	
	//updateEditedAccount
	@RequestMapping(value = "/updateEditedAccount",method = RequestMethod.POST)
	public String updateEditedAccount(@ModelAttribute("updatedLoggedUserBean")@Valid User updatedLoggedUserBean, 
			Model m,BindingResult br
			,HttpSession hs)
	{
		System.out.println("----HC's updateEditedAccount()");
		String lubAccountUpdateStatusStr = "";
		try
		{
			User loggedUserBean = (User)hs.getAttribute("loggedUserBean");
			System.out.println("_______-----------------------");
			//System.out.println("---b4 upadiitnf LUSB =  "+loggedUserBean);
			//System.out.println("------LUSB s userId = "+loggedUserBean.getUserId());
			
			//copy userId of LUSB to UpdateLUSB & update updtedLUSB usig dao
			updatedLoggedUserBean.setUserId(loggedUserBean.getUserId());
			//set activity level to true
			updatedLoggedUserBean.setActiveMember(true);
			
		
			
			userServiceImpl.update(updatedLoggedUserBean);
			
			
		//	System.out.println("--------upatded user tq.....");
		//	System.out.println("-----after updating  LUSB = "+loggedUserBean);
			//System.out.println("-----after updaying Updated LUSB = "+updatedLoggedUserBean);
			lubAccountUpdateStatusStr = "Hey "+updatedLoggedUserBean.getFirstName()+" "+
			         updatedLoggedUserBean.getLastName()
					+" your account details are updated to your wish & Please find new details as follows. T.Q";
			
			//set base64imageto user
			updatedLoggedUserBean = getBase64ImageSetUser(updatedLoggedUserBean);
			
			//add back the updated LUSB to session
			hs.setAttribute("loggedUserBean", updatedLoggedUserBean);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("------HC s catch of updateEditedAccount");
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
		m.addAttribute("lubAccountUpdateStatusMessage", lubAccountUpdateStatusStr);
		
		return "LoggedUsersAccountDetails";
	}
	
	//toOneUsersAccount
	@RequestMapping("/toOneUsersAccount")
	public String toOneUsersAccount(@RequestParam("oneUsersEmail") String oneUsersEmail,
			@RequestParam("makeBFMessage") String makeBFStr,
			@RequestParam("bfReqSentStatusMessage") String bfReqSentStatusStr
			,Model m)
	{
		System.out.println("----------HC's toOneUsersAccount()");
		try
		{
			System.out.println("---------1usb email = "+oneUsersEmail);
			User oneUserBean  = userServiceImpl.find(oneUsersEmail) ;
			
			oneUserBean = getBase64ImageSetUser(oneUserBean);
			
			m.addAttribute("detailedOneUserBean", oneUserBean) ;
			m.addAttribute("oneUserBean", oneUserBean) ;
			m.addAttribute("makeBFMessage", makeBFStr);
			m.addAttribute("bfReqSentStatusMessage",bfReqSentStatusStr);
			
			//load all walls & add to Model
			List<Wall> allWallsList = loadAllWalls(oneUserBean);
			m.addAttribute("allWallsList", allWallsList);
			
			return "OneUsersMain";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
		
	}
	
	//sendMessageToOneUserBean
	@RequestMapping("/sendMessageToOneUserBean")
	public String sendMessageToOneUserBean(@RequestParam("messageToOneUserBeanStr") String messageToOneUserBeanStr,
			@RequestParam("oneUsersEmail") String oneUsersEmail,
			@RequestParam("makeBFMessage") String makeBFStr,
			@RequestParam("bfReqSentStatusMessage") String bfReqSentStatusStr,
			Model m,HttpSession hs,@RequestParam("requestedPageStr") String requestedpageStr)
			
	{
		System.out.println("------hC' sendMessageToOneUserBean()");
		//System.out.println("requestedPageStr size  = "+requestedpageStr.length());
		//System.out.println("requestedPageStr = "+requestedpageStr);
		String messageSentStatusStr = "";
		String repliedMessageStatusStr ="";
	//	System.out.println("--------------------------HSREsq's reqURI = "+hsReq.getRequestURI());
	//	System.out.println("--------------------------HSREsq's URL    = "+hsReq.getRequestURL());
		//System.out.println("---------------------------"+hsReq.get);
		try
		{
			//find recver
			User oneUserBean  = userServiceImpl.find(oneUsersEmail) ;
			
			m.addAttribute("makeBFMessage", makeBFStr);
			m.addAttribute("bfReqSentStatusMessage",bfReqSentStatusStr);
			
			if(messageToOneUserBeanStr == null || messageToOneUserBeanStr.trim().length() == 0)
			{
				messageSentStatusStr = "No worth of sending a blank message, we do not support you,Sorry";
				repliedMessageStatusStr = "No worth of replying a blank message, we do not support you,Sorry";
			}
			else if(DirtyCheck.isMessageDirty(messageToOneUserBeanStr) == true )
			{
				messageSentStatusStr = "We do not support dirty messages.";
				repliedMessageStatusStr = "We do not support dirty replies.";
			}
			else
			{
				//sender
				User loggedUserBean = (User)hs.getAttribute("loggedUserBean");		
				
				
				//crate message for sender
				Message fromLUBToOneUSBMessage1 = new Message();
				fromLUBToOneUSBMessage1.setMessage(messageToOneUserBeanStr);
				fromLUBToOneUSBMessage1.setDateOfMessageCreated(new Date());
				fromLUBToOneUSBMessage1.setMessageFromUser(loggedUserBean);
				fromLUBToOneUSBMessage1.setMessageToUser(oneUserBean);
				fromLUBToOneUSBMessage1.setMessageOwnerUser(loggedUserBean);
				
				//crate message for receiver
				Message fromLUBToOneUSBMessage2 = new Message();
				fromLUBToOneUSBMessage2.setMessage(messageToOneUserBeanStr);
				//do not crete new date here wanring only 1 msg stste two objects
				fromLUBToOneUSBMessage2.setDateOfMessageCreated(fromLUBToOneUSBMessage1.getDateOfMessageCreated());
				fromLUBToOneUSBMessage2.setMessageFromUser(loggedUserBean);
				fromLUBToOneUSBMessage2.setMessageToUser(oneUserBean);
				fromLUBToOneUSBMessage2.setMessageOwnerUser(oneUserBean);
				//save message obj1 
				messageServiceImpl.create(fromLUBToOneUSBMessage1);
				
				//save message obj2
				messageServiceImpl.create(fromLUBToOneUSBMessage2);
				//now add mesage to 1usb & update 1usb
				//add to boths collctions
				if( (oneUserBean.getMessagesList().add(fromLUBToOneUSBMessage2) == true )
						&& ( loggedUserBean.getMessagesList().add(fromLUBToOneUSBMessage1) ) == true )
				{
					System.out.println("yes mesage added succfully to both ");
					userServiceImpl.update(oneUserBean);
					userServiceImpl.update(loggedUserBean);
					System.out.println("//////////////aftr updating");
					System.out.println("usbn msglis size = "+oneUserBean.getMessagesList().size());
					System.out.println("all 1usb msgs = "+oneUserBean.getMessagesList());
					
					System.out.println("");
					System.out.println("LUSB msglts sz = "+loggedUserBean.getMessagesList().size());
                    System.out.println("all "+loggedUserBean.getMessagesList());
					messageSentStatusStr = "Message sent successfully";
					repliedMessageStatusStr = "Successfully replied to "+oneUserBean.getFirstName()+" "
							+oneUserBean.getLastName();
				}
				else
				{
					System.out.println("no coulnd add messge rollbacking ");
			
					//delete both msg objstcs if failed to add to sub cll & lusb colnt
					messageServiceImpl.delete(fromLUBToOneUSBMessage1.getMessageId());
					messageServiceImpl.delete(fromLUBToOneUSBMessage2.getMessageId());
					messageSentStatusStr = "Sorry,Due to some reasons we could not forward your message"
							+".Please re try after checking your internet connection,T.Q";
					repliedMessageStatusStr = "Sorry,Due to some reasons we could not forward your reply"
							+".Please re try after checking your internet connection,T.Q";
				}
			}
			//check if returning to OneUSersMani page
			if(requestedpageStr.equals("OneUsersMainPage") == true)
			{
				oneUserBean = getBase64ImageSetUser(oneUserBean);
				
				m.addAttribute("oneUserBean", oneUserBean);
				m.addAttribute("messageSentStatusMessage", messageSentStatusStr);
				
				//load all walls & add to Model
				List<Wall> allWallsList = loadAllWalls(oneUserBean);
				//Wall wallBean = new Wall();
				//m.addAttribute("wallBean", wallBean);
				m.addAttribute("allWallsList", allWallsList);
				
				return "OneUsersMain";
			}
			else if(requestedpageStr.equals("LoggedUsersMessagesPage") == true)
			{
				m.addAttribute("repliedMessageStatusMessage", repliedMessageStatusStr);
				//return "LoggedUsersMessages";
				return showMyMessages(m, hs);
			}
			else
			{
				return "Error";
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
	}
	//showMyMessages
	@RequestMapping("/showMyMessages")
	public String showMyMessages(Model m ,HttpSession hs)
	{
		System.out.println("-------hc's showMyMessages()");
		String lusbAllMessagesStatusStr ="" ;
		try
		{
			User loggedUserBean = (User)hs.getAttribute("loggedUserBean");
			/*System.out.println("msggLis size  = "+loggedUserBean.getMessagesList().size());
			for(Message m1:loggedUserBean.getMessagesList())
			{
				System.out.println("s11= msg = "+m1);
			}*/
			
		
		List<Message>  tempList= messageServiceImpl.getAllMessagesOfLUSB(loggedUserBean);
		//	List<Message> tempList = loggedUserBean.getMessagesList();
			
			//System.out.println("lusbList size = "+lusbAllMesgList.size());
			/*for(Message m1:lusbAllMesgList)
			{
				
				System.out.println("-------------");
				System.out.println("m1.getMessageFromUser().equals(loggedUserBean) = "+m1.getMessageFromUser().equals(loggedUserBean));
				System.out.println("m1.getMessageToUser().equals(loggedUserBean) "+m1.getMessageToUser().equals(loggedUserBean));
				System.out.println("s11= msg = "+m1);
				System.out.println("from "+m1.getMessageFromUser().getFirstName()+" to "+m1.getMessageToUser().getFirstName());
			}*/
			SortByDateOfMessageSent dateCmp = new SortByDateOfMessageSent() ;
			//TreeMap<Message, Date> tmap = new TreeMap<Message, Date>(dateCmp);
			TreeMap<Date, Message>  treeMap = new TreeMap<Date, Message>(dateCmp);
			//copy evrything from lst to map
			for(Message msg : tempList)
			{
				//System.out.println("----puting "+msg+" into map ");
				treeMap.put(msg.getDateOfMessageCreated(), msg);
			}
			//creta new Arraylist & copy all msgs 
			List<Message> lusbAllMesgList = new ArrayList<Message>();
			//copy all msg from map tit lsit
			
			for(Entry<Date, Message> e :treeMap.entrySet())
			{
				System.out.println("e = "+e);
				System.out.println("e.key = "+e.getKey());
				System.out.println("e value = "+e.getValue());
				lusbAllMesgList.add(e.getValue());
				
			}
  			if(lusbAllMesgList == null || lusbAllMesgList.size() == 0 )
			{
				lusbAllMessagesStatusStr = "Nither you have sent, nor you have received any messages now.";
			}
			else
			{		
				
				//m.addAttribute("lusbAllMesgList", lusbAllMesgList);
				Collection<Message> base64ImageSetUsersMessages = getBase64ImageSetUsersMessages(lusbAllMesgList);
				
				m.addAttribute("lusbAllMesgList", base64ImageSetUsersMessages);
				
			}
			m.addAttribute("lusbAllMessagesStatusMessage", lusbAllMessagesStatusStr);
			////m.addAttribute("loggedUserBean", loggedUserBean);
			return "LoggedUsersMessages";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}		
	}
	


	//delete a msg of lUSB
	@RequestMapping(value ="/deleteMyThisMessage",method = RequestMethod.POST)
	public String deleteMyThisMessage(@RequestParam("messageId") long messageId,Model m,HttpSession hs)
	{
		System.out.println("---------HC's deleteMyThisMessage()");
		String deleteMessageStatusStr = "";
		try
		{
			System.out.println("deleting in HC ..messageId = "+messageId);
			messageServiceImpl.delete(messageId);
			deleteMessageStatusStr = "Successfully deleted";
			m.addAttribute("deleteMessageStatusMessage", deleteMessageStatusStr);
			return showMyMessages(m, hs);
			//return "LoggedUsersMessages";
		}
		catch(Exception e)
		{
			System.out.println("----catch of Hc's deleteMyThisMessage ");
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}		
	}
	
	//cancellBFRequest
	@RequestMapping(value = "/cancellBFRequest",method = RequestMethod.POST)
	public String cancellBFRequest(@RequestParam("inviteId") long inviteId,Model m,HttpSession hs)
	{
		try
		{
			String sentInvitationStatusStr = "";
			Invitation sentInvitation = invitationServiceImpl.find(inviteId) ;
			if(sentInvitation != null)
			{
				if(sentInvitation.isInvitationAccepted() == true )
				{
					sentInvitationStatusStr = "You are too late to take back your BF invitation."
							+" Receiver has already accepted it";
				}
				else if(sentInvitation.isInvitationAccepted() == false)
				{
					invitationServiceImpl.delete(inviteId);
					sentInvitationStatusStr ="You succeeded in taking back the sent BF request."
							+" Next time send invitation only if you are sure about the receiver.";
				}					
			}
			else
			{
				sentInvitationStatusStr =" You have not sent any such invitation to cancell";
			}
			m.addAttribute("sentInvitationStatusMessage", sentInvitationStatusStr);
			
			//load rest of invtitions & send control back to sme page:)	
			
			Set<Invitation> allInvtSet = loadMyBFRequests(hs);
			
			if(allInvtSet == null || allInvtSet.size() == 0)
			{
				String noPeningInvitationsStr = "You do not have any pending invitations now.";
				m.addAttribute("noPendingInvitationsMessage", noPeningInvitationsStr);
			}
			else
			{
				
				
				Collection<Invitation> base64ImageSetUsersReceivedInvitations = getBase64ImageSetUsersInvitations(allInvtSet);
				
//				m.addAttribute("allPendingInvtSet", allInvtSet);
				
				m.addAttribute("allPendingInvtSet", base64ImageSetUsersReceivedInvitations);
			}
			//load all se tbf reqtss
			Set<Invitation> allBFReqSentByLUSBSet = loadBFRequestsSentByMe(hs);
			
			Collection<Invitation> base64ImageSetUsersSentInvitations = getBase64ImageSetUsersInvitations(allBFReqSentByLUSBSet);
			
			
//			m.addAttribute("allBFReqSentByLUSBSet", allBFReqSentByLUSBSet);
			
			m.addAttribute("allBFReqSentByLUSBSet", base64ImageSetUsersSentInvitations);
			
			return "LoggedUsersPendingInvitations";
			
		}
		catch(Exception e)
		{
			//System.out.println("----catch of Hc's deleteMyThisMessage ");
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
	}
	
	//deleteMyThisWall
	@RequestMapping("/deleteMyThisWall")
	public String deleteMyThisWall(@RequestParam("wallId") long wallId ,Model m,HttpSession hs,
			@RequestParam("makeBFMessage") String makeBFMessage,
			@RequestParam("bfReqSentStatusMessage") String bfReqSentStatusMessage,
			@RequestParam("oneUsersEmail") String oneUsersEmail)
	{
		System.out.println("------HC 's deleteMyThisWall() with wallid = "+wallId);
		String wallDeleteStatusStr = "";
		User loggedUserBean = (User)hs.getAttribute("loggedUserBean");
		try
		{
			Wall deleteWall = wallServiceImpl.find(wallId);
			if(deleteWall == null)
			{
				System.out.println("------HC 's deleteMyThisWall() deletewll = null");
				wallDeleteStatusStr = "We understand your angerness when you saw that post on your wall."+
						"But ,that wall which you are trying to delete is already been deleted by you";
			}
			else
			{
				System.out.println("------HC 's deleteMyThisWall() deletewll is goimg o be deleted");
				int deltedRowsInt = wallServiceImpl.delete(wallId);
				if(deltedRowsInt == 1)
				{
					wallDeleteStatusStr = "The wall you did not like was successfully deleted.";
				}
				else
					wallDeleteStatusStr ="Something went wrong in deleting a wall,sorry";
			}
			
			m.addAttribute("wallDeleteStatusMessage", wallDeleteStatusStr);
			List<Wall> allWallsList = new ArrayList<Wall>();
			if(oneUsersEmail == null || oneUsersEmail.trim().length() == 0)
			{
				//load all walls
//				allWallsList = loadAllWalls(loggedUserBean);
//				m.addAttribute("allWallsList", allWallsList);
//				return "Main"
				
				return toMain(m, hs);
			}
			else
			{
				User oneUserBean = userServiceImpl.find(oneUsersEmail);
				allWallsList = loadAllWalls(oneUserBean);
				//1 usman speuicf
				
				oneUserBean = getBase64ImageSetUser(oneUserBean);
				
				m.addAttribute("makeBFMessage", makeBFMessage);
				m.addAttribute("bfReqSentStatusMessage", bfReqSentStatusMessage);
				m.addAttribute("allWallsList", allWallsList);
				m.addAttribute("oneUserBean", oneUserBean);
				return "OneUsersMain";
			}
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
	}
	
	//wallId
	@RequestMapping(value = "/likeThisWall",method = RequestMethod.POST)
	public String likeThisWall(@RequestParam("wallId") long wallId,Model m,HttpSession hs,
			@RequestParam("makeBFMessage") String makeBFMessage,
			@RequestParam("bfReqSentStatusMessage") String bfReqSentStatusMessage,
			@RequestParam("oneUsersEmail") String oneUsersEmail)
	{
		System.out.println("HC's --------------likeThisWall()");
		User loggedUserBean = (User)hs.getAttribute("loggedUserBean");
		String likeWallStatusStr = "";
		String returnPageStr ="";
		try 
		{
			Wall wallBean = wallServiceImpl.find(wallId);
			if(wallBean == null )
			{
				likeWallStatusStr = "The wall you want to like is no more";
			}
			else
			{
				Set<User> allLikedUsersSet = wallBean.getLikedUsersSet() ;
				if(allLikedUsersSet != null)
				{
					//check wether perso alreayd liked
					if ( allLikedUsersSet.contains(loggedUserBean) == true )
					{
						//do not do anything leave as it is
						likeWallStatusStr = "You have already liked this wall "
								+ ",clicking same button again & again will not increase the total number of likes.";
					}
					else
					{
						//add thta LUSb 
						if( allLikedUsersSet.add(loggedUserBean) == true )
						{
							wallBean.setTotalWallLikes(wallBean.getTotalWallLikes()+1);
							Set<User> allHatedUsersSet = wallBean.getHatedUsersSet();
							if( allHatedUsersSet.contains(loggedUserBean) == true )
							{
								//if it ctains hated remove
								if( allHatedUsersSet.remove(loggedUserBean) == true )
								{
									wallBean.setTotalWallHates(wallBean.getTotalWallHates() - 1);
								}
							}
							wallServiceImpl.update(wallBean);
							likeWallStatusStr = "You have liked a wall ...you still can undo it by clicking on \"I hate it\" option";
						}
						else
						{
							likeWallStatusStr = "Could not like due to some reasons,sorry";
						}
					}
				}
			}
			List<Wall> allWallsList = new ArrayList<Wall>();
			if(oneUsersEmail == null || oneUsersEmail.trim().length() == 0)
			{
			
				//load all walls of LUSB
				allWallsList = loadAllWalls(loggedUserBean);
		
				m.addAttribute("likeWallStatusMessage",likeWallStatusStr);
				returnPageStr ="Main";
			}
			else
			{
				//load all walls 1USb
				User oneUserBean = userServiceImpl.find(oneUsersEmail);
				allWallsList = loadAllWalls(oneUserBean);
			
				oneUserBean = getBase64ImageSetUser(oneUserBean);
				
				//1 usman speuicf
				m.addAttribute("makeBFMessage", makeBFMessage);
				m.addAttribute("bfReqSentStatusMessage", bfReqSentStatusMessage);
				
				m.addAttribute("oneUserBean", oneUserBean);
				returnPageStr = "OneUsersMain";			
				
			}
		
			m.addAttribute("allWallsList", allWallsList);
			m.addAttribute("likeWallStatusMessage",likeWallStatusStr);
						
			return returnPageStr ;
						
		}
		catch (Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
	}
	
	//hateThisWall
	@RequestMapping(value = "/hateThisWall",method = RequestMethod.POST)
	public String hateThisWall(@RequestParam("wallId") long wallId,Model m,HttpSession hs,
			@RequestParam("makeBFMessage") String makeBFMessage,
			@RequestParam("bfReqSentStatusMessage") String bfReqSentStatusMessage,
			@RequestParam("oneUsersEmail") String oneUsersEmail)
	{
		System.out.println("----HC 's -hateThisWall()");
		String returnPageStr ="";
		String hateWallStatusStr = "";
		try
		{
			User loggedUserBean = (User)hs.getAttribute("loggedUserBean");
			
			Wall wallBean = wallServiceImpl.find(wallId);
			if(wallBean == null )
			{
				hateWallStatusStr = "The wall you want to hate is no more";
			}
			else
			{
				Set<User> allHatedUsersSet = wallBean.getHatedUsersSet();
				if(allHatedUsersSet != null)
				{
					if(allHatedUsersSet.contains(loggedUserBean) == true )
					{
						//do not do anything leave as it is
						hateWallStatusStr = "You have already hated this wall "
								+ ",clicking same button again & again will not increase the total number of hates.";
					}
					else
					{
						if(allHatedUsersSet.add(loggedUserBean) == true)
						{
							//imcrease hates
							wallBean.setTotalWallHates(wallBean.getTotalWallHates()+1);
							Set<User> allLikedUsersSet = wallBean.getLikedUsersSet() ;
							if(allLikedUsersSet.contains(loggedUserBean) == true)
							{
								System.out.println("yes alreday liked memebr ");
								if(allLikedUsersSet.remove(loggedUserBean) == true)
								{
									// drecres likes								
									wallBean.setTotalWallLikes(wallBean.getTotalWallLikes()-1);
								}
							}
							wallServiceImpl.update(wallBean);
							hateWallStatusStr = "You have hated a wall ...you still can undo "
									+ "it by clicking on \"I like it\" option";
						}
					}
				}
			}
			List<Wall> allWallsList = new ArrayList<Wall>();
			if(oneUsersEmail == null || oneUsersEmail.trim().length() == 0)
			{
			
				//load all walls of LUSB
				allWallsList = loadAllWalls(loggedUserBean);
		
				m.addAttribute("hateWallStatusMessage",hateWallStatusStr);
				returnPageStr ="Main";
			}
			else
			{
				//load all walls 1USb
				User oneUserBean = userServiceImpl.find(oneUsersEmail);
				allWallsList = loadAllWalls(oneUserBean);
			
				oneUserBean = getBase64ImageSetUser(oneUserBean);
				
				//1 usman speuicf
				m.addAttribute("makeBFMessage", makeBFMessage);
				m.addAttribute("bfReqSentStatusMessage", bfReqSentStatusMessage);			
				m.addAttribute("oneUserBean", oneUserBean);
				returnPageStr = "OneUsersMain";
				
				
			}
		
			m.addAttribute("allWallsList", allWallsList);
			m.addAttribute("hateWallStatusMessage",hateWallStatusStr);
						
			return returnPageStr ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			return "Error";
		}
	}
	
	//showAllLikesAndHates
	@RequestMapping(value ="/showAllLikesAndHates",method = RequestMethod.POST)
	public String showAllLikesAndHates(@RequestParam("wallId") long wallId,Model m)
	{
		System.out.println("--------------HC's---showAllLikesAndHates");
		String seeAlllikesNHatesStatusStr = "";
	
		try
		{
		   Wall wallBean = wallServiceImpl.find(wallId);
		   if(wallBean == null)
		   {
			  seeAlllikesNHatesStatusStr = "The wall whose all likes & hates you want to see"
			  		+ " is no more"; 
		   }
		   else
		   {
			
//			  m.addAttribute("wallBean", wallBean); 
			   
			   //set local api to set base64image 
			   
			  Wall base64ImageSetUsersWall =  getBase64ImageSetUsersWall(wallBean);
			   
			  m.addAttribute("wallBean", base64ImageSetUsersWall); 
		   }
		   m.addAttribute("seeAlllikesNHatesStatusMessage", seeAlllikesNHatesStatusStr);
		   return "WallLikesAndHates";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessagae", e.getLocalizedMessage());
			return "Error";
		}
	}
	
	

	//showOneUsersBFs
	@RequestMapping(value ="/showOneUsersBFs",method = RequestMethod.POST)
	public String showOneUsersBFs(@RequestParam("oneUsersEmail") String oneUsersEmail,Model m)
	{
		System.out.println("---HC;s showOneUsersBFs()");
		String showOneUsersBFStatusStr = "";
		try 
		{
			User oneUserBean = userServiceImpl.find(oneUsersEmail);
			Set<User> oneUsersBFsSet = oneUserBean.getFriendsSet() ;
			if(oneUsersBFsSet == null || oneUsersBFsSet.size() == 0)
			{
				showOneUsersBFStatusStr = oneUserBean.getFirstName()+" "+oneUserBean.getLastName()
						+" does not have any BFs.";
			}
			else
			{
				Collection<User> base64ImageSetUsers = getBase64ImageSetUsers(oneUsersBFsSet);
				
				//m.addAttribute("oneUsersBFsSet", oneUsersBFsSet);
				
				m.addAttribute("oneUsersBFsSet", base64ImageSetUsers);
			}
			
			oneUserBean = getBase64ImageSetUser(oneUserBean);
			
			m.addAttribute("showOneUsersBFStatusMessage", showOneUsersBFStatusStr);
			m.addAttribute("oneUserBean", oneUserBean);
			return "OneUsersBFs";
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessagae", e.getLocalizedMessage());
			return "Error";
		}
	}
	


/*	//openAdvancedSearchView
	@RequestMapping("/openAdvancedSearchView")
	public String openAdvancedSearchView(Model m)
	{
		System.out.println("------------HC's openAdvancedSearchView");
		try
		{
			return "AdvancedSearch";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessagae", e.getLocalizedMessage());
			return "Error";
		}
	}*/
	
/*	//advancedSearch
	@RequestMapping(value ="/advancedSearch",method = RequestMethod.POST)
	public String advancedSearch(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,Model m,
			@RequestParam("email") String email,		
			@RequestParam("city") String city,
			@RequestParam("state") String state,
			@RequestParam("country") String country)
	{
		System.out.println("----------HC' advancedSearch()");
		String advSerchResStatusStr = "";
		try
		{
			Set<User> searchResultSet = userServiceImpl.advancedSearch(firstName, lastName, email,city, state, country);
			if(searchResultSet == null || searchResultSet.size() == 0)
			{
				advSerchResStatusStr="Zero matching records found .Please try something else, good luck";
			}
			else
			{
				m.addAttribute("searchResultSet", searchResultSet);
			}
			m.addAttribute("advSerchResStatusMessage", advSerchResStatusStr);
			return "AdvancedSearch";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessagae", e.getLocalizedMessage());
			return "Error";
		}
	}*/
	
	//enctype="multipart/form-data"
	/*@RequestMapping("/openChngeDpView")
	public String openChngeDpView(Model m)
	{
		System.out.println("HC'c openChngeDpView()");
		User dpUserBean = new User();
		m.addAttribute("dpUserBean", dpUserBean);
		return "ChangeDp";
	}*/
	
	
/*	//changeDp
    @RequestMapping("/changeDp")
	public String changeDp(@RequestParam("dp") Blob dp,Model m,HttpSession hs)
	{
		System.out.println("HC'c chngeDp");
		try
		{
			String dpStatusStr = "";
			User loggedUserBean = (User)hs.getAttribute("loggedUserBean");
			
			//loggedUserBean.setDp(dpUserBean.getDp());
		//	userServiceImpl.update(loggedUserBean);
		//	hs.setAttribute("loggedUserBean", loggedUserBean);
			dpStatusStr = "Dp updated successfully";
			m.addAttribute("dpStatusMessage", dpStatusStr);
			return "ChangeDp";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessagae", e.getLocalizedMessage());
			return "Error";
		}
	}*/
	//@RequestParam("bfReqSentStatusMessage") String bfReqSentStatusMessage
	//unFriend
	@RequestMapping(value ="/unFriend",method=RequestMethod.POST)
	public String unFriend(@RequestParam("oneUserId") long oneUserId,Model m,HttpSession hs)
	{
		try
		{
			String makeBFStr = "Make B.F";	
		
			User loggedUserBean  = (User)hs.getAttribute("loggedUserBean");
			User oneUserBean = userServiceImpl.find(oneUserId);
			if(loggedUserBean.getFriendsSet().remove(oneUserBean) == true )
			{
				System.out.println("---LUSB removd 1usb ");
				if(oneUserBean.getFriendsSet().remove(loggedUserBean) == true )
				{
					System.out.println("--1usb iunfrnded Lusb ");
					userServiceImpl.update(oneUserBean);
					userServiceImpl.update(loggedUserBean);
					
					
					
					
					
					
					m.addAttribute("loggedUserBean", loggedUserBean);
				}
			}
			//loading waaalss of 1usb
			List<Wall> allWallsList = loadAllWalls(oneUserBean);
			Wall wallBean = new Wall() ;
			m.addAttribute("wallBean", wallBean);
			m.addAttribute("allWallsList", allWallsList);
			//m.addAttribute("bfReqSentStatusMessage", bfReqSentStatusMessage);
			
			oneUserBean = getBase64ImageSetUser(oneUserBean);
			
			
			m.addAttribute("oneUserBean", oneUserBean);
			//make eligible forsending bf req again
			m.addAttribute("makeBFMessage", makeBFStr);
			
			//delete invitation
			Invitation deleteInvitation = null ;
			boolean isInvt1ExistBool = invitationServiceImpl.isInvitationExist(loggedUserBean, oneUserBean);
			boolean isInvt2ExistBool = invitationServiceImpl.isInvitationExist(oneUserBean, loggedUserBean);
			if(isInvt1ExistBool == true )
				deleteInvitation = invitationServiceImpl.find(loggedUserBean, oneUserBean);
			else if (isInvt2ExistBool == true)
				deleteInvitation = invitationServiceImpl.find(oneUserBean, loggedUserBean);
	
			if(deleteInvitation != null)
			{
				invitationServiceImpl.delete(deleteInvitation.getInviteId());
			}
			return "OneUsersMain";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			m.addAttribute("errorMessagae", e.getLocalizedMessage());
			return "Error";
		}
	}
	
	
	
	
	
	    //openUploadProfilePicOption
        @RequestMapping(value = "/openUploadProfilePicOption",method = RequestMethod.GET)
		public String openChngeDpView(Model m, HttpSession session)
		{
			System.out.println("HC'c openChngeDpView()");
			LOGGER.info("BFC-openChngeDpView-");
			
			User user = (User)session.getAttribute("loggedUserBean");
			//User dpUserBean = new User();
			//m.addAttribute("dpUserBean", dpUserBean);
			boolean isUserLikeToAddOrChangeDP = true ;
			
			m.addAttribute("isUserLikeToAddOrChangeDP", isUserLikeToAddOrChangeDP);
			
			//return "Main";
			
			return toMain(m, session);
			//addOrChangeDP
			//return "ChangeDp";
		}
    
    
	@RequestMapping(value = "/uploadProfilePic",method = RequestMethod.POST)
	public String uploadProfilePic(@RequestParam("profilePic") MultipartFile profilePic,
			HttpSession session,Model m)
	{
		LOGGER.info("\n UserProfileController -uploadProfilePic -  \n");
		System.out.println("\n UserProfileController -uploadProfilePic -  \n");
		String dpStatusStr = "";
		try
		{
			if(profilePic == null || profilePic.isEmpty())
			{
			
				System.out.println("\n UserProfileController -uploadProfilePic - profilePic i snull or empty .. so stoping\n");
				//throw new Exception("please upload image photo");
				String errorMessage = "U didn't select any photo, so we didnt add/Change ur Profile Pic..";
				throw new RuntimeException(errorMessage);
			}

			 
			User userBean = (User)session.getAttribute("loggedUserBean");
			
			
			 System.out.println("\n loggedUserBean = \n"+userBean);
			
			//call local api update
			//User updatedProfile = updateProfilePic(userBean);
			
			User updatedProfile = updateProfilePic(userBean.getUserId(),profilePic);
			
			LOGGER.info("\n BFC-updatedProfile = \n"+updatedProfile);
			
			System.out.println("\n BFC-updatedProfile = \n"+updatedProfile);
			
			//User userBean = (User)session.getAttribute("loggedUserBean");
			
			 //set base64image also to loggeduserbean
			
			//call local api to set base64image
			updatedProfile = getBase64ImageSetUser(updatedProfile);
			
			
				System.out.println("\n user -uploadProfilePic -\n");
			 
			//update to session, swap userbeans
			session.setAttribute("loggedUserBean", updatedProfile);
			
		    dpStatusStr = "Dp updated succesessionfully  ";
						
			m.addAttribute("successMessage", dpStatusStr);
			
			
			//return "Main";
			
		//call main api
			return toMain(m, session);
		}
		catch (RuntimeException e) 
		{
			e.printStackTrace();
			
			System.out.println("\nBestFrindController-  uploadProfilePic-RuntimeException \n"+e.getLocalizedMessage());
			
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			
			return toMain(m, session);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			LOGGER.error("BestFrindController - uploadProfilePic - Exception ",e);
			
			System.out.println("UserProfileController - uploadProfilePic - Exception "+e.getLocalizedMessage());
			
			m.addAttribute("errorMessage", e.getLocalizedMessage());
			
//			return "Main" ;
			
			return toMain(m, session);
		}
	}
	

	
	//openDeleteProfilePicOption
	@RequestMapping(value = "/openDeleteProfilePicOption",method = RequestMethod.GET)
	public String openDeleteProfilePicOption(Model model, HttpSession session)
	{
		try
		{
              LOGGER.info("UserProfileController - openDeleteProfilePicOption -  ");
			
			System.out.println("UserProfileController - openDeleteProfilePicOption -  ");
			
			//check does he has Dp atleast
			User loggedUserBean = (User) session.getAttribute("loggedUserBean");
			
			if(loggedUserBean.getProfilePic() == null)
			{
				System.out.println("\n UserProfileController - openDeleteProfilePicOption he doset have dp ata all\n");
				
				 LOGGER.info("\n UserProfileController - openDeleteProfilePicOption he doset have dp ata all\n");
				 
				String errorMessage = "Hello "+loggedUserBean.getFirstName()+" I guess u dont have DP at all.. ..";
				
				model.addAttribute("errorMessage",errorMessage);
				
				System.out.println("\n UserProfileController - openDeleteProfilePicOption  calling toMain() internally\n");
				
				 LOGGER.info("\n UserProfileController - openDeleteProfilePicOption  calling toMain() internally\n");
				 
				 
				return toMain(model, session);
			}
			model.addAttribute("isUserLikeToDeleteDP", true);
			
			System.out.println("\n UserProfileController - openDeleteProfilePicOption  calling toMain() internally\n");
			
			 LOGGER.info("\n UserProfileController - openDeleteProfilePicOption  calling toMain() internally\n");
			 
			return toMain(model, session);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
			LOGGER.error("UserProfileController - uploadProfilePic - Exception ",e);
			
			System.out.println("UserProfileController - uploadProfilePic - Exception "+e.getLocalizedMessage());
			
			model.addAttribute("errorMessage", e.getLocalizedMessage());
			
			return "Main" ;
		}
	}
	
	//deleteProfilePic
	@RequestMapping(value = "/deleteProfilePic",method = RequestMethod.GET)
	public String deleteProfilePic(Model model, HttpSession session)
	{
		try 
		{
			User loggedUserBean = (User) session.getAttribute("loggedUserBean");
			
			boolean isDPRemoved = userServiceImpl.removeDP(loggedUserBean.getUserId());
			
			//hs.setAttribute("loggedUserBean", userBeanOriginal);
			
			if(isDPRemoved )
			{
				//yes
				System.out.println("\n HC-deleteProfilePic -isDPRemoved- yes \n");
				model.addAttribute("successMessage", "Ur dp removed successfully .. happy?");
				
				//update back session the user Dp
				loggedUserBean.setProfilePic(null);
				 
				//and base64image too
				loggedUserBean.setBase64Image(null);
				
				//no need to upadte baack user to session bcs session doesnt  depencively copy objects
				//when getAttribute() is called
				//session.setAttribute("loggedUserBean",loggedUserBean);
			}
			else
			{
				//no
				System.out.println("\n HC-deleteProfilePic -isDPRemoved -no \n");
				model.addAttribute("errorMessage", "sorry couldnt remove Ur dp..");
			}
			
			return toMain(model, session);
		} 
		catch (Exception e)
		{
            e.printStackTrace();
			
			LOGGER.error("UserProfileController - uploadProfilePic - Exception ",e);
			
			System.out.println("UserProfileController - uploadProfilePic - Exception "+e.getLocalizedMessage());
			
			model.addAttribute("errorMessage", e.getLocalizedMessage());
			
			return "Main" ;
		}
	}
}//end of class
