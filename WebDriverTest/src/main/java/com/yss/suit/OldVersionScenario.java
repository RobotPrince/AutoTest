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
import com.yss.db.SaveTable;
import com.yss.method.RiChangYunYingQingSuan;
import com.yss.method.RiChangYunYingQingSuan.RiChangYunYingQingSuanEnum;
/**
 * 旧版本需要运行save，抓取数据库表
 * @author tanglonglong
 *
 */
public class OldVersionScenario {

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
	@Test(priority = 1 )
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
