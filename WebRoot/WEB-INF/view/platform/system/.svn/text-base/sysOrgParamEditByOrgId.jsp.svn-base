<%@page import="com.hotent.platform.model.system.Dictionary"%>
<%@page import="com.hotent.platform.service.system.DictionaryService"%>
<%@page import="com.hotent.platform.model.system.SysOrgParam"%>
<%@page import="com.hotent.platform.service.system.SysParamService"%>
<%@page import="com.hotent.core.util.StringUtil"%>
<%@page import="java.util.*"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>组织参数属性管理</title>
<%@include file="/commons/include/form.jsp"%>
    <f:link href="tree/zTreeStyle.css"></f:link>
    <script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysOrgParam"></script>
    <script type="text/javascript" src="${ctx }/js/hotent/displaytag.js"></script>
	<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/htCatCombo.js"></script>
<style type="text/css">
.error {
	border-color: red;
}
</style>
<script type="text/javascript">
	function validValue() {
		var yes = true;
		var $aryId = $("#sysParamItem input[name='paramValue']");
		if ($aryId.length > 0) {
			$aryId
					.each(function(i, t) {
						var val = $(t).val();
						var tr = $(t).parents("tr");
						var option = $(tr).find(
								"div[name='paramselect'] ");
						var dataType = $(option).attr("title");
						var sourceType = $(option).attr("id");
						if (dataType == "Integer") {
							val = val.replace(/\s+/g, "");
							if (val == "" || isNaN(val)) {
								$(t).addClass("error");
								if ($(t).next().html() == null
										|| $(t).next().html() == '') {
									$(t).after(
											'<font color="red">请输入数字。</font>');
								}
								yes = false;
							}
						} else if (dataType == "Date") {
							var pattern = /^((\d{2}(([02468][048])|([13579][26]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|([1-2][0-9])))))|(\d{2}(([02468][1235679])|([13579][01345789]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\s(((0?[0-9])|([1-2][0-3]))\:([0-5]?[0-9])((\s)|(\:([0-5]?[0-9])))))?$/;
							if (!pattern.exec(val)) {
								$(t).addClass("error");
								if ($(t).next().html() == null
										|| $(t).next().html() == '')
									$(t).after(
											'<font color="red">请输入日期。</font>');
								yes = false;
							}
						} else {
							if ($.isEmpty(val)) {
								$(t).addClass("error");
								$(t).after('<font color="red">请输入值。</font>');
								yes = false;
							}
						}

					});
		}
		if (yes) {
			$aryId.removeClass("error");
			if ($aryId.next().html() != null)
				$aryId.next().empty();
		}
		return yes;
	};

	$(function() {

		function showRequest(formData, jqForm, options) {
			var v = validValue();
			return (v);
		}
		valid(showRequest, showResponse);
		$("a.save").click(function() {
			saveData();
		});
		
		function saveData(){
			var orgId = $("#orgId").val();
			var serializeObj={};  
            var array= $("form").serializeArray();  
            $(array).each(function(){  
                if(serializeObj[this.name]){  
                    if($.isArray(serializeObj[this.name])){  
                        serializeObj[this.name].push(this.value);  
                    }else{  
                        serializeObj[this.name]=[serializeObj[this.name],this.value];  
                    }  
                }else{  
                    serializeObj[this.name]=this.value;   
                }
            }); 
            var str = JSON.stringify(serializeObj);
            $("#jsonParamData").val(str);
			if (orgId == null) {
				$.ligerDialog.warn("你还没选择组织!");
			} else {
				$('#sysOrgParamForm').submit();
			}
		}

		function showResponse(responseText, statusText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {//成功
				$.ligerDialog.success(obj.getMessage(), '提示信息', function(rtn) {
					window.location.href = window.location.href;
				});
			} else {//失败
				$.ligerDialog.err('出错信息', "保存组织参数属性失败", obj.getMessage());
			}
		}
		
		$("a.add").click(function() {
			var url = window.location.href;
			var index = url.indexOf('&paramType');
			if(index >0)
				url = url.substring(0,index);
			var paramType = "";
			$("input[name='catName_']").each(function () {
				if(this.checked == true)
					paramType = paramType + this.value+",";
			});
			url =url + '&paramType='+paramType; 
			window.location = url;
		});
		$("a.del").click(function() {
			var tbody =  $("#sysParamItem").children()[1];
			var tr = $(tbody).children();
			tr.each(function(i){
				$(this).remove()
			 }); 
			saveData();
		});

	});
	
	function delRow(obj) {
		var tr = $(obj).parents("tr");
		$(tr).remove();
	};
	
</script>

</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">【${sysOrg.orgName}】组织参数属性管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<a class="link del"><span></span>全部删除</a>
					<div class="l-bar-separator"></div>
					<div class="group">
						<input id="01" type="checkbox" value="all" name="catName_" <c:if test="${fn:contains(paramType,'all,')}">checked="checked"</c:if>>
						<label for="01">全部</label>
						<c:forEach items="${categoryList}" var="catName" varStatus="index">
							<input id="${index}" type="checkbox" name="catName_" value="${catName}" <c:if test="${fn:contains(paramType,catName) }">checked="checked"</c:if>>
							<label for="${index}">${catName}</label>
						</c:forEach>
						<a class="link add"><span></span>按分类过滤添加</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysOrgParamForm" method="post"
				action="${ctx }/platform/system/sysOrgParam/saveByOrgId.ht">
				<input type="hidden" name="orgId" id="orgId" value="${sysOrg.orgId}">
				<input type="hidden" id="jsonParamData" name ="jsonParamData" value=""/>
				<table id="sysParamItem" cellpadding="1" cellspacing="1"
					class="table-grid">
					<thead>
						<th style="text-align: center;">参数名</th>
						<th style="text-align: center;">参数值</th>
						<th style="text-align: center;">参数描述</th>
						<th style="text-align: center;">管理</th>
					</thead>
				    <jsp:include page="incToolBarParam.jsp"></jsp:include>
				</table>
			</form>
		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
</body>
</html>


