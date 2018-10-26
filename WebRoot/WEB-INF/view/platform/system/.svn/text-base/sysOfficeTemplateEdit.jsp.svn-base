<%--
	time:2012-05-25 10:16:17
	desc:edit the office模版
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑系统模版</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysOfficeTemplate"></script>	
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/AttachMent.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/FlexUploadDialog.js"></script>
	<script type="text/javascript">
	
		$(function() {
			var filePath=$("textarea[name='filePath']").attr("filePath");
			if(filePath){
				init(filePath);
			}
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${sysOfficeTemplate.id ==null }){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				var v=$("#file").val();
				/* if(v==''){
					$.ligerDialog.warn("请上传模板文件");
				}else{
					$("#sysOfficeTemplateForm").submit();
				} */
				
				$("#sysOfficeTemplateForm").submit();
			});
		});
		
		function init(filePath){
			var paths=filePath.split("\\");
			var path=paths[paths.length-1];
			var aryJson=[];
			var obj={};
			obj.id="";
			obj.name=path;
			aryJson.push(obj);
			
			var divObj=$("div.attachement");
			var inputJson=$("textarea[name='filePath']");
			
			var json=JSON2.stringify(aryJson);		
			var html=getHtml(aryJson);
			divObj.empty();
			divObj.append($(html));
			inputJson.val(json);	
		}
		
		function showResponse(responseText){
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(obj.isSuccess()){
				$.ligerDialog.success(obj.getMessage(),"提示",function(){
						window.location.href="list.ht";
				});
			}else{
				$.ligerDialog.err('出错信息',"保存系统模板失败",obj.getMessage());
			}
		}
		
		function uploadLoad(obj){
			var inputObj=$(obj);
/* 			var fieldName=inputObj.attr("field");
			var parent=inputObj.parent().parent();
			
			var rights="w";
			var divName="div.attachement";
			var inputName="input[name='" +fieldName +"'],textarea[name='" +fieldName +"']"; */
			//获取div对象。
			var divObj=$("div.attachement");
			var inputJson=$("textarea[name='filePath']");
			
		//	var aryJson=AttachMent.getFileJsonArray(divObj);
			var aryJson=[];
			//文件选择器
			FlexUploadDialog({isSingle:true,callback:function (fileIds,fileNames,filePaths,extPaths){
				if(fileIds==undefined || fileIds=="") return ;
				var aryFileId=fileIds.split(",");
				var aryName=fileNames.split(",");
				var aryExtPath=extPaths.split(",");
			
				for(var i=0;i<aryFileId.length;i++){
					var name=aryName[i];
					AttachMent.addJson(aryFileId[i],name,aryJson);
				}
				//获取json
				var json=JSON2.stringify(aryJson);		
				var html=getHtml(aryJson);
				divObj.empty();
				divObj.append($(html));
				inputJson.val(json);				
			}});
		}
		
		function getHtml(aryJson){
			var str="";
			var template="<span class='attachement-span'><span fileId='#fileId#' name='attach' file='#file#' ><input type='hidden' name='fileId' id='fileId' value='#fileId#'><a class='attachment' target='_blank' path='#path#'  title='#title#'>#name#</a></span></span>";

			for(var i=0;i<aryJson.length;i++){
				var obj=aryJson[i];
				var id=obj.id;
				var name=obj.name;
				var path =__ctx +"/platform/system/sysFile/file_" +obj.id+ ".ht";
					
				var file=id +"," + name ;
				var tmp=template.replace("#file#",file).replace("#path#",path).replace("#name#", name).replace("#title#",name).replaceAll("#fileId#", id);
				//附件如果是图片就显示到后面
				str+=tmp;
			}
			return str;
		}

		
		
		
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${sysOfficeTemplate.id !=null }">
			            <span class="tbar-label">编辑系统模版</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加系统模版</span>
			        </c:otherwise>
			    </c:choose>			
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				
				<form id="sysOfficeTemplateForm" method="post" action="saveTemplate.ht">
				
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">主题: </th>
							<td><input type="text" id="subject" name="subject" value="${sysOfficeTemplate.subject}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">模版类型: </th>
							<td>
								<input type="radio"  name="templatetype" value="1" <c:if test="${sysOfficeTemplate.templatetype!=2}">checked="checked"</c:if> />普通模版
								<input type="radio"  name="templatetype" value="2" <c:if test="${sysOfficeTemplate.templatetype==2}">checked="checked"</c:if> />套红模版
							</td>
						</tr>
						<tr>
							<th width="20%">路径: </th>
							<td class="formInput" style="width:80%">
								<div right="w" name="div_attachment_container">
									<div class="attachement"></div>
									<textarea style="display:none;" controltype="attachment" lablename="附件" name="filePath" filePath=" ${sysOfficeTemplate.path}"></textarea>
									<a class="link selectFile" onclick="uploadLoad(this);" validate="{'maxlength':2000}" field="filePath" href="javascript:;">选择</a>
								</div>
							</td>
						</tr>
						<tr>
							<th width="20%">备注: </th>
							<td>
								<textarea rows="4" cols="40"id="memo" name="memo" value="${sysOfficeTemplate.memo}">${sysOfficeTemplate.memo}</textarea>
							</td>
						</tr>
					</table>
					<input type="hidden" id="officeTemplateId" name="id" value="${sysOfficeTemplate.id}" />
				</form>
				
		</div>
</div>
</body>
</html>
