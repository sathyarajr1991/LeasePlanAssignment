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
	 * Verifies that the HTTP response contains the expected product in all items.
	 * @param product the product to look for in the HTTP response
	 * @throws AssertionError if the expected product is not present in any one of the HTTP response list items
	 */
	@Step("^I should see the results displayed for {string} all the response items$")
	public void iSeeTheResultsDisplayedForAllItems(String product) {
		String failMessage = "The endpoint for " + product + " product"
				+ "\n\t is expecting to contain '" + product + "' in all results title"
				+ "\n\tBut some of the titles could not be found.";
		List<Product> products = Arrays.asList(then().extract().as(Product[].class));
		assertThat(products).extracting(prod -> prod.getTitle()
				.toString()
				.toLowerCase())
				.as(failMessage)
				.contains(product);
	}
	
	/**
	 * Verifies that the HTTP response contains the expected product in any one of the item.
	 * @param product the product to look for in the HTTP response
	 * @throws AssertionError if the expected product is present in any one of the HTTP response list items
	 */
	@Step("^I should see the results displayed for {string} any one of the response items$")
	public void iSeeTheResultsDisplayedForAnyOneOfTheItem(String product) {
		String failMessage = "The endpoint for " + product + " product"
				+ "\n\t is expecting to contain '" + product + "' in any one of the results title"
				+ "\n\tBut none of the items contain '"+product+"'";
		List<Product> products = Arrays.asList(then().extract().as(Product[].class));
		boolean isAnyTitleMatched = products.stream()
		        .map(prod -> prod.getTitle().toString().toLowerCase())
		        .anyMatch(title -> title.contains(product.toLowerCase()));
		assertThat(isAnyTitleMatched).as(failMessage).isTrue();

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
}
