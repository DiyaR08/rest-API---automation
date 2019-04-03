package com.udemy.files;



import org.testng.annotations.Test;


import com.udemy.jira.HelperMethod;
import com.udemy.jira.Resources;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Payload {
	static Response res = null;
	
	//Body of request goes to payload
	
	//Add Place
	public static String addPlace() {
		String b = "{\n" + 
				"    \"location\":{\n" + 
				"        \"lat\" : -38.383494,\n" + 
				"        \"lng\" : 33.427362\n" + 
				"    },\n" + 
				"    \"accuracy\":50,\n" + 
				"    \"name\":\"Frontline house\",\n" + 
				"    \"phone_number\":\"(+91) 983 893 3937\",\n" + 
				"    \"address\" : \"29, side layout, cohen 09\",\n" + 
				"    \"types\": [\"shoe park\",\"shop\"],\n" + 
				"    \"website\" : \"http://google.com\",\n" + 
				"    \"language\" : \"French-IN\"\n" + 
				"}\n" + 
				"";
		
		return b;
	}
	
	
	public static String getPlaceId() {
		String placeID = res.jsonPath().get("\"place_id\"");
		return null;
		
	}
	public static String deletePlace() {
		String b1 =
		"{"+
		   "\"place_id\": \""+getPlaceId()+"\""+
		"}";
		
		return b1;
		
	}
	

	//Add Book
	public static String addBook(String aisle, String isbn) {
		String b2= "{\n" + 
				" \n" + 
				"\"name\":\"Learn Appium Automation with Java\",\n" + 
				"\"isbn\":\""+aisle+"\",\n" + 
				"\"aisle\":\""+isbn+"\",\n" + 
				"\"author\":\"John foe\"\n" + 
				"}";
		return b2;
		
	}
	
	public static String deleteBook(String id) {
		String b3 = "{\n" + 
				" \n" + 
				"\"ID\" : \""+id+"\"\n" + 
				" \n" + 
				"}";
		return b3;
	}
	

}
