package com.yss.common;
import com.yss.method.CheckMenu.CheckMenuElement;
import com.yss.method.Login.LoginElement;


/**
 * Element enum of all
 * @author tanglonglong
 *
 */
public enum AllElementEnum{
	
	CheckMenuElement(CheckMenuElement.class),
	LoginElement(LoginElement.class);
	
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