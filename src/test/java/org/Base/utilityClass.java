package org.Base;

import java.time.LocalDateTime;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class utilityClass {
	RequestSpecification reqspec;
	Response response;

	public void setRestAssured() {
		reqspec = RestAssured.given();
	}
	
	public void setHeaders(Map head) {
		reqspec = reqspec.headers(head);
	}

	public void setHeader(String key, String value) {
		reqspec = reqspec.header(key, value);
	}

	public void pathParam(String key, Object value) {
		reqspec = reqspec.param(key, value);
	}

	public void queryParam(String key, Object value) {
		reqspec = reqspec.queryParam(key, value);
	}

	public void body(Object value) {
		reqspec = reqspec.body(value);
	}

	public Response methodType(String type, String endpoint) {
		switch (type) {
		case "GET":
			response = reqspec.get(endpoint);

			break;
		case "POST":
			response = reqspec.post(endpoint);

			break;
		case "PUT":
			response = reqspec.put(endpoint);

			break;
		case "DELETE":
			response = reqspec.delete(endpoint);

			break;

		default:
			break;
		}
		return response;
	}

	public int responseCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	public String getBodyAsString(Response response) {
		String asString = response.getBody().asString();
		return asString;
	}

	public String getBodyAsPretty(Response response) {
		String asPrettyString = response.getBody().asPrettyString();
		return asPrettyString;

	}

	public String getValueByJsonPath(String jsonPath) {
		ResponseBody body = response.getBody();
		String value = (String) body.jsonPath().getString(jsonPath);
		return value;
	}

	public String getNanoSeconds() {
		LocalDateTime now = LocalDateTime.now();
		int nano = now.getNano() + now.getMinute();
		String valueOf = String.valueOf(nano);
		String substring = valueOf.substring(0, 5);
		return substring;

	}

	public String getResponseAsPretty() {
		String asPrettyString = response.getBody().asPrettyString();
		return asPrettyString;
	}

}
