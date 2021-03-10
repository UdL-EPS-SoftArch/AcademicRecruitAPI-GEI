Feature: Register User
  In order to control who uses the app
  As an administrator
  I want to register authorised users

  Background:
    Given There is a registered administrator with username "admin" and password "password" and email "admin@sample.app" and name "name" and lastname "lastname" and dni "12345678A"

  Scenario: Register new user
    Given There is no registered user with username "newuser"
    And I login as "admin" with password "password"
    When I register a new user with username "newuser" , email "newuser@sample.app" , password "password" , name "name" , lastname "lastname" and dni "12345678A"
    Then The response code is 201
    And It has been created a user with username "newuser" , email "newuser@sample.app" , name "name" , lastname "lastname" and dni "12345678A" , the password is not returned
    And I can login with username "newuser" and password "password"

  Scenario: Register existing username
    Given There is a registered user with username "newuser" and password "password" and email "newuser@sample.app"
    And I login as "admin" with password "password"
    When I register a new user with username "newuser" , email "newuser@sample.app" , password "password" , name "name" , lastname "lastname" and dni "12345678A"
    Then The response code is 409
    And I cannot login with username "newuser" and password "newpassword"

  Scenario: Register user when not authenticated
    Given I'm not logged in
    When I register a new user with username "newuser" , email "newuser@sample.app" , password "password" , name "name" , lastname "lastname" and dni "12345678A"
    Then The response code is 401
    And It has not been created a user with username "newuser"

  Scenario: Register user with empty password
    Given I login as "admin" with password "password"
    When I register a new user with username "newuser" , email "newuser@sample.app" , password "" , name "name" , lastname "lastname" and dni "12345678A"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a user with username "newuser"

  Scenario: Register user with empty email
    Given I login as "admin" with password "password"
    When I register a new user with username "newuser" , email "" , password "password" , name "name" , lastname "lastname" and dni "12345678A"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a user with username "newuser"

  Scenario: Register user with invalid email
    Given I login as "admin" with password "password"
    When I register a new user with username "newuser" , email "userasample.app" , password "password" , name "name" , lastname "lastname" and dni "12345678A"
    Then The response code is 400
    And The error message is "must be a well-formed email address"
    And It has not been created a user with username "newuser"

  Scenario: Register user with password shorter than 8 characters
    Given I login as "admin" with password "password"
    When I register a new user with username "newuser" , email "newuser@sample.app" , password "pass" , name "name" , lastname "lastname" and dni "12345678A"
    Then The response code is 400
    And The error message is "length must be between 8 and 256"
    And It has not been created a user with username "newuser"

  Scenario: Register user with an existing email
    Given There is a registered user with username "newuser" and password "password" and email "newuser@sample.app" and dni "12345678A"
    And I login as "admin" with password "password"
    When I register a new user with username "newuser2" , email "newuser@sample.app" , password "password2" , name "name" , lastname "lastname" and dni "12345679A"
    Then The response code is 409
    And I can login with username "newuser" and password "password"

  Scenario: Register user with empty name
    Given I login as "admin" with password "password"
    When I register a new user with username "newuser" , email "newuser@sample.app" , password "password" , name "" , lastname "lastname" and dni "12345678A"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a user with username "newuser"

  Scenario: Register user with empty lastname
    Given I login as "admin" with password "password"
    When I register a new user with username "newuser" , email "newuser@sample.app" , password "password" , name "name" , lastname "" and dni "12345678A"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a user with username "newuser"

  Scenario: Register user with empty dni
    Given I login as "admin" with password "password"
    When I register a new user with username "newuser" , email "newuser@sample.app" , password "password" , name "name" , lastname "lastname" and dni ""
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a user with username "newuser"

  Scenario: Register user with invalid dni longer than 9 characters
    Given I login as "admin" with password "password"
    When I register a new user with username "newuser" , email "newuser@sample.app" , password "password" , name "name" , lastname "lastname" and dni "123456789A"
    Then The response code is 400
    And The error message is "length must be between 9 and 9"
    And It has not been created a user with username "newuser"

  Scenario: Register user with invalid dni shorter than 9 characters
    Given I login as "admin" with password "password"
    When I register a new user with username "newuser" , email "newuser@sample.app" , password "password" , name "name" , lastname "lastname" and dni "1234567A"
    Then The response code is 400
    And The error message is "length must be between 9 and 9"
    And It has not been created a user with username "newuser"

  Scenario: Register user with an existing email
    Given There is a registered user with username "newuser" and password "password" and email "newuser@sample.app" and dni "12345678A"
    And I login as "admin" with password "password"
    When I register a new user with username "newuser2" , email "newuser2@sample.app" , password "password2" , name "name" , lastname "lastname" and dni "12345678A"
    Then The response code is 409
    And I can login with username "newuser" and password "password"
