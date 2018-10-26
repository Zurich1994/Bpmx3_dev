<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">-->
var num = ${num};
var dialog = frameElement.dialog;
var myjsonlist = '${myjsonlist}';
var list = myjsonlist.split(",@");
 $(function() {
	$.each(list, function(idx,obj) {
    	    var domjson = JSON.parse(obj);
        	$("#eventid").append("<tr id=tr"+idx+" align='center'>"
            	          +"<td>"+(idx+1)+"</td>"
                	      +"<td>"+domjson.domname+"</td>"
                     	  +"<td>"+domjson.domId+"</td>"
             	          +"<td>"+domjson.domaction+"</td>"
                	      +'<td><a href="${ctx}/platform/bpm/bpmDefinition/design.ht?defId='+domjson.graphId+'&flagflex=3" target="_blank">'+domjson.graphsubject+'</a></td>'
                    	  +"<td><textarea >"+JSON.stringify(domjson.readdom)+"</textarea></td>"
         	              +"<td><textarea >"+JSON.stringify(domjson.writedom)+"</textarea></td>"
            	          +"<td>数据库</td>"
                	      +"<td>"+domjson.formKey+"</td>"
         	);
	});
}); 

</script>
</head>

<body>
<div align="left">
<input name="close" type="submit" id="close" onclick="dialog.close()" value="关闭" />
  <table width="100%" border="1" align="center" id="eventid">
    <tr align="left">
      <td width="4%" bgcolor="#8EC2F2"><div align="center">序号</div></td>
      <td width="11%" bgcolor="#8EC2F2"><div align="center">对象名称</div></td>
      <td width="11%" bgcolor="#8EC2F2"><div align="center">对象ID</div></td>
      <td width="8%" bgcolor="#8EC2F2"><div align="center">绑定事件</div></td>
      <td width="11%" bgcolor="#8EC2F2"><div align="center">事件图</div></td>
      <td width="16%" bgcolor="#8EC2F2"><div align="center">入参数绑定情况</div></td>
      <td width="16%" bgcolor="#8EC2F2"><div align="center">出参数绑定情况</div></td>
      <td width="6%" bgcolor="#8EC2F2"><div align="center">数据来源类型</div></td>
      <td width="11%" bgcolor="#8EC2F2"><div align="center">数据来源ID</div></td>
    </tr>
  </table>
</div>

</body>
</html>