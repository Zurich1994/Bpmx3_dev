/**
 * 流程分管授权分配。
 * 
 */
function bpmDefAuthorizeUserDialog(id,jsonStr,callBack){
	var url=__ctx +"/platform/bpm/bpmDefAuthorize/userDialog.ht?num=1";
	var conf={};
	if(objectIsEmpty(id)){
		conf.id="";
	}else{
		conf.id=id;
	}
	if(objectIsEmpty(jsonStr)){
		conf.jsonStr="";
	}else{
		conf.jsonStr=jsonStr;
	}
	//var winArgs="dialogWidth:650px;dialogHeight:390px;help:0;status:0;scroll:1;center:1;resizable:1";
	url=url.getNewUrl();
 	//var rtn=window.showModalDialog(url,conf,winArgs);
 	//return rtn;
	/*KILLDIALOG*/
	//TODO 要处理上一级回调
	DialogUtil.open({
        height:420,
        width: 650,
        title : '用户选择器',
        url: url, 
        isResize: true,
        //自定义参数
        conf: conf,
        sucCall:callBack
    });
};

//人员查看详情事件
function openDetailEvent(){
	$("a.moreinfo").live('click',function(){
		var me = $(this),
			hrefStr = me.attr('hrefstr');
		if(!hrefStr)return;
		openDetailWin({url:hrefStr,hasClose:true});
	});
}

//显示用户详情
function openDetailWin(conf){
	var dialogWidth=650;
	var dialogHeight=550;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	var url = conf.url + '&hasClose=' +conf.hasClose;
	//var rtn=window.showModalDialog(url,"",winArgs);
	
	/*KILLDIALOG*/
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title : '用户选择器',
		url: url, 
		isResize: true,
	});
};


//流程查看详情事件
function openActDetailEvent(){
	$("a.definfo").live('click',function(){
		var me = $(this),
			hrefStr = me.attr('hrefstr');
		if(!hrefStr)return;
		openActDetailWin({url:hrefStr,hasClose:true});
	});
}

//显示流程详情
function openActDetailWin(conf){
	var dialogWidth=650;
	var dialogHeight=550;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:1,center:1},conf);

	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	var url = conf.url + '&hasClose=' +conf.hasClose;
	//var rtn=window.showModalDialog(url,"",winArgs);
	/*KILLDIALOG*/
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title : '用户选择器',
		url: url, 
		isResize: true,
	});
};


//选择器回填
function setOwnerSpan(tr,json,href){
	var div = $("div.owner-div",tr);
	if(!div||div.length==0)return;
	div.empty();
	if(!json||json.length==0)return;
	for(var i=0,c;c=json[i++];){
		if(c.ownerId == "")
			continue;
		var a = $('<a class="moreinfo"></a>').html(c.ownerName).attr("ownerId",c.ownerId);
		if(href){
			a.attr("hrefstr",href+c.ownerId);
			a.attr("href","#");
		}
		var	span = $('<span class="owner-span"></span>').html(a);
		div.append(span);
	}
	var html = div.html();
};

//重置
function resetSelect(obj) {
	var tr = $(obj).closest("tr"),
		div = $("div.owner-div",tr),
		owner = $("textarea[name='ownerName']",tr);
	div.empty();
	owner.val('');
}

//设置值
function setVal(obj,json,href){
	var tr=$(obj).closest("tr"),
		owner = $("textarea[name='ownerName']",tr);
	if(json=='textarea'){
		json = owner.val();
		json = $.parseJSON(json);
	}else{
		var jsonStr = JSON2.stringify(json);
		owner.val(jsonStr);
	}
	setOwnerSpan(tr,json,href);
};


//选择所有用户
function chooseAllHandler(obj) {
	var tr=$(obj).closest("tr");
	var json=[{
		ownerId:0,
		ownerName:"所有用户"
	}];
	setVal(obj,json);
};

function convertToJson(tempIds,tempNames){
	var ids=tempIds.split(",");
	var names=tempNames.split(",");
	var json=[];
	for(var i=0;i<ids.length;i++){
		var obj={};
		obj.ownerId=ids[i];
		obj.ownerName=names[i];
		json.push(obj);
	}
	return json;
}

//选择用户
function chooseUser(obj) {
	var tr=$(obj).closest("tr"),
		data = $("textarea[name='ownerName']",tr).val(),
		href = __ctx+"/platform/system/sysUser/get.ht?openType=detail&userId=";
  
	if(data){
		data = changeStr(data);
		data = eval("("+data+")"); 
	}

	UserDialog({isSingle:false,selectUsers:data,callback:function(userIds, fullnames,email,mobile,json){
		json=convertToJson(userIds,fullnames);
		setVal(obj,json,href);
	}});
};


//过滤字符串
function changeStr(data){
	if(objectIsEmpty(data)){
		return "";
	}
	data = data.replaceAll('"ownerId"','"id"');
	data = data.replaceAll('"ownerName"','"name"');
	return data
};

//选择组织
function chooseOrg(obj){
	var tr=$(obj).closest("tr"),
		json = $("textarea[name='ownerName']",tr).val();
	if(json){
		json = changeStr(json);
		json = eval("("+json+")"); 
	}
	OrgDialog({isSingle:false,arguments:json,callback:function(orgIds, orgnames,json){
		json=convertToJson(orgIds,orgnames);
		setVal(obj,json);
	}});
};

//选择角色
function chooseRole(obj){
	var tr=$(obj).closest("tr"),
		json = $("textarea[name='ownerName']",tr).val();
	if(json){
		json = changeStr(json);
		json = eval("("+json+")"); 
	}		
	RoleDialog({isSingle:false,arguments:json,callback:function(roleIds, rolenames,json){
		json=convertToJson(roleIds,rolenames);
		setVal(obj,json);
	}});
};

//岗位选择
function choosePosition(obj){
	var tr=$(obj).closest("tr"),
		json = $("textarea[name='ownerName']",tr).val();
	if(json){
		json = changeStr(json);
		json = eval("("+json+")"); 
	}
	PosDialog({isSingle:false,arguments:json,callback:function(roleIds, rolenames,json){
		json=convertToJson(roleIds,rolenames);
		setVal(obj,json);
	}});
};

// ID为all_CheckboxClass的checkbox选中 ，下面的checkbox的 class = CheckboxClass的也选中
function selectCheckbox(all_checkboxName){
	var checkboxNames = all_checkboxName.split("_");
	var checkboxName = checkboxNames[1];
	var checkboxNameArry = $("."+checkboxName);
	var checked=$("#"+all_checkboxName).is(":checked");
	if(checked){
		checkboxNameArry.attr("checked",true); 
	}else{
		checkboxNameArry.removeAttr("checked"); 
	}
};


//是否为空
function objectIsEmpty(obj){
	// 内容是是否为空
	if(typeof(obj)==undefined||obj==null||obj==''){
		return true;
	}else{
		return false;
	}
};

//并且不能等str的内容，是否为空
function objectIsEmptyByRep(obj,str){
    // 内容是是否为空
	if(typeof(obj)==undefined||obj==null||obj==''||obj==str){
		return true;
	}else{
		return false;
	}
};

