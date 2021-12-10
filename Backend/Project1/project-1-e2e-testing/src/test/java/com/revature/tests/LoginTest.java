package com.revature.tests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.revature.pages.EmployeePage;
import com.revature.pages.FinanceManagerPage;
import com.revature.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class LoginTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private EmployeePage employeePage;
	private FinanceManagerPage financeManagerPage;

	@Given("I am at the login page")
	public void i_am_at_the_login_page() {

		System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");

		this.driver = new ChromeDriver();

		this.driver.get("http://localhost:5500");
		this.loginPage = new LoginPage(driver);
	}

	@When("I type in an employee username of {string}")
	public void i_type_in_an_employee_username_of(String string) {
		this.loginPage.getUsernameInput().sendKeys(string);
	}

	@When("I type in the employee password  {string}")
	public void i_type_in_the_employee_password(String string) {
		this.loginPage.getPasswordInput().sendKeys(string);
	}

	@When("I click the Login button")
	public void i_click_the_login_button() {

		this.loginPage.getLoginButton().click();
	}

	@Then("I should be sent to the employee page")
	public void i_should_be_sent_to_the_employee_page() {
	    this.employeePage = new EmployeePage(this.driver);
	    
		String expectedWelcomeHeadingText = "Veiw and Add Reimbursement Menu";
		
		Assertions.assertEquals(expectedWelcomeHeadingText, this.employeePage.getEmployeeHeading().getText());
		
		this.driver.quit();
	}

	@When("I type in an manager username of {string}")
	public void i_type_in_an_manager_username_of(String string) {
		this.loginPage.getUsernameInput().sendKeys(string);
	}

	@When("I type in the manager password  {string}")
	public void i_type_in_the_manager_password(String string) {
		this.loginPage.getPasswordInput().sendKeys(string);
	}

	@Then("I should be sent to the manager page")
	public void i_should_be_sent_to_the_manager_page() {
	    this.financeManagerPage = new FinanceManagerPage(this.driver);
	    
		String expectedWelcomeHeadingText = "Veiw and Approve/Deny Reimbursement Menu";
		
		Assertions.assertEquals(expectedWelcomeHeadingText, this.financeManagerPage.getManagerHeading().getText());
		
		this.driver.quit();
	}

	@When("I type in an employee username of {string};")
	public void i_type_in_an_employee_username_of1(String string) {
		this.loginPage.getUsernameInput().sendKeys(string);
	}

	@Then("I should be see a message of {string}")
	public void i_should_be_see_a_message_of(String string) {
		String actual = this.loginPage.getErrorMessage().getText();
		
	    Assertions.assertEquals(string, actual);
	    
	    this.driver.quit();
	}

}
