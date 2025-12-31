package testcases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import coreUtilities.testutils.ApiHelper;
import coreUtilities.utils.FileOperations;
import pages.StartupPage;
import pages.radiology_page;
import testBase.AppTestBase;
import testBase.UserActions;
import testdata.LocatorsFactory;

public class radiology_testcase extends AppTestBase {
	Map<String, String> configData;
	Map<String, String> loginCredentials;
	String expectedDataFilePath = testDataFilePath + "expected_data.xlsx";
	String loginFilePath = loginDataFilePath + "Login.xlsx";
	StartupPage startupPage;
	String randomInvoiceNumber;
	LocatorsFactory locatorsFactoryInstance;
	UserActions userActionsInstance;
	radiology_page radiology_pageInstance;

	@Parameters({ "browser", "environment" })
	@BeforeClass(alwaysRun = true)
	public void initBrowser(String browser, String environment) throws Exception {
		configData = new FileOperations().readExcelPOI(config_filePath, environment);
		configData.put("url", configData.get("url").replaceAll("[\\\\]", ""));
		configData.put("browser", browser);
		boolean isValidUrl = new ApiHelper().isValidUrl(configData.get("url"));
		Assert.assertTrue(isValidUrl,
				configData.get("url") + " might be Server down at this moment. Please try after sometime.");
		initialize(configData);
		startupPage = new StartupPage(driver);
	}

	@Test(priority = 1, groups = { "sanity" }, description = "1. Login in the healthapp application\r\n"
			+ "2. Scroll down menu till radiology\r\n" + "3. Click on the radiology")
	public void verifyRadiologyPageTitle()  throws Exception {
		radiology_pageInstance = new radiology_page(driver);
		Map<String, String> radiologyExpectedData = new FileOperations().readExcelPOI(expectedDataFilePath,
				"radiology");
		Map<String, String> loginData = new FileOperations().readExcelPOI(loginFilePath, "credentials");
		Assert.assertTrue(radiology_pageInstance.loginToHealthAppByGivenValidCredetial(loginData),
				"Login failed, Invalid credentials ! Please check manually");
		radiology_pageInstance.scrollDownAndClickRadiologyTab();
		System.out.println("Verification Page url : " + radiologyExpectedData.get("title"));
		Assert.assertEquals(radiology_pageInstance.verifyRadiologyPageTitle(), radiologyExpectedData.get("title"));
	}

	@Test(priority = 2, groups = { "sanity" }, description = "Pre condition: User should be logged in \r\n"
			+ "1. Click on the radiology Module drop-down arrow")
	public void verifyRadiologySubModules() throws Exception {
		radiology_pageInstance = new radiology_page(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Assert.assertTrue(radiology_pageInstance.verifyRadiologySubModules(
				locatorsFactoryInstance.getPageBarFixedLocator("List Requests")));
		Assert.assertTrue(radiology_pageInstance.verifyRadiologySubModules(
				locatorsFactoryInstance.getPageBarFixedLocator("List Reports")));
		Assert.assertTrue(radiology_pageInstance.verifyRadiologySubModules(
				locatorsFactoryInstance.getPageBarFixedLocator("Edit Doctors")));
		Assert.assertTrue(radiology_pageInstance.verifyRadiologySubModules(
				locatorsFactoryInstance.getPageBarFixedLocator("Ward Billing")));
	}

	@Test(priority = 3, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on radiology module\r\n"
					+ "1. Click on the radiology module drop-down arrow \r\n" + "2. Click on list request section")
	public void verifyListRequestsComponents() throws Exception {
		radiology_pageInstance = new radiology_page(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String toDate = currentDate.format(formatter);
		Thread.sleep(3000); // Waiting for data to load
		

		Assert.assertTrue(radiology_pageInstance.verifyListRequestsComponents());		
	}

	@Test(priority = 4, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on radiology module\r\n"
					+ "1. Click on the list requests \r\n" + "2. Click on the edit doctors\r\n"
					+ "3. Click on Ward Billing\r\n" + "4. Click on List Requests")
	public void verifySelectedTabIsActiveOrNot() throws Exception {
		radiology_pageInstance = new radiology_page(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Assert.assertTrue(
				radiology_pageInstance
						.verifySelectedTabIsActiveOrNot(locatorsFactoryInstance.getPageBarFixedLocator("List Reports")),
				"List Reports page is not active");
		Assert.assertTrue(
				radiology_pageInstance
						.verifySelectedTabIsActiveOrNot(locatorsFactoryInstance.getPageBarFixedLocator("Edit Doctors")),
				"Edit Doctors page is not active");
		Assert.assertTrue(
				radiology_pageInstance
						.verifySelectedTabIsActiveOrNot(locatorsFactoryInstance.getPageBarFixedLocator("Ward Billing")),
				"Ward Billing page is not active");
		Assert.assertTrue(
				radiology_pageInstance
						.verifySelectedTabIsActiveOrNot(locatorsFactoryInstance.getPageBarFixedLocator("List Requests")),
				"List Requests page is not active");
	}

	@Test(priority = 5, groups = { "sanity" }, description = "Pre condition: User should be logged in\r\n"
			+ "1.  Select \"All\" drop down option from the \"Filter\" field \r\n"
			+ "2.  Scroll all the way to the bottom of the page")
	public void verifyNextButtonIsDisplayed() throws Exception {
		radiology_pageInstance = new radiology_page(driver);

		Assert.assertTrue(radiology_pageInstance.verifyNextButtonIsDisplayed(), "Scroll operation is performed");
		;
	}

	@Test(priority = 6, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on Radiology > List Requests section\r\n"
					+ "1. Navigate to the Radiology module.\r\n" + "2. Click on the \"List Requests\" section.\r\n"
					+ "3. Click on \"FROM\" and select \"Jan 2020\"\r\n"
					+ "4. Click on \"TO\" and selct \"march 2024\"\r\n" + "5. Click on \"star\" tooltip\r\n"
					+ "6. Click on \"OK\" button\r\n"
					+ "7. Navigate back to the \"List Requests\" section of the Radiology module.")
	public void verifyDatesAreRemebered() throws Exception {
		radiology_pageInstance = new radiology_page(driver);

		LocalDate currentDate = LocalDate.now();
		LocalDate dateToFilter = LocalDate.of(2020, 1, 1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String toDate = currentDate.format(formatter);
		String fromDate = dateToFilter.format(formatter);

		System.out.println("From Date : " + fromDate + ", To Date : " + toDate);
		Assert.assertTrue(radiology_pageInstance.verifyDatesAreRememberedCorrectly(fromDate, toDate));
	}

	@Test(priority = 7, groups = {
	"sanity" }, description = "Pre condition: User should be logged in and it is on radiology module\r\n"
			+ "1. Click on the data range button\r\n" + "2. Select\"one week\" option from the drop down\r\n"
			+ "3. Click on \"OK\" button"
			+ "4. Data should be present as per the selected date range using dropdown\r\n"
			+ "The 'Requested on' column date must fall within the \"one week\".")
		public void verifyToDataRangeBySelectOneWeekOptionFromDropdown() throws Exception {
		radiology_pageInstance = new radiology_page(driver);
		
		Assert.assertTrue(radiology_pageInstance.clickDateRangeDropdownAndSelectLast1Week());
		LocalDate currentDate = LocalDate.now();
		LocalDate date7DaysAgo = currentDate.minusDays(7);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String toDate = currentDate.format(formatter);
		String fromDate = date7DaysAgo.format(formatter);
		Thread.sleep(3000); // Waiting for data to load
		Assert.assertTrue(radiology_pageInstance.verifyToDataRangeBySelectOneWeekOptionFromDropdown(fromDate, toDate));
		}

		@Test(priority = 8, groups = {
		"sanity" }, description = "Pre condition: User should be logged in and it is on List requests section \r\n"
				+ "1. Click on Filter drop down\r\n" + "2. Click on \"X-RAY\" drop down option\r\n"
				+ "3. Click on \"FROM\" and select \"Jan 2020\"\r\n"
				+ "4. Click on \"TO\" and selct \"march 2024\"\r\n" + "5. Click on \"OK\" button"
				+ "6. Record should filter out as per status ")
		public void verifyDataFromTabelByEnteringDataXRAYInSearchField() throws Exception {
		radiology_pageInstance = new radiology_page(driver);
		
		Map<String, String> radiologyExpectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "radiology");
		
		Assert.assertTrue(radiology_pageInstance
			.verifyDataFromTabelByEnteringDataXRAYInSearchField(radiologyExpectedData.get("type")));
		}
		
		@Test(priority = 9, groups = { "sanity" }, description = "1. Navigate to the Radiology module\r\n"
		+ "2. Go to Ward Billing\r\n" + "3. Search for Patient\r\n"
		+ "4. Click on the View Details button next to the Test Patient 3 Radiology\r\n"
		+ "5. Click on the Cancel button within the Radiology Ward Billing modal"
		+ "6. Clicking the \"Cancel\" button within the modal should trigger a popup message stating \"Please Write Cancellation Remarks.\"")
		public void verifyRadiologyWardBillingCancellationPopup() throws Exception {
		radiology_pageInstance = new radiology_page(driver);
		
		Map<String, String> radiologyExpectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "radiology");
		
		Assert.assertEquals(radiology_pageInstance.verifyRadiologyWardBillingCancellationPopup(radiologyExpectedData),
			radiologyExpectedData.get("cancellationRemarksMsg"));
		}
		
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		System.out.println("before closing the browser");
		browserTearDown();
	}

	@AfterMethod
	public void retryIfTestFails() throws Exception {
		startupPage.navigateToUrl(configData.get("url"));
	}
}
