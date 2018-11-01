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
/**
 * 
 * @author anqi
 *
 */	

public class YinHangJiBenXinXi implements BaseInterface {

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
		//点击业务申请
		MyResponse yeWuShenQingResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.YEWUSHENQING_1);
		if((int) yeWuShenQingResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of yewushenqing1 failed");
			return false;
		}
		Common.click((WebElement)yeWuShenQingResponse.get("ele"));
	    //点击银行基本信息
	    MyResponse YinHangJiBenXinXiResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.YINHANGJIBENXINXI_2);
		if((int) YinHangJiBenXinXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of YinHangJiBenXinXiResponse failed");
			return false;
		}
		Common.click((WebElement)YinHangJiBenXinXiResponse.get("ele"));
		return false;
	}

	@Override
	public boolean add() {
		Common.logInfo("add");

		//循环所有数据
		int sizeOfData = ReadFromExcel.dataForYinHangJiBenXinXiFromExcel.size();
		for(int i = 0; i < sizeOfData; i++){
			//点击新增
			Common.clickTopAdd();
			HashMap<YinHangJiBenXinXiEnum, String> data = ReadFromExcel.dataForYinHangJiBenXinXiFromExcel.get(i);
			Set<YinHangJiBenXinXiEnum> set = data.keySet();
			Iterator<YinHangJiBenXinXiEnum> iterator = set.iterator();
			
			while( iterator.hasNext() ){
				//isChecked在这里不需要做任何操作
				YinHangJiBenXinXiEnum eunm = iterator.next();
				if(eunm.equals(YinHangJiBenXinXiEnum.ISCHECKED)){
					continue;
				}
				//若Excel中取出的数值为空，则不需要设置这一项
				if(data.get(eunm).equals("")||data.get(eunm)==null){
					continue;
				}
				
				// 此处可能会随页面需要将display:none转为display:inline-block
//				if(eunm.equals(YinHangJiBenXinXiEnum.CHANPINDAIMA)||eunm.equals(YinHangJiBenXinXiEnum.JIGOUDAIMA) || eunm.equals(YinHangJiBenXinXiEnum.YEWULEIXINGDAIMA)){
//					Common.getWebElementForSelect(PageEnum.YINHANGJIBENXINXI,AllElementEnum.YinHangJiBenXinXiElement,eunm);
//					Common.logInfo("Change display to inline-block success");
//				}
				//获取add需要的元素
				MyResponse YinHangJiBenXinXiResponse = Common.getWebElement(PageEnum.YINHANGJIBENXINXI,AllElementEnum.YinHangJiBenXinXiElement,eunm);
				if ((int) YinHangJiBenXinXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED) {
					Common.logError("get element data of YinHangJiBenXinXiResponse failed");
					return false;
				}
				//processTable
				//将数据填到表单中
				WebElement ele = (WebElement)YinHangJiBenXinXiResponse.get("ele");
				String remark = YinHangJiBenXinXiResponse.get("rem").toString();
				MyResponse setYinHangJiBenXinXiResponse = Common.proccessTable( ele, data.get(eunm),  remark );
				if( (int)setYinHangJiBenXinXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED ){		
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
			//获取页面中的银行代码
			MyResponse yinHangDaiMaResponse = Common.getWebElementForSelect(PageEnum.YINHANGJIBENXINXI, AllElementEnum.YinHangJiBenXinXiElement, YinHangJiBenXinXiEnum.YINHANGDAIMA);
			//获取页面中的银行名称
			MyResponse yinHangMingChengResponse = Common.getWebElementForSelect(PageEnum.YINHANGJIBENXINXI, AllElementEnum.YinHangJiBenXinXiElement, YinHangJiBenXinXiEnum.YINHANGMINGCHENG);
			//获取页面中的服务热线
			MyResponse fuWuReXianResponse = Common.getWebElementForSelect(PageEnum.YINHANGJIBENXINXI, AllElementEnum.YinHangJiBenXinXiElement, YinHangJiBenXinXiEnum.FUWUREXIAN);
			//获取页面中的联系电话
			MyResponse lianXiDianHuaResponse = Common.getWebElementForSelect(PageEnum.YINHANGJIBENXINXI, AllElementEnum.YinHangJiBenXinXiElement, YinHangJiBenXinXiEnum.LIANXIDIANHUA);
			//获取页面中的官网网址
			MyResponse guanWangWangZhiResponse = Common.getWebElementForSelect(PageEnum.YINHANGJIBENXINXI, AllElementEnum.YinHangJiBenXinXiElement, YinHangJiBenXinXiEnum.GUANWANGWANGZHI);
			//获取页面中的详细地址
			MyResponse xiangXiDiZhiResponse = Common.getWebElementForSelect(PageEnum.YINHANGJIBENXINXI, AllElementEnum.YinHangJiBenXinXiElement, YinHangJiBenXinXiEnum.XIANGXIDIZHI);
			//找出在Excel中对应的项
			int sizeOfData = ReadFromExcel.dataForYinHangJiBenXinXiFromExcel.size();
			for(int j = 0; j < sizeOfData; j++){
				//获取Excel此行的银行代码
				String yinhangdaima = ReadFromExcel.dataForYinHangJiBenXinXiFromExcel.get(j).get(YinHangJiBenXinXiEnum.YINHANGDAIMA);
				//获取Excel此行的银行名称
				String yinhangmingcheng  = ReadFromExcel.dataForYinHangJiBenXinXiFromExcel.get(j).get(YinHangJiBenXinXiEnum.YINHANGMINGCHENG);
				//获取Excel此行的服务热线
				String fuwurexian  = ReadFromExcel.dataForYinHangJiBenXinXiFromExcel.get(j).get(YinHangJiBenXinXiEnum.FUWUREXIAN);
				//获取Excel此行的联系电话
				String lianxidianhua = ReadFromExcel.dataForYinHangJiBenXinXiFromExcel.get(j).get(YinHangJiBenXinXiEnum.LIANXIDIANHUA);
				//获取Excel此行的官网网址
				String guanwangwangzhi  = ReadFromExcel.dataForYinHangJiBenXinXiFromExcel.get(j).get(YinHangJiBenXinXiEnum.GUANWANGWANGZHI);
				//获取Excel此行的详细地址
				String xiangxidizhi  = ReadFromExcel.dataForYinHangJiBenXinXiFromExcel.get(j).get(YinHangJiBenXinXiEnum.XIANGXIDIZHI);
				//比较找出对于此页面在Excel中的对应行
				if(((WebElement)yinHangDaiMaResponse.get("ele")).getAttribute("value").contains(yinhangdaima)
						&&((WebElement)yinHangMingChengResponse.get("ele")).getAttribute("value").contains(yinhangmingcheng)
						){
					//比较页面中所涉及到项目的是否正确
					HashMap<YinHangJiBenXinXiEnum, String> hashMap = ReadFromExcel.dataForYinHangJiBenXinXiFromExcel.get(j);
					Set<YinHangJiBenXinXiEnum> keySet = hashMap.keySet();
					Iterator<YinHangJiBenXinXiEnum> iterator = keySet.iterator();
					while(iterator.hasNext()){
						//从Excel中取出数据
						YinHangJiBenXinXiEnum YinHangJiBenXinXiEnum = iterator.next();
						if(YinHangJiBenXinXiEnum.equals(YinHangJiBenXinXiEnum.ISCHECKED)){
							break;
						}
						String dataFromExcel = hashMap.get(YinHangJiBenXinXiEnum);
						////若Excel中取出的数值为空,说明该项不存在或不需要比较
						if(dataFromExcel.equals("")||dataFromExcel==null){
							continue;
						}
						//从页面中取出数值
						MyResponse YinHangJiBenXinXiResponse = Common.getWebElement(PageEnum.YINHANGJIBENXINXI, AllElementEnum.YinHangJiBenXinXiElement, YinHangJiBenXinXiEnum);
						if(MyResponse.FAILED == (int)YinHangJiBenXinXiResponse.get(MyResponse.STATUS)){
							Common.logError("get element of"+YinHangJiBenXinXiEnum+"failed from page");
							return false;
						}
						Common.click((WebElement)YinHangJiBenXinXiResponse.get("ele"));
						YinHangJiBenXinXiResponse = Common.getWebElement(PageEnum.YINHANGJIBENXINXI, AllElementEnum.YinHangJiBenXinXiElement, YinHangJiBenXinXiEnum);
						String dataFromPage = ((WebElement)YinHangJiBenXinXiResponse.get("ele")).getAttribute("value");
						//比较两数值是否相等
						if(!dataFromExcel.contains(dataFromPage)){
							//再点击之后这里做个延时，使页面发生响应变化。如999,999,999->999999
							try {
								Thread.sleep(1000);
								Common.click((WebElement)YinHangJiBenXinXiResponse.get("ele"));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							YinHangJiBenXinXiResponse = Common.getWebElement(PageEnum.YINHANGJIBENXINXI, AllElementEnum.YinHangJiBenXinXiElement, YinHangJiBenXinXiEnum);
							dataFromPage = ((WebElement)YinHangJiBenXinXiResponse.get("ele")).getAttribute("value");
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
	
	public enum YinHangJiBenXinXiEnum implements ElementEnum {
		/**
		 * 银行代码
		*/
		YINHANGDAIMA,
		/**
		 * 银行名称
		*/
		YINHANGMINGCHENG,
		/**
		 * 服务热线
		*/
		FUWUREXIAN,
		/**
		 * 联系电话
		*/
		LIANXIDIANHUA,
		/**
		 * 官网网址
		*/
		GUANWANGWANGZHI,
		/**
		 * 详细地址
		*/
		XIANGXIDIZHI,
		/**
		 * 是否需要审核
		*/
		ISCHECKED

	}
}
