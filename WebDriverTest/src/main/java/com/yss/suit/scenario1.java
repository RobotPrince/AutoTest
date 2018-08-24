package com.yss.suit;

import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.yss.common.Common;
import com.yss.db.t_ta_acktradeblotter_compare;
import com.yss.db.t_ta_acktradeblotter_save;
import com.yss.method.HeSuanJiGouXinXi;
import com.yss.method.Login;

public class scenario1 {
	public static Logger logger = Logger.getLogger(scenario1.class);
	@BeforeTest
	public void getData(){
		new com.yss.common.ReadFromExcel().allReadMethod();
	}
		/*
	 * 将这条测试流所用到的方法写在这里
	 * 如：1.登陆 2.开户 3.申购
	 */
	@Test(priority = 0)
	public void login() {

		new Login().login();
		Reporter.log("登录成功");
	}
	@Test(priority = 1)
	public void HeSuanJiGouXinXiAdd() throws InterruptedException {
		
		new HeSuanJiGouXinXi().before();
		new HeSuanJiGouXinXi().add();
		Reporter.log("核算机构信息-新增成功");
	}
//	@Test(priority = 2)
	public void HeSuanJiGouXinXiReview() throws InterruptedException {
		

		new HeSuanJiGouXinXi().review();
		Reporter.log("核算机构信息-审核成功");
	}
	@BeforeClass
	public void beforeClass() {

		logger.info("beforeClass");
		logger.info("--------------------------------------------------");
		logger.info("----------   "+scenario1.class+"    ----------------");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");
		Common.driver.quit();
        logger.info("----------   "+"测试用例执行结束"+"    ----------------");        
	}

}
