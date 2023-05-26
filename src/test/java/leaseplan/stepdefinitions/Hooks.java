package leaseplan.stepdefinitions;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.core.environment.EnvironmentSpecificConfiguration.from;

/**
 * The Hooks class contains methods for initialising the base URI before other 
 * class uses the response .
 *  @author Sathya 
 */
public class Hooks {

  @Before
  public static void init() {
	  EnvironmentVariables environmentVariables = Injectors.getInjector()
              .getInstance(EnvironmentVariables.class);
	  RestAssured.baseURI =from(environmentVariables).getProperty("baseurl");
  }
}
