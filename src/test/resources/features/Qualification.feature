Feature: Qualification
    In order to qualify applicants
    As a secretary of the committee
    I want to set qualification and observations

    Background:
        Given There is a registered user with username "secretary" and password "password" and email "email@gmail.com" and name "name" and lastname "lastname" and dni "12345678B"

    Scenario: Qualify an applicant
        Given I login as "secretary" with password "password"
        When I register a new applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345678C"
        And Assigns a new qualification mark "9.0" and an observation "excelent work" to an applicant with email "applicant@sample.app"
        And I create a CommitteeMember with username "secretary" with rank "SECRETARY"
        Then The response code is 201
        And I can check that the mark "9.0" and the observation is "excelent work" to an applicant with email "applicant@sample.app"

    Scenario: Qualify an applicant without being logged as secretary
        Given I'm not logged in
        When I register a new applicant with email "applicant@sample.app" and name "name" and lastname "lastname" and dni "12345678C"
        And I set a new qualification mark "9.0" and an observation "excelent work" to an applicant with email "applicant@sample.app"
        Then The response code is 401
        And The mark with the observation "excelent work" has not been created