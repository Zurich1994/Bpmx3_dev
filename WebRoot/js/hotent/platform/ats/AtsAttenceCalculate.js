/*******************************************************************************
 * 
 * 考勤管理-考勤计算js
 * 
 * <pre>
 *  
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2015-06-01-上午11:10:52
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 * 
 ******************************************************************************/
var AtsAttenceCalculatePlugin = null;
;(function($, window, document, undefined) {
	var pluginName = "AtsAttenceCalculate",
	defaults = {
	 },
	 me,
	 _attendPolicyId = null,
	 _startTime = null,
	 _endTime = null,
	 _userId = null,
	 _orgId = null;
	_sidValue = [];
	function Plugin(element, options) {
		this.settings = $.extend({}, defaults, options);
		this._defaults = defaults;
		this._name = pluginName;
		this.init();
	}
	
	Plugin.prototype = {
		init:function(){
			me = this;
			AtsAttenceCalculatePlugin = this;
			$('.nav-tabs > li').click(function(){
			    var $this    = $(this);
				if($this.hasClass('active'))
					   return ;
			 $this.siblings('li').removeClass('active');
			   $this.addClass("active");
			   var action = $this.attr("action");
			   if(action == "1"){
				   me.loadGrid();
			   }else{
				  me.loadNoUserGrid();
			   }
			});
			
			this.loadGrid();
			//查询
			$('a.fa-search').click(function(){
				me.searchGrid();
			});
			$('a.allCalculate').click(function(){
				me.calculate();
			});
			
			$('a.calculateSelect').click(function(){
				  var selectedIds = $("#reportGrid").jqGrid('getGridParam','selarrrow');
			
				if (selectedIds == null || selectedIds.length < 1) {
					$.ligerDialog.alert("请选择要计算的人员！","提示信息");
					return;
				}
				var fileIds = [];
		  		for (var i = 0; i < selectedIds.length; i++) {
        		     var rowData = $("#reportGrid").jqGrid("getRowData", selectedIds[i]);
        			 fileIds.push(rowData.fileId);
        		}
		  		me.calculate(fileIds.join(","));
			
			});
			$('a.summary').click(function(){
				var url=__ctx + "/platform/ats/atsAttenceCalculateSet/edit.ht?type=1";
					url=url.getNewUrl();
					DialogUtil.open({
						height:400,
						width: 500,
						title : '汇总显示',
						url: url, 
						isResize: true,
						sucCall:function(rtn){
						//	me.loadGrid();
						}
					});	
			});
			
			$('a.detail').click(function(){
				var url=__ctx + "/platform/ats/atsAttenceCalculateSet/edit.ht?type=2";
				url=url.getNewUrl();
				DialogUtil.open({
					height:400,
					width: 500,
					title : '明细显示',
					url: url, 
					isResize: true,
					sucCall:function(rtn){
					//	me.loadGrid();
					}
				});	
			});
			

		
		},
		/**
		 * 没有计算的数据
		 */
		loadNoUserGrid:function(){
			$('#dataGrid').empty();
			$('#dataGrid')
					.append(
							'<div id="gridPager1"></div> <table id="reportGrid" style="height:1px;"></table>');
		
			
			var options = {
				url:__ctx+"/platform/ats/atsAttenceCalculate/getNoneCalList.ht?Q_beginattenceTime_DL="+_startTime+"&Q_endattenceTime_DE="+_endTime+"&Q_attendPolicy_L="+_attendPolicyId,
				datatype : "json", // 数据来源，本地数据
				mtype : "POST",// 提交方式
				height : document.body.clientHeight - 265,// 高度，表格高度。可为数值、百分比或'auto'
				width : document.body.clientWidth - 30,
				colNames : [ 'fileId', '考勤编号', '工号', '姓名', '组织', '岗位' ],
				colModel : [ {
					name : 'fileId',
					hidden : true
				}, {
					name : 'cardNumber'
				}, {
					name : 'account',
					width : 80
				}, {
					name : 'userName'
				}, {
					name : 'orgName'
				}, {
					name : 'posName'
				} ],
				multikey : 'id',
				multiselect : true,
				rownumbers : true,// 添加左侧行号
				viewrecords : true,// 是否在浏览导航栏显示记录总数
				rowNum : 30,// 每页显示记录数
				rowList : [ 15, 30, 45, 60 ],// 用于改变显示行数的下拉列表框的元素数组。
				jsonReader : {
					root : "results",// json中代表实际模型数据的入口
					total : "total", // json中代表总页数的数据
					page : "page", // json中代表当前页码的数据
					records : "records",// json中代表数据行总数的数据
					repeatitems :false
				// 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。
				},
				prmNames : {
					page : "page", // 表示请求页码的参数名称
					rows : "pageSize" // 表示请求行数的参数名称
				},
				shrinkToFit : true,
				pager : $('#gridPager1'),
				afterInsertRow : function(rowid, rowdata) {
			
				},
				onSelectAll : function(rowids, status) {
			
				},
				onSelectRow : function(rowid, status) {
				
				}
			};
			$("#reportGrid").html();
			$("#reportGrid").jqGrid(options);
			
		},
		searchGrid:function(){
			me.getInitVal();
			var action = $('.nav-tabs > li.active').attr("action");
		   if(action == "1"){
			   me.loadGrid();
		   }else{
			  me.loadNoUserGrid();
		   }
		},
		initUserGrid:function(){
			var action = $('.nav-tabs > li.active').attr("action");
		   if(action == "1"){
			   me.loadGrid();
		   }else{
			  me.loadNoUserGrid();
		   }
		},
		/**
		 * 计算数据
		 */
		calculate:function(fileIds){
	  		me.getInitVal();
			$.ligerDialog.waitting("请稍后……");
			me.remoteCall(
					{method : "calculate",
					param : {
						"startTime" : _startTime,
						"endTime" : _endTime,
						"attendPolicyId" : _attendPolicyId,
						"fileIds":fileIds?fileIds:''
					},
					success : function(data) {
						$.ligerDialog.closeWaitting();
						if(data.success)
							me.initUserGrid();
					}});
		
		},
		getInitVal:	function(){
			_attendPolicyId = $('#attendPolicy').val();
			var attenceCycleOption =  $("#attenceCycle").find("option:selected");
			_startTime = attenceCycleOption.attr('startTime');
			_endTime = attenceCycleOption.attr('endTime');
			_orgId = $('#orgId').val();
			_userId = $('#userId').val();
		},
		remoteCall:function(conf){
			var url = conf.url;
			if(conf.method)
				url = __ctx+"/platform/ats/atsAttenceCalculate/"+conf.method+".ht";
			$.ajax({
				type : "POST",
				url :url,
				data : conf.param?conf.param:{},
				success : function(data) {
					conf.success.call(this,data);
				}
			});
		},
		loadGrid:function(){
			
			$('#dataGrid').empty();
			$('#dataGrid')
					.append(
							'<div id="gridPager1"></div> <table id="reportGrid" style="height:1px;"></table>');
			this.renderDataGrid("getGridColModel");
		},
		renderDataGrid:function(method){
			me.getInitVal();
			me.remoteCall({
				method : method,
				param : {
					"startTime" : _startTime,
					"endTime" : _endTime,
					"attendPolicyId" : _attendPolicyId
				},
				success : function(data) {
					me.doRenderDataGrid(data);
				}
			});
		},
		doRenderDataGrid : function(data) {
			var table = $("#reportGrid"),
				url = __ctx+"/platform/ats/atsAttenceCalculate/reportGrid.ht?" +
						"Q_beginattenceTime_DL="+_startTime+"&Q_endattenceTime_DE="+_endTime
						+"&Q_attendPolicy_L="+_attendPolicyId +"&Q_userId_L="+_userId +"&Q_orgId_L="+_orgId ,
				colNames = data.colNames,
				colModel = data.colModel;
			for (var i = 0; i < colModel.length; i++) {
				if(i<= 2)
					continue;
			
			}
			var options = {
				url : url,
				datatype : "json",
				multiselect : true,
				rownumbers : false,
				colNames : colNames,
				colModel : colModel,
				rowNum : 30,
			//	pager : '#gridPager1',
				autoheight : true,
				height : 'auto',
				width:document.body.clientWidth-30,
				rowList : [ 15, 30, 45, 60 ],
				gridview : true,
				pginput : true,
				shrinkToFit :colModel.length > 10 ? false
						: true,
				autoScroll: true,
				viewrecords : true,
				jsonReader:{
			        	root: "results",// json中代表实际模型数据的入口
			        	repeatitems : false
				},
				grouping : true,
				groupingView : {
					groupField : [ 'orgName' ],
					groupColumnShow : [ false ],
					groupText : [ '<b>{0}</b>' ],
					groupCollapse : false,
					groupOrder : [ 'asc' ],
					groupSummary : [ true ],
					groupDataSorted : true,
					showSummaryOnHide : true
				},
				onCellSelect : function(rowid, index, contents,event) {
					if (index == 0) 
						return;
					var data = $("#reportGrid").jqGrid("getRowData", rowid),
						fileId = data['fileId'],colName = colModel[index - 1].name,
						userName = data['userName'];
					//
					if ( colName == 'account' ||colName == 'userName') {
						me.showCalendarDetailAction(fileId,userName,_startTime,_endTime);
					}else  if (colName.substring(0, 1) == "S") {
						if (contents == 0) {
							$.ligerDialog.alert("该汇总项没有明细！","提示信息");
							return;
						}
						me.showSummaryDetailAction(fileId,userName,colName,colModel[index - 1].label,_startTime,_endTime);
					}else  {
						me.showBillDetailAction(fileId,userName,colModel[index - 1].index);
					}
				},
				onSelectRow : function(id) {
					jQuery('#reportGrid').jqGrid('editRow', id,
							false, function() {
							});
					//_sidValue
//					sidValue.push(id);
//					lastsel2 = id;
//					$("#reportGrid")
//							.attr("sid", sidValue.join(","));
				}
			};
			$("#reportGrid").html();
			$("#reportGrid").jqGrid(options);
		//	$("#reportGrid").jqGrid('setFrozenColumns');
			
			var  h= (document.body.clientHeight)+'px';
			$('#reportGrid .ui-jqgrid-bdiv').css('height',  h).css(
					'width', '100%').css('overflow-y', 'scroll',
					'overflow-x', 'scroll');
			$('#reportGrid .ui-jqgrid .ui-jqgrid-htable th div').css('height','35px');
			},
			/**
			 * 展示日历
			 */
			showCalendarDetailAction:function(fileId,userName,startTime,endTime){
				var url=__ctx + "/platform/ats/atsAttenceCalculate/calendar.ht?fileId="
						+fileId+"&startTime="+startTime+"&endTime="+endTime;
				url=url.getNewUrl();
				DialogUtil.open({
					height:600,
					width: 800,
					title : userName+"("+startTime+"--"+endTime+")",
					url: url, 
					isResize: true
				});
			},
			/**
			 * 汇总明细
			 */
			showSummaryDetailAction:function(fileId,userName,colName,collabel,startTime,endTime){
				var url=__ctx + "/platform/ats/atsAttenceCalculate/summary.ht?fileId="
				+fileId+"&startTime="+startTime+"&endTime="+endTime+"&colName="+colName;
					url=url.getNewUrl();
					DialogUtil.open({
						height:600,
						width: 800,
						title : userName+"["+collabel+"]("+startTime+"--"+endTime+")",
						url: url, 
						isResize: true
					});
			},
			/**
			 * 详细明细
			 */
			showBillDetailAction:function(fileId,userName,colName){
				var url=__ctx + "/platform/ats/atsAttenceCalculate/bill.ht?fileId="
				+fileId+"&colName="+colName;
					url=url.getNewUrl();
				DialogUtil.open({
					height:600,
					width: 800,
					title : userName+"["+colName+"]---考勤记录",
					url: url, 
					isResize: true
				});
			}
	}
	
	$.fn[pluginName] = function(options) {
		return this
				.each(function() {
					if (!$.data(this, "plugin_" + pluginName)) {
						$.data(this, "plugin_" + pluginName, new Plugin(this,
								options));
					}
				});
	};

})(jQuery, window, document);

$(document).ready(function() {
	$('body').AtsAttenceCalculate();
});

function selectAttendPolicy(){
	AtsAttencePolicyDialog({
		isSingle:true,
		callback:function(data){
			$('#attendPolicy').val(data.id);
			$('#attendPolicyName').val(data.name);
		}
	})
}

function selectOrg(){
	OrgDialog({isSingle:true,callback:function(orgId,orgName){
		$('#orgId').val(orgId);
		$('#orgName').val(orgName);
	}});
}


function selectUser(){
	UserDialog({isSingle:true,callback:function(userId,userName){
		$('#userId').val(userId);
		$('#userName').val(userName);
	}});
}