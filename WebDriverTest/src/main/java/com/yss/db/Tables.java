package com.yss.db;

import java.util.List;

public enum Tables {
	/**
	 * 账户确认02导出表(申请单编号)
	 */
	T_PD_I_02_OUT(Keys.T_PD_I_02_OUT_keys,"APPSHEETSERIALNO"),
	/**
	 * 交易确认04导出表(申请单编号)
	 */
	T_PD_I_04_OUT(Keys.T_PD_I_04_OUT_keys,"APPSHEETSERIALNO"),
	/**
	 * 基金账户对账数据05导出表(基金代码,投资人基金交易账号投资人在销售机构内开设的用于交易的账号,销售人代码,投资人基金账号)
	 */
	T_PD_I_05_OUT(Keys.T_PD_I_05_OUT_keys,"FUNDCODE","TRANSACTIONACCOUNTID","DISTRIBUTORCODE","TAACCOUNTID"),
	/**
	分红数据06导出表,(分红日/发放日,基金代码,权益登记日期(格式为：YYYYMMDD)，投资人基金交易账号(投资人在销售机构内开设的用于交易的账号)，销售人代码，业务代码，投资人基金账号)
	 */
	T_PD_I_06_OUT(Keys.T_PD_I_06_OUT_keys,"DIVIDENTDATE","FUNDCODE","REGISTRATIONDATE","TRANSACTIONACCOUNTID","DISTRIBUTORCODE","BUSINESSCODE","TAACCOUNTID"),
	/**
	*基金动态信息数据项07导出表,(基金代码,基金净值日期)
	*/
	T_PD_I_07_OUT(Keys.T_PD_I_07_OUT_keys,"FUNDCODE","UPDATEDATE"),
	/**
	*公告08导出表,(公告文件号，公告类别,公告日期)
	*/
	T_PD_I_08_OUT(Keys.T_PD_I_08_OUT_keys, "ANNOUNCEMENTNO","ANNOUNCEMENTTYPE","ANNOUNCEMENTDATE"),
	/**
	*红利汇总数据项09导出表,(分红日/发放日,基金代码,销售人代码)
	*/
	///T_PD_I_09_OUT(Keys.T_PD_I_09_OUT_keys,"DIVIDENTDATE", "FUNDCODE","DISTRIBUTORCODE"),
	/**
	*日交割汇总数据项10导出表,(基金代码,销售人代码,管理人代码,汇总日期)
	*/
	///	T_PD_I_10_OUT(Keys.T_PD_I_10_OUT_keys ,"FUNDCODE","DISTRIBUTORCODE","MANAGERCODE","AGGREGATIONDATE"),
	/**
	///*TA发送的业务申请汇总数据项11导出表,(基金代码,销售人代码,业务代码,汇总日期)
	*/
	///T_PD_I_11_OUT(Keys.T_PD_I_11_OUT_keys,"FUNDCODE","DISTRIBUTORCODE","BUSINESSCODE","AGGREGATIONDATE"),
	/**
	*TA发送的业务确认汇总数据项12导出表,(基金代码,销售人代码,业务代码,汇总日期)
	*/
	///T_PD_I_12_OUT(Keys.T_PD_I_12_OUT_keys,"FUNDCODE","DISTRIBUTORCODE","BUSINESSCODE","AGGREGATIONDATE"),
	/**
	 *销售发送的业务申请汇总数据项13导入表,(基金代码,销售人代码,业务代码,汇总日期)
	*/
	///T_PD_I_13_IN(Keys.T_PD_I_13_IN_keys,"FUNDCODE","DISTRIBUTORCODE","BUSINESSCODE","AGGREGATIONDATE"),
	/**
	*参与人及结算席位21导出表,(席位代码,销售人代码)
	*/
	///T_PD_I_21_OUT(Keys.T_PD_I_21_OUT_keys,"SEATCODE","DISTRIBUTORCODE"),
	/**
	*其他类申请文件23导入表,
	*/
	///T_PD_I_23_IN(Keys.T_PD_I_23_IN_keys),
	/**
	*其他类确认文件24导出表,
	*/
	///T_PD_I_24_OUT(Keys.T_PD_I_24_OUT_keys),
	/**
	*资金清算文件25导出表
	*/
	///T_PD_I_25_OUT(Keys.T_PD_I_25_OUT_keys),
	/**
	*基金份额调整和对账文件导出表,(日期,基金代码,销售商代码,份额级别)
	**/
	T_PD_I_ADJUSTSHARE_OUT(Keys.T_PD_I_ADJUSTSHARE_OUT_keys,"FAPDT","FFUNDID","FSEATNO","FSHARE_LEVEL"),
	/**
	*申购核算数据导出表,(日期,基金代码,销售商代码)
	**/
	T_PD_I_ALLOT_OUT(Keys.T_PD_I_ALLOT_OUT_keys,"FAPDT","FFUNDID","FSEATNO"),
	/**
	*分红核算数据导出表,(日期,基金代码,销售商代码)
	**/
	T_PD_I_BONUS_OUT(Keys.T_PD_I_BONUS_OUT_keys,"FAPDT","FFUNDID","FSEATNO"),
	/**
	*基金基础参数C1导出表,(基金代码)
	**/
	T_PD_I_C1_OUT(Keys.T_PD_I_C1_OUT_keys,"FUNDCODE"),
	/**
	*基金代理关系C2导出表,(基金代码,销售人代码,网点号码)
	**/
	T_PD_I_C2_OUT(Keys.T_PD_I_C2_OUT_keys,"FUNDCODE","DISTRIBUTORCODE","BRANCHCODE"),
	/**
	*基金转换关系C3导出表,(基金代码,转换时的目标基金代码,销售人代码)
	**/
	T_PD_I_C3_OUT(Keys.T_PD_I_C3_OUT_keys,"FUNDCODE","CODEOFTARGETFUND","DISTRIBUTORCODE"),
	/**
	*基金分红方案C4导出表，(基金代码,权益登记日期)
	**/
	T_PD_I_C4_OUT(Keys.T_PD_I_C4_OUT_keys,"FUNDCODE","REGISTRATIONDATE"),
	/**
	*基金费率C5导出表,(基金代码,业务代码,份额上限,金额下限,金额上限,天数下限,天数上限费率)
	**/
	T_PD_I_C5_OUT(Keys.T_PD_I_C5_OUT_keys,"FUNDCODE","BUSINESSCODE","VOLUPPERLIMIT","AMOUNTLOWERLIMIT","AMOUNTUPPERLIMIT","DAYSLOWERLIMIT","DAYSUPPERLIMIT"),
	/**
	*转托管数据导出表,(日期,基金代码,销售商代码)
	**/
	T_PD_I_CHANGEAGENCY_OUT(Keys.T_PD_I_CHANGEAGENCY_OUT_keys,"FAPDT","FFUNDID","FSEATNO"),
	/**
	*基金收益情况数据文件,(日期,基金代码)
	**/
	T_PD_I_INCOME_OUT(Keys.T_PD_I_INCOME_OUT_keys,"FAPDT","FFUNDID"),
	/**
	*净值导入表,(净值日期,基金代码)
	**/
	T_PD_I_NAV_IN(Keys.T_PD_I_NAV_IN_keys,"FFD_NET_DATE","FFUNDID"),
	
	//FIXME:数据库里不存在这张表
//	/**
//	*净值导出表,
//	**/
//	T_PD_I_NAV_OUT(Keys.T_PD_I_NAV_OUT_keys,"FID"),
	/**
	*净值补充数据导入表,(日期,基金代码)
	**/
	T_PD_I_NAVADD_IN(Keys.T_PD_I_NAVADD_IN_keys,"FFD_NET_DATE","FFUNDID"),
	
	//FIXME:数据库里不存在这张表
//	/**
//	*净值补充数据导入表,
//	**/
//	T_PD_I_NAVADD_OUT(Keys.T_PD_I_NAVADD_OUT_keys,"FID"),
	/**
	*赎回核算数据导出表,(日期,基金代码,销售商代码)
	**/
	T_PD_I_REDEEM_OUT(Keys.T_PD_I_REDEEM_OUT_keys,"FAPDT","FFUNDID","FSEATNO"),
	/**
	*认购核算数据导出表(日期,基金代码,销售商代码)
	**/
	T_PD_I_SUB_OUT(Keys.T_PD_I_SUB_OUT_keys,"FAPDT","FFUNDID","FSEATNO"),
	/**
	*基金转换(入)核算数据输出表(日期,基金代码,销售商代码)
	**/
	T_PD_I_SWITCHIN_OUT(Keys.T_PD_I_SWITCHIN_OUT_keys,"FAPDT","FFUNDID","FSEATNO"),
	/**
	*基金转换转出数据导出表(日期,基金代码,销售商代码)
	**/
	T_PD_I_SWITCHOUT_OUT(Keys.T_PD_I_SWITCHOUT_OUT_keys,"FAPDT","FFUNDID","FSEATNO"),
	/**
	*业绩报酬流水(申请单号)
	**/
	T_TA_ACHIEVEMENTPAYBLOTTER(Keys.T_TA_ACHIEVEMENTPAYBLOTTER_keys,"FAPPNO"),
	/**
	*业绩报酬余额明细(申请单号)
	**/
	T_TA_ACHIEVEPAYBLOTTERDETAIL(Keys.T_TA_ACHIEVEPAYBLOTTERDETAIL_keys, "FAPPNO"),
	/**
	*账户确认(申请流水号)
	**/
	T_TA_ACKFDACBLOTTER(Keys.T_TA_ACKFDACBLOTTER_keys,"FAPPNO"),
	/**
	*交易确认(申请流水号,原申请流水号)
	**/
	T_TA_ACKTRADEBLOTTER(Keys.T_TA_ACKTRADEBLOTTER_keys,"FAPPNO","FORIGINALAPPNO"),
	/**
	*申请号确认号对应关系表(申请单号,确认单号)
	**/
	T_TA_APPNOACKNOREF(Keys.T_TA_APPNOACKNOREF_keys,"FAPPNO"),
	
	//FIXME:数据库里不存在这张表
//	/**
//	*余额构成
//	**/
//	T_TA_BALCONS(Keys.T_TA_BALCONS_keys,"FID"),
	/**
	*余额构成流水(申请流水号)
	**/
	T_TA_BALCONSBLOTTER(Keys.T_TA_BALCONSBLOTTER_keys,"FAPPNO"),
	
	//FIXME:数据库里不存在这张表
//	/**
//	*清算中用到的参数默认值
//	**/
//	T_TA_DEFAULTPARAM,
	/**
	*份额余额(基金账号,交易账号,清算日期)
	**/
	T_TA_FUNDBALANCE(Keys.T_TA_FUNDBALANCE_keys,"FFUNDACCT","FCUSTNO","FCLEARDT"),
	/**
	*权益登记信息(基金账号,交易账号,权益登记日)
	**/
	T_TA_RIGHTSREG(Keys.T_TA_RIGHTSREG_keys,"FFUNDACCT","FCUSTNO","FREG_DATE"),
	/**
	*权益登记详单表(基金账号,交易账号,红利发放日)
	**/
	T_TA_RIGHTSREGDETIAL(Keys.T_TA_RIGHTSREGDETIAL_keys,"FFUNDACCT","FCUSTNO","FDEAL_DATE"),
	/**
	*销售服务费明细
	**/
	T_TA_SERVICECHARGEBLOTTER(Keys.T_TA_SERVICECHARGEBLOTTER_keys,"FCALCULATEDATE","FPD_CODE","FSEATNO");
	
	/**
	 * 表对应的所有字段
	 */
	private List<String> list;
	/**
	 * 表对应的唯一标识的下标
	 */
	private String[] str;
	
	Tables(List<String> list,String ...s){
		this.list = list;
		this.str= s;
	}
	Tables(){
		
	}
	public List<String> getList(){
		return this.list;
	}
	public String[] getUnique(){
		return this.str;
	}

}
