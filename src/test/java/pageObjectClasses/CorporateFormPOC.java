package pageObjectClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import baseClasses.BaseClass;
import commonMethods.ExcelUtils;
import commonMethods.MyExplicitWait;

public class CorporateFormPOC {
	
	WebDriver driver;
	static String testData_file_path = System.getProperty("user.dir") + ".\\ExcelFiles\\FormTestData.xlsx";
	
	String[][] data;
	WebElement inp_phone, inp_email;
	List<String> expectedResults = new ArrayList<String>();
	List<String> actualResults = new ArrayList<String>();
	
//	Constructor
	
	public CorporateFormPOC(WebDriver driver) {
		this.driver = driver;
	}
	
//	Locators
	
	By dropDown_corporate = By.xpath("//div[@class = 'nav-right text-right']//span[3]");
	By txt_healthWellness = By.xpath("//div[@class = 'nav-right text-right']//span//div[1]/a");
	By inp_name_textBox = By.xpath("//header[@id = 'header']/div[2]//form/div[1]/input[@id = 'name']");
	By inp_organization_textBox = By.xpath("//header[@id = 'header']/div[2]//form/div[2]/input[@id = 'organizationName']");
	By inp_phone_textBox = By.xpath("//header[@id = 'header']/div[2]//form/div[3]/input[@id = 'contactNumber']");
	By inp_email_textBox = By.xpath("//header[@id = 'header']/div[2]//form/div[4]/input[@id = 'officialEmailId']");
	By dropDown_organizationSize = By.xpath("//header[@id = 'header']/div[2]//form/div[5]/select");
	By dropDown_interest = By.xpath("//header[@id = 'header']/div[2]//form/div[6]/select");
	By btn_scheduleDemo = By.xpath("//header[@id = 'header']/div[2]//form/div[6]/following-sibling::button");
	By txt_afterSubmission = By.xpath("//div[@id = 'app']/following-sibling::div[1]//div[text() = 'THANK YOU']");
	
	public void openWellnessForm() {
		driver.findElement(dropDown_corporate).click();
		MyExplicitWait.visibilityWait(txt_healthWellness, driver);
		driver.findElement(txt_healthWellness).click();
	}
	
//	Getting form page title
	
	public String getWellnessFormTitle() throws IOException {
		
		BaseClass.captureOutputScreenshot("CorporateForm");
		
		return driver.getTitle();
	}
	

//	Filling form with Test Data from Excel
	
	
	public void fillingForm() throws IOException {
		
		driver.findElement(inp_name_textBox).sendKeys("Ramlal");
		driver.findElement(inp_organization_textBox).sendKeys("CTS");
	
		Select orgSize = new Select(driver.findElement(dropDown_organizationSize));
		orgSize.selectByValue("1001-5000");
		
		Select interest = new Select(driver.findElement(dropDown_interest));
		interest.selectByValue("A career opportunity");
		
		data = ExcelUtils.getCellData(testData_file_path, "Sheet1");
						
		for(int i=0; i<data.length; i++) {
				
			inp_phone = driver.findElement(inp_phone_textBox);
			inp_phone.sendKeys(data[i][0]);
				
			inp_email = driver.findElement(inp_email_textBox);
			inp_email.sendKeys(data[i][1]);
				
			if(driver.findElement(btn_scheduleDemo).isEnabled()) {
				
				inp_phone.clear();
				inp_email.clear();
				
				ExcelUtils.setCellData(testData_file_path, "Sheet1", i+1, 3, "TRUE");
				actualResults.add("TRUE");
				continue;
				
			} else {
				ExcelUtils.setCellData(testData_file_path, "Sheet1", i+1, 3, "FALSE");
				
				actualResults.add("FALSE");
				inp_phone.clear();
				inp_email.clear();
			}
		}
		
		BaseClass.getLogger().info("Corporate Form automated with test data");

	}
	
//	Comparing Expected and Actual result from excel
	
	public void compareResults() throws IOException {
		
		System.out.println(actualResults.size());
		System.out.println(ExcelUtils.getExpectedData(testData_file_path, "Sheet1").size());
		
		expectedResults = ExcelUtils.getExpectedData(testData_file_path, "Sheet1");
		
		for(int i=0; i<actualResults.size(); i++) {
			
			if(actualResults.get(i).equals(expectedResults.get(i))) {
				
				ExcelUtils.setCellData(testData_file_path, "Sheet1", i+1, 4, "Passed");
				ExcelUtils.fillGreenColor(testData_file_path, "Sheet1", i+1, 4);
				
			} else {
				
				ExcelUtils.setCellData(testData_file_path, "Sheet1", i+1, 4, "Failed");
				ExcelUtils.fillRedColor(testData_file_path, "Sheet1", i+1, 4);
			}
		}
		
		BaseClass.getLogger().info("Expected and Actual results compared");

	}
	
	

}

