<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ page import="java.*"%>
<%@ page import="com.hotent.support.model.catelog.FileCatalog" %>
<html>
<head>

	<title>编辑 文件目录</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		function foo(){
		var file=document.getElementById("filecatalog").value;
		var os=document.getElementById("productos").value;
		var lang=document.getElementById("languages").value;
		var pid=document.getElementById("pid").value;
		
		url = __ctx+'/support/catelog/fileList/list.ht?PRODUCTID=' + pid + "&FILECATALOG=" + file + "&OS=" + os +"&LANGUAGE=" + lang;
		location.href=url;
		}
	</script>
</head>
<body>
<%String i = (String)request.getAttribute("id"); %>
<input type="hidden" value="<%=i%>" id="pid"/>
<button onclick="foo()">继续</button>
<form id="all" method="post" action="edit.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">文件目录</td>
  </tr>
   <tr><td><select id="filecatalog">
   <option value="c://">目录1</option>
   <option value="c://">目录2</option>
   </div>
   </select></td>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${fileCatalog.id}"/>
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">操作系统</td>
  </tr>
   <tr><td><select id="productos">
   <option value="os1">os1</option>
   <option value="os2">os2</option>
   </select></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${productOs.id}"/>
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">语言</td>
  </tr>
   <tr><td><select id="languages">
   <option value="Chinese">Chinese</option>
   <option value="English">English</option>
   </select></td>
   </tr>
   <!-- <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="FILECATALOG" lablename="文件目录" class="inputText" validate="{maxlength:25,required:true}" isflag="tableflag" type="text" value="${fileCatalog.FILECATALOG}" /></span></td> -->
  </tr>
 </tbody>
</table>
			</div>
		</div>
	</form> 
</body>
</html>
