<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>分类排序</title>
	
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/util/SelectOption.js"></script>
	<script type="text/javascript">
	$(function(){
		/*KILLDIALOG*/
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		
		//格子
		//for(var i=1;i<=10;i++)$("#sel_cell").append("<option value='"+i+"'>"+i+"</option>");
		//顶部、向上、向下、底部
		var obj=document.getElementById('dicIds');
		$("#btn_top").click(function(){
			
			__SelectOption__.moveTop(obj);
		});
		$("#btn_up").click(function(){
			__SelectOption__.moveUp(obj, 1);
		});
		$("#btn_down").click(function(){
			__SelectOption__.moveDown(obj, 1);
		});
		$("#btn_bottom").click(function(){
			__SelectOption__.moveBottom(obj);
		});
	
		
		
		$("a.save").click(function() {
			var obj=document.getElementById('dicIds');
			var dicIds = "";
			for(var i=0;i<obj.length;i++){
				dicIds+=obj[i].value+",";
			}
			if(dicIds.length>1){
				dicIds=dicIds.substring(0,dicIds.length-1);
				var url="${ctx}/platform/system/dictionary/sort.ht";
				var params={"dicIds":dicIds};
	 			$.post(url,params,function(result){
	 				var obj=new com.hotent.form.ResultMessage(result);
	 				if(obj.isSuccess()){//成功
	 					$.ligerDialog.success("字典排序完成!",'提示信息',function(){
	 						dialog.close();});
	 					//var conf=window.dialogArguments;
	 					var conf=dialog.get("conf");
	 					
	 					if(conf.callBack){
	 						conf.callBack();
	 					}
	 				}
	 				else{
	 					$.ligerDialog.err('出错信息',"排序分类失败",obj.getMessage());
	 				}
	 			});
			}
		});
	});
	
	
	</script>
</head>
<body>
<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">分类排序</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" id="dataFormSave" onclick="dialog.get('sucCall')();" href="javascript:;"><span></span>保存</a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link close"  href="javascript:;" onclick="dialog.close();"><span></span>关闭</a></div>
					</div>
				</div>
		</div>
	<form id="dataForm" method="post" action="sort.ht">
		<div class="panel-data">
			<table class="table-detail"  border="0" cellspacing="0" cellpadding="0" >
			
				<tr>
					<td class="form_title" align="center" >
						<select id="dicIds" name="dicIds" size="12" style="width:100%;" multiple="multiple">
							<c:forEach items="${dictionaryList}" var="d">
								<option value="${d.dicId }">${d.itemName }</option>
							</c:forEach>
						</select>
					</td>
					<td class="form_title" style="text-align:left;width:80px">
						<input type="button" id="btn_top" value="顶部" /><br/>
						<input type="button" id="btn_up" value="向上" /><br/>
						<input type="button" id="btn_down" value="向下" /><br/>
						<input type="button" id="btn_bottom" value="底部" /><br/>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
</body>
</html>
