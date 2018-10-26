<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>字段设置</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript">
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	// 保存
	function save() {
		var fieldJson = getJsonArrStr(); 
		var tableId = $("#tableId").val();
		var rtn =  {
			fieldJson:	fieldJson,
			tableId:tableId
		};
		console.info(fieldJson);
		dialog.get("sucCall")( rtn);
		dialog.close();
	}
	
	function getJsonArrStr() {
		var json = [];
		$("#fieldTbl  tr[var='fieldTr']").each(function(i){
			var me = $(this),obj={};
			obj.fieldName =$("[name='fieldName']",me).html();
			obj.fieldDesc =$("[name='fieldDesc']",me).val();
			obj.isShow = $("[name='isShow']:checked",me).val() == 1?'1':'0';
			obj.sn =(i+1)+"" ;
			json.push(obj);
		});
		return JSON2.stringify(json);
		}
	
	function moveTr(obj, isUp) {
		var thisTr = $(obj).parents("tr");
		if (isUp) {
			var prevTr = $(thisTr).prev();
			if (prevTr) {
				thisTr.insertBefore(prevTr);
			}
		} else {
			var nextTr = $(thisTr).next();
			if (nextTr) {
				thisTr.insertAfter(nextTr);
			}
		}
	}
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:save();"><span></span>确定</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link close" href="javascript:dialog.close();"><span></span>关闭</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<input type="hidden" id="tableId" value="${tableId }"/>
			<table id="fieldTbl" cellpadding="1" cellspacing="1" class="table-grid table-list">
				<thead>
					<tr>
						<th width="5%">序号</th>
						<th width="10%">字段名</th>
						<th width="10%">列名</th>
						<th width="5%">是否显示</th>
						<th width="5%">排序</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(formFieldList)<=0}"><tr><td colspan="5">未获取表单字段，请检查流程是否设置表单。</td></tr></c:when>
						<c:otherwise>
							<c:forEach items="${formFieldList}" var="field" varStatus="index">
								<tr var="fieldTr">
									<td width="20px" id="index" class=""index"">${index.index+1}</td>
									<td width="10%" name="fieldName"  >${field.fieldName}</td>
									<td width="10%"><input type="text" name="fieldDesc" class="inputText" value="${field.fieldDesc}"/></td>
									<td width="5%"><input name="isShow" type="checkbox" value="1" <c:if test="${field.isShow ==1}"> checked="checked" </c:if>></td>
									<td>
										<a class="link moveup" href="javascript:;" title="上移" onclick="moveTr(this,true)"></a>
										<a class="link movedown" href="javascript:;"  title="下移" onclick="moveTr(this,false)"></a>
									</td>
								</tr>
							</c:forEach>
						
						</c:otherwise>
					</c:choose>
					
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>