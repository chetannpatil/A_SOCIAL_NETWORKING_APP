package org.chetan.model;

import javax.persistence.Embeddable;

import org.chetan.util.RemoveExtraSpacesFromALine;

@Embeddable
public class Address 
{
	private String houseNumber ;
	private String street ;
	private String city;
	private String zip ;
	private String state ;
	private String country ;
	
	public Address()
	{
		super();
		// TODO Auto-generated constructor stub
		System.out.println("adress no arg cnstr");
	}
	

	public Address(String houseNumber, String street, String city, String zip,
			String state, String country) 
	{
		super();
		System.out.println("adress param cnstr");
		this.houseNumber = houseNumber;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.country = country;
	}


	public String getStreet() 
	{
		return street;
	}


	public void setStreet(String street)
	{
		street = RemoveExtraSpacesFromALine.removeExtraSpace(street) ;
		this.street = street;
	}


	public String getHouseNumber()
	{
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber)
	{
		houseNumber = RemoveExtraSpacesFromALine.removeExtraSpace(houseNumber) ;
		this.houseNumber = houseNumber;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		city = RemoveExtraSpacesFromALine.removeExtraSpace(city) ;
		this.city = city;
	}
	public String getZip() 
	{
		return zip;
	}
	public void setZip(String zip) 
	{
		zip = RemoveExtraSpacesFromALine.removeExtraSpace(zip) ;
		this.zip = zip;
	}
	public String getState() 
	{
		return state;
	}
	public void setState(String state) 
	{
		state = RemoveExtraSpacesFromALine.removeExtraSpace(state) ;
		this.state = state;
	}
	public String getCountry()
	{
		return country;
	}
	public void setCountry(String country) 
	{
		country = RemoveExtraSpacesFromALine.removeExtraSpace(country) ;
		this.country = country;
	}
	
	@Override
	public String toString() 
	{
		return "Address [houseNumber=" + houseNumber + ", street=" + street
				+ ", city=" + city + ", zip=" + zip + ", state=" + state
				+ ", country=" + country + "]";
	}
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((houseNumber == null) ? 0 : houseNumber.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (houseNumber == null) {
			if (other.houseNumber != null)
				return false;
		} else if (!houseNumber.equals(other.houseNumber))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}
	
	
	
}
