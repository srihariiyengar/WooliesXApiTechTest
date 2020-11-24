package steps;

import apiUtils.apiInvocation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utility.RetrieveEndpointProperty;

public class verifyNumberOfRaces {
    private String endPoint;
    private RetrieveEndpointProperty retrieveEndpointProperty = new RetrieveEndpointProperty();
    private apiInvocation apiInvoke = new apiInvocation();
    private Response response;
    XmlPath xmlPath;

    @Given("^I want to know the number of Formula One races in (.+?)$")
    public void i_want_to_know_the_number_of_Formula_One_races_in(String season) {
        endPoint = retrieveEndpointProperty.baseURL("endPoint");
        endPoint = endPoint.concat("/" + season);
    }

    @When("^I retrieve the circuit list for that season$")
    public void i_retrieve_the_circuit_list_for_that_season() {
        response = apiInvoke.getApi(endPoint);
        Assert.assertEquals(200, (response.getStatusCode()));
    }

    @Then("^there should be (.+?) circuits in the list returned$")
    public void there_should_be_circuits_in_the_list_returned(String numberOfCircuits) {
        xmlPath = new XmlPath(response.asString());
        String totalNumberOfRacesForTheSeason = xmlPath.get("MRData.@total");
        System.out.println("totalNumberOfRacesForTheSeason" + totalNumberOfRacesForTheSeason);
        Assert.assertTrue("Number of Races do not match", numberOfCircuits.equals(totalNumberOfRacesForTheSeason));
    }
}
