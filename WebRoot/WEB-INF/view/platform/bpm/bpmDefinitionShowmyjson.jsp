<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">-->
var dialog = frameElement.dialog;
var jsonstr = '${jsonstr}';
var mydom = JSON.parse(jsonstr);
var num=0;
$(function() {
    $("#domId").append(mydom.domId);
    $("#domname").prop("innerHTML",mydom.domname);
    $("#domaction").prop("innerHTML",mydom.domaction);
    $("#theformfield").prop("innerHTML",mydom.theformfield);
    $("#graphID").prop("innerHTML",'<a href="${ctx}/platform/bpm/bpmDefinition/design.ht?defId='+mydom.graphId+'&flagflex=3" target="_blank">'+mydom.graphId+'</a>');
    $("#graphsubject").prop("innerHTML",'<a href="${ctx}/platform/bpm/bpmDefinition/design.ht?defId='+mydom.graphId+'&flagflex=3" target="_blank">'+mydom.graphsubject+'</a>');
    $("#outnum").prop("innerHTML",mydom.outnum);
    $("#innum").prop("innerHTML",mydom.innum);
    $("#dialogId").prop("innerHTML",mydom.dialogId);
    $("#dialogname").prop("innerHTML",mydom.dialogname);
    $("#formKey").prop("innerHTML",mydom.formKey);
    $("#formsubject").prop("innerHTML",mydom.formsubject);
    $("#templateId").prop("innerHTML",mydom.templateId);
    $("#templatesubject").prop("innerHTML",mydom.templatesubject);
    JSON.stringify(mydom.readdom);
    $.each(mydom.readdom, function(idx,obj) {
    	num++;
    	if(num%2==0){
    		 $("#readdom").append("<tr id=tr"+idx+" align='center'>"
                          +"<td bgcolor=\"#BFBFBF\">入参元素ID:</td>"
                          +"<td bgcolor=\"#BFBFBF\">"+obj.indomid+"</td>"
                          +"<td bgcolor=\"#BFBFBF\">输入参数ID：</td>"
                          +"<td bgcolor=\"#BFBFBF\">"+obj.invarid+"</td>"
            );
    	}else{
    		$("#readdom").append("<tr id=tr"+idx+" align='center'>"
                          +"<td bgcolor=\"#99EE95\">入参元素ID:</td>"
                          +"<td bgcolor=\"#99EE95\">"+obj.indomid+"</td>"
                          +"<td bgcolor=\"#99EE95\">输入参数ID：</td>"
                          +"<td bgcolor=\"#99EE95\">"+obj.invarid+"</td>"
            );
    	}
    });
    $.each(mydom.writedom, function(idx,obj) {
    	num++;
    	if(num%2==0){
    		 $("#writedom").append("<tr id=tr"+idx+" align='center'>"
                          +"<td bgcolor=\"#BFBFBF\">出参元素ID:</td>"
                          +"<td bgcolor=\"#BFBFBF\">"+obj.outdomid+"</td>"
                          +"<td bgcolor=\"#BFBFBF\">输出参数ID：</td>"
                          +"<td bgcolor=\"#BFBFBF\">"+obj.outvarid+"</td>"
            );
    	}else{
    		$("#writedom").append("<tr id=tr"+idx+" align='center'>"
                          +"<td bgcolor=\"#99EE95\">出参元素ID:</td>"
                          +"<td bgcolor=\"#99EE95\">"+obj.outdomid+"</td>"
                          +"<td bgcolor=\"#99EE95\">输出参数ID：</td>"
                          +"<td bgcolor=\"#99EE95\">"+obj.outvarid+"</td>"
            );
    	}
    });
}); 

</script>
</head>
<body>
<table width="100%" border="1">
  <tr>
    <th width="141" align="center" bgcolor="#8EC2F2" scope="row">对象ID</th>
    <td width="279" align="center" id="domId"></td>
  </tr>
  <tr>
    <th align="center" bgcolor="#8EC2F2" scope="row">对象名称</th>
    <td align="center" id="domname">&nbsp;</td>
  </tr>
  <tr>
    <th align="center" bgcolor="#8EC2F2" scope="row">事件类型</th>
    <td align="center" id="domaction">&nbsp;</td>
  </tr>
  <tr>
    <th align="center" bgcolor="#8EC2F2" scope="row">绑定表字段</th>
    <td align="center" id="theformfield">&nbsp;</td>
  </tr>
  <tr>
    <th align="center" bgcolor="#8EC2F2" scope="row">绑定对话框信息</th>
    <td align="center" valign="middle"><table width="100%" border="1">
      <tr>
        <th width="50%" align="center" bgcolor="#F3F0B6" scope="row">对话框ID：</th>
        <td width="400" align="left" id="dialogId">&nbsp;</td>
      </tr>
      <tr>
        <th width="50%" align="center" bgcolor="#F3F0B6" scope="row">对话框名称：</th>
        <td align="left" id="dialogname">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <th align="center" bgcolor="#8EC2F2" scope="row">绑定表单信息</th>
    <td align="center"><table width="100%" border="1">
      <tr>
        <th width="50%" align="center" bgcolor="#F3F0B6" scope="row">表单formKey：</th>
        <td width="50%" align="left" id="formKey">&nbsp;</td>
      </tr>
      <tr>
        <th align="center" bgcolor="#F3F0B6" scope="row">表单名称：</th>
        <td align="left" id="formsubject">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <th align="center" bgcolor="#8EC2F2" scope="row">绑定表格信息</th>
    <td align="center"><table width="100%" border="`1" bordercolor="#000000">
      <tr>
        <th width="50%" align="center" bgcolor="#F3F0B6" scope="row">表格ID：</th>
        <td width="50%" align="left" id="templateId">&nbsp;</td>
      </tr>
      <tr>
        <th width="50%" align="center" bgcolor="#F3F0B6" scope="row">表格名称：</th>
        <td width="50%" align="left" id="templatesubject">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <th align="center" bgcolor="#8EC2F2" scope="row">事件图信息</th>
    <td align="center"><table width="100%" border="1">
      <tr>
        <th width="50%" align="center" bgcolor="#F3F0B6" scope="row">事件图ID：</th>
        <td width="50%" align="left" id="graphID">&nbsp;</td>
      </tr>
      <tr>
        <th align="center" bgcolor="#F3F0B6" scope="row">事件图名称：</th>
        <td align="left" id="graphsubject">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <th align="center" bgcolor="#8EC2F2" scope="row">出参绑定情况</th>
    <td align="left"><table id="writedom"  width="100%" border="1">
    </table></td>
  </tr>
  <tr>
    <th align="center" bgcolor="#8EC2F2" scope="row">入参绑定情况</th>
    <td align="left"><table id="readdom"  width="100%" border="1">
    </table></td>
  </tr>
   <tr>
    <th align="center" bgcolor="#8EC2F2" scope="row">事件出参个数</th>
    <td align="center" id="outnum">&nbsp;</td>
  </tr>
  <tr>
    <th align="center" bgcolor="#8EC2F2" scope="row">事件入参个数</th>
    <td align="center" id="innum">&nbsp;</td>
  </tr>
</table>
</body>

</html>
