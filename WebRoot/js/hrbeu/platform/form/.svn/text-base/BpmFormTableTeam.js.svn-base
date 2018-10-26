var colstree;

/**
 * 自定义表分组。
 * @returns {BpmFormTableTeam}
 */
if (typeof BpmFormTableTeam == 'undefined') {
	BpmFormTableTeam = {};
}

/**
 * 根据表获取字段构建树。
 * 
 * @param tableId
 */
BpmFormTableTeam.getFieldsByTableId = function(tableId) {
		var iconFolder = __ctx + '/styles/tree/';
	$.post('getFieldsByTableId.ht?tableId=' + tableId, function(data) {
		var json = eval("("+data+")"),
			treeData = [];
		
		for(var i=0,c;c=json.fields[i++];){
				c.name = c.fieldDesc;
				c.id = c.fieldName;
				c.pId = 0;
				c.icon = iconFolder + c.fieldType + '.png';
				treeData.push(c);
		}
		
		
		var setting = {
				view: {
					fontCss : setFontCss
				},
   				data: {
   					key : {
   						name: "name"
   					},
   					simpleData: {
   						enable: true,
   						idKey: "id",
   						pIdKey: "pId",
						rootPId: 0
   					}
   				},
   				callback : {
   					
   					onDblClick : function(event, treeId, treeNode) { 
   						BpmFormTableTeam.addField(treeId,treeNode);
   						
   					}
   				}
   			};
		colstree = $.fn.zTree.init($("#colstree"),setting, treeData);
	});
};

/**
 * 初始化设置样式
 */
function setFontCss(treeId, treeNode) {
	if($.isEmpty(team)) return '';
	var v = eval("("+team+")"),p=[];	
	for(var i=0,c;c=v.team[i++];){
		for(var j=0,o;o=c.teamField[j++];){
			p.push(o.fieldName);
		}
	}
	if($.isEmpty(p)) return '';
	for(var i=0,c;c=p[i++];){
		if(c == treeNode.id)
			return {color:"red"};
	}
	return '';
};

//移除已选字段
function removeOpt(e){
	var me = $(e);
	var optId = me.val();
	if($.isEmpty(optId)) return;
	
	e.remove(e.selectedIndex);
	//$("option[value='"+optId+"']",me).remove(); 
	 if(colstree){
		 var node = colstree.getNodesByFilter(function(node){
			  return node.id == optId? true:false;
		 }, true);  
		 setCss(node.tId,"");
	 }
}

//设置样式
function setCss(o,t){
	if($.isEmpty(o)) return ;
	$('#'+o+'_a').css("color",t) ;
}

/**
 * 初始化解析分组
 */
function parseTeam(o){
	if($.isEmpty(o)) return;
	var v = eval("("+o+")");
	if(v.isShow){
		$('#isShow').attr("checked","checked");
		$('#showPosition').val(v.showPosition);
		$('#showPosition').show();
	}else{
		$('#isShow').attr("checked",false);
		$('#showPosition').hide();
	}
		
	for(var i=0,c;c=v.team[i++];){
		add(false,c);
	}
}

//增加分组
function add(t,o){
	var table = $($("#cloneTemplate [zone='team']")[0]).clone(true, true);

	if(!t){
		var option = [];
		for(var i=0,c;c=o.teamField[i++];){
			option.push('<option value="'+c.fieldName+'">'+c.fieldDesc+'</option>');
		}
		$("[name='teamName']", table).val(o.teamName);
		$("[name='teamField']", table).append(option.join(''));
	}
	$("[var=del]", table).click(function(e) {
		$.ligerDialog.confirm('删除当前分组设置?', '提示', function(rtn) {
				if (rtn) {
					table.fadeOut(500, function() {
								$(this).remove();
							});
				}
			});
	});
	$("#teamItem").append(table);
	
	$("select[name='teamField']",table).each(function(){
		selectTeam (this);
	});

};

function selectTeam (obj){
	var teamItem= $("#teamItem");
	$(".validError",teamItem).each(function(){
		$(this).removeClass('validError');	
		$(this).closest("table").css("border","")
	});
	$(obj).addClass('validError');
	$(obj).closest("table").css("border","1px solid red")	
}

/**
 * 增加字段
 */
BpmFormTableTeam.addField = function(treeId,treeNode) {
	var id = treeNode.id,name= treeNode.name,selectTeam = $(".validError"),flag =false;
	$("#teamItem select[name='teamField'] option").each(function(){
		if(id == $(this).val())
			flag = true;
	});
	if(flag){
		alert('该字段已经添加分组,请先移除再添加!');
		return;
	}
	if(!selectTeam||selectTeam.length==0){
		alert('请选择要添加的分组！');
		return;
	}
	__SelectOption__.add(selectTeam.get(0),name, id);
	 setCss(treeNode.tId,"red");
}


/**
 * 保存数据
 */
function saveData(){
	var rtn = valid.valid();
	if(!rtn) return false;	
	$('.validError').removeClass('validError');
	var team = [],json= {};
	$("#teamItem .table-detail").each(function(){
		var me = $(this),data = {},teamField=[];
		data.teamName = $("input[name='teamName']",me).val();
		me.find("select[name='teamField'] option").each(function(){
			var o = {};
			o.fieldName = $(this).val();
			o.fieldDesc = $(this).text();
			teamField.push(o);
		});
		
		if(!teamField||teamField.length==0)
			rtn = false;
		
		data.teamField = teamField;
		team.push(data);
	});
	if(!rtn){
		alert('请选择分组的字段！');
		return false;	
	}
	json.isShow = $('#isShow').is(":checked");
	json.showPosition = $('#showPosition').val();
	json.team = team;
	json = JSON2.stringify(json);
	var url = __ctx + "/platform/form/bpmFormTable/teamSave.ht"; 
	$.post(url,{tableId:tableId,json:json},function(d){
		var result = eval("("+d+")");		
		if(result.success)
			$.ligerDialog.success(result.msg,'提示',function(){
				window.close();	
			});
		else
			$.ligerDialog.error(result.msg,'出错了!');
	});
}