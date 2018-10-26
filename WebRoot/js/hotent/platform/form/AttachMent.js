/**
 * 附件管理。
 * @returns {AttachMent}
 */
if (typeof AttachMent == 'undefined') {
	AttachMent = {};
}
/**
 * 选择非直接上传附件时判断用flash还是html
 */
AttachMent.addFile=function(obj){
	var uploadType ;
	$.ajax({
		type : "get",
		async : false,
		dataType : "json",
		url : __ctx + "/platform/system/sysFile/uploadType.ht",
		success : function(data) {
			uploadType =data.uploadType;
		},
		error : function(msg) {
			alert(msg);
		}
	});
	if(uploadType=="flash"){
		AttachMent.FlexAddFile(obj);
	}else{
		AttachMent.htmlUpLoadFile(obj);
	}
}

/**
 * 选择直接上传附件时判断用flash还是html
 */
AttachMent.directUpLoadFile=function(obj){
	var uploadType ;
	$.ajax({
		type : "get",
		async : false,
		dataType : "json",
		url : __ctx + "/platform/system/sysFile/uploadType.ht",
		success : function(data) {
			uploadType =data.uploadType;
		},
		error : function(msg) {
			alert(msg);
		}
	});
	if(uploadType=="flash"){
		AttachMent.directUpLoad(obj);
	}else{
		AttachMent.htmlUpLoadFile(obj);
	}
}

/**
 * flash附件非直接上传
 */
AttachMent.FlexAddFile=function(obj){	
	var inputObj=$(obj);
	var fieldName=inputObj.attr("field");
	var parent=inputObj.parent().parent();

	var rights="w";
	var divName="div.attachement";
	var inputName="input[name='" +fieldName +"'],textarea[name='" +fieldName +"']";
	//获取div对象。
	var divObj=$(divName,parent);
	var inputJson=$(inputName,parent);
	
	var aryJson=AttachMent.getFileJsonArray(divObj);
	//文件选择器
	FlexUploadDialog({isSingle:false,callback:function (fileIds,fileNames,filePaths,extPaths){
		if(fileIds==undefined || fileIds=="") return ;
		var aryFileId=fileIds.split(",");
		var aryName=fileNames.split(",");
		var aryExtPath=extPaths.split(",");
	
		for(var i=0;i<aryFileId.length;i++){
			var name=aryName[i];
			AttachMent.addJson(aryFileId[i],name,aryJson);
		}
		//获取json
		var json=JSON2.stringify(aryJson);		
		var html=AttachMent.getHtml(aryJson,rights);
		divObj.empty();
		divObj.append($(html));
		inputJson.val(json);
		if(typeof CustomForm != "undefined"){
			CustomForm.validate();
		}
		
	}});
};
/**
 * 直接附件上传
 */
AttachMent.directUpLoad=function(obj){
	var inputObj=$(obj);
	var fieldName=inputObj.attr("field");
	var parent=inputObj.parent().parent();
	var rights="w";
	var divName="div.attachement";
	var inputName="input[name='" +fieldName +"'],textarea[name='" +fieldName +"']";
	//获取div对象。
	var divObj=$(divName,parent);
	var inputJson=$(inputName,parent);
	
	var aryJson=AttachMent.getFileJsonArray(divObj);
	//文件上传
	DirectUploadDialog({callback:function (attachs){
		if(attachs==undefined || attachs==[]) return ;
		for(var i=0;i<attachs.length;i++){
			var fileId=attachs[i].fileId;
			var name=attachs[i].fileName;
			AttachMent.addJson(fileId,name,aryJson);
		}
		//获取json
		var json=JSON2.stringify(aryJson);		
		var html=AttachMent.getHtml(aryJson,rights);
		divObj.empty();
		divObj.append($(html));
		inputJson.val(json);
		if(typeof CustomForm != "undefined"){
			CustomForm.validate();
		}
	}});
};

/**
 * html附件上传dialog
 * @param conf
 */

AttachMent.htmlUpLoadFile=function(obj){	
	var inputObj=$(obj);
	var fieldName=inputObj.attr("field");
	var parent=inputObj.parent().parent();
	var divName="div.attachement";
	var rights="w";
	var inputName="input[name='" +fieldName +"'],textarea[name='" +fieldName +"']";
	//获取div对象。
	var divObj=$(divName,parent);
	var inputJson=inputObj.prev();//获取到textarea对象
	
	var aryJson=AttachMent.getFileJsonArray(divObj);
	//文件选择器
	HtmlUploadDialog({callback:function (aryFileId,aryName){
		if(aryFileId==undefined || aryFileId=="") return ;
		for(var i=0;i<aryFileId.length;i++){
			var name=aryName[i];
			AttachMent.addJson(aryFileId[i],name,aryJson);
		}
		
		var json=JSON2.stringify(aryJson);		
		var html=AttachMent.getHtml(aryJson,rights);
		divObj.empty();
		divObj.append($(html));
		inputJson.val(json);
		inputJson.text(json);
		if(typeof CustomForm != "undefined"){
			CustomForm.validate();
		}
		
	}});
};

/**
 * 删除附件
 * @param obj 删除按钮。
 */
AttachMent.delFile=function(obj){
	var inputObj=$(obj);
	var parent=inputObj.parent();
	var divObj=parent.parent();
	var spanObj=$("span[name='attach']",parent);
	var divContainer=divObj.parent();
	var fileId=spanObj.attr("fileId");
	var aryJson=AttachMent.getFileJsonArray(divObj);
	AttachMent.delJson(fileId,aryJson);
	var json=JSON2.stringify(aryJson);
	var inputJsonObj=$("textarea",divContainer);
	if(aryJson.length == 0)
		json = "";		
	//设置json
	inputJsonObj.val(json);
	//删除span
	parent.remove();
	if(typeof CustomForm != "undefined"){
		CustomForm.validate();
	}
};

/**
 * 初始化表单的附件字段数据。
 */
AttachMent.init=function(subRights,parent){
	if(	$.isEmpty(parent))
		parent = $("div[name='div_attachment_container']");
	parent.each(function(){
		var me=$(this),
			rights=me.attr("right");
		//如果没有权限属性，可能是子表中的附件
		if(!rights){
			rights=me.closest("[type='subtable']").attr("right");
		}
		//对于弹出框的处理
		if(!$.isEmpty(subRights))
			rights = subRights;	
		if(rights){
			rights=rights.toLowerCase();
		}
		if(rights!="w" && rights!="r" && rights!="b"){
			me.remove();
		}		
		else{
			if(rights=="r"){
				//$("a.attachement").remove();
				//$("a.selectFile").remove();
				$("a[field]",me).remove();
			}
			var atta =$("textarea[controltype='attachment']",me);
			var jsonStr = atta.val();
			if(!$.isEmpty(jsonStr)){
				jsonStr = jsonStr.replaceAll("￥@@￥","\"");
				atta.val(jsonStr);
			}
			var divAttachment=$("div.attachement",me);
			//json数据为空。
			AttachMent.insertHtml(divAttachment,jsonStr,rights);
		}
	});
};

/**
 *  附件插入显示
 * @param {} div
 * @param {} jsonStr 
 * @param {} rights 权限 如果不传，默认是r
 */
AttachMent.insertHtml= function(div,jsonStr,rights){
	if($.isEmpty(jsonStr)) {
		div.empty();
		return ;
	}
	if($.isEmpty(rights)) rights ='r';
	var jsonObj=[];
	try {
		jsonStr = jsonStr.replaceAll("￥@@￥","\"");
		jsonObj =	jQuery.parseJSON(jsonStr);
	} catch (e) {
	}
	var html=AttachMent.getHtml(jsonObj,rights);
	div.empty();
	div.append($(html));
};

/**
 * 获取文件的html。
 * @param aryJson
 * @returns {String}
 */
AttachMent.getHtml=function(aryJson,rights){	
	var str="";
	var template="";
	var templateW="<li style='margin-bottom: 10px;margin-top: 10px;'><span class='attachement-span'><span fileId='#fileId#' name='attach' file='#file#' ><a class='attachment' target='_blank' path='#path#' onclick='AttachMent.handleClickItem(this,\"w\")' title='#title#'>#name#</a></span><a href='javascript:;' onclick='AttachMent.download(this);' title='下载' class='download'></a>&nbsp;<a href='javascript:;' onclick='AttachMent.delFile(this);' class='cancel'></a></span></li>";
	var templateR="<li style='margin-bottom: 10px;margin-top: 10px;'><span class='attachement-span'><span fileId='#fileId#' name='attach' file='#file#' ><a class='attachment' target='_blank' path='#path#' onclick='AttachMent.handleClickItem(this,\"r\")' title='#title#'>#name#</a></span><a href='javascript:;' onclick='AttachMent.download(this);' title='下载' class='download'></a></span></li>";
	
	if(rights=="w"){
		template=templateW;
	}
	else{
		template=templateR;
	}
	for(var i=0;i<aryJson.length;i++){
		var obj=aryJson[i];
		var id=obj.id;
		var name=obj.name;
		var path =__ctx +"/platform/system/sysFile/file_" +obj.id+ ".ht";
			
		var file=id +"," + name ;
		var tmp=template.replace("#file#",file).replace("#path#",path).replace("#name#", AttachMent.parseName(name)).replace("#title#",name).replace("#fileId#", id);
		//附件如果是图片就显示到后面
		str+=tmp;
	}
	str = "<ul>"+str+"</ul>";	/*改为一个附件就占领一行*/
	return str;
};

AttachMent.parseName = function(name){
	/*if(name.length >10)
		return name.substr(0,6)+"...";*/	/*暂时去掉截取字段*/
	return name;
}

/**
 * 添加json。
 * @param fileId
 * @param name
 * @param path
 * @param aryJson
 */
AttachMent.addJson=function(fileId,name,aryJson){
	var rtn=AttachMent.isFileExist(aryJson,fileId);
	if(!rtn){
		var obj={id:fileId,name:name};
		aryJson.push(obj);
	}
};

/**
 * 删除json。
 * @param fileId 文件ID。
 * @param aryJson 文件的JSON。
 */
AttachMent.delJson=function(fileId,aryJson){
	for(var i=aryJson.length-1;i>=0;i--){
		var obj=aryJson[i];
		if(obj.id==fileId){
			aryJson.splice(i,1);
		}
	}
};

/**
 * 判断文件是否存在。
 * @param aryJson
 * @param fileId
 * @returns {Boolean}
 */
AttachMent.isFileExist=function(aryJson,fileId){
	for(var i=0;i<aryJson.length;i++){
		var obj=aryJson[i];
		if(obj.id==fileId){
			return true;
		}
	}
	return false;
};

/**
 * 取得文件json数组。
 * @param divObj
 * @returns {Array}
 */
AttachMent.getFileJsonArray=function(divObj){
	var aryJson=[];
	var arySpan=$("span[name='attach']",divObj);
	arySpan.each(function(i){
		var obj=$(this);
		var file=obj.attr("file");
		var aryFile=file.split(",");
		var obj={id:aryFile[0],name:aryFile[1]};
		aryJson.push(obj);
	});
	return aryJson;
};

AttachMent.handleClickItem = function(obj,rights){

	var _this = $(obj);
	var span = _this.closest("span");
	var fileId = span.attr("fileId");
	
	var url =__ctx+"/platform/system/sysFile/getJson.ht";
	var sysFile;
	$.ajax({
		url:url,
		data:{
			fileId:fileId
		},
		success:function(data){
			
			if(typeof(data)=="string"){
				$.ligerDialog.error('系统超时请重新登录!','提示');
				return ;
			}
			
			if(data.status!=1){
				$.ligerDialog.error(data.msg,'提示');
			}else{
				sysFile = data.sysFile;
				var path = _this.attr("path");
				if(/(doc)|(docx)|(xls)|(xlsx)|(ppt)|(pptx)/ig.test(sysFile.ext)){
					var h=screen.availHeight-35;
					var w=screen.availWidth-5;
					
					var vars="top=0,left=0,height="+h+",width="+w+",status=no,toolbar=no,menubar=no,location=no,resizable=1,scrollbars=1";
					var showUrl = __ctx+"/platform/system/sysFile/office.ht?fileId=" + fileId;
					
					if(/(doc)|(docx)/ig.test(sysFile.ext)){//word才支持在线编辑
						if(rights=='w'){
							showUrl+="&rights=w";
						}
					}else{
						showUrl+="&rights=r";
					}
					
					window.open(showUrl,"myWindow",vars);
				}else{
					window.open(path,'_blank');	
				}
			}
		}
		
		
	});
};

/**
 * 下载
 */		
AttachMent.download	= function(obj){
	var me = $(obj);
	var	span = me.siblings("span");
	if(span.length >0)
	var	fileId = span.attr("fileId");
	
	var path =__ctx+"/platform/system/sysFile/file_"+fileId+".ht?download=true";
	window.open(path,'_blank');	
}
	


