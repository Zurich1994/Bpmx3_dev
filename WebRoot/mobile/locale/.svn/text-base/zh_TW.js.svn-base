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
				doneText : '確定'
			});
}

/**
 *  * 處理選擇漢化  *  *
 * 
 * @type  
 */
var defaultPhonePickerConfig = {
	doneButton : '確定',
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
				'loadingText' : '加載中...',
				'emptyText' : '沒有匹配的數據.'
			});
}

if (Ext.util.Format) {
	Ext.util.Format.defaultDateFormat = 'y年m月d日';
}

if (Ext.MessageBox) {
	Ext.MessageBox.OK.text = '確定';
	Ext.MessageBox.CANCEL.text = '取消';
	Ext.MessageBox.YES.text = '是';
	Ext.MessageBox.NO.text = '否';

	Ext.MessageBox.OKCANCEL = [{
				text : '取消',
				itemId : 'cancel'
			}, {
				text : '確定',
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

var __lang = 'zh_TW';

// 系統國際化
Ext.ns('lang');
lang = {
	unsupported : "當前的瀏覽器不支持.\n\n支持瀏覽器：\n",
	defaultBackButtonText : '返回',
	switchLang : '切換語言',
	operate : {
		msgTip : '提示',
		searchPlaceHolder : ' 搜索...',
		button : {
			cancel : '取消',
			save : '保存',
			ok : '確定',
			clean : '清空'
		}
	},
	tip : {
		loadingText : '加載中……',
		noRecords : '沒有找到記錄',
		pullRefreshText : '下拉可以更新',
		releaseRefreshText : '鬆開開始更新',
		loading : '正在刷新……',
		loadMoreText : '更多……',
		lastUpdatedText : '上次刷新:',
		noMoreRecordsText : '沒有更多條記錄了',
		waitMsg : '正在提交數據……',
		successMsg : '信息成功保存!',
		failureMsg : '信息保存出錯，請聯繫管理員!'
	},
	response : {
		timeout : '請求超時,請重新登錄!',
		accessRight : '系統訪問權限提示',
		notRight : '您目前沒有權限訪問：{0}',
		error : '出錯了，請聯繫管理員!'
	},
	system : {
		title : 'BPM X3业务管理移动平台',
		menu : '功能菜單'
	},
	login : {
		username : '賬號',
		password : '密碼',
		login : '登錄',
		reset : '重置'
	},
	logout:{
		text:"退出",
		tip:"您確定退出嗎？"
	},
	menus : {
		pendingMatters : '待辦事宜',
		alreadyMatters : '已辦事宜',
		completedMatters : '辦結事宜',
		taskExe : '轉辦(代理)事宜',
		newProcess : '新建流程',
		myDraftList : '我的草稿',
		myRequest : '我的請求',
		myCompleted : '我的辦結',
		myStartTask : '我發起的事項',
		myUndertakeTask : '我承接的事項'
	},
	tableForm : {
		option : '審批意見',
		feedback : '溝通反饋',
		cancelAgency : '取消代理',
		cancelTransmitting : '取消轉辦',
		tableDetail : '表單明細',
		flowTable : '流程表單',
		userImage : '流程圖',
		taskOpinions : '審批歷史',
		button : {
			directComlete : '直接結束',
			agree : '同意',
			notAgree : '反對',
			abandon : '棄權',
			retoactive : '補簽',
			saveForm : '保存表單',
			changeAssignee : '轉辦',
			reject : '駁回',
			rejectToStart : '駁回發起人',
			communication : '溝通意見',
			recover : '撤銷',
			urge : '催辦',
			reSubmit : '重新提交',
			del : '刪除',
			copy : '複製',
			divert : '轉發',
			copyUser : '抄送人'
		},
		msg : {
			execute : '執行任務成功!',
			reject : '駁回任務成功!',
			rejectToStart : '駁回到發起人成功!',
			option : '請填寫意見!'
		},
		retoactive : {
			title : '補簽',
			signUserIds : '補簽人ID',
			signFullnames : '補簽人',
			button : '補簽',
			msg : '請選擇補簽人!'
		},
		changeAssignee : {
			title : '轉辦',
			assigneeId : '轉辦人ID',
			assigneeName : '轉辦人',
			memo : '轉辦原因',
			msg : {
				assigneeName : '請選擇轉辦人!',
				memo : '請填寫轉辦原因!'
			}
		},
		communication : {
			title : '溝通意見',
			cmpIds : '溝通人ID',
			cmpNames : '溝通人',
			opinion : '內容',
			msg : {
				assigneeName : '請選擇溝通人!',
				opinion : '請填寫溝通內容!'
			}
		},
		toTaskNotify : {
			title : '溝通反饋',
			msg : '請填寫溝通反饋意見!'
		},
		cancelTaskExe : {
			msg : '請填寫原因!'
		},
		copyUser : {
			title : '抄送人',
			readed : '已讀',
			noReaded : '未讀',
			copyTo : '抄送',
			divert : '轉發',
			ccUname : '接受人',
			posName : '崗位',
			orgName : '部門',
			isReaded : '是否已讀',
			ccTime : '接受時間',
			cpType : '類型'
		}
	},
	grade : {
		high : '高',
		middle : '中',
		low : '低'
	},
	taskState : {
		notStarted : '未開始',
		inProgress : '進行中',
		discontinued : '已終止',
		completed : '已完成',
		rated : '已評價'
	},

	taskExe : {
		status : {
			init : '初始狀態',
			completed : '完成任務',
			cancelAgency : '取消代理',
			cancelTransmitting : '取消轉辦',
			otherCompleted : '其他人完成'
		}
	},
	taskOpinions : {
		title : '審批歷史記錄',
		emptyText : '沒有審批歷史記錄',
		opinion : '意見',
		status : '狀態',
		receiver : '接收人',
		startTime : '開始時間',
		exeFullname : '執行人'
	},
	userImage : {
		init : '未執行',
		agree : '同意',
		abandon : '棄權',
		curNode : '當前節點',
		notAgree : '反對',
		reject : '駁回',
		recover : '撤銷',
		signPassed : '會簽通過',
		signNotPassed : '會簽不通過',
		endProcess : '人工終止',
		nodeName : '節點名稱',
		nodeTitle : '節點明細',
		task : {
			title : '任務執行情況',
			taskName : '任務名稱',
			exeFullname : '執行人',
			candidateUser : '候選人',
			startTime : '開始時間',
			endTime : '結束時間',
			durTime : '時長',
			status : '狀態',
			opinion : '意見'
		},
		subFlow : {
			title : '子流程'
		}
	},
	receiver : {
		noRecords : '暫無接收人記錄',
		receivername : '接收人',
		statu : '狀態',
		status : {
			init : '初始',
			read : '已閱',
			communication : '已溝通'
		},
		createtime : '創建時間',
		receivetime : '接收時間',
		feedbacktime : '反饋時間',
		title : '溝通接收人'
	},
	taskStatus : {
		pending : '待辦',
		transmitting : '轉辦',
		communication : '溝通意見',
		recover : '撤銷',
		reject : '駁回',
		rejectToStart : '駁回發起人',
		cancelAgency : '取消代理',
		agency : '代理',
		cancelTransmitting : '取消轉辦'
	},
	processStatus : {
		suspend : '掛起狀態 ',
		running : '審批中',
		finish : '已歸檔',
		manualFinish : '已終止',
		recover : '已撤銷',
		reject : '已駁回',
		recoverToStart : '追回發起人',
		rejectToStart : '駁回發起人',
		transmitting : '轉辦',
		agency : '代理',
		del : '邏輯刪除',
		restartTask:'重啟任務'
	},
	assignType : {
		agency : '代理',
		transmitting : '轉辦'
	},
	tasTaskForm : {
		toolbar : {
			done : '終止',
			deal : '處理',
			dialogue : '會話',
			comment : '評價',
			queryComment : '查看評價'
		},
		title : '事項明細',
		taskname : '事項名稱',
		grade : '緊急程度',
		fiveelementName : '五元素三級',
		startUser : '發起人',
		handleuser : '承接人',
		superviseuser : '監督人',
		planstarttime : '計劃開始時間',
		planendtime : '計劃結束時間',
		factstarttime : '實際開始時間',
		factendtime : '實際結束時間',
		warningtime : '預警時間',
		attachment : '事項附件',
		stopidea : '終止意見',
		taskDoneIdea : '辦結意見',
		taskdesc : '事項內容',
		createName : '創建人',
		createtime : '創建時間',
		occupy : "此事項已被其他人佔用!",
		done : {
			tip : {
				discontinued : "此事項已終止不能再終止!",
				completed : "事項已辦結不能再終止!",
				comment : "此事項已評價不能再終止!"
			},
			title : '終止事項',
			stopidea : '終止意見',
			done : '終止',
			inputStopidea : '請填寫終止意見!',
			confirm : '確認終止嗎？ ',
			success : '終止事項成功!'
		},
		deal : {
			tip : {
				discontinued : "此事項經終止不能再辦結!",
				completed : "此事項已辦結!",
				comment : '此事項已評價不能再辦結!'
			},
			title : '處理事項',
			taskDoneIdea : '辦結意見',
			deal : '辦結',
			inputTaskDoneIdea : '請填寫辦結意見!',
			confirm : '確認要辦結事項嗎？ ',
			success : '辦結事項成功!'
		},
		dialogue : {
			tip : {
				discontinued : "事項已終止,不能再會話!",
				completed : "事項已完成,不能再會話!",
				comment : "事項已評價,不能再會話!"
			}
		},
		comment : {
			tip : {
				discontinued : "事項已終止,不能再評價!",
				comment : "事項已評價!"
			},
			title : '評價事項',
			grade : '評價等級',
			content : '評價意見',
			inputGrade : '請選擇評價等級!',
			inputContent : '請填寫評價意見!',
			confirm : '是否確認評價？ ',
			success : '評價事項成功!'
		},
		queryComment : {
			tip : {
				noComment : "事項未有評價!"
			},
			message : '暫無評價記錄!',
			title : '查看評價',
			grade : '評價等級',
			content : '評價意見',
			commentDate : '評價時間'
		}
	},
	tasTaskDialogue : {
		startUser : '發起人',
		handleUser : '承接人',
		title : '會話',
		conoUser : '會話人',
		sendMessage : '發送消息',
		inputMessage : '請輸入發送消息!',
		inputSelectUser : '請選擇發送人',
		inputOwerMessage : '不能給自己發送消息'
	}
}