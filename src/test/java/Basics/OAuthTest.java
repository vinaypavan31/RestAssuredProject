package Basics;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import POJO.Api;
import POJO.GetCourse;
import POJO.WebAutomation;

public class OAuthTest {
	
	@Test
	public void OAuthTest() {
		
		String[] courseTitles= {"Selenium Webdriver Java","Cypress", "Protractor"};
		
		String response= given()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant-type","client_credentials")
		.formParam("scope","trust")
		.log().all()
		.when()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("**********************************************************");
		System.out.println(response);
		JsonPath jp=new JsonPath(response);
		String aToken=jp.getString("access_token");
		
		
		GetCourse gc= given().queryParam("access_token",aToken)
		.when()
		.log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		
		
		System.out.println("**********************************************************");
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println( gc.getCourses().getApi().get(1).getCourseTitle());
		
		List<Api> apiCourses=gc.getCourses().getApi();
		
		for(int i=0;i<apiCourses.size();i++) {
			if(apiCourses.get(i).getCourseTitle().equals("SoapUI Webservices testing")) {
				System.out.println( apiCourses.get(i).getPrice());
			}
		}
		
		List<WebAutomation> webCourses=gc.getCourses().getWebAutomation();
		List<String> li=new ArrayList<>();
		for(int i=0;i<webCourses.size();i++) {
			li.add(webCourses.get(i).getCourseTitle());
		}
		
		List<String> exp=Arrays.asList(courseTitles);
		Assert.assertTrue(li.equals(exp));
		
		
		
	}

}
