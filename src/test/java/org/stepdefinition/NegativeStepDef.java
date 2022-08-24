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

public class NegativeStepDef extends utilityClass {
	static readConstants constants = new readConstants();
	Response response;
	String nansec = getNanoSeconds();
	static String dataid;
	static String dataEmail;
	static String dataName;
	static String logintoken;
	static Map<String, String> headersmap = new HashMap<>();
	readCommmons commons = constants.getCommons();

	@Given("user register with name,email and password for negative")
	public void user_register_with_name_email_and_password_for_negative() {
		setRestAssured();
		headersmap.put(commons.getContenttype(), commons.getAppjson());
		setHeaders(headersmap);
		RegistrationRoot registration = new RegistrationRoot();
		registration.setName(commons.getCreatename() + nansec);
		registration.setEmail(commons.getCreateemail() + nansec);
		registration.setPassword(commons.getPassword());
		body(registration);
	}

	@When("user verify statuscode and fields for negative")
	public void user_verify_statuscode_and_fields_for_negative() {
		response = methodType(commons.getPost(), constants.getBaseURL() + constants.getUserRegistrationPath());
		dataName = getValueByJsonPath("data.Name");
		Assert.assertEquals(commons.getCreatename() + nansec, dataName);
		dataEmail = getValueByJsonPath("data.Email");
		Assert.assertEquals(commons.getCreateemail() + nansec, dataEmail);
		dataid = getValueByJsonPath("data.Id");
		int statusCode2 = response.getStatusCode();
		Assert.assertEquals(200, statusCode2);
	}

	@When("user login with the registered email and password for negative")
	public void user_login_with_the_registered_email_and_password_for_negative() {
		setRestAssured();
		setHeaders(headersmap);
		LoginRoot login = new LoginRoot();
		login.setEmail(dataEmail);
		login.setPassword(123456);
		body(login);
	}

	@Then("user verify statuscode and response fields for negative")
	public void user_verify_statuscode_and_response_fields_for_negative() {
		response = methodType(commons.getPost(), constants.getBaseURL() + constants.getUserLoginPath());
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
		String loginid = getValueByJsonPath("data.Id");
		Assert.assertEquals(dataid, loginid);
		String loginemail = getValueByJsonPath("data.Email");
		Assert.assertEquals(dataEmail, loginemail);
		String loginname = getValueByJsonPath("data.Name");
		Assert.assertEquals(dataName, loginname);
		logintoken = getValueByJsonPath("data.Token");
		headersmap.put(commons.getAuth(), commons.getBearer() + logintoken);
	}

	@Given("user should add header key and value for user registration")
	public void user_should_add_header_key_and_value_for_user_registration() {
		try {
			setRestAssured();
			setHeaders(headersmap);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add response body values name, email and password for user registration")
	public void user_should_add_response_body_values_name_email_and_password_for_user_registration() {
		try {
			RegistrationRoot register = new RegistrationRoot();
			register.setName(commons.getNegativename());
			register.setEmail(commons.getNegativeemail());
			register.setPassword(commons.getNegativepassword());
			body(register);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add methodtype to send request endpoint for registration")
	public void user_should_add_methodtype_to_send_request_endpoint_for_registration() {
		try {
			response = methodType(commons.getPost(), constants.getBaseURL() + constants.getUserRegistrationPath());
			String asPrettyString = getResponseAsPretty();
			System.out.println(asPrettyString);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Then("user should verify statuscode {string} and verify the error message {string} for registration {string} {string}")
	public void user_should_verify_statuscode_and_verify_the_error_message_for_registration(String statuscode,
			String message, String emailmessage, String passwordmessage) {
		try {
			int statusCode2 = response.getStatusCode();
			Assert.assertEquals(Integer.parseInt(statuscode), statusCode2);
			String errormessage = getValueByJsonPath("Message");
			Assert.assertTrue(errormessage.contains(message));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Given("user should add header key and value for negative user login")
	public void user_should_add_header_key_and_value_for_negative_user_login() {
		try {
			setRestAssured();
			setHeaders(headersmap);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add body using registered email and password for negative user login")
	public void user_should_add_body_using_registered_email_and_password_for_negative_user_login() {
		try {
			LoginRoot login = new LoginRoot();
			login.setEmail(commons.getNegativeemail());
			login.setPassword(commons.getNegativepassword());
			body(login);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add methodtype to send request to endpoint for negative user login")
	public void user_should_add_methodtype_to_send_request_to_endpoint_for_negative_user_login() {
		try {
			response = methodType(commons.getPost(), constants.getBaseURL() + constants.getUserLoginPath());
			String asPrettyString = getResponseAsPretty();
			System.out.println(asPrettyString);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Then("user should verify statuscode {string} and error message {string} for user login")
	public void user_should_verify_statuscode_and_error_message_for_user_login(String statuscode, String message) {
		try {
			int statusCode = response.getStatusCode();
			Assert.assertEquals(Integer.parseInt(statuscode), statusCode);
			String errormessage = getValueByJsonPath("message");
			Assert.assertTrue(errormessage.contains(message));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Given("user should add header key and value to get particuler user with invalid user id")
	public void user_should_add_header_key_and_value_to_get_particuler_user_with_invalid_user_id() {
		try {
			setRestAssured();
			setHeaders(headersmap);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add methodtype  to send endpoint to get a user with invalid user id {int}")
	public void user_should_add_methodtype_to_send_endpoint_to_get_a_user_with_invalid_user_id(Integer id) {
		try {
			response = methodType(commons.getGet(), constants.getBaseURL() + constants.getAUserPath() + id);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Then("user should verify statuscode {string} for invalid user id")
	public void user_should_verify_statuscode_for_invalid_user_id(String statuscode) {
		try {
			int statusCode = response.getStatusCode();
			Assert.assertEquals(Integer.parseInt(statuscode), statusCode);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Given("user should add header key and value to get create user object with invalid body")
	public void user_should_add_header_key_and_value_to_get_create_user_object_with_invalid_body() {
		try {
			setRestAssured();
			setHeaders(headersmap);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should use bearer token and request body for creating user object with invalid body")
	public void user_should_use_bearer_token_and_request_body_for_creating_user_object_with_invalid_body() {
		try {
			CreateObjectRoot createuser = new CreateObjectRoot();
			createuser.setName(commons.getNegativename());
			createuser.setEmail(commons.getNegativeemail());
			createuser.setLocation(commons.getNegativelocation());
			body(createuser);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add methodtype to send endpoint to create a user object with invalid body")
	public void user_should_add_methodtype_to_send_endpoint_to_create_a_user_object_with_invalid_body() {
		try {
			response = methodType(commons.getPost(), constants.getBaseURL() + constants.getAUserPath());
			String asPrettyString = getResponseAsPretty();
			System.out.println(asPrettyString);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Then("user should verify statuscode {string} and error message {string} for creating user object with invalid body")
	public void user_should_verify_statuscode_and_error_message_for_creating_user_object_with_invalid_body(
			String statuscode, String message) {
		try {
			int statusCode2 = response.getStatusCode();
			Assert.assertEquals(Integer.parseInt(statuscode), statusCode2);
			String errormessage = getValueByJsonPath("Message");
			Assert.assertTrue(errormessage.contains(message));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Given("user should add header key and value to get update user object with invalid id")
	public void user_should_add_header_key_and_value_to_get_update_user_object_with_invalid_id() {
		try {
			setRestAssured();
			setHeaders(headersmap);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add request body {int} for updating user object with invalid id")
	public void user_should_add_request_body_for_updating_user_object_with_invalid_id(Integer id) {
		try {
			updateObjectRoot updateuser = new updateObjectRoot();
			updateuser.setId(id);
			updateuser.setName(commons.getNegativename());
			updateuser.setEmail(commons.getNegativeemail());
			updateuser.setLocation(commons.getNegativelocation());
			body(updateuser);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add methodtype to send endpoint to update a user {int} object with invalid id")
	public void user_should_add_methodtype_to_send_endpoint_to_update_a_user_object_with_invalid_id(Integer invalidid) {
		try {
			response = methodType(commons.getPut(), constants.getBaseURL() + constants.getAUserPath() + invalidid);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Then("user should verify statuscode {string} for updating user object with invalid id")
	public void user_should_verify_statuscode_for_updating_user_object_with_invalid_id(String statuscode) {
		try {
			int statusCode = response.getStatusCode();
			Assert.assertEquals(Integer.parseInt(statuscode), statusCode);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Given("user should add header key and value to get all users without query parameter")
	public void user_should_add_header_key_and_value_to_get_all_users_without_query_parameter() {
		try {
			setRestAssured();
			setHeaders(headersmap);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add methodtype to send endpoint and get all users without query parameter")
	public void user_should_add_methodtype_to_send_endpoint_and_get_all_users_without_query_parameter() {
		try {
			response = methodType(commons.getGet(), constants.getBaseURL() + constants.getAllUsersPath());
			String asPrettyString = getResponseAsPretty();
			System.out.println(asPrettyString);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Then("user should verify statuscode {string} and error message {string} for getting all users without query parameter")
	public void user_should_verify_statuscode_and_error_message_for_getting_all_users_without_query_parameter(
			String statuscode, String message) {
		try {
			int statusCode2 = response.getStatusCode();
			Assert.assertEquals(Integer.parseInt(statuscode), statusCode2);
			String errormessage = getValueByJsonPath("Message");
			Assert.assertTrue(errormessage.contains(message));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Given("user should add header key and value to get delete user object with invalid id")
	public void user_should_add_header_key_and_value_to_get_delete_user_object_with_invalid_id() {
		try {
			setRestAssured();
			setHeaders(headersmap);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@When("user should add methodtype to send endpoint to delete a user object with invalid id {int}")
	public void user_should_add_methodtype_to_send_endpoint_to_delete_a_user_object_with_invalid_id(Integer id) {
		try {
			response = methodType(commons.getDelete(), constants.getBaseURL() + constants.getAUserPath() + id);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Then("user should verify statuscode {string} and response fields of object delete with invalid id")
	public void user_should_verify_statuscode_and_response_fields_of_object_delete_with_invalid_id(String statuscode) {
		try {
			int statuCode = responseCode(response);
			Assert.assertEquals(Integer.parseInt(statuscode), statuCode);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
