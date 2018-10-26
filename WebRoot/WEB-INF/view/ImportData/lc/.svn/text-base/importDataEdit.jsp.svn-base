<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 导入数据</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#importDataForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#importDataForm').submit();
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
						window.location.href = "${ctx}/ImportData/lc/importData/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		//初始化
		$(function(){
			//alert("begin");
			$("#yySelectFile").hide();
			$("#ymSelectFile").hide();
			$("#mdSelectFile").hide();
			$("#dhSelectFile").hide();
			$("#hmSelectFile").hide();
			$("#yyExcelImport").hide();
			$("#ymExcelImport").hide();
			$("#mdExcelImport").hide();
			$("#dhExcelImport").hide();
			$("#hmExcelImport").hide();
			
			$("#yyRelyImport").hide();
			$("#ymRelyImport").hide();
			$("#mdRelyImport").hide();
			$("#dhRelyImport").hide();
			$("#hmRelyImport").hide();
			
			
			$("#yyCurveView").hide();
			$("#ymCurveView").hide();
			$("#mdCurveView").hide();
			$("#dhCurveView").hide();
			$("#hmCurveView").hide();
			
			$("#yyRelyConfig").hide();
			$("#ymRelyConfig").hide();
			$("#mdRelyConfig").hide();
			$("#dhRelyConfig").hide();
			$("#hmRelyConfig").hide();
		});
		
		/*
			"yyCk"-checkbox id
			"#yySelectFile" <a>选择</a> id
			yyLabel label id
			yyExcelImport <a>导入</a> id
			
		*/
		
	
		//Excel导入checkbox
		function ExcelChecked(checkId,labelId,selectId,excelImportId){
			if(document.getElementById(checkId).checked == true){
				$("#"+selectId).show();
				$("#"+labelId).hide();
				$("#"+excelImportId).show();
				
			}else{
				$("#"+selectId).hide();
				$("#"+labelId).show();
				$("#"+excelImportId).hide();
				
				
			}
		}
		
		//导入Excel数据
		function importData(timeType){
			var processId = $("#processId").val();
			window.location.href = "${ctx}/HistoryData/lc/historyDataLc/save.ht?timeType="+timeType+"&processId="+processId;
		}
		
		//参数依赖checkbox
		function RelyChecked(checkId,labelId,relyImportId,relyConfigId){
			if(document.getElementById(checkId).checked == true){
				$("#"+labelId).hide();
				//$("#"+relyImportId).show();
				$("#"+relyConfigId).show();
			}else{
				$("#"+labelId).show();
				//$("#"+relyImportId).hide();
				$("#"+relyConfigId).hide();
			}
		}
		//曲线checkbox
		function CurveChecked(checkId,labelId,CurveViewId){
			if(document.getElementById(checkId).checked == true){
				$("#"+labelId).hide();
				$("#"+CurveViewId).show();
				
			}else{
				$("#"+labelId).show();
				$("#"+CurveViewId).hide();
			}
		}
		
		//跳转到依赖参数录入
		function importFixParam(paramType){
			var processId = $("#processId").val();
			window.location.href = "${ctx}/FixParam/lc/fixParam/edit.ht?processId="+processId+"&paramType="+paramType;
		}
		
		//返回上一页
		function goBack(){
			var processId = $("#processId").val();
			window.location.href = "${ctx}/BusinessCollectCot/lc/businessCollectCot/edit.ht?processId="+processId;
		}
		
		function goRelyConfig(relyTimeType){
			var processId = $("#processId").val();
			window.location.href = "${ctx}/ParamRely/lc/paramRely/edit.ht?processId="+processId+"&relyTimeType="+relyTimeType;
		}
		
		function historyView(timeType){
			window.location.href = "${ctx}/HistoryData/lc/historyDataLc/showHistory.ht?timeType="+timeType;
			//window.location.href = "${ctx}/HistoryData/lc/historyDataLc/flexShow.ht?timeType="+timeType;
		}
		
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty importDataItem.id}">
			        <span class="tbar-label"><span></span>编辑导入数据</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加导入数据</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a style="display: none;" class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a  class="link back" href="javascript:void(0);" onclick="goBack()"><span></span>返回</a></div>
				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				流程定义id:<input type="text" id="processId" name="processId" value="<%=request.getParameter("processId") %>" readonly="readonly" />
			</div>
		</div>
	</div>
	<form id="importDataForm" method="post" action="save.ht">
	
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">导入数据表单</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">年年数据导入:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <input id="yyExcelCk" type="checkbox"  name="excelImpot" value="导入Excel数据" onclick="ExcelChecked(this.id,'yyExcelLabel','yySelectFile','yyExcelImport')"/><label id="yyExcelLabel">导入Excel数据</label><a id="yyExcelImport" name="year" href="javascript:void(0);"  onclick="importData('year')"  id="yyExcelImport">导入</a>&nbsp;
     <textarea  style="display:none" controltype="attachment" name="year_year_date" lablename="年年数据导入">${importData.year_year_date}</textarea>   
     <a id="yySelectFile"  href="javascript:;" field="year_year_date" class="link selectFile" atype="select" onclick="{AttachMent.directUpLoadFile(this);}" validate="{}" name="year_year_date">选择</a>
      <input  type="checkbox" name="historyDate" value="历史数据发生比例录入"/>历史数据发生比例录入&nbsp;
      <input id="yyRelyCK" type="checkbox" name="paramRely" value="参数依赖输入" onclick="RelyChecked('yyRelyCK','yyRelyLabel','yyRelyImport','yyRelyConfig')"/><label id="yyRelyLabel">参数依赖输入</label><a name="year" href="javascript:void(0);"  onclick="importFixParam('year')"  id="yyRelyImport">输入</a>
      <a name="year" href="javascript:void(0);"  onclick="goRelyConfig('year')"  id="yyRelyConfig">设置</a>&nbsp;
      <input type="checkbox" name="curveImpot" value="趋势曲线信息录入" />趋势曲线信息录入&nbsp;
       <input id="yyCurveCK" type="checkbox" name="historyCurve" value="查看历史数据曲线" onclick="CurveChecked('yyCurveCK','yyCurveLabel','yyCurveView')" /><label id="yyCurveLabel">查看历史数据曲线</label><a name="year" href="javascript:void(0);"  onclick="historyView('year')"  id="yyCurveView" >查看</a>&nbsp;
    </div></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">年月数据导入:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
      <input id="ymExcelCk" type="checkbox"  name="excelImpot" value="导入Excel数据" onclick="ExcelChecked(this.id,'ymExcelLabel','ymSelectFile','ymExcelImport')"/><label id="ymExcelLabel">导入Excel数据</label><a id="ymExcelImport" name="month" href="javascript:void(0);" onclick="importData('month')" id="ymExcelImport">导入</a>&nbsp;
     <textarea style="display:none" controltype="attachment" name="year_month_date" lablename="年月数据导入">${importData.year_month_date}</textarea>
     <a id="ymSelectFile" href="javascript:;"  field="year_month_date" class="link selectFile" atype="select" onclick="{AttachMent.directUpLoadFile(this);}" validate="{}" name="year_month_date">选择</a>
     <input type="checkbox" name="historyDate" value="历史数据发生比例录入"/>历史数据发生比例录入&nbsp;
     <input id="ymRelyCK" type="checkbox" name="paramRely" value="参数依赖输入" onclick="RelyChecked('ymRelyCK','ymRelyLabel','ymRelyImport','ymRelyConfig')"/><label id="ymRelyLabel">参数依赖输入</label><a name="month" href="javascript:void(0);"  onclick="importFixParam('month')"  id="ymRelyImport">输入</a>
     <a name="month" href="javascript:void(0);"  onclick="goRelyConfig('month')"  id="ymRelyConfig">设置</a>&nbsp;
     <input type="checkbox" name="curveImpot" value="趋势曲线信息录入" />趋势曲线信息录入&nbsp;
     <input id="ymCurveCK" type="checkbox" name="historyCurve" value="查看历史数据曲线" onclick="CurveChecked('ymCurveCK','ymCurveLabel','ymCurveView')"/><label id="ymCurveLabel">查看历史数据曲线</label><a name="month" href="javascript:void(0);"  onclick="historyView('month')"  id="ymCurveView" >查看</a>&nbsp;
    </div></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">月日数据导入:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <input id="mdExcelCk" type="checkbox"  name="excelImpot" value="导入Excel数据" onclick="ExcelChecked(this.id,'mdExcelLabel','mdSelectFile','mdExcelImport')"/><label id="mdExcelLabel">导入Excel数据</label><a id="mdExcelImport" name="day" href="javascript:void(0);" onclick="importData('day')" id="mdExcelImport">导入</a>&nbsp;
     <textarea style="display:none" controltype="attachment" name="month_day_date" lablename="月日数据导入">${importData.month_day_date}</textarea>
     <a id="mdSelectFile" href="javascript:;" field="month_day_date" class="link selectFile" atype="select" onclick="{AttachMent.directUpLoadFile(this);}" validate="{}" name="month_day_date">选择</a>
     <input type="checkbox" name="historyDate" value="历史数据发生比例录入"/>历史数据发生比例录入&nbsp;
     <input id="mdRelyCK" type="checkbox" name="paramRely" value="参数依赖输入" onclick="RelyChecked('mdRelyCK','mdRelyLabel','mdRelyImport','mdRelyConfig')"/><label id="mdRelyLabel">参数依赖输入</label><a name="day" href="javascript:void(0);"  onclick="importFixParam('day')"  id="mdRelyImport">输入</a>
     <a name="day" href="javascript:void(0);"  onclick="goRelyConfig('day')"  id="mdRelyConfig">设置</a>&nbsp;
     <input type="checkbox" name="curveImpot" value="趋势曲线信息录入" />趋势曲线信息录入&nbsp;
     <input id="mdCurveCK" type="checkbox" name="historyCurve" value="查看历史数据曲线" onclick="CurveChecked('mdCurveCK','mdCurveLabel','mdCurveView')"/><label id="mdCurveLabel">查看历史数据曲线</label><a name="day" href="javascript:void(0);"  onclick="historyView('day')"  id="mdCurveView">查看</a>&nbsp;
    </div></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">日时数据导入:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <input id="dhExcelCk" type="checkbox"  name="excelImpot" value="导入Excel数据" onclick="ExcelChecked(this.id,'dhExcelLabel','dhSelectFile','dhExcelImport')"/><label id="dhExcelLabel">导入Excel数据</label><a name="hour" href="javascript:void(0);" onclick="importData('hour')" id="dhExcelImport">导入</a>&nbsp;
     <textarea style="display:none" controltype="attachment" name="day_hour_date" lablename="日时数据导入">${importData.day_hour_date}</textarea>
     <a id="dhSelectFile" href="javascript:;" field="day_hour_date" class="link selectFile" atype="select" onclick="{AttachMent.directUpLoadFile(this);}" validate="{}" name="day_hour_date">选择</a>
     <input type="checkbox" name="historyDate" value="历史数据发生比例录入"/>历史数据发生比例录入&nbsp;
     <input id="dhRelyCK" type="checkbox" name="paramRely" value="参数依赖输入" onclick="RelyChecked('dhRelyCK','dhRelyLabel','dhRelyImport','dhRelyConfig')"/><label id="dhRelyLabel">参数依赖输入</label><a name="hour" href="javascript:void(0);"  onclick="importFixParam('hour')"  id="dhRelyImport">输入</a>
     <a name="hour" href="javascript:void(0);"  onclick="goRelyConfig('hour')"  id="dhRelyConfig">设置</a>&nbsp;
     <input type="checkbox" name="curveImpot" value="趋势曲线信息录入" />趋势曲线信息录入&nbsp;
     <input id="dhCurveCK" type="checkbox" name="historyCurve" value="查看历史数据曲线" onclick="CurveChecked('dhCurveCK','dhCurveLabel','dhCurveView')" /><label id="dhCurveLabel">查看历史数据曲线</label><a name="hour" href="javascript:void(0);"  onclick="historyView('hour')"  id="dhCurveView">查看</a>&nbsp;
    </div></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">时分数据导入:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <input id="hmExcelCk" type="checkbox"  name="excelImpot" value="导入Excel数据" onclick="ExcelChecked(this.id,'hmExcelLabel','hmSelectFile','hmExcelImport')"/><label id="hmExcelLabel">导入Excel数据</label><a id="hmExcelImport" name="minute" href="javascript:void(0);" onclick="importData('minute')" id="hmExcelImport">导入</a>&nbsp;
     <textarea style="display:none" controltype="attachment" name="hour_minute_date" lablename="时分数据导入">${importData.hour_minute_date}</textarea>
     <a id="hmSelectFile" href="javascript:;" field="hour_minute_date" class="link selectFile" atype="select" onclick="{AttachMent.directUpLoadFile(this);}" validate="{}" name="hour_minute_date">选择</a>
     <input type="checkbox" name="historyDate" value="历史数据发生比例录入"/>历史数据发生比例录入&nbsp;
     <input id="hmRelyCK" type="checkbox" name="paramRely" value="参数依赖输入" onclick="RelyChecked('hmRelyCK','hmRelyLabel','hmRelyImport','hmRelyConfig')"/><label id="hmRelyLabel">参数依赖输入</label><a name="minute" href="javascript:void(0);"  onclick="importFixParam('minute')"  id="hmRelyImport">输入</a>
     <a name="minute" href="javascript:void(0);"  onclick="goRelyConfig()"  id="hmRelyConfig">设置</a>&nbsp;
     <input type="checkbox" name="curveImpot" value="趋势曲线信息录入" />趋势曲线信息录入&nbsp;
     <input id="hmCurveCK" type="checkbox" name="historyCurve" value="查看历史数据曲线" onclick="CurveChecked('hmCurveCK','hmCurveLabel','hmCurveView')" /><label id="hmCurveLabel">查看历史数据曲线</label><a name="minute" href="javascript:void(0);"  onclick="historyView('minute')"  id="hmCurveView">查看</a>&nbsp;
    </div></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${importData.id}"/>
	</form>
</body>
</html>
