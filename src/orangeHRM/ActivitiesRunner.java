package orangeHRM;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Features",
		glue = {"stepDefinitions"},
		tags = {"@OrangeHRM"},
		strict = true,
		plugin = { "pretty", "html:target/cucumber-reports" },
		monochrome = true
		)
public class ActivitiesRunner {

//Empty class
}
