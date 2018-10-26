Date.dayNames = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];

Date.monthNames = ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月",
		"十一月", "十二月"];

Date.monthNumbers = {
	'Jan' : 0,
	'Feb' : 1,
	'Mar' : 2,
	'Apr' : 3,
	'May' : 4,
	'Jun' : 5,
	'Jul' : 6,
	'Aug' : 7,
	'Sep' : 8,
	'Oct' : 9,
	'Nov' : 10,
	'Dec' : 11
};

Date.getShortMonthName = function(month) {
	return Date.monthNames[month].substring(0, 3);
};

Date.getShortDayName = function(day) {
	return Date.dayNames[day].substring(0, 3);
};

Date.getMonthNumber = function(name) {
	return Date.monthNumbers[name.substring(0, 1).toUpperCase()
			+ name.substring(1, 3).toLowerCase()];
};

// Date.parseCodes.S.s = '(?:st|nd|rd|th)';

if (Ext.picker.Picker) {
	Ext.override(Ext.picker.Picker, {
				doneText : '确定',
				doneButton:'确定'
			});
}

/**
 * 处理选择汉化
 * 
 * @type
 */
var defaultPhonePickerConfig = {
	doneButton : '确定',
	cancelButton : '取消'
};

if (Ext.picker.Date) {
	Ext.override(Ext.picker.Date, {
				'dayText' : '日',
				'monthText' : '月',
				'yearText' : '年',
				'slotOrder' : ['月', '日', '年']
			});
}

if (Ext.IndexBar) {
	Ext.override(Ext.IndexBar, {
				'letters' : ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
						'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
						'V', 'W', 'X', 'Y', 'Z']
			});
}

if (Ext.NestedList) {
	Ext.override(Ext.NestedList, {
				'backText' : '返回',
				'loadingText' : '加载中...',
				'emptyText' : '没有匹配的数据.'
			});
}

if (Ext.util.Format) {
	Ext.util.Format.defaultDateFormat = 'y年m月d日';
}

if (Ext.MessageBox) {
	Ext.MessageBox.OK.text = '确定';
	Ext.MessageBox.CANCEL.text = '取消';
	Ext.MessageBox.YES.text = '是';
	Ext.MessageBox.NO.text = '否';

	Ext.MessageBox.OKCANCEL = [{
				text : '取消',
				itemId : 'cancel'
			}, {
				text : '确定',
				itemId : 'ok',
				ui : 'action'
			}];
	Ext.MessageBox.YESNO = [{
				text : '否',
				itemId : 'no'
			}, {
				text : '是',
				itemId : 'yes',
				ui : 'action'
			}]
}

var __lang = 'zh_CN';

// 系统国际化
Ext.ns('lang');
lang = {
	unsupported : "当前的浏览器不支持.\n\n支持浏览器：\n",
	defaultBackButtonText : '返回',
	switchLang : '切换语言',
	operate : {
		msgTip : '提示',
		searchPlaceHolder : ' 搜索...',
		button : {
			cancel : '取消',
			save : '保存',
			ok : '确定',
			clean : '清空'
		}
	},
	tip : {
		loadingText : '加载中……',
		noRecords : '没有找到记录',
		pullRefreshText : '下拉可以更新',
		releaseRefreshText : '松开开始更新',
		loading : '正在刷新……',
		loadMoreText : '更多……',
		lastUpdatedText : '上次刷新:',
		noMoreRecordsText : '没有更多条记录了',
		waitMsg : '正在提交数据……',
		successMsg : '信息成功保存!',
		failureMsg : '信息保存出错，请联系管理员!'
		
	},
	response : {
		timeout : '请求超时,请重新登录!',
		accessRight : '系统访问权限提示',
		notRight : '您目前没有权限访问：{0}',
		error : '出错了，请联系管理员!'
	},
	system : {
		title : 'BPM X3业务管理移动平台',
		menu : '功能菜单'
	},
	login : {
		username : '账号',
		password : '密码',
		login : '登录',
		reset : '重置'
	},
	logout:{
		text:"退出",
		tip:"您确定退出吗？"
	},
	menus : {
		pendingMatters : '待办事宜',
		alreadyMatters : '已办事宜',
		completedMatters : '办结事宜',
		taskExe : '转办(代理)事宜',
		newProcess : '新建流程',
		myDraftList : '我的草稿',
		myRequest : '我的请求',
		myCompleted : '我的办结',
		myStartTask : '我发起的事项',
		myUndertakeTask : '我承接的事项'
	},
	tableForm : {
		option : '审批意见',
		feedback : '沟通反馈',
		cancelAgency : '取消代理',
		cancelTransmitting : '取消转办',
		tableDetail : '表单明细',
		flowTable : '流程表单',
		userImage : '流程图',
		taskOpinions : '审批历史',
		button : {
			directComlete : '直接结束',
			agree : '同意',
			notAgree : '反对',
			abandon : '弃权',
			retoactive : '补签',
			saveForm : '保存表单',
			changeAssignee : '转办',
			reject : '驳回',
			rejectToStart : '驳回发起人',
			communication : '沟通意见',
			recover : '撤销',
			urge : '催办',
			reSubmit : '重新提交',
			del : '删除',
			copy : '复制',
			divert : '转发',
			copyUser : '抄送人'
		},
		msg : {
			execute : '执行任务成功!',
			reject : '驳回任务成功!',
			rejectToStart : '驳回到发起人成功!',
			option : '请填写意见!'
		},
		retoactive : {
			title : '补签',
			signUserIds : '补签人ID',
			signFullnames : '补签人',
			button : '补签',
			msg : '请选择补签人!'
		},
		changeAssignee : {
			title : '转办',
			assigneeId : '转办人ID',
			assigneeName : '转办人',
			memo : '转办原因',
			msg : {
				assigneeName : '请选择转办人!',
				memo : '请填写转办原因!'
			}
		},
		communication : {
			title : '沟通意见',
			cmpIds : '沟通人ID',
			cmpNames : '沟通人',
			opinion : '内容',
			msg : {
				assigneeName : '请选择沟通人!',
				opinion : '请填写沟通内容!'
			}
		},
		toTaskNotify : {
			title : '沟通反馈',
			msg : '请填写沟通反馈意见!'
		},
		cancelTaskExe : {
			msg : '请填写原因!'
		},
		copyUser : {
			title : '抄送人',
			readed : '已读',
			noReaded : '未读',
			copyTo : '抄送',
			divert : '转发',
			ccUname : '接受人',
			posName : '岗位',
			orgName : '部门',
			isReaded : '是否已读',
			ccTime : '接受时间',
			cpType : '类型'
		}
	},
	grade : {
		high : '高',
		middle : '中',
		low : '低'
	},
	taskState : {
		notStarted : '未开始',
		inProgress : '进行中',
		discontinued : '已终止',
		completed : '已完成',
		rated : '已评价'
	},

	taskExe : {
		status : {
			init : '初始状态',
			completed : '完成任务',
			cancelAgency : '取消代理',
			cancelTransmitting : '取消转办',
			otherCompleted : '其他人完成'
		}
	},
	taskOpinions : {
		title : '审批历史记录',
		emptyText : '没有审批历史记录',
		opinion : '意见',
		status : '状态',
		receiver : '接收人',
		startTime : '开始时间',
		exeFullname : '执行人'
	},
	userImage : {
		init : '未执行',
		agree : '同意',
		abandon : '弃权',
		curNode : '当前节点',
		notAgree : '反对',
		reject : '驳回',
		recover : '撤销',
		signPassed : '会签通过',
		signNotPassed : '会签不通过',
		endProcess : '人工终止',
		nodeName : '节点名称',
		nodeTitle : '节点明细',
		task : {
			title : '任务执行情况',
			taskName : '任务名称',
			exeFullname : '执行人',
			candidateUser : '候选人',
			startTime : '开始时间',
			endTime : '结束时间',
			durTime : '时长',
			status : '状态',
			opinion : '意见'
		},
		subFlow : {
			title : '子流程'
		}
	},
	receiver : {
		noRecords : '暂无接收人记录',
		receivername : '接收人',
		statu : '状态',
		status : {
			init : '初始',
			read : '已阅',
			communication : '已沟通'
		},
		createtime : '创建时间',
		receivetime : '接收时间',
		feedbacktime : '反馈时间',
		title : '沟通接收人'
	},
	taskStatus : {
		pending : '待办',
		transmitting : '转办',
		communication : '沟通意见',
		recover : '撤销',
		reject : '驳回',
		rejectToStart : '驳回发起人',
		cancelAgency : '取消代理',
		agency : '代理',
		cancelTransmitting : '取消转办'
	},
	processStatus : {
		suspend : '挂起状态 ',
		running : '审批中',
		finish : '已归档',
		manualFinish : '已终止',
		recover : '已撤销',
		reject : '已驳回',
		recoverToStart : '追回发起人',
		rejectToStart : '驳回发起人',
		transmitting : '转办',
		agency : '代理',
		del : '逻辑删除',
		restartTask:'重启任务'
	},
	assignType : {
		agency : '代理',
		transmitting : '转办'
	},
	tasTaskForm : {
		toolbar : {
			done : '终止',
			deal : '处理',
			dialogue : '会话',
			comment : '评价',
			queryComment : '查看评价'
		},
		title : '事项明细',
		taskname : '事项名称',
		grade : '紧急程度',
		fiveelementName : '五元素三级',
		startUser : '发起人',
		handleuser : '承接人',
		superviseuser : '监督人',
		planstarttime : '计划开始时间',
		planendtime : '计划结束时间',
		factstarttime : '实际开始时间',
		factendtime : '实际结束时间',
		warningtime : '预警时间',
		attachment : '事项附件',
		stopidea : '终止意见',
		taskDoneIdea : '办结意见',
		taskdesc : '事项内容',
		createName : '创建人',
		createtime : '创建时间',
		occupy : "此事项已被其他人占用!",
		done : {
			tip : {
				discontinued : "此事项已终止不能再终止!",
				completed : "事项已办结不能再终止!",
				comment : "此事项已评价不能再终止!"
			},
			title : '终止事项',
			stopidea : '终止意见',
			done : '终止',
			inputStopidea : '请填写终止意见!',
			confirm : '确认终止吗？',
			success : '终止事项成功!'
		},
		deal : {
			tip : {
				discontinued : "此事项经终止不能再办结!",
				completed : "此事项已办结!",
				comment : '此事项已评价不能再办结!'
			},
			title : '处理事项',
			taskDoneIdea : '办结意见',
			deal : '办结',
			inputTaskDoneIdea : '请填写办结意见!',
			confirm : '确认要办结事项吗？',
			success : '办结事项成功!'
		},
		dialogue : {
			tip : {
				discontinued : "事项已终止,不能再会话!",
				completed : "事项已完成,不能再会话!",
				comment : "事项已评价,不能再会话!"
			}
		},
		comment : {
			tip : {
				discontinued : "事项已终止,不能再评价!",
				comment : "事项已评价!"
			},
			title : '评价事项',
			grade : '评价等级',
			content : '评价意见',
			inputGrade : '请选择评价等级!',
			inputContent : '请填写评价意见!',
			confirm : '是否确认评价？',
			success : '评价事项成功!'
		},
		queryComment : {
			tip : {
				noComment : "事项未有评价!"
			},
			message : '暂无评价记录!',
			title : '查看评价',
			grade : '评价等级',
			content : '评价意见',
			commentDate : '评价时间'
		}
	},
	tasTaskDialogue : {
		startUser : '发起人',
		handleUser : '承接人',
		title : '会话',
		conoUser : '会话人',
		sendMessage : '发送消息',
		inputMessage : '请输入发送消息!',
		inputSelectUser : '请选择发送人',
		inputOwerMessage : '不能给自己发送消息'
	}
}
