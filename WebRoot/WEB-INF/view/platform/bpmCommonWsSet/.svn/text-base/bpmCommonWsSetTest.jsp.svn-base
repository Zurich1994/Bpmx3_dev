<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>通用webservice调用设置测试</title>
<%@include file="/commons/include/get.jsp"%>
<style type="text/css">
	.block{
		display:block;
		margin:10px 0;
	}
</style>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js" ></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript">
	var doc = '${bpmCommonWsSet.document}',
		win;
	$(function(){
		if(doc){
			var docObj = $.parseJSON(doc);
			$("span[name='url']").text(docObj.url);
			$("span[name='method']").text(docObj.method);
			initInputs(docObj.inputs);
		}
		
		$("a.close").click(function(){
			frameElement.dialog.close();
		});
		
		$("a.done").click(doExecute);
	});
	
	function doExecute(){
		$("#show_result_p").text('');
		$.ligerDialog.waitting('正在调用,请稍候...');
		var url = __ctx+'/platform/bpmCommonWsSet/bpmCommonWsSet/doExecute.ht',
			parent = $("#param_table"),
			setId = $("input[name='setId']",parent).val(),
			json = [];
		
		$("tbody > tr",parent).each(function(){
			var me = $(this),
				bindingVal = $("span[name='bindingVal']",me).text(),
				javaType = $("input[name='javaType']",me).val(),
				testValObj = $("[name='testVal']",me);
			
			var	testVal = testValObj.val();
			if(testValObj.length > 1){
				testVal = [];
				testValObj.each(function(){
					testVal.push($(this).val());
				});
			}
			
			json.push({bindingVal:bindingVal,javaType:javaType,testVal:testVal});
		});
		
		json = JSON2.stringify(json);
		
		$.post(url,{setId:setId,json:json},function(d){
			$.ligerDialog.closeWaitting();
			var data = $.parseJSON(d);
			if(data.result){
				$("#show_result_p").text(data.message);
				if(!win){
					var obj=$("#show_result_div");
					win= $.ligerDialog.open({ target:obj , height: 300,width:500, modal :true}); 
				}
				win.show();
			}
			else{
				$.ligerDialog.error("调用出错了，出错的详细信息如下:\n"+data.message);
			}
		});
	};
	
	function initInputs(list){
		if(!list||list.length==0)return;
		var ary = [];
		for(var i=0,c;c=list[i++];){
			if(c.bindingType=="2"){
				ary.push(c);
			}
		}
		
		var template = $("#param_tr").val();
			
		var html = easyTemplate(template,ary).toString();
		$("#param_table").append($(html));
	};
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">通用webservice调用设置测试</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link done" href="###"><span></span>调用</a>
					</div>
					<div class="group">
						<a class="link close" href="###"><span></span>关闭</a>
					</div>
				</div>
			</div>
		</div>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">别名:</th>
				<td>${bpmCommonWsSet.alias}</td>
			</tr>
			<tr>
				<th width="20%">wsdl地址:</th>
				<td>${bpmCommonWsSet.wsdlUrl}</td>
			</tr>
			<tr>
				<th width="20%">调用方法:</th>
				<td>
					<span name="method"></span>
				</td>
			</tr>
			<tr>
				<th width="20%">调用地址:</th>
				<td>
					<span name="url"></span>
				</td>
			</tr>
		</table>
		<table id="param_table" class="table-grid table-list" cellpadding="1" cellspacing="1">
			<thead>
				<tr>
					<input type="hidden" name="setId" value="${bpmCommonWsSet.id}"/>
					<td colspan="3" style="text-align: center">调用参数设置</td>
				</tr>
				<tr>
					<th>参数名</th>
					<th>参数类型</th>
					<th>参数值</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		</div>
		<div style="display:none;">
			<textarea id="param_tr">
				<#list data as obj>
					<tr>
						<td style="text-align: center">
						<input type="hidden" name="javaType" value="\${obj.javaType}"/>
						<span name="bindingVal">\${obj.bindingVal}</span>
						</td>
						<#if (obj.javaType == 1)>
						<td style="text-align: center">字符串</td>
						<td style="text-align: center">
							<input type="text" name="testVal"/>
						</td>
						<#elseif (obj.javaType == 2)>
						<td style="text-align: center">数字</td>
						<td style="text-align: center">
							<input type="text" name="testVal"/>
						</td>
						<#elseif (obj.javaType == 3)>
						<td style="text-align: center">列表</td>
						<td style="padding:0 50px;">
							<span class="block"><input type="text" name="testVal"/></span>
							<span class="block"><input type="text" name="testVal"/></span>
							<span class="block"><input type="text" name="testVal"/></span>
						</td>
						<#else>
						<td style="text-align: center">日期</td>
						<td style="text-align: center">
							<input type="text" name="testVal" class="inputText date"/>
						</td>
						</#if>
					</tr>
				</#list>
			</textarea>			
			<div id="show_result_div">
				<p id="show_result_p"></p>
			</div>
		</div>
	</div>
</body>
</html>

