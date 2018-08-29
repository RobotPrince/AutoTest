package com.yss.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yss.common.Common;
import com.yss.common.DBConnect;
import com.yss.common.MyResponse;
/**
 * 账户类申请表、用来存储账户类申请数据
 * @author tanglonglong
 *
 */
public class Save_T_TA_B_APPFDACBLOTTER {
	
	public static final String[] KEY = {"FADDR","FINSTREPRIDCODE","FINSTREPRIDTYPE","FINSTREPRNAME","FAPPNO","FIDTYPE","FIDNO","FINVNAME",
		"FOPENDT","FAPTM","FINVTYPE","FPOSTCODE","FBKIDNO","FBKIDTYPE","FBROKER","FCUSTNO","FSEATNO","FAPKIND",
		"FACCTNOOFFMINCLEARINGAGENCY","FACCTNAMEOFFMINCLEARINGAGENCY","FCLEARINGAGENCYCODE","FBIRTHDAY","FDEPOSITACCT",
		"FREGIONCODE","FEDLEVEL","FEMAIL","FFAXNO","FVOCCODE","FHOMETEL","FANNINCOME","FMOBILENO","FNETPOINT","FOFFICETEL","FACCOUNTABBR",
		"FCONFIDENTIALDOCCODE","FSEX","FSHSECACC","FSZSECACC","FFUNDACCT","FTELNO","FTRADINGMETHOD","FCHILDID",
		"FDELIVERTYPE","FHANDLERFLAG","FWARRANT","FMULTIACCTFLAG","FOCUSTNO","FACCTNAME","FCLEARACCT","FBANKNO",
		"FSTATEMENTFLAG","FNATION","FFROMNETPOINT","FRECOMMEND","FCORPNAME","FCUSTIDVALID","FBKIDVALID",
		"FINSTREPRCERTVALIDDATE","FCLIENTRISKRATE","FINSTSCOPE","FSTOCKHOLDER","FACTUALCONTROLLER","FMARRIAGESTATUS",
		"FFAMILYNUM","FPENATES","FMEDIAHOBBY","FSEATTP","FENGLISHFIRSTNAME","FENGLISHFAMLIYNAME","FVOCATION",
		"FCORPOPROPERTY","FSTAFFNUM","FHOBBYTYPE","FPROVINCE","FCITY","FCOUNTY","FCOMMENDPERSON","FCOMMENDPERSONTYPE",
		"FACCPTMD","FFROZENCAUSE","FFREEZINGDEADLINE","FORIGINALSERIALNO","FORIGINALAPPNO","FSUMMARY","FCUSTTYPE",
		"FACCTTYPE","FPAGERNO","FMELONMD","FDIVIDENDRATIO","FCHILDFLAG","FINDUSTRYTYPE","FCORPROPERTIY","FREGISTCAPITAL",
		"FCONNWAY","FINVESTHOBBY","FINVESTDEST","FVIP_FLAG","FVIP_LEVEL","FTRADE_SUM","FTRADE_LIMIT","FSNTM","FAPPLYST",
		"FACKDT","FRETCODE","FCUSTODIANNETNO","FMGRID","FPERSONADDR","FINSTID","FINSTTAXREGISTRATIONNO","FINSTPRINCIPA",
		"FBANKNAME","FDISTRIBUTORCODE","FPASSWD","FTRADEMD","FSUBAMT","FCHARGE","FCASE_NO","FID","FCREATOR_ID",
		"FCREATE_TIME","FLAST_EDIT_TIME","FLAST_EDITOR_ID","FDELETED","FDELETE_USER_ID","FMARK_DELETED_TIME","FCHECKED",
		"FCHECKER_ID","FCHECK_TIME","FSTART_TIME","FEND_TIME","FDATA_SOURCE","FCLEARST","FCLEAR_CODE","FNAMEALIAS","FHUMAN_OPER"};
	public MyResponse save_T_TA_B_APPFDACBLOTTER() throws SQLException{
		Common.logInfo("save_T_TA_B_APPFDACBLOTTER");
		
		MyResponse myResponse = new MyResponse();
		Connection con = DBConnect.getConnection();
		String allJson = new String();
		Map<String, Object> JsonMap = new LinkedHashMap<String,Object>();
		try {
		String sql = "select * from T_TA_B_APPFDACBLOTTER t order by t.FAPPNO";
			 PreparedStatement prepareStatement = con.prepareStatement(sql);
			 ResultSet resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Map<String, Object> map = new LinkedHashMap<String,Object>();
				for(String k : KEY){
					map.put(k, resultSet.getString(k) );
				}
				//设置json的key为申请单号FADDR
				JsonMap.put( resultSet.getString(KEY[4]), map);
			}
			allJson = new JSONObject(JsonMap).toJSONString();	
		} catch (SQLException e) {
			return myResponse.failed("get data from db failed");
		}
		SimpleDateFormat df  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String time = df.format(new Date());
		//判断文件夹是否存在
		File fileDir = new File("E://T_TA_B_APPFDACBLOTTER");
		if(!fileDir.exists()){
			fileDir.mkdir();
		}
		File file = new File("E://T_TA_B_APPFDACBLOTTER/T_TA_B_APPFDACBLOTTER-"+time+".json");
		try {
			//默认是GBK,万恶的GBK->UTF-8,并不能实现
			String allJsonGBK = new String(new String(allJson).getBytes("GBK"),"GBK");
			String s =allJsonGBK.substring(0, allJsonGBK.length()-1);
			BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (file,true),"GBK"));
			writer.write(s);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			return myResponse.failed("get data from db failed");
		}
		return myResponse.success();
	}
}
