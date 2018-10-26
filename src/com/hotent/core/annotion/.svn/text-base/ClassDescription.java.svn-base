package com.hotent.core.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解， 功能：设置一个POJO类对应的表名，程序可以读取此注解。<br>
 * 使用方法：<br>
 * <pre>
 * 	@ClassDescription(tableName="SYS_ROLE")
 * 	public class Role
 *  {
 *  }
 *  Role role=new Role();
 *  Class cls= obj.getClass();
 *  ClassDescription clsDesc=(ClassDescription)cls.getAnnotation(ClassDescription.class);
 *  //取得表名
 *  clsDesc.tableName()
 * </pre>
 * @author hotent
 *
 */
@Target({ElementType.TYPE}) 
@Retention(RetentionPolicy.RUNTIME) 
public @interface ClassDescription {
	public String tableName();
	
}
