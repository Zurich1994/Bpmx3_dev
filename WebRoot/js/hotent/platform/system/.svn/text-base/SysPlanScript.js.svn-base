//初始化选择器
function initData(){
	var href = __ctx+"/platform/system/sysUser/get.ht?openType=detail&userId=";
	
	//初始化提交人
	var submitId = $("input[name='submitId']").val();
	var submitor = $("input[name='submitor']").val();
	var arrySubmit = [{id:submitId,name:submitor}];
	if(submitId!=''&&submitId!=null&& 'undefined' != typeof (submitId)){
		setOwnerDiv('submitDiv',arrySubmit,href,'submitId','submitor');
	}
	
	//初始化负责人
	var chargeId = $("input[name='chargeId']").val();
	var charge = $("input[name='charge']").val();
	var arryCharge = [{id:chargeId,name:charge}];
	if(chargeId!=''&&chargeId!=null&& 'undefined' != typeof (chargeId)){
		setOwnerDiv('chargeDiv',arryCharge,href,'chargeId','charge');
	}
	
	//初始化参与人
	var participantIds = $("input[name='participantIds']").val();
	var participants = $("input[name='participants']").val();
	var arryParticipant = convertToArry(participantIds,participants);
	if(participantIds!=''&&participantIds!=null&& 'undefined' != typeof (participantIds)){
		setOwnerDiv('participantDiv',arryParticipant,href,'participantIds','participants');
	}
	
	//初始化客户
	var customerId = $("input[name='customerId']").val();
	var customer = $("input[name='customer']").val();
	var arryCustomer = [{id:customerId,name:customer}];
	if(customerId!=''&&customerId!=null&& 'undefined' != typeof (customerId)){
		setOwnerDiv('customerDiv',arryCustomer,"",'customerId','customer');
	}
	
	
	//初始化 工单
	var runId = $("input[name='runId']").val();
	var runName = $("input[name='runName']").val();
	var arryRun = [{id:runId,name:runName}];
	if(runId!=''&&runId!=null&& 'undefined' != typeof (runId)){
		var flowHref = __ctx+"/platform/bpm/processRun/info.ht?runId=";
		setOwnerDiv('runDiv',arryRun,flowHref,runId,runName,"1000","600","查看流程工单");
	}
	
	
	//初始化附件选择器
//	AttachMent.init("w");
	
}


//初始化提交事件
function initSubmit(){	
	$("a.save").click(function() {
		//验证
		var frm=$('#sysPlanEdit');   
		if(!frm.valid()) return ;
		
	    //核验选择器的
	    var submitId =  $("input[name='submitId']").val();
	    if(typeof(submitId)=='undefined'||submitId==null||submitId==''){
	    	$.ligerDialog.warn("请选择提交人！","消息提示");
	    	return;
		}
	    var chargeId =  $("input[name='chargeId']").val();
	    if(typeof(chargeId)=='undefined'||chargeId==null||chargeId==''){
	    	$.ligerDialog.warn("请选择负责人！","消息提示");
	    	return;
		}
		
		//提交保存内容
		frm.submit();
	});
}


//初始化日程交流提交事件
function initExchangeSubmit(dialog){	
	$("a.save").click(function() {
		//核验信息
	    var content =  $("textarea[name='content']").val();
	    if(typeof(content)=='undefined'||content==null||content==''){
	    	$.ligerDialog.warn("请填写内容！","消息提示");
	    	return;
		}
	    var url = __ctx + "/platform/system/sysPlan/saveExchange.ht";
		var planId = $("input[name='planId']").val();
		var id = $("input[name='id']").val();
		var doc =  $("textarea[name='doc']").val();
	    $.ajax({ 
	   	   	 type:"post", 
	   	   	 url:url, 
	   	   	 dataType:"json", 
	   	   	 data:{
	   	   		     id:id,
	   	   		     planId:planId,
	   	   		     content:content,
	   	   		     doc:doc
	   	   		  }, 
	   	   	 success:function(data){ 
	   				var type = $("input[name='type']").val();
	   				var currentViweDate = $("input[name='currentViweDate']").val();
	   	   		    if(data.result){
	   	   		  		$.ligerDialog.success(data.message,"消息提示",function(){
			   	   		  	dialog.get("sucCall")(data.cause);
		   	   		 		dialog.close();
	   	   		  		});
	   				}
	   				else{
	   					$.ligerDialog.warn(data.message,"消息提示");
	   				}
	   	   	 } 
	   	 }); 
	    
	});
}


//初始化删除事件
function initDelete(){	
	$("#delSysPlan").click(function() {	
		var ids = $("input[name='id']").val();
		delSysPlan(ids);
	});
}

//通过id字符串（id1,id2,id3...）删除日程
function delSysPlan(ids){	
	if(!ids){
		$.ligerDialog.warn("日程ID为空，删除失败！","消息提示");
		return;
	}
	
	$.ligerDialog.confirm('确定删除吗?', function (confirm) {
        if (confirm){
        	var url = __ctx + "/platform/system/sysPlan/delete.ht";
        	$.ajax({ 
        	   	 type:"post", 
        	   	 url:url, 
        	   	 dataType:"json", 
        	   	 data:{
        	   		     ids:ids
        	   		  }, 
        	   	 success:function(data){ 
        				var type = $("input[name='type']").val();
        				var currentViweDate = $("input[name='currentViweDate']").val();
        	   		    if(data.result){
        					$.ligerDialog.success(data.message,"消息提示",function(){
        						var href =  __ctx + "/platform/system/sysPlan/submit.ht?currentViweDate="+currentViweDate;
        						if(type=='charge'){
        							href =  __ctx + "/platform/system/sysPlan/charge.ht?currentViweDate="+currentViweDate;
        						}
        						window.location.href = href;
        					});
        				}
        				else{
        					$.ligerDialog.warn(data.message,"消息提示");
        				}
        	   	 } 
        	 }); 
        }
    });
	
}


//初始化删除日程交流事件
function initDeleteExchang(){	
	$("#delSysPlanExchange").click(function() {	
		var ids = "";
		$("input[name='exchangeId']").each(function () { 
			var me = $(this);
			if(me.is(":checked")){
				ids += me.val()+",";
			}
		});
        if(ids){
        	ids = ids.substring(0,ids.length-1);
        }
    	delSysPlanExchange(ids);
	});
}

//通过id字符串（id1,id2,id3...）删除日程交流信息
function delSysPlanExchange(ids){	
	if(!ids){
		$.ligerDialog.warn("删除失败，请选择日程交流信息！","消息提示");
		return;
	}
	
	$.ligerDialog.confirm('确定删除吗?', function (confirm) {
        if (confirm){
        	var url = __ctx + "/platform/system/sysPlan/deleteExchange.ht";
        	$.ajax({ 
        	   	 type:"post", 
        	   	 url:url, 
        	   	 dataType:"json", 
        	   	 data:{
        	   		     ids:ids
        	   		  }, 
        	   	 success:function(data){ 
        				var id = $("input[name='id']").val();
        				var type = $("input[name='type']").val();
        				var currentViweDate = $("input[name='currentViweDate']").val();
        	   		    if(data.result){
        					$.ligerDialog.success(data.message,"消息提示",function(){
        						var href =  __ctx + "/platform/system/sysPlan/submit.ht?id="+id+"&currentViweDate="+currentViweDate;
        						if(type=='charge'){
        							href =  __ctx + "/platform/system/sysPlan/exchange.ht?type=charge&id="+id+"&currentViweDate="+currentViweDate;
        						}else if(type=='participant'){
        							href =  __ctx + "/platform/system/sysPlan/participantToExchange.ht?type=participant&id="+planId+"&currentViweDate="+currentViweDate;
        						}else if(type=='myPlan'){
        							href =  __ctx + "/platform/system/sysPlan/exchange.ht?type=myPlan&id="+id;
        						}
        						window.location.href = href;
        					});
        				}
        				else{
        					$.ligerDialog.warn(data.message,"消息提示");
        				}
        	   	 } 
        	 }); 
        }
    });
	
}


//初始化完成日程事件
function initFinishSysPlan(){	
	$("#finishSysPlan").click(function() {	
		var id = $("input[name='id']").val();
		finishSysPlan(id);
	});
}

//通过id完成日程
function finishSysPlan(id){	
	if(!id){
		$.ligerDialog.warn("日程ID信息为空，完成任务失败！","消息提示");
		return;
	}
	
	$.ligerDialog.confirm('确定完成任务吗?', function (confirm) {
        if (confirm){
        	var url = __ctx + "/platform/system/sysPlan/chargeSysPlans.ht";
        	$.ajax({ 
        	   	 type:"post", 
        	   	 url:url, 
        	   	 dataType:"json", 
        	   	 data:{
        	   		     ids:id,
        	   		     rate:100
        	   		  }, 
        	   	 success:function(data){ 
        				var type = $("input[name='type']").val();
        				var currentViweDate = $("input[name='currentViweDate']").val();
        	   		    if(data.result){
        					$.ligerDialog.success(data.message,"消息提示",function(){
        						var href =  __ctx + "/platform/system/sysPlan/submit.ht?id="+id+"&currentViweDate="+currentViweDate;
        						if(type=='charge'){
        							href =  __ctx + "/platform/system/sysPlan/exchange.ht?type=charge&id="+id+"&currentViweDate="+currentViweDate;
        						}else if(type=='participant'){
        							href =  __ctx + "/platform/system/sysPlan/participantToExchange.ht?type=participant&id="+id+"&currentViweDate="+currentViweDate;
        						}else if(type=='myPlan'){
        							href =  __ctx + "/platform/system/sysPlan/exchange.ht?type=myPlan&id="+id;
        						}
        						window.location.href = href;
        					});
        				}
        				else{
        					$.ligerDialog.warn(data.message,"消息提示");
        				}
        	   	 } 
        	 }); 
        }
    });
	
}


//初始化日程交流信息添加事件
function initAddSysPlanExchange(){	
	$("#addSysPlanExchange").click(function() {	
		var planId = $("input[name='id']").val();
		editSysPlanExchange("",planId);
	});
}

//打开修改日程交流页面
function editSysPlanExchange(id,planId){
	var url = __ctx + "/platform/system/sysPlan/exchangeEdit.ht?planId="+planId+"&id="+id;
	var type = $("input[name='type']").val();
	var currentViweDate = $("input[name='currentViweDate']").val();
	DialogUtil.open({
		height:400,
		width: 600,
		title : '编辑日程交流',
		url: url, 
		isResize: false,
		sucCall:function(retVal){
			if(retVal){
				var href =  __ctx + "/platform/system/sysPlan/submit.ht?id="+planId+"&currentViweDate="+currentViweDate;
				if(type=='charge'){
					href =  __ctx + "/platform/system/sysPlan/exchange.ht?type=charge&id="+planId+"&currentViweDate="+currentViweDate;
				}else if(type=='participant'){
					href =  __ctx + "/platform/system/sysPlan/participantToExchange.ht?type=participant&id="+planId+"&currentViweDate="+currentViweDate;
				}else if(type=='myPlan'){
					href =  __ctx + "/platform/system/sysPlan/exchange.ht?type=myPlan&id="+planId;
				}
				window.location.href = href;
			}
		}
		
	});
}

//打开日交流信息查看页面
function openSysPlanExchange(id,planId){
	var url = __ctx + "/platform/system/sysPlan/exchangeGet.ht?planId="+planId+"&id="+id;
	DialogUtil.open({
		height:400,
		width: 600,
		title : '日程交流信息详情',
		url: url, 
		isResize: false,
		sucCall:function(retVal){
			//回调
		}
	});
}

//选择用户
/* 例子：
	<input type="hidden" name="submitId" value="${sysPlan.submitId}"  />
	<input type="hidden" name="submitor" value="${sysPlan.submitor}"  />
	<div id='submitDiv'></div>
	<a class="link add" onclick="chooseUser(this,'submitDiv','submitId','submitor','yes');" ><span></span>选择</a>
	<a class="link reset" onclick="resetSelect('submitDiv','submitId','submitor');" ><span></span>重置</a>
*/
function chooseUser(me,divId,myId,myName,isSingleMark) {
	var isSingle = false;
	if(isSingleMark=='yes'){
		isSingle = true;
	}
	var data = [];
	var href = __ctx+"/platform/system/sysUser/get.ht?openType=detail&userId=";
	var ids = $("input[name='"+myId+"']").val();
	var names = $("input[name='"+myName+"']").val();
	if(ids){
		data = convertToArry(ids, names);
	}
		
	UserDialog({isSingle:isSingle,selectUsers:data,callback:function(userIds, fullnames,email,mobile,json){
		var arry = convertToArry(userIds, fullnames);
		if(arry.length>0){
			setOwnerDiv(divId,arry,href,myId,myName);
        }
	}});
};


//选择自定义对话框
/* 例子：
	<input type="hidden" name="customerId" value="${sysPlan.chargeId}" />
	<input type="hidden" name="customer" value="${sysPlan.charge}"  />
	<div id='customerDiv'></div>
	<a class="link add" onclick="chooseCustomer(this,'spxx（别名）','这里是参数为空','customerDiv','customerId','customer');" ><span></span>选择</a>
    <a class="link reset" onclick="resetSelect('customerDiv','customerId','customer');" ><span></span>重置</a>
 */
function chooseCustomer(me,alias,paramValueString,divId,myId,myName){	
	//最好是返回一个对象的，因为客户这个字段只支持一个
	CommonDialog(alias,function(data){
	  //data返回 Object { F_spmc = "参数值", F_dj = "参数值", F_sl = "参数值"}，多个则返回 Object 数组
	 	if(data){
	 		var arry = convertToArry(data.ID, data.F_spmc);
			if(arry.length>0){
				setOwnerDiv(divId,arry,null,myId,myName);
	        }
	  	}
	},paramValueString);
}


/* 例子：
*/
function chooseProcessRun(me,divId,myId,myName,isSingleMark) {
	var isSingle = 'yes';
	if(isSingleMark!='yes'){
		isSingle = 'no';
	}
	var ids = $("input[name='"+myId+"']").val();
	var names = $("input[name='"+myName+"']").val();
	var data = [];
	if(ids){
		data = convertToArry(ids, names);
	}
	processRunDialog({isSingle:isSingle,arguments:data,callback:function(that,rtn){
		//var arry = convertToArry(userIds, fullnames);
		if(that&&that.json){
			var arry = that.json;
			setOwnerDiv(divId,arry,"",myId,myName);
	    }
	}});
};



/**
 * 流程工单选择窗口。
 * processRunDialog({isSingle:true,callback:dlgCallBack}){
 *		//回调函数处理
 *	}});
 * @param conf
 */
function processRunDialog(conf){
	var dialogWidth=900;
	var dialogHeight=640;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

	if(!conf.isSingle)conf.isSingle='yes';
	var url=__ctx + '/platform/system/sysPlan/processRunDialog.ht?isSingle=' + conf.isSingle;
	url=url.getNewUrl();
	
	
	var that = this;
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title : '打开选择引用流程实例工单对话框',
		url: url, 
		isResize: true,
		conf:conf,
		//自定义参数
		arguments: conf.arguments,
		sucCall:function(rtn){
			 conf.callback.call(that,rtn);
		}
	});
}




//转成JSON数组
function convertToArry(tempIds,tempNames){
	var ids=tempIds.split(",");
	var names=tempNames.split(",");
	var arry=[];
	for(var i=0;i<ids.length;i++){
		var obj={};
		obj.id=ids[i];
		obj.name=names[i];
		arry.push(obj);
	}
	return arry;
}

//选择器回填
function setOwnerDiv(divId,arry,href,myId,myName,width,height,title){
	var div = $("#"+divId);
	div.empty();
	var myIdObj = $("input[name='"+myId+"']");
	var myNameObj = $("input[name='"+myName+"']");
	var ids = "";
	var names = "";
	for(var i=0;i<arry.length;i++){
		var obj=arry[i];
		ids += obj.id+",";
		names += obj.name+",";
		var a = $('<a class="moreinfo"></a>').html(obj.name).attr("ownerId",obj.id);
		if(href){
			a.attr("hrefstr",href+obj.id);
			a.attr("href","#");
		}
		if(width){
			a.attr("dialogWidth",width);
		}
		if(height){
			a.attr("dialogHeight",height);
		}
		if(title){
			a.attr("dialogTitle",title);
		}
		var	span = $('<span class="owner-span"></span>').html(a);
		div.append(span);
	}
	ids = ids.substring(0, ids.length-1);
	names = names.substring(0, names.length-1);
	myIdObj.val(ids);
	myNameObj.val(names);
}

//重置
function resetSelect(divId,cleanId,cleanName){
	$("#"+divId).empty();
	$("input[name='"+cleanId+"']").val("");
	$("input[name='"+cleanName+"']").val("");
}

//人员查看详情事件
function openDetailEvent(){
	$("a.moreinfo").live('click',function(){
		var me = $(this),
			hrefStr = me.attr('hrefstr'),
			dialogWidth = me.attr('dialogWidth'),
			dialogHeight = me.attr('dialogHeight'),
			dialogTitle = me.attr('dialogTitle');
		if(!hrefStr)return;
		openDetailWin({url:hrefStr,hasClose:true,dialogWidth:dialogWidth,dialogHeight:dialogHeight,dialogTitle:dialogTitle});
	});
}

//显示用户详情
function openDetailWin(conf){
	var dialogWidth=800;
	var dialogHeight=600;
	var dialogTitle='用户详情';
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,dialogTitle:dialogTitle, help:0,status:0,scroll:0,center:1},conf);
	var url = conf.url + '&hasClose=' +conf.hasClose;
	
	/*KILLDIALOG*/
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title: conf.dialogTitle,
		url: url, 
		isResize: true,
	});
}

//全选择方法
function selectCheckbox(all_checkboxName){
	var checkboxNames = all_checkboxName.split("_");
	var checkboxName = checkboxNames[1];
	var checkboxNameArry =  $("input[name='"+checkboxName+"']");
	var checked=$("#"+all_checkboxName).is(":checked");
	if(checked){
		checkboxNameArry.attr("checked",true); 
	}else{
		checkboxNameArry.removeAttr("checked"); 
	}
};


//订阅
function subscribeSysPlan(planId){
	var executionUrl = __ctx + "/platform/system/sysPlan/subscribeSysPlan.ht";
	var reutrnUrl = __ctx + "/platform/system/sysPlan/underList.ht";
	var queryParam = {planId:planId};
	executionSysPlan(queryParam,executionUrl,reutrnUrl);
}

//退订
function cancelSysPlan(subscribeId){
	var executionUrl = __ctx + "/platform/system/sysPlan/cancelSysPlan.ht";
	var reutrnUrl = __ctx + "/platform/system/sysPlan/underList.ht";
	var queryParam = {id:subscribeId};
	executionSysPlan(queryParam,executionUrl,reutrnUrl);
}

//执行异步回返方法
function executionSysPlan(queryParam,executionUrl,reutrnUrl){
	var queryUserId = $("input[name='queryUserId']").val();
	if(queryUserId!=null&&queryUserId!=''&& 'undefined' != typeof (queryUserId)){
		reutrnUrl = reutrnUrl+"?queryUserId="+queryUserId;
	}
	$.ajax({ 
	   	 type:"post", 
	   	 url:executionUrl, 
	   	 dataType:"json", 
	   	 data:queryParam, 
	   	 success:function(data){ 
	   		    if(data.result){
					$.ligerDialog.success(data.message,"消息提示",function(){
						window.location.href = reutrnUrl;
					});
				}
				else{
					$.ligerDialog.warn(data.message,"消息提示");
				}
	   	 } 
	 }); 
}



