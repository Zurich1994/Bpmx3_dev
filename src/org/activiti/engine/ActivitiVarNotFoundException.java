package org.activiti.engine;

/**
 * 流程变量不存在异常
 * @author ray
 *
 */
public class ActivitiVarNotFoundException extends RuntimeException {
		
		 private static final long serialVersionUID = 1L;

		  public ActivitiVarNotFoundException(String message, Throwable cause) {
		    super(message, cause);
		  }

		  public ActivitiVarNotFoundException(String message) {
		    super(message);
		  }

	
}
