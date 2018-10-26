<%@page language="java" pageEncoding="UTF-8" import="com.hotent.BusinessCurve.model.lc.BusinessCurve"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ page import="com.hotent.BusinessCurve.model.lc.BusinessCurve" %>
<html>
<head>
	<title>编辑 业务曲线信息表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#businessCurveForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#businessCurveForm').submit();
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
						window.location.href = "${ctx}/BusinessCurve/lc/businessCurve/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function selectDataId(){
		var exp = document.getElementsByName("unknownExp")[0].value;
		var rtnType = document.getElementsByName("rtnType")[0].value;
		//alert("exp="+exp);
		 window.location.href ='${ctx}/excelTempData_lc/excelTempData/excelsjlsbLc/list.ht?exp='+exp+'&rtnType='+rtnType;
	
		}
		
<<<<<<< .mine
					GeneralMetaDataDialog2({isSingle:1,callback:function(service_name){
						$("#basicExpression").val(service_name);
					}},'/excelTempData_lc/excelTempData/excelsjlsbLc/list.ht?isSingle=');
				}
				
		function GeneralMetaDataDialog2(conf,urlnew)
		{
			var dialogWidth=1005;
			var dialogHeight=500;

			conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

			var winArgs="dialogWidth="+dialogWidth+"px;dialogHeight="+conf.dialogHeight
				+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
			if(!conf.isSingle)conf.isSingle=1;
			var url=__ctx + urlnew + conf.isSingle;
			url=url.getNewUrl();
			var rtn=window.showModalDialog(url,null,winArgs);
			if(conf.callback)
			{
				if(rtn!=undefined){
					 conf.callback.call(this,rtn.service_name); 
				}
			}
		};	
		
		function goBack(){
			alert("goBack");
			var processId = $("#processId").val();
			window.location.href = "${ctx}/ImportData/lc/importData/edit.ht?processId="+processId;
		}
		
		function save(){
			//alert("save");
			var processId = $("#processId").val();
			//alert(processId);
			document.getElementById("historyScaleForm").action="save.ht?processId="+processId;
		}
	</script>
=======
	function ch_fx(t)
{
	 var select_id=t.selectedIndex;
	 alert("t="+t.options[select_id].value)
	 if(t.options[select_id].value=="二元一次方程曲线")
	 {
	  document.getElementsByName("unknownExp")[0].value="y=a*x+b";
	  alert($("unknownExp").va())
	 }else
	 {
	 document.getElementsByName("unknownExp")[0].value="y=a*(x^2)+b*x+c";
 }
}
	
	
	
   </script>
>>>>>>> .r240
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty businessCurveItem.id}">&nbsp; 
			        <span class="tbar-label"><span></span>编辑业务曲线信息表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加业务曲线信息表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;" onclick="save()"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="javascript:;" onclick="goBack()"><span></span>返回</a></div>
				
			</div>
		</div>
	</div>
	<form id="businessCurveForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">业务曲线信息表</td>
  </tr>
   <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程ID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input id="processId" name="processId" lablename="流程ID" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="<%=request.getParameter("processId") %>" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">时间类型:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="timeType" lablename="时间类型" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="<%=request.getParameter("timeType") %>" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">曲线数据编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="curveDataId" lablename="曲线数据编号" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="110${rtnPid}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程编号:</td>   
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="curveId" lablename="曲线编号" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${rtnPid}" /></span></td>
  </tr>
   <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp" >曲线类别:</td>
   <td align="left">
  			 <select name="curveType" id = "type" class="select " style="width:258px;"  onchange="ch_fx(this)" >  			 				
                            <option  value="<%=BusinessCurve.TYPE_0%>"  >二元一次方程曲线</option>
                            <option value="<%=BusinessCurve.TYPE_1%>"  >二元二次方程曲线</option>
                            
              </select>
   </td>  
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">待求公式:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">
	   <input name="unknownExp" lablename="待求公式" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="y=a*x+b"/>
   </span>
   </td>  
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">基本公式:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">
   <input name="basicExpression"  id= "basicExpression" lablename="基本公式" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${returnExp}" /></span>
   
     <a href="#" onclick="selectDataId()" class="link search">选择</a>
   </td>
  </tr>
 
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">曲线开始时间:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">
	   <input name="curveStartTime" lablename="曲线开始时间" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value ="<c:if test="${rtnType == 'year'}"></c:if><c:if test="${rtnType == 'month'}">1</c:if><c:if test="${rtnType == 'day'}">1</c:if><c:if test="${rtnType == 'hour'}">1</c:if><c:if test="${rtnType == 'minute'}">1</c:if>"/></span>
   </td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">曲线结束时间:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">
	<input name="curveEndTime" lablename="曲线结束时间" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="<c:if test="${rtnType == 'year'} ">  </c:if><c:if test="${rtnType == 'month'}">12</c:if><c:if test="${rtnType == 'day'}">31</c:if><c:if test="${rtnType == 'hour'}">24</c:if><c:if test="${rtnType == 'minute'}">60</c:if>"/> </span>
   </td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="rtnType" value="${rtnType}"/>
		<input type="hidden" name="id" value="${businessCurve.id}"/>
	</form>
</body>
</html>
