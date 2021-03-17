Feature: Qualification
    In order to qualify applicants
    As a secretary of the committee
    I want to set qualification and observations

    Background:
        Given There is a registered user with username "secretary" and password "password" and email "email@gmail.com" and name "name" and lastname "lastname" and dni "12345678B"

    Scenario: Qualify an applicant
        Given I login as "secretary" with password "password"
        When I set a new qualification mark "9.0" and an observation "excelent work"
        Then The response code is 201
        And I can check that the mark "9.0"
        And I can check that the observation "excelent work" is correct

    Scenario: Qualify an applicant without being logged as secretary
        Given I'm not logged in
        When I set a new qualification mark "9.0" and an observation "excelent work"
        Then The response code is 401
        And I can not check that the mark "9.0" is correct
        And I can not check that the observation "excelent work" is correct