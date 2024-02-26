package commonMethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyExplicitWait {
	
	public static void visibilityWait(By xpath, WebDriver driver) {
		WebDriverWait myVisibleWait = new WebDriverWait(driver, Duration.ofSeconds(20));
		myVisibleWait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
	}
	
	public static void clickableWait(By xpath, WebDriver driver) {
		WebDriverWait myClickWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		myClickWait.until(ExpectedConditions.elementToBeClickable(xpath));
	}
	
	public static void locationVisibilityWait(By xpath, WebDriver driver) {
		WebDriverWait myLocWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		myLocWait.until(ExpectedConditions.textToBePresentInElementLocated(xpath, "Bangalore"));
	}
	
	public static boolean stateWait(By xpath, WebDriver driver) {
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait1.until(ExpectedConditions.elementSelectionStateToBe(xpath, true));
	}
}
