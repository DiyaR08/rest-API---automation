package com.udemy.jira;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class HelperMethod {
	
	public static XmlPath rawToXml(Response res) {
		String responses = res.asString();
		XmlPath x = new XmlPath(responses);
		return x;
	}
	
	public static JsonPath rawToJson(Response res) {
		String responses = res.asString();
		System.out.println(responses);
		JsonPath js = new JsonPath(responses);
		return js;
	}
	
	public static String getSessionValue() {
	
		Response res = given().
			header("Content-Type","application/json").
			body(Payload.sessionValue()).
		
		when().
			post(Resources.createSessionId()).
		
		then().
			assertThat().
			statusCode(200).
			
		extract().response();
		JsonPath js = HelperMethod.rawToJson(res);
		String sessionValue = js.get("session.value");
		return sessionValue;
	}
	
	public static String createIssue() {
		Response res = given().
			header("Content-Type","application/json").
			header("Cookie","JSESSIONID="+HelperMethod.getSessionValue()).
			body(Payload.createIssue()).
		when().
		 	post(Resources.createIssue()).
		then().
			assertThat(). statusCode(201).
		extract().response();
		JsonPath js = HelperMethod.rawToJson(res);
		String id = js.get("id");
		String key = js.get("key");
		return key;
	}

}
