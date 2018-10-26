
/**
 * 查看流程图
 * 
 * @author zxh
 * @date 2013-06-04
 */
Ext.define('mobile.form.UserImage', {
	extend : 'Ext.Panel',
	fullscreen : true,
	name : 'userImage',

	constructor : function(conf) {
		conf = conf || {};
		var moblieImage = conf.moblieImage, html = '', processInstanceId, actDefId;
		if (moblieImage.success) {
			// 运行流程图
			if (moblieImage.type == 0) {
				html = '<div><ul class="legendContainer">'
						+ '<li><div style="background-color:gray; " class="legend"></div>'
						+ lang.userImage.init
						+ '</li>'
						+ '<li><div style="background-color:#00FF00;" class="legend"></div>'
						+ lang.userImage.agree
						+ '</li>'
						+ '<li><div style="background-color:orange;" class="legend"></div>'
						+ lang.userImage.abandon
						+ '</li>'
						+ '<li><div style="background-color:red;" class="legend"></div>'
						+ lang.userImage.curNode
						+ '</li>'
						+ '<li><div style="background-color:blue;" class="legend"></div>'
						+ lang.userImage.notAgree
						+ '</li>'
						+ '<li><div style="background-color:#8A0902;" class="legend"></div>'
						+ lang.userImage.reject
						+ '</li>'
						+ '<li><div style="background-color:#023B62;" class="legend"></div>'
						+ lang.userImage.recover
						+ '</li>'
						+ '<li><div style="background-color:#338848;" class="legend"></div>'
						+ lang.userImage.signPassed
						+ '</li>'
						+ '<li><div style="background-color:#82B7D7;" class="legend"></div>'
						+ lang.userImage.signNotPassed
						+ '</li>'
						+ '<li><div style="background-color:#EEAF97;" class="legend"></div>'
						+ lang.userImage.endProcess
						+ '</li>'
						+ '</ul></div>'
						+ '<div style="padding-top:20px;background-color: white;"><div id="divTaskContainer" style="margin:0 auto;  position: relative;background:url(\''
						+ __ctx + '/bpmImage?processInstanceId='
						+ moblieImage.processInstanceId
						+ '\') no-repeat;width:' + moblieImage.width
						+ 'px;height:' + moblieImage.height + 'px;">'
						+ moblieImage.xml + '</div></div>';
				processInstanceId = moblieImage.processInstanceId;
			} else {
				html = '<div style="padding-top:20px;background-color: white;"><div id="divTaskContainer" style="margin:0 auto;  position: relative;background:url(\''
						+ __ctx
						+ '/bpmImage?definitionId='
						+ moblieImage.actDefId
						+ '\') no-repeat;width:'
						+ moblieImage.width
						+ 'px;height:'
						+ moblieImage.height
						+ 'px;">' + moblieImage.xml + '</div></div>';

			}
		} else {
			html = '<div>' + moblieImage.msg + '</div>';
		}
		Ext.apply(conf, {
			fullscreen : true,
			layout : 'fit',
			items : [{
				xtype : 'panel',
				docked : 'top',
				scrollable : {
					direction : 'both',
					directionLock : true
				},
				height : '100%',
				width : '100%',
				style : {
					backgroundColor : '#fff'
				},
				html : html
			}],
			listeners : {
				activeitemchange : function(container, value, oldValue, eOpts) {

				}
			}
		});

		this.callParent([conf]);
		// 点击节点事件
		var el = Ext.select("div.flowNode", true);
		el.on("tap", function(e, d) {
			var type = d.getAttribute('type');
			var nodeId = d.getAttribute('id');
			var title = d.getAttribute('title');
			if (type == 'userTask' || type == 'multiUserTask') {
				if (moblieImage.type == 0) {
					Ext.define('taskOpinion', {
						extend : 'Ext.data.Model',
						config : {
							fields : [{
								name : 'taskName',
								type : 'string'
							}, {
								name : 'exeFullname',
								type : 'string'
							}, {
								name : 'candidateUser',
								type : 'string'
							}, {
								name : 'startTimeStr',
								type : 'string'
							}, {
								name : 'endTimeStr',
								type : 'string'
							}, {
								name : 'durTimeStr',
								type : 'string'
							}, {
								name : 'status',
								type : 'string'
							}]
						}
					});

					var store = Ext.create('HT.Store', {
						model : 'taskOpinion',
						url : __ctx
								+ '/platform/mobile/processRun/getFlowStatusByInstanceId.ht?processInstanceId='
								+ processInstanceId + '&nodeId=' + nodeId
					});

					this.showTip(title, store, moblieImage.type);
				} else {
					this.showTip(title, null, moblieImage.type);
				}

			}
			// 子流程
			else if (type == 'callActivity') {
				this.showSubFlowImage(title, nodeId);
			}
		}, this);
	},
	/**
	 * 显示流程的执行情况和节点
	 * 
	 * @param {}
	 *            title 标题
	 * @param {}
	 *            store
	 * @param {}
	 *            type
	 */
	showTip : function(title, store, type) {
		if (type) {
			var tip = Ext.Viewport.add({
				xtype : 'panel',
				modal : true,
				width : '90%',
				height : '50%',
				centered : true,
				styleHtmlContent : true,
				scrollable : true,
				html : '<table class="table-task" cellpadding="0" cellspacing="0" border="0">'
						+ '<tr><th>'
						+ lang.userImage.nodeName
						+ ' </th> <td>'
						+ title + '</td></tr><table>',
				items : [{
					docked : 'top',
					xtype : 'toolbar',
					title : lang.userImage.nodeTitle,
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
			});
			tip.show();
		} else {
			var count = 0;
			store.load({
				callback : function() {
					count = store.getCount();
					var tip = Ext.Viewport.add({
						xtype : 'list',
						modal : true,
						hideOnMaskTap : true,
						width : '90%',
						height : count > 0 ? '90%' : '50%',
						centered : true,
						styleHtmlContent : true,
						scrollable : true,
						store : count > 0 ? store : null,
						html : count == 0
								? ('<table class="table-task" cellpadding="0" cellspacing="0" border="0">'
										+ '<tr><th>'
										+ lang.userImage.task.taskName
										+ ': </th> <td>' + title + '</td></tr><table>')
								: null,
						itemTpl : count > 0
								? (Ext
										.create(
												'Ext.XTemplate',
												'<table class="table-task" cellpadding="0" cellspacing="0" border="0">',
												'<tr><th>'
														+ lang.userImage.task.taskName
														+ ':</th>',
												'<td>{taskName}</td></tr>',

												'<tr><th>'
														+ lang.userImage.task.exeFullname
														+ ':</th>',
												'<td>{exeFullname}</td></tr>',

												'<tr><th>'
														+ lang.userImage.task.candidateUser
														+ ':</th>',
												'<td>{candidateUser}</td></tr>',

												'<tr><th  nowrap="nowrap">'
														+ lang.userImage.task.startTime
														+ ': </th>',
												'<td>{startTimeStr}</td></tr>',

												'<tr><th>'
														+ lang.userImage.task.endTime
														+ ': </th>',
												'<td>{endTimeStr}</td></tr>',

												'<tr><th >'
														+ lang.userImage.task.durTime
														+ ': </th>',
												'<td>{durTimeStr}</td></tr>',

												'<tr><th>'
														+ lang.userImage.task.status
														+ ':  </th>',
												'<td>{status}</td></tr>',

												'<tr><th>'
														+ lang.userImage.task.opinion
														+ ': </th>',
												'<td>{opinion}</td></tr>',
												'<table>'))
								: null,
						items : [{
							docked : 'top',
							xtype : 'toolbar',
							title : lang.userImage.task.title,
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
					});

					tip.show();
				}
			});
		}

	},
	/**
	 * 子流程
	 * 
	 * @param {}
	 *            title
	 */
	showSubFlowImage : function(title) {
		// var html = '<div style="padding-top:20px;background-color:
		// white;"><div id="divTaskContainer" style="margin:0 auto; position:
		// relative;background:url(\''
		// + __ctx + '/bpmImage?definitionId='
		// + moblieImage.actDefId + '\') no-repeat;width:'
		// + moblieImage.width + 'px;height:' + moblieImage.height
		// + 'px;">' + moblieImage.xml + '</div></div>';

		var tip = Ext.Viewport.add({
			xtype : 'panel',
			modal : true,
			width : '90%',
			height : '90%',
			centered : true,
			styleHtmlContent : true,
			scrollable : true,
			html : '<table class="table-task" cellpadding="0" cellspacing="0" border="0">'
					+ '<tr><th>'
					+ lang.userImage.nodeName
					+ ': </th> <td>'
					+ title + '</td></tr><table>',
			items : [{
				docked : 'top',
				xtype : 'toolbar',
				title : lang.userImage.subFlow.title,
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
		});
		tip.show();

	},
	onDragStart : function(e) {
		var scroller = this.getActiveItem().getScrollable().getScroller();
		if (e.targetTouches.length === 1
				&& (e.deltaX < 0 && scroller.getMaxPosition().x === scroller.position.x)
				|| (e.deltaX > 0 && scroller.position.x === 0)) {
			this.callParent(arguments);
		}
	},
	onDrag : function(e) {
		if (e.targetTouches.length == 1)
			this.callParent(arguments);
	},
	onDragEnd : function(e) {
		if (e.targetTouches.length < 2)
			this.callParent(arguments);
	}

});
