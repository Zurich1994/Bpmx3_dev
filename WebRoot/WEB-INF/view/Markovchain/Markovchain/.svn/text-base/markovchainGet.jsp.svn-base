
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>马尔科夫链表明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/subform.js"></script>
<script type="text/javascript">
	//放置脚本
	function backbtn(){
	 var defId=$("#defid").val();
			window.location.href = "${ctx}/Markovchain/Markovchain/markovchain/listbydefid.ht?defId="+defId;
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">马尔科夫链表详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" onclick=backbtn()><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="4" class="formHead">马尔科夫链表</td>
  </tr>
  <tr>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">马尔科夫链名称:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${markovchain.markovchainNAME}</span></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">是否参与仿真:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><c:if test="${markovchain.isSimulation=='是'}">是</c:if><c:if test="${markovchain.isSimulation=='否'}">否</c:if></span></td>
  </tr>
  <tr>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">所属流程:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${markovchain.belongto}</span></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">依赖马尔科夫链:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${markovchain.dependmark}</span></td>
  </tr>
  <tr>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">马尔科夫链发生次数:</td>
   <td style="width:35%;" class="formInput">${markovchain.frequency}</td>
   <td align="right" style="word-break:break-all;" class="formTitle" nowrap="nowarp">马尔科夫链的概率:</td>
   <td style="width:35%;" class="formInput">${markovchain.probability}</td>
  </tr>
  <tr>
   <td align="right" style="width:15%;word-break:break-all;" class="formTitle" nowrap="nowarp"><span style="font-size:12px;text-align:right;background-color:#f0f1f1;">马尔科夫链详细备注:</span></td>
   <td style="width:35%;word-break:break-all;" class="formInput" rowspan="1" colspan="3"><br /><span name="editable-input" style="display:inline-block;" isflag="tableflag">${markovchain.markovchainDes}</span></td>
  </tr>
  <tr>
   <td align="right" style="width:15%;word-break:break-all;" class="formTitle" nowrap="nowarp"><span style="font-size:12px;text-align:right;background-color:#f0f1f1;">马尔科夫链xml文件:</span></td>
   <!--<td style="width:35%;word-break:break-all;" class="formInput" rowspan="1" colspan="3">${markovchain.markovchainXML}</td>
   -->
   <td>
   <img src="${ctx}/markImage?id=${markovchain.id}"/></td>
   
  </tr>
 </tbody>
</table>
<input type="text" id="defid" value="${markovchain.defid}">
</body>
</html>

