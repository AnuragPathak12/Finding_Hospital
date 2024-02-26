Feature: Surgeries

  Scenario: Listing surgeries they provide
    Given User is on application homepage
    When User clicks on surgery option
    Then List of surgeries available will be displayed
