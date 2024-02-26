package pageObjectClasses;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseClasses.BaseClass;
import commonMethods.ExcelUtils;
import commonMethods.MyExplicitWait;
//import commonMethods.ScreenshotsTaker;

public class ListingDoctorsPOC {
	
	WebDriver driver;
	List<WebElement> doctorNames;
	String availability;
	static String output_file_path = System.getProperty("user.dir") + ".\\ExcelFiles\\OutputData.xlsx";
	
	public ListingDoctorsPOC(WebDriver driver) {
		this.driver = driver;
	}
	
	By dropDown_patientStories = By.xpath("//header[@class = 'c-filter']/div[1]//div[2]//i");
	By opt_patientStories = By.xpath("//header[@class = 'c-filter']/div[1]//div[2]/ul/li[1]/span");
	By opt_patientStoriesWait = By.xpath("//header[@class = 'c-filter']/div[1]//div[2]/span/span/span");
	By dropDown_experience = By.xpath("//header[@class = 'c-filter']/div[1]//div[3]//i");
	By opt_experience = By.xpath("//header[@class = 'c-filter']/div[1]//div[3]/ul/li[3]/span");
	By opt_ExperienceWait = By.xpath("//header[@class = 'c-filter']/div[1]//div[3]/span/span/span");
	By dropDown_AllFilters = By.xpath("//header[@class = 'c-filter']/div[1]//div[4]//i");
	By opt_feesFilter = By.xpath("//header[@class = 'c-filter']/div[2]//div[2]//label[2]/span/span");
	By opt_availability = By.xpath("//header[@class = 'c-filter']/div[2]//div[3]//label[2]/span/span");
	By dropDown_sort = By.xpath("//header[@class = 'c-filter']/div[1]//div[5]/div/div/div/span/i");
	By opt_sorting = By.xpath("//header[@class = 'c-filter']/div[1]//div[6]/div/div/div//ul/li[2]/span");
	By txt_doctorDetails = By.cssSelector("div[class = 'info-section']");
	By btn_reset = By.xpath("//button[@data-qa-id = 'Reset_Filters']/span");
	By txt_totalDoctors = By.xpath("//h1[@class = 'u-xx-large-font u-bold']");
	
	public String patientStoriesFilter() {
		
		driver.findElement(dropDown_patientStories).click();
		
		MyExplicitWait.visibilityWait(opt_patientStories, driver);
		String patientStory = driver.findElement(opt_patientStories).getText();
		driver.findElement(opt_patientStories).click();
		
		return patientStory;
	}
	
	
	public String experienceFilter() {
		
		MyExplicitWait.visibilityWait(opt_patientStoriesWait, driver);
		driver.findElement(dropDown_experience).click();
		
		MyExplicitWait.visibilityWait(opt_experience, driver);
		String experience = driver.findElement(opt_experience).getText();
		driver.findElement(opt_experience).click();
		return experience;
	}
	
	public String feesFilter() {
		
		driver.findElement(dropDown_AllFilters).click();
		
		MyExplicitWait.visibilityWait(opt_feesFilter, driver);
		
		String fees = driver.findElement(opt_feesFilter).getText();
		driver.findElement(opt_feesFilter).click();

		return fees;
	}
	
	public String availabilityFilter() {
		
		MyExplicitWait.visibilityWait(btn_reset, driver);
	
		driver.findElement(dropDown_AllFilters).click();
		MyExplicitWait.visibilityWait(opt_availability, driver);
		availability = driver.findElement(opt_availability).getText();
		driver.findElement(opt_availability).click();
		
		return availability;
	}
	
	public String sorting() {
		
//		MyExplicitWait.visibilityWait(opt_ExperienceWait, driver);
		
		driver.findElement(dropDown_sort).click();
		
		MyExplicitWait.visibilityWait(opt_sorting, driver);
		String sorted = driver.findElement(opt_sorting).getText();
		driver.findElement(opt_sorting).click();
		return sorted;
	}
	
	public String getDoctors() throws IOException {
		
		BaseClass.captureOutputScreenshot("DoctorResults");
		
		doctorNames = driver.findElements(txt_doctorDetails);
		for(int i=0; i<5; i++) {
			
		System.out.println(doctorNames.get(i).getText());
		System.out.println("====================");
		
		ExcelUtils.setCellData(output_file_path, "Sheet1", i+1, 0, doctorNames.get(i).getText());
		}
		return driver.getTitle();
	}
	
	
	
}
