package com.qa.rest.test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.udemy.files.ReusableMethods;

public class ParsingJSonToExtractValues {
	
	@Test
	public static void extractValues() {
		RestAssured.baseURI = "http://ergast.com";
		
		Response res = given().
		
		when().
		get("/api/f1/2017/circuits.json").
		
		then().
		
		assertThat().

		statusCode(200).and().
		body("MRData.CircuitTable.Circuits[0].circuitName", equalTo("Albert Park Grand Prix Circuit")).and().
		
		extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		int circuitCount = js.get("MRData.CircuitTable.Circuits.size()");
		System.out.println(circuitCount);
		
		for(int i=0;i<circuitCount;i++) {
			String result = js.get("MRData.CircuitTable.Circuits["+i+"].circuitName");//Add '+' and " " to treat it as vairable not text 
			System.out.println(result);
			
		}
		
		
	}

}
