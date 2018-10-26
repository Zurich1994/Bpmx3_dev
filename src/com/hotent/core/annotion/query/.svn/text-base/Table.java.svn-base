package com.hotent.core.annotion.query;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表注解。
 * 
 * @author win
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)   
@Documented  
@Inherited
public @interface Table {
	/**
	 * 表名。如bpm_form_def
	 * @return
	 */
	public String name() default "";
	/**
	 * 实体的参数名，第一个字母小写。
	 * @return
	 */
	public String var() default "";
	
	/**
	 * List.jsp页面的DisplayTag的id
	 */
	public String displayTagId() default "";
	
	/**
	 * 该表的主键名称，默认是ID
	 * @return
	 */
	public String pk() default "ID";
	/**
	 * 表注释。
	 * @return
	 */
	public String comment() default "";	
	
	/**
	 * 默认是否主表。
	 * @return
	 */
	public boolean isPrimary() default true;
	
	/**
	 * 如果为从表,那么指定与主表关联的表字段。如REFID
	 * @return
	 */
	public String relation() default "";
	
	/**
	 * 如果为从表，则指定主表。
	 * @return
	 */
	public String primaryTable() default "";
}
