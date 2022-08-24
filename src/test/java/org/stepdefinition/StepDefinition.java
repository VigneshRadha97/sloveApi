package org.stepdefinition;

import java.util.HashMap;
import java.util.Map;

import org.Base.utilityClass;
import org.junit.Assert;
import org.pojo.CreateObjectRoot;
import org.pojo.LoginRoot;
import org.pojo.RegistrationRoot;
import org.pojo.readCommmons;
import org.pojo.updateObjectRoot;
import org.testrunner.readConstants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class StepDefinition extends utilityClass {
	static readConstants constants = new readConstants();
	Response response;
	String nansec = getNanoSeconds();
	static String dataid;
	static String dataEmail;
	static String dataName;
	static String loginToken;
	static Map<String, String> headersmap = new HashMap<>();
	readCommmons commons = constants.getCommons();

	@Given("user register with name,email and password")
	public void user_register_with_name_email_and_password() {
		try {
			setRestAssured();
			headersmap.put(commons.getContenttype(), commons.getAppjson());
			setHeaders(headersmap);
			RegistrationRoot registration = new RegistrationRoot();
			registration.setName(commons.getName() + nansec);
			registration.setEmail(commons.getEmail() + nansec);
			registration.setPassword(commons.getPassword());
			body(registration);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user verify statuscode and fields")
	public void user_verify_statuscode_and_fields() {
		try {
			response = methodType(commons.getPost(), constants.getBaseURL() + constants.getUserRegistrationPath());
			dataName = getValueByJsonPath("data.Name");
			Assert.assertEquals(commons.getName() + nansec, dataName);
			dataEmail = getValueByJsonPath("data.Email");
			Assert.assertEquals(commons.getEmail() + nansec, dataEmail);
			dataid = getValueByJsonPath("data.Id");
			int statusCode2 = response.getStatusCode();
			Assert.assertEquals(200, statusCode2);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user login with the registered email and password")
	public void user_login_with_the_registered_email_and_password() {
		try {
			setRestAssured();
			setHeaders(headersmap);
			LoginRoot login = new LoginRoot();
			login.setEmail(dataEmail);
			login.setPassword(123456);
			body(login);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Then("user verify statuscode and response fields")
	public void user_verify_statuscode_and_response_fields() {
		try {
			response = methodType(commons.getPost(), constants.getBaseURL() + constants.getUserLoginPath());
			int statusCode = response.getStatusCode();
			Assert.assertEquals(200, statusCode);
			String loginid = getValueByJsonPath("data.Id");
			Assert.assertEquals(dataid, loginid);
			String loginemail = getValueByJsonPath("data.Email");
			Assert.assertEquals(dataEmail, loginemail);
			String loginname = getValueByJsonPath("data.Name");
			Assert.assertEquals(dataName, loginname);
			loginToken = getValueByJsonPath("data.Token");
			headersmap.put(commons.getAuth(), commons.getBearer() + loginToken);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Given("user should add header key and value to get particuler user with dataid")
	public void user_should_add_header_key_and_value_to_get_particuler_user_with_dataid() {
		try {
			setRestAssured();
			setHeaders(headersmap);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add methodtype to send endpoint to get a user")
	public void user_should_add_methodtype_to_send_endpoint_to_get_a_user() {
		try {
			response = methodType(commons.getGet(), constants.getBaseURL() + constants.getAUserPath() + dataid);
			String asPrettyString = getResponseAsPretty();
			System.out.println(asPrettyString);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Then("user should verify statuscode {string} and response fields")
	public void user_should_verify_statuscode_and_response_fields(String statuscode) {
		try {
			int statusCode2 = response.getStatusCode();
			Assert.assertEquals(Integer.parseInt(statuscode), statusCode2);
			String id = getValueByJsonPath("id");
			Assert.assertEquals(dataid, id);
			String name = getValueByJsonPath("name");
			Assert.assertEquals(dataName, name);
			String email = getValueByJsonPath("email");
			Assert.assertEquals(dataEmail, email);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Given("user should add header key and value to get create user object with login token")
	public void user_should_add_header_key_and_value_to_get_create_user_object_with_login_token() {
		try {
			setRestAssured();
			setHeaders(headersmap);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should use bearer token and request body for creating user object")
	public void user_should_use_bearer_token_and_request_body_for_creating_user_object() {
		try {
			CreateObjectRoot createobject = new CreateObjectRoot();
			createobject.setName(commons.getCreatename() + nansec);
			createobject.setEmail(commons.getCreateemail() + nansec);
			createobject.setLocation(commons.getCreatelocation());
			body(createobject);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add methodtype to send endpoint to create a user object")
	public void user_should_add_methodtype_to_send_endpoint_to_create_a_user_object() {
		try {
			response = methodType(commons.getPost(), constants.getBaseURL() + constants.getAUserPath());
			String asPrettyString = getResponseAsPretty();
			System.out.println(asPrettyString);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Then("user should verify statuscode {string} and response fields of object creation")
	public void user_should_verify_statuscode_and_response_fields_of_object_creation(String statuscode) {
		try {
			int statusCode2 = response.getStatusCode();
			Assert.assertEquals(Integer.parseInt(statuscode), statusCode2);
			dataName = getValueByJsonPath("name");
			Assert.assertEquals(commons.getCreatename() + nansec, dataName);
			dataEmail = getValueByJsonPath("email");
			Assert.assertEquals(commons.getCreateemail() + nansec, dataEmail);
			dataid = getValueByJsonPath("id");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Given("user should add header key and value to get update user object with login token")
	public void user_should_add_header_key_and_value_to_get_update_user_object_with_login_token() {
		try {
			setRestAssured();
			setHeaders(headersmap);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should use bearer token and request body for updating user object")
	public void user_should_use_bearer_token_and_request_body_for_updating_user_object() {
		try {
			updateObjectRoot updateuser = new updateObjectRoot();
			updateuser.setId(Integer.parseInt(dataid));
			updateuser.setName(commons.getUpdatename() + nansec);
			updateuser.setEmail(commons.getUpdateemail() + nansec);
			updateuser.setLocation(commons.getUpdatelocation());
			body(updateuser);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add methodtype to send endpoint to update a user object")
	public void user_should_add_methodtype_to_send_endpoint_to_update_a_user_object() {
		try {
			response = methodType(commons.getPut(), constants.getBaseURL() + constants.getAUserPath() + dataid);
			String asPrettyString = getResponseAsPretty();
			System.out.println(asPrettyString);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Then("user should verify statuscode {string} and response fields of object update")
	public void user_should_verify_statuscode_and_response_fields_of_object_update(String statuscode) {
		try {
			int statusCode = response.getStatusCode();
			Assert.assertEquals(Integer.parseInt(statuscode), statusCode);
			dataName = getValueByJsonPath("name");
			Assert.assertEquals(commons.getUpdatename() + nansec, dataName);
			dataEmail = getValueByJsonPath("email");
			Assert.assertEquals(commons.getUpdateemail() + nansec, dataEmail);
			String id = getValueByJsonPath("id");
			Assert.assertEquals(dataid, id);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Given("user should add header key and value to get delete user object with login token")
	public void user_should_add_header_key_and_value_to_get_delete_user_object_with_login_token() {
		try {
			setRestAssured();
			setHeaders(headersmap);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add methodtype to send endpoint to delete a user object")
	public void user_should_add_methodtype_to_send_endpoint_to_delete_a_user_object() {
		try {
			response = methodType(commons.getDelete(), constants.getBaseURL() + constants.getAUserPath() + dataid);
			String asPrettyString = getResponseAsPretty();
			System.out.println(asPrettyString);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Then("user should verify statuscode {string} and response fields of object delete")
	public void user_should_verify_statuscode_and_response_fields_of_object_delete(String statuscode) {
		try {
			int statusCode = response.getStatusCode();
			Assert.assertEquals(Integer.parseInt(statuscode), statusCode);
			String id = getValueByJsonPath("id");
			Assert.assertEquals(dataid, id);
			String name = getValueByJsonPath("name");
			Assert.assertEquals(dataName, name);
			String email = getValueByJsonPath("email");
			Assert.assertEquals(dataEmail, email);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Given("user should add header key and value to get all users with login token")
	public void user_should_add_header_key_and_value_to_get_all_users_with_login_token() {
		try {
			setRestAssured();
			setHeaders(headersmap);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add methodtype to send endpoint and get all users per page {string} {string}")
	public void user_should_add_methodtype_to_send_endpoint_and_get_all_users_per_page(String page, String perpage) {
		try {
			response = methodType(commons.getGet(), constants.getBaseURL() + constants.getAllUsersPath() + page);
			String asPrettyString = getResponseAsPretty();
			System.out.println(asPrettyString);
			String respage = getValueByJsonPath("page");
			Assert.assertEquals(page, respage);
			String resperpage = getValueByJsonPath("per_page");
			Assert.assertEquals(perpage, resperpage);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Then("user should verify statuscode {string} and response fields of getting all users")
	public void user_should_verify_statuscode_and_response_fields_of_getting_all_users(String statuscode) {
		try {
			int statusCode2 = response.getStatusCode();
			Assert.assertEquals(Integer.parseInt(statuscode), statusCode2);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
