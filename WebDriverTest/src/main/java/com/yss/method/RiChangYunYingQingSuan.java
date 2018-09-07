package com.yss.method;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
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
	/**
	 * 预先操作
	 * @return
	 */
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
	
		return true;
	}
	
	/**
	 * 净值管理(导入维护)
	 * @return
	 */
	public boolean jingzhiguanli(){
		Common.logInfo("jingzhiguanli");
		
//		//点击产品净值
//	    MyResponse chanpinjingzhiResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.CHANPINJINGZHI_2);
//		if((int) chanpinjingzhiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
//			Common.logError("Double click of chanpinjingzhiResponse failed");
//			return false;
//		}
//		Common.click((WebElement)chanpinjingzhiResponse.get("ele"));
//		// 切换driver到top
//		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
//		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
//			Common.logError("get element data of iframe1 failed");
//			return false;
//		}
//		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		
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
		//切换driver到iframe2
		MyResponse iframe2Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM2);
		if( (int)iframe2Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe2 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe2Response.get("ele"));
//		
//		//选择清算日期
//		MyResponse clearDataRes = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.QINGSUANRIQI);
//		if((int)clearDataRes.get(MyResponse.STATUS)==MyResponse.FAILED){
//			Common.logError("get element of clearData failed");
//			return false;
//		}
//		Common.setParameter((WebElement)clearDataRes.get("ele"),ReadFromExcel.dataForRiChangYunYingFromExcel.get(0).get(RiChangYunYingQingSuanEnum.QINGSUANRIQI));
//		//点击导入
//		MyResponse insertTopRes = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.INSERT_TOP);
//		if((int)insertTopRes.get(MyResponse.STATUS)==MyResponse.FAILED){
//			Common.logError("get element of insert top failed");
//			return false;
//		}
//		Common.click((WebElement)insertTopRes.get("ele"));
		//切换driver到iframe2
		MyResponse iframe2Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM2);
		if( (int)iframe2Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe2 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe2Response.get("ele"));	
		//选择导入核算机构
		MyResponse XuanzedaoruhesuanRes = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.XUANZEDAORUHESUANJIGOU);
		if((int)XuanzedaoruhesuanRes.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Get Element of XuanzedaoruhesuanRes failed!");
			return false;
		}
		Common.select((WebElement)XuanzedaoruhesuanRes.get("ele"),ReadFromExcel.dataForRiChangYunYingFromExcel.get(0).get(RiChangYunYingQingSuanEnum.XUANZEDAORUHESUANJIGOU));
		//选择导入日期
		MyResponse xuanzedaoruriqiRes = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.XUANZEDAORURIQI);
		if((int)xuanzedaoruriqiRes.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Get Element of xuanzedaoruriqiRes failed!");
			return false;
		}
		Common.setParameter((WebElement)xuanzedaoruriqiRes.get("ele"), ReadFromExcel.dataForRiChangYunYingFromExcel.get(0).get(RiChangYunYingQingSuanEnum.XUANZEDAORURIQI));

		//获取导入button
		MyResponse popupInsertesponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_INSERT);
		if((int)popupInsertesponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of popup yes failed");
			return false;
		}
		//点击导入button
		MyResponse clickPopupInsert = Common.click((WebElement)popupInsertesponse.get("ele"));
		if((int)clickPopupInsert.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Click element of popup insert failed");
			return false;
		}
		//点击刷新
		Common.clickRefresh();
		try{
			
			Thread.sleep(5000l);
		}catch(Exception e){
			
		}
		JavascriptExecutor js=(JavascriptExecutor)Common.driver;
        // roll down and keep the element to the center of browser
        js.executeScript("window.scrollTo(0,600)");
		//切换driver到default
		Common.driver.switchTo().defaultContent();
		// 切换driver到top
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		//获取页面所有复选框数目
		MyResponse itemCheckBoxResponse = Common.getWebElements(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.ITEM_CHECKBOX);
		if((int)itemCheckBoxResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get Elements of"+CommonElementEnum.ITEM_CHECKBOX+"failed");
			return false;
		}

		//把所有项目变成审核的状态
		List<WebElement> itemList = (List<WebElement>)itemCheckBoxResponse.get("ele");
		for(WebElement webElement : itemList){
			MyResponse clickCheckBoxResponse = Common.click(webElement);
			if((int)clickCheckBoxResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
				Common.logError("Click checkbox of"+webElement+"failed");
				return false;			
			}
		}
		MyResponse unreviewTopResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.REVIEW_TOP);
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

//		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
//		//等待100%
//		MyResponse getIsDoneRes = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.SHENQINGSHUJUDAORUISDONE);
//		if((int)getIsDoneRes.get(MyResponse.STATUS)==MyResponse.FAILED){
//			Common.logError("Get Element of 100% failed");
//			return false;
//		}
		//点击关闭
		Common.clickClose();
		return true;
	}
	
	/**
	 * 确认清算日期
	 * @return
	 */
	public boolean qingsuanriqi(){
		Common.logInfo("qingsuanriqi");
		
		//切换driver到default
		Common.driver.switchTo().defaultContent();
		//点击日常运营
	    MyResponse riChangYunYingResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.RICHANGYUNYING_2);
		if((int) riChangYunYingResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of richangyunyingResponse failed");
			return false;
		}
		Common.click((WebElement)riChangYunYingResponse.get("ele"));       
		// 切换driver到top
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		//设置清算日期
		MyResponse qingsuanriqiRes = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.QINGSUANRIQI);
		if((int)qingsuanriqiRes.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Get Element of qingusanriqi failed!");
			return false;
		}
		Common.setParameter((WebElement)qingsuanriqiRes.get("ele"),ReadFromExcel.dataForRiChangYunYingFromExcel.get(0).get(RiChangYunYingQingSuanEnum.QINGSUANRIQI));
		//点击右侧确定
		MyResponse querenRes = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.QINGSUANRIQIQUEREN);
		if((int)querenRes.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Get Element of queren failed!");
			return false;
		}
		Common.click((WebElement)querenRes.get("ele"));
		
		//点击弹出框确定
		Common.clickYES();

		return true;
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
		
		//获取申请数据导入元素
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
		//切换driver到iframe2
		MyResponse iframe2Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM2);
		if( (int)iframe2Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe2 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe2Response.get("ele"));
		//获取选择导入日期元素
		MyResponse xuanzedaoruriqiResponse = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.XUANZEDAORURIQI);
		if((int)xuanzedaoruriqiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Element of"+RiChangYunYingQingSuanEnum.XUANZEDAORURIQI+"failed");
			return false;
		}
		//输入选择导入日期
		MyResponse xuanzedaoruriqiSetParamResponse = Common.setParameter((WebElement)xuanzedaoruriqiResponse.get("ele"), ReadFromExcel.dataForRiChangYunYingFromExcel.get(0).get(RiChangYunYingQingSuanEnum.XUANZEDAORURIQI));
		if((int)xuanzedaoruriqiSetParamResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Element of"+RiChangYunYingQingSuanEnum.XUANZEDAORURIQI+"failed");
			return false;
		}
		//获取选择导入销售机构
		MyResponse xiaoshoujigouResponse = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.XUANZEDAORUXIAOSHOUJIGOU);
		if((int)xiaoshoujigouResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Element of"+RiChangYunYingQingSuanEnum.XUANZEDAORUXIAOSHOUJIGOU+"failed");
			return false;
		}
		//点击销售机构
		MyResponse xiaoshoujigouSelectRes = Common.select((WebElement)xiaoshoujigouResponse.get("ele"), ReadFromExcel.dataForRiChangYunYingFromExcel.get(0).get(RiChangYunYingQingSuanEnum.XUANZEDAORUXIAOSHOUJIGOU));
		if((int)xiaoshoujigouSelectRes.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Element of"+RiChangYunYingQingSuanEnum.XUANZEDAORUXIAOSHOUJIGOU+"failed");
			return false;
		}
		Common.click((WebElement)xuanzedaoruriqiResponse.get("ele"));
		//获取选择导入方式
		MyResponse daorufangshiResponse = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.DAORUFANGSHI);
		if((int)daorufangshiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Element of"+RiChangYunYingQingSuanEnum.DAORUFANGSHI+"failed");
			return false;
		}
		//点击导入方式
		MyResponse daorufangshiClickRes = Common.select((WebElement)daorufangshiResponse.get("ele"), ReadFromExcel.dataForRiChangYunYingFromExcel.get(0).get(RiChangYunYingQingSuanEnum.DAORUFANGSHI));
		if((int)daorufangshiClickRes.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Element of"+RiChangYunYingQingSuanEnum.DAORUFANGSHI+"failed");
			return false;
		}
		//获取导入button
		MyResponse popupInsertesponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_INSERT);
		if((int)popupInsertesponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of popup yes failed");
			return false;
		}
		//点击导入button
		MyResponse clickPopupInsert = Common.click((WebElement)popupInsertesponse.get("ele"));
		if((int)clickPopupInsert.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Click element of popup insert failed");
			return false;
		}
		Common.clickRefresh();
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		//等待100%
		MyResponse getIsDoneRes = Common.waitLongTimeForWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.SHENQINGSHUJUDAORUISDONE,Common.WAIT100PERCENT);
		if((int)getIsDoneRes.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Get Element of 100% failed");
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
		
		//获取账户检查元素
		MyResponse zhanghujianchaResponse = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.ZHANGHUJIANCHA);
		if((int)zhanghujianchaResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Element of"+RiChangYunYingQingSuanEnum.ZHANGHUJIANCHA+"failed");
			return false;
		}
		//点击元素
		WebElement zhanghujianchaElement = (WebElement)zhanghujianchaResponse.get("ele");
		MyResponse zhanghujianchaElementClickResponse = Common.click(zhanghujianchaElement);
		if((int)zhanghujianchaElementClickResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("click Element of"+zhanghujianchaElementClickResponse+"failed");
			return false;
		}
		//点击popup‘是’
		MyResponse popupyesResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_YES);
		if((int)popupyesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of popup yes failed");
			return false;
		}
		Common.click((WebElement)popupyesResponse.get("ele"));
		//等待100%
		MyResponse getIsDoneRes = Common.waitLongTimeForWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.ZHANGHUJIANCHAISDONE,Common.WAIT100PERCENT);
		if((int)getIsDoneRes.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Get Element of 100% failed");
			return false;
		}
		
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
				
				//获取账户清算元素
				MyResponse zhanghuqingsuanResponse = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.ZHANGHUQINGSUANAN);
				if((int)zhanghuqingsuanResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
					Common.logError("get Element of"+RiChangYunYingQingSuanEnum.ZHANGHUQINGSUANAN+"failed");
					return false;
				}
				//点击元素
				WebElement zhanghuqingsuanElement = (WebElement)zhanghuqingsuanResponse.get("ele");
				MyResponse zhanghuqingsuanElementClickResponse = Common.click(zhanghuqingsuanElement);
				if((int)zhanghuqingsuanElementClickResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
					Common.logError("click Element of"+zhanghuqingsuanElementClickResponse+"failed");
					return false;
				}
				//点击popup‘是’
				MyResponse popupyesResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_YES);
				if((int)popupyesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
					Common.logError("get element of popup yes failed");
					return false;
				}
				Common.click((WebElement)popupyesResponse.get("ele"));
				//等待100%
				MyResponse getIsDoneRes = Common.waitLongTimeForWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.ZHANGHUQINGSUANANISDONE,Common.WAIT100PERCENT);
				if((int)getIsDoneRes.get(MyResponse.STATUS)==MyResponse.FAILED){
					Common.logError("Get Element of 100% failed");
					return false;
				}
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
		
		//获取交易检查元素
		MyResponse jiaoyijianchaResponse = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.JIAOYIJIANCHA);
		if((int)jiaoyijianchaResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Element of"+RiChangYunYingQingSuanEnum.JIAOYIJIANCHA+"failed");
			return false;
		}
		//点击元素
		WebElement jiaoyijianchaElement = (WebElement)jiaoyijianchaResponse.get("ele");
		MyResponse jiaoyijianchaElementClickResponse = Common.click(jiaoyijianchaElement);
		if((int)jiaoyijianchaElementClickResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("click Element of"+jiaoyijianchaElementClickResponse+"failed");
			return false;
		}
		//点击popup‘是’
		MyResponse popupyesResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_YES);
		if((int)popupyesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of popup yes failed");
			return false;
		}
		Common.click((WebElement)popupyesResponse.get("ele"));
		//等待100%
		MyResponse getIsDoneRes = Common.waitLongTimeForWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.JIAOYIJIANCHAISDONE,Common.WAIT100PERCENT);
		if((int)getIsDoneRes.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Get Element of 100% failed");
			return false;
		}
		
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
		//获取交易清算元素
		MyResponse jiaoyiqingsuanResponse = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.JIAOYIQINGSUAN);
		if((int)jiaoyiqingsuanResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Element of"+RiChangYunYingQingSuanEnum.JIAOYIJIANCHA+"failed");
			return false;
		}
		//点击元素
		WebElement jiaoyiqingsuanElement = (WebElement)jiaoyiqingsuanResponse.get("ele");
		MyResponse jiaoyiqingsuanElementClickResponse = Common.click(jiaoyiqingsuanElement);
		if((int)jiaoyiqingsuanElementClickResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("click Element of"+jiaoyiqingsuanElementClickResponse+"failed");
			return false;
		}
		//点击popup‘是’
		MyResponse popupyesResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_YES);
		if((int)popupyesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of popup yes failed");
			return false;
		}
		Common.click((WebElement)popupyesResponse.get("ele"));
		//等待100%
		MyResponse getIsDoneRes = Common.waitLongTimeForWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.JIAOYIQINGSUANISDONE,Common.WAIT100PERCENT);
		if((int)getIsDoneRes.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Get Element of 100% failed");
			return false;
		}
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
		
		//获取确认数据导出元素
		MyResponse querenshujudaochuResponse = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.QUERENSHUJUDAOCHU);
		if((int)querenshujudaochuResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Element of"+RiChangYunYingQingSuanEnum.QUERENSHUJUDAOCHU+"failed");
			return false;
		}
		//点击元素
		WebElement querenshujudaochuElement = (WebElement)querenshujudaochuResponse.get("ele");
		MyResponse querenshujudaochuClickResponse = Common.click(querenshujudaochuElement);
		if((int)querenshujudaochuClickResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("click Element of"+querenshujudaochuClickResponse+"failed");
			return false;
		}
		//点击popup‘是’
		MyResponse popupyesResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_YES);
		if((int)popupyesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of popup yes failed");
			return false;
		}
		Common.click((WebElement)popupyesResponse.get("ele"));
		//等待100%
		MyResponse getIsDoneRes = Common.waitLongTimeForWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.QUERENSHUJUDAOCHUISDONE,Common.WAIT100PERCENT);
		if((int)getIsDoneRes.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Get Element of 100% failed");
			return false;
		}
		return true;
	}	
	/**
	 * 净值导出
	 */
	public boolean jingzhidaochu() {
		Common.logInfo("jingzhidaochu");
		
		//切换driver到default
		Common.driver.switchTo().defaultContent();
		// 切换driver到top
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		
		//获取净值导出元素
		MyResponse jingzhidaochuResponse = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.JINGZHIDAOCHU);
		if((int)jingzhidaochuResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Element of"+RiChangYunYingQingSuanEnum.JINGZHIDAOCHU+"failed");
			return false;
		}
		//点击元素
		WebElement jingzhidaochuElement = (WebElement)jingzhidaochuResponse.get("ele");
		MyResponse jingzhidaochuClickResponse = Common.click(jingzhidaochuElement);
		if((int)jingzhidaochuClickResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("click Element of"+jingzhidaochuClickResponse+"failed");
			return false;
		}
		//点击popup‘是’
		MyResponse popupyesResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_YES);
		if((int)popupyesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of popup yes failed");
			return false;
		}
		Common.click((WebElement)popupyesResponse.get("ele"));
		//等待100%
		MyResponse getIsDoneRes = Common.waitLongTimeForWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.JINGZHIDAOCHUISDONE,Common.WAIT100PERCENT);
		if((int)getIsDoneRes.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Get Element of 100% failed");
			return false;
		}
		return true;
	}
	/**
	 * 日终确认
	 * @return
	 */
	public boolean rizhongqueren() {
		Common.logInfo("jingzhidaochu");
		
		//切换driver到default
		Common.driver.switchTo().defaultContent();
		// 切换driver到top
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		
		//获取日终确认元素
		MyResponse rizhongquerenResponse = Common.getWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.RIZHONGQUEREN);
		if((int)rizhongquerenResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get Element of"+RiChangYunYingQingSuanEnum.RIZHONGQUEREN+"failed");
			return false;
		}
		//点击元素
		WebElement rizhongquerenElement = (WebElement)rizhongquerenResponse.get("ele");
		MyResponse rizhongquerenClickResponse = Common.click(rizhongquerenElement);
		if((int)rizhongquerenClickResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("click Element of"+rizhongquerenClickResponse+"failed");
			return false;
		}
		//点击popup‘是’
		MyResponse popupyesResponse = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_YES);
		if((int)popupyesResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get element of popup yes failed");
			return false;
		}
		Common.click((WebElement)popupyesResponse.get("ele"));
		//等待100%
		MyResponse getIsDoneRes = Common.waitLongTimeForWebElement(PageEnum.RICHANGYUNYINGQINGSUAN, AllElementEnum.RiChangYunYingQingSuanElement, RiChangYunYingQingSuanEnum.RIZHONGQUERENISDONE,Common.WAIT100PERCENT);
		if((int)getIsDoneRes.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("Get Element of 100% failed");
			return false;
		}
		return true;
	}
	public enum RiChangYunYingQingSuanEnum implements ElementEnum {
		/**
		 * 清算日期
		 */
		QINGSUANRIQI,
		/**
		 * 清算日期确认
		 */
		QINGSUANRIQIQUEREN,
		/**
		 * 选择导入核算机构
		 */
		XUANZEDAORUHESUANJIGOU,
		/**
		 * 净值管理
		 */
		JINGZHIGUANLI,
		/**
		 * 净值管理是否执行完成
		 */
		JINGZHIGUANLIISDONE,
		/**
		 * 净值导出
		 */
		JINGZHIDAOCHU,
		/**
		 * 净值导出是否执行完成
		 */
		JINGZHIDAOCHUISDONE,
		/**
		 * 申请数据导入
		 */
		SHENQINGSHUJUDAORU,
		/**
		 * 申请数据导入是否执行完成
		 */
		SHENQINGSHUJUDAORUISDONE,	
		/**
		 * 选择导入日期
		 */
		XUANZEDAORURIQI,
		
		/**
		 * 选择导入销售机构
		 */
		XUANZEDAORUXIAOSHOUJIGOU,
		/**
		 * 导入方式
		 */
		DAORUFANGSHI,
		/**
		 *
		 * 账户检查
		 */
		ZHANGHUJIANCHA,
		/**
		 * 账户检查是否执行完成
		 */
		ZHANGHUJIANCHAISDONE,
		/**
		 * 账户清算
		 */
		ZHANGHUQINGSUANAN,
		/**
		 * 账户清算是否执行完成
		 */
		ZHANGHUQINGSUANANISDONE,
		/**
		 * 交易检查
		 */
		JIAOYIJIANCHA,
		/**
		 * 交易检查是否执行完成
		 */
		JIAOYIJIANCHAISDONE,
		/**
		 * 交易清算
		 */
		JIAOYIQINGSUAN,

		/**
		 * 交易清算是否执行完成
		 */
		JIAOYIQINGSUANISDONE,
		
		/**
		 * 确认数据导出
		 */
		QUERENSHUJUDAOCHU,
		/**
		 * 确认数据导出是否执行完成
		 */
		QUERENSHUJUDAOCHUISDONE,
		/**
		 * 日终确认
		 */
		RIZHONGQUEREN,
		/**
		 * 日终确认是否执行完成
		 */
		RIZHONGQUERENISDONE

		
	}


}
