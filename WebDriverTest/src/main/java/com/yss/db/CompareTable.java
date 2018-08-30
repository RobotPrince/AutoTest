package com.yss.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import com.yss.common.Common;
import com.yss.common.MyResponse;

public class CompareTable {
	
	public MyResponse compareAllTables(){
		MyResponse myResponse = new MyResponse();
		
		return myResponse;
	}

	private MyResponse compare(Map<String,String> map){
		Common.logInfo("compare");
		
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
		if(fileList.length<2){
			Common.logError("不存在两个以上文件，无法比较");
			return myResponse.failed("不存在两个以上文件，无法比较");
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
			//50M
			char[] charOld = new char[1024*1024*50];
			char[] charNew = new char[1024*1024*50];
			//读入到String中
			BufferedReader oldReader = new BufferedReader (new InputStreamReader (new FileInputStream (oldFile),"utf-8"));
			BufferedReader newReader = new BufferedReader (new InputStreamReader (new FileInputStream (newFile),"utf-8"));
			int oldLength = oldReader.read(charOld);
			int newLength = newReader.read(charNew);
			if(new String(charOld).equals(new String(charNew))){
				
				Common.logInfo("交易清算成功");
				return myResponse.success();
			}
			String oldString = new String(charOld,0,oldLength);
			oldString = new String(oldString.getBytes("utf-8"),"utf-8");
			String newString = new String(charNew,0,newLength);
			newString = new String(newString.getBytes("utf-8"),"utf-8");
			LinkedHashMap<String, Object> oldMap = new LinkedHashMap<String,Object>();
			LinkedHashMap<String, Object> newMap = new LinkedHashMap<String,Object>();
			//String 转Json
            JSONObject oldJson = JSONObject.fromObject(oldString);
            JSONObject newJson = JSONObject.fromObject(newString);
            //循环外层Map，取出确认流水号FACKNO对应的交易
            for (Object k : oldJson.keySet()) {
                Map v = (Map) oldJson.get(k);
                oldMap.put(k.toString(), v);
            }
            for (Object k : newJson.keySet()) {
            	Map v = (Map) newJson.get(k);
            	newMap.put(k.toString(), v);
            }
            
		    Set<String> oldKeySet = oldMap.keySet();
		    Set<String> newKeySet = newMap.keySet();
		    List<String> newTempArray = new ArrayList<>(newKeySet);
		    //缺少的交易的确认流水号
		    String missTradeFACKNO = null;
//		    //多出的交易的确认流水号
//		    String overTradeFACKNO = null;
		    
		    for(String oldKey : oldKeySet){
		    	//判断新版本是否少交易
		    	if(!newTempArray.contains(oldKey)){
		    		missTradeFACKNO = oldKey;
		    		Common.logError("新交易申请清算后确认数据中没有确认流水号FACKNO为"+oldKey+"的交易清算确认数据!");
		    		myResponse.put("msg","\n新交易申请清算后确认数据中没有确认流水号FACKNO为"+oldKey+"的交易清算确认数据!");
		    	}
		    	else{
		    		//若在新版中找到该笔交易，则移除
			    	newTempArray.remove( oldKey);
		    	}
		    }
		    //判断新版本是否多交易
		    if(!newTempArray.isEmpty()){
		    	for( Object key : newTempArray ){
		    		Common.logError("新交易申请清算后确认数据中多出确认流水号FACKNO为"+key+"的交易清算确认数据!");
		    		myResponse.put("msg",myResponse.get("msg")+"\n新交易申请清算后确认数据中多出确认流水号FACKNO为"+key+"的交易清算确认数据!!");
		    	}
		    }
		    //将keyset还原
		    newKeySet = newMap.keySet();

		    //获取所有交易
		    for( String set : oldKeySet ){
		    	//若该笔交易缺失，不必计较
		    	if( missTradeFACKNO.equals(set) ){
		    		continue;
		    	}
		    	//获取所有字段
		    	Map oldItem = (Map)oldMap.get(set);
		    	Map newItem = (Map)newMap.get(set);
		    	for(String key: Save_T_TA_ACKFDACBLOTTER.KEY){
		    		//判断新旧两版对应字段的值是否相同
		    		if( !(oldItem.get(key) == newItem.get(key) || oldItem.get(key).equals(newItem.get(key)))){
		    			Common.logError("交易申请清算后确认数据中确认流水号FACKNO为"+set+"的字段为"+key+"发生了错误(新"+newItem.get(key)+", 旧"+oldItem.get(key)+")");
		    			myResponse.put("msg",myResponse.get("msg")+"\n交易申请清算后确认数据中确认流水号FACKNO为"+set+"的字段为"+key+"发生了错误(新"+newItem.get(key)+", 旧"+oldItem.get(key)+")");
		    		}
		    	}
		    }
		}catch(Exception e){
			Common.logError("程序发生错误，获取文件失败"+oldFile.getName()+" or "+newFile.getName());
			myResponse.put("msg",myResponse.get("msg")+"\n程序发生错误，获取文件失败");
			return myResponse.failed((String)myResponse.get("msg"));
		}
		return myResponse;
	}
}
