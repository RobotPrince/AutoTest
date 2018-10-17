package com.yss.common;
import java.util.LinkedHashMap;

/**
 * 用于返回方法执行的结果
 * @author tanglonglong
 *
 */
public class MyResponse extends LinkedHashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	public static final int SUCCESS = 1;
	public static final int FAILED = 0;
	public static final String STATUS = "status";
	public static final String MESSAGE = "msg";
	public static final String CODE = "code";

	public MyResponse() {
		super();
	}

	public MyResponse failed(String msg) {
		put(STATUS, FAILED);
		put(MESSAGE, msg);
		return this;
	}
	
	public MyResponse failed(String msg, String code) {
		put(STATUS, FAILED);
		put(MESSAGE, msg);
		put(CODE, code);
		return this;
	}
	
	public MyResponse success() {
		put(STATUS, SUCCESS);
		return this;
	}

	public MyResponse success(String msg) {
		put(STATUS, SUCCESS);
		put(MESSAGE, msg);
		return this;
	}

	public MyResponse success(String msg , String code) {
		put(STATUS, SUCCESS);
		put(MESSAGE, msg );
		put(CODE, code);
		return this;
	}

	public MyResponse successWithData(String key, Object value) {
		put(STATUS, SUCCESS);
		put(key, value);
		return this;
	}

	public MyResponse setMessage(String msg) {
		put(MESSAGE, msg);
		return this;
	}

	public String getMessage() {
		return (String) get(MESSAGE);
	}

	public boolean isfailed() {
		return get(STATUS) != null && (Integer) get(STATUS) == FAILED;
	}
	

	public boolean isSuccess() {
		return get(STATUS) != null && (Integer) get(STATUS) == SUCCESS;
	}


}