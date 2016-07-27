package com.class8.eduAdmin.exception;
/**
 * Business异常，业务逻辑层中当不满足某个条件时，抛出该异常，该异常相当于方法的第二个返回值
 * 
 * 继承自Exception.
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = -6019683244418916430L;
	
	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

}
