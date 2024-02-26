package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClasses.BaseClass;
import pageObjectClasses.CorporateFormPOC;

public class CorporateForm extends BaseClass {
	
	CorporateFormPOC corporateForm;
	
	@Test(priority = 1)
	public void verifyFormPage() throws IOException {
		corporateForm = new CorporateFormPOC(driver);
		corporateForm.openWellnessForm();
		Assert.assertEquals(corporateForm.getWellnessFormTitle(), "Employee Health | Corporate Health & Wellness Plans | Practo");
	}
	
//	@Test(priority = 2)
//	public void validateWithInvalidMobEmail() throws IOException {
//		
//		Assert.assertEquals(corporateForm.fillingFormWithBothInvalidMobEmail(), false);
//	}
//	
//	@Test(priority = 3)
//	public void validateWithInvalidMobAndValidEmail() throws IOException {
//		
//		Assert.assertEquals(corporateForm.fillingFormWithInvalidMobAndValidEmail(), false);
//	}
//	
//	@Test(priority = 4)
//	public void validateWithValidMobAndInvalidEmail() throws IOException {
//		
//		Assert.assertEquals(corporateForm.fillingFormWithValidMobAndInvalidEmail(), false);
//	}
//	
//	@Test(priority = 5)
//	public void validateWithBothValidMobEmail() throws IOException {
//		
//		Assert.assertEquals(corporateForm.fillingFormWithBothValidMobEmail(), true);
//	}
//	
	@Test(priority = 6)
	public void validateForm() throws IOException {

		corporateForm.fillingForm();
		corporateForm.compareResults();
		
	}
}
