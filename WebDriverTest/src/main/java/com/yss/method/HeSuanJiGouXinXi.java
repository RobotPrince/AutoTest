package com.yss.method;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebElement;

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

		//循环所有数据-这是与AddOne的唯一不同之处
		int sizeOfData = ReadFromExcel.dataForHeSuanJiGouFromExcel.size();
		for(int i = 0; i < sizeOfData; i++){
			//点击新增
			Common.clickTopAdd();
			HashMap<HeSuanJiGouXinXiEnum, String> data = ReadFromExcel.dataForHeSuanJiGouFromExcel.get(i);
			Set<HeSuanJiGouXinXiEnum> set = data.keySet();
			Iterator<HeSuanJiGouXinXiEnum> iterator = set.iterator();
			while( iterator.hasNext() ){
				//isChecked在这里不需要做任何操作
				HeSuanJiGouXinXiEnum eunm = iterator.next();
				if(eunm.equals(HeSuanJiGouXinXiEnum.ISCHECKED)){
					continue;
				}
				//获取add需要的元素
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
		}
		return true;
	}

	@Override
	public boolean review() {
		return true;
	}

	@Override
	public boolean reviewAFew() {
		Common.logInfo("reviewAFew");
		
		//切换driver到default
		Common.driver.switchTo().defaultContent();
		// 切换driver到top
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		//获取页面所有复选框数目
		MyResponse itemCheckBoxResponse = Common.getWebElements(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.ITEM_CHECKBOX);
		if((int)itemCheckBoxResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get Elements of"+CommonElementEnum.ITEM_CHECKBOX+"failed");
			return false;
		}
		//第一步应是把所有项目变成反审核的状态
		List<WebElement> itemList = (List<WebElement>)itemCheckBoxResponse.get("ele");
		for(WebElement webElement : itemList){
			MyResponse clickCheckBoxResponse = Common.click(webElement);
			if((int)clickCheckBoxResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("Click checkbox of"+webElement+"failed");
				return false;			
			}
		}
		MyResponse unreviewTopResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.UNREVIEW_TOP);
		if((int)unreviewTopResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of unreivewtop failed");
			return false;			
		}
		MyResponse clickUnreviewTopResponse = Common.click((WebElement)unreviewTopResponse.get("ele"));
		if((int)unreviewTopResponse.get(clickUnreviewTopResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Click checkbox of UnreviewTop failed");
			return false;			
		}

		//点击确定
		 MyResponse clickYesResponse = Common.clickYES();
		 if((int)clickYesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			//点击是
			MyResponse popupyesResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_YES);
			if((int)popupyesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("get element of popup yes failed");
				return false;
			}
			MyResponse clickPopupYes = Common.click((WebElement)popupyesResponse.get("ele"));
			if((int)clickPopupYes.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("Click element of popup yes failed");
				return false;
			}
			//点击确定
			MyResponse clickYesRes = Common.clickYES();
			if((int)clickYesRes.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("Click Yes failed");
				return false;
			}
		 }
		 //去掉所有被勾上的复选框
		itemCheckBoxResponse = Common.getWebElements(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.ITEM_CHECKBOX);
		itemList = (List<WebElement>)itemCheckBoxResponse.get("ele");
		for(WebElement webElement : itemList){
			MyResponse clickCheckBoxResponse = Common.click(webElement);
			if((int)clickCheckBoxResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("Click checkbox of"+webElement+"failed");
				return false;			
			}
		}
	 
		//循环所有数据
		int sizeOfData = ReadFromExcel.dataForHeSuanJiGouFromExcel.size();
		for(int i = 0; i < sizeOfData; i++){
			HashMap<HeSuanJiGouXinXiEnum, String> data = ReadFromExcel.dataForHeSuanJiGouFromExcel.get(i);
			if("Y".equalsIgnoreCase(data.get(HeSuanJiGouXinXiEnum.ISCHECKED))){
				//点击复选框
				MyResponse clickResponse = Common.click(itemList.get(i));
				 if((int)clickResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
					 Common.logError("Click checkbox of "+itemList.get(i)+" failed");
					 return false;
				 }
			}
		}
		//点击顶部的review
		MyResponse reviewTopResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.REVIEW_TOP);
		if( (int)reviewTopResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of reviewTop failed");
			return false;
		}
		MyResponse clickReviewTopResponse = Common.click((WebElement)reviewTopResponse.get("ele"));
		if( (int)clickReviewTopResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Click element of ReviewTop failed");
			return false;
		}
		//点击确定
		clickYesResponse = Common.clickYES();
		 if((int)clickYesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
				//点击是
			MyResponse popupyesResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_YES);
			if((int)popupyesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("get element of popup yes failed");
				return false;
			}
			MyResponse clickPopupYes = Common.click((WebElement)popupyesResponse.get("ele"));
			if((int)clickPopupYes.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("Click element of popup yes failed");
				return false;
			}
			//点击确定
			MyResponse clickYesResponse2 = Common.clickYES();
			if((int)clickYesResponse2.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("Click yes failed");
				return false;
			}
		 }
		//去掉所有被勾上的复选框
		itemCheckBoxResponse = Common.getWebElements(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.ITEM_CHECKBOX);
		itemList = (List<WebElement>)itemCheckBoxResponse.get("ele");
		for(int i = 0; i < sizeOfData; i++){
			HashMap<HeSuanJiGouXinXiEnum, String> data = ReadFromExcel.dataForHeSuanJiGouFromExcel.get(i);
			if("Y".equalsIgnoreCase(data.get(HeSuanJiGouXinXiEnum.ISCHECKED))){
				//点击复选框
				MyResponse clickResponse = Common.click(itemList.get(i));
				 if((int)clickResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
					 Common.logError("Click checkbox of "+itemList.get(i)+" failed");
					 return false;
				 }
			}
		}
		return true;
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
		DAOCHULUJING,
		/**
		 * 是否需要审核
		 */
		ISCHECKED
	}

}
