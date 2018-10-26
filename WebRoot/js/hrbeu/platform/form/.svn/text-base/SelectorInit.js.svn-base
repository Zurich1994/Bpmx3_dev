$(function(){
	handSelector();
});

/**
 * 初始化选择器，根据class构建选择器HTML
 */
function init(){
	$('[ctlType="selector"]').each(function(){
		var type = $(this).attr('class');
		buildSelector($(this), type);
	});
}

var selectField = $('<a href="javascript:;" class="link" name="" >选择</a>');
var resetField = $('<a href="javascript:;" class="link reset" name="" >重置</a>');
var hiddenField = $('<input name="" type="hidden" lablename="" class="hidden" value="" >');

/**
 * 组建选择器
 * obj：将在此对象之前添加隐藏域，在其之后添加选择、重置按钮
 * className：选择器的class属性值
 */
function buildSelector(obj, className){
	var self = $(obj);
	self.wrap('<div></div>');
	var name = self.attr('name');
	var lablename = self.attr('lablename');
	var initvalue = self.attr('initvalue');
	var selectObj = selectField.clone(true);
	var hiddenObj = hiddenField.clone(true);
	var resetObj = resetField.clone(true);
	hiddenObj.attr('name',name+'ID');
	hiddenObj.attr('lablename',lablename+'ID');
	if(initvalue && initvalue!=''){
		hiddenObj.attr('value',initvalue);
	}
	self.before(hiddenObj);
	
	resetObj.attr('name',name);
	self.after(resetObj);
	
	selectObj.attr('name',name);
	selectObj.addClass(className);
	self.after(selectObj);
}

/**
 * 显示选择器对话框。
 * obj 按钮控件
 * fieldName 字段名称
 * type :选择器类型。
 * 1.人员选择器（单选）
 * 2.人员选择器（多选）
 * 3.角色选择器（单选）
 * 4.角色选择器（多选）
 * 5.组织选择器（单选）
 * 6.组织选择器（多选）
 * 7.岗位选择器（单选） 
 * 8.岗位选择器（单选）
 */
function handSelector(){
	// 初始化选择器
	init();
	
	//1.人员选择器（单选）
	$("body").delegate("a.link.user", "click",function(){
		selector($(this),1);
	});
	//2.人员选择器（多选）
	$("body").delegate("a.link.users", "click",function(){
		selector($(this),2);
	});
	//3.角色选择器（单选）
	$("body").delegate("a.link.role", "click",function(){
		selector($(this),3);
	});
	//4.角色选择器（多选）
	$("body").delegate("a.link.roles", "click",function(){
		selector($(this),4);
	});
	//5.组织选择器（单选）
	$("body").delegate("a.link.org", "click",function(){
		selector($(this),5);
	});
	// 6.组织选择器（多选）
	$("body").delegate("a.link.orgs", "click",function(){
		selector($(this),6);
	});
	//7.岗位选择器（单选） 
	$("body").delegate("a.link.position", "click",function(){
		selector($(this),7);
	});
	//8.岗位选择器（单选）
	$("body").delegate("a.link.positions", "click",function(){
		selector($(this),8);
	});
	//9.流程实例选择器（多选）
	$("body").delegate("a.link.actInsts", "click",function(){
		selector($(this),9);
	});
	//重置选择器的值
	$("body").delegate("a.link.reset", "click",function(){
		var obj = $(this);
		var fieldName=obj.attr("name");
		var parent=obj.parent();
		var idFilter="input[name='"+fieldName+"ID']";
		var nameFilter="input[name='"+fieldName+"']";
		var inputId=$(idFilter,parent);
		var inputName=$(nameFilter,parent);
		inputId.val("");
		inputName.val("");
		inputName.removeAttr("refid");
		
	});
};


/**
 * 触发选择器 obj 按钮控件对象 type :选择器类型。
 */
function selector(obj, type) {
	var fieldName = obj.attr("name"), 
		parent = obj.parent(), 
		idFilter = "input[name='"+ fieldName + "ID']", 
		nameFilter = "input[name='" + fieldName+ "']", 
		linkFielter = "a[name='" + fieldName + "ID']", 
		inputId = $(idFilter, parent), 
		inputName = $(nameFilter, parent), 
		link = $(linkFielter, parent), 
		oldIdVal = inputId.val(), 
		idStr = inputId.val(), 
		nameStr = inputName.val(), 
		arguments = [];

	if (idStr) {
		var ids = idStr.split(','), names = nameStr.split(','), size = ids.length;
		for (var i = 0; i < size; i++) {
			arguments.push({
						id : ids[i],
						name : names[i]
					});
		}
	}

	switch (type) {
		// 人员选择器(单选)
		case 1 :
			UserDialog({
						isSingle : true,
						selectUsers:arguments,
						callback : function(ids, names) {
							if (inputId.length > 0) {
								inputId.val(ids);
							};
							inputName.val(names);
						}
					});
			break;
		// 人员选择器(多选)
		case 2 :
			UserDialog({
						isSingle : false,
						selectUsers:arguments,
						callback : function(ids, names) {
							if (inputId.length > 0) {
								inputId.val(ids);
							};
							inputName.val(names);
						}
					});
			break;
		// 3.角色选择器(单选)
		case 3 :
			RoleDialog({
						isSingle : true,
						callback : function(ids, names) {
							if (inputId.length > 0) {
								inputId.val(ids);
							};
							inputName.val(names);
						}
					});
			break;
		// 4.角色选择器（多选）
		case 4 :
			RoleDialog({
						arguments:arguments,
						callback : function(ids, names) {
							if (inputId.length > 0) {
								inputId.val(ids);
							};
							inputName.val(names);
						}
					});
			break;
		// 5.组织选择器(单选)
		case 5 :
			OrgDialog({
						isSingle : true,
						callback : function(ids, names) {
							if (inputId.length > 0) {
								inputId.val(ids);
							};
							inputName.val(names);
						}
					});
			break;

		// 6.组织选择器（多选）
		case 6 :
			OrgDialog({
						arguments:arguments,
						callback : function(ids, names) {
							if (inputId.length > 0) {
								inputId.val(ids);
							};
							inputName.val(names);
						}
					});
			break;

		// 岗位选择器(单选)
		case 7 :
			PosDialog({
						isSingle : true,
						callback : function(ids, names) {
							if (inputId.length > 0) {
								inputId.val(ids);
							};
							inputName.val(names);
						}
					});
			break;
		// 岗位选择器（多选）
		case 8 :
			PosDialog({
						arguments:arguments,
						callback : function(ids, names) {
							if (inputId.length > 0) {
								inputId.val(ids);
							};
							inputName.val(names);
						}
					});
			break;
		// 流程引用选择器（多选）
		case 9 :
			var defId = $("[name='defId']").val();
			if (!defId) {
				defId = 0;
			}
			ActInstDialog({
						defId : defId,
						isSingle : 2,
						arguments : arguments,
						callback : function(data) {
							if (!data) {
								return;
							}
							if (inputId.length > 0) {
								inputId.val(data.ids);
							};
							inputName.val(data.names);
							inputName.attr("refid", data.ids);
						}
					});
	}
	inputName.trigger("blur");
	if (inputId.val() != oldIdVal) {
		inputId.trigger("change");
		inputName.trigger("change");
	}
};
