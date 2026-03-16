package Files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	public static JsonPath rawToJson(String responseUpdate) {
		JsonPath jp=new JsonPath(responseUpdate);
		return jp;
	}
	
}
