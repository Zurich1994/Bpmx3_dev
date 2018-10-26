<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>支付者信息</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript">
		/* $(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#smbForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#smbForm').submit();
				}
			});
		});*/
		
		
		
		/*function sentuserid(USERID) {
			var USERID = new com.hotent.form.ResultMessage(responseText);
			
			}
		}*/
		
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		
		<h2 align="center">是否显示详细信息</h2>
		<div class="panel-toolbar" >
			<div class="toolBar"   >
				<div class="group"> 
				<div class="group"><a  href="get.ht"  class="link back" ><span></span>确定</a></div>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group"><a  href="javascript:history.back()"  class="link back" ><span></span>取消</a></div>
			</div>
		</div>
	</div>
<%  
String name=request.getParameter("name");  
 
%> 
<div id="hidden" style="display:none">
<form name="hidden"  method="post" action="get.ht?name=<%=request.getParameter("name")%>">  
        <input type="text" >  

</form>
</div>
	
</body>
</html>

