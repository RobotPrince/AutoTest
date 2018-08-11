package com.yss.method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yss.common.Common;
import com.yss.common.PageEnum;
import com.yss.common.ReadFromExcel;

public class Login {

	public boolean login() {
		Common.logInfo("login");

		WebDriver driver = Common.driver;
		String url = "http://127.0.0.1:8080/sofa/sofa-sso/validate";
		//打开网址
		driver.get(url);
		String pageSource = driver.getPageSource();
		//获取页面元素
		List<String> listOfUser = new ArrayList<String>();
		List<String> listOfPwd = new ArrayList<String>();
		List<String> listOfLogin = new ArrayList<String>();
		HashMap<String, List<String>> hashMap = ReadFromExcel.hashMapOfExcel.get(PageEnum.LOGIN_PAGE);
		Set<String> keySet = hashMap.keySet();
		for(String key : keySet){
			switch(key)
			{
			case "user" : listOfUser = hashMap.get(key);
			break;
			case "pwd" : listOfPwd = hashMap.get(key);
			break;
			case "login" : listOfLogin = hashMap.get(key);
			break;
			default :Common.logError("WebElement name error,not supproted for "+key+" yet");
			return false;
			}
		}
			
		//填写值，点击
		WebElement findElement1 = Common.getWebElement(listOfUser.get(1), listOfUser.get(0));
		WebElement findElement2 = Common.getWebElement(listOfPwd.get(1), listOfPwd.get(0));
		WebElement findElement3 = Common.getWebElement(listOfLogin.get(1), listOfLogin.get(0));
		if( !Common.setParameter(findElement1, "tll") ){
			Common.logError("Set parameter "+ findElement1+" to"+"admin"+" failed");
			return false;
		}
		if( !Common.setParameter(findElement2, "000000") ){
			Common.logError("Set parameter "+ findElement2+" to"+"000000"+" failed");
			return false;
		}
		if( !Common.click(findElement3) ){
			Common.logError("Click element of "+findElement3+" failed");
			return false;
		}
		return true;
	}
}
