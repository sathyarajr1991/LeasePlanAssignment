### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/demo/{product} for getting the products.
### Available products: "orange", "apple", "pasta", "cola"
### Prepare Positive and negative scenarios

@feature:search
Feature: Search for the product

  @search @positive
  Scenario Outline: As an API user, when I call invalid endpoints, I should get a not found message in the response
    When I call "<product>" endpoint
    Then I should see the response code as <status>
    And I should get the response has not found
    Examples:
      | product | status |
      | car     | 404    |

  @search @positive
  Scenario Outline: Search test to verify HTTP response containing searched product
    When I call "<product>" endpoint
    Then I should see the response code as <status>
    And I should see the results displayed for "<product>" in any one of the response items
    Examples:
      | product | status | jsonSchema      	|
      | orange  | 200    |search_product  	|
      
  @search @negative
  Scenario Outline: As an API user, when I call valid product endpoints, I should get the corresponding searched product in the response
    When I call "<product>" endpoint
    Then I should see the response code as <status>
    And I should see the results displayed for "<product>" in all the response items
    And I verify the schema should match with the specification defined in "<jsonSchema>"
    Examples:
	  | product | status | jsonSchema      |
      | orange  | 200    | search_product  |
      | apple   | 200    | search_product  |
      | pasta   | 200    | search_product  |
      | cola    | 200    | search_product  |

 
