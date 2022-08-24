Feature: Slove APi Automation

Background: user register and login with email and password 
Given user register with name,email and password 
When user verify statuscode and fields 
When user login with the registered email and password
Then user verify statuscode and response fields

@positive
Scenario Outline: Verify user should get a user 
Given user should add header key and value to get particuler user with dataid
And user should add methodtype to send endpoint to get a user
Then user should verify statuscode "<statuscode>" and response fields 

Examples:
|statuscode|
|200|


@positive
Scenario Outline: Verify user should create a user object
Given user should add header key and value to get create user object with login token
When user should use bearer token and request body for creating user object
And user should add methodtype to send endpoint to create a user object
Then user should verify statuscode "<statuscode>" and response fields of object creation

Examples:
|statuscode|
|201|


@positive
Scenario Outline: Verify user should update a user object
Given user should add header key and value to get update user object with login token
When user should use bearer token and request body for updating user object
And user should add methodtype to send endpoint to update a user object
Then user should verify statuscode "<statuscode>" and response fields of object update

Examples:
|statuscode|
|200|


@positive
Scenario Outline: Verify user should delete a user object
Given user should add header key and value to get delete user object with login token
When user should add methodtype to send endpoint to delete a user object
Then user should verify statuscode "<statuscode>" and response fields of object delete 

Examples:
|statuscode|
|200|

@positive
Scenario Outline: Verify user should get all users
Given user should add header key and value to get all users with login token
When user should add methodtype to send endpoint and get all users per page "<page>" "<perpage>"
Then user should verify statuscode "<statuscode>" and response fields of getting all users

Examples:
|page|perpage|statuscode|
|2|10|200|

