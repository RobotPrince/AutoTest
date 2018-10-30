package com.yss.method;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.yss.common.AllElementEnum;
import com.yss.common.Common;
import com.yss.common.ElementEnum;
import com.yss.common.MyResponse;
import com.yss.common.PageEnum;
import com.yss.common.ReadFromExcel;
public class CheckMenu {

	public boolean checkTAMenuText() {
		Common.logInfo("checkTAMenuText");

		// 点击TAB
		List<String> TATabList = ReadFromExcel.elementsFromExcel.get(PageEnum.TAB_MENU).get("TAdengjiguohu");
//		Common.getWebElement(TATabList.get(1), TATabList.get(0)).click();
		
	//	MyResponse myResponse = Common.getWebElement(TATabList.get(1), TATabList.get(0));
//		if( (int)myResponse.get(MyResponse.STATUS) == MyResponse.FAILED){
//			Common.logError("Get Element failed");
//		}
//		((WebElement)myResponse.get("ele")).click();

		// 获取页面元素R
		//方案一和方案二
//		Common.getElementData(PageEnum.TA_MENU,AllElementEnum.CheckMenuElement,"richangyunying_1");
	//	MyResponse response = Common.getElementData(PageEnum.TA_MENU,AllElementEnum.CheckMenuElement,CheckMenuElement.RICHANGYUNYING_1);
		
//		WebElement richangyunying1Ele = Common.getWebElement(list.get(1),
//				list.get(0));
//		richangyunying1Ele.getAttribute("ext:tree-node-id");
		return true;
	}

	
	public enum CheckMenuElement implements ElementEnum{
		
		/**
		 * 日常运营一级
		 */
		RICHANGYUNYING_1,
		/**
		 * 日常运营
		 */
		RICHANGYUNYING_2,
		/**
		 * 产品净值
		 */
		CHANPINJINGZHI_2,
		/**
		 * 份额分级净值管理
		 */
		FENEFENJI_2,
		/**
		 * 比例审核
		 */
		BILISHENHE_2,
		/**
		 * 公告管理
		 */
		GONGGAOGUANLI_2,
		/**
		 * 人工干预
		 */
		RENGONGGAOYU_2,
		/**
		 * 执行人工作日
		 */
		ZHIXINGRENGONGZUORI_2,
		/**
		 * 产品设置一级
		 */
		CHANPINSHEZHI_1,
		/**
		 * 产品信息
		 */
		CHANPINXINXI_2,	
		/**
		 * 产品费率
		 */
		CHANPINFEILU_2,
		/**
		 * 加收转换费率
		 */
		JIASHOUZHUANHUANFEILU_2,
		/**
		 * 产品转换设置
		 */
		CHANPINZHUANHUANSHEZHI_2,
		/**
		 * 产品动态
		 */
		CHANPINDONGTAI_2,
		/**
		 * 分红方案
		 */
		FENHONGFANGAN_2,
		/**
		 * 产品清盘
		 */
		CHANPINQINGPAN_2,
		/**
		 * 违约赎回费率
		 */
		WEIYUESHUHUIFEILU_2,
		/**
		 * 固定收益利率
		 */
		GUDINGSHOUYILILU_2,
		/**
		 * 业绩报酬方案
		 */
		YEJIBAOCHOUFANGAN_2,
		/**
		 * 业绩报酬比例
		 */
		YEJIBAOCHOUBILI_2,
		/**
		 * 产品开放日
		 */
		CHANPINKAIFANGRI_2,
		/**
		 * 募集利率设置
		 */
		MUJILILUSHEZHI_2,
		/**
		 * 固定利率分红
		 */
		GUDINGLILUFENHONG_2,
		/**
		 * 产品份额级别
		 */
		CHANPINFENEJIBIE_2,
		/**
		 * 业务申请一级
		 */
		YEWUSHENQING_1,
		//FIXME:以下为临时增加
		/**
		 * 机构参数
		 */
		JIGOUCANSHU_1,
		/**
		 * 核算机构信息
		 */
		HESUANJIGOUXINXI_2,
		/**
		 * 销售机构信息
		 */
		XIAOSHOUJIGOUXINXI_2,
		/**
		 * 关联机构信息
		 */
		GUANLIANJIGOUXINXI_2,
		/**
		 *产品销售代理关系
		 */
		CHANPINXIAOSHOUDAILIGUANXI_2,
		/**
		 * 费用分成
		 */
		FEIYONGFENCHENG_2,
		/**
		 * 折扣管理
		 */
		ZHEKOUGUANLI_2,

		/**
		 * 参数设置
		 */
		CANSHUSHEZHI_1,
		/**
		 * 产品清算周期
		 */
		CHANPINQINGSUANZHOUQI_2,
		/*
		 * 执行权限设置
		 */
		CHANPINQUANXIANSHEZHI_1,
		/*
		 * 用户执行人关系
		 */
		GONGHUZHIXINGRENGUANXI_2
		


//FIXME:下面的暂时用不上，格式设置同上。 一对一
//		基金账户开户
//		增开交易账户
//		修改账户资料
//		基金账户销户
//		取消交易账户
//		产品认购申请
//		产品申购申请
//		产品赎回申请
//		产品转换申请
//		服务费与尾佣一级
//		销售服务费方案
//		尾随佣金明细
//		尾随佣金方案
//		销售服务费明细
//		TA发起交易一级
//		基金账户冻结
//		基金账户解冻
//		强行调减
//		无份额强行调增
//		强行调增
//		非交易过户
//		强制赎回
//		份额冻结
//		份额解冻
//		查询一级
//		基金账户基本信息
//		账户对应关系
//		账户申请明细
//		账户申请汇总
//		账户确认明细
//		账户确认汇总
//		交易申请明细
//		交易申请汇总
//		交易确认明细
//		交易确认汇总
//		持仓余额
//		权益登记信息
//		分红明细
//		余额明细
//		余额构成流水
//		历史基金余额信息
//		业绩报酬明细
//		机构参数一级
//		核算机构信息




//		机构限额
//		机构账号
//		产品销售结转设置
//		执行权限设置一级
//		执行权限人信息
//		用户执行人关系
//		产品执行人关系
//		参数设置一级
//		账户类业务授权管理
//		账户类参数
//		交易类业务授权管理
//		交易类参数
//		产品清算周期
//		系统参数管理
//		接口文件查看
//		资金监管一级
//		基础信息
//		核对信息
//		核实信息
//		生成申请
//		生成拆息
//		收藏夹
//		jijinzhanghukaihu_2
//		zengkaijiaoyizhanghu_2
//		xiugaizhanghuziliao_2
//		jijinzhanghuxiaohu_2
//		qixiaojiaoyizhanghu_2
//		chanpinrengoushenqing_2
//		chanpinshengoushenqing_2
//		chanpinshuhuishenqing_2
//		chanpinzhuanhuanshenqing_2
//		fuwufeiyuweiyong_1
//		xiaoshoufuwufeifangan_2
//		weisuiyongjinmingxi_2
//		weisuiyongjinfangan_2
//		xiaoshoufuwufeimingxi_2
//		TAfaqijiaoyi_1
//		jijinzhanghudongjie_2
//		jijinzhanghujiedong_2
//		qingxingtiaojian_2
//		wufeneqingxingtiaozeng_2
//		qingxingtiaozeng_2
//		feijiaoyiguohu_2
//		qiangzhishuhui_1
//		fenedongjie_2
//		fenejiedong_2
//		chaxun_1
//		jijinzhanghujibenxinxi_2
//		zhanghuduiyingguanxi_2
//		zhanghushenqingmingxi_2
//		zhanghushenqinghuizong_2
//		zhanghuquerenmingxi_2
//		zhanghuquerenhuizong_2
//		jiaoyishenqingmingxi_2
//		jiaoyishenqinghuizong_2
//		jiaoyiquerenmingxi_2
//		jiaoyiquerenhuizong_2
//		chicangyue_2
//		jiaoyidengjixinxi_2
//		fenhongmingxi_2
//		yuemingxi_2
//		yuegouchengliushui_2
//		lishijijinyuexinxi_2
//		yejibaochoumingxi_2
//		jigoucanshu_1
//		hesuanjigouxinxi_2



//		jigouxine_2

//		jigouzhanghao_2
//		chanpinxiaoshoujiezhuanshezhi_2
//		chanpinquanxianshezhi_1
//		zhixingquanxianrenxinxi_2
//		gonghuzhixingrenguanxi_2
//		chanpinzhixingrenguanxi
//		canshushezhi_1
//		zhanghuleiyewushouquanguanli_2
//		zhanghuleicanshu_2
//		jiaoyilieyewushouquanguanli_2
//		jiaoyileicanshu_2
//		chanpinqingsuanzhouqi_2
//		xitongcanshushezhi_2
//		jiekouwenjianchakan_2
//		jijinjianguan_1
//		jichuxinxi_2
//		heduixinxi_2
//		heshixinxi_2
//		shengchengshenqing_2
//		shengchengchaixi_2
//		shoucangjia_1

	}
}
