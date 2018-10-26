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
   <td colspan="2" class="formHead">文件信息</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">文件ID:</td>
   <td class="formInput" style="width:80%;"><input name="fileid" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:10,maxDecimalLen:0,required:true}" value="${fileList.fileid}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">产品ID:</td>
   <td class="formInput" style="width:80%;"><input name="productid" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:10,maxDecimalLen:0,required:true}" value="${fileList.productid}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">操作系统:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="os" lablename="操作系统" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" value="${fileList.os}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">下载目录:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="filecatalog" lablename="下载目录" class="inputText" validate="{maxlength:25,required:true}" isflag="tableflag" value="${fileList.filecatalog}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">语言:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="language" lablename="语言" class="inputText" validate="{maxlength:25,required:true}" isflag="tableflag" value="${fileList.language}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">文件名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="filename" lablename="文件名" class="inputText" validate="{maxlength:255,required:true}" isflag="tableflag" value="${fileList.filename}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">文件日期:</td>
   <td class="formInput" style="width:80%;"><input name="filedate" type="text" class="Wdate" displaydate="0" validate="{empty:false,required:true}" value="<fmt:formatDate value='${fileList.filedate}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">文件大小:</td>
   <td class="formInput" style="width:80%;"><input name="filesize" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:15,maxDecimalLen:0,required:true}" value="${fileList.filesize}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">文件描述:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="description" lablename="文件描述" class="inputText" validate="{maxlength:255,required:true}" isflag="tableflag" value="${fileList.description}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">额外信息:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="additional" lablename="额外信息" class="inputText" validate="{maxlength:2000}" isflag="tableflag" value="${fileList.additional}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">下载链接:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <textarea style="display:none" controltype="attachment" name="downloadurl" lablename="下载链接">${fileList.downloadurl}</textarea>
     <a href="javascript:;" field="downloadurl" class="link selectFile" atype="select" onclick="{AttachMent.directUpLoadFile(this);}" validate="{required:true}" name="downloadurl">选择</a>
    </div></td>
  </tr>
 </tbody>
</table>
