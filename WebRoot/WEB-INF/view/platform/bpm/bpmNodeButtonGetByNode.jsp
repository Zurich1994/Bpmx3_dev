<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html ng-app="bpmDataTemplateApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作按钮设置</title>
<%@include file="/commons/include/get.jsp" %>
<base target="_self"> 
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
	<script type="text/javascript" src="${ctx}/js/angular/module/DataRightsAppsher.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/controller/bpmDataTemplateController.js"></script>
<script type="text/javascript"><!--
var aryId= $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
var DataRightsJson=${DataRightsJson};
var bpmFormTableJSON = ${bpmFormTableJSON};
var actdefIdsher = '${actdefIdsher}';
var defIdsher = ${defIdsher};
var nodeIdsher = '${nodeIdsher}';
var templateIdsher = '${templateIdsher}';
var buttonFlagsher =' ${buttonFlagsher}';
var formKeysher = '${formKeysher}';
var bpmFormTableIdsher = ${bpmFormTableIdsher};
var manageField = ${manageField};
var list ;
var lasttime;
var indexsher;
var o;
var cansher;
var len;
var op ;
$(function(){
    if(templateIdsher=="none"&&formKeysher=="none"){
            $.ligerDialog.error("该节点未绑定任何表单,请绑定!", function (){
                 var url1 = __ctx + "/platform/bpm/bpmNodeSet/list.ht?defId=${defId}";
                        $.gotoDialogPage(url1);
            });
     }
    $("#waibuadd").click(function(){
            var url=__ctx + "/platform/bpm/bpmNodeButton/editnew.ht?defId=${defId}&nodeId=${nodeId}&buttonFlag=${buttonFlag}";  
            $.gotoDialogPage(url);
    });
    $("#edits").click(function(){
        var url=__ctx+"/platform/form/bpmDataTemplate/editsher.ht?templateIdsher=${templateIdsher}&formKey=${formKeysher}&tableId=${tableIdsher}&defIdsher=${defId}&nodeIdsher=${nodeId}&buttonFlagsher=${buttonFlag}";
            $.gotoDialogPage(url);
    });
    $("#neibuadd").click(function(){
        if(templateIdsher=="none"&&formKeysher!="none"){
           $.ligerDialog.error("该节点绑定的表单为在线表单!", function (){
            });
        }else if(templateIdsher!="none"){
            manageField = ${manageField};
            var url=__ctx+"/platform/form/bpmDataTemplate/editys.ht?id=${templateIdLong}&formKey=${formKeysher}&tableId=${tableIdsher}&name=${templateName}";
            $.gotoDialogPage(url);
        }
    });
    $("a.save").unbind("click").click(save);
    $("a.link.init").unbind("click");
    init();   
});
function check_all(obj, cName) { 
  
  var checkboxs = document.getElementsByName(cName);  
  for ( var i = 0; i < checkboxs.length; i+=1) {  
    checkboxs[i].checked = obj.checked;  
  }  
};
function delDef(){	
		var check = document.getElementsByName("check");
		var len=check.length;
		var id="";
		//没有选择记录
	    var aryId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
		if(aryId.length == 0){
		    $.ligerDialog.warn("请先选择按钮！");
			return false;
		}
		else{
            var myArray=new Array();
            for(var i=0;i<len;i++){
                if(check[i].checked){
                    myArray[i]=check[i].value;  
          		}
            }
			var url =__ctx + "/platform/bpm/bpmNodeButton/delall.ht?id="+myArray+"&nodeId=${nodeId}&buttonFlag=${buttonFlag}";
				$.gotoDialogPage(url);     
		}
};
function delDefnew(){	
		var check = document.getElementsByName("checknew");
		var len=check.length;
		var id="";
		//没有选择记录
	    var aryId = $("input[type='checkbox'][disabled!='disabled'][class='pknew']:checked");
		if(aryId.length == 0){
			$.ligerDialog.warn("请先选择按钮！");
			return false;
		}
		else{
                var myArray=new Array();
                 for(var i=0;i<len;i++){
                       if(check[i].checked){
                    myArray[i]=check[i].value;  
            }
            }
            
			var url =__ctx + "/platform/bpm/bpmNodeButton/delall.ht?id="+myArray+"&nodeId=${nodeId}&buttonFlag=${buttonFlag}";
				$.gotoDialogPage(url);     
			}
};
function delsherlockinside(id,templateId){
	$.ligerDialog.confirm('是否彻底在模板内删除按钮', function (yes)
                     {
                         if (yes) {
                         	var url = __ctx + "/platform/bpm/bpmNodeButton/del.ht?id="+id+"&templateIdsher="+templateId;
                         	$.gotoDialogPage(url);  
                         }else{
                           var myArray=new Array();
                           myArray[0] = id;
                           var url =__ctx + "/platform/bpm/bpmNodeButton/delall.ht?id="+myArray+"&nodeId=${nodeId}&buttonFlag=${buttonFlag}";
								$.gotoDialogPage(url);  
                         }
                  });
};
function save(){
	var aryId=[];
	$("tr.trNodeBtn").each(function(){
		aryId.push($(this).attr("id"));
	});
	if(aryId.length==0){
		$.ligerDialog.warn("没有定义按钮!",'提示信息');
		return;
	}
	var ids=aryId.join(",");
	var url="sort.ht";
	$.post(url,{ids:ids},function(responseText){
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){//成功
			$.ligerDialog.success("修改排序成功!",'提示信息',function(){
				var nurl =__ctx + "/platform/bpm/bpmNodeButton/getByNode.ht?defId=${defId}&nodeId=${nodeId}&buttonFlag=${buttonFlag}";
				$.gotoDialogPage(nurl);
				
			});
		}
		else{
			$.ligerDialog.err("出错信息","修改排序失败",obj.getMessage());
		}
	})
};


function sortTr(obj,isUp) {
	var thisTr = $(obj).parents("tr");
	if(isUp){
		var prevTr = $(thisTr).prev();
		if(prevTr){
			thisTr.insertBefore(prevTr);
		}
	}
	else{
		var nextTr = $(thisTr).next();
		if(nextTr){
			thisTr.insertAfter(nextTr);
		}
	}
};


function init(){
	$("a.link.init").click(function(){
		var ele=this;
		$.ligerDialog.confirm('确认初始化按钮吗？','提示信息',function(rtn) {
			if(rtn){
				var url =__ctx + "/platform/bpm/bpmNodeButton/init.ht?defId=${defId}&nodeId=${nodeId}&buttonFlag=${buttonFlag}";
				$.gotoDialogPage(url);
			}
		});
		return false;
	});
};
--></script>

</head>
<body ng-controller="bpmDataTemplateCtrl">
  <c:if test="${buttonFlag}">  
	    <jsp:include page="incDefinitionHead.jsp">
	   		<jsp:param value="节点操作按钮" name="title"/>
		</jsp:include>
	    <f:tab curTab="button" tabName="flow"/>
   </c:if>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">操作按钮列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="btnUpd" action="sort.ht"><span></span>保存排序</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a  class="link init" id="bntInit" href="javascript:;"><span></span>初始化按钮</a></div>
					<div class="group"><a  class="link back"  href="list.ht?defId=${defId}"><span></span>返回</a></div>
				</div>	
			</div>
		</div>
		</div>
		<div class="panel-body" id="newBottList">
		  <div class="panel-toolbar" style="height:26px;" >
		    <div class="toolBar">
		       <table cellpadding="0" cellspacing="0" border="0" width="100%" >
		       <tr >
				<td width="50%"><a class="link add" id="neibuadd" href="javascript:;"><span></span>查看按钮详细</a>
				
				<td width="50%"><a class="link add" id="waibuadd" href="javascript:;"><span></span>添加外部按钮</a>
				<a class="link del"  onclick="delDef()"><span></span>批量删除外部按钮</a></td>
				
				</tr>
		      </table>
		      </div>
		  </div>
		
		<div style="float:left;width:50%;height:100%;overflow-x:auto;overflow-y:scroll;" >
			<table cellpadding="1" cellspacing="1" class="table-grid table-list">
				<thead>
				<tr>
				    <th></th>
				    <th></th>
					<th></th>
					<th style="text-align:center;">内部按钮列表</th>
					<th></th>
					<th></th>
				</tr>
				<tr>
				<th style="width:30px;">
				<input type="checkbox" name="checknew" value="${item.id}" onclick="check_all(this,'checknew')"/>
				</th>
					<th>序号</th>
					<th>按钮名</th>
					<th>操作类型</th>
					<th>排序</th>
					<th>
						管理
					</th>
				</tr>
				</thead>
			
				<c:forEach items="${neibuList}" var="item" varStatus="status">
				<tr class="trNodeBtn" id="${item.id}">
				<!-- 
				<input type="text" value="${item.id}"/>
				 -->
				 <c:if test="${item.operatortype>18 }">
				  <td style="width:30px;">
				 <input type="checkbox"  name="check" value="${item.id}" class="pk" />
				 </td>  
					<td style="text-decoration: none;text-align:center">
						${status.index +1}
					</td>
					<td style="text-decoration: none;text-align:center">
						${item.btnname }
					</td>
					<td nowrap="nowrap" style="text-decoration: none;text-align:center">
						<c:choose>
							<c:when test="${item.isstartform==1 }">
								<c:choose>
								    <c:when test="${item.operatortype==19 }">数据行内编辑按钮</c:when>
								    <c:when test="${item.operatortype==20 }">数据行内删除按钮</c:when>
								    <c:when test="${item.operatortype==21 }">数据行内明细按钮</c:when>
								    <c:when test="${item.operatortype==22 }">行内单行启动按钮</c:when>
								    <c:when test="${item.operatortype==23 }">行内单行重启按钮</c:when>
								    <c:when test="${item.operatortype==24 }">数据行内自定义按钮</c:when>
								    <c:when test="${item.operatortype==25 }">数据行外增加按钮</c:when>
								    <c:when test="${item.operatortype==26 }">数据行外删除按钮</c:when>
								    <c:when test="${item.operatortype==27 }">数据行外导出按钮</c:when>
								    <c:when test="${item.operatortype==28 }">数据行外导入按钮</c:when>
								    <c:when test="${item.operatortype==29 }">数据行外打印按钮</c:when>
								    <c:when test="${item.operatortype==30 }">行外批量启动按钮</c:when>
								    <c:when test="${item.operatortype==31 }">行外批量重启按钮</c:when>
								    <c:when test="${item.operatortype==32 }">数据行外自定义按钮</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
								   <c:when test="${item.operatortype==19 }">数据行内编辑按钮</c:when>
								    <c:when test="${item.operatortype==20 }">数据行内删除按钮</c:when>
								    <c:when test="${item.operatortype==21 }">数据行内明细按钮</c:when>
								    <c:when test="${item.operatortype==22 }">行内单行启动按钮</c:when>
								    <c:when test="${item.operatortype==23 }">行内单行重启按钮</c:when>
								    <c:when test="${item.operatortype==24 }">数据行内自定义按钮</c:when>
								    <c:when test="${item.operatortype==25 }">数据行外增加按钮</c:when>
								    <c:when test="${item.operatortype==26 }">数据行外删除按钮</c:when>
								    <c:when test="${item.operatortype==27 }">数据行外导出按钮</c:when>
								    <c:when test="${item.operatortype==28 }">数据行外导入按钮</c:when>
								    <c:when test="${item.operatortype==29 }">数据行外打印按钮</c:when>
								    <c:when test="${item.operatortype==30 }">行外批量启动按钮</c:when>
								    <c:when test="${item.operatortype==31 }">行外批量重启按钮</c:when>
								    <c:when test="${item.operatortype==32 }">数据行外自定义按钮</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</td>
					<td style="text-decoration: none;text-align:center">
						<a alt='上移' href='#' class='link moveup' onclick='sortTr(this,true)'>&nbsp;</a>
				        <a alt='下移' href='#' class='link movedown' onclick='sortTr(this,false)'>&nbsp;</a>
					</td>
					<td style="text-decoration: none;text-align:center">
				   <button class="link edit" id="detailed" ng-click="service.checkurl(${item.id})">查看参数</button>
					<button class="link save" id="bangdingcanshu"  ng-click = "service.writein(${item.id},${status.index})" >
					绑定列参数
  					</button>
					</td>
					</c:if>
				</tr>
				
				</c:forEach>
			</table>
		</div>
	
		<div style="float:right;width:50%;height:100%;overflow-x:auto;overflow-y:scroll;">
			<table cellpadding="1" cellspacing="1" class="table-grid table-list">
				<thead>
				<tr>
				    <th></th>
				    <th></th>
					<th></th>
					<th style="text-align:center;">外部按钮列表</th>
					<th></th>
					<th></th>
					
				</tr>
				<tr>
				<th style="width:30px;">
				<input type="checkbox" name="check" value="${item1.id}" class="pk" onclick="check_all(this,'check')"/>
				</th>
				
					<th>序号</th>
					<th>按钮名</th>
					<th>操作类型</th>
					<th>排序</th>
					<th>
						管理
					</th>
				</tr>
				</thead>
			
				
				<c:forEach items="${waibuList}" var="item1" varStatus="status1">
				<tr class="trNodeBtn" id="${item1.id}">
				<!--  
				<input type="text" value="${item1.id}"/>
				-->
				<c:if test="${item1.operatortype<19 }">
				<td style="width:30px;">
				 <input type="checkbox"  name="check" value="${item1.id}"  class="pk"/>
				 </td>
					<td>
						${status1.index +1}
					</td>
					<td>
						${item1.btnname }
					</td>
					<td nowrap="nowrap">
						<c:choose>
							<c:when test="${item1.isstartform==1 }">
								<c:choose>
									<c:when test="${item1.operatortype==1 }">启动流程</c:when>
									<c:when test="${item1.operatortype==2 }">流程示意图</c:when>
									<c:when test="${item1.operatortype==3 }">打印</c:when>
									<c:when test="${item1.operatortype==4 }">短信</c:when>
									<c:when test="${item1.operatortype==5 }">邮件</c:when>
									<c:when test="${item1.operatortype==6 }">保存草稿</c:when>
								    <c:when test="${item1.operatortype==14 }">Web签章</c:when>
								    <c:when test="${item1.operatortype==15 }">手写签章</c:when>
								    <c:when test="${item1.operatortype==18 }">自定义类型</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${item1.operatortype==1 }">同意</c:when>
									<c:when test="${item1.operatortype==2 }">反对</c:when>
									<c:when test="${item1.operatortype==3 }">弃权</c:when>
									<c:when test="${item1.operatortype==4 }">驳回</c:when>
									<c:when test="${item1.operatortype==5 }">驳回到发起人</c:when>
									<c:when test="${item1.operatortype==6 }">交办</c:when>
									<c:when test="${item1.operatortype==7 }">补签</c:when>
									<c:when test="${item1.operatortype==8 }">保存表单</c:when>
									<c:when test="${item1.operatortype==9 }">流程示意图</c:when>
									<c:when test="${item1.operatortype==10 }">打印</c:when>
									<c:when test="${item1.operatortype==11}">审批历史</c:when>
									<c:when test="${item1.operatortype==14 }">Web签章</c:when>
									<c:when test="${item1.operatortype==15 }">手写签章</c:when>
									<c:when test="${item1.operatortype==16 }">沟通</c:when>
									<c:when test="${item1.operatortype==17 }">加签</c:when>
								    <c:when test="${item1.operatortype==18 }">自定义类型</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
						
					</td>
					<td>
						<a alt='上移' href='#' class='link moveup' onclick='sortTr(this,true)'>&nbsp;</a>
				        <a alt='下移' href='#' class='link movedown' onclick='sortTr(this,false)'>&nbsp;</a>
					</td>
					<td>
						<a class="link edit" href="edit.ht?id=${item1.id}&defId=${defId}&nodeId=${nodeId}&buttonFlag=${buttonFlag}">编辑</a>
						<a class="link del" href="del.ht?id=${item1.id}&buttonFlag=${buttonFlag}">删除</a>
					</td>
					</c:if>
				</tr>
				</c:forEach>
			</table>
		</div>
		
	</div><!-- end of panel-body -->				
</div> <!-- end of panel -->
</body>
</html>





