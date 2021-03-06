package com.yss.suit;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yss.common.Common;
import com.yss.method.Login;

public class scenario2 {
	public static Logger logger = Logger.getLogger(scenario2.class);
	@BeforeTest
	public void getData(){
		new com.yss.common.ReadFromExcel().allReadMethod();
	}
		/*
	 * 将这条测试流所用到的方法写在这里
	 * 如：1.登陆 2.开户 3.申购
	 */
	@Test(priority = 0)
	@Parameters({"TA_address","user","password"})
	public void login(String TA_address, String user, String password) {
		try{
		
			if(!new Login().login(TA_address, user, password)){
				Reporter.log("登录失败");
				Assert.fail("登录失败");
			}
		}catch(Exception e){
			Reporter.log("登录失败");
			Assert.fail("登录失败");
		}
	}
	@BeforeClass
	public void beforeClass() {

		logger.info("beforeClass");
		logger.info("--------------------------------------------------");
		logger.info("----------   "+scenario2.class+"    ----------------");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");
		Common.driver.quit();
        logger.info("----------   "+"测试用例执行结束"+"    ----------------");        
	}

}
