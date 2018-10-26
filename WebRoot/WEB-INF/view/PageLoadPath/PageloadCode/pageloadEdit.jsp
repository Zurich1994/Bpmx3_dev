<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 原子操作写页面</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDialog2.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmFormDialog"></script>
	<script type="text/javascript">
		$(function() {
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#pageloadForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#pageloadForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/PageLoadPath/PageloadCode/pageload/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		//史欣慧：设置页面元素
		function dialog2(){
			$("#selInfo").text("");
			var id=$("#tableId").val();
			var istable=$("#istable").val();
			var objname=$("#objname").val();
			var dataSource=$("#dataSource").val();
			var style=$("input[name='style']:checked").val();
			if(id==0){
				if(objname==null){
					$("#selInfo").text("请先选择数据库表");
					return ;
				}
			}
			showSettingDialog2(dataSource,objname,istable,style,id);
		}
		
		function showSettingDialog2(dsName,objectname,istable,style,id){
			var settingobj=$("#settingobj").val(),
				fields={};
			
			if(settingobj==objectname){
				var displayField=$("#displayfield").val(),
					conditionField=$("#conditionfield").val(),
					sortField=$("#sortfield").val(),
					resultField=$("#resultfield").val();
				
				if(displayField)
					fields.displayField=displayField;
				if(conditionField)
					fields.conditionField=conditionField;
				if(resultField)
					fields.resultField=resultField;
				if(sortField)
					fields.sortField=sortField;
			}
			var url=__ctx+"/platform/form/bpmFormDef/transFormId.ht?ids=" + id;
			DialogUtil.open({
                height:560,
                width: 805,
                title : '设置页面元素',
                url: url, 
                isResize: true,
                //自定义参数
                finalCall:function(rtn){
                	$("#textarea").val(rtn);
                	
                }
            });
          	
		}
		
		
		//shixinhui选择节点表单
	function selectNodeForm(obj) {
		FormDialog2({
			callbackwithValue:function(textValue,key){
				$("#inputArea").val(textValue);
				$("#tableId").val(key);
			}
		})
	}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <br><c:choose>
			    <c:when test="${not empty pageloadItem.id}">
			        <span class="tbar-label">t<span></span>编辑页面加载</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加页面加载</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="pageloadForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">原子操作写页面</td>
  </tr>

  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">表单名称:  <input  type="hidden" id="tableId"  value="0"/></td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="formname" lablename="表单名称" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" id="inputArea" value="${pageload.formname}" /></span>
   <a href="javascript:;" class="link get" onclick="selectNodeForm(this)" id="subNodeSel">选择自定义表单</a></td>
  
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程ID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" lablename="流程ID" class="inputText" validate="{maxlength:100}" isflag="tableflag" name="defID" value="<%=request.getParameter("defId") %>" />
</span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">节点ID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="nodeID" lablename="节点ID" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="<%=request.getParameter("nodeId") %>" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">页面元素:</td>
   <td class="formInput" style="width:80%;"><textarea name="element" validate="{empty:false}" id="textarea">${pageload.element}</textarea>
   <a href="javascript:;" id="btnSetting" class="link setting" onclick="dialog2()">设置页面元素</a>
   
   <c:choose>
									<c:when test="${bpmFormDialog.id==0}">
										<br>
										<select id="objname" name="objname" size="10" style="width:350px;">
										</select>
										<span id="selInfo" name="selInfo" style="color:red"></span>
									</c:when>
									<c:otherwise >
										<input type="hidden"  id="objname" name="objname" value="${bpmFormDialog.objname}" />
									</c:otherwise>
								</c:choose></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${pageload.id}"/>
	</form>
</body>
</html>
