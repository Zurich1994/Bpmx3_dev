<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 服务产品表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/subform.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#serviceproductsForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				$("#saveData").val(1);
				if(frm.valid()){
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					if(OfficePlugin.officeObjs.length>0){
						OfficePlugin.submit(function(){
							frm.handleFieldName();
							$('#serviceproductsForm').submit();
						});
					}else{
						frm.handleFieldName();
						$('#serviceproductsForm').submit();
					}
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
			    var aa=obj.getMessage();
			    $.ligerDialog.confirm(aa+",是否继续操作","提示信息", function(rtn) {
				      if(aa=="更新"){
					        if(rtn){
						          //window.location.href = window.location.href;
						          window.location.href = "${ctx}/Serviceproducts/Serviceproducts/serviceproducts/list.ht?id=<%=request.getParameter("serviceId")%>&status=1";
					        }else{
					             //window.location.href = window.location.href;
						         window.location.href = "${ctx}/Serviceproducts/Serviceproducts/serviceproducts/list.ht?id=<%=request.getParameter("serviceId")%>&status=1";
					         }
				       }
				      if(aa=="添加"){
                           if(rtn){
						         window.location.href = window.location.href;
				           }else{
					           //window.location.href = window.location.href;
						       window.location.href = "${ctx}/Serviceproducts/Serviceproducts/serviceproducts/list.ht?id=<%=request.getParameter("serviceId")%>&status=1";
					       }
				     }
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel" style="height:100%;overflow:auto;">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty serviceproductsItem.id}">
			        <span class="tbar-label"><span></span>编辑服务产品表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加服务产品表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht?id=<%=request.getParameter("serviceId")%>&status=1"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="serviceproductsForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">服务产品表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">服务产品名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:Serviceproducts:name" lablename="服务产品名" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${Serviceproducts.name}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">服务编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:Serviceproducts:serviceID" lablename="服务编号" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="<%=request.getParameter("serviceId")%>" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">服务地址:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:Serviceproducts:serviceaddress" lablename="服务地址" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${Serviceproducts.serviceaddress}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">服务端口号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:Serviceproducts:serviceport" lablename="服务端口号" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${Serviceproducts.serviceport}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${Serviceproducts.id}"/>
		<input type="hidden" id="saveData" name="saveData"/>
		<input type="hidden" name="executeType"  value="start" />
	</form>
</body>
</html>
