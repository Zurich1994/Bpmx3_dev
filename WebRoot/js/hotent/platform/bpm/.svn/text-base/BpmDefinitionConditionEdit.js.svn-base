var assignUseType = 0;
$(function(){
	//绑定上下移动	
	$("a.moveup,a.movedown").click(move);
	var data = getInitData();
	
	
	//初始化日期控件
	FormUtil.initCalendar();		
	$("#ruleDiv").linkdiv({data:data,updateContent:updateContent,rule2json:rule2json});
});
/*KILLDIALOG*/
function addDiv(ruleType){
	$("#ruleDiv").linkdiv("addDiv",{ruleType:ruleType});
};

function removeDiv(){
	$("#ruleDiv").linkdiv("removeDiv");	
};

function assembleDiv(){
	$("#ruleDiv").linkdiv("assembleDiv");
};

function splitDiv(){
	$("#ruleDiv").linkdiv("splitDiv");
};

function getData(){
	var json = $("#ruleDiv").linkdiv("getData");
	return json;
};

function getInitData(){
	var data = $("#conditionTxt").val().trim();
	if(data=="") return;
	var json = eval("("+data+")");
	if(json.length==0)return;
	return json;
};

//保存数据
function save(){
	//获取条件的JS，这个方法在BpmNodeRule.js
	var json = getData();
	//构建参数
	var param = $("#conditionEntity").val();
	param = eval("("+param+")");
	assignUseType = param.conditionType;
	if(json.length>0)
		param.condition = JSON2.stringify(json);
	var users = extractNodeUserDatas();

	if(!users){
		$.ligerDialog.warn("没有设置用户!","提示信息");
		return;
	}
	var conditionShow = getconditionShow();
	if(conditionShow)
		param.conditionShow = conditionShow;
	param.users = users;
	var parentActDefId=$('#parentActDefId').val();
	if(parentActDefId){
		param.parentActDefId = parentActDefId;
	}
	$.post(__ctx+"/platform/bpm/bpmUserCondition/save.ht",param,function(response){
		var resultJson=eval('('+response+')');
		if(resultJson.result==1){
			$.ligerDialog.success("保存成功!","提示信息",function(rtn){
				//对话框
				//window.returnValue=true;
				try{
					dialog.get("sucCall")(true);
					dialog.close();
				}catch (e) {
					//使用window open打开的处理。
					if(window.opener){
						window.opener.location.href=window.opener.location.href.getNewUrl();
						window.close();
					}
				}
			});
		}
		else{
			$.ligerDialog.warn(resultJson.message);
		}
	});
	
};

/******************用户设置相关代码*********************/
//绑定上下移动	
function move(){
 	var obj=$(this);
 	var direct=obj.hasClass("moveup");
 	var objTr=obj.closest("tr");
	if(direct){
		var prevObj=objTr.prev();
		if(prevObj.length>0){
			objTr.insertBefore(prevObj);	
		}
	}
	else{
		var nextObj=objTr.next();
		if(nextObj.length>0){
			objTr.insertAfter(nextObj);
		}
	}
};



// 获取用户设置
function extractNodeUserDatas(){
	var users = new Array();
	$("#tbodyUserSet").find("tr").each(function(){
		var tr = $(this);
		var assignType = tr.find("[name='assignType']").val();
		var nodeId = tr.find("[name='nodeId']").val();
		var cmpIds = tr.find("[name='cmpIds']").val();
		var cmpNames = tr.find("[name='cmpNames']").val();
		var nodeUserId = tr.find("[name='nodeUserId']").val();
		var compType = tr.find("[name='compType']").val();
		var extractUser = tr.find("[name='extractUser']").val();
		var user={
			nodeUserId:nodeUserId,
			assignType:assignType,	
			assignUseType:assignUseType,
			nodeId:nodeId,
			cmpIds:cmpIds,
			cmpNames:cmpNames,
			compType:compType,
			extractUser:extractUser
		};
		users.push(user);
	});
	if(users.length==0)return false;
	return JSON2.stringify(users);
};
//获取用来显示的用户设置文字描述
function getconditionShow(){
	var joinTxt = [];
	
	$("#tbodyUserSet").find("tr").each(function(){
		var me = $(this),
			assignTypeSpan = $("option:selected",$("select[name='assignType']",me)), 
			assignType = '',
			cmpNames = $("textarea[name='cmpNames']",me).val(),
			compTypeSpan = $("option:selected",$("select[name='compType']",me)),
			compType = '';

		if(assignTypeSpan[0]){
			assignType = assignTypeSpan[0].text;
		}
		if(compTypeSpan[0]){
			compType = compTypeSpan[0].text;
		}
		if(!!!assignType){
			assignType = $("input[name='assignType']",me).siblings("span").text();
		}
		if(joinTxt.length > 0){
			joinTxt.push(' ');
			joinTxt.push(compType);
			joinTxt.push(' ');
		}
		joinTxt.push(assignType);
		joinTxt.push(':');
		joinTxt.push(cmpNames);
	});
	

	return joinTxt.join('');

};	


// 获取选择的用户用户设置
function getNodeUserDatas(){
	var users = new Array();
	$("#tbodyUserSet").find("tr").each(function(){
		var tr = $(this);	
		var nodeUserCk=tr.find("input[name='nodeUserCk']:checked");	
		var assignType = tr.find("[name='assignType']").val();
		var nodeId = tr.find("[name='nodeId']").val();
		var cmpIds = tr.find("[name='cmpIds']").val();
		var cmpNames = tr.find("[name='cmpNames']").val();
		var nodeUserId = tr.find("[name='nodeUserId']").val();
		var compType = tr.find("[name='compType']").val();
		var user={
			nodeUserCk:nodeUserCk.length>0?true:false,
			nodeUserId:nodeUserId,
			assignType:assignType,	
			assignUseType:assignUseType,
			nodeId:nodeId,
			cmpIds:cmpIds,
			cmpNames:cmpNames,
			compType:compType
		};
		users.push(user);
	});
	if(users.length==0)return false;
	var userData =  new Array();
	$.each(users,function(d, e) {
		if(e.nodeUserCk)
			userData.push(e);
	})
	if(userData.length>0){
		return JSON2.stringify(userData);
	}else{
		return JSON2.stringify(users);
	}
};
/**
 * 预览
 * @param {} defId
 */
function previewUserSetting(defId){
	var params=getNodeUserDatas();
	if(!params) {
		alert("还没有定义用户配置!");
		return;
	}
	var winArgs="dialogWidth=600px;dialogHeight=400px;help=0;status=0;scroll=1;center=1";
	var url=__ctx + '/platform/bpm/bpmNodeUser/previewMockParams.ht?defId=' +defId;
	url=url.getNewUrl();
	DialogUtil.open({
		height:400,
		width: 600,
		title : '预览',
		url: url, 
		isResize: true,
		//自定义参数
		params: params
	});
}