**BDD Cucumber with serenity**

*Serenity Cucumber is a framework that combines the behavior-driven development (BDD) approach of Cucumber with the powerful reporting capabilities of Serenity.*

*List of tools used to build framework:*


**FrameWork structure**
- **EndPoint.java** : *An enum class that contains all the base paths and helper methods for retrieving endpoints.*
- **Product.java** : *A POJO (Plain Old Java Object) class that defines the attributes of a product and provides setter and getter methods.*
- **TestRunner.java** : *The execution starts from the TestRunner Java file, which is responsible for running the tests using Cucumber and Serenity.*
- **CommonActions.java** : *This class contains common methods used for all the endpoints. These methods are reusable and provide common functionality for different test scenarios.*
- **SearchProductActions.java** : *This class contains methods for testing the search product functionality of the API. It includes steps for calling the search endpoint, verifying search results, and handling errors specific to the search functionality.*
- **Hooks.java** : *This class contains an initialization method that initializes the base URI before other classes use the service. It is used to set up the necessary configuration or environment for the tests.*
- **SearchProductSteps.java** : *This is a step definition file that defines the step implementations for the BDD scenarios mentioned in the "search_product.features" file. It maps the steps in the feature file to the corresponding Java methods.*
- **search_product.features** : *This feature file contains all the test cases or scenarios written in the Gherkin language. Each scenario describes a specific test case for the search product functionality.*
- **schema folder** : *This folder contains the valid schema specification files used for API response schema validation. The schema files are usually in JSON format.*
- **target folder** : *After executing the tests, the Serenity reports are generated and stored in this folder. The reports provide detailed information about the test execution, including test results, logs, and screenshots.*
- **Serenity.conf** : *This configuration file contains environment variables and settings for the framework, such as the base URL for different environments.*

### The project directory structure

```Gherkin
src
 + main
  + java
    + leaseplan
      + common                    
      + endpoint
          EndPoint                /* Enum class contains all endpoints and supported methods.*/
      + pojo                      /*Package for all domain model attributes implementation*/
        + product
          Product
 + test
  + java
    + leaseplan
      TestRunner                  /*Test runners and supporting code*/
    + leaseplan
      + actions                   /*Package for API calls and User actions*/
        CommonActions             /*Domain model package consisting of all functionality*/
        SearchProductActions      /*API calls and User actions for search product*/
      + stepdefinitions           /*Step definitions for the BDD features*/
        LeasePlanHooks            /*Hooks file where it initializes the base endpoint*/
        SearchProductSteps        /*Common step definitions for the search class*/
  + resources
    + features
      + search                    /*Feature files directory*/
        search_product.feature    /*Feature containing BDD scenarios*/
    + schema                      /*Folder containing JSON schema for API schema validation*/
      search_product.json
    serenity.conf                 /*Configurations file*/
```

**How to write test cases ?**
- *Create a new feature file under features folder.*
- *Each Scenario / scenario outline will be considered as one test case. we use scenario outline when we have to add validation under Example tag in the test cases.*
- *Externalize all the global variables, environment specific variables in `Serenity.conf`*
- *Test case is written in Gherkin language with step-definition . Just follow `Given`, `When`,`And`, `Then`*
- *Write common test case method in `Commonactions` and `SearchProductActions`*
- *Tag each test cases to group, like : `@search`*

## Executing the tests 
*Run the following command from the root directory:*

```
mvn clean verify
```
*The test results will be recorded here `target/site/serenity/index.html`.*
*Please run the below command from root directory to open the result after execution.*

```bash
open target/site/serenity/index.html 
```
## Gitlab report

- *Go to CI/CD → Pipelines, select the pipeline, and navigate to the test tab to find the online JUnit report.*
- *Go to download artifact and open the Serenity report.*

### Additional configurations

*Additional command line parameters can be passed to switch the application environment.*

*Run the following command:*

```
Run mvn clean verify -Denvironment=dev
```

*Configurations for different environments are set in the test/resources/serenity.conf file. In real-time projects, each environment can be configured with its base URL to run the tests based on different environments.*

```
environments {
  default {
    baseurl = "https://waarkoop-server.herokuapp.com"
  }
  dev {
    baseurl = "https://waarkoop-server.herokuapp.com"
  }
  staging {
    baseurl = "https://waarkoop-server.herokuapp.com"
  }
}
```

###Modifications on framwork are :

- *Restructure code : Endpoints and environment variables are externalized*   
- *Test suite and test scenarios : for better coverage*
- *`History` folder is removed*
- *`Build.gradle` is removed(as we have maven )*
- *`gradlew` is removed(as we have maven )*
- *`gradlebat` is removed(as we have maven )*
- *Removed some settings in `pom.xml`: for clean up* 
- *`Upgraded serenity`: resporting is informative*
- *`Restructure code`: constants and environment variables  are externalized*   
- *`Test suite and test scenarios`: for better coverage*
- *`CarsAPI` is removed as its not required*


## Requirement in feature file 
- *Please use the endpoint `GET https://waarkoop-server.herokuapp.com/api/v1/search/test/{product}` to retrieve the products.*
- *Available products: `orange`, `apple`, `pasta`, `cola`*
- *Prepare positive and negative scenarios*

**Test case implementation explaination:**

**`Note`** : *Although readme files ideally does not have test case explainations , beacuse the assignment question was twisted .* 
*I wanted to justify my point of view and making negative test case fail .*

#`Scenario:`
     `When` *he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/orange"*
     `Then` *he sees the results displayed for apple*
     `When` *he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/apple"*
     `Then` *he sees the results displayed for mango*
     `When` *he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/car"*
     `Then` *he doesn not see the results*

###`Scenario 1 :`
- `Expected :`*As per the requirement when we call GET API with apple , I am supposed to see mango results.* 
- `Actual :` *I verify mango is not present in the available list (as per requirement).*
- `Reason :` *hence I have failed the test case with message search for available product list.*

`Available products:` *`orange`, `apple`, `pasta`, `cola`*
*as its an issue the test case would fail now , when the fix is given for the issue the test case would pass*

###`Scenario 2:`
- `Expected :` *As per the requirement when we call GET API for car , results do not comeup*
- `Actual :` *while I search for car , I validate the error message and pass the test case*
*As per the question the car API gives error message , hence marked as positive test case*

###`Scenario 3:` *( extra test case)*
- `Scenario considered` : *search for GET orange API and verify the response schema* 
- `Actual` : *while I GET call for orange , I validate the orange response chema with valid sepecification* 
