<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/js/hotent/scriptMgr.js"></script>
<script type="text/javascript">
	function afterOnload(){
		var afterLoadJs=[
		     			'${ctx}/js/hotent/formdata.js',
		     			'${ctx}/js/hotent/subform.js'
		 ];
		ScriptMgr.load({
			scripts : afterLoadJs
		});
	}
</script>
<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="8" class="formHead">数据库方法参数表</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">表名:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.table_name}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">方法类型:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><c:if test='${dbFunc.func_type==1}'>添加</c:if><c:if test='${dbFunc.func_type==2}'>删除</c:if><c:if test='${dbFunc.func_type==3}'>修改</c:if><c:if test='${dbFunc.func_type==4}'>查询</c:if></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">方法名称:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.func_name}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">方法别名:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.func_alias}</span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">备注:</td>
   <td style="width:15%;" class="formInput"><textarea name="remarks" validate="{empty:false}" readonly="readonly">${dbFunc.remarks}</textarea></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">传参类型:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.parameterType}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">返回值类型:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.resultType}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">返回字段:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.resultField}</span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">条件字段:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.conditionField}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">排序字段:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.sortField}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">设置字段:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.settingField}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">显示字段:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.displayField}</span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">数据源名称:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.dsName}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">数据源别名:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.ds_Alias}</span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">是否为表 :</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><c:if test='${dbFunc.isTable==0}'>视图</c:if><c:if test='${dbFunc.isTable==1}'>数据库表</c:if></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">是否正在使用:</td>
   <td style="width:15%;" class="formInput">${dbFunc.is_Using}</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">objname:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${dbFunc.objname}</span></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
  </tr>
 </tbody>
</table>
