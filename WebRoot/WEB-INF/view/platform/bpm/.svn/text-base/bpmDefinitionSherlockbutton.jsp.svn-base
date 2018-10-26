
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,com.hotent.core.util.AppUtil,com.hotent.platform.model.bpm.BpmDefinition"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title></title>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@include file="/commons/include/form.jsp"%>
		<link rel="stylesheet" type="text/css" href="../input.css">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/default/css/form.css">
		<script type="text/javascript"
			src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
		<script type="text/javascript" src="${ctx}/js/hotent/ajaxgrid.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/system/BpmDefinitionDialog_new.js"></script>

		<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
		<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/ueditor2/_src/ui/editor.js"></script>
		<style>
.new_design,.had_design,.last_design {
	background: #C8C8C8 repeat-x;
	padding-top: 3px;
	border-top: 1px solid #708090;
	border-right: 1px solid #708090;
	border-bottom: 1px solid #708090;
	border-left: 1px solid #708090;
	width: 180px;
	height: auto;
	font-size: 10pt;
	cursor: hand;
}

.ok_btn,.cancle_btn {
	background: #7D7D7D repeat-x;
	width: 280px;
	height: 250px;
}

.free_design {
	background: #FF7575;
	padding-top: 3px;
	border-top: 1px solid #708090;
	border-right: 1px solid #708090;
	border-bottom: 1px solid #708090;
	border-left: 1px solid #708090;
	width: 180px;
	height: auto;
	font-size: 10pt;
	cursor: hand;
}
</style>
		<script type="text/javascript">	

var obj = $(editor.curInput);
		var bodyObj=obj.parents('body');
		var setting = {
				edit: {
					enable: true,
					showRemoveBtn: false,
					showRenameBtn: false,	
					drag:{}
				},				
				data: {
					keep: {
						parent: true,
						leaf: true
					},
					simpleData: {
						enable: true
					}
				},				
				view: {
					selectedMulti: false
				}
			},
			dragDiv;

		$(function() {
			$(".button-td").bind("mouseenter mouseleave", function() {
				$(this).toggleClass("button-td-hover");
			});
			
			var transactiongraphStr = $(editor.curInput).get(0).getAttribute("transactiongraph");
			if (transactiongraphStr) {
				bindData(transactiongraphStr);
			}		
			
			$('.fieldCheck','.fieldTr').live("click",function(){
				$(this).siblings('.fieldSelect').each(function(){
					if($(this).css('display')!='none'){
						$(this).hide() ;
					}else{
						$(this).show() ;
					}
				});
				
			});
			
		});		

		dialog.onok = function() {
			//var defKey =$("#defkey1").val();
			alert("defKey!"+defKey);		
			var mes =  $("#mes").val();
	//		subject = $("#flowname1").val();
	//	var defKey =	${bpmdefinition.defKey };
	//			alert(defKey);
	//      href1="/mas/platform/bpm/bpmDefinition/get.ht?defKey="+name;//+"target=/"view_window/"";
	//		editor.curInput.setAttribute("href",href1);
	//		editor.curInput.setAttribute("target","view_window");
	        alert("!!!"+defKey+subject);
	        var json="{defKey:'"+defKey+"',mes:'"+mes+"',subject:'"+subject+"'}";
			editor.curInput.setAttribute("transactiongraph",json);
			editor.curInput = null;
		};
		



var defKey ="";
var defId  =0;
var subject ="";		
var dialog = frameElement.dialog;	
			
		
		
function design() { 
	var nurl =__ctx + "/platform/bpm/bpmDefinition/designBtn.ht";
    window.open(nurl);
};

function ok_fun(){
   window.close();
};

function bindData(transactiongraphStr) {
			var transactiongraph = eval("("+transactiongraphStr+")" ),field;
			if(!transactiongraph)return;
			alert("1"+transactiongraph.defKey);

		    $("#mes").val(transactiongraph.mes);
			defKey = transactiongraph.defKey;
			subject= transactiongraph.subject;
		    $("#defKey").val(defKey);
		    $("#subject").val(subject);
		    	
		
			
		};

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
			$("#flowid").val(defIds);
			
			$("#flowname").val(subjects);
			$("#flowkey").val(defKeys);
		};		
		/**
		* 部分代理 添加流程
		*/
		function addFlow(){
			BpmDefinitionDialog({isSingle:true,showAll:1,returnDefKey:true,validStatus:2,callback:function(defIds,subjects,defKeys){
				if(!subjects) return ;
				$('#firstRow').remove();
				var newSubjects=subjects.split(",");
				var newDefKeys=defKeys.split(",");
				var newDefIds=defIds.split(",");
				
				for(var i=0,len=newDefKeys.length;i<len;i++){
					defKey=newDefKeys[i];
					defId=newDefIds[i];
					 subject=newSubjects[i];
					
					var row=$("#def_" + defKey);
					if(row.length>0) continue;
					var tr=getRow(defId,defKey,subject);
					$("#bpmAgentItem").empty();
					$("#bpmAgentItem").append(tr);
				}
			}});
		};		
		/**
		* 部分代理 构造一行流程(用于添加到表中)
		*/
		function getRow(defId,defKey,subject){
			var template=$("#tableRowTemplate").val();
			return template.replaceAll("#defId",defId).replaceAll("#defKey",defKey).replaceAll("#subject",subject);
			
		};
		
		/**
		* 删除一行
		*/
		function singleDel(obj){
			var tr=$(obj).closest('tr');
			$(tr).remove();
		};
		
		function abcd(){
		var url="${ctx}/platform/bpm/bpmDefinition/designsher.ht";
		window.open(url);
		};
		function disbang(){
			var btnnamestr = $("#btnname").val();
		
			var formDefId =$("#formDefId").val();
			var url =__ctx + "/platform/bpm/bpmDefinition/disbang.ht?";	
				  
					$.post(url,{},function(){
					alert("已解綁。");
				});
		};
		
function savebang(){
		    var gai = $("#mes").val();
		    alert("gai:"+gai);
			var url =__ctx + "/platform/bpm/bpmDefinition/sherlock.ht?";			  
					$.post(url,{},function(){
					});
		};
		function sherfresh(){
		   //var a = ${bpmdefinitionsher.defKey };
		   //window.location.reload();
		   defKey = $("#defKeysher").val();
		   subject = $("#defsubjectsher").val();
		   alert("${bpmdefinitionsher.defKey }:"+defKey);
		    $("#defKey").val(defKey);
		    $("#subject").val(subject);
			//
		};
		function detail(){
		var href="${ctx}/platform/bpm/bpmDefinition/design.ht?defKey="+defKey+"&flagflex=3";
		window.open(href);
		}
 --></script>
	</head>
	<body>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="100%" align="center">
					<center>
						<h2>
							绑定流程
							<h2>
					</center>
				</th>
			</tr>
		</table>
		<div id="inputPanel">
			<form id="agentSettingForm" method="post" action="save.ht">
				<label for="mes"></label>
				<div class="panel-body">
					<table class="table-detail" cellpadding="0" cellspacing="0"
						border="0">



						<tbody id="bpmAgentItem">
							<c:choose>
								<c:when test="${fn:length(agentSetting.agentDefList)>0}">
									<c:forEach items="${agentSetting.agentDefList}"
										var="bpmAgentItem">
										<tr id="def_${bpmAgentItem.flowkey}">
											<td>
												<input type="text" name="flowkey"
													value="${bpmAgentItem.flowkey}" />
												<input type="text" name="flowname"
													value="${bpmAgentItem.flowname}">
												<input type="text" name="defid"
													value="${bpmAgentItem.flowid}">

											</td>

										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>

									<tr id="firstRow">

										<th width="30%">
											流程名称：
										</th>
										<td>

											<input type="hidden" id="defKey" name="flowkey"
												readonly="readonly">
											<input type="hidden" id="defId" name="flowId"
												readonly="readonly" value="${bpmdefinitionsher.defId}" />
											<input type="text" id="subject" name="subject"
												readonly="readonly"  >

										</td>
										<td>
											<div class="panel-toolbar">
												<div class="toolBar">
													<div class="group">
														<a class="link flowDesign"
															onclick="window.open('${ctx}/platform/bpm/bpmDefinition/designsher.ht?flagflex=3');"><span>
														</span>新建事务图</a>
													</div>
													<div class="group">
														<a class="link add" href="javascript:;"
															onclick="addFlow();"><span></span>选择已有流程</a>
													</div>
													<div class="group">
														<a class="link add" href="javascript:;"
															onclick="disbang();dialog.close();"><span></span>解除绑定</a>
													</div>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<th width="30%">
											详细信息：
										</th>
										<td>
											<a onclick="detail();" target="_blank">详细流程图</a>
										</td>
										<td></td>
									</tr>

								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0" style="margin-left: 3px; margin-right: 3px;">
					<tbody id="bpmAgentItem">
						
						<tr>
						<tr id="firstRow" height="40px">
							<th width="30%">
								表单概率 ：
							</th>
							<td>
								<input type="text" name="mes" id="mes" height="30px"
									width="5000px" value="${gai}">
							</td>
							<td>
								<div class="panel-toolbar">


									<a class="link reload" onclick="sherfresh()" ><span></span>刷新</a>
									<input type="hidden" id="defKeysher" value="${defKeysher}" />
                                    <input type="hidden" id="defsubjectsher" value="${defsubjectsher}" />


								</div>
							</td>
						</tr>




					</tbody>
				</table>

			</form>
		</div>

		<textarea id="tableRowTemplate" style="display: none;">

<tr id="def_#defKey" type="subdata">
	<th width="30%">流程名称：</th>
			<td>
			<input type="text" id = "flowname1" name="flowname" value="#subject">			
            <input type="hidden" id="defkey1" name="flowkey"
						value="#defKey">
			<input type="hidden" name="flowId" value="#defId">
			
			</td>
		<td>
					<div class="panel-toolbar">
						<div class="toolBar">
								<div class="group">
								<a class="link flowDesign" onclick="abcd();"><span> </span>新建流程</a>
							</div>
													<div class="group">
								<a class="link add" href="javascript:;" onclick="addFlow();"><span></span>选择已有流程</a>
							</div>
													<div class="group">
								<a class="link add" href="javascript:;"
									onclick="disbang();dialog.close();"><span></span>解除绑定</a>
							</div>
						</div>
					</div>	
					</td>	
			
	</tr>
	<tr id="def_#defKey" type="subdata">
<th width="30%">详细信息：</th>
<td>
					<a href="${ctx}/platform/bpm/bpmDefinition/design.ht?defId=#defId&flagflex=3"
						target="_blank">#subject</a>
				</td>
	</tr>
	
</textarea>

		<textarea id="agentConditionTableRowTemplate" style="display: none;">
	<tr type="subdata">
		<td>
			<input type="hidden" name="condition" value="#condition">
			<input type="hidden" name="memo" value="#memo">
			<input type="hidden" name="agentid" value="#agentid">
			<input type="hidden" name="agent" value="#agent">
		</td>
	</tr>
</textarea>



	</body>
</html>
