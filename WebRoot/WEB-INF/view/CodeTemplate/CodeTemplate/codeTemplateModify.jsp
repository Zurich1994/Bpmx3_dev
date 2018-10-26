<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/codegen/include/customForm.jsp" %>
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
   <td colspan="2" class="formHead">流程模板</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模版名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:templatename" lablename="模版名称" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${codeTemplate.templatename}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">别名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:bm" lablename="别名" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${codeTemplate.bm}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模板类别:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="m:code_template:templatetype" lablename="模板类别" controltype="select" validate="{empty:false,required:true}" value="${codeTemplate.templatetype}"><option value="1">流程图</option><option value="2">操作图</option><option value="3">事务图</option><option value="4">word文档</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程类别:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="m:code_template:subjecttype" lablename="流程类别" controltype="select" validate="{empty:false}" value="${codeTemplate.subjecttype}"><option value="1">行政执法</option><option value="2">政务公开</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:subjectname" lablename="流程名称" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${codeTemplate.subjectname}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">备注:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:bz" lablename="备注" class="inputText" validate="{maxlength:500}" isflag="tableflag" type="text" value="${codeTemplate.bz}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">是否标记为模板:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="m:code_template:issign" lablename="是否标记为模板" controltype="select" validate="{empty:false}" value="${codeTemplate.issign}"><option value="1">是</option><option value="2">fou</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">创建时间:</td>
   <td class="formInput" style="width:80%;"><input name="m:code_template:createtime" class="Wdate" displaydate="1" datefmt="yyyy-MM-dd" validate="{empty:false}" type="text" value="${codeTemplate.createtime}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模板生成的文件路径:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:mbscdwjlj" lablename="模板生成的文件路径" class="inputText" validate="{maxlength:500}" isflag="tableflag" type="text" value="${codeTemplate.mbscdwjlj}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模板文件生成的文件名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:mbwjscdwjmc" lablename="模板文件生成的文件名称" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${codeTemplate.mbwjscdwjmc}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">模板XML:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:code_template:templateXML" lablename="模板XML" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${codeTemplate.templateXML}" /></span></td>
  </tr>
 </tbody>
</table>
