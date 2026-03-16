package Basics;

import org.testng.Assert;
import org.testng.annotations.Test;

import POJO.LoginRequestEcom;
import POJO.LoginResponse;
import POJO.OrderDetails;
import POJO.Orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class ECommerceAPITest {
	
	@Test
	public void EComApiTest() {
		
		RequestSpecification request= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		 .setContentType(ContentType.JSON).build();
		LoginRequestEcom loginReq=new LoginRequestEcom();
		loginReq.setUserEmail("vinaypavan14264@gmail.com");
		loginReq.setUserPassword("Rahul@rest9");
		
		
		RequestSpecification reqLogin=given().relaxedHTTPSValidation().log().all().spec(request).body(loginReq);
		LoginResponse loginResponse=reqLogin.when().post("/api/ecom/auth/login")
		.then().log().all().extract().response().as(LoginResponse.class);
		
		System.out.println( loginResponse.getToken());
		String token = loginResponse.getToken();
		System.out.println( loginResponse.getUserId());
		String userId=loginResponse.getUserId();
		
		//Add the product
		
		RequestSpecification addProductReqSpec= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addHeader("Authorization",token)
		.build();
		
		 RequestSpecification addProductReq=given().log().all().spec(addProductReqSpec)
		.param("productName", "Laptop")
		.param("productAddedBy", userId)
		.param("productCategory", "fashion")
		.param("productSubCategory", "shirts")
		.param("productPrice", "2340")
		.param("productDescription", "lenovo")
		.param("productFor", "men")
		.multiPart("productImage",new File("C:/Users/KC/Documents/pics/laptoppic.jpg"));
		
		String responseAddProduct=addProductReq.when().post("/api/ecom/product/add-product")
		.then().log().all().extract().response().asString();
		
		JsonPath jp=new JsonPath(responseAddProduct);
		String productId=jp.getString("productId");
		
		System.out.println(productId);
		
		//create order 
		
		RequestSpecification createOrderBaseReq =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addHeader("Authorization", token)
		.setContentType(ContentType.JSON).build();
		
		OrderDetails od= new OrderDetails();
		od.setCountry("India");
		od.setProductOrderedId(productId);
		
		List<OrderDetails> odList=new ArrayList<>();
		odList.add(od);
		Orders orders = new Orders();
		orders.setOrders(odList);
		RequestSpecification createOrderReq= given().log().all().spec(createOrderBaseReq).body(orders);
		String responseAddOrder= createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		System.out.println(responseAddOrder);
		
		//delete - product
		
		RequestSpecification deleteProductBaseReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).build();
		
		RequestSpecification deleteProductReq= given().log().all().spec(deleteProductBaseReq).pathParam("productId", productId);
		String deleteProductResponse=deleteProductReq.when().delete("/api/ecom/product/delete-product/{productId}")
		.then().log().all().extract().response().asString();
		
		JsonPath js=new JsonPath(deleteProductResponse);
		String msgDelete=js.getString("message");
		Assert.assertEquals("Product Deleted Successfully",msgDelete);
	}
}
