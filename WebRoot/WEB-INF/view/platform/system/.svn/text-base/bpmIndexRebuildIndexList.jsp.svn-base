<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>索引重建管理</title>
<%@include file="/commons/include/get.jsp" %>
<%-- <link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" /> --%>
<f:link href="jquery.qtip.css"></f:link>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<script type="text/javascript">
	$(function(){
		$("a.unsupport-rebuild-index").each(function(){
			var msg=$(this).attr("msg")
			$(this).qtip({
				content:msg
			});	
		});
		
	});
	function editRebuildJob() {
		var indexesAry = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
		if (indexesAry.length == 0) {
			$.ligerDialog.warn("请选择索引！");
			return false;
		} else {
			var temhtml=$('#divJob').html();
			var defDesc="";
			indexesAry.each(function(i){
					defDesc+="表名："+$(indexesAry[i]).attr('table')+",";
					defDesc+="索引名："+$(indexesAry[i]).val()+";\n";
			});
			$('#jobDescription1').val(defDesc);
			$.ligerDialog.open({
				width : 600,
				height : 400,
				type : 'error',
				title : '请输入索引重建计划名称和描述',
				target : $('#frmJob'),
				buttons : [ {
					text : '确定',
					onclick : function(button,dialog){
						var jobName = $('#jobName1').val();
						var jobDescription = $('#jobDescription1').val();
						if ($.trim(jobName) != '') {
							$('#jobName').val(jobName);
							$('#jobDescription').val($.trim(jobDescription));
							saveRebuidJob();
							dialog.close();
							$('#divJob').html(temhtml);
						} else {
							$.ligerDialog.warn("索引重建计划名不能为空");
	
						}
					}
				}, {
					text : '取消',
					onclick : function(button,dialog){
						dialog.close();
						$('#divJob').html(temhtml);
					}
				} ]
			});
			
		}
	}
	function saveRebuidJob() {
		var url = __ctx + "/platform/system/bpmIndexRebuild/saveJob.ht";
		var indexAry=new Array();
		var indexeNames = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
		indexeNames.each(function(){
			var name = $(this).val();
// 			var table = $(this).next('input[name="indexTable"]').val();
			var table=$(this).attr('table');
			var isCustom=$(this).attr('custom');
			var isPrimaryKeyIndex=$(this).attr('primaryKeyIndex');
			var index={
				name:name,
				table:table,
				isCustom:isCustom,
				isPrimaryKeyIndex:isPrimaryKeyIndex
			};
			indexAry.push(index);
		});
		var params={
				indexes:JSON2.stringify(indexAry),
				jobName:$('#jobName').val(),
				jobDescription:$('#jobDescription').val()
		};
		$.post(url,params, function(data) {
			var obj = new com.hotent.form.ResultMessage(data);
			if (obj.isSuccess()) {
				$.ligerDialog.success(obj.getMessage(),"提示信息",function(){
					window.close();
				});
				
				
			} else {
				$.ligerDialog.err('出错信息',"保存索引失败",obj.getMessage());
			}
		});
	}

</script>

</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">索引重建管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<c:choose>
							<c:when test="${dbType=='db2'}">
								<a id="editRebuildJob" class="link add disabled unsupport-rebuild-index" msg="DB2 数据库不支持在线索引重建" href="javascript:;"><span></span>创建计划</a>
							</c:when>
							<c:when test="${dbType=='h2'}">
								<a id="editRebuildJob" class="link add disabled unsupport-rebuild-index" msg="H2 数据库不支持索引重建" href="javascript:;"><span></span>创建计划</a>
							</c:when>
							<c:otherwise>
								<a id="editRebuildJob" class="link add" href="javascript:;" onclick="editRebuildJob()"><span></span>添加定时任务</a>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group"><a id="searchIndex" class="link search" href="javascript:;" ><span></span>查询</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm">
					<ul class="row">
			            <li><span class="label">索引名称:</span><input type="text" name="Q_indexName_S"  class="inputText" value="${param['Q_indexName_S']}"/></li>
			            <li><span class="label">表名称:</span><input type="text" name="Q_tableName_S"  class="inputText" value="${param['Q_tableName_S']}"/></li>
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
					
				<form id="frmIndexRebuid">
			    	<c:set var="checkAll">
						<input type="checkbox" id="chkall"/>
					</c:set>
					
					<c:set var="dbIndexList">
						<table id="indexList" class="table-grid table-list" cellpadding="1" cellspacing="1">
					   		<thead>
					   			<tr>
					   				<th>${checkAll}&nbsp;选择</th>
					   				<th>索引名</th>
					   				<th>表名</th>
					   				<th>自定义表</th>
					   				<th>主键索引</th>
					   				<th>表描述</th>
					   			</tr>
					   		</thead>
					   		<tbody>
					   			<c:forEach items="${indexList}" var="indexMap">
					   				<tr>
					   					<td width="100px">
					   						<input class="pk"  type="checkbox" value="${indexMap['index'].indexName}" 
					   						table="${indexMap['index'].indexTable}"
					   						custom="${indexMap['isCustom'] }"
					   						primaryKeyIndex="${indexMap['index'].pkIndex }"
					   						/>
					   						<input name="indexTable" type="hidden" value="${indexMap['index'].indexTable}"/>
					   					</td>
					   					<td >${indexMap['index'].indexName }</td>
					   					<td >${indexMap['index'].indexTable }</td>
					   					<c:choose>
					   						<c:when test="${indexMap['isCustom'] }">
					   							<td >√</td>
					   						</c:when>
					   						<c:otherwise>
					   							<td >X</td>
					   						</c:otherwise>
					   					</c:choose>
					   					<c:choose>
					   						<c:when test="${indexMap['index'].pkIndex }">
					   							<td >√</td>
					   						</c:when>
					   						<c:otherwise>
					   							<td >X</td>
					   						</c:otherwise>
					   					</c:choose>
										<td>${indexMap['tableComment'] }</td>
									</tr>
					   			</c:forEach>
					   		</tbody>
					   	</table>
					</c:set>
					<c:set var="dbTableList">
						<table id="indexList" class="table-grid table-list" cellpadding="1" cellspacing="1">
					   		<thead>
					   			<tr>
					   				<th>${checkAll}&nbsp;选择</th>
					   				<th>索引名</th>
					   				<th>表名</th>
					   				<th>自定义表</th>
					   				<th>主键索引</th>
					   				<th>表描述</th>
					   			</tr>
					   		</thead>
					   		<tbody>
					   			<c:forEach items="${indexList}" var="indexMap">
					   				<tr>
					   					<td width="100px">
					   						<input class="pk"  type="checkbox" value="${indexMap['index'].indexName}" 
					   						table="${indexMap['index'].indexTable}"
					   						custom="${indexMap['isCustom'] }"
					   						primaryKeyIndex="${indexMap['index'].pkIndex }"
					   						/>
					   					</td>
					   					<td >${indexMap['index'].indexName }</td>
					   					<td >${indexMap['index'].indexTable}</td>
					   					<c:choose>
					   						<c:when test="${indexMap['isCustom'] }">
					   							<td >√</td>
					   						</c:when>
					   						<c:otherwise>
					   							<td >X</td>
					   						</c:otherwise>
					   					</c:choose>
					   					<c:choose>
					   						<c:when test="${indexMap['index'].pkIndex }">
					   							<td >√</td>
					   						</c:when>
					   						<c:otherwise>
					   							<td >X</td>
					   						</c:otherwise>
					   					</c:choose>
										<td>${indexMap['tableComment'] }</td>
									</tr>
					   			</c:forEach>
					   		</tbody>
					   	</table>
					</c:set>
					<c:if test="${dbType=='mysql'}">${dbTableList }</c:if>
					<c:if test="${dbType=='oracle'}">${dbIndexList }</c:if>
					<c:if test="${dbType=='sqlserver'}">${dbIndexList }</c:if>
					<c:if test="${dbType=='db2'}">${dbIndexList }</c:if>
					<c:if test="${dbType=='h2'}">${dbIndexList }</c:if>
					<c:if test="${dbType=='dm'}">${dbIndexList }</c:if>
				   	<input name="jobName" id="jobName" type="hidden" value=""/>
				   	<input name="jobDescription" id="jobDescription" type="hidden" value=""/>
			   	</form>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
	<div id="divJob" style="display:none">
  		<form id="frmJob" >
  			<table class="table-grid table-list">
  				<tr>
  					<td>计划名:</td>
  					<td><input id="jobName1" class="inputText"/></td>
  				</tr>
  				<tr>
  					<td>描述:</td>
  					<td><textarea id="jobDescription1" cols="30" rows="10" style="height:200px;width:400px;"></textarea></td>
  				</tr>
  			</table>
  		</form>
  	</div>
</body>
</html>


