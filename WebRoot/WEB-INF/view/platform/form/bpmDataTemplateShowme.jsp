<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表格建模信息</title>
<%@include file="/commons/include/newweb.jsp" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDialog2.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript">-->
var name = "${name}";
var id = ${id};
var formkey = "${formkey}";
var defId = ${defId};
var type = "${type}";
var displayfield = '${displayfield}';
var managefield = '${managefield}';
var num=0;
$(function() {
	$("#name").prop("innerHTML",name);
	$("#templateId").prop("innerHTML",id);
	$("#formKey").prop("innerHTML",formkey);
	$("#defId").prop("innerHTML",defId);
	$("#templateAlias").prop("innerHTML",type);
	managefield = managefield.replace("*","'");
	$.each(JSON.parse(displayfield), function(idx,obj) {
    	num++;
    	if(num%2==0){
    		 $("#fieldm").append("<tr id=tr"+idx+" align='center'>"
                          +"<td bgcolor=\"#BFBFBF\">"+(idx+1)+"</td>"
                          +"<td bgcolor=\"#BFBFBF\">"+obj.desc+"</td>"
                          +"<td bgcolor=\"#BFBFBF\">文本</td>"
            );
    	}else{
    		$("#fieldm").append("<tr id=tr"+idx+" align='center'>"
                          +"<td bgcolor=\"#99EE95\">"+(idx+1)+"</td>"
                          +"<td bgcolor=\"#99EE95\">"+obj.desc+"</td>"
                          +"<td bgcolor=\"#99EE95\">文本</td>"
            );
    	}
    });
    $.each(JSON.parse(managefield), function(idx,obj) {
    	num++;
    	if(num%2==0){
    		 $("#managefieldm").append("<tr id=tr"+idx+" align='center'>"
                          +"<td bgcolor=\"#BFBFBF\">"+(idx+1)+"</td>"
                          +"<td bgcolor=\"#BFBFBF\">"+obj.desc+"</td>"
                          +"<td bgcolor=\"#BFBFBF\">"+obj.name+"</td>"
						  +"<td bgcolor=\"#BFBFBF\">"+obj.btntype+"</td>"
                          +"<td bgcolor=\"#BFBFBF\">"+obj.scan+"</td>"
                          +"<td bgcolor=\"#BFBFBF\"><a href=\"\" onclick=\"eventdetail("+idx+","+id+")\">"+obj.event+"</a></td>"
            );
    	}else{
    		$("#managefieldm").append("<tr id=tr"+idx+" align='center'>"
                           +"<td bgcolor=\"#99EE95\">"+(idx+1)+"</td>"
                          +"<td bgcolor=\"#99EE95\">"+obj.desc+"</td>"
                          +"<td bgcolor=\"#99EE95\">"+obj.name+"</td>"
						  +"<td bgcolor=\"#99EE95\">"+obj.btntype+"</td>"
                          +"<td bgcolor=\"#99EE95\">"+obj.scan+"</td>"
                          +"<td bgcolor=\"#99EE95\"><a href=\"\" onclick=\"eventdetail("+idx+","+id+")\">"+obj.event+"</a></td>"
            );
    	}
    });
});
function eventdetail(index,id){
	var url="${ctx}/platform/form/bpmDataTemplate/showevent.ht?id="+id+"&index="+index;
		DialogUtil.open({
            title:"",
            url: url,
            height:500,
            width:700,
            isResize: false,
            pwin:window,
            sucCall:function(){     
            }
        });
}
</script>
</head>

<body>
<table width="100%" border="2" id="showgrade">
  <tr>
    <th width="30%" bgcolor="#8EC2F2" scope="row">模板名称</th>
    <td width="70%" align="center" id="name">&nbsp;</td>
  </tr>
  <tr>
    <th bgcolor="#8EC2F2" scope="row">ID</th>
    <td align="center" id="templateId">&nbsp;</td>
  </tr>
  <tr>
    <th bgcolor="#8EC2F2" scope="row">表单Key</th>
    <td align="center" id="formKey">&nbsp;</td>
  </tr>
  <tr>
    <th bgcolor="#8EC2F2" scope="row">绑定流程</th>
    <td align="center" id="defId">&nbsp;</td>
  </tr>
  <tr>
    <th bgcolor="#8EC2F2" scope="row">模板类型</th>
    <td align="center" id="templateAlias">&nbsp;</td>
  </tr>
  <tr>
    <th bgcolor="#8EC2F2" scope="row">列元素建模</th>
    <td align="center" id="displayfield"><table width="100%" border="1" id="fieldm">
      <tr>
        <th width="22%" bgcolor="#99EE95" scope="col">编号</th>
        <th width="37%" bgcolor="#99EE95" scope="col">注释</th>
        <th width="41%" bgcolor="#99EE95" scope="col">显示形式</th>
      </tr>
    </table></td>
  </tr>
  <tr>
    <th bgcolor="#8EC2F2" scope="row">功能按钮建模</th>
    <td align="center" id="button"><table width="100%" border="1" id="managefieldm">
      <tr>
        <th bgcolor="#99EE95" scope="col">编号</th>
        <th bgcolor="#99EE95" scope="col">名称</th>
        <th bgcolor="#99EE95" scope="col">属性</th>
        <th bgcolor="#99EE95" scope="col">类型</th>
        <th bgcolor="#99EE95" scope="col">参数绑定</th>
        <th bgcolor="#99EE95" scope="col">事件详情</th>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>