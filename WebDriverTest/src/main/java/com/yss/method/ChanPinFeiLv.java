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

public class ChanPinFeiLv implements BaseInterface {

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
		//点击产品设置
		MyResponse jiGouCanShuResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.CHANPINSHEZHI_1);
		if((int) jiGouCanShuResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of chanpinshezhi1 failed");
			return false;
		}
		Common.click((WebElement)jiGouCanShuResponse.get("ele"));
	    //点击产品费率
	    MyResponse chanPinFeiLvResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.CHANPINFEILU_2);
		if((int) chanPinFeiLvResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of chanPinFeiLvResponse failed");
			return false;
		}
		Common.click((WebElement)chanPinFeiLvResponse.get("ele"));
		return false;
	}

	@Override
	public boolean add() {
		Common.logInfo("add");

		//循环所有数据
		int sizeOfData = ReadFromExcel.dataForChanPinFeiLvFromExcel.size();
		for(int i = 0; i < sizeOfData; i++){
			//点击新增
			Common.clickTopAdd();
			HashMap<ChanPinFeiLvEnum, String> data = ReadFromExcel.dataForChanPinFeiLvFromExcel.get(i);
			Set<ChanPinFeiLvEnum> set = data.keySet();
			Iterator<ChanPinFeiLvEnum> iterator = set.iterator();
			
			while( iterator.hasNext() ){
				//isChecked在这里不需要做任何操作
				ChanPinFeiLvEnum eunm = iterator.next();
				if(eunm.equals(ChanPinFeiLvEnum.ISCHECKED)){
					continue;
				}
				//若Excel中取出的数值为空，则不需要设置这一项
				if(data.get(eunm).equals("")||data.get(eunm)==null){
					continue;
				}
				//获取add需要的元素
				MyResponse chanPinFeiLvResponse = Common.getWebElement(PageEnum.CHANPINFEILV,AllElementEnum.ChanPinFeiLvElement,eunm);
				if ((int) chanPinFeiLvResponse.get(MyResponse.STATUS) == MyResponse.FAILED) {
					Common.logError("get element data of chanPinFeiLvResponse failed");
					return false;
				}
				//processTable
				//将数据填到表单中
				WebElement ele = (WebElement)chanPinFeiLvResponse.get("ele");
				String remark = chanPinFeiLvResponse.get("rem").toString();
				MyResponse setChanPinFeiLvResponse = Common.proccessTable( ele, data.get(eunm),  remark );
				if( (int)setChanPinFeiLvResponse.get(MyResponse.STATUS) == MyResponse.FAILED ){		
					Common.logError("set parameter of"+eunm+"failed");
					return false;
				}
//				//当产品大类设置完以后展开所有的下拉中的项目
//				if(eunm.equals(ChanPinFeiLvEnum.CHANPINDALEI)){
//					MyResponse ChanPinPullDownRes = Common.getWebElements(PageEnum.CHANPINXINXI, AllElementEnum.ChanPinXinXiElement, ChanPinFeiLvEnum.CHANPINPULLDOWN);
//					@SuppressWarnings("unchecked")
//					List<WebElement> listOfPullDown = (List<WebElement>)ChanPinPullDownRes.get("ele");
//					for(int j = 1; j < listOfPullDown.size(); j++){
//						Common.click(listOfPullDown.get(j));
//					}
//				}
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
			//获取页面中的产品代码
			MyResponse chanPinDaiMaResponse = Common.getWebElement(PageEnum.CHANPINFEILV, AllElementEnum.ChanPinFeiLvElement, ChanPinFeiLvEnum.CHANPINDAIMA);
			//获取页面中的业务代码
			MyResponse yeWuDaiMaResponse = Common.getWebElement(PageEnum.CHANPINFEILV, AllElementEnum.ChanPinFeiLvElement, ChanPinFeiLvEnum.YEWUDAIMA);
			
			//找出在Excel中对应的项
			int sizeOfData = ReadFromExcel.dataForChanPinFeiLvFromExcel.size();
			for(int j = 0; j < sizeOfData; j++){
				//获取Excel此行的产品代码
				String chanpindaima  = ReadFromExcel.dataForChanPinFeiLvFromExcel.get(j).get(ChanPinFeiLvEnum.CHANPINDAIMA);
				//获取Excel此行的业务代码
				String yewudaima  = ReadFromExcel.dataForChanPinFeiLvFromExcel.get(j).get(ChanPinFeiLvEnum.YEWUDAIMA);
				//比较找出对于此页面在Excel中的对应行
				if(((WebElement)chanPinDaiMaResponse.get("ele")).getAttribute("value").contains(chanpindaima)&&((WebElement)yeWuDaiMaResponse.get("ele")).getAttribute("value").contains(yewudaima)){
					//比较页面中所涉及到项目的是否正确
					HashMap<ChanPinFeiLvEnum, String> hashMap = ReadFromExcel.dataForChanPinFeiLvFromExcel.get(j);
					Set<ChanPinFeiLvEnum> keySet = hashMap.keySet();
					Iterator<ChanPinFeiLvEnum> iterator = keySet.iterator();
					while(iterator.hasNext()){
						//从Excel中取出数据
						ChanPinFeiLvEnum ChanPinFeiLvEnum = iterator.next();
						if(ChanPinFeiLvEnum.equals(ChanPinFeiLvEnum.ISCHECKED)){
							break;
						}
						String dataFromExcel = hashMap.get(ChanPinFeiLvEnum);
						////若Excel中取出的数值为空,说明该项不存在或不需要比较
						if(dataFromExcel.equals("")||dataFromExcel==null){
							continue;
						}
						//从页面中取出数值
						MyResponse chanPinFeiLvResponse = Common.getWebElement(PageEnum.CHANPINFEILV, AllElementEnum.ChanPinFeiLvElement, ChanPinFeiLvEnum);
						if(MyResponse.FAILED == (int)chanPinFeiLvResponse.get(MyResponse.STATUS)){
							Common.logError("get element of"+ChanPinFeiLvEnum+"failed from page");
							return false;
						}
						Common.click((WebElement)chanPinFeiLvResponse.get("ele"));
						chanPinFeiLvResponse = Common.getWebElement(PageEnum.CHANPINFEILV, AllElementEnum.ChanPinFeiLvElement, ChanPinFeiLvEnum);
						String dataFromPage = ((WebElement)chanPinFeiLvResponse.get("ele")).getAttribute("value");
						//比较两数值是否相等
						if(!dataFromExcel.contains(dataFromPage)){
							//再点击之后这里做个延时，使页面发生响应变化。如999,999,999->999999
							try {
								Thread.sleep(1000);
								Common.click((WebElement)chanPinFeiLvResponse.get("ele"));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							chanPinFeiLvResponse = Common.getWebElement(PageEnum.CHANPINFEILV, AllElementEnum.ChanPinFeiLvElement, ChanPinFeiLvEnum);
							dataFromPage = ((WebElement)chanPinFeiLvResponse.get("ele")).getAttribute("value");
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
	
	public enum ChanPinFeiLvEnum implements ElementEnum {
		/**
		 * 产品代码
		 */
		CHANPINDAIMA,
		/**
		 * 费率明细名称
		*/
		FEILVMINGXIMINGCHENG,
		/**
		 * 业务代码
		*/
		YEWUDAIMA,
		/**
		 * 手续费率
		*/
		SHOUXUFEILV,
		/**
		 * 个人/机构标志
		*/
		GERENJIGOUBIAOZHI,
		/**
		 * 时间单位
		*/
		SHIJIANDANWEI,
		/**
		 * 金额下限
		*/
		JINEXIAXIAN,
		/**
		 * 金额上限
		*/
		JINESHANGXIAN,
		/**
		 * 持有时间下限
		*/
		CHIYOUSHIJIANXIAXIAN,
		/**
		 * 持有时间上限
		*/
		CHIYOUSHIJIANSHANGXIAN,
		/**
		 * 最低收费
		*/
		ZUIDISHOUFEI,
		/**
		 * 最高收费
		*/
		ZUIGAOSHOUFEI,
		/**
		 * 手续费归资产比例
		*/
		SHOUXUFEIGUIZICHANBILI,
		/**
		 * 是否需要审核
		*/
		ISCHECKED
	}
}
