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
   <td colspan="2" class="formHead">机房信息表</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">序号:</td>
   <td class="formInput" style="width:80%;"><input name="id" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" value="${serverroom.id}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">机房编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="room_ID" lablename="机房编号" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${serverroom.room_ID}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">机房名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="room_name" lablename="机房名称" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" value="${serverroom.room_name}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">地址:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="address" lablename="地址" class="inputText" validate="{maxlength:200}" isflag="tableflag" value="${serverroom.address}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">备注:</td>
   <td class="formInput" style="width:80%;"><textarea name="remark" validate="{empty:false}">${serverroom.remark}</textarea></td>
  </tr>
 </tbody>
</table>
