package com.yss.common;

import java.util.List;

/**
 * 关于页面的新增，删除，审核，反审核，获取Excel中元素值的接口
 * @author tanglonglong
 *
 */
public interface BaseInterface {

	boolean review();
	boolean unreviewed();
	boolean add();
	boolean delete();
	List<String> getElementData(String key);
}
