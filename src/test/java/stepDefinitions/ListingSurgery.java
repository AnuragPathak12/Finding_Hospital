package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import baseClasses.BaseClass;
import io.cucumber.java.en.*;
import pageObjectClasses.ListingSurgeriesPOC;

public class ListingSurgery extends BaseClass{

	BaseClass baseClass;
	ListingSurgeriesPOC listingSurgery;
	
	@Given("User is on application homepage")
	public void user_is_on_application_homepage() {
		
		baseClass = new BaseClass();
	}

	@When("User clicks on surgery option")
	public void user_clicks_on_surgery_option() {
		listingSurgery = new ListingSurgeriesPOC(driver);
	    listingSurgery.getSurgeryPageTitle();
	}

	@Then("List of surgeries available will be displayed")
	public void list_of_surgeries_available_will_be_displayed() throws IOException {
	  
		listingSurgery.getSurgeries();
		Assert.assertEquals(listingSurgery.getSurgeryPageTitle(), "Practo Care Surgeries | End to end care from top surgeons in your city");
		baseClass.tearDown();
	}


}
