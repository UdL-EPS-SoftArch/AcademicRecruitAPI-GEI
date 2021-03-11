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
    And It has been created a new applicant with email "email", name "name", lastname "lastname" and dni "12345678C"
