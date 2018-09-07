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
import com.yss.db.SaveTable;
/**
 * 旧版本需要运行save，抓取数据库表
 * @author tanglonglong
 *
 */
public class OldVersionScenario {

	public static Logger logger = Logger.getLogger(NewVersionScenario.class);
	@BeforeTest
	public void getData(){
		new com.yss.common.ReadFromExcel().allReadMethod();
	}
	@Test(priority = 0 )
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
	
	@BeforeClass
	public void beforeClass() {

		logger.info("beforeClass");
		logger.info("--------------------------------------------------");
		logger.info("----------   "+OldVersionScenario.class+"    ----------------");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");
		Common.driver.quit();
        logger.info("----------   "+"测试用例执行结束"+"    ----------------");        
	}
}
