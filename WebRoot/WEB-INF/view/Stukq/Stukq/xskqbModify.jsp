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
   <td class="formInput" style="width:80%;"><input name="rq" class="Wdate" type="text" validate="{empty:false,required:true}" displaydate="0" value="<fmt:formatDate value='${xskqb.rq}' pattern='yyyy-MM-dd HH:mm:00'/>" /></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">考勤表一:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <textarea name="kqby" style="display:none;" lablename="考勤表一" controltype="attachment">${xskqb.kqby}</textarea>
     <a class="link selectFile" onclick="{AttachMent.directUpLoadFile(this);}" href="javascript:;" validate="{required:true}" atype="select" field="kqby" name="kqby">选择</a>
    </div></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">考勤表二:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <textarea name="kqbe" style="display:none;" lablename="考勤表二" controltype="attachment">${xskqb.kqbe}</textarea>
     <a class="link selectFile" onclick="{AttachMent.directUpLoadFile(this);}" href="javascript:;" validate="{required:true}" atype="select" field="kqbe" name="kqbe">选择</a>
    </div></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">备注:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="bz" class="inputText" type="text" validate="{maxlength:1000,required:true}" lablename="备注" isflag="tableflag" value="${xskqb.bz}" /></span></td>
  </tr>
 </tbody>
</table>
<div type="subtable" tablename="xskqzhb" id="xskqzhb" formtype="edit">
 <br />
 <div class="subTableToolBar l-tab-links">
  <a class="link add" onclick="return false;" href="javascript:;" name="">添加</a>
 </div><c:forEach items='${xskqzhbList}' var='xskqzhb' varStatus='status'>
 <div class="block" type="subdata">
  <table class="listTable">
   <tbody>
    <tr>
     <td class="formHead" colspan="6">学生考勤综合表</td>
    </tr>
    <tr>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">学生姓名:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="xsxm" class="inputText" type="text" validate="{maxlength:50}" lablename="学生姓名" isflag="tableflag" value="${xskqzhb.xsxm}" /></span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">对应时段:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="dysd" class="inputText" type="text" validate="{maxlength:100}" lablename="对应时段" isflag="tableflag" value="${xskqzhb.dysd}" /></span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">签到时间:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="qdsj" class="inputText" type="text" validate="{maxlength:100}" lablename="签到时间" isflag="tableflag" value="${xskqzhb.qdsj}" /></span></td>
    </tr>
    <tr>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">签退时间:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="qtsj" class="inputText" type="text" validate="{maxlength:100}" lablename="签退时间" isflag="tableflag" value="${xskqzhb.qtsj}" /></span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">迟到时间:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="cdsj" class="inputText" type="text" validate="{maxlength:100}" lablename="迟到时间" isflag="tableflag" value="${xskqzhb.cdsj}" /></span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">早退时间:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="ztsj" class="inputText" type="text" validate="{maxlength:100}" lablename="早退时间" isflag="tableflag" value="${xskqzhb.ztsj}" /></span></td>
    </tr>
    <tr>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">工作时间:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="gzsj" class="inputText" type="text" validate="{maxlength:100}" lablename="工作时间" isflag="tableflag" value="${xskqzhb.gzsj}" /></span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">出勤时间:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="cqsj" class="inputText" type="text" validate="{maxlength:100}" lablename="出勤时间" isflag="tableflag" value="${xskqzhb.cqsj}" /></span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">上班前加班:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="sbqjb" class="inputText" type="text" validate="{maxlength:100}" lablename="上班前加班" isflag="tableflag" value="${xskqzhb.sbqjb}" /></span></td>
    </tr>
    <tr>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">下班后加班:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="xbhjb" class="inputText" type="text" validate="{maxlength:100}" lablename="下班后加班" isflag="tableflag" value="${xskqzhb.xbhjb}" /></span></td>
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">加班合计:</td>
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="jbhj" class="inputText" type="text" validate="{maxlength:100}" lablename="加班合计" isflag="tableflag" value="${xskqzhb.jbhj}" /></span></td>
     <td class="formTitle" style="width:10%;"></td>
     <td class="formInput" style="width:23%;"></td>
    </tr>
   </tbody>
  </table>
 </div></c:forEach>
 <div class="block" type="append" style="display:none;"> 
  <table class="listTable"> 
   <tbody> 
    <tr> 
     <td class="formHead" colspan="6">学生考勤综合表</td> 
    </tr> 
    <tr> 
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">学生姓名:</td> 
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="xsxm" class="inputText" type="text" validate="{maxlength:50}" lablename="学生姓名" isflag="tableflag" value="${xskqzhb.xsxm}" /></span></td> 
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">对应时段:</td> 
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="dysd" class="inputText" type="text" validate="{maxlength:100}" lablename="对应时段" isflag="tableflag" value="${xskqzhb.dysd}" /></span></td> 
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">签到时间:</td> 
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="qdsj" class="inputText" type="text" validate="{maxlength:100}" lablename="签到时间" isflag="tableflag" value="${xskqzhb.qdsj}" /></span></td> 
    </tr> 
    <tr> 
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">签退时间:</td> 
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="qtsj" class="inputText" type="text" validate="{maxlength:100}" lablename="签退时间" isflag="tableflag" value="${xskqzhb.qtsj}" /></span></td> 
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">迟到时间:</td> 
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="cdsj" class="inputText" type="text" validate="{maxlength:100}" lablename="迟到时间" isflag="tableflag" value="${xskqzhb.cdsj}" /></span></td> 
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">早退时间:</td> 
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="ztsj" class="inputText" type="text" validate="{maxlength:100}" lablename="早退时间" isflag="tableflag" value="${xskqzhb.ztsj}" /></span></td> 
    </tr> 
    <tr> 
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">工作时间:</td> 
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="gzsj" class="inputText" type="text" validate="{maxlength:100}" lablename="工作时间" isflag="tableflag" value="${xskqzhb.gzsj}" /></span></td> 
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">出勤时间:</td> 
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="cqsj" class="inputText" type="text" validate="{maxlength:100}" lablename="出勤时间" isflag="tableflag" value="${xskqzhb.cqsj}" /></span></td> 
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">上班前加班:</td> 
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="sbqjb" class="inputText" type="text" validate="{maxlength:100}" lablename="上班前加班" isflag="tableflag" value="${xskqzhb.sbqjb}" /></span></td> 
    </tr> 
    <tr> 
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">下班后加班:</td> 
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="xbhjb" class="inputText" type="text" validate="{maxlength:100}" lablename="下班后加班" isflag="tableflag" value="${xskqzhb.xbhjb}" /></span></td> 
     <td align="right" class="formTitle" nowrap="nowarp" style="width:10%;">加班合计:</td> 
     <td class="formInput" style="width:23%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="jbhj" class="inputText" type="text" validate="{maxlength:100}" lablename="加班合计" isflag="tableflag" value="${xskqzhb.jbhj}" /></span></td> 
     <td class="formTitle" style="width:10%;"></td> 
     <td class="formInput" style="width:23%;"></td> 
    </tr> 
   </tbody> 
  </table> 
 </div>
 <br />
</div>
