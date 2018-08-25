package com.yss.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
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
 * 交易申请表、用来存储交易类申请数据
 * @author tanglonglong
 *
 */
public class Save_T_TA_B_APPTRADEBLOTTER {

	public MyResponse save_T_TA_B_APPTRADEBLOTTER(){
		Common.logInfo("save_T_TA_B_APPTRADEBLOTTER");
		
		MyResponse myResponse = new MyResponse();
		Connection con = DBConnect.getConnection();
		StringBuffer allJson = new StringBuffer();
		
		String[] key = {"FAPPNO","FFUNDID","FLARGEREDEEMFLAG","FAPDT","FAPTM","FCUSTNO","FSEATNO","FSUBQUTY","FSUBAMT","FAPKIND",
				"FFUNDACCT","FCOMMRO","FCLEARACCT","FREGIONCODE","FCURRENCYTYPE","FNETPOINT","FORIGINALAPPNO","FORIGINALAPPDATE",
				"FINVTP","FVALIDAYS","FBOOKING","FREDEMPTIONDATEINADVANCE","FDATEOFPERIODICSUBS","FTERMOFPERIODICSUBS","FFUTUREBUYDATE",
				"FOSEATNO","FCHARGE","FTARGETBRANCHCODE","FOCUSTNO","FTARGETREGIONCODE","FDIVIDENDRATIO","FSUMMARY","FOFUNDID",
				"FTOTALBACKENDLOAD","FCHARGETYPE","FOLDACKDT","FDETAILFLAG","FOLDAPDT","FMELONMD","FFROZENCAUSE","FFREEZINGDEADLINE",
				"FVARIETYCODEOFPERIODICSUBS","FSERIALNOOFPERIODICSUBS","FRATIONTYPE","FOFUNDACCT","FOTANO","FFROMNETPOINT","FTACUSTOMERNO",
				"FOCHARGETYPE","FRATIONPROTOCOLNO","FBEGINDATEOFPERIODICSUBS","FENDDATEOFPERIODICSUBS","FSENDDAYOFPERIODICSUBS","FACCPTMD",
				"FFORCEREDEMPTIONTYPE","FTAKEINCOMEFLAG","FPURPOSEOFPESUBS","FFREQUENCYOFPESUBS","FPERIODSUBTIMEUNIT","FBATCHNUMOFPESUBS",
				"FCAPITALMODE","FDETAILCAPTICALMODE","FBACKENLOADDISCOUNT","FINVGROUPNO","FFUTURESUBSCRIBEDATE","FTRADEMD","FLARGEBUYFLAG",
				"FCHARGROUTE","FCHARGERATE","FFEE","FINVNM","FDELAYDT","FSPECREDEEMFLAG","FAPPLYNAV","FACKNAV","FACKQUTY","FACKAMT","FFEEID",
				"FAGENCYFEE","FFEEREDM","FFEEBID","FTAFEE","FFUNDFEE","FTAX","FSTAMPDUTY","FOTHERFEE","FTAIL","FBANKNO","FPLANID",
				"FCONTRACTNO","FSNTM","FAPPLYST","FAPPLYFROM","FMANCAUSED","FMACKQUTY","FMACKAMT","FACKDT","FRETCODE","FCUSTODIANNETNO",
				"FOCUSTODIANNETNO","FACHIEVEMENTPAY","FBREACHFEE","FFROMTAFLAG","FISOPENPERIOD","FAPPFUNDST","FCHECKFLAG","FAPPROVRATIO",
				"FACKNO","FOTHERFEE1","FBREACHFEEBACKTOFUND","FFORCEREDTYPE","FL_SERIALNO","FINCOMEAMT","FADUITRESULT","FBROKER","FBKIDTP",
				"FBKIDNO","FOLDACKNO","FTASERIALNO","FSALESPROMOTION","FORIGINALSERIALNO","FID","FCREATOR_ID","FCREATE_TIME","FLAST_EDIT_TIME",
				"FLAST_EDITOR_ID","FDELETED","FDELETE_USER_ID","FMARK_DELETED_TIME","FCHECKED","FCHECKER_ID","FCHECK_TIME","FSTART_TIME",
				"FEND_TIME","FDATA_SOURCE","FREASONOFSPECIALBUSINESS","FCASE_NO","FCLEARST","FAPPROVE_RATIO","FCLEAR_CODE","FDISCOUNT",
				"FSHARE_TYPE","FHUMAN_OPER","FTRADEOFENDED","FAMTOFEND","FJUST_IN_TIME"};
		Map<String, Object> map = new LinkedHashMap<String,Object>();
		try {
			ResultSet resultSet = con.prepareStatement("select * from T_TA_B_APPTRADEBLOTTER").executeQuery();
			while(resultSet.next()){
				for(String k : key){
					map.put(k, resultSet.getString(k) );
				}
				String jsonString = new JSONObject(map).toJSONString();
				allJson.append(jsonString+"\n");
			}
		} catch (SQLException e) {
			return myResponse.failed("get data from db failed");
		}
		SimpleDateFormat df  = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String time = df.format(new Date());
		//判断文件夹是否存在
		File fileDir = new File("E://T_TA_B_APPTRADEBLOTTER");
		if(!fileDir.exists()){
			fileDir.mkdir();
		}
		File file = new File("E://T_TA_B_APPTRADEBLOTTER/T_TA_B_APPTRADEBLOTTER-"+time+".json");
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
