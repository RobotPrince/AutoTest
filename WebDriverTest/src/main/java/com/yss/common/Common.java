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
import org.openqa.selenium.JavascriptExecutor;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common {
	public static WebDriver driver;
	public static final long SLEEP_TIME = 2000l;
	/**
	 * sleep 用来等待页面加载
	 */
	public static void loading(){
		Common.logInfo("loading");
		
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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

	public static MyResponse click(WebElement element) {

		logInfo("click");
		MyResponse myResponse = new MyResponse();
		if (!element.isDisplayed()) {
			logError("Element can't click for" + element + " in "
					+ Thread.currentThread().getStackTrace()[2].getClassName());
			return myResponse.failed("Click element of "+element+" failed");
		}
		element.click();
		Common.logWarn("Click "+element+" Success");
		return myResponse.success("Click element of "+ element+" successed");
	}
	/**
	 * 双击，此方法不稳定
	 * @param element
	 * @return
	 */
	@Deprecated
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
	 * 
	 * @param element
	 * @param elementEnum
	 * @param val
	 * @return
	 */
	public static MyResponse select(PageEnum pageEnum, AllElementEnum allElementEnum, ElementEnum elementEnum, WebElement element, String val){
		
		logInfo("select");
		MyResponse myResponse = new MyResponse();
//		
//		if(!val.equals( elementEnum.toString()) ){
//			logWarn(val+" is not "+elementEnum.toString()+" can't select");
//			return myResponse.success();
//		}
		//点击进入到select列表
		myResponse = click(element);
		if((int)myResponse.get(MyResponse.STATUS)== MyResponse.FAILED){
			logError("get Webelement of"+element +"failed");
			return myResponse.failed("getWebElement failed");
		}
		//选择下拉列表，点击
		 ElementEnum[] enumConstants=elementEnum.getClass().getEnumConstants();
		 for(ElementEnum eEnum : enumConstants){
			 if(val.toLowerCase().equals(eEnum.toString().toLowerCase())){
				 //获取要点击的元素
				 myResponse= getWebElement(pageEnum, allElementEnum, eEnum);
				
				if((int)myResponse.get(MyResponse.STATUS)== MyResponse.FAILED){
					logError("get Webelement of"+eEnum +"failed");
					return myResponse.failed("getWebElement failed");
				}
				//点击
				myResponse = Common.click( (WebElement)myResponse.get("ele") );
				break;
			 }
		 }
		return myResponse.success();
	}
	/**
	 * 
	 * @param element
	 * @param param
	 * @param remarkEnum
	 * @return
	 */
	
	public static MyResponse proccessTable( PageEnum pageEnum,AllElementEnum allElementEnum, ElementEnum elementEnum, WebElement element, String param, Object remarkEnum){
		
		logInfo("proccessTable");
		MyResponse myRespnose = new MyResponse();
		if (!element.isDisplayed()) {
			logError("Element can't setParameter for" + element + " in "
					+ Thread.currentThread().getStackTrace()[2].getClassName());
			return myRespnose.failed("Element of "+element+" not displayed");
		}
		if( RemarkEnum.valueOf(String.valueOf(remarkEnum) ) == null){
			logError("cast remrkEnum"+remarkEnum+" faild, param error");
			return myRespnose.failed( "cast remrkEnum"+remarkEnum+" faild, param error" );
		}
		switch(RemarkEnum.valueOf((String)remarkEnum)){
		case READABLE:
			//TODO:
			MyResponse readResponse = read(element);
			break;
		case WRITABLE: 
			MyResponse writeResponse = setParameter(element, param);
			if( ( (int)writeResponse.get(MyResponse.STATUS) == (MyResponse.FAILED)) ){
				logError("setParamenter of "+element+" failed");
				return myRespnose.failed("setParamenter of "+element+" failed");
			}
			break;
		case CLICKABLE:
			MyResponse clickResponse = click(element);
			if( ( (( (int)clickResponse.get(MyResponse.STATUS) ))==(MyResponse.FAILED)) ){
				logError("click element of "+element+" failed");
				return myRespnose.failed("clickResponse of "+element+" failed");
			}
			break;
		case SELECTABLE:
			MyResponse selectResponse = select(pageEnum, allElementEnum, elementEnum,element, param);
			if( ( (int)selectResponse.get(MyResponse.STATUS) == (MyResponse.FAILED)) ){
				logError("select element of"+element +"failed");
				return myRespnose.failed("select element of"+element +"failed");
			}
			break;
		case ALLABLE:
			//TODO:
			break;
		case NONE:
			//TODO:
			break;
		default:logError(remarkEnum+"not supported yet");
		return myRespnose.failed(remarkEnum+"not supported yet");
		}
		
		return myRespnose.success();
		
		
	}
	//TODO:
	public static MyResponse read(WebElement webElement){
		return new MyResponse();
	}

	/**
	 * 键入值
	 * 
	 * @param element
	 * @param param
	 * @return boolean
	 * @author tanglonglong
	 */
	public static MyResponse setParameter(WebElement element, String param) {

		logInfo("setParameter");
		MyResponse myResponse = new MyResponse();
		if (!element.isDisplayed()) {
			logError("Element can't setParameter for" + element + " in "
					+ Thread.currentThread().getStackTrace()[2].getClassName());
			return myResponse.failed("Element of "+element+" not displayed");
		}
		element.sendKeys(param);
		return myResponse.success();
	}

	/**
	 * getText
	 * 
	 * @author tanglonglong
	 * @param element
	 * @return
	 */
	public static String getText(WebElement element) {

		logInfo("getText");
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
	@Deprecated
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
		String locatorRemark = list.get(2);
		
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
			return myResponse.failed("Not supported for " + type + " yet");
		}
		//闪烁一下当前找到的元素
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border = '2px solid red'", findElement);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border = 'none'", findElement);
		RemarkEnum remarkEnum = null;
		
		switch(locatorRemark){
		case "C":remarkEnum = RemarkEnum.CLICKABLE;break;
		case "R":remarkEnum = RemarkEnum.READABLE;break;
		case "W":remarkEnum = RemarkEnum.WRITABLE;break;
		case "A":remarkEnum = RemarkEnum.ALLABLE;break;
		case "N":remarkEnum = RemarkEnum.NONE;break;
		case "S":remarkEnum = RemarkEnum.SELECTABLE;break;
		default:
			logError("Not support for "+locatorRemark+" yet!");
			return myResponse.failed("Not support for "+locatorRemark+" yet!");
		}
		myResponse.successWithData("rem", remarkEnum);
		return myResponse.successWithData("ele", findElement);
	}
	/**
	 * getElemenstDate(pageEnum,allElementEnum,elementEnum)
	 * 获取元素定位的相关数据
	 * @param pageEnum
	 * @param allElementEnum
	 * @param elementEnum
	 * @return MyResponse<"ele",WebElement>
	 */
	@SuppressWarnings("finally")
	public static MyResponse getWebElements(PageEnum pageEnum,AllElementEnum allElementEnum,ElementEnum elementEnum) {
		Common.logInfo("getWebElements");
		
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
		
		List<WebElement> findElements = null;
		String locatorType = list.get(0);
		String locatorVal = list.get(1);
		String locatorRemark = list.get(2);
		
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
				findElements = driver.findElements(By.xpath(locatorVal));
			} 
			catch (org.openqa.selenium.NoSuchElementException ex) {
				Common.logError("findElement is "+findElements);
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
				findElements = driver.findElements(By.cssSelector(locatorVal));
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
				findElements = driver.findElements(By.id(locatorVal));
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
			return myResponse.failed("Not supported for " + type + " yet");
		}
		
		RemarkEnum remarkEnum = null;
		
		switch(locatorRemark){
		case "C":remarkEnum = RemarkEnum.CLICKABLE;break;
		case "R":remarkEnum = RemarkEnum.READABLE;break;
		case "W":remarkEnum = RemarkEnum.WRITABLE;break;
		case "A":remarkEnum = RemarkEnum.ALLABLE;break;
		case "N":remarkEnum = RemarkEnum.NONE;break;
		case "S":remarkEnum = RemarkEnum.SELECTABLE;break;
		default:
			logError("Not support for "+locatorRemark+" yet!");
			return myResponse.failed("Not support for "+locatorRemark+" yet!");
		}
		myResponse.successWithData("rem", remarkEnum);
		return myResponse.successWithData("ele", findElements);
	}
	/**
	 * @author tanglonglong
	 * @return
	 */
	public static MyResponse clickTopAdd(){
		logInfo("clickAdd");
		//切换driver到default
		Common.driver.switchTo().defaultContent();
		// 切换driver到top
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return iframe1Response.failed("get element data of iframe1 failed");
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		//点击新增
		MyResponse addTopResponse = Common.getWebElement(PageEnum.COMMON,AllElementEnum.CommonElementEnum, CommonElementEnum.ADD_TOP);
		if ((int) addTopResponse.get(MyResponse.STATUS) == MyResponse.FAILED) {
			Common.logError("get element data of top response failed");
			return addTopResponse.failed("get element data of top response failed");
		}
		MyResponse clickAddTopResponse = Common.click((WebElement)addTopResponse.get("ele"));
		if(((int)clickAddTopResponse.get(MyResponse.STATUS))==MyResponse.FAILED){
			Common.logError("click element of add top failed");
			return clickAddTopResponse.failed("click element of add top failed");
		}
		//切换driver到add
		MyResponse iframe2Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM2);
		if( (int)iframe2Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return iframe2Response.failed("get element data of iframe1 failed");
		}
		Common.driver.switchTo().frame((WebElement)iframe2Response.get("ele"));
		return new MyResponse().success();
	}
	/**
	 * 点击确定
	 * @return
	 */
	public  static  MyResponse clickYES(){

		logInfo("clickYES");
		//切换driver
		Common.driver.switchTo().defaultContent();
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return iframe1Response.failed("get element data of iframe1 failed");
			}
		
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		
		MyResponse webOKElement = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_OK);
		if((int)webOKElement.get(MyResponse.STATUS)==MyResponse.FAILED){
			logError("click button of"+webOKElement.get("ele")+"failed");
			return webOKElement.failed("click button of"+webOKElement.get("ele")+"failed");
		}
		MyResponse clickYesResponse = Common.click((WebElement)webOKElement.get("ele"));
		if((int)clickYesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			logError("click button of"+webOKElement.get("ele")+"failed");
			return clickYesResponse.failed("click button of"+webOKElement.get("ele")+"failed");
		}
		return new MyResponse().success();
	}
	/**
	 *  
	 * @param by
	 * @return
	 */
	public static boolean waitForElement( final By elementLocator) {
		Common.logInfo("waitForElement");
        try {
            WebDriverWait driverWait = (WebDriverWait) new WebDriverWait(driver, 5, 500).ignoring(StaleElementReferenceException.class).withMessage("元素在10秒内没有出现!");
            return driverWait.until(new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driver) {
                	System.out.println("apply in");
                    try {
                    	WebElement webElement;
                    	By elementLocator_N = elementLocator;
                         if ((webElement = driver.findElement(elementLocator_N))==null) {
                        	 return false;
                         }
                    	  if(!waitForElementVisible(elementLocator_N,10)){
                    		  Common.logWarn("Not find e");
                    		  return false;
                    	  }else{
                    		  
                    		  System.out.println("isVisible");
                    	  }
//                    	  if(!webElement.isDisplayed()){
//                    		  Common.logWarn("Not find e");
//                    		  return false;
//                    	  }else{
//                    		  
//                    		  System.out.println("isDisplayed");
//                    	  }
                    } catch (IndexOutOfBoundsException e) {
                    	Common.logError("waitForElement"+elementLocator+" failed, Some errors in code");
                    	return false;
                    } catch (NoSuchElementException e) {
                    	Common.logError("waitForElement"+elementLocator+" failed, NO Such Element");
                    	return false;
                    }
                    Common.shot();
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
     * 判断元素在指定时间是否显示
     * 元素是否在指定时间内显示（存在dom结构且属性为显示）马上返回true
     * 如果到指定时间仍未显示（不存在与dom结构 或者存在于dom结构但属性为‘隐藏’）则返回false
     * 适用于ajax
     * 
     * @param by 元素
     * @param seconds 指定秒数
     * @return 出现返回true 否则返回false
     */
    public static boolean waitForElementVisible(final By by, int seconds) {
        try {
            new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
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
		 * 点击复选框选中项目
		 */
		ITEM_CHECKBOX,
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
