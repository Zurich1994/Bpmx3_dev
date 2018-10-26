<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.hotent.platform.model.bpm.BpmDefRights"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程定义扩展管理</title>
<%@include file="/commons/include/get.jsp" %>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/AddResourceDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ImportExportXmlUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/tabOperator.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowRightDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowUtil.js" ></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/htCatCombo.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/htCatCombo1.js"></script>
<script type="text/javascript">
	var dialogCategory
	$(function(){
		dialogCategory = $("#dialogCategory").html();
	});
	
	var dialogCategory1
	$(function(){
		dialogCategory1 = $("#dialogCategory1").html();
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
		DialogUtil.open({
			height:250,
			title : '导入流程定义',
			url: url, 
			isResize: true
			//自定义参数
		});
		//ImportExportXml.showModalDialog({
		//	url:url,
		//	title:"导入流程定义",
		//});
	}
	//导出一图四表流程定义 
	function exportChart(){
		var bpmDefIds = ImportExportXml.getChkValue('pk');
		if (bpmDefIds ==''){
			$.ligerDialog.warn('还没有选择,请选择一项流程定义!','提示信息');
			return ;
		}
		var url=__ctx + "/platform/bpm/bpmDefinition/exportchart.ht?bpmDefIds="+bpmDefIds;
		window.open(url);
		
	}
	//代码生成
	function code(){
	    var bpmDefIds = ImportExportXml.getChkValue('pk');
		if (bpmDefIds ==''){
			$.ligerDialog.warn('还没有选择,请选择一项流程定义!','提示信息');
			return ;
		}
		var url=__ctx + "/platform/system/sysCodeoperate/codeoperateZip.ht?bpmDefIds="+bpmDefIds;
		window.open(url);
	}
	//跳转操作
		function addToResource(id){
		var url="/platform/form/bpmFormHandler/editdefinition.ht?DefId="+id;
		alert(url);
		AddResourceDialog({addUrl:url});
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
				alert(defKeys);alert(typeId);
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
	
	
		
	//******参考分类************
	
	
	
	var dialog1=null;
	//重设子系统
	function setCategory1(){
		$("#dialogCategory1").html(dialogCategory1);
		$('.catComBo').each(function() {
			$(this).htCatCombo1();
		});
		
		var defKeys=getDefKey();
		if(defKeys==""){
			$.ligerDialog.warn('还没有选择,请选择一项记录!','提示');
			return;
		}
		if(dialog1==null){
			dialog1=$.ligerDialog.open({title:"设置子系统",target:$("#dialogCategory1"),width:400,height:250,buttons:
			
				[ {text : '确定',onclick: setCategoryOk1},
				{text : '取消',onclick: function (item, dialog1) {
						dialog1.hide();
					}
				}]
			});	
		}
		dialog1.show();
	}
	
	function setCategoryOk1(item, dialog1){
		var typeId=$("#typeId").val();
		
		if(typeId==""){
			$.ligerDialog.warn('请选择子系统','提示');
			return;
		}
		var defKeys=getDefKey();
		
		var params={defKeys:defKeys,typeId:typeId};
				alert(defKeys);alert(typeId);
		var url="${ctx}/platform/bpm/bpmDefinition/setCategory1.ht";
		$.post(url,params,function(responseText){
				$.ligerDialog.success('操作成功!','提示',function(){
					dialog1.hide();
					var url=location.href.getNewUrl();
					location.href=url;
				});
			
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
	//******************
	
	
	
	
	
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
	function deftongji(n)
	{	//alert(n);
		var id=ImportExportXml.getChkValue('pk');
		if (id=='')
		{
			$.ligerDialog.warn('还没有选择,请选择一项流程定义!','提示信息');
			return ;
		}
		//alert(id+"进入deftongji")
		var url=__ctx + "/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/deftongji.ht?id="+id+"&typeName="+n;
		window.open(url);//跳转到下一个页
	}
	//批量生成     //周美丽
	function make(n){          //n=typeName
	    alert(n);               //输出n,目的是看n是否输出typeName
	    var aryChk=$("input:checkbox[name='defId']:checked");  //
	    if(aryChk.length==0){
	    	alert("请选择流程");
	    	return;
	    }
	    var status="";
	  	aryChk.each (function(){      //fuction的括号是包含的一句话，记住不能删！笨蛋
			
			alert($(this).attr("index"));   
			status+=$(this).attr("index");
			status+=",";                       //只能识别用逗号隔开的数字
			
		});
		
	
		
		var defId=ImportExportXml.getChkValue('pk');//将PK的值传给id，将打对勾的传给id
		if (defId=='')
		{ 
			$.ligerDialog.warn('还没有选择,请选择一项流程定义!','提示信息');
			
			return ;
		}
		
		    else {	
		    alert(defId);
		    alert(n);
		    alert(status);
						var url=__ctx + "/platform/bpm/bpmDefinition/mass_produce.ht?defId="+defId+"&typeName="+n+"&status="+status;
						window.open(url);//跳转到下一个页面
					}	
		}

	
</script>
</head>
<body>      
	<div class="panel">
	<div class="panel-top">	
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
					<%String documentGeneration=request.getParameter("documentGeneration");
					String typeName=request.getParameter("typeName");%>
					<a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<!-- <div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div> -->
					<!-- 这些的DIV 不用class="group" 为了避开共同方法 displaytag.js中handlerDelSelect()删除流程的方法 -->
					<%if(!"true".equals(documentGeneration)) { %>
					<div><a class="link del"    onclick="delDef()" ><span></span>删除</a></div>
					<%if("flowChart".equals(typeName)) {%>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link flowDesign" onclick="window.open('design.ht?typeName=flowChart&status=-1&designChart=true')"><span></span>在线流程设计</a></div>
					<%}else if("transactionChart".equals(typeName)){ %>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link flowDesign" onclick="window.open('design.ht?typeName=transactionChart&status=-1&designChart=true')"><span></span>在线事务图设计</a></div>
					<%}else{ %>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link flowDesign" onclick="window.open('design.ht?typeName=operationChart&status=-1&designChart=true')"><span></span>在线操作图设计</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link category" onclick="code()"><span></span>代码生成</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a onclick="make('<%=typeName %>')"  class="link category"><span></span>批量生成</a></div>
					<%} %>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link export"   onclick="exportXml()"><span></span>导出</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link import"   onclick="importXml()"><span></span>导入</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reload"   onclick="window.location.reload()"><span></span>刷新</a></div>	
					<div class="l-bar-separator"></div>
					<div class="group"><a onclick="setCategory()"  class="link category"><span></span>设置分类</a></div>
					<%}if("flowChart".equals(typeName)){%>
					<div class="l-bar-separator"></div>
					<div class="group">
							<a class="link export" onclick="exportChart()"><span></span>导出一图四表</a>
						</div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link flowDesign" onclick="window.open('design.ht?typeName=lineChart&status=5')"><span></span>查看发生量</a></div>
					<%if(!"true".equals(documentGeneration)) {%>
					<div class="group"><a onclick="setCategory1()"  class="link category"><span></span>设置子系统</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link export" onclick="deftongji('<%=typeName%>');"><span></span>业务逻辑校验</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a onclick="make('<%=typeName %>')"  class="link category"><span></span>批量生成</a></div>
					<%}} %>
					<!-- 
						 暂时去掉分管授权
						<div class="l-bar-separator"></div>
						<div class="group"><a onclick="abatchGrant()"  class="link grant"><span></span><spr:message code="menu.button.batchGrant"/></a></div> 
					-->									
				</div>	
			</div>
			<div class="panel-search">
			       
				<form id="searchForm" method="post" action="list.ht">	    
					<ul class="row">
						<input type="hidden" name="typeId" value="${param['typeId']}" title="流程分类ID"></input>
						<input type="hidden" name="typeName" value="${typeName}" title="流程分类"></input>
						]<input type="hidden" name="documentGeneration" value="${documentGeneration}" title="生成文档"></input>
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
						  	<input type="checkbox" class="pk" name="defId" value="${bpmDefinitionItem.defId}" index="${bpmDefinitionItem.status}" 
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
					
					<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
					<%if(!"true".equals(documentGeneration)) {%>
						<c:if test="${bpmDefinitionItem.authorizeRight.managementDel=='Y'}">
							<f:a alias="delProcess" href="del.ht?defId=${bpmDefinitionItem.defId}" css="link del" >删除</f:a>
						</c:if>
						
						<c:if test="${bpmDefinitionItem.authorizeRight.managementEdit=='Y'}">
						    <f:a alias="" href="htmlNodeSet.ht?defId=${bpmDefinitionItem.defId}&typeName=${typeName}&status=${bpmDefinitionItem.status}&designChart=true" target="_blank" css="link flowDesign" >HTML5设置</f:a>
							<f:a alias="flex" href="design.ht?defId=${bpmDefinitionItem.defId}&typeName=${typeName}&status=${bpmDefinitionItem.status}&designChart=true" target="_blank" css="link flowDesign" >设计</f:a>
							<!--<f:a alias="detail" href="detail.ht?defId=${bpmDefinitionItem.defId}" target="_blank" css="link flowDesign" >detail明细</f:a>-->
							<f:a alias="get" href="get.ht?defId=${bpmDefinitionItem.defId}" target="_blank" css="link flowDesign" >get明细</f:a>
						</c:if>
						
						<c:if test="${bpmDefinitionItem.authorizeRight.managementSet=='Y'&& bpmDefinitionItem.status!=0}">

							<f:a alias="setBpm" href="detail.ht?defId=${bpmDefinitionItem.defId}&time=${time}&defIdForReturn=${defIdForReturn}" css="link setting" >设置</f:a>			<!--<f:a alias="markovchain" href="${ctx}/Markovchain/Markovchain/markovchain/listbydefid.ht?defId=${bpmDefinitionItem.defId}" target="_self" css="link search" >设置马尔科夫链</f:a>
						--></c:if>
						<c:if test="${bpmDefinitionItem.authorizeRight.managementSet=='Y'&& bpmDefinitionItem.status!=0&&bpmDefinitionItem.typeName!='事务图'}">
							<f:a alias="markovchain" href="${ctx}/Markovchain/Markovchain/markovchain/listbydefid.ht?defId=${bpmDefinitionItem.defId}&typeName=${typeName}&status=${bpmDefinitionItem.status}" target="_self" css="link search" >设置马尔科夫链</f:a>
						</c:if>
						<c:if test="${bpmDefinitionItem.authorizeRight.managementEdit=='Y'&&bpmDefinitionItem.typeName=='操作图'}">
							<f:a alias="markovchain" href="javascript:;" onclick="addToResource(${bpmDefinitionItem.defId})" css="link search">封装资源节点</f:a>
						</c:if>
						<c:if test="${bpmDefinitionItem.authorizeRight.managementEdit=='Y' && bpmDefinitionItem.typeId==2}">
							<f:a alias="operate" href="${ctx}/platform/bpm/bpmDefinition/flexOperateDefSave.ht?defId=${bpmDefinitionItem.defId}" target="_blank" css="link flowDesign"  >生成操作图</f:a>
						</c:if>
						
						<c:if test="${bpmDefinitionItem.authorizeRight.managementEdit=='Y'&& bpmDefinitionItem.typeId==10000041550186}">
							<f:a alias="operate"  href="${ctx}/platform/bpm/bpmDefinition/transactionDefSave.ht?defId=${bpmDefinitionItem.defId}" target="_blank" css="link flowDesign"  >生成事务图</f:a>
						</c:if>
						
						<c:if test="${bpmDefinitionItem.authorizeRight.managementEdit=='Y'&& bpmDefinitionItem.status!=0 && bpmDefinitionItem.typeId==10000041550186}">
							<f:a alias="operate" href="${ctx}/platform/bpm/bpmDefinition/createFormSave.ht?defId=${bpmDefinitionItem.defId}" target="_blank" css="link flowDesign" >生成表单</f:a>
						</c:if>
						
						<c:if test="${bpmDefinitionItem.typeId =='10000006460068'}">
										<c:if test="${bpmDefinitionItem.authorizeRight.managementEdit=='Y'}">
										<c:if test="${bpmDefinitionItem.status==1||bpmDefinitionItem.status==4}">
							<f:a alias="publishProcess" href="${ctx}/flowwebservice/flowwebservice/flowwebservice/edit.ht?defId=${bpmDefinitionItem.defId}&typeId=10000021910018" css="link deploy" ><em>封装WebService服务</em></f:a>
						</c:if>
						
							</c:if>
							</c:if>
						<c:if test="${bpmDefinitionItem.authorizeRight.managementStart=='Y'}">
							<c:if test="${bpmDefinitionItem.status==1||bpmDefinitionItem.status==4}">
								<f:a alias="startProcess" href="javaScript:void(0)" onclick="FlowUtil.startFlow(${bpmDefinitionItem.defId},'${bpmDefinitionItem.actDefId}','${defIdForReturn}')" css="link run" >启动</f:a>
							</c:if>
						</c:if>
						
						<!-- 
						             暂时去掉分管授权
							<f:a alias="grantProcess" href="javascript:FlowRightDialog(${bpmDefinitionItem.defId},0,'${bpmDefinitionItem.defKey}')" css="link grant" ><spr:message code="menu.button.authorize"/></f:a> 
						-->
						
						<c:if test="${bpmDefinitionItem.status==0}">
							<f:a alias="publishProcess" href="deploy.ht?defId=${bpmDefinitionItem.defId}" css="link deploy" >发布</f:a>
						</c:if>
						
                 <%--  <c:if test="${bpmDefinitionItem.authorizeRight.managementInternational=='Y'}">
							<c:if test="${bpmDefinitionItem.status!=0}">
								<f:a alias="internationalization" href="${ctx}/platform/bpm/bpmFormLanguage/defList.ht?actdefid=${bpmDefinitionItem.actDefId}" css="link comment" >国际化</f:a>
							</c:if>
						</c:if> --%>
						
						<c:if test="${bpmDefinitionItem.authorizeRight.managementClean=='Y'}">
							<a  href="javascript:cleanData('${bpmDefinitionItem.defId}')" class="link clean" >清除数据</a>
					    </c:if>
					     <c:if test="${bpmDefinitionItem.authorizeRight.managementEdit=='Y'}">
						<!-- 	<f:a alias="publishProcess" href="http://localhost:8080/bpmx3_dev/flowwebservice/flowwebservice/flowwebservice/edit.ht?defId=${bpmDefinitionItem.defId}" css="link deploy" ><em>WebService服务</em></f:a> 
						<f:a alias="publishProcess" href="${ctx}/flowwebservice/flowwebservice/flowwebservice/edit.ht?defId=${bpmDefinitionItem.defId}&typeId=10000021910018" css="link deploy" ><em>WebService服务</em></f:a>-->
						</c:if>
						   <!--<c:if test="${bpmDefinitionItem.authorizeRight.managementEdit=='Y'}">
					
						<f:a alias="flex" href="check.ht?defId=${bpmDefinitionItem.defId}" target="_blank" css="link flowDesign" >查看</f:a>
						</c:if>-->
						<%} %>
					</display:column>
				</display:table>
				<hotent:paging tableId="bpmDefinitionItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
	<div id="dialogCategory" style="width: 380px;display: none;">
	<div class="panel">
			<div class="panel-body">
				<form id="frmDel">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th style="width:150px;">设置分类:</th>
							<td>
								<input class="catComBo" catKey="FLOW_TYPE" typeName="${typeName}" valueField="typeId" catValue="" name="typeName" height="150" width="150"/>
							</td>
						</tr>
					</table>
				
				</form>
			</div>
		</div>
</div>
<div id="dialogCategory1" style="width: 380px;display: none;">
	<div class="panel">
			<div class="panel-body">
				<form id="frmDel">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th style="width:150px;">设置子系统:</th>
							<td>
								  <input class="catComBo" catKey="SubSystem_key" valueField="typeId" catValue="" name="typeName" height="150" width="150"/>
							</td>
						</tr>
					</table>
				
				</form>
			</div>
		</div>
</div>

</body>
</html>


