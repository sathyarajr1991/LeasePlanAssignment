### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/demo/{product} for getting the products.
### Available products: "orange", "apple", "pasta", "cola"
### Prepare Positive and negative scenarios

@feature:@search @product
Feature: Search for the product

 @positive
Scenario Outline: As a API user, when I call valid product endpoints, I should get corresponding values
	When I call "<product>" endpoint
	Then I should see the response code as <status>
	And I should see the results displayed for "<product>"
	And I verify schema should match with the specification defined in "<jsonSchema>"
	Examples:
  	| product|status|jsonSchema	   |
  	| orange |200	|search_product|
  	| apple  |200	|search_product|
  	| pasta  |200  	|search_product|
  	| cola   |200   |search_product|

  @negative1
  Scenario Outline: As a API user, when I call invalid endpoints, I should get not found message in the response
    When I call "<product>" endpoint
    Then I should see the response code as <status>
    And I should get the response has not found
    Examples:
    | product |status|
	| car     |404	 |

 @negative2
  Scenario Outline: Search test without product
    When I call the get search test endpoint
    Then I should see the response code as <status>
    And I should get unauthorized error in search result
     Examples:
    | status |
    | 401    |
    
