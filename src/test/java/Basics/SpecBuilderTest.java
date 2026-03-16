package Basics;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import POJO.AddPlace;
import POJO.Location;

public class SpecBuilderTest {
	@Test
	public void SerializeTest() {
		
		AddPlace ap= new AddPlace();
		ap.setAccuracy(50);
		ap.setName("Frontline house");
		ap.setPhoneNumber("(+91) 983 893 3937");
		ap.setAddress("29, side layout, cohen 09");
		ap.setWebsite("http://google.com");
		ap.setLanguage("French-IN");
		List<String> al = new ArrayList<>(Arrays.asList("shoe park","shop"));
		ap.setTypes(al);
		Location l= new Location();
		l.setLat( -38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		
		RequestSpecification res= given().spec(req)
				.body(ap);
		
		
				Response response=res.when().post("/maps/api/place/add/json")
				.then().spec(resSpec).extract().response();
		
//		Response res= given().log().all()
//		.queryParam("key","qaclick123").body(ap)
//		.when().post("/maps/api/place/add/json")
//		.then().assertThat().statusCode(200).extract().response();
//		
		String resString=response.asString();
		System.out.println(resString);
	}
}
