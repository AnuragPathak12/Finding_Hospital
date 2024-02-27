package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import baseClasses.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	 WebDriver driver;
	 BaseClass baseClass;
	 
	@Before
    public void setup() throws IOException
    {
		baseClass = new BaseClass();
		driver = baseClass.setup("chrome");
    			
	}
		
    
    @After
    public void tearDown(Scenario scenario) {
        		
    	baseClass.tearDown();
    }
    

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        if((scenario.isFailed())|| (scenario.isFailed()==false)) {

        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
        	            
        }
      
    }
}
