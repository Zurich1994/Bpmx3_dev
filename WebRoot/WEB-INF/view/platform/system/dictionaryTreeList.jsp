<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>数据字典管理</title>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript">
		//树列表的收展
		function treeClick(obj){
			var clazz=$(obj).attr("class");
			var id=$(obj).parents("tr").attr("id");
			if(clazz=="tree-list-minus"){
				toggleChild(id,"hide");
			}
			else if(clazz=="tree-list-plus"){
				toggleChild(id,"show");
			}
			//置换加减号
			$(obj).toggleClass("tree-list-minus");
			$(obj).toggleClass("tree-list-plus");
		};
		
		//子结点收展
		function toggleChild(parentId,type){
			var child=$("tr[parentId='"+parentId+"']");
			$.each( child, function(i, c){
				if(type=="hide"){
					$(c).hide();
				}
				else if(type=="show"){
					$(c).find("a[name='tree_a']").removeClass("tree-list-plus");
					$(c).find("a[name='tree_a']").addClass("tree-list-minus");
					$(c).show();
				}
				var id=$(c).attr("id");
				toggleChild(id,type);
			});
		};
		
		//向上
		function sortUp(obj) {
			var thisTr = $(obj).parents("tr");
			var parentId=$(thisTr).attr("parentId");
			var prevTr = findPrev(thisTr,parentId);
			if (typeof (prevTr) != 'undefined' && prevTr!=null && prevTr!=''){
				var thisTrHtml = $(thisTr).html();
				var prevTrHtml = $(prevTr).html();
				if(prevTrHtml!=null){
					$(thisTr).html(prevTrHtml);
					$(prevTr).html(thisTrHtml);
					reSortTreeListSn(thisTr);
				}
			}
		};
		// 向下
		function sortDown(obj) {
			var thisTr = $(obj).parents("tr");
			var parentId=$(thisTr).attr("parentId");
			var nextTr = findNext(thisTr,parentId);
			if (typeof (nextTr) != 'undefined'&& nextTr!=null && nextTr!=''){
				var thisTrHtml = $(thisTr).html();
				var nextTrHtml = $(nextTr).html(); 
				if(nextTrHtml!=null){
					$(thisTr).html(nextTrHtml);
					$(nextTr).html(thisTrHtml);
					reSortTreeListSn(thisTr);
				}
			}
		};
		
		//向上找
		function findPrev(thisTr,thisParentId){
			var prevTr =$(thisTr).prev("tr");
			if(prevTr.html()!=null){
				var prevParentId=$(prevTr).attr("parentId");
				if(thisParentId==prevParentId){
					return prevTr;
				}else{
					if(prevTr!=null){
						return findPrev(prevTr,thisParentId);
					}
				}
			}
			
		};
		//向下找
		function findNext(thisTr,thisParentId){
			var prevTr =$(thisTr).next();
			if(prevTr.html()!=null){	
				var prevParentId=$(prevTr).attr("parentId");
				if(thisParentId==prevParentId){
					return prevTr;
				}else{
					if(prevTr!=null){
						return findNext(prevTr,thisParentId);
					}
				}
			}
		};
		//重新排序
		function reSortTreeListSn(trObj){
			var parentId=$(trObj).attr("parentId");
			var table= $(trObj).parents("table");
			var trs=$(table).find("tr[parentId="+parentId+"]");
			var checkbox=$(trs).find(":checkbox[name='dicId']");
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
		
		function addNew(){
			var typeId=parent.getTypeId();
			if(typeId==0){
				 $.ligerDialog.warn("请选择左边具体的分类才能添加数据字典!");
				 return;
			}
			else{
				location.href="add1.ht?typeId="+typeId;
			}
		}
	</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">${ sysGlType.typeName}</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link add" onclick="addNew()" href="javascript:;"><span></span>添加字典</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
		</div>
		</div>
		<div class="panel-body">
		
			<div class="panel-data">
		    	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			   	<table id="dicTable" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
			   		<thead>
			   			<th width="30px"><input type="checkbox" id="chkall"></th>
			    		<th >项名</th>
			    		<th >项值</th>
			    		<th >项Key</th>
			    		<th >管理</th>
			    	</thead>
			    	<tbody>
				    	<c:forEach items="${dictionaryList}" var="dictionaryItem" varStatus="status">
				    		<tr id="${dictionaryItem.dicId }" parentId="${dictionaryItem.parentId }" class="${status.index%2==0?'even':'odd'}">
				    			<td>
					    			<input type="checkbox" class="pk" name=dicId value="${dictionaryItem.dicId }">
					    		</td>
				    			<td nowrap="nowrap">
				    				${dictionaryItem.space }
				    				<a name="tree_a" class="tree-list-minus" onclick="treeClick(this)"><span class="tree-list-span">${dictionaryItem.itemName }</span></a>
				    			</td>
				    			<td>${dictionaryItem.itemValue }</td> 
				    			<td>${dictionaryItem.itemKey }</td>
				    			<td nowrap="nowrap" style="width:415px;">
				    				<a href="del.ht?dicId=${dictionaryItem.dicId}" class="link del">删除</a>
									<a href="upd1.ht?dicId=${dictionaryItem.dicId}&returnUrl=${returnUrl}" class="link edit">编辑</a>
									<a href="add1.ht?typeId=${typeId}&parentId=${dictionaryItem.dicId}&returnUrl=${returnUrl}" class="link-add"><span class="link-btn">添加子结点</span></a>
									<c:if test="${dictionaryItem.first==false}">
										<a href="javascript:;" class="link moveup" onclick="sortUp(this)">向上</a>
									</c:if>
									<c:if test="${dictionaryItem.last==false}">
										<a href="javascript:;" class="link movedown" onclick="sortDown(this)">向下</a>
									</c:if>
								</td>
				    		</tr>
				    	</c:forEach>
			    	</tbody>
			    </table>
			</div>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


