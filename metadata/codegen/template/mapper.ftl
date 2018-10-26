<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign system=vars.system>
<#assign type="${vars.packagePre}."+system+".model."+package+"." +class>
<#assign tableName=model.tableName>
<#assign system=vars.system>
<#assign foreignKey=model.foreignKey>
<#assign sub=model.sub>
<#assign colList=model.columnList>
<#assign commonList=model.commonList>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.getPkVar(model) >
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd"> 
<mapper namespace="${type}">
	<resultMap id="${class}" type="${type}">
		<#list colList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		<#if (col.isPK) >
		<id property="${colName}" column="${col.columnName}" jdbcType="${func.getJdbcType(col.colDbType)}"/>
		<#else>
		<result property="${colName}" column="${col.columnName}" jdbcType="${func.getJdbcType(col.colDbType)}"/>
		</#if>
		</#list>
	</resultMap>
	
	<#if func.supportFlow(model)>
	<resultMap type="${type}" id="Ext${class}" extends="${class}">
		<id property="taskId" column="TASK_ID" jdbcType="NUMERIC"/>
		<id property="nodeId" column="TASK_DEF_KEY_" jdbcType="VARCHAR"/>
		<id property="nodeName" column="NODE_NAME_" jdbcType="VARCHAR"/>
		<id property="runId" column="FLOW_RUNID" jdbcType="NUMERIC"/>
		<id property="actInstId" column="PROC_INST_ID_" jdbcType="NUMERIC"/>
	</resultMap>
	</#if>
	

	<sql id="columns">
		<#list colList as col>${col.columnName}<#if col_has_next>,</#if></#list>
	</sql>
	
	<#if func.supportFlow(model)>
	<sql id="wfColumns">
		<#list colList as col>${col.columnName}<#if col_has_next>,</#if></#list>
	</sql>
	</#if>
	
	<sql id="dynamicWhere">
		<where>
			<#list colList as col>
			<#assign colName=func.convertUnderLine(col.columnName)>
			<#if (col.colType=="String")>
			<if test="@Ognl@isNotEmpty(${colName})"> AND ${col.columnName}  LIKE <#noparse>#{</#noparse>${colName}}  </if>
			<#else>
			<#if (col.colType=="java.util.Date")>
			<if test="@Ognl@isNotEmpty(${colName})"> AND ${col.columnName}  =<#noparse>#{</#noparse>${colName}} </if>
			<if test="@Ognl@isNotEmpty(begin${colName})"> AND ${col.columnName}  >=<#noparse>#{</#noparse>begin${colName},jdbcType=DATE} </if>
			<if test="@Ognl@isNotEmpty(end${colName})"> AND ${col.columnName} <![CDATA[ <=<#noparse>#{</#noparse>end${colName},jdbcType=DATE}]]> </if>
			<#else>
			<if test="@Ognl@isNotEmpty(${colName})"> AND ${col.columnName}  =<#noparse>#{</#noparse>${colName}} </if>
			</#if>
			</#if>
			</#list>
		</where>
	</sql>

	<insert id="add" parameterType="${type}">
		INSERT INTO ${tableName}
		(<#list colList as col>${col.columnName}<#if col_has_next>,</#if></#list>)
		VALUES
		(<#list colList as col><#assign colName=func.convertUnderLine(col.columnName)><#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>, </#if></#list>)
	</insert>
	
	<delete id="delById" parameterType="java.lang.Long">
		DELETE FROM ${tableName} 
		WHERE
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
	</delete>
	
	<update id="update" parameterType="${type}">
		UPDATE ${tableName} SET
		<#list commonList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		${col.columnName}=<#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>,</#if>
		</#list>
		WHERE
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
	</update>
	<#--子表部分-->
	<#if sub?exists && sub>
	<#assign foreignKeyVar=func.convertUnderLine(foreignKey)>
	<delete id="delByMainId">
	    DELETE FROM ${tableName}
	    WHERE
	    ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
	</delete>    
	
	<select id="get${class}List" resultMap="${class}">
	    SELECT <include refid="columns"/>
	    FROM ${tableName} 
	    WHERE ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
	</select>
	</#if>
		    
	<select id="getById" parameterType="java.lang.Long" resultMap="${class}">
		SELECT <include refid="columns"/>
		FROM ${tableName}
		WHERE
		${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
	</select>
	
	<select id="getAll" resultMap="${class}">
		SELECT <include refid="columns"/>
		FROM ${tableName}   
		<include refid="dynamicWhere" />
		<if test="@Ognl@isNotEmpty(orderField)">
		order by <#noparse>${orderField}</#noparse> <#noparse>${orderSeq}</#noparse>
		</if>
		<if test="@Ognl@isEmpty(orderField)">
		order by ${pk}  desc
		</if>
	</select>
	
	<#if func.supportFlow(model)>
	<select id="getDraftByUser" resultMap="${class}">
		SELECT <include refid="wfColumns"/>  ,B.runId
		FROM ${tableName} A,BPM_PRO_RUN B
		WHERE A.${pk}=B.BUSINESSKEY_NUM AND B.STATUS=4
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
		WHERE A.${pk}=B.BUSINESSKEY_NUM AND B.ACTINSTID=C.PROC_INST_ID_ AND C.ASSIGNEE_=<#noparse>#{</#noparse>userId}
		
		union
		
		SELECT <include refid="wfColumns"/>,
		C.ID_ TASK_ID,C.TASK_DEF_KEY_,B.RUNID FLOW_RUNID,C.PROC_INST_ID_,
		C.NAME_ NODE_NAME_,C.CREATE_TIME_ TASK_CREATE_TIME_
		FROM ${tableName} A,BPM_PRO_RUN B ,
		ACT_RU_TASK C,
		ACT_RU_IDENTITYLINK D
		WHERE A.${pk}=B.BUSINESSKEY_NUM AND B.ACTINSTID=C.PROC_INST_ID_ 
		AND C.id_ = D.TASK_ID_ AND D.USER_ID_ = <#noparse>#{</#noparse>userId}
		AND C.ASSIGNEE_ =0 and C.DESCRIPTION_!='39' 
		
		UNION
		
		SELECT <include refid="wfColumns"/>,
		C.ID_ TASK_ID,C.TASK_DEF_KEY_,B.RUNID FLOW_RUNID,C.PROC_INST_ID_,
		C.NAME_ NODE_NAME_,C.CREATE_TIME_ TASK_CREATE_TIME_
		FROM ${tableName} A,BPM_PRO_RUN B ,
		ACT_RU_TASK C,
		ACT_RU_IDENTITYLINK D
		WHERE A.${pk}=B.BUSINESSKEY_NUM AND B.ACTINSTID=C.PROC_INST_ID_ 
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
		WHERE A.${pk}=B.BUSINESSKEY_NUM AND B.ACTINSTID=C.PROC_INST_ID_ 
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
		WHERE A.${pk}=B.BUSINESSKEY_NUM AND B.ACTINSTID=C.PROC_INST_ID_ 
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
		WHERE A.${pk}=B.BUSINESSKEY_NUM 
		and B.CREATORID=<#noparse>#{</#noparse>userId} and B.STATUS=2
		order by B.ENDTIME DESC
	</select>
	
	</#if>
	
</mapper>
