<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.hotent.platform.model.bpm.BpmDefRights"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大然流程定义扩展管理</title>
<%@include file="/commons/include/get.jsp" %>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ImportExportXmlUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/tabOperator.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowRightDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowUtil.js" ></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/htCatCombo.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/ajaxgrid.js"></script>
<style>
.ok_button{
 background:#57E1DB repeat-x;
 padding-top:3px;
 border-top:1px solid #708090;
 border-right:1px solid #708090;
 border-bottom:1px solid #708090;
 border-left:1px solid #708090; 
 width:180px;
 height:auto;
 font-size:10pt;
 cursor:hand;
}
</style>
<script type="text/javascript">
var defID;
	var dialogCategory
	$(function(){
		dialogCategory = $("#dialogCategory").html();
	});
	// 导出流程定义
	function exportXml(){	
		var bpmDefIds = ImportExportXml.getChkValue('pk');
		if (bpmDefIds ==''){
			$.ligerDialog.warn('还没有选择,请选择一项流程定义!','提示信息');
			return ;
		}

		var url=__ctx + "/platform/bpm/bpmDefinition/export.ht?bpmDefIds="+bpmDefIds;
		ImportExportXml.showModalDialog({url:url,height:400});
	}
	
	//导入流程定义
	function importXml(){
		var url=__ctx + "/platform/bpm/bpmDefinition/import.ht";
		ImportExportXml.showModalDialog({url:url});
	}
	
	function cleanData(defId){
		$.ligerDialog.confirm("确定清除数据?",function(btn){
			if(!btn) return;
			$.post('cleanData.ht',{defId:defId},function(data){
	 			var obj=new com.hotent.form.ResultMessage(data);
	 			if(obj.isSuccess()){
	 				$.ligerDialog.success(obj.getMessage(),'清除数据成功');
	 			}else{
	 				$.ligerDialog.error('出错信息',"清除数据失败",obj.getMessage());
	 			}
			});
		});
	}
	
	
	var dialog=null;
	//重设分类
	function setCategory(){
		$("#dialogCategory").html(dialogCategory);
		$('.catComBo').each(function() {
			$(this).htCatCombo();
		});
		
		var defKeys=getDefKey();
		if(defKeys==""){
			$.ligerDialog.warn('还没有选择,请选择一项记录!','提示');
			return;
		}
		if(dialog==null){
			dialog=$.ligerDialog.open({title:"设置分类",target:$("#dialogCategory"),width:400,height:250,buttons:
				[ {text : '确定',onclick: setCategoryOk},
				{text : '取消',onclick: function (item, dialog) {
						dialog.hide();
					}
				}]
			});	
		}
		dialog.show();
	}
	
	function setCategoryOk(item, dialog){
		var typeId=$("#typeId").val();
		if(typeId==""){
			$.ligerDialog.warn('请选择分类','提示');
			return;
		}
		var defKeys=getDefKey();
		
		var params={defKeys:defKeys,typeId:typeId};
		var url="${ctx}/platform/bpm/bpmDefinition/setCategory.ht";
		$.post(url,params,function(responseText){
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(obj.isSuccess()){
				$.ligerDialog.success('操作成功!','提示',function(){
					dialog.hide();
					var url=location.href.getNewUrl();
					location.href=url;
				});
			}
			else{
				$.ligerDialog.err('提示','操作失败!',obj.getMessage());
			}
		});
	}
	function getDefKey(){
		var aryChk=$("input:checkbox[name='defId']:checked");
		if(aryChk.length==0) return "";
		var aryDefKey=[];
		aryChk.each(function(){
			aryDefKey.push($(this).attr("defKey"));
		});
		return aryDefKey.join(",");
	}
	 function getDefId(){
		 var aryChk=$("input:checkbox[name='defId']:checked");
			if(aryChk.length==0) return "";
			var aryDefId=[];
			aryChk.each(function(){
				aryDefId.push($(this).val());
			});
			return aryDefId.join(",");
	 }
	
	
	function abatchGrant(){
		var defKeys=getDefKey();
		var defIds=getDefId();
		if(defKeys==""||defIds==""){
			$.ligerDialog.warn('还没有选择,请选择一项记录!','提示');
			return;
		}
		FlowRightDialog(defIds,0,defKeys);
	}
	
	
	//删除流程
	function delDef(){	
		var action = "del.ht";
		var $aryId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
		
		//没有选择记录
		if($aryId.length == 0){
			$.ligerDialog.warn("请选择记录！");
			return false;
		}
		
		//是否有删除权限的过滤
		var str = "";
		var delId ="" ;
		$aryId.each(function(){
			var me = $(this);
			var right_m_del = me.attr("right_m_del");
			if(right_m_del!='Y'){
				me.removeAttr("checked");
				var subject = me.attr("subject");
				str += subject+"、"
			}else{
				delId += me.val()+",";
			}
		
		});
		
		//判断是否要提交删除
		var keyName = "defId"
		var url=action +"?" +keyName +"=" +delId ;
		if(str!=""){
			str = "["+str.substring(0,str.length-1)+"]";
			if(delId==""){
				str += "这些流程没有删除权限,不可删除！";
				$.ligerDialog.warn(str);
			}else{
				delId = delId.substring(0,delId.length-1);
				str += "这些流程没有删除权限,不可删除！还要删除其它剩下的流程吗？";
				$.ligerDialog.confirm(str,"消息提示",function(rtn) {
					if(rtn) {
						var form=new com.hotent.form.Form();
						form.creatForm("form", action);
						form.addFormEl(keyName, delId);
						form.submit();
					}
				});
			}
			
		}else{
			$.ligerDialog.confirm("确认删除流程吗？","消息提示",function(rtn) {
				if(rtn) {
					var form=new com.hotent.form.Form();
					form.creatForm("form", action);
					form.addFormEl(keyName, delId);
					form.submit();
				}
			});
		} 
		return false;
	}
	function myfun(){
	 alert("已进入");
	 var $aryId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
	 if($aryId.length == 0){
			$.ligerDialog.warn("请选择记录！");
			return false;
	  }
	 else if($aryId.length >1){
			$.ligerDialog.warn("只能选择一项~！");
			return false;
	  }else{
	  	//var delId ="" ;
	  //	var me = $(this);
	  	//de
	  	var obj = document.getElementsByTagName("input");
	  	
   			 for(var i=0; i<obj.length; i ++){
      		  if(obj[i].checked){
         		   defID = obj[i].value;
                     document.getElementById("sherlock").href="${ctx}/js/mydialog/definition_manager.jsp?defbId="+defID;
                  var $button = $("#sherlock");
                  $("input defId").val(defId);
                  
                  //var h = $button.attr("defId");
                 // alert(h); 
       		 }
   		 }
   		 alert(defID);
   		var url="${ctx}/js/mydialog/definition_manager.jsp?defbId="+defID; 
   		window.open(url);
   		//openLinkDialog({scope:this,isFull:false}).open(url);
	  }
	// alert(${bpmDefinitionItem.defId});
	}
</script>
</head>
<body>      
	<div class="panel">
	<div class="panel-top">	
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
					
					<a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reload"   onclick="window.location.reload()"><span></span>刷新</a></div>	
					<div class="l-bar-separator"></div>
					<div class="group"><input type="button" id = "sherlock"  class="ok_button" value="确定"    onclick="myfun();"/></div>									
				</div>	
			</div>
			<div class="panel-search">
			       
				<form id="searchForm" method="post" action="list.ht">	    
					<ul class="row">
						<input type="hidden" name="typeId" value="${param['typeId']}" title="流程分类ID"></input>
						<li><span class="label">标题:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/></li>
						<!-- 
						<span class="label">流程分类:</span><input type="text" name="Q_typeName_S" class="inputText" value="${param['Q_typeName_S']}"/>
						 -->
						<li><span class="label">流程定义Key:</span><input type="text" name="Q_defKey_SL"  class="inputText" value="${param['Q_defKey_SL']}"/></li>
						<li><span class="label">描述:</span><input type="text" name="Q_descp_SL" class="inputText" maxlength="125" value="${param['Q_descp_SL']}"/></li>
						<li><span class="label">状态:</span><select name="Q_status_L" class="select" value="${param['Q_status_L']}">
							<option value="">所有</option>
							<option value="1" <c:if test="${param['Q_status_L'] == '1'}">selected</c:if>>已发布</option>
							<option value="0" <c:if test="${param['Q_status_L'] == '0'}">selected</c:if>>未发布</option>
							<option value="4" <c:if test="${param['Q_status_L'] == '4'}">selected</c:if>>测试</option>
							<option value="2" <c:if test="${param['Q_status_L'] == '2'}">selected</c:if>>禁用</option>
							<option value="3" <c:if test="${param['Q_status_L'] == '3'}">selected</c:if>>禁用实例</option>
						</select>
						</li>
						<div class="row_date">
						<li><span class="label">创建时间:</span><input type="text" name="Q_createtime_DL"  class="inputText date" value="${param['Q_createtime_DL']}"/></li>
						<li><span class="label">至</span><input name="Q_endcreatetime_DG" class="inputText date" value="${param['Q_endcreatetime_DG']}"/></li>
						</div>
						
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
		    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="bpmDefinitionList" id="bpmDefinitionItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="defId" value="${bpmDefinitionItem.defId}" 
						  			defKey="${bpmDefinitionItem.defKey}" subject="${bpmDefinitionItem.subject}" right_m_del="${bpmDefinitionItem.authorizeRight.managementDel}">
					</display:column>
					<display:column property="subject" title="标题" sortable="true" sortName="subject" ></display:column>
					
					<display:column title="分类" sortable="true" sortName="typeId">
						<c:out value="${bpmDefinitionItem.typeName}"></c:out>
					</display:column>
					<display:column property="versionNo" title="版本号" sortable="true" sortName="versionNo" style="width:25px"></display:column>
					<display:column title="发布状态" sortable="true" sortName="status" style="width:45px;">
						<c:choose>
							<c:when test="${bpmDefinitionItem.status eq 0}"><span class="red">未发布</span></c:when>
							<c:when test="${bpmDefinitionItem.status eq 1}"><span class="green">已发布</span></c:when>
							<c:when test="${bpmDefinitionItem.status eq 2}"><span class="red">禁用</span></c:when>
							<c:when test="${bpmDefinitionItem.status eq 3}"><span class="red">禁用(实例)</span></c:when>
							<c:when test="${bpmDefinitionItem.status eq 4}"><span class="red">测试</span></c:when>
							<c:otherwise><span class="red">未选择</span></c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="创建时间" sortable="true" sortName="createtime"  style="width:70px;">
						<fmt:formatDate value="${bpmDefinitionItem.createtime}" pattern="yyyy-MM-dd"/>
					</display:column>
					
					
				</display:table>
				<hotent:paging tableId="bpmDefinitionItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


