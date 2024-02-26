package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClasses.BaseClass;
import pageObjectClasses.SearchDoctorsPOC;

public class SearchDoctors extends BaseClass {
	
	SearchDoctorsPOC searchDoctorsPOC;
	
	@Test(priority = 1, groups = {"smoke"})
	public void validateLogo(){
		searchDoctorsPOC = new SearchDoctorsPOC(driver);

		Assert.assertEquals(searchDoctorsPOC.checkHeaderLogo(), true);

	}
	
	@Test(priority = 2, groups = {"smoke"})
	public void validateTitle() throws IOException {
		
		Assert.assertEquals(searchDoctorsPOC.applicationTitle(), "Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");
	}
	
	@Test(priority = 3)
	public void validateSpeciality() throws InterruptedException {
		searchDoctorsPOC.setNearLoc();
		Assert.assertEquals(searchDoctorsPOC.searchForDoctors(), "Dentist");
	}
}
