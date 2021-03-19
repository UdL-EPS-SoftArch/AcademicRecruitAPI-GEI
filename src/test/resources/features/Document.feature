Feature: Document
  In order to control a job applications document
  As a member of the committee
  I want to create the different document for a Job Application

  Background:
    Given There is a registered user with username "secretary" and password "password" and email "email@gmail.com" and name "name" and lastname "lastname" and dni "12345678B"

  Scenario: Create a new document on jobApplication1
    Given I login as "secretary" with password "password"
    And I register a new job application name "jobApplication1", requirements "requirement1, requirement2" and description "description1"
    When I create a new document with name "doc1", path "C://path"
    Then The response code is 201
    And It has been created a new document with name "doc1", path "C://path"


  Scenario: Create a new document on jobApplication1
    When I create a new document with name "doc1", path "C://path"
    Then The response code is 401
    And It has not been created a new document with name "doc1", path "C://path"
