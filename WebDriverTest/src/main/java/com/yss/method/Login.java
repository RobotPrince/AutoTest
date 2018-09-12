package com.yss.method;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yss.common.AllElementEnum;
import com.yss.common.Common;
import com.yss.common.ElementEnum;
import com.yss.common.MyResponse;
import com.yss.common.PageEnum;
import com.yss.common.ReadFromExcel;

public class Login {

	@SuppressWarnings("unchecked")
	public boolean login() {
		Common.logInfo("login");

		WebDriver driver = Common.driver;
		//TODO：修改Tomcat的IP和端口
		String url = "http://127.0.0.1:8080/sofa/sofa-portal/sofa-portal/homepage.ctrl";
		//打开网址
		driver.get(url);
		//获取页面元素
		MyResponse userResponse = Common.getWebElement(PageEnum.LOGIN_PAGE,AllElementEnum.LoginElement,LoginEnum.USER);
		if( userResponse.get(MyResponse.STATUS).equals(MyResponse.FAILED)){
			Common.logError("Get User Name failed");
			return false;
		}
		WebElement userElement = (WebElement)userResponse.get("ele");
//		List<String> userList = (List<String>)responseUser.get("list");
//		WebElement findElement1 = (WebElement)Common.getWebElementOld(userList.get(1), userList.get(0)).get("ele");
		
		MyResponse pwdResponse = Common.getWebElement(PageEnum.LOGIN_PAGE,AllElementEnum.LoginElement,LoginEnum.PWD);
		if( pwdResponse.get(MyResponse.STATUS).equals(MyResponse.FAILED)){
			Common.logError("Get pwd failed");
			return false;
		}
		WebElement pwdElement = (WebElement)pwdResponse.get("ele");
		
//		
//		List<String> pwdList = (List<String>)responsePwd.get("list");
//		WebElement findElement2 = Common.getWebElement(pwdList.get(1), pwdList.get(0));
	
		MyResponse loginResponse = Common.getWebElement(PageEnum.LOGIN_PAGE,AllElementEnum.LoginElement,LoginEnum.LOGIN);
		if( loginResponse.get(MyResponse.STATUS).equals(MyResponse.FAILED)){
			Common.logError("Get login failed");
			return false;
		}
		WebElement loginElement = (WebElement)loginResponse.get("ele");
//		List<String> loginList = (List<String>)responseLogin.get("list");
//		WebElement findElement3 = Common.getWebElement(loginList.get(1), loginList.get(0));

		//获取用户名和密码
		List<HashMap<LoginEnum, String>> dataForLoginPageFromExcel = ReadFromExcel.dataForLoginPageFromExcel;
		//填写值，点击
		MyResponse setUserResponse = Common.setParameter(userElement, dataForLoginPageFromExcel.get(0).get(LoginEnum.USER));
		if( (int)setUserResponse.get(MyResponse.STATUS)== MyResponse.FAILED){
			Common.logError("Set parameter "+ userElement+" to"+dataForLoginPageFromExcel.get(0).get(LoginEnum.USER)+" failed");
			return false;
		}
		MyResponse setPwdResponse = Common.setParameter(pwdElement, dataForLoginPageFromExcel.get(0).get(LoginEnum.PWD));
		if( (int)setPwdResponse.get(MyResponse.STATUS)== MyResponse.FAILED){
			Common.logError("Set parameter "+ pwdElement+" to"+dataForLoginPageFromExcel.get(0).get(LoginEnum.PWD)+" failed");
			return false;
		}
		MyResponse clickLoginResponse = Common.click(loginElement) ;
		if( (int)clickLoginResponse.get(MyResponse.STATUS)== MyResponse.FAILED ){
			Common.logError("Click element of "+loginElement+" failed");
			return false;
		}
		return true;
	}
	
	public enum LoginEnum implements ElementEnum{
		
		/**
		 * 用户名
		 */
		USER,
		/**
		 * 密码
		 */
		PWD,
		/**
		 * 登录
		 */
		LOGIN
	}
}

