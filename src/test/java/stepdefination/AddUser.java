package stepdefination;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import Pages.addUserPage;
import browserFactory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class AddUser {
	
	public WebDriver driver;
	public addUserPage aup;
	
	
	@Given("/^I am on the web tables page$/")
	public void i_am_on_the_web_tables_page() throws Throwable {

		
		driver =DriverFactory.getDriver();	
		aup = new addUserPage(driver);
		
		String acutalTitle = aup.getTitleOfThePage();
		System.out.println("The title of the page is : "+acutalTitle );
		Assert.assertTrue(acutalTitle, acutalTitle.contains("Protractor practice"));

		
	}
	
	@When("/^I click on the Add User button$/")
	public void i_click_on_the_Add_User_button() {
		
		aup.clickOnAddUserButton();
		String parent = driver.getWindowHandle();
		System.out.println("The window ID is :" + parent);
	}
	
	@When("/^I fill in the form with the following details (.*) (.*) (.*) (.*) (.*) (.*) (.*) (.*) all$/")
    public void i_fill_in_the_form_with_the_following_details_all(String firstName,String lastName, String userName,String password,String customer,String role,String email,String cellPhone) throws InterruptedException {
		

		String parent1 = driver.getWindowHandle();
		System.out.println("Next window ID is : " + parent1);
		
		aup.enterFirstName(firstName);
		aup.enterLastName(lastName);
		aup.enterUserName(userName);
		aup.enterPassword(password);
		aup.selectCustomer(customer);
		aup.selectRole(role);
		aup.enterEmail(email);
		aup.enterCellPhone(cellPhone);
		
	}
	
	@And("/^I click the save button$/")
	public void i_click_the_save_button() {
		
		aup.clickSave();
		
	}
	
	@Then("/^I should see (.*) in the user table$/")
	public void i_should_see_in_the_user_table(String userName) {
		
		aup.verifyUserName(userName);
	}
	
	@When("/^I search for the user (.*) in the user table$/")
	public void i_search_for_the_user_novak_in_the_user_table(String userName) {
	    
		aup.verifyUserName(userName);
	}

	@When("/^I click the delete button for the user (.*)$/")
	public void i_click_the_delete_button_for_the_user_novak(String userName) {
	  
	  aup.clickOnDeleteButton(userName);  
	}

	@When("/^I confirm the deletion$/")
	public void i_confirm_the_deletion() {
	    
		aup.clickOnOKButton();
	}

	@Then("/^I should not see (.*) in the user table$/")
	public void i_should_not_see_novak_in_the_user_table(String userName) {
		
		aup.verifyUserName(userName);
				
	}

}

