import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class FXRateTest {

    FxRateTranslator fxRateTranslator;
    Double input_1, input_2, output;



    @Given("I have a FxRateTranslator calculator")
    public void i_have_a_fx_rate_translator_calculator() {
        fxRateTranslator = new FxRateTranslator();
    }



    @When("I multiply  {double} with {double}")
    public void i_multiply_with(Double double1, Double double2) {
        input_1 = double1;
        input_2 = double2;

    }

    @Then("the fix rate value should be {double}")
    public void the_fix_rate_value_should_be(Double double1) {
        output = fxRateTranslator.calculateFxRate(input_1, input_2);
        Assert.assertEquals(double1, output);
    }



}
