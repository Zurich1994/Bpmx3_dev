<%--
	time:2011-12-31 15:48:59
	desc:edit the 流程节点消息
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 流程节点消息</title>
	<%@include file="/commons/include/form.jsp" %>
	<link href="${ctx}/js/jquery/plugins/token-input-facebook.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.tokeninput.js"></script>
    <script type="text/javascript"  src="${ctx }/js/hotent/platform/system/SysDialog.js"></script>
    <script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor_sysTemp.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/TemplateDialog.js"></script>
	<script type="text/javascript">
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
		 var obj;
		 function showRequest(formData, jqForm, options) {
				return true;
		 }
		function fun()  {
			 document.getElementById("innerMessage").style.display="none";
			 document.getElementById("telephoneMessage").style.display="none";
			 $("a.save").click(save);
			 $(".token-input").each(function(){
					var _this=$(this);
					//进jquery.tokenInput.js执行转码  方法： $.fn.tokenInput = function (method)   
					_this.tokenInput([],{theme:"facebook",onDelete:deleteBpmUserCondition});

					//初始化原有数据
					var recs = _this.val();
					if(!recs){
						return;
					}
					var conds = $.parseJSON(recs);
					var tokenData=getTokensFromConditions(conds);
					$(tokenData).each(function(){
						_this.tokenInput("add",this);
					});				
			 });
			 mailEditor = ckeditor('template_mail');
			 innerEditor = ckeditor('template_inner');
			 handFlowVars();
		}
		
      //显示 条件栏 
		function handFlowVars(){
			$("select[name='selFlowVar']").change(function(){	
				var val=$(this).val();
				var thisEditor=$(this).closest('tr').find('textarea');
				var editorName=$(thisEditor).attr('name');
				var oEditor;
				if(editorName.indexOf('mail')!=-1){
					oEditor = CKEDITOR.instances.template_mail;
				}else{
					oEditor = CKEDITOR.instances.template_inner;
				}
				var text=$(this).find("option:selected").text();
				
				if(val.length==0) return;
				if(text=="发起人(长整型)")
					text=text.replace("(长整型)","");			
				var inStr="{"+text+":"+val+"}";
				// Check the active editing mode.
				if ( oEditor.mode == 'wysiwyg' ){
					oEditor.insertText( inStr );
				}else
					alert( '请把编辑器设置为编辑模式' );
			});
		}
		
		function InsertText(val){
			// Get the editor instance that we want to interact with.
			var oEditor = CKEDITOR.instances.taskNameRule;
			
		}

		function getTokensFromConditions(conds){
			var tokenData=[];
			for(var i=0;i<conds.length;i++){
				var id = conds[i].id;
				var name = "批次号["+conds[i].groupNo+"] - "+conds[i].conditionShow;
				tokenData.push({id:id,name:name});
			}
			return tokenData;
		};
		
	     function save(){
	         //alert("保存");
    		 var valRes=validata();
    		 if(!valRes) return;
    		 var rtn=$("#bpmNodeMessageForm").valid();
    		 if(!rtn) return;
    		 var url=__ctx+ "/platform/bpm/bpmNodeMessage/save.ht";
	  		 $('#template_mail').val(mailEditor.getData());
			 $('#template_inner').val(innerEditor.getData());
    		 var para=$('#bpmNodeMessageForm').serialize();
    		 $.post(url,para,showResult);
	     };
	     function validata(){
	       // alert("保存!!!!");
		     var isFlag = true;
		     // alert("保存0!!!!");
	    	 $(".token-input").each(function(){
	    	//  alert("保存0.5!!!!");
		    	 var _this = $(this);
		    	//  alert("进入tokeninput函数"); 
		    	 var datas = _this.tokenInput("get");
		    	////  alert("保存0.56!!!!");
		    	 if(!datas||datas.length>0){
		    		 isFlag=false;
		    		//  alert("保存0.6!!!!");
		    	 }
	    	 }
	    	 );
	    	 if(isFlag){
	    	 // alert("保存.1!!!!");
	    		 $.ligerDialog.warn('至少需要填写一种消息发送方式的参数','提示信息');
	    		 return false;
	    	 }
	    	 var mailDatas = $("#receiver_mail").tokenInput("get");
	    	 if(mailDatas && mailDatas.length>0){
	    	  //alert("保存2!!!!");
	    		 if($("#subject_mail").val()==""){
	    		//  alert("保存3!!!!");
    				 $.ligerDialog.warn('请输入邮件的主题','提示信息');
    				 return false;
       			}
	    	 }else{
	    		 if($("#subject_mail").val()!=""){
	    		  //alert("保存3!!!!");
	    			 $.ligerDialog.warn('请设置邮件接收人','提示信息');
    				 return false;
	    		 }
	    	 }


	    	 var innerDatas = $("#receiver_inner").tokenInput("get");
	    	 if(innerDatas && innerDatas.length>0){
	    		 if($("#subject_inner").val()==""){
	    		// alert("保存4!!!!");
	    			 $.ligerDialog.warn('请输入站内信息的主题','提示信息');
    				 return false;
    			}
	    	 }else{
	    		 if($("#subject_inner").val()!=""){
	    		// alert("保存5!!!!");
	    			 $.ligerDialog.warn('请设置站内信息接收人','提示信息');
    				 return false;
	    		 }
	    	 }
	    	 
	    	 return true;
	     }
	     function showResult(responseText)
		{			
			var obj=new com.hotent.form.ResultMessage(responseText);
			
			if(!obj.isSuccess()){
				$.ligerDialog.error( obj.getMessage(),"出错了");
				return;
			}else{
				$.ligerDialog.success(obj.getMessage(),'提示信息',function(rtn){
					if(rtn) dialog.close();						
				});
			}
		}


		//设置消息接收人
		function receiverSetting(type){
		//alert("aaaaaa"+type);
			var hw = $.getWindowRect();
			var dialogWidth = hw.width*9/10;
			var dialogHeight = hw.height*9/10;
			var actDefId=$("#actDefId").val();
			var nodeId = $("#nodeId").val();
			var url = __ctx+"/platform/bpm/bpmNodeMessage/receiverSetting.ht?actDefId="+actDefId+"&nodeId="+nodeId+"&type="+type;
		 	var winArgs="dialogWidth="+dialogWidth+"px;dialogHeight="+dialogHeight+"px;help:0;status:0;scroll:1;center:1;resizable:1";
			url=url.getNewUrl();
			
		 	//var rtn = window.showModalDialog(url,"",winArgs);
		 	//window.location.reload();
		 	//reloadToken(type);
		 	
		 	/*KILLDIALOG*/
		 	DialogUtil.open({
		 		height:dialogHeight,
		 		width: dialogWidth,
		 		title : '消息接收人',
		 		url: url, 
		 		isResize: true,
		 		//自定义参数
		 		sucCall:function(rtn){
				 	reloadToken(type);
		 		}
		 	});
		};

		function reloadToken(type){
			var actDefId=$("#actDefId").val();
			var nodeId = $("#nodeId").val();
			var url = __ctx+"/platform/bpm/bpmNodeMessage/getReceiverUserCondition.ht";
		 	var param = {
				 	actDefId:actDefId,
				 	nodeId:nodeId,
				 	receiverType:type
		 	}
		 	var tokenContainer = $(".token-input[rtype="+type+"]");
		 	$.post(url,param,function(data){
			 	if(!data.status){
					var tokenData=getTokensFromConditions(data.conditions);
					tokenContainer.tokenInput("clearOnly");
					$(tokenData).each(function(){
						tokenContainer.tokenInput("add",this);
					});	
			 	}else{
				 	$.ligerDialog.error("重新加载出错!","出错");
			 	}
		 	});
		};

		function deleteBpmUserCondition(data){
			var tokenContainer = $(this);
			var type = tokenContainer.attr("rtype");
			var id=data.id;
			var url =__ctx + '/platform/bpm/bpmUserCondition/delByAjax.ht';
			$.post(url,{id:id},function(t){
				var resultData=eval('('+t+')');
				if(!resultData.result){
					$.ligerDialog.error("删除出错!","出错提示");
					reloadToken(type);
				}
			});
		};
		
		/**
		* Select Template
		*/
		function slectTemplate(txtId,isText){
			var objcondExpCode=document.getElementById(txtId);
		    TemplateDialog({isText:isText,callback:function(content){
		    	if(isText)
					jQuery.insertText(objcondExpCode,content);
		    	else{		    		
		    		CKEDITOR.instances[txtId].setData(content);
		    	}				
			}});
		};		
		
	/**	$(function(){
	*$("a.add").click(function(){
	*	var url=__ctx + "/platform/bpm/bpmNodeButton/editnew.ht?&defId=${defId}&nodeId=${nodeId}&buttonFlag=${buttonFlag}";	
	*	$.gotoDialogPage(url);
	*});
	*$("a.save").unbind("click").click(save);
	*$("a.link.init").unbind("click");
	*init();
});**/
	function changePattern(){
		var patternVal = $("#messPatt").find("option:selected").text();
		//alert(patternVal);
		if(patternVal == "短信"){
			document.getElementById("mailMessage").style.display="none";
			document.getElementById("telephoneMessage").style.display="";
		}
		if(patternVal == "邮件"){
			document.getElementById("telephoneMessage").style.display="none";
			document.getElementById("mailMessage").style.display="";
		}
	}	
		
	</script>
</head>
<body onload="fun()">

		
			<div class="tbar-title">
				<span class="tbar-label">消息参数设置</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" onclick="save()"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" onclick="window.close()"><span></span>关闭</a></div>
					
					<!-- 控制服务类型select by yuda -->
					<div class="messagePattern" >
						&nbsp;
						<select id="messPatt" style="height:25px" onchange="changePattern()">
							<option num="1" value="邮件">邮件</option>
							<option num="2" value="邮件">短信</option>
						</select>
					</div>
				</div>	
			</div>
		</div>
		<div class="panel-body">
		<form id="bpmNodeMessageForm" method="post" action="save.ht">
			<div class="panel-detail">
			        
					<div id="mailMessage" class="foldBox">
					<div class="title" >
					        邮件信息
					   </div>
					 <div class="content" >
					 
					 <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">发送: </th>
							<td><input type="checkbox" id="sendMail" name="sendMail"  value="1" <c:if test="${mailMessage.isSend==1}">checked="checked"</c:if> /></td>
						</tr>
						<tr>
							<th width="20%">邮件主题: </th>
							<td><input type="text" id="subject_mail" name="subject_mail" value="${mailMessage.subject}"  class="inputText" style="width:325px !important"/></td>
						</tr>
						<tr>
							<th width="20%">邮件接收人: </th>
							<td valign="top">
							<textarea rtype="3" id="receiver_mail" class="token-input" name="receiver_mail"  rows="2" readonly="readonly" style="width:328px !important">${receiverMailCondJsons}</textarea>
					        <a href="javascript:receiverSetting(3);" class="link edit">设置</a>
							</td>
						</tr>
						<tr>
							<th width="20%">普通抄送: </th>
							<td>
							<textarea rtype="4" id="copyTo_mail" class="token-input" name="copyTo_mail"  rows="3" readonly="readonly" style="width:328px !important">${copyToMailCondJsons}</textarea>
							<a href="javascript:receiverSetting(4);" class="link edit">设置</a>
							</td>
						</tr>
						<tr>
							<th width="20%">秘密抄送: </th>
							<td>
							<textarea rtype="5" id="bcc_mail" class="token-input" name="bcc_mail"  rows="2" readonly="readonly" style="width:328px !important">${bccMailCondJsons}</textarea>
							<a href="javascript:receiverSetting(5);"  class="link edit">设置</a>
							</td>
						</tr>					
						<tr>
							<th width="20%">邮件模版: </th>
							<td>
								<div>	
								表单变量:<f:flowVar defId="${defId}" controlName="selFlowVar"></f:flowVar>
								<a href="javascript:slectTemplate('template_mail',false);"  class="link var" title="选择模板内容" >选择模板内容</a>
								</div>
								<textarea id="template_mail" name="template_mail">${fn:escapeXml(mailMessage.template)}</textarea>
						    </td>
						</tr>
						
						
					</table>
					 </div>
			        </div>				         	         
		          <div id="innerMessage" class="foldBox">
				  	<div class="title" >
						内部消息
					  </div>
					<div class="content" >
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">发送: </th>
							<td><input type="checkbox" id="sendInner" name="sendInner"  value="1" <c:if test="${innerMessage.isSend==1}">checked="checked"</c:if>/></td>
						</tr>
						<tr>
							<th width="20%">消息主题: </th>
							<td><input type="text" id="subject_inner" name="subject_inner" value="${innerMessage.subject}"  class="inputText" style="width:324px !important"/></td>
						</tr>
						<tr>
							<th width="20%">消息接收人: </th>
							<td valign="top">
							<textarea rtype="6" id="receiver_inner" class="token-input" name="receiver_inner"  rows="3" readonly="readonly" style="width:328px !important">${receiverInnerCondJsons}</textarea>
							<a href="javascript:receiverSetting(6);" class="link edit" >设置</a>
							</td>
						</tr>									
						<tr>
							<th width="20%">消息模版: </th>
							<td>
								<div>	
								表单变量:<f:flowVar defId="${defId}" controlName="selFlowVar"></f:flowVar>
								<a href="javascript:slectTemplate('template_inner',false);"  class="link var" title="选择模板内容" >选择模板内容</a>
					         	</div>
					         	<textarea id="template_inner" name="template_inner">${fn:escapeXml(innerMessage.template)}</textarea>
						    </td>
						</tr>
					</table>	
					</div>
					</div>
					<div id="telephoneMessage" class="foldBox">
					<div class="title" >
					       手机短信
					   </div>
					 <div class="content" >
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">						
						<tr>
							<th width="20%">发送: </th>
							<td><input type="checkbox" id="sendMobile" name="sendMobile"  value="1" <c:if test="${smsMessage.isSend==1}">checked="checked"</c:if> /></td>
						</tr>
						<tr>
							<th width="20%">短信接收人: </th>
							<td valign="top">
							<textarea  rtype="7" id="receiver_mobile" class="token-input" name="receiver_mobile" rows="3" readonly="readonly" style="width:328px !important">${receiverMobileCondJsons}</textarea>
							<a href="javascript:receiverSetting(7);" class="link edit">设置</a>
							</td>
						</tr>								
						<tr>
							<th width="20%">短信模版: </th>
							<td>
							<div>
							<a href="javascript:slectTemplate('template_mobile',true);"  class="link var" title="选择模板内容" >选择模板内容</a>	
					         </div>
					         <textarea rows="5" cols="30" id="template_mobile" name="template_mobile">${fn:escapeXml(smsMessage.template)}</textarea>	
						    </td>
						</tr>
					</table>
					</div>
					</div>
					<input type="hidden" id="id" name="id" value="${id}"  class="inputText"/>				
					<input type="hidden" id="actDefId" name="actDefId" value="${actDefId}"  class="inputText"/>
					<input type="hidden" id="nodeId" name="nodeId" value="${nodeId}"  class="inputText"/>
					<input type="hidden" id="mailMessageId" name="mailMessageId" value="${mailMessage.id}"  class="inputText"/>
					<input type="hidden" id="innerMessageId" name="innerMessageId" value="${innerMessage.id}"  class="inputText"/>
					<input type="hidden" id="smsMessageId" name="smsMessageId" value="${smsMessage.id}"  class="inputText"/>
	            	</div>																	
		 </form>
	    </div>

</body>
</html>
