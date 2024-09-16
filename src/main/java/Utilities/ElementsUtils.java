package Utilities;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementsUtils {

	public WebDriver driver;

	public ElementsUtils(WebDriver driver) {
		this.driver = driver;

	}

	public WebElement waitForElement(WebElement element, long durationInSeconds) {
		WebElement webElement = null;

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
		webElement = wait.until(ExpectedConditions.elementToBeClickable(element));

		return webElement;

	}

	public void clickOnElement(WebElement element, long durationInSeconds) {

		WebElement element3 = waitForElement(element, durationInSeconds);
		element3.click();

	}

	public String getTextFromElement(WebElement element, long durationInSeconds) {

		WebElement ele = waitForElement(element, durationInSeconds);
		return ele.getText();
	}

	public void typeTextIntoElement(WebElement element, String textToBeTyped, long durationInSeconds) {

		WebElement ele = waitForElement(element, durationInSeconds);

		ele.click();
		ele.clear();
		ele.sendKeys(textToBeTyped);

	}

	public void selectOptionInDropdown(WebElement element, String dropdownOption, long durationInSeconds) {

		WebElement ele = waitForElement(element, durationInSeconds);
		Select select = new Select(element);
		select.selectByVisibleText(dropdownOption);
	}

	public void acceptAlert(WebElement element, long durationInSeconds) {

		Alert alert = waitForAlert(durationInSeconds);
		alert.accept();

	}

	public void dissmissAlert(long durationInSeconds) {

		Alert alert = waitForAlert(durationInSeconds);
		alert.dismiss();
	}

	public Alert waitForAlert(long durationInSeconds) {

		Alert alert = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
			alert = wait.until(ExpectedConditions.alertIsPresent());

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return alert;
	}

	public void mouseHoverAndClick(WebElement element, long durationInSeconds) {

		WebElement webElement = visibilityOfElement(element, durationInSeconds);

		Actions actions = new Actions(driver);
		actions.moveToElement(webElement).click().build().perform();
	}

	public WebElement visibilityOfElement(WebElement element, long durationInSeconds) {
		WebElement webElement = null;

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
			webElement = wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return webElement;
	}

	public List<WebElement> visibilityOfElements(List<WebElement> element, long durationInSeconds) {
		List<WebElement> webElements = null;

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
			webElements = wait.until(ExpectedConditions.visibilityOfAllElements(element));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return webElements;
	}

	public void javaScriptClick(WebElement element, long durationInSeconds) {

		WebElement webElement = visibilityOfElement(element, durationInSeconds);
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].click()", webElement);
	}

	public void javascriptType(WebElement element, long durationInSeconds, String textToBeTyped) {

		WebElement webElement = visibilityOfElement(element, durationInSeconds);
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].value='" + textToBeTyped + "'", webElement);
	}

	public boolean displayStatusofElement(WebElement element, long durationInSeconds) {

		try {
			WebElement webElement = visibilityOfElement(element, durationInSeconds);
			return webElement.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public void checkBoxSelect(WebElement element, long durationInSeconds) {

		try {
			WebElement webElement = visibilityOfElement(element, durationInSeconds);
			if (element.isDisplayed()) {
				element.click();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickEscapeINBetween() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
	}

	// print the list of web elements
	public ArrayList<String> printListOfWebelements(List<WebElement> element, long durationInSeconds) {

		String name = null;
		ArrayList<String> names = new ArrayList<String>();

		List<WebElement> webElement = visibilityOfElements(element, durationInSeconds);
		for (int i = 0; i < webElement.size(); i++) {
			name = webElement.get(i).getText();
			names.add(name);
		}
		System.out.println("The List of Elements present inside the dropdwn are : " + names);
		return names;

	}

	/**
	 * Method to check assertion failures
	 */
	public void assertFailScenario(String message) {
		Assert.fail(message);
	}

	/*
	 * * Print Sie of Elements
	 */
	public int printSizeOFWebElements(List<WebElement> element, long durationInSeconds) {

		List<WebElement> ele = visibilityOfElements(element, durationInSeconds);
		int count = ele.size();
		System.out.println("The count of data avilable is :" + count);
		return count;
	}

	public boolean elementIsDisplayed(WebElement element, long durationInSeconds) {
		System.out.println(element.isDisplayed());
		return element.isDisplayed();
	}

	public boolean isFileDownload(String expectedFileName, String fileExtension, String timeout) {

		// Download Folder path
		String folderName = System.getProperty("user.dir") + File.separator + "downloads";
		File[] listOFiles;
		String fileName;

		boolean fileDownload = false;
		long startTime = Instant.now().toEpochMilli();
		int timepouts = Integer.parseInt(timeout);
		long waitTime = startTime + timepouts;

		while (Instant.now().toEpochMilli() < waitTime) {

			listOFiles = new File(folderName).listFiles();

			for (File file : listOFiles) {
				fileName = file.getName().toLowerCase();

				if (fileName.contains(expectedFileName.toLowerCase()) && fileName.contains(fileExtension.toLowerCase())
						&& !fileName.contains("crdownlaod") && file.lastModified() > startTime) {
					fileDownload = true;
					break;
				}
			}
			if (fileDownload)
				break;
		}
		System.out.println("Downloaded File is prssent and validated");
		return fileDownload;

	}

	public void refreshThePage() {

		driver.navigate().refresh();
	}

	public void waitTime() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
