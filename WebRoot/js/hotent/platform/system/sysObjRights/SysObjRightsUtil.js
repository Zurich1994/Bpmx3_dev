/**
 * 设置权限的工具类
 */
var SysObjRightsUtil = {
	/**
	 * id：授权对象的唯一标示 通常是ID
	 * type:授权对象的类型key 主要是为了防止不同类型的授权对象 ID相同的情况，所以要多一个类型KEY
	 * beanId:就是类型数组的beanId 默认是"type+TypeList"
	 */
	setRights:function(id,type,beanId){
		if(!beanId){
			beanId="defaultPermissionList";
		}
		var url = __ctx+"/platform/system/sysObjRights/edit.ht?objectId="+id+"&objType="+type+"&beanId="+beanId;
		DialogUtil.open({
			height:500,
			width: 600,
			title : "设置授权",
			url: url
		});
	}
}