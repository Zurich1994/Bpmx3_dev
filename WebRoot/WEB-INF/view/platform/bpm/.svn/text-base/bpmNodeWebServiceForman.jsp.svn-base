<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>绑定参数</title>
<%@include file="/commons/include/get.jsp"%>
<link rel="stylesheet" href="${ctx}/js/tree/zTreeStyle.css" type="text/css" />
    <script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.exhide.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.exedit.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.core.js"  ></script>
    <script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.excheck.js"  ></script>
	<script type="text/javascript" src="${ctx}/js/hotent/	CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormTableDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/ResultTableDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDialog.js"></script>
<!-- add by lzc -->
<script type="text/javascript" src="${ctx}/js/hrbeu/extension/bpm/Dialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hrbeu/platform/system/AddResourceDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hrbeu/platform/form/CommonDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormBindDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/util/DialogUtil.js" ></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<style type="text/css">
div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #e3e3e3;text-align: left;padding: 2px;}
div#rMenu ul li{

	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color:#fff;
	color: #3399FF;
}
body{
	overflow:hidden;
}
.wsTable {font-size：14px;
	border: 2px #8dc2e3 solid;
	width: 100%;
	height: 100%;
	padding-top: 4px;
	background: #ffffff;
}

.fontBold {
	font-weight: bold;
}

.inputDiv {
	float: left;
	width: 50%;
}

.outDiv {
	float: right;
	width: 50%;
}

.clear {
	clear: both;
}

.drag-span{
	font-style: italic;
}

td {
	margin: 5px;
}

ul.radio {
	
}

ul.radio li {
	margin-left: 10px;
	float: left;
}

</style>
<script type="text/javascript">
	//定义常量
	var defId = "${defId}";
	//var defId = "10000019130037";
	var nodeId = "${nodeId}";
	var actDefId = "${actDefId}";
	var defId = "${defId}";
	
	//type insert,delete,update,select 增删改查  5,6inputpager outputpager
	var type="${type}";
	//var type="add";
	var typename;
	var bpmNodeWebServiceSetId = "${setId}";
	//var bpmNodeWebServiceDocument = '${document}';
	var existDocument = '${document}';
//	var tableName="${tableName}";
	var table_Name;
//	var methodId="method1";
//	var methodId="${methodId}";
</script>
<script type="text/javascript">
	function selectTable(){
		var callBack=function(tableId,tableName){		
			$("#tableId").val(tableId);
			$("#tableName").val(tableName);
			
			table_Name=tableName;
	        loadFormx(tableId);
		}
		FormTableDialog({callBack:callBack});
	//	ResultTableDialog({callBack:callBack});
	//树状选择器  页面
	
	//	FormBindDialog({callBack:callBack});
	}
	function selectBpmForm(obj) {
		FormDialog({
			callback : function(ids, names, tableId) {
				var tdObj=$(obj).parent();
				$("input.formKey",tdObj).val(ids);
				$("input.formDefName",tdObj).val(names);
				$("#tableId").val(ids);
			    $("#tableName").val(names);
				table_Name=names;
				//tableIdsender(tableId);
				alert(ids);
				loadFormx(ids);
			}
		})
	};
	function selectdatapackage(obj){
	var callBack=function(tableId,tableName){		
			$("#tableId").val(tableId);
			$("#tableName").val(tableName);
		}
		FormTableDialogA({callBack:callBack});
	};
	function tempNodeSel(obj){
	var paramValueString = "";
	CommonDialog('ywsjmb',function(data){
	  var tdObj=$(obj).parent();
	  // alert("data:"+data.subject);
	  $("#subanning1",tdObj).val(data.subject);
	  $("#subanning2",tdObj).val(data.ID);
	  $("#subanning3",tdObj).val(data.TABLEID);
	  $("#subanning4",tdObj).val(data.FORMKEY);
	  alert("tableid:"+data.TABLEID+"id"+data.ID+"data.FORMKEY"+data.FORMKEY);
	},paramValueString)
	}
	function selectNodeForm(obj) {
		FormDialog({
			callback : function(ids, names, tableId) {
				var tdObj=$(obj).parent();
				$("input.formKey",tdObj).val(ids);
				$("input.formDefName",tdObj).val(names);
				table_Name=names; 
					
				loadFormx(ids);
					//$.ligerDialog.warn(names,'names');
					//$.ligerDialog.warn(tabledId,'tableId');
				$("input.tableId",tdObj).val(tableId);
				//给表单添加 超链接，使之能看到表单明细
				//var namesUrl="<a target='_blank' href="+__ctx+"/platform/form/bpmFormHandler/edit.ht?formDefId="+ids+" >"+names+"</a>";
				//$("span[name='spanForm']",tdObj).html(namesUrl);
				// 是否显示子表授权功能
				$.ajax({
					type : "POST",
					url : __ctx + "/platform/form/bpmFormDef/isSubTable.ht",
					data : {formKey:ids},
					dataType : "json",
					success : function(res) {
						var result= eval('(' + res + ')');
						if(result.success && obj.id == 'subNodeSel'){
							$(obj).siblings("a.grant").show();
						}else{
							$(obj).siblings("a.grant").hide();
						}
					},
					error : function(res) {
						
					}
				});
			}
		})
	}
	function resetTable(){
		$("#tableId").val('');
		$("#tableName").val('');
	};
	function getTable(tableId,tableName){
		
		var obj={tableId:tableId,tableName:tableName};
		//window.returnValue=obj;
		dialog.get('sucCall')(obj);
		
		dialog.close();
	//
	};
	
	function getDataPackageNumber(){
		$.ajax({
		type : "POST",
		url:__ctx + "/Newjsprelation/Newjsprelation/newjsprelation/getVarsTreex.ht",
		dataType : "json",
		data : {
		//子程序def id
			'Id' : formId,
			'type':type
		},
		success : function(num) {
			//var number = "";
			alert(num);
			for(var i = 0;i<num;i++){
				var basename = "inputTree";
				var baseedit = "inputTreeEdit";
				var finname = basename + num.toString();
				var finedit = baseedit + num.toString();
             }	
		},
		error : function(msg) {
			$.ligerDialog.error("出错了！","提示信息");
			return;
		}
	});
	};
	
  function getBinding(){
     var ids = "TESTPreview";
     loadFormx1(ids);
	}
</script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmNodeFormBindan.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.dragspan.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/ligerui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerResizable.js" ></script>
<script type="text/javascript" src="${ctx}/js/calendar/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/util/DialogUtil.js" ></script>
</head>
<body>
<form action="">
<input type="hidden" id="htype" value="<%=request.getParameter("operate") %>"/>
<input type="hidden" id="actDefId" value="<%=request.getParameter("actDefId") %>"/>
<input type="hidden" id="defId" value="<%=request.getParameter("defId") %>"/>
<input type="hidden" id="nodeId" value="<%=request.getParameter("nodeId") %>"/>
</form>
	<div id="webpanel" class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">绑定参数</span>
			</div>
		</div>
		<div id="webLayout" class="panel-body">
		<!--  	<div position="left" title="WebService参数"-->
			<div position="left" title="列表"
				style="overflow: hidden; float: left; width: 100%; height: 100%;">
				<div id="wsLayout" style="height: 40p; border: 1px solid #ddd;">
					<div>
					<select name="isTable" id="istable" style="width:20%;">
										<option value="1">读页面</option>
										<option value="0">写页面</option>
									</select>
						<!--  
							<select name="bindingType" style="width: 20%; height: 23px" id="stype"  >
							
							<option value="1">查询</option>
							<option value="2" selected="selected">修改</option>
							<option value="3">添加</option>
							<option value="4">删除</option>
						</select>
						-->
						<input type="text" id="tableName" class="inputText" name="tableName" value="请选择表"
						style="width: 60%; height: 23px"
						 readonly="readonly" >
							<input type="hidden" id="tableId" name="tableId" value="">
							<input id="bpmFormKey" class="formKey"  type="hidden" name="bpmFormKey" value="${bpmForm.formKey}">
									<input id="bpmFormName" class="formDefName"  type="hidden" name="bpmFormName" value="${bpmForm.formDefName}">
									<span name="spanForm"><a target="_blank" href="${ctx}/platform/form/bpmFormHandler/edit.ht?formDefId=${bpmForm.formKey}" >${bpmForm.formDefName}</a></span>
                                                  <br> 选择表单：<a href="javascript:;" class="link get" onclick="selectBpmForm(this)"></a>
                                                   <br> 选择数据包：<a href="javascript:;" class="link get" onclick="selectdatapackage(this);"></a>
                                                   <br> 选择业务数据模板：<a href="javascript:;" class="link get" onclick="tempNodeSel(this);" >
                                                     
                                                  <input type="text" style='background:none;border:0;'  name="anning" id="subanning1" value="${item.templateName}"/>
                                                  <input type="hidden" name = "anning" id ="subanning2" value="${item.templateId}"/>										
                                                  <input type="hidden" name = "anning" id ="subanning3" value="${item.tableId}"/>										
													</a>			      
					<!--	<a href='#' class='link redo' style='margin-left:10px;' onclick="resetTable()"><span>重选a</span></a>
							
						  <input type="text" id="wsdlTxt" value="请填写名称"
							style="width: 40%; height: 23px" />
							
							
							 <a class="link search"
							id="treeSearch" onclick="javascript:getTable();">查询</a>
							
							<input type="button" id="treeSearch" onclick="javascript:getTable();" value="查询w"/>
					-->
					</div>
					<div class="tree-toolbar" id="pToolbar">
						<div class="toolBar"
							style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap">
					
						</div>
					</div>
	   
				</div>
				<div id="varLayout" style="height:40%;">
					<div class="panel-toolbar" style="border: 1px #8dc2e3 solid;">
						
						<div class="group">流程变量</div>
						<!-- 
						<a class="link add2" id="add_custom" style="margin: 0 0 0 10px;"
								title="添加自定义参数" href="javascript:;">添加自定义变量</a>
						 -->
						
					</div>
					
					<ul id="varTree" class="ztree" style="height:100%;overflow: auto;"></ul>
				</div>
			</div>
<!--  		<div position="center" title="webservice设置" style="overflow: auto;">-->	
			<div position="center" title="设置" style="overflow: auto;">
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">
							<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存吗</a>
						</div>
						<div class="group">
							<a class="link close" onclick="javasrcipt:closeWin()"><span></span>关闭</a>
						</div>
						<div class="group">
							<a class="link search" onclick="getBinding()"><span></span>子图变量</a>
						</div>
					</div>
				</div>
				<div>
					<form id="bpmNodeWebServiceForm" method="post" action="save.ht">
						<div id="webservice"></div>
					</form>
				</div>
			</div>
		</div>
		
	</div>
	<!--<div style="display: none;" id="editField">  -->
	<div  id="editField">
		<!-- 方法表格 -->
		<fieldset style="margin: 5px 0px 5px 0px;" zone="method">
			<legend>
				<div class="group" style="float: none;width: 120px;">
					<span>参数绑定</span>
					<a class="link del" var="del" title="删除当前绑定"></a>
				</div>
			</legend>
			<table class="table-detail">
				<tbody>
					<tr>
						<th style="width: 15%;">表单描述</th>
						<td style="width: 35%" var="wsdl"></td>
						<th style="width: 15%;">表单ID</th>
						<td>
							<input type="text" class="inputText" var="invokeUrl" style="width: 300px;" readonly="readonly" />
							<input type="hidden" var="serviceName" />
							<input type="hidden" var="soapaction"/>
						</td>
					</tr>
					<tr>
						<th>表单名称</th>
						<td var="namespace"></td>
						<th>所属页面</th>
						<td var="method"></td>
					</tr>
					<tr>
						<th id="typeth"> </th>
						<td colspan="3">
							<div style="float: left; width: 30%;">
								<ul var="inputTree" class="ztree" style="overflow: auto;"></ul>
							</div> 
							<div style="float: right; width: 65%; padding: 5px;"
								var="inputTreeEdit"></div>
						</td>
					</tr>
					<tr>
						 <th id="typeth"> </th>
						<td colspan="3">
						
							<div style="float: left; width: 30%;">
								<ul var="inputTree2" class="ztree" style="overflow: auto;"></ul>
							</div> 
							<div style="float: right; width: 65%; padding: 5px;"
								var="inputTreeEdit2"></div>
								
						</td> 
					
					</tr>	
								
					<tr>
						<th>设置字段绑定</th>
						<td colspan="3">
							<div style="float: left; width: 30%;">
								<ul var="outputTree" class="ztree" style="overflow: auto;"></ul>
							</div>
							<div style="float: right; width: 65%; padding: 5px;"
								var="outputTreeEdit"></div>
						</td>
					</tr>					
				</tbody>
			</table>
		</fieldset>
	     <div id="custom_param_div">
							<table class="table-detail" cellpadding="0" cellspacing="0"
								border="0">
								<tr>
									<th style="width: 113px; text-align: center;">
										参数名称:
									</th>
									<td>
										<input name="name" type="text" />
										<input name="id" type="hidden" />
									</td>
								</tr>
								<tr>
									<th style="width: 113px; text-align: center;">
										参数类型:
									</th>
									<td>
										<select name="paramType">
											<option value="1">
												字符串
											</option>
											<option value="2">
												数字
											</option>
											<option value="3">
												列表
											</option>
											<option value="4">
												日期
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th style="width: 113px; text-align: center;">
										参数说明:
									</th>
									<td>
										<input type="text" name="description" />
									</td>
								</tr>
							</table>
						</div>
		<!-- 出入参编辑表格 -->
		<table class="table-detail" zone="binding">
			<tbody>
				<tr>
					<th width="20%">参数名</th>
					<td width="30%">
						<input type="hidden" var="fullpath"/>
						<span var="name"></span>
					</td>
					<th width="20%">SOAP类型</th>
					<td var="type"></td>
				</tr>
				<tr>
					<th>绑定类型</th>
					<td colspan="3">
						<select name="bindingType">
							<option value="1">固定值</option>
							<option value="2" selected="selected">流程变量</option>
							<option value="3">脚本</option>
						</select>
					</td>
				</tr>
				<tr bingdingType="1" style="display: none;">
					<th>默认值</th>
					<td colspan="3"><input type="text" class="inputText" name="defValue1" /></td>
				</tr>
				<tr bingdingType="2">
					<th>绑定变量</th>
					<td colspan="3"><span class="drag-span" name="defValue2">[请拖拽流程变量到此处]</span></td>
				</tr>
				<tr bingdingType="2">
					<th>变量JAVA类型</th>
					<td colspan="3" name="javaType"></td>
				</tr>
				<tr bingdingType="3" style="display: none;">
					<th>
						<a href="javascript:;" class="link tipinfo hidden">
							<span style="z-index: 100;text-align: left;">
								1、返回值使用returnObj表示;<br/>
								2、流程变量可以直接使用java<br/>语法进行操作，比如for循环等等。
							</span>
						</a>
						脚本
					</th>
					<td colspan="3">
						<textarea cols="50" rows="5" name="defValue3"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	
	</div>
	
</body>
</html>