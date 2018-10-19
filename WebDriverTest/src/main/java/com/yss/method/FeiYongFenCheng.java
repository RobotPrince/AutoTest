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


public class FeiYongFenCheng implements BaseInterface {

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
			Common.logError("Double click of chanpinshezhi1 failed");
			return false;
		}
		Common.click((WebElement)jiGouCanShuResponse.get("ele"));
	    //点击费用分成
	    MyResponse FeiYongFenChengResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.FEIYONGFENCHENG_2);
		if((int) FeiYongFenChengResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of FeiYongFenChengResponse failed");
			return false;
		}
		Common.click((WebElement)FeiYongFenChengResponse.get("ele"));
		return false;
	}

	@Override
	public boolean add() {
		Common.logInfo("add");

		//循环所有数据
		int sizeOfData = ReadFromExcel.dataForFeiYongFenChengFromExcel.size();
		for(int i = 0; i < sizeOfData; i++){
			//点击新增
			Common.clickTopAdd();
			HashMap<FeiYongFenChengEnum, String> data = ReadFromExcel.dataForFeiYongFenChengFromExcel.get(i);
			Set<FeiYongFenChengEnum> set = data.keySet();
			Iterator<FeiYongFenChengEnum> iterator = set.iterator();
			
			while( iterator.hasNext() ){
				//isChecked在这里不需要做任何操作
				FeiYongFenChengEnum eunm = iterator.next();
				if(eunm.equals(FeiYongFenChengEnum.ISCHECKED)){
					continue;
				}
				//若Excel中取出的数值为空，则不需要设置这一项
				if(data.get(eunm).equals("")||data.get(eunm)==null){
					continue;
				}
				
				// 此处可能会随页面需要将display:none转为display:inline-block
				if(eunm.equals(FeiYongFenChengEnum.CHANPINDAIMA)||eunm.equals(FeiYongFenChengEnum.JIGOUDAIMA) || eunm.equals(FeiYongFenChengEnum.YEWULEIXING)){
					Common.getWebElementForSelect(PageEnum.FEIYONGFENCHENG,AllElementEnum.FeiYongFenChengElement,eunm);
					Common.logInfo("Change display to inline-block success");
				}
				//获取add需要的元素
				MyResponse FeiYongFenChengResponse = Common.getWebElement(PageEnum.FEIYONGFENCHENG,AllElementEnum.FeiYongFenChengElement,eunm);
				if ((int) FeiYongFenChengResponse.get(MyResponse.STATUS) == MyResponse.FAILED) {
					Common.logError("get element data of FeiYongFenChengResponse failed");
					return false;
				}
				//processTable
				//将数据填到表单中
				WebElement ele = (WebElement)FeiYongFenChengResponse.get("ele");
				String remark = FeiYongFenChengResponse.get("rem").toString();
				MyResponse setFeiYongFenChengResponse = Common.proccessTable( ele, data.get(eunm),  remark );
				if( (int)setFeiYongFenChengResponse.get(MyResponse.STATUS) == MyResponse.FAILED ){		
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
			//获取页面中的业务类型
			MyResponse yeWuLeiXingResponse = Common.getWebElementForSelect(PageEnum.FEIYONGFENCHENG, AllElementEnum.FeiYongFenChengElement, FeiYongFenChengEnum.YEWULEIXING);
			//获取页面中的产品代码
			MyResponse chanPinDaiMaResponse = Common.getWebElementForSelect(PageEnum.FEIYONGFENCHENG, AllElementEnum.FeiYongFenChengElement, FeiYongFenChengEnum.CHANPINDAIMA);
			Common.getWebElementForSelect(PageEnum.FEIYONGFENCHENG, AllElementEnum.FeiYongFenChengElement, FeiYongFenChengEnum.JIGOUDAIMA);
			//找出在Excel中对应的项
			int sizeOfData = ReadFromExcel.dataForFeiYongFenChengFromExcel.size();
			for(int j = 0; j < sizeOfData; j++){
				//获取Excel此行的产品代码
				String chanpindaima  = ReadFromExcel.dataForFeiYongFenChengFromExcel.get(j).get(FeiYongFenChengEnum.CHANPINDAIMA);
				//获取Excel此行的业务类型
				String yewuleixing  = ReadFromExcel.dataForFeiYongFenChengFromExcel.get(j).get(FeiYongFenChengEnum.YEWULEIXING);
				//比较找出对于此页面在Excel中的对应行
				if(((WebElement)chanPinDaiMaResponse.get("ele")).getAttribute("value").contains(chanpindaima)&&((WebElement)yeWuLeiXingResponse.get("ele")).getAttribute("value").contains(yewuleixing)){
					//比较页面中所涉及到项目的是否正确
					HashMap<FeiYongFenChengEnum, String> hashMap = ReadFromExcel.dataForFeiYongFenChengFromExcel.get(j);
					Set<FeiYongFenChengEnum> keySet = hashMap.keySet();
					Iterator<FeiYongFenChengEnum> iterator = keySet.iterator();
					while(iterator.hasNext()){
						//从Excel中取出数据
						FeiYongFenChengEnum FeiYongFenChengEnum = iterator.next();
						if(FeiYongFenChengEnum.equals(FeiYongFenChengEnum.ISCHECKED)){
							break;
						}
						String dataFromExcel = hashMap.get(FeiYongFenChengEnum);
						////若Excel中取出的数值为空,说明该项不存在或不需要比较
						if(dataFromExcel.equals("")||dataFromExcel==null){
							continue;
						}
						//从页面中取出数值
						MyResponse FeiYongFenChengResponse = Common.getWebElement(PageEnum.FEIYONGFENCHENG, AllElementEnum.FeiYongFenChengElement, FeiYongFenChengEnum);
						if(MyResponse.FAILED == (int)FeiYongFenChengResponse.get(MyResponse.STATUS)){
							Common.logError("get element of"+FeiYongFenChengEnum+"failed from page");
							return false;
						}
						Common.click((WebElement)FeiYongFenChengResponse.get("ele"));
						FeiYongFenChengResponse = Common.getWebElement(PageEnum.FEIYONGFENCHENG, AllElementEnum.FeiYongFenChengElement, FeiYongFenChengEnum);
						String dataFromPage = ((WebElement)FeiYongFenChengResponse.get("ele")).getAttribute("value");
						//比较两数值是否相等
						if(!dataFromExcel.contains(dataFromPage)){
							//再点击之后这里做个延时，使页面发生响应变化。如999,999,999->999999
							try {
								Thread.sleep(1000);
								Common.click((WebElement)FeiYongFenChengResponse.get("ele"));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							FeiYongFenChengResponse = Common.getWebElement(PageEnum.FEIYONGFENCHENG, AllElementEnum.FeiYongFenChengElement, FeiYongFenChengEnum);
							dataFromPage = ((WebElement)FeiYongFenChengResponse.get("ele")).getAttribute("value");
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
	
	public enum FeiYongFenChengEnum implements ElementEnum {
		/**
		 * 分成方案名称
		 */
		FENCHENGFANGANMINGCHENG,
		/**
		 * 分成方案号
	 	*/
		FENCHENGFANGANHAO,
		/**
		 * 机构代码
		*/
		JIGOUDAIMA,
		/**
		 * 产品代码
		*/
		CHANPINDAIMA,
		/**
		 * 业务类型
		*/
		YEWULEIXING,
		/**
		 * 分成比例
		*/
		FENCHENGBILI,
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
