$(function(){
	//初始化表单tab
	FormUtil.initTab();
	//初始化日期控件。
	FormUtil.initCalendar();
	//附件初始化
	AttachMent.init();
	//
	ReadOnlyQuery.init();
	
	FormUtil.toDataTemplate();
	
	FormUtil.InitMathfunction();
	//绑定对话框。
	FormUtil.initCommonDialog();
	FormUtil.initCommonQuery();
	
	FormUtil.initExtLink();
	
	QueryUI.init();
	// 绑定word套打按钮
	FormUtil.initWordTemplate();
	// 初始化表单导入导出excel
	FormUtil.importExcel();
});

$(window).bind("load",function(){
	//Office控件初始化。
	OfficePlugin.init();
	//WebSign控件初始化。
	WebSignPlugin.init();
	//PictureShow 图片展示控件
	PictureShowPlugin.init();	
	//初始化第一个表单tab 中部分样式问题
	FormUtil.initTabStyle();
});