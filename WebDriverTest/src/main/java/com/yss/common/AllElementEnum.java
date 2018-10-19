package com.yss.common;
import com.yss.common.Common.CommonElementEnum;
import com.yss.method.ChanPinXinXi.ChanPinXinXiEnum;
import com.yss.method.CheckMenu.CheckMenuElement;
import com.yss.method.GuanLianJiGouXinXi.GuanLianJiGouXinXiEnum;
import com.yss.method.HeSuanJiGouXinXi.HeSuanJiGouXinXiEnum;
import com.yss.method.Login.LoginEnum;
import com.yss.method.RiChangYunYingQingSuan.RiChangYunYingQingSuanEnum;
import com.yss.method.XiaoShouJiGouXinXi.XiaoShouJiGouXinXiEnum;


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
	CommonElementEnum(CommonElementEnum.class);
	
	
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