import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LowerCaseTeast {

    String input = "";
    String output = "";

    @Given("this is the data for lowercase {string}")
    public void this_is_the_data_for_lowercase(String string) {
        input = string;
    }

    @When("lowercase is called")
    public void lowercase_is_called() {
        output = input.toLowerCase();
    }

    @Then("output for lowercase is {string}")
    public void output_for_lowercase_is(String string) {
        Assert.assertEquals(output, string);
    }

}
