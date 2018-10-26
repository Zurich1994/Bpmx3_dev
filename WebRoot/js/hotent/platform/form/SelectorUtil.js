/**
*选择器帮助类
*/
var SelectorUtil = function() {

};
// 属性及函数
SelectorUtil.prototype = {
	/**
	 * 选择用户
	 * @param {} conf 
	 * 				{self:this //这个是必须的
	 * 				  isSingle：true或者false 
	 * 				}
	 */
	selectUser:function(conf){
		var me= $(conf.self),
			isSingle = typeof conf.isSingle != 'undefined'?conf.isSingle:true;
			objNames = me.prev(),
			objIds= me.prev().prev();
		
		UserDialog({
			selectUserIds:objIds.val(),
			selectUserNames:objNames.val(),
			isSingle:isSingle,
			callback:function(ids,names){
				objIds.val(ids);
				objNames.val(names);
			}});	
	},
	/**
	 * 选择组织
	 * @param {} conf 
	 * 				{self:this //这个是必须的
	 * 				  isSingle：true或者false 
	 * 				}
	 */
	selectOrg:function(conf){
		var me= $(conf.self),
			isSingle = typeof conf.isSingle != 'undefined'?conf.isSingle:true;
			objNames = me.prev(),
			objIds= me.prev().prev();
		OrgDialog({
			isSingle:isSingle,
			callback:function(ids,names){
				objIds.val(ids);
				objNames.val(names);
		}});	
	},
	/**
	 * 选择角色
	 * @param {} conf 
	 * 				{self:this //这个是必须的
	 * 				  isSingle：true或者false 
	 * 				}
	 */
	selectRole:function(conf){
		var me= $(conf.self),
			isSingle = typeof conf.isSingle != 'undefined' ?conf.isSingle:true;
			objNames = me.prev(),
			objIds= me.prev().prev();
		RoleDialog({
			isSingle:isSingle,
			callback:function(ids,names){
				objIds.val(ids);
				objNames.val(names);
		}});	
	},
	/**
	 * 选择岗位
	 * @param {} conf 
	 * 				{self:this //这个是必须的
	 * 				  isSingle：true或者false 
	 * 				}
	 */
	selectPos:function(conf){
		var me= $(conf.self),
			isSingle = typeof conf.isSingle != 'undefined'?conf.isSingle:true;
			objNames = me.prev(),
			objIds= me.prev().prev();
		PosDialog({
			isSingle:isSingle,
			callback:function(ids,names){
				objIds.val(ids);
				objNames.val(names);
		}});	
	}
};


var __Selector__ = new SelectorUtil();// 默认生成一个对象