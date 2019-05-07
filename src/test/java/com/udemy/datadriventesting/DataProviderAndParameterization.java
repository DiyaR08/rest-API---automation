package com.udemy.datadriventesting;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;

import com.udemy.files.Payload;
import com.udemy.files.Resources;
import com.udemy.files.ReusableMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
	
public class DataProviderAndParameterization {
	
	@Test(dataProvider="getData")
	public void addBook(String isbn, String aisle){
		
		Response res = given().
		body(Payload.addBook(isbn, aisle)).
		
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
	
	@Test(dataProvider= "getData")
	public void deleteBook(String ID) {
		given().
		body(Payload.deleteBook(ID)).
		
		when().
		post(Resources.deleteBook()).
		
		then().
		
		assertThat().
		
		statusCode(200);
	}
	
	@DataProvider
	public Object[][] getData() {
		
		/*Object[][]data = new Object[3][2];
		data[0][0]= "abcd";
		data[0][1]= "1234";
		
		data[1][0]= "efgh";
		data[1][1]= "5678";
		
		data[2][0]= "ijkl";
		data[2][1]= "9012";
		return data;*/
		
		return new Object [][] {{"abcd","1234"},{"efgh", "5678"},{"yuik","0987"}};
	}

}
