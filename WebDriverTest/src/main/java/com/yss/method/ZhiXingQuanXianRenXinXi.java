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
import com.yss.method.ZhiXingQuanXianRenXinXi.ZhiXingQuanXianRenXinXiEnum;

/**
 * 
 * @author yanglei
 *
 */
public class ZhiXingQuanXianRenXinXi implements BaseInterface{

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
		//点击执行权限人设置
		MyResponse jiGouCanShuResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.ZHIXINGQUANXIANSHEZHI_1);
		if((int) jiGouCanShuResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of chanpinshezhi1 failed");
			return false;
		}
		Common.click((WebElement)jiGouCanShuResponse.get("ele"));
		//点击执行权限人信息
	    MyResponse ZhiXingQuanXianRenXianXinResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.ZHIXINGQUANXIANRENXINXI_2);
		if((int) ZhiXingQuanXianRenXianXinResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of ZhiXingQuanXianRenXianXinResponse failed");
			return false;
		}
		Common.click((WebElement)ZhiXingQuanXianRenXianXinResponse.get("ele"));
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
			
			//获取页面中的执行人代码
			MyResponse zhiXingQuanXianRenDaiMaResponse = Common.getWebElementForSelect(PageEnum.ZHIXINGQUANXIANRENXINXI, AllElementEnum.ZhiXingQuanXianRenXinXiElement, ZhiXingQuanXianRenXinXiEnum.ZHIXINGQUANXIANRENDAIMA);
			//获取页面中的执行人名称
			MyResponse zhiXingQuanXianRenMingChengResponse = Common.getWebElementForSelect(PageEnum.ZHIXINGQUANXIANRENXINXI, AllElementEnum.ZhiXingQuanXianRenXinXiElement, ZhiXingQuanXianRenXinXiEnum.ZHIXINGQUANXIANRENMINGCHENG);
			
			//找出在Excel中对应的项
			int sizeOfData = ReadFromExcel.dataForZhiXingQuanXianRenXinXiFromExcel.size();
			for(int j = 0; j < sizeOfData; j++){
				//获取Excel此行的执行权限人代码
				String zhixingquanxianrendaima = ReadFromExcel.dataForZhiXingQuanXianRenXinXiFromExcel.get(j).get(ZhiXingQuanXianRenXinXiEnum.ZHIXINGQUANXIANRENDAIMA);
				//获取Excel此行的执行权限人代码
				String zhixingquanxianrenmingcheng = ReadFromExcel.dataForZhiXingQuanXianRenXinXiFromExcel.get(j).get(ZhiXingQuanXianRenXinXiEnum.ZHIXINGQUANXIANRENDAIMA);
				
				//比较找出对于此页面在Excel中的对应行
				if(((WebElement)zhiXingQuanXianRenDaiMaResponse.get("ele")).getAttribute("value").contains(zhixingquanxianrendaima)
						&&((WebElement)zhiXingQuanXianRenMingChengResponse.get("ele")).getAttribute("value").contains(zhixingquanxianrenmingcheng)){
					//比较页面中所涉及到项目的是否正确
					HashMap<ZhiXingQuanXianRenXinXiEnum, String> hashMap = ReadFromExcel.dataForZhiXingQuanXianRenXinXiFromExcel.get(j);
					Set<ZhiXingQuanXianRenXinXiEnum> keySet = hashMap.keySet();
					Iterator<ZhiXingQuanXianRenXinXiEnum> iterator = keySet.iterator();
					while(iterator.hasNext()){
						//从Excel中取出数据
						ZhiXingQuanXianRenXinXiEnum ZhiXingQuanXianRenXinXiEnum = iterator.next();
						if(ZhiXingQuanXianRenXinXiEnum.equals(ZhiXingQuanXianRenXinXiEnum.ISCHECKED)){
							break;
						}
						String dataFromExcel = hashMap.get(ZhiXingQuanXianRenXinXiEnum);
						////若Excel中取出的数值为空,说明该项不存在或不需要比较
						if(dataFromExcel.equals("")||dataFromExcel==null){
							continue;
						}
						//从页面中取出数值
						MyResponse ZhiXingQuanXianRenXinXiResponse = Common.getWebElement(PageEnum.ZHIXINGQUANXIANRENXINXI, AllElementEnum.ZhiXingQuanXianRenXinXiElement, ZhiXingQuanXianRenXinXiEnum);
						if(MyResponse.FAILED == (int)ZhiXingQuanXianRenXinXiResponse.get(MyResponse.STATUS)){
							Common.logError("get element of"+ZhiXingQuanXianRenXinXiEnum+"failed from page");
							return false;
						}
						Common.click((WebElement)ZhiXingQuanXianRenXinXiResponse.get("ele"));
						ZhiXingQuanXianRenXinXiResponse = Common.getWebElement(PageEnum.ZHIXINGQUANXIANRENXINXI, AllElementEnum.ZhiXingQuanXianRenXinXiElement, ZhiXingQuanXianRenXinXiEnum);
						String dataFromPage = ((WebElement)ZhiXingQuanXianRenXinXiResponse.get("ele")).getAttribute("value");
						//比较两数值是否相等
						if(!dataFromExcel.contains(dataFromPage)){
							//再点击之后这里做个延时，使页面发生响应变化。如999,999,999->999999
							try {
								Thread.sleep(1000);
								Common.click((WebElement)ZhiXingQuanXianRenXinXiResponse.get("ele"));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ZhiXingQuanXianRenXinXiResponse = Common.getWebElement(PageEnum.ZHIXINGQUANXIANRENXINXI, AllElementEnum.ZhiXingQuanXianRenXinXiElement, ZhiXingQuanXianRenXinXiEnum);
							dataFromPage = ((WebElement)ZhiXingQuanXianRenXinXiResponse.get("ele")).getAttribute("value");
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
		Common.unreviewed();
		return true;
	}

	@Override
	public boolean add() {
		Common.logInfo("add");

		//循环所有数据
		int sizeOfData = ReadFromExcel.dataForZhiXingQuanXianRenXinXiFromExcel.size();
		
		for(int i = 0; i < sizeOfData; i++){
			//点击新增
			Common.clickTopAdd();
			HashMap<ZhiXingQuanXianRenXinXiEnum, String> data = ReadFromExcel.dataForZhiXingQuanXianRenXinXiFromExcel.get(i);
			Set<ZhiXingQuanXianRenXinXiEnum> set = data.keySet();
			Iterator<ZhiXingQuanXianRenXinXiEnum> iterator = set.iterator();
			
			while( iterator.hasNext() ){
				//isChecked在这里不需要做任何操作
				ZhiXingQuanXianRenXinXiEnum eunm = iterator.next();
				if(eunm.equals(ZhiXingQuanXianRenXinXiEnum.ISCHECKED)){
					continue;
				}
				//若Excel中取出的数值为空，则不需要设置这一项
				if(data.get(eunm).equals("")||data.get(eunm)==null){
					continue;
				}
				//获取add需要的元素
				MyResponse ZhiXingQuanXianRenXinXiResponse = Common.getWebElement(PageEnum.ZHIXINGQUANXIANRENXINXI,AllElementEnum.ZhiXingQuanXianRenXinXiElement,eunm);
				if ((int) ZhiXingQuanXianRenXinXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED) {
					Common.logError("get element data of ZhiXingQuanXianRenXinXiResponse failed");
					return false;
				}
				//processTable
				//将数据填到表单中
				WebElement ele = (WebElement)ZhiXingQuanXianRenXinXiResponse.get("ele");
				String remark = ZhiXingQuanXianRenXinXiResponse.get("rem").toString();
				MyResponse setZhiXingQuanXianRenXinXiResponse = Common.proccessTable( ele, data.get(eunm),  remark );
				if( (int)setZhiXingQuanXianRenXinXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED ){		
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
		return false;
	}

	@Override
	public boolean after() {
		Common.logInfo("After");
		Common.driver.navigate().refresh();
		return true;
	}
	
	public enum ZhiXingQuanXianRenXinXiEnum implements ElementEnum {
		/**
		 * 执行权限人代码
		*/
		ZHIXINGQUANXIANRENDAIMA,
		/**
		 * 执行权限人名称
		*/
		ZHIXINGQUANXIANRENMINGCHENG,
		/**
		 * 执行权限人描述
		*/
		ZHIXINGQUANXIANRENMIAOSHU,
		/**
		 * 是否需要审核
		 */
		ISCHECKED
	}
}
