
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>order明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">order详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
	</div>
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
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">承接人ID:</td>
   <td class="formInput" style="width:80%;">${orderyan.o_carrier_id}</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">订单行数量:</td>
   <td class="formInput" style="width:80%;">${orderyan.o_ol_cnt}</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">是否全是本地商品:</td>
   <td class="formInput" style="width:80%;">${orderyan.o_all_local}</td>
  </tr>
 </tbody>
</table>
</body>
</html>

