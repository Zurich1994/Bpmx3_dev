var DesktopManage=function(){
	{
		this.desktop=null;
		this.deskColumns=[];
		this.thisDiv=null;
	}
	//构建桌面布局参数
	this.setDesktop=function(){
		var self=this;
		this.desktop={  
				sortable: true,
	            create: function() {
	            },
	            removeItem: function(i,j){
	            	self.removeColumn(i,j);
	            },
	            afterDrag:function(){
	            	self.sortDesk();
	            },
	            columns:self.deskColumns
	         };
	}
	//初始化桌面
	this.init=function(){
		var self=this;
		var layoutId=$("#layoutId").val();
		var url=__ctx+'/platform/system/desktop/getMyDesktop.ht';
		if(layoutId){
			url=__ctx+"/platform/system/desktop/getLayoutData.ht?layoutId="+layoutId;
		}
		$.post(url,function(data){
			self.handerData(data);
			self.setDesktop();
			$('#myPage').portlet(self.desktop);
		});
	};
	//解析数据构建deskColumns对象
	this.handerData=function(data){
		var self=this;
		self.deskColumns=[];
		var w=document.body.offsetWidth;
		for(var i=0;i<data.columns.length;i++){
			var width=data.widthMap[i+1]*(w-30)/100;
			var portlets=new Array();
			if(data.datas[i+1]!=[]&&data.datas[i+1]!=null){
				for(var j=0;j<data.datas[i+1].length;j++){
					var json=data.datas[i+1][j];
					var columnId=json.columnId;
					if(!columnId){
						columnId=json.id;
					}
					var moreUrl="";
					if(json.columnUrl!=null){
						moreUrl=__ctx+json.columnUrl;
					}
					var url=__ctx+'/platform/system/desktopColumn/getColumnData.ht?id='+columnId;
					var portlet={
								attrs: {
			                        id: columnId
			                    },
								title:json.columnName,
								icon: 'ui-icon-signal-diag',
								more:json.columnUrl,
								content: {
				                    //设置区域内容属性
				                    style: {
				                        height: '320px'
				                    },
				                    type: 'ajax',
			                        dataType: 'json',
			                        url: url,
			                        formatter: function(o, pio, data) {
			                        	var html='';
			                           	if(data.html){
			                        	   html=data.html;
			                          	}else{
			                          		var template=data.column.html;
			                          		var json=data.result;
			                        	  	html=easyTemplate(template,json).toString();
			                           	}
			                           	return html;
			                        }
								}};
					portlets.push(portlet);
				}
			}
			var column={width:width,portlets:portlets};
			self.deskColumns.push(column);
		}
	};
	//点击区域更换样式
	this.changeClass=function(obj){
		var self=this;
		if($(obj).hasClass("active")){
			$(obj).removeClass("active");
			self.thisDiv=null;
			self.thisColumn=null;
		}else{
			$(".ui-sortable").removeClass("active");
			$(obj).addClass("active");
			self.thisDiv=$('.active');
		}
	};
	//添加栏目
	this.addColumn=function(){
		var self=this;
		if(self.thisDiv==null){
			$.ligerDialog.error("请选择一列！","提示信息");
			return;
		}
		var index=$(self.thisDiv).index();
		var columnId = $("#columnName").val();
		var columnName=$('select option:selected').attr('name');
		var moreUrl=$('select option:selected').attr('moreUrl');
		var url=__ctx + "/platform/system/desktopColumn/getColumnData.ht?id="+ columnId;
		var column={
				attrs: {
                    id: columnId
                },
				title:columnName,
				more:moreUrl,
				icon: 'ui-icon-signal-diag',
				content: {
					//设置区域内容属性
                    style: {
                        height: '320px'
                    },
                    type: 'ajax',
                    dataType: 'json',
                    url: url,
                    formatter: function(o, pio, data) {
                    	var html='';
                       	if(data.html){
                    	   html=data.html;
                      	}else{
                      		var template=data.column.html;
                      		var json=data.result;
                    	  	html=easyTemplate(template,json).toString();
                       	}
                       	return html;
                    }
				}
			};
		self.deskColumns[index].portlets.push(column);
		self.setDesktop();
		self.resetDesk();
		self.thisDiv=null;
	};
	//删除栏目
	this.removeColumn=function(sn,col){
		var self=this;
		self.deskColumns[col].portlets.splice(sn,1);
		self.setDesktop();
	};
	//重置桌面
	this.resetDesk=function(){
		var desk=$("#myPage").clone();
		$('#myPage').remove();
		$(desk).empty();
		$('.panel-body').append(desk);
		$('#myPage').portlet(this.desktop);
	};
	//更换布局
	this.changLayout=function(){
		var self=this;
		var layoutId=$("#colsSelect").val();
		var url=__ctx+"/platform/system/desktop/getLayoutData.ht?layoutId="+layoutId;
		$.post(url,function(data){
			self.handerData(data);
			self.setDesktop();
			self.resetDesk();
		});
	};
	//重新排列布局
	this.sortDesk=function(){
		var self=this;
		var div=$("#myPage");
		var columns=new Array();
		$(div).find('.ui-portlet-column').each(function(){
			var width=$(this).width();
			var portlets=new Array();
			if($(this).children().length==0){
				columns.push({width:width,portlets:[]});
				return true;
			}
			$(this).find('.ui-portlet-item').each(function(index){
				var id=$(this).attr('id');
				var columnName=$(this).attr('title');
				var moreUrl=$(this).attr('moreurl');
				var url=__ctx + "/platform/system/desktopColumn/getColumnData.ht?id="+ id;
				
				var portlet={
						attrs: {
	                        id: id
	                    },
						title:columnName,
						more:moreUrl,
						icon: 'ui-icon-signal-diag',
						content: {
		                    //设置区域内容属性
		                    style: {
		                        height: '320px'
		                    },
		                    type: 'ajax',
		                    dataType: 'json',
		                    url: url,
		                    formatter: function(o, pio, data) {
		                    	var html='';
		                       	if(data.html){
		                    	   html=data.html;
		                      	}else{
		                      		var template=data.column.html;
		                      		var json=data.result;
		                    	  	html=easyTemplate(template,json).toString();
		                       	}
		                       	return html;
		                    }
						}};
				portlets[index]=portlet;
				
			});
			columns.push({width:width,portlets:portlets});
		});
		self.deskColumns=columns;
	};
	//保存布局
	this.saveLayout=function(){
		var self=this;
		if(self.deskColumns==[])return;
		var layoutId=$("#colsSelect").val();
		var url=__ctx+"/platform/system/desktopMycolumn/saveCol.ht";
		if(!layoutId){
			layoutId=$("#layoutId").val();
			url=__ctx+"/platform/system/desktopLayoutcol/saveCol.ht";
		}
		var cols=self.deskColumns.length;
		var data="[";
		for(var i=0;i<cols;i++){
			var columns=self.deskColumns[i].portlets;
			if(columns){
				for(var j=0;j<columns.length;j++){
					var columnId=columns[j].attrs.id;
					if(data!="[")
						data+=",";
					data+='{"col":'+(i+1)+',"sn":'+(j+1)+',"columnId":'+columnId+'}';
				}
			}
		}
		data+="]";
		if(data=="[]"){
			$.ligerDialog.error("还未设置列栏目！！",'提示信息');
			return;
		}
		$.post(url,{"layoutId":layoutId,"data":data},function(reData){				
							var parStr=$.parseJSON(reData);				
							if(parStr.result==1)
								$.ligerDialog.success( parStr.message,"提示信息",function(){
									window.location.reload();
								});
							else
								$.ligerDialog.error(parStr.message, "提示信息",function(){
									window.location.reload();
								});
						});
	};
};
//操作更多路径
function getMore(url){
	$.post(__ctx+"/platform/console/getResourceNode.ht?columnUrl="+url,function(data){
		if(data==null){
			$.ligerDialog.error("栏目更多路径配置有误",'提示信息');
		}else{
			addToTab(data.defaultUrl,data.resName,data.resId,data.icon);
		}
	});
}
 //添加到tab或者刷新
function addToTab(url,txt,id,icon){
	top.tab.addTabItem({url:__ctx+url,text:txt,tabid:id,icon:__ctx+icon});
};
