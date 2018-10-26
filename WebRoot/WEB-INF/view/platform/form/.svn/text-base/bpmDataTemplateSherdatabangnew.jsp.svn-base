<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<link rel="stylesheet" type="text/css" href="${ctx}/styles/default/css/form.css">
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDialog2.js"></script>
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js" ></script>
<style type="text/css">
	body{
		overflow:hidden;
	}
	a.extend{
	  display:inline;
	}
.field-ul {
	width: 95%;
	height: 95%;
	margin: 0; 
	padding: 0;
	overflow-y: auto;
	overflow-x: hidden;
}
.fields-div {
	float: left;
	border: 1px solid #828790;
	width: 160px;
	height: 260px;
	overflow: auto;
}
.domBtnDiv {
	display: block;
	margin-left:5px;
	float:left;
	width:340px;
	height:260px;
	background-color: powderblue;
	overflow-y: auto;
	overflow-x: hidden;
}
#fieldContainer{
	height:200px;
	overflow-y:auto;
	overflow-x:hidden;
}
#fieldTable{
	margin:0;
}
</style>
<script type="text/javascript"><!--
	var dialog = frameElement.dialog;
	var snode = "";
	var temp = "";
	var scan = '${scansher}';
	var actdefIdsher = '${actdefIdsher}';
	var btnId = ${btnId};
	var urlsher = "/mas/platform/bpm/task/complete2.ht?actdefIdsher="+actdefIdsher+"%26btnId="+btnId+"%26scan="+scan;
	var thistime = ${thistime};
	$(function() {
		initselectnode();
	});
	function initselectnode(){
		var a = '${nodeIdsher}';
			a = a.replace("[","");
			a = a.replace("]","");
		var nodeIdsher = a.split(",");
		var o = document.getElementById("selectnode");
		var len = nodeIdsher.length;
		for(var i = 0; i < len; i++){
 			var op = document.createElement("option");
 			op.setAttribute("value",i+1);
 			op.innerHTML = nodeIdsher[i];
 			o.appendChild(op);
		}
	};
	function bang(){
		if(snode!=""&&snode!="------------------节点选择------------------"){
			$.ligerDialog.confirm("节点"+snode+"已绑定。", function (yes){
					if(yes){
					    urlsher = $("#theurl").val();
						dialog.get("sucCall")(snode,urlsher);
						dialog.close();
					}
            });
		}else{
			$.ligerDialog.warn("关键信息未填写！","失败");
		}
	};
	function getSelectnode(){
		snode = $("#selectnode option:selected").text();
		$("#nodeIds").val(snode);
		if(temp==""){
			temp = urlsher;
			urlsher+="%26nodeIdandnodeName="+snode;
			$("#theurl").val(urlsher);
		}else{
			urlsher = temp;
			urlsher+="%26nodeIdandnodeName="+snode;
			$("#theurl").val(urlsher);
		}
	};
--></script>
</head>
<body>
	<div id="inputPanel">
	<br/>
			<table>
				<tr>
					<td>&nbsp
						<select name="selectnode" class="ht-input" id="selectnode"  onChange="getSelectnode();" >
                    	  <option value ="0" >------------------节点选择------------------</option>
                   		</select>
					</td>
				</tr>
				<tr>
					<th>&nbsp节点参数:&nbsp&nbsp&nbsp&nbsp<input class="ht-input" id="nodeIds" type="text" style="width:175px;"/></th>
				</tr>
			</table>
			<br/>
			&nbsp按钮链接：<br/>
			&nbsp<textarea 	type="ht-input" id="theurl" rows="5" value="该按钮所指何处"  
							onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
							onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
							style="width:285px;color:#999999"></textarea>
			<br/>
			<br/>
	   <fieldset class="base">
			<div class="panel-toolbar">
			<table>
				<tr >
				   <td width="73%">
						<a class="link save"  href="javascript:;" onclick = "bang();">
							<span></span>
							绑定
						</a>
				  </td>
				  <td width="70%">
						<a class="link close"  href="javascript:;" onclick = "dialog.close();">
							<span></span>
							取消
						</a>
				  </td>
				</tr>
			</table>
		  </div>
	 </fieldset>
	</div>
</body>
</html>