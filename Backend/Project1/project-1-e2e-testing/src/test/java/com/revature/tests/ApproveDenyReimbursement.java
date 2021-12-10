package com.revature.tests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.revature.pages.EmployeePage;
import com.revature.pages.FinanceManagerPage;
import com.revature.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApproveDenyReimbursement {

	private WebDriver driver;
	private LoginPage loginPage;
	private EmployeePage employeePage;
	private FinanceManagerPage financeManagerPage;
	
	@Given("I am at the manager page with a reimbursement to approve\\/deny")
	public void i_am_at_the_manager_page_with_a_reimbursement_to_approve_deny() {
		System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");

		this.driver = new ChromeDriver();

		this.driver.get("http://localhost:5500");
		this.loginPage = new LoginPage(driver);
		
		this.loginPage.getUsernameInput().sendKeys("MikeDingeldein");
		this.loginPage.getPasswordInput().sendKeys("password");
		
		this.loginPage.getLoginButton().click();
		
		this.employeePage = new EmployeePage(this.driver);
		this.employeePage.getReimbursementAmount().sendKeys("99.99");
		
		this.employeePage.getReimbursmentType().sendKeys("OTHER");
		
		this.employeePage.getReimbursmentDescription().sendKeys("Approve/Deny Test");
		
		this.employeePage.getReimbursmentReciept().sendKeys("C://Users//miked//OneDrive//Desktop/purchase-receipt-jpg.jpg");
		
		this.employeePage.getAddReimbursementButton().click();
		
		this.employeePage.getLogoutButton().click();
		
//		this.loginPage = new LoginPage(driver);
		
		this.loginPage.getUsernameInput().sendKeys("MikesWallet");
		this.loginPage.getPasswordInput().sendKeys("password");

		this.loginPage.getLoginButton().click();
		
	}

	@When("I click approve for a reimbursement")
	public void i_click_approve_for_a_reimbursement() {
		this.financeManagerPage = new FinanceManagerPage(this.driver);
		this.financeManagerPage.getApproveButton().click();
		
	}

	@Then("the reimbursement is approved")
	public void the_reimbursement_is_approved() throws InterruptedException {
		String expectedStatusText = "Approved";
		
		Assertions.assertEquals(expectedStatusText, this.financeManagerPage.getApprovedStatus().getText());
		
		this.driver.quit();
	}

	@When("I click deny for a reimbursement")
	public void i_click_deny_for_a_reimbursement() {
		this.financeManagerPage = new FinanceManagerPage(this.driver);
		this.financeManagerPage.getDenyButton().click();
	}

	@Then("the reimbursement is denied")
	public void the_reimbursement_is_denied() throws InterruptedException {
		String expectedStatusText = "Denied";
		
		Assertions.assertEquals(expectedStatusText, this.financeManagerPage.getDeniedStatus().getText());
		
		this.driver.quit();
	}
	
}
