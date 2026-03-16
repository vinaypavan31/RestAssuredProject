package Basics;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.payload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		String response= given().log().all().header("Content-Type","application/json")
		.body(payload.AddBook(isbn,aisle))
		.when()
		.post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath jp= new JsonPath(response);
		
		String id=jp.get("ID");
		System.out.println(id);
	}
	
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		
		return new Object[][]{
			{"buk1","101"},{"buk2","102"},{"buk3","103"},{"buk4","104"}
		};
		
	}
	
	//
}
