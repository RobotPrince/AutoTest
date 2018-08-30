package com.yss.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yss.common.Common;
import com.yss.common.DBConnect;
import com.yss.common.MyResponse;

public class SaveTable {

	public MyResponse saveAllTables(){
		Common.logInfo("SaveAllTables");
		
		MyResponse myResponse = new MyResponse();
		//获取全部数据表
		Tables[] values = Tables.values();
		for(Tables t : values){
			saveTable(t);
		}
		return myResponse;
	}
	private MyResponse saveTable(Tables t){
		Common.logInfo("SaveTable");
		
		MyResponse myResponse = new MyResponse();
		//获取数据表名称
		String tableName = t.name();
		//获取所有的字段名称
		List<String> keyList = t.getList();
		//获取唯一标识
		String primaryKey = t.getUnique();
		
		Connection con = DBConnect.getConnection();
		String allJson = new String();
		Map<String, Object> JsonMap = new LinkedHashMap<String,Object>();
		try {
			ResultSet resultSet = con.prepareStatement("select * from "+tableName+" t").executeQuery();
			while(resultSet.next()){
				Map<String, Object> map = new LinkedHashMap<String,Object>();
				for(String k : keyList){
					map.put(k, resultSet.getString(k) );
				}
				//设置json的key为唯一标识
				JsonMap.put( resultSet.getString(primaryKey), map);
			}
		allJson = new JSONObject(JsonMap).toJSONString();
		} catch (SQLException e) {
			return myResponse.failed("get data from db failed");
		}
		SimpleDateFormat df  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String time = df.format(new Date());
		//判断文件夹是否存在
		File fileDir = new File("E://DB_Save/");
		if(!fileDir.exists()){
			fileDir.mkdir();
		}
		//判断文件夹是否存在
		File fileDir2 = new File("E://DB_Save/"+tableName);
		if(!fileDir2.exists()){
			fileDir2.mkdir();
		}
		File file = new File("E://DB_Save/"+tableName+"/"+tableName+"-"+time+".json");
		try {
			//默认是GBK,万恶的GBK->UTF-8,并不能实现
			String allJsonGBK = new String(new String(allJson).getBytes("GBk"),"GBK");
			String s =allJsonGBK.substring(0, allJsonGBK.length());
			BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (file,true),"utf-8"));
			writer.write(s);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			return myResponse.failed("get data from db failed");
		}
		return myResponse.success();
	}
}
