package Basics;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import POJO.AddPlace;
import POJO.Location;

public class serializeTest {
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
		
		Response res= given().log().all()
		.queryParam("key","qaclick123").body(ap)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		String resString=res.asString();
		System.out.println(resString);
	}
}
