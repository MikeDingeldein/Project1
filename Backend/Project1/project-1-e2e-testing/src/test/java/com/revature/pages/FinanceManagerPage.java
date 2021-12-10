package com.revature.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FinanceManagerPage {

	private WebDriver driver;
	private WebDriverWait wdw;
	
//	@FindBy(xpath="//*[text()='Veiw and Approve/Deny Reimbursement Menu'") //check this
	@FindBy(id = "title")
	private WebElement managerHeading;
	
	@FindBy(xpath="//button[@id='logout']")
	private WebElement logoutButton;
	
	@FindBy(xpath="//tbody/tr[1]/td[11]/button[1]")
	private WebElement approveButton;
	
	@FindBy(xpath="//tbody/tr[1]/td[12]/button[1]")
	private WebElement denyButton;
	
	@FindBy(xpath = "//tbody/tr[1]/td[5]")
	private WebElement approvedStatus;

	@FindBy(xpath = "//tbody/tr[1]/td[5]")
	private WebElement deniedStatus;
	
	public FinanceManagerPage(WebDriver driver) {
		this.driver = driver;
		this.wdw = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getManagerHeading() {
		return this.wdw.until(ExpectedConditions.visibilityOf(managerHeading));
		
	}
	
	public WebElement getLogoutButton() {
		wdw.until(ExpectedConditions.visibilityOf(logoutButton));  //not working
		return this.logoutButton;
	}
	
	public WebElement getApproveButton() {
		wdw.until(ExpectedConditions.visibilityOf(approveButton));  //not working
		return this.approveButton;
	}
	
	public WebElement getDenyButton() {
		wdw.until(ExpectedConditions.visibilityOf(denyButton));  //not working
		return this.denyButton;
	}
	
	public WebElement getApprovedStatus() throws InterruptedException {
		Thread.sleep(500); //wait for table to refresh
		return this.wdw.until(ExpectedConditions.visibilityOf(approvedStatus));
		
	}
	
	public WebElement getDeniedStatus() throws InterruptedException {
		Thread.sleep(500); //wait for table to refresh
		return this.wdw.until(ExpectedConditions.visibilityOf(deniedStatus));
		
	}
}
