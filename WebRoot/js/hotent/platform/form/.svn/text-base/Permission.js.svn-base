/**
 * 表单权限。
 * @returns {Permission}
 */
Permission=function(){
	{
		this.FieldsPermission=[];
		this.SubTablePermission=[];
		this.SubTableFilePermission=[];
		this.subTableShows=[];
		this.Opinion=[];
	};
	/**
	 * 获取默认权限对象。
	 */
	this.getDefaultPermission=function(name,memo){
		var permission={"title":name,"memo":memo,"read": {"type":"everyone","id":"", "fullname":""},"write":{"type":"everyone","id":"", "fullname":""},"required":{"type":"everyone","id":"", "fullname":""}};
		return permission;
	};
	
	
	/**
	 * 从数据库加载权限，并初始化html表格状态。
	 * 需要传入tableId，formDefId。
	 * 如果是新建表单，权限根据表获取。
	 * 如果是更新表单，权限从表单权限获取。
	 */
	this.loadPermission=function(tableId,formKey,platform){
		var params={tableId:tableId,formKey:formKey,platform:platform};
		this.load("getPermissionByTableFormKey.ht", params);
	};
		
	/**
	 * 从数据库加载权限，并初始化html表格状态。
	 */
	this.loadByNode=function(actDefId, nodeId,formKey,parentActDefId,platform){
		var params={actDefId:actDefId,nodeId:nodeId,formKey:formKey,parentActDefId:parentActDefId,platform:platform};
		this.load("getPermissionByFormNode.ht", params);
	};
	
	/**
	 * 从数据库加载权限，并初始化html表格状态。
	 */
	this.loadByActDefId=function(actDefId,formKey,parentActDefId,platform){
		var params={actDefId:actDefId,formKey:formKey,parentActDefId:parentActDefId,platform:platform};
		this.load("getPermissionByActDefId.ht", params);
	};
	
	this.load=function(url,params){
		
		var _self=this;
		$.ligerDialog.waitting("正在加载表单权限,请稍后...");
		$.post(url, params,function(data){
			$.ligerDialog.closeWaitting();
			var fields =data["field"];
			var tables =data["table"];
			var opinions =data["opinion"];
			var tableShows =data["tableShow"];
			
			//字段权限。
			if(fields!=undefined && fields!=''){
				_self.FieldsPermission=fields;
				var fieldHtml=_self.getPermission(_self.FieldsPermission,"field");
				$("#fieldPermission").empty();
				$("#fieldPermission").append(fieldHtml);
				_self.initStatus("fieldPermission");
			}
			//子表权限
			if(tables!=undefined && tables!=''){
				_self.SubTablePermission=tables;
				_self.setSubTableFilePermission(tables);
				var tableHtml=_self.getPermission(_self.SubTablePermission,"subtable");
				$("#tablePermission").empty();
				$("#tablePermission").append(tableHtml);
				for ( var i = 0; i < _self.SubTablePermission.length; i++) {    //按子表table的ID区域去初始化控件状态
					var tablePermission=_self.SubTablePermission[i];
					_self.initStatus("tableId_"+tablePermission.tableId);
				}
			}else{
				$("#tablePermission").closest( 'table' ).hide();
			}
			
			//意见权限。
			if(opinions!=undefined && opinions!=''){
				_self.Opinion=opinions;
				var opinionHtml=_self.getPermission(_self.Opinion,"opinion");
				$("#opinionPermission").empty();
				$("#opinionPermission").append(opinionHtml);
				_self.initStatus("opinionPermission");
			}else{
				$("#opinionPermission").closest( 'table' ).hide();
			}
			
			//子表是否显示。
			if(tableShows!=undefined && tableShows!=''){
				_self.subTableShows=tableShows;
				_self.initSubTableCheck(_self.subTableShows);
			}
			

		});
		_self.handChange();
		_self.handClick();
	};
	
	
	/**
	 * 加载完权限后，修改子表是否显示的复选框。
	 */
	this.initSubTableCheck=function(tableShows){
		for(var i=0;i<tableShows.length;i++){
			var objPermission=tableShows[i];
			var scope = "#tableId_"+objPermission.tableId;
			var objScope=$(scope);
			var show = objPermission.show;
			if(!show) show = {};
			var jsonLen = 0;
			for(var obj in show){
				jsonLen++;
			}
			if(jsonLen!=3){
				$('input:checkbox[value="addRow"]',objScope).attr("checked",true);
				$('input:checkbox[value="addRow"]',objScope).attr("show",'true');
				continue;
			}
			$('input:checkbox[name^="checkbox_"]',objScope).each(function(){      //设置objPermission.showr的值为当前选中项
				var self = $(this);
				var value = self.val();
				if(show[value]=='true') {
					self.attr("checked",true);
					self.attr("show",'true');
				}else {
					self.attr("checked",false);
					self.attr("show",'false');
				}
				hideSubTable(self, self.attr("checked"));
			}); 
		}
	};
	
	
	/**
	 * 加载完权限表格后，修改控件的状态。
	 */
	this.initStatus=function(id){
		var _self=this;
		$("tr","#"+id).each(function(){
			var trObj=$(this);
			//取得下拉框
			var selReadObj=$("select.r_select",trObj);
			var selWriteObj=$("select.w_select",trObj);
			var selRequiredObj=$("select.b_select",trObj);
			//值为user,everyone,none,role,orgMgr,pos等。
			//查看下拉框
			var rPermissonType=selReadObj.attr("permissonType");
			var wPermissonType=selWriteObj.attr("permissonType");
			var bPermissonType=selRequiredObj.attr("permissonType");
			
			//初始化下拉框选中。
			selReadObj.val(rPermissonType);
			selWriteObj.val(wPermissonType);
			selRequiredObj.val(bPermissonType);
			
			//是否显示选中的人员或岗位等信息。
			var spanReadObj=$("span[name='r_span']",trObj);
			var spanWriteObj=$("span[name='w_span']",trObj);
			var spanRequiredObj=$("span[name='b_span']",trObj);
			//初始化是否显示选择人员
			_self.showSpan(rPermissonType,spanReadObj);
			_self.showSpan(wPermissonType,spanWriteObj);
			_self.showSpan(bPermissonType,spanRequiredObj);
			
		});
	};
	
	/**
	 * 处理下拉框change事件。
	 */
	this.handChange=function(){
		var _self=this;
		$("#fieldPermission,#tablePermission,#opinionPermission").delegate("select.r_select,select.w_select,select.b_select","change",function(){
			var obj=$(this);
			var spanObj=obj.next();
			//当用户权限类型修改时，同时修改span的显示。
			_self.showSpan(obj.val(), spanObj);
			
			var trObj=obj.parents("tr");
			var tbodyObj=obj.parents("tbody");
			//read,write
			//read,write,required
			var mode="read";
			if(obj.attr("class")=="r_select")
				mode ="read";
			else if (obj.attr("class")=="w_select")
				mode = "write";
			else if (obj.attr("class")=="b_select")
				mode = "required";
			//var mode=(obj.attr("class")=="r_select")?"read":"write";
			//获取行在表格中的索引
			var idx=tbodyObj.children().index(trObj);
			//权限类型（field,subtable,opinion)
			var permissionType=trObj.attr("type");
			var selType=obj.val();
			_self.changePermission(permissionType,idx,mode,selType,"","");
			var txtObj=$("input:text",spanObj);
			var idObj=$("input:hidden",spanObj);
			txtObj.val("");
			idObj.val("");
		});
	};
	
	/**
	 * 修改对应行的权限数据。
	 * permissionType:权限类型
	 * field:字段类型
	 * subtable:子表
	 * opinion:意见
	 * 
	 * idx:行数
	 * mode:read,write
	 * type:
	 * everyone,none,user,role,org,orgMgr,pos
	 */
	this.changePermission=function(permissionType,idx,mode,type,ids,names){
		if(idx==-1) return;
		var aryPermission=[];
		switch(permissionType){
			case "field":
				aryPermission=this.FieldsPermission;
				break;
			case "subtable":
				aryPermission=this.SubTableFilePermission;
				if(idx>0){
					idx=idx-1;   //子表的显示区域中中多了一行表名解析，所以相对的第行对应SubTableFilePermission数组的第一个元素
				}
				break;
			case "opinion":
				aryPermission=this.Opinion;
				break;
		}
		var objPermssion=aryPermission[idx];
		
		
		//alert(permissionType +"," + idx + "," + mode + "," + type +","+ ids);
		
		objPermssion[mode]["type"]=type;
		objPermssion[mode]["id"]=ids;
		objPermssion[mode]["fullname"]=names;
	};
	
	/**
	 * 处理选择人员，岗位，组织，角色点击事件。
	 */
	this.handClick=function(){
		var _self=this;
		$("#fieldPermission,#tablePermission,#opinionPermission").delegate("a.link-get","click",function(){
			var obj=$(this);
			
			var txtObj=obj.prev();   //select 对象的前一个对象
			var idObj=txtObj.prev();
			var selObj=obj.parent().prev();
			var selType=selObj.val();
			
			var callback = function(ids, names) {
				var trObj=obj.parents("tr");
				var tbodyObj=obj.parents("tbody");
				//read,write
				var mode=obj.attr("mode");
				var idx=tbodyObj.children().index(trObj);
				var permissionType=trObj.attr("type");
				
				_self.changePermission(permissionType,idx,mode,selType,ids,names);
				txtObj.val(names);
				idObj.val(ids);
			};
			
			var ids = idObj.val();
			var names = txtObj.val();
			
			switch(selType){
				case "user":
					UserDialog({
						selectUserIds:ids,
	    	        	selectUserNames:names,
						callback : callback
					});
					break;
				case "role":
					RoleDialog({
						ids:ids,
	    	        	names:names,
						callback : callback
					});
					break;
				case "org":
				case "orgMgr":
					OrgDialog({
						ids:ids,
	    	        	names:names,
						callback : callback
					});
					break;
				case "pos":
					PosDialog({
						ids:ids,
	    	        	names:names,
						callback : callback
					});
					break;
			}
		});
	};
	
	/**
	 * 是否显示选择框
	 */
	this.showSpan=function(permissionType,spanObj){
		switch(permissionType){
			case "user":
			case "role":
			case "org":
			case "orgMgr":
			case "pos":
				spanObj.show();
				break;
			case "everyone":
			case "none":
				spanObj.hide();
				break;
		}
	};
	
	/**
	 * 根据权限集合和权限类型获取权限的html，代码。
	 */
	this.getPermission=function(aryPermission,type){
		var sb=new StringBuffer();
		if(type=='subtable'){
			for(var i=0;i<aryPermission.length;i++){
				var tablePermission=aryPermission[i];	
				var fieldPermission=tablePermission.subField;
				var additionalHtml = "";
				for(var j=0;j<fieldPermission.length;j++){
					additionalHtml += this.getHtml(fieldPermission[j], type);
				}
				tablePermission.additionalHtml = additionalHtml;
				var template = $('#subtableTemplate').text();
				var subTableHtml = easyTemplate(template,tablePermission);
				
				sb.append(subTableHtml);
			}
		}else{
			for(var i=0;i<aryPermission.length;i++){
				var str=this.getHtml(aryPermission[i], type);
				sb.append(str);
			}
		}
		return sb.toString();
	};
	
	
	/**
	 * 由子表权限解析并获取子表字段的权限并放入subTableFilePermission。
	 */
	this.setSubTableFilePermission=function(tables){
		//将字表的字段权限入到_self.SubTableFilePermission数组
		for(var i=0;i<tables.length;i++){
			var tablePermission=tables[i];
			var fieldPermission=tablePermission.subField;
			for(var j=0;j<fieldPermission.length;j++){
				var objPermission=fieldPermission[j];
				this.SubTableFilePermission.push(objPermission);
			}
		}
	};
	
	
	
	/**
	 * 根据权限对象和权限类型（字段，子表，意见）获取一行的显示。
	 */
	this.getHtml=function(permission,type){
		var tdTemplate = $('#tdTemplate').text();
		var tdData = {right:'r' ,permission:permission}; // 只读模板
		var readHtml = easyTemplate(tdTemplate.replaceAll('#rightName', 'read'),tdData).toString();
		tdData.right = 'w'; // 编辑模板
		var writeHtml = easyTemplate(tdTemplate.replaceAll('#rightName', 'write'),tdData).toString();
		tdData.right = 'b'; // 必填模板
		var requiredHtml = easyTemplate(tdTemplate.replaceAll('#rightName', 'required'),tdData).toString();
		var data = {"permission":permission, "type":type, "requiredHtml":requiredHtml, 
				"readHtml":readHtml, "writeHtml":writeHtml};
		var fieldTemplate = $('#fieldPermissionTemplate').text();
		if(permission.controlType == 12 && permission.menuRight){//office控件
			//将obj转为字符串
			permission.menuRight = JSON2.stringify(permission.menuRight).replaceAll("\"", "'");
		}
		var fieldHtml = easyTemplate(fieldTemplate, data).toString();
		return fieldHtml;
	};
	
	/**
	 * 获取权限的json字符串。
	 */
	this.getPermissionJson=function(){
		var fieldJson={field:this.FieldsPermission,subtable:this.SubTableFilePermission,opinion:this.Opinion,subTableShows:this.subTableShows};
		var jsonStr=JSON2.stringify(fieldJson);
		return jsonStr;
	};
	
	/**
	 * 添加权限。
	 */
	this.addPermission=function(name,memo,aryPermission){
		var rtn=this.isPermissionExist(name, aryPermission);
		if(!rtn) {
			var obj=this.getDefaultPermission(name, memo);
			aryPermission.push(obj);
			return true;
		}
		return false;
	};
	
	/**
	 * 判断权限在集合中已经存在。
	 */
	this.isPermissionExist=function(name,aryPermission){
		for(var i=0;i<aryPermission.length;i++){
			var obj=aryPermission[i];
			var tmp=obj.title.toLocaleLowerCase();
			name=name.toLocaleLowerCase();
			if(tmp==name){
				return true;
			}
		}
		return false;
	};
	
	/**
	 * 根据名称获取权限。
	 */
	this.getPermissionByName=function(name,aryPermission){
		for(var i=0;i<aryPermission.length;i++){
			var obj=aryPermission[i];
			var tmp=obj.title.toLocaleLowerCase();
			name=name.toLocaleLowerCase();
			if(tmp==name){
				return obj;
			}
		}
		return null;
	};
	
	/**
	 * 添加意见权限。
	 */
	this.addOpinion=function(formName,name){
		formName=formName.replace(/opinion:/g,'');
		var rtn=this.addPermission(formName,name, this.Opinion);
		//意见权限。
		var opinionHtml=this.getPermission(this.Opinion,"opinion");
		$("#opinionPermission").empty().append(opinionHtml);
		this.initStatus("opinionPermission");
		return rtn;
	};
	
	/**
	 * 替换意见权限。
	 * title:"",memo:""
	 */
	this.replaceOpinion=function(originName,curName,curMemo){
		var obj=this.getPermissionByName(originName,this.Opinion);
		obj["title"]=curName;
		obj["memo"]=curMemo;
		var opinionHtml=this.getPermission(this.Opinion,"opinion");
		$("#opinionPermission").empty().append(opinionHtml);
		this.initStatus("opinionPermission");
	};
	
	/**
	 * 同步意见列表
	 * arry意见列表
	 */
	this.syncOpinion=function(arry){
		if(arry.length==0){
			this.Opinion=[];
		}
		else{
			for(var i=0;i<this.Opinion.length;i++){
				var tmp=0,title=this.Opinion[i].title.toLocaleLowerCase();	
				for(var j=0;j<arry.length;j++){
					var name=$(arry[j]).attr("name").toLocaleLowerCase().replace(/opinion:/g,'');
					if(title!=name){					
						this.addOpinion(name,$(arry[j]).attr("opinionname"));
					}else{
						tmp=1;
						arry.splice(j, 1);
					}
				}
				if(tmp==0){
					this.Opinion.splice(i,1);
				}
			}
		}
		if(this.Opinion.length!=0){
			var opinionHtml=this.getPermission(this.Opinion,"opinion");
			$("#opinionPermission").empty().append(opinionHtml);
			$("#opinionPermission").closest('table').show();
			this.initStatus("opinionPermission");
		}
	};	
	
	/**
	 * 重新设置权限。
	 */
	this.setAllPermission=function(){
		this.readPermission(this.FieldsPermission,"field");
		this.readPermission(this.SubTablePermission,"subtable");
		this.readPermission(this.Opinion,"opinion");
	};
	
	//用区域分段读取权限
	this.readPermission=function(aryPermission,type){
		if(type=='field'){
	    	this.FieldsPermission = this.readPermissionByObj("#fieldPermission",type);
		}else if(type=='subtable'){
			var tables = aryPermission;        //多个子表
			//每个子表是否显示数组：
			this.subTableShows = this.readTablePermission(tables,type);	
			for(var i=0;i<tables.length;i++){
					var tablePermission=tables[i];				
					//子表每子段的数据内容：
					var id = "#tableId_"+tablePermission.tableId
					var arry = this.readPermissionByObj(id,type);  
					if(i==0){
						this.SubTableFilePermission = arry; //添加第一个子表时，将覆盖原来的数据
					}else{
						for(var j=0;j<arry.length;j++){
							this.SubTableFilePermission.push(arry[j])  //其它的子表的数据则直接在的数组追加 (或者合并)
						}
					}
			}
		}else{
			this.Opinion = this.readPermissionByObj("#opinionPermission",type);
		}
		
	};
	
	//读取每一个字段的权限，并放入对应的数组里面  
	this.readPermissionByObj=function(scope,type){
		var objScope=$(scope);
		var aryPermission = []; // 赋值为一个空数组 
		$("tr",objScope).each(function(index){
			if(type=="subtable"&&index==0){   //子表的第一行是子表的表名（子表的相关信息）
				return true;             // each 方法中 return false时相当于break, 如果return true 就相当于continure。
			}
			var trObj=$(this);
			var tableId=trObj.attr("tableId");
			var mainTableId=trObj.attr("mainTableId");
			var tableName=trObj.attr("tableName");
			var mainTableName=trObj.attr("mainTableName");
			
			//var memo=trObj.children().first().text();		
			var memo=$(".mySpan",trObj.children().first())[0].firstChild.data;	
			var rSelectObj=$(".r_select",trObj);
			var rSpanObj=$("[name=r_span]",trObj);
			var rId=$("input:hidden",rSpanObj).val();
			var rFullName=$("input:text",rSpanObj).val();
			
			var wSelectObj=$(".w_select",trObj);
			var wSpanObj=$("[name=w_span]",trObj);
			var wId=$("input:hidden",wSpanObj).val();
			var wFullName=$("input:text",wSpanObj).val();
			
			var bSelectObj=$(".b_select",trObj);
			var bSpanObj=$("[name=b_span]",trObj);
			var bId=$("input:hidden",bSpanObj).val();
			var bFullName=$("input:text",bSpanObj).val();
			
			var rpostInput = $("[name=rpost]",trObj);
			var rpost = false;
			if(rpostInput.length>0){
				rpost = rpostInput.is(":checked");	
			}
			
			//读取office控件的菜单设置
			var controlType = trObj.attr("controlType");
			var menuRight = "";
			if(controlType==12){
				menuRight = $("a.officeMenu",trObj).attr("menuRight");
			}
			
			var fieldName=rSelectObj.attr("name");
			
			var permission={
					"title":fieldName,"memo":memo,"tableId":tableId,"mainTableId":mainTableId,
					"tableName":tableName,"mainTableName":mainTableName,
					"read": {"type": rSelectObj.val() ,"id":rId, "fullname":rFullName},
					"write":{"type":wSelectObj.val(),"id":wId, "fullname":wFullName},
					"required":{"type":bSelectObj.val(),"id":bId, "fullname":bFullName},
					"rpost":rpost,"controlType":controlType
					};
			
			//添加入控件菜单权限
			if(typeof(menuRight)!=undefined&&menuRight!=''&&menuRight!='undefined'){
				permission.menuRight = menuRight;
			}
			
			aryPermission.push(permission);			
		});
		return aryPermission;
	};
	
	//读取每个子表的（radio）是否显示的选择，并放入对应的数组里面；  
	this.readTablePermission=function(tables,type){
		var aryPermission = []; // 赋值为一个空数组 
		for ( var cn = 0; cn < tables.length; cn++) {
        	var table=tables[cn];
        	var scope = "#tableId_"+table.tableId;
    		var objScope=$(scope);
    		var show = {};
    		$('input:checkbox[name^="checkbox_"]',objScope).each(function(){
    			var value = $(this).val();
    			show[value] = $(this).attr('show');
    		});
    		var permission={
				"title":table.title,"memo":table.memo,"tableId":table.tableId,"mainTableId":table.mainTableId,
				"tableName":table.tableName,"mainTableName":table.mainTableName,
				"show":show
			};
    		
    		aryPermission.push(permission);
		}		
		return aryPermission;
	}
	
};


//OFFICE控件菜单选择
function selectOfficeMenu(obj){
	if(!obj){
		return;
	}
	var conf = {};
	var menuRight = $(obj).attr("menuRight");
	if(typeof(menuRight)!=undefined&&menuRight!=''&&menuRight!='undefined'){
		conf =  eval("("+menuRight+")");
	}
	var url=__ctx + '/platform/form/bpmFormDef/selectOfficeMenu.ht';
	url=url.getNewUrl();
	/*KILLDIALOG*/
	DialogUtil.open({
		height:350,
		width :450,
		title : 'OFFICE控件菜单选择',
		url: url, 
		isResize: true,
		//自定义参数
		conf: conf,
		sucCall:function(rtn){
			if(rtn){
				var jsonStr = JSON2.stringify(rtn);
				$(obj).attr("menuRight",jsonStr);
			}
		}
	});
}
function changeSubTableRight(obj){
	var result = changeCheckbox(obj);
	if(!result) return;
	$(obj).siblings("input:checkbox[value='addRow']").attr('checked', true);
	$(obj).siblings("input:checkbox[value='addRow']").attr('show', 'true');
}

function changeCheckbox(obj){
	var isChecked = $(obj).attr('checked');
	hideSubTable(obj, isChecked);
	if(!isChecked) {
		$(obj).attr('show', 'false');
		return false;
	}
	$(obj).attr('show', 'true');
	return true;
}

function hideSubTable(obj, isChecked){
	var value = $(obj).attr('value');
	if(value != 'y') return;
	if(isChecked) {
		$(obj).siblings('input').removeAttr('checked');
		$(obj).siblings('input').attr('show', 'false');
	}
}