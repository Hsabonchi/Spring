package chat;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources",
        glue="classpath:com.robust.springboot.cucumber",
        stepNotifications = true,
        tags = "@RegressionTest",
        publish = false
)
public class CucumberRunnerTest {
}
