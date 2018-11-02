package com.yss.common;
import com.yss.common.Common.CommonElementEnum;
import com.yss.method.ChanPinDongTai.ChanPinDongTaiEnum;
import com.yss.method.ChanPinFeiLv.ChanPinFeiLvEnum;
import com.yss.method.ChanPinQingSuanZhouQi.ChanPinQingSuanZhouQiEnum;
import com.yss.method.ChanPinXiaoShouDaiLiGuanXi.ChanPinXiaoShouDaiLiGuanXiEnum;
import com.yss.method.ChanPinXinXi.ChanPinXinXiEnum;
import com.yss.method.ChanPinZhiXingRenGuanXi.ChanPinZhiXingRenGuanXiEnum;
import com.yss.method.CheckMenu.CheckMenuElement;
import com.yss.method.FeiYongFenCheng.FeiYongFenChengEnum;
import com.yss.method.GuDingShouYiLiLv.GuDingShouYiLiLvEnum;
import com.yss.method.GuanLianJiGouXinXi.GuanLianJiGouXinXiEnum;
import com.yss.method.HeSuanJiGouXinXi.HeSuanJiGouXinXiEnum;
import com.yss.method.Login.LoginEnum;
import com.yss.method.RiChangYunYingQingSuan.RiChangYunYingQingSuanEnum;
import com.yss.method.WeiYueShuHuiFeiLu.WeiYueShuHuiFeiLuEnum;
import com.yss.method.XiaoShouJiGouXinXi.XiaoShouJiGouXinXiEnum;
import com.yss.method.YinHangJiBenXinXi.YinHangJiBenXinXiEnum;
import com.yss.method.YongHuZhiXingRenGuanXi.YongHuZhiXingRenGuanXiEnum;
import com.yss.method.ZheKouGuanLi.ZheKouGuanLiEnum;
import com.yss.method.ZhiXingQuanXianRenXinXi.ZhiXingQuanXianRenXinXiEnum;

/**
 * Element enum of all
 * @author tanglonglong
 *
 */
public enum AllElementEnum{
	
	CheckMenuElement(CheckMenuElement.class),
	LoginElement(LoginEnum.class),
	RiChangYunYingQingSuanElement(RiChangYunYingQingSuanEnum.class),
	HeSuanJiGouXinXiElement(HeSuanJiGouXinXiEnum.class),
	XiaoShouJiGouXinXiElement(XiaoShouJiGouXinXiEnum.class),
	GuanLianJiGouXinXiElement(GuanLianJiGouXinXiEnum.class),
	ChanPinXinXiElement(ChanPinXinXiEnum.class),
	CommonElementEnum(CommonElementEnum.class),
	ChanPinFeiLvElement(ChanPinFeiLvEnum.class),
	ChanPinXiaoShouDaiLiGuanXiElement(ChanPinXiaoShouDaiLiGuanXiEnum.class),
	FeiYongFenChengElement(FeiYongFenChengEnum.class),
	ZheKouGuanLiElement(ZheKouGuanLiEnum.class),

	ChanPinZhiXingRenGuanXiElement(ChanPinZhiXingRenGuanXiEnum.class),
	ChanPinQingSuanZhouQiElement(ChanPinQingSuanZhouQiEnum.class),

	YongHuZhiXingRenGuanXiElement(YongHuZhiXingRenGuanXiEnum.class),
	WeiYueShuHuiFeiLuElement(WeiYueShuHuiFeiLuEnum.class),

	GuDingShouYiLiLvElement(GuDingShouYiLiLvEnum.class),
	ZhiXingQuanXianRenXinXiElement(ZhiXingQuanXianRenXinXiEnum.class),
	ChanPinDongTaiElement(ChanPinDongTaiEnum.class) ,
	YinHangJiBenXinXiElement(YinHangJiBenXinXiEnum.class);
	
	private ElementEnum[] elementEnums;
	private AllElementEnum( Class<? extends ElementEnum> e){
		elementEnums=e.getEnumConstants();
	}
	public String[] valueToString(){
		
		String[] str = new String[elementEnums.length];
		
		for(int i = 0;i < str.length; i++){
			str[i] = elementEnums[i].toString();
		}
		return str;
	}
}