<#-- 
Name:自定义表管理树模板
Desc:自定义表管理树模板
 -->
<#setting number_format="#">

<#assign __cn="${custom_name!'custom_name'}">
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">${tbarTitle}</span>
		</div>
	</div>
	<div class="panel-body">
		<div name="${__cn}" class="ztree">
		</div>
		<script type="text/javascript">
			var ${__cn}dialogTree;
			var ${__cn}setting = {
					data: {
						key : {name: "${displayField.displayName}"},
						simpleData: {
							enable: true,
							idKey: "${displayField.id}",
							pIdKey: "${displayField.pid}",
						}
					},
					check: {
						chkboxType:  { "Y" : "", "N" : "p" }
					}
				};
				var ${__cn}url=__ctx+"/platform/system/sysTableManage/getTreeData.ht";
				var ${__cn}params={
					__manageid__:${__manageid__}
				};
				$.post(${__cn}url,${__cn}params,function(result){
					for(var i=0;i<result.length;i++){
						result[i].${displayField.displayName}=""+result[i].${displayField.displayName};
					}
					${__cn}dialogTree=$.fn.zTree.init($("div:[name='${__cn}']"), ${__cn}setting,result);
					${__cn}dialogTree.expandAll(true);
				});
		</script>
	</div>
</div>