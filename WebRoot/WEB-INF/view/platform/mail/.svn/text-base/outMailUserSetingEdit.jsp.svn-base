<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 邮箱配置</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=outMailUserSeting"></script>
	<script type="text/javascript">
		$(function() {
			if(${outMailUserSeting.mailType=='imap'}){
				$('.pop').css('display','none');
			}else{
				$('.imap').css('display','none');
			}
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${outMailUserSeting.id ==null}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				$('#outMailUserSetingForm').attr('action','save.ht');
				var type=$("#recieveMailType").val();
				if(type=='pop3'){
					$('#imapHost').val('');
					$('#imapPort').val('');
				}else{
					$('#popHost').val('');
					$('#popPort').val('');
				}
				$('#mailType').val($('#recieveMailType').val());
				$('#outMailUserSetingForm').submit(); 
			});
			
			$("#mailAddress").blur(function(){
				var address=$("#mailAddress").val();
				if(address!=''){
					address=address.trim();
					var type=$("#recieveMailType").val();
					var s=address.substring(address.indexOf('@')+1,address.length+1).trim();
					$("#smtpHost").val('smtp.'+s);
					$("#popHost").val('pop'+'.'+s);
					if(type=='pop3'){
						if(s=='gmail.com'||s=='msn.com'||s=='live.cn'||s=='live.com'||s=='hotmail.com'){
							$('#SSL').val('0') ;
							$('#SSLCheckBox').trigger('click') ;
							$('#SSLCheckBox').attr('checked','checked');
						}else{
							$("#popPort").val('110');
							$("#smtpPort").val('25');
						}
					}else{
						if(s=='gmail.com'||s=='msn.com'||s=='live.cn'||s=='live.com'||s=='hotmail.com'){
							$('#SSL').val('0') ;
							$('#SSLCheckBox').trigger('click') ;
							$('#SSLCheckBox').attr('checked','checked');
						}else{
							$("#imapPort").val('143');
							$("#smtpPort").val('25');
						}
						$("#imapHost").val('imap'+'.'+s);
					}
				}
			});
			
			$("#testConnect").click(function(){
				var mailAddress=$("#mailAddress").val();
				var password=$("#mailPass").val();
				if(mailAddress==''&&password==''){
		    		alert('请先填写邮箱地址!');
		    		$("#mailAddress").focus();
		    		return false;
		    	}
				if(!"${outMailUserSeting.id}" && password==''){
		    		alert('请先填写邮箱密码!');
		    		$("#mailPass").focus();
		    		return false;
		    	}
				$.ligerDialog.waitting('正在测试连接,请耐心等候... ');
				$('#outMailUserSetingForm').attr('action','test.ht?isEdit=1');
				$('#outMailUserSetingForm').ajaxSubmit(function(data){
					var obj=new com.hotent.form.ResultMessage(data);
					$.ligerDialog.closeWaitting();
					if(obj.isSuccess()){//成功
						$.ligerDialog.success("连接成功！");
				    }else{//失败
				    	$.ligerDialog.err('出错信息',"测试连接失败",obj.getMessage());
				    	
				    }
	            });
			});
			
			$("#recieveMailType").change(function(){
				var type=$("#recieveMailType").val();
				if(type=='pop3'){
					$("#imapH,#imapP").hide();
					$("#popH,#popP").show();
				}else{
					$("#imapP,#imapH").show();
					$("#popH,#popP").hide();
				}
			});
			$('td').delegate('#SSLCheckBox','click',function(){
				var isSSL = $('#SSL').val() ;
				if(isSSL=='0'){
					$('#SSL').val('1') ;
					$("#imapPort").val('993');
					$("#smtpPort").val('465');
					$("#popPort").val('995');
				}else{
					$('#SSL').val('0') ;
					$("#imapPort").val('143');
					$("#popPort").val('110');
					$("#smtpPort").val('25');
				}
			});
			$('td').delegate(':checkbox:not(#SSLCheckBox)','click',function(){
				var hideSibling = $(this).siblings(':hidden');
				var value = hideSibling.val() ;
				if(value=='0'){
					hideSibling.val('1');
				}else {
					hideSibling.val('0');
				}
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${outMailUserSeting.id !=null}">
			            <span class="tbar-label">编辑邮箱</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加邮箱</span>
			        </c:otherwise>
			    </c:choose>
			            
			   
				
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save " id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link test" id="testConnect" ><span></span>测试连接</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="outMailUserSetingForm" method="post" action="save.ht">
					
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">账号名称:  <span class="required">*</span></th>
							<td><input type="text" id="userName" name="userName" value="${outMailUserSeting.userName}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">邮箱类型:  <span class="required">*</span></th>
							<td><select name="mailType" id="recieveMailType">
									<option value="pop3" <c:if test="${outMailUserSeting.mailType=='pop3'}">selected="selected"</c:if> >pop3类型</option>
									<option value="imap" <c:if test="${outMailUserSeting.mailType=='imap' }">selected="selected"</c:if>> imap类型</option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%">邮箱地址:  <span id="required" class="">*</span></th>
							<td><input type="text" id="mailAddress" name="mailAddress" value="${outMailUserSeting.mailAddress}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">邮箱密码:  <span class="required">*</span></th>
							<td><input type="password" id="mailPass" name="mailPass" value="${outMailUserSeting.mailPass}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">smtp主机:  <span class="required">*</span></th>
							<td><input type="text" id="smtpHost" name="smtpHost" value="${outMailUserSeting.smtpHost}"  class="inputText"/></td>
						</tr>
						<tr>
				   			<th width="20%">smtp端口:  <span class="required">*</span></th>
							<td><input type="text" id="smtpPort" name="smtpPort" value="${outMailUserSeting.smtpPort}"  class="inputText"/></td>
						</tr>
						<tr id="popH"  class="pop">
							<th width="20%">pop主机:  <span class="required">*</span></th>
							<td><input type="text" id="popHost" name="popHost" value="${outMailUserSeting.popHost}"  class="inputText"/></td>
						</tr>
						<tr id="popP"  class="pop">
							<th width="20%">pop端口:  <span class="required">*</span></th>
							<td><input type="text" id="popPort" name="popPort" value="${outMailUserSeting.popPort}"  class="inputText"/></td>
						</tr>
						<tr id="imapH" class="imap">
							<th width="20%">imap主机:  <span class="required">*</span></th>
							<td><input type="text" id="imapHost" name="imapHost" value="${outMailUserSeting.imapHost}"  class="inputText"/></td>
						</tr>
						<tr id="imapP" class="imap">
							<th width="20%">imap端口:  <span class="required">*</span></th>
							<td><input type="text" id="imapPort" name="imapPort" value="${outMailUserSeting.imapPort}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">使用SSL &nbsp; &nbsp;</th>
							<td>
								<input type="checkbox" id="SSLCheckBox" <c:if test="${outMailUserSeting.SSL eq 1}">checked="checked"</c:if> />
								<input type="hidden" name="SSL" value='<c:out value="${outMailUserSeting.SSL}" default="0"></c:out>' id="SSL"/>
							</td>
						</tr>
						<tr>
							<th width="20%">使用身份认证</th>
							<td>
								<input type="checkbox" id="validateCheckBox" <c:if test="${outMailUserSeting.validate eq 1}">checked="checked"</c:if> />
								<input type="hidden" name="validate" value='<c:out value="${outMailUserSeting.validate}" default="1"></c:out>' id="validate"/>
							</td>
						</tr>
						<tr>
							<th width="20%">是否下载附件</th>
							<td>
								<input type="checkbox" id="isHandleAttachCheckBox" <c:if test="${outMailUserSeting.isHandleAttach eq 1}">checked="checked"</c:if> />
								<input type="hidden" name="isHandleAttach" value='<c:out value="${outMailUserSeting.isHandleAttach}" default="1"></c:out>' id="isHandleAttach"/>
							</td>
						</tr>
						<tr>
							<th width="20%">是否删除远程邮件</th>
							<td>
								<input type="checkbox" id="isDeleteRemoteCheckBox" <c:if test="${outMailUserSeting.isDeleteRemote eq 1}">checked="checked"</c:if> />
								<input type="hidden" name="isDeleteRemote" value='<c:out value="${outMailUserSeting.isDeleteRemote}" default="0"></c:out>' id="isDeleteRemote"/>
							</td>
						</tr>
						
					</table>
					
					<input type="hidden" name="id" value="${outMailUserSeting.id}" />
				</form>
		</div>
</div>
</body>
</html>
