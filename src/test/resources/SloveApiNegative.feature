Feature: Slove negative api testing 

Background: user register and login with email and password for negative 
Given user register with name,email and password for negative
When user verify statuscode and fields for negative
When user login with the registered email and password for negative
Then user verify statuscode and response fields for negative

@negative
Scenario Outline: Verify user should register api with negative scenario
Given user should add header key and value for user registration
When user should add response body values name, email and password for user registration
And user should add methodtype to send request endpoint for registration
Then user should verify statuscode "<statuscode>" and verify the error message "<message>" for registration "<emailmessage>" "<passwordmessage>"

Examples:
|statuscode|message|emailmessage|passwordmessage|
|400|The request is invalid.|Enter valid email|password at least 6|

@negative
Scenario Outline: Verify user should login with negative email and password
Given user should add header key and value for negative user login 
When user should add body using registered email and password for negative user login
And user should add methodtype to send request to endpoint for negative user login
Then user should verify statuscode "<statuscode>" and error message "<message>" for user login

Examples:
|statuscode|message|
|200|invalid username or password|


@negative
Scenario Outline: Verify user should get a user with invalid id
Given user should add header key and value to get particuler user with invalid user id
When user should add methodtype  to send endpoint to get a user with invalid user id <id>
Then user should verify statuscode "<statuscode>" for invalid user id

Examples:
|id|statuscode|
|12345|404|

@negative
Scenario Outline: Verify user should create a user object with invalid body
Given user should add header key and value to get create user object with invalid body
When user should use bearer token and request body for creating user object with invalid body
And user should add methodtype to send endpoint to create a user object with invalid body
Then user should verify statuscode "<statuscode>" and error message "<message>" for creating user object with invalid body

Examples:
|statuscode|message|
|400|try with different email|

@negative
Scenario Outline: Verify user should update a user object with invalid id 
Given user should add header key and value to get update user object with invalid id 
When user should add request body <id> for updating user object with invalid id 
And user should add methodtype to send endpoint to update a user <invalidid> object with invalid id 
Then user should verify statuscode "<statuscode>" for updating user object with invalid id 

Examples:
|id|invalidid|statuscode|
|123|1234|400|

@negative
Scenario Outline: Verify user should get all users without query parameter
Given user should add header key and value to get all users without query parameter
When user should add methodtype to send endpoint and get all users without query parameter
Then user should verify statuscode "<statuscode>" and error message "<message>" for getting all users without query parameter

Examples:
|statuscode|message|
|400|request is invalid|

@negative
Scenario Outline: Verify user should delete a user object with invalid user id
Given user should add header key and value to get delete user object with invalid id
When user should add methodtype to send endpoint to delete a user object with invalid id <id>
Then user should verify statuscode "<statuscode>" and response fields of object delete with invalid id

Examples:
|id|statuscode|
|1234567|404|

