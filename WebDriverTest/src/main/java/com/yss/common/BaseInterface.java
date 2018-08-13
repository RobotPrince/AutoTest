package com.yss.common;

import java.util.List;

/**
 * 关于页面的新增，删除，审核，反审核，获取Excel中元素值的接口
 * @author tanglonglong
 *
 */
public interface BaseInterface {

	/**
	 * 审核
	 * @return
	 */
	boolean review();
	/**
	 * 审核
	 * @return
	 */
	boolean reviewAFew();
	/**
	 * 反审核
	 * @return
	 */
	boolean unreviewed();
	/**
	 * 新增一个
	 * @return
	 */
	boolean addOne();
	/**
	 * 新增数个
	 * @return
	 */
	boolean addAFew();
	/**
	 * 删除
	 * @return
	 */
	boolean delete();
}
