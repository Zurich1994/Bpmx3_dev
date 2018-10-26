<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="com.hotent.core.web.controller.BaseController"%>
<%@page import="com.hotent.core.web.ResultMessage"%>

<%
ResultMessage _obj_=(ResultMessage)session.getAttribute(BaseController.Message);
if(_obj_!=null){
	session.removeAttribute(BaseController.Message);
%>
<script type="text/javascript">
$(function(){
	<%
	  if(_obj_.getResult()==ResultMessage.Success){
	%>
		$.ligerDialog.success('<p><font color="green"><%=_obj_.getMessage()%></font></p>');
	
	<%}
	  else{
		if(!"".equals(_obj_.getCause())){
	%>
		$.ligerDialog.err('','<%=_obj_.getMessage()%>','<%=_obj_.getCause()%>');
		<%}else{%>
		$.ligerDialog.warn('<p><font color="red"><%=_obj_.getMessage()%></font></p>');
	<%   }
	   }%>
});
</script>
<%
} %>



