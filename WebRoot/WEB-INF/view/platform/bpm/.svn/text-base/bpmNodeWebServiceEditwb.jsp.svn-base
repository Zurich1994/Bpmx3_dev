<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.sun.faces.renderkit.html_basic.HtmlBasicRenderer.Param"%>
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
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/util/DialogUtil.js" ></script>
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
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js" ></script>
<script type="text/javascript">
	//定义常量
	//var defId = "${defId}";
	
	var defId = "${defId}";
	var nodeId = "${nodeId}";
	var actDefId = "${actDefId}";
	var type="${operate}";
	var bpmNodeWebServiceSetId = "${setId}";
	//var bpmNodeWebServiceDocument = '${document}';
	var existDocument = "${document}";
//	var tableName="${tableName}";
	var tableName;
//	var methodId="method1";
//	var methodId="${methodId}";

	function selectTable(){
		var callBack=function(tableId,tableName){		
			$("#tableId").val(tableId);
			$("#tableName").val(tableName);
		}
		//FormTableDialog({callBack:callBack});
		ResultTableDialog({callBack:callBack});
		//加选择器 完美duihuakua	
	}
	function openQuotaDialog() {
	
		CommonDialog("sel_db_fun_table",function(data){
			var param = "";
				param = param + data.TABLENAME;
		alert(param);
				$("#tableName").val(param);
			
			
			
		},"");						
	}
	function resetTable(){
		$("#tableId").val('');
		$("#tableName").val('');
	};
	
	
</script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormTableDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/ResultTableDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmNodeWebServiceEditwb.js"></script>
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
				<div id="wsLayout" style="height: 30%; border: 1px solid #ddd;">
					<div>
					<select name="isTable" id="istable" style="width:10%;">
										<option value="1">表</option>
										<option value="0">视图</option>
									</select>
						<!--  
							<select name="bindingType" style="width: 20%; height: 23px" id="stype"  >
							
							<option value="1">查询</option>
							<option value="2" selected="selected">修改</option>
							<option value="3">添加</option>
							<option value="4">删除</option>
						</select>
						-->
						<!--  
						<input type="text" id="wsdlTxt" value="请填写名称"
							style="width: 40%; height: 23px" />
							 <a class="link search"
							id="treeSearch" onclick="javascript:getByWsdlUrl();">查询</a>
					-->
					<input type="text" id="tableName" class="inputText" name="tableName" value="" >
							 <a class="link search"	id="treeSearch" href="javascript:openQuotaDialog()">查询</a>
							
							<a href='#' class='link redo' style='margin-left:10px;' onclick="resetTable()"><span>重选</span></a>
					
					<input type="button" id="treeSearch" onclick="javascript:getTable();" value="查询"/>
					</div>
					<div class="tree-toolbar" id="pToolbar">
						<div class="toolBar"
							style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap">
							<!--  
							<div class="group">
								<a class="link reload" id="treeReFresh">刷新</a>
							</div>
							
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link expand" id="treeExpand">表</a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link collapse" id="treeCollapse">视图</a>
							</div>
							-->
						</div>
					</div>
	   <div id="rMenu">
	  
	   <ul>
		<li id="m_detail" onclick="detailFun();">查看详细</li>
		<!--  
		<li id="m_del" onclick="removeTreeNode();">删除节点</li>
		<li id="m_check" onclick="checkTreeNode(true);">Check节点</li>
		<li id="m_unCheck" onclick="checkTreeNode(false);">unCheck节点</li>
		<li id="m_reset" onclick="resetTree();">恢复zTree</li>
		-->
	</ul>
</div>
					<ul id="wsTree" class="ztree" style="overflow: auto;"></ul>
				</div>
				<div id="varLayout" style="height:80%;">
					<div class="panel-toolbar" style="border: 1px #8dc2e3 solid;">
						
						<div class="group">流程变量</div>
						<!-- 
						<a class="link add2" id="add_custom" style="margin: 0 0 0 10px;"
								title="添加自定义参数" href="javascript:;">添加自定义变量</a>
						 -->
						
					</div>
					
					<ul id="varTree" class="ztree" style="height:auto;overflow: auto;"></ul>
				</div>
			</div>
<!--  		<div position="center" title="webservice设置" style="overflow: auto;">-->	
			<div position="center" title="设置" style="overflow: auto;">
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">
							<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
						</div>
						<div class="group">
							<a class="link close" onclick="javasrcipt:closeWin()"><span></span>关闭</a>
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
					<span>字段绑定</span>
					<a class="link del" var="del" title="删除当前绑定"></a>
				</div>
			</legend>
			<table class="table-detail">
				<tbody>
					<tr>
						<th style="width: 15%;">方法名称</th>
						<td style="width: 35%" var="wsdl"></td>
						<th style="width: 15%;">方法别名</th>
						<td>
							<input type="text" class="inputText" var="invokeUrl" style="width: 300px;" />
							<input type="hidden" var="serviceName" />
							<input type="hidden" var="soapaction"/>
						</td>
					</tr>
					<tr>
						<th>所属表</th>
						<td var="namespace"></td>
						<th>备注</th>
						<td var="method"></td>
					</tr>
					<tr>
						<th>条件字段绑定</th>
						<td colspan="3">
							<div style="float: left; width: 30%;">
								<ul var="inputTree" class="ztree" style="overflow: auto;"></ul>
							</div>
							<div style="float: right; width: 65%; padding: 5px;"
								var="inputTreeEdit">
							</div>							
						</td>
					</tr>
					<tr>
					   <th></th>
						<td colspan="3">
							<div style="float: left; width: 30%;">
								<ul var="inputTree1" class="ztree" style="overflow: auto;"></ul>
							</div>
							<div style="float: right; width: 65%; padding: 5px;"
								var="inputTreeEdit1">
							</div>							
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
					<tr>
					 <th></th>
						<td colspan="3">
							<div style="float: left; width: 30%;">
								<ul var="outputTree1" class="ztree" style="overflow: auto;"></ul>
							</div>
							<div style="float: right; width: 65%; padding: 5px;"
								var="outputTreeEdit1"></div>
						</td>
						
					</tr>
					<tr id="resulttr" >
						<th>结果字段绑定</th>
						<td colspan="3">
							<div style="float: left; width: 30%;">
								<ul var="resultTree" class="ztree" style="overflow: auto;"></ul>
							</div>
							<div style="float: right; width: 65%; padding: 5px;"
								var="resultTreeEdit">
							</div>
						</td>
					</tr>
					<tr id="resulttr" >
						<th></th>
						<td colspan="3">
							<div style="float: left; width: 30%;">
								<ul var="resultTree1" class="ztree" style="overflow: auto;"></ul>
							</div>
							<div style="float: right; width: 65%; padding: 5px;"
								var="resultTreeEdit1">
							</div>
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
		<form action="">
		<input type="hidden"  name="id" id="id" value="${dbFunc.id}"/>
		
		<input type="hidden" id="settingobj" value="${dbFunc.objname}" />
		<textarea id="displayField" name="displayField" style="display: none;">
				${dbFunc.displayField}
		</textarea>
		<textarea  id="conditionField"  name="conditionField" style="display: none;">
				${dbFunc.conditionField}
		</textarea>
		<textarea  id="resultField"  name="resultField" style="display: none;" >
				${dbFunc.resultField}
		</textarea>
		<textarea  id="sortField"  name="sortField" style="display: none;" >
				${dbFunc.sortField}
		</textarea>
		<textarea  id="settingField"  name="settingField" style="display: none;">
				${dbFunc.settingField}
		</textarea>
		</form>
	</div>
	
</body>
</html>