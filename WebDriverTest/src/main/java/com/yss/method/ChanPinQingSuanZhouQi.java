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
import com.yss.common.MyResponse;
import com.yss.common.PageEnum;
import com.yss.common.ReadFromExcel;
import com.yss.method.CheckMenu.CheckMenuElement;
import com.yss.common.ElementEnum;
import org.openqa.selenium.JavascriptExecutor;


public class ChanPinQingSuanZhouQi implements BaseInterface {

	@SuppressWarnings("deprecation")
	@Override
	public boolean before() {
		Common.logInfo("before");
		
		Common.driver.switchTo().defaultContent();
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
		//点击参数设置
		MyResponse canShuSheZhiResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.CANSHUSHEZHI_1);
		if((int) canShuSheZhiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of chanpinshezhi1 failed");
			return false;
		}
		Common.click((WebElement)canShuSheZhiResponse.get("ele"));
	    //点击产品清算周期
	    MyResponse chanPinQingSuanZhouQiResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.CHANPINQINGSUANZHOUQI_2);
		if((int) chanPinQingSuanZhouQiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of chanPinQingSuanZhouQiResponse failed");
			return false;
		}
		Common.click((WebElement)chanPinQingSuanZhouQiResponse.get("ele"));
		return false;
	}

	@Override
	public boolean add() {
		Common.logInfo("add");

		//循环所有数据
		int sizeOfData = ReadFromExcel.dataForChanPinQingSuanZhouQiFromExcel.size();
		for(int i = 0; i < sizeOfData; i++){
			//点击新增
			Common.clickTopAdd();
			HashMap<ChanPinQingSuanZhouQiEnum, String> data = ReadFromExcel.dataForChanPinQingSuanZhouQiFromExcel.get(i);
			Set<ChanPinQingSuanZhouQiEnum> set = data.keySet();
			Iterator<ChanPinQingSuanZhouQiEnum> iterator = set.iterator();
			
			while( iterator.hasNext() ){
				//isChecked在这里不需要做任何操作
				ChanPinQingSuanZhouQiEnum eunm = iterator.next();
				if(eunm.equals(ChanPinQingSuanZhouQiEnum.ISCHECKED)){
					continue;
				}
				//若Excel中取出的数值为空，则不需要设置这一项
				if(data.get(eunm).equals("")||data.get(eunm)==null){
					continue;
				}
				
				// 此处可能会随页面需要将display:none转为display:inline-block
				if(eunm.equals(ChanPinQingSuanZhouQiEnum.CHANPINDAIMA)||eunm.equals(ChanPinQingSuanZhouQiEnum.CHANPINQINGSUANZHOUQI) || eunm.equals(ChanPinQingSuanZhouQiEnum.MIAOSHU)){
					Common.getWebElementForSelect(PageEnum.CHANPINQINGSUANZHOUQI,AllElementEnum.ChanPinQingSuanZhouQiElement,eunm);
					Common.logInfo("Change display to inline-block success");
     			} 
				//获取add需要的元素
				MyResponse ChanPinQingSuanZhouQiResponse = Common.getWebElement(PageEnum.CHANPINQINGSUANZHOUQI,AllElementEnum.ChanPinQingSuanZhouQiElement,eunm);
				if ((int) ChanPinQingSuanZhouQiResponse.get(MyResponse.STATUS) == MyResponse.FAILED) {
					Common.logError("get element data of ChanPinQingSuanZhouQiResponse failed");
					return false;
				}
				//processTable
				//将数据填到表单中
				WebElement ele = (WebElement)ChanPinQingSuanZhouQiResponse.get("ele");
				String remark = ChanPinQingSuanZhouQiResponse.get("rem").toString();
				MyResponse setChanPinQingSuanZhouQiResponse = Common.proccessTable( ele, data.get(eunm),  remark );
				if( (int)setChanPinQingSuanZhouQiResponse.get(MyResponse.STATUS) == MyResponse.FAILED ){		
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
			//点击刷新，用来退出查看页面
			Common.clickRefresh();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean view() {
		Common.logInfo("view");
		//将driver切回到default
		Common.driver.switchTo().defaultContent();
		//将driver切到ifram1
		MyResponse ifram1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		Common.driver.switchTo().frame((WebElement)ifram1Response.get("ele"));
		MyResponse myResponse = new MyResponse();
		//获取页面中所有项目数目
		myResponse = Common.getWebElements(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.VIEW);
		List<WebElement> findElements = (List<WebElement>) myResponse.get("ele");
		
		//循环页面中所有项目
		for(int i = 0; i < findElements.size(); i++){
			MyResponse viewResponse = Common.getWebElements(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.VIEW);
			findElements = (List<WebElement>) viewResponse.get("ele");
			//点击查看
			Common.click(findElements.get(i));
			//切换driver至ifram2
			MyResponse webElement = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM2);
			Common.driver.switchTo().frame((WebElement)webElement.get("ele"));
			//需要将display none 改为 dispaly inline-block
			//获取页面中的产品代码
			MyResponse chanPinDaiMaResponse = Common.getWebElementForSelect(PageEnum.CHANPINQINGSUANZHOUQI, AllElementEnum.ChanPinQingSuanZhouQiElement, ChanPinQingSuanZhouQiEnum.CHANPINDAIMA);
			//获取页面中的产品清算周期
			MyResponse chanPinQingSuanZhouQiResponse = Common.getWebElementForSelect(PageEnum.CHANPINQINGSUANZHOUQI, AllElementEnum.ChanPinQingSuanZhouQiElement, ChanPinQingSuanZhouQiEnum.CHANPINQINGSUANZHOUQI);
			//获取页面中的描述
			MyResponse miaoShuResponse = Common.getWebElementForSelect(PageEnum.CHANPINQINGSUANZHOUQI, AllElementEnum.ChanPinQingSuanZhouQiElement, ChanPinQingSuanZhouQiEnum.MIAOSHU);
			//找出在Excel中对应的项
			int sizeOfData = ReadFromExcel.dataForChanPinQingSuanZhouQiFromExcel.size();
			for(int j = 0; j < sizeOfData; j++){
				//获取Excel此行的产品代码
				String chanpindaima = ReadFromExcel.dataForChanPinQingSuanZhouQiFromExcel.get(j).get(ChanPinQingSuanZhouQiEnum.CHANPINDAIMA);
				//获取Excel此行的产品清算周期
				String chanpinqingsuanzhouqi  = ReadFromExcel.dataForChanPinQingSuanZhouQiFromExcel.get(j).get(ChanPinQingSuanZhouQiEnum.CHANPINQINGSUANZHOUQI);
				//获取Excel此行的描述
				String miaoshu  = ReadFromExcel.dataForChanPinQingSuanZhouQiFromExcel.get(j).get(ChanPinQingSuanZhouQiEnum.MIAOSHU);
				//比较找出对于此页面在Excel中的对应行
				if(((WebElement)chanPinDaiMaResponse.get("ele")).getAttribute("value").contains(chanpindaima)
						&&((WebElement)chanPinQingSuanZhouQiResponse.get("ele")).getAttribute("value").contains(chanpinqingsuanzhouqi)
						&&((WebElement)miaoShuResponse.get("ele")).getAttribute("value").contains(miaoshu)){
					//比较页面中所涉及到项目的是否正确
					HashMap<ChanPinQingSuanZhouQiEnum, String> hashMap = ReadFromExcel.dataForChanPinQingSuanZhouQiFromExcel.get(j);
					Set<ChanPinQingSuanZhouQiEnum> keySet = hashMap.keySet();
					Iterator<ChanPinQingSuanZhouQiEnum> iterator = keySet.iterator();
					while(iterator.hasNext()){
						//从Excel中取出数据
						ChanPinQingSuanZhouQiEnum ChanPinQingSuanZhouQiEnum = iterator.next();
						if(ChanPinQingSuanZhouQiEnum.equals(ChanPinQingSuanZhouQiEnum.ISCHECKED)){
							break;
						}
						String dataFromExcel = hashMap.get(ChanPinQingSuanZhouQiEnum);
						////若Excel中取出的数值为空,说明该项不存在或不需要比较
						if(dataFromExcel.equals("")||dataFromExcel==null){
							continue;
						}
						//从页面中取出数值
						MyResponse ChanPinQingSuanZhouQiResponse = Common.getWebElement(PageEnum.CHANPINQINGSUANZHOUQI, AllElementEnum.ChanPinQingSuanZhouQiElement, ChanPinQingSuanZhouQiEnum);
						if(MyResponse.FAILED == (int)ChanPinQingSuanZhouQiResponse.get(MyResponse.STATUS)){
							Common.logError("get element of"+ChanPinQingSuanZhouQiEnum+"failed from page");
							return false;
						}
						Common.click((WebElement)ChanPinQingSuanZhouQiResponse.get("ele"));
						ChanPinQingSuanZhouQiResponse = Common.getWebElement(PageEnum.CHANPINQINGSUANZHOUQI, AllElementEnum.ChanPinQingSuanZhouQiElement, ChanPinQingSuanZhouQiEnum);
						String dataFromPage = ((WebElement)ChanPinQingSuanZhouQiResponse.get("ele")).getAttribute("value");
						//比较两数值是否相等
						if(!dataFromExcel.contains(dataFromPage)){
							//再点击之后这里做个延时，使页面发生响应变化。如999,999,999->999999
							try {
								Thread.sleep(1000);
								Common.click((WebElement)ChanPinQingSuanZhouQiResponse.get("ele"));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ChanPinQingSuanZhouQiResponse = Common.getWebElement(PageEnum.CHANPINQINGSUANZHOUQI, AllElementEnum.ChanPinQingSuanZhouQiElement, ChanPinQingSuanZhouQiEnum);
							dataFromPage = ((WebElement)ChanPinQingSuanZhouQiResponse.get("ele")).getAttribute("value");
						}
						if(!dataFromExcel.contains(dataFromPage)){
							Common.logError("data not equals when compare, "+"dataFromExcel "+dataFromExcel+" dataFromPage "+dataFromPage);
							return false;
						}
					}
				}
			}
			//点击刷新，用来退出查看页面
			Common.clickRefresh();
			Common.driver.switchTo().defaultContent();
			//将driver切到ifram1
			ifram1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
			Common.driver.switchTo().frame((WebElement)ifram1Response.get("ele"));
		}
		return true;
	}

	@Override
	public boolean delete() {
		return false;
	}

	@Override
	public boolean after() {
		Common.logInfo("After");
		Common.driver.navigate().refresh();
		return true;
	}

	@Override
	public boolean review(){
		Common.review();
		return true;
	}
	@Override
	public boolean unreview(){
		Common.unreviewed();
		return true;
	}
	
	public enum ChanPinQingSuanZhouQiEnum implements ElementEnum {
		/**
		 * 产品代码
		*/
		CHANPINDAIMA,
		/**
		 * 产品清算周期
		*/
		CHANPINQINGSUANZHOUQI,
		/**
		 * 描述
		*/
		MIAOSHU,
		/**
		 * 是否需要审核
		*/
		ISCHECKED

	}
}
