package OAuth2;

import io.restassured.*;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OAuth2Test {

	public static void main(String[] args) throws InterruptedException {

//		WebDriver driver = new EdgeDriver();
//		String url = "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php";
//		driver.get(url);
//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("vinaypavan14264@gmail.com");
//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
//		Thread.sleep(4000);
//
//		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Vijayawada@4");
//		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
//		Thread.sleep(4000);

	String code_url = "https://rahulshettyacademy.com/getCourse.php?iss=https%3A%2F%2Faccounts.google.com&code=4%2F0AfrIepClkAe3oohKXVUKJgwNLhYRFEVWpCbHp6cwvaJ3F21r1zwvAIY-l0ZD8SVq3nFsHA&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
			String partialcode = code_url.split("code=")[1];
		String code = partialcode.split("&scope")[0];
		
		
		
		String access_Token_Response = given().urlEncodingEnabled(false)
				.queryParam("code", code)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(access_Token_Response);
		String access_Token = js.get("access_token");

		String response = given().queryParam("access_token", access_Token).when()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);
		
		

	}

}
