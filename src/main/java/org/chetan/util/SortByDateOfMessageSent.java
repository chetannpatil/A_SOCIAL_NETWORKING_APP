package org.chetan.util;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;

import org.chetan.model.Message;

public class SortByDateOfMessageSent implements Comparator<Date>
{
/*
	public static Set<Message> SortByDateOfMessageSent()
	{
		
	}*/

	@Override
	public int compare(Date d1, Date d2) 
	{

		int returnCompInt = 1;
		int year1 = d1.getYear();
		int month1 = d1.getMonth() ;
		int day1 = d1.getDate() ;
		int hour1 = d1.getHours(), minutes1 = d1.getMinutes(), seconds1 = d1.getSeconds() ;	
		
		int year2 = d2.getYear();
		int month2 = d2.getMonth() ;
		int day2 = d2.getDate() ;
		int hour2 = d2.getHours() ,minutes2 = d2.getMinutes() , seconds2 = d2.getSeconds() ;
		if(year1 < year2)
		{
			returnCompInt = -1 ;
		}
		else if (year1 > year2)
		{
			returnCompInt = 1 ;
		}
		else if( year1 == year2 )
		{
			if( month1 < month2 )
			{
				returnCompInt = -1 ;
			}
			else if (month1 > month2 )
			{
				returnCompInt = 1 ;
			}
			else if ( month1 == month2 )
			{
				if( day1 < day2 )
					returnCompInt = -1 ;
				else if ( day1 > day2 )
					returnCompInt = 1 ;
				else if( day1 == day2 )
				{
					if ( hour1 < hour2 )
					{
						returnCompInt = -1 ;
					}
					else if ( hour1 > hour2 )
					{
						returnCompInt = 1 ;
					}
					else if ( hour1 == hour2 )
					{
						if ( minutes1 < minutes2 )
							returnCompInt = -1 ;
						else if ( minutes1 > minutes2 )
							returnCompInt = 1 ;
						else if ( minutes1 == minutes2 )
						{
							if ( seconds1 < seconds2 )
								returnCompInt = -1 ;
							else if ( seconds1 > seconds2 )
								returnCompInt = 1 ;
							else
								returnCompInt = 1 ;
						}
					}
						
				}
		
			}
			
		}
		
		return returnCompInt ;
	}
}
