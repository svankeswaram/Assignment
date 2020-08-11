@MyTag
Feature: Default API tests

Scenario: Test all the given APIs
	Given I print number of users in the system
	Then I get the user by id "123" and validate the response 200
	Then I get the user by id "1001" and validate the response 404
	And I get the users by location "London" and validate the location
	And I get the users by location "Leeds" and validate the location
