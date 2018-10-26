if (typeof ColumnDialogOption == 'undefined') {
  ColumnDialogOption = {};
}

$(function(){
  $("#option-table>tbody").delegate('tr.noramlTr','mouseover mouseleave',function(e){
    if(e.type=='mouseover'){
      $(this).addClass('hover');  
    }else{
      $(this).removeClass('hover'); 
    }
  });
  $("#option-table>tbody").delegate('tr.noramlTr','click',function(e){
    var me = $(this),
      moreTr = $("#moreInfo"),
      hiddenTable = $("#hiddenTable"),
      nextId = me.next().attr("id"),
      curMe = null;
      
    if(nextId=='moreInfo'){
      curMe = me;
      if(curMe)
        ColumnDialogOption.saveData(curMe,moreTr);
      hiddenTable.append(moreTr);
    }
    else{
      var preTr = moreTr.prev();
      if(preTr.hasClass('noramlTr'))
        curMe = preTr;
      var data = me.data('resData');
      if(curMe)
        ColumnDialogOption.saveData(curMe,moreTr);
      ColumnDialogOption.displayData(data,moreTr);
      moreTr.insertAfter(me);
    }
    ColumnDialogOption.stopBubble(e);
  });
  $("#option-table>tbody").delegate('a.link.del','click',function(e){
    ColumnDialogOption.stopBubble(e);
    if(confirm('确认删除？')){
	    var me = $(this),           
	      tr = me.parents('tr.noramlTr'),
	      nextId = tr.next().attr("id");
	    if(nextId=='moreInfo'){
	      $("#hiddenTable").append($("#moreInfo"));
	    }
	    tr.remove();
    }
  });
    //上移
   $("#option-table>tbody").delegate('a.link.moveup','click',function(e){
     	 ColumnDialogOption.stopBubble(e);
     	 ColumnDialogOption.move($(this),true);
   });
   //下移
   $("#option-table>tbody").delegate('a.link.movedown','click',function(e){
     	 ColumnDialogOption.stopBubble(e);
     	 ColumnDialogOption.move($(this),false);
   });
  
  //新增  
  $("a.add").click(function(){
    var data = {key:"",value:[]},
      newTr = ColumnDialogOption.genDescript(data);

    var tbody = $('#option-table>tbody');
    tbody.append(newTr);
  });
});


/**
 * 上下移动
 * @param {} obj 移动的对象
 * @param {} flag 上移 true,下移 false
 */
ColumnDialogOption.move = function(obj,flag){         
	    var  trObj = obj.parents('tr.noramlTr');
	if(flag){
		var prevObj=trObj.prev();
		if(prevObj.length>0){
			trObj.insertBefore(prevObj);	
		}
	}else{
		var nextObj=trObj.next();
		if(nextObj.length>0){
			trObj.insertAfter(nextObj);
		}
	}
}

/**
 * 终止事件冒泡
 * @param  {[type]} e [description]
 * @return {[type]}   [description]
 */
ColumnDialogOption.stopBubble = function(e){
  if(e && e.stopPropagation)
    e.stopPropagation();
  else
    window.event.cancelBubble = true;
};

/**
 * 初始化数据
 * @param  {[json array]} json [json数组]
 * @return {[type]}      [description]
 */
ColumnDialogOption.init = function(json){
  if(!json)return;
  var tbody = $('#option-table>tbody');
  for(var i=0,c;c=json[i++];){
    var newTr = ColumnDialogOption.genDescript(c);
    tbody.append(newTr);
  }
};

/**
 * 显示数据
 * @param  {[json]} data [数据]
 * @return {[type]}      [description]
 */
ColumnDialogOption.displayData = function(data,tr){
  var value = data.value;
  $("input[name='key']",tr).val(data.key);
  $("input.long",tr).each(function(){
    var me = $(this).val(''),
      name = me.attr("name");
    for(var i=0,c;c=value[i++];){
      if(c.lantype==name){
        me.val(c.lanres);
      }
    }
  });
};

/**
 * 保存数据
 * @param  {[json]} data [数据]
 * @return {[type]}      [description]
 */
ColumnDialogOption.saveData = function(oldTr,moreTr){
  var key = $("input[name='key']",moreTr).val(),
    value = [],
    json = oldTr.data('resData');

  $("input.long",moreTr).each(function(){
    var me = $(this),
      name = me.attr("name"),
      val = me.val(),
      memo = me.attr("title");
    value.push({lantype:name,lanres:val,lanmemo:memo});
  });
  json.key = key;
  json.value = value;

  oldTr.data('resData',json);
  oldTr.before(ColumnDialogOption.genDescript(json));
  oldTr.remove();
  return json;
};

/**
 * 生成描述文字
 * @param  {[json]} data [数据]
 * @return {[type]}      [description]
 */
ColumnDialogOption.genDescript = function(data){
  var val = ColumnDialogOption.getSelectValue(data.value),
    key = data.key;

  var valSpan = $('<span></span>').html(val).attr('title',val),
    valDiv = $('<div style="overflow:hidden;width:400px;"></div>').append(valSpan),
    fTd = $('<td></td>').append(valDiv),
    keySpan = $('<span></span>').html(key).attr('title',key),
    removeBnt = $('<a href="javascript:;" class="link moveup"></a><a href="javascript:;" class="link movedown"></a><a href="javascript:;" class="link del"></a>'),
    keyDiv = $('<div style="width:60px;overflow:hidden;height:20px;float:left;"></div>').append(keySpan),
    removeDiv = $('<div style="width:40px;float:right;"></div>').append(removeBnt),
    div = $('<div style="width:100px;height:20px;"></div>').append(keyDiv).append(removeDiv),
    tTd = $('<td align="right"></td>').append(div),
    tr = $('<tr class="noramlTr"></tr>').append(fTd).append(tTd);

  tr.data('resData',data);
  return tr;
};

/**
 * 获取选项值
 */
ColumnDialogOption.getSelectValue = function(val){
  if(!val||val.length==0)return '';
  var str = [];
  if(val.length==0)return str;
  for(var i=0,c;c=val[i++];){
    str.push('['+c.lanmemo + ']' + c.lanres);
  }
  return str.join('  ');
};

/**
 * 获取选项值
 */
ColumnDialogOption.reset = function(){
  $("tr.noramlTr").remove();
};

/**
 * 获取数据
 */
ColumnDialogOption.getData = function(){
  var opts = [];
  $("tr.noramlTr").each(function(){
    var me = $(this),           
      nextId = me.next().attr("id"),
      data = {};
    if(nextId=='moreInfo'){
      data = ColumnDialogOption.saveData(me,$("#moreInfo"));
    }
    else{
      data = me.data("resData");
    }
    opts.push(data);
  });
  return opts;
};