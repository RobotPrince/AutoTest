package com.yss.common;

// 读取Excel的类 
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;


/**
 * Read WebElements from Excel 
 * @author tanglonglong
 */
public class ReadFromExcel {

	final static String ELEMENT_NAME = "element_name";
	final static String TYPE = "type";
	final static String VALUE = "value";
	final static String REMARK = "remark";
	
	public static HashMap<PageEnum, HashMap<String, List<String>>> hashMapOfExcel = new HashMap<PageEnum, HashMap<String, List<String>>>();

	/**
	 * HashMap(PageEnum, HashMap(String, List(String))) hashMapOfExcel
	 * @author tanglonglong
	 */
	@SuppressWarnings("unused")
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
			PageEnum pageEnum = PageEnum.valueOf(sheet.getCell(0, 1).getContents().toUpperCase());
			HashMap<String,List<String>> hashMapOfPage = new HashMap<String,List<String>>();
			//循环所有行
			for(int i = 1; i < rows; i++){
				
				String PageName = sheet.getCell(0, i).getContents();
				PageEnum pageEnumTemp= PageEnum.valueOf(PageName.toUpperCase());
				
				//判断该元素与上一元素是否属于同一画面
				if(!pageEnumTemp.equals(pageEnum)){
					hashMapOfPage = new HashMap<String,List<String>>();
					pageEnum = pageEnumTemp;
				}
					String elementName = null;
					String type = null;
					String value = null;
					String remark = null;
					//取出該元素的的element_name,type,value,remark
					for(int j = 1; j < columns; j++){
						
						switch(sheet.getCell(j, 0).getContents()){
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
					//將取出的值放入Map中
					ArrayList<String> arrayList = new ArrayList<String>();
					arrayList.add(type);
					arrayList.add(value);
					arrayList.add(remark);
					hashMapOfPage.put(elementName, arrayList);
					hashMapOfExcel.put(pageEnumTemp, hashMapOfPage);
//					//判断该元素是否是最后一个元素
//					if(!pageEnumTemp.equals(pageEnum)|| i == rows-1){
//						hashMapOfExcel.put(pageEnumTemp, hashMapOfPage);
//					}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}
	public boolean readForTestData(){
		Common.logInfo("readForTestData");
		
		
		return true;
	}
}