package browserFactory;

import java.time.Duration;
import java.util.Properties;

import org.apache.commons.collections4.Get;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utilities.ConfigReader;
import Utilities.commonUtils;

public class DriverFactory {

	public static WebDriver driver;

	public static WebDriver initializeBrowser(String browser) {

		Properties prop = ConfigReader.initializePrperties();
		//String urlName = prop.getProperty(url);

		if (browser.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();
			driver.get(prop.getProperty("url"));
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(commonUtils.Pageload_Wait_Time));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(commonUtils.Implicit_Wait_Time));
			// driver.manage().window().setSize(new Dimension(1400,900));

		} else if (browser.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();

		}

		return driver;

	}

	public static WebDriver getDriver() {
		return driver;
	}

}
