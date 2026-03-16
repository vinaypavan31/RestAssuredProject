package Files;

public class payload {
	public static String AddPlace() {
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
	}
	
	public static String CoursePrice() {
		return "{\r\n"
				+ "  \"dashboard\": {\r\n"
				+ "    \"purchaseAmount\": 2310,\r\n"
				+ "    \"currency\": \"INR\"\r\n"
				+ "  },\r\n"
				+ "  \"courses\": [\r\n"
				+ "    {\r\n"
				+ "      \"courseId\": \"C001\",\r\n"
				+ "      \"title\": \"Selenium WebDriver\",\r\n"
				+ "      \"price\": 400,\r\n"
				+ "      \"copies\": 2\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"courseId\": \"C002\",\r\n"
				+ "      \"title\": \"Rest Assured API\",\r\n"
				+ "      \"price\": 300,\r\n"
				+ "      \"copies\": 1\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"courseId\": \"C003\",\r\n"
				+ "      \"title\": \"Cypress Automation\",\r\n"
				+ "      \"price\": 230,\r\n"
				+ "      \"copies\": 1\r\n"
				+ "    }\r\n"
				+ ",\r\n"
				+ "    {\r\n"
				+ "      \"courseId\": \"C004\",\r\n"
				+ "      \"title\": \"Appium Automation\",\r\n"
				+ "      \"price\": 490,\r\n"
				+ "      \"copies\": 2\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
	}
	
	public static String AddBook(String code,String numCode) {
		String payload=
		"{\r\n"
		+ "  \"name\":\"Learn Appium Automation with java\",\r\n"
		+ "  \"isbn\":\""+code+"\",\r\n"
		+ "  \"aisle\":"+numCode+",\r\n"
		+ "  \"author\":\"John Foe\"\r\n"
		+ "}";
		
		return payload;
	}
}
