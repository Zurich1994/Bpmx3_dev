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
<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">projectInfo</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">项目名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="projectName" lablename="项目名称" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" value="${projectinfo.projectName}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">description:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="description" lablename="description" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${projectinfo.description}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">开始时间:</td>
   <td class="formInput" style="width:80%;"><input name="stime" type="text" class="Wdate" displaydate="0" validate="{empty:false,required:true}" value="<fmt:formatDate value='${projectinfo.stime}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">结束时间:</td>
   <td class="formInput" style="width:80%;"><input name="etime" type="text" class="Wdate" displaydate="0" validate="{empty:false,required:true}" value="<fmt:formatDate value='${projectinfo.etime}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">实际开始时间:</td>
   <td class="formInput" style="width:80%;"><input name="astime" type="text" class="Wdate" displaydate="0" validate="{empty:false}" value="<fmt:formatDate value='${projectinfo.astime}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">实际结束时间:</td>
   <td class="formInput" style="width:80%;"><input name="aetime" type="text" class="Wdate" displaydate="0" validate="{empty:false}" value="<fmt:formatDate value='${projectinfo.aetime}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">删除开关:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="deleteFlag" lablename="删除开关" class="inputText" validate="{maxlength:1}" isflag="tableflag" value="${projectinfo.deleteFlag}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">项目报文:</td>
   <td class="formInput" style="width:80%;"><textarea name="projectXml" validate="{empty:false}">${projectinfo.projectXml}</textarea></td>
  </tr>
 </tbody>
</table>
