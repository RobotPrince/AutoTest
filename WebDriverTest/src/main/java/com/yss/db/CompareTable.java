package com.yss.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

import org.apache.commons.lang.StringUtils;

import com.yss.common.Common;
import com.yss.common.MyResponse;
import com.yss.common.XMLParameterEnum;

public class CompareTable {
	
	public MyResponse compareAllTables(){
		Common.logInfo("compareAllTables");
		
		MyResponse myResponse = new MyResponse();
		 String errorMes = "";
		 try{
			 
			//获取全部数据表
			Tables[] values = Tables.values();
			for(Tables t : values){
				MyResponse compareTableRes = compare(t);
				//判断该表的比较是否失败
				 if(compareTableRes.get("msg")!=null && !"".equals(compareTableRes.get("msg"))){
					 //获取表的错误信息
					 errorMes += (String)compareTableRes.getMessage()+"/n";
				 }
			}
		 }catch(Exception e){
			 Common.logError("Exception happend in CompareTable");
		 }finally{
			 
			 if(errorMes != ""){
				 return myResponse.failed(errorMes);
			 }
		 }
		return myResponse.success();
	}

	private MyResponse compare(Tables table){ 
		Common.logInfo("compare-"+table);
		
		String savePlace = Common.XMLMap.get(XMLParameterEnum.SAVEPLACE);

		//将String数组拼接成A,B,C的形式
		MyResponse myResponse = new MyResponse();
		String primaryKey = StringUtils.join(table.getUnique(), ",");
		List<String> keyList = table.getList();
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		File dir = new File(savePlace+table+"/");
		//获取文件夹下所有文件名称
		String[] fileList = dir.list();
		long[] fileTimeList = new long[fileList.length];
		for(int i = 0; i< fileList.length; i++){
			try {
				//将文件名称中的时间转换成long类型
				fileTimeList[i] = sdf.parse(fileList[i].split("-")[1]).getTime();
			} catch (ParseException e) {
				Common.logError("文件名称格式不正确"+fileTimeList[i]);
				return myResponse.failed("文件名称格式不正确"+fileTimeList[i]);
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
		
		File oldFile = new File(savePlace+table+"/"+table+"-"+oldTime+".json");
		File newFile = new File(savePlace+table+"/"+table+"-"+newTime+".json");
		
		BufferedReader oldReader = null;
		BufferedReader newReader = null;
		try {
			//50M
			char[] charOld = new char[1024*1024*50];
			char[] charNew = new char[1024*1024*50];
			//读入到String中
			oldReader = new BufferedReader (new InputStreamReader (new FileInputStream (oldFile),"utf-8"));
			newReader = new BufferedReader (new InputStreamReader (new FileInputStream (newFile),"utf-8"));
			int oldLength = oldReader.read(charOld);
			int newLength = newReader.read(charNew);
			//若两表完全相同，则直接成功退出
			if(new String(charOld).equals(new String(charNew))){
				
				Common.logInfo(table+"比较成功");
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
            //循环外层Map，取出Primary对应的交易
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
		    //缺少的数据的primary key
		    String missRow = new String();
		    
		    for(String oldKey : oldKeySet){
		    	//判断新版本是否少整条数据
		    	if(!newTempArray.contains(oldKey)){
		    		missRow = oldKey;
		    		Common.logError(table+"中不存在"+primaryKey+":"+oldKey+"的该条数据!");
		    		myResponse.put("msg","\n"+table+"中不存在"+primaryKey+":"+oldKey+"的该条数据!");
		    	}
		    	else{
		    		//若在新版中找到该条数据，则移除
			    	newTempArray.remove( oldKey);
		    	}
		    }
		    //判断新版本是否多整条数据
		    if(!newTempArray.isEmpty()){
		    	for( Object key : newTempArray ){
		    		Common.logError(table+"中多出"+primaryKey+":"+key+"的该条数据!");
		    		myResponse.put("msg",myResponse.get("msg")+"\n"+table+"中多出"+primaryKey+":"+key+"的该条数据!");
		    	}
		    }
		    //将keyset还原
		    newKeySet = newMap.keySet();

		    //获取全部
		    for( String set : oldKeySet ){
		    	//若该条数据缺失，不必比较
		    	if( missRow.equals(set) ){
		    		continue;
		    	}
		    	//获取所有字段
		    	Map oldItem = (Map)oldMap.get(set);
		    	Map newItem = (Map)newMap.get(set);
		    	for(String key: keyList){
		    		//判断新旧两版对应字段的值是否相同
		    		if( !(oldItem.get(key) == newItem.get(key) || oldItem.get(key).equals(newItem.get(key)))){
		    			Common.logError(table+"表中"+primaryKey+"为"+set+"的该条数据中字段为"+key+"处发生了错误(新"+newItem.get(key)+", 旧"+oldItem.get(key)+")");
		    			myResponse.put("msg",myResponse.get("msg")+"\n"+table+"表中"+primaryKey+"为"+set+"的该条数据中字段为"+key+"处发生了错误(新"+newItem.get(key)+", 旧"+oldItem.get(key)+")");
		    		}
		    	}
		    }
		}catch(Exception e){
			System.out.println(e);
			Common.logError("程序发生错误，获取文件失败"+oldFile.getName()+" or "+newFile.getName());
			myResponse.put("msg",myResponse.get("msg")+"\n程序发生错误，获取文件失败"+oldFile.getName()+" or "+newFile.getName());
			return myResponse.failed((String)myResponse.get("msg"));
		}
		finally{
			try {
 				oldReader.close();
				newReader.close();
			} catch (IOException e) {
				Common.logError("oldReader close failed");
				return myResponse.failed((String)myResponse.get("msg")+ "BufferReader close failed");
			}
			
		}
		return myResponse.success();
	}
}
