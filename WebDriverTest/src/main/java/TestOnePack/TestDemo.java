package TestOnePack;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yss.common.Common;
import com.yss.common.DBConnect;
import com.yss.common.MyResponse;



public class TestDemo {


	public static void main(String args[]) throws IOException
	{
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		File dir = new File("E://T_TA_ACKTRADEBLOTTER/");
		//获取文件夹下所有文件名称
		String[] fileList = dir.list();
		long[] fileTimeList = new long[fileList.length];
		for(int i = 0; i< fileList.length; i++){
			try {
				//将文件名称中的时间转换成long类型
				fileTimeList[i] = sdf.parse(fileList[i].split("-")[1]).getTime();
			} catch (ParseException e) {
				System.out.println("文件格式不正确"+fileTimeList[i]);
			}
		}
		//按照文件名称的时间排序
		Arrays.sort(fileTimeList);
		// 根据long类型的毫秒数生命一个date类型的时间
		Date dateNew = new Date(fileTimeList[fileList.length-1]);
		Date dateOld = new Date(fileTimeList[fileList.length-2]);
		// 把date类型的时间转换为string
		String oldTime = sdf.format(dateOld);
		String newTime = sdf.format(dateNew);
		
		File oldFile = new File("E://T_TA_ACKTRADEBLOTTER/T_TA_ACKTRADEBLOTTER-"+oldTime+".json");
		File newFile = new File("E://T_TA_ACKTRADEBLOTTER/T_TA_ACKTRADEBLOTTER-"+newTime+".json");
		try {
			char[] charOld = new char[1024*1024*5];
			char[] charNew = new char[1024*1024*5];
			FileReader fileReaderOld = new FileReader(oldFile);
			FileReader fileReaderNew = new FileReader(newFile);
			//读入到String中
			int oldLength = fileReaderOld.read(charOld);
			int newLength = fileReaderNew.read(charNew);
			
			if(new String(charOld).equals(new String(charNew))){
				
				System.out.println("一模一样");
			}
			String oldString = new String(charOld,0,oldLength);
			String newString = new String(charNew,0,newLength);
			//按照换行分割
			String oldStringArray[] = oldString.split("\n");
			String newStringArray[] = newString.split("\n");
			//判断交易笔数
			if(oldStringArray.length != newStringArray.length){
				
				System.out.println("交易总笔数不同!");
			}
			//获取最大的交易笔数
			int stringLength = oldStringArray.length >= newStringArray.length ?oldStringArray.length : newStringArray.length;
			for(int i = 0; i < stringLength; i++){
				int stringSplitLength = 0;
				//按照,分割表中字段
				//判断交易的总笔数
				if(newStringArray.length==i){
					System.out.println("新交易清算中没有第"+(i+1)+"笔交易!");
				}
				//判断交易的总笔数
				if(oldStringArray.length==i){
					System.out.println("旧交易清算中没有第"+(i+1)+"笔交易!");
				}
				String[] newStringSplitArray = newStringArray[i].split(",");
				String[] oldStringSplitArray = oldStringArray[i].split(",");
				//判断表中的字段数目
				if(newStringSplitArray.length != oldStringSplitArray.length ){
					System.out.println("第"+(i+1)+"笔交易发生错误，缺啥!");
				}
				//获取最大的字段数目
				stringSplitLength = newStringSplitArray.length <= oldStringSplitArray.length ? newStringSplitArray.length : oldStringSplitArray.length;
				//判断字段是否全部相同
				for(int j = 0; j < stringSplitLength; j++){
					
					if( !newStringSplitArray[j].equals(oldStringSplitArray[j]) ){
						System.out.println("第"+(i+1)+"笔交易的字段"+newStringSplitArray[j]+"发生了错误");
						}
					}
				}

		} catch (Exception e) {
//			Common.logError("获取文件失败"+oldFile.getName()+" or "+newFile.getName());
		}
	}
//		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
//		File dir = new File("E://T_TA_ACKTRADEBLOTTER/");
//		//获取文件夹下所有文件名称
//		String[] fileList = dir.list();
//		long[] fileTimeList = new long[fileList.length];
//		for(int i = 0; i< fileList.length; i++){
//			try {
//				//将文件名称中的时间转换成long类型
//				fileTimeList[i] = sdf.parse(fileList[i].split("-|\\.")[1]).getTime();
//			} catch (ParseException e) {
////				Common.logError("文件格式不正确"+fileTimeList[i]);
//			}
//		}
//		//按照文件名称的时间排序
//		Arrays.sort(fileTimeList);
//		// 根据long类型的毫秒数生命一个date类型的时间
//		Date dateNew = new Date(fileTimeList[fileList.length-1]);
//		Date dateOld = new Date(fileTimeList[fileList.length-2]);
//		// 把date类型的时间转换为string
//		String oldTime = sdf.format(dateOld);
//		String newTime = sdf.format(dateNew);
//		//获取最新的两个文件
//		File oldFile = new File("E://T_TA_ACKTRADEBLOTTER/T_TA_ACKTRADEBLOTTER-"+oldTime+".json");
//		File newFile = new File("E://T_TA_ACKTRADEBLOTTER/T_TA_ACKTRADEBLOTTER-"+newTime+".json");
//		try {
//			char[] charOld = new char[1024*1024*5];
//			char[] charNew = new char[1024*1024*5];
//			FileReader fileReaderOld = new FileReader(oldFile);
//			FileReader fileReaderNew = new FileReader(newFile);
//			//读入到String中
//			int oldLength = fileReaderOld.read(charOld);
//			int newLength = fileReaderNew.read(charNew);
//			String oldString = new String(charOld,0,oldLength);
//			String newString = new String(charNew,0,newLength);
//			
//			String oldStringArray[] = oldString.split("\n");
//			String newStringArray[] = newString.split("\n");
//			if(oldStringArray.length != newStringArray.length){
//				
//			}
//			for(int i = 0; i < oldStringArray)
//			if(new String(charOld).equals(new String(charNew))){;
//			System.out.println("一模一样");
//			}
//			
//		} catch (Exception e) {
////			Common.logError("获取文件失败"+oldFile.getName()+" or "+newFile.getName());
//		}}
		
//		//开始一个事务
//		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
//		Session openSession = sessionFactory.openSession();
//		openSession.beginTransaction();
//		
//		Db_t_ta_acktradeblotter db_t_ta_acktradeblotter = new Db_t_ta_acktradeblotter();
////		db_t_ta_acktradeblotter.setFackno("aaaaaa");
//		String a = db_t_ta_acktradeblotter.getFackno();
//		openSession.save(db_t_ta_acktradeblotter);
//		sessionFactory.close();
//		System.out.println(a);
		
		
	
	//問題在第一個參數,应是enumTest，enumTest2的上层父类
	public List<String> getElementData(Class<Enum> class1,String elementString) {

		List<String> list = new ArrayList<String>();
		try{
			//这一行需要用到两种类型枚举
//			class1.valueOf(, elementString);
			Enum[] enumConstants = class1.getEnumConstants();
			enumConstants[0].toString();
		}
		catch(Exception e){
			Common.logError("ElementName not in excel");
			return null;
		}

		return list;
	}

}

interface test{
	
	enum enumTest implements test{
		A;
//		@Override
//		public toString(){
			
//		}
	}
	
	enum enumTest2 implements test{
		B
	}
}

enum enumTest3 implements test{
	C
}

enum AllEnum{
	ENUMTEST(test.enumTest.class),
	EUNMTEST2(test.enumTest2.class),
	ENUMTEST3(enumTest3.class);
	private test[] t;
	private AllEnum( Class<? extends test> e){
		 t=e.getEnumConstants();
	}
	public test[] getValue(){
		return t;
	}
	public String[] valueToString(){
		
		String[] str = new String[t.length];
		
		for(int i = 0;i < str.length; i++){
			str[i] = t[i].toString();
		}
		return str;
	}
}




