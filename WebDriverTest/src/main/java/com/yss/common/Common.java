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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Common {
	public static WebDriver driver;
	//单位毫秒
	public static final long SLEEP_TIME = 2000l;
	//单位秒
	public static final long WAIT100PERCENT = 300l;
	
	/**
	 * getFireFoxDriver
	 * 
	 * for firefox
	 * @return
	 * @author tanglonglong
	 */
//	public static WebDriver getFFDriver() {
//		String browserPath = "C:/Program Files/Mozilla Firefox/firefox.exe";
//		System.setProperty("webdriver.firefox.bin", browserPath);
//		driver = new FirefoxDriver();
//		driver.manage().window().maximize();
//		return driver;
//	}
	/**
	 * getFireFoxDriver
	 * 
	 * for chrome
	 * @return
	 * @author tanglonglong
	 */
	public static WebDriver getFFDriver() {
		
	
		/* chrome */
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		System.setProperty("webdriver.chrome.bin", "/usr/bin/google-chrome");
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--headless");
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox");
		System.out.println("before driver");
		driver = new ChromeDriver(options);
		driver.get("http://www.baidu.com");
		System.out.println("after driver");
		
		System.out.println(driver.getTitle());


		return driver;
	}
	
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
	 * click
	 * 
	 * @param element
	 * @return
	 * @author tanglonglong
	 */

	public static MyResponse click(WebElement element) {

		logInfo("click");
		MyResponse myResponse = new MyResponse();
		try{
		
			if (!element.isDisplayed()) {
				logError("Element can't click for" + element + " in "
						+ Thread.currentThread().getStackTrace()[2].getClassName());
				return myResponse.failed("Click element of "+element+" failed");
			}
			element.click();
			Common.logWarn("Click "+element+" Success");
		}catch(Exception e){
			myResponse.failed("Element can't click for" + element + " in "
					+ Thread.currentThread().getStackTrace()[2].getClassName());
		}
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
	public static MyResponse select( WebElement element, String val ){
		
		logInfo("select");
		MyResponse myResponse = new MyResponse();
//		/*这里切换的目的是将之前所有找到的元素清除掉*/
//		//切换driver至default
//		Common.driver.switchTo().defaultContent();
//		
//		//切换driver到ifram1
//		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
//		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
//			Common.logError("get element data of iframe1 failed");
//			return iframe1Response.failed("get element data of iframe1 failed");
//		}
//		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
//		//切换driver到ifram2
//		MyResponse iframe2Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM2);
//		if( (int)iframe2Response.get(MyResponse.STATUS) == MyResponse.FAILED){
//			Common.logError("get element data of iframe2 failed");
//			return iframe2Response.failed("get element data of iframe1 failed");
//		}
//		Common.driver.switchTo().frame((WebElement)iframe2Response.get("ele"));
//		
		//点击进入到select列表
  		myResponse = Common.click(element);
		if((int)myResponse.get(MyResponse.STATUS)== MyResponse.FAILED){
			logError("get Webelement of"+element +"failed");
			return myResponse.failed("getWebElement failed");
		}
		//下拉列表点击
		String valArray[] = val.split("\\|");
		for(String str : valArray ){
		//	System.out.println("---------"+driver.findElement(By.xpath(".//div[@class='x-combo-list-inner']//*[contains(text(),'"+str+"')]/..")).toString());
			myResponse = Common.getWebElementOld(".//*[contains(@style, 'visible')]//div[@class='x-combo-list-inner']//*[contains(text(),'"+str+"')]/..", "Xpath");
			if((int)myResponse.get(MyResponse.STATUS)== MyResponse.FAILED){
				logError("get Webelement of"+".//div[@class='x-combo-list-inner']//*[contains(text(),'"+str+"')]/.." +"failed");
				return myResponse.failed("getWebElement failed");
			}
			
			myResponse = Common.click((WebElement)myResponse.get("ele"));
			if((int)myResponse.get(MyResponse.STATUS)== MyResponse.FAILED){
				logError("clilck Webelement of"+".//div[@class='x-combo-list-inner']//*[contains(text(),'"+str+"')]/.." +"failed");
				return myResponse.failed("getWebElement failed");
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
	
	public static MyResponse proccessTable( WebElement element, String param, Object remarkEnum){
		
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
			MyResponse selectResponse = select(element, param);
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
	/**
	 * 获取该元素的值
	 * @param webElement
	 * @return
	 */
	public static MyResponse read(WebElement webElement){
		MyResponse myResponse = new MyResponse();
		String text = webElement.getText();
		myResponse.setMessage(text);
		return myResponse;
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
		element.clear();
		click(element);
		element.clear();
		if (!element.isDisplayed()) {
			logError("Element can't setParameter for" + element + " in "
					+ Thread.currentThread().getStackTrace()[2].getClassName());
			return myResponse.failed("Element of "+element+" not displayed");
		}
		element.sendKeys(param);
		//点击下空白处，将刚入力的值保存
	    Actions actions = new Actions(Common.driver);
	    actions.moveByOffset(100000000, 100000000).click().build().perform();
	    
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
			if(!s.equalsIgnoreCase(elementEnum.toString())){
				continue;
			}
			isContains = true;
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
	 * getWebElementForSelect(pageEnum,allElementEnum,elementEnum)
	 * 获取元素定位的相关数据,需要在页面上先执行JS是元素显示出来，只能byId
	 * @param pageEnum
	 * @param allElementEnum
	 * @param elementEnum
	 * @return MyResponse<"ele",WebElement>
	 */
	@SuppressWarnings("finally")
	public static MyResponse getWebElementForSelect(PageEnum pageEnum,AllElementEnum allElementEnum,ElementEnum elementEnum) {
		Common.logInfo("getWebElementForSelect");
		
		MyResponse myResponse = new MyResponse();
		List<String> list = new ArrayList<String>();
		boolean isContains = false;
		HashMap<String, List<String>> hashMap = ReadFromExcel.elementsFromExcel
				.get(pageEnum);
		//取出该页面对应的所有元素的名称
		String[] str = AllElementEnum.valueOf(allElementEnum.toString()).valueToString();
		for(String s : str){
			if(!s.equalsIgnoreCase(elementEnum.toString())){
				continue;
			}
			isContains = true;
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
		case ID:
			((JavascriptExecutor)Common.driver).executeScript("document.getElementById('"+locatorVal+"').style.cssText='display:block !important';");
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
	 * 获取元素定位的相关数据,滚动以后不能waitElement，否则isVasable会失败
	 * @param pageEnum
	 * @param allElementEnum
	 * @param elementEnum
	 * @return MyResponse<"ele",WebElement>
	 */
	@SuppressWarnings("finally")
	public static MyResponse getWebElementsAfterScroll(PageEnum pageEnum,AllElementEnum allElementEnum,ElementEnum elementEnum) {
		Common.logInfo("getWebElementsAfterScroll");
		
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
            WebDriverWait driverWait = (WebDriverWait) new WebDriverWait(driver, 20, 500).ignoring(StaleElementReferenceException.class).withMessage("元素在10秒内没有出现!");
            return driverWait.until(new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driver) {
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
                    		  
                    		 Common.logInfo("isVisible");
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
                    //FIXME:在这里会生成大量截图，考虑是否去掉
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
	 *  
	 * @param by
	 * @return
	 */
	public static boolean waitForElement( final By elementLocator,Long waitSeconds) {
		Common.logInfo("waitForElement");
		try {
			//三个参数分别为，driver，timeout（最长等待时间s）,sleepMills(间隔时间毫秒)
			WebDriverWait driverWait = (WebDriverWait) new WebDriverWait(driver, waitSeconds, 500).ignoring(StaleElementReferenceException.class).withMessage("元素在10秒内没有出现!");
			return driverWait.until(new ExpectedCondition<Boolean>() {
				
				public Boolean apply(WebDriver driver) {
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
							
							Common.logInfo("isVisible");
						}
					} catch (IndexOutOfBoundsException e) {
						Common.logWarn("waitForElement"+elementLocator+" failed, Some errors in code");
						return false;
					} catch (NoSuchElementException e) {
						Common.logWarn("waitForElement"+elementLocator+" failed, NO Such Element");
						return false;
					}
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
     * 点击tab页的刷新
     * @return
     */
    public static MyResponse clickRefresh(){
    	logInfo("clickRefresh");
    	try{
    		
			Common.driver.switchTo().defaultContent();
			//点击刷新
			MyResponse getRefreshRes = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.REFRESH);
			if((int)getRefreshRes.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("Get element of refresh failed");
				return getRefreshRes.failed("Get element of refresh failed");
			}
			MyResponse clickRefreshRes = Common.click((WebElement)getRefreshRes.get("ele"));
			if((int)clickRefreshRes.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("Click element of refresh failed");
			}
			return getRefreshRes.success();
    	}catch(Exception e){
    		return new MyResponse().failed("Click element of refresh failed");
    	}
    }
    /**
	 * 点击关闭tab页
	 * @return
	 */
	public  static  MyResponse clickClose(){
		logInfo("clickClose");
    	try{
			Common.driver.switchTo().defaultContent();
			//点击刷新
			MyResponse getCloseRes = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.CLOSE);
			if((int)getCloseRes.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("Get element of close failed");
				return getCloseRes.failed("Get element of close failed");
			}
			MyResponse clickCloseRes = Common.click((WebElement)getCloseRes.get("ele"));
			if((int)clickCloseRes.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("Click element of close failed");
			}
			return getCloseRes.success();
    	}catch(Exception e){
    		return new MyResponse().failed("Click element of close failed");
    	} 
	}
	/**
	 * 当需要长时间等待时，如等待3min
	 */
	public static MyResponse waitLongTimeForWebElement(PageEnum pageEnum,AllElementEnum allElementEnum,ElementEnum elementEnum,Long WaitTime){
		logInfo("waitLongTimeForWebElement");
			
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
				if(!Common.waitForElement(By.xpath(locatorVal),WaitTime)){
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
				if(!Common.waitForElement(By.cssSelector(locatorVal),WaitTime)){
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
				if(!Common.waitForElement(By.id(locatorVal),WaitTime)){
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
	 * 反审核全部项
	 * @author tanglonglong
	 */
	public static boolean unreviewed() {
		Common.logInfo("unreviewed");
		
		Common.driver.switchTo().defaultContent();
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		
		//选中所有的CheckBox
		MyResponse allCheckBoxResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.ALLCHECKBOX);
		if((int)allCheckBoxResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of allCheckBox failed");
			return false;			
		}
		Common.click((WebElement)allCheckBoxResponse.get("ele"));
		//点击Top上的反审核
		MyResponse unreviewTopResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.UNREVIEW_TOP);
		if((int)unreviewTopResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of unreivewtop failed");
			return false;			
		}
		MyResponse clickReviewTopResponse = Common.click((WebElement)unreviewTopResponse.get("ele"));
		if((int)unreviewTopResponse.get(clickReviewTopResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Click checkbox of UnreviewTop failed");
			return false;			
		}
		//点击确定
		 MyResponse clickYesResponse = Common.clickYES();
		 if((int)clickYesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			//点击是
			MyResponse popupyesResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_YES);
			if((int)popupyesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("get element of popup yes failed");
				return false;
			}
			MyResponse clickPopupYes = Common.click((WebElement)popupyesResponse.get("ele"));
			if((int)clickPopupYes.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("Click element of popup yes failed");
				return false;
			}
			//点击确定
			MyResponse clickYesResponse2 = Common.clickYES();
			if((int)clickYesResponse2.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("Click yes failed");
				return false;
			}
		 }
		Common.driver.switchTo().defaultContent();
		iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		//去掉选中所有的CheckBox
		allCheckBoxResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.ALLCHECKBOX);
		if((int)allCheckBoxResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of allCheckBox failed");
			return false;			
		}
		Common.click((WebElement)allCheckBoxResponse.get("ele"));
		Common.click((WebElement)allCheckBoxResponse.get("ele"));
		return true;
	}
	/**
	 * 审核全部项
	 * @author tanglonglong
	 */
	public static boolean review() {
		Common.logInfo("review");
		
		Common.driver.switchTo().defaultContent();
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		//等待页面加载完成
		Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.VIEW);
		//选中所有的CheckBox
		MyResponse allCheckBoxResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.ALLCHECKBOX);
		if((int)allCheckBoxResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of allCheckBox failed");
			return false;			
		}
		Common.click((WebElement)allCheckBoxResponse.get("ele"));
		//点击Top上的审核
		MyResponse reviewTopResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.REVIEW_TOP);
		if((int)reviewTopResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of reivewtop failed");
			return false;			
		}
		MyResponse clickReviewTopResponse = Common.click((WebElement)reviewTopResponse.get("ele"));
		if((int)reviewTopResponse.get(clickReviewTopResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Click checkbox of UnreviewTop failed");
			return false;			
		}
		
		//点击是
		MyResponse popupyesResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_YES);
		Common.click((WebElement)popupyesResponse.get("ele"));
		//点击确定
		MyResponse clickYesResponse2 = Common.clickYES();
		if((int)clickYesResponse2.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Click yes failed");
			return false;
		}
//		//点击确定
//		 MyResponse clickYesResponse = Common.clickYES();
//		 if((int)clickYesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
//			//点击是
//			MyResponse popupyesResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_YES);
//			if((int)popupyesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
//				Common.logError("get element of popup yes failed");
//				return false;
//			}
//			MyResponse clickPopupYes = Common.click((WebElement)popupyesResponse.get("ele"));
//			if((int)clickPopupYes.get(MyResponse.STATUS)==MyResponse.FAILED){
//				Common.logError("Click element of popup yes failed");
//				return false;
//			}
//			//点击确定
//			MyResponse clickYesResponse2 = Common.clickYES();
//			if((int)clickYesResponse2.get(MyResponse.STATUS)==MyResponse.FAILED){
//				Common.logError("Click yes failed");
//				return false;
//			}
//		 }
		Common.driver.switchTo().defaultContent();
		iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		//等待页面加载完成
		Common.getWebElements(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.VIEW);
		try{
			//去掉选中所有的CheckBox
			allCheckBoxResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.ALLCHECKBOX);
			if((int)allCheckBoxResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("get element of allCheckBox failed");
				return false;			
			}
			Common.click((WebElement)allCheckBoxResponse.get("ele"));
			Common.click((WebElement)allCheckBoxResponse.get("ele"));
		}catch(Exception e){
			Common.logError("Some error happend when Click");
		}
		return true;
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
		 * tab页面的导入
		 */
		INSERT_TOP,
		/**
		 * 辅助定位页面
		 */
		IFRAM1,
		/**
		 * 点击复选框选中项目
		 */
		ITEM_CHECKBOX,
		/**
		 * 点击复选框全选
		 */
		ALLCHECKBOX,
		/**
		 * 关闭
		 */
		CLOSE,
		/**
		 * 刷新
		 */
		REFRESH,
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
		 * 弹出确认框-确定
		 */
		POPUP_OK,
		/**
		 * 弹出确认框-导入
		 */
		POPUP_INSERT,
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
