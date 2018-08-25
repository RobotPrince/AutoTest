package com.yss.suit;

import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.yss.common.Common;
import com.yss.method.Login;
import com.yss.method.RiChangYunYingQingSuan;

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
	public void login() {

		new Login().login();
		Reporter.log("登录成功");
	}
	@Test(priority = 1)
	public void biefore() throws InterruptedException {
		
		if( !new RiChangYunYingQingSuan().before() ){
			Reporter.log("日常运营清算-预先操作成功");
		}
		Reporter.log("日常运营清算-预先操作失败");
		
	}
//	@Test(priority = 2)
	public void jingzhiguanli() throws InterruptedException {
		
		if(! new RiChangYunYingQingSuan().jingzhiguanli()){
			Reporter.log("日常运营清算-净值管理失败");
		}
		Reporter.log("日常运营清算-净值管理成功");
	}
//	@Test(priority = 3)
	public void jingzhidaochu() throws InterruptedException {
		
		if( !new RiChangYunYingQingSuan().jingzhidaochu()){
			
			Reporter.log("日常运营清算-净值导出失败");
		}
		Reporter.log("日常运营清算-净值导出成功");
	}
	@Test(priority = 4)
	public void shenqingshujudaoru() throws InterruptedException {

		if(!new RiChangYunYingQingSuan().shenqingshujudaoru()){
			Reporter.log("日常运营清算-申请数据导入失败");
		}
		Reporter.log("日常运营清算-申请数据导入成功");
	}
	@Test(priority = 5)
	public void zhanghujiancha() throws InterruptedException {
		
		if(! new RiChangYunYingQingSuan().zhanghujiancha() ){
			Reporter.log("日常运营清算-账户检查失败");
		}
		Reporter.log("日常运营清算-账户检查成功");
	}
//	@Test(priority = 6)
	public void zhanghuqingsuan() throws InterruptedException {
		
		if ( !new RiChangYunYingQingSuan().zhanghuqingsuan() ){
			Reporter.log("日常运营清算-账户清算失败");
		}
		Reporter.log("日常运营清算-账户清算成功");
	}
	
//	@Test(priority = 7)
	public void jiaoyijiancha() throws InterruptedException {
		
		if(!new RiChangYunYingQingSuan().jiaoyijiancha()){
			Reporter.log("日常运营清算-交易检查失败");
		}
		Reporter.log("日常运营清算-交易检查成功");
	}

//	@Test(priority = 8)
	public void jiaoyiqingsuan() throws InterruptedException {
		
		
		if( !new RiChangYunYingQingSuan().jiaoyiqingsuan()){
			
			Reporter.log("日常运营清算-交易清算失败");
		}
		Reporter.log("日常运营清算-交易清算成功");
	}
//	@Test(priority = 9)
	public void querenshujudaochu() throws InterruptedException {
		
		
		if(!new RiChangYunYingQingSuan().querenshujudaochu()){
			
			Reporter.log("日常运营清算-确认数据导出失败");
		}
		Reporter.log("日常运营清算-确认数据导出成功");
	}
//	@Test(priority = 10)
	public void rizhongqueren() throws InterruptedException {
		
		
		if(!new RiChangYunYingQingSuan().rizhongqueren()){
			
			Reporter.log("日常运营清算-日终确认失败");
		}
		Reporter.log("日常运营清算-日终确认成功");
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
