/*******************************************************************************
 * 
 * 考勤管理-编排管理js
 * 
 * <pre>
 *  
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2015-05-29-上午11:10:52
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 * 
 ******************************************************************************/
var AtsTurnShiftWizardPlugin = null;
;
(function($, window, document, undefined) {

	var pluginName = "AtsTurnShiftWizard", defaults = {}, me, _shiftType = null, // 排班类型
	_startTime = null, // 开始时间
	_endTime = null, // 结束时间
	_listRowDatas = [], AtsConstant = { // 用户的常量
		SHIFT_TYPE_CALENDAR : 1,
		SHIFT_TYPE_LIST : 2
	};
	function Plugin(element, options) {
		this.settings = $.extend({}, defaults, options);
		this._defaults = defaults;
		this._name = pluginName;
		this.init();
	}

	Plugin.prototype = {
		/**
		 * 初始化页面
		 */
		init : function() {
			me = this;
			AtsTurnShiftWizardPlugin = this;
			me.initWizard();
			me.initUserGrid();
			me.initCalendar();
			me.initScheduleShiftGrid();
			me.initOperation();
		},
		/**
		 * 初始化向导
		 */
		initWizard : function() {
			$('#fuelux-wizard')
					.ht_wizard()
					.on('change', function(e, data) {
						var step = data.step, direction = data.direction;
						// 第1步的下一步操作
						if (step == 1 && direction == 'next')
							return me.change1next();
						// 第2步的下一步操作
						else if (step == 2 && direction == 'next')
							return me.change2next();
						else if (step == 3 && direction == 'next')
							return me.change3next();
						// 第4步的上一步 操作
						else if (step == 4 && direction == 'previous')
							me.clickQueryUser();
						// 第四步下一步 检查是否排班
						else if (step == 4 && direction == 'next')
							return me.change4next();

					})
					.on('changed',function(e, data) {
								if (data.currentStep == 4
										&& _shiftType == AtsConstant.SHIFT_TYPE_CALENDAR) {
									$('#calendar_info').fullCalendar('render');
									$('#calendar_info').fullCalendar(
											'gotoDate', _startTime);
								}
							})
					.on('finished',function(e) {// 完成事件
								$.ligerDialog.confirm("排班完成,是否继续操作","提示信息",function(rtn) {
										if (rtn) {
											window.location.href = __ctx
													+ "/platform/ats/atsTurnShift/wizard.ht";
										} else {
											window.location.href = __ctx
													+ "/platform/ats/atsScheduleShift/list.ht";
										}
									});
							})
					.on('stepclick', function(e, data) {
						// e.preventDefault();//this will prevent clicking and
						// selecting steps
						// return false;
					});
		},
		/**
		 * 第1步的下一步 操作
		 */
		change1next : function() {
			if (!$.isEmpty(_shiftType))// 变换
				_listRowDatas = [];
			_shiftType = $("input[name='shiftType']:checked").val();
			if ($.isEmpty(_shiftType)) {
				$.ligerDialog.alert("请选择排班方式！", "提示信息");
				return false;
			}
		},
		/**
		 * 第2步的下一步 操作
		 */
		change2next : function() {
			_startTime = $('#startTime').val();
			_endTime = $('#endTime').val();
			if ($.isEmpty(_startTime) || $.isEmpty(_endTime)) {
				$.ligerDialog.alert("请设置排班时间！", "提示信息");
				return false;
			}

			// 时间范围不超过一个月
			var startTime1 = me.formatDate(_startTime),
				endTime1 =  me.formatDate(_endTime),
				startTime2 =  me.formatDate(_startTime);
			startTime2.setMonth(startTime2.getMonth() + 1);
			var leftsecond = endTime1.getTime() - startTime1.getTime(), rightsecond = startTime2
					.getTime()
					- startTime1.getTime();
			if (leftsecond > rightsecond) {
				$.ligerDialog.alert("开始日期和结束日期之间的时间间隔不能超过一个月！", "提示信息");
				return false;
			}

			me.clickQueryUser();
		},
		/**
		 * 第3步的下一步 操作
		 */
		change3next : function() {
			if ($('#attencePolicy').val() == "") {
				$.ligerDialog.alert("请选择考勤制度进行查询！", "提示信息");
				return false;
			}
			var userlists = $('#userlist').find(".text-tag");
			if (userlists.length == 0) {
				$.ligerDialog.alert("请选择人员！", "提示信息");
				return false;
			}
			if (_shiftType == AtsConstant.SHIFT_TYPE_CALENDAR) {
				$('#scheduleCalendar').show();
				$('#scheduleList').hide();
			} else {
				$('#scheduleCalendar').hide();
				$('#scheduleList').show();
				var userAry = me.getUserAry();
				$.ajax({
					type : "POST",
					url : __ctx + "/platform/ats/atsTurnShift/scheduleList.ht",
					data : {
						startTime : _startTime,
						endTime : _endTime
					},
					success : function(data) {
						me.initUserScheduleList(data, userAry);
					}
				});
			}
		},
		/**
		 * 第4步的下一步 操作
		 */
		change4next : function() {
			var userAry = me.getUserAry();
			if (userAry.length == 0) {
				$.ligerDialog.alert("请选择人员！", "提示信息");
				return false;
			}
			if (_listRowDatas.length == 0) {
				$.ligerDialog.alert("请进行排班！", "提示信息");
				return false;
			}
			$.ligerDialog.waitting("请稍后……");
			$.ajax({
				type : "POST",
				url : __ctx + "/platform/ats/atsTurnShift/finished.ht",
				async : true,
				data : {
					userAry : JSON2.stringify(userAry),
					listRowDatas : JSON2.stringify(_listRowDatas),
					attencePolicyName : $('#attencePolicyName').val(),
					shiftType : _shiftType
				},
				success : function(data) {
					var d = $.parseJSON(data);
					$("#scheduleShiftGrid").jqGrid("clearGridData");
					$.ligerDialog.closeWaitting();
					if (!d.success) {
						$.ligerDialog.error(d.results, "提示信息");
						return;
					}
					$("#scheduleShiftGrid").jqGrid('setGridParam', {
						data : d.results,
						datatype : "local",
						page : 1
					}).trigger("reloadGrid"); // 重新载入
				}
			});
		},
		initUserScheduleList : function(data, userAry) {
			$.ligerDialog.waitting("请稍后……");
			var colModel = data.colModel;
			for (var i = 0; i < colModel.length; i++) {
				if (i <= 2)
					continue;
				colModel[i].formatter = function(d, options, rowObject) {
					if ($.isEmpty(d))
						return "";
					var title = d.title, backgroundColor = '#337ab7';
					if (d.dateType == 1) {
						backgroundColor = '#337ab7';
					} else if (d.dateType == 2) {
						backgroundColor = '#000000';
					} else if (d.dateType == 3) {
						backgroundColor = '#257e4a';
					}
					return "<span style='background-color: " + backgroundColor
							+ ";color: #fff;cursor:pointer;'>" + d.title
							+ "</span>";
				}
			}
			var options = {
				url : __ctx
						+ "/platform/ats/atsTurnShift/listShiftHandler.ht?startTime="
						+ _startTime + "&endTime=" + _endTime + "&users="
						+ encodeURIComponent(JSON2.stringify(userAry)),
				datatype : "json",
				multiselect : true,
				rownumbers : false,
				colNames : data.colNames,
				colModel : colModel,
				recordpos : 'left',
				gridview : true,
				pginput : true,
				autoheight : true,
				shrinkToFit : data.colModel.length > 10 ? false : true,
				height : 'auto',
				width : document.body.clientWidth - 30,
				viewrecords : false,
				cellEdit : false,
				sortable : false,
				jsonReader : {
					root : "results",// json中代表实际模型数据的入口
					repeatitems : false
				},
				gridComplete : function() {
				},
				onCellSelect : function(rowid, iCol, cellcontent, e) {
					if (iCol > 3 && iCol <= data.colModel.length) {
						var table = $("#list_info");
						var tdObject = $("#" + rowid + ">td")[iCol];
						var colName = data.colModel[iCol - 1].name;
						me.replaceShift({
									start : colName,
									callback : function(rtn, date1) {
										table.setCell(rowid, iCol, rtn);
										$(tdObject).attr('title', rtn.title);
										var rowData = (_listRowDatas[parseInt(rowid) - 1])[colName];
										rowData.title = rtn.title;
										rowData.start = rtn.start;
										rowData.dateType = rtn.dateType;
										rowData.shiftId = rtn.shiftId;
									}
								});
					}
				},
				loadComplete : function(data) {
					$.ligerDialog.closeWaitting();
					_listRowDatas = data.results;
				}
			};
			$('#scheduleList').empty();
			$('#scheduleList').append("<table  id='list_info' > </table>");
			$('#list_info').html();
			$('#list_info').jqGrid(options);
			// $('#list_info').jqGrid('setFrozenColumns');
			var height = (userAry.length + 1) * 33.3, h = document.body.clientHeight - 265;
			if (height > h) {
				height = h;
			}
			height += 'px'
			$('#scheduleList .ui-jqgrid-bdiv').css('height', height).css(
					'width', '100%').css('overflow-y', 'scroll', 'overflow-x',
					'scroll');
			$('#scheduleList .ui-jqgrid .ui-jqgrid-htable th div').css(
					'height', '35px');
			$('#scheduleList .frozen-bdiv').css('top', '36px').css('height',
					'35px');
			$('#scheduleList .frozen-div').css('height', '36px');
		},
		/**
		 * 初始化选择人员的列表
		 */
		initUserGrid : function() {
			$("#userGrid").jqGrid({
				datatype : "json", // 数据来源，本地数据
				mtype : "POST",// 提交方式
				height : document.body.clientHeight - 265,// 高度，表格高度。可为数值、百分比或'auto'
				width : document.body.clientWidth - 30,
				colNames : [ 'ID', '考勤编号', '工号', '姓名', '组织', '岗位' ],
				colModel : [ {
					name : 'id',
					hidden : true
				}, {
					name : 'cardNumber'
				}, {
					name : 'account',
					width : 80
				}, {
					name : 'fullname'
				}, {
					name : 'orgName'
				}, {
					name : 'posName'
				} ],
				multikey : 'id',
				multiselect : true,
				rownumbers : true,// 添加左侧行号
				viewrecords : true,// 是否在浏览导航栏显示记录总数
				rowNum : 100,// 每页显示记录数
				rowList : [ 10, 20, 50, 100, 200 ],// 用于改变显示行数的下拉列表框的元素数组。
				jsonReader : {
					root : "results",// json中代表实际模型数据的入口
					total : "total", // json中代表总页数的数据
					page : "page", // json中代表当前页码的数据
					records : "records",// json中代表数据行总数的数据
					repeatitems : false
				// 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。
				},
				prmNames : {
					page : "page", // 表示请求页码的参数名称
					rows : "pageSize" // 表示请求行数的参数名称
				},
				shrinkToFit : true,
				pager : $('#gridPager'),
				afterInsertRow : function(rowid, rowdata) {
					me.userGridAfterInsertRow(rowid, rowdata);
				},
				onSelectAll : function(rowids, status) {
					me.userGridSelectAll(rowids, status);
				},
				onSelectRow : function(rowid, status) {
					me.userGridSelectRow(rowid, status);
				}
			});

			// 查询
			$("#btn_query").click(function() {
				$("#userGrid").jqGrid('setGridParam', {
					url : __ctx + "/platform/ats/atsTurnShift/userList.ht",
					postData : {
						'Q_attencePolicy_L' : $("#attencePolicy").val(),
						'Q_attenceGroup_L' : $("#attenceGroup").val(),
						'Q_userId_L' : $("#userId").val()
					}, // 发送数据
					page : 1
				}).trigger("reloadGrid"); // 重新载入
			});

		},
		userGridAfterInsertRow : function(rowid, rowdata) {
			var id = $('#userGrid').jqGrid("getCell", rowid, "id");
			$("#userlist").find(".text-tag").each(function() {
				if (this['id'] == id) {
					$('#userGrid').jqGrid("setSelection", rowid, true);
					return;
				}
			});
		},
		userGridSelectAll : function(rowids, status) {
			for (var i = 0; i < rowids.length; i++) {
				me.userGridSelectRow(rowids[i], status);
			}
		},
		userGridSelectRow : function(rowid, status) {
			var id = "", userName = "", orgName = "", cardNumber = "", userlist = $("#userlist"), userGrid = $('#userGrid'), hasName = false;
			id = userGrid.jqGrid("getCell", rowid, "id");
			if (status) {
				account = userGrid.jqGrid("getCell", rowid, "account");
				userName = userGrid.jqGrid("getCell", rowid, "fullname");
				orgName = userGrid.jqGrid("getCell", rowid, "orgName");
				cardNumber = userGrid.jqGrid("getCell", rowid, "cardNumber");
				hasName = false;
				userlist.find(".text-tag").each(function() {
					if (this['id'] == id) {
						hasName = true;
						return;
					}
				});
				value = '[' + cardNumber + ']' + userName;
				if (!hasName) {
					userlist.append('<div class="text-tag" id="' + id
							+ '" account="' + account + '" userName="'
							+ userName + '"  orgName="' + orgName
							+ '" cardNumber="' + cardNumber + '" >'
							+ '<span class="text-label" title="' + value + '">'
							+ value + '</span>'
							+ '<span class="text-remove" title="删除">x</span>'
							+ '</div>');
				}
			} else {
				userlist.find(".text-tag").each(function() {
					if (this['id'] == id) {
						$(this).remove();
						return;
					}
				});
			}
		},
		initOperation : function() {
			$("#userlist").delegate('.text-remove', 'click', function() {
				$(this).parent().remove();
			});
			$('.help-block').click(function() {
				var v = $(this).attr("var");
				$("#" + v).attr("checked", "checked");
			});
		},
		/**
		 * 点击查询用户
		 */
		clickQueryUser : function() {
			$("#btn_query").click();
		},
		/**
		 * 清除用户
		 */
		cleanUserInfo : function() {
			$("#userlist").empty();
		},
		getUserAry : function() {
			var userAry = [];
			$("#userlist").find(".text-tag").each(
				function() {
					var me = $(this), id = me.attr("id"), account = me
							.attr("account"), userName = me
							.attr("userName"), orgName = me
							.attr("orgName"), cardNumber = me
							.attr("cardNumber");
					var obj = {
						id : id,
						account : account,
						userName : userName,
						orgName : orgName,
						cardNumber : cardNumber
					};
					userAry.push(obj);
				});
			return userAry;
		},
		initCalendar : function() {
			$('#calendar_info').fullCalendar({
				header : {
					left : 'title',
					right : 'prev,next'
				},
				height : 600,
				editable : true,
				aspectRatio : 1.35,
				disableDragging : true,
				selectable : true,
				selectHelper : true,
				select : function(start, end) {
					var startDate = start.format('YYYY-MM-DD'), date1 = me
							.formatDate(startDate);
					var events = $('#calendar_info')
							.fullCalendar('clientEvents');
					for (var j = 0; j < events.length; j++) {
						var event = events[j], date = me
								.formatDate(event["start"]
										.format('YYYY-MM-DD'));
						if (date.getTime() == date1.getTime()) {
							me.replaceShiftCalendar(event,
									start, date1);
						}
					}
				},
				eventClick : function(event, e) {
					var start = event.start
							.format('YYYY-MM-DD'), date1 = me
							.formatDate(start);
					me.replaceShiftCalendar(event, start,
									date1);
				}
			});
			$('tr[class="fc-week fc-last"]').css('display', 'none');
		},
		addShiftRule : function() {
			var selectedIds = [];
			if (_shiftType == AtsConstant.SHIFT_TYPE_LIST) {// 列表模式
				selectedIds = $("#list_info").jqGrid('getGridParam',
						'selarrrow');
				if (selectedIds == null || selectedIds.length < 1) {
					$.ligerDialog.alert("请选择要编排人员！", "提示信息");
					return;
				}
			}
			var params = {
				startTime : _startTime,
				endTime : _endTime,
				attencePolicy : $('#attencePolicy').val()
			}, url = __ctx + '/platform/ats/atsShiftRule/setting.ht';
			url = url.getNewUrl();
			DialogUtil.open({
						height : 600,
						width : 800,
						title : '轮班规则',
						url : url,
						isResize : true,
						// 自定义参数
						params : params,
						// 回调函数
						sucCall : function(rtn, beginCol) {
							if (_shiftType == AtsConstant.SHIFT_TYPE_CALENDAR) {// 日历模式
								_listRowDatas = rtn;
								var cal = $('#calendar_info');
								cal.fullCalendar('removeEvents');
								cal.fullCalendar('addEventSource', rtn);
							} else {
								var table = $("#list_info");
								for (var i = 0; i < selectedIds.length; i++) {
									var rowid = selectedIds[i];
									for (var j = 0; j < rtn.length; j++) {
										var iCol = beginCol + j + 3;// 从第3列开始
										var tdObject = $("#" + rowid + ">td")[iCol];
										table.setCell(rowid, iCol, rtn[j]);
										$(tdObject).attr('title', rtn[j].title);
										// 数据处理
										var rowData = (_listRowDatas[parseInt(rowid) - 1])[rtn[j].start];
										rowData.title = rtn[j].title;
										rowData.start = rtn[j].start;
										rowData.dateType = rtn[j].dateType;
										rowData.shiftId = rtn[j].shiftId;
									}
								}
							}
						}
					});
		},
		replaceShiftCalendar : function(event, start, date1) {
			me.replaceShift({
				start : start,
				callback : function(rtn) {
					for (var j = 0; j < _listRowDatas.length; j++) {
						var events = _listRowDatas[j], date = me
								.formatDate(events["start"]);
						if (date.getTime() == date1.getTime()) {
							events.title = rtn.title;
							events.backgroundColor = rtn.backgroundColor;
							events.dateType = rtn.dateType;
							events.shiftId = rtn.shiftId;
							events.start = rtn.start;

							event.title = rtn.title;
							event.backgroundColor = rtn.backgroundColor;
							$('#calendar_info').fullCalendar('updateEvent',
									event);
							break;
						}
					}
				}
			});
		},
		replaceShift : function(conf) {
			var params = {
				start : conf.start
			}, url = __ctx + '/platform/ats/atsShiftInfo/replace.ht?isSingle=true';
			url = url.getNewUrl();
			DialogUtil.open({
				height : 600,
				width : 800,
				title : '选择日期类型和班次',
				url : url,
				isResize : true,
				// 自定义参数
				params : params,
				// 回调函数
				sucCall : function(rtn) {
					conf.callback.call(this, rtn);
				}
			});
		},
		formatDate : function(d) {
			return new Date(d.replace(/-/g, "/"));
		},
		/**
		 * 初始化编排完成的表格
		 */
		initScheduleShiftGrid : function() {
			$("#scheduleShiftGrid").jqGrid(
					{
						datatype : "local", // 数据来源，本地数据
						mtype : "POST",// 提交方式
						height : document.body.clientHeight - 200,
						width : document.body.clientWidth - 30,
						colNames : [ '用户名', '组织名称', '考勤时间', '日期类型', '班次名称',
								'考勤编号', '考勤制度', '取卡规则' ],
						colModel : [ {
							name : 'userName',
							width : 80
						}, {
							name : 'orgName',
							width : 80
						}, {
							name : 'attenceTime',
							width : 80
						}, {
							name : 'dateType',
							width : 80
						}, {
							name : 'shiftName',
							width : 80
						}, {
							name : 'cardNumber',
							width : 80
						}, {
							name : 'policyName',
							width : 80
						}, {
							name : 'cardRuleName',
							width : 80
						} ],
						rownumbers : true,// 添加左侧行号
						viewrecords : true,
						pager : $('#scheduleShiftGridPager')
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
	$('body').AtsTurnShiftWizard();
});

/**
 * 新增排班规则
 */
function addShiftRule() {
	AtsTurnShiftWizardPlugin.addShiftRule();
}
/**
 * 选择考勤制度
 */
function selectAttencePolicy() {
	AtsAttencePolicyDialog({
		isSingle:true,
		callback : function(rtn) {
			$('#attencePolicy').val(rtn.id);
			$('#attencePolicyName').val(rtn.name);
			AtsTurnShiftWizardPlugin.clickQueryUser();
			AtsTurnShiftWizardPlugin.cleanUserInfo();
		}
	});
}

function selectAttenceGroup() {
	AtsAttenceGroupDialog({
		isSingle : true,
		callback : function(rtn) {
			$('#attenceGroup').val(rtn.id);
			$('#attenceGroupName').val(rtn.name);
		}
	});
}

/**
 * 选择用户
 */
function selectUser() {
	UserDialog({
		isSingle : true,
		callback : function(userId, userName) {
			$('#userId').val(userId);
			$('#userName').val(userName);
		}
	});

}