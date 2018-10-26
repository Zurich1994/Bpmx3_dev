<%@page import="com.hotent.platform.model.system.GlobalType"%>
<%@page pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html"%>
<html>
	<head>
		<title>选择流程表单</title>
		<%@include file="/commons/include/form.jsp" %>
		<f:link href="tree/zTreeStyle.css"></f:link>
		<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js" ></script>
	    <script type="text/javascript"	src="${ctx}/js/tree/jquery.ztree.js"></script>
	    <script type="text/javascript" src="${ctx}/js/hotent/platform/form/GlobalType.js"></script>
	    <script type="text/javascript">
		    /*KILLDIALOG*/
		    var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	   		
		    var catKey="<%=GlobalType.CAT_FORM%>";
			var globalType=new GlobalType(catKey,"glTypeTree",{onClick:treeClick,expandByDepth:2});
			
			$(function(){
				$("#defLayout").ligerLayout({ leftWidth: 200,height: '90%',
						bottomHeight :40,
					 	allowBottomResize:false});
			
				globalType.loadGlobalTree();
				var condition = dialog.get('condition')||"";
				$("#formFrame").attr("src","selector.ht"+condition);
			});
			
			//展开收起
			function treeExpandAll(type){
				globalType.treeExpandAll(type);
			};
			
			function treeClick( treeNode){
				if(treeNode.isRoot==undefined){
					var categroyId=treeNode.typeId;
				
					var url="selector.ht?categoryId="+categroyId;
	        		$("#formFrame").attr("src",url);
				}
        	}
			
			function selectForm(){
				var formName=[];
				var formKey=[];
				var tableId=[];
				$('#formFrame').contents().find(":radio[name='formKey']:checked,:checkbox[name='formKey']:checked").each(function(){
					formName.push($(this).siblings("input[name='subject']").val());
					formKey.push($(this).val());
					tableId.push($(this).siblings("input[name='tableId']").val());
				});
				if(formKey==""){
					$.ligerDialog.warn("请选择表单ID!","提示信息");
					return "";
				}
				
				//window.returnValue={formKey:formKey,formName:formName,tableId:tableId};
				dialog.get('sucCall')({formKey:formKey.join(","),formName:formName.join(","),tableId:tableId.join(",")});
				dialog.close();
			}
			
		</script>
		<style type="text/css">
			body{overflow: hidden;}
			div.bottom{text-align: center;}
			div.bottom input {width:65px;margin: 8px 10px;font-size: 14px;height: 23px;}
		</style>
	</head>
	<body>
			<div id="defLayout" >
	            <div position="left" title="表单分类" style="overflow: auto;float:left;width:103%;height:94%;">
					<ul id="glTypeTree" class="ztree"></ul>
	            </div>
	            <div position="center"  title="表单">
	          		<iframe id="formFrame" name="formFrame" height="100%" width="100%" frameborder="0" ></iframe>
	            </div>  
       	  </div>
       	 <div position="bottom"  class="bottom" style='margin-top:10px;'>
			  <a href='#' class='button'  onclick="selectForm()" ><span class="icon ok"></span><span >选择</span></a>
			  <a href='#' class='button' style='margin-left:10px;' onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
		</div>
	
	</body>
</html>