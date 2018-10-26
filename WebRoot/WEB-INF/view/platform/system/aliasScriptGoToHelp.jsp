<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@include file="/commons/include/get.jsp" %>
	<title>别名脚本开发帮助</title>
	<style type="text/css">
	html,body{height:100%;width:100%; overflow: auto;}
	</style>
</head>
<body>

	<div class="panel">
		<div class="panel-top">
		</div>
		<div class="panel-body">
				<div class='panel-table'>
						<table id="callCodeTable" class="table-grid table-list">
							<tr>
								<th colspan="2">别名脚本开发帮助</th>
							</tr>
							<tr>
								<td>步骤一：</td><td style="text-align: left;">引入RunAliasScript.js：<br>
								&lt;script type="text/javascript" src="\${ctx}/js/hotent/platform/form/RunAliasScript.js"&gt;&lt;/script&gt;；</td>
							</tr>
							<tr>
								<td>步骤二：</td><td style="text-align: left;">构造需要向别名脚本传递的参数和对应的键值：<br>
								    var conf = {<br/>
								                &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
										            aliasName:'别名（唯一的）',  &nbsp;&nbsp; &nbsp;&nbsp; //脚本的别名（唯一的）             <br/>     
												&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
												    arg0:'参数值1',   &nbsp;&nbsp;&nbsp;&nbsp; //脚本的参数名和值（参数名必须按照建立别名脚本时的参数名填写）              <br/>     
												&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
												    arg1:'参数值2'     &nbsp;&nbsp; &nbsp;&nbsp; //脚本的参数名和值        <br/>
											    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
											    ...... <br/>                
											    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
											   };  <br/>
								</td>
							</tr>
							<tr>
								<td>步骤三：</td><td style="text-align: left;">调用RunAliasScript方法，传入脚本的别名和参数及其对应的值；<br/>
								             RunAliasScript(conf); &nbsp;&nbsp; &nbsp;&nbsp; //结果是返回JSON数据
								</td>
							</tr>
							<tr>
								<td style="width:100px;"><font color="red">调用代码的例示：</font></td>
								<td style="text-align: left;">
								  <font color="red">
									&lt;script type="text/javascript" src="\${ctx}/js/hotent/platform/form/RunAliasScript.js"&gt;&lt;/script&gt;<br>
									&lt;script type="text/javascript"&gt;
									<br>
										$(function(){ <br/>
											&nbsp;&nbsp;var conf = {<br/>
														&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
													    aliasName:'testCode', &nbsp;&nbsp; &nbsp;&nbsp;//脚本的别名（唯一的）<br/>
													    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
													    arg0:'0',   &nbsp;&nbsp; &nbsp;&nbsp; //脚本的参数名和值（可以有多个不同名的参数）<br/>
													    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
													    arg1:'two'  <br/>
													    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
													   }; <br/>
											&nbsp;&nbsp;//执行别名脚本调用方法<br/>
											&nbsp;&nbsp;var json = RunAliasScript(conf);    &nbsp;&nbsp; &nbsp;&nbsp; //结果是返回JSON数据<br/>
										   &nbsp;&nbsp; if(json.isSuccess==0){              <br/>
												&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
												alert(json.result);       &nbsp;&nbsp; &nbsp;&nbsp; //显示脚本执行后返回的JSON结果      <br/>
											&nbsp;&nbsp;}else{  <br/>
												&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
												alert(json.msg);                 <br/>
											&nbsp;&nbsp;}<br/>
										}); <br/>
									&lt;/script&gt;
								  </font>
								</td>
							</tr>
						</table>
				</div>
			
		</div><!-- end of panel-body -->
	</div> <!-- end of panel -->
       	 
</body>
</html>
