package graphQL;
import io.restassured.*;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;
public class GraphQLScript {
	
	
	@Test
	public void queryTest(){
		int charId=164;
		String body = "{\n" +
				"  \"query\": \"query ($episodeId: Int!, $characterId: Int!, $locationId: Int!) { episode(episodeId: $episodeId) { id name } character(characterId: $characterId) { name species location { id name type dimension } } location(locationId: $locationId) { id name type dimension residents { id name type status species episodes { id name } } } }\",\n" +
				"  \"variables\": {\n" +
				"    \"episodeId\": 61,\n" +
				"    \"characterId\": "+charId+",\n" +
				"    \"locationId\": 121\n" +
				"  }\n" +
				"}";
		
		
		String response=given().log().all().header("Content-Type","application/json")
				.body(body).
		when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		System.out.println(response);
		JsonPath js= new JsonPath(response);
		
		String charName=js.getString("data.character.name");
		System.out.println(charName);
		Assert.assertEquals(charName, "Munna bhaiya");
	}
	@Test
	public void mutationTest() {
		String charName="Mahesh Babu";
		String body="{\"query\":\"mutation {\\n  createCharacter(character: {name: \\\""+charName+"\\\", type: \\\"Hero\\\", status: \\\"alive\\\", species: \\\"HomoSapien\\\", gender: \\\"male\\\", image: \\\"kb.png\\\", originId: 62, locationId: 72}) {\\n    id\\n  }\\n}\\n\",\"variables\":null}";
		String response=given().log().all().header("Content-Type","application/json")
				.body(body).
		when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().log().all().extract().response().asString();
		System.out.println(response);
		
		
		
	}
	
}
