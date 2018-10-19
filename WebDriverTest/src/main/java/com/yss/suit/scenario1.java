package com.yss.suit;

import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.yss.common.Common;
import com.yss.method.ChanPinXinXi;
import com.yss.method.GuanLianJiGouXinXi;
import com.yss.method.HeSuanJiGouXinXi;
import com.yss.method.Login;
import com.yss.method.XiaoShouJiGouXinXi;

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
	
	@Test(priority = 2)
	public void HeSuanJiGouXinXiView() throws InterruptedException {
		
		new HeSuanJiGouXinXi().view();
		Reporter.log("核算机构信息-查看成功");
	}
	
	@Test(priority = 3)
	public void HeSuanJiGouXinXiReview() throws InterruptedException {
		
		new HeSuanJiGouXinXi().review();
		new HeSuanJiGouXinXi().after();
		Reporter.log("核算机构信息-审核成功");
	}
	
	@Test(priority = 4)
	public void XiaoJiGouXinXiAdd() throws InterruptedException {
		
		new XiaoShouJiGouXinXi().before();
		new XiaoShouJiGouXinXi().add();
		Reporter.log("销售机构信息-新增成功");
	}
	
	@Test(priority = 5)	
	public void XiaoJiGouXinXiView() throws InterruptedException {
		new XiaoShouJiGouXinXi().view();
		Reporter.log("销售机构信息-查看成功");
	}
	
	@Test(priority = 6)	
	public void XiaoJiGouXinXiReview() throws InterruptedException {
		new XiaoShouJiGouXinXi().review();
		new XiaoShouJiGouXinXi().after();
		Reporter.log("销售机构信息-审核成功");
	}
	@Test(priority = 7)
	public void GuanLianJiGouXinXiAdd() throws InterruptedException{
		new GuanLianJiGouXinXi().before();
		new GuanLianJiGouXinXi().add();
		Reporter.log("关联机构信息-新增成功");
	}
	@Test(priority = 8)
	public void GuanLianJiGouXinXiView() throws InterruptedException{
		new GuanLianJiGouXinXi().view();
		Reporter.log("关联机构信息-查看成功");
	}
	@Test(priority = 9)
	public void GuanLianJiGouXinXiReview() throws InterruptedException{
		new GuanLianJiGouXinXi().review();
		new GuanLianJiGouXinXi().after();
		Reporter.log("关联机构信息-审核成功");
	}
	@Test(priority = 10)
	public void ChanPinXinXiAdd() throws InterruptedException{
		new ChanPinXinXi().before();
		new ChanPinXinXi().add();
		Reporter.log("产品信息-新增成功");
	}
	@Test(priority = 11)
	public void ChanPinXinXiView() throws InterruptedException{
		new ChanPinXinXi().view();
		Reporter.log("产品信息-查看成功");
	}
	@Test(priority = 12)
	public void ChanPinXinXiReview() throws InterruptedException{
		new ChanPinXinXi().review();
		new ChanPinXinXi().after();
		Reporter.log("产品信息-审核成功");
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