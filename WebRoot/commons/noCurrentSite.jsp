<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
	<title>没有设置站点</title>
	<%@include file="/commons/include/get.jsp" %>
	
</head> 
<body>

<table border="0" cellspacing="0" cellpadding="0" class="listHeader" style="width:500px;margin-top: 50px"  align="center">
	<tr>
		<td class="title">没有设置站点</td>
		<td>
		
		</td>
	</tr>
</table>

<table border="0" cellspacing="0" cellpadding="0" class="listTable" style="width:500px;height: 150px" align="center">
	<tr>
		<td>
			<table width="100%" height="100%">
				<tr>
					<td width="100px"><img alt="" src="${ctx }/commons/image/error.gif"></td>
					<td>
					没有设置站点,请<a href="${ctx }/manage/site/siteInfo.ht">设置当前操作站点</a>。</td>
				</tr>
			</table>

		</td>
	</tr>
</table>

</body>
</html>

