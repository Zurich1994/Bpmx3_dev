var zNodes = [  {
	name:'系统管理',
	alias:'system',
	open:true,
	id:'100',
	nodes:[
		{
			name : "数据字典",
			alias : "",
			link : "/js/ligerui/plugins/demo/dicComboBox.jsp",
			id : "118",
			nodes : [{
				name : "数据字典",
				alias : "",
				link : "/js/ligerui/plugins/demo/dicComboBox.jsp",
				id : "120"
			} ]
		},
	       {
			name:'用户列表',
			alias:'sysUser',
			link:'/platform/system/sysUser/list.ht',
			id:'101'
		},
		{
		name:'组织管理',
		alias:'sysOrg',
		link:'/platform/system/sysOrg/list.ht',
		id:'102'
	}, {
		name : '子系统管理',
		alias : 'subSys',
		link : '/platform/system/subSystems.ht',
		id : '102'
	}, {
		name : '角色管理',
		alias : 'sysRole',
		link : '/platform/system/sysRole/list.ht',
		id : '103'
	}, {
		name:'系统数据源',
		alias:'sysDataSource',
		link:'/platform/system/sysDataSources.ht',
		id:'102'
	},{
		name:'系统日志',
		alias:'subAudit',
		link:'/platform/system/sysAudit/list.ht',
		id:'105'
	}, {
		name:'系统分类',
		alias:'glType',
		link:'/platform/system/globalType/tree.ht',
		id:'106'
	}, {
		name:'数据字典',
		alias:'dic',
		link:'/platform/system/dictionary/tree.ht',
		id:'104'
		},
		{
			name:'系统附件',
			alias:'dic',
			link:'/platform/system/sysFile/list.ht',
			id:'107'
		},
		{
			name:'岗位管理',
			alias:'position',
			link:'/platform/system/position/tree.ht',
			id:'108'
		},
		{
			name:'资源管理',
			alias:'position',
			link:'/platform/system/resources/tree.ht',
			id:'109'
		}
		]
},{
	name : "流程管理",
	alias : "bpm",
	open : true,
	id : "110",
	nodes : [ {
		name : "流程定义管理",
		alias : "",
		link : "/platform/bpm/bpmDefinition/manage.ht",
		id : "111"
	},
	{
		name : "我的待办事项",
		alias : "mytask",
		link : "/platform/bpm/task/forMe.ht",
		id : "112"
	},
	{
		name : "任务管理",
		alias : "task",
		link : "/platform/bpm/task/list.ht",
		id : "113"
	},
	{
		name : "任务实例监控",
		alias : "processrun",
		link : "/platform/bpm/processRun/list.ht",
		id : "114"
	},
	{
		name : "流程历史",
		alias : "processrun",
		link : "/platform/bpm/history/list.ht",
		id : "115"
	}
	]
},{
	name : "基本控件使用",
	alias : "",
	open : true,
	id : "1",
	nodes : [ {
		name : "TAB控件使用",
		alias : "",
		link : "/js/ligerui/plugins/demo/tab.jsp",
		id : "2"
	}, {
		name : "LAYOUT控件使用",
		alias : "",
		link : "/js/ligerui/plugins/demo/layout.jsp",
		id : "3"
	}, {
		name : "WINDOW控件使用",
		alias : "",
		link : "/js/ligerui/plugins/demo/window.jsp",
		id : "4"
	}, {
		name : "Msg控件使用",
		alias : "",
		link : "/js/ligerui/plugins/demo/msg.jsp",
		id : "5"
	}, {
		name : "comboBoxTree控件使用",
		alias : "",
		link : "/js/ligerui/plugins/demo/comboBoxTree.jsp",
		id : "06"
	}, {
		name : "simpletab控件使用",
		alias : "",
		link : "/js/ligerui/plugins/demo/simpletab.jsp",
		id : "6"
	}, {
		name : "messagebox控件使用",
		alias : "",
		link : "/js/ligerui/plugins/demo/messagebox.jsp",
		id : "7"
	}, {
		name : "button控件使用",
		alias : "",
		link : "/js/ligerui/plugins/demo/button.jsp",
		id : "8"
	}, {
		name : "toolbar控件使用",
		alias : "",
		link : "/js/ligerui/plugins/demo/toolbar.jsp",
		id : "9"
	}, {
		name : "tree 右键菜单",
		alias : "",
		link : "/js/ligerui/plugins/demo/treemenu2.jsp",
		id : "10"
	}
	
	]
}];