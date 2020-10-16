package org.chetan.model;

import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;

import org.chetan.util.RemoveExtraSpacesFromALine;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
//select * from User where UPPER(state) like upper('%kar%')
//@NamedNativeQuery(name ="nnq",query="select * from Song where title like ?
//Or duration like  ? or rating like ?",resultClass=Song.class)
//update  "PUBLIC"."USER" set country = 'INDIA' where userid = 14
@Entity
@NamedNativeQueries({@NamedNativeQuery(name ="searchAll",query="select * from User where UPPER(firstName) like ?"
		+ " Or UPPER(lastName) like  ? or UPPER(email) like ? or UPPER(street) like ? or UPPER(city) like ?"
		+ " or UPPER(state) like ? or UPPER(country) like ? or dob like ?",resultClass=User.class),
		@NamedNativeQuery(name = "updateEditedLUSBQuery",query = "Update User set firstName = ?,lastName = ?"
				+ "gender = ?,houseNumber = ?,street = ?,city = ?,zip =?,state = ?, country = ?,dob = ?,"
				+ "moreAboutMe = ?,maritalStatus = ?,")})
//@Table(name = "INDIVIDUAL")
public class User 
{

	
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "individual_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long userId ;
	
	@NotEmpty(message ="First name is mandatory & it will be displayed as profile name")
	private String firstName;
	
	private String lastName;
	
	@NotEmpty(message = "You have to reveal your gender")
	private String gender ;
	
	@NotEmpty(message = "Email id is mandatory & it will be your loggin id")
	@Email
	private String email;
	
	@NotEmpty
	private String password;
	
	//@NotEmpty	
    transient private String repeatPassword;
	
	//@NotEmpty	
    //private String repeatPassword;
	
	private Address address ;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")	
    @Past(message="We can not create a acoount for person who is not yet born")	
	private Date dob ;
	
	private String moreAboutMe ;
	
	
	private String maritalStatus ;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private boolean isActiveMember ;	
	
	//private List<User> friendsList = new ArrayList<User>();
	//@Table(name ="FRIENDS_SET")
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<User> friendsSet = new LinkedHashSet<User>();
	
	@OneToMany(mappedBy="invitationFromUser",fetch=FetchType.EAGER)
    private Set<Invitation> invitationsSet = new LinkedHashSet<Invitation>();

	//wall colection
	@OneToMany(mappedBy="wallFromUser",fetch=FetchType.EAGER)
	private List<Wall> wallsList = new ArrayList<Wall>();

	//message collection
	@OneToMany(mappedBy="messageFromUser",fetch=FetchType.EAGER)
	private List<Message> messagesList = new ArrayList<Message>();
	
	private String qualification ;
	
	private String profession;
	
	
	//DP
//	@Lob
//	@Column(name ="DP")
//	private Blob dp ;	

	@Lob
	private byte [] profilePic ;
	
	private transient String base64Image;
	
	

	
	public String getBase64Image() 
	{
		System.out.println("\n User - getBase64Image -base64image = \n"+this.base64Image);
		return base64Image;
	}

	public void setBase64Image(String image)
	{
		System.out.println("\n User - setBase64Image -passsed base64image = \n"+image);
		
		System.out.println("\n current base64image = \n"+this.base64Image);
		
		this.base64Image = image;
		
		
	}



	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public boolean isActiveMember() 
	{
		System.out.println("User getter of isActiveMemebr");
		return isActiveMember;
	}

	public void setActiveMember(boolean isActiveMember) 
	{
		System.out.println("User setActiveMemebr()");
		this.isActiveMember = isActiveMember;
	}

	public Set<User> getFriendsSet() 
	{
		System.out.println("---User getfrndset()");
		return friendsSet;
	}

	public void setFriendsSet(Set<User> friendsSet) 
	{
		this.friendsSet = friendsSet;
	}

	public Set<Invitation> getInvitationsSet() {
		return invitationsSet;
	}

	public void setInvitationsSet(Set<Invitation> invitationsSet) {
		this.invitationsSet = invitationsSet;
	}

	public List<Wall> getWallsList() {
		return wallsList;
	}

	public void setWallsList(List<Wall> wallsList) {
		this.wallsList = wallsList;
	}

	public List<Message> getMessagesList() {
		return messagesList;
	}

	public void setMessagesList(List<Message> messagesList) {
		this.messagesList = messagesList;
	}

	public User() 
	{
		super();
		// TODO Auto-generated constructor stub
		System.out.println("user no arg constrc");
		//this.isActiveMember = false ;
	}

	

	public User(long userId, String firstName, String lastName, String gender,
			String email, String password, String repeatPassword,
			Address address, Date dob, String moreAboutMe,
			String maritalStatus, boolean isActiveMember, Set<User> friendsSet,
			Set<Invitation> invitationsSet, List<Wall> wallsList,
			List<Message> messagesList, String qualification, String profession) 
	{
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.repeatPassword = repeatPassword;
		this.address = address;
		this.dob = dob;
		this.moreAboutMe = moreAboutMe;
		this.maritalStatus = maritalStatus;
		this.isActiveMember = isActiveMember;
		this.friendsSet = friendsSet;
		this.invitationsSet = invitationsSet;
		this.wallsList = wallsList;
		this.messagesList = messagesList;
		this.qualification = qualification;
		this.profession = profession;
	}

	




	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", email=" + email + ", password=" + password + ", address=" + address + ", dob=" + dob
				+ ", moreAboutMe=" + moreAboutMe + ", maritalStatus=" + maritalStatus + ", isActiveMember="
				+ isActiveMember + ", qualification=" + qualification + ", profession=" + profession + ", profilePic="
				+ Arrays.toString(profilePic) + ", base64Image=" + base64Image + "]";
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}



	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId) 
	{
		this.userId = userId;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		firstName = RemoveExtraSpacesFromALine.removeExtraSpace(firstName);
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		lastName = RemoveExtraSpacesFromALine.removeExtraSpace(lastName) ;
		this.lastName = lastName;
	}

	public String getGender() 
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		email = RemoveExtraSpacesFromALine.removeExtraSpace(email);
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password) 
	{
		password = RemoveExtraSpacesFromALine.removeExtraSpace(password) ;
		this.password = password;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{		
		this.address = address;
	}

	public Date getDob() 
	{
		//Date dt = new Date(this.dob.getTime());
		//diffensive copying dosnt work here
		return this.dob;
	}

	public void setDob(Date d)
	{
		//copying deiffensively
		//this.dob = new Date(d.getTime());
		//diffensive worjks partuially
		this.dob = d ;
	}

	public String getMoreAboutMe() 
	{
		return moreAboutMe;
	}

	public void setMoreAboutMe(String moreAboutMe)
	{
		moreAboutMe = RemoveExtraSpacesFromALine.removeExtraSpace(moreAboutMe) ;
		this.moreAboutMe = moreAboutMe;
	}

	public String getMaritalStatus() 
	{
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) 
	{
		this.maritalStatus = maritalStatus;
	}
	
	public static boolean isPasswordMatchingRepeatPassword(String psw, String repPsw)
	{
		System.out.println("UserBean static passwd validation()");
		if(psw.trim().length() != repPsw.trim().length())
			return false ;
		if(psw.trim().equals(repPsw.trim()) == false)
			return false ;
		else
			return true ;
	}
	
	public static boolean isValidDob(Date dob) throws ParseException
	{
		int dateInt = dob.getDate() ;
		int monthInt = dob.getMonth() ;
		int yearInt = dob.getYear() ;
		
		String dobStr = dateInt+"/"+monthInt+"/"+yearInt;
		validateDOB(dobStr);
		return true ;
	}
	
	 //validate dOb of contact
    private static void validateDOB(String dobStr) throws ParseException
    {
    	if(dobStr == null)
    		  throw new IllegalArgumentException("DOB OF CONTACT CAN NOT BE NULL");
    	if(dobStr.trim().length() == 0)
    		  throw new IllegalArgumentException("DOB OF CONTACT CAN NOT BE EMPTY/BLANK");
    	if((dobStr.trim().length() != 10) || (dobStr.contains("/") == false))
    		  throw new IllegalArgumentException("INVALID DOB FORMAT, PLEASE ENTER DOB IN DD/MM/YYYY FORMAT");
    	
    	String [] saDob = dobStr.split("/");
    	if(saDob.length != 3)
    		throw new IllegalArgumentException("INVALID DOB FORMAT, PLEASE ENTER DOB IN DD/MM/YYYY FORMAT");
    	String ddStr = saDob[0];
    	String mmStr = saDob[1];
    	String yyyyStr = saDob[2];
    	if((ddStr.trim().length() != 2) ||(mmStr.trim().length() != 2) || (yyyyStr.trim().length() != 4) )
    		throw new IllegalArgumentException("INVALID DOB FORMAT, PLEASE ENTER DOB IN DD/MM/YYYY FORMAT");
    	StringBuilder sb = new StringBuilder(dobStr.trim());
    	for(int i=0;i<sb.length() ;i++)
    	{
    		char chValid = sb.charAt(i);
    		if(  (chValid == 47)  || (chValid >= 48 && chValid <= 57)  )
    		{
    			//acceptable
    		}
    		else
    			throw new IllegalArgumentException("INVALID DOB FORMAT, PLEASE ENTER DOB IN DD/MM/YYYY FORMAT");
    	}
    	if( Integer.parseInt(ddStr) >=1 && Integer.parseInt(ddStr) <= 31 )
    			{
    		//acceptable
    			}
    	else
    		throw new IllegalArgumentException(ddStr+" CAN NOT BE A DAY");
    	if(Integer.parseInt(mmStr) >= 1 && Integer.parseInt(mmStr) <= 12)
    	{
    		//acceptable
    	}
    	else
    		throw new IllegalArgumentException(mmStr+" CAN NOT BE A MONTH");
    	if(Integer.parseInt(yyyyStr) == 0)
    		throw new IllegalArgumentException(yyyyStr+" CAN NOT BE A YEAR");
    	Date currentDate = new Date();
    	Date compDate = sdf.parse(dobStr);
  	  if((currentDate.getTime() - compDate.getTime()) < 0)
  		  throw new IllegalArgumentException("CAN NOT ADD CONTACT OF PERSON WHO IS NOT YET BORN");
  	  validateForCorrectDayOfMonth(yyyyStr,mmStr, ddStr);
  	  
    		
    }
    
    //validate for right day of month
    private static void validateForCorrectDayOfMonth(String yyyyStr ,String mmStr,String ddStr)
    {
    	int mmInt = Integer.parseInt(mmStr);
    	int ddInt = Integer.parseInt(ddStr);
    	int yyyyInt = Integer.parseInt(yyyyStr);
    	
    	switch (mmInt)
    	{
		case 1:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"JANUARY");
						
			break;

		case 2:
			if( (yyyyInt % 4) == 0)
			{
					if(ddInt >= 1 && ddInt <= 29)
				{
					//acceptable
				}
				else
					throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"FEBRUARY");
			}
			else
			{
				if(ddInt >= 1 && ddInt <= 28)
				{
					//acceptable
				}
				else if(ddInt == 29)
					throw new IllegalArgumentException("29th OF FEB COMES IN ONLY LEAP YEARS");
				else
					throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"FEBRUARY");
			}
			
						
			break;
			
		case 3:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"MARCH");
						
			break;
			
		case 4:
			if(ddInt >= 1 && ddInt <= 30)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"APRIL");
						
			break;
			
		case 5:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"MAY");
						
			break;
			
		case 6:
			if(ddInt >= 1 && ddInt <= 30)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"JUNE");
						
			break;
			
		case 7:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"JULY");
						
			break;
			
		case 8:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"AUGUST");
						
			break;
			
		case 9:
			if(ddInt >= 1 && ddInt <= 30)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"SEPTEMBER");
						
			break;
			
		case 10:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"OCTOBER");
						
			break;
			
		case 11:
			if(ddInt >= 1 && ddInt <= 30)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"NOVEMBER");
						
			break;
		case 12:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"DECEMBER");
						
			break;
		default:throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+mmStr);
		
		}
    }
}
