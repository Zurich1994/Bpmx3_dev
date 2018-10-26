
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下属管理管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx }/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript">
	$(function() {
		$('#reload').attr('href',window.location.href);
		$("div.group > a.link.primary").click(function()
			{	
				var conf={};				
				var url=__ctx + "/platform/system/userUnder/myLeaders.ht";
				conf.url=url;
				var dialogWidth=550;
				var dialogHeight=250;
				conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);
				var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
					+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;				
				DialogUtil.open({
					height:conf.dialogHeight,
					width: conf.dialogWidth,
					title : '新窗口',
					url: url, 
					isResize: true,
				});
				
			});
	});
	function dlgCallBack(userIds, fullnames) {
		if (userIds.length > 0) {
			var userId=$("#userId").val();
			var id = userIds.split(",");
			for(var i=0;i<id.length;i++){
				if(id[i]==userId){
					alert("不能将自己添加为下属!");
					return false;
				}
			}
			$.post("addUnderUser.ht",
				   {userIds: userIds, userNames:fullnames,userId:userId},
					function(response){
						reload.click();
					 });			
		}
	};

	function add() {
		UserDialog({
			callback : dlgCallBack,
			isSingle : false
		});
	}
	
	
</script>
</head>
  <base target="_self">
<body>
    <a id="reload"  href="本页面url" style="display:none"></a>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">下属管理管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>					
					<div class="group">
						<f:a alias="addUnder" css="link add"  href="javascript:add();"><span></span>添加</f:a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
					<c:if test="${showMyleader}"><div class="group"><a class="link primary" ><span></span>我的上级</a></div></c:if>
				</div>	
			</div>
			<div class="panel-search">
					<form id="searchForm" method="post" action="list.ht?userId=${userId}">
							<ul class="row">									
								<li><span class="label">下属用户名:</span><input type="text" name="Q_underusername_SL"  class="inputText" value="${param['Q_underusername_SL']}"/>	</li>										
							</ul>
					</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
				<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="userUnderList" id="userUnderItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="id" value="${userUnderItem.id}">
					</display:column>
					
					<display:column property="underusername" title="下属用户名" sortable="true" sortName="underusername"></display:column>
					<display:column title="管理" media="html" style="width:180px;text-align:center">
						<a href="del.ht?id=${userUnderItem.id}" class="link del">删除</a>
						
					</display:column>
				</display:table>
				<hotent:paging tableId="userUnderItem" showExplain="false"/>
				<input type="hidden" id="userId" name="userId" value="${userId}" />
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


