<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">-->
var dialog = frameElement.dialog;
var manageone = '${manageone}';
var num=0;
$(function() {
    manageone = manageone.replace("*","'");
    $("#subject").prop("innerHTML",JSON.parse(manageone).graphsubject);
    $("#eventgraphid").prop("innerHTML",JSON.parse(manageone).graphId);
    $("#event").prop("innerHTML",JSON.parse(manageone).event);
    $("#num").prop("innerHTML",JSON.parse(manageone).cannum);
    $.each(JSON.parse(manageone).inputbind, function(idx,obj) {
        num++;
        if(num%2==0){
             $("#varbind").append("<tr id=tr"+idx+" align='center'>"
             			  +"<td bgcolor=\"#BFBFBF\">"+(idx+1)+"</td>"
                          +"<td bgcolor=\"#BFBFBF\">"+obj.varname+"</td>"
                          +"<td bgcolor=\"#BFBFBF\">"+obj.varId+"</td>"
                          +"<td bgcolor=\"#BFBFBF\">"+obj.varbindtype+"</td>"
                          +"<td bgcolor=\"#BFBFBF\">"+obj.varbindval+"</td>"
            );
        }else{
            $("#varbind").append("<tr id=tr"+idx+" align='center'>"
            			 +"<td bgcolor=\"#BFBFBF\">"+(idx+1)+"</td>"
                         +"<td bgcolor=\"#99EE95\">"+obj.varname+"</td>"
                          +"<td bgcolor=\"#99EE95\">"+obj.varId+"</td>"
                          +"<td bgcolor=\"#99EE95\">"+obj.varbindtype+"</td>"
                          +"<td bgcolor=\"#99EE95\">"+obj.varbindval+"</td>"
            );
        }
    });
}); 

</script>
</head>

<body>
<table width="100%" border="2">
  <tr>
    <th width="27%" bgcolor="#FFCC66" scope="row">事件图标题</th>
    <td width="73%" align="center" id="subject">&nbsp;</td>
  </tr>
  <tr>
    <th bgcolor="#FFCC66" scope="row">事件图ID</th>
    <td align="center" id="eventgraphid">&nbsp;</td>
  </tr>
  <tr>
    <th bgcolor="#FFCC66" scope="row">事件</th>
    <td align="center" id="event">&nbsp;</td>
  </tr>
  <tr>
    <th bgcolor="#FFCC66" scope="row">出入个数</th>
    <td align="center" id="num">&nbsp;</td>
  </tr>
  <tr>
    <th bgcolor="#FFCC66" scope="row">参数绑定</th>
    <td><table id = "varbind" width="100%" border="1">
      <tr>
        <th align="center" scope="row"><strong>序号</strong></th>
        <td align="center"><strong>变量名称</strong></td>
        <td align="center"><strong>变量ID</strong></td>
        <td align="center"><strong>绑定方式</strong></td>
        <td align="center"><strong>值</strong></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>