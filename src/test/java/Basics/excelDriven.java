package Basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import ExcelDataDriven.dataDriven;
import Files.ReusableMethods;

public class excelDriven {
	
	@Test(enabled=false)
	public void addBook() {

		RestAssured.baseURI = "http://216.10.245.166";
		String body = "{ \"name\":\"Learn Appium Automation with Vinay\", \"isbn\":\"rams\", \"aisle\":14634, \"author\":\"vinay\" }";

		String res = given().header("Content-Type", "application/json").body(body).when().post("/Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();

		JsonPath js = ReusableMethods.rawToJson(res);

		String id = js.get("ID");
		System.out.println(id);

	}

	@Test(enabled=false)
	public void addBookWithHashMap() {
		Map<String, Object> jsonHMap = new HashMap<>();
		jsonHMap.put("name", "Sapiens");
		jsonHMap.put("isbn", "bba");
		jsonHMap.put("aisle", "12595");
		jsonHMap.put("author", "pavan");

		RestAssured.baseURI = "http://216.10.245.166";

		String res = given().header("Content-Type", "application/json").body(jsonHMap).when()
				.post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		JsonPath js = ReusableMethods.rawToJson(res);

		String id = js.get("ID");
		System.out.println(id);
	}

	@Test
	public void addBookWithExcel() throws IOException {
		dataDriven data=new dataDriven();
		List d= data.getData("RestAssured","Rest Addbook");
		Map<String, Object> jsonHMap = new HashMap<>();
		jsonHMap.put("name", d.get(1));
		jsonHMap.put("isbn", d.get(2));
		jsonHMap.put("aisle", d.get(3));
		jsonHMap.put("author", d.get(4));

		RestAssured.baseURI = "http://216.10.245.166";

		String res = given().header("Content-Type", "application/json").body(jsonHMap).when()
				.post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		JsonPath js = ReusableMethods.rawToJson(res);

		String id = js.get("ID");
		System.out.println(id);
	}

}
