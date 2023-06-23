import io.cucumber.java8.En;
import org.junit.Assert;
import rtx.hsan.sabonchi.SimpleInterest;

public class SimpleInterestTest implements En {

    Double input_1, input_2, input_3, output;
    SimpleInterest simpleInterest;

    public SimpleInterestTest() {

        Given("I have a Simple interest calculator", () -> {
            simpleInterest = new SimpleInterest();
        });

        When("calculate simple interest {double} {double} {double}", (Double double1, Double double2, Double double3) -> {
            input_1 = double1;
            input_2 = double2;
            input_3 = double3;
        });

        Then("Simple interest value is {double}", (Double double1) -> {
            Double output = simpleInterest.calculate(input_1, input_2, input_3);
            Assert.assertEquals(output, double1
            );
        });

    }


}
