package testpro.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtil {

	public static void waitUntilElementByCssSelectorToBeClickable(WebDriver driver, String cssSelector) {
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(cssSelector)));
	}
	
	public static void waitUntilElementByCssSelector(WebDriver driver, String cssSelector) {
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector(cssSelector)));
	}
	
	public static void waitUntilElementByClassName(WebDriver driver, String className) {
		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.presenceOfElementLocated(
				By.className(className)));
	}
	
	public static WebElement findElementByClassName(WebDriver driver, String className) {	
		return driver.findElement(By.className(className));
	}
	
	public static WebElement findElementByCssSelector(WebDriver driver, String cssSelector) {	
		return driver.findElement(By.cssSelector(cssSelector));
	}
	
}
