package com.udemy.jira;

import java.io.BufferedReader;
import java.io.FileReader;

public class Payload {
	
	public static String sessionValue() {
		String userCredentials = "{ \"username\": \"diyarao08\", \"password\": \"squirrel@44006600\" }";
		return userCredentials;
	}
	
	public static String createIssue() {
		String bug = "{  \n" + 
				"   \"fields\":{  \n" + 
				"      \"project\":{  \n" + 
				"         \"key\":\"AT\"\n" + 
				"      },\n" + 
				"      \"summary\":\"Automation Testing Jira API\",\n" + 
				"      \"description\":\"Creating bug\",\n" + 
				"      \"issuetype\":{  \n" + 
				"         \"name\":\"Bug\"\n" + 
				"      }\n" + 
				"   }\n" + 
				"}";
		return bug;		
	}	
	
	public static String addComment() {
		String comment = "{\n" + 
				"    \"body\": \"Adding comment using RestAssured\",\n" + 
				"    \"visibility\": {\n" + 
				"        \"type\": \"role\",\n" + 
				"        \"value\": \"Administrators\"\n" + 
				"    }\n" + 
				"}";
		return comment;
	}
	
	
	
		
/*	 	 //Read a text file
	 	 String line; //To store the data
	 	 FileReader fileR1 = new FileReader(System.getProperty("user.dir")+"/src/");// to open the text file in read mode
	 	 BufferedReader br = new BufferedReader(fileR1);// To read the data
	 	 
	 	 while((line = br.readLine())!=null) {
	 		 System.out.println(line);
	 		 
	 	 }
	 	 br.close(); // close the object to release the memory
	 	 fileR1.close();*/
	 	 
	

}
