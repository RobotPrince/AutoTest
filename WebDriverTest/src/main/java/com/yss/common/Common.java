package com.yss.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		Common.logWarn("Click "+element+" Success");
		return true;
	}
	
	public static boolean doubleClick(WebElement element) {
		
		logInfo("doubleClick");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!element.isDisplayed()) {
			logError("Element can't click for" + element + " in "
					+ Thread.currentThread().getStackTrace()[2].getClassName());
			return false;
		}
		Actions actions=new Actions(Common.driver);
	    if( actions.doubleClick(element) == null){
	    	logError("Element of "+element.toString()+" double faied");
	    	return false;
	    }
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
	 * @return MyResponse<"ele",WebElement>
	 */
	//FIXME:删除掉
	@SuppressWarnings("finally")
	public static MyResponse getWebElementOld(String locatorVal, String locatorType) {

		MyResponse myResponse = new MyResponse();
		WebElement findElement = null;
		LocatorTypeEnum type = LocatorTypeEnum.valueOf(locatorType
				.toUpperCase());
		
		switch (type) {
		case XPATH:
			if(!Common.waitForElement(By.xpath(locatorVal))){
				Common.logError("Element of " + locatorVal + " can't find");
				myResponse.failed("Element of " + locatorVal + " can't find");
				break;
			}
			try {
				findElement = driver.findElement(By.xpath(locatorVal));
			} 
			catch (org.openqa.selenium.NoSuchElementException ex) {
				Common.logError("Element of " + locatorVal + " can't find,Some errors in code");
				myResponse.failed("Element of " + locatorVal + " can't find,Some errors in code");
				break;
			}
			finally{
				break;
			}
		case CSS:
			if(!Common.waitForElement(By.cssSelector(locatorVal))){
				Common.logError("Element of " + locatorVal + " can't find");
				myResponse.failed("Element of " + locatorVal + " can't find");
				break;
			}
			try {
				findElement = driver.findElement(By.cssSelector(locatorVal));
			}
			catch (org.openqa.selenium.NoSuchElementException ex) {
				Common.logError("Element of " + locatorVal + " can't find,Some errors in code");
				myResponse.failed("Element of " + locatorVal + " can't find,Some errors in code");
			}
			finally{
				break;
			}
		case ID:
			if(!Common.waitForElement(By.id(locatorVal))){
				Common.logError("Element of " + locatorVal + " can't find");
				myResponse.failed("Element of " + locatorVal + " can't find");
				break;
			}
			try {
			findElement = driver.findElement(By.id(locatorVal));
			}
			catch (org.openqa.selenium.NoSuchElementException ex) {
				Common.logError("Element of " + locatorVal + " can't find,Some errors in code");
				myResponse.failed("Element of " + locatorVal + " can't find,Some errors in code");
			}
			finally{
				break;
			}
		default:
			logError("Not supported for " + type + " yet");
			myResponse.failed("Not supported for " + type + " yet");
		}
		myResponse.successWithData("ele", findElement);
		return myResponse.successWithData("ele", findElement);
	}
	/**
	 * getElementDate(pageEnum,allElementEnum,elementEnum)
	 * 获取元素定位的相关数据
	 * @param pageEnum
	 * @param allElementEnum
	 * @param elementEnum
	 * @return MyResponse<"ele",WebElement>
	 */
	@SuppressWarnings("finally")
	public static MyResponse getWebElement(PageEnum pageEnum,AllElementEnum allElementEnum,ElementEnum elementEnum) {
		Common.logInfo("getWebElement");
		MyResponse myResponse = new MyResponse();
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
			return myResponse.failed("ElementName not in excel");
		}
		
		list = hashMap.get(elementEnum.toString().toLowerCase());
		
		WebElement findElement = null;
		String locatorType = list.get(0);
		String locatorVal = list.get(1);
		LocatorTypeEnum type = LocatorTypeEnum.valueOf(locatorType
				.toUpperCase());
		
		switch (type) {
		case XPATH:
			if(!Common.waitForElement(By.xpath(locatorVal))){
				Common.logError("Element of " + locatorVal + " can't find");
				myResponse.failed("Element of " + locatorVal + " can't find");
				return myResponse;
			}
			try {
				findElement = driver.findElement(By.xpath(locatorVal));
			} 
			catch (org.openqa.selenium.NoSuchElementException ex) {
				Common.logError("findElement is "+findElement);
				Common.logError("Element of " + locatorVal + " can't find,Some errors in code");
				myResponse.failed("Element of " + locatorVal + " can't find,Some errors in code");
				return myResponse;
			}
			finally{
				break;
			}
		case CSS:
			if(!Common.waitForElement(By.cssSelector(locatorVal))){
				Common.logError("Element of " + locatorVal + " can't find");
				myResponse.failed("Element of " + locatorVal + " can't find");
				return myResponse;
			}
			try {
				findElement = driver.findElement(By.cssSelector(locatorVal));
			}
			catch (org.openqa.selenium.NoSuchElementException ex) {
				Common.logError("Element of " + locatorVal + " can't find,Some errors in code");
				myResponse.failed("Element of " + locatorVal + " can't find,Some errors in code");
				return myResponse;
			}
			finally{
				break;
			}
		case ID:
			if(!Common.waitForElement(By.id(locatorVal))){
				Common.logError("Element of " + locatorVal + " can't find");
				myResponse.failed("Element of " + locatorVal + " can't find");
				return myResponse;
			}
			try {
			findElement = driver.findElement(By.id(locatorVal));
			}
			catch (org.openqa.selenium.NoSuchElementException ex) {
				Common.logError("Element of " + locatorVal + " can't find,Some errors in code");
				myResponse.failed("Element of " + locatorVal + " can't find,Some errors in code");
				return myResponse;
			}
			finally{
				break;
			}
		default:
			logError("Not supported for " + type + " yet");
			myResponse.failed("Not supported for " + type + " yet");
		}
		return myResponse.successWithData("ele", findElement);
	}
//	/**
//	 * getElementDate(pageEnum,allElementEnum,elementEnum)
//	 * 获取元素定位的相关数据
//	 * @param pageEnum
//	 * @param allElementEnum
//	 * @param elementEnum
//	 * @return <"list",（type,value）>
//	 */
//	public static MyResponse getWebElement(PageEnum pageEnum,AllElementEnum allElementEnum,ElementEnum elementEnum) {
//		
//		List<String> list = new ArrayList<String>();
//		boolean isContains = false;
//		HashMap<String, List<String>> hashMap = ReadFromExcel.elementsFromExcel
//				.get(pageEnum);
//		//取出该页面对应的所有元素的名称
//		String[] str = AllElementEnum.valueOf(allElementEnum.toString()).valueToString();
//		for(String s : str){
//			if(s.equals(elementEnum.toString())){
//				isContains = true;
//			}
//		}
//		//若elementString不存在于所有元素名称则报错
//		if(isContains == false){
//			Common.logError("ElementName not in excel");
//			return new MyResponse().failed("ElementName not in excel");
//		}
//		
//		list = hashMap.get(elementEnum.toString().toLowerCase());
//		return new MyResponse().successWithData("list", list);
//	}
	
	/**
	 *  
	 * @param by
	 * @return
	 */
	public static boolean waitForElement( final By elementLocator) {

        try {
            WebDriverWait driverWait = (WebDriverWait) new WebDriverWait(driver, 30, 500).ignoring(StaleElementReferenceException.class).withMessage("元素在10秒内没有出现!");
            return driverWait.until(new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driver) {
                    try {
                    	By elementLocator_N = elementLocator;
                    	Common.logError(elementLocator_N.toString());
                         if (!driver.findElement(elementLocator_N).isDisplayed()) {
                        	 Common.logWarn("Not find e");
                            return false;
                        }
                    } catch (IndexOutOfBoundsException e) {
                    } catch (NoSuchElementException e) {
                    }
                    Common.logInfo("wait for elementLocator "+elementLocator.toString()+" successed");
////try {
////	Thread.sleep(2000l);
////} catch (InterruptedException e) {
////	// TODO Auto-generated catch block
////	e.printStackTrace();
//}
//                    WaitUtil.waitElementToBeDisplayed(Common.driver,elementLocator,10);
                    return true;
                }
            });
        } catch (Exception e) {
            return false;
        }

    }
	
	/**
	 * 定义所有的共有元素
	 * @author tanglonglong
	 *
	 */
	public enum CommonElementEnum implements ElementEnum{
		/**
		 * tab页面的新增
		 */
		ADD_TOP,
		 /**
		  * tab页面的删除
		  */
		DELETE_TOP,
		/**
		 * tab页面的审核
		 */
		REVIEW_TOP,
		/**
		 * tab页面的反审核
		 */
		UNREVIEW_TOP,
		/**
		 * 辅助定位页面
		 */
		IFRAM1,
		/**
		 * 编辑
		 */
		EDIT,
		/**
		 * 查看
		 */
		VIEW,
		/**
		 * 删除
		 */
		DELETE,
		/**
		 * 审核
		 */
		REVIEW,
		/**
		 * 反审核
		 */
		UNREVIEW,
		/**
		 * 复制
		 */
		COPY,
		/**
		 * 弹出确认框-是
		 */
		POPUP_YES,
		/**
		 * 弹出确认框-否
		 */
		POPUP_NO,
		/**
		 * 弹出确认框-ok
		 */
		POPUP_OK,
		/**
		 * 辅助定位新增页面
		 */
		IFRAM2,
		/**
		 * 详细页面提交
		 */
		COMMIT,
		/**
		 * 详细页面重置
		 */
		RESET
	}
}
