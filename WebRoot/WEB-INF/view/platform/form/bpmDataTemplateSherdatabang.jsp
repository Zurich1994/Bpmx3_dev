
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
	var btnId = ${btnId};
	var thistime = ${thistime};
	$(function() {
	});
	function bang(){
		var check = document.getElementsByName("checknew");
		var len=check.length;
		var myArray=new Array();
		var count = 0;
		var aryId = $("input[type='checkbox'][disabled!='disabled'][class='pknew']:checked");
		if(aryId.length == 0){
			$.ligerDialog.warn("请绑定参数！");
			return false;
		}else{
                for(var i=0;i<len;i++){	
                       if(check[i].checked){
                    		myArray[count]=check[i].value; 
                    		count++;
       					}
            	}
            	$.ligerDialog.confirm("已绑定"+count+"个参数。", function (yes){
					if(yes){
						dialog.get("sucCall")(myArray);
						dialog.close();
					}
            });
         }
	};
--></script>
</head>
<body>
	<div id="inputPanel">
	   <fieldset class="base">
			     <div style="width:280px; height:300px; overflow:scroll;">
						<table style="margin-top: 0px;"  id="canItem" class="table-grid table-list" cellpadding="1" cellspacing="1">
							<c:forEach items="${fieldnamelistsher}" var="item" varStatus="status">
								<tr class="fieldname" ">
				 					 <td style="width:30px;">
				 						<input type="checkbox"  name="checknew" value="${item}" class="pknew" />
				  					 </td>
				  					 <td>
											${item}
				 			 		 </td>
								</tr>
		   		   			</c:forEach>
		    			</table>
		    	</div>
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