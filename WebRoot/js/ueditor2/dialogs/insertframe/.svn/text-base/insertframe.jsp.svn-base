<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <script type="text/javascript" src="../internal.js"></script>
    <%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<link rel="stylesheet" type="text/css" href="${ctx}/styles/default/css/form.css">
    <style type="text/css">
    .panel-toolbar {
    height: 20px;
    margin-top: 2px;
    padding: 8px 5px 5px;
    background: #FFFFFF;
    border-bottom: solid 0px #cacaca;
    border-top: solid 0px #cacaca;
    width:400px
}
        .warp {width: 320px;height: 153px;margin-left:5px;padding: 20px 0 0 15px;position: relative;}
        #url {width: 290px; margin-bottom: 2px; margin-left: -6px; margin-left: -2px\9;*margin-left:0;_margin-left:0; }
        .format span{display: inline-block; width: 58px;text-align: center; zoom:1;}
        table td{padding:5px 0;}
        #align{width: 65px;height: 23px;line-height: 22px;}
    </style>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
</head>
<body>
<div class="warp">
<table>
                                <tr>
                                <td>
                                    <div class="panel-toolbar">
                                        <div class="toolBar">
                                            <div class="group">
                                                <a name="disable" class="link search" id="mybuttonform" style="width:80px" href="javascript:;">选择子表</a>
                                                <input class="inputText" type="text"  id="buttonformsubject" value="" style="vertical-align:middle;margin-left: 5px;margin-right: 10px;width: 240px"></input>
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
                                                <a name="disable" class="link search" id="mybuttondatatemplate" style="width:80px" href="javascript:;">选择子数据表格</a>
                                                <input class="inputText" type="text"  id="buttondatatemplatesubject" value="" style="vertical-align:middle;margin-left: 5px;margin-right: 10px;width: 240px"></input>
                                                <input class="inputText" type="text"  id="buttondatatemplateid" value="" style="vertical-align:middle;margin-left: 5px;margin-right: 10px;width: 200px;display:none;"></input>
                                            </div>
                                        </div>
                                    </div>
                               </td>
                          </tr>
                        </table>
        <table width="400px" cellpadding="0" cellspacing="0">
            <tr>
                <td colspan="2" class="format">
                    <span>地址：</span>
                    <input style="width:300px" id="url" type="text" value=""/>
                </td>
            </tr>
     		<tr>
                <td colspan="2" class="format"><span>名称：</span><input style="width:300px" type="text" id="iframename"/></td>
            </tr>
            <tr>
                <td colspan="1" class="format"><span>宽度：</span><input style="width:50px" type="text" id="width"/></td>
                <td colspan="1" class="format"><span>高度：</span><input style="width:50px" type="text" id="height"/></td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;<span>允许滚动条：</span><input type="checkbox" id="scroll"/> </td>
                <td>&nbsp;&nbsp;<span>显示框架边框：</span><input type="checkbox" id="frameborder"/> </td>
            </tr>

            <tr>
                <td colspan="2">&nbsp;&nbsp;<span>对齐方式：</span>
                    <select id="align">
                        <option value=""></option>
                        <option value="left"></option>
                        <option value="right"></option>
                    </select>
                </td>
            </tr>
        </table>
</div>
<script type="text/javascript">
    var iframe = editor._iframe;
    if(iframe){
   		$G("name").value = iframe.getAttribute("iframename");
        $G("url").value = iframe.getAttribute("src")||"";
        $G("width").value = iframe.getAttribute("width")||iframe.style.width.replace("px","")||"";
        $G("height").value = iframe.getAttribute("height") || iframe.style.height.replace("px","") ||"";
        $G("scroll").checked = (iframe.getAttribute("scrolling") == "yes") ? true : false;
        $G("frameborder").checked = (iframe.getAttribute("frameborder") == "1") ? true : false;
        $G("align").value = iframe.align ? iframe.align : "";
    }
   // $("#mybuttondialog").click(searchdialog);
		                    $("#mybuttonform").click(searchform);
		                    $("#mybuttondatatemplate").click(searchdatatemplate);
    function searchdatatemplate(){
        CommonDialog("ywsjmb",function(data){
            $("#buttondatatemplatesubject").attr("value",data.subject);
            $("#buttondatatemplateid").attr("value",data.ID);       
			$("#url").attr("value","http://192.168.3.170:8080"+"${ctx}/platform/form/bpmDataTemplate/dataListbyKey_"+ data.ID+".ht");
        },"");
    }
     function searchform(){
        CommonDialog("bdxzq",function(data){
            $("#buttonformsubject").attr("value",data.SUBJECT);
            //var str = JSON.stringify(data);  
            $("#buttonformkey").attr("value",data.FORMKEY);
           	$("#url").attr("value","http://192.168.3.170:8080"+"${ctx}/platform/form/bpmFormHandler/edit.ht?formDefId="+data.FORMDEFID);
        },"");
    }
    //function searchdialog(){
    //    var paramValueString = "";
     //   CommonDialog("zdydhklb",function(data){
     //       $("#buttondialogname").attr("value",data.NAME);
     //       $("#buttondialogid").attr("value",data.ID);
      //  },"");
    //};
    function queding(){
        var  	iframename = $G("iframename").value,  
       			url = $G("url").value.replace(/^\s*|\s*$/ig,""),
                width = $G("width").value,
                height = $G("height").value,
                scroll = $G("scroll"),
                frameborder = $G("frameborder"),
                float = $G("align").value,
                newIframe = editor.document.createElement("iframe"),
                div;
        if(!url){
            alert(lang.enterAddress);
            return false;
        }
        newIframe.setAttribute("name",iframename);
        newIframe.setAttribute("src",/http:\/\/|https:\/\//ig.test(url) ? url : "http://"+url);
        /^[1-9]+[.]?\d*$/g.test( width ) ? newIframe.setAttribute("width",width) : "";
        /^[1-9]+[.]?\d*$/g.test( height ) ? newIframe.setAttribute("height",height) : "";
        scroll.checked ?  newIframe.setAttribute("scrolling","yes") : newIframe.setAttribute("scrolling","no");
        newIframe.setAttribute("class","normaliframe");
        frameborder.checked ?  newIframe.setAttribute("frameborder","1",0) : newIframe.setAttribute("frameborder","0",0);
        float ? newIframe.setAttribute("align",float) :  newIframe.setAttribute("align","");
        if(iframe){
            iframe.parentNode.insertBefore(newIframe,iframe);
            domUtils.remove(iframe);
        }else{
            div = editor.document.createElement("div");
            div.appendChild(newIframe);
            editor.execCommand("inserthtml",div.innerHTML);
        }
        editor._iframe = null;
        dialog.close();
    }
    dialog.onok = queding;
    $G("url").onkeydown = function(evt){
        evt = evt || event;
        if(evt.keyCode == 13){
            queding();
        }
    };
    $focus($G( "url" ));
</script>
</body>
</html>