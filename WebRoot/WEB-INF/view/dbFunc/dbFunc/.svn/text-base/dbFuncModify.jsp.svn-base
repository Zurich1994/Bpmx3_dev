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
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="table_name" lablename="表名" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${dbFunc.table_name}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">方法类型:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="func_type" lablename="方法类型" controltype="select" validate="{empty:false}"><option value="1" <c:if test='${dbFunc.func_type=="1"}'>selected='selected'</c:if>>添加</option><option value="2" <c:if test='${dbFunc.func_type=="2"}'>selected='selected'</c:if>>删除</option><option value="3" <c:if test='${dbFunc.func_type=="3"}'>selected='selected'</c:if>>修改</option><option value="4" <c:if test='${dbFunc.func_type=="4"}'>selected='selected'</c:if>>查询</option></select></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">方法名称:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="func_name" lablename="方法名称" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${dbFunc.func_name}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">方法别名:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="func_alias" lablename="方法别名" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${dbFunc.func_alias}" /></span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">备注:</td>
   <td style="width:15%;" class="formInput"><textarea name="remarks" validate="{empty:false}">${dbFunc.remarks}</textarea></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">传参类型:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="parameterType" lablename="传参类型" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${dbFunc.parameterType}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">返回值类型:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="resultType" lablename="返回值类型" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${dbFunc.resultType}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">返回字段:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="resultField" lablename="返回字段" class="inputText" validate="{maxlength:500}" isflag="tableflag" type="text" value="${dbFunc.resultField}" /></span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">条件字段:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="conditionField" lablename="条件字段" class="inputText" validate="{maxlength:500}" isflag="tableflag" type="text" value="${dbFunc.conditionField}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">排序字段:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="sortField" lablename="排序字段" class="inputText" validate="{maxlength:500}" isflag="tableflag" type="text" value="${dbFunc.sortField}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">设置字段:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="settingField" lablename="设置字段" class="inputText" validate="{maxlength:500}" isflag="tableflag" type="text" value="${dbFunc.settingField}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">显示字段:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="displayField" lablename="显示字段" class="inputText" validate="{maxlength:500}" isflag="tableflag" type="text" value="${dbFunc.displayField}" /></span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">数据源名称:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="dsName" lablename="数据源名称" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${dbFunc.dsName}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">数据源别名:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ds_Alias" lablename="数据源别名" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${dbFunc.ds_Alias}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">是否为表 :</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="isTable" lablename="是否为表 " controltype="select" validate="{empty:false}"><option value="0" <c:if test='${dbFunc.isTable=="0"}'>selected='selected'</c:if>>视图</option><option value="1" <c:if test='${dbFunc.isTable=="1"}'>selected='selected'</c:if>>数据库表</option></select></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">是否正在使用:</td>
   <td style="width:15%;" class="formInput"><input name="is_Using" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:5,maxDecimalLen:0}" type="text" value="${dbFunc.is_Using}" /></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">objname:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="objname" lablename="objname" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${dbFunc.objname}" /></span></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
  </tr>
 </tbody>
</table>
