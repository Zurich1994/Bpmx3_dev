var grid;
var id;
var rowindex = 0;
var columns = []; //所有的列表names
var hideColumns = []; //所有的列表names
var disabledColumns = []; //所有只显示却不能编辑的列 -》》需要解决这种不能编辑的列的值保存不成功的问题。
var disabledColumnsDatas = {}; //所有只显示却不能编辑的列 -》》需要解决这种不能编辑的列的值保存不成功的问题。
var currentEditData; //当前编辑行的数据
var innerEdit = true; //页内编辑是否
var innerAdd = false; //页内编辑是否
var pageBean = {}; // 分页对象
var needToolbar = true; //设置是否需要操作栏
var newRows=[];//新增添并且没有保存的数据
$(function() {
    $("div.groupUI > a.link.del").unbind("click");
    $("div.groupUI > a.link.search").unbind("click");
    //删除操作
    $("div.groupUI > a.link.del").click(delFunction);
    //查询操作
    $("div.groupUI > a.link.search").click(function() {
        if (!$(this).hasClass('disabled')) {
        	var queryDatas={};
        	grid.options.isAdding = false;
            $(".inputText").each(function(index, item) {
                queryDatas[$(item).attr("name")] = $(item).val();
            });
            grid.options.parms=$.extend(true, {}, queryDatas);
            queryDatas.page = 1;
            queryDatas.pagesize = pageBean["pageSize"];
            var serachAction = $("#searchForm").attr("action");
            $.post(serachAction, queryDatas, 
            function(response) {
            	grid.options['newPage']=1;
                grid._getSearchData(response);
            })
        }
    });
});

function initData(context) {
    //通过context.columns 获取当前的显示的columns、隐藏的hideColumns和不能编辑的disabledColumns列。
    for (var column in context.columns) {
        if (!context.columns[column]["hide"]) {
            columns.push(context.columns[column]["name"]);
        } else {
            var hideCol = context.columns[column]["name"]
            hideColumns.push(hideCol);
        }
        if (!context.columns[column]["editor"]) {
            disabledColumns.push(context.columns[column]["name"]);
        }
    }
    innerEdit = context.innerEdit;
    innerAdd = context.innerAdd;
    needToolbar = context.needToolbar;
    //计算列宽
    var columnWidth = Math.ceil((document.body.clientWidth) / (columns.length)) - 8;
    if (needToolbar) {
        //如果需要操作栏 需要减去操作栏的列宽在进行计算
        columnWidth = Math.ceil((document.body.clientWidth - 200) / (columns.length)) - 8;
        context.columns.push({
            display: '操作',
            isSort: false,
            isAllowHide: false,
            width: 200,
            render: function(rowdata, rowindex, value) {
                var h = "";
                //操作栏的显示按钮 可以进行扩展
                if (!rowdata._editing) {
                    h += "<a href='javascript:beginEdit(" + rowindex + "," + rowdata.id + ")'>修改</a> ";
                    h += "<a class='link del' href='javascript:delFunction(" + rowindex + "," + rowdata.id + ")'>删除</a> ";
                } else {
                    h += "<a href='javascript:submitChange(" + rowindex + "," + rowdata.id + ")'>提交</a> ";
                    h += "<a href='javascript:cancelEdit(" + rowindex + ")'>取消</a> ";
                }
                return h;
            }
        });
    }
    grid = $("#grid").ligerGrid({
        columns: context.columns, //展现的字段
        usePager: true, //是否用分页
        url: "getList.ht", //url  这里使用url 那么dataAction需要设为'server'
        root: "Rows", //json数据的开头  不需要修改
        record: 'Total', //总数的开头  不需要修改
        dataAction: 'server', //数据从服务器获取
        pageSize: 10, //当前页的显示数据大小
        pageSizeOptions :[5,10,15,20],
        width: 'auto', //设置宽度
        heightDiff: -10, //设置高度调整，当height为100%时可能会超过屏幕。
        checkbox: true, //是否显示复选框
        rownumbers: true, //是否显示行序号
        columnWidth: columnWidth, //默认列宽度
        rowHeight: 35, //行默认的高度
        enabledSort: true, //是否允许排序
        enabledEdit: true, //是否允许编辑
        clickToEdit: false, //是否允许 单击进行单元格编辑
        dateFormat: "yyyy-MM-dd", //默认时间显示格式
        fixedCellHeight: true, //是否固定单元格的高度
        colDraggable: false,
        allowAdjustColWidth: 0,
        onCheckRow: function(checked, data, rowid, rowobj) {
            if (checked) {
                id = data.id;
                rowindex = data.__index;
            } //选中之后触发的事件  这里锁定id和rowindex
        },
        onAfterSubmitEdit: function(e) {
            currentEditData = e;
            for (var id in disabledColumns) {
                if (!isInArray(hideColumns, disabledColumns[id])) {
                    currentEditData.newdata[disabledColumns[id]] = disabledColumnsDatas[disabledColumns[id]];
                    currentEditData.record[disabledColumns[id]] = disabledColumnsDatas[disabledColumns[id]];
                }
            }
        },
        onLoaded: function(grid) {
            grid.gridloading['hide']();
        },
        onEndEdit: function(e) {
            console.info("onEndEdit");
        }
    });
    
    //判断是否需要进行页内添加
    innerAdd=true;
    if(innerAdd||columns.length<=9){
	 //添加操作
	    $("div.groupUI > a.link.add").unbind("click");
	    $("div.groupUI > a.link.add").click(function(){
	    	grid.options.isAdding = true;
	    	grid.currentData.Rows=[];
	    	newRows.push(grid.addRow());
	    	for(var index=0;index<newRows.length;index++){
	    		grid.beginEdit(newRows[index]);
	    	}
	    });
    }else{
    	$("div.groupUI > a.link.add").attr("href",$("div.groupUI > a.link.add").attr("action"));
    }
    
    //判断是否需要进行页内编辑
    $("a.link.update").unbind("click");
    $("div.groupUI > a.link.update").click(function() {
        if ($(this).hasClass('disabled'))
            return false;
        var aryId = [];
        var rows = grid.getCheckedRows();
        $(rows).each(function(i, item) {
            aryId.push(item.id);
        });
        if (aryId.length == 0) {
            $.ligerDialog.warn("还没有选择,请选择一项进行编辑!", '提示信息');
            return false;
        } else if (aryId.length > 1) {
            $.ligerDialog.warn("已经选择了多项,请选择一项进行编辑!", '提示信息');
            return false;
        }
        innerEdit=true;
        if (innerEdit||columns.length<=9) {
            beginEdit(rowindex);
            return false;
        }
        var name = "id";
        var value = aryId[0];
        var form = new com.hotent.form.Form();
        var action = $(this).attr("action");
        form.creatForm("form", action);
        form.addFormEl(name, value);
        form.submit();
    
    });
    
}

// 开始编辑
function beginEdit(rowid, curId) {
    rowindex = rowid;
    id = curId;
    var currentRow = grid['currentData']['Rows'][rowindex];
    for (var id in disabledColumns) {
        disabledColumnsDatas[disabledColumns[id]] = currentRow[disabledColumns[id]];
    }
    grid.beginEdit(rowid);
}
;
// 取消编辑
function cancelEdit(rowid, id) {
	loadData();
    grid.cancelEdit(rowid);
}
;
function endEdit(rowid, id) {
    grid.endEdit(rowid);
}
;
//删除操作
function delFunction(index, id) {
    var params = {}, rowid = "", delId = "";
    if (id && index >= 0) {
        delId = id;
        rowid = index;
    } else {
        delId = [];
        var rows = grid.getCheckedRows();
        $(rows).each(function(i, item) {
            delId.push(item.id);
        });
        if (delId.length == 0) {
            $.ligerDialog.warn("请选择记录！");
            return;
        }
        delId = JSON.stringify(delId);
        if(delId.indexOf("[")>=0){
		    delId=delId.substring(1,delId.length-1);
		}
    }
    //向后台传输的值 当前页、当前选中的id 和当前页大小
    params= $.extend(true,{},grid.options.parms);
    params.id = delId;
    params.page = pageBean["currentPage"];
    params.pagesize = pageBean["pageSize"];
    $.ligerDialog.confirm('确认删除所选数据吗？', '提示信息', function(rtn) {
        if (rtn) {
            $.post("del.ht", params, function(response) {
                //在LigerGrid中自定仪的_getSearchData方法 用于渲染返回回来的json数据列表   response主要返回了两个数据  一个是提示信息，另一个是删除之后查询出来的json数据
	            	 //在grid列表中删除选中的行
	            if (rowid != '') {
	                grid.deleteRow(rowid);
	            } else {
	                grid.deleteSelectedRow();
	            }
                grid._getSearchData(response.substring(response.indexOf("}") + 1, response.length));
                showResponse(response.substring(0, response.indexOf("}") + 1));
            })
        }
    });
}
//提交修改
function submitChange(rowid, id) {
    endEdit(rowid, id);
    var dateCols = currentEditData.dateCols;
    var json = currentEditData.newdata;
    for (var js in json) {
        if (json[js] instanceof Date) {
            json[js] = json[js].Format(dateCols[js]);
        } else {
            json[js] = json[js];
        }
    }
    json["id"] = id;
    var jsonObj = {"json": JSON.stringify(json)};
    $.post("save.ht", jsonObj, function(response) {
        showResponse(response);
    })
}
//提示操作后的反馈信息
function showResponse(responseText) {
	loadData();
    var obj = new com.hotent.form.ResultMessage(responseText);
    if (obj.isSuccess()) { // 成功
        $.ligerDialog.closeWaitting();
        $.ligerDialog.success('<p><font color="green">' + obj.getMessage() 
        + '</font></p>', '提示信息', function() {
        });
    } else { // 失败
        $.ligerDialog.closeWaitting();
        var message = '<p><font color="red">' + obj.getMessage() 
        + '</font></p>';
        $.ligerDialog.tipDialog('提示信息', "删除结果如下:", message, null, function() {
            $.ligerDialog.hide();
        });
    }
}
//获取日期类型中的配置
function getFormat(json) {
    json = eval("[" + json + "]");
    var format = json[0].format || "yyyy-MM-dd";
    return format;
}
//获取代码生成之后下拉选择框的参数    因为ligerui支持的是 [{fieldName : "", text:""},{fieldName : "", text:""}]类型的数据格式
function getSelectData(options, fieldName) {
    options = options.replaceAll('key', fieldName);
    options = eval(options.replaceAll('value', 'text'));
    return options;
}
// 初始化数据 ，并且将带有日期的数据转换
function getFormatData(response) {
    var jsonObj = {};
    jsonObj.Rows = eval(response);
    for (var i = 0; i < jsonObj.Rows.length; i++) {
        for (var j = 0; j < columns.length; j++) {
            var tempVar = jsonObj.Rows[i][columns[j]];
            if (tempVar && tempVar.time) {
                jsonObj.Rows[i][columns[j]] = new Date(tempVar.time);
            }
        }
    }
    return jsonObj.Rows;
}
//解决IE下面new Date("yyyy-mm-dd")为NAN值的情况
function newDate(str) {
    str = str.split('-');
    var date = new Date();
    date.setUTCFullYear(str[0], str[1] - 1, str[2]);
    date.setUTCHours(0, 0, 0, 0);
    return date;
}
//获取单选框和复选框key对应的值 用于显示在grid中。
function getComboboxValue(options, curVal) {
    if (!curVal) {
        return;
    }
    var curVals = curVal.split(",");
    var returnText = "";
    options = eval(options);
    for (var i = 0; i < options.length; i++) {
        for (var j in curVals) {
            if (options[i]['key'] == curVals[j]) {
                returnText += options[i]['value'] + ",";
            }
        }
    }
    return returnText.substring(0, returnText.length - 1);
}
//当窗口大小改变时  自动改变列宽
function relaziseWidth() {
    var columnWidth = Math.ceil((document.body.clientWidth) / (columns.length)) - 8;
    var columnsLength = grid.columns.length;
    if (needToolbar) {
        columnWidth = Math.ceil((document.body.clientWidth - 200) / (columns.length)) - 8;
        columnsLength = columnsLength - 1;
    }
    for (var i = 1; i < columnsLength; i++) {
        grid.columns[i]["_width"] = columnWidth;
    }
    var headCells = $(".l-grid-hd-cell-inner:parent[columnname!='']");
    for (var i = 1; i < headCells.length; i++) {
        if (needToolbar && i == headCells.length - 1) {
            $(headCells[i]).attr("style", "width:" + 200 + "px;");
            break;
        }
        if (hideColumns.length > 0) {
            if (headCells[i]['clientWidth'] != 0) {
                $(headCells[i]).attr("style", "width:" + (columnWidth) + "px;");
                $(headCells[i]).parent(".l-grid-hd-cell").attr("style", "width:" + (columnWidth - 2) + "px;");
            }
        } else {
            $(headCells[i]).attr("style", "width:" + (columnWidth - 2) + "px;");
            $(headCells[i]).parent(".l-grid-hd-cell").attr("style", "width:" + (columnWidth) + "px;");
        }
    
    }
    if (grid.currentData) {
        grid.reRender();
    }
}
function isInArray(arr, obj) {
    for (i = 0; i < arr.length && arr[i] != obj; i++);
    return !(i == arr.length);
}
function loadData(){
	if(grid.options.isAdding){
		grid.options['newPage']=1;
		grid.loadData(true);
		grid.options.isAdding=false;
	}
}