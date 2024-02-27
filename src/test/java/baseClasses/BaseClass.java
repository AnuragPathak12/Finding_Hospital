package baseClasses;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class BaseClass {
	
public static WebDriver driver;
public static File targetFile;
public static Logger log;
public static Properties property;

	@BeforeTest(groups = {"smoke", "regression"})
	@Parameters({"browser"})
	public WebDriver setup(String br) throws IOException {
		
		if(getProperties().getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//Getting OS type
			
			if (getProperties().getProperty("os").equalsIgnoreCase("windows")) {
				
			    capabilities.setPlatform(Platform.WINDOWS);
			} 
			else if (getProperties().getProperty("os").equalsIgnoreCase("mac")) {
				
			    capabilities.setPlatform(Platform.MAC);
			    
			} else {
				
			    System.out.println("No matching OS..");
			      }
			
			//Getting Browser type
			
			switch (getProperties().getProperty("browser").toLowerCase()) {
			    case "chrome":
			        capabilities.setBrowserName("chrome");
			        break;
			    case "edge":
			        capabilities.setBrowserName("MicrosoftEdge");
			        break;
			    default:
			        System.out.println("No matching browser");
			     }
	       
	        driver = new RemoteWebDriver(new URL("http://192.168.29.81:4444"),capabilities);
			
		}
		
		else if(getProperties().getProperty("execution_env").equalsIgnoreCase("local")) {
			
			if(br.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			}
			else if(br.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
				
			}
		}
		driver.get("https://practo.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
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
	
	public static Logger getLogger(){	
		
		log=LogManager.getLogger(); //Log4j
		return log;
	}
	
	public static Properties getProperties() throws IOException {		
		
        FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
       		
        property = new Properties();
        property.load(file);
		return property;
	}
	
}
