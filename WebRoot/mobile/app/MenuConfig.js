/**
 * 菜单配置
 * 
 * @author zxh
 * @date 2013-06-04
 */
var menus = [{
	cls : 'pendingMatters',
	title : lang.menus.pendingMatters,
	view : 'mobile.undertake.PendingMatters'
	//notice:'/platform/mobile/mobileTask/pendingMatters.ht'
}, {
	cls : 'alreadyMatters',
	title : lang.menus.alreadyMatters,
	view : 'mobile.undertake.AlreadyMatters'
}, {
	cls : 'completedMatters',
	title : lang.menus.completedMatters,
	view : 'mobile.undertake.CompletedMatters'
}, {
	cls : 'taskExe',
	title : lang.menus.taskExe,
	view : 'mobile.undertake.TaskExe'
}
//, {
//	cls : 'myDraft',
//	title : lang.menus.myDraft,
//	view : 'mobile.launched.MyDraft'
//}, {
//	cls : 'myRequest',
//	title : lang.menus.myRequest,
//	view : 'mobile.launched.MyRequest'
//}, {
//	cls : 'myCompleted',
//	title : lang.menus.myCompleted,
//	view : 'mobile.launched.MyCompleted'
//}
];