package com.yss.method;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
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

public class ChanPinXinXi implements BaseInterface {

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
	    //点击产品信息
	    MyResponse chanPinXinXiResponse = Common.getWebElement(PageEnum.TA_MENU, AllElementEnum.CheckMenuElement, CheckMenuElement.CHANPINXINXI_2);
		if((int) chanPinXinXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
			Common.logError("Double click of chanPinXinXiResponse failed");
			return false;
		}
		Common.click((WebElement)chanPinXinXiResponse.get("ele"));
		return false;
	}

	@Override
	public boolean add() {
		Common.logInfo("add");

		//循环所有数据
		int sizeOfData = ReadFromExcel.dataForChanPinXinXiFromExcel.size();
		for(int i = 0; i < sizeOfData; i++){
			//点击新增
			Common.clickTopAdd();
			HashMap<ChanPinXinXiEnum, String> data = ReadFromExcel.dataForChanPinXinXiFromExcel.get(i);
			Set<ChanPinXinXiEnum> set = data.keySet();
			Iterator<ChanPinXinXiEnum> iterator = set.iterator();
			
			while( iterator.hasNext() ){
				//isChecked在这里不需要做任何操作
				ChanPinXinXiEnum eunm = iterator.next();
				if(eunm.equals(ChanPinXinXiEnum.ISCHECKED)){
					continue;
				}
				//若Excel中取出的数值为空，则不需要设置这一项
				if(data.get(eunm).equals("")||data.get(eunm)==null){
					continue;
				}
				//获取add需要的元素
				MyResponse chanPinXinXiResponse = Common.getWebElement(PageEnum.CHANPINXINXI,AllElementEnum.ChanPinXinXiElement,eunm);
				if ((int) chanPinXinXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED) {
					Common.logError("get element data of chanPinXinXiResponse failed");
					return false;
				}
				//processTable
				//将数据填到表单中
				WebElement ele = (WebElement)chanPinXinXiResponse.get("ele");
				String remark = chanPinXinXiResponse.get("rem").toString();
				MyResponse setChanPinXinXiResponse = Common.proccessTable( ele, data.get(eunm),  remark );
				if( (int)setChanPinXinXiResponse.get(MyResponse.STATUS) == MyResponse.FAILED ){		
					Common.logError("set parameter of"+eunm+"failed");
					return false;
				}
				//当产品大类设置完以后展开所有的下拉中的项目
				if(eunm.equals(ChanPinXinXiEnum.CHANPINDALEI)){
					MyResponse ChanPinPullDownRes = Common.getWebElements(PageEnum.CHANPINXINXI, AllElementEnum.ChanPinXinXiElement, ChanPinXinXiEnum.CHANPINPULLDOWN);
					@SuppressWarnings("unchecked")
					List<WebElement> listOfPullDown = (List<WebElement>)ChanPinPullDownRes.get("ele");
					for(int j = 1; j < listOfPullDown.size(); j++){
						Common.click(listOfPullDown.get(j));
					}
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
			myResponse = Common.getWebElements(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.VIEW);
			findElements = (List<WebElement>) myResponse.get("ele");
			//点击查看
			Common.click(findElements.get(i));
			//切换driver至ifram2
			MyResponse webElement = Common.getWebElement(PageEnum.COMMON, AllElementEnum.CommonElementEnum, CommonElementEnum.IFRAM2);
			Common.driver.switchTo().frame((WebElement)webElement.get("ele"));
			//展开所有的项目
			MyResponse ChanPinPullDownRes = Common.getWebElements(PageEnum.CHANPINXINXI, AllElementEnum.ChanPinXinXiElement, ChanPinXinXiEnum.CHANPINPULLDOWN);
			@SuppressWarnings("unchecked")
			List<WebElement> listOfPullDown = (List<WebElement>)ChanPinPullDownRes.get("ele");
			for(int j = 1; j < listOfPullDown.size(); j++){
				Common.click(listOfPullDown.get(j));
			}
			//获取页面中的产品代码
			myResponse = Common.getWebElement(PageEnum.CHANPINXINXI, AllElementEnum.ChanPinXinXiElement, ChanPinXinXiEnum.CHANPINDAIMA);
			//找出在Excel中对应的项
			int sizeOfData = ReadFromExcel.dataForChanPinXinXiFromExcel.size();
			for(int j = 0; j < sizeOfData; j++){
				//获取Excel此行的产品代码
				String chanpindaima  = ReadFromExcel.dataForChanPinXinXiFromExcel.get(j).get(ChanPinXinXiEnum.CHANPINDAIMA);
				//比较找出对于此页面在Excel中的对应行
				if(chanpindaima.equals(((WebElement)myResponse.get("ele")).getAttribute("value"))){
					//比较页面中所涉及到项目的是否正确
					HashMap<ChanPinXinXiEnum, String> hashMap = ReadFromExcel.dataForChanPinXinXiFromExcel.get(j);
					Set<ChanPinXinXiEnum> keySet = hashMap.keySet();
					Iterator<ChanPinXinXiEnum> iterator = keySet.iterator();
					while(iterator.hasNext()){
						//从Excel中取出数据
						ChanPinXinXiEnum chanPinXinXiEnum = iterator.next();
						if(chanPinXinXiEnum.equals(ChanPinXinXiEnum.ISCHECKED)||chanPinXinXiEnum.equals(ChanPinXinXiEnum.CHANPINPULLDOWN)){
							break;
						}
						String dataFromExcel = hashMap.get(chanPinXinXiEnum);
						////若Excel中取出的数值为空,说明该项不存在或不需要比较
						if(dataFromExcel.equals("")||dataFromExcel==null){
							continue;
						}
						//从页面中取出数值
						MyResponse chanPinXinXiResponse = Common.getWebElement(PageEnum.CHANPINXINXI, AllElementEnum.ChanPinXinXiElement, chanPinXinXiEnum);
						if(MyResponse.FAILED == (int)chanPinXinXiResponse.get(MyResponse.STATUS)){
							Common.logError("get element of"+chanPinXinXiEnum+"failed from page");
							return false;
						}
						Common.click((WebElement)chanPinXinXiResponse.get("ele"));
						chanPinXinXiResponse = Common.getWebElement(PageEnum.CHANPINXINXI, AllElementEnum.ChanPinXinXiElement, chanPinXinXiEnum);
						String dataFromPage = ((WebElement)chanPinXinXiResponse.get("ele")).getAttribute("value");
						//比较两数值是否相等
						if(!dataFromExcel.contains(dataFromPage)){
							//再点击之后这里做个延时，使页面发生响应变化。如999,999,999->999999
							try {
//								if(chanPinXinXiEnum.equals(ChanPinXinXiEnum.JIGOUZUIGAOSHENGOUFENE)){
//									Thread.sleep(3000);
//								}
								Thread.sleep(1000);
								Common.click((WebElement)chanPinXinXiResponse.get("ele"));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							chanPinXinXiResponse = Common.getWebElement(PageEnum.CHANPINXINXI, AllElementEnum.ChanPinXinXiElement, chanPinXinXiEnum);
							dataFromPage = ((WebElement)chanPinXinXiResponse.get("ele")).getAttribute("value");
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
	
	public enum ChanPinXinXiEnum implements ElementEnum {
		/**
		 * 份额超规模处理方式
		 */
		FENECHAOGUIMOCHULIFANGSHI,
		/**
		* 产品大类
		*/
		CHANPINDALEI,
		/**
		 * 产品下拉
		 */
		CHANPINPULLDOWN,
		/**
		* 产品代码
		*/
		CHANPINDAIMA,
		/**
		* 产品英文名
		*/
		CHANPINYINGWENMING,
		/**
		* 产品全称
		*/
		CHANPINQUANCHENG,
		/**
		* 产品简称
		*/
		CHANPINJIANCHENG,
		/**
		* 管理人代码
		*/
		GUANLIRENDAIMA,
		/**
		* 托管人代码
		*/
		TUOGUANRENDAIMA,
		/**
		* 发起人代码
		*/
		FAQIRENDAIMA,
		/**
		* 公募标识
		*/
		GONGMUBIAOSHI,
		/**
		* 产品类型
		*/
		CHANPINLEIXING,
		/**
		* 产品币种
		*/
		CHANPINBIZHONG,
		/**
		* 产品状态
		*/
		CHANPINZHUANGTAI,
		/**
		* 交易对象
		*/
		JIAOYIDUIXIANG,
		/**
		* 转换标志
		*/
		ZHUANHUANBIAOZHI,
		/**
		* 产品面值
		*/
		CHANPINMIANZHI,
		/**
		* 费用计算方式
		*/
		FEIYONGJISUANFANGSHI,
		/**
		* 收费方式
		*/
		SHOUFEIFANGSHI,
		/**
		* 产品成立日期
		*/
		CHANPINCHENGLIRIQI,
		/**
		* 产品风险等级
		*/
		CHANPINFENGXIANDENGJI,
		/**
		* 投资顾问名称
		*/
		TOUZIGUWENMINGCHENG,
		/**
		* 投资顾问私募管理人编码
		*/
		TOUZIGUWENSIMUGUANLIRENBIANMA,
		/**
		* 关系用途
		*/
		GUANXIYONGTU,
		/**
		* 募集开始日期
		*/
		MUJIKAISHIRIQI,
		/**
		* 募集结束日期
		*/
		MUJIJIESHURIQI,
		/**
		* 封闭截止日期
		*/
		FENGBIJIEZHIRIQI,
		/**
		* 产品终止日期
		*/
		CHANPINZHONGZHIRIQI,
		/**
		* 产品发行方式
		*/
		CHANPINFAXINGFANGSHI,
		/**
		* 产品发行价格
		*/
		CHANPINFAXINGJIAGE,
		/**
		* 最低募集规模
		*/
		ZUIDIMUJIGUIMO,
		/**
		* 最高募集规模
		*/
		ZUIGAOMUJIGUIMO,
		/**
		* 最低开户数量
		*/
		ZUIDIKAIHUSHULIANG,
		/**
		* 最高开户数量
		*/
		ZUIGAOKAIHUSHULIANG,
		/**
		* 户数超限处理方式
		*/
		HUSHUCHAOXIANCHULIFANGSHI,
		/**
		* 募集期利息处理方式
		*/
		MUJIQILIXICHULIFANGSHI,

		/**
		* 募集期计息起始日期
		*/
		MUJIQIJIXIQISHIRIQI,
		/**
		* 募集期计息截止日期
		*/
		MUJIQIJIXIJIEZHIRIQI,
		/**
		* 超规模末笔处理方式
		*/
		CHAOGUIMOMOBICHULIFANGSHI,
		/**
		* 交易开市时间
		*/
		JIAOYIKAISHISHIJIAN,
		/**
		* 交易闭市时间
		*/
		JIAOYIBISHISHIJIAN,
		/**
		* 巨额赎回比例
		*/
		JUESHUHUIBILI,
		/**
		* 默认分红方式
		*/
		MORENFENHONGFANGSHI,
		/**
		* 账户最低持有份额
		*/
		ZHANGHUZUIDICHIYOUFENE,
		/**
		* 账户最低持有金额
		*/
		ZHANGHUZUIDICHIYOUJINE,
		/**
		* 最低红利转投金额
		*/
		ZUIDIHONGLIZHUANTOUJINE,
		/**
		* 最低产品托管份数
		*/
		ZUIDICHANPINTUOGUANFENSHU,
		/**
		* 最低产品转换份数
		*/
		ZUIDICHANPINZHUANHUANFENSHU,
		/**
		* 产品最低赎回限额
		*/
		CHANPINZUIDISHUHUIXIANE,
		/**
		* 托管费计提费率
		*/
		TUOGUANFEIJITIFEILU,
		/**
		* 管理费计提费率
		*/
		GUANLIFEIJITIFEILU,
		/**
		* 投资方向
		*/
		TOUZIFANGXIANG,
		/**
		* 是否保本
		*/
		SHIFOUBAOBEN,
		/**
		* 是否LOF
		*/
		SHIFOULOF,
		/**
		* 是否QDII
		*/
		SHIFOUQDII,
		/**
		* 是否ETF
		*/
		SHIFOUETF,
		/**
		* 个人首次认购最低金额
		*/
		GERENSHOUCIRENGOUZUIDIJINE,
		/**
		* 个人首次申购最低金额
		*/
		GERENSHOUCISHENGOUZUIDIJINE,
		/**
		* 个人单笔申购最高金额
		*/
		GERENDANBISHENGOUZUIGAOJINE,
		/**
		* 个人最高申购金额
		*/
		GERENZUIGAOSHENGOUJINE,
		/**
		* 个人最高认购金额
		*/
		GERENZUIGAORENGOUJINE,
		/**
		* 个人最高申购份额
		*/
		GERENZUIGAOSHENGOUFENE,
		/**
		* 个人最高认购份额
		*/
		GERENZUIGAORENGOUFENE,
		/**
		* 个人申购金额单位
		*/
		GERENSHENGOUJINEDANWEI,
		/**
		* 个人认购金额单位
		*/
		GERENRENGOUJINEDANWEI,
		/**
		* 个人追加认购最低金额
		*/
		GERENZHUIJIARENGOUZUIDIJINE,
		/**
		* 个人追加申购最低金额
		*/
		GERENZHUIJIASHENGOUZUIDIJINE,
		/**
		* 个人单日累计购买最高金额
		*/
		GERENDANRILEIJIGOUMAIZUIGAOJINE,
		/**
		* 个人单日累计赎回最高份额
		*/
		GERENDANRILEIJISHUHUIZUIGAOFENE,
		/**
		* 个人最高赎回份额
		*/
		GERENZUIGAOSHUHUIFENE,
		/**
		* 机构首次认购最低金额
		*/
		JIGOUSHOUCIRENGOUZUIDIJINE,
		/**
		* 机构首次申购最低金额
		*/
		JIGOUSHOUCISHENGOUZUIDIJINE,
		/**
		* 机构单笔申购最高金额
		*/
		JIGOUDANBISHENGOUZUIGAOJINE,
		/**
		* 机构最高申购金额
		*/
		JIGOUZUIGAOSHENGOUJINE,
		/**
		* 机构最高认购金额
		*/
		JIGOUZUIGAORENGOUJINE,
		/**
		* 机构最高申购份额
		*/
		JIGOUZUIGAOSHENGOUFENE,
		/**
		* 机构最高认购份额
		*/
		JIGOUZUIGAORENGOUFENE,
		/**
		* 机构申购金额单位
		*/
		JIGOUSHENGOUJINEDANWEI,
		/**
		* 机构认购金额单位
		*/
		JIGOURENGOUJINEDANWEI,
		/**
		* 机构追加认购最低金额
		*/
		JIGOUZHUIJIARENGOUZUIDIJINE,
		/**
		* 机构追加申购最低金额
		*/
		JIGOUZHUIJIASHENGOUZUIDIJINE,
		/**
		* 机构单日累计购买最高金额
		*/
		JIGOUDANRILEIJIGOUMAIZUIGAOJINE,
		/**
		* 机构单日累计赎回最高份额
		*/
		JIGOUDANRILEIJISHUHUIZUIGAOFENE,
		/**
		* 机构最高赎回份额
		*/
		JIGOUZUIGAOSHUHUIFENE,
		/**
		到期前N日提醒
		*/
		DAOQIQIANNRITIXING,
		/**
		* 专户产品属性
		*/
		ZHUANHUCHANPINSHUXING,
		/**
		* 封闭期清算频率
		*/
		FENGBIQIQINGSUANPINLU,
		/**
		* 违约赎回是否收取赎回费
		*/
		WEIYUESHUHUISHIHOUSHOUQUSHUHUIFEI,
		/**
		* 收益计算年天1
		*/
		SHOUYIJISUANNIANTIAN1,
		/**
		* 业绩提成尾差处理
		*/
		YEJITICHENGWEICHASHULI,
		/**
		* 审查备案日期
		*/
		SHENCHABEIANRIQI,
		/**
		* 合同生效日期
		*/
		HETONGSHENGXIAORIQI,
		/**
		* 合同终止日期
		*/
		HETONGZHONGZHIRIQI,
		/**
		* 违约金计算公式1
		*/
		WEIYUEJINJISUANGONGSHI1,
		/**
		* 收益计算年天2
		*/
		SHOUYIJISUANNIANTIAN2,
		/**
		* 违约金计算公式2
		*/
		WEIYUEJINJISUANGONGSHI2,
		/**
		* 时间累进计算
		*/
		SHIJIANLEIJINJISUAN,
		/**
		* 违约天数
		*/
		WEIYUETIANSHU,
		/**
		* 收益计算年天3
		*/
		SHOUYIJISUANNIANTIAN3,
		/**
		 * 是否需要审核
		 */
		ISCHECKED
	}
}
