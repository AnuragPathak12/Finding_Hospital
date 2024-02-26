Feature: Searching doctors nearby

  Scenario: Listing required doctors
    Given User navigates to application page
    When User enters required location and speciality
    And User applies the filter to the result
    Then List of available doctors will be displayed
