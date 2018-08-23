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
//		Connection con = DBConnect.getConnection();
//		PreparedStatement prepareStatement;
//		StringBuffer allJson = new StringBuffer();
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		String[] key = {"FACKNO","FAPPNO","FSEATNO","FORIGINALAPPNO","FNETPOINT","FREGIONCODE","FFUNDACCT","FINVTP","FCUSTNO","FAPKIND","FFUNDID","FSUBAMT","FSUBQUTY","FCURRENCYTYPE","FCOMMRO","FBOOKING","FVALIDAYS","FLARGEREDEEMFLAG","FAPPLYNAV","FACKNAV","FCHARGERATE","FFEE","FAGENCYFEE","FFEEREDM","FFEEBID","FTAFEE","FFUNDFEE","FTAX","FSTAMPDUTY","FINTEREST","FINTERESTTAX","FOTHERFEE","FTAIL","FLASTBAL","FACKAMT","FACKQUTY","FBALANCE","FAVAILABLE","FBANKNO","FCLEARACCT","FOSEATNO","FOFUNDACCT","FOCUSTNO","FOFUNDID","FMELONMD","FDIVIDENDRATIO","FFREEZINGDEADLINE","FFROZENCAUSE","FDATEOFPERIODICSUBS","FPLANID","FCONTRACTNO","FORIGINALAPPDATE","FAPDT","FAPTM","FACKDT","FSNTM","FSETTLEDATE","FNAVDT","FNAV","FMINTDH","FUNFROZENBALANCE","FFROZENSHARES","FFROZENBALANCE","FCHARGETYPE","FTRANSST","FDOWNLOADDATE","FAPPLYST","FBROADCASTFLAG","FRETCODE","FSUMMARY","FCUSTODIANNETNO","FOCUSTODIANNETNO","FOCHARGETYPE","FACHIEVEMENTPAY","FBREACHFEE","FFROMNETPOINT","FMGRID","FACCPTMD","FINVGROUPNO","FDETAILFLAG","FVARIETYCODEOFPERIODICSUBS","FRATIONTYPE","FTACUSTOMERNO","FSERIALNOOFPERIODICSUBS","FRATIONPROTOCOLNO","FBEGINDATEOFPERIODICSUBS","FENDDATEOFPERIODICSUBS","FSENDDAYOFPERIODICSUBS","FSALESPROMOTION","FFORCEREDEMPTIONTYPE","FALTERNATIONDATE","FTAKEINCOMEFLAG","FPURPOSEOFPESUBS","FFREQUENCYOFPESUBS","FBATCHNUMOFPESUBS","FCAPITALMODE","FDETAILCAPTICALMODE","FBACKENLOADDISCOUNT","FREFUNDAMOUNT","FSALEPERCENT","FMANAGERREALRATIO","FCHANGEFEE","FRECUPERATEFEE","FACHIEVEMENTCOMPEN","FSHARESADJUSTMENTFLAG","FGENERALTASERIALNO","FUNDISTRIBUTEMONETARYINCOME","FUNDISTRIBUTEMONETARYINCOMEFL","FBREACHFEEBACKTOFUND","FPUNISHFEE","FCHANGEAGENCYFEE","FRECUPERATEAGENCYFEE","FERRORDETAIL","FTOTALTRANSFEE","FREDEMPTIONREASON","FORIGINALCFMDATE","FFROZENMETHOD","FREDEMPTIONINADVANCEFLAG","FFROMTAFLAG","FTRANSFERFEE","FOTHERFEE2","FMINFEE","FCFMVOLOFTARGETFUND","FTARGETFUNDPRICE","FTARGETNAV","FTRADINGPRICE","FTRANSFERDIRECTION","FTARGETREGIONCODE","FTARGETBRANCHCODE","FVOLUMEBYINTEREST","FTOTALBACKENDLOAD","FRATEFEE","FORIGINALSUBSDATE","FLARGEREDEMPTIONFLAG","FFEECALCULATOR","FDRAWBONUSUNIT","FFROZENSHARESFORREINVEST","FDIVIDENDTYPE","FXRDATE","FREGISTRATIONDATE","FDIVIDENDPERUNIT","FTOTALFROZENVOL","FLARGEBUYFLAG","FRAISEINTEREST","FSHAREREGISTERDATE","FPERIODSUBTIMEUNIT","FOTHERFEE1","FFORCEREDTYPE","FL_SERIALNO","FINCOMEAMT","FID","FCREATOR_ID","FCREATE_TIME","FLAST_EDIT_TIME","FLAST_EDITOR_ID","FDELETED","FDELETE_USER_ID","FMARK_DELETED_TIME","FCHECKED","FCHECKER_ID","FCHECK_TIME","FSTART_TIME","FEND_TIME","FDATA_SOURCE","FSHARE_TYPE","FINCOME","FTRADEOFENDED","FAMTOFEND","FGDINCOME"};
//		Map<String, Object> map = new LinkedHashMap<String,Object>();
//		try {
//			ResultSet resultSet = con.prepareStatement("select * from T_TA_ACKTRADEBLOTTER").executeQuery();
//			while(resultSet.next()){
//				for(String k : key){
//					map.put(k, resultSet.getString(k) );
//				}
//				String jsonString = new JSONObject(map).toJSONString();
//				System.out.println(jsonString);
//				allJson.append(jsonString+"\n");
//			}
//		} catch (SQLException e) {
//			return myResponse.failed("get data from db failed");
//		}
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
			String oldString = new String(charOld,0,oldLength);
			String newString = new String(charNew,0,newLength);
			//按照换行分割
			String oldStringArray[] = oldString.split("\n");
			String newStringArray[] = newString.split("\n");
			//判断交易笔数
			if(oldStringArray.length != newStringArray.length){
				
				Common.logError("交易笔数不同!");
			}
			//获取最大的交易笔数
			int stringLength = oldStringArray.length >= newStringArray.length ?oldStringArray.length : newStringArray.length;
			for(int i = 0; i < stringLength; i++){
				int stringSplitLength = 0;
				//按照,分割表中字段
				String[] newStringSplitArray = newStringArray[i].split(",");
				String[] oldStringSplitArray = oldStringArray[i].split(",");
				//判断表中的字段数目
				if(newStringSplitArray.length != oldStringSplitArray.length ){
					Common.logError("第"+i+"笔交易发生错误!");
					//获取最大的字段数目
					stringSplitLength = newStringSplitArray.length >= oldStringSplitArray.length ? newStringSplitArray.length : oldStringSplitArray.length;
				}
				//判断字段是否全部相同
				for(int j = 0; j < stringSplitLength; j++){
					
				}
				
			}
			if(new String(charOld).equals(new String(charNew))){
				
			System.out.println("一模一样");
			}
		} catch (Exception e) {
			Common.logError("获取文件失败"+oldFile.getName()+" or "+newFile.getName());
			return myResponse.failed("获取文件失败");
		}
		
		return myResponse.success();
	}
}
