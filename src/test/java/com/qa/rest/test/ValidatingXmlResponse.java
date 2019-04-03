package com.qa.rest.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.Test;

import com.udemy.files.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ValidatingXmlResponse {
	
	Properties prop = new Properties();
	
	@Test
	public void grabbingRespose() throws IOException {
		
		//Task 1: Grab the response
		String postData= generateStringFromResource("/Users/veena/Documents/workspace_git/rest-API---automation/src/test/resources/Data.xml");
		RestAssured.baseURI = "http://216.10.245.166";
		
		Response res = given(). // Response is extracted in variable res
		queryParam("key", prop.getProperty("KEY")).
		body(postData).

		
		when().
		post("/maps/api/place/add/xml").
		
		then().
		
		assertThat().
		
		statusCode(200).and().
		
		contentType("application/xml").and().
		extract().response();
		XmlPath x = ReusableMethods.rawToXml(res);
		String placeID = x.get("response.place_id");
		System.out.println(placeID);
		
		
	
		
	}
	
	public static String generateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
