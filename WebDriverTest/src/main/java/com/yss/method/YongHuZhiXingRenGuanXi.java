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
import com.yss.method.YongHuZhiXingRenGuanXi.YongHuZhiXingRenGuanXiEnum;

/**
 * 
 * @author yuanpeihong
 *
 */

public class YongHuZhiXingRenGuanXi implements BaseInterface {
	
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
		//点击执行权限设置
		MyResponse zhiXingQianXianSheZhiResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.chanpinquanxianshezhi_1);
		if((int) zhiXingQianXianSheZhiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of chanpinquanxianshezhi1 failed");
			return false;
		}
		Common.click((WebElement)zhiXingQianXianSheZhiResponse.get("ele"));
	    //点击用户执行人关系
	    MyResponse YongHuZhiXingRenGuanXiResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.gonghuzhixingrenguanxi_2);
		if((int) YongHuZhiXingRenGuanXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of yonghuzhixingrenguanxiResponse failed");
			return false;
		}
		Common.click((WebElement)YongHuZhiXingRenGuanXiResponse.get("ele"));
		return false;
	}
	
	@Override
	public boolean add() {
		Common.logInfo("add");
		
		//循环所有数据
		int sizeOfData = ReadFromExcel.dataForYongHuZhiXingRenGuanXiFromExcel.size();
		for(int i = 0; i < sizeOfData; i++){
			//点击新增
			Common.clickTopAdd();
			HashMap<YongHuZhiXingRenGuanXiEnum, String> data = ReadFromExcel.dataForYongHuZhiXingRenGuanXiFromExcel.get(i);
			Set<YongHuZhiXingRenGuanXiEnum> set = data.keySet();
			Iterator<YongHuZhiXingRenGuanXiEnum> iterator = set.iterator();
			while( iterator.hasNext() ){
				//isChecked在这里不需要做任何操作
				YongHuZhiXingRenGuanXiEnum eunm = iterator.next();
				if(eunm.equals(YongHuZhiXingRenGuanXiEnum.ISCHECKED)){
					continue;
				}
				//若Excel中取出的数值为空，则不需要设置这一项
				if(data.get(eunm).equals("")||data.get(eunm)==null){
					continue;
				}
				//获取add需要的元素
				MyResponse YongHuZhiXingRenGuanXiResponse = Common.getWebElement(PageEnum.YONGHUZHIXINGRENGUANXI,AllElementEnum.YongHuZhiXingRenGuanXiElement,eunm);
				if ((int)  YongHuZhiXingRenGuanXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED) {
					Common.logError("get element data of  YongHuZhiXingRenGuanXiResponse failed");
					return false;
				}
				//processTable
				//将数据填到表单中
				WebElement ele = (WebElement)YongHuZhiXingRenGuanXiResponse.get("ele");
				String remark = YongHuZhiXingRenGuanXiResponse.get("rem").toString();
				MyResponse setYongHuZhiXingRenGuanXiResponse = Common.proccessTable( ele, data.get(eunm),  remark );
				if( (int)setYongHuZhiXingRenGuanXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED ){		
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
			//获取页面中的登入用户名
			MyResponse dengRuYongHuMingResponse = Common.getWebElementForSelect(PageEnum.YONGHUZHIXINGRENGUANXI, AllElementEnum.YongHuZhiXingRenGuanXiElement, YongHuZhiXingRenGuanXiEnum.DENGRUYONGHUMING);
			//找出在Excel中对应的项
			int sizeOfData = ReadFromExcel.dataForYongHuZhiXingRenGuanXiFromExcel.size();
			for(int j = 0; j < sizeOfData; j++){
				//获取Excel此行的机构代码
				String dengruyonghuming = ReadFromExcel.dataForYongHuZhiXingRenGuanXiFromExcel.get(j).get(YongHuZhiXingRenGuanXiEnum.DENGRUYONGHUMING);
				//比较找出对于此页面在Excel中的对应行
				if(((WebElement)dengRuYongHuMingResponse.get("ele")).getAttribute("value").contains(dengruyonghuming)){
					//比较页面中所涉及到项目的是否正确
					HashMap<YongHuZhiXingRenGuanXiEnum, String> hashMap = ReadFromExcel.dataForYongHuZhiXingRenGuanXiFromExcel.get(j);
					Set<YongHuZhiXingRenGuanXiEnum> keySet = hashMap.keySet();
					Iterator<YongHuZhiXingRenGuanXiEnum> iterator = keySet.iterator();
					while(iterator.hasNext()){
						//从Excel中取出数据
						YongHuZhiXingRenGuanXiEnum YongHuZhiXingRenGuanXiEnum = iterator.next();
						if(YongHuZhiXingRenGuanXiEnum.equals(YongHuZhiXingRenGuanXiEnum.ISCHECKED)){
							break;
						}
						String dataFromExcel = hashMap.get(YongHuZhiXingRenGuanXiEnum);
						////若Excel中取出的数值为空,说明该项不存在或不需要比较
						if(dataFromExcel.equals("")||dataFromExcel==null){
							continue;
						}
						//从页面中取出数值
						MyResponse YongHuZhiXingRenGuanXiResponse = Common.getWebElement(PageEnum.YONGHUZHIXINGRENGUANXI, AllElementEnum.YongHuZhiXingRenGuanXiElement, YongHuZhiXingRenGuanXiEnum);
						if(MyResponse.FAILED == (int)YongHuZhiXingRenGuanXiResponse.get(MyResponse.STATUS)){
							Common.logError("get element of"+YongHuZhiXingRenGuanXiEnum+"failed from page");
							return false;
						}
						Common.click((WebElement)YongHuZhiXingRenGuanXiResponse.get("ele"));
						YongHuZhiXingRenGuanXiResponse = Common.getWebElement(PageEnum.YONGHUZHIXINGRENGUANXI, AllElementEnum.YongHuZhiXingRenGuanXiElement, YongHuZhiXingRenGuanXiEnum);
						String dataFromPage = ((WebElement)YongHuZhiXingRenGuanXiResponse.get("ele")).getAttribute("value");
						//比较两数值是否相等
						if(!dataFromExcel.contains(dataFromPage)){
							//再点击之后这里做个延时，使页面发生响应变化。如999,999,999->999999
							try {
								Thread.sleep(1000);
								Common.click((WebElement)YongHuZhiXingRenGuanXiResponse.get("ele"));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							YongHuZhiXingRenGuanXiResponse = Common.getWebElement(PageEnum.YONGHUZHIXINGRENGUANXI, AllElementEnum.YongHuZhiXingRenGuanXiElement, YongHuZhiXingRenGuanXiEnum);
							dataFromPage = ((WebElement)YongHuZhiXingRenGuanXiResponse.get("ele")).getAttribute("value");
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
		//小刷新
		Common.clickRefresh();
		Common.driver.switchTo().defaultContent();
		
		Common.review();
		return true;
	}
	@Override
	public boolean unreview(){
		Common.unreviewed();
		return true;
	}
	
	public enum YongHuZhiXingRenGuanXiEnum implements ElementEnum{
		/**
		 * 登入用户名
		 */
		DENGRUYONGHUMING,
		/**
		 * 执行人代码
		 */
		ZHIXINGRENDAIMA,
		/**
		 * 金额下限
		*/
		JINEXIAXIAN,
		/**
		 * 金额上限
		*/
		JINESHANGXIAN,
		/**
		 * 有效开始时间
		*/
		YOUXIAOKAISHISHIJIAN,
		/**
		 * 有效结束时间
		*/
		YOUXIAOJIESHUSHIJIAN,
		/**
		 * 是否需要审核
		*/
		ISCHECKED
	}
}
