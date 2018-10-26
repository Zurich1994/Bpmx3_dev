<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>编辑 流程WebService节点</title>
<%@include file="/commons/include/get.jsp"%>
<link rel="stylesheet" href="${ctx}/js/tree/zTreeStyle.css" type="text/css" />
<style type="text/css">
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
	var bpmNodeWebServiceSetId = "${bpmCommonWsSet.id}";
	var bpmNodeWebServiceDocument = '${bpmCommonWsSet.document}';
</script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmCommonWsSet.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.dragspan.js"></script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">编辑流程WebService节点</span>
			</div>
		</div>
		<div id="webLayout" class="panel-body">
			<div position="left" title="WebService参数"
				style="overflow: hidden; float: left; width: 100%; height: 100%;">
				<div id="wsLayout" style="height: 48%; border: 1px solid #ddd;">
					<div>
						<input type="text" id="wsdlTxt" value="${bpmCommonWsSet.wsdlUrl}" 
							   style="width: 75%; height: 23px" />
						<a class="link search" id="treeSearch" onclick="javascript:getByWsdlUrl();">查询</a>
					</div>
					<div class="tree-toolbar" id="pToolbar">
						<div class="toolBar"
							style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap">
							<div class="group">
								<a class="link reload" id="treeReFresh">刷新</a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link expand" id="treeExpand">展开</a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link collapse" id="treeCollapse">收起</a>
							</div>
						</div>
					</div>
					<ul id="wsTree" class="ztree" style="overflow: auto;"></ul>
				</div>
				<div id="varLayout" style="height:140%;">
					<div style="border: 1px #8dc2e3 solid;height:35px;line-height:35px;padding:0 8px;background:#ebebeb;">
						<div class="group">自定义参数</div>
						<a class="link add2" id="add_custom" style="margin:0 0 0 10px;" title="添加自定义参数" href="javascript:;">添加</a>
					</div>
					<ul id="varTree" class="ztree" style="height:100%;overflow: auto;"></ul>
				</div>
			</div>
			<div position="center" title="webservice设置" style="overflow: auto;">
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">
							<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
						</div>
						<div class="group">
							<a class="link back" href="list.ht"><span></span>返回</a>
						</div>
					</div>
				</div>
				<div>
					<form id="bpmNodeWebServiceForm" method="post" action="save.ht">
						<div id="webservice" style="padding:10px 0 0 0;">
							<table class="table-detail" zone="method">
								<tbody>
									<tr>
										<th style="width: 15%;">命名空间</th>
										<td style="width: 35%;" var="namespace"></td>
										<th style="width: 15%;">调用方法</th>
										<td style="width: 35%;" var="method"></td>
									</tr>
									<tr>
										<th>WS调用别名</th>
										<td colspan="3">
											<input type="text" name="alias" value="${bpmCommonWsSet.alias}"/>
										</td>
									</tr>
									<tr>
										<th >WSDL地址</th>
										<td var="wsdl"  colspan="3"></td>
									</tr>
									<tr>
										<th style="width: 15%;">调用地址</th>
										<td colspan="3">
											<div style="padding:0 10px 0 0;">
												<input type="text" class="inputText" var="invokeUrl" style="width:100%;" />
												<input type="hidden" var="serviceName" />
												<input type="hidden" var="soapaction"/>
											</div>
										</td>
									</tr>
									<tr>
										<th>参数绑定</th>
										<td colspan="3">
											<div style="float: left; width: 30%;">
												<ul var="inputTree" class="ztree" style="overflow: auto;"></ul>
											</div>
											<div style="float: right; width: 65%; padding: 5px;"
												var="inputTreeEdit"></div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div style="display: none;" id="editField">
		<div id="custom_param_div">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th style="width:113px;text-align:center;">参数名称:</th>
					<td>
						<input name="name" type="text"/>
						<input name="id" type="hidden" />
					</td>
				</tr>
				<tr>
					<th style="width:113px;text-align:center;">参数类型:</th>
					<td>
						<select name="paramType">
							<option value="1">字符串</option>
							<option value="2">数字</option>
							<option value="3">列表</option>
							<option value="4">日期</option>
						</select>
					</td>
				</tr>
				<tr>
					<th style="width:113px;text-align:center;">参数说明:</th>
					<td>
						<input type="text" name="description"/>
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
					<th>变量类型</th>
					<td colspan="3" name="javaType"></td>
				</tr>
				<tr bingdingType="3" style="display: none;">
					<th>
						<a href="javascript:;" class="link tipinfo hidden">
							<span style="z-index: 100;text-align: left;">
								1、返回值使用returnObj表示;
								2、流程变量可以直接使用java语法进行操作，比如for循环等等。
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