package com.udemy.files;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.udemy.jira.Payload;
import com.udemy.jira.Resources;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
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
	
	@Test
	public void getSessionValue() {
	
		Response res = given().
			header("Content-Type","application/json").
			body(Payload.sessionValue()).
		
		when().
			post(Resources.createSessionId()).
		
		then().
			assertThat().
			statusCode(200).
			
		extract().response();
		JsonPath js = ReusableMethods.rawToJson(res);
		String sessionValue = js.get("session.value");
		System.out.println(sessionValue);	
	}

}
