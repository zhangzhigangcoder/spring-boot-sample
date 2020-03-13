package org.spring.boot.exception;

public class ErrorInfo<T> {
	
	public static final Integer OK = 0;
	public static final Integer ERROR = 100;
	
	private Integer code;
	private String message;
	private String url;
	private T date;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public T getDate() {
		return date;
	}
	public void setDate(T date) {
		this.date = date;
	}
	
	
}
