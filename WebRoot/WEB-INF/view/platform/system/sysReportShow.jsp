<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/get.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.leftGroup{float:right;}
	.group{margin-right:5px !important;}
	.exportlinks{cursor: pointer;}
	.exportlinks *{width:55px;text-align:right;}
	.group a.link{margin-top:-3px !important;border: 0 !important;padding: 0 0 3px !important;}
</style>
<f:link href="form.css" ></f:link>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.PrintArea.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/SelectorInit.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript">
	$(function() {
		$("#print").click(function() {
			$("div#printArea").printArea();
		});
		$('#printArea').hide();
		$('select#pageSize').val('${pageSize}');
		$('#l-bar-button a').live('click',function(){
			if('${paramMap}'){
				var href = $('#_nav').attr('href');
				$('#_nav').attr('href',href+$('#searchForm').serialize());
			}
		});
		
		
		$(':input[id="dialog"]').live('click',function(){
			var target = $(this).attr('param');
			var dialog = $(this).attr('dialog');
			var me = $(this);
			CommonDialog(dialog,function(data){
				if(Object.prototype.toString.call((data)) == '[object Array]'){
					var result = "" ;
					for(var i=0,d;d=data[i++];){
						result += d[target]+',';
					}
					me.prev(':input').attr('value',result.substring(0,result.length-1));
				}else{
					me.prev(':input').attr('value',data[target]);
				}
			});
		});
		initSearchForm();
	});
	function onHideFileChange(obj){
		var input = $(obj).closest('li').find(':input');
		input.val($(obj).val());
	}
	function initSearchForm(){
		var params = ${params} ;
		var paramMap = ${paramMap};
		var obj = {} ;
		var ul = $('.panel-search').find('ul');
		var field,paraCt,defaultType ;
		for(var i=0;i<params.length;i++){
			var li = $('.panel-search').find('li:first').clone(true);
			obj = params[i];
			defaultType = obj.defaultType;
			field = obj.field ;
			paraCt = obj.paraCt ;
			$('.label',li).text(obj.comment);
			var target = $(':input',li);
			target.attr('name',field);
			if(defaultType=='0'){//固定值
				target.attr('value',obj.defaultValue);
			}else if(defaultType=='1'){//控件
				target.attr('value',paramMap[field]);
				if(paraCt=="-1"){// 日期控件
					target.addClass('date');
				}else if(paraCt=="0"){//自定义对话框
					li.append('<input type="button" value="…" id="dialog" dialog="'+obj.dialog+'" param="'+obj.dialogParam+'"/>');
				}else if(paraCt!="1"){//人员选择器
					var input = getSelector(target, obj);
					li.append(input);
				}
				li.show();
				$('.panel-search').show();
			}else if(defaultType=='2'){//脚本
				target.val(paramMap[field]);
			}
			ul.append(li);
		}
		init(); //初始化控件
		//调整控件样式
		$('[ctlType="selector"]').closest('div').css('display', 'inline');
	}
	function save(exportType) {
		window.location.href = "${ctx}/platform/system/sysReport/export.ht?reportId=${param.reportId}&t=${totalCount}&exportType="
				+ exportType+"&"+$('#searchForm').serialize();
	}
	//覆盖displaytag.js中的方法，添加表单参数
	function goPage(n,tableIdCode){
		var url = replacecurrentPage($("#_nav"+tableIdCode).attr('href')+"&"+$('#searchForm').serialize(),n,tableIdCode);
		url = replagePageSize(url,$("#oldPageSize"+tableIdCode).val(),tableIdCode);
		location.href=replageOldPageSize(url,$("#oldPageSize"+tableIdCode).val(),tableIdCode);
	}
	//覆盖displaytag.js中的方法，添加表单参数
	function changePageSize(sel,tableIdCode){
		var url = replagePageSize($("#_nav"+tableIdCode).attr('href')+"&"+$('#searchForm').serialize(),sel.value,tableIdCode);
		url = replacecurrentPage(url,$("#currentPage"+tableIdCode).val(),tableIdCode);
		location.href=replageOldPageSize(url,$("#oldPageSize"+tableIdCode).val(),tableIdCode);
	}

	function getSelector(obj, paramObj){
		var paraCt = paramObj.paraCt;
		$(obj).attr("ctlType", "selector");
		$(obj).attr("initvalue", paramObj.defaultValue);
		$(obj).attr("lablename", paramObj.field);
		var str = "";
		switch(paraCt){
		case "0":
			str = "dialog";
			break;
		case "4"://用户单选
			str = "user";
			break;
		case "17"://角色
			str = "role";
			break;
		case "18"://组织
			str = "org";
			break;
		case "19"://岗位
			str = "position";
		}
		$(obj).removeClass("inputText");
		$(obj).addClass(str);
	}
	
</script>
<title>报表展示</title>
</head>
<body>
<div class="panel-top">
	<div class="panel-toolbar">
		<div class="toolBar area-export">
			<div class="group exportlinks" title="导出EXCEL" onclick="save('xls')">
				<div class="excel">EXCEL</div>
			</div>
			<div class="l-bar-separator"></div>
			<div class="group exportlinks" title="导出WORD" onclick="save('doc')">
				<div class="rtf">WORD</div>
			</div>
			<div class="l-bar-separator"></div>
			<div class="group" title="打印">
				<a id='print' class="link print" style="background:url('') !important;"/><span></span>打印</a>
			</div>
			<div class="l-bar-separator"></div>
			<div class="group" title="查询">
				<a id="search" class="link search" style="background:url('') !important;"/>
					<span style="height:16px"></span>查询
				</a>
			</div>
			<!--右侧布局 -->
			
			<div class="group leftGroup">
				<span> 
					<input type="button" id="btnGo" value="GO" class="btn-go" onclick="jumpTo('')" />
				</span>
				<input type="hidden" id="currentPage" name="currentPage" value="${pageIndex}"/>
				<input type="hidden" id="totalPage" name="totalPage" value="${lastPageIndex}"/>
				<input type="hidden" id="oldPageSize" name="oldPageSize" value="${pageSize}"/>
				<input type="hidden" id="_nav" href="${ctx}/platform/system/sysReport/show.ht?reportId=${param.reportId}&t=${totalCount}">
			</div>
			<div class="l-bar-separator leftGroup"></div>
			<div class="group leftGroup">
				<div class="l-bar-button l-bar-btnnext">
					<a href="javascript:;" onclick="next('')" title="下一页"> <span></span>
					</a>
				</div>
				<div class="l-bar-button l-bar-btnlast">
					<a href="javascript:;" onclick="last('')" title="尾页">
						<span></span>
					</a>
				</div>
			</div>
			<div class="l-bar-separator leftGroup"></div>
			
			<div class="group leftGroup">
				<span class="pcontrol"> 
				<input size="4" value="${pageIndex}" style="width: 20px; text-align: center" maxlength="3"
					class="inputText" type="text" id="navNum" name="navNum" />/ <span>${lastPageIndex}</span>
				</span>
			</div>
			<div class="l-bar-separator leftGroup"></div>
			<div class="group leftGroup">
				<div class="l-bar-button l-bar-btnfirst">
					<a href="javascript:;" onclick="first('')" title="首页"> <span class=""></span>
					</a>
				</div>
				<div class="l-bar-button l-bar-btnprev">
					<a href="javascript:;" onclick="previous('')" title="上一页"> 
					<span class=""></span>
					</a>
				</div>
			</div>
			<div class="group l-bar-selectpagesize leftGroup">
				每页记录&nbsp;<select id="pageSize" name="pageSize" onchange="changePageSize(this,'');" class="select_short">
					<option value="5" >5</option>
					<option value="10" >10</option>
					<option value="15" >15</option>
					<option value="20" >20</option>
					<option value="50" >50</option>
					<option value="100" >100</option>
				</select>
			</div>
		</div>
	</div>
	<div class="panel-search" style="display:none;">
		<form id="searchForm" method="post" action="show.ht?reportId=${param.reportId}&t=${totalCount}">
			<ul class="row">
				<li style="display:none"><span class="label"></span><input type="text" class="inputText" value=""/></li>
			</ul>
		</form>
	</div>
</div>
	<p>
	<div id="previewArea">
		<center>${tempPreview}</center>
	</div>
	<div id="printArea">
		<jsp:include page="${sourceHtmlFile}" />
	</div>
	<div id="template-container" class="hidden">
	<div name="custom-div">
		<div name="paraValueDiv"></div>
	</div>
	</div>
	<%@include file="/commons/include/nodeRuleTemplate.jsp" %>
</body>
</html>