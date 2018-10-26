package com.hotent.core.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 发起审批流程注解。
 * <pre> 
 * 构建组：x5-bpmx-api
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-6-5-上午10:39:48
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
@Target({ElementType.METHOD}) 
@Retention(RetentionPolicy.RUNTIME) 
public @interface WorkFlow {
	/**
	 * 流程定义key。
	 * @return
	 */
	public String flowKey() default ""; 
	/**
	 * 业务主表名称。
	 * @return
	 */
	public String tableName() default "";
}
