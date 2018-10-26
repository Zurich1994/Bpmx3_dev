
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>系统岗位管理</title>
	<%@include file="/commons/include/get.jsp"%>
	<script type="text/javascript">
		var isSingle="${isSingle}";
		forbidF5("Chrome");//禁止刷新页面
		//树列表的收展
		function treeClick(obj) {
			var clazz = $(obj).attr("class");
			var id = $(obj).parents("tr").attr("id");
			if (clazz == "tree-list-minus") {
				toggleChild(id, "hide");
			} else if (clazz == "tree-list-plus") {
				toggleChild(id, "show");
			}
			//置换加减号
			$(obj).toggleClass("tree-list-minus");
			$(obj).toggleClass("tree-list-plus");
		};
		//子结点收展
		function toggleChild(parentId, type) {
			var child = $("tr[parentId='" + parentId + "']");
			$.each(child, function(i, c) {
				if (type == "hide") {
					$(c).hide();
				} else if (type == "show") {
					$(c).find("a[name='tree_a']").removeClass("tree-list-plus");
					$(c).find("a[name='tree_a']").addClass("tree-list-minus");
					$(c).show();
				}
				var id = $(c).attr("id");
				toggleChild(id, type);
			});
		};
		
		function clickPos(obj){
			if($(obj).attr("type")=="radio"){
				return;
			}
			var posId = $(obj).val();
	
			var isChecked=false;
			if(obj.checked){
				isChecked=true;
			}else{
				isChecked=false;
			}
		}
		 
		 $(function(){
			 //选择一行，增加list
			 $("#positionItem>tbody").find("tr").bind('click', function() {
				 if(isSingle=='true'){
					var rad=$(this).find('input[name=posId]:radio');
					rad.attr("checked","checked");
				}else{
					var ch=$(this).find(":checkbox");
					window.parent.selectMulti(ch);
				}
			});	
			 //单条记录勾选，单行打勾
			 $(".pk").parent().parent().click(function(){
				 var pks = $(this).find(".pk");
				 if(pks.length>0){
					 clickPos(pks.get(0));
				 }
			}); 
			 //点击 全选checkbox
			 $("#chkall").bind("click",function(){
			        var checkAll=false;
					if($(this).attr("checked")){
						checkAll=true;	
					}
					var checkboxs=$(":checkbox",$("#positionItem>tbody"));
					checkboxs.each(function(){
						if(checkAll){
							window.parent.selectMulti(this);
						}
					});
			 });
		 });
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-search">
				<form id="searchForm" action="selector.ht" method="POST" target="posFrame">
					<ul class="row">
						<input type="hidden" name="isSingle" value="${isSingle }">
						<input type="hidden" name="type" value="${type }">
						<input type="hidden" name="typeVal" value="${typeVal }">
						<li><span class="label">岗位名称:</span> 
						<input type="hidden" name="pid" id="pid" /> 
						<input type="text" id="posName" name="Q_posName_SL" class="inputText" size="40" value="${param['Q_posName_SL']}"/> &nbsp; 
						<a href='#' class='button'  onclick="$('#searchForm').submit();"><span>查询</span></a></li>
					</ul>
				</form>
		</div>
		<div class="panel-body">
			<div class="panel-data">
				<c:if test="${isSingle==false}">
				<c:set var="checkAll">
					<input type="checkbox" id="chkall" />
				</c:set>
				</c:if>
				 <display:table name="positionList" id="positionItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
					<c:choose>
						<c:when test="${isSingle==false}">
					  	<input type="checkbox" class="pk" name="posId"  value="${positionItem.posId}#${positionItem.posName}"/>
					  	</c:when>
						<c:otherwise>
						<input type="radio" class="pk" name="posId" value="${positionItem.posId}#${positionItem.posName}#${positionItem.jobName}#${positionItem.jobGradeName}">
						</c:otherwise>
					</c:choose>
						<input type="hidden" name="posName" value="${positionItem.posName }">
					</display:column>
					<display:column property="posName" title="岗位名称" sortable="true" sortName="posName"></display:column>
					<display:column property="jobName" title="职务名称" ></display:column>
					<display:column property="jobGradeName" title="职务等级" ></display:column>
					<display:column property="posDesc" title="岗位描述" ></display:column>
				</display:table>
				<hotent:paging tableId="positionItem" showExplain="true"/>
			</div>
 		</div>
		
	</div>
</body>
</html>