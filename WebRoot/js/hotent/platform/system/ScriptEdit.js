/**
 * 下拉选项模版。
 */
var optiontemplate = '<option value="#value">#text</option>';

$(function(){
	getDialogs();
	$("select[name='methodName']").change(methodChange);	
	$('select[name="paraCt"]').live('change',function(){
		var parentDiv = $(this).closest('div');
		if($(this).val()==0){
			$('#settingSpan',parentDiv).show();
		}
		else{
			$('#settingSpan',parentDiv).hide();
		}
	});
});

//获取自定义对话框
function getDialogs(){
	var url = __ctx + '/platform/form/bpmFormDialog/getAllDialogs.ht';
	$.ajax({
	    type:"get",
	    async:false,
	    url:url,
	    success:function(data){
			if (data) {
				for(var i=0,c;c=data[i++];){
					var opt = $('<option value="'+c.alias+'">'+c.name+'</option>');
					opt.attr("fields",c.resultfield);
					$("select[name='dialog-type']",$("#settingSpan")).append(opt);
				}
			}
	    }
	});
};		
//选择不同的对话框
function dialogChange(obj){
	var dia=$(obj).find("option:selected");
	var v = dia.attr("fields");
	if(v){
		var paramSelector = $(obj).siblings("#dialog-param");
		var opt = paramSelector.find("option:first-child");
		paramSelector.text('');
		//添加   请选择…… option
		paramSelector.append(opt);
		var fields = $.parseJSON(v);
		for(var i=0,f;f=fields[i++];){
			opt = $('<option value="'+f.field+'">'+f.comment+'</option>');
			paramSelector.append(opt);
		}
	}
}

//选择的方法事件
function methodChange(){
	var option = $(this).find("option:selected");

	if(!option)return;
	var	methodInfo = option.data('methodInfo');
	if(!methodInfo)return;
	$("input[name='returnType']").val(methodInfo.returnType);		
	$("input[name='returnType']").trigger('change');		
	var param = constructParamTable(methodInfo);
	$("#paraInfo").empty().append(param);
	
	if($('#typeName').val()=="text"){
		$("input[name='paraName']").each(function(n){
			$(this).blur(function(){
				var me = $(this);
				var paraValue = me.val();
				$("input[name='paraName']").not(this).each(function(m){
				    var other = $(this);
					var value = other.val();
					if('undefined' != typeof (paraValue) && paraValue!=null && paraValue!='' && paraValue == value ){
				    	alert('参数值不能为空！');
				    	return false;
				    }
				});
			});
		});
	}
};
//获取该类的方法
function getMethods(url){
	$.post(url,'',function(r){
		var data = eval("("+r+")");
		if(data.result){
			var methods = data.methods,
				methodSelect = $("select[name='methodName']").empty(),
				methodName = $("#methodName").val();

			for(var i=0,c;c=methods[i++];){
			
				var newOpt = $('<option></option>').val(c.methodName).text(c.methodName);
				if(c.methodName == methodName){
					var paraData = $("textarea[name='argument']").val();
					if(paraData){
						paraData = eval("("+paraData+")");
						c.para = paraData;
					}
					$(newOpt).attr("selected",true);
				}
				$(newOpt).data('methodInfo',c);
				methodSelect.append(newOpt);
			}
			methodSelect.trigger("change");
		}
		else{
			$.ligerDialog.error(data.message,'出错了!');
		}
	});
}

//根据表单ID保存并提交
function save(formId){
	
	//验证
	var frm=$('#'+formId);   
	if(!frm.valid()) return ;
	
	var isReturnFalse = false;
	var settingSpan = $("[id='settingSpan']");
	var setting;
	for(var i=0;i<settingSpan.length;i++){
		setting = settingSpan[i];
		if($(setting).css('display')=='none') continue ;
		var dialog = $(setting).find('#dialog-type');
		var param = $(setting).find('#dialog-param');
		var paraName = $(setting).closest('tr').find('[name="paraName"]').text();
		if(!dialog.val()){
			$.ligerDialog.warn("请选择参数【"+paraName+"】所要绑定的对话框！",'提示');
			isReturnFalse = true;
			break;
		}else if(!param.val()){
			$.ligerDialog.warn("请选择参数【"+paraName+"】对应的对话框返回值！",'提示');
			isReturnFalse = true;
			break;
		}
	};
	if(isReturnFalse) return false;
	var json = getTrJson();
	json = JSON2.stringify(json);
	if(json=='[]'){
		json='';
	}
	$("textarea[name='argument']").val(json);
	$("#"+formId).submit();
};


/**
* 获取参数行的JSON字符口串
*/
function getTrJson(){
	var json = [];
	$("#paraInfo").find("tr").each(function(){
		var me = $(this);
		var paraNameSpan;	
		if($('#typeName').val()=="text"){
			paraNameSpan = $("input[name='paraName']",me);
		}else{
			paraNameSpan = $("span[name='paraName']",me);
		}
		if(!paraNameSpan||paraNameSpan.length==0)return true;
		var paraTypeSpan = $("span[name='paraType']",me),
			paraDescInput = $("input[name='paraDesc']",me),
			paraCtlTypeSelect = $("[name='paraCt']",me),
			job = {};

		if($('#typeName').val()=="text"){
			job.paraName = paraNameSpan.val();
			$("input[name='isRequired']",me).each(function(){
				if($(this).attr("checked")=="checked"){
					job.isRequired = 1;
				}else{
					job.isRequired = 0;
				}
			});
			
		}else{
			job.paraName = paraNameSpan.text();
		}
		job.paraType = paraTypeSpan.text();
		job.paraDesc = paraDescInput.val();
		job.paraCt = paraCtlTypeSelect.val();
		if($('#settingSpan',me).css('display')!='none'){
			var dialog = $("select[name='dialog-type']",me),
				target = $("select[name='dialog-param']",me);
			job.dialog = dialog.val();
			job.target = target.val();
		}
		json.push(job);
	});
	
	return json;
};

function showResponse(r){
	var data = eval("("+r+")");
	if(data.result){
		$.ligerDialog.success(data.message,'操作成功!',function(){
			window.location.href = 'list.ht';
		});
	}
	else{
		$.ligerDialog.warn(data.message,'出错了!');
	}
}

/**
* 创建参数表格
*/
function constructParamTable(params){
	var table = $("#para-txt table").clone();
	var tbody = $("tbody",table).empty();
	for(var i=0;i<params.para.length;i++){
		var p = params.para[i];
		var tr = constructParamTr(p);
		tbody.append(tr);
	}
	return table;
};

/**
* 创建参数表格行
*/
function constructParamTr(p){
	var tr = $("#para-txt table tbody tr").clone();
	if($('#typeName').val()=="text"){
		$("input[name='paraName']",tr).val(p.paraName);
		$("input[name='paraName']",tr).attr("validate","{'required':true}");
		if(p.isRequired==1){
			$("input[name='isRequired']",tr).each(function(){
				$(this).attr("checked","checked");
			});
		}
	}else{
		$("[name='paraName']",tr).text(p.paraName);
	}
	$("[name='paraType']",tr).text(p.paraType);
	$("[name='paraDesc']",tr).val(p.paraDesc);
	$("[name='paraCt']",tr).val(p.paraCt);
	if(p.dialog){
		var settingSpan = $('#settingSpan',tr);
		settingSpan.show();
		$("#dialog-type",settingSpan).find("option[value='"+p.dialog+"']").attr("selected","selected");
		dialogChange($("#dialog-type",settingSpan));
		$("#dialog-param",settingSpan).find("option[value='"+p.target+"']").attr("selected","selected");
	}
	return tr;		
};

/**
* 预览脚本效果
*/
function previewScript(){
	var scriptType = $("#scriptType").val();
	//自定义类型custom\\默认类型
	if(scriptType=='custom'){
		//先保存脚本编辑器内容到scriptComten
		InitMirror.save();
		var scriptComten = $("#scriptComten").val();
		exeCustomScript(scriptComten,"edit");
	}else{
		var classInsName = $("input[name='classInsName']").val();
		var methodName = $("select[name='methodName']").val();
		var json = getTrJson();
		exeScript(classInsName,methodName,json);
	}
};


//执行脚本方法
function exeCustomScript(scriptComten,type){
	if(typeof(scriptComten)==undefined||scriptComten==null||scriptComten==''){
		alert("脚本内容不能为空！");
		return false;
	}	
	var url= __ctx + '/platform/system/aliasScript/toCustomPreview.ht';	
	var conf={ scriptComten:scriptComten,type:type };
	var winArgs="dialogWidth:800px;dialogHeight:600px;help:0;status:0;scroll:1;center:1";
	url=url.getNewUrl();
	
	/*KILLDIALOG*/
	DialogUtil.open({
		height:600,
		width: 800,
		title : '执行脚本方法',
		url: url, 
		isResize: true,
		//自定义参数
		conf: conf,
		sucCall:function(returnValue){
			if(typeof(returnValue)!=undefined&&returnValue!=null&&type=="edit"){
				//编辑进入的方法才有返回值代码
				InitMirror.editor.setCode(returnValue);
			}
		}
	});
	
};


//执行脚本方法
function exeScript(classInsName,methodName,json){
	if(typeof(classInsName)==undefined||classInsName==null||classInsName==''){
		alert("调用类的对象名不能为空！");
		return false;
	}
	if(typeof(methodName)==undefined||methodName==null||methodName==''){
		alert("调用方法名不能为空！");
		return false;
	}
	var scriptStr = classInsName+"."+methodName;
	var url= __ctx + '/platform/system/aliasScript/toPreview.ht';
	var jsonStr = JSON2.stringify(json);
	if(typeof(jsonStr)==undefined||jsonStr==null||jsonStr=='[]'||jsonStr==''){
		jsonStr='';
		scriptStr += "()";
	}else{
		var str = '';
		for ( var i = 0; i < json.length; i++) {
			var obj = json[i];
			str += obj.paraName+","
		}
		str = str.substring(0, str.length-1);
		scriptStr += '('+str+')';
	}
	scriptStr = encodeURIComponent(scriptStr);
	jsonStr = encodeURIComponent(jsonStr);
	url += "?scriptStr="+scriptStr+"&alias="+jsonStr;
	var winArgs='height=500,width=700,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no';
	url=url.getNewUrl();
	window.open (url,"别名脚本预览",winArgs);
};
