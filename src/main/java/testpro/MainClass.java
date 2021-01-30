package testpro;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import testpro.beans.MyFormData;
import testpro.excel.ExcelParserPoi;
import testpro.util.MySeleniumUtil;

public class MainClass {
	private static boolean signedIn = false;
	public static void main(String[] args) {
		
		System.out.println("hello rita");
		//setting the driver executable
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
	
		


		//Initiating your chromedriver
		WebDriver driver=new ChromeDriver();

		//Applied wait time
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//maximize window
		driver.manage().window().maximize();

		
		//open browser with desried URL
		String url = "http://localhost:8080/testpage1";
		
		for (MyFormData formData : ExcelParserPoi.getExcel()) {	
			navigateToUrl(driver, url);
			
			makeSurePageLoaded(driver);
			
			if(!signedIn) {
				signin(driver);
			}
			
			fillData(driver, formData);
			
			doSubmit(driver);
		}
		closeBrowser(driver);	
	}
	
	
	public static void navigateToUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	public static void makeSurePageLoaded(WebDriver driver) {
//		(new WebDriverWait(driver, 10))
//		.until(ExpectedConditions.presenceOfElementLocated(By.className("lfr-ddm-form-submit")));
		
		MySeleniumUtil.waitUntilElementByClassName(driver, "lfr-ddm-form-submit");
		
	}
	
	public static void closeBrowser(WebDriver driver) {
		//closing the browser
		driver.close();
	}
	
	public static void doSubmit(WebDriver driver) {
//		WebElement submitElement = driver.findElement(By.className("lfr-ddm-form-submit"));
		WebElement submitElement = MySeleniumUtil.findElementByClassName(driver, "lfr-ddm-form-submit");
		submitElement.click();
		
		/*(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("div.ddm-form-success-page")));*/
		
		MySeleniumUtil.waitUntilElementByCssSelector(driver, "div.ddm-form-success-page");
		
	}
	
	public static void fillData(WebDriver driver, MyFormData formData) {
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		WebElement nameElement = driver.findElement(By.cssSelector("input[placeholder='name']"));
		WebElement nameElement = MySeleniumUtil.findElementByCssSelector(driver, "input[placeholder='name']");
				
		//System.out.println("before wrting name");
		nameElement.sendKeys(formData.getName()); 
		//System.out.println("after wrting name");
//		WebElement calendarButton = driver.findElement(By.cssSelector("button.date-picker-dropdown-toggle.btn.btn-unstyled"));
//		WebElement calendarButton = SeleniumUtil.findElementByCssSelector(driver, "button.date-picker-dropdown-toggle.btn.btn-unstyled");
				
//		calendarButton.click();
		
		//new WebDriverWait(driver, 10);
		
		
//		WebElement chosenDateButton = driver.findElement(By.cssSelector("button[aria-label='2021 01 11']"));
		DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String dateOutput = formData.getDate().format(newPattern);
		System.out.println("date:" + dateOutput);
		WebElement dateElement = MySeleniumUtil.findElementByCssSelector(driver,
				".date-picker .form-control.input-group-inset.input-group-inset-after");
		dateElement.clear();
		dateElement.sendKeys(dateOutput);
		
//		WebElement chosenCheckboxElement = driver.findElement(By.cssSelector(
//				"div.lfr-ddm-checkbox-multiple .custom-control.custom-checkbox:nth-child(2)
//		label"));
		
		if(formData.isOption1()) {
			WebElement chosenCheckboxElement = MySeleniumUtil.findElementByCssSelector(
					driver, 
					"div.lfr-ddm-checkbox-multiple .custom-control.custom-checkbox:nth-child(1) "
					+ "label");
			chosenCheckboxElement.click();
		}
		if(formData.isOption2()) {
			WebElement chosenCheckboxElement = MySeleniumUtil.findElementByCssSelector(
					driver, 
					"div.lfr-ddm-checkbox-multiple .custom-control.custom-checkbox:nth-child(2) "
					+ "label");
			chosenCheckboxElement.click();
		}
		if(formData.isOption3()) {
			WebElement chosenCheckboxElement = MySeleniumUtil.findElementByCssSelector(
					driver, 
					"div.lfr-ddm-checkbox-multiple .custom-control.custom-checkbox:nth-child(3) "
					+ "label");
			chosenCheckboxElement.click();
		}
		
		
//		WebElement arrows = driver.findElement(By.cssSelector(
//				".select-arrow-down-container"));
//		
		WebElement arrows = MySeleniumUtil.findElementByCssSelector(driver,".select-arrow-down-container");
		
		arrows.click();
		
//		WebElement selectOptionButton = driver.findElement(By.cssSelector("button[data-testid='dropdownItem-3']"));
		String chosenOption = formData.getChosenOption();
		WebElement selectOptionButton = MySeleniumUtil.findElementByCssSelector(driver,"button[label='" + chosenOption + "']");

		selectOptionButton.click();
		
		
		uploadFile(driver, formData);
	}
	public static void uploadFile(WebDriver driver, MyFormData formData) {
//		WebElement uploadButton = driver.findElement(By.cssSelector(
//				"div.liferay-ddm-form-field-document-library button.select-button.btn.btn-secondary"));
		WebElement uploadButton = MySeleniumUtil.findElementByCssSelector(driver,
				"div.liferay-ddm-form-field-document-library button.select-button.btn.btn-secondary");

		uploadButton.click();
		
		driver.switchTo().frame("_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormPortlet_INSTANCE_bHlaSZg9L7J9_selectDocumentLibrary_iframe_");
		
//		WebElement uploadElement = driver.findElement(By.cssSelector("input#vwsx___InputFile"));
		
		WebElement uploadElement = MySeleniumUtil.findElementByCssSelector(driver,"input#vwsx___InputFile");
		
		uploadElement.sendKeys(formData.getFilePath()); 
		
		
//		WebElement addButton = driver.findElement(By.cssSelector(
//				"#p_p_id_com_liferay_item_selector_web_portlet_ItemSelectorPortlet_ ul.navbar-nav>li.nav-item>button.btn.btn-primary"));
		WebElement addButton = MySeleniumUtil.findElementByCssSelector(driver,
				"#p_p_id_com_liferay_item_selector_web_portlet_ItemSelectorPortlet_ ul.navbar-nav>li.nav-item>button.btn.btn-primary");

		addButton.click();

		
		driver.switchTo().defaultContent();
		
//		(new WebDriverWait(driver, 10))
//		.until(ExpectedConditions.presenceOfElementLocated(
//				By.cssSelector("div.liferay-ddm-form-field-document-library button.clear-button")));
		MySeleniumUtil.waitUntilElementByCssSelector(driver, "div.liferay-ddm-form-field-document-library button.clear-button");
		
	}
	
	
	public static void signin(WebDriver driver) {
		
//		WebElement signInButton = driver.findElement(By.cssSelector(
//				".taglib-icon-label"));
		WebElement signInButton =  MySeleniumUtil.findElementByCssSelector(driver,
				".taglib-icon-label");
		
		signInButton.click();
		

//		WebElement userNameElement = driver.findElement(By.cssSelector("#_com_liferay_login_web_portlet_LoginPortlet_login"));
		WebElement userNameElement =  MySeleniumUtil.findElementByCssSelector(driver,
				"#_com_liferay_login_web_portlet_LoginPortlet_login");
		
		userNameElement.clear();
		userNameElement.sendKeys("test@liferay.com"); 
		
//		WebElement passwordElement = driver.findElement(By.cssSelector("#_com_liferay_login_web_portlet_LoginPortlet_password"));
		WebElement passwordElement =  MySeleniumUtil.findElementByCssSelector(driver,
				"#_com_liferay_login_web_portlet_LoginPortlet_password");

		passwordElement.sendKeys("test"); 
		
		
//		WebElement submitButton = driver.findElement(By.cssSelector(
//				"form#_com_liferay_login_web_portlet_LoginPortlet_loginFormModal button[type='submit'].btn.btn-primary"));
		WebElement submitButton =  MySeleniumUtil.findElementByCssSelector(driver,
				"form#_com_liferay_login_web_portlet_LoginPortlet_loginFormModal button[type='submit'].btn.btn-primary");

		submitButton.click(); 
		
//		(new WebDriverWait(driver, 10))
//		.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body.has-control-menu")));
		MySeleniumUtil.waitUntilElementByCssSelector(driver, "body.has-control-menu");
		
		signedIn=true;
	}

	
	
	
	
}
