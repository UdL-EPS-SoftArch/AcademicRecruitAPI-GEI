Feature: Document
  In order to control a job applications document
  As a member of the committee
  I want to create the different document for a Job Application

  Background:
    Given There is a registered user with username "secretary" and password "password" and email "email@gmail.com" and name "name" and lastname "lastname" and dni "12345678B"

  Scenario: Create a new document on jobApplication1
    Given I login as "secretary" with password "password"
    And I register a new applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345678C"
    And I create a new phase with name "phaseExample", initialDate "01/01/2020" and finishDate "31/12/2020"
    When I create a new document with name "doc1", path "C://path" for applicant "applicant@sample.app"
    And I associate the previous document to phase "phaseExample"
    Then The response code is 204
    And It has been created a new document with name "doc1", path "C://path" that is assigned to phase "phaseExample" and belongs to an applicant with email "applicant@sample.app"


  Scenario: Create a new document on jobApplication1
    Given I login as "secretary" with password "password"
    And I register a new applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345678C"
    And I create a new phase with name "phaseExample", initialDate "01/01/2020" and finishDate "31/12/2020"
    And I'm not logged in
    When I create a new document with name "doc1", path "C://path" for applicant "applicant@sample.app"
    Then The response code is 401
    And It has not been created a new document with name "doc1", path "C://path"
