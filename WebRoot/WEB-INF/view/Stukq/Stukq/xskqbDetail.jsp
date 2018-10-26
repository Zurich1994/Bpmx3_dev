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
<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">学生考勤表</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">日期:</td>
   <td class="formInput" style="width:80%;"><fmt:formatDate value='${xskqb.rq}' pattern='yyyy-MM-dd HH:mm:00'/></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">考勤表一:</td>
   <td class="formInput" style="width:80%;" right="r">
    <div name="div_attachment_container" right="r">
     <div class="attachement"></div>
     <textarea name="kqby" style="display:none;" lablename="考勤表一" controltype="attachment" readonly="readonly">${xskqb.kqby}</textarea>
    </div></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">考勤表二:</td>
   <td class="formInput" style="width:80%;" right="r">
    <div name="div_attachment_container" right="r">
     <div class="attachement"></div>
     <textarea name="kqbe" style="display:none;" lablename="考勤表二" controltype="attachment" readonly="readonly">${xskqb.kqbe}</textarea>
    </div></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">备注:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${xskqb.bz}</span></td>
  </tr>
 </tbody>
</table>
<div type="subtable" tablename="xskqzhb" id="xskqzhb" formtype="edit">
 <br />
 <div class="subTableToolBar l-tab-links"></div><c:forEach items='${xskqzhbList}' var='xskqzhb' varStatus='status'>
 <div class="block" type="subdata">
  <table class="listTable">
   <tbody>
    <tr>
     <td class="formHead" colspan="6">学生考勤综合表</td>
    </tr>
    <tr>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">学生姓名:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${xskqzhb.xsxm}</span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">对应时段:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${xskqzhb.dysd}</span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">签到时间:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${xskqzhb.qdsj}</span></td>
    </tr>
    <tr>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">签退时间:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${xskqzhb.qtsj}</span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">迟到时间:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${xskqzhb.cdsj}</span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">早退时间:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${xskqzhb.ztsj}</span></td>
    </tr>
    <tr>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">工作时间:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${xskqzhb.gzsj}</span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">出勤时间:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${xskqzhb.cqsj}</span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">上班前加班:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${xskqzhb.sbqjb}</span></td>
    </tr>
    <tr>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">下班后加班:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${xskqzhb.xbhjb}</span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">加班合计:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${xskqzhb.jbhj}</span></td>
     <td class="formTitle" style="width:10%;"></td>
     <td class="formInput" style="width:23%;"></td>
    </tr>
   </tbody>
  </table>
 </div></c:forEach>
 <br />
</div>
