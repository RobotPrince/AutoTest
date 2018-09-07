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

import com.yss.method.HeSuanJiGouXinXi.HeSuanJiGouXinXiEnum;
import com.yss.method.Login.LoginEnum;
import com.yss.method.RiChangYunYingQingSuan.RiChangYunYingQingSuanEnum;
import com.yss.method.XiaoShouJiGouXinXi.XiaoShouJiGouXinXiEnum;

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
	public static List<HashMap<HeSuanJiGouXinXiEnum, String>> dataForHeSuanJiGouFromExcel = new ArrayList<HashMap<HeSuanJiGouXinXiEnum, String>>();
	public static List<HashMap<XiaoShouJiGouXinXiEnum, String>> dataForXiaoShouJIGouXinXiFromExcel = new ArrayList<HashMap<XiaoShouJiGouXinXiEnum, String>>();
	public static List<HashMap<RiChangYunYingQingSuanEnum, String>> dataForRiChangYunYingFromExcel = new ArrayList<HashMap<RiChangYunYingQingSuanEnum, String>>();

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
		
		readForWebElements();
		readForHeSuanJiGouXinXi();
		readForLoginPage();
		readForRiChangYunYingQingSuan();
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
						break;
					case TYPE:
						type = sheet.getCell(j, i).getContents();
						break;
					case VALUE:
						value = sheet.getCell(j, i).getContents();
						break;
					case REMARK:
						remark = sheet.getCell(j, i).getContents();
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
}
