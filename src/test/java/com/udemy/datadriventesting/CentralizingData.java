package com.udemy.datadriventesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.udemy.files.Resources;
import com.udemy.files.Payload;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CentralizingData {
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		 
		//File f = new File(System.getProperty("/Users/veena/Documents/workspace_git/rest-API---automation/src/test/resources/config.properties"));
		FileInputStream fis = new FileInputStream("/Users/veena/Documents/workspace_git/rest-API---automation/src/test/resources/config.properties");
		prop.load(fis);
	}
	
	@Test
	public void grabResponse() {
		
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response res = given().
		queryParam("key", prop.getProperty("KEY")).
		body(Payload.addPlace()).
		
		
		when().
		post(Resources.addPlace()).
		
		then().
		
		assertThat().
		
		statusCode(200).and().
		
		contentType("application/json;charset=UTF-8").and().
		body("status",equalTo("OK")).
		extract().response();
		System.out.println(res);
		
		//Response is in raw form,  extract in variable res and convert to string type 
		
		String responseString = res.asString(); 
		System.out.println(responseString);
		
		
		//Task 2: Grab the place ID from the response
		
		JsonPath js = new JsonPath(responseString);//Convert string to Jsonpath to grab the id in json format
		String placeID= js.get("place_id");
		System.out.println("Place ID: "+ placeID);
		
		//Task 3: Place this place Id in delete request
		
		/*given().
		queryParam("key", prop.getProperty("KEY")).
		body(Payload.deleteData()).
		

		when().
		post(Resources.deletePlace()).
		
		then().
		
		assertThat().
		
		statusCode(200).and().
		
		body("status", equalTo("OK"));*/
		

		
	}

}
