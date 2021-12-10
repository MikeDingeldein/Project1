package com.revature.tests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import com.revature.pages.EmployeePage;
import com.revature.pages.FinanceManagerPage;
import com.revature.pages.LoginPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogoutTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private EmployeePage employeePage;
	private FinanceManagerPage financeManagerPage;
	
	@When("I am sent to the employee page")
	public void i_am_sent_to_the_employee_page() {
	    this.employeePage = new EmployeePage(this.driver);
	    
		String expectedWelcomeHeadingText = "Veiw and Add Reimbursement Menu";
		
		Assertions.assertEquals(expectedWelcomeHeadingText, this.employeePage.getEmployeeHeading().getText());
	}
	
	@When("I am be sent to the manager page")
	public void i_am_be_sent_to_the_manager_page() {
	    this.financeManagerPage = new FinanceManagerPage(this.driver);
	    
		String expectedWelcomeHeadingText = "Veiw and Approve/Deny Reimbursement Menu";
		
		Assertions.assertEquals(expectedWelcomeHeadingText, this.financeManagerPage.getManagerHeading().getText());
	}

	@Then("I click the logout button")
	public void i_click_the_logout_button() {

		this.employeePage.getLogoutButton().click();
	
	}
	
	@Then("I return to the login page")
	public void i_return_to_the_login_page() {
	    
		String expectedWelcomeHeadingText = "Reimbursement";
		
		Assertions.assertEquals(expectedWelcomeHeadingText, this.loginPage.getLoginHeading().getText());
		
		this.driver.quit();
	}

	@Then("I click the manager page logout button")
	public void i_click_the_manager_page_logout_button() {

		this.financeManagerPage.getLogoutButton().click();
	
	}
}
