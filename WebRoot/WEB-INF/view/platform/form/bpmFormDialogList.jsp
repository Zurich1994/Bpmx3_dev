
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通用表单对话框管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>

<script type="text/javascript">
	var win;
	function preview(alias){
		//向对话框传入参数
		var paramValueString = "" ;
		if(alias==null || alias==undefined){
			$.ligerDialog.warn("别名为空！",'提示信息');
			return;
		}
		var isPreviewCallCode = false ;
		if(alias.indexOf("callCode-")==0){
			isPreviewCallCode = true;
			alias = alias.replace("callCode-","");
		}
		
		var url=__ctx + "/platform/form/bpmFormDialog/dialogObj.ht?alias=" +alias;
		url=url.getNewUrl();
		var isParamsNeeded = false ;
		$.ajax({  
			type : "post",  
			url  : url,  
			data : {"alias":alias},   
			async : false,  
			success : function(data){
			if(data.success==0){
				$.ligerDialog.warn("输入别名不正确！",'提示信息');
				isParamsNeeded = true ;
				return;
			}
			var obj=data.bpmFormDialog;
			var fieldObj=eval("("+obj.conditionfield.trim()+")") ;
			var paramArr = [] ;
			var urlForParams = __ctx + "/platform/form/bpmFormDialog/params.ht?alias="+alias;
			for(var i=0,c;c=fieldObj[i++];){
				//4：动态传入参数，5：java脚本参数
				if(c.defaultType=="4"){
					paramArr.push(c.field) ;
					isParamsNeeded = true ;
				}else if(c.defaultType=="5" && c.dbType=='isAfferent'){
					paramArr.push(c.field) ;
					isParamsNeeded = true ;
				}
			}
			//需要传入参数
			urlForParams += "&params="+JSON2.stringify(paramArr)+"&isPreviewCallCode="+isPreviewCallCode;
			if(isParamsNeeded){
				if(isPreviewCallCode){
					urlForParams += "&resultfield="+obj.resultfield.trim() ;
					$.ligerDialog.open({ url:urlForParams, height: obj.height,width: obj.width,title:'对话框调用说明',name:'paramDialog_'});
				}else{
				//若取消，返回null
				DialogUtil.open({ url:urlForParams, height: obj.height,width: obj.width,title:'对话框参数传入',name:'paramDialog_',
					buttons: [ { text: '确定', onclick: function (item, dialog) { 
						var contents=$("iframe",dialog.dialog).contents()
						var result= ""; 
						var paramNames = contents.find('[name="paramName"]') ;
	    				for(var i=0;i<paramNames.length;i++){
							if($.trim($(paramNames[i]).val())!=''){
								result += $(paramNames[i]).val() + "=" + $(paramNames[i]).closest('td').next('td').find('[name="paramValue"]').val() + "&" ;
							}
						}
						paramValueString = result.substring(0,result.length-1);
						CommonDialog(alias,function(data){
							var json=JSON2.stringify(data);
							$("#txtJsonData").val(json);
							if(!win){
								var obj=$("#divJsonData");
								win= $.ligerDialog.open({ target:obj , height: 300,width:500, modal :true}); 
							}
							win.show();
						},paramValueString);
						dialog.close();
					} }, 
					{ text: '取消', onclick: function (item, dialog) { dialog.close(); } } ] });
				
				}
			}else if(isPreviewCallCode){
				urlForParams += "&resultfield="+obj.resultfield.trim() ;
				$.ligerDialog.open({ url:urlForParams, height: obj.height,width: obj.width,title:'对话框调用代码预览',name:'paramDialog_'});
			}
			}
		});
		if(isParamsNeeded || isPreviewCallCode) return ;
		CommonDialog(alias,function(data){
			var json=JSON2.stringify(data);
			$("#txtJsonData").val(json);
			if(!win){
				var obj=$("#divJsonData");
				win= $.ligerDialog.open({ target:obj , height: 300,width:500, modal :true}); 
			}
			win.show();
		},paramValueString);
	}
</script>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">通用表单对话框管理列表</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><f:a alias="delFormDialog" action="del.ht" css="link del" showNoRight="false" >删除</f:a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link category" href="${ctx}/platform/form/bpmFormDialogCombinate/list.ht"><span></span>组合对话框</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link category" href="${ctx}/platform/form/formDefCombinate/list.ht"><span></span>表单组合</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>							
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
											<li><span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/></li>
											<li><span class="label">别名:</span><input type="text" name="Q_alias_SL"  class="inputText" value="${param['Q_alias_SL']}"/></li>
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
					
					
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="bpmFormDialogList" id="bpmFormDialogItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="id" value="${bpmFormDialogItem.id}">
							</display:column>
							<display:column property="name" title="名称" sortable="true" sortName="name"></display:column>
							<display:column property="alias" title="别名" sortable="true" sortName="alias"></display:column>
							<display:column  title="显示样式" sortable="true" sortName="style">
							
								<c:choose>
									<c:when test="${bpmFormDialogItem.style==0}">
										<span class="green">列表</span>
									</c:when>
									<c:otherwise>
										<span class="red">树形</span>
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column  title="是否单选" sortable="true" sortName="issingle">
								<c:choose>
									<c:when test="${bpmFormDialogItem.issingle==0}">
										<span class="red">多选</span>
									</c:when>
									<c:otherwise>
										<span class="green">单选</span>
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="width" title="宽度" sortable="true" sortName="width"></display:column>
							<display:column property="height" title="高度" sortable="true" sortName="height"></display:column>
							<display:column  title="是否为表" sortable="true" sortName="istable">
								<c:choose>
									<c:when test="${bpmFormDialogItem.istable==0}">
										<span class="red">视图</span>
									</c:when>
									<c:otherwise>
										<span class="green">数据库表</span>
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="objname" title="对象名称" sortable="true" sortName="objname"></display:column>
							<display:column property="dsalias" title="数据源名称" sortable="true" sortName="dsalias"></display:column>
							<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
								<f:a alias="delFormDialog" href="del.ht?id=${bpmFormDialogItem.id}" css="link del">删除</f:a>
								<a href="edit.ht?id=${bpmFormDialogItem.id}" class="link edit">编辑</a>
								<a href="javascript:preview('${bpmFormDialogItem.alias}')"  class="link detail">预览</a>
								<a href="javascript:preview('callCode-${bpmFormDialogItem.alias}')"  class="link copyTo">开发帮助</a>
							</display:column>
						</display:table>
						<hotent:paging tableId="bpmFormDialogItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
			
			<div id="divJsonData" style="display: none;">
				<div>对话框返回的JSON格式数据。</div>
				<ul>
					<li >1.单选为JSON对象数据,字段为return字段。</li>
					<li>2.多选为JSON数组,字段为return字段。</li>
				</ul>
				<textarea id="txtJsonData" rows="10" cols="80" style="height: 180px;width:480px"></textarea>
			</div>
</body>
</html>


