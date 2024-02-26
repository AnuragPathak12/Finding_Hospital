package baseClasses;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class BaseClass {
	
public static WebDriver driver;
static File targetFile;
	
	@BeforeTest
	@Parameters({"browser"})
	public WebDriver setup(String br) {
		
		if(br.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://practo.com");
		}
		else if(br.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			driver.get("https://practo.com");
		}
		
		return driver;
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	public static String captureOutputScreenshot(String tName) throws IOException {

				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "//ScreenShots//"+tName+".png";
		
		if(tName.equals("HomePage") || tName.equals("DoctorResults") || tName.equals("Surgeries") || tName.equals("CorporateForm")) {

		targetFile=new File(targetFilePath);
		FileUtils.copyFile(sourceFile, targetFile);
		
		} else {
			
			targetFile=new File(targetFilePath);
			FileUtils.copyFile(sourceFile, targetFile);
		}
			
		return targetFilePath;

	}
	
}
