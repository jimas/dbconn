package com.jimas.dbconn.pojo.rest;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ResultVo<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private int status; //返回结果[200成功]
	private String message; //失败时，返回错误消息
	private T result; //返回结果集
	public ResultVo(){
		this.status = 200; //默认成功
	}
	public ResultVo(int status,String message,T result){
		this.status = status;
		this.message = message;
		this.result = result;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
