package com.yss.method;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yss.common.AllElementEnum;
import com.yss.common.BaseInterface;
import com.yss.common.Common;
import com.yss.common.Common.CommonElementEnum;
import com.yss.common.ElementEnum;
import com.yss.common.MyResponse;
import com.yss.common.PageEnum;
import com.yss.common.ReadFromExcel;
import com.yss.method.CheckMenu.CheckMenuElement;

public class HeSuanJiGouXinXi implements BaseInterface {

	@Override
	public boolean before() {
		Common.logInfo("before");
		
		try {
			Thread.sleep(Common.SLEEP_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 点击TAB
		List<String> TATabList = ReadFromExcel.elementsFromExcel.get(PageEnum.TAB_MENU).get("TAdengjiguohu");
		MyResponse TAdengjiguohuResponse = Common.getWebElementOld(TATabList.get(1), TATabList.get(0));
		if( (int)TAdengjiguohuResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Click TAdengjiguohu failed");
			return false;
		}
		Common.click((WebElement)TAdengjiguohuResponse.get("ele"));
		//点击机构参数
		MyResponse jiGouCanShuResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.JIGOUCANSHU_1);
		if((int) jiGouCanShuResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of jigoucanshu1 failed");
			return false;
		}
		Common.click((WebElement)jiGouCanShuResponse.get("ele"));
	    //点击核算机构信息
	    MyResponse heSuanJiGouXinXiResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.HESUANJIGOUXINXI_2);
		if((int) heSuanJiGouXinXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of heSuanJigougingiResponse failed");
			return false;
		}
		Common.click((WebElement)heSuanJiGouXinXiResponse.get("ele"));
	
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addOne() {
		Common.logInfo("addOne");

		//点击新增
		Common.clickTopAdd();
		//获取第一条数据
		HashMap<HeSuanJiGouXinXiEnum, String> data = ReadFromExcel.dataForHeSuanJiGouFromExcel.get(0);
		Set<HeSuanJiGouXinXiEnum> set = data.keySet();
		Iterator<HeSuanJiGouXinXiEnum> iterator = set.iterator();
		while( iterator.hasNext() ){
			HeSuanJiGouXinXiEnum eunm = iterator.next();
			//获取所有add需要的元素
			MyResponse heSuanJiGouXinXiResponse = Common.getWebElement(PageEnum.HESUANJIGOUXINXI,AllElementEnum.HeSuanJiGouXinXiElement,eunm);
			if ((int) heSuanJiGouXinXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED) {
				Common.logError("get element data of heSuanJiGouXinXiResponse failed");
				return false;
			}
			//processTable
			//将第一条数据填到表单中
			WebElement ele = (WebElement)heSuanJiGouXinXiResponse.get("ele");
			String remark = heSuanJiGouXinXiResponse.get("rem").toString();
			MyResponse setHeSuanJiGouXinXiREsponse = Common.proccessTable(PageEnum.HESUANJIGOUXINXI,AllElementEnum.HeSuanJiGouXinXiElement, eunm, ele, data.get(eunm),  remark );
			if( (int)setHeSuanJiGouXinXiREsponse.get(MyResponse.STATUS) == MyResponse.FAILED ){		
				Common.logError("set parameter of"+eunm+"failed");
				return false;
			}
		}
		//点击提交
		MyResponse commitResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.COMMIT);
		if((int)commitResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Elemnent of "+commitResponse.get("ele")+" failed");
			return false;
		}
		MyResponse clickCommitResponse = Common.click((WebElement)commitResponse.get("ele"));
		if((int)clickCommitResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("click Elemnent of "+clickCommitResponse.get("ele")+" failed");
			return false;
		}
		//点击确定
		Common.clickYES();
		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean addAFew() {
		Common.logInfo("addAFew");

		// 获取元素 --可如下写也可以写入for循环中
		MyResponse jiGouDaiMaElementData = Common.getWebElement(
				PageEnum.HESUANJIGOUXINXI,
				AllElementEnum.HeSuanJiGouXinXiElement,
				HeSuanJiGouXinXiEnum.JIGOUDAIMA);
		MyResponse jiGouMingChengElementData = Common.getWebElement(
				PageEnum.HESUANJIGOUXINXI,
				AllElementEnum.HeSuanJiGouXinXiElement,
				HeSuanJiGouXinXiEnum.JIGOUMINGCHENG);
		MyResponse jiGouZhuangTaiElementData = Common.getWebElement(
				PageEnum.HESUANJIGOUXINXI,
				AllElementEnum.HeSuanJiGouXinXiElement,
				HeSuanJiGouXinXiEnum.JIGOUZHUANGTAI);
		MyResponse zhuXiaoElementData = Common.getWebElement(
				PageEnum.HESUANJIGOUXINXI,
				AllElementEnum.HeSuanJiGouXinXiElement,
				HeSuanJiGouXinXiEnum.ZHUXIAO);
		MyResponse tingYongElementData = Common.getWebElement(
				PageEnum.HESUANJIGOUXINXI,
				AllElementEnum.HeSuanJiGouXinXiElement,
				HeSuanJiGouXinXiEnum.TINGYONG);
		MyResponse dianHuaElementData = Common.getWebElement(
				PageEnum.HESUANJIGOUXINXI,
				AllElementEnum.HeSuanJiGouXinXiElement,
				HeSuanJiGouXinXiEnum.DIANHUA);
		MyResponse jiKouBanBenElementData = Common.getWebElement(
				PageEnum.HESUANJIGOUXINXI,
				AllElementEnum.HeSuanJiGouXinXiElement,
				HeSuanJiGouXinXiEnum.JIEKOUBANBEN);
		MyResponse jieKou263ElementData = Common.getWebElement(
				PageEnum.HESUANJIGOUXINXI,
				AllElementEnum.HeSuanJiGouXinXiElement,
				HeSuanJiGouXinXiEnum.JIEKOU_2_6_3);
		MyResponse jieKou265ElementData = Common.getWebElement(
				PageEnum.HESUANJIGOUXINXI,
				AllElementEnum.HeSuanJiGouXinXiElement,
				HeSuanJiGouXinXiEnum.JIEKOU_2_6_5);
		MyResponse diZhiElementData = Common.getWebElement(
				PageEnum.HESUANJIGOUXINXI,
				AllElementEnum.HeSuanJiGouXinXiElement,
				HeSuanJiGouXinXiEnum.DIZHI);
		MyResponse wangZhiElementData = Common.getWebElement(
				PageEnum.HESUANJIGOUXINXI,
				AllElementEnum.HeSuanJiGouXinXiElement,
				HeSuanJiGouXinXiEnum.WANGZHI);
		MyResponse daoRuLuJingElementData = Common.getWebElement(
				PageEnum.HESUANJIGOUXINXI,
				AllElementEnum.HeSuanJiGouXinXiElement,
				HeSuanJiGouXinXiEnum.DAORULUJING);
		MyResponse daiChuLuJingElementData = Common.getWebElement(
				PageEnum.HESUANJIGOUXINXI,
				AllElementEnum.HeSuanJiGouXinXiElement,
				HeSuanJiGouXinXiEnum.DAOCHULUJING);

		Map<HeSuanJiGouXinXiEnum, String> mapOfElementsData = new HashMap<HeSuanJiGouXinXiEnum, String>();
		for (HeSuanJiGouXinXiEnum heSuanJiGouXinXiEnum : HeSuanJiGouXinXiEnum
				.values()) {
			MyResponse elementData = Common.getWebElement(
					PageEnum.HESUANJIGOUXINXI,
					AllElementEnum.HeSuanJiGouXinXiElement,
					heSuanJiGouXinXiEnum);
			String s = (String) elementData.get(MyResponse.STATUS);
			if ("0".equals((String) elementData.get(MyResponse.STATUS))) {

			}
		}
		Map<HeSuanJiGouXinXiEnum, List> mapOfValues = new HashMap<HeSuanJiGouXinXiEnum, List>();
		// /从Excel 取值
		List<HashMap<HeSuanJiGouXinXiEnum, String>> dataForHeSuanJiGouFromExcel = ReadFromExcel.dataForHeSuanJiGouFromExcel;
		// 循环取出每一行数据的值
		for (HashMap<HeSuanJiGouXinXiEnum, String> hashMap : dataForHeSuanJiGouFromExcel) {
			// 循环取出该行数据的值
			for (int j = 0; j < HeSuanJiGouXinXiEnum.values().length; j++) {
				String strOfValue = hashMap
						.get(HeSuanJiGouXinXiEnum.values()[j]);
				MyResponse elementData = Common.getWebElement(PageEnum.HESUANJIGOUXINXI,AllElementEnum.HeSuanJiGouXinXiElement,HeSuanJiGouXinXiEnum.values()[j]);
				List list = (List) elementData.get("list");
			}
		}
		// dataForHeSuanJiGouFromExcel.size();
		// Excel 第一行
		HashMap<HeSuanJiGouXinXiEnum, String> hashMap = dataForHeSuanJiGouFromExcel
				.get(0);
		// Excel 机构代码
		String string = hashMap.get(HeSuanJiGouXinXiEnum.JIGOUDAIMA);

		return false;
	}

	@Override
	public boolean review() {
		return false;
	}

	@Override
	public boolean reviewAFew() {
		// TODO Auto-generated method stub
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

	@Override
	public Boolean after() {
		// TODO Auto-generated method stub
		return null;
	}

	public enum HeSuanJiGouXinXiEnum implements ElementEnum {
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
