Feature: Applicants
  In order to control the Applicants
  As a secretary of the committee
  I want to create Applicants

  Background:
    Given There is a registered user with username "secretary" and password "password" and email "email@gmail.com" and name "name" and lastname "lastname" and dni "12345678B"

  Scenario: Create new Applicant
    Given I login as "secretary" with password "password"
    When I register a new applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345678C"
    Then The response code is 201
    And It has been created a new applicant with email "applicant@sample.app", name "name", lastname "lastname" and dni "12345678C"

  Scenario: New Applicant with existing email
    Given There is a registered applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345678A"
    And I login as "secretary" with password "password"
    When I register a new applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345674C"
    Then The response code is 409

  Scenario: New Applicant with existing dni
    Given There is a registered applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345678A"
    And I login as "secretary" with password "password"
    When I register a new applicant with email "applicant2@sample.app" and name "name" and lastname "lastname" and dni "12345678A"
    Then The response code is 409

  Scenario: New Applicant with invalid dni
    Given There is a registered applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345678A"
    And I login as "secretary" with password "password"
    When I register a new applicant with email "applicant2@sample.app" and name "name" and lastname "lastname" and dni "1234578A"
    Then The response code is 400

  Scenario: New Applicant with invalid email
    Given There is a registered applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345678A"
    And I login as "secretary" with password "password"
    When I register a new applicant with email "applicant2sample.app" and name "name" and lastname "lastname" and dni "12345678A"
    Then The response code is 400

  Scenario: New Applicant with empty name
    Given There is a registered applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345678A"
    And I login as "secretary" with password "password"
    When I register a new applicant with email "applicant2@sample.app" and name "" and lastname "lastname" and dni "12345674C"
    Then The response code is 400

  Scenario: New Applicant with empty lastname
    Given There is a registered applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345678A"
    And I login as "secretary" with password "password"
    When I register a new applicant with email "applicant2@sample.app" and name "name" and lastname "" and dni "12345674C"
    Then The response code is 400

  Scenario: New Applicant with empty dni
    Given There is a registered applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345678A"
    And I login as "secretary" with password "password"
    When I register a new applicant with email "applicant2@sample.app" and name "name" and lastname "lastname" and dni ""
    Then The response code is 400

  Scenario: New Applicant with empty email
    Given There is a registered applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345678A"
    And I login as "secretary" with password "password"
    When I register a new applicant with email "" and name "name" and lastname "lastname" and dni "12345674C"
    Then The response code is 400
