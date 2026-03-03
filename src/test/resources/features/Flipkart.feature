Feature: Flipkart E2E Automation Validation

  @Regression @Search
  Scenario Outline: Validate Flipkart Search functionality with Scenario Outline
    Given User is on the Flipkart homepage
    When User searches for "<product>"
    Then User validates the search results
    And User extracts all result titles and prices

    Examples:
      | product |
      | mobile  |
      | laptop  |
