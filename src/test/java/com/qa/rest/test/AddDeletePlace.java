package com.qa.rest.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

public class AddDeletePlace {
	
	@Test
	public void grabbingRespose() {
		
		
		//Task 1: Grab the response
		RestAssured.baseURI = "http://216.10.245.166";
		
		String b = "{\n" + 
				"    \"location\":{\n" + 
				"        \"lat\" : -38.383494,\n" + 
				"        \"lng\" : 33.427362\n" + 
				"    },\n" + 
				"    \"accuracy\":50,\n" + 
				"    \"name\":\"Frontline house\",\n" + 
				"    \"phone_number\":\"(+91) 983 893 3937\",\n" + 
				"    \"address\" : \"29, side layout, cohen 09\",\n" + 
				"    \"types\": [\"shoe park\",\"shop\"],\n" + 
				"    \"website\" : \"http://google.com\",\n" + 
				"    \"language\" : \"French-IN\"\n" + 
				"}\n" + 
				"";
		
		Response res = given(). // Response is extracted in variable res
		queryParam("key", "qaclick123").
		body(b).
		
		when().
		post("/maps/api/place/add/json").
		
		then().
		
		assertThat().
		
		statusCode(200).and().
		
		header("content-type","application/json;charset=UTF-8").and().
		
		//contentType("application/json;charset=UTF-8").and().
		
		body("status",equalTo("OK")).
		extract().response();
		
		//Response is in raw form,  extract in variable res and convert to string type 
		
		//String responseString = res.asString(); 
		//System.out.println(responseString);
		
		
		//Task 2: Grab the place ID from the response
		
		//JsonPath js = new JsonPath(responseString);//To convert string to Jsonpath to grab the id in json format
		//String placeID= js.get("place_id");
		//System.out.println("Place ID: "+ placeID);
		String placeID = res.jsonPath().get("place_id");
		
		String deleteBody = "{" +
				   "\"place_id\": \""+placeID+"\"" +
				"}";
		
		System.out.println(deleteBody);
		
		Response deleteResponse = RestAssured.given()
			.body(deleteBody)
			.post("/maps/api/place/delete/json");
		
		AssertJUnit.assertEquals(deleteResponse.statusCode(), 200);
		System.out.print(deleteResponse.getBody().asString());

		
		//Task 3: Place this place Id in delete request
		
		/*given().
		queryParam("key", "qaclick123").
		body().
		

		when().
		post("/maps/api/place/delete/json").
		
		then().
		
		assertThat().
		
		statusCode(200).and().
		
		body("status", equalTo("OK"));
		
		
*/		
		

		
		
		
	}

}
