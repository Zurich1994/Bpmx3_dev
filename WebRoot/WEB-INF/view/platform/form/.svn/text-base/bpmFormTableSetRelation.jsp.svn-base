<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>表单验证规则明细</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/util/tableHeadFix.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/Relation.js"></script>
	<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
		var pkField="${pkField}";
		var tablename="${tableName}";
		var dataSource="${dataSource}";
		var tdTemplate="<tr style='cursor:pointer'><td style='width:135px;'>#tbname</td><td style='width:135px;'>#comment</td></tr>";
	
		var relationObj=new com.hotent.platform.form.Relation();
		
		$(function(){
		
			$("#selTables").change(changeTable);
			$("#tbTableList").chromatable({height:"200px",width:"270px"});
			$("#tbDestTableList").chromatable({height:"200px",width:"270px"});
			if(pkField==""){
				$("#tbTableList tr").click(handClick);
			}
			else{
				$("#tbTableList tr[fieldName='"+pkField+"']").addClass("over");
			}
			
			$("#btnAddRelation").click(addRelation);
			initRelation();
			
			$("#dataFormSave").click(saveRelation);
		});
		
		var divTemplte="<span class='spanRelation'><span class='relation'>#relation</span><a href='#' onclick='removeRelation(this)'>删除</a></span>";
	
		//添加关联
		function addRelation(){
			var trSource=$("#tbTableList tr.over");
			var trDest=$("#tbDestTableList tr.over");
			if(trSource.length==0){
				$.ligerDialog.warn("请主表主键!");
				return;
			}
			if(trDest.length==0){
				$.ligerDialog.warn("请子表外键!");
				return;
			}
			var primaryKey=$("td:first",trSource).text();
			var foreignKey=$("td:first",trDest).text();
			var tableName=$("#selTables").val();
			
			var rtn=relationObj.addRelation(primaryKey, tableName, foreignKey);
			if(rtn==1){
				$.ligerDialog.warn(tableName + ",外键已经定义!");
				return ;
			}
			else if(rtn==2){
				$.ligerDialog.warn("主键已经定义为:" + relationObj.pk +",如果需要修改主键，请先删除所有的关联关系!");
				return ;
			}
			
			var divHtml=divTemplte.replaceAll("#relation",tableName +"(从表)," + foreignKey);
			$("#relationContainer").append(divHtml);
		}
		
		function removeRelation(obj){
			var tmp=$(obj).parent();
			var tbName=tmp.attr("tbName");
			relationObj.remove(tbName);
			tmp.remove();
		}
		
		function handClick(){
			var obj=$(this);
			obj.addClass("over").siblings().removeClass("over");
		}
		
		function changeTable(){
			
			var tabName=$(this).val();
			if(tabName=="") return;
			var url="getByTable.ht".getNewUrl();
			var tableObj=$("#tbDestTableList");
			$.post(url,{dataSource:dataSource,tableName:tabName},function(data){
				$("#tbDestTableList tr:gt(0)").remove(); 
				for(var i=0;i<data.length;i++){
					var tr=tdTemplate.replace("#tbname",data[i].name).replace("#comment",data[i].comment);
					tableObj.append(tr);
				}
				$("#tbDestTableList tr").click(handClick);
			});
		}
		
		function saveRelation(){
			
			var str=relationObj.getRelation();
			var url="saveRelation.ht";
			$.post(url,{relation:str,tablename:tablename,dataSource:dataSource},function(data){
				
				var obj=new com.hotent.form.ResultMessage(data);
				if(obj.isSuccess()){//成功
					$.ligerDialog.success('保存关联关系成功!','提示信息',function(){
						dialog.get('sucCall')();
						dialog.close();
					});
				}
				else{
					$.ligerDialog.err('出错信息',"保存关联关系失败",obj.getMessage());
				}
			});
		}
		
		function initRelation(){
			var objContainer=$("#relationContainer .spanRelation");
			objContainer.each(function(i){
				var obj=$(this);
				var pk=obj.attr("pk");
				var fk=obj.attr("fk");
				var tbName=obj.attr("tbName");
				relationObj.addRelation(pk, tbName, fk);
			});
		}
</script>
<style type="text/css">
	.relation{margin-right: 5px;}
	.spanRelation{margin-left: 5px;}
</style>
</head>
<body>
<form id="relationForm" method="post" action="save.ht">
	<div class="panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">设置外部表关联关系</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link del" href="javascript:;" onclick="dialog.get('sucCall')();dialog.close();" ><span></span>关闭</a></div>
					</div>
				</div>
				<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th style="text-align: left;">
									主表
								</th>
								<th style="text-align: left;">
									从表
									<select id="selTables" style="width: 200px;">
										<option value="">选择</option>
										<c:forEach items="${tables}" var="item">
											<option value="${item.tableName}">${item.tableDesc}</option>
										</c:forEach>
									</select>
								</th>
							</tr>
							<tr>
								<th   style="height:200px;text-align: center;vertical-align: top;">
									<table id="tbTableList" cellpadding="0" class="table-grid table-list"  cellspacing="0">
										<thead> 
											<tr ><th style="text-align: center;width: 135px;">表名</th><th style="text-align: center;width: 135px;">表注释</th></tr>
										</thead>
										<c:forEach items="${fieldList}" var="field">
											<tr style="cursor: pointer;" fieldName="${field.fieldName}"><td style="text-align: center;">${field.fieldName}</td><td style="text-align: center;">${field.fieldDesc}</td></tr>
										</c:forEach>
									</table>
								</th>
								<th style="height:200px;text-align: center;vertical-align: top;">
									
										<table id="tbDestTableList" cellpadding="0" class="table-grid table-list"  cellspacing="0">
											<thead> 
												<tr style="height: 20px;"><th style="text-align: center;width: 135px;">表名</th><th style="text-align: center;width: 135px;">表注释</th></tr>
											</thead>
										</table>
								</th>
							</tr>
							<tr>
								<td colspan="2" style="height:25px">
									<a href="javascript:;" id="btnAddRelation">添加关联:</a>
									<span id="relationContainer">
										<c:if test="${not empty relation}">
											<c:forEach items="${relation.relations}" var="tmp">
												<span class='spanRelation' tbName='${tmp.key}' fk="${tmp.value }" pk='${relation.pk}'><span class='relation'>${tmp.key}(从表):${tmp.value }</span><a href='#' onclick='removeRelation(this)' class="link del">删除</a></span>
											</c:forEach>
										</c:if>
									</span>
								</td>
							</tr>
						</table>
					</div>
			</div>
	</div>
</form>
</body>
</html>