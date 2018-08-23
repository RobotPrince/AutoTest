package com.yss.method;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.yss.common.AllElementEnum;
import com.yss.common.Common;
import com.yss.common.Common.CommonElementEnum;
import com.yss.common.ElementEnum;
import com.yss.common.MyResponse;
import com.yss.common.PageEnum;
import com.yss.common.ReadFromExcel;
import com.yss.method.CheckMenu.CheckMenuElement;

public class RiChangYunYingQingSuan{
	public boolean before() {
		Common.logInfo("before");
		
		try {
			Thread.sleep(Common.SLEEP_TIME);
		} catch (InterruptedException e) {
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
		//点击日常运营菜单
		MyResponse riChangYunYingSupervisorResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.RICHANGYUNYING_1);
		if((int) riChangYunYingSupervisorResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of richangyunyingsupervisor failed");
			return false;
		}
		Common.click((WebElement)riChangYunYingSupervisorResponse.get("ele"));
	    //点击日常运营
	    MyResponse riChangYunYingResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.RICHANGYUNYING_2);
		if((int) riChangYunYingResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of richangyunyingResponse failed");
			return false;
		}
		Common.click((WebElement)riChangYunYingResponse.get("ele"));
	
		return false;
	}
	
	/*
	 * 申请数据导入
	 */
	public boolean shenqingshujudaoru(){
		Common.logInfo("shenqingshujudaoru");
		
		//切换driver到default
		Common.driver.switchTo().defaultContent();
		// 切换driver到top
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		
		//获取元素
		MyResponse shenqingshujudaoruResponse = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.SHENQINGSHUJUDAORU);
		if((int)shenqingshujudaoruResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Element of"+RiChangYunYingQingSuanEnum.SHENQINGSHUJUDAORU+"failed");
			return false;
		}
		//点击元素
		WebElement shenQingShuJuDaoRuElement = (WebElement)shenqingshujudaoruResponse.get("ele");
		MyResponse shenQingShuJuDaoRuElementClickResponse = Common.click(shenQingShuJuDaoRuElement);
		if((int)shenQingShuJuDaoRuElementClickResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("click Element of"+shenQingShuJuDaoRuElementClickResponse+"failed");
			return false;
		}
		//点击popup‘是’
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
		
		
//		//正常写
//		for(RiChangYunYingQingSuanEnum enmu:RiChangYunYingQingSuanEnum.values()){
//			MyResponse jingZhiGuanLResponse = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, enmu);
//			if((int)jingZhiGuanLResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
//				Common.logError("get Element of"+RiChangYunYingQingSuanEnum.JINGZHIGUANLI+"failed");
//				return false;
//			}
//			WebElement we = (WebElement)jingZhiGuanLiResponse.get("ele");
//		}
		return true;
	}
	public boolean jizhiguanli(){
		Common.logInfo("jingzhiguanli");
		
		//切换driver到default
				Common.driver.switchTo().defaultContent();
				// 切换driver到top
				MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
				if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
					Common.logError("get element data of iframe1 failed");
					return false;
				}
				Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
				
				//获取元素
				MyResponse jingZhiGuanLiResponse = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.JINGZHIGUANLI);
				if((int)jingZhiGuanLiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
					Common.logError("get Element of"+RiChangYunYingQingSuanEnum.JINGZHIGUANLI+"failed");
					return false;
				}
				//点击元素
				WebElement jingZhiGuanLiElement = (WebElement)jingZhiGuanLiResponse.get("ele");
				MyResponse jingZhiGuanLiElementClickResponse = Common.click(jingZhiGuanLiElement);
				if((int)jingZhiGuanLiElementClickResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
					Common.logError("click Element of"+jingZhiGuanLiElement+"failed");
					return false;
				}
				//点击popup‘是’
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
		return true;
	}
	/*
	 * 账户检查
	 */
	public boolean zhanghujiancha(){
		Common.logInfo("zhanghujiancha");
		
		//切换driver到default
		Common.driver.switchTo().defaultContent();
		// 切换driver到top
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		return true;
	}
	/*
	 * 账户清算
	 */
	public boolean zhanghuqingsuan(){
		Common.logInfo("zhanghujiancha");
		
		//切换driver到default
		Common.driver.switchTo().defaultContent();
		// 切换driver到top
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		return true;
	}
	/*
	 * 交易检查
	 */
	public boolean jiaoyijiancha(){
		Common.logInfo("jiaoyijiancha");
		
		//切换driver到default
		Common.driver.switchTo().defaultContent();
		// 切换driver到top
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		return true;
	}
	/*
	 * 交易清算
	 */
	public boolean jiaoyiqingsuan(){
		Common.logInfo("jiaoyiqingsuan");
		
		//切换driver到default
		Common.driver.switchTo().defaultContent();
		// 切换driver到top
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		return true;
	}
	/*
	 * 确认数据导出
	 */
	public boolean querenshujudaochu(){
		Common.logInfo("querenshujudaochu");
		
		//切换driver到default
		Common.driver.switchTo().defaultContent();
		// 切换driver到top
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		return true;
	}
	
	public enum RiChangYunYingQingSuanEnum implements ElementEnum {
		/**
		 * 净值管理
		 */
		JINGZHIGUANLI,
		/**
		 * 净值导出
		 */
		JINGZHIDAOCHU,
		/**
		 * 申请数据导入
		 */
		SHENQINGSHUJUDAORU,
		/**
		 * 账户检查
		 */
		ZHANGHUJIANCHA,
		/**
		 * 账户清算
		 */
		ZHANGHUQINGSUANAN,
		/**
		 * 交易检查
		 */
		JIAOYIJIANCHA,
		/**
		 * 交易清算
		 */
		JIAOYIQINGSUAN,
		/**
		 * 确认数据导出
		 */
		QUERENSHUJUDAOCHU,
		/**
		 * 日终确认
		 */
		RIZHONGQUEREN,
		
	}
}
