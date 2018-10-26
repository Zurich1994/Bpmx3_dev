$(function(){
	initDisplaytag()
});

/**
 * 初始化信息
 */
function initDisplaytag(){
	//行高亮
	jQuery.highlightTableRows();
	//选中一行
	jQuery.selectTr();
	//搜索
	handlerSearch();
	//处理回车查询事件
	handleSearchKeyPress();
	//全选
	handlerCheckAll();
	//处理如果一个行没选中，全选去除
	handCheckBox();
	//删除一行
	handlerDelOne();	
	//编辑一行
	handlerUpd();
	//删除选中行
	handlerDelSelect();
	//导出所有
	exportCheckAll();
	//初始化信息
	//handlerInit();
	pageInit();

}


/**
 * 导出选择时，更改导出链接url中的导出来全部项的参数.
 * 后台没处理导出全部的处理
 **/
function exportAllCheckChange(obj){
	var spanDiv=document.getElementById("divExportAll");
	if(spanDiv!=null){
		var childs=spanDiv.childNodes;
		for(var i=0;i<childs.length;i++){
			if(childs[i]!=null&&childs[i].tagName=="A"){
				var url=childs[i].href;
				if(obj.checked){
					if(url.indexOf("?")!=-1){
						url+="&";
					}else{
						url+="?";
					}
					childs[i].href=url+"exportAll=1";
				}else{
					childs[i].href=childs[i].href.replace("&exportAll=1","").replace("?exportAll=1").replace("exportAll=1");
				}
			}
		}
	}
}

function exportCheckAll()
{
	$(".expCheckAll").bind("click",function(event){
		
		var checked=event.target.checked;
		$(".expCheckAll ~ a").each(function(){
			var url=$(this).attr('href');
			if(checked){
				if(url.indexOf("?")!=-1){
					url+="&";
				}else{
					url+="?";
				}
				$(this).attr('href',url+"exportAll=1");
			}else{
				url=url.replace("&exportAll=1","").replace("?exportAll=1").replace("exportAll=1");
				$(this).attr('href',url);
			}
		});
	});
	//event.target.tagName
}

function handlerCheckAll(){
	$("#chkall").click(function(){
		var state=$(this).attr("checked");
		if(state==undefined)
			checkAll(false);
		else
			checkAll(true);
	});
}

function handCheckBox(){
	$("input[type='checkbox'][class='pk']").bind("chlick change",function (){
		var isChecked=$(this).attr("checked")=="checked";
		if(!isChecked){
			$("#chkall").attr("checked", false);
		}else{
			var isAll = true;
			$("input[type='checkbox'][class='pk']").each(function(){
				var isChecked=$(this).attr("checked")=="checked";
				if(!isChecked){ 
					isAll = false;
				}
			});
			if(isAll){
				$("#chkall").attr("checked", "checked");
			}else{
				$("#chkall").attr("checked", false);
			}
		}
	})
}

function checkAll(checked){
	$("input[type='checkbox'][class='pk']").each(function(){
		$(this).attr("checked", checked);
	});
}



//查询
function handlerSearch()
{
	$("a.link.search").click(function(){
		if(!$(this).hasClass('disabled')) {
			$("#searchForm").submit();
		}
	});
}
/**
 * 处理回车查询
 */
function handleSearchKeyPress(){
	$(".panel-search :input").keypress(function(e) {
		if(	e.keyCode == 13){//回车
			$("a.link.search").click();
		}else if(e.keyCode == 27){//ESC
			var searchForm = $("#searchForm");
			if(searchForm)
				searchForm[0].reset();
		}		
    })
}

//处理删除一行
function handlerDelOne()
{
	$("table.table-grid td a.link.del").click(function(){
		if($(this).hasClass('disabled')) return false;
		
		var ele = this;
		$.ligerDialog.confirm('确认删除吗？','提示信息',function(rtn) {
			if(rtn) {
				if(ele.click) {
					$(ele).unbind('click');
					ele.click();
				} else {
					location.href=ele.href;
				}
			}
		});
		
		return false;
	});
}

//更新
function handlerUpd(){
	$("div.group > a.link.update").click(function() {
		if($(this).hasClass('disabled')) return false;
		
		var action=$(this).attr("action");
		var aryId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
		var len=aryId.length;
		if(len==0){
			$.ligerDialog.warn("还没有选择,请选择一项进行编辑!",'提示信息');
			return;
		}
		else if(len>1){
			$.ligerDialog.warn("已经选择了多项,请选择一项进行编辑!",'提示信息');
			return;
		}
		var name=aryId.attr("name");
		var value=aryId.val();
		var form=new com.hotent.form.Form();
		form.creatForm("form", action);
		form.addFormEl(name, value);
		form.submit();
		
	});
}


function handlerDelSelect()
{
	//单击删除超链接的事件处理
	$("div.group > a.link.del").click(function()
	{	
		if($(this).hasClass('disabled')) return false;
		
		var action=$(this).attr("action");
		var $aryId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
		
		if($aryId.length == 0){
			$.ligerDialog.warn("请选择记录！");
			return false;
		}
		
		//提交到后台服务器进行日志删除批处理的日志编号字符串
		var delId="";
		var keyName="";
		var len=$aryId.length;
		$aryId.each(function(i){
			var obj=$(this);
			if(i<len-1){
				delId+=obj.val() +",";
			}
			else{
				keyName=obj.attr("name");
				delId+=obj.val();
			}
		});
		var url=action +"?" +keyName +"=" +delId ;
		
		$.ligerDialog.confirm('确认删除吗？','提示信息',function(rtn) {
			if(rtn) {
				var form=new com.hotent.form.Form();
				form.creatForm("form", action);
				form.addFormEl(keyName, delId);
				form.submit();
			}
		});
		return false;
	
		
	});
}

function goPage(n,tableIdCode){
	var url = replacecurrentPage($("#_nav"+tableIdCode).attr('href'),n,tableIdCode);
	url = replagePageSize(url,$("#oldPageSize"+tableIdCode).val(),tableIdCode);
	location.href=replageOldPageSize(url,$("#oldPageSize"+tableIdCode).val(),tableIdCode);
}

function first(tableIdCode){
	goPage(1,tableIdCode);
}

function last(tableIdCode){
	var lastPage=$("#totalPage"+tableIdCode).val();
	goPage(lastPage,tableIdCode);
}

function previous(tableIdCode){
	var currentPage=parseInt($("#currentPage"+tableIdCode).val());
	currentPage-=1;
	if(currentPage<1)currentPage=1;
	goPage(currentPage,tableIdCode);
}

function next(tableIdCode){
	var currentPage=parseInt($("#currentPage"+tableIdCode).val());
	var totalPage=parseInt($("#totalPage"+tableIdCode).val());
	currentPage+=1;
	if(currentPage>totalPage)currentPage=totalPage;
	goPage(currentPage,tableIdCode);
}

function changePageSize(sel,tableIdCode){
	var url = replagePageSize($("#_nav"+tableIdCode).attr('href'),sel.value,tableIdCode);
	url = replacecurrentPage(url,$("#currentPage"+tableIdCode).val(),tableIdCode);
	location.href=replageOldPageSize(url,$("#oldPageSize"+tableIdCode).val(),tableIdCode);
}
/**
 * 跳转至第n页
 */
function jumpTo(tableIdCode){
	var me =$("#navNum"+tableIdCode), currentPage=me.val(),totalPages = $('#totalPage'+tableIdCode).val();
	jumpToPage(tableIdCode,currentPage,totalPages);
}
function jumpToPage(tableIdCode,currentPage,totalPages){
	currentPage = parseInt(currentPage),totalPages= parseInt(totalPages);
    if (/^\d+$/.test(currentPage) && currentPage > 0 && currentPage <= totalPages){
		goPage(currentPage,tableIdCode);
	}else{
		var tip = String.format($lang.operateTip.pageTip,totalPages);
		alert(tip);
		$("#navNum"+tableIdCode).focus();
	}
}

/**
 * 初始化跳转页面
 */
function pageInit(){
	$("div.panel-page input.pageInput").keypress(function(e) {
        return handleKeyPress(e, this);
    })
}

function handleKeyPress(e, obj) {
	var me=$(obj), currentPage = me.val(),tableIdCode = me.attr("tableidcode"),totalPages = $('#totalPage'+tableIdCode).val(); 
    if (/^13$|^39$|^37$/.test(e.keyCode)) {
        switch (e.keyCode) {
            case 13: // Enter
            	jumpToPage(tableIdCode,currentPage,totalPages);
                break;
            case 39: // right arrow
                break;
            case 37: // left arrow
                break;
        }
    }
}

//http://localhost:8080/eShop/hello.do?age=3&currentPage=5&fullname=mansan;

/**
 * 替换url中的page参数，用于数据分页显示
 */
function replacecurrentPage(url,currentPage,tableIdCode){
	//把锚点标记先移除出来
	var index=url.indexOf("#");
	if(index!=-1){
		url=url.substring(0,index);
	}
	var pageParam=tableIdCode+'p';
	//查询的页码需要替换
	var reg=new RegExp(pageParam + '=\\d*');
	if(reg.test(url)){
		url=url.replace(reg,pageParam+'='+currentPage);
	}else if(url.indexOf('?')!=-1){
		url+='&'+pageParam+'='+currentPage;
	}else{
		url+='?'+pageParam+'='+currentPage;
	}
	return url;
}




function replagePageSize(url,pageSize,tableIdCode){
	var pageParam=tableIdCode+'z';
	var reg=new RegExp(pageParam + '=\\d*');
	if(reg.test(url)){
		url=url.replace(reg,pageParam+'='+pageSize);
	}else if(url.indexOf('?')!=-1){
		url+='&'+pageParam+'='+pageSize;
	}else{
		url+='?'+pageParam+'='+pageSize;
	}
	return url;
}


function replageOldPageSize(url,pageSize,tableIdCode){
	var pageParam=tableIdCode+'oz';
	var reg=new RegExp(pageParam + '=\\d*');
	if(reg.test(url)){
		url=url.replace(reg,pageParam+'='+pageSize);
	}else if(url.indexOf('?')!=-1){
		url+='&'+pageParam+'='+pageSize;
	}else{
		url+='?'+pageParam+'='+pageSize;
	}
	return url;
}