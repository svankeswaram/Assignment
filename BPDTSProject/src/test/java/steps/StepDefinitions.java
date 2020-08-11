package steps;

import java.util.List;

import model.User;
import org.junit.Assert;

import APITest.BpdtsAPIService;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StepDefinitions {
    private static final Logger LOGGER = LoggerFactory.getLogger(StepDefinitions.class);

    BpdtsAPIService bpdtsAPIService = new BpdtsAPIService();

	@Given("^I print number of users in the system$")
	public void printNumberOfUsers() {
	    try {
            List<User> users = bpdtsAPIService.getAllUsers();
            LOGGER.info( "Number of users present: " + users.size());
        } catch(Exception e) {
	        Assert.fail("Problem executing the step. Please check exception, "+e.getMessage());
        }
	}

	@Then("^I get the user by id \"([^\"]*)\" and validate the response (\\d+)$")
	public void getUserById(String id, int responseCode) {
        try {
            if (responseCode==200) {
                User user = bpdtsAPIService.getUserById(id, responseCode);
                Assert.assertEquals("user id not matched", id, user.getId().toString());
            } else {
                bpdtsAPIService.getUserById(id, responseCode);
            }
        } catch(Exception e) {
            Assert.fail("Problem executing the step. Please check exception,"+e.getMessage());
        }
	}

	@Then("^I get the users by location \"([^\"]*)\" and validate the location$")
	public void getUsersByCity(String city) {
        try {
            List<User> users = bpdtsAPIService.getUsersByLocation(city);

            if (users.size() >= 1) {
                LOGGER.info("Number of users present in the given location are "+users.size());
                for(User user : users) {
                    User userById = bpdtsAPIService.getUserById(user.getId().toString(), 200);
                    Assert.assertEquals("User city not matched for "+userById.getId(), city.toLowerCase(), userById.getCity().toLowerCase());
                }
            } else {
                LOGGER.info("No users present in the given location.");
            }
        } catch(Exception e) {
            Assert.fail("Problem executing the step. Please check exception, "+e.getMessage());
        }
	}

}
