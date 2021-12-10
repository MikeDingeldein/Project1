package com.revature.tests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.revature.pages.EmployeePage;
import com.revature.pages.FinanceManagerPage;
import com.revature.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddReimbursementTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private EmployeePage employeePage;
	private FinanceManagerPage financeManagerPage;

	@Given("I am at the employee reimbursement page")
	public void i_am_at_the_employee_reimbursement_page() {
		System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");

		this.driver = new ChromeDriver();

		this.driver.get("http://localhost:5500");
		this.loginPage = new LoginPage(driver);
		
		this.loginPage.getUsernameInput().sendKeys("MikeDingeldein");
		this.loginPage.getPasswordInput().sendKeys("password");
		
		this.loginPage.getLoginButton().click();
	}
	
	@When("I type reimbursement amount of {string}")
	public void i_type_reimbursement_amount_of(String string) {
		this.employeePage = new EmployeePage(this.driver);
		this.employeePage.getReimbursementAmount().sendKeys(string);
	}

	@When("I type in a reimbursement type of {string}")
	public void i_type_in_a_reimbursement_type_of(String string) {

		this.employeePage.getReimbursmentType().sendKeys(string);
	}

	@When("I type in a reimbursement description of {string}")
	public void i_type_in_a_reimbursement_description_of(String string) {
		this.employeePage.getReimbursmentDescription().sendKeys(string);
	}

	@When("I choose a reimbursement receipt file")
	public void i_choose_a_reimbursement_receipt_file() {
		// Write code here that turns the phrase above into concrete actions
		
		this.employeePage.getReimbursmentReciept().sendKeys("C://Users//miked//OneDrive//Desktop/purchase-receipt-jpg.jpg");
		

	}

	@When("I click the Add Reimbursement Button")
	public void i_click_the_add_reimbursement_button() {
		this.employeePage.getAddReimbursementButton().click();
	}

	@Then("I should see the added reimbursement")
	public void i_should_see_the_added_reimbursement() throws InterruptedException {
	    this.employeePage = new EmployeePage(this.driver);
	    
		String expectedAddedReceiptDescriptionText = "E2E test";
		
		Assertions.assertEquals(expectedAddedReceiptDescriptionText, this.employeePage.getFirstReimbursementDescrition().getText());
		
		this.driver.quit();
	}
	
	@Then("I should be see an error message of {string}")
	public void i_should_be_see_an_error_message_of(String string) {
		String expectedErrorMessageText = string;
		
		Assertions.assertEquals(expectedErrorMessageText, this.employeePage.getErrorMessage().getText());
		
		this.driver.quit();
	}

}
