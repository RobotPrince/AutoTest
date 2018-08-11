package com.yss.method;

import java.util.List;

import com.yss.common.BaseInterface;
import com.yss.common.Common;

public class HeSuanJiGouXinXi implements BaseInterface {
	
	@Override
	public boolean add() {
		Common.logInfo("add");
		
//		Common.driver.findElement();
		return false;
	}

	@Override
	public boolean review() {
		return false;
	}

	@Override
	public boolean unreviewed() {
		return false;
	}

	@Override
	public boolean delete() {
		return false;
	}

	@Override
	public List<String> getElementData(String key) {
		// TODO Auto-generated method stub
		return null;
	}
}
