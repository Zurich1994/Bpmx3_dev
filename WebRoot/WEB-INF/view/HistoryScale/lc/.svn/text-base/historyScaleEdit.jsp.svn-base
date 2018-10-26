<%@page language="java" pageEncoding="UTF-8" import="com.hotent.HistoryScale.model.lc.HistoryScale"%>

<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 历史数据发生比例表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var timeType = $("#timeType").val();
			//alert(timeType);
			if(timeType != "year"){
				$("#amount").hide();
				$("#year").hide();
			}
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#historyScaleForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#historyScaleForm').submit();
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
						window.location.href = "${ctx}/HistoryScale/lc/historyScale/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function save(){
			//alert("save");
			var processId = $("#processId").val();
			//alert(processId);
			document.getElementById("historyScaleForm").action="save.ht?processId="+processId;
		}
		
	<%--
	function selectChange(obj) {
		var value = obj.value;
		
		var v1 = document.getElementById("amount");
		var v2 = document.getElementById("year");
		
		if (value != "年年") {
			console.log("two is hidden");
			v1.style.display = "none";	
			v2.style.display = "none";			
		}else{ 
			//alert("2");
			v1.style.display=""; 
			v2.style.display = "";	
			}
	}

	--%>

	
	$(document).ready(function(){
　　　　//adding your code here
		var v1 = document.getElementById("amount");
		var v2 = document.getElementById("year");
		var value="${rtnType}";
		if (value != "year") {
			console.log("two is hidden");
			v1.style.display = "none";	
			v2.style.display = "none";			
		}else{ 
			//alert("2");
			v1.style.display=""; 
			v2.style.display = "";	
			}
　　}); 
	
	

</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty historyScaleItem.id}">
			        <span class="tbar-label"><br><span></span>编辑历史数据发生比例表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加历史数据发生比例表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;" onclick="save()"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="historyScaleForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">历史数据发生比例表</td>
  </tr>
  <%-- 
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">历史比例编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="historyProportionId" lablename="历史比例编号" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="111${rtnPid}" /></span></td>
  </tr>
  --%>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">

   <input id="processId" name="processtId" lablename="流程编号" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="<%=request.getParameter("processId") %>" /></span></td>

   

  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">比例时间类别:</td>
   <td align="left">

   <%-- 
  			 <select name="proportionTimeType" id = "type" class="select " style="width:258px;" onchange="selectChange(this)"  >  			 				
                            <option  value="年年"  <c:if test="${historyScale.proportionTimeType=='年年'}">selected</c:if> >年年</option>
                            <option value="年月"  <c:if test="${historyScale.proportionTimeType=='年月'}">selected</c:if> >年月</option>
                            <option value="月日"    <c:if test="${historyScale.proportionTimeType=='月日'}">selected</c:if> >月日</option>
                            <option value="日时"   <c:if test="${historyScale.proportionTimeType=='日时'}">selected</c:if> >日时</option>
                            <option value="时分"    <c:if test="${historyScale.proportionTimeType=='时分'}">selected</c:if>  >时分</option>
              </select>

  			 <select name="proportionTimeType" id = "proportionTimeType" class="select " style="width:258px;" onchange="selectChange(this)"  >  			 				
                            <option  value="<%=HistoryScale.STATUS_YEAR%>"  <c:if test="${rtnType=='year'}">selected</c:if> >年年</option>
                            <option value="<%=HistoryScale.STATUS_MONTH%>"  <c:if test="${rtnType=='month'}">selected</c:if> >年月</option>
                            <option value="<%=HistoryScale.STATUS_DAY%>"    <c:if test="${rtnType=='day'}">selected</c:if> >月日</option>
                            <option value="<%=HistoryScale.STATUS_HOUR%>"   <c:if test="${rtnType=='hour'}">selected</c:if> >日时</option>
                            <option value="<%=HistoryScale.STATUS_SEC%>"    <c:if test="${rtnType=='minute'}">selected</c:if> >时分</option>
              </select> 

   --%>
   <input id="timeType" name="proportionTimeType" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}"  type="text" value="<%=request.getParameter("timeType") %>" />
   
   </td>

  </tr>
 <tr id="year">
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">年选择区间:</td>
  
   <td align="left">
  			 <select name="type1" style="width:258px;"  >                           
                            <option  value="2015" >2015年</option>
                            <option  value="2014" >2014年</option>
                            <option  value="2013 ">2013年</option>
                            <option  value="2012" >2012年</option>
                             <option  value="2011" >2011年</option>
                             <option  value="2010" >2010年</option>
                            <option  value="2009" >2009年</option>
                            <option  value="2008" >2008年</option>
                            <option  value="2007" >2007年</option>
                             <option  value="2006" >2006年</option>
                             <option  value="2005" >2005年</option>
                            <option  value="2004" >2004年</option>
                            <option  value="2003" >2003年</option>
                            <option  value="2002" >2002年</option>
                             <option  value="2001" >2001年</option>
                             <option  value="2000" >2000年</option>
                           
              </select>

  			  <select name="type2" style="width:258px;"  >                           
                            <option  value="2015" >2015年</option>
                            <option  value="2014" >2014年</option>
                            <option  value="2013 ">2013年</option>
                            <option  value="2012" >2012年</option>
                             <option  value="2011" >2011年</option>
                             <option  value="2010" >2010年</option>
                            <option  value="2009" >2009年</option>
                            <option  value="2008" >2008年</option>
                            <option  value="2007" >2007年</option>
                             <option  value="2006" >2006年</option>
                             <option  value="2005" >2005年</option>
                            <option  value="2004" >2004年</option>
                            <option  value="2003" >2003年</option>
                            <option  value="2002" >2002年</option>
                             <option  value="2001" >2001年</option>
                             <option  value="2000" >2000年</option>
                           
              </select>
   </td>
  </tr>
   <tr  id="amount" >
   <td  style="width:20%;" class="formTitle" align="right" nowrap="nowarp">发生量:</td>
   <td  class="formInput" style="width:80%;"><input name="occurenceAmount" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" type="text" value="${historyDataLc.occurenceAmount}" />
 年数：
  			 <select name="type3" style="width:258px;"  >                           
                            <option  value="2015" >2015年</option>
                            <option  value="2014" >2014年</option>
                            <option  value="2013 ">2013年</option>
                            <option  value="2012" >2012年</option>
                             <option  value="2011" >2011年</option>
                             <option  value="2010" >2010年</option>
                            <option  value="2009" >2009年</option>
                            <option  value="2008" >2008年</option>
                            <option  value="2007" >2007年</option>
                             <option  value="2006" >2006年</option>
                             <option  value="2005" >2005年</option>
                            <option  value="2004" >2004年</option>
                            <option  value="2003" >2003年</option>
                            <option  value="2002" >2002年</option>
                             <option  value="2001" >2001年</option>
                             <option  value="2000" >2000年</option>
                           
              </select>
   </td>
  </tr>
 
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">比例:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="proportion" lablename="比例" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${historyScale.proportion}" /></span></td>
  </tr>
  
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${historyScale.id}"/>
	</form>
</body>
</html>
