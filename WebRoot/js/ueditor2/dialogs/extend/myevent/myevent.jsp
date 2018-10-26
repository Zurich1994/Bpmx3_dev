<%@page language="java" pageEncoding="UTF-8" 
import = "com.hotent.core.util.UniqueIdUtil"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<link rel="stylesheet" type="text/css" href="${ctx}/styles/default/css/form.css">
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDialog2.js"></script>
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/myligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>

<style type="text/css">
body {
    overflow: hidden;
}

a.extend {
    display: inline;
}
.mybase{
margin-left: 10px;
margin-bottom: 10px;
margin-right: 10px;
margin-top: 10px;
border: 1px solid #ddd;
}
.base{
    margin-bottom: 10px;
}
.field-ul {
    width: 95%;
    height: 95%;
    margin: 0;
    padding: 0;
    overflow-y: auto;
    overflow-x: hidden;
}

.fields-div {
    float: left;
    border: 1px solid #828790;
    width: 160px;
    height: 260px;
    overflow: auto;
}

.domBtnDiv {
    display: block;
    margin-left: 5px;
    float: left;
    width: 340px;
    height: 260px;
    background-color: powderblue;
    overflow-y: auto;
    overflow-x: hidden;
}

#fieldtextcontainer {
    height: 200px;
    overflow-y: auto;
    overflow-x: hidden;
}

#fieldTable {
    margin: 0;
}
table{
    margin-top: 3px;
    margin-bottom: 7px;
}
select {
    margin: 5px 0;
    border: 1px solid #ccc;
}

.panel-toolbar {
    height: 20px;
    margin-top: 2px;
    padding: 8px 5px 5px;
    background: #FFFFFF;
    border-bottom: solid 0px #cacaca;
    border-top: solid 0px #cacaca;
}

</style>
<style type="text/css">
    .container {
        position: absolute;
        display: none;
        padding-left: 10px;
    }
    
    .shadow {
        float: left;
    }
    
    .frame {
        position: relative;
        background: #fff;
        padding: 6px;
        display: block;
        -moz-box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.6);
        -webkit-box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.6);
        box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.6);
    }
    
    .clear {
        clear: left;
    }
    
    label,
    a {
        width:75px;
        font-size: 13px;
        color: #4f6b72;
    }
    input[type="text"] {font-size:13px;color:#4f6b72;border:1px solid #4f6b72;height:25px;display: inline-block;
    min-height:23px;
    font-size: 14px;
    line-height: 1.428571429;
    color: #555555;
    vertical-align: middle;
    background-color: #ffffff;
    background-image: none;
    border: 1px solid #cccccc;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
    -webkit-transition: border-color ease-in-out .15s, box-shadow
     ease-in-out .15s;
    transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    text-align:center;}
    
    .type {font-size:13px;color:#4f6b72;border:1px solid #4f6b72;display: inline-block;
    width: 300px;
    font-size: 12px;
    line-height: 1.428571429;
    color: #555555;
    vertical-align: middle;
    background-color: #ffffff;
    background-image: none;
    border: 1px solid #cccccc;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
    -webkit-transition: border-color ease-in-out .15s, box-shadow
     ease-in-out .15s;
    transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    margin-right: 5px;
    margin-top: 5px;
    margin-bottom: 5px;
    }
    div.frame div {
        margin-bottom: 5px;
    }
    
    div.frame div.foot {
        margin-top: 10px;
    }
    
    div.frame label {
        margin: 0 10px 0 5px;
    }
    
    div.frame a:link,
    div.frame span a:visited {
        text-decoration: none;
    }
</style>
<script type="text/javascript">
    window.onload = function() {
        document.onclick = function(e) {
            var classname = e.target.className.split(" ");
            if(classname[0]!="container"&&classname[0]!="foot"&&classname[0]!="mydiv"&&classname[0]!="doms"&&classname[0]!="label"&&classname[0]!="mybase"&&classname[0]!="shadow"&&classname[0]!="frame"&&classname[0]!="inframe"&&classname[0]!="doms"&&classname[0]!="type"&&classname[0]!="selectinvar"&&classname[0]!="selectoutvar"&&classname[0]!="varoption"){
                $(".container").hide(200);
            }
        }
        $(".container").click = function(e) {
            e = e || event;
            stopFunc(e);
        }
    }
    function stopFunc(e) {
        e.stopPropagation ? e.stopPropagation() : e.cancelBubble = true;
    }
</script>
<script type="text/javascript"><!--
var formDefId = window.parent.formDefId;
var formsubject = window.parent.formsubject;
var _element;
var readdom = [];
var writedom = [];
var re="";
var wr = "";
var ii=0;
var rtnData = [{    
    "isParent": "true",
    "open": "true",
    "parentId": 0,
    "domId": 1,
    "domtag":"",
    "realname":"页面",
    "typeName": "页面"
}, {
    "isParent": "true",
    "open": "true",
    "parentId": 1,
    "domId": 21,
    "domtag":"",
    "realname":"文本",
    "typeName": "文本",
},{
    "isParent": "true",
    "open": "true",
    "parentId": 21,
    "domId": 31,
    "domtag":"textarea",
    "realname":"多行文本",
    "typeName": "多行文本",
},{
    "isParent": "true",
    "open": "true",
    "parentId": 21,
    "domId": 32,
    "domtag":"input",
    "realname":"单行文本",
    "typeName": "单行文本",
},{
    "isParent": "true",
    "open": "true",
    "parentId": 1,
    "domId": 22,
    "domtag":"a",
    "realname":"按钮",
    "typeName": "按钮"
},{
    "isParent": "true",
    "open": "true",
    "parentId": 1,
    "domId": 23,
    "domtag":"a",
    "realname":"超文本",
    "typeName": "超文本"
},{
    "isParent": "true",
    "open": "true",
    "parentId": 1,
    "domId": 24,
    "domtag":"iframe",
    "typeName": "IFRAME"
},{
    "isParent": "true",
    "open": "true",
    "parentId": 24,
    "domId": 33,
    "domtag":"iframe",
    "realname":"地图",
    "typeName": "地图"
},{
    "isParent": "true",
    "open": "true",
    "parentId": 24,
    "domId": 34,
    "domtag":"iframe",
    "realname":"表单数据",
    "typeName": "表单数据"
},{
    "isParent": "true",
    "open": "true",
    "parentId": 24,
    "domId": 35,
    "domtag":"iframe",
    "realname":"表格数据",
    "typeName": "表格数据"
},{
    "isParent": "true",
    "open": "true",
    "parentId": 24,
    "domId": 36,
    "domtag":"iframe",
    "realname":"未指定域",
    "typeName": "未指定域"
},{
    "isParent": "true",
    "open": "true",
    "parentId": 1,
    "domId": 25,
    "domtag":"ztree",
    "realname":"树",
    "typeName": "树"
},{
    "isParent": "true",
    "open": "true",
    "parentId": 1,
    "domId": 26,
    "domtag":"input",
    "realname":"隐藏域",
    "typeName": "隐藏域"
},{
    "isParent": "true",
    "open": "true",
    "parentId": 1,
    "domId": 27,
    "domtag":"input",
    "realname":"多选框",
    "typeName": "多选框"
},{
    "isParent": "true",
    "open": "true",
    "parentId": 1,
    "domId": 28,
    "domtag":"input",
    "realname":"单选框",
    "typeName": "单选框"
},{
    "isParent": "true",
    "open": "true",
    "parentId": 1,
    "domId": 29,
    "domtag":"input",
    "realname":"下拉框",
    "typeName": "下拉框"
}];
var textflag = 0;
var iframeflag = 0;
var tags = new Array();
var typenames = new Array();
var domnames="";
var varliststr="";
var domset;
var domId=-1;
var input=[];
var output=[];
var input2 = [];
var output2 = [];
function initCatcombo(){
        //文本域
        domset = $(editor.getContent()).find("textarea");
        for(var i = 0;i<domset.length;i++){
            domnames = $(domset[i]).attr("name").split(":")[2];
            if($(domset[i]).attr("domId")){
                    domId = $(domset[i]).attr("domId");
            }
            var arr  ={"isParent": "false","open": "true","parentId": 31,"domtag":"textarea","realname":$(domset[i]).attr("name"),"domId":domId,"typeName": domnames};
            rtnData.push(arr);
            domId=-1;
        }
        
        domset = $(editor.getContent()).find("input");
        for(var i = 0;i<domset.length;i++){
            if($(domset[i]).attr("type")=="text"){
                var name = $(domset[i]).attr("name");
                if(name){
                	if(name.split(":")[1]=="hidedomain"){//隐藏域
                		if($(domset[i]).attr("domId")){
                    		domId = $(domset[i]).attr("domId");
                		}
               			var arr  ={"isParent": "false","open": "true","parentId": 26,"domtag":"input","realname":$(domset[i]).attr("name"),"domId":domId,"typeName": name.split(":")[2]};
                		rtnData.push(arr);
                	}
                	else if($(domset[i]).attr("class")=="inputText"){//单行文本
                		if($(domset[i]).attr("domId")){
                    		domId = $(domset[i]).attr("domId");
                		}
               			var arr  ={"isParent": "false","open": "true","parentId": 32,"domtag":"input","realname":$(domset[i]).attr("name"),"domId":domId,"typeName": name.split(":")[2]};
                		rtnData.push(arr);
                	}
                }
            }
            domId=-1;
        }
        //按钮
        domset = $(editor.getContent()).find("input");
        for(var i = 0;i<domset.length;i++){
            if($(domset[i]).attr("type")=="button"){
                domnames = $(domset[i]).attr("name").split(":")[2];
                if($(domset[i]).attr("domId")){
                    domId = $(domset[i]).attr("domId");
                }
                var arr  ={"isParent": "false","open": "true","parentId": 22,"domtag":"input","realname":$(domset[i]).attr("name"),"domId":domId,"typeName": domnames};
                rtnData.push(arr);
            }
            domId=-1;
        }
        //复选框,单选框,下拉框
        domset = $(editor.getContent()).find("span");
        for(var i = 0;i<domset.length;i++){
            if($(domset[i]).attr("class")=="checkbox"){
                domnames = $(domset[i]).attr("myname").split(":")[2];
                if($(domset[i]).attr("domId")){
                    domId = $(domset[i]).attr("domId");
                }
                var arr  ={"isParent": "false","open": "true","parentId": 27,"domtag":"input","realname":$(domset[i]).attr("myname"),"domId":domId,"typeName": domnames};
                rtnData.push(arr);
                domId=-1;
            }else if($(domset[i]).attr("class")=="radioinput"){
            	domnames = $(domset[i]).attr("myname").split(":")[2];
                if($(domset[i]).attr("domId")){
                    domId = $(domset[i]).attr("domId");
                }
                var arr  ={"isParent": "false","open": "true","parentId": 28,"domtag":"input","realname":$(domset[i]).attr("myname"),"domId":domId,"typeName": domnames};
                rtnData.push(arr);
                domId=-1;
            }else if($(domset[i]).attr("class")=="selectinput"){
            	domnames = $(domset[i]).attr("myname").split(":")[2];
                if($(domset[i]).attr("domId")){
                    domId = $(domset[i]).attr("domId");
                }
                var arr  ={"isParent": "false","open": "true","parentId": 29,"domtag":"input","realname":$(domset[i]).attr("myname"),"domId":domId,"typeName": domnames};
                rtnData.push(arr);
                domId=-1;
            }
        }
        //超链接
        domset = $(editor.getContent()).find("a");
        for(var i = 0;i<domset.length;i++){
            var name = $(domset[i]).attr("name");
            if(name){
            	if(name.split(":")[1]=="a"){
            		domnames = name.split(":")[2];
            		if($(domset[i]).attr("domId")){
                   	 domId = $(domset[i]).attr("domId");
                	}
            		var arr  ={"isParent": "false","open": "true","parentId": 23,"domtag":"a","realname":$(domset[i]).attr("name"),"domId":domId,"typeName": domnames};
            		rtnData.push(arr);
            		domId=-1;
            	}else if(name.split(":")[1]=="b"){
            		domnames = name.split(":")[2];
            		if($(domset[i]).attr("domId")){
                   	 domId = $(domset[i]).attr("domId");
                	}
            		var arr  ={"isParent": "false","open": "true","parentId": 22,"domtag":"a","realname":$(domset[i]).attr("name"),"domId":domId,"typeName": domnames};
            		rtnData.push(arr);
            		domId=-1;
            	}
            }
        }
        //iframe 
        domset = $(editor.getContent()).find("iframe");
        for(var i = 0;i<domset.length;i++){
            if($(domset[i]).attr("class")=="normaliframe"){
                domnames = $(domset[i]).attr("name");
                if($(domset[i]).attr("domId")){
                    domId = $(domset[i]).attr("domId");
                }
                var arr  ={"isParent": "false","open": "true","parentId": 36,"domtag":"iframe","realname":$(domset[i]).attr("name"),"domId":domId,"typeName": domnames};
                rtnData.push(arr);
                 domId=-1;
            }
            if($(domset[i]).attr("class")=="ueditor_baidumap"){
                domnames = $(domset[i]).attr("name");
                if($(domset[i]).attr("domId")){
                    domId = $(domset[i]).attr("domId");
                }
                var arr  ={"isParent": "false","open": "true","parentId": 33,"domtag":"iframe","realname":$(domset[i]).attr("name"),"domId":domId,"typeName": domnames};
                rtnData.push(arr);
                 domId=-1;
            }
        }
    };
    $(function() {
        _element = null;
        _element = editor.curInput;
        initCatcombo();
        var all = $("#all").find("*");
        $.each(all, function(idx,obj) {
        	if($(obj).attr("name")=="disable"){
        		$(obj).attr("disabled","disabled");
        		$(obj).attr("onclick","alert('请先选择一个页面对象！')");
        	}
        });
    });
--></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/myCatCombo.js"></script>

</head>
<body>
	<form id="all">
    <table>
        <tr>
            <td width="120">
                <div class="panel-toolbar">
                  <div class="toolBar">
                    <div class="group"> <a class="link search" name="disable" id="eventsave" href="javascript:;"  style=" margin-left: 15px;">保存</a> </div>
                  </div>                  
                </div>
            </td>
            <td width="220">
                <div class="panel-toolbar">
                        <div class="toolBar">
                            <div class="group">
                                <input class="inputText" type="text"  id="domId" value="" style="vertical-align:middle;margin-left: 5px;margin-right: 10px;width: 200px;display: none;"></input>
                                <input class="inputText" type="text"  id="pId" value="" style="vertical-align:middle;margin-left: 5px;margin-right: 10px;width: 200px;display: none;"></input>
                                <input id ="catcombo" class="catComBo" catKey="Domap" valueField="categoryId" catValue="对象选择" value="对象选择" name="typeName" height="300" width="220"></input>        
                            </div>
                        </div>
                    </div>
            </td>
            <td>
            	<div class="panel-toolbar">
                        <div class="toolBar">
                            <div class="group">
                            <a class="link search" id="eventsave2" href="javascript:;" onclick="watchAllevent();" style=" margin-left: 15px;">查看所有事件</a> 
                        </div>
                        </div>
                    </div>  
            </td>
        </tr>
    </table>
    <div id="inputPanel">
        <fieldset class="base">
            <legend>场景事件</legend>
            <table>
                <tr>
                    <td>
                        <div class="panel-toolbar" >
                            <div class="toolBar" >
                                <div class="group">
                                元素名称：<input name="disable" class="inputText" type="text"  id="btnname" value="" style="margin-left: 5px;margin-right: 10px;width: 100px"></input>
                                </div>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="panel-toolbar" >
                            <div class="toolBar" >
                                <div class="group"> 
                                选择事件: <select name="disable"  id="selectevent"  onChange="getEvent(this);" style="box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);border-radius: 4px;margin-left: 5px;margin-right: 10px;width: 100px;height: 25px">
                                            <!--<optgroup label="事件选择"></option>-->
                                            <!--<option value="0" style="font-weight:bold">&nbsp选择事件</option> -->
                                            <option value ="onclick" selected="selected" >无</option>
                                            <option value ="onclick" selected="selected" >onclick</option>
                                            <option value ="onchange" selected="selected" >onChange</option>
                                            <option value ="onblur" selected="selected" >onBlur</option>
                                            <option value ="ondrag" selected="selected" >onDrag</option>
                                             <option value ="ondrag" selected="selected" >oninput</option>
                                             <option value ="ondrag" selected="selected" >onmousedown</option>
                                             <option value ="ondrag" selected="selected" >onmouseup</option>
                                             <option value ="ondrag" selected="selected" >onmousemove</option>
                                          </select>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
            <table style="margin-top:0px">
                <tr>
                     <td>
                          <input type="button" value="事件图" name="disable"  class="choosegraph" id="btnSearch" href="javascript:;" style="width:60px;"></input>
                     </td>
                     <td>
                         <input name="disable" class="inputText" type="text"  id="definitionid" value="" style="vertical-align:middle;margin-left: 0px;margin-right: 10px;width: 250px;display:none;"></input>
                    <input name="disable" class="inputText" type="text" disabled="disabled" id="definition" value="" style="margin-left: 10px;margin-right: 0px;width: 200px"></input>
                     </td>
                     <td><input type="button" id="clean" style="width:50px;margin-left: 5px;margin-right: 5px;" class="link search" onclick="cleans()" value="解绑"></input></td>
                     <td><a type="button" id="show" style="width:50px;" class="link search" target="_blank" >预览</a></td>
               </tr> 
            </table>
            <table>
            	<tr>
                    <td>
                     <div class="panel-toolbar" >
                            <div class="toolBar" >
                                <div class="group">
                                                                                   入参个数：<input name="disable" class="inputText" type="text" disabled="disabled" id="inputDom" value="0" style="margin-left: 5px;margin-right: 10px;width: 100px"></input>
                                </div>
                            </div>
                      </div>
                    </td>
                    <td>
                        <div class="panel-toolbar" >
                            <div class="toolBar" >
                                <div class="group">
                                        出参个数：<input name="disable" class="inputText" disabled="disabled" type="text" id="outputDom" value="0" style="margin-left: 5px;margin-right: 10px;width: 100px"></input>
                                </div>
                          </div>
                         </div>
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
    <div id="inputPanel">
        <fieldset class="base">
            <legend>元素对象</legend>        
            <table>
            <tr>
                            <td width="50%">
                                <div id="inputPanel">
                                    <fieldset  class="base">
                                        <legend>读元素</legend>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label style="width: 40px" for="texttype" > 文本类:</label>
                                                </td>
                                                <td>
                                                    <input type="text" id="texttype" class="type"  disabled="disabled"/>
                                                    <div class="container" id="textcontainer">
                                                        <div class ="shadow" id="textshadow">
                                                            <div class = "frame" id="textframe">
                                                                <div class="inframe" id="intextframe" style="overflow-y:auto;max-height:245px;height:auto;width: 300px;"></div>
                                                                    <div id="textfoot" class="foot"><a href="#" id="textsubmit" class="submit">确定</a></div>
                                                            </div>
                                                        </div>
                                                   </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label style="width: 40px" for="iframetype">IFRAME:</label>
                                                </td>
                                                <td>
                                                    <input type="text" id="iframetype" class="type" disabled="disabled"/>
                                                    <div class="container" id="iframecontainer">
                                                        <div class ="shadow" id="iframeshadow">
                                                            <div class = "frame" id="iframeframe">
                                                                <div class="inframe" id="iniframeframe" style="overflow-y:auto;max-height:245px;height:auto;width: 300px;"></div>
                                                                <div id="iframefoot" class="foot"><a href="#" id="iframesubmit" class="submit">确定</a></div>
                                                            </div>
                                                       </div>
                                                    </div>
                                                </td>
                                           </tr>
                                        </table>           
                                </fieldset>
                            </div>                      
                         </td>
                         </tr>
                         <tr>
                         <td width="50%">
                            <div id="inputPanel">
                                <fieldset  class="base">
                                    <legend>写元素</legend>
                                    <table>
                                        <tr>
                                            <td>
                                                <label for="outtexttype">文本类：</label>
                                            </td>
                                            <td>
                                                <input type="text" id="outtexttype" class="type" disabled="disabled"/>
                                                    <div class="container" id="outtextcontainer">
                                                        <div class ="shadow" id="outtextshadow">
                                                            <div class = "frame" id="outtextframe">
                                                                <div class="inframe" id="outintextframe" style="overflow-y:auto;max-height:245px;height:auto;width: 300px;"></div>
                                                                <div id="outtextfoot" class="foot"><a href="#" id="outtextsubmit" class="submit">确定</a></div>
                                                            </div>
                                                       </div>
                                                    </div>
                                           </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label for="outiframetype">IFRAME：</label>
                                            </td>
                                            <td>
                                                <input type="text" id="outiframetype" class="type"  disabled="disabled"/>
                                                    <div class="container" id="outiframecontainer">
                                                        <div class ="shadow" id="outiframeshadow">
                                                            <div class = "frame" id="outiframeframe">
                                                                <div class="inframe" id="outiniframeframe" style="overflow-y:auto;max-height:245px;height:auto;width: 300px;"></div>
                                                                <div id="outiframefoot" class="foot"><a href="#" id="outiframesubmit" class="submit">确定</a></div>
                                                            </div>
                                                       </div>
                                                    </div>
                                            </td>
                                       </tr>
                                    </table>           
                              </fieldset>
                        </div>                      
                      </td>
                      </tr>
                <tr>
                 <td>
                    <fieldset class="base">
                        <legend>数据</legend> 
                        <table>
                            <tr>
                                <td>
                                    <div class="panel-toolbar">
                                        <div class="toolBar">
                                            <div class="group">
                                                <a name="disable" class="link search" id="mybuttondialog" style="width:80" href="javascript:;">选择对话框</a>
                                                <input  class="inputText" type="text" disabled="disabled" value="" id="buttondialogname" style="margin-left: 5px;margin-right: 10px;width: 240px"></input>
                                                <input  class="inputText" type="text"  id="buttondialogid" value="" style="vertical-align:middle;margin-left: 5px;margin-right: 10px;width: 200px;display:none"></input>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <!--<tr>
                                <td>
                                    <div class="panel-toolbar">
                                        <div class="toolBar">
                                            <div class="group">
                                                <select id="formfield"></select>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>    
                                --><!--<tr>
                                <td>
                                    <div class="panel-toolbar">
                                        <div class="toolBar">
                                            <div class="group">
                                                <a name="disable" class="link search" id="mybuttonform" style="width:80" href="javascript:;">选择子表</a>
                                                <input class="inputText" type="text"  disabled="disabled" id="buttonformsubject" value="" style="vertical-align:middle;margin-left: 5px;margin-right: 10px;width: 240px"></input>
                                                <input class="inputText" type="text"  id="buttonformkey" value="" style="vertical-align:middle;margin-left: 5px;margin-right: 10px;width: 200px;display:none;"></input>
                                            </div>
                                        </div>
                                    </div>
                               </td>
                               </tr>
                               <tr>
                               <td>
                                    <div class="panel-toolbar">
                                        <div class="toolBar">
                                            <div class="group">
                                                <a name="disable" class="link search" id="mybuttondatatemplate" style="width:80" href="javascript:;">选择子数据模板</a>
                                                <input class="inputText" type="text"  disabled="disabled" id="buttondatatemplatesubject" value="" style="vertical-align:middle;margin-left: 5px;margin-right: 10px;width: 240px"></input>
                                                <input class="inputText" type="text"  id="buttondatatemplateid" value="" style="vertical-align:middle;margin-left: 5px;margin-right: 10px;width: 200px;display:none;"></input>
                                            </div>
                                        </div>
                                    </div>
                               </td>
                          </tr>
                        --></table>
                    </fieldset>
                 </td>
                </tr>
                </tr>
        </tr>   
        </table>        
        </fieldset>
    </div>
<script language="javascript"><!--
    $('#outtexttype,#texttype').bind('click', function() {
        var selectid = $(this).attr("id").replaceAll("type","frame").replaceAll("text","intext");
        var offset = $(this).offset(), textcontainer = $('#'+$(this).attr("id").replaceAll("type","container"));
        $(".container").hide(200);
        if(textflag){
            textcontainer.css({top:offset.top+Number($(this).css('height').replace('px', '')), left:offset.left}).slideToggle(400);
        }else{
            alert("该界面尚无此类元素！");
        }
    });
    $('#outiframetype,#iframetype').bind('focus', function() {
        var selectid = $(this).attr("id").replaceAll("type","frame").replaceAll("text","intext");
        var offset = $(this).offset(), textcontainer = $('#'+$(this).attr("id").replaceAll("type","container"));
        $(".container").hide(200);
        if(iframeflag){
            textcontainer.css({top:offset.top+Number($(this).css('height').replace('px', '')), left:offset.left}).slideToggle(400);
        }else{
            alert("该界面尚无此类元素！");
        }
    });
    $('.submit').bind('click', function(){
        var inorouten = $(this).attr("id")[0]=='o'?'out':'in';
        var targettext = $(this).attr("id").replace("submit","type");
        var targetframe = $(this).attr("id").replace("submit","frame");
        var targetcontainer = $(this).attr("id").replace("submit","container");
        var vals = '', length;
        $('#'+targetframe+' input[type=checkbox]:checked').each(function(){
            vals += ($(this).next().text() + ';');
            if(inorouten=="in"){
                re = '{"indomid":'+$(this).next().next().val()+',"invarid":'+$(this).next().next().next().val()+'}';
                readdom.push(JSON.parse(re));
                re="";
            }else if(inorouten=="out"){
                wr = '{"outdomid":'+$(this).next().next().val()+',"outvarid":'+$(this).next().next().next().val()+'}';
                writedom.push(JSON.parse(wr));
                wr="";
            }
        });
        if ((length = vals.length) > 0) vals = vals.substr(0, length -1);
        $('#'+targettext).attr("value",vals);
        $('#'+targetcontainer).slideToggle(500);
        var intextput=$("#texttype").val();
        var inframeput=$("#iframetype").val();
        var outtextput=$("#outtexttype").val();
        var outframeput=$("#outiframetype").val();
        var innum = 0,outnum=0;
        if(intextput){
        	innum += intextput.split(";").length;	
        }
        if(inframeput){
        	innum += inframeput.split(";").length;	
        }
        if(outtextput){
        	outnum += outtextput.split(";").length;	
        }
        if(outframeput){
        	outnum += outframeput.split(";").length;	
        }
        $("#inputDom").attr("value",innum);
        $("#outputDom").attr("value",outnum);
    });
    function saveByJson(){
    	var innum = 0;
    	var outnum = 0;
        var mydomId=-1;
        var finhtml = "";
        var finContent="";
        var myjson="";
        var target;
        var oldjson="";
        var intextput=$("#texttype").val();
        var inframeput=$("#iframetype").val();
        var outtextput=$("#outtexttype").val();
        var outframeput=$("#outiframetype").val();
        if(intextput){
        	innum += intextput.split(";").length;	
        }
        if(inframeput){
        	innum += inframeput.split(";").length;	
        }
        if(outtextput){
        	outnum += outtextput.split(";").length;	
        }
        if(outframeput){
        	outnum += outframeput.split(";").length;	
        }   
        var rd=JSON.stringify(readdom);
        var wd=JSON.stringify(writedom);
        var domname=$("#btnname").val()==""?"":$("#btnname").val();
        var graphId=$("#definitionid").val()==""?0:$("#definitionid").val();
        var graphsubject=$("#definition").val()==""?"":$("#definition").val();
        var dialogId=$("#buttondialogid").val()==""?0:$("#buttondialogid").val();
        var buttondialogname = $("#buttondialogname").val()==""?"":$("#buttondialogname").val();
        //var buttonformsubject = $("#buttonformsubject").val()==""?"":$("#buttonformsubject").val();
        //var formKey=$("#buttonformkey").val()==""?"":$("#buttonformkey").val();
       // var templateId=$("#buttondatatemplateid").val()==""?0:$("#buttondatatemplateid").val();
        //var templatesubject=$("#buttondatatemplatesubject").val()==""?"":$("#buttondatatemplatesubject").val();
        var domaction=$("#selectevent option:selected").text()==""?"":$("#selectevent option:selected").text();
        $.each(rtnData, function(idx, obj) {
            if(obj.parentId==$("#pId").val()&&obj.typeName==$("#catcombo").val()){
                target = $(editor.getContent()).find("\""+obj.domtag+"[name='"+obj.realname+"']\"");
                oldjson = target.attr("myjson");
                if(obj.domId==-1){
                    mydomId=<%=UniqueIdUtil.genId()%>;
                    obj.domId = mydomId;
                }else{
                    mydomId = $("#domId").val();
                }
                myjson='{"domId":'+mydomId+',"domname":"'+domname+
                		'","domaction":"'+domaction+'","graphId":'+graphId+
                		',"graphsubject":"'+graphsubject+'","outnum":'+outnum+
                		',"innum":'+innum+',"readdom":'+rd+',"writedom":'+wd+
                		',"intextput":"'+intextput+'","inframeput":"'+inframeput+
                		'","outtextput":"'+outtextput+'","outframeput":"'+outframeput+'","dialogId":'+dialogId+',"dialogname":"'+buttondialogname+'"}';
                $("#domId").val(mydomId);
                var tagname = target.get(0).tagName.toLowerCase();
                var outhtml = target.prop("outerHTML");
                if(oldjson){
                    oldjson = oldjson.replaceAll("\"","&quot;");
                     if(outhtml.indexOf("</"+tagname+">")==-1){
                        var endstr = " />";
                        outhtml = outhtml.replaceAll(">",endstr);
                     }
                     finhtml = outhtml.replace(oldjson,"");
                     finhtml = finhtml.replaceAll("myjson=\"\"","");
                     finhtml = finhtml.replaceAll("onclick='showmyjson()'","");
                     finhtml = finhtml.replaceAll("<"+tagname,"<"+tagname+" myjson='"+myjson+"' onclick='showmyjson("+myjson+");'");
                     finContent = editor.getContent().replace(outhtml,finhtml);
                     editor.setContent(finContent);
                }else{
                    if(outhtml.indexOf("</"+tagname+">")==-1){
                        var endstr = " />";
                        outhtml = outhtml.replaceAll(">",endstr);
                    }
                    finhtml = outhtml.replace("<"+tagname,"<"+tagname+" myjson='"+myjson+"' onclick='showmyjson("+myjson+")'"+" domId='"+mydomId+"' "); 
                    finContent = editor.getContent().replace(outhtml,finhtml);
                    editor.setContent(finContent);
                }
            }
        });
        location.reload();
    }
   // function searchdatatemplate(){
   //     CommonDialog("ywsjmb",function(data){
   //         $("#buttondatatemplatesubject").attr("value",data.subject);
   //         $("#buttondatatemplateid").attr("value",data.ID);       
   //     },"");
   // }
	function watchAllevent(){
        var mycontent = '<form >' + editor.getContent() + '</form>';
        var domlist = [];
        var doms = $(mycontent).find("*");
        $.each(doms, function(idx,obj) {
            if($(obj).attr("myjson")&&domlist.length==0){
                domlist.push($(obj).attr("myjson"));
            }else if($(obj).attr("myjson")&&domlist.length!=0){
                domlist.push("@"+$(obj).attr("myjson"));
            }    
        }); 
		var url="${ctx}/platform/bpm/bpmDefinition/watchAllevent.ht?myjsonlist="+domlist.toString()+"&num="+domlist.length;
        DialogUtil.open({
            title:"",
            url: url,
            height:700,
            width:1000,
            isResize: false,
            pwin:window,
            sucCall:function(){     
            }
        }); 
	}
    function getEvent(me){
        alert(me.value);
    };  
   // function searchform(){
   //     CommonDialog("bdxzq",function(data){
   //         $("#buttonformsubject").attr("value",data.SUBJECT);
   //         //var str = JSON.stringify(data);  
   //         $("#buttonformkey").value("value",data.FORMKEY);       
   //     },"");
   // }
    function searchdialog(){
        var paramValueString = "";
        CommonDialog("zdydhklb",function(data){
            $("#buttondialogname").attr("value",data.NAME);
            $("#buttondialogid").attr("value",data.ID);
        },"");
    };
    function getEventDomByTagName(tagname,selectid,typename,puts){
        var inorout = selectid[0]=='i'?'输入':'输出';
        var inorouten = selectid[0]=='i'?'in':'out';
        var i = 0;
        var domnum = 0;
        var htmlstr ="";
        var chinesetag=new Array();
        for(var k = 0;k<tagname.length;k++){
            switch(tagname[k]){
                case "input":
                    if(typename[k]==":text"){
                        chinesetag[k]="单行文本";
                    }
                    break;
                case "textarea":
                    chinesetag[k]="文本域";
                    break;
                case "iframe":
                    chinesetag[k]="IFRMAE";
                    break;                  
            }
        }
        for(var j = 0;j<tagname.length;j++){
            var doms = $(editor.getContent()).find(tagname[j]+typename[j]);
            var domname = "";
            domnum += doms.length;
            if(doms.length){
                for(var k = 0;i<domnum;i++,ii++,k++){
                    if($(doms[k]).attr("domid")){
                        htmlstr += "<fieldset class=\"mybase\"><legend>"+chinesetag[j]+"</legend>";
                        break;
                    }
                }
                if(htmlstr!=""){
                    for(var k = 0;i<domnum;i++,ii++,k++){
                        if($(doms[k]).attr("domid")){
                          if(puts.length){
                          	if(inorouten=="in"){
                           		if(puts.indexOf($(doms[k]).attr("domid").toString())!=-1){
                           			domname = $(doms[k]).attr("name");
                                	if(domname.indexOf(":")>0){
                                   		var temp = domname.split(":");
                                     	htmlstr += "<div class=\"mydiv\"><input class = \"doms\"  checked=\"true\" onclick=\"checkboxs1('selectinvar"+ii+"','eventtextin"+ii+"')\" type=\"checkbox\"  id=\"eventtextin"+ii+"\"/><label style=\"width:100px\" class =\"label\" onclick=\"checkboxs('selectinvar"+ii+"','eventtextin"+ii+"')\">"+temp[2]+"</label><input id=\"hiddenid"+ii+"\" type=\"text\" value=\""+$(doms[k]).attr("domid")+"\" style=\"display:none\"/><input class=\"invarid\" value=\"\" id=\"invarid"+ii+"\" style=\"display:none\"/><select disabled=\"disabled\" style=\"display:none;width:100px\" onchange=\"changevar("+ii+",'in',this)\" class=\"selectinvar\" id=\"selectinvar"+ii+"\" ><option class=\"varoption\" value =\"0\" >"+"输入变量选择</option></select></div>";
                                 	}else{
                                    	htmlstr += "<div class=\"mydiv\"><input class = \"doms\" checked=\"true\" onclick=\"checkboxs1('selectinvar"+ii+"','eventtextin"+ii+"')\" type=\"checkbox\"  id=\"eventtextin"+ii+"\"/><label style=\"width:100px\" class =\"label\" onclick=\"checkboxs('selectinvar"+ii+"','eventtextin"+ii+"')\" >"+domname+"</label><input id=\"hiddenid"+ii+"\" type=\"text\" value=\""+$(doms[k]).attr("domid")+"\" style=\"display:none\"/><input class=\"invarid\" value=\"\" id=\"invarid"+ii+"\"  style=\"display:none\"/><select disabled=\"disabled\" style=\"display:none;width:100px\" onchange=\"changevar("+ii+",'in',this)\" class=\"selectinvar\" id=\"selectinvar"+ii+"\" ><option class=\"varoption\" value =\"0\" >"+"输入变量选择</option></select></div>";
                                 	}
                                 }else{
                                 	domname = $(doms[k]).attr("name");
                                	if(domname.indexOf(":")>0){
                                   		var temp = domname.split(":");
                                     	htmlstr += "<div class=\"mydiv\"><input class = \"doms\" onclick=\"checkboxs1('selectinvar"+ii+"','eventtextin"+ii+"')\" type=\"checkbox\"  id=\"eventtextin"+ii+"\"/><label style=\"width:100px\" class =\"label\" onclick=\"checkboxs('selectinvar"+ii+"','eventtextin"+ii+"')\">"+temp[2]+"</label><input id=\"hiddenid"+ii+"\" type=\"text\" value=\""+$(doms[k]).attr("domid")+"\" style=\"display:none\"/><input class=\"invarid\" value=\"\" id=\"invarid"+ii+"\" style=\"display:none\"/><select disabled=\"disabled\" style=\"display:none;width:100px\" onchange=\"changevar("+ii+",'in',this)\" class=\"selectinvar\" id=\"selectinvar"+ii+"\" ><option class=\"varoption\" value =\"0\" >"+"输入变量选择</option></select></div>";
                                 	}else{
                                    	htmlstr += "<div class=\"mydiv\"><input class = \"doms\" onclick=\"checkboxs1('selectinvar"+ii+"','eventtextin"+ii+"')\" type=\"checkbox\"  id=\"eventtextin"+ii+"\"/><label style=\"width:100px\" class =\"label\" onclick=\"checkboxs('selectinvar"+ii+"','eventtextin"+ii+"')\" >"+domname+"</label><input id=\"hiddenid"+ii+"\" type=\"text\" value=\""+$(doms[k]).attr("domid")+"\" style=\"display:none\"/><input class=\"invarid\" value=\"\" id=\"invarid"+ii+"\"  style=\"display:none\"/><select disabled=\"disabled\" style=\"display:none;width:100px\" onchange=\"changevar("+ii+",'in',this)\" class=\"selectinvar\" id=\"selectinvar"+ii+"\" ><option class=\"varoption\" value =\"0\" >"+"输入变量选择</option></select></div>";
                                 	}
                                 }
                           }else if(inorouten=="out"){
                           		if(puts.indexOf($(doms[k]).attr("domid").toString())!=-1){
                           			 domname = $(doms[k]).attr("name");
                                        if(domname.indexOf(":")>0){
                                            var temp = domname.split(":");
                                            htmlstr += "<div class=\"mydiv\"><input class = \"doms\"  checked=\"true\" onclick=\"checkboxs1('selectoutvar"+ii+"','eventtextout"+ii+"')\" type=\"checkbox\"  id=\"eventtextout"+ii+"\"/><label style=\"width:100px\" class =\"label\" onclick=\"checkboxs('selectoutvar"+ii+"','eventtextout"+ii+"')\">"+temp[2]+"</label><input id=\"hiddenid"+ii+"\" type=\"text\" value=\""+$(doms[k]).attr("domid")+"\" style=\"display:none\"/><input class=\"outvarid\" value=\"\" id=\"outvarid"+ii+"\" style=\"display:none\"/><select disabled=\"disabled\" style=\"display:none;width:100px\" onchange=\"changevar("+ii+",'out',this)\" class=\"selectoutvar\" id=\"selectoutvar"+ii+"\" ><option class=\"varoption\" value =\"0\" >"+"输出变量选择</option></select></div>";
                                        }else{
                                            htmlstr += "<div class=\"mydiv\"><input class = \"doms\" checked=\"true\" onclick=\"checkboxs1('selectoutvar"+ii+"','eventtextout"+ii+"')\" type=\"checkbox\"  id=\"eventtextout"+ii+"\"/><label style=\"width:100px\" class =\"label\" onclick=\"checkboxs('selectoutvar"+ii+"','eventtextout"+ii+"')\" >"+domname+"</label><input id=\"hiddenid"+ii+"\" type=\"text\" value=\""+$(doms[k]).attr("domid")+"\" style=\"display:none\"/><input class=\"outvarid\" value=\"\" id=\"outvarid"+ii+"\"  style=\"display:none\"/><select disabled=\"disabled\" style=\"display:none;width:100px\" onchange=\"changevar("+ii+",'out',this)\" class=\"selectoutvar\" id=\"selectoutvar"+ii+"\" ><option class=\"varoption\" value =\"0\" >"+"输出变量选择</option></select></div>";
                                        }
                                 }else{
                                 	domname = $(doms[k]).attr("name");
                                        if(domname.indexOf(":")>0){
                                            var temp = domname.split(":");
                                            htmlstr += "<div class=\"mydiv\"><input class = \"doms\"  onclick=\"checkboxs1('selectoutvar"+ii+"','eventtextout"+ii+"')\" type=\"checkbox\"  id=\"eventtextout"+ii+"\"/><label style=\"width:100px\" class =\"label\" onclick=\"checkboxs('selectoutvar"+ii+"','eventtextout"+ii+"')\">"+temp[2]+"</label><input id=\"hiddenid"+ii+"\" type=\"text\" value=\""+$(doms[k]).attr("domid")+"\" style=\"display:none\"/><input class=\"outvarid\" value=\"\" id=\"outvarid"+ii+"\" style=\"display:none\"/><select disabled=\"disabled\" style=\"display:none;width:100px\" onchange=\"changevar("+ii+",'out',this)\" class=\"selectoutvar\" id=\"selectoutvar"+ii+"\" ><option class=\"varoption\" value =\"0\" >"+"输出变量选择</option></select></div>";
                                        }else{
                                            htmlstr += "<div class=\"mydiv\"><input class = \"doms\"  onclick=\"checkboxs1('selectoutvar"+ii+"','eventtextout"+ii+"')\" type=\"checkbox\"  id=\"eventtextout"+ii+"\"/><label style=\"width:100px\" class =\"label\" onclick=\"checkboxs('selectoutvar"+ii+"','eventtextout"+ii+"')\" >"+domname+"</label><input id=\"hiddenid"+ii+"\" type=\"text\" value=\""+$(doms[k]).attr("domid")+"\" style=\"display:none\"/><input class=\"outvarid\" value=\"\" id=\"outvarid"+ii+"\"  style=\"display:none\"/><select disabled=\"disabled\" style=\"display:none;width:100px\" onchange=\"changevar("+ii+",'out',this)\" class=\"selectoutvar\" id=\"selectoutvar"+ii+"\" ><option class=\"varoption\" value =\"0\" >"+"输出变量选择</option></select></div>";
                                        }
                                 }
                           }
                          }else{
                          		if(inorouten=="in"){
                                 	domname = $(doms[k]).attr("name");
                                	if(domname.indexOf(":")>0){
                                   		var temp = domname.split(":");
                                     	htmlstr += "<div class=\"mydiv\"><input class = \"doms\" onclick=\"checkboxs1('selectinvar"+ii+"','eventtextin"+ii+"')\" type=\"checkbox\"  id=\"eventtextin"+ii+"\"/><label style=\"width:100px\" class =\"label\" onclick=\"checkboxs('selectinvar"+ii+"','eventtextin"+ii+"')\">"+temp[2]+"</label><input id=\"hiddenid"+ii+"\" type=\"text\" value=\""+$(doms[k]).attr("domid")+"\" style=\"display:none\"/><input class=\"invarid\" value=\"\" id=\"invarid"+ii+"\" style=\"display:none\"/><select disabled=\"disabled\" style=\"display:none;width:100px\" onchange=\"changevar("+ii+",'in',this)\" class=\"selectinvar\" id=\"selectinvar"+ii+"\" ><option class=\"varoption\" value =\"0\" >"+"输入变量选择</option></select></div>";
                                 	}else{
                                    	htmlstr += "<div class=\"mydiv\"><input class = \"doms\" onclick=\"checkboxs1('selectinvar"+ii+"','eventtextin"+ii+"')\" type=\"checkbox\"  id=\"eventtextin"+ii+"\"/><label style=\"width:100px\" class =\"label\" onclick=\"checkboxs('selectinvar"+ii+"','eventtextin"+ii+"')\" >"+domname+"</label><input id=\"hiddenid"+ii+"\" type=\"text\" value=\""+$(doms[k]).attr("domid")+"\" style=\"display:none\"/><input class=\"invarid\" value=\"\" id=\"invarid"+ii+"\"  style=\"display:none\"/><select disabled=\"disabled\" style=\"display:none;width:100px\" onchange=\"changevar("+ii+",'in',this)\" class=\"selectinvar\" id=\"selectinvar"+ii+"\" ><option class=\"varoption\" value =\"0\" >"+"输入变量选择</option></select></div>";
                                 	}
                                 }else if(inorouten=="out"){
                                 			domname = $(doms[k]).attr("name");
                                        	if(domname.indexOf(":")>0){
                                           		 var temp = domname.split(":");
                                            	 htmlstr += "<div class=\"mydiv\"><input class = \"doms\"  onclick=\"checkboxs1('selectoutvar"+ii+"','eventtextout"+ii+"')\" type=\"checkbox\"  id=\"eventtextout"+ii+"\"/><label style=\"width:100px\" class =\"label\" onclick=\"checkboxs('selectoutvar"+ii+"','eventtextout"+ii+"')\">"+temp[2]+"</label><input id=\"hiddenid"+ii+"\" type=\"text\" value=\""+$(doms[k]).attr("domid")+"\" style=\"display:none\"/><input class=\"outvarid\" value=\"\" id=\"outvarid"+ii+"\" style=\"display:none\"/><select disabled=\"disabled\" style=\"display:none;width:100px\" onchange=\"changevar("+ii+",'out',this)\" class=\"selectoutvar\" id=\"selectoutvar"+ii+"\" ><option class=\"varoption\" value =\"0\" >"+"输出变量选择</option></select></div>";
                                        	}else{
                                            	 htmlstr += "<div class=\"mydiv\"><input class = \"doms\"  onclick=\"checkboxs1('selectoutvar"+ii+"','eventtextout"+ii+"')\" type=\"checkbox\"  id=\"eventtextout"+ii+"\"/><label style=\"width:100px\" class =\"label\" onclick=\"checkboxs('selectoutvar"+ii+"','eventtextout"+ii+"')\" >"+domname+"</label><input id=\"hiddenid"+ii+"\" type=\"text\" value=\""+$(doms[k]).attr("domid")+"\" style=\"display:none\"/><input class=\"outvarid\" value=\"\" id=\"outvarid"+ii+"\"  style=\"display:none\"/><select disabled=\"disabled\" style=\"display:none;width:100px\" onchange=\"changevar("+ii+",'out',this)\" class=\"selectoutvar\" id=\"selectoutvar"+ii+"\" ><option class=\"varoption\" value =\"0\" >"+"输出变量选择</option></select></div>";
                                        	}
                           		}
                          }
                        }else{
                            continue;
                        }
                    }
                    htmlstr+="</fieldset>"
                }
            }
        }
        $("#"+selectid).html(htmlstr);
        if(htmlstr!=""){
            return 1;
        }else{
            return 0;
        }
    }
    function changevar(index,inorouten,me){
		$("#"+inorouten+"varid"+index).attr("value",me.value);
       // $("#btnname").val(me.value);
    }
    function checkboxs1(selectid,checkid){
        if(!$("#"+checkid).attr("checked")){
            $("#"+selectid).attr("disabled","disabled");            
        }else{
            $("#"+selectid).removeAttr("disabled");
        }
    }
    function checkboxs(selectid,checkid){
        if($("#"+checkid).attr("checked")){
            $("#"+selectid).attr("disabled","disabled");
            $("#"+checkid).removeAttr("checked");
        }else{
            $("#"+checkid).attr("checked","true");
            $("#"+selectid).removeAttr("disabled");
        }
    }
    function searchdefinition(){
        var varjson;
        var inputvar=[];
        var outputvar=[];
        var url="${ctx}/platform/bpm/bpmDefinition/gettransaction.ht?formDefId="+formDefId+"&formsubject="+formsubject;
        DialogUtil.open({
            title:"",
            url: url,
            height:500,
            width:800,
            isResize: false,
            pwin:window,
            sucCall:function(defId,subject,defKey,varlist){ 
            	$("#show").attr("href",__ctx+"/platform/bpm/bpmDefinition/design.ht?defId="+defId+"&flagflex=3");  
                $("#definition").attr("value",subject);
                $("#definitionid").attr("value",defId);
                $(".type").attr("value","");
                $(".type").attr("disabled","disabled")
                varjson = $.parseJSON(varlist);
                 $.each(varjson, function(idx,obj) {
                        if(obj.vartype=="输入"){
                            inputvar.push(obj);
                        }else{
                            outputvar.push(obj);
                        }
                  });
                 if(inputvar.length){
                 	tags.length=0;
	             	typenames.length=0;
                 	tags.push("input");
	             	tags.push("textarea");
	             	typenames.push(":text");
	             	typenames.push("");
	             	textflag = getEventDomByTagName(tags,"intextframe",typenames,[]);
	             	tags.length=0;
	             	typenames.length=0;
	             	tags.push("iframe");
	             	typenames.push("");
	             	iframeflag = getEventDomByTagName(tags,"iniframeframe",typenames,[]);
	             	tags.length=0;
	             	typenames.length=0;
                 	$('.selectinvar').show();
                 	$.each(inputvar, function(idx,obj) {
                        $(".selectinvar").append("<option class=\"varoption\" value='"+obj.varId+"'>"+obj.varname+"</option>");
                 	});
          	        $("#texttype").removeAttr("disabled");
          	        $("#iframetype").removeAttr("disabled");
                 }else{
                 	$("#texttype").attr("value","所选事件图未设置流程变量！)");
          	        $("#iframetype").attr("value","所选事件图未设置流程变量！)");
                 }
                 if(outputvar.length){
                 	tags.length=0;
	             	typenames.length=0;
                 	tags.push("input");
	             	tags.push("textarea");
	             	typenames.push(":text");
	             	typenames.push("");
	             	textflag = getEventDomByTagName(tags,"outintextframe",typenames,[]);
	             	tags.length=0;
	             	typenames.length=0;
	             	tags.push("iframe");
	             	typenames.push("");
	             	iframeflag = getEventDomByTagName(tags,"outiniframeframe",typenames,[]);
	             	tags.length=0;
	             	typenames.length=0;
                 	$('.selectoutvar').show();
                 	$.each(outputvar, function(idx,obj) {
                       $(".selectoutvar").append("<option class=\"varoption\" value='"+obj.varId+"'>"+obj.varname+"</option>");
                 	});
               	   $("#outtexttype").removeAttr("disabled");
               	   $("#outiframetype").removeAttr("disabled");
                 }else{
                 	$("#outtexttype").attr("value","所选事件图未设置流程变量！");
          	        $("#outiframetype").attr("value","所选事件图未设置流程变量！");
                 }  
            }
        }); 
    }
    function cleans(){
    	$("#show").removeAttr("href");
    	$("#definition").attr("value","");
    	$("#definitionid").attr("value","");
    	$(".type").attr("value","");
    	
    	tags.length=0;
	    typenames.length=0;
        tags.push("input");
	    tags.push("textarea");
	    typenames.push(":text");
	    typenames.push("");
	    textflag = getEventDomByTagName(tags,"intextframe",typenames,[]);
	    getEventDomByTagName(tags,"outintextframe",typenames,[]);
	    tags.length=0;
	    typenames.length=0;
	    tags.push("iframe");
	    typenames.push("");
	    iframeflag = getEventDomByTagName(tags,"iniframeframe",typenames,[]);
	    getEventDomByTagName(tags,"outiniframeframe",typenames,[]);
	    tags.length=0;
	    typenames.length=0;
	    $(".type").attr("disabled","disabled");
    }
--></script>
</form>
</body>
</html>