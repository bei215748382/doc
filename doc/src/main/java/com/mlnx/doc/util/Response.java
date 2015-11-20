package com.mlnx.doc.util;

import com.mlnx.doc.util.EnumCollection.ResponseCode;

public class Response {
	private String responseCode;
	private String msg;
	
	public Response(){
		
	}
	
	public Response(ResponseCode responseCode) {
		this.responseCode = responseCode.getCode();
		this.msg = responseCode.getMsg();
	}
	
	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Response [responseCode=" + responseCode + ", msg=" + msg + "]";
	}

}
