package books;

//Link a postman
//https://www.getpostman.com/collections/d2948f50169706303052

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class BookCompany {
	
	@BeforeTest
	private void startTesting() {
		RestAssured.baseURI = "https://simple-books-api.glitch.me/";;
	}

	//Test 1
	@Test
	public void logIn() {

		given()
		.when().get("")
		.then().log().all().assertThat().statusCode(200);
		
	}
	
	//Test 2
	@Test
	public void viewAllBooks() {

		given()
		.when().get("books")
		.then().log().all().assertThat().statusCode(200);
			
	}
	
	//Test 3
	@Test
	public void availableBooks() {

		given()
		.when().get("https://simple-books-api.glitch.me/books?available=true")
		.then().log().all().assertThat().statusCode(200);
			
	}
		
	
	//Test 4
	@Test
	public void typeBooks() {

		given()
		.when().get("books?type=fiction")
		.then().log().all().assertThat().statusCode(200);
			
	}
	
	//Test 5
	@Test
	public void viewBook() {

		given()
		.when().get("books/1")
		.then().log().all().assertThat().statusCode(200);
			
	}
	
	//Test 6
	@Test
	public void fiveBooksPerPage() {

		given()
		.when().get("https://simple-books-api.glitch.me/books?limit=5")
		.then().log().all().assertThat().statusCode(200);
			
	}
	
	
	
	//Test 7
	@Test
	public void createProfile() {
		int number = (int) (Math.random() * 100.0d) ;

		given().header("Content-Type","application/json").body("{\n"
				+ "    \"clientName\": \"Alejandra Loredo\",\n"
				+ "    \"clientEmail\": \"alejandra.loredo" + number + "@example.com\"\n"
				+ "}")
		.when().post("api-clients")
		.then().log().all().assertThat().statusCode(201).body("$", hasKey("accessToken"));
			
	}
	
	//Test 8
	@Test
	public void placeOrder() {

		given().header("Authorization", "Bearer a45cdfd68af5a1cf2ac201971aa120c3eab035f22c143a8e2cb494c0257a5626" )
		.header("Content-Type","application/json")
		.body("{\n"
				+ "    \"bookId\": 1\n"
				+ "\n"
				+ "}")
		.when().post("orders")
		.then().log().all().assertThat().statusCode(201).body("$", hasKey("orderId"));
				
		}
		
	//Test 9
	@Test
	public void vieaAllOrders() {

		given().header("Authorization", "Bearer a45cdfd68af5a1cf2ac201971aa120c3eab035f22c143a8e2cb494c0257a5626" )
		.header("Content-Type","application/json")
		.when().get("https://simple-books-api.glitch.me/orders")
		.then().log().all().assertThat().statusCode(200);
			
	}
	
}
