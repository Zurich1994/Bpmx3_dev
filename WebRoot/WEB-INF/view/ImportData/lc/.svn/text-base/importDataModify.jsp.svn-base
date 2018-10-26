<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/js/hotent/scriptMgr.js"></script>
<script type="text/javascript">
	function afterOnload(){
		var afterLoadJs=[
		     			'${ctx}/js/hotent/formdata.js',
		     			'${ctx}/js/hotent/subform.js'
		 ];
		ScriptMgr.load({
			scripts : afterLoadJs
		});
	}
</script>
<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">导入数据</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">年年:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <textarea style="display:none" controltype="attachment" name="year_year_date" lablename="年年">${importData.year_year_date}</textarea>
     <a href="javascript:;" field="year_year_date" class="link selectFile" atype="select" onclick="{AttachMent.directUpLoadFile(this);}" validate="{}" name="year_year_date">选择</a>
    </div></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">年月:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <textarea style="display:none" controltype="attachment" name="year_month_date" lablename="年月">${importData.year_month_date}</textarea>
     <a href="javascript:;" field="year_month_date" class="link selectFile" atype="select" onclick="{AttachMent.directUpLoadFile(this);}" validate="{}" name="year_month_date">选择</a>
    </div></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">月日:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <textarea style="display:none" controltype="attachment" name="month_day_date" lablename="月日">${importData.month_day_date}</textarea>
     <a href="javascript:;" field="month_day_date" class="link selectFile" atype="select" onclick="{AttachMent.directUpLoadFile(this);}" validate="{}" name="month_day_date">选择</a>
    </div></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">日时:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <textarea style="display:none" controltype="attachment" name="day_hour_date" lablename="日时">${importData.day_hour_date}</textarea>
     <a href="javascript:;" field="day_hour_date" class="link selectFile" atype="select" onclick="{AttachMent.directUpLoadFile(this);}" validate="{}" name="day_hour_date">选择</a>
    </div></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">时分:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <textarea style="display:none" controltype="attachment" name="hour_minute_date" lablename="时分">${importData.hour_minute_date}</textarea>
     <a href="javascript:;" field="hour_minute_date" class="link selectFile" atype="select" onclick="{AttachMent.directUpLoadFile(this);}" validate="{}" name="hour_minute_date">选择</a>
    </div></td>
  </tr>
 </tbody>
</table>
