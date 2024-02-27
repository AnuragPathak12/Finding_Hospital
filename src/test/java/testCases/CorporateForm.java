package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClasses.BaseClass;
import pageObjectClasses.CorporateFormPOC;

public class CorporateForm extends BaseClass {
	
	CorporateFormPOC corporateForm;
	
	@Test(priority = 8, groups = {"smoke", "regression"})
	public void verifyFormPage() throws IOException {
		
		corporateForm = new CorporateFormPOC(driver);
		corporateForm.openWellnessForm();
		Assert.assertEquals(corporateForm.getWellnessFormTitle(), "Employee Health | Corporate Health & Wellness Plans | Practo");
	}
	
	@Test(priority = 9, groups = {"regression"})
	public void validateForm() throws IOException {
		corporateForm = new CorporateFormPOC(driver);

		corporateForm.fillingForm();
		corporateForm.compareResults();
		
	}
}
