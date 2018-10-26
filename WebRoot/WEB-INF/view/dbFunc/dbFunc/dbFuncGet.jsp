
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>数据库方法参数表明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">数据库方法参数表详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="getByTableName.ht?tableName=${dbFunc.table_name}"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<table class="formTable" border="1" cellpadding="2" cellspacing="0">
	<tr>
   <td colspan="8" class="formHead">数据库方法参数表</td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">表名:</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.table_name}</span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">方法类型:</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><c:if test='${dbFunc.func_type==1}'>添加</c:if><c:if test='${dbFunc.func_type==2}'>删除</c:if><c:if test='${dbFunc.func_type==3}'>修改</c:if><c:if test='${dbFunc.func_type==4}'>查询</c:if></span></td>
   </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">方法名称:</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.func_name}</span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">方法别名:</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.func_alias}</span></td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">备注:</td>
   <td style="width:25%;" class="formInput"><textarea name="remarks" validate="{empty:false}" readonly="readonly">${dbFunc.remarks}</textarea></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">传参类型:</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.parameterType}</span></td>
   </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">返回值类型:</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.resultType}</span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">objname:</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.objname}</span></td>
   
  </tr>
 
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">数据源名称:</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.dsName}</span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">数据源别名:</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.ds_Alias}</span></td>
   </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">是否为表 :</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><c:if test='${dbFunc.isTable==0}'>视图</c:if><c:if test='${dbFunc.isTable==1}'>数据库表</c:if></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">是否正在使用:</td>
   <td style="width:45%;" class="formInput">${dbFunc.is_Using}</td>
  </tr>
	
 <tbody>

  
  <c:if test="${dbFunc.func_type==1}">
	
	  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">设置字段:</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.settingField}</span></td>
  
  </tr>
	
	
	
	</c:if>
	
	<c:if test="${dbFunc.func_type==2}">
	
	
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">设置字段:</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.settingField}</span></td>
    <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">条件字段:</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.conditionField}</span></td>
  </tr>
	
	
	
	
	</c:if>
	<c:if test="${dbFunc.func_type==3}">	
  
   

     <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">设置字段:</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.settingField}</span></td>
    <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">条件字段:</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.conditionField}</span></td>
  </tr>
 
  	</c:if>
	<c:if test="${dbFunc.func_type==4}">
	
 
     <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">条件字段:</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.conditionField}</span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">排序字段:</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.sortField}</span></td>
   </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">返回字段:</td>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.resultField}</span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">显示字段:</td>
   <td style="width:45%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.displayField}</span></td>
  </tr>
	
	
	
	
	
	</c:if>
 </tbody>
</table>
</body>
</html>

