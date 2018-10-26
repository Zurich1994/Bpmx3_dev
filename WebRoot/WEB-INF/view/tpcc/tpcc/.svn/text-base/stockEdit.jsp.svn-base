<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 stock</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#stockForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#stockForm').submit();
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
						window.location.href = "${ctx}/stock/stock/stock/list.ht";
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
			    <c:when test="${not empty stockItem.id}">
			        <span class="tbar-label"><span></span>编辑stock</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加stock</span>
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
	<form id="stockForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">stock</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_w_id:</td>
   <td class="formInput" style="width:80%;"><input name="s_w_id" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0,required:true}'" type="text" value="${stock.s_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_quantity:</td>
   <td class="formInput" style="width:80%;"><input name="s_quantity" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0}'" type="text" value="${stock.s_quantity}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_dist_01:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="s_dist_01" lablename="s_dist_01" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${stock.s_dist_01}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_dist_02:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="s_dist_02" lablename="s_dist_02" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${stock.s_dist_02}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_dist_03:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="s_dist_03" lablename="s_dist_03" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${stock.s_dist_03}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_dist_04:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="s_dist_04" lablename="s_dist_04" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${stock.s_dist_04}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_dist_05:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="s_dist_05" lablename="s_dist_05" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${stock.s_dist_05}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_dist_06:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="s_dist_06" lablename="s_dist_06" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${stock.s_dist_06}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_dist_07:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="s_dist_07" lablename="s_dist_07" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${stock.s_dist_07}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_dist_08:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="s_dist_08" lablename="s_dist_08" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${stock.s_dist_08}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_dist_09:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="s_dist_09" lablename="s_dist_09" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${stock.s_dist_09}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_dist_10:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="s_dist_10" lablename="s_dist_10" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${stock.s_dist_10}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_ytd:</td>
   <td class="formInput" style="width:80%;"><input name="s_ytd" showtype="validate='{number:true,maxIntLen:8,maxDecimalLen:0}'" type="text" value="${stock.s_ytd}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_order_cnt:</td>
   <td class="formInput" style="width:80%;"><input name="s_order_cnt" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0}'" type="text" value="${stock.s_order_cnt}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_remote_cnt:</td>
   <td class="formInput" style="width:80%;"><input name="s_remote_cnt" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0}'" type="text" value="${stock.s_remote_cnt}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">s_data:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="s_data" lablename="s_data" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${stock.s_data}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="s_i_id" value="${stock.s_i_id}"/>
	</form>
</body>
</html>
