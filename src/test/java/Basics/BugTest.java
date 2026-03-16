package Basics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugTest {

	@Test
	public void buttonNotWorkingTest() {
		RestAssured.baseURI = "https://vinay-pavan.atlassian.net/";
		String createIssueResponse = given().header("Content-Type", "application/json").header("Authorization",
				"Basic dmluYXlwYXZhbjE0MjY0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBPWXVQWVZSUEo2S1JLMGtQWGJLOXhkWU9aTG1LVHlodEpzbmI1YXhJbW9qLVF4N3lkamdLSGRqdFAtUWxaSGYzSFROaG9oaDd2TkhZenBxSTByOGlhYlhTNkVpMDI3Wjc1MFhaeE82ejdJMFh2M1pzd0kzdVVkVWhrNXc1NFczak93cUktMExZZG5SVm1fQ1F2MnZmQWVkcjZzVVNZS3ZVNW9JUWlDYW9hVVU9Nzc2QTE3RDk=")
				.body("{\r\n" + "    \"fields\": {\r\n" + "       \"project\":\r\n" + "       {\r\n"
						+ "          \"key\": \"SCRUM\"\r\n" + "       },\r\n"
						+ "       \"summary\": \"links are not working.\",\r\n" + "       \r\n"
						+ "       \"issuetype\": {\r\n" + "          \"name\": \"Bug\"\r\n" + "       }\r\n"
						+ "   }\r\n" + "}")
				.log().all().when().post("rest/api/3/issue").then().log().all().assertThat().statusCode(201).extract()
				.response().asString();

		JsonPath jp = new JsonPath(createIssueResponse);
		String issueId = jp.getString("id");
		System.out.println(issueId);

		// add attachment

		given().pathParam("key", issueId)
				.header("X-Atlassian-Token", "no-check")
				.header("Authorization",
						"Basic dmluYXlwYXZhbjE0MjY0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBPWXVQWVZSUEo2S1JLMGtQWGJLOXhkWU9aTG1LVHlodEpzbmI1YXhJbW9qLVF4N3lkamdLSGRqdFAtUWxaSGYzSFROaG9oaDd2TkhZenBxSTByOGlhYlhTNkVpMDI3Wjc1MFhaeE82ejdJMFh2M1pzd0kzdVVkVWhrNXc1NFczak93cUktMExZZG5SVm1fQ1F2MnZmQWVkcjZzVVNZS3ZVNW9JUWlDYW9hVVU9Nzc2QTE3RDk=")
				.multiPart("file", new File("C:\\Users\\KC\\Documents\\ss\\Screenshot 2025-09-09 111206.png")).log()
				.all().post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	}
}
