
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<html> 
<head>

	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/ReportServer?op=resource&resource=/com/fr/web/platform/css/frplatform.css"/> 
	<script type="text/javascript" src="${ctx}/ReportServer?op=emb&resource=finereport.js&inter=zh_CN"></script> 
 
	<script type="text/javascript"> 
		$(function() {			
			
			var connectionInfo = {
				fr_platform_version: -1
			};
			
			var changedConnectionArray = {};
			var changedConName = new Array();
			
			$.ajax({
				url : '${ctx}/ReportServer?op=fr_server&cmd=sc_getconnectioninfo',
				type : 'POST',
				async : false,
				complete : function(res, status) {
					if (res.responseText == null||res.responseText == "") {
						return;
					}
					connectionInfo = FR.jsonDecode(res.responseText);
				}
			});

			var connection = connectionInfo.connection;
			document.getElementById('connection').innerHTML = easyTemplate($('#txtTaskStatus').text(),connection);

			$.each(connection, function(i){
				
				var driverText =$('input[name=driver]').blur(function(){
					var changedConnection = changedConnectionArray[connection[i].name];
					if (changedConnection == null) {
						changedConName.push(connection[i].name);
						changedConnection = {};
						changedConnectionArray[connection[i].name] = changedConnection;
					}
					changedConnection['driver'] = driverText.attr('value');
				});
				
				var urlText =$('input[name=url]').blur(function(){
					var changedConnection = changedConnectionArray[connection[i].name];
					if (changedConnection == null) {
						changedConName.push(connection[i].name);
						changedConnection = {};
						changedConnectionArray[connection[i].name] = changedConnection;
					}
					changedConnection['url'] = urlText.attr('value');
				});
				
				var userText =$('input[name=user]').blur(function(){
					var changedConnection = changedConnectionArray[connection[i].name];
					if (changedConnection == null) {
						changedConName.push(connection[i].name);
						changedConnection = {};
						changedConnectionArray[connection[i].name] = changedConnection;
					}
					changedConnection['user'] = userText.attr('value');
				});
				
				var pwText =$("input[name='password']").blur(function(){
					var changedConnection = changedConnectionArray[connection[i].name];
					if (changedConnection == null) {
						changedConName.push(connection[i].name);
						changedConnection = {};
						changedConnectionArray[connection[i].name] = changedConnection;
					}
					changedConnection['password'] = pwText.attr('value');
				});
				
				$('#testConnect').click(function() {
					var data = {};
					var tbid = $(this).attr('tbid');
					data['driverName'] = $('#'+tbid+' input[name=driver]').val();
					data['url'] = $('#'+tbid+' input[name=url]').val();
					data['userName'] = $('#'+tbid+' input[name=user]').val();
					data['password'] = $('#'+tbid+' input[name=password]').val();
					parent.$.ligerDialog.waitting("正在连接请稍后...");
					$.post('${ctx}/platform/system/sysDataSource/testConnectByForm.ht', data, function(data) {
						parent.$.ligerDialog.closeWaitting();
						var d = data[0];
						if(d.success) {
							parent.$.ligerDialog.success('<p><font color="green">连接成功!</font></p>');
						} else {
							parent.$.ligerDialog.error('<p><font color="red">连接失败!<br>原因：' + d.msg + '</font></p>');
						}
					});
				});
				
			});
									
			var buttonS = $('a.save').click(function() {
				
				FR.showLoadingDialog({width : 310, height : 80, title : "数据连接修改", text : "正在提交"});
				changedConnectionArray['fr_platform_version'] = connectionInfo.fr_platform_version;
				FR.ajax({					
					url : '${ctx}/ReportServer?op=fr_server&cmd=sc_connection_attrset',
					type : 'POST',
					data : {
						type : 'POST',
						__parameters__ : changedConnectionArray,
						changedConName : changedConName
					},
					complete : function(res, status) {
						FR.hideLoadingDialog();
						var conAttrStatus = res.responseText;
						if (conAttrStatus == -1) {
							parent.$.ligerDialog.warn("提交冲突!请刷新页面后再修改提交","提示");
						} else if(conAttrStatus=="success"){
							parent.$.ligerDialog.success("提交成功","提示");
						}else{
							parent.$.ligerDialog.error("提交失败!" + conAttrStatus,"提示");
						}
					}
	    		});
			});

		});
	</script> 
</head> 
 
<body>
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">数据连接查询修改</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" href="javascript:;"><span></span>保存</a></div>
			</div>
		</div>
	</div>
	<textarea id="txtTaskStatus" style="display:none">
		<#list data as connection>
		<table id='\${connection.name}' cellpadding="0" cellspacing="0" border="0" class="table-detail">
			<tbody>
				<tr><th width="20%">数据连接名:</th><td style="width:250px">\${connection.name}  <a class="link test" id="testConnect" tbid="\${connection.name}">测试连接</a></td></tr>
				<tr><th width="20%">驱动器:</th><td><input type="text" name="driver" class="inputText" style="width:40%;" value="\${connection.driver}"></td></tr>
				<tr><th width="20%">url:</th><td><input type="text" name="url" class="inputText" style="width:70%;" value="\${connection.url}"></td></tr>
				<tr><th width="20%">用户名:</th><td><input type="text" name="user" class="inputText" value="\${connection.user}"></td></tr>
				<tr><th width="20%">密码:</th><td><input type="text" name="password" class="inputText" value="\${connection.password}"></td></tr>
			</tbody>
		</table>
		</#list>
	</textarea>
	<div id="connection" style="padding: 5px; font-size: 16px; overflow: auto;"></div>
</body> 
</html>