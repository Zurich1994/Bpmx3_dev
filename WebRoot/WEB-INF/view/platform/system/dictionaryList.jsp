
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>数据字典管理</title>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript">
		//向上
		function sortUp(obj) {
		
			var thisTr = $(obj).parents("tr");
			var prevTr = $(thisTr).prev();
			if (typeof (prevTr) != 'undefined' && prevTr!=null && prevTr!=''){
				var thisTrHtml = $(thisTr).html();
				var prevTrHtml = $(prevTr).html();
				if(prevTrHtml!=null){
					
					$(thisTr).html(prevTrHtml);
					$(prevTr).html(thisTrHtml);
					reSortListSn(thisTr);
				}
			}
		};
		
		// 向下
		function sortDown(obj) {
		
			var thisTr = $(obj).parents("tr");
			var nextTr = $(thisTr).next();
			if (typeof (nextTr) != 'undefined'&& nextTr!=null && nextTr!=''){
				var thisTrHtml = $(thisTr).html();
				var nextTrHtml = $(nextTr).html(); 
				if(nextTrHtml!=null){
					$(thisTr).html(nextTrHtml);
					$(nextTr).html(thisTrHtml);
					reSortListSn(thisTr);
				}
				
			}
		};
		
		//重新排序
		function reSortListSn(trObj,url){
			var table= $(trObj).parents("table");
		
			var checkbox=$(table).find(":checkbox[name='dicId']");
			var dicIds="";
			$.each( checkbox, function(i, c){
				dicIds+=$(c).val()+",";
			});
		
			if(dicIds.length>1){
				dicIds=dicIds.substring(0,dicIds.length-1);
					
				var form=new com.hotent.form.Form();
				form.creatForm("form", "${ctx}/platform/system/dictionary/sort.ht");
				form.addFormEl("dicIds", dicIds);
				form.submit();
					
			}
		};
		
		function addNew(parentId,typeId,returnUrl)
		{
			if(parentId==0)
			{
				 $.ligerDialog.warn("请选择左边具体的分类才能添加数据字典!");
				 return;
			}
			else
			{
				location.href="add1.ht?typeId="+typeId+"&returnUrl="+returnUrl;
			}
		}
	
	</script>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">${ globalType.typeName}</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link add\" onclick="addNew('${parentId}','${typeId}','${returnUrl}')" href="javascript:;"><span></span>添加字典</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
						</div>	
					</div>
				</div>
				</div>
				<div class="panel-body">
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label" style="display:none; ">nodePath:</span><input type="hidden" name="nodePath"  class="inputText" value="${nodePath }" readonly="readonly"/></li>
											
												<li><span class="label">项Key:</span><input type="text" name="itemKey"  class="inputText" value="${param['itemKey']}"/></li>
											
												<li><span class="label">项名:</span><input type="text" name="itemName"  class="inputText" value="${param['itemName']}"/></li>
											
									</ul>
							</form>
					</div>
					<br/>
					<div class="panel-data">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="dictionaryList" id="dictionaryItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="dicId" value="${dictionaryItem.dicId}">
							</display:column>
							<display:column property="itemName" title="项名" style="text-align:left"></display:column>
							<display:column property="itemValue" title="项值" sortable="true" sortName="itemValue" style="text-align:left"></display:column>
							<display:column property="itemKey" title="项Key" sortable="true" sortName="itemKey" ></display:column>
							
							
							<display:column title="管理" media="html" style="width:300px;text-align:center">
								<a href="del.ht?dicId=${dictionaryItem.dicId}" class="link del">删除</a>
								<a href="upd1.ht?dicId=${dictionaryItem.dicId}&returnUrl=${returnUrl}" class="link edit">编辑</a>
								<a href="get.ht?dicId=${dictionaryItem.dicId}" class="link detail">明细</a>
								<a href="javascript:;" class="link moveup" onclick="sortUp(this)">向上</a>
								<a href="javascript:;" class="link movedown" onclick="sortDown(this)">向下</a>							
							</display:column>
						</display:table>
						
					</div>
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


