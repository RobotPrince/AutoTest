package com.yss.method;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;

import com.yss.common.AllElementEnum;
import com.yss.common.Common;
import com.yss.common.ElementEnum;
import com.yss.common.MyResponse;
import com.yss.common.PageEnum;
import com.yss.common.ReadFromExcel;

public class Login {

	@SuppressWarnings("unchecked")

	public boolean login(String TA_address, String user, String password) {
		Common.logInfo("login");
		WebDriver driver = Common.driver;

//		String url = "http://192.168.103.213:8088/sofa/sofa-portal/index.jsp";
		String url = TA_address;

		//打开网址
		driver.get(url);
		//获取页面元素
		MyResponse userResponse = Common.getWebElement(PageEnum.LOGIN_PAGE,AllElementEnum.LoginElement,LoginEnum.USER);
		if( userResponse.get(MyResponse.STATUS).equals(MyResponse.FAILED)){
			Common.logError("Get User Name failed");
			return false;
		}
		WebElement userElement = (WebElement)userResponse.get("ele");
		
		MyResponse pwdResponse = Common.getWebElement(PageEnum.LOGIN_PAGE,AllElementEnum.LoginElement,LoginEnum.PWD);
		if( pwdResponse.get(MyResponse.STATUS).equals(MyResponse.FAILED)){
			Common.logError("Get pwd failed");
			return false;
		}
		WebElement pwdElement = (WebElement)pwdResponse.get("ele");
		MyResponse loginResponse = Common.getWebElement(PageEnum.LOGIN_PAGE,AllElementEnum.LoginElement,LoginEnum.LOGIN);
		if( loginResponse.get(MyResponse.STATUS).equals(MyResponse.FAILED)){
			Common.logError("Get login failed");
			return false;
		}
		WebElement loginElement = (WebElement)loginResponse.get("ele");
		//获取用户名和密码
		List<HashMap<LoginEnum, String>> dataForLoginPageFromExcel = ReadFromExcel.dataForLoginPageFromExcel;
		//填写值，点击
//		MyResponse setUserResponse = Common.setParameter(userElement, dataForLoginPageFromExcel.get(0).get(LoginEnum.USER));
		MyResponse setUserResponse = Common.setParameter(userElement, user);
		if( (int)setUserResponse.get(MyResponse.STATUS)== MyResponse.FAILED){
			Common.logError("Set parameter "+ userElement+" to"+dataForLoginPageFromExcel.get(0).get(LoginEnum.USER)+" failed");
			return false;
		}
//		MyResponse setPwdResponse = Common.setParameter(pwdElement, dataForLoginPageFromExcel.get(0).get(LoginEnum.PWD));
		MyResponse setPwdResponse = Common.setParameter(pwdElement, password);
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

