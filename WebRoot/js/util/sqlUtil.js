/**
 * sql语句的工具类
 */
var SqlUtil = {
	//系统引入语句：<script type="text/javascript" src="${ctx}/js/util/sqlUtil.js"></script>
	//检查sql合法性
	//sql:sql语句
	//dsalias：数据源别名，默认本地数据源
	//rollback:执行语句后是否回滚，默认true
	//callback:返回函数----注意，当为空时，默认是一个提示函数
	checkValidity : function(sql,dsalias,rollback,callback){
		var params = {
			sql : sql,
			dsalias : dsalias,
			rollback:rollback
		};
		if(!callback){
			callback=function(data){
				if (data) {
					$.ligerDialog.success('<p><font color="green">验证通过!<br></font></p>');
				}else {
					$.ligerDialog.error('<p><font color="red">验证不通过!<br></font></p>');
				}
			}
		}
		$.ajax({
		      url: __ctx+'/platform/system/sysQuerySql/validSql.ht',
		      type: 'POST',
		      dataType: 'json',
		      data:params,
		      async:true,
		      success: callback
      	});
	},
}