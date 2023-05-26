package leaseplan.actions;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import leaseplan.common.EndPoint;
import leaseplan.pojo.product.Product;

import static net.serenitybdd.rest.SerenityRest.then;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

/**
 * The SearchProductActions class contains methods for testing the search product functionality of the API.
 * This class provides steps for calling the search endpoint, verifying search results, and handling errors.
 * @author Sathya
 */
public class SearchProductActions {
	
	static {
		RestAssured.basePath = EndPoint.SEARCH.toString();
	}
	
	/**
	 * Calls the search endpoint with the given product parameter.
	 * @param product the product parameter to send in the search request
	 * @return a Response object representing the HTTP response from the endpoint
	 */
	@Step("^I call {string} endpoint$")
	public void iCallEndpoint(String product) {	
		SerenityRest.get(product);
	}
	
	/**
	 * Verifies that the HTTP response contains the expected product.
	 * @param product the product to look for in the HTTP response
	 * @throws AssertionError if the expected product is not present in the HTTP response
	 */
	@Step("^I should see the results displayed for {string}$")
	public void iSeeTheResultsDisplayedFor(String product) {
		String failMessage = "The endpoint for " + product + " product"
				+ "\n\t is expecting to contain " + product + " in all results title"
				+ "\n\tBut some of the titles could not be found.";
		List<Product> products = Arrays.asList(then().extract().as(Product[].class));
		assertThat(products).extracting(prod -> prod.getTitle()
				.toString()
				.toLowerCase())
				.as(failMessage)
				.contains(product);
	}
	
	/**
	 * Verifies that the HTTP response indicates a product not found.
	 * @throws AssertionError if the expected body message is not present in the HTTP response
	 */
	@Step("^I should see the results displayed for not found$")
	public void iShouldGetTheResponse() {
		String message = "Not found";
		assertThat(then().body("detail.message", Matchers.is(message)));
	}

	/**
	 * Calls the search endpoint without providing the product.
	 * @return a Response object representing the HTTP response from the endpoint
	 */
	@Step("^I call the get search test endpoint$")
	public void iCallTheGetSearchTestEndpoint() {
		SerenityRest.get();
	}

	/**
	 * Verifies that the HTTP response contains an unauthorized error for the search endpoint without the product.
	 * @return a Response object representing the HTTP response from the endpoint
	 */
	@Step("^I should get unauthorized error in search result")
	public void iShouldGetUnauthorizedErrorInSearchResult() {
		String message = "Not authenticated";
		assertThat(then().body("detail", Matchers.is(message)));
	}
}
