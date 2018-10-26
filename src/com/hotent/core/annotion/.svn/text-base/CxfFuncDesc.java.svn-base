package com.hotent.core.annotion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于描述调用cxf的方法
 * @author cjj
 *
 */
@Target(ElementType.METHOD)   
@Retention(RetentionPolicy.RUNTIME)   
@Documented  
@Inherited 
public @interface CxfFuncDesc {
	/**
	 * 调用方法名称
	 * @return
	 */
	public String callName() default "";
	/**
	 * 调用描述
	 * @return
	 */
	public String callDesc() default "";
}
