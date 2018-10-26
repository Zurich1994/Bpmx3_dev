
/**
 * 扩展 container
 * by cjj
 */

Ext.override(Ext.Container, {  
    getCmpByName : function(name) {  
        var getByName = function(container, name) {  
            var items = container.items;  
            if (items != null) {  
                for (var i = 0; i < items.getCount(); i++) {  
                    var comp = items.get(i);  
                    var cp = getByName(comp, name);  
                    if (cp != null)  
                        return cp;  
                    if (comp.getName && name == comp.getName()) {  
                        return comp;  
                        break;  
                    }  
                }  
            }  
            return null;  
        };  
        return getByName(this, name);  
    }  
}); 

Ext.ns('HT');
/**
 * 转换时间
 */
HT.parseLongTime = function(v,dateFormat){
	if(Ext.isEmpty(v))
		return "";
	return   Ext.Date.format( new Date(v),(dateFormat?dateFormat:'Y-m-d H:i:s'));
};
HT.parseLongDate = function(v,dateFormat){
	if(Ext.isEmpty(v))
		return "";
	return   Ext.Date.format( new Date(v),(dateFormat?dateFormat:'Y-m-d'));
};

HT.loadingText =  lang.tip.loadingText;

/**
 * 加载
 */
HT.loadMask = function(message){
 	Ext.Viewport.setMasked({
	            xtype: 'loadmask',
	            message: message?message:HT.loadingText
	 });
}
/**
 * 取消
 */
HT.unMask = function(){
 	Ext.Viewport.unmask();
}

/**
 * 分页数据
 */
Ext.define('HT.Store',  {
	extend:'Ext.data.Store',
	name:'htStore',
	constructor: function (conf) {
		conf = conf || {};
			
		Ext.apply(conf,{
			model:conf.model,
			pageSize:conf.pageSize?conf.pageSize:10,//对应的每页数据量 
			currentPage:conf.currentPage?conf.currentPage:1,//当前页
			autoLoad: conf.autoLoad?conf.autoLoad:true,
		    proxy: {
		        type: "ajax",
		        url: conf.url,
		        pageParam: conf.pageParam?conf.pageParam:'page', //设置page参数，默认为page 
		        limitParam: conf.limitParam?conf.limitParam:'pageSize', //设置pageSize参数，默认为pageSize  
		        reader: {
		            type: 'json',
		            rootProperty: conf.rootProperty?conf.rootProperty:'results',//顶部数据
		            totalProperty :conf.totalProperty?conf.totalProperty:'totalCounts'//总记录数
		        }
		    }	
	    });
    	this.callParent([conf]);
	}
});

/**
 * 分页
 */
Ext.define('HT.List', {
    extend: 'Ext.List',    
    name: 'htList',
    constructor: function (conf) {		
    	conf = conf || {};		
      	Ext.apply(conf,{
      			title:conf.title?conf.title:'',
                store: conf.store,
      			scrollable:true,
            	loadingText:conf.loadingText?conf.loadingText:HT.loadingText,
                emptyText:'<p class="no-searches">'+( conf.emptyText?conf.emptyText:lang.tip.noRecords)+'</p>'
         });
          	
    	this.callParent([conf]);
	}
});

/**
 * 分页
 */
Ext.define('HT.PageList', {
    extend: 'Ext.List',    
    name: 'htPageList',
    constructor: function (conf) {		
    	conf = conf || {};	

      	Ext.apply(conf,{
      			title:conf.title?conf.title:'',
                store: conf.store,
            	loadingText:conf.loadingText?conf.loadingText:HT.loadingText,
                plugins: [{
				　　　　xclass: 'Ext.plugin.PullRefresh',
				　　　　pullRefreshText: conf.pullRefreshText?conf.pullRefreshText:lang.tip.pullRefreshText,
				　　　　releaseRefreshText: conf.releaseRefreshText?conf.releaseRefreshText:lang.tip.releaseRefreshText,
				　　　　loading:  conf.loading?conf.loading:lang.tip.loading,
					    lastUpdatedText:lang.tip.lastUpdatedText,
					    loadingText :HT.loadingText,
				　　　　refreshFn: conf.refreshFn
                },{
                	xclass: 'Ext.plugin.ListPaging',
            　　　　　　　　loadMoreText: conf.loadMoreText?conf.loadMoreText:lang.tip.loadMoreText,
            　　　　　　　　noMoreRecordsText: conf.noMoreRecordsText?conf.noMoreRecordsText:lang.tip.noMoreRecordsText,
            　　　　　　　　autoPaging: true//设置为TRUE将自动触发
            　　　　　　}],
                emptyText: '<p class="no-searches">'+( conf.emptyText?conf.emptyText:lang.tip.noRecords)+'</p>',
                itemTpl:conf.itemTpl,
                listeners:[{
                	"beforerefresh" : function(){
                		this.loadMark.enable();
                	}}
                ]
                
         });
          	
    	this.callParent([conf]);
	}
});





/**
 * 提交表单
 * 
 * @example
 * 
 * <pre>
 *  使用以下所示：
 * 	$postForm({
 * 	formPanel : this.formPanel,
 * 	scope : this,
 * 	url : __ctxPath + '/admin/saveBook.do',
 * 	params : {},
 * 	callback : function(fp, action) {
 * 		if (this.callback) {
 * 			this.callback.call(this.scope);
 * 		}
 * 		this.close();
 * 	}
 *  });
 * </pre>
 * 
 * @param {}
 *            conf
 */
var $postForm = function(conf) {
	var scope = conf.scope ? conf.scope : this,
		waitMsg = Ext.isEmpty(conf.waitMsg) ? lang.tip.waitMsg : conf.waitMsg,
		successMsg = Ext.isEmpty(conf.successMsg)
			? lang.tip.successMsg
			: conf.successMsg,
		failureMsg = Ext.isEmpty(conf.failureMsg)
			? lang.tip.failureMsg
			: conf.failureMsg;
	// Mask the form
    conf.formPanel.setMasked({
            xtype: 'loadmask',
            message: waitMsg
        });
	conf.formPanel.submit({
			scope : scope,
			url : conf.url,
			method : 'post',
			params : conf.params,
			//waitMsg : waitMsg,
			success : function(fp, action) {
				if (conf.callback) {
					conf.callback.call(scope, fp, action);
				}
				if (conf.success) {
					conf.success.call(scope, fp, action);
				}
			},
			failure : function(fp, action) {
				if (!Ext.isEmpty(action) && !Ext.isEmpty(action.msg)) {
					failureMsg = action.msg;
				}
				Ext.Msg.alert('', action.msg);	
				if (conf.callback) {
					conf.callback.call(scope, fp, action);
				}
			}
		});
};

/**
 * 扩展String的方法
 *  var str=String.format("姓名:{0},性别:{1}","ray","男");
 * alert(str);
 * @return {}
 */
String.format=function(){
	var template=arguments[0];
	var args=arguments;
	var str=template.replace(/\{(\d+)\}/g,function(m,i){
		var k=parseInt(i)+1;
		return args[k];
	});
	return str;
};


