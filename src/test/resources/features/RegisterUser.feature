Feature: Register User
  In order to control who uses the app
  As an administrator
  I want to register authorised users

  Background:
    Given There is a registered administrator with username "admin" and password "password" and email "admin@sample.app"

  Scenario: Register new user
    Given There is no registered user with username "newuser"
    And I login as "admin" with password "password"
    When I register a new user with username "newuser", email "newuser@sample.app" and password "password"
    Then The response code is 201
    And It has been created a user with username "newuser" and email "newuser@sample.app", the password is not returned
    And I can login with username "newuser" and password "password"

  Scenario: Register existing username
    Given There is a registered user with username "newuser" and password "password" and email "newuser@sample.app"
    And I login as "admin" with password "password"
    When I register a new user with username "newuser", email "newuser@sample.app" and password "newpassword"
    Then The response code is 409
    And I cannot login with username "newuser" and password "newpassword"

  Scenario: Register user when not authenticated
    Given I'm not logged in
    When I register a new user with username "newuser", email "newuser@sample.app" and password "password"
    Then The response code is 401
    And It has not been created a user with username "newuser"

  Scenario: Register user with empty password
    Given I login as "admin" with password "password"
    When I register a new user with username "newuser", email "newuser@sample.app" and password ""
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a user with username "newuser"

  Scenario: Register user with empty email
    Given I login as "admin" with password "password"
    When I register a new user with username "newuser", email "" and password "password"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a user with username "newuser"

  Scenario: Register user with invalid email
    Given I login as "admin" with password "password"
    When I register a new user with username "newuser", email "userasample.app" and password "password"
    Then The response code is 400
    And The error message is "must be a well-formed email address"
    And It has not been created a user with username "newuser"

  Scenario: Register user with password shorter than 8 characters
    Given I login as "admin" with password "password"
    When I register a new user with username "newuser", email "newuser@sample.app" and password "pass"
    Then The response code is 400
    And The error message is "length must be between 8 and 256"
    And It has not been created a user with username "newuser"

  Scenario: Register user with an existing email
    Given There is a registered user with username "newuser" and password "password" and email "newuser@sample.app"
    And I login as "admin" with password "password"
    When I register a new user with username "newuser2", email "newuser@sample.app" and password "password2"
    Then The response code is 409
    And I can login with username "newuser" and password "password"
