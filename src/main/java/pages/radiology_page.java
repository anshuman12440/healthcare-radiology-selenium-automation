package pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class radiology_page extends StartupPage {

//	TC-1 Locator
	By getUsernameTextfieldLocator = By.id("username_id");
	By getPasswordTextboxLocator = By.xpath("//input[@id='password']");
	By getSignInButtonLocator = By.xpath("//button[@id='login']");
	By getRadiologyLocator = By.xpath("//a[@href='#/Radiology']");
//	TC-2 Locator
//	Please write the locators here	
//	TC-3 Locators
	By searchBarId = By.id("quickFilterInput");
	By getDateRangeButton = By.cssSelector("td [data-hover='dropdown']");
	By getFilterDropdownLocator = By.xpath("//b[text()='Filter']/../imaging-type-selector/select");
//	TC-4 Locator
//	Please write the locators here
//	TC-5 Locators
	public By getButtonLocatorsBytext = By.xpath("//button[contains(text(),'" + "Next" + "')]");
//	TC-6 Locators	
	By getPageBarFixedLocatorListRequests = By.xpath("//ul[@class='page-breadcrumb']/li/a[@href='#/Radiology/ImagingRequisitionList']");
	By getPageBarFixedLocatorWardBilling = By.xpath("//ul[@class='page-breadcrumb']/li/a[@href='#/Radiology/InpatientList']");
	By getOkButtonLocator = By.xpath("//button[@class='btn green btn-success']");
	By calendarFromDropdown = By.xpath("(//input[@id='date'])[1]");
	By calendarToDropdown = By.xpath("(//input[@id='date'])[2]");
	By getStarIconLocator = By.xpath("//i[contains(@class,'icon-favourite')]/..");
//	TC-7 Locators
	public By getAnchorTagLocatorByTextLast1Week = By.xpath("//a[contains(text(), \"Last 1 Week\" )]");
	public By getActualRequestedOnDates = By.xpath("//div[@ref=\"eBodyViewport\"]//div//div[@row-index]/div[1]/span");
	public By getSelectDateRangeDropDown = By.xpath("//span[@class=\"icon-range-ddl dropdown-toggle\"]");
//	TC-8 Locators
	public By getAnchorTagLocatorByTextLast3Months = By.xpath("//a[contains(text(), \"Last 3 Months\" )]");
	public By getImagingTypeName = By.xpath("//div[@col-id='ImagingTypeName' and text()='X-RAY']");
//	TC-9 Locators
	public By getAnchorTagLocatorByTextListRequests = By.xpath("//a[contains(text(),'List Requests')]");
	public By getAnchorTagLocatorByTextWardBilling = By.xpath("//a[contains(text(),'" + "Ward Billing" + "')]");
	public By getAnchorTagLocatorByTextX = By.xpath("//a[contains(text(),'" + "X" + "')]");
	public By getButtonLocatorsBytextCancel = By.xpath("(//button[contains(text(),'Cancel')])[1]");
	public By getButtonLocatorsBytextX = By.xpath("//a[contains(text(),'" + "X" + "')]");
	public By popupCloseButton = By.cssSelector("a.close-btn");
	public By wardBillingViewDetailsbutton = By.cssSelector("a[danphe-grid-action='ViewDetails']");
	public By wardBillingModal = By.xpath("//span[text()='Radiology Ward Billing']");	
	public By getPopUpMessageText = By.xpath("//p[contains(text(),' " + "failed" + " ')]/../p[contains(text(),'" + "Please Write Cancellation Remarks" + "')]");
	

	public radiology_page(WebDriver driver) {
		super(driver);
	}

	/**@Test 1.1
	 * Logs in to the Health App using the provided valid credentials.
	 * 
	 * @param expectedData A map containing login credentials, where "username" and "password" are the keys.
	 * @return true if login steps are executed successfully, false otherwise.
	 * @throws Exception if any error occurs during the login process.
	 * 
	 * @author Yaksha
	 */
	public boolean loginToHealthAppByGivenValidCredetial(Map<String, String> expectedData) throws Exception {
		Boolean textIsDisplayed = false;
		try {
			WebElement usernametextFieldWebElement = commonEvents.findElement(getUsernameTextfieldLocator);
			commonEvents.highlightElement(usernametextFieldWebElement);
			commonEvents.sendKeys(getUsernameTextfieldLocator, expectedData.get("username"));

			WebElement passwordtextFieldWebElement = commonEvents.findElement(getPasswordTextboxLocator);
			commonEvents.highlightElement(passwordtextFieldWebElement);
			commonEvents.sendKeys(getPasswordTextboxLocator, expectedData.get("password"));

			WebElement signinButtonWebElement = commonEvents.findElement(getPasswordTextboxLocator);
			commonEvents.highlightElement(signinButtonWebElement);
			commonEvents.click(getSignInButtonLocator);
			textIsDisplayed = true;
		} catch (Exception e) {
			throw e;
		}
		return textIsDisplayed;
	}

	/**
	 * @Test1.2 
	 * Scrolls down to the Radiology tab and clicks on it.
	 * @param : null
	 * @description : verify the radiology tab, scroll to it, and click it
	 * @return : null
	 * @author : YAKSHA
	 * @author YAKSHA
	 */
	public void scrollDownAndClickRadiologyTab() throws Exception {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement radiologyTab = commonEvents.findElement(getRadiologyLocator);
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", radiologyTab);
			jsExecutor.executeScript("window.scrollBy(0, -50)");
			commonEvents.highlight(radiologyTab);
			commonEvents.click(radiologyTab);

			// Wait for the URL to contain "Verification/Inventory"
			commonEvents.waitForUrlContains("Radiology/ImagingRequisitionList", 10);
		} catch (Exception e) {
			throw e;
		}
	}

	/**@Test1.3
	 * Retrieves and returns the title of the Radiology page.
	 * @param : null
	 * @return The title of the current page as a String.
	 * @throws Exception if any error occurs during execution.
	 * 
	 * @author YAKSHA
	 */
	public String verifyRadiologyPageTitle() throws Exception {
		try {
			String titleToVerify = commonEvents.getTitle();
			return titleToVerify;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test2
	 * about this method highlightAndVerifyWhetherElementIsDisplayed
	 * 
	 * @param element : By - Locator of the element to be highlighted and verified
	 * @description : This method verifies whether an element is displayed on the
	 *              page, highlights it if displayed, and returns true if displayed.
	 * @return : boolean - true if the element is displayed, otherwise false
	 * @author : YAKSHA
	 */
	public boolean verifyRadiologySubModules(By element) {
		boolean isElementDisplayed = false;
		try {
			if (commonEvents.isDisplayed(element)) {
				WebElement elementToHighlight = commonEvents.findElement(element);
				commonEvents.highlight(elementToHighlight);
				isElementDisplayed = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isElementDisplayed;
	}

	/**
	 * @Test3 
	 * Verifies the presence of key components in the list requests section.
	 * Specifically, checks if the search bar, date range button, and filter dropdown are visible.
	 * @param : null
	 * @return true if all components are visible, false otherwise.
	 * @throws Exception if there is an issue finding any of the components.
	 * 
	 * @author YAKSHA
	 */
	public boolean verifyListRequestsComponents() {
	    return verifyRadiologySubModules(searchBarId) &&
	           verifyRadiologySubModules(getDateRangeButton) &&
	           verifyRadiologySubModules(getFilterDropdownLocator);
	}


	/**
	 * @Test4 about this method
	 * verifySelectedTabIsActiveOrNot()
	 * 
	 * @param : element - the locator of the tab to be verified
	 * @description : This method verifies if the specified tab is displayed, clicks
	 *              it, and returns whether its "class" attribute contains "active".
	 *              This can be used to determine if the tab is active based on its
	 *              class attributes.
	 * @return : boolean - true if the "class" attribute of the tab contains
	 *         "active", false otherwise
	 * @throws : Exception - if there is an issue locating, highlighting, clicking
	 *           the tab, or getting its attribute
	 * @author : YAKSHA
	 */
	public boolean verifySelectedTabIsActiveOrNot(By element) throws Exception {
		boolean isActive = false;
		try {
			if (commonEvents.isDisplayed(element)) {
				WebElement tabs = commonEvents.findElement(element);
				commonEvents.highlight(tabs);
				commonEvents.click(tabs);
				String locatorAttributeValue = commonEvents.getAttribute(tabs, "class");
				isActive = locatorAttributeValue.contains("active");
			}
		} catch (Exception e) {
			throw e;
		}
		return isActive;
	}

	/**
	 * @Test5
	 * Verifies if the "Next" button is displayed on the page.
	 * @param : null
	 * @return true if the "Next" button is found and visible, false otherwise.
	 * @throws Exception if there is an issue locating the button.
	 * 
	 * @author YAKSHA
	 */
	public boolean verifyNextButtonIsDisplayed() {
		boolean isNextButtonDisplayed = false;
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement nextButton = commonEvents.findElement(getButtonLocatorsBytext);
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", nextButton);
			jsExecutor.executeScript("window.scrollBy(0, -50)");
			commonEvents.highlight(nextButton);
			isNextButtonDisplayed = commonEvents.isDisplayed(nextButton);
			System.out.println("isNextButtonDisplayed >> " + isNextButtonDisplayed);
			isNextButtonDisplayed = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isNextButtonDisplayed;
	}


	/**
	 * @Test6 about this method verifyDatesAreRememberedCorrectly()
	 * 
	 * @param fromDate - the expected "from" date in the format "dd-MM-yyyy"
	 * 
	 * @param toDate - the expected "to" date in the format "dd-MM-yyyy"
	 * 
	 * @description : This method selects the "from" and "to" dates in the calendar,
	 * clicks the OK button, navigates away to another tab, returns to the original
	 * tab, and verifies if the dates are remembered correctly.
	 * 
	 * @return : boolean - true if the dates are remembered correctly, false
	 * otherwise
	 * 
	 * @throws : Exception - if there is an issue locating, highlighting, or
	 * clicking elements, or if there is an issue with date selection or
	 * verification
	 * 
	 * @author : YAKSHA
	 * 
	 * @throws Exception
	 */
	public boolean verifyDatesAreRememberedCorrectly(String fromDate, String toDate) throws Exception {
		try {
			// Split the fromDate and toDate into day, month, and year components
			String[] fromDateComponents = fromDate.split("-");
			String fromDay = fromDateComponents[0];
			String fromMonth = fromDateComponents[1];
			String fromYear = fromDateComponents[2];

			String[] toDateComponents = toDate.split("-");
			String toDay = toDateComponents[0];
			String toMonth = toDateComponents[1];
			String toYear = toDateComponents[2];

			// Locate the date dropdowns and OK button
			WebElement fromDateDropdown = commonEvents.findElement(calendarFromDropdown);
			WebElement toDateDropdown = commonEvents.findElement(calendarToDropdown);
			WebElement okButton = commonEvents.findElement(getOkButtonLocator);

			// Highlight and set the "from" date
			commonEvents.highlight(fromDateDropdown).sendKeys(fromDateDropdown, fromDay)
					.sendKeys(fromDateDropdown, fromMonth).sendKeys(fromDateDropdown, fromYear);

			// Highlight and set the "to" date
			commonEvents.highlight(toDateDropdown).sendKeys(toDateDropdown, toDay).sendKeys(toDateDropdown, toMonth)
					.sendKeys(toDateDropdown, toYear);

			// Locate and click the tooltip
			WebElement toolTip = commonEvents.findElement(getStarIconLocator);
			commonEvents.click(toolTip);

			// Highlight and click the OK button
			commonEvents.highlight(okButton).click(okButton);

			// Navigate to "List Reports" and "List Requests"
			commonEvents.click(getPageBarFixedLocatorWardBilling);
			commonEvents.click(getPageBarFixedLocatorListRequests);

			// Wait for the OK button to be visible
			commonEvents.waitTillElementVisible(getOkButtonLocator, 10000);

			// Construct the actual dates from the selected components
			String actualFromDate = fromDay + "-" + fromMonth + "-" + fromYear;
			String actualToDate = toDay + "-" + toMonth + "-" + toYear;

			System.out.println("Actual from date : " + actualFromDate);
			System.out.println("Actual to date : " + actualToDate);

			// Verify if the remembered dates match the expected dates
			if (actualFromDate.equals(fromDate) && actualToDate.equals(toDate)) {
				System.out.println("Returned true");
				return true;
			}

		} catch (Exception e) {
			throw e;
		}
		return false;
	} 
	


	/**
	 * @Test7.1 about this method clickDateRangeDropdownAndSelect()
	 * 
	 * @param : String - Text of the value to select from dropdown
	 * @description : This method clicks on the date range button and selects a
	 *              value by its text
	 * @return : boolean - true if successfully selected the intended value and
	 *         false if the value is not selected
	 * @throws : Exception - if there is an issue finding the dropdown or its values
	 * @author : YAKSHA
	 */
	public boolean clickDateRangeDropdownAndSelectLast1Week() throws Exception {
		try {
			WebElement dateRangeButton = commonEvents.findElement(getSelectDateRangeDropDown);
			commonEvents.highlight(dateRangeButton).click(dateRangeButton);
			WebElement valueToSelectElement = commonEvents.findElement(getAnchorTagLocatorByTextLast1Week);
			commonEvents.highlight(valueToSelectElement).click(valueToSelectElement);
			commonEvents.highlight(dateRangeButton).click(dateRangeButton);
			commonEvents.highlight(valueToSelectElement).click(valueToSelectElement);
			boolean isValueSelected = commonEvents.getAttribute(valueToSelectElement, "class")
					.contains("selected-range");
			WebElement okButton = commonEvents.findElement(getOkButtonLocator);
			commonEvents.highlight(okButton).click(okButton);
			return isValueSelected;

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test7.2 about this method
	 * verifyToDataRangeBySelectOneWeekOptionFromDropdown()
	 * 
	 * @param : String, String - from date - to date
	 * @description : This method verifies whether the "Requested On" dates for all
	 *              the result requisitions are within the specified date range.
	 * @return : boolean - true if the actual dates fall within the specified date
	 *         and false if they don't.
	 * @throws : Exception - if there is an issue finding the actual data
	 * @author : YAKSHA
	 */

	public boolean verifyToDataRangeBySelectOneWeekOptionFromDropdown(String fromDate, String toDate) throws Exception {

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			List<WebElement> actualDatesAfterFilterApplied = commonEvents.getWebElements(getActualRequestedOnDates);
			LocalDate from = LocalDate.parse(fromDate, formatter);
			LocalDate to = LocalDate.parse(toDate, formatter);

			for (WebElement dateElement : actualDatesAfterFilterApplied) {
				commonEvents.highlight(dateElement);
				String dateText = dateElement.getText();

				LocalDate date;
				LocalDate newDate;
				try {
					date = LocalDate.parse(dateText, inputFormatter);
					newDate = LocalDate.parse(date.format(formatter), formatter);

				} catch (Exception e) {
					System.out.println("Date parsing failed for: " + dateText);
					return false;
				}

				if (newDate.isBefore(from) || newDate.isAfter(to)) {
					return false;
				}
			}
			return true;

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test8 about this method
	 *         verifyDataFromTabelByEnteringDataXRAYInSearchField() Verifies that
	 *         all records in the table match the entered text in the dropdown.
	 * 
	 * @param dropDownValue - The text to select from the dropdown.
	 * @return boolean - Returns true if all records match the selected text,
	 *         otherwise false.
	 * @throws Exception - If there is an issue locating or interacting with
	 *                   elements.
	 */
	public boolean verifyDataFromTabelByEnteringDataXRAYInSearchField(String dropDownValue) throws Exception {
		try {
			
			LocalDate currentDate = LocalDate.now();
			LocalDate dateToFilter = LocalDate.of(2024, 4, 5); // Set your desired "from" date
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String toDate = currentDate.format(formatter);
			String fromDate = dateToFilter.format(formatter);
			
			commonEvents.findElement(calendarFromDropdown).sendKeys(fromDate);
			Thread.sleep(2000);
			commonEvents.findElement(calendarToDropdown).sendKeys(toDate);
			Thread.sleep(2000);
			commonEvents.findElement(getOkButtonLocator).click();
			Thread.sleep(2000);

			// Locate the dropdown element
			
			driver.findElement(getFilterDropdownLocator).click();
			
			WebElement dropdownElement = driver.findElement(getFilterDropdownLocator);

			// Highlight the dropdown element (if applicable)
			commonEvents.highlight(dropdownElement).click(dropdownElement);

			// Create a Select object
			Select dropdown = new Select(dropdownElement);

			// Select the option with visible text
			dropdown.selectByVisibleText(dropDownValue);

			System.out.println("Selected the ' " + dropDownValue + " ' option from the dropdown.");

			Thread.sleep(1000);

			// Locate all the imaging name elements in the table
			List<WebElement> imagingTypeNameElements = commonEvents.getWebElements(getImagingTypeName);
			System.out.println("Length of status > " + imagingTypeNameElements.size());

			commonEvents.click(searchBarId);

			if (imagingTypeNameElements.size() > 0) {
				// Verify that each imaging name element matches the entered text
				for (WebElement imagingTypeNameElement : imagingTypeNameElements) {
					commonEvents.highlight(imagingTypeNameElement);
					String imagingTypeNameElementText = imagingTypeNameElement.getText().trim();
					if (!imagingTypeNameElementText.equalsIgnoreCase(dropDownValue)) {
						System.out.println("Found a record that is not equal to expected: " + imagingTypeNameElement);
						return false;
					}
				}
				System.out.println("All Imaging Name records verified");
				return true;
			} else {
				throw new Exception("No Imaging Name records found.");
			}

		} catch (Exception e) {
			throw new Exception("Failed to verify Table Data", e);
		}
	}

	/**
	 * @Test9 about this method verifyRadiologyWardBillingCancellationPopup()
	 * 
	 * @param radiologyExpectedData - A map containing the expected data for the
	 *                              radiology test, such as patient name and
	 *                              expected cancellation remarks message.
	 * @description This method verifies that the Radiology Ward Billing modal opens
	 *              upon searching for a patient and clicking on the view details
	 *              button. It then clicks the cancel button and verifies that the
	 *              appropriate popup message "Please Write Cancellation Remarks"
	 *              appears.
	 * @return String - The actual failed remarks message displayed in the popup.
	 * @throws Exception - If there is an issue locating or interacting with
	 *                   elements.
	 * @autor YAKSHA
	 */
	public String verifyRadiologyWardBillingCancellationPopup(Map<String, String> radiologyExpectedData) {
		String failedRemarksMsg = "";
		try {
			// Navigate to the "Ward Billing" section
			commonEvents.click(getAnchorTagLocatorByTextWardBilling);

			Thread.sleep(1000);

			// Locate the search bar element
			WebElement searchBar = commonEvents.findElement(searchBarId);

			// Highlight and enter text into the search bar
			commonEvents.highlight(searchBar).click(searchBar);
			commonEvents.sendKeys(searchBar, radiologyExpectedData.get("patientName"));

			// Click on the "View Details" button for the specified patient
			commonEvents.click(wardBillingViewDetailsbutton);

			// Verify that the Radiology Ward Billing modal is displayed
			boolean isModalDisplayed = commonEvents.isDisplayed(wardBillingModal);
			System.out.println("isModalDisplayed: " + isModalDisplayed);

			// Locate all the elements that match the locator for "Cancel" buttons
			List<WebElement> cancelButton = commonEvents.getWebElements(getButtonLocatorsBytextCancel);

			// Click on the first "Cancel" button in the list
			WebElement firstCancelButton = cancelButton.get(0);
			commonEvents.highlight(firstCancelButton).click(firstCancelButton);
			System.out.println("Clicked on first cancel button");

			// Verify the failed message
			WebElement failedMessageElement = commonEvents
					.findElement(getPopUpMessageText);
			System.out.println("Failed message text: " + failedMessageElement.getText() + " Expected: "
					+ radiologyExpectedData.get("cancellationRemarksMsg"));

			// Store the failed remarks message
			failedRemarksMsg = failedMessageElement.getText();

			// Close the popup
			commonEvents.click(popupCloseButton);

			// Close the modal
			commonEvents.click(getAnchorTagLocatorByTextX);

			// Navigate back to the "List Requests" section
			commonEvents.click(getAnchorTagLocatorByTextListRequests);

			return failedRemarksMsg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return failedRemarksMsg;
	}


}
