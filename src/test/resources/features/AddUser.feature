#Author: arifsyed83.email@your.domain.com

@tag
Feature: Validation for Adding /Deleting users functionality

  @tag1
  Scenario Outline: User management in the web table
    Given I am on the web tables page
    When I click on the Add User button
    And I fill in the form with the following details <FirstName> <LastName> <UserName> <Password> <Customer> <Role> <Email> <CellPhone> all
    And I click the save button
    Then I should see <UserName> in the user table
    Examples: 
      | FirstName  | LastName | UserName | Password | Customer   | Role    | Email                | CellPhone |
      | John       | Doe      | johndoe  | password | CompanyAAA | Admin   | john.doe@example.com | 123456789 |
    

  @tag2
  Scenario Outline: Delete the user <UserName> from the table and validate the user has been deleted
    Given I am on the web tables page
    When I search for the user <UserName> in the user table
    And I click the delete button for the user <UserName>
    And I confirm the deletion
    Then I should not see <UserName> in the user table
    
     Examples: 
      | UserName | 
      | novak    |
