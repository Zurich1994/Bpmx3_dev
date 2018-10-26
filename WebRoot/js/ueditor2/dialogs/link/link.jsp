<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" src="../internal.js"></script>
    <style type="text/css">
        *{margin:0;padding:0;color: #838383;}
        table{font-size: 12px;margin: 10px;line-height: 30px}
        .txt{width:300px;height:21px;line-height:21px;border:1px solid #d7d7d7;}
    </style>
    <script type="text/javascript">
	    function selectFile(){
			FlexUploadDialog({isSingle:true,callback:picCallBack});
		}
		function FlexUploadDialog(conf) {
			if(!conf) conf={};
			var url= "${ctx}/platform/system/sysFile/dialog.ht?isSingle=1";
			var winArgs="dialogWidth:700px;dialogHeight:500px;help:0;status:0;scroll:0;center:1";
			/* var rtn=window.showModalDialog(url,"",winArgs);
			if(rtn!=undefined){
				if(conf.callback){
					var fileIds=rtn.fileIds;
					var fileNames=rtn.fileNames;
					var filePaths=rtn.filePaths;
					var extPath=rtn.extPath;
					conf.callback.call(this,fileIds,fileNames,filePaths,extPath);
				}
			} */
			var that =this;
			DialogUtil.open({
				height:conf.dialogHeight,
				width: conf.dialogWidth,
				title : '文件上传',
				url: url, 
				isResize: true,
				//自定义参数
				sucCall:function(rtn){
					if(conf.callback){
						var fileIds=rtn.fileIds;
						var fileNames=rtn.fileNames;
						var filePaths=rtn.filePaths;
						var extPath=rtn.extPath;
						conf.callback.call(that,fileIds,fileNames,filePaths,extPath);
					}
				}
			});
			
		}
		function picCallBack(fileIds,fileNames,filePaths){
			if(filePaths=="") return;
			$G("href").value="${ctx}/platform/system/sysFile/file_" + fileIds +".ht";
		};
    </script>
</head>
<body>

	<table>
        <tr>
            <td><label for="text"><var id="lang_input_text"></var></label></td>
            <td><input class="txt" id="text" type="text" disabled="true"/></td>
        </tr>
        <tr>
            <td><label for="href"> <var id="lang_input_url"></var></label></td>
            <td>
            	<input class="txt" id="href" type="text" />
           		<a href="javascript:;" onclick="selectFile();"><!--<var id="lang_choose_att"></var></a>-->
           	</td>
        </tr>
        <tr>
            <td><label for="title"> <var id="lang_input_title"></var></label></td>
            <td><input class="txt" id="title" type="text"/></td>
        </tr>
        <tr>
             <td colspan="2">
                 <label for="target"><var id="lang_input_target"></var></label>
                 <input id="target" type="checkbox"/>
             </td>
        </tr>
        <tr>
            <td colspan="2" id="msg"></td>
        </tr>
    </table>
<script type="text/javascript">
    var range = editor.selection.getRange(),
        link = range.collapsed ? editor.queryCommandValue( "link" ) : editor.selection.getStart(),
        url,
        text = $G('text'),
        rangeLink = domUtils.findParentByTagName(range.getCommonAncestor(),'a',true),
        orgText;
    link = domUtils.findParentByTagName( link, "a", true );
    if(link){
        url = link.getAttribute( 'data_ue_src' ) || link.getAttribute( 'href', 2 );

        if(rangeLink === link && !link.getElementsByTagName('img').length){
            text.removeAttribute('disabled');
            orgText = text.value = link[browser.ie ? 'innerText':'textContent'];
        }else{
            text.setAttribute('disabled','true');
            text.value = lang.validLink;
        }

    }else{
        if(range.collapsed){
            text.removeAttribute('disabled');
            text.value = '';
        }else{
            text.setAttribute('disabled','true');
            text.value = lang.validLink;
        }

    }
    $G("title").value = url ? link.title : "";
    $G("href").value = url ? url: '';
    $G("target").checked = url && link.target == "_blank" ? true :  false;
    $focus($G("href"));

    function handleDialogOk(){
        var href =$G('href').value.replace(/^\s+|\s+$/g, '');
        if(href){
            if(!hrefStartWith(href,["http","/","ftp://"])) {
                //href  = "http://" + href;
                href  =parent.UEDITOR_CONFIG.filePath + href;
            }
            var obj = {
            	'name' : "m:a:"+text.value,
                'href' : href,
                'target' : $G("target").checked ? "_blank" : '_self',
                'title' : $G("title").value.replace(/^\s+|\s+$/g, ''),
                'data_ue_src':href
            };
            //修改链接内容的情况太特殊了，所以先做到这里了
            //todo:情况多的时候，做到command里
            if(orgText && text.value != orgText){

                link.innerHTML = text.value;
                range.selectNode(link).select()
            }
            if(range.collapsed){
                obj.textValue = text.value;
            }
            editor.execCommand('link',obj );
            dialog.close();
        }
    }
    dialog.onok = handleDialogOk;
    $G('href').onkeydown = $G('title').onkeydown = function(evt){
        evt = evt || window.event;
        if (evt.keyCode == 13) {
            handleDialogOk();
            return false;
        }
    };
    $G('href').onblur = function(){
        if(!hrefStartWith(this.value,["http","/","ftp://"])){
        	$G("msg").innerHTML = "<span style='color: red'>"+lang.httpPrompt+"</span>";
        }else{
            $G("msg").innerHTML = "";
        }
    };

    function hrefStartWith(href,arr){
        href = href.replace(/^\s+|\s+$/g, '');
        for(var i=0,ai;ai=arr[i++];){
            if(href.indexOf(ai)==0){
                return true;
            }
        }
        return false;
    }


</script>
</body>
</html>
