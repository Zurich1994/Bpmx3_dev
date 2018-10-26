if (typeof ReadOnlyQuery == 'undefined') {
	ReadOnlyQuery = {};
}

ReadOnlyQuery.init = function() {

	$("span[selectquery]").each(function() {
		var me = $(this);
		var cond = me.attr("selectvalue");
		var queryJson = JSON2.parse(me.attr("selectquery")
				.replaceAll("'", "\""));
		var key = queryJson.binding.key; // 返回值作为select的value
		var value = queryJson.binding.value; // 返回值作为select的显示值
		var query = queryJson.query;
		var querydataStr='';
		var datastr='{'
		for(var i in query){
			if(query[i].initValue){
				datastr+=query[i].condition+ ":\"" + query[i].initValue + "\",";
			} 
		}
		datastr = datastr.substring(0, datastr.length - 1);
		datastr +='}';
		if(datastr.length > 2){
			querydataStr=datastr;
		}
		var queryedCacheDataString="";
		//如果querydataStr为{}，说明是查询全部数据，引用一个"queryedCacheDataString"标识全部数据
		if(querydataStr==""){
			queryedCacheDataString="queryedCacheDataString";
		}else{
			queryedCacheDataString=querydataStr;
		}
		
		var cacheData=ReadOnlyQuery.getCascaData(queryJson.name,queryedCacheDataString);
		if(cacheData){
			ReadOnlyQuery.handData(cacheData,queryJson,me,cond);
		}else{
			queryCond = {
				alias : queryJson.name,
				querydata : querydataStr,
				page : 0,
				pagesize : 0
			};
			DoQuery(queryCond, function(data) {
					if (data.errors || data.list.length < 1) {
						return;
					}
					ReadOnlyQuery.handData(data.list,queryJson,me,cond);
					ReadOnlyQuery.setCascaData(queryJson.name,queryedCacheDataString,data.list);
			},true);
		}
	});
};

/**
 * 获取缓存数据
 */
ReadOnlyQuery.getCascaData=function(alias,querydata){
	return QueryUI.getCascaData(alias,querydata);
};

/**
 * 设置缓存数据
 */
ReadOnlyQuery.setCascaData = function(alias,querydata,data){
	QueryUI.setCascaData(alias,querydata,data);
};

/**
 * 获取数据以后经过处理，显示多下拉列表中
 */
ReadOnlyQuery.handData=function(data,queryJson,currSelectObj,currcond){
	var me=$(currSelectObj);
	for (var i = 0; i < data.length; i++) {
		var dataobj = data[i];
		if (dataobj[queryJson.binding.key.toLowerCase()] == currcond) {
			var datavalue = dataobj[queryJson.binding.value.toLowerCase()];
			me.text(datavalue);
		}
	}
};


