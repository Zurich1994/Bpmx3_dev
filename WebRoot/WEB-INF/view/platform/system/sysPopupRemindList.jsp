<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>提醒功能管理列表</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/sysPopupRemind/SysPopupRemindUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/sysObjRights/SysObjRightsUtil.js"></script>
<script type="text/javascript">
	window.setTimeout(function(){
		easyDialog.open({
			  container : {
			    header : '关闭拖拽',
			    content : '欢迎使用easyDialog : )'
			  },
			  drag : false
			});
	},2000)
	function show(userId){
		SysPopupRemindUtil.show("",function(){
			$.ligerDialog.warn("弹出列表为空（sql结果为0或者不启动都不算上列表）");
		});
	}
	
	function setEnabled(enabled){
		var objs = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
		var ids="";
		objs.each(function(i){
			if(ids!=""){
				ids+=",";
			}
			ids+=$(this).val();
		});
		var url=__ctx +"/platform/system/sysPopupRemind/setEnabled.ht";
		$.post(url,{ids:ids,enabled:enabled},function(data){
			if(data.status){
				$.ligerDialog.success(data.msg,"提示信息",function(){
					$("#btnSearch").click();
				});
			}else{
				 $.ligerDialog.error(data.msg,"提示信息");
			}
		});
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">提醒功能管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link auth" onclick="show()"><span></span>演示</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link export" onclick="setEnabled(1)"><span></span>启动所选</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link import" onclick="setEnabled(0)"><span></span>关闭所选</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">主题:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']} " />
						<span class="label">数据源别名:</span><input type="text" name="Q_dsalias_SL"  class="inputText" value="${param['Q_dsalias_SL']} " />
						<span class="label">是否启用:</span>
						<select name="Q_enabled_S" value="${param['Q_enabled_S']}" >
							<option value="" >无</option>
						    <option value="1" <c:if test="${param['Q_enabled_S'] == 1}">selected</c:if>>是</option>
						    <option value="0" <c:if test="${param['Q_enabled_S'] == 0&&param['Q_enabled_S'] !=''}">selected</c:if> >否</option>
						</select>
						<span class="label">创建时间 从:</span> <input  name="Q_begincreateTime_DL"  class="inputText date" value="${param['Q_begincreateTime_DL']} " />
						<span class="label">至: </span><input  name="Q_endcreateTime_DG" class="inputText date" value="${param['Q_endcreateTime_DG']} "   />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysPopupRemindList" id="sysPopupRemindItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysPopupRemindItem.id}">
				</display:column>
				<display:column property="subject" title="主题 " sortable="true" sortName="SUBJECT" maxLength="80"></display:column>
				<display:column property="dsalias" title="数据源别名" sortable="true" sortName="DSALIAS"></display:column>
				<display:column property="sn" title="排序" sortable="true" sortName="SN"></display:column>
				<display:column title="是否启用" sortable="true" sortName="ENABLED">
					<c:choose>
						<c:when test="${sysPopupRemindItem.enabled==0}">
							<span class="red">否</span>
						</c:when>
						<c:otherwise>
							<span class="green">是</span>
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="创建时间" sortable="true" sortName="CREATETIME">
					<fmt:formatDate value="${sysPopupRemindItem.createTime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${sysPopupRemindItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${sysPopupRemindItem.id}" class="link edit">编辑</a>
					<a onclick="SysObjRightsUtil.setRights(${sysPopupRemindItem.id},'popupMsg')" class="link auth">授权</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysPopupRemindItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


