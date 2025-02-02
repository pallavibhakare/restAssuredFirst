package utils;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import java.io.File;
import java.util.HashMap;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtils {
	public static Response postReq(HashMap<String, String> headers, String payload, String path) {
		//public static Response postReq(HashMap<String, String> headers, jsonObject payload, String path) {
		Response loginRes = RestAssured.given().headers(headers).when()
		.body(payload).post(path)
		.then().statusCode(201).extract().response();
		return loginRes;
	}
	public static Response putReq(HashMap<String, String> headers, String payload, String path) {
		
		Response putReq = RestAssured.given().headers(headers).when()
		.body(payload).put(path)
		.then().statusCode(200).extract().response();
		return putReq;
	}
	
	public static Response getReq(HashMap<String, String> headers, String path) {
		
		Response getReq = RestAssured.given().headers(headers).when()
		.get(path)
		.then().statusCode(200).extract().response();
		return getReq;
	}	
	public static Response deleteReq(HashMap<String, String> headers, String payload, String path) {
		
		Response delRes = RestAssured.given().headers(headers).when()
		.body(payload).delete(path)
		.then().statusCode(200).extract().response();
		return delRes;
	}
	
	public static void validateSchema(Response response, String filePath) {
		response.then().assertThat().body(matchesJsonSchema(new File(filePath)));
	}
}
