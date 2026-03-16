package Basics;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import Files.ReusableMethods;
import Files.payload;

public class Basics {
	public static void main(String[] args) throws IOException {
	//validate if add place is working as expected
	
	//given all input details
	//when - submit the API-source , http method
	//then - validate the response
	
	RestAssured.baseURI="https://rahulshettyacademy.com";
	String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\KC\\eclipse-workspace\\DemoAPI\\src\\test\\java\\Files\\addPlace.json"))))
	.when().post("maps/api/place/add/json")
	.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
	.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
	System.out.println(response);
	JsonPath js=new JsonPath(response); //for parsing Json
	String placeId=js.getString("place_id");
	
	//update place
	String newaddress="Tundurru,Bhimavaram,AP";
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body("{\r\n"
			+ "\"place_id\":\""+placeId+"\",\r\n"
			+ "\"address\":\""+newaddress+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}").when().put("maps/api/place/update/json")
	.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	//Get place 
	
	String responseUpdate=given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId)
	.when().get("maps/api/place/get/json")
	.then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath jp=ReusableMethods.rawToJson(responseUpdate);
	String actualAddress=jp.getString("address");
	
	Assert.assertEquals(actualAddress,newaddress);
	System.out.println(actualAddress);
	
	System.out.println("Test Completed Successfully");
}
}