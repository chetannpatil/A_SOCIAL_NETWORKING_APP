package org.chetan.util;

import java.util.ArrayList;
import java.util.List;

public class DirtyCheck 
{

	static String [] dirtySA = {"ass","anal","asshole","boob","boobs","buts","bum","breast","basterd","dick","fuck"
		,"loafer","pennis","pussy","rascal","sex","sexy","scoundrel","vegina"};
	static List<String> dirtyList = new ArrayList<String>();
	
	static 
	{
		for(String dirtyStr :dirtySA)
		{
			dirtyList.add(dirtyStr);
		}
	}
	public static boolean isMessageDirty(String messageStr)
	{
		if(messageStr == null || messageStr.trim().length() == 0)
			return true ;
		messageStr = RemoveExtraSpacesFromALine.removeExtraSpace(messageStr).toLowerCase();
		System.out.println("////////////////////////////////////////////////////");
		System.out.println("msgstr = "+messageStr);
		//split as sa
		String [] sa = messageStr.split(" ");
		//loop dirtySa & check any of dirty word exist in passd str
		
		for(String dirtyCheckStr :sa)
		{
			if(dirtyList.contains(dirtyCheckStr) == true )
			{
				System.out.println("////////////////////////////////////////////////////");
				System.out.println("drty word = "+dirtyCheckStr);
				return true ;
			}
			/*if(messageStr.contains(dirtyStr) == true )
			{
				System.out.println("////////////////////////////////////////////////////");
				System.out.println("drty word = "+dirtyStr);
				return true ;
			}*/
				
		}
		//if for loop never brake return its as a good message
		return false ;
	}
}
