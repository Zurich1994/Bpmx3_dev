
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,com.hotent.core.util.AppUtil" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
				<%
String defId = null;
//defId = request.getParameter("defbId").toString();
 %>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<link rel="stylesheet" type="text/css" href="${ctx}/styles/default/css/form.css">
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/ajaxgrid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/BpmDefinitionDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<style>
.new_design,.had_design,.last_design{
 background:#C8C8C8 repeat-x;
 padding-top:3px;
 border-top:1px solid #708090;
 border-right:1px solid #708090;
 border-bottom:1px solid #708090;
 border-left:1px solid #708090; 
 width:180px;
 height:auto;
 font-size:10pt;
 cursor:hand;
}
.ok_btn,.cancle_btn{
 background:#7D7D7D repeat-x;
 width:280px;
 height:250px;
}
.free_design{
background:#FF7575;
 padding-top:3px;
 border-top:1px solid #708090;
 border-right:1px solid #708090;
 border-bottom:1px solid #708090;
 border-left:1px solid #708090; 
 width:180px;
 height:auto;
 font-size:10pt;
 cursor:hand;
}
</style>
<script type="text/javascript">
var dialog = frameElement.dialog;
function design() { 
	var nurl =__ctx + "/platform/bpm/bpmDefinition/designBtn.ht?defId="<%=defId%>;
    window.open(nurl);
}
function ok_fun(){
   window.close();
}
</script>
<script type="text/javascript">
 /**
		* 选择流程
		*/
		function selectFlow(){
			BpmDefinitionDialog({isSingle:true,showAll:1,validStatus:2,callback:dlgCallBack,returnDefKey:true});
		};		
		/**
		* 选择流程的回调处理
		*/
		function dlgCallBack(defIds,subjects,defKeys){
			if(subjects==null || subjects =="") return;
			$("#flowkey").val(defKeys);
			$("#flowname").val(subjects);
		};
		
		/**
		* 部分代理 添加流程
		*/
		function addFlow(){
			BpmDefinitionDialog({isSingle:false,showAll:1,returnDefKey:true,validStatus:2,callback:function(defIds,subjects,defKeys){
				if(!subjects) return ;
				$('#firstRow').remove();
				var newSubjects=subjects.split(",");
				var newDefKeys=defKeys.split(",");
				for(var i=0,len=newDefKeys.length;i<len;i++){
					var defKey=newDefKeys[i];
					
					var subject=newSubjects[i];
					var row=$("#def_" + defKey);
					if(row.length>0) continue;
					var tr=getRow(defKey,subject);
					$("#bpmAgentItem").append(tr);
				}
			}});
		}
		
		/**
		* 部分代理 构造一行流程(用于添加到表中)
		*/
		function getRow(defKey,subject){
			var template=$("#tableRowTemplate").val();
			return template.replaceAll("#defKey",defKey).replaceAll("#subject",subject);
		}
		
		/**
		* 删除一行
		*/
		function singleDel(obj){
			var tr=$(obj).closest('tr');
			$(tr).remove();
		};
		
		
		
 </script>
 <script type="text/javascript">

 
 </script>
</head>
<body>
	<div id="inputPanel">
	
	<form id="agentSettingForm" method="post" action="save.ht" name="forms">
	  
		<table>
				<tr>
					
				</tr>
				<tr>
					<td>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link add"  onclick="window.open('${ctx}/platform/bpm/bpmDefinition/designBtn.ht?')"><span></span>新建流程</a>
							</div>
						</div>
					</div>	
					</td>
				</tr>
                <tr>
					<td>
						
			

					</td>
				</tr>
                <tr>
					<td>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link add" href="javascript:;" onclick="window.open('${ctx}/platform/bpm/bpmDefinition/designBtn.ht?defId=<%=defId %>')"><span></span>最新绘图</a>
							</div>
						</div>
					</div>	 
					</td>
				</tr>
                <tr>
					<td>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link add" href="javascript:;" onclick="window.close()"><span></span>确定</a>
							</div>
						</div>
					</div>	 
					</td>
				</tr>				
		  </table>
		<p>&nbsp;</p>

	    <label for="mes"></label>
	   
		<p>
		 绑定的流程ID： <input type="text" name="mes" id="mes" height="30px" width="5000px" value="<%=defId%>">
		</p>
			<div class="panel-body" >		
					   	<table  class="table-grid table-list"  Id = "agentDef" cellpadding="1" cellspacing="1" style="width:100%" >
					   		<thead>
					   			<tr>
					    			<th>流程名称</th>
					    			<th width="150px">管理</th>
					    		</tr>
					    	</thead>
					    	<tbody id="bpmAgentItem">
					    	<c:choose>
					    		<c:when test="${fn:length(agentSetting.agentDefList)>0}">
					    			<c:forEach items="${agentSetting.agentDefList}" var="bpmAgentItem">
							    		<tr id="def_${bpmAgentItem.flowkey}" >
							    			<td>
							    				<input type="hidden" name="defKey" value="${bpmAgentItem.flowkey}" />
							    				<input type="hidden" name="flowname" value="${bpmAgentItem.flowname}">
							    				<a href="${ctx}/platform/bpm/bpmDefinition/designBtn.ht?defId=${bpmAgentItem.flowkey}" target="_blank">${bpmAgentItem.flowname}</a>
							    			</td>
							    			<td>
							    				<a href="javascript:;" class="link del" onclick="singleDel(this);" class="link del">删除</a>
							    				
											</td>
							    		</tr>
							    	</c:forEach>
					    		</c:when>
					    		<c:otherwise>
					    			<tr id="firstRow">
					    				<td colspan="2" align="center">
					    					<font color='red'>还未选择流程</font>
					    				</td>
					    			</tr>
					    		</c:otherwise>
					    	</c:choose>
					    	</tbody>
					    </table>
				</div>
				
			
			<input type="hidden" id="id" name="id" value="${agentSetting.id}" />
			<input type="hidden" name="createtime" value="<fmt:formatDate value='${agentSetting.createtime}' pattern='yyyy-MM-dd'/>"/>
	</form>
</div>

<textarea id="tableRowTemplate" style="display: none;">
	<tr id="def_#defKey"}" type="subdata">
			<td>
			<input type="hidden" name="defKey" value="#defKey">
			<input type="hidden" name="flowname" value="#subject">
		<a href="${ctx}/platform/bpm/bpmDefinition/get.ht?defKey=#defKey" target="_blank">#subject</a>
			</td>
			<td>
				<a href="javascript:;" class="link del" onclick="singleDel(this);">删除</a>
			</td>
	</tr>
</textarea>

<textarea id="agentConditionTableRowTemplate" style="display: none;">
	<tr type="subdata">
		<td>
			<input type="hidden" name="condition" value="#condition" >
			<input type="hidden" name="memo" value="#memo">
			<input type="hidden" name="agentid" value="#agentid">
			<input type="hidden" name="agent" value="#agent">
		</td>
	</tr>
</textarea>

				
     </body>
</html>
