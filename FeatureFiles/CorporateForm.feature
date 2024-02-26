Feature: Corporate Wellness and Health Form

  Scenario: Validating Corporate Wellness Form with test data
    Given User is on application web page
    When User clicks on Corporate dropdown and selects Health and Wellness Plan
    Then Corporate Employee Health and Wellness page opens
    And Fill the form with test data
