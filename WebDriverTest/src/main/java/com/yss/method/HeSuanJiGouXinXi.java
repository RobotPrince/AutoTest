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

public class HeSuanJiGouXinXi implements BaseInterface {
	
	@Override
	public boolean addOne() {
		Common.logInfo("addOne");
		
		//获取元素 --可如下写也可以写入for循环中
		MyResponse jiGouDaiMaElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.JIGOUDAIMA);
		MyResponse jiGouMingChengElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.JIGOUMINGCHENG);
		MyResponse jiGouZhuangTaiElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.JIGOUZHUANGTAI);
		MyResponse zhuXiaoElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.ZHUXIAO);
		MyResponse tingYongElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.TINGYONG);
		MyResponse dianHuaElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.DIANHUA);
		MyResponse jiKouBanBenElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.JIEKOUBANBEN);
		MyResponse jieKou263ElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.JIEKOU_2_6_3);
		MyResponse jieKou265ElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.JIEKOU_2_6_5);
		MyResponse diZhiElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.DIZHI);
		MyResponse wangZhiElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.WANGZHI);
		MyResponse daoRuLuJingElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.DAORULUJING);
		MyResponse daiChuLuJingElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.DAOCHULUJING);
		
		//获取值
		MyResponse elementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI,AllElementEnum.HeSuanJiGouXinXiElement,HeSuanJiGouXinXiEnum.JIGOUDAIMA);
		return false;
	}

	@Override
	public boolean addAFew() {
		Common.logInfo("addAFew");
		
		
		
//		Common.getElementData();
		
		///从Excel 值
		
		List<HashMap<HeSuanJiGouXinXiEnum, String>> dataForHeSuanJiGouFromExcel = ReadFromExcel.dataForHeSuanJiGouFromExcel;
		
		for(HashMap hm : dataForHeSuanJiGouFromExcel){
			
		}
//		dataForHeSuanJiGouFromExcel.size();
		//Excel 第一行
		HashMap<HeSuanJiGouXinXiEnum, String> hashMap = dataForHeSuanJiGouFromExcel.get(0);
		//Excel 机构代码
		String string = hashMap.get(HeSuanJiGouXinXiEnum.JIGOUDAIMA);
		
		
		return false;
	}
	
	@Override
	public boolean review() {
		return false;
	}

	@Override
	public boolean unreviewed() {
		return false;
	}

	@Override
	public boolean delete() {
		return false;
	}
	
	public enum HeSuanJiGouXinXiEnum implements ElementEnum{
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
