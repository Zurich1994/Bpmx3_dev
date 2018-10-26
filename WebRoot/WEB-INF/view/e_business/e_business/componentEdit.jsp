<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 用户定制</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#componentForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#componentForm').submit();
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
						window.location.href = "${ctx}/e_business/e_business/component/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty componentItem.id}">
			        <span class="tbar-label"><span></span>编辑用户定制</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加用户定制</span>
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
	<form id="componentForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">用户定制</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">ITEM ID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="PRODUCTID" lablename="ITEM ID" class="inputText" validate="{maxlength:16,required:true}" isflag="tableflag" value="${component.PRODUCTID}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">页数:</td>
   <td class="formInput" style="width:80%;"><input name="PAGE" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:1,maxDecimalLen:0,required:true}" value="${component.PAGE}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">组件类型:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="COMPONENTTYPE" lablename="组件类型" controltype="select" validate="{empty:false,required:true}"><option value="1" <c:if test='${component.COMPONENTTYPE=="1"}'>selected='selected'</c:if>>1</option><option value="2" <c:if test='${component.COMPONENTTYPE=="2"}'>selected='selected'</c:if>>2</option><option value="3" <c:if test='${component.COMPONENTTYPE=="3"}'>selected='selected'</c:if>>3</option></select></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">组件名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="COMPONENTNAME" lablename="组件名" controltype="select" validate="{empty:false,required:true}"><option value="1" <c:if test='${component.COMPONENTNAME=="1"}'>selected='selected'</c:if>>1</option><option value="2" <c:if test='${component.COMPONENTNAME=="2"}'>selected='selected'</c:if>>2</option><option value="3" <c:if test='${component.COMPONENTNAME=="3"}'>selected='selected'</c:if>>3</option></select></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">组件ID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="COMPONENTID" lablename="组件ID" controltype="select" validate="{empty:false,required:true}"><option value="111" <c:if test='${component.COMPONENTID=="111"}'>selected='selected'</c:if>>111</option><option value="222" <c:if test='${component.COMPONENTID=="222"}'>selected='selected'</c:if>>222</option><option value="333" <c:if test='${component.COMPONENTID=="333"}'>selected='selected'</c:if>>333</option></select></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">价格:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="PRICE" lablename="价格" controltype="select" validate="{empty:false,required:true}"><option value="1" <c:if test='${component.PRICE=="1"}'>selected='selected'</c:if>>1</option><option value="2" <c:if test='${component.PRICE=="2"}'>selected='selected'</c:if>>2</option><option value="3" <c:if test='${component.PRICE=="3"}'>selected='selected'</c:if>>3</option></select></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">货币:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="CURRENCY" lablename="货币" controltype="select" validate="{empty:false,required:true}"><option value="1" <c:if test='${component.CURRENCY=="1"}'>selected='selected'</c:if>>1</option><option value="2" <c:if test='${component.CURRENCY=="2"}'>selected='selected'</c:if>>2</option><option value="3" <c:if test='${component.CURRENCY=="3"}'>selected='selected'</c:if>>3</option></select></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${component.id}"/>
	</form>
</body>
</html>
