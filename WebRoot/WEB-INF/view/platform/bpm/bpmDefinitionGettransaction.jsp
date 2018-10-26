<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="java.util.List,com.hotent.core.util.AppUtil,com.hotent.platform.model.bpm.BpmDefinition"
%>
  <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
  <html xmlns="http://www.w3.org/1999/xhtml">
    
    <head>
      <title>
      </title>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
        <style>
          .new_design,.had_design,.last_design { background: #C8C8C8 repeat-x; padding-top:
          3px; border-top: 1px solid #708090; border-right: 1px solid #708090; border-bottom:
          1px solid #708090; border-left: 1px solid #708090; width: 180px; height:
          auto; font-size: 10pt; cursor: hand; } .ok_btn,.cancle_btn { background:
          #7D7D7D repeat-x; width: 280px; height: 250px; } .free_design { background:
          #FF7575; padding-top: 3px; border-top: 1px solid #708090; border-right:
          1px solid #708090; border-bottom: 1px solid #708090; border-left: 1px solid
          #708090; width: 180px; height: auto; font-size: 10pt; cursor: hand; }
        </style>
        <script type="text/javascript"><!--          
          var dialog = frameElement.dialog;
          var defId,subject,defKey;
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
               			$("#subject").val(subjects);
               			$("#eventdetail").attr("href",eventurl);
               			$("#eventdetail").text(subjects);   
              }
            });
          };
      	 function bangSave(){
      	 	var varlist;
      	 	var url = "${ctx}/platform/bpm/bpmDefinition/getvarlist.ht?defId="+defId;
      	 	$.ajax({
			    type:"post",
			    async:false,
			    url:url,
			    success:function(result){
					varlist = result;
				}
			});
      	 	dialog.get("sucCall")(defId,subject,defKey,varlist);
      	 };
      	 function eventrefresh(){
			var url = "${ctx}/platform/bpm/bpmDefinition/eventrefresh.ht";
			$.ajax({
			    type:"post",
			    async:false,
			    url:url,
			    success:function(result){
					if (result[1]!=""){
						var eventurl = "${ctx}/platform/bpm/bpmDefinition/design.ht?defId="+result[0]+"&flagflex=3";
						defId = result[0];
               			subject = result[1];
               			defKey = result[2];
						$("#subject").val(result[1]);
						$("#eventdetail").attr("href",eventurl);
               			$("#eventdetail").text(result[0]); 
					}else{
						alert("已刷新.");
					}
				}
			});
      	 };
      	 function newevent(){
      	    var url = "${ctx}/platform/bpm/bpmDefinition/design.ht?typeName=transactionChart&status=-1&designChart=true";
      	 	//var ref = top.window.open(url, '_blank', 'location=no');  
			//var myCallback = function(event) {  
    			//alert(event);  
		 	//}
			//ref.addEventListener('beforeunload', myCallback);
			url = url.getNewUrl();
			$.openFullWindow(url);	 
      	 };
      	 function eventclose(){
      	 	var url = "${ctx}/platform/bpm/bpmDefinition/eventclose.ht";
			$.ajax({
			    type:"post",
			    async:false,
			    url:url,
			    success:function(result){
					if (result){
						dialog.close();
					}else{
						alert("暂时无法关闭！");
					}
				}
			});
      	 }
        --></script>
    </head>
    
    <body>
      <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
        <tr>
          <th width="100%" align="center">
            <center>
              <h2>
                ${formsubject}:绑定事件图 
                <h2>
            </center>
          </th>
        </tr>
      </table>
      <div id="inputPanel">
        <form id="agentSettingForm" method="post" action="save.ht">
          <div class="panel-body">
            <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
                    <tr id="firstRow">
                      <th width="30%">
                        场景名称：
                      </th>
                      <td>
                        <input type="hidden" id="defKey" name="flowkey" readonly="readonly" value="${bpmdefinition.defKey }">
                        <input type="hidden" id="defId" name="flowId" readonly="readonly" value="${bpmdefinition.defId}"/>
                        <input type="text" id="subject" name="subject" readonly="readonly" value="${bpmdefinition.subject}"/>
                      </td>
                      <td>
                        <div class="panel-toolbar">
                          <div class="toolBar">
                            <div class="group">
                              <a class="link flowDesign" onclick="newevent();">
                                <span>
                                </span>
                                新建事件图
                              </a>
                            </div>
                            <div class="group">
                              <a class="link add" href="javascript:;" onclick="addFlow();">
                                <span>
                                </span>
                                选择已有事件图
                              </a>
                            </div><!--
                            <div class="group">
                              <a class="link add" href="javascript:;" onclick="disbang()">
                                <span>
                                </span>
                                解除绑定
                              </a>
                            </div>
                          --></div>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <th width="30%">
                        详细信息：
                      </th>
                      <td>
                        <a id="eventdetail" href="${ctx}/platform/bpm/bpmDefinition/design.ht?defId=${bpmdefinition.defId}&flagflex=3" target="_blank">
                        </a>
                      </td>
                      <td>
                      </td>
                    </tr>
              </tbody>
            </table>
          </div>
          <table class="table-detail" cellpadding="0" cellspacing="0" border="0"
          style="margin-left: 3px; margin-right: 3px;">
            <tbody id="bpmAgentItem">
              <tr>
                <th width="30%">
                </th>
                <td>
                  <div class="panel-toolbar">
                    <div class="toolBar">
                      <div class="group">
                        <a class="link add" href="javascript:;" onclick="bangSave();eventclose();">
                          <span>
                          </span>
                          确定
                        </a>
                        <a class="link reload" href="javascript:;" onclick="eventrefresh();">
                          <span>
                          </span>
                         刷新
                        </a>
                        <a class="link add" href="javascript:;" onclick="eventclose();">
                          <span>
                          </span>
                          取消
                        </a>
                      </div>
                    </div>
                  </div>
                </td>
              </tr>
          </table>
        </form>
      </div>
    </body>
  
  </html>