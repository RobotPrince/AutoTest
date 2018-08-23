package com.yss.common;
import com.yss.common.Common.CommonElementEnum;
import com.yss.method.CheckMenu.CheckMenuElement;
import com.yss.method.HeSuanJiGouXinXi.HeSuanJiGouXinXiEnum;
import com.yss.method.Login.LoginEnum;


/**
 * Element enum of all
 * @author tanglonglong
 *
 */
public enum AllElementEnum{
	
	CheckMenuElement(CheckMenuElement.class),
	LoginElement(LoginEnum.class),
	HeSuanJiGouXinXiElement(HeSuanJiGouXinXiEnum.class),
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