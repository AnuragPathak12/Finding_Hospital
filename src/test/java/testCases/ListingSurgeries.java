package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClasses.BaseClass;
import pageObjectClasses.ListingSurgeriesPOC;

public class ListingSurgeries extends BaseClass {
	
	ListingSurgeriesPOC listingSurgeries;
	
	
	@Test(priority = 1)
	public void verifySurgeryPage() {
		listingSurgeries = new ListingSurgeriesPOC(driver);
		Assert.assertEquals(listingSurgeries.getSurgeryPageTitle(), "Practo Care Surgeries | End to end care from top surgeons in your city");

	}
	
	@Test(priority = 2)
	public void availableSurgeries() throws IOException {
		listingSurgeries = new ListingSurgeriesPOC(driver);
		Assert.assertEquals(listingSurgeries.getSurgeries(), 19);
	}
}
