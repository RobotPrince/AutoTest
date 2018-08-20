package com.yss.suit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.yss.common.Common;
import com.yss.common.ReadFromExcel;
import com.yss.method.HeSuanJiGouXinXi;
import com.yss.method.Login;

public class scenario1 {
	public static Logger logger = Logger.getLogger(scenario1.class);

	/*
	 * 将这条测试流所用到的方法写在这里
	 * 如：1.登陆 2.开户 3.申购
	 */
	@Test
	public void test1() {

		new Login().login();
	}
	@Test
	public void test2() throws InterruptedException {
		new HeSuanJiGouXinXi().before();
		new HeSuanJiGouXinXi().addAFew();
		new HeSuanJiGouXinXi().reviewAFew();
//		new HeSuanJiGouXinXi().addOne();
//		new HeSuanJiGouXinXi().addOne();
//		new HeSuanJiGouXinXi().addOne();
//		new HeSuanJiGouXinXi().review();
//		new HeSuanJiGouXinXi().unreviewed();
//		new HeSuanJiGouXinXi().delete();
	}

	public void test3() {
	
		new Login().login();
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
        logger.info("----------------------------------------------------");
	}

}
