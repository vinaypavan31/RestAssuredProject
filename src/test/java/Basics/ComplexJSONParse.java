package Basics;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJSONParse {

	public static void main(String[] args) {
		
		JsonPath jp=new JsonPath(payload.CoursePrice());
		
		int count=jp.getInt("courses.size()");
		System.out.println(count);
		
		int pa=jp.getInt("dashboard.purchaseAmount");
		System.out.println(pa);
		
		String title1=jp.getString("courses[0].title");
		System.out.println(title1);
		
		
		for(int i=0;i<count;i++) {
			String cT=jp.get("courses["+i+"].title");
			
			System.out.println(cT);
			System.out.println( jp.get("courses["+i+"].price").toString());
		}
		System.out.println("********************************************");
		for(int i=0;i<count;i++) {
		if( jp.getString("courses["+i+"].title").equals("Appium Automation")) {
			System.out.println(jp.getInt("courses["+i+"].copies"));
			break;
		}
		
		}
		int purchaseAmount=jp.getInt("dashboard.purchaseAmount");
		int sumOfCopies=0;
		for(int i=0;i<count;i++) {
			int noCopies=jp.getInt("courses["+i+"].copies");
			int price=jp.getInt("courses["+i+"].price");
			sumOfCopies=sumOfCopies+(noCopies*price);
		}
		System.out.println(purchaseAmount);
		System.out.println(sumOfCopies);
		if(purchaseAmount==sumOfCopies) {
			System.out.println("Yes it is "+purchaseAmount+","+sumOfCopies);
		}
		else {
			System.out.println("It s Wrong");
		}
		
	}

}
