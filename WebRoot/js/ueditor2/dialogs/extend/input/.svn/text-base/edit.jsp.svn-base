<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<link rel="stylesheet" type="text/css" href="../edit.css">
<link rel="stylesheet" type="text/css" href="${ctx}/styles/default/css/form.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/tree/zTreeStyle.css"/>
<script type="text/javascript" src="${ctx}/js/util/loadjscss.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/conf/orgUserConf.js"></script>

<script type="text/javascript">	
	var oldElement,valid;
	$(function() {
		$(".button-td").bind("mouseenter mouseleave",function(){
			$(this).toggleClass("button-td-hover");
		});
		JsLoader.LoadCount=1;
		//获取所有数据字典列表
		JsLoader.Load(__ctx + "/js/lg/plugins/htCatCombo.js","javascript1");
		if (editor.curInputHtml) {			
			var content=editor.curInputHtml.replace(/#validrule#/g,function(){
				//alert("content:"+content);
				//getAllRules();
				return '正在获取验证规则...';
				}).replace(/#serialnum#/g,function(){					
					return '正在获取流水号...';
					});
			//alert("1");
			//alert("content:"+content);
			$("#inputPanel").html(content);
		}
		//alert("2");
		valid = $("#inputPanel").form();
		
		init();
		
		handOrgUser();
	});
	
	function handOrgUser(){
		 var selectObj = $("#fromType");
		 for(var i=0 ; i<orgUserConfJson.length;i++){
			 var json = orgUserConfJson[i];
			 if(json){
			 var opt = $("<option>").val(json.value).text(json.title);
			 opt.attr("type",json.type);
			 selectObj.append(opt);
			}
		}
	}
	
	function initOptionArray(json) {
		var tbody = $('#option-table>tbody');
		if (typeof(json)!=undefined && json!=null && json != '' && json.length>0){  //有数据时
			for ( var i = 0, c; c = json[i++];) {
				var newTr = addColumn(c);
				tbody.append(newTr);
			}
		}else{
			var data ={key:" ",value:"--请选择--"};  //key 为一个空格
			var newTr = addColumn(data);
			tbody.append(newTr);
		}		
		/* if (!json)return;
		var tbody = $('#option-table>tbody');
		for ( var i = 0, c; c = json[i++];) {
			var newTr = addColumn(c);
			tbody.append(newTr);
		} */
	};
	
	function getOptionData(){
		var optionAry = [];
		$("tr.editable-tr",$("#option-table")).each(function(){
			var	me = $(this),
				optionKey = $("input[name='optionKey']",me).val(),
				optionValue = $("input[name='optionValue']",me).val();
			optionAry.push({key:optionKey,value:optionValue});
		});
		return optionAry;
	};
	
	/**
	 * 终止事件冒泡
	 * @param  {[type]} e [description]
	 * @return {[type]}   [description]
	 */
	function stopBubble(e) {
	    if (e && e.stopPropagation) e.stopPropagation();
	    else window.event.cancelBubble = true;
	};

	/**
	 * 添加选项z
	 */
	function addColumn(data){
	  var hiddenTable = $("#hiddenTable"),
	  	  tmpTr = $("tr.editable-tr",hiddenTable),
	  	  newTr = tmpTr.clone();
	  
	  if( data.key == ' ' || (data.value).indexOf("请选择")>-1 ){
		  $("input[name='optionKey']",newTr).val(data.key);
		  $("input[name='optionValue']",newTr).val(data.value);	
		  $("input[name='optionKey']",newTr).attr("readonly","readonly");
		  $("input[name='optionValue']",newTr).attr("readonly","readonly");
	  }else{	
		  $("input[name='optionKey']",newTr).val(data.key);
		  $("input[name='optionValue']",newTr).val(data.value);	    
	  }
	  return newTr;
	};
	$("a.add").click(function() {
	        var data = {
	            key: "",
	            value: ""
	        }, newTr = addColumn(data);

	        var tbody = $('#option-table>tbody');
	        tbody.append(newTr);
	});
	function init(){
		if($(editor.target).is('td')){
			var comment=$(editor.target).prev().text().replace(':','').trim();
			var $comment=$("#inputPanel").find('input[eid="comment"]');
			if(comment){
				$comment.val(comment);
				getKeyName($comment);
			}	
		}
		
		//判断是否是子表，是子表的就把显示到列表的字段隐藏起来，否则则显示
		var subDiv=$(editor.target).parents("div.subtable");
		if(subDiv.length!=0){
			$("label[name='Listlabel']").hide();
			$("label[name='QueryLabel']").hide();
			
		}
		
		$('#isDateString').bind('change',function(){
			if($('#isDateString').attr('checked')=="checked"){
				$('#dateStrFormat').parents('span:first').show();
			}else{
				$('#dateStrFormat').parents('span:first').hide();
			}
		});
		
		$("#option-table>tbody").delegate('tr.editable-tr','mouseover mouseleave', function(e) {
	 		 if (e.type == 'mouseover') {
	            $(this).addClass('hover');
	        } else {
	            $(this).removeClass('hover');
	        } 
	    });
	    
	    $("#option-table>tbody").delegate('a.remove-bnt','click',function(e){
	        stopBubble(e);
	        var me = $(this),           
	            tr = me.parents('tr.editable-tr');
	        tr.remove();
	    });
	    
	    $("a.add").click(function() {
	        var data = {
	            key: "",
	            value: ""
	        }, newTr = addColumn(data);

	        var tbody = $('#option-table>tbody');
	        tbody.append(newTr);
	    });
	    
	    $("#fromType").change(function(){
			var me = $(this);
			var opt = me.find('option:selected');
			var type = opt.attr("type");
			if(type =='script'){
				var fromTypeScriptTr = $("#fromTypeScriptTr").show();
			}else{
				var fromTypeScriptTr = $("#fromTypeScriptTr").hide();
			}
		})
	  
	}
	//获取所有验证规则
	function getAllRules(){
		var url=__ctx+"/platform/form/bpmFormRule/getAllRules.ht";
		$.get(url,function(data){
			if(!data)return;
			var html=['<th>验证规则:</th><td colspan="3"><select eid="content" prenode="valueFrom"><option value="">无</option>'];
			for(var i=0,c;c=data[i++];){
				html.push('<option value="'+c.name+'">'+c.name+'</option>');
			}
			html.push('</select></td></td>');
			$(".valuefrom0").html(html.join(''));
			getAllSerialnum();
		});
	};
	//获取所有流水号
	function getAllSerialnum(){
		var url=__ctx+"/platform/system/identity/getAllIdentity.ht";
		$.get(url,function(data){
			if(!data)return;
			var html=['<th>流水号:</th><td colspan="3"><select eid="content" prenode="valueFrom">'];
			for(var i=0,c;c=data[i++];){
				html.push('<option value="'+c.alias+'">'+c.name+'</option>');
			}
			html.push('</select><label><input eid="isShowidentity" type="checkbox" name="isShowidentity" id="isShowidentity" > 是否显示在启动流程中</label></td>');
			$(".valuefrom3").html(html.join(''));
			bindData();
		});
	};	
	//加载完inputPanel的内容以后触发该事件
	function initComplete(){
		$("[eid='type']").bind("change",typeChange);
		$("[eid='value']").bind("change",valueChange);
		$("#isQuery").bind("click",conditionChange);
		$("[eid='searchFrom']").bind("change",searchFromChange);
		typeChange.call($("[eid='type']")[0]);
	};	
	function conditionChange(){
		$(".condition_tr").toggleClass("hidden");
	};
	//值类型变化
	function typeChange(){
		$(".dbformat_td").html(getFormatHtml($(this).val()));
		var curInputType = editor.curInputType;
		if(curInputType=="attachement"||curInputType=="pictureshowcontrol"){//附件设置长度为2000
			$("[eid='length']").val('2000');
		}
		if(curInputType=="processinstance") {
			$('.valuefrom0').hide();
			$("[eid='length']").val('2000');
		}
	};
	//值来源变化
	function valueChange(){
		var val = $(this).val();
		$("tr[class^='valuefrom']").each(function(){
			if($(this).attr('class').indexOf(val)>-1){
				$(this).removeClass('hidden');
			}
			else{
				$(this).addClass('hidden');
			}
		});
		var curInputType = editor.curInputType;
		if(curInputType=="processinstance" && '12'.indexOf(val)>-1) {
			$('.trScriptID').removeClass('hidden');
		}else{
			$('.trScriptID').addClass('hidden');
		}

	};
	//查询条件值来源变化
	function searchFromChange(){
		var val = $(this).val();
		if(val=="fromForm")
			$(".searchValue-td").addClass('hidden');
		else
			$(".searchValue-td").removeClass('hidden');
	};
	//修改状态下的初始化
	function bindData(){
		oldElement = null;
		if(!editor.curInputElement)return;
		var externalStr= $(editor.curInputElement).attr("external");
		externalStr = htmlDecode(externalStr);
		var external=eval("("+externalStr+")");
	
		//是否有 select 标签(下拉控件)
		if($("select",$(editor.curInputElement)).length ==1){
			if(typeof(external.options)==undefined || external.options==null || external.options == '' && external.options.length==0){
				external.options = new Array();       //当下拉空格为空时，设置一个空的默认值
				var data ={key:" ",value:"--请选择--"};  //key 为一个空格
				external.options.push(data)
			}
		}		
		
		oldElement = editor.curInputElement;
		editor.curInputElement=null;
		bind(external);
		if($('#isDateString').attr('checked')=="checked"){
			$('#dateStrFormat').parents('span:first').show();
		}
		
		if($('#isCoin').attr('checked')=="checked"){
			//货币选择事件
			selectCoinChange($('#isCoin'),'isShowComdify','coinValue')
		}
		
		if(external.scope){
			var scope = external.scope;
			if(scope.type == 'script'){
				$("#fromType").val("");
				$("#fromTypeScriptTr").show();
				$("#fromScript").val(scope.value);
			}else{
				$("#fromType").val(scope.value);
			}
		}
		
	}
	//递归绑定值 
	function bind(d){	
		for (var k in d){
	    	 var val= d[k];
	    	 if(typeof val == 'object' && val != null){
	    		 if(k=='options'){
					 initOptionArray(val);
				 }
	    		 else{
	    			 if(k!='scope'){
	    		 		bind(val);
	    			 }
	    		 }
	    	 }
   	 		 else if(val===1){
				$("[eid='"+k+"']").attr("checked","checked");
				if(k=='isQuery'){
					conditionChange();
				}
				if(editor.canEditColumnNameAndType){
					 if(k=='displayDate')
						 $("[eid='"+k+"']").attr("disabled","disabled");
				 }
			 }
			 else{
				 if(k=='options'){
					 var options = [],
					 	 optAry = val.split('#newline#'),
					 	 opt;
					 while(opt=optAry.shift()){
						 options.push({key:opt,value:opt});
					 }
					 initOptionArray(options);
					 continue;
				 }
				 $("#"+k+":hidden").val(val);
				 $("[eid='"+k+"']:visible").val(val);
				 if(k==="dateStrFormat"){
				 	 $("[eid='"+k+"']").val(val);
				 }
				 if(k=='type'){
					 typeChange.call($("[eid='"+k+"']")[0]);
				 }
				 if(k=='value'){
					 valueChange.call($("[eid='"+k+"']")[0]);
				 }
				 if(k=='searchFrom'){
					 searchFromChange.call($("[eid='"+k+"']")[0]);
				 }
				 if(editor.canEditColumnNameAndType){
					 if(k=='name'||k=='type'||k=='length'||k=='intLen'||k=='decimalLen'||k=='format'){//编辑时不能修改字段的这些属性
						 $("[eid='"+k+"']").attr("disabled","disabled");
					 }
				 }
			 }
	     }
	}
	//选择货币事件
	function selectCoinChange(obj,cId,sId){
		if($(obj).is (":checked")){
			$('#'+cId).attr('checked','checked');
			$('#'+cId).attr('disabled','disabled');
			$('#'+sId).show();
		}else{
			$('#'+cId).attr('checked',false);
			$('#'+cId).attr('disabled',false);
			$('#'+sId).hide();
		}
	}
	//切换数据格式
	function getFormatHtml(type){
		var html=[''];
		switch(type){
			case 'varchar':
				html.push('长度:<input eid="length" prenode="dbType" style="width:60px;" type="text" value="50"/>');
				$(".defaultValue_tr").removeClass('hidden');
				break;
			case 'number':
				html.push('<label for="isShowComdify">千分位:<input id="isShowComdify" prenode="dbType" type="checkbox" eid="isShowComdify" value="1"></label>'
						+'<label for="isCoin">货币:<input id="isCoin" eid="isCoin" type="checkbox" value="1" onchange="selectCoinChange(this,\'isShowComdify\',\'coinValue\')"></label>'
						+'<select id="coinValue" prenode="dbType"  eid="coinValue" style="width:80px;display:none"><option value="￥">￥人民币</option><option value="$">$美元</option></select>'
					+'<label>整数位:<input eid="intLen" prenode="dbType" style="width:30px;" type="text" value="14"/> 小数位:<input eid="decimalLen" prenode="dbType" style="width:25px;" type="text" value="0"/></label>');
				$(".defaultValue_tr").addClass('hidden');
				break;
			case 'date':
				html.push('<select eid="format" prenode="dbType"><option value="yyyy-MM-dd">yyyy-MM-dd</option><option value="yyyy-MM-dd HH:mm:ss">yyyy-MM-dd HH:mm:ss</option><option value="yyyy-MM-dd HH:mm:00">yyyy-MM-dd HH:mm:00</option><option value="HH:mm:ss">HH:mm:ss</option></select><br /><label for="displayDate"><input id="displayDate" eid="displayDate" prenode="dbType" type="checkbox" />当前时间</label>');
				$(".defaultValue_tr").addClass('hidden');
				break;
		}
		return html.join('');
	};
	
	dialog.onok = function() {
		var rtn=valid.valid();
		if(!rtn)return false;
		var data=[];
		$("#inputPanel").find("*:visible").each(function(){
			var id = $(this).attr("eid"),
				val = $(this).val(),
				dictType=$("#dictType").val(),
				prenode = $(this).attr("prenode");
			if($(this).attr("type")){
				if($(this).attr("type")=="checkbox"||$(this).attr("type")=="radio"){
					val = (!!$(this).attr("checked")) ? 1 : 0;
				}
			}
			//数据字典取值
			if(dictType){
				data.push({id:'dictType',val:dictType});
			}
			if((id&&val)||(id&&val==0)){
				var item = {id:id,val:val};
				if(prenode) item.prenode = prenode;
				data.push(item);
			}
		});
		var options = getOptionData();
		if(options && options.length > 0){
			data.push({id:'options',val:options});
		}
		
		var scope = getUserScope();
		if(scope){
			data.push({id:'scope',val:scope});
		}
		
		//回填字段名
		if($(editor.target).is('td')){
			var comment = $("#inputPanel").find('input[eid="comment"]').val();
			if(comment){
				var target = $(editor.target).prev();
				if(target && target.hasClass("formTitle")){
					if($("span",target).length==0 && $("div", target).length==0){
						target.text(comment+":");
					}
				}
			}
		}
		editor.execCommand(editor.curInputType,data,oldElement);
	};	
	//解码单引号和双引号
	function htmlDecode(str){
		return str.replace(/\&quot\;/g, '\"').replace(/\&\#39\;/g, '\'');
	};
	
	function getUserScope(){
		var scope = "";
		var opt = $("select#fromType").find('option:selected');
		if(opt[0]){
			scope={};
			var fromType = opt.attr("type");
			var typeVal = null;
			if(fromType == 'script'){
				typeVal = $("#fromScript").val();
				typeVal = typeVal.replace(/\'/g, '\"');
			}else{
				typeVal = opt.val();
			}
			scope.type = fromType;
			scope.value = typeVal;
		}
		return scope;
	};
	
function getKeyName(obj){
    var value=$(obj).val()
	Share.getPingyin({
			input:value,
			postCallback:function(data){
				var inputObj=	$("input[eid='name']");
					if(inputObj.val().length<1){
							inputObj.val(data.output);
					}			
			}
	});
}

//自动生成子表的表名
function getTableName(obj){
    var value=$(obj).val()
	Share.getPingyin({
			input:value,
			postCallback:function(data){
				var inputObj=	$("input[eid='tablename']");
					if(inputObj.val().length<1){
							inputObj.val(data.output);
					}			
			}
	});
}

//调用常用脚本对话框
function addScript(obj){
	var _this = $(obj);
	var target = _this.closest('td').find('textarea')[0];
	ScriptDialog({
		callback:function(data){
			jQuery.insertText(target,data);
		}
	});
};

//处理人员选择器单选时显示当前人员
function handCurUser(obj){
	var me=$(obj);
	var checked=me.attr('checked');
	var curUserCheckbox=me.parent('label').next();
	if(checked){
		curUserCheckbox.show();
	}else{
		curUserCheckbox.hide();
	}
};

</script>
</head>
<body>
	<div id="inputPanel">		
	</div>
</body>
</html>