import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
        tags={"@SmokeTest"})

public class TestRunner {
    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File("src/test/config/extent-config.xml"));
    }
}
