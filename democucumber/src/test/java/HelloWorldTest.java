import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Locale;


public class HelloWorldTest {
    String input = "";

    @Given("this is the data {string}")
    public void this_is_the_data(String string) {
        input = string;
    }

    String output = "";

    @When("uppercase is called")
    public void uppercase_is_called() {
        output = input.toUpperCase();
    }

    @Then("output is {string}")
    public void output_is(String string) {

        Assert.assertEquals(output, string);
    }

}
