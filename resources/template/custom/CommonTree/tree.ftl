<#-- 
Name:自定义表管理树模板
Desc:自定义表管理树模板
 -->
<#setting number_format="#">

<#assign text=sysCustomDisplay.setting>
<#assign setting=text?eval >
<#assign idKey=setting.node.name>
<#assign pIdKey=setting.parent.name>
<#assign display=setting.display.name>
<#assign displayId=sysCustomDisplay.id>
<#assign __cn="${custom_name!'custom_name'}">
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">${sysCustomDisplay.name}</span>
		</div>
	</div>
	<div class="panel-body">
		<div name="${__cn}" class="ztree">
		</div>
		<script type="text/javascript">
		<#noparse>
		$(function(){
			var cssFiles=[
				"${ctx}/js/tree/zTreeStyle.css"
			];
			
			for(var i=0;i<cssFiles.length;i++){
				var file=document.createElement("link");
				file.setAttribute("type","text/css");
				file.setAttribute("rel","stylesheet");
             	 file.setAttribute("href",cssFiles[i]);
				$("head")[0].appendChild(file);
			}
			
			var jsFiles=[
				"${ctx}/js/tree/jquery.ztree.js",
				"${ctx}/js/jqplot/plugins/jqplot.pointLabels.min.js"
			];
			</#noparse>
			$.getMutilScript(jsFiles,function(){
				loadTree();
			});
			function loadTree(){
				var ${__cn}dialogTree;
				var ${__cn}setting = {
						data: {
							key : {name: "${display}"},
							simpleData: {
								enable: true,
								idKey: "${idKey}",
								pIdKey: "${pIdKey}"
							}
						},
						check: {
							chkboxType:  { "Y" : "", "N" : "p" }
						}
					};
					var ${__cn}url=__ctx+"/platform/system/sysCustomDisplay/getData.ht";
					var ${__cn}params={
						__displayId:${displayId}
					};
					$.post(${__cn}url,${__cn}params,function(result){
						var datas=result.dataMaps;
						datas=$.parseJSON(datas);
						for(var i=0;i<datas.length;i++){
							datas[i].${display}=""+datas[i].${display};
						}
						//console.dir(JSON2.stringify(datas));
						${__cn}dialogTree=$.fn.zTree.init($("div[name='${__cn}']"), ${__cn}setting,datas);
						${__cn}dialogTree.expandAll(true);
					});
				}
			});
		</script>
	</div>
</div>