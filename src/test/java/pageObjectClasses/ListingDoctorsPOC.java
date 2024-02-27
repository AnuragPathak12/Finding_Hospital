package pageObjectClasses;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseClasses.BaseClass;
import commonMethods.ExcelUtils;
import commonMethods.MyExplicitWait;

public class ListingDoctorsPOC {
	
	WebDriver driver;
	List<WebElement> doctorNames;
	String availability;
	static String output_file_path = System.getProperty("user.dir") + ".\\ExcelFiles\\OutputData.xlsx";
	
//	Constructor
	
	public ListingDoctorsPOC(WebDriver driver) {
		this.driver = driver;
	}
	
//	Locators
	
	By dropDown_patientStories = By.xpath("//header[@class = 'c-filter']/div[1]//div[2]//i");
	By opt_patientStories = By.xpath("//header[@class = 'c-filter']/div[1]//div[2]/ul/li[1]/span");  //10+ PatientStories
	By opt_patientStoriesWait = By.xpath("//header[@class = 'c-filter']/div[1]//div[2]/span/span/span");  
	By dropDown_experience = By.xpath("//header[@class = 'c-filter']/div[1]//div[3]//i");
	By opt_experience = By.xpath("//header[@class = 'c-filter']/div[1]//div[3]/ul/li[3]/span");  // 15+ Experience
	By opt_ExperienceWait = By.xpath("//header[@class = 'c-filter']/div[1]//div[3]/span/span/span");
	By dropDown_AllFilters = By.xpath("//header[@class = 'c-filter']/div[1]//div[4]//i");
	By opt_feesFilter = By.xpath("//header[@class = 'c-filter']/div[2]//div[2]//label[2]/span/span");   // Above 500
	By opt_availability = By.xpath("//header[@class = 'c-filter']/div[2]//div[3]//label[2]/span/span");  // Available Today
	By dropDown_sort = By.xpath("//header[@class = 'c-filter']/div[1]//div[5]/div/div/div/span/i");
	By opt_sorting = By.xpath("//header[@class = 'c-filter']/div[1]//div[6]/div/div/div//ul/li[2]/span");  // Exp High to Low
	By txt_doctorDetails = By.cssSelector("div[class = 'info-section']");
	By btn_reset = By.xpath("//button[@data-qa-id = 'Reset_Filters']/span");
	By txt_totalDoctors = By.xpath("//h1[@class = 'u-xx-large-font u-bold']");
	
//	Selecting Patient Stories Filter
	
	public String patientStoriesFilter() {
		
		driver.findElement(dropDown_patientStories).click();
		
		MyExplicitWait.visibilityWait(opt_patientStories, driver);
		String patientStory = driver.findElement(opt_patientStories).getText();
		driver.findElement(opt_patientStories).click();
		
		BaseClass.getLogger().info("Patient Stories filter applied");

		return patientStory;
	}
	
//	Selecting Experience Filter
	
	public String experienceFilter() {
		
		MyExplicitWait.visibilityWait(opt_patientStoriesWait, driver);
		driver.findElement(dropDown_experience).click();
		
		MyExplicitWait.visibilityWait(opt_experience, driver);
		String experience = driver.findElement(opt_experience).getText();
		driver.findElement(opt_experience).click();
		
		BaseClass.getLogger().info("Experience filter applied");

		return experience;
	}
	
//	Selecting Fees
	
	public String feesFilter() {
		
		driver.findElement(dropDown_AllFilters).click();
		
		MyExplicitWait.visibilityWait(opt_feesFilter, driver);
		
		String fees = driver.findElement(opt_feesFilter).getText();
		driver.findElement(opt_feesFilter).click();
		
		BaseClass.getLogger().info("Fees filter applied");

		return fees;
	}
	
//	Selecting Availability
	
	public String availabilityFilter() {
		
		MyExplicitWait.visibilityWait(btn_reset, driver);
	
		driver.findElement(dropDown_AllFilters).click();
		MyExplicitWait.visibilityWait(opt_availability, driver);
		availability = driver.findElement(opt_availability).getText();
		driver.findElement(opt_availability).click();
		
		BaseClass.getLogger().info("Availability filter applied");

		
		return availability;
	}
	
//	Selecting type of Sorting
	
	public String sorting() {
				
		driver.findElement(dropDown_sort).click();
		
		MyExplicitWait.visibilityWait(opt_sorting, driver);
		String sorted = driver.findElement(opt_sorting).getText();
		driver.findElement(opt_sorting).click();
		
		BaseClass.getLogger().info("Sorting applied");

		return sorted;
	}
	
//	Getting List of Doctors
	
	public String getDoctors() throws IOException {
		
		BaseClass.captureOutputScreenshot("DoctorResults");
		
		doctorNames = driver.findElements(txt_doctorDetails);
		for(int i=0; i<5; i++) {
			
		System.out.println(doctorNames.get(i).getText());
		System.out.println("====================");
		
		ExcelUtils.setCellData(output_file_path, "Sheet1", i+1, 0, doctorNames.get(i).getText());
		}
		
		BaseClass.getLogger().info("List of doctors successfully printed and written");

		return driver.getTitle();
	}
	
	
	
}
