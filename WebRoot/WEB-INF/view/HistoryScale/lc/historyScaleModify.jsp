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
	function selectChange(obj) {
		var value = obj.value;
		var v1 = document.getElementById("amount");
		
		alert(v1.style.display+"haha");
		if (value != "年年") {
			console.log("two is hidden");
			v1.style.display = "none";			
		}else{ 
			alert("1");
			v1.style.display=""; 
			
			}
	}
</script>
<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">历史数据发生比例表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">历史比例编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="historyProportionId" lablename="历史比例编号" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${historyScale.historyProportionId}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="processtId" lablename="流程编号" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${historyScale.processtId}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">比例时间类别:</td>
   <td  >
  			 <select name="type" style="258px;" onchange="selectChange(this)"  >
                            <option  value="年年" >年年</option> 
                            <option value="月日" >年月</option>
                            <option value="月日"  >月日</option>
                             <option value="日时" >日时</option>
                            <option value="时分" >时分</option>
              </select>
        
   </td>
  </tr>
   <tr  id="amount" >
   <td style="width:20%;display: block;" class="formTitle" align="right" nowrap="nowarp">发生量:</td>
   <td  class="formInput" style="width:80%;display: block;"><input name="occurenceAmount" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" type="text" value="${historyDataLc.occurenceAmount}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">比例:</td>
   <td class="formInput" style="width:80%;">
   		<span name="editable-input" style="display:inline-block;" isflag="tableflag">
   			<input name="proportion" lablename="比例" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${historyScale.proportion}" />
   		</span>
   </td>
  </tr><!--
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">比例发生时间:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="proportionOccurTime" lablename="比例发生时间" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${historyScale.proportionOccurTime}" /></span></td>
  </tr>
 --></tbody>
</table>
