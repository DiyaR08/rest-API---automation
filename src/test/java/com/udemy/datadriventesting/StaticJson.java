package com.udemy.datadriventesting;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.udemy.files.Payload;
import com.udemy.files.Resources;
import com.udemy.files.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class StaticJson {
	
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
	
	/*@Test
	public void addBook() throws IOException{
		
		Response res = given().
		body(generateStringFromResource(System.getProperty("user.dir")+"/src/test/resources/addBookBody.json")).
		
		when().
		post(Resources.addBook()).
		
		then().
		
		assertThat().
		
		statusCode(200).and().
		
		extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		
		String bookID = js.get("ID");
		
		System.out.println("ID: "+bookID);
		
	}*/
	
	public static String generateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	

}
