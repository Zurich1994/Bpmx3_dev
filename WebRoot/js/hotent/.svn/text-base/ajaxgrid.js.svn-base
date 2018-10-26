$(function(){
	$("a.link.search.ajax").unbind("click");
});
//处理删除一行
function goPageAjax(obj,n,tableIdCode){
	var url = $("#_nav"+tableIdCode).attr('href');
	url = replacecurrentPage(url,n,tableIdCode);
	//在page.ftl中，$("#oldPageSize"+tableIdCode)的值被设为当前的分页大小
	url = replagePageSize(url,$("#oldPageSize"+tableIdCode).val(),tableIdCode);
	url = replageOldPageSize(url,$("#oldPageSize"+tableIdCode).val(),tableIdCode);
	updateAjax(obj,url);
}

function firstAjax(obj,tableIdCode){
	goPageAjax(obj,1,tableIdCode);
}

function lastAjax(obj,tableIdCode){
	var lastPage=parseInt($("#totalPage"+tableIdCode).val());
	if(lastPage<=0) return;
	goPageAjax(obj,lastPage,tableIdCode);
}

function previousAjax(obj,tableIdCode){
	var currentPage=parseInt($("#currentPage"+tableIdCode).val());
	currentPage-=1;
	if(currentPage<1)currentPage=1;
	goPageAjax(obj,currentPage,tableIdCode);
}

function nextAjax(obj,tableIdCode){
	var currentPage=parseInt($("#currentPage"+tableIdCode).val());
	var totalPage=parseInt($("#totalPage"+tableIdCode).val());
	if(totalPage<=0) return;
	currentPage+=1;
	if(currentPage>totalPage)currentPage=totalPage;
	goPageAjax(obj,currentPage,tableIdCode);
}

function changePageSizeAjax(obj,tableIdCode){
	var url=$("#_nav"+tableIdCode).attr('href');
	url = replagePageSize(url,obj.value,tableIdCode);
	url = replacecurrentPage(url,$("#currentPage"+tableIdCode).val(),tableIdCode);
	var container = $(obj).closest("div[ajax='ajax']");
	updateAjax(obj,url);
}
/**
 * 跳转至第n页
 */
function jumpToAjax(obj,tableIdCode){
	var currentPage=$("#navNum"+tableIdCode).val();
	var str=/^[1-9]\d*$/;
	if(str.test(currentPage)){
		goPageAjax(obj,currentPage,tableIdCode);
	}else{
		$.ligerDialog.error("非法的页码!");
		$("#navNum"+tableIdCode).focus();
	}
}


function refreshAjax(obj,url){
	//把锚点标记先移除出来
	var index=url.indexOf("#");
	if(index!=-1){
		url=url.substring(0,index);
	}
	updateAjax(obj,url);
}

//组件中的点击事件
function linkAjax(obj){
	var $obj = $(obj);
	var url=$obj.attr("action");
	updateAjax(obj,url);
}

function handlerSearchAjax(obj){
	var form = $(obj).closest("div.panel-top").find("form[name='searchForm']");
	//验证是否有效的规则
	var rtn=form.valid();
	if(!rtn){
		$.ligerDialog.warn("请检查输入查询条件是否有效！");
		return;
	}
	
	var href=form.attr("action");
	updateAjax(obj,href);
}


function updateAjax(obj,url){
	//查询参数
	var searchForm = $("div.panel-top").find("form[name='searchForm']");
	var params = serializeObject(searchForm);	
	//自定义显示的最外层Div
	var container = $(obj).closest("div[ajax='ajax']");
	//自定义显示组件的ID
	var displayId=container.attr("displayId");
	var filterKey=container.attr("filterKey");
	
	params.__displayId=displayId;
	params.__filterKey__ = filterKey;
	$.ligerDialog.waitting("加载中...");
	//提交到后台，取得自更新后的Html元素，并替换掉之前的html元素
	$.post(url,params,function(data){
		$.ligerDialog.closeWaitting();
		if(data.success){
			container.replaceWith(data.html);
			initReplaceHtml();
		}else{
			$.ligerDialog.err( "提示","出错了",data.msg);
		}
	});
}

function initReplaceHtml(){
	//初始化查询
	handleAjaxSearchKeyPress();
	//初始化单选
	initDisplaytag();
	$.initRowOps();
	$.initAbsoulteInTop();
	//初始化查询
	$.initFoldBox();
	try{
		//初始化导出菜单
		Export.initExportMenu();
	}catch(e){
	}
};

	
/**
 * 处理回车查询
 */
function handleAjaxSearchKeyPress(){
	$(".panel-search :input").keypress(function(e) {
		if(	e.keyCode == 13){//回车
			$("a.link.ajaxSearch").click();
		}else if(e.keyCode == 27){//ESC
			var searchForm = $("#searchForm");
			if(searchForm)
				searchForm[0].reset();
		}		
    })
};


/**
 * 序列化查询参数
 * @param {} form
 * @return {}
 */
function serializeObject(form){
	var o = {};
	var a = $(form).serializeArray();
	$.each(a, function() {
	    if (o[this.name]) {
	        if (!o[this.name].push) {
	            o[this.name] = [o[this.name]];
	        }
	        o[this.name].push(this.value || '');
	    } else {
	        o[this.name] = this.value || '';
	    }
	});
	return o;
}

/**
 * 打开窗口链接
 * @param {} obj
 * @param {} width
 * @param {} height
 * @param {} isFull
 */
function openLinkDialog(conf){
	conf = conf || {};
	var obj =conf.scope||this; 
		width= conf.width||800,
		height=conf.height||600,
		isFull =conf.isFull||false,
		title =conf.title||'新增',
		isStart = conf.isStart||false;
	if(isFull){
		height=screen.availHeight-35;
		width=screen.availWidth-5;
	}
	if(isStart){
		$.ligerDialog.warn("请先绑定流程！");
		return;
	}
	var winArgs="dialogWidth="+width+"px;dialogHeight="+height+"px;help=0;status=0;scroll=1;center=1";
	var url=$(obj).attr("action");
	url=url.getNewUrl();
	//var rtn = window.showModalDialog(url,{},winArgs);TODO jsp找不到
	/*KILLDIALOG*/
	DialogUtil.open({
		height:height,
		width: width,
		title : title,
		url: url, 
		sucCall:function(){
			//刷新原来的当前的页面信息
			locationPrarentPage();
			location.href=location.href.getNewUrl();
		}
	});
	
	/*//刷新原来的当前的页面信息
	locationPrarentPage();*/
	
	/*if(rtn!=undefined){
		location.href=location.href.getNewUrl();
	}*/
}


//当执行返回内容时，把当前页面按本次设置的刷新一次
function locationPrarentPage(){
	var btnloadDiv = $("div.l-bar-btnload");  //有刷新样式的DIV
	$("a",btnloadDiv).each(function(){      //有刷新样式的DIV的历遍超链接
		var aLink=$(this);
		var onclickStr = aLink.attr("onclick"); 
		if(onclickStr.indexOf("refreshAjax")>-1){
			aLink.click();
		};
	});
}


function datePicker(obj,type){
	if('yyyy-MM-dd'==type){
		WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});
	}else{
		WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true});
	}
	$(obj).blur();
}


function linkSortAjax(obj,tableIdCode){
	var $obj = $(obj);
	var url=$obj.attr("action");
	var sortField=$obj.attr("sort");
	var orderSeq="DESC";
	var curSortField=$("#sortField"+tableIdCode).val();
	var curOrderSeq=$("#orderSeq"+tableIdCode).val();
	
	if(sortField==curSortField){
		if(curOrderSeq=="DESC"){
			orderSeq="ASC";
		}
	}
	
	url = replaceOrderSeq(url,orderSeq,tableIdCode);
	url = replaceSortField(url,sortField,tableIdCode);

	updateAjax(obj,url);
}



/**
 * 替换url中的sortField参数，用于排序显示
 */
function replaceSortField(url,sortField,tableIdCode){
	//把锚点标记先移除出来
	var index=url.indexOf("#");
	if(index!=-1){
		url=url.substring(0,index);
	}
	var sortFieldParam=tableIdCode+'s';
	//查询的页码需要替换
	var reg=new RegExp(sortFieldParam + '=\\w*');
	if(reg.test(url)){
		url=url.replace(reg,sortFieldParam+'='+sortField);
	}else if(url.indexOf('?')!=-1){
		url+='&'+sortFieldParam+'='+sortField;
	}else{
		url+='?'+sortFieldParam+'='+sortField;
	}
	return url;
}

/**
 * 替换url中的orderSeq参数，用于排序显示
 */
function replaceOrderSeq(url,orderSeq,tableIdCode){
	//把锚点标记先移除出来
	var index=url.indexOf("#");
	if(index!=-1){
		url=url.substring(0,index);
	}
	var orderSeqParam=tableIdCode+'o';
	//查询的页码需要替换
	var reg=new RegExp(orderSeqParam + '=\\w*');
	if(reg.test(url)){
		url=url.replace(reg,orderSeqParam+'='+orderSeq);
	}else if(url.indexOf('?')!=-1){
		url+='&'+orderSeqParam+'='+orderSeq;
	}else{
		url+='?'+orderSeqParam+'='+orderSeq;
	}
	return url;
}
