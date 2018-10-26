<%--
	time:2012-03-25 9:00:00
	拦截URL
--%>
<%@page language="java" pageEncoding="UTF-8" import="java.util.*;"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>编辑URL拦截器</title>
<style type="text/css">
tr.ruleTR th, tr.sysUrlruleTr td {
	text-align: center;
}
</style>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript">
	$(function() {
		var frm=$('#sysUrlPermissionItems').form();
		$("a.save").click(function(){
			handSave();
		})
	});
	function editData(obj){
		var me=$(obj);
		var trObj=me.parents(".sysUrlruleTr");
		var jsonData={};
		getSubData(jsonData,trObj);
		showUrlRrles(trObj,jsonData);
	};
	
	
	function delData(obj){
		var me=$(obj); 
		var trObj=me.parents(".sysUrlruleTr");
		trObj.remove();
	}
	
	function delDataId(conf,obj){
		var me=$(obj);
		$.post(__ctx + '/platform/system/sysUrlRules/del.ht',{id:conf});
		delData(me);
	}
	/* //添加参数
	function addParams(obj) {
		var me = $(obj);
		var paramLable = $('#template span').clone(true);
		$(".paramLable", paramLable).attr("param", "param");
		me.before(paramLable);
		$("#paramsDiv").delegate(".paramLable","click",function() {
			var labelObj = $(this);

			var labelText = labelObj.text().trim();
			if (!labelText)
				return;
			var txtObj = $("<input type='text' class='inputText' maxlength='10' size='10' value='"+labelText+"' />");
			txtObj.blur(function() {
				var tmpObj = $(this);
				var val = tmpObj.val();
				if (val) {
					tmpObj.parent().text(val);
				} else {
					tmpObj.parent().text("param");
				}
				tmpObj.remove();

			});
			labelObj.empty();
			labelObj.append(txtObj);
			txtObj.focus();
		});
		$("#paramsDiv").delegate(".del", "click", function() {
			var delme = $(this);
			delme.parent().remove();
		});
	} */


	function showUrlRrles(conf,data) {
		var params = $("#params").val(); 
		var winArgs = "dialogWidth=650px;dialogHeight=500px;help=0;status=0;scroll=1;center=1";
		var url = __ctx + '/platform/system/sysUrlRules/edit.ht';
		url = url.getNewUrl();
		/* var rtn = window.showModalDialog(url, { param : params,data : data }, winArgs);
		if (rtn != undefined) {
			if(typeof(conf)!='undefined'){
				var me=$(conf);
				me.remove();
			}
			var jsonData=JSON2.parse(rtn.sysUrlRule);
			var trObj=$("tr[name='hiddentr']").clone(true);
				trObj.removeClass('hidden');
				trObj.addClass('sysUrlruleTr');
				trObj.attr("name","sysUrlruleTr");
			var descp=$("input[name='tempdescp']",trObj);
				descp.attr("name","ruledescp");
				descp.val(jsonData.descp);
			var enable=$("select[name='tempenable']",trObj);
				enable.attr("name","ruleenable");
				enable.val(jsonData.enable);
			var script=$("textarea[name='tempscript']",trObj);
				script.attr("name","rulescript");
				script.text(jsonData.script);
			var sort=$("input[name='tempsort']",trObj);
				sort.attr("name","rulesort");
				sort.val(jsonData.sort);
			var id=$("input[name='tempid']",trObj);
				id.attr("name","ruleid");
				id.val(jsonData.id);
			$(".ruleTR").after($(trObj));
		} */
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:500,
			width: 750,
			title : '显示url',
			url: url, 
			isResize: true,
			//自定义参数
			data: { param : params,data : data },
			sucCall:function(rtn){
				if (rtn != undefined) {
					if(typeof(conf)!='undefined'){
						var me=$(conf);
						me.remove();
					}
					var jsonData=JSON2.parse(rtn.sysUrlRule);
					var trObj=$("tr[name='hiddentr']").clone(true);
						trObj.removeClass('hidden');
						trObj.addClass('sysUrlruleTr');
						trObj.attr("name","sysUrlruleTr");
					var descp=$("input[name='tempdescp']",trObj);
						descp.attr("name","ruledescp");
						descp.val(jsonData.descp);
					var enable=$("select[name='tempenable']",trObj);
						enable.attr("name","ruleenable");
						enable.val(jsonData.enable);
					var script=$("textarea[name='tempscript']",trObj);
						script.attr("name","rulescript");
						script.text(jsonData.script);
					var sort=$("input[name='tempsort']",trObj);
						sort.attr("name","rulesort");
						sort.val(jsonData.sort);
					var id=$("input[name='tempid']",trObj);
						id.attr("name","ruleid");
						id.val(jsonData.id);
					$(".ruleTR").after($(trObj));
				}
			}
		});
	}
	//处理保存
	function handSave(){
		var sub=[];
		//处理子表
		var subTr=$(".sysUrlruleTr");
		for(var j=0; j<subTr.length; j++){
			var trObj=subTr[j];
			var tempsub={};
			getSubData(tempsub,trObj);
			sub.push(tempsub);
		}
		$("#subData").val(JSON2.stringify(sub));
		$("#sysUrlPermissionItems").ajaxForm({success:showResponse });
		if($("#sysUrlPermissionItems").valid()){
			$("#sysUrlPermissionItems").submit(); 
		} 
		
	};
	
	function getSubData(subJson,trObj){
		subJson.id=$("[name='ruleid']",trObj).val();
		subJson.descp=$("[name='ruledescp']",trObj).val();
		subJson.enable=$("[name='ruleenable'] option:selected",trObj).val();
		subJson.script=$("[name='rulescript']",trObj).text();
		subJson.sort=$("[name='rulesort']",trObj).val();
	}
	
	function showResponse(responseText) {
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (obj.isSuccess()) {
			$.ligerDialog.confirm( obj.getMessage()+$lang.operateTip.continueOp,$lang.tip.confirm, function(rtn) {
				if(!rtn){
					window.location.href = "${ctx}/platform/system/sysUrlPermission/list.ht";
				}else{
					window.location.href = "${ctx}/platform/system/sysUrlPermission/edit.ht";
				}
			});
		} else {
			$.ligerDialog.error(obj.getMessage(),$lang.tip.error);
		}
	}
	
	var win;//将开发帮助窗口缓存起来
	function developHelp(){
		if(!win){
			var obj=$("#develop_help_div");
			win= $.ligerDialog.open({ target:obj , height: 430,width:610, modal :true}); 
		}
		win.show();
	};
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			 <c:choose>
			    <c:when test="${sysUrlPermission.id !=null}">
			        <span class="tbar-label">编辑拦截器</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加拦截器</span>
			    </c:otherwise>			   
		    </c:choose>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group"><a onclick="developHelp()" class="link copyTo"><span></span>配置帮助</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysUrlPermissionItems" method="post" action="save.ht">
				<input type="hidden" name="id" id="id" value="${sysUrlPermission.id}">
				<input type="hidden" name="subData" id="subData" >
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<th width="20%">拦截器名称:</th>
						<td><input type="text" id="descp" name="descp" size="80"
							value="${sysUrlPermission.descp}" validate="{required:true}" class="inputText" /></td>
					</tr>
					<tr>
						<th width="20%">URL地址:</th>
						<td><input id="url" name="url" size="100"
							value="${sysUrlPermission.url}" validate="{required:true}"/></td>
					</tr>
					<tr>
						<th width="20%">参数名称:</th>
						<td>
							<input type="text" name="params" id="params" value="${sysUrlPermission.params}">
							<div class="tipbox"><a class="tipinfo"><span>参数与参数之间以","分割,eg:usrId,name</span></a></div>
							<!-- <div id="paramsDiv">
								<a id="btnAddParameter" class="link add"
									onclick="addParams(this)"><span></span>添加</a>
							</div> -->
						</td>
					</tr>
					<tr>
						<th width="20%">是否启用:</th>
						<td><select name="enable" id="enable" class="select"
							style="width: 50px !important">
								<option value="0"
									<c:if test="${sysUrlPermission.enable==0}">selected</c:if>>
									禁用
								</option>
								<option value="1"
									<c:if test="${sysUrlPermission.enable==1}">selected</c:if>>
									启用
								</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="panel-detail">
			<div class="tbar-title">
				<span class="tbar-label">任务参数</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a id="btnAddParameter" class="link add" onclick="showUrlRrles()"><span></span>添加规则</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
		<div type="subtable" tablename="sys_url_rules" id="sysUrlRules" formtype="form">
			<table class="table-detail" cellpadding="0" cellspacing="0"
				border="0">
				<tbody>
					<tr  class="ruleTR">
						<th >脚本名称</th>
						<th >是否启用</th>
						<th >优先级</th>
						<th >管理</th>
						<th  class="hidden">脚本</th>
					</tr>               
					<c:forEach items="${sysUrlRulesList}" var="sysUrlRules">
						<tr   class="sysUrlruleTr" name="sysUrlruleTr">
							<input type="hidden" name="ruleid" value="${sysUrlRules.id}">
							<td  ><input type="text" name="ruledescp" value="${ sysUrlRules.descp}"></td>
							<td >
								<select name="ruleenable"  class="select"
									style="width: 50px !important">
										<option value="0"
											<c:if test="${sysUrlRules.enable==0}">selected</c:if>>
											禁用
										</option>
										<option value="1"
											<c:if test="${sysUrlRules.enable==1}">selected</c:if>>
											启用
									</option>
								</select>
							</td>
							<td   ><input type="text" name="rulesort" value="${ sysUrlRules.sort}"></td>
							<td >
								<a class="link edit"  href="javascript:;" onclick="javascript:editData(this);"><span></span>编辑</a>
								<a class="link del"  onclick="javascript:delDataId(${sysUrlRules.id},this);"><span></span>删除</a>
							</td>
							<td  class="hidden">
								<textarea type="text"  name="rulescript" rows="10" cols="80">${sysUrlRules.script}</textarea>
							</td>
						</tr>
					</c:forEach>
					<tr  name="hiddentr" class="hidden">
						<input type="hidden" name="tempid">
						<td  ><input type="text" name="tempdescp"></td>
						<td  >
							<select name="tempenable"  class="select"
								style="width: 50px !important">
									<option value="0">
										禁用
									</option>
									<option value="1">
										启用
								</option>
							</select>
						</td>
						<td ><input type="text" name="tempsort" ></td>
						<td >
						<a class="link edit"  onclick="javascript:editData(this);"><span></span>编辑</a>
						<a class="link del"  onclick="javascript:delData(this);"><span></span>删除</a>
						</td>
						<td  class="hidden">
						<textarea type="text"  codemirror="true" 
								mirrorheight="190px" name="tempscript" rows="10" cols="80">
						</textarea>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
	</div>
	<div class="hidden" id="template">
		<span spanname='param' class='backgrounddiv'> <lable
				class='paramLable' param_="hiddenParam">param</lable>
				<a href='javascript:void(0);' class="link del"><span></span>删除</a>
		</span>
	</div>
	<div id="develop_help_div" class="hidden" style="font-size:13px;">
		<table id="callCodeTable" class="table-grid table-list">
			<tr>
				<td>URL地址：</td>
				<td style="text-align: left;">
					要拦截的目标地址
				</td>
			</tr>
			<tr>
				<td>参数名称：</td>
				<td style="text-align: left;">
					多个参数以","分割,eg:userId,name。表示要拦截上述【URL地址】中的哪些参数<br>
					如果要拦截所有的参数，则不需要填写
				</td>
			</tr>
			<tr>
				<td>规则的编写：</td>
				<td style="text-align: left;">
					①可以编写多条规则，按优先级排序，优先级越高越先匹配<br>
					②规则脚本中可以使用map变量获取【参数名称】所配置的url参数，比如map.get("userId")<br>
					③规则脚本需要返回boolean值，如下：<br>
					String currentUserId = scriptImpl.getCurrentUserId().toString();<br>
  					return currentUserId.equals(map.get("userId"));<br>
  					④如果有多个规则，则会按优先级降序检查规则<br>
  					若有一个规则返回true，则表示可以访问当前页面，否则无权访问
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
