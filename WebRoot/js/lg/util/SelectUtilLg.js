var rowdata;
var currentColumns;
function setObjs(rowData,columnname,values){
	rowdata=grid.getRow(rowData);
	var newRowData={};
	newRowData[columnname+"ID"]=values['id'];
	newRowData[columnname]=values['name'];
	currentColumns=[columnname+"ID",columnname];
	$.extend(rowdata, newRowData || {});
}

/**
 * 选择标签
 */
function selectTag(rowData,columnname,multi){
	TagDialog({isSingle:multi,callback:function(ids,names){
		setObjs(rowData,columnname,{id:ids,name:names});
		grid.insertValue({rowdata:rowdata,currentColumns:currentColumns});
	}}); 
} 




/**
 * 选择组织
 */
function selectOrg(rowData,columnname,multi){
	OrgDialog({isSingle:multi,
		callback:function(ids,names){
			setObjs(rowData,columnname,{id:ids,name:names});
			grid.insertValue({rowdata:rowdata,currentColumns:currentColumns});
	}});	
}

/**
 * 选择用户
 */
function  selectUser(rowData,columnname,multi){
	UserDialog({
		isSingle:multi,
		callback:function(ids,names){
			setObjs(rowData,columnname,{id:ids,name:names});
            grid.insertValue({rowdata:rowdata,currentColumns:currentColumns});
		}});
}
/**
 * 选择角色
 */
function selectRole(rowData,columnname,multi){
	RoleDialog({
			isSingle : multi,
			callback : function(ids, names) {
				setObjs(rowData,columnname,{id:ids,name:names});
				grid.insertValue({rowdata:rowdata,currentColumns:currentColumns});
			}
		});
}
/**
 * 选择岗位
 */
function selectPos(rowData,columnname,multi){
	PosDialog({
			isSingle : multi,
			callback : function(ids, names) {
				setObjs(rowData,columnname,{id:ids,name:names});
				grid.insertValue({rowdata:rowdata,currentColumns:currentColumns});
			}
		});
}



/**
 * 标签选择窗口
 * @param conf
 */
function TagDialog(conf){
	var dialogWidth=650;
	var dialogHeight=500;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:1,center:1},conf);

	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	if(!conf.isSingle)conf.isSingle=false;
	var url=__ctx +'/ecp/bpm/bpmTag/dialog.ht?isSingle=' + conf.isSingle;
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,"",winArgs);
	
	//TODO jsp找不到
	if(conf.callback)
	{
		if(rtn!=undefined){
			 conf.callback.call(this,rtn.tagid,rtn.tagname);
		}
	}
} 


/**
 * 通用对话框。
 * @param alias		对话框别名。
 * @param callBack	回调函数。
 * 调用示例：
 * CommonDialog("role",function(data){
 * 
 * });
 * data:为json数组或者为json对象。
 * @param paramValueString	向对话框传递的"参数=值"字符串
 * 传入多个则需要使用"&"符号进行连接（user=admin&orgId=1）
 */
function CommonDialog(alias,callBack,paramValueString){
	window.__resultData__=0;
	if(alias==null || alias==undefined){
		$.ligerDialog.warn("别名为空！",'提示信息');
		return;
	}
	var url=__ctx + "/platform/form/bpmFormDialog/dialogObj.ht?alias=" +alias;
	url=url.getNewUrl();
	$.post(url,{"alias":alias},function(data){
		if(data.success==0){
			$.ligerDialog.warn("输入别名不正确！",'提示信息');
			return;
		}
		var obj=data.bpmFormDialog;
		var width=obj.width;
		var name=obj.name;
		var height=obj.height;
		var displayList=obj.displayfield.trim();
		var resultfield=obj.resultfield.trim();
		if( displayList==""){
			$.ligerDialog.warn("没有设置显示字段！",'提示信息');
			return;
		}
		if( resultfield==""){
			$.ligerDialog.warn("没有设置结果字段！",'提示信息');
			return;
		}
		
		var urlShow=__ctx + "/platform/form/bpmFormDialog/show.ht?dialog_alias_=" +alias;
		if(!paramValueString==false){
			urlShow = urlShow + "&" + encodeURI(paramValueString) ;
		}
		urlShow=urlShow.getNewUrl();
		$.ligerDialog.open({ url:urlShow, height: height,width: width, title :name,name:"frameDialog_",
			buttons: [ { text: '确定', onclick: function (item, dialog) { 
					if(__resultData__==-1 || __resultData__==0){
						alert("还没有选择数据项！");
						return;
					}
					if(callBack){
						callBack(__resultData__);
					}
					dialog.close();
			} }, 
			{ text: '取消', onclick: function (item, dialog) { dialog.close(); } } ] });
	});
};