<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 马尔科夫链表</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			
		});
		
		  //alert(defId);
		function searchMark(){
		
		    var defId=$("#defid").val();
			//var paramValueString = "F_defid=参数值";
		 	var paramValueString = "F_defid="+defId;	
		   CommonDialog("mekflxzq",function(data){
				var paramid = "";
				var paramname="";
		
				paramid = paramid + data.ID;
				paramname = paramname + data.F_markovchainNAME;
			    //paramdefid = paramname + data.F_defid;
				//alert(paramid);
				//alert(paramname);
			
			    
				$("#dependmark").val(paramname);
				$("#dependId").val(paramid);
				$("#depend").val(paramname);
				
				//dependname=$("#dependmark").val();
				//alert(dependname);
			},paramValueString);
		}    
		
		
		function savebtn(responseText){
			
			var defId=$("#defid").val();
			//var subject=$("#subject").val();
			alert(defId);
			//alert(subject);
			//window.location.href = "${ctx}/Markovchain/Markovchain/markovchain/save.ht?defId="defId;document.getElementById("markovchainForm").action="save.ht?defId="+defId+"subject="+subject;
			document.getElementById("markovchainForm").action="save.ht?defId="+defId;
			document.getElementById("markovchainForm").submit();
		}
		
		function backbtn(){
			
			var defId=$("#defid").val();
			var typeName=$("#typeName").val();
			var status=$("#status").val();
			window.location.href = "${ctx}/Markovchain/Markovchain/markovchain/listbydefid.ht?defId="+defId+"&typeName="+typeName+"&status="+status;	
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			var defId=$("#defid").val();
			var typeName=$("#typeName").val();
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = window.location.href;
					}else{
					    //window.location.href = window.location.href;
						//window.location.href = "${ctx}/Markovchain/Markovchain/markovchain/list.ht";
						window.location.href = "${ctx}/Markovchain/Markovchain/markovchain/listbydefid.ht?defId="+defId+"&typeName="+typeName;
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		function shezhi()
		{
			var defId=$("#defid").val();
			var typeName=$("#typeName").val();
			var status=$("#status").val();
			window.open("${ctx}/platform/bpm/bpmDefinition/design.ht?defId="+defId+"&typeName="+typeName+"&status="+status);
		}
		function shuaxin()
		{
			$.ajax({ 
			type: "POST", //提交的类型 
			url: "${ctx}/platform/bpm/bpmDefinition/updateMessageStateUnread.ht",//提交地址 
			data: "name=John&location=Boston",//参数 
			success: function(msg){ //回调方法 
			//var object=eval('('+msg+')'); 
			//alert( "Data Saved: " + object.a);//这里是方法内容，和上面的get方法一样 
			document.getElementById("markovchainXML").value =msg;
			
			}, error: function(){
              //请求出错处理
                  alert("Error!");
              } 
			});
		}
	</script>
</head>
<body>
<div class="panel" style="height:100%;overflow:auto;">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty markovchainItem.id}">
			        <span class="tbar-label"><span></span>编辑马尔科夫链表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加马尔科夫链表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save"  onclick="savebtn()"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" onclick="backbtn()"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="markovchainForm" method="post" action="">
		<div type="custform">
			<div class="panel-detail">
				<table cellpadding="2" cellspacing="0" border="1" >
 <tbody>
  <tr>
   <td colspan="4" class="formHead">马尔科夫链表</td>
  </tr>
  <tr>
   <td align="right"  style="width:10%;" class="formTitle" nowrap="nowarp">马尔科夫链名称:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="markovchainNAME" lablename="马尔科夫链名称" class="inputText" validate="{maxlength:100}" isflag="tableflag" value="${markovchain.markovchainNAME}" /></span></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">是否参与仿真:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="isSimulation" lablename="是否参与仿真" controltype="select" validate="{empty:false}" value="${markovchain.isSimulation}"><option value="是">是</option><option value="否">否</option></select></span></td>
  </tr>
  <tr>

   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">所属流程:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="belongto" lablename="所属流程" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${subject}" /></span></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">依赖马尔科夫链:</td>
   <c:choose>
   <c:when test="${not empty markovchainItem.id}">
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" id="depend" name="dependmark" lablename="依赖马尔科夫链" class="inputText" validate="{maxlength:100}" isflag="tableflag" value="" /></span>
   </c:when>
   <c:otherwise>
   <td style="width:25%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" id="depend" name="dependmark" lablename="依赖马尔科夫链" class="inputText" validate="{maxlength:100}" isflag="tableflag" value="${markovchain.dependmark}" /></span>
   </c:otherwise>
   </c:choose>
   <a class="link search" href="javascript:searchMark()"><span></span>查询</a></td>
   
  </tr>
  <tr>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">马尔科夫链发生次数:</td>
   <td style="width:15%;" class="formInput"><input name="frequency" type="text" showtype="{"coinValue":"","isShowComdify":0,"decimalValue":0}" validate="{number:true,maxIntLen:9,maxDecimalLen:0}" value="${markovchain.frequency}" /></td>
   <td align="right" style="word-break:break-all;" class="formTitle" nowrap="nowarp">马尔科夫链的概率:</td>
   <td style="width:25%;" class="formInput"><input name="probability" type="text" showtype="{"coinValue":"","isShowComdify":0,"decimalValue":2}" validate="{number:true,maxIntLen:1,maxDecimalLen:2}" value="${markovchain.probability}" /></td>
  </tr>
  <tr>

   <td align="right" style="width:10%;word-break:break-all;" class="formTitle" nowrap="nowarp"><span style="font-size:12px;text-align:right;background-color:#f0f1f1;">马尔科夫链详细备注:</span></td>
   <td style="width:15%;word-break:break-all;" class="formInput" rowspan="1" colspan="3"><br /><span name="editable-input" style="display:inline-block;" isflag="tableflag"><textarea name="markovchainDes" lablename="马尔科夫链详细备注" class="l-textarea" rows="5" cols="40" validate="{maxlength:2000}" isflag="tableflag" style="margin:2px;width:792px;height:75px;">${markovchain.markovchainDes}</textarea></span></td>

  </tr>
  <tr>

   <td align="right" style="width:10%;word-break:break-all;" class="formTitle" nowrap="nowarp"><span style="font-size:12px;text-align:right;background-color:#f0f1f1;">马尔科夫链xml文件:</span></td>
   			<td style="width:35%;word-break:break-all;" class="formInput" rowspan="1" colspan="1">
   			<textarea id="markovchainXML" name="markovchainXML" validate="{empty:false}" style="margin:2px;width:792px;height:61px;">${markovchain.markovchainXML}</textarea>
  			</td > 
  			<td  colspan="2" >
  				
  				<c:choose>

  				<c:when test="${not empty markovchain.id}">
  				
  				</c:when>
   				<c:otherwise>
   				
   				
  				<div class="group"><a  onclick="javascript:shezhi()" class="link category"> <span></span>设置</a></div>
  				<div class="group"><a class="link reload"  onclick="javascript:shuaxin()"><span></span>刷新</a></div>
  				<div class="group"><a href="javascript:;" class="tipinfo"><span>请刷新显示这个xml</span></a></div>
  				
  				
  				</c:otherwise>
  				</c:choose>
  				
  				
 			</td>
  			
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="text" name="id" value="${markovchain.id}"/>
		<input type="text" id="saveData" name="saveData"/>
		<input type="text" name="executeType"  value="start" />
		<input type="text" id="defid" name="defid" value="${defId}">
		<input type="text" id="dependId" name="dependId" value="${dependid}">
		<input type="text" id="dependmark"  value="${dependmark}">
		<input type="text" id="typeName" value="${typeName}">
		<input type="text" id="status" value="${status}">
		
	</form>
</body>
</html>
