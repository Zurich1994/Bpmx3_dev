Date.dayNames = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday',
		'Friday', 'Saturday'];

Date.monthNames = ['January', 'February', 'March', 'April', 'May', 'June',
		'July', 'August', 'September', 'October', 'November', 'December'];

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
				doneText : 'Done'
			});
}

/**
 * 处理选择汉化
 * 
 * @type
 */
var defaultPhonePickerConfig = {
	doneButton : 'done',
	cancelButton : 'cancel'
};

if (Ext.picker.Date) {
	Ext.override(Ext.picker.Date, {
				'dayText' : 'Day',
				'monthText' : 'Month',
				'yearText' : 'Year',
				'slotOrder' : ['month', 'day', 'year']
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
				'backText' : 'Back',
				'loadingText' : 'Loading...',
				'emptyText' : 'No items available.'
			});
}

if (Ext.util.Format) {
	Ext.util.Format.defaultDateFormat = 'm/d/Y';
}

if (Ext.MessageBox) {
	Ext.MessageBox.OK.text = 'OK';
	Ext.MessageBox.CANCEL.text = 'Cancel';
	Ext.MessageBox.YES.text = 'Yes';
	Ext.MessageBox.NO.text = 'No';

	Ext.MessageBox.OKCANCEL = [{
				text : 'Cancel',
				itemId : 'cancel'
			}, {
				text : 'OK',
				itemId : 'ok',
				ui : 'action'
			}];
	Ext.MessageBox.YESNO = [{
				text : 'NO',
				itemId : 'no'
			}, {
				text : 'Yes',
				itemId : 'yes',
				ui : 'action'
			}]
}

var __lang = 'en_US';

// System International
Ext.ns('lang');
lang = {
	unsupported : "Your current browser does not support this website. \n \n Supporting browser: \n",
	defaultBackButtonText : 'Back',
	switchLang : 'Switch languages',
	operate : {
		msgTip : 'Attention',
		searchPlaceHolder : 'Search ...',
		button : {
			cancel : 'Cancel',
			save : 'Save',
			ok : 'OK',
			clean : 'Clear'
		}
	},
	tip : {
		loadingText : 'Loading ......',
		noRecords : 'No Result',
		pullRefreshText : 'Pull down to refresh...',
		releaseRefreshText : 'Release to refresh...',
		loading : 'Loading……',
		loadMoreText : 'Load More ......',
		lastUpdatedText : 'Last Updated:',
		noMoreRecordsText : 'No More Records',
		waitMsg : 'Submitting data…',
		successMsg : 'Information saved successfully!',
		failureMsg : 'Information could not be saved , please contact the administrator!'
	},
	response : {
		timeout : 'Request timeout, please login again!',
		accessRight : 'System access notification',
		notRight : 'You currently have no access: {0}',
		error : 'Error, please contact the administrator!'
	},
	system : {
		title : 'BPM X3业务管理移动平台',
		menu : 'Function Menu'
	},
	login : {
		username : 'Account',
		password : 'Password',
		login : 'Login',
		reset : 'Reset'
	},
	logout:{
		text: "Exit",
		tip: "Are you sure you want to exit?"
	},
	menus : {
		pendingMatters : 'Pending',
		alreadyMatters : 'Handled',
		completedMatters : 'Completed',
		taskExe : 'Transferred/Delegated',
		newProcess : 'New Workflow',
		myDraftList : 'My Draft',
		myRequest : 'My Request',
		myCompleted : 'My Completed',
		myStartTask : 'My Initiated Task',
		myUndertakeTask : 'My Task'
	},
	tableForm : {
		option : 'Opionions',
		feedback : 'Feedback',
		cancelAgency : 'Cancel proxy',
		cancelTransmitting : 'Cancel transfer',
		tableDetail : 'Form Details',
		flowTable : 'Form',
		userImage : 'Flowchart',
		taskOpinions : 'History',
		button : {
			directComlete : 'End immediately',
			agree : 'Approve',
			notAgree : 'Object',
			abandon : 'Abstain',
			retoactive : 'Supplementary signature',
			saveForm : 'Save',
			changeAssignee : 'Transfer',
			reject : 'Reject',
			rejectToStart : 'Reject and return to initiator',
			communication : 'Communicate',
			recover : 'Cancel',
			urge : 'Urge',
			reSubmit : 'Re-submit',
			del : 'Delete',
			copy : 'Copy',
			divert : 'Forward',
			copyUser : 'Cc'
		},
		msg : {
			execute : 'Task execution successful!',
			reject : 'Task rejection successful!',
			rejectToStart : 'Reject and return to initiator successful!',
			option : 'Please fill opinions!'
		},
		retoactive : {
			title : 'Supplementary signature',
			signUserIds : 'Supplementary signer ID',
			signFullnames : 'Supplementary signer',
			button : 'Supplementary signature',
			msg : 'Please choose the supplementary signer!'
		},
		changeAssignee : {
			title : 'Transfer',
			assigneeId : 'Transferee ID',
			assigneeName : 'Transferee ',
			memo : 'Reason',
			msg : {
				assigneeName : 'Please choose the transferee!',
				memo : 'Please write your reason for transfer!'
			}
		},
		communication : {
			title : 'Communicate',
			cmpIds : "Contact person' s ID",
			cmpNames : 'Contact person ',
			opinion : 'Contents',
			msg : {
				assigneeName : 'Please choose the contact person!',
				opinion : 'Please write the contents!'
			}
		},
		toTaskNotify : {
			title : 'Communicate & Feedback',
			msg : 'Please write the suggestions from communication and feedbacks!'
		},
		cancelTaskExe : {
			msg : 'Please write the reason!'
		},
		copyUser : {
			title : 'Cc',
			readed : 'Read',
			noReaded : 'Unread',
			copyTo : 'Cc',
			divert : 'Forward',
			ccUname : 'Recipient',
			posName : 'Job position',
			orgName : 'Department',
			isReaded : 'Read/Unread',
			ccTime : 'Time received',
			cpType : 'Type'
		}
	},
	grade : {
		high : 'High',
		middle : 'Medium',
		low : 'Low'
	},
	taskState : {
		notStarted : 'Not started',
		inProgress : 'In process',
		discontinued : 'Terminated',
		completed : 'Completed',
		rated : 'Evaluated'
	},

	taskExe : {
		status : {
			init : 'Default setting',
			completed : 'Complete task',
			cancelAgency : 'Cancel proxy',
			cancelTransmitting : 'Cancel Transmitting',
			otherCompleted : 'Completed by others'
		}
	},
	taskOpinions : {
		title : 'Approval History',
		emptyText : 'No histroy record.',
		opinion : 'Suggestions',
		status : 'Status',
		receiver : 'Receiver',
		startTime : 'Start time',
		exeFullname : 'Executor'
	},
	userImage : {
		init : 'Not executed',
		agree : 'Approve',
		abandon : 'Abstain',
		curNode : 'Current node',
		notAgree : 'Object',
		reject : 'Reject',
		recover : 'Cancel',
		signPassed : 'Countersignature approved',
		signNotPassed : 'Countersignature not approved',
		endProcess : 'Terminate manually ',
		nodeName : 'Name of node',
		nodeTitle : 'Node details',
		task : {
			title : 'Task status',
			taskName : 'Task Name',
			exeFullname : 'Executor',
			candidateUser : 'Candidate',
			startTime : 'Start time',
			endTime : 'End time',
			durTime : 'Duration',
			status : 'Status',
			opinion : 'Suggestions'
		},
		subFlow : {
			title : 'Sub-workflow'
		}
	},
	receiver : {
		noRecords : 'No receiver record',
		receivername : 'Receiver',
		statu : 'Status',
		status : {
			init : 'Default',
			read : 'Read',
			communication : 'Communicated'
		},
		createtime : 'Time created ',
		receivetime : 'Time received ',
		feedbacktime : 'Feedback time',
		title : 'receiver'
	},
	taskStatus : {
		pending : 'Pending',
		transmitting : 'Transfer',
		communication : 'Communicate',
		recover : 'Cancel',
		reject : 'Reject',
		rejectToStart : 'Reject and return to the initiator',
		cancelAgency : 'Cancel proxy',
		agency : 'Proxy',
		cancelTransmitting : 'Cancel transfer'
	},
	processStatus : {
		suspend : 'Suspended',
		running : 'Waiting for approval',
		finish : 'Archived',
		manualFinish : 'Terminated',
		recover : 'Canceled',
		reject : 'Rejected',
		recoverToStart : 'Cancelled by the initiator',
		rejectToStart : 'Reject and return to the initiator',
		transmitting : 'Transfer',
		agency : 'Proxy',
		del : 'Logical deletion',
		restartTask: 'Restart task'
	},
	assignType : {
		agency : 'Proxy',
		transmitting : 'Transfer'
	},
	tasTaskForm : {
		toolbar : {
			done : 'Terminate',
			deal : 'Handle',
			dialogue : 'Conversation',
			comment : 'Evaluate',
			queryComment : 'View evaluation'
		},
		title : 'Task details',
		taskname : 'Task name',
		grade : 'Priority level',
		fiveelementName : 'Five elements Grade',
		startUser : 'Initiator',
		handleuser : 'Handler',
		superviseuser : 'Supervisor',
		planstarttime : 'Estimated start time',
		planendtime : 'Estimated completion time',
		factstarttime : 'Actual start time',
		factendtime : 'Actual completion time',
		warningtime : 'Reminder time',
		attachment : 'Attachment',
		stopidea : 'Reason for termination ',
		taskDoneIdea : 'Opinion upon completion',
		taskdesc : 'Contents',
		createName : 'creator',
		createtime : 'Time created ',
		occupy : "The task had already been undertaken by others!",
		done : {
			tip : {
				discontinued : "The task had already been terminated and cannot be terminated again!",
				completed : "The task had already been completed and cannot be terminated!",
				comment : "The task had already been evaluated and cannot be terminated!"
			},
			title : 'Terminated task',
			stopidea : 'Reasons for termination',
			done : 'Terminate',
			inputStopidea : 'Please write the reasons for termination!',
			confirm : 'Are you sure you want to terminate it?',
			success : 'Termination successful!'
		},
		deal : {
			tip : {
				discontinued : "The task had already been terminated and cannot be completed!",
				completed : "The task had already been completed!",
				comment : 'The task had already been evaluated and cannot be completed!'
			},
			title : 'Task handled',
			taskDoneIdea : 'Opinions upon completion',
			deal : 'Completed',
			inputTaskDoneIdea : 'Please write your opinions upon completion!',
			confirm : 'Are you sure you want to complete the task?',
			success : 'Task completion successful!'
		},
		dialogue : {
			tip : {
				discontinued : "The task had already been terminated, unable to leave comments!",
				completed : "The task had already been completed, unable to leave comments!",
				comment : "The task had already been evaluated, unable to leave comments!"
			}
		},
		comment : {
			tip : {
				discontinued : "The task had already been terminated, unable to evaluated!",
				comment : "The task had already been evaluated!"
			},
			title : 'Evaluated task',
			grade : 'Grade',
			content : 'Suggestions',
			inputGrade : 'Please grade!',
			inputContent : 'Please write the suggestions',
			confirm : 'Are you sure evaluation? ',
			success : 'Evaluation successful!'
		},
		queryComment : {
			tip : {
				noComment : "Task not evaluated!"
			},
			message : 'No evaluation record!',
			title : 'View evaluation',
			grade : 'Grade',
			content : 'Suggestions ',
			commentDate : 'Time of evaluation'
		}
	},
	tasTaskDialogue : {
		startUser : 'Initiator',
		handleUser : 'Handler',
		title : 'Conversation',
		conoUser : 'Commentator',
		sendMessage : 'Send message',
		inputMessage : 'Please enter the message!',
		inputSelectUser : 'Please choose the sender!',
		inputOwerMessage : 'You cannot send message to yourself'
	}
}