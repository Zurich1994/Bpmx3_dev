<%--
	time:2015-03-18 11:22:46
	desc:edit the 首页栏目
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags/wf"%>
<html>
<head>
	<title>编辑 首页栏目</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<f:link href="codemirror/lib/codemirror.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/codemirror/lib/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/mode/xml/xml.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/mode/javascript/javascript.js"></script>
    <script type="text/javascript" src="${ctx}/js/codemirror/mode/css/css.js"></script>
    <script type="text/javascript" src="${ctx}/js/codemirror/mode/htmlmixed/htmlmixed.js"></script>

	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/BpmQueryDialog.js"></script>
	<script type="text/javascript">
		var returnUrl="${returnUrl}";	
		
		$(function() {
			var width = $("#templateHtml").width();
			var height = $("#templateHtml").height();
			var editor = CodeMirror.fromTextArea(document.getElementById("templateHtml"), {
				mode: "text/html",
				tabMode: "indent",
				lineNumbers: true
			 });
			
			editor.setSize(width,height);
			
			$(":radio[name='dataMode']").click(function(){
				var  checked=$(this).prop('checked');
				var val=$(this).val();
				if(checked&&val==1){
					$("#queryAlias").show();
					$("#serviceMethod").hide();
				}else{
					$("#queryAlias").hide();
					$("#serviceMethod").show();
				}
			});
			
			
			$("a.save").click(function() {
				$("#templateHtml").val(editor.getValue());
				var dataFrom = "",dataMode =$("input[name='dataMode']:checked").val();
				
				if(dataMode ==0){
					dataFrom = $("#dataFromService").val();
				}else{
					dataFrom = $("#dataFromQuery").val();
				}
				$('#dataFrom').val(dataFrom);
				submitForm();
			});
		});
		
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#frmSubmit').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success(obj.getMessage(),"提示信息", function(rtn) {
					if(rtn){
						if(window.opener){
							window.opener.location.reload();
							window.close();
						}else{
							this.close();
							window.location.href="list.ht";
						}
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function selectQuery(){
			BpmQueryDialog({callback:function(alias){
				$("#dataFromQuery").val(alias);
			}});
		}

		function setParam(){
			DialogUtil.open({
				height:500,
				width: 660,
				title : '参数设置',
				url: __ctx+"/platform/system/sysIndexColumn/setParam.ht", 
				isResize: true,
				//自定义参数
				dataParam: $('#dataParam').val(),
				sucCall:function(rtn){
					$('#dataParam').val(rtn);
					
				}
			});
		}
		
	</script>
</head>
<body>
<form id="frmSubmit" method="post" action="save.ht">
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${sysIndexColumn.id !=null}">
			        <span class="tbar-label">编辑首页栏目</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加首页栏目</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" href="javascript:void(0);"><span></span>保存</a></div>
				<div class="group"><a class="link back" href="${returnUrl}"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<table class="table table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%">栏目名称:<span class="required">*</span> </th>
						<td>
						<input type="text" id="name" name="name" style="width: 50%;" value="${sysIndexColumn.name}"  class="inputText" style="width: 80%;" validate="{required:true,maxlength:100}"/>
						</td>
						
						<th width="20%">栏目别名:<span class="required">*</span> </th>
						<td>
							<input type="text" id="alias" name="alias" style="width: 50%;" value="${sysIndexColumn.alias}"  class="inputText" style="width: 80%;"  validate="{required:true,maxlength:100}"/>
							<a href="javascript:;" class="tipinfo"><span>别名在系统中不能重复</span></a>
						</td>
					</tr>
					<tr>
						<th width="20%">栏目分类: </th>
						<td>
							<select id="catalog" name="catalog" >
								<c:forEach items="${catalogList}" var="catalog">
									<option value="${catalog.typeId}" <c:if test="${ catalog.typeId== sysIndexColumn.catalog}">selected</c:if>>${catalog.typeName}</option>
								</c:forEach>
							</select>
						</td>
						<th width="20%">栏目类型: </th>
						<td>
							<select id="colType" name="colType">
								<option value="0" <c:if test="${sysIndexColumn == null ||sysIndexColumn.colType == '0'}">selected</c:if>>一般类型栏目</option>
								<option value="1" <c:if test="${sysIndexColumn.colType == '1'}">selected</c:if>>图表类型栏目</option>
								<option value="2" <c:if test="${sysIndexColumn.colType == '2'}">selected</c:if>>日历类型栏目</option>
								<option value="3" <c:if test="${sysIndexColumn.colType == '3'}">selected</c:if>>滚动类型栏目</option>
							</select>
							<a href="javascript:;" class="tipinfo"><span>这个是识别栏目是通过什么类型展示数据。</span></a>
						</td>
					</tr>
					<tr>
						<th width="20%">更多路径: </th>
						<td>
							<input type="text" id="colUrl" name="colUrl" style="width: 50%;" value="${sysIndexColumn.colUrl}"  class="inputText" style="width: 80%;" />
						</td>
						<th width="20%">展示效果: </th>
						<td>
							<select id="showEffect" name="showEffect">
								<option value="0" <c:if test="${sysIndexColumn == null ||sysIndexColumn.showEffect == '0'}">selected</c:if>>默认效果</option>
								<option value="1" <c:if test="${sysIndexColumn.showEffect == '1'}">selected</c:if>>走马灯</option>
								<option value="2" <c:if test="${sysIndexColumn.showEffect == '2'}">selected</c:if>>幻灯片</option>
							</select>
						</td>
					</tr>
					<tr>
						<th width="20%">数据加载方式: </th>
						<td colspan="3">
							<label><input type="radio"   name="dataMode"  value="0" <c:if test="${sysIndexColumn == null ||sysIndexColumn.dataMode==0}">checked="checked"</c:if>  validate="{required:true,maxlength:100}"/>Service方法</lable>
							<label><input type="radio"  name="dataMode" value="1" <c:if test="${sysIndexColumn.dataMode==1}">checked="checked"</c:if>  validate="{required:true,maxlength:100}"/>自定义查询方式</lable>
						</td>
					</tr>
					
					<tr id="serviceMethod" <c:if test="${sysIndexColumn != null && sysIndexColumn.dataMode !=0}">style="display:none"</c:if>>
						<th width="20%">方法路径: </th>
						<td colspan="3">
							<input type="text"  id="dataFromService" style="width: 40%;" value="${fn:escapeXml(sysIndexColumn.dataFrom)}"  class="inputText" style="width: 80%;"/>
							<a  href="javascript:;" onclick="setParam()"  class="button"><span>参数设置</span></a>
							<a href="javascript:;" class="tipinfo"><span>填写格式:1、service+"."+方法名,service为spring的service名称。</span></a>
						</td>
					
					</tr>
					<tr id="queryAlias" <c:if test="${sysIndexColumn.dataMode!=1}">style="display:none"</c:if> >
						<th width="20%">自定义查询: </th>
						<td colspan="3">
							<input type="text"  id="dataFromQuery" style="width: 20%;" value="${sysIndexColumn.dataFrom}"  readonly="readonly" class="inputText" />
							&nbsp;<a  href="javascript:;" onclick="selectQuery()"  class="button"><span>选 择...</span></a>
						</td>
					</tr>
				
					<tr>
						<th width="20%">是否公共栏目: </th>
						<td>
							<label><input type="radio"  name="isPublic" value="1" <c:if test="${sysIndexColumn.isPublic==1}">checked="checked"</c:if>  />是</lable>
							<label><input type="radio"   name="isPublic"  value="0" <c:if test="${sysIndexColumn == null ||sysIndexColumn.isPublic==0}">checked="checked"</c:if>  />否</lable>
						</td>
						<th width="20%">栏目高度: </th>
						<td><input type="text" id="colHeight" name="colHeight" value="${sysIndexColumn.colHeight}" validate="{required:false,number:true,maxIntLen:10 }" class="inputText"/>
							<a href="javascript:;" class="tipinfo"><span>如果不填写，默认320。</span></a>
						</td>
					</tr>
					<tr>
						<th width="20%">是否支持刷新: </th>
						<td>
							<label><input type="radio"  name="supportRefesh" value="1" <c:if test="${sysIndexColumn.supportRefesh==1}">checked="checked"</c:if>  />是</lable>
							<label><input type="radio"   name="supportRefesh"  value="0" <c:if test="${sysIndexColumn == null ||sysIndexColumn.supportRefesh==0}">checked="checked"</c:if>  />否</lable>
						</td>
						<th width="20%">刷新时间: </th>
						<td><input type="text" id="refeshTime" name="refeshTime" value="${sysIndexColumn.refeshTime}" validate="{required:false,number:true,maxIntLen:10 }" class="inputText"/>
							<a href="javascript:;" class="tipinfo"><span>时间计算是以秒为单位。如果选择支持刷新，默认刷新时间是5秒。</span></a>
						</td>
					</tr>
					<tr>
						<th width="20%">描述: </th>
						<td ><input type="text" id="memo" name="memo" value="${sysIndexColumn.memo}" validate="{required:false,maxlength:768}" class="inputText"/></td>
						<th width="20%" id="needPage">是否分页: </th>
						<td>
							<input type="radio" name="needPage" value="0"   <c:if test="${sysIndexColumn == null ||sysIndexColumn.needPage==0}">checked="checked"</c:if> >不分页
							<input type="radio" name="needPage" value="1" " <c:if test="${sysIndexColumn.needPage==1}">checked="checked"</c:if>>分页
							
						</td>
					</tr>
					<tr>
						<th width="20%">模板html: </th>
						<td colspan="3">
							<textarea id="templateHtml" name="templateHtml" style="width: 90%;height: 220px;">${fn:escapeXml(sysIndexColumn.templateHtml)}</textarea>
						</td>
					</tr>
				</table>
			<textarea id="dataParam" name="dataParam" style="display: none;">${fn:escapeXml(sysIndexColumn.dataParam)}</textarea>
			<input type="hidden" name="dataFrom" id="dataFrom" value="${sysIndexColumn.dataFrom}">
			<input type="hidden" id="orgId" name="orgId" value="${sysIndexColumn.orgId}"  class="inputText"/>
			<input type="hidden" name="id" value="${sysIndexColumn.id}" />					
	</div>
</div>
</form>
</body>
</html>
