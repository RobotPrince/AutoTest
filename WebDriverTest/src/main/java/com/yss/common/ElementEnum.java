package com.yss.common;

import com.yss.method.CheckMenu.CheckMenuElement;



/**
 * Element enum of all
 * @author tanglonglong
 *
 */
enum AllElementEnum{
	
	CHECKMENUELEMENT(CheckMenuElement.class);
	
	private ElementEnum[] elementEnum;
	private AllElementEnum( Class<? extends ElementEnum> e){
		elementEnum=e.getEnumConstants();
	}
//	public ElementEnum[] getValue(){
//		return elementEnum;
//	}
	public String[] valueToString(){
		
		String[] str = new String[elementEnum.length];
		
		for(int i = 0;i < str.length; i++){
			str[i] = elementEnum[i].toString();
		}
		return str;
	}
}

public interface ElementEnum {
		
}
