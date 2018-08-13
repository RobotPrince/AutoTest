package com.yss.method;

import java.util.HashMap;
import java.util.List;

import com.yss.common.AllElementEnum;
import com.yss.common.BaseInterface;
import com.yss.common.Common;
import com.yss.common.ElementEnum;
import com.yss.common.MyResponse;
import com.yss.common.PageEnum;
import com.yss.common.ReadFromExcel;
import com.yss.method.HeSuanJiGouXinXi.HeSuanJiGouXinXiEnum;

public class XiaoShouJiGouXinXi implements BaseInterface {

	
	
	@Override
	public boolean review() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unreviewed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addOne() {
		Common.logInfo("addOne");
		
		MyResponse[] myResponse = new MyResponse[XiaoShouJiGouXinXiEnum.values().length];
		int i =0;
		//获取元素 --可如下写也可以写入for循环中
		for(XiaoShouJiGouXinXiEnum xsjxx : XiaoShouJiGouXinXiEnum.values()){
			
			myResponse[i++] = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement,xsjxx);
		}
				
		//获取值
		///从Excel 值
		List<HashMap<HeSuanJiGouXinXiEnum, String>> dataForHeSuanJiGouFromExcel = ReadFromExcel.dataForHeSuanJiGouFromExcel;
		//Excel 第一行
		HashMap<HeSuanJiGouXinXiEnum, String> hashMap = dataForHeSuanJiGouFromExcel.get(0);
		//Excel 机构代码
		String string = hashMap.get(HeSuanJiGouXinXiEnum.JIGOUDAIMA);
		return false;
	}

	@Override
	public boolean addAFew() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public enum XiaoShouJiGouXinXiEnum implements ElementEnum{
		/**
		 * 机构代码
		 */
		JIGOUDAIMA,
		/**
		 * 机构名称
		 */
		JIGOUMINGCHENG,
		/**
		 * 机构状态
		 */
		JIGOUZHUANGTAI,
		/**
		 * 机构状态——正常
		 */
		ZHENGCHANG,
		/**
		 * 机构状态——注销
		 */
		ZHUXIAO,
		/**
		 * 机构状态——停用
		 */
		TINGYONG,
		/**
		 * 电话
		 */
		DIANHUA,
		/**
		 * 接口版本
		 */
		JIEKOUBANBEN,
		/**
		 * 接口版本2.63
		 */
		JIEKOU_2_6_3,
		/**
		 * 接口版本2.65
		 */
		JIEKOU_2_6_5,
		/**
		 * 地址
		 */
		DIZHI,
		/**
		 * 网址
		 */
		WANGZHI,
		/**
		 * 接口导入路径
		 */
		DAORULUJING,
		/**
		 * 接口导出路径
		 */
		DAOCHULUJING
	}

}
