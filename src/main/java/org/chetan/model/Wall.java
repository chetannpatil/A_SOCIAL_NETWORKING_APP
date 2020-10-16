package org.chetan.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.chetan.util.RemoveExtraSpacesFromALine;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
//@Table(name = "WALL")
public class Wall
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long wallId;
	
	@ManyToOne
	private User wallFromUser ;
	
	@ManyToOne
	private User wallToUser ;
	
	@NotEmpty
	private String wallMessage;
	
	Date dateOfWallCreated;
    
	private long totalWallLikes ;
	
	private long totalWallHates;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "WALL_LIKEDUSERS")
	private Set<User> likedUsersSet;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "WALL_HATEDUSERS")
	private Set<User> hatedUsersSet ;
	
	
	
	public Set<User> getLikedUsersSet() 
	{
		return likedUsersSet;
	}



	public void setLikedUsersSet(Set<User> likedUsersSet) 
	{
		this.likedUsersSet = likedUsersSet;
	}



	public Set<User> getHatedUsersSet() {
		return hatedUsersSet;
	}



	public void setHatedUsersSet(Set<User> hatedUsersSet) {
		this.hatedUsersSet = hatedUsersSet;
	}



	public Wall()
	{
		super();
		System.out.println("---wall no arg constrctr()");
	}

	

	public long getWallId() {
		return wallId;
	}

	public void setWallId(long wallId) {
		this.wallId = wallId;
	}

	public User getWallFromUser() {
		return wallFromUser;
	}

	public void setWallFromUser(User wallFromUser) {
		this.wallFromUser = wallFromUser;
	}

	public User getWallToUser() {
		return wallToUser;
	}

	public void setWallToUser(User wallToUser) {
		this.wallToUser = wallToUser;
	}

	public String getWallMessage() {
		return wallMessage;
	}

	public void setWallMessage(String wallMessage)
	{
		wallMessage = RemoveExtraSpacesFromALine.removeExtraSpace(wallMessage);
		this.wallMessage = wallMessage;
	}

	public Date getDateOfWallCreated() {
		return dateOfWallCreated;
	}

	public void setDateOfWallCreated(Date dateOfWallCreated) {
		this.dateOfWallCreated = dateOfWallCreated;
	}



	public long getTotalWallLikes() {
		return totalWallLikes;
	}



	public void setTotalWallLikes(long totalWallLikes) {
		this.totalWallLikes = totalWallLikes;
	}



	public long getTotalWallHates() {
		return totalWallHates;
	}



	public void setTotalWallHates(long totalWallHates) {
		this.totalWallHates = totalWallHates;
	}



	@Override
	public String toString() {
		return "Wall [wallId=" + wallId + ", wallFromUser=" + wallFromUser
				+ ", wallToUser=" + wallToUser + ", wallMessage=" + wallMessage
				+ ", dateOfWallCreated=" + dateOfWallCreated
				+ ", totalWallLikes=" + totalWallLikes + ", totalWallHates="
				+ totalWallHates + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((dateOfWallCreated == null) ? 0 : dateOfWallCreated
						.hashCode());
		result = prime * result
				+ (int) (totalWallHates ^ (totalWallHates >>> 32));
		result = prime * result
				+ (int) (totalWallLikes ^ (totalWallLikes >>> 32));
		result = prime * result
				+ ((wallFromUser == null) ? 0 : wallFromUser.hashCode());
		result = prime * result + (int) (wallId ^ (wallId >>> 32));
		result = prime * result
				+ ((wallMessage == null) ? 0 : wallMessage.hashCode());
		result = prime * result
				+ ((wallToUser == null) ? 0 : wallToUser.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wall other = (Wall) obj;
		if (dateOfWallCreated == null) {
			if (other.dateOfWallCreated != null)
				return false;
		} else if (!dateOfWallCreated.equals(other.dateOfWallCreated))
			return false;
		if (totalWallHates != other.totalWallHates)
			return false;
		if (totalWallLikes != other.totalWallLikes)
			return false;
		if (wallFromUser == null) {
			if (other.wallFromUser != null)
				return false;
		} else if (!wallFromUser.equals(other.wallFromUser))
			return false;
		if (wallId != other.wallId)
			return false;
		if (wallMessage == null) {
			if (other.wallMessage != null)
				return false;
		} else if (!wallMessage.equals(other.wallMessage))
			return false;
		if (wallToUser == null) {
			if (other.wallToUser != null)
				return false;
		} else if (!wallToUser.equals(other.wallToUser))
			return false;
		return true;
	}

	
	

		
}
