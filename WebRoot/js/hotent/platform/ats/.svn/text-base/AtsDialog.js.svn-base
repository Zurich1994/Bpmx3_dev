
/**
 * 日历模版窗口
 * @param conf
 */
function AtsCalendarTemplDialog(conf)
{
	if(!conf) conf={};
	var url=__ctx + '/platform/ats/atsCalendarTempl/dialog.ht';
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 800,
		title : '选择日历模版',
		url: url, 
		isResize: true,
	   //自定义参数
		params: conf.params,
		 //回调函数
        sucCall:function(rtn){
        	conf.callback.call(this,rtn);
        }
	});
}


/**
 * 法定假日
 * @param conf
 */
function AtsLegalHolidayDialog(conf)
{
	if(!conf) conf={};
	var url=__ctx + '/platform/ats/atsLegalHoliday/dialog.ht';
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 800,
		title : '选择法定假日',
		url: url, 
		isResize: true,
	   //自定义参数
		params: conf.params,
		 //回调函数
        sucCall:function(rtn){
        	conf.callback.call(this,rtn);
        }
	});
}

/**
 * 工作日历
 * @param conf
 */
function AtsWorkCalendarDialog(conf)
{
	if(!conf) conf={};
	var url=__ctx + '/platform/ats/atsWorkCalendar/dialog.ht';
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 800,
		title : '选择工作日历',
		url: url, 
		isResize: true,
	   //自定义参数
		params: conf.params,
		 //回调函数
        sucCall:function(rtn){
        	conf.callback.call(this,rtn);
        }
	});
}

/**
 * 考勤周期
 * @param conf
 */
function AtsAttenceCycleDialog(conf)
{
	if(!conf) conf={};
	var url=__ctx + '/platform/ats/atsAttenceCycle/dialog.ht';
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 800,
		title : '选择考勤周期',
		url: url, 
		isResize: true,
	   //自定义参数
		params: conf.params,
		 //回调函数
        sucCall:function(rtn){
        	conf.callback.call(this,rtn);
        }
	});
}

/**
 * 取卡规则
 * @param conf
 */
function AtsCardRuleDialog(conf)
{
	if(!conf) conf={};
	var url=__ctx + '/platform/ats/atsCardRule/dialog.ht';
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 800,
		title : '选择取卡规则',
		url: url, 
		isResize: true,
	   //自定义参数
		params: conf.params,
		 //回调函数
        sucCall:function(rtn){
        	conf.callback.call(this,rtn);
        }
	});
}

/**
 * 考勤制度
 * @param conf
 */
function AtsAttencePolicyDialog(conf)
{
	if(!conf) conf={};
	if(!conf.isSingle)conf.isSingle=false;
	var url=__ctx + '/platform/ats/atsAttencePolicy/dialog.ht?isSingle=' + conf.isSingle;
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 800,
		title : '选择考勤制度',
		url: url, 
		isResize: true,
	   //自定义参数
		params: conf.params,
		 //回调函数
        sucCall:function(rtn){
        	conf.callback.call(this,rtn);
        }
	});
}


/**
 * 假期制度
 * @param conf
 */
function AtsHolidayPolicyDialog(conf)
{
	if(!conf) conf={};
	if(!conf.isSingle)conf.isSingle=false;
	var url=__ctx + '/platform/ats/atsHolidayPolicy/dialog.ht?isSingle=' + conf.isSingle;
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 800,
		title : '选择假期制度',
		url: url, 
		isResize: true,
	   //自定义参数
		params: conf.params,
		 //回调函数
        sucCall:function(rtn){
        	conf.callback.call(this,rtn);
        }
	});
}

/**
 * 班次设置
 * @param conf
 */
function AtsShiftInfoDialog(conf)
{
	if(!conf) conf={};
	if(!conf.isSingle)conf.isSingle=false;
	var url=__ctx + '/platform/ats/atsShiftInfo/dialog.ht?isSingle=' + conf.isSingle;
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 800,
		title : '选择班次设置',
		url: url, 
		isResize: true,
	   //自定义参数
		params: conf.params,
		 //回调函数
        sucCall:function(rtn){
        	conf.callback.call(this,rtn);
        }
	});
}

/**
 * 轮班规则
 * @param conf
 */
function AtsShiftRuleDialog(conf)
{
	if(!conf) conf={};
	var url=__ctx + '/platform/ats/atsShiftRule/dialog.ht';
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 900,
		title : '选择轮班规则',
		url: url, 
		isResize: true,
	   //自定义参数
		params: conf.params,
		 //回调函数
        sucCall:function(rtn){
        	conf.callback.call(this,rtn);
        }
	});
}
/**
 * 导入
 * @param conf
 */
function AtsImport(conf)
{
	if(!conf) conf={};
	var url= conf.url;
	url=url.getNewUrl();
	DialogUtil.open({
		height:conf.height?conf.height:400,
		width: conf.width?conf.width:500,
		title : conf.title,
		url: url, 
		isResize: true,
	   //自定义参数
		params: conf.params,
		 //回调函数
        sucCall:function(rtn){
    		if(rtn){
    			if(conf.returnUrl)
    				window.location.href = conf.returnUrl;
    			else
    				window.location.reload(true);
    		}
        }
	});
}

/**
 * 选择考勤人员
 * @param conf
 */
function AtsAttendanceFileDialog(conf)
{
	if(!conf) conf={};
	if(!conf.isSingle)conf.isSingle=false;
	var url=__ctx + '/platform/ats/atsAttendanceFile/dialog.ht?isSingle=' + conf.isSingle;
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 800,
		title : '选择考勤人员',
		url: url, 
		isResize: true,
	   //自定义参数
		params: conf.params,
		 //回调函数
        sucCall:function(rtn){
        	conf.callback.call(this,rtn);
        }
	});
}
function AtsAttenceGroupDialog(conf){
	if(!conf) conf={};
	if(!conf.isSingle)conf.isSingle=false;
	var url=__ctx + '/platform/ats/atsAttenceGroup/dialog.ht?isSingle=' + conf.isSingle;
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 800,
		title : '选择考勤组',
		url: url, 
		isResize: true,
	   //自定义参数
		params: conf.params,
		 //回调函数
        sucCall:function(rtn){
        	conf.callback.call(this,rtn);
        }
	});
}

/**
 * 假期类型
 * @param conf
 */
function AtsHolidayTypeDialog(conf)
{
	if(!conf) conf={};
	var url=__ctx + '/platform/ats/atsHolidayType/dialog.ht';
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 800,
		title : '选择假期类型',
		url: url, 
		isResize: true,
	   //自定义参数
		params: conf.params,
		 //回调函数
        sucCall:function(rtn){
        	conf.callback.call(this,rtn);
        }
	});
}