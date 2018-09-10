package com.yss.suit;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
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
	@BeforeTest
	public void getData(){
		new com.yss.common.ReadFromExcel().allReadMethod();
		
	}

	@Test(priority = 0)
	public void login() {
		try{
		
			if(!new Login().login()){
				Reporter.log("登录失败");
				Assert.fail("登录失败");
			}
		}catch(Exception e){
			Reporter.log("登录失败");
			Assert.fail("登录失败");
		}
	}
	@Test(priority = 1)
	public void autoPage(){
		String qingsuanriqi = ReadFromExcel.dataForRiChangYunYingFromExcel.get(0).get(RiChangYunYingQingSuanEnum.QINGSUANRIQI);
		String xuanzedaoruriqi = ReadFromExcel.dataForRiChangYunYingFromExcel.get(0).get(RiChangYunYingQingSuanEnum.XUANZEDAORURIQI);
		String qingsuanriqiArr[] = qingsuanriqi.split(",");
		String xuanzedaoruriqiArr[] = xuanzedaoruriqi.split(",");
		if(qingsuanriqiArr.length != xuanzedaoruriqiArr.length){
			Reporter.log("清算日期和选择导入日期个数不匹配");
			Assert.fail("清算日期和选择导入日期个数不匹配");
		}
		for(int i=0; i<qingsuanriqiArr.length;i++){
			
			this.before(qingsuanriqiArr[i],xuanzedaoruriqiArr[i]);
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
	public void before(String qingsuanriqi,String xuanzedaoruriqi){
		
		try{
			
			if( !new RiChangYunYingQingSuan().before(qingsuanriqi,xuanzedaoruriqi) ){
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
			if(! new RiChangYunYingQingSuan().qingsuanriqi()){
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
			if(! new RiChangYunYingQingSuan().jingzhiguanli()){
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
			
			if( !new RiChangYunYingQingSuan().jingzhidaochu()){
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
			
			if(!new RiChangYunYingQingSuan().shenqingshujudaoru()){
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
			
			if(! new RiChangYunYingQingSuan().zhanghujiancha() ){
				
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
			
			if ( !new RiChangYunYingQingSuan().zhanghuqingsuan() ){
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
			
			if(!new RiChangYunYingQingSuan().jiaoyijiancha()){
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
			
			if( !new RiChangYunYingQingSuan().jiaoyiqingsuan()){
				
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
			
			if(!new RiChangYunYingQingSuan().querenshujudaochu()){
				
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
			
			if(!new RiChangYunYingQingSuan().rizhongqueren()){
				
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
	@Test(priority = 2 )
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

