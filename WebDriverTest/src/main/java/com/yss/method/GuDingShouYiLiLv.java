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

public class GuDingShouYiLiLv implements BaseInterface {

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
		MyResponse chanPinSheZhiResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.CHANPINSHEZHI_1);
		if((int) chanPinSheZhiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of chanpinshezhi1 failed");
			return false;
		}
		Common.click((WebElement)chanPinSheZhiResponse.get("ele"));
	    //点击固定收益利率
	    MyResponse GuDingShouYiLiLvResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.GUDINGSHOUYILILV_2);
		if((int) GuDingShouYiLiLvResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of GuDingShouYiLiLvResponse failed");
			return false;
		}
		Common.click((WebElement)GuDingShouYiLiLvResponse.get("ele"));
		return false;
	}

	@Override
	public boolean add() {
		Common.logInfo("add");

		//循环所有数据
		int sizeOfData = ReadFromExcel.dataForGuDingShouYiLiLvFromExcel.size();
		for(int i = 0; i < sizeOfData; i++){
			//点击新增
			Common.clickTopAdd();
			HashMap<GuDingShouYiLiLvEnum, String> data = ReadFromExcel.dataForGuDingShouYiLiLvFromExcel.get(i);
			Set<GuDingShouYiLiLvEnum> set = data.keySet();
			Iterator<GuDingShouYiLiLvEnum> iterator = set.iterator();
			
			while( iterator.hasNext() ){
				//isChecked在这里不需要做任何操作
				GuDingShouYiLiLvEnum eunm = iterator.next();
				if(eunm.equals(GuDingShouYiLiLvEnum.ISCHECKED)){
					continue;
				}
				//若Excel中取出的数值为空，则不需要设置这一项
				if(data.get(eunm).equals("")||data.get(eunm)==null){
					continue;
				}
				
//				// 此处可能会随页面需要将display:none转为display:inline-block
//				if(eunm.equals(GuDingShouYiLiLvEnum.XIAOSHOUJIGOU)||eunm.equals(GuDingShouYiLiLvEnum.CHANPINDAIMA) || eunm.equals(GuDingShouYiLiLvEnum.QISHU)){
//					Common.getWebElementForSelect(PageEnum.GUDINGSHOUYILILV,AllElementEnum.GuDingShouYiLiLvElement,eunm);
//					Common.logInfo("Change display to inline-block success");
//				}
				//获取add需要的元素
				MyResponse GuDingShouYiLiLvResponse = Common.getWebElement(PageEnum.GUDINGSHOUYILILV,AllElementEnum.GuDingShouYiLiLvElement,eunm);
				if ((int) GuDingShouYiLiLvResponse.get(MyResponse.STATUS) == MyResponse.FAILED) {
					Common.logError("get element data of GuDingShouYiLiLvResponse failed");
					return false;
				}
				//processTable
				//将数据填到表单中
				WebElement ele = (WebElement)GuDingShouYiLiLvResponse.get("ele");
				String remark = GuDingShouYiLiLvResponse.get("rem").toString();
				MyResponse setGuDingShouYiLiLvResponse = Common.proccessTable( ele, data.get(eunm),  remark );
				if( (int)setGuDingShouYiLiLvResponse.get(MyResponse.STATUS) == MyResponse.FAILED ){		
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
			//获取页面中的销售机构
			MyResponse xiaoShouJiGouResponse = Common.getWebElementForSelect(PageEnum.GUDINGSHOUYILILV, AllElementEnum.GuDingShouYiLiLvElement, GuDingShouYiLiLvEnum.XIAOSHOUJIGOU);
			//获取页面中的产品代码
			MyResponse chanPinDaiMaResponse = Common.getWebElementForSelect(PageEnum.GUDINGSHOUYILILV, AllElementEnum.GuDingShouYiLiLvElement, GuDingShouYiLiLvEnum.CHANPINDAIMA);
			//获取页面中的期数
			MyResponse qiShuResponse = Common.getWebElementForSelect(PageEnum.GUDINGSHOUYILILV, AllElementEnum.GuDingShouYiLiLvElement, GuDingShouYiLiLvEnum.QISHU);
			//找出在Excel中对应的项
			int sizeOfData = ReadFromExcel.dataForGuDingShouYiLiLvFromExcel.size();
			for(int j = 0; j < sizeOfData; j++){
				//获取Excel此行的销售机构
				String xiaoshoujigou = ReadFromExcel.dataForGuDingShouYiLiLvFromExcel.get(j).get(GuDingShouYiLiLvEnum.XIAOSHOUJIGOU);
				//获取Excel此行的产品代码
				String chanpindaima  = ReadFromExcel.dataForGuDingShouYiLiLvFromExcel.get(j).get(GuDingShouYiLiLvEnum.CHANPINDAIMA);
				//获取Excel此行的期数
				String qishu  = ReadFromExcel.dataForGuDingShouYiLiLvFromExcel.get(j).get(GuDingShouYiLiLvEnum.QISHU);
				//比较找出对于此页面在Excel中的对应行
				if(((WebElement)xiaoShouJiGouResponse.get("ele")).getAttribute("value").contains(xiaoshoujigou)
						&&((WebElement)chanPinDaiMaResponse.get("ele")).getAttribute("value").contains(chanpindaima)
						&&((WebElement)qiShuResponse.get("ele")).getAttribute("value").contains(qishu)){
					//比较页面中所涉及到项目的是否正确
					HashMap<GuDingShouYiLiLvEnum, String> hashMap = ReadFromExcel.dataForGuDingShouYiLiLvFromExcel.get(j);
					Set<GuDingShouYiLiLvEnum> keySet = hashMap.keySet();
					Iterator<GuDingShouYiLiLvEnum> iterator = keySet.iterator();
					while(iterator.hasNext()){
						//从Excel中取出数据
						GuDingShouYiLiLvEnum GuDingShouYiLiLvEnum = iterator.next();
						if(GuDingShouYiLiLvEnum.equals(GuDingShouYiLiLvEnum.ISCHECKED)){
							break;
						}
						String dataFromExcel = hashMap.get(GuDingShouYiLiLvEnum);
						////若Excel中取出的数值为空,说明该项不存在或不需要比较
						if(dataFromExcel.equals("")||dataFromExcel==null){
							continue;
						}
						//从页面中取出数值
						MyResponse GuDingShouYiLiLvResponse = Common.getWebElement(PageEnum.GUDINGSHOUYILILV, AllElementEnum.GuDingShouYiLiLvElement, GuDingShouYiLiLvEnum);
						if(MyResponse.FAILED == (int)GuDingShouYiLiLvResponse.get(MyResponse.STATUS)){
							Common.logError("get element of"+GuDingShouYiLiLvEnum+"failed from page");
							return false;
						}
						Common.click((WebElement)GuDingShouYiLiLvResponse.get("ele"));
						GuDingShouYiLiLvResponse = Common.getWebElement(PageEnum.GUDINGSHOUYILILV, AllElementEnum.GuDingShouYiLiLvElement, GuDingShouYiLiLvEnum);
						String dataFromPage = ((WebElement)GuDingShouYiLiLvResponse.get("ele")).getAttribute("value");
						//比较两数值是否相等
						if(!dataFromExcel.contains(dataFromPage)){
							//再点击之后这里做个延时，使页面发生响应变化。如999,999,999->999999
							try {
								Thread.sleep(1000);
								Common.click((WebElement)GuDingShouYiLiLvResponse.get("ele"));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							GuDingShouYiLiLvResponse = Common.getWebElement(PageEnum.GUDINGSHOUYILILV, AllElementEnum.GuDingShouYiLiLvElement, GuDingShouYiLiLvEnum);
							dataFromPage = ((WebElement)GuDingShouYiLiLvResponse.get("ele")).getAttribute("value");
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
	
	public enum GuDingShouYiLiLvEnum implements ElementEnum {
		/**
		 * 销售机构
		*/
		XIAOSHOUJIGOU,
		/**
		 * 产品代码
		*/
		CHANPINDAIMA,
		/**
		 * 固收明细流水号
		*/
		GUSHOUMINGXILIUSHUIHAO,
		/**
		 * 年化收益率
		*/
		NIANHUASHOUYILV,
		/**
		 * 金额下限(元)
		*/
		JINEXIAXIAN,
		/**
		 * 金额上限(元)
		*/
		JINESHANGXIAN,
		/**
		 * 持有时间下限(日)
		*/
		CHIYOUSHIJIANXIAXIAN,
		/**
		 * 持有时间上限(日)
		*/
		CHIYOUSHIJIANSHANGXIAN,
		/**
		 * 最低收益(元)
		*/
		ZUIDISHOUYI,
		/**
		 * 最高收益(元)
		*/
		ZUIGAOSHOUYI,
		/**
		 * 开放起始日期
		*/
		KAIFANGQISHIRIQI,
		/**
		 * 开放截止日期
		*/
		KAIFANGJIEZHIRIQI,
		/**
		 * 期数
		*/
		QISHU,
		/**
		 * 利息结算日期
		*/
		LIXIJIESUANRIQI,
		/**
		 * 是否需要审核
		*/
		ISCHECKED

	}
}
