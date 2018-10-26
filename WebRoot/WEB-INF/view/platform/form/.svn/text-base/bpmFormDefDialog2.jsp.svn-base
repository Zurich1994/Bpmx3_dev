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
	    <script type="text/javascript"><!--
		    /*KILLDIALOG*/
		    var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	   		
		    var catKey="<%=GlobalType.CAT_FORM%>";
			var globalType=new GlobalType(catKey,"glTypeTree",{onClick:treeClick,expandByDepth:2});
			
			$(function(){
				$("#defLayout").ligerLayout({ leftWidth: 200,height: '90%',
						bottomHeight :40,
					 	allowBottomResize:false});
			
				globalType.loadGlobalTree();
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
				var formName="";
				var formKey="";
				var tableId="";
				$('#formFrame').contents().find(":radio[name='formKey']:checked").each(function(){
					formName=$(this).siblings("input[name='subject']").val();
					formKey=$(this).val();
					tableId=$(this).siblings("input[name='tableId']").val();
				});
				if(formKey==""){
					$.ligerDialog.warn("请选择表单ID!","提示信息");
					return "";
				}
				
				var text;
				//window.returnValue={formKey:formKey,formName:formName,tableId:tableId};
				//dialog.get('sucCall')({formKey:formKey,formName:formName,tableId:tableId});
				$.ajax({
					type : "POST",
					url : __ctx + "/platform/form/bpmFormDef/getFormName.ht",
					async : false,
					data : {formKey:formKey},
					dataType : "json",
					success : function(res) {
						text = eval("("+res+")").result;
					}
				});
				dialog.get('returnValue')({text:text,key:formKey});
				dialog.close();
			}
				
				
				/*window.returnValue={formKey:formKey,formName:formName,tableId:tableId};
				dialog.get('sucCall')({formKey:formKey,formName:formName,tableId:tableId});
				dialog.close();
			}*/
		
			//史欣慧 选择页面元素
		/*	function dialog2(){
				var id="";
				$('#formFrame').contents().find(":radio[name='formKey']:checked").each(function(){
						//formName=$(this).siblings("input[name='subject']").val();
						id=$(this).val();
						//tableId=$(this).siblings("input[name='tableId']").val();
					});
				if(id==""){
					$.ligerDialog.warn("请选择表单ID!","提示信息");
					return "";
				}
				showSettingDialog2(id);
		}
		
		function showSettingDialog2(id){
			var url=__ctx+"/platform/bpm/bpmFormQuery/settingElement.ht?id=" + id;
			url=url.getNewUrl();
			alert(url);
			DialogUtil.open({
                height:600,
                width: 800,
                title : '设置列',
                url: url, 
                isResize: true,
                //自定义参数                
                sucCall:function(rtn){
                	 $("#settingobj").val(objectname);
               		 $("#conditionfield").val(rtn[1]);
               		 $("#resultfield").val(rtn[2]); 
               		 $("#sortfield").val(rtn[3]); 
               		 $("#name").focus();
                }
            });
		}*/
		
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
	          		<iframe id="formFrame" name="formFrame" height="100%" width="100%" frameborder="0"  src="selector.ht"></iframe>
	            </div>  
       	  </div>
       	 <div position="bottom"  class="bottom" style='margin-top:10px;'>
			  <a href='#' class='button' style='margin-left:10px;'  onclick="selectForm()" ><span class="icon ok"></span><span >选择2</span></a>
			 <!-- <a href='#' class='button' style='margin-left:10px;'  onclick="dialog2()" ><span class="icon ok"></span><span >显示页面元素</span></a> -->
			 <a href='#' class='button' style='margin-left:10px;' onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
		</div>
	
	</body>
</html>