<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>通用webservice调用设置管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	var win;
	function test(setId){
		var url=__ctx+'/platform/bpmCommonWsSet/bpmCommonWsSet/test.ht?setId='+setId;
		var winArgs="dialogWidth=840px;dialogHeight=600px;help=0;status=0;scroll=1;center=1";
		//window.showModalDialog(url,"",winArgs);
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:600,
			width: 840,
			title : '通用webservice调用设置管理',
			url: url, 
			isResize: true,
		});
	};
	
	function developHelp(){
		if(!win){
			var obj=$("#develop_help_div");
			win= $.ligerDialog.open({ target:obj , height: 430,width:610, modal :true}); 
		}
		win.show();
	};
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">通用webservice调用设置管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a onclick="developHelp()" class="link copyTo"><span></span>开发帮助</a></div>
					
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">别名:</span><input type="text" name="Q_alias_SL"  class="inputText" />
						<span class="label">wsdl地址:</span><input type="text" name="Q_wsdlUrl_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmCommonWsSetList" id="bpmCommonWsSetItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${bpmCommonWsSetItem.id}">
				</display:column>
				<display:column property="alias" title="别名" sortable="true" sortName="alias"></display:column>
				<display:column property="wsdlUrl" title="wsdl地址" sortable="true" sortName="wsdlUrl" maxLength="80"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${bpmCommonWsSetItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${bpmCommonWsSetItem.id}" class="link edit">编辑</a>
					<a href="###" onclick="test(${bpmCommonWsSetItem.id})" class="link detail">测试</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmCommonWsSetItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
	
	<div id="develop_help_div" class="hidden" style="font-size:13px;">
		<table id="callCodeTable" class="table-grid table-list">
			<tr>
				<td>调用方法：</td>
				<td style="text-align: left;">
					①构建webservice调用参数<br />
					Map&lt;String,Object> map = new HashMap&lt;String, Object>();<br />
					map.put("test",obj);<br />
					②调用webservice接口<br />
					String xml = WebserviceHelper.executeXml(alias, map);<br />
				</td>
			</tr>
			<tr>
				<td>解析返回值：</td>
				<td style="text-align: left;">
					①使用dom4j来解析xml格式的返回值<br />
					Document doc= Dom4jUtil.loadXml(xml);<br />
					List&lt;Node> nodes = doc.selectNodes("//string");<br />
					<span class="red">②注意：如果返回的xml有名称空间，上面的xpath可能解析不到节点，比如下面的xml返回值<br /></span>
			    	&lt;?xml version="1.0" encoding="UTF-8" ?> <br />
			        &lt;root xmlns="http://WebXml.com.cn/"> <br />
			        &nbsp;&nbsp;&nbsp;&nbsp;&lt;string>test&lt;/string><br />
			      	&lt;/root><br />
			      	<span class="red">此时需要按照下面的方式来获取string节点<br/></span>
			      	Document doc= Dom4jUtil.loadXml(xml);<br />
			      	Dom4jXPath path = new Dom4jXPath("//ns:string");<br />
					path.addNamespace("ns", "http://WebXml.com.cn/");<br />
					List&lt;Node> nodes = path.selectNodes(doc);
				</td>
			</tr>
		</table>
	</div>
</body>
</html>


