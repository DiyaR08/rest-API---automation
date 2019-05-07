package com.udemy.jira;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class EndToEndTesting {

	Properties prop = new Properties();
	FileInputStream fis;
	String sessionValue = HelperMethod.getSessionValue();
	String issueId = HelperMethod.createIssue();
	
	@BeforeMethod
	@BeforeTest
	public void setUp() throws IOException {
		fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop.load(fis);
		RestAssured.baseURI = prop.getProperty("JIRA_HOST");
	}
	
	@Test
	public void addComment() {
		
		String payload = Payload.addComment();
		
		given().header("Content-Type", "application/json")
		.header("Cookie", "JSESSIONID=" + sessionValue).body(payload).when()
		.post(Resources.addComment(issueId)).then().assertThat().statusCode(201);
	}

	@Test
	public void deleteIssue() {
	
		String endpoint = Resources.deleteIssue(issueId);
	
		System.out.println(endpoint);
	
		given().
		header("Content-Type","application/json").
		header("Cookie","JSESSIONID="+HelperMethod.getSessionValue()).
		when().
		delete(endpoint).
		then().
		assertThat().statusCode(204);
	}
}
