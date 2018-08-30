package com.yss.db;

import java.util.List;

public enum Tables {
//	/**
//	 * 账户确认02导出表
//	 */
//	T_PD_I_02_OUT,
//	/**
//	 * 交易确认04导出表
//	 */
//	T_PD_I_04_OUT,
//	/**
//	 * 基金账户对账数据05导出表
//	 */
//	T_PD_I_05_OUT,
//	/**
//	分红数据06导出表,
//	 */
//	T_PD_I_06_OUT,
//	/**
//	*基金动态信息数据项07导出表,
//	*/
//	T_PD_I_07_OUT,
//	/**
//	*公告08导出表,
//	*/
//	T_PD_I_08_OUT,
//	/**
//	*红利汇总数据项09导出表,
//	*/
//	T_PD_I_09_OUT,
//	/**
//	*日交割汇总数据项10导出表,
//	*/
//	T_PD_I_10_OUT,
//	/**
//	*TA发送的业务申请汇总数据项11导出表,
//	*/
//	T_PD_I_11_OUT,
//	/**
//	*TA发送的业务确认汇总数据项12导出表,
//	*/
//	T_PD_I_12_OUT,
//	/**
//	 *销售发送的业务申请汇总数据项13导入表,
//	*/
//	T_PD_I_13_IN,
//	/**
//	*参与人及结算席位21导出表,
//	*/
//	T_PD_I_21_OUT,
//	/**
//	*其他类申请文件23导入表,
//	*/
//	T_PD_I_23_IN,
//	/**
//	*其他类确认文件24导出表,
//	*/
//	T_PD_I_24_OUT,
//	/**
//	*资金清算文件25导出表
//	*/
//	T_PD_I_25_OUT,
	/**
	*基金份额调整和对账文件导出表,
	**/
	T_PD_I_ADJUSTSHARE_OUT(Keys.T_PD_I_ADJUSTSHARE_OUT_keys,"FNUM"),
	/**
	*申购核算数据导出表,
	**/
	T_PD_I_ALLOT_OUT(Keys.T_PD_I_ALLOT_OUT_keys,"FNUM"),
	/**
	*分红核算数据导出表,
	**/
	T_PD_I_BONUS_OUT(Keys.T_PD_I_BONUS_OUT_keys,"FNUM"),
	/**
	*基金基础参数C1导出表,
	**/
	T_PD_I_C1_OUT(Keys.T_PD_I_C1_OUT_keys,"SEQUENCENO"),
	/**
	*基金代理关系C2导出表,
	**/
	T_PD_I_C2_OUT(Keys.T_PD_I_C2_OUT_keys,"SEQUENCENO"),
	/**
	*基金转换关系C3导出表,
	**/
	T_PD_I_C3_OUT(Keys.T_PD_I_C3_OUT_keys,"SEQUENCENO"),
	/**
	*基金分红方案C4导出表,
	**/
	T_PD_I_C4_OUT(Keys.T_PD_I_C4_OUT_keys,"SEQUENCENO"),
	/**
	*基金费率C5导出表,
	**/
	T_PD_I_C5_OUT(Keys.T_PD_I_C5_OUT_keys,"SEQUENCENO"),
	/**
	*转托管数据导出表,
	**/
	T_PD_I_CHANGEAGENCY_OUT(Keys.T_PD_I_CHANGEAGENCY_OUT_keys,"FNUM"),
	/**
	*基金收益情况数据文件,
	**/
	T_PD_I_INCOME_OUT(Keys.T_PD_I_INCOME_OUT_keys,"FNUM"),
	/**
	*净值导入表,
	**/
	T_PD_I_NAV_IN(Keys.T_PD_I_NAV_IN_keys,"FID"),
	
	//FIXME:数据库里不存在这张表
//	/**
//	*净值导出表,
//	**/
//	T_PD_I_NAV_OUT(Keys.T_PD_I_NAV_OUT_keys,"FID"),
	/**
	*净值补充数据导入表,
	**/
	T_PD_I_NAVADD_IN(Keys.T_PD_I_NAVADD_IN_keys,"FID"),
	
	//FIXME:数据库里不存在这张表
//	/**
//	*净值补充数据导入表,
//	**/
//	T_PD_I_NAVADD_OUT(Keys.T_PD_I_NAVADD_OUT_keys,"FID"),
	/**
	*赎回核算数据导出表,
	**/
	T_PD_I_REDEEM_OUT(Keys.T_PD_I_REDEEM_OUT_keys,"FNUM"),
	/**
	*认购核算数据导出表
	**/
	T_PD_I_SUB_OUT(Keys.T_PD_I_SUB_OUT_keys,"FNUM"),
	/**
	*基金转换转入数据导出表
	**/
	T_PD_I_SWITCHIN_OUT(Keys.T_PD_I_SWITCHIN_OUT_keys,"FNUM"),
	/**
	*基金转换转出数据导出表
	**/
	T_PD_I_SWITCHOUT_OUT(Keys.T_PD_I_SWITCHOUT_OUT_keys,"FNUM"),
	/**
	*业绩报酬流水
	**/
	T_TA_ACHIEVEMENTPAYBLOTTER(Keys.T_TA_ACHIEVEMENTPAYBLOTTER_keys, "FAPPNO"),
	/**
	*业绩报酬余额明细
	**/
	T_TA_ACHIEVEPAYBLOTTERDETAIL(Keys.T_TA_ACHIEVEPAYBLOTTERDETAIL_keys, "FAPPNO"),
	/**
	*账户确认
	**/
	T_TA_ACKFDACBLOTTER(Keys.T_TA_ACKFDACBLOTTER_keys,"FACKNO"),
	/**
	*交易确认
	**/
	T_TA_ACKTRADEBLOTTER(Keys.T_TA_ACKTRADEBLOTTER_keys,"FACKNO"),
	/**
	*申请号确认号对应关系表
	**/
	T_TA_APPNOACKNOREF(Keys.T_TA_APPNOACKNOREF_keys,"FID"),
	
	//FIXME:数据库里不存在这张表
//	/**
//	*余额构成
//	**/
//	T_TA_BALCONS(Keys.T_TA_BALCONS_keys,"FID"),
	/**
	*余额构成流水
	**/
	T_TA_BALCONSBLOTTER(Keys.T_TA_BALCONSBLOTTER_keys,"FID"),
	
	//FIXME:数据库里不存在这张表
//	/**
//	*清算中用到的参数默认值
//	**/
//	T_TA_DEFAULTPARAM,
	/**
	*份额余额
	**/
	T_TA_FUNDBALANCE(Keys.T_TA_FUNDBALANCE_keys,"FID"),
	/**
	*权益登记信息
	**/
	T_TA_RIGHTSREG(Keys.T_TA_RIGHTSREG_keys,"FID"),
	/**
	*权益登记详单表
	**/
	T_TA_RIGHTSREGDETIAL(Keys.T_TA_RIGHTSREGDETIAL_keys,"FID"),
	/**
	*销售服务费明细
	**/
	T_TA_SERVICECHARGEBLOTTER(Keys.T_TA_SERVICECHARGEBLOTTER_keys,"FID");
	
	/**
	 * 表对应的所有字段
	 */
	private List<String> list;
	/**
	 * 表对应的唯一标识的下标
	 */
	private String i;
	
	Tables(List<String> list,String i){
		this.list = list;
		this.i = i;
	}
	Tables(){
		
	}
	public List<String> getList(){
		return this.list;
	}
	public String getPrimary(){
		return this.i;
	}

}
