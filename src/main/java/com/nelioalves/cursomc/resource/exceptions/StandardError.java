package com.nelioalves.cursomc.resource.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer Status;
	private String msg;
	private Long timeStamp;
	
	
	public StandardError(int i, String string, long l) {
		
	}


	public Integer getStatus() {
		return Status;
	}


	public void setStatus(Integer status) {
		Status = status;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Long getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
