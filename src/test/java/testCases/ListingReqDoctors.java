package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import baseClasses.BaseClass;
import pageObjectClasses.ListingDoctorsPOC;

public class ListingReqDoctors extends BaseClass {
	ListingDoctorsPOC listingDoctor;
	
	@Test(priority = 1)
	public void validateFilters() {
		listingDoctor = new ListingDoctorsPOC(driver);
		SoftAssert filterAsserts = new SoftAssert();
		
		
		filterAsserts.assertEquals(listingDoctor.patientStoriesFilter(), "10+ Patient Stories");
		filterAsserts.assertEquals(listingDoctor.experienceFilter(), "15+ Years of experience");
		filterAsserts.assertEquals(listingDoctor.feesFilter(), "Above ₹500");
		filterAsserts.assertEquals(listingDoctor.availabilityFilter(), "Available Today");
		filterAsserts.assertEquals(listingDoctor.sorting(), "Experience - High to Low");
		filterAsserts.assertAll();
		
	}
	
	@Test(priority = 2)
	public void getDetails() throws IOException {
		Assert.assertEquals(listingDoctor.getDoctors(), "Best Dentists Near Me In Bangalore - Instant Appointment Booking, View Fees, Feedbacks | Practo");
	}
}
