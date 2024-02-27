package pageObjectClasses;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseClasses.BaseClass;
import commonMethods.ExcelUtils;
import commonMethods.MyExplicitWait;

public class ListingSurgeriesPOC {
	
	WebDriver driver;
	List<WebElement> surgeries;
	static String output_file_path = System.getProperty("user.dir") + ".\\ExcelFiles\\OutputData.xlsx";

//	Constructor
	
	public ListingSurgeriesPOC(WebDriver driver) {
		this.driver = driver;
	}
	
//	Locators
	
	By btn_surgeries = By.xpath("//div[@class = 'nav-mid']/div[5]//div[1]");
	By txt_surgeryHeadline = By.xpath("//h1[text() = 'Popular Surgeries']");
	By txt_surgeries = By.xpath("//p[@data-qa-id = 'surgical-solution-ailment-name']");
	
	
//	Get Surgery Page Title
	
	public String getSurgeryPageTitle() {
		driver.findElement(btn_surgeries).click();
		return driver.getTitle();
	}
	
	
//	Get all listed Surgeries
	
	public int getSurgeries() throws IOException {
		
		MyExplicitWait.visibilityWait(txt_surgeryHeadline, driver);
		
		JavascriptExecutor forSurgery = (JavascriptExecutor) driver;
		forSurgery.executeScript("arguments[0].scrollIntoView();", driver.findElement(txt_surgeryHeadline));
		
		BaseClass.captureOutputScreenshot("Surgeries");
		
		surgeries = driver.findElements(txt_surgeries);
		for(int i=0; i<surgeries.size(); i++) {
			System.out.println(surgeries.get(i).getText());
			
			ExcelUtils.setCellData(output_file_path, "Sheet1", i+1, 1, surgeries.get(i).getText());
		}
		
		BaseClass.getLogger().info("All mentioned surgeries printed and written");

		driver.navigate().back();
		
		return surgeries.size();
	}
}
