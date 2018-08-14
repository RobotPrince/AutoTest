package com.yss.method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yss.common.AllElementEnum;
import com.yss.common.BaseInterface;
import com.yss.common.Common;
import com.yss.common.ElementEnum;
import com.yss.common.MyResponse;
import com.yss.common.PageEnum;
import com.yss.common.ReadFromExcel;
import com.yss.method.CheckMenu.CheckMenuElement;

public class HeSuanJiGouXinXi implements BaseInterface {
	
	@SuppressWarnings("unchecked")
	public HeSuanJiGouXinXi() throws InterruptedException{
		Common.logInfo("HeSuanJiGouXinXi");
		Thread.sleep(2000l);
		
		// 点击TAB
		List<String> TATabList = ReadFromExcel.elementsFromExcel.get(PageEnum.TAB_MENU).get("TAdengjiguohu");
		Common.getWebElement(TATabList.get(1), TATabList.get(0)).click();
		Thread.sleep(2000l);
		//双击机构参数
		MyResponse jiGouCanShuElementData = Common.getElementData(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.JIGOUCANSHU_1);
	    Actions actions=new Actions(Common.driver);
	    actions.doubleClick(Common.getWebElement(((List<String>)jiGouCanShuElementData.get("list")).get(1), ((List<String>)jiGouCanShuElementData.get("list")).get(0))).perform();
		Thread.sleep(2000l);
	    //点击核算机构信息
	    MyResponse heSuanJiGouXinXiElementData = Common.getElementData(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.HESUANJIGOUXINXI_2);
		Common.getWebElement(((List<String>)heSuanJiGouXinXiElementData.get("list")).get(1), ((List<String>)heSuanJiGouXinXiElementData.get("list")).get(0)).click();
	}
	@Override
	public boolean addOne() {
		Common.logInfo("addOne");
		
		//获取元素 --可如下写也可以写入for循环中
		MyResponse jiGouDaiMaElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.JIGOUDAIMA);
		List<HashMap<HeSuanJiGouXinXiEnum, String>> dataForHeSuanJiGouFromExcel = ReadFromExcel.dataForHeSuanJiGouFromExcel;
		HashMap<HeSuanJiGouXinXiEnum, String> hashMap = dataForHeSuanJiGouFromExcel.get(0);
		
		if(1 == (int)(jiGouDaiMaElementData.get(MyResponse.STATUS))){
			List<String> list = (List)jiGouDaiMaElementData.get("list");
			//
			Common.driver.switchTo().frame(Common.getWebElement(".//div[@class='x-panel-bwrap']//iframe[@name]", "xpath"));
			Common.driver.switchTo().frame(Common.getWebElement(".//iframe", "xpath"));
			WebElement webElement = Common.getWebElement(".//*[@id='vcOrgCode']", list.get(0));
			

			webElement.sendKeys(hashMap.get(HeSuanJiGouXinXiEnum.JIGOUDAIMA));
		}
//		MyResponse jiGouMingChengElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.JIGOUMINGCHENG);
//		MyResponse jiGouZhuangTaiElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.JIGOUZHUANGTAI);
//		MyResponse zhuXiaoElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.ZHUXIAO);
//		MyResponse tingYongElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.TINGYONG);
//		MyResponse dianHuaElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.DIANHUA);
//		MyResponse jiKouBanBenElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.JIEKOUBANBEN);
//		MyResponse jieKou263ElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.JIEKOU_2_6_3);
//		MyResponse jieKou265ElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.JIEKOU_2_6_5);
//		MyResponse diZhiElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.DIZHI);
//		MyResponse wangZhiElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.WANGZHI);
//		MyResponse daoRuLuJingElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.DAORULUJING);
//		MyResponse daiChuLuJingElementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.DAOCHULUJING);
//		
//		//获取值
//		elementData.get(key);
		return true;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public boolean addAFew() {
		Common.logInfo("addAFew");
		
//		获取元素 --可如下写也可以写入for循环中
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
		
		Map<HeSuanJiGouXinXiEnum, String> mapOfElementsData = new HashMap<HeSuanJiGouXinXiEnum,String>();
		for(HeSuanJiGouXinXiEnum heSuanJiGouXinXiEnum : HeSuanJiGouXinXiEnum.values()){
			MyResponse elementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, heSuanJiGouXinXiEnum);
			String s = (String)elementData.get(MyResponse.STATUS);
			if("0".equals((String)elementData.get(MyResponse.STATUS))){
				
			}
		}
		Map<HeSuanJiGouXinXiEnum, List> mapOfValues = new HashMap<HeSuanJiGouXinXiEnum, List>();
		///从Excel 取值
		List<HashMap<HeSuanJiGouXinXiEnum, String>> dataForHeSuanJiGouFromExcel = ReadFromExcel.dataForHeSuanJiGouFromExcel;
		//循环取出每一行数据的值
		for(HashMap<HeSuanJiGouXinXiEnum, String> hashMap : dataForHeSuanJiGouFromExcel){
			//循环取出该行数据的值
			for( int j = 0; j < HeSuanJiGouXinXiEnum.values().length; j++){
				String strOfValue = hashMap.get(HeSuanJiGouXinXiEnum.values()[j]);
				MyResponse elementData = Common.getElementData(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.values()[j]);
				List list = (List) elementData.get("list");
			}
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
