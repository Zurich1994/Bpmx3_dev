<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/codegen/include/customForm.jsp" %>
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
   <td colspan="2" class="formHead">子系统内部信息统计</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">子系统名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:sysName" lablename="子系统名称" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.sysName}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">子系统id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:sysId" lablename="子系统id" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.sysId}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">新增与修改类操作非规范元素个数:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:nmopNonElemNum" lablename="新增与修改类操作非规范元素个数" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.nmopNonElemNum}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">新增与修改类操作元素总个数:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:nmopElemTotal" lablename="新增与修改类操作元素总个数" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.nmopElemTotal}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">业务成熟度:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:workSubsysMaturity" lablename="业务成熟度" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.workSubsysMaturity}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">信息规范程度:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:infoStandGrade" lablename="信息规范程度" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.infoStandGrade}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">知识型操作个数:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:knowledgeOpnum" lablename="知识型操作个数" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.knowledgeOpnum}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">知识型操作自动实现的操作数量:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:knowledgeAutoOpnum" lablename="知识型操作自动实现的操作数量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.knowledgeAutoOpnum}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">知识结构化比例:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:knowledStrucktGrade" lablename="知识结构化比例" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.knowledStrucktGrade}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">本地信息量:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:local" lablename="本地信息量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.local}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">利用外部信息量:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:outGov" lablename="利用外部信息量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.outGov}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">外部利用信息量:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:inGov" lablename="外部利用信息量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.inGov}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">利用公共信息量:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:outPub" lablename="利用公共信息量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.outPub}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">公共利用信息量:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:inPub" lablename="公共利用信息量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.inPub}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">本地服务量:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:flocal" lablename="本地服务量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.flocal}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">利用外部服务量:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:foutGov" lablename="利用外部服务量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.foutGov}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">外部利用服务量:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:finGov" lablename="外部利用服务量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.finGov}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">利用公共服务量:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:foutPub" lablename="利用公共服务量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.foutPub}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">公共利用服务量:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:finPub" lablename="公共利用服务量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.finPub}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">业务架构开放程度:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:busFrameOpenGrade" lablename="业务架构开放程度" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.busFrameOpenGrade}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">节点数量:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:activityNum" lablename="节点数量" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${sysInfomation.activityNum}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">马尔科夫节点数量:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:sysInfomation:markActivityNum" lablename="马尔科夫节点数量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${sysInfomation.markActivityNum}" /></span></td>
  </tr>
 </tbody>
</table>
