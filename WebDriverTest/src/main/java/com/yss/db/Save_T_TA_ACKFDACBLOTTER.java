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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yss.common.Common;
import com.yss.common.DBConnect;
import com.yss.common.MyResponse;
/**
 * 账户确认表、账户申请清算后的确认数据
 * @author tanglonglong
 *
 */
public class Save_T_TA_ACKFDACBLOTTER {

	public static final String[] KEY = {"FACKNO","FAPPNO","FSEATNO","FNETPOINT","FCUSTTYPE","FCUSTNO","FAPKIND","FACKDT","FSETTLEDATE","FFUNDACCT",
		"FWARRANT","FINVTYPE","FINVNAME","FIDTYPE","FIDNO","FACCOUNTABBR","FMELONMD","FMULTIACCTFLAG","FFROZENCAUSE",
		"FFREEZINGDEADLINE","FTRANSST","FDOWNLOADDATE","FAPPLYST","FBROADCASTFLAG","FRETCODE","FSUMMARY","FCUSTODIANNETNO",
		"FCUSTIDVALID","FDISTRIBUTORCODE","FAPDT","FAPTM","FFROMTAFLAG","FOCUSTNO","FTACUSTOMERNO","FERRORDETAIL","FFROMNETPOINT",
		"FID","FCREATOR_ID","FCREATE_TIME","FLAST_EDIT_TIME","FLAST_EDITOR_ID","FDELETED","FDELETE_USER_ID","FMARK_DELETED_TIME",
		"FCHECKED","FCHECKER_ID","FCHECK_TIME","FSTART_TIME","FEND_TIME","FDATA_SOURCE"};
	
	public MyResponse save_T_TA_ACKFDACBLOTTER(){
		Common.logInfo("save_T_TA_ACKFDACBLOTTER");
		
		MyResponse myResponse = new MyResponse();
		Connection con = DBConnect.getConnection();
		String allJson = new String();
		Map<String, Object> JsonMap = new LinkedHashMap<String,Object>();
		try {
			ResultSet resultSet = con.prepareStatement("select * from T_TA_ACKFDACBLOTTER t order by t.FACKNO").executeQuery();
			while(resultSet.next()){
				Map<String, Object> map = new LinkedHashMap<String,Object>();
				for(String k : KEY){
					map.put(k, resultSet.getString(k) );
				}
				//设置json的key为确认流水号FACKNO
				JsonMap.put( resultSet.getString(KEY[0]), map);
			}
			allJson = new JSONObject(JsonMap).toJSONString();
		} catch (SQLException e) {
			return myResponse.failed("get data from db failed");
		}
		SimpleDateFormat df  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String time = df.format(new Date());
		//判断文件夹是否存在
		File fileDir = new File("E://T_TA_ACKFDACBLOTTER");
		if(!fileDir.exists()){
			fileDir.mkdir();
		}
		File file = new File("E://T_TA_ACKFDACBLOTTER/T_TA_ACKFDACBLOTTER-"+time+".json");
		try {
			//默认是GBK,万恶的GBK->UTF-8,并不能实现
			String allJsonGBK = new String(new String(allJson).getBytes("GBK"),"GBK");
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
