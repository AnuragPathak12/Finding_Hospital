package stepDefinitions;

import java.io.IOException;

import org.junit.Assert;

import baseClasses.BaseClass;
import io.cucumber.java.en.*;
import pageObjectClasses.CorporateFormPOC;

public class FillingCorporateWellnessForm extends BaseClass{

	BaseClass baseClass;
	CorporateFormPOC corporateForm;
	
	@Given("User is on application web page")
	public void user_is_on_application_web_page() {
	   baseClass = new BaseClass();	
	}

	@When("User clicks on Corporate dropdown and selects Health and Wellness Plan")
	public void user_clicks_on_corporate_dropdown_and_selects_health_and_wellness_plan() {
		
	  corporateForm = new CorporateFormPOC(driver);
	  corporateForm.openWellnessForm();
	}

	@Then("Corporate Employee Health and Wellness page opens")
	public void corporate_employee_health_and_wellness_page_opens() throws IOException {
	   
		corporateForm.getWellnessFormTitle();
		Assert.assertEquals(corporateForm.getWellnessFormTitle(), "Employee Health | Corporate Health & Wellness Plans | Practo");

	}

	@Then("Fill the form with test data")
	public void fill_the_form_with_test_data() throws IOException {
	    
		corporateForm.fillingForm();
		corporateForm.compareResults();
		baseClass.tearDown();
	}
}
