<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html ng-app="bpmDataTemplateApp">
<head>
	<title>数据模板设置</title>
	<%@include file="/commons/include/form.jsp" %>
	<style type="text/css">
    .container {position:absolute; display:none; padding-left:10px;}
    .shadow {float:left;}
    .frame {position:relative; background:#1874CD; padding:6px; display:block;
    	border-radius: 5px;
        -moz-box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.6);
        -webkit-box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.6);
        box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.6);
    }
    .frame2 {position:relative; background:#DBDBDB; padding:6px; display:block;
        -moz-box-shadow: 3px 3px 10px rgba(0, 0, 0, 0);
        -webkit-box-shadow: 3px 3px 10px rgba(0, 0, 0, 0);
        box-shadow: 3px 3px 10px rgba(0, 0, 0, 0);
    }
    .clear {clear:left;}
    label,a {font-size:13px;color:#4f6b72;}
    .language {font-size:13px;color:#4f6b72;border:1px solid #4f6b72;height:20px;display: inline-block;
	min-height:25px;
	width: 260px;
	font-size: 12px;
	line-height: 1.428571429;
	color: #555555;
	vertical-align: middle;
	background-color: #ffffff;
	background-image: none;
	border: 1px solid #cccccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	-webkit-transition: border-color ease-in-out .15s, box-shadow
	 ease-in-out .15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;}
    div.frame div {margin-bottom:5px;}
    div.frame div.foot {margin-top:10px;}
    div.frame label {margin: 0 10px 0 5px;}
    div.frame a:link,div.frame span a:visited {
        text-decoration:none;
    }
</style>
<!--[if IE]>
<style type="text/css">
    .container {padding-left:14px;}
    .frame {left:4px; top:4px;}
    .shadow {background:#000; margin:-2px 0px 0px 0px; filter:progid:DXImageTransform.Microsoft.Blur(PixelRadius='5', MakeShadow='true', ShadowOpacity='0.60');}
</style>
<![endif]-->
	<script type="text/javascript" src="${ctx}/js/lang/view/platform/form/zh_CN.js"></script>
	<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
	<link href="${ctx}/styles/default/css/hotent/dataRights.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.fix.clone.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js" ></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/lib/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/lib/util/matchbrackets.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/mode/groovy/groovy.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/AddResourceDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/BpmDefinitionDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/angular.min.js"></script>
	<script type="text/javascript"	src="${ctx}/js/angular/service/baseServices.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/module/DataRightsAppNewSherlock.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/controller/bpmDataTemplateController.js"></script>
	<script type="text/javascript">
	var list ;
	var lasttime;
	var indexsher;
	var o;
	var cansher,bangcansher;
	var len;
	var op ;
	var DataRightsJson=${DataRightsJson};
	var templateAlias = '${templateAlias}';
	var bpmFormTableIdsher = ${bpmFormTableIdsher};
	var bpmFormTableJSON=${bpmFormTableJSON};
	var manageField = ${manageField};
	var fieldnamelistsher = '${fieldnamelistsher}';
	var scanstr = "";
	function _$(id) {
        return document.getElementById(id);
    }
    window.onload = function() {
        document.onclick = function(e) {
        var classname = e.target.className.split(" ");
        if(classname[0]!="location"&&classname[0]!="foot"&&classname[0]!="frame"&&classname[0]!="frame2"&&classname[0]!="language"&&classname[0]!="checkbox"&&classname[0]!="label"&&classname[0]!="div"){
        	$(".container").hide();
        }
        }
        _$("container").onclick = function(e) {
            e = e || event;
            stopFunc(e);
        }
    }
	$(function() {
		fieldnamelistsher = fieldnamelistsher.replace("[","");
		fieldnamelistsher = fieldnamelistsher.replace("]","");
		fieldnamelistsher = fieldnamelistsher.replaceAll(" ","");
		cansher = fieldnamelistsher.toString().split(",");
			for(var j = 0;j<manageField.length;j++){
			    bangcansher = manageField[j].scan.toString().split(",");
					for(var i = 0;i<cansher.length;i++){
				   		if($.inArray(cansher[i].toString(),bangcansher)>=0){
							scanstr +="<div class=\"div\" ><input class=\"checkbox\" type=\"checkbox\" checked=\"checked\" id=\"location"+i+"\"/><label class=\"label\" for=\"location"+i+"\">"+cansher[i]+"</label></div>";
				    	}else{
				    		scanstr +="<div class=\"div\" ><input class=\"checkbox\" type=\"checkbox\" id=\"location"+i+"\"/><label class=\"label\" for=\"location"+i+"\">"+cansher[i]+"</label></div>";
				    	}
				   }
		   	  $("#frame2"+j).html(scanstr); 
		   	  scanstr = "";
		    }
	});
	function makehtml(index){
		for(var i = 0;i<cansher.length;i++){
				scanstr +="<div class=\"div\" ><input class=\"checkbox\" type=\"checkbox\" id=\"location"+i+"\"/><label class=\"label\" for=\"location"+i+"\">"+cansher[i]+"</label></div>";
		}
		$("#frame2"+index).html(scanstr); 
		scanstr = "";
	}
	function radiochange(obj){
		indexsher = obj.value;
		if(indexsher!=lasttime){
			$("button[value="+lasttime+"]").attr("disabled","true");
			$("button[value="+lasttime+"]").attr("class","inputText");
			$("input[name=butype"+lasttime+"]").attr("disabled","true");
		}
		$("input[name=butype"+indexsher+"]").removeAttr("disabled"); 
		$("input[name=butype"+indexsher+"]").removeAttr("disabled"); 
		$("button[value="+indexsher+"]").removeAttr("disabled"); 
		$("button[value="+indexsher+"]").attr("class","bt-select");
		lasttime = indexsher;
	};
	function butype(flag){
		if(flag==1){
			$("#finname"+indexsher).attr("disabled","true");
		  	$("#foutname"+indexsher).removeAttr("disabled"); 
		}else if(flag==2){
			$("#foutname"+indexsher).attr("disabled","true");
			$("#finname"+indexsher).removeAttr("disabled");
		}
		if(indexsher!=lasttime){
			$("#finname"+indexsher).attr("disabled","true");
			$("#foutname"+indexsher).attr("disabled","true");
		}
	}
	$('body').click(function(e) {
   			if(e.target.class != 'container')
       $(".container").hide();
})
</script>
</head>
<body ng-controller="bpmDataTemplateCtrl" >
	<div class="panel" ng-show="hasInitTab">
		<div class="hide-panel">
			<div class="panel-top">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">数据模板设置</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link save"  href="javascript:;" ng-click="save()"><span></span>保存</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link preview" href="javascript:;" ng-click="preview()"><span></span>预览</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link edit" href="javascript:;" ng-click="editTemplate()"><span></span>编辑模板</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link collapse" href="javascript:;" ng-click="addToResource()"><span></span>添加为菜单</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link close" href="javascript:;" onclick="window.location.href =  __ctx+'/ywsjmk/ywsjmk/ywsjmb/list.ht';"><span></span>关闭</a></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="dataRightsForm" >
				<div id="tab"> 
					<!-- 基本信息  start-->
					<div tabid="baseSetting"  title="基本信息">
						<div >
							<div class="tbar-title">
								<span class="tbar-label">基本信息</span>
							</div>
							<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main" style="border-width: 0!important;">
								<tr>
									<th  width="10%">模版名称:</th>
									<td>
										<input type="text" ng-model="dataRightsJson.name" onblur="setStyle(this.id)" id="name" class="inputText" value="${name}" style="width:210px;margin-right:2px;" />
										<!--  <input type="button" value="验证模板是否处在" id="checkNameButton">-->
									</td>
								</tr>
								<tr>
									<th  width="10%">模板ID:</th>
									<td>
										<input type="text" ng-model="dataRightsJson.id"  readonly="readonly" class="inputText" value="${id}" style="width:210px;margin-right:2px;" id="templateId"/>
									</td>
								</tr>
								<tr>
									<th  width="10%">表单别名:</th>
									<td>
										<input type="text" ng-model="dataRightsJson.formKey"  readonly="readonly" class="inputText" validate="{required:true}" style="width:210px;margin-right:2px;" id="formDesc"/>
									</td>
								</tr>
								<tr>
									<th  width="10%">绑定流程:</th>
									<td>
										<input type="text" name = "subjectsher" id="subjectsher" value="${DataRightsJson.subject}" readonly="readonly"  class="inputText"  style="width:210px;margin-right:2px;" />
										<a style="margin-right:5px;" ng-click="selectFlow()" class="button" href="javascript:;">
											<span class="icon ok"></span>
											<span>选择</span>
										</a>
										<a  ng-click="cancel()" class="button" href="javascript:;">
											<span class="icon cancel"></span>
											<span>重置</span>
										</a>
									</td>
								</tr>
								<tr>
									<th >是否分页:</th>
									<td>
										<input type="radio" ng-model="dataRightsJson.needPage" value="0" >
										不分页
										<input type="radio" ng-model="dataRightsJson.needPage" value="1" >
										分页
										<span style="color:red;" ng-if="dataRightsJson.needPage==1">
											分页大小：
											<select ng-model="dataRightsJson.pageSize" >
												<option value="5"  >5</option>
												<option value="10" >10</option>
												<option value="15" >15</option>
												<option value="20" >20</option>
												<option value="50" >50</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<th>是否初始查询:</th>
									<td>
										<select ng-model="dataRightsJson.isQuery"  validate="{required:true}">
											<option value="0"  >是</option>
											<option value="1"  >否</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>
										没有过滤条件
										<br/>
										是否需要默认过滤:
									</th>
									<td>
										<select ng-model="dataRightsJson.isFilter"  validate="{required:true}">
											<option value="0"  >是</option>
											<option value="1"  >否</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>数据模板:</th>
									<td>
										<select ng-model="dataRightsJson.templateAlias"  validate="{required:true}" id="templateType">
											<option value="">--请选择数据模板--</option>
											<c:forEach items="${templates}" var="template">
												<option value="${template.alias}">${template.templateName}</option>
											</c:forEach>
										</select>
										<div class="tipbox">
											<a href="javascript:;" class="tipinfo">
												<span>添加更多数据模板，请到自定义表单模板中添加类型为"业务数据模板"的模板</span>
											</a>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div tabid="displaySetting"  title="显示列字段">
						<display-setting ></display-setting>
					</div>
					<div tabid="conditionSetting"  title="查询条件字段">
						<condition-setting ></condition-setting>
					</div>
					<div tabid="sortSetting"  title="排序字段">
						<sort-setting></sort-setting>
					</div>
					<div tabid="filterSetting"  title="过滤条件">
						<filter-setting ></filter-setting>
					</div>
					<div tabid="exportSetting"  title="导出字段">
						<export-setting ></export-setting>
					</div>
					<c:if test="${templateAlias eq 'dataTemplateList'}" >
					<div tabid="manageSettingFz"  title="功能按钮">
						<manage-setting-fz></manage-setting-fz>
					</div>
					</c:if>
					<c:if test="${templateAlias eq 'gendataTemplate'}" >
					<div tabid="manageSetting"  title="功能按钮">
						<manage-setting ></manage-setting>
					</div>
					</c:if>
				</div>
			</form>
		</div>
		<!-- end of panel-body -->
	</div>
</body>
</html>