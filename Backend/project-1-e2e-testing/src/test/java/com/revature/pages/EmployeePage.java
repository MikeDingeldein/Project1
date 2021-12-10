package com.revature.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeePage {

	private WebDriver driver;
	private WebDriverWait wdw;
	
//	@FindBy(xpath="//*[text()='Veiw and Add Reimbursement Menu']")
	@FindBy(id = "title")
	private WebElement employeeHeading;
	
	@FindBy(xpath="//button[@id='logout']")
	private WebElement logoutButton;
	
	public EmployeePage(WebDriver driver) {
		this.driver = driver;
		this.wdw = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getEmployeeHeading() {
		return this.wdw.until(ExpectedConditions.visibilityOf(employeeHeading));
		
	}
	
	public WebElement getLogoutButton() {
		wdw.until(ExpectedConditions.visibilityOf(logoutButton));
		
		return this.logoutButton;
	}
	
}
