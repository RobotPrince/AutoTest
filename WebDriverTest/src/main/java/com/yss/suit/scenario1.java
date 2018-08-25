package com.yss.suit;

import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.yss.common.Common;
import com.yss.db.Compare_T_TA_ACKFDACBLOTTER;
import com.yss.db.Compare_T_TA_ACKTRADEBLOTTER;
import com.yss.db.Compare_T_TA_B_APPFDACBLOTTER;
import com.yss.db.Compare_T_TA_B_APPTRADEBLOTTER;
import com.yss.db.Save_T_TA_ACKFDACBLOTTER;
import com.yss.db.Save_T_TA_ACKTRADEBLOTTER;
import com.yss.db.Save_T_TA_B_APPFDACBLOTTER;
import com.yss.db.Save_T_TA_B_APPTRADEBLOTTER;
import com.yss.method.HeSuanJiGouXinXi;

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

	//	new Login().login();
		Reporter.log("登录成功");
	}
	@Test(priority = 1)
	public void save_T_TA_ACKFDACBLOTTER() throws InterruptedException {
		//账户确认表
		new Save_T_TA_ACKFDACBLOTTER().save_T_TA_ACKFDACBLOTTER();
		new Compare_T_TA_ACKFDACBLOTTER().compare_T_TA_ACKFDACBLOTTER();
		
		//交易确认表
		new Save_T_TA_ACKTRADEBLOTTER().save_T_TA_ACKTRADEBLOTTER();
		new Compare_T_TA_ACKTRADEBLOTTER().compare_T_TA_ACKTRADEBLOTTER();
		
		//账户申请表
		new Save_T_TA_B_APPFDACBLOTTER().save_T_TA_B_APPFDACBLOTTER();
		new Compare_T_TA_B_APPFDACBLOTTER().compare_T_TA_B_APPFDACBLOTTER();
		
		//交易申请表
		new Save_T_TA_B_APPTRADEBLOTTER().save_T_TA_B_APPTRADEBLOTTER();
		new Compare_T_TA_B_APPTRADEBLOTTER().compare_T_TA_B_APPTRADEBLOTTER();
		//new HeSuanJiGouXinXi().add();
		//Reporter.log("核算机构信息-新增成功");
	}
	//@Test(priority = 1)
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
