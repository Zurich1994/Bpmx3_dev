
/**
 * 审批历史
 * 
 * @author zxh
 * @date 2013-06-04
 */
Ext.define('mobile.form.TaskOpinions', {
	extend : 'Ext.dataview.List',
	name : 'taskOpinions',
	constructor : function(conf) {

		conf = conf || {};

		var taskId = conf.taskId ? conf.taskId : '', runId = conf.runId
				? conf.runId
				: '';

		Ext.define('taskOpinionItem', {
			extend : 'Ext.data.Model',
			config : {
				fields : [{
					name : "opinionId",
					type : "string"
				}, {
					name : "taskName",
					type : "string"
				}, {
					name : "status",
					type : "string"
				}, {
					name : "exeFullname",
					type : "string"
				}, {
					name : "checkStatus",
					type : "int"
				}, {
					name : "opinion",
					type : "string"
				}, {
					name : "startTime",
					type : 'date',
					convert : this.convertStartTime
				}]
			}
		});

		this.store = Ext.create('Ext.data.Store', {
			model : 'taskOpinionItem',
			autoLoad : true,
			proxy : {
				type : "ajax",
				url : __ctx
						+ '/platform/mobile/mobileTask/taskOpinions.ht?taskId='
						+ taskId + '&runId=' + runId,
				reader : {
					type : "json",
					rootProperty : 'results'
				}
			}
		});

		Ext.apply(conf, {
			layout : 'fit',
			title : lang.taskOpinions.title,
			fullscreen : true,
			store : this.store,
			emptyText : lang.taskOpinions.emptyText,
			itemTpl : Ext.create('Ext.XTemplate',
					'<div class="x-show-contact">', '<div class="top">',
					'<div class="name">{taskName}<span>'
							+ lang.taskOpinions.opinion + ':{opinion}<div>'
							+ lang.taskOpinions.status + ':{status}',
					'<tpl if="checkStatus == 15">',
					'<a href="javascript:;" onclick="HTUtil.receiver({opinionId})">'
							+ lang.taskOpinions.receiver + '</a>', '</tpl>',
					'<div>' + lang.taskOpinions.startTime
							+ ':{startTime}</div></span>', '<div>'
							+ lang.taskOpinions.exeFullname
							+ ':{exeFullname}</div>', '</div>', '</div>',
					'</div>')
		});

		this.callParent([conf]);
	},
	convertStartTime : function(v, r) {
		return HT.parseLongTime(v);
	}
});
