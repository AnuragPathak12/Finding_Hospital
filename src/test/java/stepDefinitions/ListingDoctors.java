package stepDefinitions;


import java.io.IOException;

import org.junit.Assert;

import baseClasses.BaseClass;
import io.cucumber.java.en.*;
import pageObjectClasses.ListingDoctorsPOC;
import pageObjectClasses.SearchDoctorsPOC;

public class ListingDoctors extends BaseClass{
	
	BaseClass baseClass;
	SearchDoctorsPOC searchDoctor;
	ListingDoctorsPOC listingDoctor;
	
	@Given("User navigates to application page")
	public void user_navigates_to_application_page() {
		baseClass = new BaseClass();
	}

	@When("User enters required location and speciality")
	public void user_enters_required_location_and_speciality() throws InterruptedException, IOException {
		
		searchDoctor = new SearchDoctorsPOC(driver);
		searchDoctor.setNearLoc();
		searchDoctor.searchForDoctors();
	}

	@When("User applies the filter to the result")
	public void user_applies_the_filter_to_the_result() {
		
		listingDoctor = new ListingDoctorsPOC(driver);
		listingDoctor.patientStoriesFilter();
		listingDoctor.experienceFilter();
		listingDoctor.feesFilter();
		listingDoctor.availabilityFilter();
		listingDoctor.sorting();
		
	}

	@Then("List of available doctors will be displayed")
	public void list_of_available_doctors_will_be_displayed() throws IOException {
	 
		Assert.assertEquals(listingDoctor.getDoctors(), "Best Dentists Near Me In Bangalore - Instant Appointment Booking, View Fees, Feedbacks | Practo");
		baseClass.tearDown();
		
	}

}
