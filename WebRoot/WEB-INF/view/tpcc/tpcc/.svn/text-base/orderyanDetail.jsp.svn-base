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
<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">order</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">订单号:</td>
   <td class="formInput" style="width:80%;">${orderyan.o_id}</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">地区号:</td>
   <td class="formInput" style="width:80%;">${orderyan.o_d_id}</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">仓库号:</td>
   <td class="formInput" style="width:80%;">${orderyan.o_w_id}</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">用户号:</td>
   <td class="formInput" style="width:80%;">${orderyan.o_c_id}</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">日期和时间:</td>
   <td class="formInput" style="width:80%;"><fmt:formatDate value='${orderyan.o_entry_d}' pattern='yyyy-MM-dd'/></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">o_carrier_id:</td>
   <td class="formInput" style="width:80%;">${orderyan.o_carrier_id}</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">订单行数量:</td>
   <td class="formInput" style="width:80%;">${orderyan.o_ol_cnt}</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">o_all_local:</td>
   <td class="formInput" style="width:80%;">${orderyan.o_all_local}</td>
  </tr>
 </tbody>
</table>
