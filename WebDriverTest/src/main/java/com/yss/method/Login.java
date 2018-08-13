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
		String url = "http://127.0.0.1:8080/sofa/sofa-sso/validate";
		//打开网址
		driver.get(url);
		//获取页面元素
		MyResponse responseUser = Common.getElementData(PageEnum.LOGIN_PAGE,AllElementEnum.LoginElement,LoginEnum.USER);
		if( responseUser.get(MyResponse.STATUS).equals(MyResponse.FAILED)){
			Common.logError("Get User Name failed");
			return false;
		}
		List<String> userList = (List<String>)responseUser.get("list");
		WebElement findElement1 = Common.getWebElement(userList.get(1), userList.get(0));
		
		MyResponse responsePwd = Common.getElementData(PageEnum.LOGIN_PAGE,AllElementEnum.LoginElement,LoginEnum.PWD);
		if( responsePwd.get(MyResponse.STATUS).equals(MyResponse.FAILED)){
			Common.logError("Get pwd failed");
			return false;
		}
		List<String> pwdList = (List<String>)responsePwd.get("list");
		WebElement findElement2 = Common.getWebElement(pwdList.get(1), pwdList.get(0));
	
		MyResponse responseLogin = Common.getElementData(PageEnum.LOGIN_PAGE,AllElementEnum.LoginElement,LoginEnum.LOGIN);
		if( responseLogin.get(MyResponse.STATUS).equals(MyResponse.FAILED)){
			Common.logError("Get login failed");
			return false;
		}
		List<String> loginList = (List<String>)responseLogin.get("list");
		WebElement findElement3 = Common.getWebElement(loginList.get(1), loginList.get(0));

		//获取用户名和密码
		List<HashMap<LoginEnum, String>> dataForLoginPageFromExcel = ReadFromExcel.dataForLoginPageFromExcel;
		//填写值，点击
		if( !Common.setParameter(findElement1, dataForLoginPageFromExcel.get(0).get(LoginEnum.USER)) ){
			Common.logError("Set parameter "+ findElement1+" to"+dataForLoginPageFromExcel.get(0).get(LoginEnum.USER)+" failed");
			return false;
		}
		if( !Common.setParameter(findElement2, dataForLoginPageFromExcel.get(0).get(LoginEnum.PWD)) ){
			Common.logError("Set parameter "+ findElement2+" to"+dataForLoginPageFromExcel.get(0).get(LoginEnum.PWD)+" failed");
			return false;
		}
		if( !Common.click(findElement3) ){
			Common.logError("Click element of "+findElement3+" failed");
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

