package com.revature.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wdw;

	@FindBy(id = "username")
	private WebElement usernameInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "login-btn")
	private WebElement loginButton;

	// Error message?
	@FindBy(xpath = "//div[@id='login-info']/p")
	private WebElement errorMessage;
	
	@FindBy(xpath = "//h1[contains(text(),'Reimbursement')]")
	private WebElement loginHeading;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wdw = new WebDriverWait(driver, Duration.ofSeconds(15));

		PageFactory.initElements(driver, this);
	}

	public WebElement getUsernameInput() {
		return this.usernameInput;
	}

	public WebElement getPasswordInput() {
		return this.passwordInput;
	}

	public WebElement getLoginButton() {
		return this.loginButton;
	}

	public WebElement getErrorMessage() {
		return this.wdw.until(ExpectedConditions.visibilityOf(this.errorMessage));
	}
	
	public WebElement getLoginHeading() {
		return this.wdw.until(ExpectedConditions.visibilityOf(loginHeading));
		
	}
}
