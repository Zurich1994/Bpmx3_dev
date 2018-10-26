<#if table.isExternal==0>
<#assign tableName='W_'+table.tableName?upper_case >
<#else>
<#assign tableName=table.tableName>
</#if>
<#assign package=table.variable.package>
<#assign class=table.variable.class>
<#assign fieldList=table.fieldList>
<#assign List=table.mapList>
<#assign type="com.hotent."+system+".model."+package+"." +class>
<#function getJdbcType dataType>
<#assign dbtype=dataType?lower_case>
<#assign rtn>
<#if  dbtype=="number" >
NUMERIC
<#elseif (dbtype?index_of("char")>-1)  >
VARCHAR
<#elseif (dbtype=="date")>
DATE
<#elseif (dbtype?ends_with("clob")) >
CLOB
</#if></#assign>
 <#return rtn?trim>
</#function>


<#-- 模板开始  -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd"> 
<mapper namespace="${type}">
	<resultMap id="${class}" type="${type}">
		<#if table.isExternal==0>      
		<id property="id" column="ID" jdbcType="NUMERIC"/>
		<#if table.isMain==0>                       
		<result property="refId" column="REFID" jdbcType="NUMERIC"/>
		</#if>
		<#list fieldList as field>
		<result property="${field.fieldName}" column="F_${field.fieldName?upper_case}" jdbcType="${getJdbcType(field.fieldType)}"/>
		</#list>
		<#else>
		<#list fieldList as field>
		<#if table.pkField?lower_case==field.fieldName?lower_case>
		<id property="${table.pkField}" column="${field.fieldName?upper_case}" jdbcType="NUMERIC"/>
		</#if>
		</#list>
		<#list fieldList as field>
		<#if table.pkField?lower_case!=field.fieldName?lower_case>
		<result property="${field.fieldName}" column="${field.fieldName?upper_case}" jdbcType="${getJdbcType(field.fieldType)}"/>
		</#if>
		</#list>
		</#if>
	</resultMap>
	
	<#if flowKey?exists>
	<resultMap type="${type}" id="Ext${class}" extends="${class}">
		<id property="taskId" column="TASK_ID" jdbcType="NUMERIC"/>
		<id property="nodeId" column="TASK_DEF_KEY_" jdbcType="VARCHAR"/>
		<id property="nodeName" column="NODE_NAME_" jdbcType="VARCHAR"/>
		<id property="runId" column="FLOW_RUNID" jdbcType="NUMERIC"/>
		<id property="actInstId" column="PROC_INST_ID_" jdbcType="NUMERIC"/>
	</resultMap>
	</#if>

	<sql id="columns">
		<#if table.isExternal==0>
		ID,<#if table.isMain==0>REFID,</#if><#list fieldList as field>F_${field.fieldName?upper_case}<#if field_has_next>,</#if></#list>
		<#else><#--若该表不是本地表的话   执行下边代码  -->
		<#list fieldList as field>${field.fieldName?upper_case}<#if field_has_next>,</#if></#list>
		</#if>
	</sql>
	<sql id="wfColumns">
		<#if table.isExternal==0>
		ID,<#if table.isMain==0>REFID,</#if><#list fieldList as field>F_${field.fieldName?upper_case}<#if field_has_next>,</#if></#list>
		<#else>
		<#list fieldList as field>${field.fieldName?upper_case}<#if field_has_next>,</#if></#list>
		</#if>
	</sql>
	
	<sql id="dynamicWhere">
		<where>
			<#if table.isExternal==0>
			<#list fieldList as field>
			<#if (field.fieldType=="varchar")>
			<if test="@Ognl@isNotEmpty(${field.fieldName})"> AND F_${field.fieldName?upper_case}  LIKE '%<#noparse>${</#noparse>${field.fieldName}}%'  </if>
			<#else>
			<#if (field.fieldType=="date")>
			<if test="@Ognl@isNotEmpty(${field.fieldName})"> AND F_${field.fieldName?upper_case}  =<#noparse>#{</#noparse>${field.fieldName}} </if>
			<if test="@Ognl@isNotEmpty(begin${field.fieldName})"> AND F_${field.fieldName?upper_case}  >=<#noparse>#{</#noparse>begin${field.fieldName},jdbcType=DATE} </if>
			<if test="@Ognl@isNotEmpty(end${field.fieldName})"> AND F_${field.fieldName?upper_case} <![CDATA[ <=<#noparse>#{</#noparse>end${field.fieldName},jdbcType=DATE}]]> </if>
			<#else>
			<if test="@Ognl@isNotEmpty(${field.fieldName})"> AND F_${field.fieldName?upper_case}  =<#noparse>#{</#noparse>${field.fieldName}} </if>
			</#if>
			</#if>
			</#list>
			<#else>
			<#list fieldList as field>
			<#if table.pkField?lower_case!=field.fieldName?lower_case><#--若table.pkField?lower_case!=field.fieldName?lower_case的话  执行下边的代码-->
			<#if (field.fieldType=="varchar")>
			<if test="@Ognl@isNotEmpty(${field.fieldName})"> AND ${field.fieldName?upper_case}  LIKE '%<#noparse>${</#noparse>${field.fieldName}}%'  </if>
			</#if>
			<#if (field.fieldType=="date")>
			<if test="@Ognl@isNotEmpty(${field.fieldName})"> AND ${field.fieldName?upper_case}  =<#noparse>#{</#noparse>${field.fieldName}} </if>
			<if test="@Ognl@isNotEmpty(begin${field.fieldName})"> AND ${field.fieldName?upper_case}  >=<#noparse>#{</#noparse>begin${field.fieldName},jdbcType=DATE} </if>
			<if test="@Ognl@isNotEmpty(end${field.fieldName})"> AND ${field.fieldName?upper_case} <![CDATA[ <=<#noparse>#{</#noparse>end${field.fieldName},jdbcType=DATE}]]> </if>
			</#if>
			<#else>
			<if test="@Ognl@isNotEmpty(${field.fieldName})"> AND ${field.fieldName?upper_case}  =<#noparse>#{</#noparse>${field.fieldName}} </if>
			</#if>
			</#list>
			</#if>
		</where>
	</sql>

	<insert id="add" parameterType="${type}">
		INSERT INTO ${tableName}
		<#if table.isExternal==0>
		(ID,<#if table.isMain==0>REFID,</#if>
		<#list fieldList as field>F_${field.fieldName?upper_case}<#if field_has_next>,</#if></#list>)
		VALUES
		(<#noparse>#{</#noparse>id,jdbcType=NUMERIC},
		<#if table.isMain==0>
		<#noparse>#{</#noparse>refId,jdbcType=NUMERIC},
		</#if>
		<#list fieldList as field><#noparse>#{</#noparse>${field.fieldName},jdbcType=${getJdbcType(field.fieldType)}<#noparse>}</#noparse><#if field_has_next>, </#if></#list>)
		<#else>
		(<#list fieldList as field>${field.fieldName?upper_case}<#if field_has_next>,</#if></#list>)
		VALUES
		(<#list fieldList as field><#noparse>#{</#noparse>${field.fieldName},jdbcType=${getJdbcType(field.fieldType)}<#noparse>}</#noparse><#if field_has_next>, </#if></#list>)
		</#if>
	</insert>
	
	<delete id="delById" parameterType="java.lang.Long">
		DELETE FROM ${tableName} 
		WHERE
		<#if table.isExternal==0>
		ID=<#noparse>#{</#noparse>id}
		<#else>
		${table.pkField?upper_case}=<#noparse>#{</#noparse>${table.pkField}}
		</#if>
	</delete>
	
	<update id="update" parameterType="${type}">
		UPDATE ${tableName} SET
		<#if table.isExternal==0>
		<#if table.isMain==0>
		REFID=<#noparse>#{</#noparse>refId,jdbcType=NUMERIC},
		<#noparse>#{</#noparse>refId,jdbcType=NUMERIC},
		</#if>
		<#list fieldList as field>
		F_${field.fieldName?upper_case}=<#noparse>#{</#noparse>${field.fieldName},jdbcType=${getJdbcType(field.fieldType)}<#noparse>}</#noparse><#if field_has_next>,</#if>
		</#list>
		WHERE
		ID=<#noparse>#{</#noparse>id}
		<#else><#--若该表不是本地表的话   执行下边代码  -->
		<#list fieldList as field>
		<#if table.pkField?upper_case!=field.fieldName?upper_case>
		${field.fieldName?upper_case}=<#noparse>#{</#noparse>${field.fieldName},jdbcType=${getJdbcType(field.fieldType)}<#noparse>}</#noparse><#if field_has_next>,</#if>
		</#if>
		</#list>
		WHERE
		${table.pkField?upper_case}=<#noparse>#{</#noparse>${table.pkField}}
		</#if>
	</update>
	
	<#if table.isMain!=1><#--若该表是主表的话   执行下边代码  -->
	<delete id="delByMainId">
	    DELETE FROM ${tableName}
	    WHERE
	    <#if table.isExternal==0><#--若该表不是本地表的话   执行下边代码  -->
	    REFID=<#noparse>#{</#noparse>refId}
	    <#else><#--若该表是本地表的话   执行下边代码  -->
	    ${table.relation?upper_case}=<#noparse>#{</#noparse>${table.relation?lower_case}}
	    </#if>
	</delete>    
	
	<select id="get${class}List" resultMap="${class}">
	    SELECT <include refid="columns"/>
	    FROM ${tableName} 
	    <#if table.isExternal==0><#--若该表不是本地表的话   执行下边代码  -->
	    WHERE REFID=<#noparse>#{</#noparse>refId}
	    <#else><#--若该表是本地表的话   执行下边代码  -->
	    WHERE ${table.relation?upper_case}=<#noparse>#{</#noparse>${table.relation?lower_case}}
	    </#if>
	</select>
	</#if>
		    
	<select id="getById" parameterType="java.lang.Long" resultMap="${class}">
		SELECT <include refid="columns"/>
		FROM ${tableName}
		WHERE
		<#if table.isExternal==0><#--若该表不是本地表的话   执行下边代码  -->
		ID=<#noparse>#{</#noparse>id}
		<#else><#--若该表是本地表的话   执行下边代码  -->
		${table.pkField?upper_case}=<#noparse>#{</#noparse>${table.pkField}}
		</#if>
	</select>
	
	<select id="getAll" resultMap="${class}">
		SELECT <include refid="columns"/>
		FROM ${tableName}   
		<include refid="dynamicWhere" />
		<if test="@Ognl@isNotEmpty(orderField)">
		order by <#noparse>${orderField}</#noparse> <#noparse>${orderSeq}</#noparse>
		</if>
		<if test="@Ognl@isEmpty(orderField)">
		<#if table.isExternal==0><#--若该表不是本地表的话   执行下边代码  -->
		order by ID  desc
		<#else><#--若该表是本地表的话   执行下边代码  -->
		order by ${table.pkField?upper_case} desc
		</#if>
		</if>
	</select>
	
	
	
	<#if flowKey?exists><#--flowKey存在的话   执行下边代码-->
	<select id="getDraftByUser" resultMap="${class}">
		SELECT <include refid="wfColumns"/>  ,B.runId
		FROM ${tableName} A,BPM_PRO_RUN B
		WHERE A.${table.pkField?upper_case}=B.BUSINESSKEY_NUM AND B.STATUS=4
		and B.CREATORID=<#noparse>#{</#noparse>userId}
		order by B.CREATETIME DESC
	</select>
	
	
	<select id="getMyTodoTask" resultMap="Ext${class}">
		SELECT * FROM (
		SELECT <include refid="wfColumns"/> ,
		C.ID_ TASK_ID,C.TASK_DEF_KEY_,B.RUNID FLOW_RUNID,C.PROC_INST_ID_,
		C.NAME_ NODE_NAME_,C.CREATE_TIME_ TASK_CREATE_TIME_
		FROM ${tableName} A,BPM_PRO_RUN B ,
		ACT_RU_TASK C
		WHERE A.${table.pkField?upper_case}=B.BUSINESSKEY_NUM AND B.ACTINSTID=C.PROC_INST_ID_ AND C.ASSIGNEE_=<#noparse>#{</#noparse>userId}
		
		union
		
		SELECT <include refid="wfColumns"/>,
		C.ID_ TASK_ID,C.TASK_DEF_KEY_,B.RUNID FLOW_RUNID,C.PROC_INST_ID_,
		C.NAME_ NODE_NAME_,C.CREATE_TIME_ TASK_CREATE_TIME_
		FROM ${tableName} A,BPM_PRO_RUN B ,
		ACT_RU_TASK C,
		ACT_RU_IDENTITYLINK D
		WHERE A.${table.pkField?upper_case}=B.BUSINESSKEY_NUM AND B.ACTINSTID=C.PROC_INST_ID_ 
		AND C.id_ = D.TASK_ID_ AND D.USER_ID_ = <#noparse>#{</#noparse>userId}
		AND C.ASSIGNEE_ =0 and C.DESCRIPTION_!='39' 
		
		UNION
		
		SELECT <include refid="wfColumns"/>,
		C.ID_ TASK_ID,C.TASK_DEF_KEY_,B.RUNID FLOW_RUNID,C.PROC_INST_ID_,
		C.NAME_ NODE_NAME_,C.CREATE_TIME_ TASK_CREATE_TIME_
		FROM ${tableName} A,BPM_PRO_RUN B ,
		ACT_RU_TASK C,
		ACT_RU_IDENTITYLINK D
		WHERE A.${table.pkField?upper_case}=B.BUSINESSKEY_NUM AND B.ACTINSTID=C.PROC_INST_ID_ 
		AND C.id_ = D.TASK_ID_ 
		AND C.ASSIGNEE_ =0 and C.DESCRIPTION_!='39' 
		AND D.type_ = 'org' and D.group_id_ in (select uo.orgid from SYS_USER_POS uo  where uo.userid= <#noparse>#{</#noparse>userId} )
		
		UNION
		
		SELECT <include refid="wfColumns"/>,
		C.ID_ TASK_ID,C.TASK_DEF_KEY_,B.RUNID FLOW_RUNID,C.PROC_INST_ID_,
		C.NAME_ NODE_NAME_,C.CREATE_TIME_ TASK_CREATE_TIME_
		FROM ${tableName} A,BPM_PRO_RUN B ,
		ACT_RU_TASK C,
		ACT_RU_IDENTITYLINK D
		WHERE A.${table.pkField?upper_case}=B.BUSINESSKEY_NUM AND B.ACTINSTID=C.PROC_INST_ID_ 
		AND C.id_ = D.TASK_ID_ 
		AND C.ASSIGNEE_ =0 and C.DESCRIPTION_!='39' 
		AND D.type_ = 'role' and D.group_id_ in (select ur.roleid from sys_user_role ur where ur.userid=<#noparse>#{</#noparse>userId} )
		
		union 
		
		SELECT <include refid="wfColumns"/>,
		C.ID_ TASK_ID,C.TASK_DEF_KEY_,B.RUNID FLOW_RUNID,C.PROC_INST_ID_,
		C.NAME_ NODE_NAME_,C.CREATE_TIME_ TASK_CREATE_TIME_
		FROM ${tableName} A,BPM_PRO_RUN B ,
		ACT_RU_TASK C,
		ACT_RU_IDENTITYLINK D
		WHERE A.${table.pkField?upper_case}=B.BUSINESSKEY_NUM AND B.ACTINSTID=C.PROC_INST_ID_ 
		AND C.id_ = D.TASK_ID_ 
		AND C.ASSIGNEE_ =0 and C.DESCRIPTION_!='39' 
		AND D.type_ = 'pos' and D.group_id_ in (select up.posid from sys_user_pos up where up.userid= <#noparse>#{</#noparse>userId} )
		
		) tmp
		
		<where>
			<if test="@Ognl@isNotEmpty(subject)"> AND upper(tmp.subject) LIKE <#noparse>#{</#noparse>subject}   </if>
			<if test="@Ognl@isNotEmpty(processName)"> AND upper(tmp.processName) LIKE <#noparse>#{</#noparse>processName} </if>
			<if test="@Ognl@isNotEmpty(creatorId)">AND tmp.creatorId =<#noparse>#{</#noparse>creatorId} </if>
			<if test="@Ognl@isNotEmpty(beginCreateTime)">and tmp.TASK_CREATE_TIME_>=<#noparse>#{</#noparse>beginCreateTime,jdbcType=TIMESTAMP}</if>
			<if test="@Ognl@isNotEmpty(endCreateTime)">and tmp.TASK_CREATE_TIME_ &lt;=<#noparse>#{</#noparse>endCreateTime,jdbcType=TIMESTAMP}</if>
			<if test="@Ognl@isNotEmpty(type)">
				AND tmp.description_ = <#noparse>#{</#noparse>type}
			</if>	
			
		</where>
		<if test="@Ognl@isEmpty(orderField)">
			order by tmp.TASK_CREATE_TIME_ desc
		</if>
		<if test="@Ognl@isNotEmpty(orderField)">
			order by <#noparse>${</#noparse>orderField} <#noparse>${</#noparse>orderSeq}
		</if>
	</select>
	<select id="getEndByUser" resultMap="${class}">
		SELECT <include refid="wfColumns"/>  ,B.runId
		FROM ${tableName} A,BPM_PRO_RUN_HIS B
		WHERE A.${table.pkField?upper_case}=B.BUSINESSKEY_NUM 
		and B.CREATORID=<#noparse>#{</#noparse>userId} and B.STATUS=2
		order by B.ENDTIME DESC
	</select>
	</#if>
	
	<#list List as db_Func>
	<#if "${db_Func.db_Func_Type}"=="1">   <#--添加-->
	<insert id="${db_Func.db_Func_Name}" parameterType="${type}">
	INSERT INTO ${tableName}
		<#if table.isExternal==0>
		(ID,<#if table.isMain==0>REFID,</#if>
		<#list fieldList as field>F_${field.fieldName?upper_case}<#if field_has_next>,</#if></#list>)
		VALUES
		(<#noparse>#{</#noparse>id,jdbcType=NUMERIC},
		<#if table.isMain==0>
		<#noparse>#{</#noparse>refId,jdbcType=NUMERIC},
		</#if>
		<#list fieldList as field><#noparse>#{</#noparse>${field.fieldName},jdbcType=${getJdbcType(field.fieldType)}<#noparse>}</#noparse><#if field_has_next>, </#if></#list>)
		<#else>
		(<#list fieldList as field>${field.fieldName?upper_case}<#if field_has_next>,</#if></#list>)
		VALUES
		(<#list fieldList as field><#noparse>#{</#noparse>${field.fieldName},jdbcType=${getJdbcType(field.fieldType)}<#noparse>}</#noparse><#if field_has_next>, </#if></#list>)
		</#if>
	</insert>
   <#elseif "${db_Func.db_Func_Type}"=="2">   <#--删除-->
   <delete id="${db_Func.db_Func_Name}" parameterType="java.lang.${db_Func.db_Func_ParameterType}">
   DELETE FROM ${tableName} 
   WHERE ${db_Func.fieldName?upper_case}=<#noparse>#{</#noparse>${db_Func.comment?lower_case}}
   </delete>
    <#elseif "${db_Func.db_Func_Type}"=="3">  <#--修改-->
	<update id="${db_Func.db_Func_Name}" parameterType="${type}">
    UPDATE ${tableName} SET
    <#if table.isExternal==0><#--若该表是本地表的话   执行下边代码  -->
	    <#if table.isMain==0><#--isMain==0不是主表-->
	    REFID=<#noparse>#{</#noparse>refId,jdbcType=NUMERIC},
		<#noparse>#{</#noparse>refId,jdbcType=NUMERIC},
		</#if>
    <#list fieldList as field>
	F_${field.fieldName?upper_case}=<#noparse>#{</#noparse>${field.fieldName},jdbcType=${getJdbcType(field.fieldType)}<#noparse>}</#noparse><#if field_has_next>,</#if>
	</#list>
	WHERE
	ID=<#noparse>#{</#noparse>id}
	<#else><#--若该表不是本地表的话   执行下边代码  -->
	<#list fieldList as field>
		<#if table.pkField?upper_case!=field.fieldName?upper_case>
		${field.fieldName?upper_case}=<#noparse>#{</#noparse>${field.fieldName},jdbcType=${getJdbcType(field.fieldType)}<#noparse>}</#noparse><#if field_has_next>,</#if>
		</#if>
	</#list>
	WHERE
	${table.pkField?upper_case}=<#noparse>#{</#noparse>${table.pkField}}
	</#if>
    </update>
   <#else><#--查询-->
   <select id="${db_Func.db_Func_Name}" parameterType="java.lang.${db_Func.db_Func_ParameterType}" resultMap="${class}">
   SELECT <include refid="columns"/>
   FROM ${tableName} 
   WHERE ${db_Func.fieldName?upper_case}=<#noparse>#{</#noparse>${db_Func.comment?lower_case}}
   </select>  
   </#if>
  </#list>
  
	
</mapper>
