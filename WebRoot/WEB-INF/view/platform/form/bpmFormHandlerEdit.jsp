
<%--
	time:2011-11-16 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@include file="/commons/include/get.jsp" %>
<html>
<head>
<title>表单预览</title>
<%@include file="/commons/include/customForm.jsp" %>
<script type="text/javascript">
$(function() {
	function showRequest(formData, jqForm, options) { 
		return true;
	} 
	
	var win;
	$("a.save").click(function() {
		var rtn=CustomForm.validate();
		if(rtn){
			var data=CustomForm.getData();
			//设置表单数据
			$("#txtJsonData").val(data);
			if(!win){
				var obj=$("#divJsonData");
				win= $.ligerDialog.open({ target:obj , height: 300,width:500, modal :true}); 
			}
			win.show();
		}
	});
});
function showmyjson(myjson){
	var jsonstr = JSON.stringify(myjson);
	var url="${ctx}/platform/bpm/bpmDefinition/showmyjson.ht?jsonstr="+jsonstr;
        DialogUtil.open({
            title:"",
            url: url,
            height:700,
            width:780,
            isResize: false,
            pwin:window,
            sucCall:function(){     
            }
        }); 
}
function closeWin(){
	try{
	frameElement.dialog.close();
	}catch(e){
		window.close();
	}
};
function sheriframe(iname,cla){
     var targetif = $("iframe");
 	 $("body").delegate("."+cla, "click", function(){
		var obj=$(this);
		var dataTemplateJson=$("."+cla).attr("datatemplate");
		alert(dataTemplateJson);
		var json=eval("("+dataTemplateJson+")" );
		var name=json.name;
		var paramsValueString = "" ;
		var queryArr = json.query;
		var isMain,preSelector,isReturn=false ;
		if(!queryArr==false && queryArr.length>0){
			for(var i=0;i<queryArr.length;i++){
				isMain = queryArr[i].isMain ;
				if(isMain=="true"){
					preSelector = ".formTable" ;
				}else{
					preSelector = "div[type='subtable']" ;
				}
				//解析获取对应的数据
				$(preSelector+" :hidden[name$=':"+queryArr[i].id+"ID']").each(function(){
					if($(this).val()!=""){
						paramsValueString += queryArr[i].name + "=" + $(this).val() +"&" ;
						return false ;
					}
				});
				if(paramsValueString.indexOf(queryArr[i].name)<0){
					$(preSelector+" [name$=':"+queryArr[i].id+"']").each(function(){
						var self = $(this) ;
						if(self.val()!=""){
							paramsValueString += "&" + queryArr[i].name + "=" + $(this).val();
							return false ;
						}else{
							var selfClass = self.attr("class") ;
							if(!selfClass==false){
								if(selfClass.indexOf("validError")>=0){
									isReturn = true ;
									$.ligerDialog.warn('请填写好--'+self.attr("lablename"),'提示');
									return false ;
								}
							}
						}
					});
				}
				if(isReturn) return false ;
			}
		}
		var url=__ctx +"/platform/form/bpmDataTemplate/preview.ht?__displayId__="+name+paramsValueString;
		alert(name+':'+paramsValueString);
		for (var i = 0;i<targetif.length;i++){
 			if($(targetif[i]).attr("name") == iname){
 				$(targetif[i]).attr("src",url);
 			}
 	 	} 
 	 	return true;
	});
};
</script>
</head>
<body>
<div class="hide-panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">表单预览</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="dataFormSave" href="javascript:;"><span></span>查看数据</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link close" href="javascript:closeWin()"><span></span>关闭</a>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div id="divJsonData" style="display: none;">
		<textarea id="txtJsonData" rows="10" cols="80" style="height: 225px;width:480px"></textarea>
	</div>
	<form >
		
		<div type="custform">
			<div class="panel-detail">
				${bpmFormDef.html}
			</div>
		</div>
		
	</form>
	
	
	
</body>
</html>

