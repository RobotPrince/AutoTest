package com.yss.common;

// 读取Excel的类 
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.Test;

import com.yss.method.ChanPinDongTai.ChanPinDongTaiEnum;
import com.yss.method.ChanPinFeiLv.ChanPinFeiLvEnum;
import com.yss.method.ChanPinQingSuanZhouQi.ChanPinQingSuanZhouQiEnum;
import com.yss.method.ChanPinXiaoShouDaiLiGuanXi.ChanPinXiaoShouDaiLiGuanXiEnum;
import com.yss.method.ChanPinXinXi.ChanPinXinXiEnum;
import com.yss.method.ChanPinZhiXingRenGuanXi.ChanPinZhiXingRenGuanXiEnum;
import com.yss.method.FeiYongFenCheng.FeiYongFenChengEnum;
import com.yss.method.GuDingShouYiLiLv.GuDingShouYiLiLvEnum;
import com.yss.method.GuanLianJiGouXinXi.GuanLianJiGouXinXiEnum;
import com.yss.method.HeSuanJiGouXinXi.HeSuanJiGouXinXiEnum;
import com.yss.method.Login.LoginEnum;
import com.yss.method.RiChangYunYingQingSuan.RiChangYunYingQingSuanEnum;
import com.yss.method.XiaoShouJiGouXinXi.XiaoShouJiGouXinXiEnum;
import com.yss.method.YinHangJiBenXinXi.YinHangJiBenXinXiEnum;
import com.yss.method.YongHuZhiXingRenGuanXi.YongHuZhiXingRenGuanXiEnum;
import com.yss.method.ZhangHuLeiCanShu.ZhangHuLeiCanShuEnum;
import com.yss.method.ZheKouGuanLi.ZheKouGuanLiEnum;
import com.yss.method.ZhiXingQuanXianRenXinXi.ZhiXingQuanXianRenXinXiEnum;

/**
 * Read WebElements from Excel
 * 
 * @author tanglonglong
 */
public class ReadFromExcel {

	final static String ELEMENT_NAME = "element_name";
	final static String TYPE = "type";
	final static String VALUE = "value";
	final static String REMARK = "remark";

	public static HashMap<PageEnum, HashMap<String, List<String>>> elementsFromExcel = new HashMap<PageEnum, HashMap<String, List<String>>>();
	public static List<HashMap<LoginEnum, String>> dataForLoginPageFromExcel = new ArrayList<HashMap<LoginEnum, String>>();
	public static List<HashMap<RiChangYunYingQingSuanEnum, String>> dataForRiChangYunYingFromExcel = new ArrayList<HashMap<RiChangYunYingQingSuanEnum, String>>();
	public static List<HashMap<HeSuanJiGouXinXiEnum, String>> dataForHeSuanJiGouFromExcel = new ArrayList<HashMap<HeSuanJiGouXinXiEnum, String>>();
	public static List<HashMap<XiaoShouJiGouXinXiEnum, String>> dataForXiaoShouJIGouXinXiFromExcel = new ArrayList<HashMap<XiaoShouJiGouXinXiEnum, String>>();
	public static List<HashMap<GuanLianJiGouXinXiEnum, String>> dataForGuanLianJIGouXinXiFromExcel = new ArrayList<HashMap<GuanLianJiGouXinXiEnum, String>>();
	public static List<HashMap<ChanPinXinXiEnum, String>> dataForChanPinXinXiFromExcel = new ArrayList<HashMap<ChanPinXinXiEnum, String>>();
	public static List<HashMap<ChanPinFeiLvEnum, String>> dataForChanPinFeiLvFromExcel = new ArrayList<HashMap<ChanPinFeiLvEnum, String>>();
	public static List<HashMap<ChanPinXiaoShouDaiLiGuanXiEnum, String>> dataForChanPinXiaoShouDaiLiGuanXiFromExcel = new ArrayList<HashMap<ChanPinXiaoShouDaiLiGuanXiEnum, String>>();
	public static List<HashMap<FeiYongFenChengEnum, String>> dataForFeiYongFenChengFromExcel = new ArrayList<HashMap<FeiYongFenChengEnum, String>>();
	public static List<HashMap<ZheKouGuanLiEnum, String>> dataForZheKouGuanLiFromExcel = new ArrayList<HashMap<ZheKouGuanLiEnum, String>>();
	
	public static List<HashMap<ChanPinZhiXingRenGuanXiEnum, String>> dataForChanPinZhiXingRenGuanXiFromExcel = new ArrayList<HashMap<ChanPinZhiXingRenGuanXiEnum, String>>();
	public static List<HashMap<ChanPinQingSuanZhouQiEnum, String>> dataForChanPinQingSuanZhouQiFromExcel = new ArrayList<HashMap<ChanPinQingSuanZhouQiEnum, String>>();
	public static List<HashMap<YongHuZhiXingRenGuanXiEnum, String>> dataForYongHuZhiXingRenGuanXiFromExcel = new ArrayList<HashMap<YongHuZhiXingRenGuanXiEnum, String>>();
	public static List<HashMap<GuDingShouYiLiLvEnum, String>> dataForGuDingShouYiLiLvFromExcel = new ArrayList<HashMap<GuDingShouYiLiLvEnum, String>>();
	public static List<HashMap<ZhiXingQuanXianRenXinXiEnum, String>> dataForZhiXingQuanXianRenXinXiFromExcel = new ArrayList<HashMap<ZhiXingQuanXianRenXinXiEnum, String>>();
	public static List<HashMap<ChanPinDongTaiEnum, String>> dataForChanPinDongTaiFromExcel = new ArrayList<HashMap<ChanPinDongTaiEnum, String>>();
	public static List<HashMap<ZhangHuLeiCanShuEnum, String>> dataForZhangHuLeiCanShuFromExcel = new ArrayList<HashMap<ZhangHuLeiCanShuEnum, String>>();
	
	
	public static List<HashMap<YinHangJiBenXinXiEnum, String>> dataForYinHangJiBenXinXiFromExcel = new ArrayList<HashMap<YinHangJiBenXinXiEnum, String>>();
	/**
	 * !!!!!!!!!!!!!!!!!
	 * 这个需要维护
	 */
	@Test
	public void allReadMethod(){
		if(Common.driver == null){
			Common.getFFDriver();
		}
		Common.logInfo("allReadMethod");
		boolean flag = true;
			
		
		flag = readForWebElements()&&flag;
		flag = readForHeSuanJiGouXinXi()&&flag;
		flag = readForXiaoShouJiGouXinXi()&&flag;
		flag = 	readForGuanLianJiGouXinXi()&&flag;
		flag = readForChanPinXinXi()&&flag;
		flag = readForLoginPage()&&flag;
		flag = readForRiChangYunYingQingSuan()&&flag;
		flag = readForChanPinFeiLv()&&flag;
		flag = readForChanPinXiaoShouDaiLiGuanXi()&&flag;
		flag = readForFeiYongFenCheng()&&flag;
		flag = readForZheKouGuanLi()&&flag;
		
		flag = readForChanPinZhiXingRenGuanXi()&&flag;
		flag = readForChanPinQingSuanZhouQi()&&flag;
		flag = readForYongHuZhiXingRenGuanXi()&&flag;
		flag = readForGuDingShouYiLiLv()&&flag;
		flag = readForZhiXingQuanXianRenXinXi()&&flag;
		flag = readForChanPinZhiXingRenGuanXi()&&flag;
		flag = readForZhangHuLeiCanShu()&&flag;
		
		flag = readForYinHangJiBenXinXi()&&flag;
		flag = readForChanPinDongTai()&&flag;
		
		if(flag == false){
			Common.logError("Error happed in ReadFromExcel");
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	

	/**
	 * readForChanPinZhiXingRenGuanXi-产品执行人关系
	 * @return
	 */
	private boolean readForChanPinZhiXingRenGuanXi() {
		
		Common.logInfo("readForChanPinZhiXingRenGuanXi");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得ChanPinFeiLv工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("chanpinzhixingrenguanxi".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the chanpinzhixingrenguanxi sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			
			for (int r = 2; r < rows; r++) {
				LinkedHashMap<ChanPinZhiXingRenGuanXiEnum, String> linkedHashMap = new LinkedHashMap<ChanPinZhiXingRenGuanXiEnum, String>();
				// 取出第一行数据的所有数据
				String zhixingrendaima = sheet.getCell(1, r).getContents();
				String chanpindaima = sheet.getCell(2, r).getContents();
				String chanpinmingcheng = sheet.getCell(3, r).getContents();
				String ischecked = sheet.getCell(4, r).getContents();
				
				linkedHashMap.put(ChanPinZhiXingRenGuanXiEnum.CHANPINDAIMA, chanpindaima);
				linkedHashMap.put(ChanPinZhiXingRenGuanXiEnum.ZHIXINGRENDAIMA, zhixingrendaima);
				linkedHashMap.put(ChanPinZhiXingRenGuanXiEnum.CHANPINMINGCHENG, chanpinmingcheng);
				linkedHashMap.put(ChanPinZhiXingRenGuanXiEnum.ISCHECKED, ischecked);
				
				// 放入全局变量dataForZheKouGuanLiFromExcel中
				dataForChanPinZhiXingRenGuanXiFromExcel.add(linkedHashMap);
			}
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
		
	}
	/**
	 * HashMap(PageEnum, HashMap(String, List(String))) hashMapOfExcel-所有页面元素
	 * 
	 * @author tanglonglong
	 */
	public boolean readForWebElements() {
		Common.logInfo("readExc");
		try {

			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/WebElements.xls"));
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			int columns = sheet.getColumns();
			// 获取第1行，第0列作为初始值
			PageEnum pageEnum = PageEnum.valueOf(sheet.getCell(0, 1)
					.getContents().toUpperCase());
			HashMap<String, List<String>> hashMapOfPage = new HashMap<String, List<String>>();
			// 循环所有行
			for (int i = 1; i < rows; i++) {

				String PageName = sheet.getCell(0, i).getContents();
				PageEnum pageEnumTemp = PageEnum
						.valueOf(PageName.toUpperCase());

				// 判断该元素与上一元素是否属于同一画面
				if (!pageEnumTemp.equals(pageEnum)) {
					
					Common.logWarn(pageEnumTemp+":"+hashMapOfPage.size());
					hashMapOfPage = new HashMap<String, List<String>>();
					pageEnum = pageEnumTemp;
				}
				String elementName = null;
				String type = null;
				String value = null;
				String remark = null;
				// 取出該元素的的element_name,type,value,remark
				for (int j = 1; j < columns; j++) {
					switch (sheet.getCell(j, 0).getContents()) {
					case ELEMENT_NAME:
						elementName = sheet.getCell(j, i).getContents();
						Common.logInfo(pageEnum+"-i: "+i+" j: "+j);
						break;
					case TYPE:
						type = sheet.getCell(j, i).getContents();
						Common.logInfo(pageEnum+"-i: "+i+" j: "+j);
						break;
					case VALUE:
						value = sheet.getCell(j, i).getContents();
						Common.logInfo(pageEnum+"-i: "+i+" j: "+j);
						break;
					case REMARK:
						remark = sheet.getCell(j, i).getContents();
						Common.logInfo(pageEnum+"-i: "+i+" j: "+j);
						break;
					default:
						break;
					}
				}
				// 將取出的值放入Map中
				ArrayList<String> arrayList = new ArrayList<String>();
				arrayList.add(type);
				arrayList.add(value);
				arrayList.add(remark);
				hashMapOfPage.put(elementName, arrayList);

				elementsFromExcel.put(pageEnumTemp, hashMapOfPage);
			}
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		//判断PageEnum维护的所有页面是否在WebElement.xls中被读出
		if(elementsFromExcel.entrySet().size()<PageEnum.values().length){
			Common.logError("Number of page in WebElement.xls not equals number of page in PageEnum");
			return false;
		}
		return true;
	}

	/**
	 * readForHeSuanJiGouXinXi-核算机构信息
	 * @return
	 */
	public boolean readForHeSuanJiGouXinXi() {
		Common.logInfo("readForHeSuanJiGouXinXi");

		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得Login_page工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("hesuanjigouxinxi".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the hesuanjigouxinxi sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();

			for (int r = 2; r < rows; r++) {
				LinkedHashMap<HeSuanJiGouXinXiEnum, String> linkedHashMap = new LinkedHashMap<HeSuanJiGouXinXiEnum, String>();
				// 取出第一行数据的所有数据
				String jigoudaima = sheet.getCell(1, r).getContents();
				String jigoumingcheng = sheet.getCell(2, r).getContents();
				String jigouzhuangtai = sheet.getCell(3, r).getContents();
				String dianhua = sheet.getCell(4, r).getContents();
				String jiekoubanben = sheet.getCell(5, r).getContents();
				String dizhi = sheet.getCell(6, r).getContents();
				String wangzhi = sheet.getCell(7, r).getContents();
				String daorulujing = sheet.getCell(8, r).getContents();
				String daochulujing = sheet.getCell(9, r).getContents();
				String ischecked = sheet.getCell(10, r).getContents();
				linkedHashMap.put(HeSuanJiGouXinXiEnum.JIGOUDAIMA, jigoudaima);
				linkedHashMap.put(HeSuanJiGouXinXiEnum.JIGOUMINGCHENG, jigoumingcheng);
				linkedHashMap.put(HeSuanJiGouXinXiEnum.JIGOUZHUANGTAI, jigouzhuangtai);
				linkedHashMap.put(HeSuanJiGouXinXiEnum.DIANHUA, dianhua);
				linkedHashMap.put(HeSuanJiGouXinXiEnum.JIEKOUBANBEN, jiekoubanben);
				linkedHashMap.put(HeSuanJiGouXinXiEnum.DIZHI, dizhi);
				linkedHashMap.put(HeSuanJiGouXinXiEnum.WANGZHI, wangzhi);
				linkedHashMap.put(HeSuanJiGouXinXiEnum.DAORULUJING, daorulujing);
				linkedHashMap.put(HeSuanJiGouXinXiEnum.DAOCHULUJING, daochulujing);
				linkedHashMap.put(HeSuanJiGouXinXiEnum.ISCHECKED, ischecked);
				// 放入全局变量dataForLoginPageFromExcel中
				dataForHeSuanJiGouFromExcel.add(linkedHashMap);
			}

		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * readForRiChangYunYingQingSuan-日常运营清算
	 * @return
	 */
	public boolean readForRiChangYunYingQingSuan(){
		Common.logInfo("readForRiChangYunYingQingSuan");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得Login_page工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("richangyunyingqingsuan".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("readForRiChangYunYingQingSuan error,the richangyunyingqingsuan sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();

			for (int r = 2; r < rows; r++) {
				LinkedHashMap<RiChangYunYingQingSuanEnum, String> linkedHashMap = new LinkedHashMap<RiChangYunYingQingSuanEnum, String>();
				// 取出第一行数据的所有数据
				String qingsuanriqi = sheet.getCell(1, r).getContents();
				String xuanzedaoruriqi = sheet.getCell(2, r).getContents();
				String xuanzedaoruhesuanjigou = sheet.getCell(3, r).getContents();
				String xuanzedaoruxiaoshoujigou = sheet.getCell(4, r).getContents();
				String daorufangshi = sheet.getCell(5, r).getContents();
				
				linkedHashMap.put(RiChangYunYingQingSuanEnum.QINGSUANRIQI, qingsuanriqi);
				linkedHashMap.put(RiChangYunYingQingSuanEnum.XUANZEDAORURIQI, xuanzedaoruriqi);
				linkedHashMap.put(RiChangYunYingQingSuanEnum.XUANZEDAORUHESUANJIGOU, xuanzedaoruhesuanjigou);
				linkedHashMap.put(RiChangYunYingQingSuanEnum.XUANZEDAORUXIAOSHOUJIGOU, xuanzedaoruxiaoshoujigou);
				linkedHashMap.put(RiChangYunYingQingSuanEnum.DAORUFANGSHI, daorufangshi);
				// 放入全局变量dataForLoginPageFromExcel中
				dataForRiChangYunYingFromExcel.add(linkedHashMap);
			}

		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * readForLoginPage-登录
	 * @return
	 */
	public boolean readForLoginPage() {
		Common.logInfo("readForLoginPage");

		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得Login_page工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("Login_page".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the Login_page sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			// 获取sheet的所有列数
			int columns = sheet.getColumns();

			for (int r = 2; r < rows; r++) {
				HashMap<LoginEnum, String> hashMap = new HashMap<LoginEnum, String>();
				// 取出第一行数据的user和pwd
				String user = sheet.getCell(1, r).getContents();
				String pwd = sheet.getCell(2, r).getContents();
				hashMap.put(LoginEnum.USER, user);
				hashMap.put(LoginEnum.PWD, pwd);
				// 放入全局变量dataForLoginPageFromExcel中
				dataForLoginPageFromExcel.add(hashMap);
			}

		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * readForXiaoShouJiGouXinXi-销售机构信息
	 * @return
	 */
	public boolean readForXiaoShouJiGouXinXi() {
		Common.logInfo("readForXiaoShouJiGouXinXi");

		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得Login_page工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("xiaoshoujigouxinxi".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the xiaoshoujigouxinxi sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();

			for (int r = 2; r < rows; r++) {
				LinkedHashMap<XiaoShouJiGouXinXiEnum, String> linkedHashMap = new LinkedHashMap<XiaoShouJiGouXinXiEnum, String>();
				// 取出第一行数据的所有数据
				String jigoudaima = sheet.getCell(1, r).getContents();
				String jigouquancheng = sheet.getCell(2, r).getContents();
				String jigouleixing = sheet.getCell(3, r).getContents();
				String jigouzhuangtai = sheet.getCell(4, r).getContents();
				String jigouchuanzhen = sheet.getCell(5, r).getContents();
				String jigoulianxiren = sheet.getCell(6, r).getContents();
				String dianhua = sheet.getCell(7, r).getContents();
				String youjianbianma = sheet.getCell(8, r).getContents();
				String dianziyoujian = sheet.getCell(9, r).getContents();
				String zongbudizhi = sheet.getCell(10, r).getContents();
				String duizhangfangshi = sheet.getCell(11, r).getContents();
				String jiekoubanben = sheet.getCell(12, r).getContents();
				String shifoudaochu = sheet.getCell(13, r).getContents();
				String mingxibiaoshi = sheet.getCell(14, r).getContents();
				String jiekoudaorulujin = sheet.getCell(15, r).getContents();
				String jiekoudaochulujing = sheet.getCell(16, r).getContents();
				String ischecked = sheet.getCell(17, r).getContents();
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.JIGOUDAIMA, jigoudaima);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.JIGOUQUANCHENG, jigouquancheng);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.JIGOULEIXING, jigouleixing);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.JIGOUZHUANGTAI, jigouzhuangtai);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.JIGOUCHUANZHEN, jigouchuanzhen);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.JIGOULIANXIREN, jigoulianxiren);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.DIANHUA, dianhua);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.YOUJIANBIANMA, youjianbianma);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.DIANZIYOUJIAN, dianziyoujian);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.ZONGBUDIZHI, zongbudizhi);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.DUIZHANGFANGSHI, duizhangfangshi);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.JIEKOUBANBEN, jiekoubanben);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.SHIFOUDAOCHU, shifoudaochu);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.MINGXIBIAOSHI, mingxibiaoshi);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.JIEKOUDAORULUJING, jiekoudaorulujin);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.JIEKOUDAOCHULUJING, jiekoudaochulujing);
				linkedHashMap.put(XiaoShouJiGouXinXiEnum.ISCHECKED, ischecked);
				// 放入全局变量dataForXiaoShouJIGouXinXiFromExcel中
				dataForXiaoShouJIGouXinXiFromExcel.add(linkedHashMap);
			}

		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * readForGuanLianJiGouXinXi-关联机构信息
	 * @return
	 */
	public boolean readForGuanLianJiGouXinXi() {
		Common.logInfo("readForGuanLianJiGouXinXi");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得Login_page工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("guanlianjigouxinxi".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the guanlianjigouxinxi sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			
			for (int r = 2; r < rows; r++) {
				LinkedHashMap<GuanLianJiGouXinXiEnum, String> linkedHashMap = new LinkedHashMap<GuanLianJiGouXinXiEnum, String>();
				// 取出第一行数据的所有数据
				String jigouleixing = sheet.getCell(1, r).getContents();
				String jigouzhuangtai = sheet.getCell(2, r).getContents();
				String jigoudaima = sheet.getCell(3, r).getContents();
				String jigoumingcheng = sheet.getCell(4, r).getContents();
				String dizhi = sheet.getCell(5, r).getContents();
				String dianhua = sheet.getCell(6, r).getContents();
				String wangzhi = sheet.getCell(7, r).getContents();
				String yinhangzhanghao = sheet.getCell(8, r).getContents();
				String ischecked = sheet.getCell(9, r).getContents();
				linkedHashMap.put(GuanLianJiGouXinXiEnum.JIGOULEIXING, jigouleixing);
				linkedHashMap.put(GuanLianJiGouXinXiEnum.JIGOUZHUANGTAI, jigouzhuangtai);
				linkedHashMap.put(GuanLianJiGouXinXiEnum.JIGOUDAIMA, jigoudaima);
				linkedHashMap.put(GuanLianJiGouXinXiEnum.JIGOUMINGCHENG, jigoumingcheng);
				linkedHashMap.put(GuanLianJiGouXinXiEnum.DIZHI, dizhi);
				linkedHashMap.put(GuanLianJiGouXinXiEnum.DIANHUA, dianhua);
				linkedHashMap.put(GuanLianJiGouXinXiEnum.WANGZHI, wangzhi);
				linkedHashMap.put(GuanLianJiGouXinXiEnum.YINHANGZHANGHAO, yinhangzhanghao);
				linkedHashMap.put(GuanLianJiGouXinXiEnum.ISCHECKED, ischecked);
				// 放入全局变量dataForGuanLianJIGouXinXiFromExcel中
				dataForGuanLianJIGouXinXiFromExcel.add(linkedHashMap);
			}
			
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * readForChanPinXinXi-产品信息
	 * @return
	 */
	public boolean readForChanPinXinXi() {
		Common.logInfo("readForChanPinXinXi");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得Login_page工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("chanpinxinxi".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the chanpinxinxi sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			
			for (int r = 2; r < rows; r++) {
				LinkedHashMap<ChanPinXinXiEnum, String> linkedHashMap = new LinkedHashMap<ChanPinXinXiEnum, String>();
				// 取出第一行数据的所有数据
				String chanpindalei = sheet.getCell(1, r).getContents();
				String chanpindaima = sheet.getCell(2, r).getContents();
				String chanpinyingwenming = sheet.getCell(3, r).getContents();
				String chanpinquancheng = sheet.getCell(4, r).getContents();
				String chanpinjiancheng = sheet.getCell(5, r).getContents();
				String guanlirendaima = sheet.getCell(6, r).getContents();
				String tuoguanrendaima = sheet.getCell(7, r).getContents();
				String faqirendaima = sheet.getCell(8, r).getContents();
				String gongmubiaoshi = sheet.getCell(9, r).getContents();
				String chanpinleixing = sheet.getCell(10, r).getContents();
				String chanpinbizhong = sheet.getCell(11, r).getContents();
				String chanpinzhuangtai = sheet.getCell(12, r).getContents();
				String jiaoyiduixiang = sheet.getCell(13, r).getContents();
				String zhuanhuanbiaozhi = sheet.getCell(14, r).getContents();
				String chanpinmianzhi = sheet.getCell(15, r).getContents();
				String feiyongjisuanfangshi = sheet.getCell(16, r).getContents();
				String shoufeifangshi = sheet.getCell(17, r).getContents();
				String chanpinchengliriqi = sheet.getCell(18, r).getContents();
				String chanpinfengxiandengji = sheet.getCell(19, r).getContents();
				String touziguwenmingcheng = sheet.getCell(20, r).getContents();
				String touziguwensimuguanlirenbianma = sheet.getCell(21, r).getContents();
				String guanxiyongtu = sheet.getCell(22, r).getContents();
				String mujikaishiriqi = sheet.getCell(23, r).getContents();
				String mujijieshuriqi = sheet.getCell(24, r).getContents();
				String fengbijiezhiriqi = sheet.getCell(25, r).getContents();
				String chanpinzhongzhiriqi = sheet.getCell(26, r).getContents();
				String chanpinfaxingfangshi = sheet.getCell(27, r).getContents();
				String chanpinfaxingjiage = sheet.getCell(28, r).getContents();
				String zuidimujiguimo = sheet.getCell(29, r).getContents();
				String zuigaomujiguimo = sheet.getCell(30, r).getContents();
				String zuidikaihushuliang = sheet.getCell(31, r).getContents();
				String zuigaokaihushuliang = sheet.getCell(32, r).getContents();
				String hushuchaoxianchulifangshi = sheet.getCell(33, r).getContents();
				String mujiqilixichulifangshi = sheet.getCell(34, r).getContents();
				String mujiqijixiqishiriqi = sheet.getCell(35, r).getContents();
				String fenechaoguimochulifangshi = sheet.getCell(36, r).getContents();
				String mujiqijixijiezhiriqi = sheet.getCell(37, r).getContents();
				String chaoguimomobichulifangshi = sheet.getCell(38, r).getContents();
				String jiaoyikaishishijian = sheet.getCell(39, r).getContents();
				String jiaoyibishishijian = sheet.getCell(40, r).getContents();
				String jueshuhuibili = sheet.getCell(41, r).getContents();
				String morenfenhongfangshi = sheet.getCell(42, r).getContents();
				String zhanghuzuidichiyoufene = sheet.getCell(43, r).getContents();
				String zhanghuzuidichiyoujine = sheet.getCell(44, r).getContents();
				String zuidihonglizhuantoujine = sheet.getCell(45, r).getContents();
				String zuidichanpintuoguanfenshu = sheet.getCell(46, r).getContents();
				String zuidichanpinzhuanhuanfenshu = sheet.getCell(47, r).getContents();
				String chanpinzuidishuhuixiane = sheet.getCell(48, r).getContents();
				String tuoguanfeijitifeilu = sheet.getCell(49, r).getContents();
				String guanlifeijitifeilu = sheet.getCell(50, r).getContents();
				String touzifangxiang = sheet.getCell(51, r).getContents();
				String shifoubaoben = sheet.getCell(52, r).getContents();
				String shifoulof = sheet.getCell(53, r).getContents();
				String shifouqdii = sheet.getCell(54, r).getContents();
				String shifouetf = sheet.getCell(55, r).getContents();
				String gerenshoucirengouzuidijine = sheet.getCell(56, r).getContents();
				String gerenshoucishengouzuidijine = sheet.getCell(57, r).getContents();
				String gerendanbishengouzuigaojine = sheet.getCell(58, r).getContents();
				String gerenzuigaoshengoujine = sheet.getCell(59, r).getContents();
				String gerenzuigaorengoujine = sheet.getCell(60, r).getContents();
				String gerenzuigaoshengoufene = sheet.getCell(61, r).getContents();
				String gerenzuigaorengoufene = sheet.getCell(62, r).getContents();
				String gerenshengoujinedanwei = sheet.getCell(63, r).getContents();
				String gerenrengoujinedanwei = sheet.getCell(64, r).getContents();
				String gerenzhuijiarengouzuidijine = sheet.getCell(65, r).getContents();
				String gerenzhuijiashengouzuidijine = sheet.getCell(66, r).getContents();
				String gerendanrileijigoumaizuigaojine = sheet.getCell(67, r).getContents();
				String gerendanrileijishuhuizuigaofene = sheet.getCell(68, r).getContents();
				String gerenzuigaoshuhuifene = sheet.getCell(69, r).getContents();
				String jigoushoucirengouzuidijine = sheet.getCell(70, r).getContents();
				String jigoushoucishengouzuidijine = sheet.getCell(71, r).getContents();
				String jigoudanbishengouzuigaojine = sheet.getCell(72, r).getContents();
				String jigouzuigaoshengoujine = sheet.getCell(73, r).getContents();
				String jigouzuigaorengoujine = sheet.getCell(74, r).getContents();
				String jigouzuigaoshengoufene = sheet.getCell(75, r).getContents();
				String jigouzuigaorengoufene = sheet.getCell(76, r).getContents();
				String jigoushengoujinedanwei = sheet.getCell(77, r).getContents();
				String jigourengoujinedanwei = sheet.getCell(78, r).getContents();
				String jigouzhuijiarengouzuidijine = sheet.getCell(79, r).getContents();
				String jigouzhuijiashengouzuidijine = sheet.getCell(80, r).getContents();
				String jigoudanrileijigoumaizuigaojine = sheet.getCell(81, r).getContents();
				String jigoudanrileijishuhuizuigaofene = sheet.getCell(82, r).getContents();
				String jigouzuigaoshuhuifene = sheet.getCell(83, r).getContents();
				String daoqiqiannritixing = sheet.getCell(84, r).getContents();
				String zhuanhuchanpinshuxing = sheet.getCell(85, r).getContents();
				String fengbiqiqingsuanpinlu = sheet.getCell(86, r).getContents();
				String weiyueshuhuishihoushouqushuhuifei = sheet.getCell(87, r).getContents();
				String shouyijisuanniantian1 = sheet.getCell(88, r).getContents();
				String yejitichengweichashuli = sheet.getCell(89, r).getContents();
				String shenchabeianriqi = sheet.getCell(90, r).getContents();
				String hetongshengxiaoriqi = sheet.getCell(91, r).getContents();
				String hetongzhongzhiriqi = sheet.getCell(92, r).getContents();
				String weiyuejinjisuangongshi1 = sheet.getCell(93, r).getContents();
				String shouyijisuanniantian2 = sheet.getCell(94, r).getContents();
				String weiyuejinjisuangongshi2 = sheet.getCell(95, r).getContents();
				String shijianleijinjisuan = sheet.getCell(96, r).getContents();
				String weiyuetianshu = sheet.getCell(97, r).getContents();
				String shouyijisuanniantian3 = sheet.getCell(98, r).getContents();
				String ischecked = sheet.getCell(99, r).getContents();

				linkedHashMap.put(ChanPinXinXiEnum.CHANPINDALEI, chanpindalei);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINDAIMA, chanpindaima);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINYINGWENMING, chanpinyingwenming);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINQUANCHENG, chanpinquancheng);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINJIANCHENG, chanpinjiancheng);
				linkedHashMap.put(ChanPinXinXiEnum.GUANLIRENDAIMA, guanlirendaima);
				linkedHashMap.put(ChanPinXinXiEnum.TUOGUANRENDAIMA, tuoguanrendaima);
				linkedHashMap.put(ChanPinXinXiEnum.FAQIRENDAIMA, faqirendaima);
				linkedHashMap.put(ChanPinXinXiEnum.GONGMUBIAOSHI, gongmubiaoshi);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINLEIXING, chanpinleixing);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINBIZHONG, chanpinbizhong);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINZHUANGTAI, chanpinzhuangtai);
				linkedHashMap.put(ChanPinXinXiEnum.JIAOYIDUIXIANG, jiaoyiduixiang);
				linkedHashMap.put(ChanPinXinXiEnum.ZHUANHUANBIAOZHI, zhuanhuanbiaozhi);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINMIANZHI, chanpinmianzhi);
				linkedHashMap.put(ChanPinXinXiEnum.FEIYONGJISUANFANGSHI, feiyongjisuanfangshi);
				linkedHashMap.put(ChanPinXinXiEnum.SHOUFEIFANGSHI, shoufeifangshi);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINCHENGLIRIQI, chanpinchengliriqi);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINFENGXIANDENGJI, chanpinfengxiandengji);
				linkedHashMap.put(ChanPinXinXiEnum.TOUZIGUWENMINGCHENG, touziguwenmingcheng);
				linkedHashMap.put(ChanPinXinXiEnum.TOUZIGUWENSIMUGUANLIRENBIANMA, touziguwensimuguanlirenbianma);
				linkedHashMap.put(ChanPinXinXiEnum.GUANXIYONGTU, guanxiyongtu);
				linkedHashMap.put(ChanPinXinXiEnum.MUJIKAISHIRIQI, mujikaishiriqi);
				linkedHashMap.put(ChanPinXinXiEnum.MUJIJIESHURIQI, mujijieshuriqi);
				linkedHashMap.put(ChanPinXinXiEnum.FENGBIJIEZHIRIQI, fengbijiezhiriqi);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINZHONGZHIRIQI, chanpinzhongzhiriqi);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINFAXINGFANGSHI, chanpinfaxingfangshi);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINFAXINGJIAGE, chanpinfaxingjiage);
				linkedHashMap.put(ChanPinXinXiEnum.ZUIDIMUJIGUIMO, zuidimujiguimo);
				linkedHashMap.put(ChanPinXinXiEnum.ZUIGAOMUJIGUIMO, zuigaomujiguimo);
				linkedHashMap.put(ChanPinXinXiEnum.ZUIDIKAIHUSHULIANG, zuidikaihushuliang);
				linkedHashMap.put(ChanPinXinXiEnum.ZUIGAOKAIHUSHULIANG, zuigaokaihushuliang);
				linkedHashMap.put(ChanPinXinXiEnum.HUSHUCHAOXIANCHULIFANGSHI, hushuchaoxianchulifangshi);
				linkedHashMap.put(ChanPinXinXiEnum.MUJIQILIXICHULIFANGSHI, mujiqilixichulifangshi);
				linkedHashMap.put(ChanPinXinXiEnum.MUJIQIJIXIQISHIRIQI, mujiqijixiqishiriqi);
				linkedHashMap.put(ChanPinXinXiEnum.FENECHAOGUIMOCHULIFANGSHI, fenechaoguimochulifangshi);
				linkedHashMap.put(ChanPinXinXiEnum.MUJIQIJIXIJIEZHIRIQI, mujiqijixijiezhiriqi);
				linkedHashMap.put(ChanPinXinXiEnum.CHAOGUIMOMOBICHULIFANGSHI, chaoguimomobichulifangshi);
				linkedHashMap.put(ChanPinXinXiEnum.JIAOYIKAISHISHIJIAN, jiaoyikaishishijian);
				linkedHashMap.put(ChanPinXinXiEnum.JIAOYIBISHISHIJIAN, jiaoyibishishijian);
				linkedHashMap.put(ChanPinXinXiEnum.JUESHUHUIBILI, jueshuhuibili);
				linkedHashMap.put(ChanPinXinXiEnum.MORENFENHONGFANGSHI, morenfenhongfangshi);
				linkedHashMap.put(ChanPinXinXiEnum.ZHANGHUZUIDICHIYOUFENE, zhanghuzuidichiyoufene);
				linkedHashMap.put(ChanPinXinXiEnum.ZHANGHUZUIDICHIYOUJINE, zhanghuzuidichiyoujine);
				linkedHashMap.put(ChanPinXinXiEnum.ZUIDIHONGLIZHUANTOUJINE, zuidihonglizhuantoujine);
				linkedHashMap.put(ChanPinXinXiEnum.ZUIDICHANPINTUOGUANFENSHU, zuidichanpintuoguanfenshu);
				linkedHashMap.put(ChanPinXinXiEnum.ZUIDICHANPINZHUANHUANFENSHU, zuidichanpinzhuanhuanfenshu);
				linkedHashMap.put(ChanPinXinXiEnum.CHANPINZUIDISHUHUIXIANE, chanpinzuidishuhuixiane);
				linkedHashMap.put(ChanPinXinXiEnum.TUOGUANFEIJITIFEILU, tuoguanfeijitifeilu);
				linkedHashMap.put(ChanPinXinXiEnum.GUANLIFEIJITIFEILU, guanlifeijitifeilu);
				linkedHashMap.put(ChanPinXinXiEnum.TOUZIFANGXIANG, touzifangxiang);
				linkedHashMap.put(ChanPinXinXiEnum.SHIFOUBAOBEN, shifoubaoben);
				linkedHashMap.put(ChanPinXinXiEnum.SHIFOULOF, shifoulof);
				linkedHashMap.put(ChanPinXinXiEnum.SHIFOUQDII, shifouqdii);
				linkedHashMap.put(ChanPinXinXiEnum.SHIFOUETF, shifouetf);
				linkedHashMap.put(ChanPinXinXiEnum.GERENSHOUCIRENGOUZUIDIJINE, gerenshoucirengouzuidijine);
				linkedHashMap.put(ChanPinXinXiEnum.GERENSHOUCISHENGOUZUIDIJINE, gerenshoucishengouzuidijine);
				linkedHashMap.put(ChanPinXinXiEnum.GERENDANBISHENGOUZUIGAOJINE, gerendanbishengouzuigaojine);
				linkedHashMap.put(ChanPinXinXiEnum.GERENZUIGAOSHENGOUJINE, gerenzuigaoshengoujine);
				linkedHashMap.put(ChanPinXinXiEnum.GERENZUIGAORENGOUJINE, gerenzuigaorengoujine);
				linkedHashMap.put(ChanPinXinXiEnum.GERENZUIGAOSHENGOUFENE, gerenzuigaoshengoufene);
				linkedHashMap.put(ChanPinXinXiEnum.GERENZUIGAORENGOUFENE, gerenzuigaorengoufene);
				linkedHashMap.put(ChanPinXinXiEnum.GERENSHENGOUJINEDANWEI, gerenshengoujinedanwei);
				linkedHashMap.put(ChanPinXinXiEnum.GERENRENGOUJINEDANWEI, gerenrengoujinedanwei);
				linkedHashMap.put(ChanPinXinXiEnum.GERENZHUIJIARENGOUZUIDIJINE, gerenzhuijiarengouzuidijine);
				linkedHashMap.put(ChanPinXinXiEnum.GERENZHUIJIASHENGOUZUIDIJINE, gerenzhuijiashengouzuidijine);
				linkedHashMap.put(ChanPinXinXiEnum.GERENDANRILEIJIGOUMAIZUIGAOJINE, gerendanrileijigoumaizuigaojine);
				linkedHashMap.put(ChanPinXinXiEnum.GERENDANRILEIJISHUHUIZUIGAOFENE, gerendanrileijishuhuizuigaofene);
				linkedHashMap.put(ChanPinXinXiEnum.GERENZUIGAOSHUHUIFENE, gerenzuigaoshuhuifene);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOUSHOUCIRENGOUZUIDIJINE, jigoushoucirengouzuidijine);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOUSHOUCISHENGOUZUIDIJINE, jigoushoucishengouzuidijine);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOUDANBISHENGOUZUIGAOJINE, jigoudanbishengouzuigaojine);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOUZUIGAOSHENGOUJINE, jigouzuigaoshengoujine);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOUZUIGAORENGOUJINE, jigouzuigaorengoujine);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOUZUIGAOSHENGOUFENE, jigouzuigaoshengoufene);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOUZUIGAORENGOUFENE, jigouzuigaorengoufene);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOUSHENGOUJINEDANWEI, jigoushengoujinedanwei);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOURENGOUJINEDANWEI, jigourengoujinedanwei);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOUZHUIJIARENGOUZUIDIJINE, jigouzhuijiarengouzuidijine);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOUZHUIJIASHENGOUZUIDIJINE, jigouzhuijiashengouzuidijine);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOUDANRILEIJIGOUMAIZUIGAOJINE, jigoudanrileijigoumaizuigaojine);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOUDANRILEIJISHUHUIZUIGAOFENE, jigoudanrileijishuhuizuigaofene);
				linkedHashMap.put(ChanPinXinXiEnum.JIGOUZUIGAOSHUHUIFENE, jigouzuigaoshuhuifene);
				linkedHashMap.put(ChanPinXinXiEnum.DAOQIQIANNRITIXING, daoqiqiannritixing);
				linkedHashMap.put(ChanPinXinXiEnum.ZHUANHUCHANPINSHUXING, zhuanhuchanpinshuxing);
				linkedHashMap.put(ChanPinXinXiEnum.FENGBIQIQINGSUANPINLU, fengbiqiqingsuanpinlu);
				linkedHashMap.put(ChanPinXinXiEnum.WEIYUESHUHUISHIHOUSHOUQUSHUHUIFEI, weiyueshuhuishihoushouqushuhuifei);
				linkedHashMap.put(ChanPinXinXiEnum.SHOUYIJISUANNIANTIAN1, shouyijisuanniantian1);
				linkedHashMap.put(ChanPinXinXiEnum.YEJITICHENGWEICHASHULI, yejitichengweichashuli);
				linkedHashMap.put(ChanPinXinXiEnum.SHENCHABEIANRIQI, shenchabeianriqi);
				linkedHashMap.put(ChanPinXinXiEnum.HETONGSHENGXIAORIQI, hetongshengxiaoriqi);
				linkedHashMap.put(ChanPinXinXiEnum.HETONGZHONGZHIRIQI, hetongzhongzhiriqi);
				linkedHashMap.put(ChanPinXinXiEnum.WEIYUEJINJISUANGONGSHI1, weiyuejinjisuangongshi1);
				linkedHashMap.put(ChanPinXinXiEnum.SHOUYIJISUANNIANTIAN2, shouyijisuanniantian2);
				linkedHashMap.put(ChanPinXinXiEnum.WEIYUEJINJISUANGONGSHI2, weiyuejinjisuangongshi2);
				linkedHashMap.put(ChanPinXinXiEnum.SHIJIANLEIJINJISUAN, shijianleijinjisuan);
				linkedHashMap.put(ChanPinXinXiEnum.WEIYUETIANSHU, weiyuetianshu);
				linkedHashMap.put(ChanPinXinXiEnum.SHOUYIJISUANNIANTIAN3, shouyijisuanniantian3);
				linkedHashMap.put(ChanPinXinXiEnum.ISCHECKED, ischecked);
				// 放入全局变量dataForChanPinXinXiFromExcel中
				dataForChanPinXinXiFromExcel.add(linkedHashMap);
			}
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * readForChanPinFeiLv-产品费率
	 * @return
	 */
	public boolean readForChanPinFeiLv() {
		Common.logInfo("readForChanPinFeiLv");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得ChanPinFeiLv工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("chanpinfeilv".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the chanpinfeilv sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			
			for (int r = 2; r < rows; r++) {
				LinkedHashMap<ChanPinFeiLvEnum, String> linkedHashMap = new LinkedHashMap<ChanPinFeiLvEnum, String>();
				// 取出第一行数据的所有数据
				String chanpindaima = sheet.getCell(1, r).getContents();
				String feilvmingximingcheng = sheet.getCell(2, r).getContents();
				String yewudaima = sheet.getCell(3, r).getContents();
				String shouxufeilv = sheet.getCell(4, r).getContents();
				String gerenjigoubiaozhi = sheet.getCell(5, r).getContents();
				String shijiandanwei = sheet.getCell(6, r).getContents();
				String jinexiaxian = sheet.getCell(7, r).getContents();
				String jineshangxian = sheet.getCell(8, r).getContents();
				String chiyoushijianxiaxian = sheet.getCell(9, r).getContents();
				String chiyoushijianshangxian = sheet.getCell(10, r).getContents();
				String zuidishoufei = sheet.getCell(11, r).getContents();
				String zuigaoshoufei = sheet.getCell(12, r).getContents();
				String shouxufeiguizichanbili = sheet.getCell(13, r).getContents();
				String ischecked = sheet.getCell(14, r).getContents();
				
				linkedHashMap.put(ChanPinFeiLvEnum.CHANPINDAIMA, chanpindaima);
				linkedHashMap.put(ChanPinFeiLvEnum.FEILVMINGXIMINGCHENG, feilvmingximingcheng);
				linkedHashMap.put(ChanPinFeiLvEnum.YEWUDAIMA, yewudaima);
				linkedHashMap.put(ChanPinFeiLvEnum.SHOUXUFEILV, shouxufeilv);
				linkedHashMap.put(ChanPinFeiLvEnum.GERENJIGOUBIAOZHI, gerenjigoubiaozhi);
				linkedHashMap.put(ChanPinFeiLvEnum.SHIJIANDANWEI, shijiandanwei);
				linkedHashMap.put(ChanPinFeiLvEnum.JINEXIAXIAN, jinexiaxian);
				linkedHashMap.put(ChanPinFeiLvEnum.JINESHANGXIAN, jineshangxian);
				linkedHashMap.put(ChanPinFeiLvEnum.CHIYOUSHIJIANXIAXIAN, chiyoushijianxiaxian);
				linkedHashMap.put(ChanPinFeiLvEnum.CHIYOUSHIJIANSHANGXIAN, chiyoushijianshangxian);
				linkedHashMap.put(ChanPinFeiLvEnum.ZUIDISHOUFEI, zuidishoufei);
				linkedHashMap.put(ChanPinFeiLvEnum.ZUIGAOSHOUFEI, zuigaoshoufei);
				linkedHashMap.put(ChanPinFeiLvEnum.SHOUXUFEIGUIZICHANBILI, shouxufeiguizichanbili);
				linkedHashMap.put(ChanPinFeiLvEnum.ISCHECKED, ischecked);
				// 放入全局变量dataForChanPinFeiLvFromExcel中
				dataForChanPinFeiLvFromExcel.add(linkedHashMap);
			}
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * readForChanPinXiaoShouDaiLiGuanXi-产品销售代理关系
	 * @return
	 */
	public boolean readForChanPinXiaoShouDaiLiGuanXi() {
		Common.logInfo("readForChanPinXiaoShouDaiLiGuanXi");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得ChanPinFeiLv工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("chanpinxiaoshoudailiguanxi".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the chanpinxiaoshoudailiguanxi sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			
			for (int r = 2; r < rows; r++) {
				LinkedHashMap<ChanPinXiaoShouDaiLiGuanXiEnum, String> linkedHashMap = new LinkedHashMap<ChanPinXiaoShouDaiLiGuanXiEnum, String>();
				// 取出第一行数据的所有数据
				String xiaoshoujigoudaima = sheet.getCell(1, r).getContents();
				String chanpindaima = sheet.getCell(2, r).getContents();
				String ischecked = sheet.getCell(3, r).getContents();
				
				
				linkedHashMap.put(ChanPinXiaoShouDaiLiGuanXiEnum.XIAOSHOUJIGOUDAIMA, xiaoshoujigoudaima);
				linkedHashMap.put(ChanPinXiaoShouDaiLiGuanXiEnum.CHANPINDAIMA, chanpindaima);
				linkedHashMap.put(ChanPinXiaoShouDaiLiGuanXiEnum.ISCHECKED, ischecked);
				// 放入全局变量dataForChanPinFeiLvFromExcel中
				dataForChanPinXiaoShouDaiLiGuanXiFromExcel.add(linkedHashMap);
			}
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * readForFeiYongFenCheng-费用分成
	 * @return
	 */
	public boolean readForFeiYongFenCheng() {
		Common.logInfo("readForFeiYongFenCheng");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得ChanPinFeiLv工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("feiyongfencheng".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the feiyongfencheng sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			
			for (int r = 2; r < rows; r++) {
				LinkedHashMap<FeiYongFenChengEnum, String> linkedHashMap = new LinkedHashMap<FeiYongFenChengEnum, String>();
				// 取出第一行数据的所有数据
				String fenchengfanganmingcheng = sheet.getCell(1, r).getContents();
				String fenchengfanganhao = sheet.getCell(2, r).getContents();
				String jigoudaima = sheet.getCell(3, r).getContents();
				String chanpindaima = sheet.getCell(4, r).getContents();
				String yewuleixing = sheet.getCell(5, r).getContents();
				String fenchengbili = sheet.getCell(6, r).getContents();
				String jinexiaxian = sheet.getCell(7, r).getContents();
				String jineshangxian = sheet.getCell(8, r).getContents();
				String youxiaokaishishijian = sheet.getCell(9, r).getContents();
				String youxiaojieshushijian = sheet.getCell(10, r).getContents();
				String ischecked = sheet.getCell(11, r).getContents();
				
				linkedHashMap.put(FeiYongFenChengEnum.FENCHENGFANGANMINGCHENG, fenchengfanganmingcheng);
				linkedHashMap.put(FeiYongFenChengEnum.FENCHENGFANGANHAO, fenchengfanganhao);
				linkedHashMap.put(FeiYongFenChengEnum.JIGOUDAIMA, jigoudaima);
				linkedHashMap.put(FeiYongFenChengEnum.CHANPINDAIMA, chanpindaima);
				linkedHashMap.put(FeiYongFenChengEnum.YEWULEIXING, yewuleixing);
				linkedHashMap.put(FeiYongFenChengEnum.FENCHENGBILI, fenchengbili);
				linkedHashMap.put(FeiYongFenChengEnum.JINEXIAXIAN, jinexiaxian);
				linkedHashMap.put(FeiYongFenChengEnum.JINESHANGXIAN, jineshangxian);
				linkedHashMap.put(FeiYongFenChengEnum.YOUXIAOKAISHISHIJIAN, youxiaokaishishijian);
				linkedHashMap.put(FeiYongFenChengEnum.YOUXIAOJIESHUSHIJIAN, youxiaojieshushijian);
				linkedHashMap.put(FeiYongFenChengEnum.ISCHECKED, ischecked);
				// 放入全局变量dataForFeiYongFenChengFromExcel中
				dataForFeiYongFenChengFromExcel.add(linkedHashMap);
			}
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * readForZheKouGuanLi-折扣管理
	 * @return
	 */
	public boolean readForZheKouGuanLi() {
		Common.logInfo("readForZheKouGuanLi");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得ChanPinFeiLv工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("zhekouguanli".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the zhekouguanli sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			
			for (int r = 2; r < rows; r++) {
				LinkedHashMap<ZheKouGuanLiEnum, String> linkedHashMap = new LinkedHashMap<ZheKouGuanLiEnum, String>();
				// 取出第一行数据的所有数据
				String zhekoufanganmingcheng = sheet.getCell(1, r).getContents();
				String zhekoufanganhao = sheet.getCell(2, r).getContents();
				String jigoudaima = sheet.getCell(3, r).getContents();
				String chanpindaima = sheet.getCell(4, r).getContents();
				String yewuleixingdaima = sheet.getCell(5, r).getContents();
				String zhekoufangshi = sheet.getCell(6, r).getContents();
				String zuidifeilv = sheet.getCell(7, r).getContents();
				String zhekou = sheet.getCell(8, r).getContents();
				String jinexiaxian = sheet.getCell(9, r).getContents();
				String jineshangxian = sheet.getCell(10, r).getContents();
				String youxiaokaishishijian = sheet.getCell(11, r).getContents();
				String youxiaojieshushijian = sheet.getCell(12, r).getContents();
				String ischecked = sheet.getCell(13, r).getContents();
				
				linkedHashMap.put(ZheKouGuanLiEnum.ZHEKOUFANGANMINGCHENG, zhekoufanganmingcheng);
				linkedHashMap.put(ZheKouGuanLiEnum.ZHEKOUFANGANHAO, zhekoufanganhao);
				linkedHashMap.put(ZheKouGuanLiEnum.JIGOUDAIMA, jigoudaima);
				linkedHashMap.put(ZheKouGuanLiEnum.CHANPINDAIMA, chanpindaima);
				linkedHashMap.put(ZheKouGuanLiEnum.YEWULEIXINGDAIMA, yewuleixingdaima);
				linkedHashMap.put(ZheKouGuanLiEnum.ZHEKOUFANGSHI, zhekoufangshi);
				linkedHashMap.put(ZheKouGuanLiEnum.ZUIDIFEILV, zuidifeilv);
				linkedHashMap.put(ZheKouGuanLiEnum.ZHEKOU, zhekou);
				linkedHashMap.put(ZheKouGuanLiEnum.JINEXIAXIAN, jinexiaxian);
				linkedHashMap.put(ZheKouGuanLiEnum.JINESHANGXIAN, jineshangxian);
				linkedHashMap.put(ZheKouGuanLiEnum.YOUXIAOKAISHISHIJIAN, youxiaokaishishijian);
				linkedHashMap.put(ZheKouGuanLiEnum.YOUXIAOJIESHUSHIJIAN, youxiaojieshushijian);
				linkedHashMap.put(ZheKouGuanLiEnum.ISCHECKED, ischecked);
				// 放入全局变量dataForZheKouGuanLiFromExcel中
				dataForZheKouGuanLiFromExcel.add(linkedHashMap);
			}
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * readForYongHuZhiXingRenGuanXi-用户执行人关系
	 * @return
	 */
	private boolean readForYongHuZhiXingRenGuanXi() {
		Common.logInfo("readForYongHuZhiXingRenGuanXi");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得ChanPinFeiLv工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("yonghuzhixingrenguanxi".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the yonghuzhixingrenguanxi sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			
			for (int r = 2; r < rows; r++) {
				LinkedHashMap<YongHuZhiXingRenGuanXiEnum, String> linkedHashMap = new LinkedHashMap<YongHuZhiXingRenGuanXiEnum, String>();
				// 取出第一行数据的所有数据
				String dengruyonghuming = sheet.getCell(1, r).getContents();
				String zhixingrendaima = sheet.getCell(2, r).getContents();
				String ischecked = sheet.getCell(3, r).getContents();
				
				linkedHashMap.put(YongHuZhiXingRenGuanXiEnum.DENGRUYONGHUMING, dengruyonghuming);
				linkedHashMap.put(YongHuZhiXingRenGuanXiEnum.ZHIXINGRENDAIMA, zhixingrendaima);
				linkedHashMap.put(YongHuZhiXingRenGuanXiEnum.ISCHECKED, ischecked);
				// 放入全局变量dataForZheKouGuanLiFromExcel中
				dataForYongHuZhiXingRenGuanXiFromExcel.add(linkedHashMap);
			}
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
		
	}
	
	/**
	 * readForChanPinQingSuanZhouQi-产品清算周期
	 * @return
	 */
	public boolean readForChanPinQingSuanZhouQi() {
		Common.logInfo("readForChanPinQingSuanZhouQi");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得ChanPinFeiLv工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("chanpinqingsuanzhouqi".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the chanpinqingsuanzhouqi sheet not exist!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			
			for (int r = 2; r < rows; r++) {
				LinkedHashMap<ChanPinQingSuanZhouQiEnum, String> linkedHashMap = new LinkedHashMap<ChanPinQingSuanZhouQiEnum, String>();
				// 取出第一行数据的所有数据
				String chanpindaima = sheet.getCell(1, r).getContents();
				String chanpinqingsuanzhouqi = sheet.getCell(2, r).getContents();
				String miaoshu = sheet.getCell(3, r).getContents();
				String ischecked = sheet.getCell(4, r).getContents();
				
				linkedHashMap.put(ChanPinQingSuanZhouQiEnum.CHANPINDAIMA, chanpindaima);
				linkedHashMap.put(ChanPinQingSuanZhouQiEnum.CHANPINQINGSUANZHOUQI, chanpinqingsuanzhouqi);
				linkedHashMap.put(ChanPinQingSuanZhouQiEnum.MIAOSHU, miaoshu);
				linkedHashMap.put(ChanPinQingSuanZhouQiEnum.ISCHECKED, ischecked);
				// 放入全局变量dataForZheKouGuanLiFromExcel中
				dataForChanPinQingSuanZhouQiFromExcel.add(linkedHashMap);
			}
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 *readForChanPinDongTai-产品动态    原佩宏
	 * @return	
	 */
	private boolean readForChanPinDongTai() {
		Common.logInfo("readFoChanPinDongTai");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得ChanPinFeiLv工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("chanpindongtai".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the chanpindongtai sheet not exist!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			
			for (int r = 2; r < rows; r++) {
				LinkedHashMap<ChanPinDongTaiEnum, String> linkedHashMap = new LinkedHashMap<ChanPinDongTaiEnum, String>();
				// 取出第一行数据的所有数据
				String chanpindaima = sheet.getCell(1, r).getContents();
				String chanpinzhuangtai = sheet.getCell(2, r).getContents();
				String biandongriqi = sheet.getCell(3, r).getContents();
				String ischecked = sheet.getCell(4, r).getContents();
				
				linkedHashMap.put(ChanPinDongTaiEnum.CHANPINDAIMA, chanpindaima);
				linkedHashMap.put(ChanPinDongTaiEnum.CHANPINZHUANGTAI, chanpinzhuangtai);
				linkedHashMap.put(ChanPinDongTaiEnum.BIANDONGRIQI, biandongriqi);
				linkedHashMap.put(ChanPinDongTaiEnum.ISCHECKED, ischecked);
				// 放入全局变量dataFoChanPinDongTaiFromExcel中
				dataForChanPinDongTaiFromExcel.add(linkedHashMap);
			}
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
		
	}	
		
		/**
		 * readForGuDingShouYiLiLv-固定收益利率
		 * @return
		 */
		public boolean readForGuDingShouYiLiLv() {
			Common.logInfo("readForGuDingShouYiLiLv");
			
			try {
				Workbook book = Workbook.getWorkbook(new File(
						"./testcase/TestCase.xls"));
				// 获得ChanPinFeiLv工作表对象
				Sheet[] sheets = book.getSheets();
				Sheet sheet = null;
				for (Sheet s : sheets) {
					if ("gudingshouyililv".equals(s.getName())) {
						sheet = s;
					}
				}
				if (sheet == null) {
					Common.logError("ReadForLogPage error,the gudingshouyililv sheet not exist!");
					return false;
				}
				// 获取sheet的所有行数
				int rows = sheet.getRows();
				
				for (int r = 2; r < rows; r++) {
					LinkedHashMap<GuDingShouYiLiLvEnum, String> linkedHashMap = new LinkedHashMap<GuDingShouYiLiLvEnum, String>();
					// 取出第一行数据的所有数据
					String xiaoshoujigou = sheet.getCell(1, r).getContents();
					String chanpindaima = sheet.getCell(2, r).getContents();
					String gushoumingxiliushuihao = sheet.getCell(3, r).getContents();
					String nianhuashouyilv = sheet.getCell(4, r).getContents();
					String jinexiaxian = sheet.getCell(5, r).getContents();
					String jineshangxian = sheet.getCell(6, r).getContents();
					String chiyoushijianxiaxian = sheet.getCell(7, r).getContents();
					String chiyoushijianshangxian = sheet.getCell(8, r).getContents();
					String zuidishouyi = sheet.getCell(9, r).getContents();
					String zuigaoshouyi = sheet.getCell(10, r).getContents();
					String qishu = sheet.getCell(13, r).getContents();
					String kaifangqishiriqi = sheet.getCell(11, r).getContents();
					String kaifangjiezhiriqi = sheet.getCell(12, r).getContents();
					String lixijiesuanriqi = sheet.getCell(14, r).getContents();
					String ischecked = sheet.getCell(15, r).getContents();
					
					linkedHashMap.put(GuDingShouYiLiLvEnum.XIAOSHOUJIGOU, xiaoshoujigou);
					linkedHashMap.put(GuDingShouYiLiLvEnum.CHANPINDAIMA, chanpindaima);
					linkedHashMap.put(GuDingShouYiLiLvEnum.GUSHOUMINGXILIUSHUIHAO, gushoumingxiliushuihao);
					linkedHashMap.put(GuDingShouYiLiLvEnum.NIANHUASHOUYILV, nianhuashouyilv);
					linkedHashMap.put(GuDingShouYiLiLvEnum.JINEXIAXIAN, jinexiaxian);
					linkedHashMap.put(GuDingShouYiLiLvEnum.JINESHANGXIAN, jineshangxian);
					linkedHashMap.put(GuDingShouYiLiLvEnum.CHIYOUSHIJIANXIAXIAN, chiyoushijianxiaxian);
					linkedHashMap.put(GuDingShouYiLiLvEnum.CHIYOUSHIJIANSHANGXIAN, chiyoushijianshangxian);
					linkedHashMap.put(GuDingShouYiLiLvEnum.ZUIDISHOUYI, zuidishouyi);
					linkedHashMap.put(GuDingShouYiLiLvEnum.ZUIGAOSHOUYI, zuigaoshouyi);
					linkedHashMap.put(GuDingShouYiLiLvEnum.QISHU, qishu);
					linkedHashMap.put(GuDingShouYiLiLvEnum.KAIFANGQISHIRIQI, kaifangqishiriqi);
					linkedHashMap.put(GuDingShouYiLiLvEnum.KAIFANGJIEZHIRIQI, kaifangjiezhiriqi);				
					linkedHashMap.put(GuDingShouYiLiLvEnum.LIXIJIESUANRIQI, lixijiesuanriqi);
					linkedHashMap.put(GuDingShouYiLiLvEnum.ISCHECKED, ischecked);
					// 放入全局变量dataForZheKouGuanLiFromExcel中
					dataForGuDingShouYiLiLvFromExcel.add(linkedHashMap);
				}
			} catch (Exception e) {
				Common.logError(e.getMessage());
				return false;
			}
			return true;
		}
			
	/**
	 * readForZhiXingQuanXianRenXinXi-执行权限人信息
	 * @return
	 */
	public boolean readForZhiXingQuanXianRenXinXi() {
		Common.logInfo("readForZhiXingQuanXianRenXinXi");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得执行权限设置工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("zhixingquanxianrenxinxi".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the ZhangHuLeiCanShu sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			
			for (int r = 2; r < rows; r++) {
				LinkedHashMap<ZhiXingQuanXianRenXinXiEnum, String> linkedHashMap = new LinkedHashMap<ZhiXingQuanXianRenXinXiEnum, String>();
				// 取出第一行数据的所有数据
				String zhixingquanxianrendaima = sheet.getCell(1, r).getContents();
				String zhixingquanxianrenmingcheng = sheet.getCell(2, r).getContents();
				String zhixingquanxianrenmiaoshu = sheet.getCell(3, r).getContents();
				String ischecked = sheet.getCell(4, r).getContents();
				
				linkedHashMap.put(ZhiXingQuanXianRenXinXiEnum.ZHIXINGQUANXIANRENDAIMA, zhixingquanxianrendaima);
				linkedHashMap.put(ZhiXingQuanXianRenXinXiEnum.ZHIXINGQUANXIANRENMINGCHENG, zhixingquanxianrenmingcheng);
				linkedHashMap.put(ZhiXingQuanXianRenXinXiEnum.ZHIXINGQUANXIANRENMIAOSHU, zhixingquanxianrenmiaoshu);
				linkedHashMap.put(ZhiXingQuanXianRenXinXiEnum.ISCHECKED, ischecked);
				// 放入全局变量dataForZhangHuLeiCanShuFromExcel中
				dataForZhiXingQuanXianRenXinXiFromExcel.add(linkedHashMap);
			}
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * readForYinHangJiBenXinXi-银行基本信息
	 * @return
	 */
	public boolean readForYinHangJiBenXinXi() {
		Common.logInfo("readForYinHangJiBenXinXi");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得执行权限设置工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("yinhangjibenxinxi".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the yinhangjibenxinxi sheet not exist!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			
			for (int r = 2; r < rows; r++) {
				LinkedHashMap<YinHangJiBenXinXiEnum, String> linkedHashMap = new LinkedHashMap<YinHangJiBenXinXiEnum, String>();
				// 取出第一行数据的所有数据
				String yinhangdaima = sheet.getCell(1, r).getContents();
				String yinhangmingcheng	 = sheet.getCell(2, r).getContents();
				String fuwurexian = sheet.getCell(3, r).getContents();
				String lianxidianhua = sheet.getCell(4, r).getContents();
				String guanwangwangzhi = sheet.getCell(5, r).getContents();
				String xiangxidizhi = sheet.getCell(6, r).getContents();
				String ischecked = sheet.getCell(7, r).getContents();
				
				linkedHashMap.put(YinHangJiBenXinXiEnum.YINHANGDAIMA, yinhangdaima);
				linkedHashMap.put(YinHangJiBenXinXiEnum.YINHANGMINGCHENG, yinhangmingcheng);
				linkedHashMap.put(YinHangJiBenXinXiEnum.FUWUREXIAN, fuwurexian);
				linkedHashMap.put(YinHangJiBenXinXiEnum.LIANXIDIANHUA, lianxidianhua);
				linkedHashMap.put(YinHangJiBenXinXiEnum.GUANWANGWANGZHI, guanwangwangzhi);
				linkedHashMap.put(YinHangJiBenXinXiEnum.XIANGXIDIZHI, xiangxidizhi);
				linkedHashMap.put(YinHangJiBenXinXiEnum.ISCHECKED, ischecked);
				// 放入全局变量dataForYinHangJiBenXinXiFromExcel中
				dataForYinHangJiBenXinXiFromExcel.add(linkedHashMap);
			}
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * readForZhangHuLeiCanShu-账户类参数
	 * @return
	 */
	private boolean readForZhangHuLeiCanShu() {


		Common.logInfo("readForZhangHuLeiCanShu");
		
		try {
			Workbook book = Workbook.getWorkbook(new File(
					"./testcase/TestCase.xls"));
			// 获得执行权限设置工作表对象
			Sheet[] sheets = book.getSheets();
			Sheet sheet = null;
			for (Sheet s : sheets) {
				if ("zhanghuleicanshu".equals(s.getName())) {
					sheet = s;
				}
			}
			if (sheet == null) {
				Common.logError("ReadForLogPage error,the ZhangHuLeiCanShu sheet not exit!");
				return false;
			}
			// 获取sheet的所有行数
			int rows = sheet.getRows();
			
			for (int r = 2; r < rows; r++) {
				LinkedHashMap<ZhangHuLeiCanShuEnum, String> linkedHashMap = new LinkedHashMap<ZhangHuLeiCanShuEnum, String>();
				// 取出第一行数据的所有数据
				String jigoudaima = sheet.getCell(1, r).getContents();
				String jijinzhanghaoshengchengguize = sheet.getCell(2, r).getContents();
				String zhanghudongjiequanyichulifangshi = sheet.getCell(3, r).getContents();
				String tongxiaoshou = sheet.getCell(4, r).getContents();
				String ischecked = sheet.getCell(5, r).getContents();
				
				linkedHashMap.put(ZhangHuLeiCanShuEnum.JIGOUDAIMA, jigoudaima);
				linkedHashMap.put(ZhangHuLeiCanShuEnum.JIJINZHANGHAOSHENGCHENGGUIZE, jijinzhanghaoshengchengguize);
				linkedHashMap.put(ZhangHuLeiCanShuEnum.ZHANGHUDONGJIEQUANYICHULIFANGSHI, zhanghudongjiequanyichulifangshi);
				linkedHashMap.put(ZhangHuLeiCanShuEnum.TONGXIAOSHOU, tongxiaoshou);
				linkedHashMap.put(ZhangHuLeiCanShuEnum.ISCHECKED, ischecked);
				// 放入全局变量dataForZhangHuLeiCanShuFromExcel中
				dataForZhangHuLeiCanShuFromExcel.add(linkedHashMap);
			}
		} catch (Exception e) {
			Common.logError(e.getMessage());
			return false;
		}
		return true;
		
	}
}
