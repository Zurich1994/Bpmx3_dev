<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/form.jsp" %>
<title>流程定义权限管理</title>
<style type="text/css">
	.owner-span{
		font-size: 13px;
		background-color: #EFF2F7;
	    border: 1px solid #CCD5E4;
	    border-radius: 5px 5px 5px 5px;
	    cursor: default;
	    float: left;
	    height: auto !important;
	    margin: 3px;
	    overflow: hidden;
	    padding: 2px 4px;
	    white-space: nowrap;
	}
</style>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmDefRights"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/htButtons.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

	$(function() {
		$("span.htbtn").htButtons();
		$("a.moreinfo").live('click',function(){
			var me = $(this),
				hrefStr = me.attr('hrefstr');
			if(!hrefStr)return;
			openDetailWin({url:hrefStr,hasClose:true});
		});
		
		function showRequest(formData, jqForm, options) {
			return true;
		}
		valid(showRequest, showResponse);
		$("a.save").click(function() {
			var checked=$("#selectAll").is(":checked");
			if(checked){
				$("textarea:not([rightType])").val("");
			}else{
				$("textarea[rightType]").val("");
			}
			$('#bpmDefRightsForm').submit();
		});
	
		function showResponse(responseText, statusText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) { //成功
				$.ligerDialog.success( obj.getMessage(),'提示', function(rtn) {
					if (rtn) {
						dialog.close();
					}
				});
			} else { //失败
				$.ligerDialog.err('提示','保存失败!', obj.getMessage());
			}
		}
		initOwnerSpan();
		selectAllHandler($("#selectAll"));
	});
	//显示用户详情
	function openDetailWin(conf){
		var dialogWidth=650;
		var dialogHeight=500;
		
		conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

		var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
			+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
		var url = conf.url + '&hasClose=' +conf.hasClose;
		//var rtn=window.showModalDialog(url,"",winArgs);	
		
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : '显示用户详情',
			url: url, 
			isResize: true,
		});
	};
	//初始化数据库中的数据
	function initOwnerSpan(){
		$("textarea[name='owner']").each(function(){
			var me = $(this),
				tr = me.parents("tr"),
				owner = $("textarea[name='owner']",tr).val();
				rightType = $("input[name='rightType']",tr).val();
			if(rightType==0){
				if(owner){
					$("#selectAll").attr("checked","checked");
				}
				setVal(me,'textarea');
			}else if(rightType==1){
				var href = "${ctx}/platform/system/sysUser/get.ht?userId=";
				setVal(me,'textarea',href);
			}
			else
				setVal(me,'textarea');
		});
		
	};
	//选择器回填
	function setOwnerSpan(tr,json,href){
		var div = $("div.owner-div",tr);
		if(!div||div.length==0)return;
		div.empty();
		if(!json||json.length==0)return;
		for(var i=0,c;c=json[i++];){
			if(c.id == "")
				continue;
			var a = $('<a class="moreinfo"></a>').html(c.name).attr("ownerId",c.id);
			if(href){
				a.attr("hrefstr",href+c.id);
				a.attr("href","#");
			}
			var	span = $('<span class="owner-span"></span>').html(a);
			div.append(span);
		}
	};
	//重置
	function resetSelect(obj) {
		var tr = $(obj).parents("tr"),
			div = $("div.owner-div",tr),
			owner = $("textarea[name='owner']",tr);
		div.empty();
		owner.val('');
	}
	//设置值
	function setVal(obj,json,href){
		var tr=$(obj).parents("tr"),
			owner = $("textarea[name='owner']",tr);
		if(json=='textarea'){
			json = owner.val();
			json = $.parseJSON(json);
		}
		else{
			var jsonStr = JSON2.stringify(json);
			owner.val(jsonStr);
		}
		setOwnerSpan(tr,json,href);
	};

	function selectAllHandler(obj) {
		var _this = $(obj);
		if( _this.is(":checked")){
			$("a.current").hide();
			$("div.owner-div").hide();
			$("#chooseAll").trigger("click");
		}else{
			$("a.current").show();
			$("div.owner-div").show();
			$("#resetAll").trigger("click");
		}
	};

	//选择所有用户
	function chooseAllHandler(obj) {
		var tr=$(obj).parents("tr");
		var json=[{
			id:0,
			name:'所有用户'
		}];
		setVal(obj,json);
	};
	
	function convertToJson(tempIds,tempNames){
		var ids=tempIds.split(",");
		var names=tempNames.split(",");
		var json=[];
		for(var i=0;i<ids.length;i++){
			var obj={};
			obj.id=ids[i];
			obj.name=names[i];
			json.push(obj);
		}
		return json;
	}
	
	//选择用户
	function chooseUser(obj) {
		var tr=$(obj).parents("tr"),
			data = $("textarea[name='owner']",tr).val(),
			href = "${ctx}/platform/system/sysUser/get.ht?userId=";
		if(data)
			data = eval("("+data+")"); 
		UserDialog({isSingle:false,selectUsers:data,callback:function(userIds, fullnames,email,mobile,json){
			json=convertToJson(userIds,fullnames);
			setVal(obj,json,href);
		}});
	};
	//选择组织
	function chooseOrg(obj){
		var tr=$(obj).parents("tr"),
			json = $("textarea[name='owner']",tr).val();
		if(json)
			json = $.parseJSON(json);
		OrgDialog({isSingle:false,arguments:json,callback:function(orgIds, orgnames,json){
			json=convertToJson(orgIds,orgnames);
			setVal(obj,json);
		}});
	};
	//选择角色
	function chooseRole(obj){
		var tr=$(obj).parents("tr"),
			json = $("textarea[name='owner']",tr).val();
		if(json)
			json = $.parseJSON(json);		
		RoleDialog({isSingle:false,arguments:json,callback:function(roleIds, rolenames,json){
			json=convertToJson(roleIds,rolenames);
			setVal(obj,json);
		}});	
	};
    //岗位选择
    function choosePosition(obj){
    	var tr=$(obj).parents("tr"),
			json = $("textarea[name='owner']",tr).val();
		if(json)
			json = $.parseJSON(json);
		PosDialog({isSingle:false,arguments:json,callback:function(roleIds, rolenames,json){
			json=convertToJson(roleIds,rolenames);
			setVal(obj,json);
		}});
    };
    
	function isChangeCheck(){
		var v = $('#isChange').val();
		if(v==0){
			$('#isChange').val(1);
		}else{
			$('#isChange').val(0);
		}
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
				<c:choose >
					<c:when test="${type==0}">
					<c:forEach items="${bpmDefinitions}" var="bpmDefinition" >
					${bpmDefinition.subject}
					</c:forEach>
					</c:when>
					<c:otherwise>
					<c:forEach items="${globalTypes}" var="globalType"  >
					${globalType.typeName}
					</c:forEach>
					</c:otherwise>
				</c:choose>-权限设置
				</span>
			</div>
			
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<c:if test="${isParent==1}">
						<div class="group">
							<input type="checkbox" id="chbIsChange" onclick="isChangeCheck()" >子类型随父类型权限变更
						</div>
						<div class="l-bar-separator"></div>
					</c:if>
					<div class="group"><a class="link del" onclick="dialog.close()" href="javascript:;"><span></span>关闭</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			
			<form id="bpmDefRightsForm" action="${ctx}/platform/bpm/bpmDefRight/save.ht" method="post">
				<input type="hidden"  name="id" value="${id}">
				<input type="hidden"  name="type" value="${type}">
				<input type="hidden"  name="defKey" value="${defKey}">
				<input type="hidden"  id="isChange" name="isChange" value="0">
				
						<table id="bpmDefRightTable"  class="table-grid table-list" cellpadding="0" cellspacing="0" border="0">
							<thead>
								<tr>
									<th width="150px;" nowrap="nowrap">权限分类</th>
									<th style="text-align: center;">授权给</th>
									<th  width="180px;" nowrap="nowrap"  style="text-align: center;">选择</th>
								</tr> 
							</thead>
								<tr>
									<th colspan="3"> 
										<input id="selectAll" style="float:left" type="checkbox" onclick="selectAllHandler(this)"> <label style="float:left"  for="selectAll">允许所有人访问</label>
									</th>
								</tr>
								<tr style="display: none">
									<td>所有用户</td>
									<td>
										<div class="owner-div"></div>
										<textarea rightType="0" class="hidden" name="owner">${rightsMap.all}</textarea>
										<input type="hidden" name="rightType" value="0">
									</td>
									<td>			
										<a id="chooseAll" onclick="chooseAllHandler(this);" ></a>
                                        <a id="resetAll" onclick="resetSelect(this);" ></a>
									</td>
								</tr>
								<tr>
									<td>用户授权</td>
									<td>
										<div class="owner-div"></div>
										<textarea class="hidden" name="owner">${rightsMap.user}</textarea>
										<input type="hidden" name="rightType" value="1">
									</td>
									<td>
										<a class="current" onclick="chooseUser(this);" ><span></span>选择..</a>
										<a class="current" onclick="resetSelect(this);" ><span></span>重置</a>
									</td>
								</tr>
								<tr>
									<td>角色授权</td>
									<td>
										<div class="owner-div"></div>
										<textarea class="hidden" name="owner">${rightsMap.role}</textarea>
										<input type="hidden" name="rightType" value="2">				
									</td>
									<td>
										<a class="current" onclick="chooseRole(this);" ><span></span>选择...</a>
										<a class="current" onclick="resetSelect(this);" ><span></span>重置</a>
									</td>
								</tr>
								<tr>
									<td>组织授权(本层级)</td>
									<td>
										<div class="owner-div"></div>
										<textarea class="hidden" name="owner">${rightsMap.org}</textarea>
										<input type="hidden" name="rightType" value="3">
									</td>
									<td>
										<a class="current" onclick="chooseOrg(this);" ><span></span>选择...</a>
										<a class="current" onclick="resetSelect(this);" ><span></span>重置</a>
									</td>
								</tr>
								<tr>
									<td>组织授权(包含子组织)</td>
									<td>
										<div class="owner-div"></div>
										<textarea class="hidden" name="owner">${rightsMap.orgGrant}</textarea>
										<input type="hidden" name="rightType" value="7">
										</td>
									<td>
										<a class="current" onclick="chooseOrg(this);" ><span></span>选择...</a>
										<a class="current" onclick="resetSelect(this);" ><span></span>重置</a>
									</td>
								</tr>
								<tr>
									<td>岗位授权</td>
									<td>
										<div class="owner-div"></div>
										<textarea class="hidden" name="owner">${rightsMap.position}</textarea>
										<input type="hidden" name="rightType" value="4">
									</td>
									<td>
										<a class="current" onclick="choosePosition(this);" ><span></span>选择...</a>
										<a class="current" onclick="resetSelect(this);" ><span></span>重置</a>
									</td>
								</tr>
						</table>
				</form>
		</div>
	</div>
</body>
</html>


