package com.yss.method;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;

import com.yss.common.AllElementEnum;
import com.yss.common.BaseInterface;
import com.yss.common.Common;
import com.yss.common.ElementEnum;
import com.yss.common.MyResponse;
import com.yss.common.PageEnum;
import com.yss.common.ReadFromExcel;
import com.yss.common.Common.CommonElementEnum;
import com.yss.method.CheckMenu.CheckMenuElement;

/**
 * 
 * @author bianqigang
 *
 */
public class WeiYueShuHuiFeiLu implements BaseInterface{

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
		MyResponse chanPinSheZhiResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.CHANPINSHEZHI_1);
		if((int) chanPinSheZhiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of chanPinSheZhiResponse failed");
			return false;
		}
		Common.click((WebElement)chanPinSheZhiResponse.get("ele"));
	    //点击违约赎回费率
	    MyResponse WeiYueShuHuiFeiLuResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.WEIYUESHUHUIFEILU_2);
		if((int) WeiYueShuHuiFeiLuResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of WeiYueShuHuiFeiLuResponse failed");
			return false;
		}
		Common.click((WebElement)WeiYueShuHuiFeiLuResponse.get("ele"));
		return false;
	}

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
			
			
			
			
			// 此处可能会随页面需要将display:none转为display:inline-block
			
			
			//获取页面中的产品代码
			MyResponse chanPinDaiMaResponse = Common.getWebElementForSelect(PageEnum.WEIYUESHUHUIFEILU, AllElementEnum.WeiYueShuHuiFeiLuElement,  WeiYueShuHuiFeiLuEnum.CHANPINDAIMA);
			//获取页面中的个人机构标志
			MyResponse geRenJiGouBiaoZhiResponse = Common.getWebElementForSelect(PageEnum.WEIYUESHUHUIFEILU, AllElementEnum.WeiYueShuHuiFeiLuElement,  WeiYueShuHuiFeiLuEnum.GERENJIGOUBIAOZHI);
			
			//找出在Excel中对应的项
			int sizeOfData = ReadFromExcel.dataForWeiYueShuHuiFeiLuFromExcel.size();
			for(int j = 0; j < sizeOfData; j++){
				
				//获取Excel此行的产品代码
				String chanpindaima = ReadFromExcel.dataForWeiYueShuHuiFeiLuFromExcel.get(j).get(WeiYueShuHuiFeiLuEnum.CHANPINDAIMA);
				//获取Excel此行的个人机构标志
				String gerenjigoubiaozhi = ReadFromExcel.dataForWeiYueShuHuiFeiLuFromExcel.get(j).get(WeiYueShuHuiFeiLuEnum.GERENJIGOUBIAOZHI);
				
				
				
				
				//比较找出对于此页面在Excel中的对应行
				if(((WebElement)chanPinDaiMaResponse.get("ele")).getAttribute("value").contains(chanpindaima)
						&&((WebElement)geRenJiGouBiaoZhiResponse.get("ele")).getAttribute("value").contains(gerenjigoubiaozhi)){
					//比较页面中所涉及到项目的是否正确
					
					
					HashMap<WeiYueShuHuiFeiLuEnum, String> hashMap = ReadFromExcel.dataForWeiYueShuHuiFeiLuFromExcel.get(j);
					Set<WeiYueShuHuiFeiLuEnum> keySet = hashMap.keySet();
					Iterator<WeiYueShuHuiFeiLuEnum> iterator = keySet.iterator();
					while(iterator.hasNext()){
						//从Excel中取出数据
						WeiYueShuHuiFeiLuEnum WeiYueShuHuiFeiLuEnum = iterator.next();
						if(WeiYueShuHuiFeiLuEnum.equals(WeiYueShuHuiFeiLuEnum.ISCHECKED)){
							break;
						}
						String dataFromExcel = hashMap.get(WeiYueShuHuiFeiLuEnum);
						////若Excel中取出的数值为空,说明该项不存在或不需要比较
						if(dataFromExcel.equals("")||dataFromExcel==null){
							continue;
						}
						
						
						//从页面中取出数值
						MyResponse WeiYueShuHuiFeiLuResponse = Common.getWebElement(PageEnum.WEIYUESHUHUIFEILU, AllElementEnum.WeiYueShuHuiFeiLuElement, WeiYueShuHuiFeiLuEnum);
						if(MyResponse.FAILED == (int)WeiYueShuHuiFeiLuResponse.get(MyResponse.STATUS)){
							Common.logError("get element of"+WeiYueShuHuiFeiLuResponse+"failed from page");
							return false;
						}
						Common.click((WebElement)WeiYueShuHuiFeiLuResponse.get("ele"));
						WeiYueShuHuiFeiLuResponse = Common.getWebElement(PageEnum.WEIYUESHUHUIFEILU, AllElementEnum.WeiYueShuHuiFeiLuElement, WeiYueShuHuiFeiLuEnum);
						String dataFromPage = ((WebElement)WeiYueShuHuiFeiLuResponse.get("ele")).getAttribute("value");
						//比较两数值是否相等
						if(!dataFromExcel.contains(dataFromPage)){
							//再点击之后这里做个延时，使页面发生响应变化。如999,999,999->999999
							try {
								Thread.sleep(1000);
								Common.click((WebElement)WeiYueShuHuiFeiLuResponse.get("ele"));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							WeiYueShuHuiFeiLuResponse = Common.getWebElement(PageEnum.WEIYUESHUHUIFEILU, AllElementEnum.WeiYueShuHuiFeiLuElement, WeiYueShuHuiFeiLuEnum);
							dataFromPage = ((WebElement)WeiYueShuHuiFeiLuResponse.get("ele")).getAttribute("value");
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
	public boolean review() {
		Common.review();
		return true;
	}

	@Override
	public boolean unreview() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add() {
		Common.logInfo("add");
		//循环所有数据
		int sizeOfData = ReadFromExcel.dataForWeiYueShuHuiFeiLuFromExcel.size();
		for(int i = 0; i < sizeOfData; i++){
			//点击新增
			Common.clickTopAdd();
			HashMap<WeiYueShuHuiFeiLuEnum, String> data = ReadFromExcel.dataForWeiYueShuHuiFeiLuFromExcel.get(i);
			Set<WeiYueShuHuiFeiLuEnum> set = data.keySet();
			Iterator<WeiYueShuHuiFeiLuEnum> iterator = set.iterator();
			
			while( iterator.hasNext() ){
				//isChecked在这里不需要做任何操作
				WeiYueShuHuiFeiLuEnum eunm = iterator.next();
				if(eunm.equals(WeiYueShuHuiFeiLuEnum.ISCHECKED)){
					continue;
				}
				//若Excel中取出的数值为空，则不需要设置这一项
				if(data.get(eunm).equals("")||data.get(eunm)==null){
					continue;
				}
				
				System.out.println("你和富士康");
				
				
				
				// 此处可能会随页面需要将display:none转为display:inline-block
				if(eunm.equals(WeiYueShuHuiFeiLuEnum.CHANPINDAIMA)||eunm.equals(WeiYueShuHuiFeiLuEnum.GERENJIGOUBIAOZHI)){
					Common.getWebElementForSelect(PageEnum.WEIYUESHUHUIFEILU,AllElementEnum.WeiYueShuHuiFeiLuElement,eunm);
					Common.logInfo("Change display to inline-block success");
				}
				System.out.println("你和富士康1");
				//获取add需要的元素
				MyResponse WEIYUESHUHUIFEILUResponse = Common.getWebElement(PageEnum.WEIYUESHUHUIFEILU,AllElementEnum.WeiYueShuHuiFeiLuElement,eunm);
				System.out.println("你和富士康");
				if ((int) WEIYUESHUHUIFEILUResponse.get(MyResponse.STATUS) == MyResponse.FAILED) {
					Common.logError("get element data of WEIYUESHUHUIFEILUResponse failed");
					return false;
				}
				//processTable
				//将数据填到表单中
				WebElement ele = (WebElement)WEIYUESHUHUIFEILUResponse.get("ele");
				String remark = WEIYUESHUHUIFEILUResponse.get("rem").toString();
				MyResponse setWEIYUESHUHUIFEILUResponse = Common.proccessTable( ele, data.get(eunm),  remark );
				if( (int)setWEIYUESHUHUIFEILUResponse.get(MyResponse.STATUS) == MyResponse.FAILED ){		
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

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean after() {
		Common.logInfo("After");
		Common.driver.navigate().refresh();
		return true;
	}
	
	public enum WeiYueShuHuiFeiLuEnum implements ElementEnum{
		/**
		 * 产品代码
		 */
		CHANPINDAIMA,
		/**
		 * 个人机构标志
		 */
		GERENJIGOUBIAOZHI,
		/**
		 * 天数下限
		*/
		TIANSHUXIAXIAN,
		/**
		 * 天数上限
		*/
		TIANSHUSHANGXIAN,
		/**
		 * 违约最低收费
		*/
		WEIYUEZUIDISHOUFEI,
		/**
		 * 违约最高收费
		*/
		WEIYUEZUIGAOSHOUFEI,
		/**
		 * 违约赎回费率
		*/
		WEIYUESHUHUIFEILU,
		/**
		 * 违约金计算公式
		*/
		WEIYUEJINJISUANGONGSHI,
		/**
		 * 是否需要审核
		*/
		ISCHECKED
	}
	
}
