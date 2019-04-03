package com.qa.rest.test;

import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidatingGetResponse {

	@Test
	public void validateResponse() {
		
		//Base URI
		RestAssured.baseURI = "http://ergast.com/";
		
		given().
				/*param("location", "-33.8670522,151.1957362").
				param("radius","500").
				param("key","qaclick123").*/
				
		when().
				get("/api/f1/2017/circuits.json").
		
		then().
				assertThat().
				
				statusCode(200).and().
				
				contentType("application/json").and(). // or contentType(ContentType.JSON)
				
				body("MRData.CircuitTable.Circuits[0].circuitName",equalTo("Albert Park Grand Prix Circuit")).and().
				
				header("server", "Apache/2.2.15 (CentOS)");
	
				
		Response response = RestAssured.get("/api/f1/2017/circuits.json");
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Assert.assertEquals(response.header("content-type"), "application/json; charset=utf-8");
		
		Assert.assertEquals(response.jsonPath().get("MRData.CircuitTable.Circuits[0].circuitName"), "Albert Park Grand Prix Circuit");
		
		Assert.assertEquals(response.header("server"), "Apache/2.2.15 (CentOS)");
		
		
		
		
				
		
		

	}	

}
