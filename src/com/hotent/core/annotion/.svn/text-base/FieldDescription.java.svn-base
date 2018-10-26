package com.hotent.core.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能：设置pojo对象属性的列名，是否为更新字段和主键。<br>
 * 示例如下：<br>
 * <pre>
 * 设置注解：
 * @FieldDescription(pk=true,columnName="MEMO",canUpd=true)
 * 读取注解：
 * Role obj ;
 * Class cls= obj.getClass();
 * Field[] fields=  cls.getDeclaredFields();
 * for(int i=0;i<fields.length;i++)
 * {
 * 		Field fld=fields[i];
 *		FieldDescription fldDesc=fld.getAnnotation(FieldDescription.class);
 * }
 * </pre>
 * @author hotent
 */
@Target({ElementType.FIELD}) 
@Retention(RetentionPolicy.RUNTIME) 
public @interface FieldDescription {
	/**
	 * 是否为主键
	 * @return
	 */
	public boolean pk() default false;
	/**
	 * 对应数据库列名
	 * @return
	 */
	public String columnName() default "";
	/**
	 * 该字段是否允许更新
	 * @return
	 */
	public boolean canUpd() default true;
}
