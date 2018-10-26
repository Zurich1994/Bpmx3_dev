package com.hotent.core.exception;

/**
 * 业务数据模版抛出异常。
 * @author ray
 *
 */
public class BusDataException extends RuntimeException {
	

	  /**
	 * 
	 */
	private static final long serialVersionUID = -5424738404333084563L;

	public BusDataException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  public BusDataException(String message) {
	    super(message);
	  }

}
