package com.udemy.datadriventesting;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.udemy.files.Payload;
import com.udemy.files.Resources;
import com.udemy.files.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DynamicJson {
	
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		System.out.println(System.getProperty("user.dir"));
		prop.load(fis);
	}
	
	@BeforeMethod
	public void hostUrl() {
		RestAssured.baseURI = prop.getProperty("HOST");
	}
	
	@Test
	public void addBook(){
		
		Response res = given().
		body(Payload.addBook("ghijkl", "4400")).
		
		when().
		post(Resources.addBook()).
		
		then().
		
		assertThat().
		
		statusCode(200).and().
		
		extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		
		String bookID = js.get("ID");
		
		System.out.println("ID: "+bookID);
		
	}
	
	@Test
	public void deleteBook() {
		
		given().
		body(Payload.deleteBook("ghijkl4400")).
		
		when().
		post(Resources.deleteBook()).
		
		then().
		
		assertThat().
		
		statusCode(200);
		
		
		
		
	}

}
 		