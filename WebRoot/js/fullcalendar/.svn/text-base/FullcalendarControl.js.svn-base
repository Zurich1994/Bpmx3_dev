/**
 *  fullcalendar控件（详见:http://fullcalendar.io/docs/）。
 *  使用方法：
 *  var obj=new FullcalendarControl();
 *  obj.renderTo("divContainer",{queryParams{},setUpParams:{}});
 * 	divContainer： 文档容器id
 *  queryParams:数据查询相关参数(先设置查询参数，因为设置参数中有用到查询参数)
 * 	setUpParams：控件设置参数;
 * @returns {fullcalendarObj}
 * 
 * 例如：
 * 
 * 
    $(function() {
		var queryDataUrl = __ctx + "/platform/system/sysPlan/listBySubmitAndMonth.ht";
		var openPlanUrl = __ctx + "/platform/system/sysPlan/edit.ht";
		var queryParams={
				queryDataUrl:queryDataUrl,
				openPlanUrl:openPlanUrl,
				selectDate:""       //默认是没有的
			};
		var setUpParams={
				header : {
					left : 'title',
					center : 'prev, today, next',
					right : 'agendaWeek,agendaDay'
				}
			};
		
		//开始创建控件
		var calendarObj = new FullcalendarControl();
		calendarObj.renderTo("calendar",{queryParams:queryParams,setUpParams:setUpParams});
	});
 * 
 */
FullcalendarControl=function(){
	{
		var _self=this;
		var queryDataUrl = __ctx + "/platform/system/sysPlan/listBySubmitAndMonth.ht";
		var openPlanUrl = __ctx + "/platform/system/sysPlan/edit.ht";

		_self.controlId="calendar";
		
		_self.controlObj=null;
		
		_self.queryParams={
			queryDataUrl:queryDataUrl,
			openPlanUrl:openPlanUrl,
			selectDate:""    
		};
		
		_self.setUpParams={
			//设置选项和回调   
			editable : false,
			currentTimezone : 'Asia/Beijing',
			allDayDefault : false,
			buttonText:{
				prevYear:'上一年',
			    nextYear:'下一年'
			},
			
			header : {
				left : 'title',
				center : 'prevYear, prev, today, next, nextYear',
				right : 'month,agendaWeek,agendaDay'
			},

			eventClick : function(event) {
				//获取当前视图的日期
				var date = _self.getCurrentViewDate();
				_self.queryParams.currentViweDate = _self.changeDateToStr(date);
				//鼠标点击日程
				_self.openEventSysPlan(event.planId,event.title,_self.queryParams.openPlanUrl,_self.queryParams.currentViweDate);
			},
			
			eventMouseover : function(event) {
				var obj = $(this);
				_self.toDoEventMouseover(obj, event);
			},
			
			eventMouseout : function(event) {
				//鼠标移出日程暂时未实现
			}, 

			dayClick : function(date, allDay, jsEvent, view) {
				//点击日期事件 暂时未实现
			},  
			
			selectable: true,
			selectHelper: true,
			editable: true,
			eventLimit: true, //更多
		    views: {
		    	month:{
		    		eventLimit: 5, 
		    		eventLimitClick :'day'
		    	},
    			agendaWeek: {
		            eventLimit: 5
		        },
		        agendaDay: {
		            eventLimit:5
		        }
		    },
		    
		    events:function(start,end,timezone,callback){ 
		    	var startDate = _self.changeDateToStr(start._d);
		    	var endDate = _self.changeDateToStr(end._d);
		    	$.ajax({ 
			    	 type:"post", 
			    	 url:_self.queryParams.queryDataUrl, 
			    	 dataType:"json", 
			    	 data:{
			    		     startDate:startDate,
			    		     endDate:endDate,
			    		     selectDate:_self.queryParams.selectDate
			    		  }, 
			    	 success:function(data){ 
				    	 var event = []; 
				    	 if(data){
				    		 event = data;
					     }
				    	 callback(event); 
			    	 } 
		    	 }); 
		    } 
		    
		}
	};
	
	
	/**
	 * 将控件添加到div容器中。
	 * 第一个参数：
	 * div的容器ID
	 * 第二个参数:
	 * conf{
	 *      queryParams:数据查询相关参数(先设置查询参数，因为设置参数中有用到查询参数)
     * 	    setUpParams：控件设置参数;
     * }
	 */
	this.renderTo=function(divContainerId,conf){
		_self.controlId = divContainerId;
		_self.queryParams = $.extend({},_self.queryParams,conf.queryParams);
		_self.setUpParams = $.extend({},_self.setUpParams,conf.setUpParams);
		_self.controlObj = $('#'+_self.controlId).fullCalendar(_self.setUpParams);
		return _self.controlObj;
    };
	
    /**
     * 获取当前月份的时间；
     * 
	 */
    this.getCurrentViewDate=function(){
		 var moment = $('#'+_self.controlId).fullCalendar('getDate');  //当前时间
		// alert("The current date of the calendar is " + moment.format());
		 return moment._d;
	};
	
    /**
     * 跳到指定日期的视图；
     * 
	 */
    this.goToTheDate=function(date){
    	$('#'+_self.controlId).fullCalendar( 'gotoDate', date );
	};
	
	 /**
     * 把日期转为字符；(yyyy-mm-dd)
     * 
	 */
    this.changeDateToStr=function(date){
    	var dateStr = ""
    	if(date){
    		dateStr = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
    	}
    	return dateStr
	};
    
	/**
	 * 打开日程
	 * 
	 * id 主键
	 * title 标题
	 * url 地址
	 * 
	 */
	this.openEventSysPlan=function(id,title,url,currentViweDate){
		//var url = __ctx + "/platform/system/sysPlan/edit.ht?id="+id
		if(url.indexOf("?") > 0){
			url += "&id=" + id + "&currentViweDate=" + currentViweDate;
		}else{
			url += "?id=" + id + "&currentViweDate=" + currentViweDate;
		}
		window.location.href = url;
	 	/*DialogUtil.open({
	 		height:800,
	 		width: 1000,
	 		title : '日程：' + title,
	 		url: url
	 	});
	 	*/
	};
	
	
	this.toDoEventMouseover=function(obj,thisEvent){
		var sysPlan = thisEvent.sysPlan;
		if(sysPlan==null){
			return;
		} 
		var html = _self.getEventMouseoverHtml(sysPlan);
		$(".fc-content",obj).qtip({  
				content:{
					text:html,
					title:{
						text:sysPlan.taskName			
					}
				},
		        position: {
		        	at:'top left',
		        	target:'event',
		        	adjust: {
			        		x:25,
			        		y:15
		   				}, 
					viewport:  $(window)
		        },
		        show: {
		            effect: function() {
		                $(this).fadeTo(300, 1);
		            }
		        },
		        hide: {
		            effect: function() {
		                $(this).slideUp();
		            }
		        },
		        style: {
		       	  classes:'ui-tooltip-light ui-tooltip-shadow'
		        } 			    
	 	});	
	};
	
	
	this.getEventMouseoverHtml=function(sysPlan){
		var html = '';
		if(sysPlan==null){
			return html;
		}
		html = '<table class="table-detail" cellpadding="0" cellspacing="0" border="0">	';
		html +=	'<tr><th width="20%">任务名称:</th><td>'+sysPlan.taskName+'</td><th width="20%">日程进度:</th><td>'+sysPlan.rate+'%</td></tr>';		 
		html +=	'<tr><th width="20%">提交人:</th><td>'+sysPlan.submitor+'</td><th width="20%">负责人:</th><td>'+sysPlan.charge+'</td></tr>';		 
		html +=	'<tr><th width="20%">开始时间:</th><td>'+sysPlan.startTimeStr+'</td><th width="20%">结束时间:</th><td>'+sysPlan.endTimeStr+'</td></tr>';		 
		html += '</table>';
        return html;
	}
	
};

