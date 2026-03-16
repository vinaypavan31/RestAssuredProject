package Basics;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class sumOfCourses {
	@Test
	public void sumOfCourses() {
		JsonPath jp=new JsonPath(payload.CoursePrice());
		
		
		int count=jp.getInt("courses.size()");
		System.out.println(count); 
		int purchaseAmount=jp.getInt("dashboard.purchaseAmount");
		int sumOfCopies=0;
		for(int i=0;i<count;i++) {
			int noCopies=jp.getInt("courses["+i+"].copies");
			int price=jp.getInt("courses["+i+"].price");
			sumOfCopies=sumOfCopies+(noCopies*price);
		}
		System.out.println(purchaseAmount);
		System.out.println(sumOfCopies);
		Assert.assertEquals(sumOfCopies,purchaseAmount );
	}
}
