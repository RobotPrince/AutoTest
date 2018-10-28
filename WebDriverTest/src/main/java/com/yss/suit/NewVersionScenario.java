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
import com.yss.common.MyResponse;
import com.yss.common.ReadFromExcel;
import com.yss.db.CompareTable;
import com.yss.db.SaveTable;
import com.yss.method.Login;
import com.yss.method.RiChangYunYingQingSuan;
import com.yss.method.RiChangYunYingQingSuan.RiChangYunYingQingSuanEnum;
/**
 * 新版本需要先跑完日常运营清算的全部流程，抓取数据库表，然后和旧版本抓取的表进行比较
 * @author tanglonglong
 *
 */
public class NewVersionScenario {
	public static Logger logger = Logger.getLogger(NewVersionScenario.class);
	
	RiChangYunYingQingSuan riChangYunYingQingSuan;
	String qingsuanriqi;
	String xuanzedaoruriqi;
	String qingsuanriqiArr[] ;
	String xuanzedaoruriqiArr[];
	@BeforeTest
	@Parameters({ "TA_address", "username", "password", "DB_address", "DB_username", "DB_password", "save_place"})
	public void getData(String TA_address,String username,
			String password, String DB_address,String DB_username, String DB_password,
			String save_place){
		new com.yss.common.ReadFromExcel().allReadMethod();
		qingsuanriqi = ReadFromExcel.dataForRiChangYunYingFromExcel.get(0).get(RiChangYunYingQingSuanEnum.QINGSUANRIQI);
		xuanzedaoruriqi = ReadFromExcel.dataForRiChangYunYingFromExcel.get(0).get(RiChangYunYingQingSuanEnum.XUANZEDAORURIQI);
		qingsuanriqiArr = qingsuanriqi.split(",");
		xuanzedaoruriqiArr = xuanzedaoruriqi.split(",");
		if(qingsuanriqiArr.length != xuanzedaoruriqiArr.length){
			Reporter.log("清算日期和选择导入日期个数不匹配");
			Assert.fail("清算日期和选择导入日期个数不匹配");
		}
		//从XML中获取数据
		Common.getParamFromXML(TA_address, username, password, DB_address, DB_username, DB_password, save_place);
	}

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
	
	//@Test(priority = 1)
	public void autoPage(){
		
		
		for(int i=0; i<qingsuanriqiArr.length;i++){
			riChangYunYingQingSuan = new RiChangYunYingQingSuan(qingsuanriqiArr[i],xuanzedaoruriqiArr[i]);
			if(i==0){
				this.before();
			}
			this.qingsuanriqi();
			this.jingzhiguanli();
			this.jingzhidaochu();
			this.shenqingshujudaoru();
			this.zhanghujiancha();
			this.zhanghuqingsuan();
			this.jiaoyijiancha();
			this.jiaoyiqingsuan();
			this.querenshujudaochu();
			this.rizhongqueren();
		}
	}
	public void before(){
		
		try{
			
			if( !riChangYunYingQingSuan.before() ){
				Reporter.log("日常运营清算-预先操作失败");
				Assert.fail("日常运营清算-预先操作失败");
			}else{
				Reporter.log("日常运营清算-预先操作成功");
			}
		}catch(Exception e){
			Reporter.log("日常运营清算-预先操作失败");
			Assert.fail("日常运营清算-预先操作失败");
		}
		
	}
	public void qingsuanriqi() {
		
		try{
			if(! riChangYunYingQingSuan.qingsuanriqi()){
				Reporter.log("日常运营清算-清算日期自动化页面程序失败");
				Assert.fail("日常运营清算-清算日期自动化页面程序失败");
			}else{
				Reporter.log("日常运营清算-清算日期自动化页面程序成功");
			}
		}catch(Exception e){
			Assert.fail("日常运营清算-清算日期自动化页面程序失败");
			Reporter.log("日常运营清算-清算日期自动化页面程序失败");
		}
	}
	public void jingzhiguanli() {
		
		try{
			if(! riChangYunYingQingSuan.jingzhiguanli()){
				Reporter.log("日常运营清算-净值管理自动化页面程序失败");
				Assert.fail("日常运营清算-净值管理自动化页面程序失败");
			}else{
				Reporter.log("日常运营清算-净值管理自动化页面程序成功");
			}
		}catch(Exception e){
			Reporter.log("日常运营清算-净值管理自动化页面程序失败");
			Assert.fail("日常运营清算-净值管理自动化页面程序失败");
		}
	}
	public void jingzhidaochu(){
		try{
			
			if( !riChangYunYingQingSuan.jingzhidaochu()){
				Reporter.log("日常运营清算-净值导出自动化页面程序失败");
				Assert.fail("日常运营清算-净值导出自动化页面程序失败");
			}else{
				
				Reporter.log("日常运营清算-净值导出自动化页面程序成功");
			}
		}catch(Exception e){
			Reporter.log("日常运营清算-净值导出自动化页面程序失败");
			Assert.fail("日常运营清算-净值导出自动化页面程序失败");
		}
	}
	public void shenqingshujudaoru() {

		try{
			
			if(!riChangYunYingQingSuan.shenqingshujudaoru()){
				Reporter.log("日常运营清算-申请数据导入自动化页面程序失败");
				Assert.fail("日常运营清算-申请数据导入自动化页面程序失败");
			}else{
				
				Reporter.log("日常运营清算-申请数据导入自动化页面程序成功");
			}
		}catch(Exception e){
			Reporter.log("日常运营清算-申请数据导入自动化页面程序失败");
			Assert.fail("日常运营清算-申请数据导入自动化页面程序失败");
		}
	}
	public void zhanghujiancha() {
		try{
			
			if(! riChangYunYingQingSuan.zhanghujiancha() ){
				
				Reporter.log("日常运营清算-账户检查自动化页面程序失败");
				Assert.fail("日常运营清算-账户检查自动化页面程序失败");
			}else{
				
				Reporter.log("日常运营清算-账户检查自动化页面程序成功");
			}
		}catch(Exception e){
			Reporter.log("日常运营清算-账户检查自动化页面程序失败");
			Assert.fail("日常运营清算-账户检查自动化页面程序失败");
		}
	}
	public void zhanghuqingsuan(){
		try{
			
			if ( !riChangYunYingQingSuan.zhanghuqingsuan() ){
				Reporter.log("日常运营清算-账户清算自动化页面程序失败");
				Assert.fail("日常运营清算-账户清算自动化页面程序失败");
			}else{
				
				Reporter.log("日常运营清算-账户清算自动化页面程序成功");
			}
		}catch(Exception e){
			
			Reporter.log("日常运营清算-账户清算自动化页面程序失败");
			Assert.fail("日常运营清算-账户清算自动化页面程序失败");
		}
	}
	
	public void jiaoyijiancha() {
		try{
			
			if(!riChangYunYingQingSuan.jiaoyijiancha()){
				Reporter.log("日常运营清算-交易检查自动化页面程序失败");
				Assert.fail("日常运营清算-交易检查自动化页面程序失败");
			}else{
				
				Reporter.log("日常运营清算-交易检查自动化页面程序成功");
			}
		}catch(Exception e){
			
			Reporter.log("日常运营清算-交易检查自动化页面程序失败");
			Assert.fail("日常运营清算-交易检查自动化页面程序失败");
		}
	}

	public void jiaoyiqingsuan(){
		
		try{
			
			if( !riChangYunYingQingSuan.jiaoyiqingsuan()){
				
				Reporter.log("日常运营清算-交易清算自动化页面程序失败");
				Assert.fail("日常运营清算-交易清算自动化页面程序失败");
			}else{
				
				Reporter.log("日常运营清算-交易清算自动化页面程序成功");
			}
		}catch(Exception e){
			
			Reporter.log("日常运营清算-交易清算自动化页面程序失败");
			Assert.fail("日常运营清算-交易清算自动化页面程序失败");
		}
	}
	public void querenshujudaochu() {
		
		try{
			
			if(!riChangYunYingQingSuan.querenshujudaochu()){
				
				Reporter.log("日常运营清算-确认数据导出自动化页面程序失败");
				Assert.fail("日常运营清算-确认数据导出自动化页面程序失败");
			}else{
				
				Reporter.log("日常运营清算-确认数据导出自动化页面程序成功");
			}
		}catch(Exception e){
			
			Reporter.log("日常运营清算-确认数据导出自动化页面程序失败");
			Assert.fail("日常运营清算-确认数据导出自动化页面程序失败");
		}
	}
	public void rizhongqueren() {
		
		try{
			
			if(!riChangYunYingQingSuan.rizhongqueren()){
				
				Reporter.log("日常运营清算-日终确认自动化页面程序失败");
				Assert.fail("日常运营清算-日终确认自动化页面程序失败");
			}else{
				
				Reporter.log("日常运营清算-日终确认自动化页面程序成功");
			}
		}catch(Exception e){
			
			Reporter.log("日常运营清算-日终确认自动化页面程序失败");
			Assert.fail("日常运营清算-日终确认自动化页面程序失败");
		}
	}
	//@Test(priority = 2 )
	public void save(){
		String errorMes = "";
		MyResponse myResponse = new SaveTable().saveAllTables();
		if((int)myResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			
			Reporter.log("日常运营清算-账户检查数据库读表失败"+myResponse.getMessage());
			errorMes = "日常运营清算-账户检查数据库读表失败"+myResponse.getMessage();
		}else{
			
			Reporter.log("日常运营清算-账户检查数据库读表成功");
		}
		if(!errorMes.equals("")){
		Assert.fail(errorMes);
		}
	}

	@Test(priority = 3 )
	public void compare(){
		String errorMes = "";
		MyResponse myResponse = new CompareTable().compareAllTables();
		if((int)myResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
		
		Reporter.log("日常运营清算-账户检查数据库比较失败"+myResponse.getMessage());
		errorMes += "日常运营清算-账户检查数据库比较失败"+myResponse.getMessage();
		}else{
			
			Reporter.log("日常运营清算-账户检查数据库比较成功");
		}
			if(!errorMes.equals("")){
			Assert.fail(errorMes);
		}
	}
	@BeforeClass
	public void beforeClass() {

		logger.info("beforeClass");
		logger.info("--------------------------------------------------");
		logger.info("----------   "+NewVersionScenario.class+"    ----------------");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");
		Common.driver.quit();
        logger.info("----------   "+"测试用例执行结束"+"    ----------------");        
	}

}

