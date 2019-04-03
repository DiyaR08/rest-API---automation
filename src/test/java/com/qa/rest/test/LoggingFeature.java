package com.qa.rest.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoggingFeature {
	
	@Test
	public static void extractValues() {
		RestAssured.baseURI = "http://ergast.com/";
		
		given().log().all().
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
				
				header("server", "Apache/2.2.15 (CentOS)").log().body();
	
	}
	
}
	
