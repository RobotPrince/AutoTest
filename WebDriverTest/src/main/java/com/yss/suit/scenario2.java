
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.yss.common.Common;
import com.yss.common.MyResponse;
import com.yss.db.CompareTable;
import com.yss.db.SaveTable;
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
//	@Test(priority = 0)
	public void login() {

		if(!new Login().login()){
			Reporter.log("登录失败");
			Assert.fail("登录失败");
		}
	}
//	@Test(priority = 1)
	public void before() throws InterruptedException {
		
		if( !new RiChangYunYingQingSuan().before() ){
			Reporter.log("日常运营清算-预先操作失败");
			Assert.fail("日常运营清算-预先操作失败");
		}else{
			Reporter.log("日常运营清算-预先操作成功");
		}
		
	}
	//@Test(priority = 2)
	public void jingzhiguanli() throws InterruptedException {
		
		if(! new RiChangYunYingQingSuan().jingzhiguanli()){
			Reporter.log("日常运营清算-净值管理自动化页面程序失败");
			Assert.fail("日常运营清算-净值管理自动化页面程序失败");
		}else{
			Reporter.log("日常运营清算-净值管理自动化页面程序成功");
		}
	}
	//@Test(priority = 3)
	public void jingzhidaochu() throws InterruptedException {
		
		if( !new RiChangYunYingQingSuan().jingzhidaochu()){
			Reporter.log("日常运营清算-净值导出自动化页面程序失败");
			Assert.fail("日常运营清算-净值导出自动化页面程序失败");
		}else{
			
			Reporter.log("日常运营清算-净值导出自动化页面程序成功");
		}
	}
	//@Test(priority = 4)
	public void shenqingshujudaoru() throws InterruptedException {

		if(!new RiChangYunYingQingSuan().shenqingshujudaoru()){
			Reporter.log("日常运营清算-申请数据导入自动化页面程序失败");
			Assert.fail("日常运营清算-申请数据导入自动化页面程序失败");
		}else{
			
			Reporter.log("日常运营清算-申请数据导入自动化页面程序成功");
		}
	}
//	@Test(priority = 5)
	public void zhanghujiancha() throws InterruptedException {
		
		if(! new RiChangYunYingQingSuan().zhanghujiancha() ){
			
			Reporter.log("日常运营清算-账户检查自动化页面程序失败");
			Assert.fail("日常运营清算-账户检查自动化页面程序失败");
		}else{
			
			Reporter.log("日常运营清算-账户检查自动化页面程序成功");
		}
	}
//	@Test(priority = 6)
	public void zhanghuqingsuan() throws InterruptedException {
		
		if ( !new RiChangYunYingQingSuan().zhanghuqingsuan() ){
			Reporter.log("日常运营清算-账户清算自动化页面程序失败");
			Assert.fail("日常运营清算-账户清算自动化页面程序失败");
		}else{
			
			Reporter.log("日常运营清算-账户清算自动化页面程序成功");
		}
	}
	
	//@Test(priority = 7)
	public void jiaoyijiancha() throws InterruptedException {
		
		if(!new RiChangYunYingQingSuan().jiaoyijiancha()){
			Reporter.log("日常运营清算-交易检查自动化页面程序失败");
			Assert.fail("日常运营清算-交易检查自动化页面程序失败");
		}else{
			
			Reporter.log("日常运营清算-交易检查自动化页面程序成功");
		}
	}

//	@Test(priority = 8)
	public void jiaoyiqingsuan() throws InterruptedException {
		
		
		if( !new RiChangYunYingQingSuan().jiaoyiqingsuan()){
			
			Reporter.log("日常运营清算-交易清算自动化页面程序失败");
			Assert.fail("日常运营清算-交易清算自动化页面程序失败");
		}else{
			
			Reporter.log("日常运营清算-交易清算自动化页面程序成功");
		}
	}
//	@Test(priority = 9)
	public void querenshujudaochu() throws InterruptedException {
		
		
		if(!new RiChangYunYingQingSuan().querenshujudaochu()){
			
			Reporter.log("日常运营清算-确认数据导出自动化页面程序失败");
			Assert.fail("日常运营清算-确认数据导出自动化页面程序失败");
		}else{
			
			Reporter.log("日常运营清算-确认数据导出自动化页面程序成功");
		}
	}
	//@Test(priority = 10)
	public void rizhongqueren() throws InterruptedException {
		
		
		if(!new RiChangYunYingQingSuan().rizhongqueren()){
			
			Reporter.log("日常运营清算-日终确认自动化页面程序失败");
			Assert.fail("日常运营清算-日终确认自动化页面程序失败");
		}else{
			
			Reporter.log("日常运营清算-日终确认自动化页面程序成功");
		}
	}
	@Test(priority =11 )
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

//	@Test(priority = 11)
//	public void save() throws InterruptedException, SQLException{
//		String errorMes = null;
//		MyResponse T_TA_B_APPFDACBLOTTERRes = new Save_T_TA_B_APPFDACBLOTTER().save_T_TA_B_APPFDACBLOTTER();
//		if((int)T_TA_B_APPFDACBLOTTERRes.get(MyResponse.STATUS) == MyResponse.FAILED){
//			
//			Reporter.log("日常运营清算-账户检查数据库读表失败"+T_TA_B_APPFDACBLOTTERRes.getMessage());
//			errorMes = "日常运营清算-账户检查数据库读表失败"+T_TA_B_APPFDACBLOTTERRes.getMessage();
//		}else{
//			
//			Reporter.log("日常运营清算-账户检查数据库读表成功");
////		}
//		MyResponse T_TA_ACKFDACBLOTTERRes =  new Save_T_TA_ACKFDACBLOTTER().save_T_TA_ACKFDACBLOTTER();
//		if((int)T_TA_ACKFDACBLOTTERRes.get(MyResponse.STATUS) == MyResponse.FAILED){
//			
//			Reporter.log("日常运营清算-账户检查数据库读表失败"+T_TA_ACKFDACBLOTTERRes.getMessage());
//			errorMes += "\n日常运营清算-账户清算数据库读表失败"+T_TA_ACKFDACBLOTTERRes.getMessage();
//		}else{
//			
//			Reporter.log("日常运营清算-账户清算数据库读表成功");
//		}
//		MyResponse T_TA_B_APPTRADEBLOTTERRes= new Save_T_TA_B_APPTRADEBLOTTER().save_T_TA_B_APPTRADEBLOTTER();
//		if((int)T_TA_B_APPTRADEBLOTTERRes.get(MyResponse.STATUS) == MyResponse.FAILED){
//			
//			Reporter.log("日常运营清算-交易检查数据库读表失败"+T_TA_B_APPTRADEBLOTTERRes.getMessage());
//			errorMes += "\n日常运营清算-交易检查数据库读表失败"+T_TA_B_APPTRADEBLOTTERRes.getMessage();
//		}else{
//			
//			Reporter.log("日常运营清算-交易检查数据库读表成功");
//		}
//		MyResponse T_TA_ACKTRADEBLOTTERRes = new Save_T_TA_ACKTRADEBLOTTER().save_T_TA_ACKTRADEBLOTTER();
//		if((int)T_TA_ACKTRADEBLOTTERRes.get(MyResponse.STATUS) == MyResponse.FAILED){
//			
//			Reporter.log("日常运营清算-交易清算数据库读表失败"+T_TA_ACKTRADEBLOTTERRes.getMessage());
//			errorMes += "\n日常运营清算-交易清算数据库读表失败"+T_TA_ACKTRADEBLOTTERRes.getMessage();
//		}else{
//			
//			Reporter.log("日常运营清算-交易清算数据库读表成功");
//		}
//		if(errorMes != null){
//			Assert.fail(errorMes);
//		}
//	}
	@Test(priority =12 )
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
//	@Test(priority = 12)
//	public void compare() throws InterruptedException{
//		String errorMes = "";
//		MyResponse T_TA_B_APPFDACBLOTTERRes = new Compare_T_TA_B_APPFDACBLOTTER().compare_T_TA_B_APPFDACBLOTTER();
//		if((int)T_TA_B_APPFDACBLOTTERRes.get(MyResponse.STATUS) == MyResponse.FAILED){
//			
//			Reporter.log("日常运营清算-账户检查数据库比较失败"+T_TA_B_APPFDACBLOTTERRes.getMessage());
//			errorMes += "日常运营清算-账户检查数据库比较失败"+T_TA_B_APPFDACBLOTTERRes.getMessage();
//		}else{
//			
//			Reporter.log("日常运营清算-账户检查数据库比较成功");
//		}
//		MyResponse T_TA_ACKFDACBLOTTERRes =  new Compare_T_TA_ACKFDACBLOTTER().compare_T_TA_ACKFDACBLOTTER_V2();
//		if((int)T_TA_ACKFDACBLOTTERRes.get(MyResponse.STATUS) == MyResponse.FAILED){
//			
//			Reporter.log("日常运营清算-账户清算数据库比较失败"+T_TA_ACKFDACBLOTTERRes.getMessage());
//			errorMes += "\n日常运营清算-账户清算数据库比较失败"+T_TA_ACKFDACBLOTTERRes.getMessage();
//		}else{
//			
//			Reporter.log("日常运营清算-账户清算数据库比较成功");
//		}
//		MyResponse T_TA_B_APPTRADEBLOTTERRes= new Compare_T_TA_B_APPTRADEBLOTTER().compare_T_TA_B_APPTRADEBLOTTER();
//		if((int)T_TA_B_APPTRADEBLOTTERRes.get(MyResponse.STATUS) == MyResponse.FAILED){
//			
//			Reporter.log("日常运营清算-交易检查数据库比较失败"+T_TA_B_APPTRADEBLOTTERRes.getMessage());
//			errorMes += "\n日常运营清算-交易检查数据库比较失败"+T_TA_B_APPTRADEBLOTTERRes.getMessage();
//		}else{
//			
//			Reporter.log("日常运营清算-交易检查数据库比较成功");
//		}
//		MyRESPONSE T_TA_ACKTRADEBLOTTERRES = NEW COMPARE_T_TA_ACKTRADEBLOTTER().COMPARE_T_TA_ACKTRADEBLOTTER();
//		IF((STRING)T_TA_ACKTRADEBLOTTERRES.GET("MSG") != NULL){
//			
//			REPORTER.LOG("日常运营清算-交易清算数据库比较失败"+T_TA_ACKTRADEBLOTTERRES.GET("MSG"));
//			ERRORMES += "日常运营清算-交易清算数据库比较失败"+T_TA_ACKTRADEBLOTTERRES.GET("MSG");
//		}ELSE{
//			
//			REPORTER.LOG("日常运营清算-交易清算数据库比较成功");
//		}
//		IF(!ERRORMES.EQUALS("")){
//			ASSERT.FAIL(ERRORMES);
//		}
//	}
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
