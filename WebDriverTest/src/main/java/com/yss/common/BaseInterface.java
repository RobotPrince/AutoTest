package com.yss.common;


/**
 * 关于页面的新增，删除，审核，反审核，获取Excel中元素值的接口
 * @author tanglonglong
 *
 */
public interface BaseInterface {
	/**
	 * 预先操作
	 */
	boolean before();
	/**
	 * 审核
	 * @return
	 */
	@SuppressWarnings("unchecked")
	boolean review();
	/**
	 * 审核数个
	 * @return
	 */
	@SuppressWarnings("rawtypes")
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
	/**
	 * 收尾操作
	 */
	Boolean after();
}
