package leaseplan.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import leaseplan.actions.CommonActions;
import leaseplan.actions.SearchProductActions;
import net.thucydides.core.annotations.Steps;

/**
 * @author Sathya 
 * 
 */
public class SearchProductSteps {

	@Steps
	public CommonActions commonActions;

	@Steps
	public SearchProductActions searchProductActions;

	@When("^I call \"(.*)\" endpoint$")
	public void iCallTheGetSearchProductEndpoint(String product) {
		searchProductActions.iCallEndpoint(product);
	}

	@When("^I call the get search test endpoint")
	public void iCallTheGetSearchTestEndpoint() {
		searchProductActions.iCallTheGetSearchTestEndpoint();
	}

	@Then("^I should see the response code as (.*)$")
	public void iShouldSeeTheResponseCode(int responseCode){
		commonActions.iShouldGetTheResponseCode(responseCode);
	}

	@Then("^I verify the product list should not be empty in Search results")
	public void iVerifyTheProductListShouldNotBeEmptyInSearchResults() {
		commonActions.responseShouldNotBeEmptyList();
	}

	@Then("^I should see the results displayed for \"(.*)\"$")
	public void iShouldSeeTheResultsDisplayedForGivenProduct(String product) {
		searchProductActions.iSeeTheResultsDisplayedFor(product);
	}

	@Then("^I should get the response has not found")
	public void iShouldGetTheResponseHasNotFound() {
		searchProductActions.iShouldGetTheResponse();
	}

	@Then("^I should get unauthorized error in search result")
	public void unauthorizedErrorShouldBeDisplayedInSearchResult() {
		searchProductActions.iShouldGetUnauthorizedErrorInSearchResult();
	}

	@And("^I verify schema should match with the specification defined in \"(.*)\"$")
	public void verifySchemaMatchWithTheSpecification(String spec) {
		commonActions.verifyResponseSchema(spec);
	}
}
