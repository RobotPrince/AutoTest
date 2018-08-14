package com.yss.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Common {
	public static WebDriver driver;

	/**
	 * shot
	 * 
	 * @throws IOException
	 */
	public static void shot() {

		File screenShotDir = new File("./test-output/screen-shot");

		String SCREEN_SHOT_NAME = String.valueOf(new Date().getTime()) + ".jpg";
		try {
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File(screenShotDir + "/"+ SCREEN_SHOT_NAME));
			logWarn("the screenshot saved in " + "./test-output/screen-shot"
					+ " screenshot name : " + SCREEN_SHOT_NAME);

		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * LoggerInfo
	 */
	public static void logInfo(String str) {

		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		Logger logger = Logger.getLogger(stack[1].getClassName());
		logger.log(Common.class.getName(), Level.INFO, str, null);
	}

	/**
	 * LoggerWarn
	 */
	public static void logWarn(String str) {

		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		Logger logger = Logger.getLogger(stack[1].getClassName());
		logger.log(Common.class.getName(), Level.WARN, str, null);
	}

	/**
	 * Logger
	 * 
	 * @throws IOException
	 */
	public static void logError(String str) {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		Logger logger = Logger.getLogger(stack[1].getClassName());
		shot();
		logger.log(Common.class.getName(), Level.ERROR, str, null);
	}

	/**
	 * getFireFoxDriver
	 * 
	 * @return
	 * @author tanglonglong
	 */
	public static WebDriver getFFDriver() {
		String browserPath = "C:/Program Files/Mozilla Firefox/firefox.exe";
		System.setProperty("webdriver.firefox.bin", browserPath);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * click
	 * 
	 * @param element
	 * @return
	 * @author tanglonglong
	 */

	public static boolean click(WebElement element) {

		logInfo("click");
		if (!element.isDisplayed()) {
			logError("Element can't click for" + element + " in "
					+ Thread.currentThread().getStackTrace()[2].getClassName());
			return false;
		}
		element.click();
		return true;
	}

	/**
	 * setParameter
	 * 
	 * @param element
	 * @param param
	 * @return boolean
	 * @author tanglonglong
	 */
	public static boolean setParameter(WebElement element, String param) {

		logInfo("setParameter");
		if (!element.isDisplayed()) {
			logError("Element can't setParameter for" + element + " in "
					+ Thread.currentThread().getStackTrace()[2].getClassName());
			return false;
		}
		element.sendKeys(param);
		return true;
	}

	/**
	 * getText
	 * 
	 * @author tanglonglong
	 * @param element
	 * @return
	 */
	public static String getText(WebElement element) {

		logInfo("setParameter");
		if (!element.isDisplayed()) {
			logError("Element of" + element + " cant getText in "
					+ Thread.currentThread().getStackTrace()[2].getClassName());
			return null;
		}
		return element.getText();

	}

	/**
	 * getWebElement
	 * 
	 * @param locatorVal
	 * @param locatorType
	 * @author tanglonglong
	 */
	@SuppressWarnings("finally")
	public static WebElement getWebElement(String locatorVal, String locatorType) {

		WebElement findElement = null;
		LocatorTypeEnum type = LocatorTypeEnum.valueOf(locatorType
				.toUpperCase());

		switch (type) {
		case XPATH:
			try {
				findElement = driver.findElement(By.xpath(locatorVal));
			} 
			catch (org.openqa.selenium.NoSuchElementException ex) {
				Common.logError("Element of " + locatorVal + " can't find");
				break;
			}
			finally{
				break;
			}
		case CSS:
			try {
				findElement = driver.findElement(By.cssSelector(locatorVal));
			}
			catch (org.openqa.selenium.NoSuchElementException ex) {
				Common.logError("Element of " + locatorVal + " can't find");
				
			}
			finally{
				break;
			}
		case ID:
			try {
			findElement = driver.findElement(By.id(locatorVal));
			}
			catch (org.openqa.selenium.NoSuchElementException ex) {
				Common.logError("Element of " + locatorVal + " can't find");
				
			}
			finally{
				break;
			}
		default:
			logError("Not supported for " + type + " yet");
		}
		return findElement;
	}
	/**
	 * getElementDate(pageEnum,allElementEnum,elementEnum)
	 * 获取元素定位的相关数据
	 * @param pageEnum
	 * @param allElementEnum
	 * @param elementEnum
	 * @return
	 */
	public static MyResponse getElementData(PageEnum pageEnum,AllElementEnum allElementEnum,ElementEnum elementEnum) {
		
		List<String> list = new ArrayList<String>();
		boolean isContains = false;
		HashMap<String, List<String>> hashMap = ReadFromExcel.elementsFromExcel
				.get(pageEnum);
		//取出该页面对应的所有元素的名称
		String[] str = AllElementEnum.valueOf(allElementEnum.toString()).valueToString();
		for(String s : str){
			if(s.equals(elementEnum.toString())){
				isContains = true;
			}
		}
		//若elementString不存在于所有元素名称则报错
		if(isContains == false){
			Common.logError("ElementName not in excel");
			return new MyResponse().failed("ElementName not in excel");
		}
		
		list = hashMap.get(elementEnum.toString().toLowerCase());
		return new MyResponse().successWithData("list", list);
	}
}
