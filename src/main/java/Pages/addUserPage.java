package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.ElementsUtils;
import Utilities.commonUtils;
import net.bytebuddy.asm.MemberSubstitution.FieldValue;

public class addUserPage {

	WebDriver driver;
	public ElementsUtils eu;

	public addUserPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		eu = new ElementsUtils(driver);

	}

	@FindBy(xpath = "//button[@type='add']")
	private WebElement addUserButton;

	@FindBy(xpath = "//form/table/tbody/tr/td[text()='First Name']/following::input[@type='text' and @name='FirstName']")
	private WebElement firstNameField;

	@FindBy(xpath = "//table/tbody/tr/td[text()='Last Name']/following::*[@type='text' and @name='LastName']")
	private WebElement lastNameField;

	@FindBy(xpath = "//table/tbody/tr/td[text()='User Name']/following::*[@type='text' and @name='UserName']")
	private WebElement userNameField;

	@FindBy(xpath = "//table/tbody/tr/td[text()='Password']/following::*[@type='password' and @name='Password']")
	private WebElement passwordField;

	// Customer radio buttons
	@FindBy(xpath = "//table/tbody/tr/td[text()='Customer']/following::label[text()='Company AAA']")
	private WebElement companyAAARadio;

	@FindBy(xpath = "//table/tbody/tr/td[text()='Customer']/following::label[text()='Company BBB']")
	private WebElement companyBBBRadio;

	@FindBy(xpath = "//table/tbody/tr/td[text()='Role']/following::td/select[@name='RoleId']")
	private WebElement roleDropdown;

	@FindBy(xpath = "//table/tbody/tr/td[text()='E-mail']/following::*[@type='email' and @name='Email']")
	private WebElement emailField;

	@FindBy(xpath = "//table/tbody/tr/td[text()='Cell Phone']/following::*[@type='text' and @name='Mobilephone']")
	private WebElement cellPhoneField;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveButton;

	@FindBy(xpath = "//tbody/tr/td[3]")
	private List<WebElement> userNameList;

	@FindBy(xpath = "(//tbody/tr/td/button/i[@class='icon icon-remove'])[1]")
	private WebElement deleteButton;

	@FindBy(xpath = "//button[text()='OK']")
	private WebElement okButtonConfirmation;

	public String getTitleOfThePage() {

		String ele = driver.getTitle();
		return ele;
	}

	public void clickOnAddUserButton() {

		eu.clickOnElement(addUserButton, commonUtils.Implicit_Wait_Time);
	}

	// Methods to interact with fields
	public void enterFirstName(String name) {

		eu.typeTextIntoElement(firstNameField, name, commonUtils.Implicit_Wait_Time);

	}

	public void enterLastName(String lastName) {
		eu.typeTextIntoElement(lastNameField, lastName, commonUtils.Implicit_Wait_Time);

	}

	public void enterUserName(String userName) {
		eu.typeTextIntoElement(userNameField, userName, commonUtils.Implicit_Wait_Time);

	}

	public void enterPassword(String password) {
		eu.typeTextIntoElement(passwordField, password, commonUtils.Implicit_Wait_Time);

	}

	public void selectCustomer(String customer) {
		if (customer.equals("CompanyAAA")) {
			companyAAARadio.click();
		} else if (customer.equals("CompanyBBB")) {
			companyBBBRadio.click();
		}
	}

	public void selectRole(String role) {

		eu.selectOptionInDropdown(roleDropdown, role, commonUtils.Implicit_Wait_Time);

	}

	public void enterEmail(String email) {
		eu.typeTextIntoElement(emailField, email, commonUtils.Implicit_Wait_Time);

	}

	public void enterCellPhone(String cellPhone) {
		eu.typeTextIntoElement(cellPhoneField, cellPhone, commonUtils.Implicit_Wait_Time);

	}

	public void clickSave() {
		eu.clickOnElement(saveButton, commonUtils.Implicit_Wait_Time);

	}

	public void verifyUserName(String targetUsername) {

		boolean isUsernameFound = false;

		for (WebElement usernameElement : userNameList) {
			String username = usernameElement.getText(); // Get the text of each username element
			if (username.equals(targetUsername)) {
				isUsernameFound = true;
				System.out.println("Username found: " + targetUsername);
				break; // Exit the loop if a match is found
			}
		}

		if (!isUsernameFound) {
			System.out.println("Username not found: " + targetUsername);
		}
	}

	public void clickOnDeleteButton(String targetUsername) {

		WebElement novakElement = driver.findElement(By.xpath(
				"//tbody/tr/td[text()='" + targetUsername + "']/following::button/i[@class='icon icon-remove']"));

		eu.clickOnElement(novakElement, commonUtils.Implicit_Wait_Time);

	}

	public void clickOnOKButton() {

		eu.clickOnElement(okButtonConfirmation, commonUtils.Implicit_Wait_Time);
	}
}
