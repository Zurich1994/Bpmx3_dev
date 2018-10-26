
/**
 * 全局变量和公共方法
 * 
 * @author zxh
 * @date 2013-06-04
 */

Ext.ns('HTUtil');
/**
 * 是否查看流程图
 */
var isUserImage = false;



/**
 * 退出
 */
HTUtil.logout = function() {
	Ext.Msg.confirm(lang.operate.msgTip, lang.logout.tip, function(buttonId) {
		if (buttonId == 'yes') {
				mobileView.setMasked(true);
				Ext.Ajax.request({
							url : __ctx
									+ '/mobileLogout.ht'
						});
				 window.location.reload();
			// window.opener=null;
			// window.open('','_self');
			// window.close();
			// Ext.Ajax.request({
			// url : 'mobileLogout.ht',
			// scope : this,
			// success : function(response) {
			//							
			// },
			// failure : function(response) {
			// var obj = Ext.util.JSON.decode(response.responseText);
			// Ext.Msg.alert('', obj.msg);
			// }
			// });
		}

	});
};

/**
 * 获取流程表单
 * 
 * @param {}
 * 
 * <pre>
 * conf
 * {runId:runId} 必须有     
 *     folwType：-1 ： 表示无效的类型 
 *     			 0 ：表示代理 如果有这个字段，必须有taskExeId的值
 *     			 1:  表示转办 如果有这个字段，必须有taskExeId的值
 *            	 2 : 表示我的请求
 *            	 3 : 表示我的办结
 *            	 4 : 表示办结事宜
 * </pre>
 */
HTUtil.getFolwForm = function(conf) {
	var scope = conf.scope ? conf.scope : this, runId = conf.runId, folwType = (Ext
			.isEmpty(conf.folwType) ? -1 : conf.folwType), taskExeId = (Ext
			.isEmpty(conf.taskExeId) ? '' : conf.taskExeId), processRun = conf.processRun
			? conf.processRun
			: '';

	HT.loadMask();
	Ext.Ajax.request({
		url : __ctx + '/platform/mobile/mobileTask/getFolwForm.ht',
		params : {
			runId : runId
		},
		scope : scope,
		success : function(response) {
			HT.unMask();
			var me = this;
			var result = Ext.util.JSON.decode(response.responseText);
			var form = result.form, formfields = [], subTables = [];

			if (form.fields.length != 0)
				formfields = Ext.util.JSON.decode(form.fields);
			if (form.subTableList.length != 0)
				subTables = form.subTableList;

			mobileView.push(Ext.create('mobile.form.TableForm', {
				form : form,
				extForm : form.extForm,
				formDetailUrl : form.formDetailUrl,
				formEditUrl : form.formEditUrl,
				mainFields : formfields,
				subTables : subTables,
				runId : runId,
				processRun : processRun,
				folwType : folwType,// 流程类型
				taskExeId : taskExeId,// 转办、代理
				notApprove : true,
				moblieImage : result.moblieImage,
				callback : function() {
					me.store.load();
				}
			}));
		},
		failure : function(response) {
			HT.unMask();
			var result = Ext.util.JSON.decode(response.responseText);
			Ext.Msg.alert(lang.operate.msgTip, result.msg);
			this.store.loadPage(1);
		}
	});
};

/**
 * 接收人
 * 
 * @param {}
 *            opinionId
 */
HTUtil.receiver = function(opinionId) {
	if (Ext.isEmpty(opinionId))
		return;
	Ext.define('taskOpinionItem', {
		extend : 'Ext.data.Model',
		config : {
			fields : [{
				name : "receivername",
				type : "string"
			}, {
				name : "status",
				type : "string"
			}, {
				name : "createtimeStr",
				type : "string"
			}, {
				name : "receivetimeStr",
				type : "string"
			}, {
				name : "feedbacktimeStr",
				type : 'string'
			}]
		}
	});

	var store = Ext.create('Ext.data.Store', {
		model : 'taskOpinionItem',
		autoLoad : true,
		proxy : {
			type : "ajax",
			url : 'platform/bpm/commuReceiver/getMoblieByOpinionId.ht?opinionId='
					+ opinionId,
			reader : {
				type : "json",
				rootProperty : 'results'
			}
		}
	});
	var count = 0;
	store.load({
		callback : function() {
			count = store.getCount();
			var tip = Ext.Viewport.add({
				xtype : 'list',
				modal : true,
				hideOnMaskTap : true,
				width : '90%',
				height : '50%',
				centered : true,
				styleHtmlContent : true,
				scrollable : true,
				store : count > 0 ? store : null,
				html : count > 0
						? null
						: ('<table class="table-task" cellpadding="0" cellspacing="0" border="0">'
								+ '<tr>'
								+ '<td>'
								+ lang.receiver.noRecords
								+ '</td>' + '</tr>' + '</table>'),
				itemTpl : count > 0
						? (Ext
								.create(
										'Ext.XTemplate',
										'<table class="table-task" cellpadding="0" cellspacing="0" border="0">',
										'<tr>',
										'<th>' + lang.receiver.receivername
												+ '</th>',
										'<td>{receivername }</td>',
										'</tr>',
										'<tr>',
										'<th>' + lang.receiver.statu + '</th>',
										'<td>',
										'<tpl if="status==0">',
										lang.receiver.status.init,
										'<tpl elseif="status==1">',
										'<span class="green">'
												+ lang.receiver.status.read
												+ '</span>',
										'<tpl elseif="status==2">',
										'<span class="green">'
												+ lang.receiver.status.communication
												+ '</span>', '</tpl>', '</td>',
										'</tr>', '<tpl if="status==0">',
										'<tr>', '<th>'
												+ lang.receiver.createtime
												+ '</th>',
										'<td>{createtimeStr}</td>', '</tr>',
										'<tpl elseif="status==1">', '<tr>',
										'<th>' + lang.receiver.receivetime
												+ '</th>',
										'<td>{receivetimeStr}</td>', '</tr>',
										'<tpl elseif="status==2">', '<tr>',
										'<th>' + lang.receiver.feedbacktime
												+ '</th>',
										'<td>{feedbacktimeStr}</td>', '</tr>',
										'</tpl>', '</table>'))
						: null,
				items : [{
					docked : 'top',
					xtype : 'toolbar',
					title : lang.receiver.title,
					scope : this,
					items : [{
						xtype : 'spacer'
					}, {
						ui : 'close',
						iconCls : 'close',
						iconMask : true,
						scope : this,
						handler : function() {
							tip.hide();
						}
					}]
				}]
			}).show();
		}
	});

};

/**
 * 任务状态
 */
HTUtil.taskStatus = function(v) {
	switch (v) {
		case '-1' :
			return '<span class="green">' + lang.taskStatus.pending + '</span>';
		case '21' :
			return '<span class="brown">' + lang.taskStatus.transmitting
					+ '</span>';
		case '15' :
			return '<span class="orange">' + lang.taskStatus.communication
					+ '</span>';
		case '4' :
			return '<span class="orange">' + lang.taskStatus.recover
					+ '</span>';
		case '3' :
			return '<span class="red">' + lang.taskStatus.reject + '</span>';
		case '25' :
			return '<span class="orange">' + lang.taskStatus.recover
					+ '</span>';
		case '24' :
			return '<span class="red">' + lang.taskStatus.rejectToStart
					+ '</span>';
		case '22' :
			return '<span class="red">' + lang.taskStatus.cancelTransmitting
					+ '</span>';
		case '26' :
			return '<span class="brown">' + lang.taskStatus.agency + '</span>';
		case '27' :
			return '<span class="red">' + lang.taskStatus.cancelAgency
					+ '</span>';
		case '36' :
			return "<span class='red'>" + lang.processStatus.restartTask
					+ "</span>";
		default :
			return '';
	}
};

/**
 * 流程状态
 */
HTUtil.processStatus = function(v) {
	switch (v) {
		case 0 :
			return "<span class='red'>" + lang.processStatus.suspend
					+ "</span>";
		case 1 :
			return "<span class='green'>" + lang.processStatus.running
					+ "</span>";
		case 2 :
			return "<span class='orange'>" + lang.processStatus.finish
					+ "</span>";
		case 3 :
			return "<span class='red'>" + lang.processStatus.manualFinish
					+ "</span>";
		case 4 :
			return "<span class='red'>" + lang.processStatus.recover
					+ "</span>";
		case 5 :
			return "<span class='red'>" + lang.processStatus.reject + "</span>";
		case 6 :
			return "<span class='red'>" + lang.processStatus.recoverToStart
					+ "</span>";
		case 7 :
			return "<span class='red'>" + lang.processStatus.rejectToStart
					+ "</span>";
		case 8 :
			return "<span class='red'>" + lang.processStatus.transmitting
					+ "</span>";
		case 9 :
			return "<span class='red'>" + lang.processStatus.agency + "</span>";
		case 10 :
			return "<span class='red'>" + lang.processStatus.del + "</span>";
		case 11 :
			return "<span class='red'>" + lang.processStatus.restartTask
					+ "</span>";
		default :
			return '';
	}
}

HTUtil.assignType = function(v) {
	switch (v) {
		case 1 :
			return "<span class='green'>" + lang.assignType.agency + " </span>";
		case 2 :
			return "<span class='red'>" + lang.assignType.transmitting
					+ "</span>";
		default :
			return '';
	}
}
