Feature: Job Application
  In order to control job applications
  As a secretary of the committee
  I want to create a job applciation

  Background:
    Given There is a registered user with username "secretary" and password "password" and email "email@gmail.com" and name "name" and lastname "lastname" and dni "12345678B"

  Scenario: Publish a new job application
    Given I login as "secretary" with password "password"
    When I register a new job application name "jobApplication1", requirements "requirement1, requirement2" and description "description1"
    Then The response code is 201
    And It has been created a new job application with name "jobApplication1", requirements "requirement1, requirement2" and description "description1"
    And I can check the job application

  Scenario: Publish a new job application without being logged
    Given I'm not logged in
    When I register a new job application name "jobApplication2", requirements "requirement1, requirement2" and description "description2"
    Then The response code is 401
    And It has not been created a user with name "jobApplication2"

