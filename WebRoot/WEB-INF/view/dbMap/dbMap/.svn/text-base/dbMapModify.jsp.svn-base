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
   <td colspan="2" class="formHead">数据库中map.xml信息表</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">map.xml文件名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="map_name" lablename="map.xml文件名" class="inputText" validate="{maxlength:100}" isflag="tableflag" value="${dbMap.map_name}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">绑定表名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="table_name" lablename="绑定表名" class="inputText" validate="{maxlength:100}" isflag="tableflag" value="${dbMap.table_name}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">xml内容:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <textarea style="display:none" controltype="attachment" name="xml_text" lablename="xml内容">${dbMap.xml_text}</textarea>
     <a href="javascript:;" field="xml_text" class="link selectFile" atype="select" onclick="{AttachMent.directUpLoadFile(this);}" validate="{}" name="xml_text">选择</a>
    </div></td>
  </tr>
 </tbody>
</table>
