package org.chetan.util;

public class RemoveExtraSpacesFromALine
{

	//remove extra space from sentence
			public static String removeExtraSpace(String remvExSpcStr)
			{
			 if( remvExSpcStr == null || remvExSpcStr.trim().length() == 0 )
				 return remvExSpcStr ;
				// throw new IllegalArgumentException("CAN NOT REMOVE EXTRA SPACE FROM NULL/BLANK/EMPTY STRING");
			 StringBuilder sb = new StringBuilder();
			 String [] saToBeEdoted = remvExSpcStr.trim().split(" ");
	          for(int i=0 ; i<saToBeEdoted.length ;i++)
			 {
	        	  if(saToBeEdoted[i].trim().length() != 0 )
	        	  {
	        		  sb.append(saToBeEdoted[i].trim());
						 sb.append(" ");
	        	  }	
			
			 }
			 
			 return sb.toString().trim() ;
			}
}
