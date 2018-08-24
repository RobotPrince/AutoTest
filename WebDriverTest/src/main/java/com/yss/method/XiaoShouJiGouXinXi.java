package com.yss.method;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

public class XiaoShouJiGouXinXi implements BaseInterface {

	@Override
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
		//点击机构参数
		MyResponse jiGouCanShuResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.JIGOUCANSHU_1);
		if((int) jiGouCanShuResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of jigoucanshu1 failed");
			return false;
		}
		Common.click((WebElement)jiGouCanShuResponse.get("ele"));
	    //点击销售机构信息
	    MyResponse xiaoShouJiGouXinXiResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.XIAOSHOUJIGOUXINXI_2);
		if((int) xiaoShouJiGouXinXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of xiaoShouJigougingiResponse failed");
			return false;
		}
		Common.click((WebElement)xiaoShouJiGouXinXiResponse.get("ele"));
	
		
		return false;
	}
	
	@Override
	public boolean add() {

		Common.logInfo("addA");

		//循环所有数据
		int sizeOfData = ReadFromExcel.dataForXiaoShouJIGouXinXiFromExcel.size();
		for(int i = 0; i < sizeOfData; i++){
			//点击新增
			Common.clickTopAdd();
			HashMap<XiaoShouJiGouXinXiEnum, String> data = ReadFromExcel.dataForXiaoShouJIGouXinXiFromExcel.get(i);
			Set<XiaoShouJiGouXinXiEnum> set = data.keySet();
			Iterator<XiaoShouJiGouXinXiEnum> iterator = set.iterator();
			while( iterator.hasNext() ){
				//isChecked在这里不需要做任何操作
				XiaoShouJiGouXinXiEnum eunm = iterator.next();
				if(eunm.equals(XiaoShouJiGouXinXiEnum.ISCHECKED)){
					continue;
				}
				//获取add需要的元素
				MyResponse xiaoShouJiGouXinXiResponse = Common.getWebElement(PageEnum.XIAOSHOUJIGOUXINXI,AllElementEnum.XiaoShouJiGouXinXiElement,eunm);
				if ((int) xiaoShouJiGouXinXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED) {
					Common.logError("get element data of xiaoShouJiGouXinXiResponse failed");
					return false;
				}
				//processTable
				//将第一条数据填到表单中
				WebElement ele = (WebElement)xiaoShouJiGouXinXiResponse.get("ele");
				String remark = xiaoShouJiGouXinXiResponse.get("rem").toString();
				MyResponse setXiaoShouJiGouXinXiREsponse = Common.proccessTable( ele, data.get(eunm),  remark );
				if( (int)setXiaoShouJiGouXinXiREsponse.get(MyResponse.STATUS) == MyResponse.FAILED ){		
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
		Common.logInfo("reviewAFew");
		
		Common.driver.switchTo().defaultContent();
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
			return false;
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
		
		MyResponse itemCheckBoxResponse = Common.getWebElements(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.ITEM_CHECKBOX);
		if((int)itemCheckBoxResponse.get(MyResponse.STATUS)==MyResponse.FAILED){
			Common.logError("get Elements of"+CommonElementEnum.ITEM_CHECKBOX+"failed");
			return false;
		}
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
		int sizeOfData = ReadFromExcel.dataForXiaoShouJIGouXinXiFromExcel.size();
		for(int i = 0; i < sizeOfData; i++){
			HashMap<XiaoShouJiGouXinXiEnum, String> data = ReadFromExcel.dataForXiaoShouJIGouXinXiFromExcel.get(i);
			if("Y".equalsIgnoreCase(data.get(XiaoShouJiGouXinXiEnum.ISCHECKED))){
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
			HashMap<XiaoShouJiGouXinXiEnum, String> data = ReadFromExcel.dataForXiaoShouJIGouXinXiFromExcel.get(i);
			if("Y".equalsIgnoreCase(data.get(XiaoShouJiGouXinXiEnum.ISCHECKED))){
				//点击复选框
				MyResponse clickResponse = Common.click(itemList.get(i));
				if((int)clickResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
					Common.logError("Click checkbox of "+itemList.get(i)+" failed");
					return false;
				 }
			}
		}
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
		return null;
	}
	

	public enum XiaoShouJiGouXinXiEnum implements ElementEnum{
		/**
		 * 机构代码
		 */
		JIGOUDAIMA,
		/**
		 * 机构全称
		 */
		JIGOUQUANCHENG,
		/**
		 *机构类型 
		 */
		JIGOULEIXING,
		/**
		 *机构类型 0直销
		 */
		ZHIXIAO,
		/**
		 *机构类型 1代销 
		 */
		DAIXIAO,
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
		 *机构传真
		 */
		JIGOUCHUANZHEN,
		/**
		 *机构联系人
		 */
		JIGOULIANXIREN,
		/**
		 * 机构联系电话
		 */
		JIGOULIANXIDIANHUA,
		/**
		 * 邮件编码
		 */
		YOUJIANBIANMA,
		/**
		 * 电子邮件
		 */
		DIANZIYOUJIAN,
		/**
		 * 总部地址
		 */
		ZONGBUDIZHI,
		/**
		 * 对账方式
		 */
		DUIZHANGFANGSHI,
		/**
		 * 全量对账
		 */
		QUANLIANGDUIZHANG,
		/**
		 * 增量对账
		 */
		ZENGLIANGDUIZHANG,
		/**
		 * 接口版本
		 */
		JIEKOUBANBEN,
		/**
		 * 接口版本2.0
		 */
		JIEKOU_2_0,
		/**
		 * 接口版本2.1
		 */
		JIEKOU_2_1,
		/**
		 * 接口版本2.x
		 */
		JIEKOU_2_X,
		/**
		 * 是否导出.ok
		 */
		SHIFOUDAOCHU,
		/**
		 * 是否导出 否
		 */
		FOU,
		/**
		 * 是否导出 是
		 */
		SHI,
		/**
		 * 明细标识
		 */
		MINGXIBIAOSHI,
		/**
		 * 明细标识 非明细
		 */
		FEIMINGXI,
		/**
		 * 明细标识 明细
		 */
		MINGXI,
		/**
		 * 接口导入路径
		 */
		JIEKOUDAORULUJING,
		/**
		 * 接口导出路径
		 */
		JIEKOUDAOCHULUJING,
		/**
		 * 是否需要审核
		 */
		ISCHECKED
		
	}

}
