package com.yss.db;

import java.io.File;
import java.io.FileNotFoundException;
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

public class t_ta_acktradeblotter_compare {

	public MyResponse db_compare(){
		Common.logInfo("db_compare");
		
		MyResponse myResponse = new MyResponse();
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
				Common.logError("文件格式不正确"+fileTimeList[i]);
				return myResponse.failed("文件格式不正确"+fileTimeList[i]);
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
				
				Common.logInfo("交易清算成功");
				return myResponse.success();
			}
			String oldString = new String(charOld,0,oldLength);
			String newString = new String(charNew,0,newLength);
			//按照换行分割
			String oldStringArray[] = oldString.split("\n");
			String newStringArray[] = newString.split("\n");
			//判断交易笔数
			if(oldStringArray.length != newStringArray.length){
				
				Common.logError("交易笔数不同!");
				myResponse.failed("交易笔数不同!");				
			}
			//获取最大的交易笔数
			int stringLength = oldStringArray.length >= newStringArray.length ?oldStringArray.length : newStringArray.length;
			for(int i = 0; i < stringLength; i++){
				int stringSplitLength = 0;
				//按照,分割表中字段
				//判断交易的总笔数
				if(newStringArray.length==i){
					Common.logError("新交易清算中没有第"+(i+1)+"笔交易!");
					myResponse.failed("新交易清算中没有第"+(i+1)+"笔交易!");
				}
				//判断交易的总笔数
				if(oldStringArray.length==i){
					Common.logError("旧交易清算中没有第"+(i+1)+"笔交易!");
					myResponse.failed("旧交易清算中没有第"+(i+1)+"笔交易!");
				}
				String[] newStringSplitArray = newStringArray[i].split(",");
				String[] oldStringSplitArray = oldStringArray[i].split(",");
				//判断表中的字段数目
				if(newStringSplitArray.length != oldStringSplitArray.length ){
					Common.logError("第"+(i+1)+"笔交易发生错误,缺少字段!");
					myResponse.failed("第"+(i+1)+"笔交易发生错误,缺少字段!");
				}
				//获取最大的字段数目
				stringSplitLength = newStringSplitArray.length <= oldStringSplitArray.length ? newStringSplitArray.length : oldStringSplitArray.length;
				//判断字段是否全部相同
				for(int j = 0; j < stringSplitLength; j++){
					if( !newStringSplitArray[j].equals(oldStringSplitArray[j]) ){
						Common.logError("第"+(i+1)+"笔交易的字段"+newStringSplitArray[j]+"发生了错误");
						myResponse.failed("第"+(i+1)+"笔交易的字段"+newStringSplitArray[j]+"发生了错误");
						}
					}
				}
		} catch (Exception e) {
			Common.logError("获取文件失败"+oldFile.getName()+" or "+newFile.getName());
			return myResponse.failed("获取文件失败");
		}
		return myResponse;
	}
}
