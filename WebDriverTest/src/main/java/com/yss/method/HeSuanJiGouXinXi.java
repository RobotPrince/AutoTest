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
import com.yss.method.XiaoShouJiGouXinXi.XiaoShouJiGouXinXiEnum;

public class HeSuanJiGouXinXi implements BaseInterface {

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

	@Override
	public boolean add() {
		Common.logInfo("add");

		//循环所有数据
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
				MyResponse setHeSuanJiGouXinXiREsponse = Common.proccessTable( ele, data.get(eunm),  remark );
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
	public boolean delete() {
		return false;
	}

	@Override
	public boolean after() {
		Common.logInfo("after");
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
			myResponse = Common.getWebElements(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.VIEW);
			findElements = (List<WebElement>) myResponse.get("ele");
			//点击查看
			Common.click(findElements.get(i));
			//切换driver至ifram2
			MyResponse webElement = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM2);
			Common.driver.switchTo().frame((WebElement)webElement.get("ele"));
			//获取页面中的机构代码
			myResponse = Common.getWebElement(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, HeSuanJiGouXinXiEnum.JIGOUDAIMA);
			//找出在Excel中对应的项
			int sizeOfData = ReadFromExcel.dataForHeSuanJiGouFromExcel.size();
			for(int j = 0; j < sizeOfData; j++){
				//获取Excel此行的机构代码
				String jigoudaima  = ReadFromExcel.dataForHeSuanJiGouFromExcel.get(j).get(HeSuanJiGouXinXiEnum.JIGOUDAIMA);
				//比较找出对于此页面在Excel中的对应行
				if(jigoudaima.equals(((WebElement)myResponse.get("ele")).getAttribute("value"))){
					//比较页面中所涉及到项目的是否正确
					HashMap<HeSuanJiGouXinXiEnum, String> hashMap = ReadFromExcel.dataForHeSuanJiGouFromExcel.get(j);
					Set<HeSuanJiGouXinXiEnum> keySet = hashMap.keySet();
					Iterator<HeSuanJiGouXinXiEnum> iterator = keySet.iterator();
					while(iterator.hasNext()){
						//从Excel中取出数据
						HeSuanJiGouXinXiEnum heSuanJiGouXinXiEnum = iterator.next();
						if(heSuanJiGouXinXiEnum.equals(HeSuanJiGouXinXiEnum.ISCHECKED)){
							break;
						}
						String dataFromExcel = hashMap.get(heSuanJiGouXinXiEnum);
						//从页面中取出数值
						MyResponse hesuanjigouResponse = Common.getWebElement(PageEnum.HESUANJIGOUXINXI, AllElementEnum.HeSuanJiGouXinXiElement, heSuanJiGouXinXiEnum);
						if(MyResponse.FAILED == (int)hesuanjigouResponse.get(MyResponse.STATUS)){
							Common.logError("get element of"+heSuanJiGouXinXiEnum+"failed from page");
							return false;
						}
						String dataFromPage = ((WebElement)hesuanjigouResponse.get("ele")).getAttribute("value");
						//比较两数值是否相等
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
		 * 电话
		 */
		DIANHUA,
		/**
		 * 接口版本
		 */
		JIEKOUBANBEN,
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
