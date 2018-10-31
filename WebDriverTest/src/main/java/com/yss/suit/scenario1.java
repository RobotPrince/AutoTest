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
import com.yss.method.ChanPinFeiLv;
import com.yss.method.ChanPinQingSuanZhouQi;
import com.yss.method.ChanPinXiaoShouDaiLiGuanXi;
import com.yss.method.ChanPinXinXi;
import com.yss.method.ChanPinZhiXingRenGuanXi;
import com.yss.method.FeiYongFenCheng;
import com.yss.method.GuanLianJiGouXinXi;
import com.yss.method.HeSuanJiGouXinXi;
import com.yss.method.Login;
import com.yss.method.XiaoShouJiGouXinXi;
import com.yss.method.YongHuZhiXingRenGuanXi;
import com.yss.method.ZheKouGuanLi;
import com.yss.method.ZhiXingQuanXianRenXinXi;

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
	@Parameters({"TA_address","username","password"})
	public void login(String TA_address, String username, String password) {
		try{
		System.out.println(TA_address+"--"+username+"--"+password);
			if(!new Login().login(TA_address, username, password)){
				Reporter.log("登录失败");
				Assert.fail("登录失败");
			}
		}catch(Exception e){
			Reporter.log("登录失败");
			Assert.fail("登录失败");
		}
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
		new ChanPinXinXi().before();
		new ChanPinXinXi().view();
		Reporter.log("产品信息-查看成功");
	}
	@Test(priority = 12)
	public void ChanPinXinXiReview() throws InterruptedException{
		new ChanPinXinXi().review();
		new ChanPinXinXi().after();
		Reporter.log("产品信息-审核成功");
	}
//	@Test(priority = 13)
//	public void ChanPinFeiLvAdd() throws InterruptedException{
//		new ChanPinFeiLv().before();
//		new ChanPinFeiLv().add();
//		Reporter.log("产品费率-新增成功");
//	}
//	@Test(priority = 14)
//	public void ChanPinFeiLvView() throws InterruptedException{
//		new ChanPinFeiLv().view();
//		Reporter.log("产品费率-查看成功");
//	}
//	@Test(priority = 15)
//	public void ChanPinFeiLvReview() throws InterruptedException{
//		new ChanPinFeiLv().review();
//		new ChanPinFeiLv().after();
//		Reporter.log("产品费率-审核成功");
//	}
//	
	@Test(priority = 16)
	public void ChanPinXiaoShouDaiLiGuanXiAdd() throws InterruptedException{
		new ChanPinXiaoShouDaiLiGuanXi().before();
		new ChanPinXiaoShouDaiLiGuanXi().add();
		Reporter.log("产品销售代理关系-新增成功");
	}
	@Test(priority = 17)
	public void ChanPinXiaoShouDaiLiGuanXiView() throws InterruptedException{
		new ChanPinXiaoShouDaiLiGuanXi().view();
		Reporter.log("产品销售代理关系-查看成功");
	}
	@Test(priority = 18)
	public void ChanPinXiaoShouDaiLiGuanXiReview() throws InterruptedException{
		new ChanPinXiaoShouDaiLiGuanXi().review();
		new ChanPinXiaoShouDaiLiGuanXi().after();
		Reporter.log("产品销售代理关系-审核成功");
	}
//	@Test(priority = 19)
//	public void FeiYongFenChengAdd() throws InterruptedException{
//		new FeiYongFenCheng().before();
//		new FeiYongFenCheng().add();
//		Reporter.log("费用分成-新增成功");
//	}
//	@Test(priority = 20)
//	public void FeiYongFenChengView() throws InterruptedException{
//		new FeiYongFenCheng().before();
//		new FeiYongFenCheng().view();
//		Reporter.log("费用分成-查看成功");
//	}
//	@Test(priority = 21)
//	public void FeiYongFenChengReview() throws InterruptedException{
//		new FeiYongFenCheng().review();
//		new FeiYongFenCheng().after();
//		Reporter.log("费用分成-审核成功");
//	}
//	@Test(priority = 22)
//	public void ZheKouGuanLiAdd() throws InterruptedException{
//		new ZheKouGuanLi().before();
//		new ZheKouGuanLi().add();
//		Reporter.log("折扣管理-新增成功");
//	}
//	@Test(priority = 23)
//	public void ZheKouGuanLiView() throws InterruptedException{
//		new ZheKouGuanLi().before();
//		new ZheKouGuanLi().view();
//		Reporter.log("折扣管理-查看成功");
//	}
//	@Test(priority = 24)
//	public void ZheKouGuanLiReview() throws InterruptedException{
//		new ZheKouGuanLi().review();
//		new ZheKouGuanLi().after();
//		Reporter.log("折扣管理-审核成功");
//	}
	@Test(priority = 25)
	public void ChanPinQingSuanZhouQiAdd() throws InterruptedException{
		new ChanPinQingSuanZhouQi().before();
		new ChanPinQingSuanZhouQi().add();
		Reporter.log("产品清算周期-新增成功");
	}
	@Test(priority = 26)
	public void ChanPinQingSuanZhouQiView() throws InterruptedException{
		new ChanPinQingSuanZhouQi().view();
		Reporter.log("产品清算周期-查看成功");
	}
	@Test(priority = 27)
	public void ChanPinQingSuanZhouQiReview() throws InterruptedException{
		new ChanPinQingSuanZhouQi().review();
		new ChanPinQingSuanZhouQi().after();
		Reporter.log("产品清算周期-审核成功");
	}
	
	@Test(priority = 28)
	public void YongHuZhiXingRenGuanXiAdd() throws InterruptedException{
		new YongHuZhiXingRenGuanXi().before();
		new YongHuZhiXingRenGuanXi().add();
		Reporter.log("用户执行人关系-新增成功");
	}
	@Test(priority = 29)
	public void  YongHuZhiXingRenGuanXiView() throws InterruptedException{
	//	new YongHuZhiXingRenGuanXi().before();
		new YongHuZhiXingRenGuanXi().view();
		Reporter.log("用户执行人关系-查看成功");
	}
	@Test(priority = 30)
	public void  YongHuZhiXingRenGuanXiReview() throws InterruptedException{
		new YongHuZhiXingRenGuanXi().review();
		new YongHuZhiXingRenGuanXi().after();
		Reporter.log("用户执行人关系-审核成功");
	}
	
	@Test(priority = 31)
	public void ChanPinZhiXingRenGuanXiAdd() throws InterruptedException{
		new ChanPinZhiXingRenGuanXi().before();
		new ChanPinZhiXingRenGuanXi().add();
		Reporter.log("产品执行人关系-新增成功");
	}

	@Test(priority = 32)
	public void ChanPinZhiXingRenGuanXiView() throws InterruptedException{
		new ChanPinZhiXingRenGuanXi().before();
		new ChanPinZhiXingRenGuanXi().view();
		Reporter.log("产品执行人关系-查看成功");
	}
	
	@Test(priority = 33)
	public void ChanPinZhiXingRenGuanXiReview() throws InterruptedException{
		new ChanPinZhiXingRenGuanXi().review();
		new ChanPinZhiXingRenGuanXi().after();
		Reporter.log("产品执行人关系-审核成功");
	}
	/**
	 * 
	 * @author yanglei
	 *
	 */	
	@Test(priority = 34)
	public void ZhiXingQuanXianRenXinXiAdd() throws InterruptedException{
		new ZhiXingQuanXianRenXinXi().before();
		new ZhiXingQuanXianRenXinXi().add();
		Reporter.log("执行权限人信息-新增成功");
	}
	@Test(priority = 35)
	public void ZhiXingQuanXianRenXinXiView() throws InterruptedException{
		new ZhiXingQuanXianRenXinXi().before();
		new ZhiXingQuanXianRenXinXi().view();
		Reporter.log("执行权限人信息-查看成功");
	}
	@Test(priority = 36)
	public void ZhiXingQuanXianRenXinXiReview() throws InterruptedException{
		new ZhiXingQuanXianRenXinXi().review();
		new ZhiXingQuanXianRenXinXi().after();
		Reporter.log("执行权限人信息-审核成功");
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
