<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>组织结构类型管理</title>
<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				save(); 
			});
		});
	
		function sortTr(obj,isUp) {
			var thisTr = $(obj).parents("tr");
			if(isUp){
				var prevTr = $(thisTr).prev();
				if(prevTr.length>0){
					thisTr.insertBefore(prevTr);
				}
			}
			else{
				var nextTr = $(thisTr).next();
				if(nextTr.length>0){				
					thisTr.insertAfter(nextTr);
				}
			}
		};
		
		function save(){
			var ids='';
			
			$(':checkbox').each(function(){			
				ids+=$(this).val()+",";				
			});
			if(ids.length<1){		
				this.close();
				return;
			}
			$.post('${ctx }/platform/system/sysOrgType/multSave.ht',{'ids':ids},function(responseText){
							var obj = new com.hotent.form.ResultMessage(responseText);
							if (obj.isSuccess()) {
								$.ligerDialog.confirm( obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
									if(rtn){
										window.location.reload(true);
									}else{
										window.location.href = "${ctx}/platform/system/demension/list.ht";
									}
								});
							} else {
								$.ligerDialog.err('出错信息',"保存组织结构类型失败",obj.getMessage());
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
				<span class="tbar-label">组织结构类型管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link add"  href="edit.ht?demId=${demId}"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>			
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					
					<div class="group"><a class="link save"  action="del.ht" href="javascript:;"><span></span>保存排序</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href=" ${ctx}/platform/system/demension/list.ht"><span></span>返回</a></div>
				</div>	
			</div>
			
		</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysOrgTypeList" id="sysOrgTypeItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysOrgTypeItem.id}">
				</display:column>
				
				<display:column property="name" title="名称" sortable="true" sortName="name"></display:column>
				<display:column property="levels" title="级别" sortable="true" sortName="levels"></display:column>
				<display:column property="memo" title="备注" sortable="true" sortName="memo"></display:column>
				<display:column title="管理" media="html" style="width:180px">
					<a alt='上移' href='#' class='link moveup' onclick='sortTr(this,true)'>&nbsp;&nbsp;</a>
					<a alt='下移' href='#' class='link movedown' onclick='sortTr(this,false)'>&nbsp;&nbsp;</a>
					<a href="del.ht?id=${sysOrgTypeItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${sysOrgTypeItem.id}" class="link edit">编辑</a>
				</display:column>
			</display:table>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


