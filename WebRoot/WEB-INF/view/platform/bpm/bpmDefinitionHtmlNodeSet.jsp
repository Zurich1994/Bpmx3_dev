<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
	<head>
		<title>HTML节点设置</title>
		<%@include file="/commons/include/form.jsp"%>
		<%@include file="/commons/include/get.jsp"%>
		<%-- <link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
   	<link href="${ctx}/styles/default/css/definition.node.css" rel="stylesheet" /> --%>
		<f:link href="jquery.qtip.css"></f:link>
		<f:link href="definition.node.css"></f:link>

		<meta name="description" content="">
		<meta name="viewport" content="width=device-width">
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/js/editorT/jquery-easyui-1.3.6/themes/gray/easyui.css" />
		<link rel="stylesheet" type="text/css"
			href="${ctx}/js/editorT/jquery-easyui-1.3.6/themes/icon.css" />
		<script type="text/javascript"
			src="${ctx}/js/editorT/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
		<link rel=stylesheet
			href="${ctx}/js/editorT/libs/bootstrap/css/bootstrap.css" />
		<link rel=stylesheet
			href="${ctx}/js/editorT/libs/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css" />
		<link rel="stylesheet"
			href="${ctx}/js/editorT/src/css/graph.editor.css" />
		<link rel="stylesheet" href="${ctx}/js/editorT/styles/main.css" />

		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/SignRuleWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/FlowRuleWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/FlowVarWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/ForkConditionWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/FlowEventWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/FlowForkJoinWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/FlowMessageWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/FlowWebServiceWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/FlowReminderWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/FlowApprovalItemWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/ViewSubFlowWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/InformTypeWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/FlowSetWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/ActivityInformWindow.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/jquery/plugins/jquery.qtip.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/BpmNodeSetWindows.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/hotent/platform/bpm/FlowOrgUserScopeWindow.js"></script>

	</head>
	<body>
		<div class="layout" style="float: left; width: 80%; height: 100%;">
			<div id="editor" data-options="region:'center'"></div>
		</div>
		<div class="layout" style="float: left;">
<div style="display:none"><textarea id="json">${json}</textarea></div>

			<!-- end of panel-body -->

			<iframe id="defFrame" frameborder="0"
				src="${ctx}/Markovchain/Markovchain/markovchain/loadDetail.ht";
				scrolling="no" marginheight="0" marginwidth="0"
				onLoad="iFrameHeight()"></iframe>
		</div>
		<script src="${ctx}/js/editorT/libs/jquery.min.js"></script>
		<script src="${ctx}/js/editorT/libs/bootstrap/js/bootstrap.min.js"></script>
		<script src="${ctx}/js/editorT/libs/layout.border.js"></script>
		<script
			src="${ctx}/js/editorT/libs/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
		<!-- endbuild -->

		<script src="${ctx}/js/editorT/libs/qunee-min-2016-4-30.js"></script>

		<!-- build:js libs/graph.editor/graph.editor.js -->
		<script src="${ctx}/js/editorT/src/common/i18n.js"></script>
		<script src="${ctx}/js/editorT/src/common/DomSupport.js"></script>
		<script src="${ctx}/js/editorT/src/common/DragSupport.js"></script>
		<script src="${ctx}/js/editorT/src/common/FileSupport.js"></script>
		<script src="${ctx}/js/editorT/src/common/JSONSerializer.js"></script>
		<script src="${ctx}/js/editorT/src/common/ExportPane.js"></script>
		<script src="${ctx}/js/editorT/src/common/Toolbar.js"></script>
		<script src="${ctx}/js/editorT/src/common/ToolBox.js"></script>
		<script src="${ctx}/js/editorT/src/common/PopupMenu.js"></script>
		<script src="${ctx}/js/editorT/src/common/PropertyPane.js"></script>
		<script src="${ctx}/js/editorT/src/graph.editor.js"></script>
		<script src="${ctx}/js/editorT/scripts/graphs.js"></script>
		<script  type="text/javascript" src="${ctx}/js/editorT/src/common/ArrayList.js"></script>
		<script src="${ctx}/js/editorT/src/common/parseXML.js"></script>
		<!-- endbuild -->

		<script type="text/javascript" language="javascript">   

function iFrameHeight() {   

var ifm= document.getElementById("defFrame");   

var subWeb = document.frames ? document.frames["defFrame"].document : ifm.contentDocument;   

if(ifm != null && subWeb != null) {

  ifm.height = document.body.clientHeight;

   ifm.width = subWeb.body.scrollWidth;

}   

}   

</script>
		<script>
 var data=${json};
 var xml='${xml}';
var graph;
  $(function () {
   Q.registerImage('开始', '/mas/js/editorT/data/cisco/start.png');
  Q.registerImage('结束', '/mas/js/editorT/data/cisco/stop.png');
  Q.registerImage('读页面', '/mas/js/editorT/data/cisco/readPage.png');
  Q.registerImage('写页面', '/mas/js/editorT/data/cisco/writePage.png');
  Q.registerImage('跳转页面', '/mas/js/editorT/data/cisco/jumpPage.png');
  Q.registerImage('查找数据', '/mas/js/editorT/data/cisco/dataSearch.png');
  Q.registerImage('删除数据', '/mas/js/editorT/data/cisco/dataDelete.png');
  Q.registerImage('修改数据', '/mas/js/editorT/data/cisco/dataUpdate.png');
  Q.registerImage('增加数据', '/mas/js/editorT/data/cisco/dataAdd.png');
  Q.registerImage('webService服务', '/mas/js/editorT/data/cisco/webService.png');
  Q.registerImage('事务图', '/mas/js/editorT/data/cisco/affairs.png');
  Q.registerImage('子事务图', '/mas/js/editorT/data/cisco/childAffairs.png');
  Q.registerImage('外部子图', '/mas/js/editorT/data/cisco/startSubFlow.png');
  Q.registerImage('消息任务', '/mas/js/editorT/data/cisco/messageTask.png');
  Q.registerImage('会签任务', '/mas/js/editorT/data/cisco/multiInstance.png');
  Q.registerImage('文字', '/mas/js/editorT/data/cisco/word.png');
  Q.registerImage('提交', '/mas/js/editorT/data/cisco/submitwork.png');
  Q.registerImage('发送邮件服务', '/mas/js/editorT/data/cisco/sendMailService.png');
  Q.registerImage('收邮件服务', '/mas/js/editorT/data/cisco/receiveMailService.png');
  Q.registerImage('分支', '/mas/js/editorT/data/cisco/ExclusiveGateway.png');
  Q.registerImage('同步', '/mas/js/editorT/data/cisco/ParallelGateway.png');
  Q.registerImage('条件同步', '/mas/js/editorT/data/cisco/InclusiveGateway.png');
  Q.registerImage('用户任务', '/mas/js/editorT/data/cisco/user.png');
  Q.registerImage('脚本任务', '/mas/js/editorT/data/cisco/script.png');
  Q.registerImage('ftp上传服务', '/mas/js/editorT/data/cisco/upservice.png');
  Q.registerImage('ftp下载服务', '/mas/js/editorT/data/cisco/downservice.png');
  Q.registerImage('发送短信服务', '/mas/js/editorT/data/cisco/sendNoteService.png');
  Q.registerImage('接收短信服务', '/mas/js/editorT/data/cisco/receiveNoteService.png');
  Q.registerImage('加载地图服务', '/mas/js/editorT/data/cisco/uploadMapService.png');
  Q.registerImage('定位服务', '/mas/js/editorT/data/cisco/locationService.png');
  Q.registerImage('计算机显示路线服务', '/mas/js/editorT/data/cisco/countShowLineService.png');
  Q.registerImage('测试图标', '/mas/js/editorT/data/cisco/newGroup.png');
  Q.registerImage('测试图标1', '/mas/js/editorT/data/cisco/newGroup1.png');
        $('#editor').graphEditor({ images: [
               
                   
                 {             
                   	name: '事件',
                    images:[
                    {image: '开始', clientProperties:{property:'StartEvent'},properties: {name:'开始',size: {width: 32}}},   
                    {image: '结束', clientProperties:{property:'EndEvent'},properties: {name:'结束',size: {width: 32}}}]
                 }, 
                 {
                    	name:'新增图标',
                    	images: [
                    	 {image: '测试图标', type: 'Group', properties: {groupImage: graphs.apple,name:'测试图标',size: {width: 32}}},
                    	 {image: '测试图标1', type: 'Group', properties: {groupImage: '条件同步', styles: { 'render.color': '#43f1b3'  },name:'测试图标1',size: {width: 32}}}]	
                    },              
                  {
                  	name: '网关 ',
                  	images:[
                  	{image: '分支', clientProperties:{property:'XORGateway'},properties: {name:'分支',size: {width: 32}}},
                    {image: '同步',clientProperties:{property:'ANDGateway'}, properties: {name:'同步',size: {width: 32}}},
                    {image: '条件同步',clientProperties:{property:'ORGateway'}, properties: {name:'条件同步',size: {width: 32}}}]
                  },
                  {
                  
                    name: '脚本', 
                    	images: [
                    	{image: '脚本任务', clientProperties:{property:'ScriptTask'},properties: {name:'脚本任务',size: {width: 32}}},
                   	 	{image: 'webService服务',clientProperties:{property:'ServiceTask'}, properties: {name:'webService服务',size: {width: 32}}} ]	
                    },
                    {
                    	name: '任务',
                    	images: [
                    	{image: '消息任务',clientProperties:{property:'MessageTask'}, properties: {name:'消息任务',size: {width: 32}}},
                    	{image: '文字',clientProperties:{property:'wordTask'}, properties: {name:'文字',size: {width: 32}}},
                    	{image: '事务图', clientProperties:{property:'ScriptTask'},properties: {name:'事务图',size: {width: 32}}},
                  		{image: '用户任务',clientProperties:{property:'UserTask'}, properties: {name:'用户任务',size: {width: 32}}},
                    	{image: '会签任务',clientProperties:{property:'SignTask'}, properties: {name:'会签任务',size: {width: 32}}},
                    	{image: '子事务图',clientProperties:{property:'ScriptTask'}, properties: {name:'子事务图',size: {width: 32}}}	]
                    },
                     {
                    	name:'工作流程操作',
                    	images: [
                    	 {image: '提交',clientProperties:{property:'ScriptTask'}, properties: {name:'提交',size: {width: 32}}}]
                    },
                    {
                    	name: '页面操作',
                   	 	images: [
                    	{image: '跳转页面',clientProperties:{property:'ScriptTask'}, properties: {name:'页面跳转',size: {width: 32}}},
                   		{image: '写页面',clientProperties:{property:'ScriptTask'}, properties: {name:'写页面',size: {width: 32}}},
                    	{image: '读页面', clientProperties:{property:'ScriptTask'},properties: {name:'读页面',size: {width: 32}}} ]	
                    },
                     {
                    	name:'邮件服务',
                    	images: [
                    	{image: '发送邮件服务', clientProperties:{property:'ScriptTask'},properties: {name:'发送邮件服务',size: {width: 32}}},
                  		{image: '收邮件服务', clientProperties:{property:'ScriptTask'},properties: {name:'收邮件服务',size: {width: 32}}}]
                    },   
                   {	
                    	name: '数据操作服务',
                    	images: [
                  		{image: '查找数据',clientProperties:{property:'ScriptTask'}, properties: {name:'查找数据',size: {width: 32}}},
                   		{image: '增加数据', clientProperties:{property:'ScriptTask'},properties: {name:'增加数据',size: {width: 32}}}, 
                    	{image: '修改数据',clientProperties:{property:'ScriptTask'}, properties: {name:'修改数据',size: {width: 32}}},           
                    	{image: '删除数据', clientProperties:{property:'ScriptTask'},properties: {name:'删除数据',size: {width: 32}}}]	
                    },                  
                     {
                    	name:'ftp服务',
                    	images: [
                    	{image: 'ftp上传服务',clientProperties:{property:'ScriptTask'}, properties: {name:'ftp上传服务',size: {width: 32}}},
                   		{image: 'ftp下载服务',clientProperties:{property:'ScriptTask'}, properties: {name:'ftp下载服务',size: {width: 32}}}]
                    },{
                    	name:'地图服务',
                    	images: [
                    	{image: '加载地图服务',clientProperties:{property:'ScriptTask'}, properties: {name:'加载地图服务',size: {width: 32}}},
                    	{image: '计算机显示路线服务',clientProperties:{property:'ScriptTask'}, properties: {name:'计算机显示路线服务',size: {width: 32}}},
                    	{image: '定位服务',clientProperties:{property:'ScriptTask'}, properties: {name:'定位服务',size: {width: 32}}}]     	
                    },
                    {
                    	name:'短信服务',
                    	images: [
                    	{image: '发送短信服务',clientProperties:{property:'ScriptTask'}, properties: {name:'发送短信服务',size: {width: 32}}},
                    	{image: '接收短信服务', clientProperties:{property:'ScriptTask'},properties: {name:'接收短信服务',size: {width: 32}}}]
                    }, 
                   {
                    	name:'其他',
                    	images: [
                
                    	 {image: '外部子图',clientProperties:{property:'startSubFlowTask'}, properties: {name:'外部子图',size: {width: 32}}}]
                    },
                  {
             		   	name: 'default.shapes',
               			displayName: getI18NString('Default Shapes'),
                		prefix: 'Q-',
                		images: Q.Shapes.getAllShapes(this.imageWidth, this.imageHeight)
           		}],callback: function(editor){
                
        	graph = editor.graph;
           this.graph.parseJSON(data);
			//通过client属性查找图元
            function getElementByClientProperty(name, value){
                var result;
                graph.forEach(function(element){
                    if(element.get(name) == value){
                        result = element;
                        return false;
                    }
                })
                return result;
            }
            var xmlDoc=parseXML(xml);//解析xml
			var nodelist=new ArrayList(); //存节点的链表
 			var elements = xmlDoc.getElementsByTagName("node");
			Q.log(elements.length);
			
 			for (var i = 0; i < elements.length; i++) {
                var a=xmlDoc .getElementsByTagName("node")[i];
                nodelist.add(getElementByClientProperty('bpmNodeId', a.getAttribute("id")));
				
 			}var d=nodelist.get(0);
		  				
 			
 			var edgelist=new ArrayList(); //存边的链表
           for (var i=0;i<nodelist.size()-1;i++) {
           		var edge=nodelist.get(i).getEdgeBundle(nodelist.get(i+1)).edges[0];
           		edgelist.add(edge);
           }

			var index = 0;
			
			var i=0;
			var timer = setInterval(function(){
		  		index++;
		  		index = index%31;
		  			if(index==1)
		  			{
		  				nodelist.get(i).setStyle(Q.Styles.RENDER_COLOR, "#2898E0");
		  				var c=nodelist.get(i).valueOf("bpmNodeId");
		  				Q.log(c);
		  			}
		  			if(index==0)
		  			{
		  				i++;
		  			}
		  			if(i<edgelist.size())
		  			{Q.log(i);Q.log(index);
		  				edgelist.get(i).setStyle(Q.Styles.ARROW_TO_OFFSET, -0.1 -0.035 * (27 - index));
		  				edgelist.get(i).setStyle(Q.Styles.EDGE_COLOR, "#2000E0");  
		  			}
		  			if(i==edgelist.size())
		  			{
		  				nodelist.get(i).setStyle(Q.Styles.RENDER_COLOR, "#2898E0");
		  				clearInterval(timer);
		  			}
			}, 100);
		
	
   graph.onclick=function(evt){
//nodeWithScale.setStyle(Q.Styles.SHADOW_COLOR, "#888");
//nodeWithScale.setStyle(Q.Styles.SHADOW_OFFSET_X, 5);
//nodeWithScale.setStyle(Q.Styles.SHADOW_OFFSET_Y, 5);
//nodeWithScale.setStyle(Q.Styles.SHADOW_BLUR, 5);   
	var data = evt.getData();	
	//alert(data.id);
	var bb=getElementById(data.id);
	//alert(bb);
	graph.selectionModel.set(bb);
	var data1=String(data);
	
	if(data){
	var tb=$("#defFrame").contents().find("#markovchainItem");
	    //alert(data);
         //alert(typeof data);
         
	   //  var tb=$("#defFrame").contents().find("#tableId");	     
	     var rows=tb.find("tr").length;
	       
	     for(var i=0;i<rows;i++)
	     {
	     var td=tb.find("tr").eq(i+1);
	     td.removeClass("over");
	     }
	     for(var i=0;i<rows;i++)
	     {
	     
	     var need=tb.find("tr").eq(i+1).find("td").eq(2).html() 
	     var td=tb.find("tr").eq(i+1);
	   
	    if(data1==need)
	    {    
	    td.addClass("over");
	    //tb.find("tr").eq(i+1).css("color","red");
	    }
}
}
}
           graph.moveToCenter();
          // initTreeAndTable();
         }});
    });
    function ensureVisible(node) {
        var bounds = graph.getUIBounds(node);
        var viewportBounds = graph.viewportBounds;
        if (!viewportBounds.contains(bounds)) {
            graph.sendToTop(node);
            graph.centerTo(node.x, node.y);
        }
    }

function getElementById(id){
            var result;
            graph.forEach(function(e){
                if(getId(e) == id){
                    result = e;
                    return false;
                }
            })
            return result;
        }
        
        function getId(element){
        
           // return element.get('id');
            return element;
        }
    function initTreeAndTable(){
        var treeId = "#tree";
        var tableId = "#table";

        var tableOptions = [
            {field: 'id', title: 'ID', width: 100, propertyType: Q.Consts.PROPERTY_TYPE_CLIENT},
            {field: 'index', title: '行号', width: 100, propertyType: Q.Consts.PROPERTY_TYPE_CLIENT},
            {field: 'text', title: '标签', width: 100, name: 'name', align: 'center'},
            {field: 'x', title: 'X', width: 100, align: 'center'},
            {field: 'y', title: 'Y', width: 100, align: 'center'},
        ]

        function getTreeIcon(d) {
            return d instanceof Q.Edge ? "edge_icon" : "node_icon";
        }
        function toTreeData(element){
            var data = {iconCls: getTreeIcon(element)}
            tableOptions.forEach(function(option){
                var name = option.name || option.field;
                var value;
                if(option.propertyType == Q.Consts.PROPERTY_TYPE_CLIENT){
                    value = element.get(name);
                }else if(option.propertyType == Q.Consts.PROPERTY_TYPE_STYLE){
                    value = element.getStyle(name);
                }else{
                    value = element[name];
                }
                data[option.field] = value;
            })
            if(!data.id){
                data.id = Date.now();
                element.set('id', data.id);
            }
            return data;
        }

       

        function loadDataFromGraph(){
            var datas = [];
            var map = {};
            graph.graphModel.forEachByBreadthFirst(function (d) {
                var data = toTreeData(d);
                var parent = d.parent;
                if (!parent) {
                    datas.push(data);
                    return;
                }
                parent = map[getId(parent)];
                var children = parent.children;
                if (!children) {
                    children = parent.children = [];
                }
                children.push(data);
            });
            return datas;
        }

        var datas = loadDataFromGraph();


        function updateElement(element){
            var data = $(treeId).tree('find', getId(element));
            if(!data){
                return;
            }
            var newData = toTreeData(element);
            newData.target = data.target;
            $(treeId).tree('update', newData);

            var rowIndex = $(tableId).datagrid('getRowIndex', data.id);
            if(rowIndex < 0){
                return;
            }
            $(tableId).datagrid('updateRow',{
                index: rowIndex,
                row: newData
            });
        }

        $(tableId).datagrid({
            data: datas,
            fitColumns: true,
            idField: 'id',
            columns: [tableOptions],
            onUnselect: function(index,row){
                if (graph.selectionAjdusting) {
                    return;
                }
                var node = getElementById(row.id);
                if(node){
                    graph.selectionModel.remove(node);
                }
            },
            onSelect: function (index,row) {
                if (graph.selectionAjdusting) {
                    return;
                }
                var node = getElementById(row.id);
                if(node){
                    graph.selectionModel.set(node);
                }
            }
        });

        $(treeId).tree({
            data: datas,
            onSelect: function () {
                if (graph.selectionAjdusting) {
                    return;
                }
                var selected = $(treeId).tree("getSelected");
                if (selected) {
                    var node = getElementById(selected.id);
                    alert("1");
                    graph.selectionModel.set(node);
                    if (node) {
                        ensureVisible(node);
                    }
                }
            }
        });
        graph.selectionChangeDispatcher.addListener(function (evt) {
            if (graph.selectionAjdusting) {
                return;
            }
            graph.selectionAjdusting = true;
            var selection = [];
            $(tableId).datagrid('unselectAll');
            graph.selectionModel.forEach(function (node) {
                var id = getId(node);
                var node = $(treeId).tree('find', id);
                if (node) {
                    selection.push(node.target);
                    $(tableId).datagrid('selectRecord', id);
                }
            });
            $(treeId).tree('select', selection);
            graph.selectionAjdusting = false;
        });

        graph.listChangeDispatcher.addListener(function (evt) {
            var data = evt.data;
            switch (evt.kind) {
                case Q.ListEvent.KIND_ADD :
                    var treeData = toTreeData(data);
                    $(treeId).tree('append', {data: [treeData]});
                    $(tableId).datagrid('appendRow', treeData);
                    break;
                case Q.ListEvent.KIND_REMOVE :
                    Q.forEach(data, function (node) {
                        var node = $(treeId).tree('find', node.id);
                        if (node) {
                            $(treeId).tree('remove', node.target);
                        }
                        var node = $(tableId).datagrid('find', node.id);
                        if (node) {
                            $(tableId).datagrid('remove', node.target);
                        }
                    });
                    break;
                case Q.ListEvent.KIND_CLEAR :
                    break;
            }
        });
        graph.dataPropertyChangeDispatcher.on(function(evt){
            if(evt.source){
                updateElement(evt.source);
            }
        })
    }
 </script>

	</body>
</html>
