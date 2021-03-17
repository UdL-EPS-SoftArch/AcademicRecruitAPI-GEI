Feature: Phase
  In order to control a job applications phase
  As a secretary of the committee
  I want to create the differences phases for a Job Application

  Background:
    Given There is a registered user with username "secretary" and password "password" and email "email@gmail.com" and name "name" and lastname "lastname" and dni "12345678B"

  Scenario: Create a new phase on jobApplication1
    Given I login as "secretary" with password "password"
    And I register a new job application name "jobApplication1", requirements "requirement1, requirement2" and description "description1"
    When I create a new phase with name "name1", initialDate "07/02/2021" and finishDate "07/06/2021"
    Then The response code is 201
    And It has been created a new phase with number 1 name "name1", initialDate "07/02/2021" and finishDate "07/06/2021"

  Scenario: Create a new phase on jobApplication1 without login
    When I create a new phase with name "name2", initialDate "07/02/2021" and finishDate "07/06/2021"
    Then The response code is 401
    And It phase with name "name2" has not been created.

