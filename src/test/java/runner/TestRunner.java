package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

	@RunWith(Cucumber.class)
	@CucumberOptions(
	        features = {
	                "src/test/resources/features/AddUser.feature"
	        },
	        glue = { "stepdefination", "hooks" },
	        publish = true,
	        plugin = { 
	                "pretty", 
	                "html:target/CucumberReports/CucumberReport.html",
	                "json:target/JSONReports/Cucumber.json",
	                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
					"rerun:target/failed_scenarios.txt"
	        },
	        monochrome = true,
	        dryRun = false
	)
	public class TestRunner {

	}