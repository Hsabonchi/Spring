import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import rtx.hsan.sabonchi.ProfitCalculator;

public class ProfitCalculatorTest {

    Double input_1, input_2;
    String output = "";
    ProfitCalculator profitCalculator;

    @Given("I have a profit calculator")
    public void i_have_a_profit_calculator() {
        profitCalculator = new ProfitCalculator();
    }

    @When("I add {double} and {double}")
    public void i_add_and(Double double1, Double double2) {
        input_1 = double1;
        input_2 = double2;
    }

    @Then("the result should be {string}")
    public void the_result_should_be(String string) {

        output = profitCalculator.calculate(input_2, input_1);
        Assert.assertEquals(output, string);
    }

}
