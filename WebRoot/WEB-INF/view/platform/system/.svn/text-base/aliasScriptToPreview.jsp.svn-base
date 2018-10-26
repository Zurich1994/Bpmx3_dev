<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title>脚本预览</title>
	<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
	<script type="text/javascript">
	    
	    $(function(){
		    var scriptStr='${scriptStr}';
		    var alias= '${alias}';
		    if( alias!=null && alias!='' ){
		    	var divObj =$('#divMockParams');
		    	var html = "";
		    	var data = eval('(' + alias + ')');
				var template=$("#txtReceiveTemplate").val();
				var html=easyTemplate(template,data).toString();
				divObj.html(html);
		    }else{
		    	preView();
		    }
		    
	    	$("#btnSearch").click(preView);
		});
		
		function preView(){
			var scriptStr = $('#scriptStr').val();
			$("#resultView").html("");
			if(scriptStr.length<=1){
				$.ligerDialog.warn("脚本内容为空，不能测试！");
				return;
			}
			
			var mark = false;
			$("input[isRequired='1']",$("#tableMockParams")).each(function(){
				var valueStr = $(this).val();
				if(typeof(valueStr)==undefined||valueStr==null||valueStr==''){
					mark = true;
					name = $(this).attr("name");
					$.ligerDialog.warn("参数值（"+name+"）不能为空！");
					return false;
				}
			});
			if(mark){
				return;
			}
			
			var json =[];
			var inputs = $("input:visible",$("#tableMockParams"));
			inputs.each(function(){
				var me = $(this);
				var job = {};
				job.paraName = me.attr("name");
				job.paraType = me.attr("paraType");
				job.paraValue = me.val();
				job.isRequired = me.attr("isRequired");
				json.push(job);
			});
			json = JSON2.stringify(json);
			if(json=='[]'){
				json='';
			}
			
			var url=__ctx +"/platform/system/aliasScript/preview.ht";
			$.post(url,{scriptStr:scriptStr,alias:json},function(data){
				var json = eval("("+data+")");
				if(json.isSuccess==0){
					$("#resultView").html(data);
				}else{
					$.ligerDialog.warn(json.msg);
				}
			});

		}
		
		
	</script>
	
	<style type="text/css">
		thead th{
			text-align: left!important;
			padding-left: 5px;
		}
	</style>
</head>

<body style="overflow-x: hidden;">
	<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">脚本预览</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link search" id="btnSearch"><span></span>预览</a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link close"  href="javascript:;" onclick="window.close();"><span></span>关闭</a></div>
					</div>	
				</div>
			</div>
		</div>
		
		<div class="panel-body">	
			<table id="tabPreView"  class="table-detail">
				<tr>
				    <td>
						脚本内容：<div style="height:50px;width:650px;border: 1px solid silver;overflow: auto;"> ${scriptStr}</div>
			   		</td>
                </tr>
				<tr>
				    <td>
						<!--  <div id="divMockParams" style="overflow:auto"> </div>-->	
						<div id="divMockParams" title="参数" style="width:650px;border: 1px solid silver;overflow: auto;" ></div>
			   		</td>
                </tr>
                <tr>
				    <td>
				   		返回结果：<div id="resultView" style="height:200px;width:650px;border: 1px solid silver;overflow: auto;"><br/></div>
		   			</td>
                </tr>
                <tr>
				    <td>
				   		<div id="notice" style="height:50px;width:650px;">
						     <font color="red">
							               返回结果说明：返回的结果是以JSON字符串格式返回的内容，其分为isSuccess、msg和result三个部分；
							               其中isSuccess=0为成功返回信息，为其它值时获取信息失败；msg为信息提示的内容；
							     result为脚本执行后返回的数据内容。
						     </font>
						</div>
		   			</td>
                </tr>
		   	</table>
		</div>
	</div>
	<input type='hidden' id='scriptStr' name='scriptStr' value='${scriptStr}' />	
	<input type='hidden' id='alias' name='alias' value='${alias}' />
	
	<!-- HMTL模板拼写内容 -->
    <textarea id="txtReceiveTemplate"  style="display: none;">
  		<table id="tableMockParams" class="table-detail">
   			<thead>
   				<tr>
   					<th>参数名</th>
   					<th>参数类型</th>
   					<th>参数值</th>
   				</tr>
   			</thead>
			<#list data as obj>
				<tr>
		  			<td>\${obj.paraName}</td>
		  			<td>\${obj.paraType}</td>
		  			<td>
		  			    <input type="text"  name="\${obj.paraName}"  isRequired="\${obj.isRequired}" paraType="\${obj.paraType}" />
		  		</tr>
   			</#list>
   		</table>
    </textarea>
	
		
</body>
</html>


