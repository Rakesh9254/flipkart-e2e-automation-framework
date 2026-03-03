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

  @Regression @Filter
  Scenario: Validate Flipkart filter functionality
    Given User is on the Flipkart homepage
    When User searches for "mobile"
    And User applies price filter between "10000" and "20000"
    And User applies brand filter
    And User applies RAM filter
    Then User validates the search results limit

  @Regression @Hover
  Scenario: Validate Fashion hover navigation
    Given User is on the Flipkart homepage
    When User hovers over Fashion menu
    And User hovers over Kids menu
    And User clicks on Girls Dresses
    Then User validates the redirected Fashion page title

  @Regression @AddToCart
  Scenario: Validate end-to-end Add to Cart flow
    Given User is on the Flipkart homepage
    When User searches for "mobile"
    And User clicks on the 3rd result in the list
    And User enters pincode "560001" and verifies
    Then User verifies the product title on the new tab
    And User clicks on Add to Cart

  @DataDriven
  Scenario: Validate search functionality driven by Excel
    Given User is on the Flipkart homepage
    When User iterates through Excel sheet "Products" and performs search
    Then Search results for all Excel products are logged
