<%--
	time:2012-08-29 11:26:00
	desc:edit the 电子印章
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<%@include file="/js/msg.jsp"%>
<title>office模板权限设置</title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript">

		$(function(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			  //初始化选择显示区域
            initSelect($('#rightType').attr('value'));
            $("a.save").click(function() {
				var url="saveRight.ht";
				var form = $("#sealForm");
				form.ajaxForm(options);
				form.submit();
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm( obj.getMessage()+',继续操作吗?', '提示', function(rtn) {
					if(rtn){
					}else{
						window.returnValue=true;
						this.close();
					}
					if(!rtn){
						window.returnValue=true;
						location.href='list.ht';
					}
					else{
						location.reload();
					}
				});
			} else {
				$.ligerDialog.err('提示','office模板权限设置失败',obj.getMessage());
			}
		}
		
		//选择器
		function selectRightDlg(){
			var flag = $('#rightType').attr('value');
			var ids = $('#rightIds').attr('value');
			var names = $('#rightNames').attr('value');
			if(flag=="user"){
		        UserDialog({
		        	selectUserIds:ids,
		        	selectUserNames:names,
			        callback:function(userIds,userNames){
			        	$('#rightNames').attr('value',userNames);
			        	$('#rightIds').attr('value',userIds);
			        	$('#rightNameArea').attr('value',userNames);
			        }
		        });
			}else if(flag=="role"){
				RoleDialog({
					ids:ids,
		        	names:names,
			        callback:function(userIds,userNames){
			        	$('#rightNames').attr('value',userNames);
			        	$('#rightIds').attr('value',userIds);
			        	$('#rightNameArea').attr('value',userNames);
			        }
		        });
			}else if(flag=="org"){    //flag == org
		        OrgDialog({
		        	ids:ids,
		        	names:names,
			        callback:function(userIds,userNames){
			        	$('#rightNames').attr('value',userNames);
			        	$('#rightIds').attr('value',userIds);
			        	$('#rightNameArea').attr('value',userNames);
			        }
		        });
			}
		}
		
		// 判断选择组织或用户
		function changeRight(obj){
			var newType = obj.value;
			var oldType = $('#rightTypeHidden').attr('value');              	        	
			if(newType==oldType){
				var userIds = $('#rightIdsHidden').attr('value');
				var userNames =	$('#rightNamesHidden').attr('value');
				$('#rightNames').attr('value',userNames);
		    	$('#rightIds').attr('value',userIds);
		    	$('#rightNameArea').attr('value',userNames);
			}else{
		   		$('#rightIds').attr('value','');
		   		$('#rightNameArea').attr('value','');
		   		$('#rightNames').attr('value','');
			}
			
			initSelect(newType);
		}
		
		// 判断选择显示初始化
		function initSelect(newType){
			if(newType=='all'){
				$('#rightNameArea').show();   //选择显示区域
				$('#selectRightDlg').hide();   //选择按钮隐藏
				$('#rightNameArea').attr('value','所有');
				$('#rightNames').attr('value','所有');
			}else if(newType=='not'){
				$('#selectRightDlg').hide();   //选择按钮隐藏
				$('#rightNameArea').hide();   //选择显示区域隐藏
			}else{
				$('#rightNameArea').show();   //选择显示区域
				$('#selectRightDlg').show();   //选择按钮显示
			}
		}	   
        
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">office模板权限设置</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sealForm" method="post" action="saveRight.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<input type="hidden" name="templateId" id="templateId" value="${templateId}"/>
					<tr>
						<th width="20%">主题:</th>
						<td>${templateSubject}</td>
					</tr>
					<tr>
						<th width="20%">分配类型:</th>
						<td><select id="rightType" name="rightType"
							style="width: 60px;" onchange="changeRight(this)">
								<c:forEach var="typeItem" items="${typeList}">
									<c:choose>
										<c:when test="${typeItem.id==sealRightMap.rightType}">
											<option value="${typeItem.id}" selected="selected">${typeItem.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${typeItem.id}">${typeItem.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th width="20%">分配对象:</th>
						<td>
							<ul>
								<li style="float: left;">
								<textarea rows="5" cols="150" id="rightNameArea" name="rightNameArea" readonly="readonly"
										class="inputText">${sealRightMap.rightNames}</textarea> 
								<input type="hidden" id="rightNames" name="rightNames" value="${sealRightMap.rightNames}" /> 
								<input type="hidden" id="rightIds" name="rightIds" value="${sealRightMap.rightIds}" /></li>
								<!-- 原始的数据 -->
								<input type="hidden" id="rightTypeHidden" name="rightTypeHidden"
									value="${sealRightMap.rightType}" />
								<input type="hidden" id="rightNamesHidden"
									name="rightNamesHidden" value="${sealRightMap.rightNames}" />
								<input type="hidden" id="rightIdsHidden" name="rightIdsHidden"
									value="${sealRightMap.rightIds}" />
								</li>

								<li style="float: left;">&nbsp;</li>
								<li style="float: left;"><a id='selectRightDlg' href='#'
									class='button' onclick="selectRightDlg();"><span>...</span></a></li>
							</ul>
						</td>
					</tr>
				</table>

			</form>
		</div>
	</div>
</body>
</html>
