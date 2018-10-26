<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title>提醒消息设置</title>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=message"></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor_remind.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/TemplateDialog.js"></script>
<script type="text/javascript">
	var _conditionId = '${conditionId}',
		_actDefId = '${actDefId}',
		_messageList = '${messageList}',
		_messageSetType = '${messageSetType}';
	
	$(function() {
		$("input.send_type").change(function(){
			var name = $(this).attr("name"),
				state = $(this).attr("checked");
			if(state)
				$("#"+name+"-tr").show();
			else
				$("#"+name+"-tr").hide();
		});
		initMessage();
	});
	//初始化提醒消息设置
	function initMessage(){
		if(_messageList.trim()==""){
			callCkeditor();
			return;
		}
		var messageArr = eval("("+_messageList+")");
		for(var i=0,c;c=messageArr[i++];){
			var name = getMessageName(c.messageType),
				tr = $("#"+name+"-tr");
			//解码
			var content = c.content.jsonUnescape();
			$("input=[name='"+name+"']").attr("checked","checked").attr("msgid",c.messageId).trigger("change");
			$("input[name='subject']",tr).val(c.subject),
			$("textarea.message-content",tr).val(content);
		}
		callCkeditor();
	};
	function callCkeditor(){
		ckeditor('mailContent');
		ckeditor('innerContent');
	};
	//选择模板
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

	function getMessageType(s){
		return s=='mail'?1:s=='sms'?2:3;
	};

	function getMessageName(i){
		return i==1?'mail':i==2?'sms':'inner';
	};

	function validRequired(){
		var r = true;
		$("input.inputText").each(function(){
			var v = $(this).is(":hidden"),
				val = $(this).val();

			if(!v&&!val)
				r =false;
		});
		return r;
	};

	function save(){
		if(!validRequired()){
			$.ligerDialog.warn('请填写邮件或站内信的标题!','提示');
			return;
		}
		
		//从编辑器中获取数据 放到textarea中
		$("textarea.message-content").each(function(){
			if(!CKEDITOR.instances[$(this).attr('name')])return true;
			var data = CKEDITOR.instances[$(this).attr('name')].getData();
			$(this).val(data);
		});
		var json = [];

		if(!_actDefId){
			$.ligerDialog.warn('未获取流程定义的相关信息!','提示');
			return;
		}		
		
		$("input.send_type").each(function(){
			var me = $(this),
				state = me.attr("checked");
			if(!state)return true;			
			var name = me.attr("name"),
				tr = $("#"+name+"-tr"),
				val = me.attr("msgid"),
				jObject = {};

			if(!val)val = 0;
			var subject = $("input[name='subject']",tr).val(),
				content = $("textarea.message-content",tr).val();
			//转码
			content = content.jsonEscape();
			jObject.messageId = val;
			jObject.subject = subject;
			jObject.content = content;
			jObject.messageType = getMessageType(name);

			json.push(jObject);
		});

		json = JSON2.stringify(json);
		var url = __ctx + '/platform/system/message/saveCopyMessage.ht';

		$.post(url,{data:json,actDefId:_actDefId,conditionId:_conditionId,messageSetType:_messageSetType},function(d){
			var r = eval("("+d+")");

			if(r.result){
				$.ligerDialog.success(r.message,'提示',function(){
					window.opener.location.reload();
					window.close();
				});
			}
			else
				$.ligerDialog.error(r.message,'提示');
		});
	};
</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
            <span class="tbar-label">提醒消息设置</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" onclick="save()" href="javascript:;">保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="javascript:;" onclick="window.close()">关闭</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%" nowrap="nowarp">消息类型: </th>
				<td>
					<label><input class="send_type" name="inner" type="checkbox" />站内消息</label>
					<label><input class="send_type" name="mail" type="checkbox" />邮件</label>
					<label><input class="send_type" name="sms" type="checkbox" />短信</label>
				</td>
			</tr>
			<tr id="inner-tr" class="hidden">
				<th width="20%" nowrap="nowarp">站内消息: </th>
				<td>
					<input type="text" name="subject" value="${sysTemplate.title}" class="inputText" style="width:325px !important;margin:8px 0 18px 0;"/>
					<div>
						<a href="javascript:;"  class="link var" title="选择模板" onclick="slectTemplate('innerContent',false)">选择模板</a>
					</div>
					<textarea id="innerContent" class="message-content" name="innerContent"  rows="5" cols="50">${sysTemplate.innerContent}</textarea>
				</td>
			</tr>
			<tr id="mail-tr" class="hidden">
				<th width="20%" nowrap="nowarp">邮件内容: </th>
				<td>
					<input type="text" name="subject" value="${sysTemplate.title}" class="inputText" style="width:325px !important;margin:8px 0 18px 0;"/>
					<div>
						<a href="javascript:;"  class="link var" title="选择模板" onclick="slectTemplate('mailContent',false)">选择模板</a>
					</div>
					<textarea id="mailContent" class="message-content" name="mailContent"  rows="5" cols="50">${sysTemplate.mailContent}</textarea>
				</td>
			</tr>

			<tr id="sms-tr" class="hidden">
				<th width="20%">手机短信: </th>
				<td>
					<div>
						<a href="javascript:;"  class="link var" title="选择模板" onclick="slectTemplate('smsContent',true)">选择模板</a>
					</div>
					<textarea id="smsContent" class="message-content" rows="5" cols="50">${sysTemplate.smsContent}</textarea>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>
