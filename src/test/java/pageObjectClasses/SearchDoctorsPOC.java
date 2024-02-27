package pageObjectClasses;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseClasses.BaseClass;
import commonMethods.MyExplicitWait;

public class SearchDoctorsPOC {
	
	WebDriver driver;
	
//	Constructor
	
	public SearchDoctorsPOC(WebDriver driver) {
		this.driver = driver;
	}
	
//	Locators
	
	By img_header_logo = By.xpath("//span[@class = 'practo-logo']/a/i");
	By img_footer_logo = By.xpath("//div[@class = 'c-footer__copyright']/span/img");
	By input_loc_textbox = By.cssSelector("input[placeholder = 'Search location']");
	By opt_location = By.xpath("//div[@class = 'c-omni-suggestion-group']/div[1]/span/div");
	By input_hospital_textbox = By.xpath("//div[@class = 'c-omni u-clearfix']/div[2]/div//input");
	By opt_doctor = By.xpath("//div[@class = 'c-omni-suggestion-list']/div[1]/div[1]/span[1]/div");
	
	
	
	public boolean checkHeaderLogo() {
		
		MyExplicitWait.visibilityWait(img_header_logo, driver);
		return driver.findElement(img_header_logo).isDisplayed();
	}
	
	
	public String applicationTitle() throws IOException {
		
		BaseClass.captureOutputScreenshot("HomePage");
		return driver.getTitle();
	}
	
	public void setNearLoc() throws InterruptedException, IOException {
		WebElement inpLoc = driver.findElement(input_loc_textbox);
		inpLoc.click();
		inpLoc.clear();
		inpLoc.sendKeys(BaseClass.getProperties().getProperty("location"));
		
		MyExplicitWait.locationVisibilityWait(opt_location, driver);
		driver.findElement(opt_location).click();
		
		BaseClass.getLogger().info("Nearby location set");
	}
	
	public String searchForDoctors() throws IOException {
		
		driver.findElement(input_hospital_textbox).sendKeys(BaseClass.getProperties().getProperty("speciality"));
		String speciality = driver.findElement(opt_doctor).getText();
		driver.findElement(opt_doctor).click();
		
		BaseClass.getLogger().info("Speciality set");

		return speciality;
	}
	
}
