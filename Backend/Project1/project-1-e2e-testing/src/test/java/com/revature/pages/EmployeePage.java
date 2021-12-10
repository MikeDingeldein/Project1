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
	
	@FindBy(id = "reimbursmentAmount")
	private WebElement reimbursmentAmount;
	
	@FindBy(id = "reimbursmentType")
	private WebElement reimbursmentType;
	
	@FindBy(id = "reimbursmentDescription")
	private WebElement reimbursmentDescription;
	
	@FindBy(xpath = "//input[@id='reimbursmentReciept']")
	private WebElement reimbursmentReciept;
	
	@FindBy(id="reimbursement-submit-btn")
	private WebElement addReimbursementButton;
	
	@FindBy(xpath = "//tbody/tr[1]/td[7]")
	private WebElement firstReimbursementDescription;
	
	@FindBy(xpath = "/html[1]/body[1]/div[2]/div[3]/div[2]/p[1]") //seems bad
	private WebElement errorMessage;
	
	public EmployeePage(WebDriver driver) {
		this.driver = driver;
		this.wdw = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getEmployeeHeading() {
		return this.wdw.until(ExpectedConditions.visibilityOf(employeeHeading));
		
	}
	
	public WebElement getLogoutButton() {
		this.wdw.until(ExpectedConditions.visibilityOf(logoutButton)); //not working
		
		return this.logoutButton;
	}
	
	public WebElement getReimbursementAmount() {
		this.wdw.until(ExpectedConditions.visibilityOf(reimbursmentAmount));
		return this.reimbursmentAmount;
	}
	
	public WebElement getReimbursmentType() {
		this.wdw.until(ExpectedConditions.visibilityOf(reimbursmentType));
		return this.reimbursmentType;
	}
	
	public WebElement getReimbursmentDescription() {
		this.wdw.until(ExpectedConditions.visibilityOf(reimbursmentDescription));
		return this.reimbursmentDescription;
	}
	
	public WebElement getReimbursmentReciept() {
		this.wdw.until(ExpectedConditions.visibilityOf(reimbursmentReciept));
		return this.reimbursmentReciept;
	}
	
	public WebElement getAddReimbursementButton() {
		this.wdw.until(ExpectedConditions.visibilityOf(addReimbursementButton));
		return this.addReimbursementButton;
	}
	
	public WebElement getFirstReimbursementDescrition() throws InterruptedException {
		Thread.sleep(500); //wait for table to refresh
		return this.wdw.until(ExpectedConditions.visibilityOf(firstReimbursementDescription));
		
	}
	
	public WebElement getErrorMessage() {
		return this.wdw.until(ExpectedConditions.visibilityOf(errorMessage));
		
	}
}
