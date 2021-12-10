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

public class LogoutTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private EmployeePage employeePage;
	private FinanceManagerPage financeManagerPage;

	@Given("I am at the employee page")
	public void i_am_at_the_employee_page() {
		System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");

		this.driver = new ChromeDriver();

		this.driver.get("http://localhost:5500");
		this.loginPage = new LoginPage(driver);
		
		this.loginPage.getUsernameInput().sendKeys("MikeDingeldein");
		this.loginPage.getPasswordInput().sendKeys("password");
		
		this.loginPage.getLoginButton().click();
	}

	@Given("I am at the manager page")
	public void i_am_at_the_manager_page() {
		System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");

		this.driver = new ChromeDriver();

		this.driver.get("http://localhost:5500");
		this.loginPage = new LoginPage(driver);
		
		this.loginPage.getUsernameInput().sendKeys("MikesWallet");
		this.loginPage.getPasswordInput().sendKeys("password");
		
		this.loginPage.getLoginButton().click();
	}
	


	@Then("I click the logout button")
	public void i_click_the_logout_button() {
		
		this.employeePage = new EmployeePage(this.driver);
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
		
		this.financeManagerPage = new FinanceManagerPage(this.driver);
		this.financeManagerPage.getLogoutButton().click();

	}
}
