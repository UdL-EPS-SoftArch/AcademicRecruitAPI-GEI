Feature: Committee
  In order to assign the ranks
  As an administrator
  I want to assign ranks to users in the Committee

  Background:
    Given There is a registered administrator with username "admin" and password "password" and email "admin@sample.app" and name "name" and lastname "lastname" and dni "12345678A"

  Scenario: Assign Secretary Rank
    And I login as "admin" with password "password"
    When I assign a rank "SECRETARY" to a user
    Then The response code is 201
    And It has been assigned the rank "SECRETARY" to a user

  Scenario: Assign President Rank
    And I login as "admin" with password "password"
    When I assign a rank "PRESIDENT" to a user
    Then The response code is 201
    And It has been assigned the rank "PRESIDENT" to a user

  Scenario: Assign Vocal Rank
    And I login as "admin" with password "password"
    When I assign a rank "VOCAL" to a user
    Then The response code is 201
    And It has been assigned the rank "VOCAL" to a user