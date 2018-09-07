package com.yss.method;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.yss.common.AllElementEnum;
import com.yss.common.Common;
import com.yss.common.Common.CommonElementEnum;
import com.yss.common.MyResponse;
import com.yss.common.PageEnum;
public class ChanPinKaiFangRi {

	public boolean test() throws InterruptedException{
		//切换driver到default
		Common.driver.switchTo().defaultContent();
		// 切换driver到top
		MyResponse iframe1Response = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM1);
		if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("get element data of iframe1 failed");
		}
		Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));

		MyResponse webElements = Common.getWebElements(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.EDIT);
		List l = (List)webElements.get("ele");
		for(int i=0 ;i<204;i++){
			
			webElements = Common.getWebElements(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.EDIT);
			l = (List)webElements.get("ele");
			WebElement w=(WebElement)l.get(0);
			//滚动到可视范围内
			//将滚动条滚动到可视范围内，只要能够定位该元素即可
//			scrollIntoView();
//			参数flase是指元素不会滚动到页面顶部，只要在View页面
//			显示即可
			((JavascriptExecutor) Common.driver).executeScript("arguments[0].scrollIntoView(false);",w);

			Common.click(w);
			MyResponse ifram2 = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM2);
			Common.driver.switchTo().frame((WebElement)ifram2.get("ele"));
			Common.setParameter(Common.driver.findElement(By.xpath(".//*[@id='setYear']"))," ");
			Common.click(Common.driver.findElement(By.xpath(".//*[@id='ext-gen20']")));
			Common.driver.switchTo().defaultContent();
			if( (int)iframe1Response.get(MyResponse.STATUS) == MyResponse.FAILED){
				Common.logError("get element data of iframe1 failed");
			}
			Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));
			
			Common.click((WebElement)Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.POPUP_OK).get("ele"));
			Common.clickRefresh();
//			Thread.sleep(1000);
			Common.driver.switchTo().defaultContent();
			Common.driver.switchTo().frame((WebElement)iframe1Response.get("ele"));

		}
		return true;
	}
}
