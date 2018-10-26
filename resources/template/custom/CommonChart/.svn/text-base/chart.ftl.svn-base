<#-- 
Name:自定义表管理树模板
Desc:自定义表管理树模板
 -->
<#setting number_format="#">

<#assign setting=sysCustomDisplay.setting?eval >
<#assign displayId=sysCustomDisplay.id>
<#assign __cn="${custom_name!'custom_name'}">
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">${sysCustomDisplay.name}</span>
		</div>
	</div>
	<div class="panel-body">
		<div id="${__cn}" name="${__cn}" class="chart">
		</div>
		<script type="text/javascript">
			<#noparse>
		$(function(){
			var cssFiles=[
				"${ctx}/js/jqplot/jquery.jqplot.min.css"
			];
			
			for(var i=0;i<cssFiles.length;i++){
				var file=document.createElement("link");
				file.setAttribute("type","text/css");
				file.setAttribute("rel","stylesheet");
             	 file.setAttribute("href",cssFiles[i]);
				$("head")[0].appendChild(file);
			}
			
			var jsFiles=[
				"${ctx}/js/jqplot/jquery.jqplot.js",
				"${ctx}/js/jqplot/plugins/jqplot.barRenderer.min.js",
				"${ctx}/js/jqplot/plugins/jqplot.categoryAxisRenderer.min.js",
				"${ctx}/js/jqplot/plugins/jqplot.canvasTextRenderer.min.js",
				"${ctx}/js/jqplot/plugins/jqplot.pointLabels.min.js"
			];
			</#noparse>
			$.getMutilScript(jsFiles,function(){
				loadChart();
			});
			function loadChart(){
				var jsonToArray=function(arr) {
				   var s=[];
				   if( arr instanceof Array || arr instanceof Object){
					  var isObj=0;
					  //check value type
					  for(key in arr){
						 if( isNaN(parseInt(key)) ){ //key is string
							isObj=1;
						 }else{
							//key is index , check sort
							var na=arr.length;
							var tmp=arr;
							//hack for ie
							arr=Array();
							for(var j=0;j<na;j++){
							   if( typeof(tmp[j])=="undefined" ){
								  arr[j]="";
							   }else{
								  arr[j]=tmp[j];
							   }
							}
						 }
						 break;
					  }
					  if(isObj){
						  for(key in arr){
							var value=arr[key];
							s.push([key,jsonToArray(value)]);
						  }
					  }else{
							for(key in arr){
							var value=arr[key];
							s.push(jsonToArray(value));
						  }
					  }
				   }else{
					  return arr;
				   }
				   return s;
				};
				
				var custom_nameurl=__ctx+"/platform/system/sysCustomDisplay/getData.ht";
				var custom_nameparams={
					__displayId:${sysCustomDisplay.id}
				};
				
				$.post(custom_nameurl,custom_nameparams,function(result){
					var datas=result.dataMaps;
					var datajson=$.parseJSON(datas);
					
					var setting='${sysCustomDisplay.setting}';
					var settingjson=$.parseJSON(setting);
					
					//get display field name
					var displays=[];
					var comments=[];
					for(var i=0;i<settingjson.length;i++){
						displays.push(settingjson[i].label);
						comments.push(settingjson[i].comment||settingjson[i].label);
					}
					
					//delete not display field
					for(var i=0;i<datajson.length;i++){
						for(var key in datajson[i]){
							var idx = $.inArray(key,displays);
							if(idx==-1){
								delete datajson[i][key];
							}else{
								var temp=datajson[i][key];
								delete datajson[i][key];
								datajson[i][comments[idx]]=temp;
							}
						}
					}
					//transform json form to jqplot accepted form --array
					var dataary=jsonToArray(datajson);
					
					var jqpOpts = {
						//Chart Title
						title:{
							text:"aa"
						},
						series:[
							{showMarker:false}
						],
						axesDefaults: {
						    tickRenderer: $.jqplot.CanvasAxisTickRenderer ,
						    tickOptions: {
						      angle: -30,
						      fontSize: '10pt'
						    }
						},
						axes: {
						  xaxis: {
						    renderer: $.jqplot.CategoryAxisRenderer
						  }
						}
					};
					var plotcomp = $.jqplot ("custom_name", dataary,jqpOpts);
				 });
			};
		});
		</script>
	</div>
</div>