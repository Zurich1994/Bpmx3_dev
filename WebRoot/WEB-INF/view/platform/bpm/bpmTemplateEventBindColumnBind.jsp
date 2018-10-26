<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <%@include file="/commons/include/form.jsp" %>
        <link rel="stylesheet" type="text/css" href="../input.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/styles/default/css/form.css">
        <script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js">
        </script>
        <script type="text/javascript" src="${ctx}/js/hotent/ajaxgrid.js">
        </script>
        <script type="text/javascript" src="${ctx}/js/hotent/platform/system/EventDialog.js">
        </script>
        <script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js">
        </script>
        <script type="text/javascript" src="${ctx}/js/hotent/formdata.js">
        </script>
        <script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
        <script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
        <script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js">
        </script>
        
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
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title></title>
<script type="text/javascript">
   var defId,subject,defKey;  
   var dialog = frameElement.dialog;
   var formDesc = "";
   var graphsubject="";
   var graphId="";
   var event="";
   var cannum = "";
   var bindtype="";
   var bindvar="";
   var inputbind=[];
   var inputbindstr="";
   var bindhtml = "";
   $(function(){
   		if("${formDesc}"!="none"){
   		    formDesc = "${formDesc}";
   			$("#formDesc").val(formDesc);
   		}
   		if("${cannum}"!="none"&&"${cannum}"!="undefined"){
   			cannum = "${cannum}";
   			$("#inputnum").attr("value",cannum);
   		}
   		if("${event}"!="none"&&"${event}"!="undefined"){
   		    event = "${event}";
   			$("#selectevent option").each(function(){
   				if($(this).val()==event){
   					$(this).attr("selected","selected");
   				}
   			});
   		}
   		if("${graphsubject}"!="none"&&"${graphsubject}"!="undefined"){
   			graphId = "${graphId}";
   			$("#transactionId").attr("value",graphId);
   			fieldAnalysis2();
   			inputbindstr = ${inputbind};
   			$.each(JSON.parse(inputbindstr.replaceAll("'","\"")), function(idx,obj) {
   				if(obj.varbindtype=="固定值"){
   					$("#fixField_"+idx).show();
   					$("#methodSelect_"+idx+" option[value='固定值']").attr("selected","selected");
   					$("#fixField_"+idx).attr("value",obj.varbindval);
   				}else if(obj.varbindtype=="字段"){
   					$("#selectField_"+idx).show();
   					$("#methodSelect_"+idx+" option[value='字段']").attr("selected","selected");
   					$("#selectField_"+idx+" option[value='"+obj.varbindval+"']").attr("selected","selected");
   				}
   			});
   			graphsubject="${graphsubject}";
   			$("#definition").attr("value",graphsubject);
   		}
   });
   function bindSave(){
        event = $("#selectevent option:checked").val();
   		dialog.get("sucCall")(formDesc,$("#definition").val(),$("#transactionId").val(),event,$("#inputnum").val(),JSON.stringify(inputbind));
   		dialog.close();
   }
   function reset(){
   	 $("#definition").val('');
   	 $("#transactionId").val('');
   }
   
   function newevent(){
      	    var url = "${ctx}/platform/bpm/bpmDefinition/design.ht?typeName=transactionChart&status=-1&designChart=true";
			url = url.getNewUrl();
			$.openFullWindow(url);	 
   };
   
   function bindvalue(me,index){
   		inputbind[index].varbindval = $(me).val();
   }
   function fieldbindvar(me,index){
       var x="";
       x = $(me).val();
   	   inputbind[index].varbindval = x;
   }
    function addFlow() {
            BpmDefinitionDialog({
              isSingle: true,
              showAll: 1,
              returnDefKey: true,
              validStatus: 2,
              callback: function(defIds, subjects, defKeys) {
               			//alert("defIds:"+defIds+",subjects:"+subjects+",defKeys:"+defKeys);
               			defId = defIds;
               			subject = subjects;
               			defKey = defKeys;
               			var eventurl = "${ctx}/platform/bpm/bpmDefinition/design.ht?defId="+defIds+"&flagflex=3";
               			$("#definition").val(subject);
               			$("#transactionId").val(defId);
               			 
              }
            });
   };
   
   
         	 //事件图解析
		 function fieldAnalysis(){
      	 	var defId = $("#transactionId").val();
      	 	var varlist;
      	 	 var varjson;
        	var inputvar=[];
        	var outputvar=[];
        	var varbindjson;
      	 	var url = "${ctx}/platform/bpm/bpmDefinition/getvarlist.ht?defId="+defId;
      	 	$.ajax({
			    type:"post",
			    async:false,
			    url:url,
			    success:function(result){
					varlist = result;
					varjson = $.parseJSON(varlist);
                 $.each(varjson, function(idx,obj) {
                        if(obj.vartype=="输入"){
                            inputvar.push(obj);
                        }else{
                            outputvar.push(obj);
                        }
                  });
                  $("#inputnum").val(inputvar.length);
                  if(inputvar.length){
                  	$("#eventBindPanel").html("");
                  	$("#eventBindPanel").append("<fieldset class=\"base\"><legend>参数绑定</legend><table id=\"bindTable\">"+
                  		"</table></fieldset>"
                  	);
                  	$.each(inputvar, function(idx,obj) {
                  		varbindjson = '{"varname":"'+obj.varname+'","varId":"'+obj.varId+'","varbindtype":"固定值","varbindval":""}';
                  		inputbind.push($.parseJSON(varbindjson));
                        $("#bindTable").append(
                        "<tr><div class=\"panel-toolbar\" ><div class=\"toolBar\" ><div class=\"group\">"+
                        "<td><input type=\"radio\" checkIdx=\""+idx+"\" name=\"selectRadio\" onclick=\"radioSelect()\" id=\""+idx+"\"/></td>"+
                        "<td>&nbsp;<label>"+obj.varname+"</label></td>"+
                        "<td><input type=\"hidden\" value=\""+idx+"\"/>"+
                        "&nbsp;<select  class=\"selector\" name=\"methodSelector\" disabled=\"disabled\" id=\"methodSelect_"+idx+"\" onchange=\"bindMethod()\"><option value =\"值来源\" selected=\"selected\" >值来源</option>"+
        				"<option value =\"固定值\">固定值</option>"+
        				"<option value =\"字段\">字段</option></select></td>"+
        				"<td>&nbsp;&nbsp;<input type=\"text\" class=\"fixvar\" id=\"fixField_"+idx+"\" onblur=\"bindvalue(this,"+idx+")\" value=\"\"/>"+
        				"<select  onchange=\"fieldbindvar(this,"+idx+")\"  class=\"selectvar\" id=\"selectField_"+idx+"\"><option value =\"字段值\"  selected=\"selected\" >字段值</option></select></td>"+
                        "</div></div></div></tr>"
                        );
                 	});
                  	
                  }
				}
			});
      	 	$(".fixvar").hide();
   			$(".selectvar").hide();
   			getTemplateField();
      	 };
      	 	 //事件图解析
		 function fieldAnalysis2(){
      	 	var defId = $("#transactionId").val();
      	 	var varlist;
      	 	 var varjson;
        	var inputvar=[];
        	var outputvar=[];
        	var varbindjson;
      	 	var url = "${ctx}/platform/bpm/bpmDefinition/getvarlist2.ht?defId="+defId;
      	 	$.ajax({
			    type:"post",
			    async:false,
			    url:url,
			    success:function(result){
					varlist = result;
					varjson = $.parseJSON(varlist);
                 $.each(varjson, function(idx,obj) {
                        if(obj.vartype=="输入"){
                            inputvar.push(obj);
                        }else{
                            outputvar.push(obj);
                        }
                  });
                  $("#inputnum").val(inputvar.length);
                  		if(inputvar.length){
                  			$("#eventBindPanel").html("");
                  			$("#eventBindPanel").append("<fieldset class='base'><legend>参数绑定</legend><table id='bindTable'>"+
                  				"</table></fieldset>"
                  			);
                  		$.each(inputvar, function(idx,obj) {
                  			varbindjson = '{"varname":"'+obj.varname+'","varId":"'+obj.varId+'","varbindtype":"固定值","varbindval":""}';
                  			inputbind.push($.parseJSON(varbindjson));
                        	$("#bindTable").append(
                        	"<tr><div class='panel-toolbar' ><div class='toolBar' ><div class='group'>"+
                        	"<td><input type='radio' checkIdx='"+idx+"' name='selectRadio' onclick='radioSelect()' id='"+idx+"'/></td>"+
                        	"<td>&nbsp;<label>"+obj.varname+"</label></td>"+
                        	"<td><input type='hidden' value='"+idx+"'/>"+
                        	"&nbsp;<select  class='selector' name='methodSelector' disabled='disabled' id='methodSelect_"+idx+"' onchange='bindMethod()'><option value ='值来源' selected='selected' >值来源</option>"+
        					"<option value ='固定值'>固定值</option>"+
        					"<option value ='字段'>字段</option></select></td>"+
        					"<td>&nbsp;&nbsp;<input type='text' class='fixvar' id='fixField_"+idx+"' onblur='bindvalue(this,"+idx+")' value=''/>"+
        					"<select  onchange='fieldbindvar(this,"+idx+")'  class='selectvar' id='selectField_"+idx+"'><option value ='字段值'  selected='selected' >字段值</option></select></td>"+
                        	"</div></div></div></tr>"
                        	);
                 		});
                  	}
				}
			});
      	 	$(".fixvar").hide();
   			$(".selectvar").hide();
   			getTemplateField();
      	 };
 
 
     	function bindMethod(){
      	 	//var selectVal = $("#methodSelect_0").val();
      	 	var checkboxId = $('input[name=selectRadio]:checked').attr('id');
      	 	var methodSelectId = "methodSelect_"+checkboxId;
      	 	var checkValue = $("#"+methodSelectId).val();
      	 	if(checkValue == '固定值'){
      	 	    inputbind[checkboxId].varbindtype="固定值";
      	 		$("#selectField_"+checkboxId).hide();
      	 		$("#fixField_"+checkboxId).show();
      	 		
      	 	}else if(checkValue =='字段'){
      	 		inputbind[checkboxId].varbindtype="字段";
      	 		$("#fixField_"+checkboxId).hide();
      	 		$("#selectField_"+checkboxId).show();
      	 		//getTemplateField();
      	 	}
      	 	$("#"+methodSelectId+" option[value='值来源']").remove();
      	 	
      	 }
      	 
      	 	function radioSelect(){
            var preRadioVal = $("#preRadio").val();
			if(preRadioVal != ''){
				$("#methodSelect_"+preRadioVal).attr("disabled","disabled");
				$("#fixField_"+preRadioVal).attr("disabled","disabled");
				$("#selectField_"+preRadioVal).attr("disabled","disabled");
				
			}
			
			var checkboxId = $('input[name=selectRadio]:checked').attr('id');
			$("#preRadio").val(checkboxId);		
			
			
			//alert($("#methodSelect_"+checkboxId).attr("disabled"));
			
			
			//alert("radioCount:"+radioCount);
			/**
			for(var i=0;i<radioCount;i++){
				var isDisabled = $("#methodSelect_"+i).attr("disabled");
				alert("isDisabled:"+isDisabled);
				if(undefined==isDisabled){
					alert(isDisabled);
					("#methodSelect_"+i).attr("disabled","disabled");
				}
			}
			**/
			
			
			$("#methodSelect_"+checkboxId).removeAttr("disabled");
			$("#fixField_"+checkboxId).removeAttr("disabled");
			$("#selectField_"+checkboxId).removeAttr("disabled");
			//var checkboxId = $('input[name=checkBox]:checked').parent().index();
			//var checkboxId = $('input[name=checkBox]:checked').length;
			//var newStr = "methodSelect_"+checkboxId;
		   	//var selectClass = $("#"+newStr).attr("fuck");
			//alert(checkboxId);
			
			//alert(newStr);
			//alert(selectClass);
		}
		
			//动态添加字段值
      	 function getTemplateField(){
      	 	var fieldlist;
      	 	var fieldjson;
      	 	var formDesc = $("#formDesc").val();
      	 	var fieldvar=[];
      	 	var url = "${ctx}/platform/bpm/bpmTemplateEventBind/getTemplateField.ht?formDesc="+formDesc;
				$.ajax({
			    	type:"post",
			    	async:false,
			    	url:url,
			    	success:function(result){
			    		fieldlist = result;
						fieldjson = $.parseJSON(fieldlist);
						$.each(fieldjson,function(idx,obj){
							fieldvar.push(obj);
							$(".selectvar").append("<option value =\""+obj.fieldname+"\">"+obj.fieldname+"</option>");
						});
					}
					
			    });
      	 	
      	 }
	
		
   
</script>
</head>
<body>
		<div id="eventSelectPanel">
		<fieldset class="base" >
			 <legend>列显示形式</legend>
			 <table style="margin-top:0px" border="0" table-layout="fixed">
			 	<tr>
			 		
			 		 	<div class="panel-toolbar" >
                            <div class="toolBar" >
                                <div class="group">
                                	<td>
                                		<label></label>
                                	</td>
                                </div>
                            </div>
                        </div>
                </tr>
             </table>
        </fieldset>
        </div>
        <div id="eventSelectPanel">
		<fieldset class="base" >
			 <legend>事件选择</legend>
			 <table style="margin-top:0px" border="0" table-layout="fixed">
			 	<tr>
			 		
			 		 	<div class="panel-toolbar" >
                            <div class="toolBar" >
                                <div class="group">
			 		 	<!--  
                         <input name="disable" class="inputText" type="text"  id="definitionid" value="" style="vertical-align:middle;margin-left: 0px;margin-right: 10px;width: 250px;display:none;"></input>
                         -->
                          <td>
                         
                   事件图： <input name="disable" class="inputText" type="text" disabled="disabled" id="definition" value="" style="margin-left: 10px;margin-right: 0px;width: 150px;height:25px;" ></input>
                     </td>
			 		 <td >
                          <input type="button" value="已有" onclick="addFlow()" name="disable"  class="choosegraph" id="btnSearch" href="javascript:;" style="width:45px;height:25px"></input>
                     </td>
                      <td>
                          <input type="button" value="新建" onclick="newevent()" name="disable"  class="choosegraph" id="btnSearch" href="javascript:;" style="width:45px;height:25px"></input>
                          </td>
                           <td>
                          <input type="button" value="重置" name="disable" onclick="reset()"  class="choosegraph" id="btnSearch" href="javascript:;" style="width:45px;height:25px"></input>
                          </td>
                               </div>
                            </div>
                        </div>
                     
			 	</tr>
			 	
			 	<tr>
			 		<td width="100%">
                        <div class="panel-toolbar" >
                            <div class="toolBar" >
                                <div class="group"> 
                              事&nbsp;&nbsp;件：&nbsp;<select  name="disable"  id="selectevent"  onChange="getEvent(this);" style="box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);border-radius: 4px;margin-left: 5px;margin-right: 10px;width: 150px;height: 30px">
                                            <!--<optgroup label="事件选择"></option>-->
                                            <!--<option value="0" style="font-weight:bold">&nbsp选择事件</option> -->
                                            <option value ="onclick"  >onclick</option>
                                            <option value ="onchange" selected="selected" >onChange</option>
                                            <option value ="onblur">onBlur</option>
                                            <option value ="ondrag">onDrag</option>
                                            <option value ="onInput" >onInput</option>
                                            <option value ="onmousedown">onmousedown</option>
                                             <option value ="onmousemove"  >onmousemove</option>
                                             <option value ="onmouseup">onmouseup</option>
                                          </select>
                                </div>
                            </div>
                        </div>
                    </td>
			 	</tr>
			 	<tr>
			 		<td><input id="transactionId" value="" type="hidden"></td>
			 		<td><input id="preRadio" value="" type="hidden"></td>
			 		<td><input id="formDesc" value="${formDesc}" type="hidden"></td>
			 	</tr>
			 </table>
		</fieldset>
	</div>
	<div id="eventAnalysisPanel">
		<fieldset class="base">
			<legend>参数解析</legend>
				<table id="analysisTable">
					<tr>
			 			<td>
			 				<div class="panel-toolbar" >
                           		<div class="toolBar" >
                                	<div class="group"> 
                                		输入个数：<input name="inputarg" class="inputText" type="text" disabled="disabled" id="inputnum" value="" style="margin-left: 10px;margin-right: 0px;width: 150px"></input>
                                		
                                	</div>
                                </div>
                             </div>   
			 			</td>
			 			<td>
			 				<input type="button" value="解析" onclick="fieldAnalysis()" name="disable"  class="choosegraph" id="btnany" href="javascript:;" style="width:45px;height:25px "></input>
			 				<input type="button" value="保存" onclick="bindSave()" name="disable"  class="choosegraph" id="btnany" href="javascript:;" style="width:45px;height:25px "></input>
			 			</td>
			 	</tr>
				</table>
		</fieldset>
	</div>
	
    <div id="eventBindPanel">
    
    </div>
</body>
</html>