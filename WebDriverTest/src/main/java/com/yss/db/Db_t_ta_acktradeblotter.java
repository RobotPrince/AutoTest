package com.yss.db;

import java.io.Serializable;

public class Db_t_ta_acktradeblotter implements Serializable {

	private String fackno;
	
	public String getFackno(){
		return fackno;
	}
	public void setFackno(String fackno){
		this.fackno = fackno;
	}
}
