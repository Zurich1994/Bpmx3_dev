<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 网络拓扑图表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#bpmnetworkmapForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#bpmnetworkmapForm').submit();
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
						window.location.href = "${ctx}/netWorkMap/netWorkMap/bpmnetworkmap/list.ht";
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
			    <c:when test="${not empty bpmnetworkmapItem.id}">
			        <span class="tbar-label"><span></span>编辑网络拓扑图表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加网络拓扑图表</span>
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
	<form id="bpmnetworkmapForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">网络拓扑图表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ID:</td>
   <td class="formInput" style="width:80%;"><input name="defID" showtype="{'coinValue':'','isShowComdify':1,'decimalValue':0}" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" type="text" value="${bpmnetworkmap.defID}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">类型ID:</td>
   <td class="formInput" style="width:80%;"><input name="typeID" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" type="text" value="${bpmnetworkmap.typeID}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">标题:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="subject" lablename="标题" class="inputText" validate="{maxlength:256,required:true}" isflag="tableflag" type="text" value="${bpmnetworkmap.subject}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">拓扑图key:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="defKey" lablename="拓扑图key" class="inputText" validate="{maxlength:128,required:true}" isflag="tableflag" type="text" value="${bpmnetworkmap.defKey}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">xml文件:</td>
   <td class="formInput" style="width:80%;"><textarea name="defXml" validate="{empty:false}">${bpmnetworkmap.defXml}</textarea></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">状态:</td>
   <td class="formInput" style="width:80%;"><input name="status" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:6,maxDecimalLen:0}" type="text" value="${bpmnetworkmap.status}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">创建时间:</td>
   <td class="formInput" style="width:80%;"><input name="createTime" class="Wdate" displaydate="0" validate="{empty:false}" type="text" value="<fmt:formatDate value='${bpmnetworkmap.createTime}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">更新时间:</td>
   <td class="formInput" style="width:80%;"><input name="updateTime" class="Wdate" displaydate="0" validate="{empty:false}" type="text" value="<fmt:formatDate value='${bpmnetworkmap.updateTime}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">actDefID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="actDefID" lablename="actDefID" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${bpmnetworkmap.actDefID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">版本号:</td>
   <td class="formInput" style="width:80%;"><input name="versionNo" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" type="text" value="${bpmnetworkmap.versionNo}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">预留字段:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="rest" lablename="预留字段" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${bpmnetworkmap.rest}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">预留字段2:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="rest2" lablename="预留字段2" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${bpmnetworkmap.rest2}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">预留字段3:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="rest3" lablename="预留字段3" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${bpmnetworkmap.rest3}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">预留字段4:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="rest4" lablename="预留字段4" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${bpmnetworkmap.rest4}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">预留字段5:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="rest5" lablename="预留字段5" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${bpmnetworkmap.rest5}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">预留字段6:</td>
   <td class="formInput" style="width:80%;"><input name="rest6" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" type="text" value="${bpmnetworkmap.rest6}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">预留字段7:</td>
   <td class="formInput" style="width:80%;"><textarea name="rest7" validate="{empty:false}">${bpmnetworkmap.rest7}</textarea></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${bpmnetworkmap.id}"/>
	</form>
</body>
</html>
