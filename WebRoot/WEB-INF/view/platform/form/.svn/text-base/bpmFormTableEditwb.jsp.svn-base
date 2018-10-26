<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title>添加表</title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/ColumnDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/EditTable.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FieldsManage.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormTableDialog.js"></script>
<style type="text/css">
	input.error {
	    border: 1px solid red;
	}
	#showMainTable{
		font-size: 13px;
		background-color: #EFF2F7;
	    border: 1px solid #CCD5E4;
	    border-radius: 5px 5px 5px 5px;
	    cursor: default;
	    float: left;
	    height: auto !important;
	    margin: 3px;
	    overflow: hidden;
	    padding: 2px 4px;
	    white-space: nowrap;
	    display:none;
	}
</style>
<script type="text/javascript">
	var tableId=${tableId};
	var tableName='${bpmFormTable.tableName}';
	var validator;
	var fieldObj;
	var canEditTbColName=${canEditTbColName};
	var mainTableIsPublished='${mainTableIsPublished}';
	var parcelId='${parcel.id}';
	var table=null;
	
	$(function(){
	/*
		var isEdit=tableId>0;
		fieldObj=new FieldsManage();
		mainTableIsPublished = ~~mainTableIsPublished;
		TableRow.setFieldManage(fieldObj);
		TableRow.setAllowEditColName(canEditTbColName);
		TableRow.setIsExternal(0);
		TableRow.setIsEdit(isEdit);
		if(isEdit){
			$.get("getByTableId.ht",{tableId:tableId,mainTableIsPublished:mainTableIsPublished},function(data){
				bindTable(data,canEditTbColName,mainTableIsPublished);
				table=data.table;
			});
		}
		
		*/
		//行高亮
		$("#tableColumnItem").delegate("tbody>tr", "click", function() {
			$("#tableColumnItem>tbody>tr").removeClass("over");
			$(this).addClass("over");
		});
		//编辑列的详细信息
		$("#tableColumnItem").delegate("tbody>tr a[name='editColumn']", "click", function() {
			var trObj=$(this).parent().parent();
			var idx=$("#tableColumnItem>tbody>tr").index(trObj);
			var field=fieldObj.getFieldByIndex(idx);
			TableRow.editField(field.fieldName,$("input[name='isMain']:checked").val());
		});
		//处理选项选择。
		$("#tableColumnItem").delegate("tbody>tr input:checkbox", "click", function() {
			var chkObj=$(this);
			TableRow.editFieldOption(chkObj);
		});
		//处理点击列名和列注释。
		$("#tableColumnItem").delegate("tbody>tr>td[class='editField']", "click", function() {
			var tdObj=$(this);
			TableRow.editNameComment(tdObj);
		});
		
		//字段上移动
		$("#moveupField").click(function(){
			TableRow.move(true);
		});
		//字段下移
		$("#movedownField").click(function(){
			TableRow.move(false);
		});
		
		$("#delField").click(function(){
			TableRow.del();
		});
		
		validator=validTable();
		//处理字段保存
		
		$("#dataFormSave").click(function(){
			handSave();
		});
		//处理点击主表子表的radio事件。
		handIsMain();
		
		// 点击返回时判断字段是否修改
		$("a.back").click(function(){
			if(isEdited){
				$.ligerDialog.confirm("自定义表已修改，是否进行保存?","提示",function(rtn){
					if(!rtn){
						location.href='list.ht';
					}
					else{
						handSave();
					}
				});
			}else{
				location.href='list.ht';
			}
		});
		$("a.moreinfo").click(function(){
			var me = $(this),
				hrefStr = me.attr('hrefstr');
			if(!hrefStr)return;
			openDetailWin({url:hrefStr});
		});
		$("a.table").click(generatorSubToMain);
		$("input[name='publishedAsMain']").click(switchMainType).each(function(){
			var val = $(this).val();

			if(mainTableIsPublished){
				if(~~val){
					$(this).attr("checked",true).trigger("click");
				}
				else{
					$(this).attr("checked",false).trigger("click");
				}
			}
		});
	});

	//为已生成的主表  生成子表
	function generatorSubToMain(){
		$.ligerDialog.confirm("确定要生成子表吗?","提示",function(rtn){
			if(!rtn) return;
			handSave(1);
		});
	};

	function choosePublishMain(){
		var callBack=function(tableId,tableName){
			$("#mainTableId").val(tableId);
			$("#tableName").val(tableName);
		}
		
		FormTableDialog({callBack:callBack,isExternal:0});
	};

	function switchMainType(){
		var me = $(this),
			state = me.val();
		if(~~state){
			$("#mainTable").hide();
			$("#choose-div").show();
			$("div.generate").show();
			$("#mainTableId").val("");
			$("#mainTable").val(0);
		}
		else{
			$("#mainTable").show();
			$("#choose-div").hide();
			$("div.generate").hide();
			$("#mainTableId").val("");
			$("#tableName").val("");
		}
	};

	function openDetailWin(conf){
		var dialogWidth=1000;
		var dialogHeight=700;
		
		conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);
		var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
			+"px;help=" + conf.help +";status=" + conf.status +";scroll=1;center=" +conf.center;
		//window.showModalDialog(conf.url,"",winArgs);
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : '',
			url: conf.url, 
			isResize: true,
		});
	};
	
	/**
	 * 处理保存事件。
	 */
	function handSave(generate){
			var rtn=validator.form();
			if(!rtn) return;
			
		//	var isMain=$("input[name='isMain']:checked").val();
		
			var name=$("#name").val();
			var comment=$("#comment").val();
			/*
			//处理主表字段。
			var mainTableId=$("#mainTableId").val();
			
			var objMainTable=$("#mainTable");
			var isPublished = table? ((table.isPublished == 1)?false:true):true;
			
			var isVisible=$("#spanMainTable").is(":visible");
			
			if(isVisible){
				//如果修改了主表的值，则重新选取主表。
				var publishedAsMain=$("input[name='publishedAsMain']:checked").val();
				if(publishedAsMain==0){
					mainTableId=$("#mainTable").val();
				}
			}
			
			if(mainTableId=="" || mainTableId=="0"){
				if(isMain !=1 && canEditTbColName && isPublished){
					$.ligerDialog.error("请选择所属主表!","提示");
					 return;
				}
			}
			
		*/
			var value=$("#resultfield").val();
			var url=__ctx + '/tableParcel/tableParcel/tableParcel/saveParcel.ht' 
			//
			$.post(url,{tablename:tableName,name:name,comment:comment,value:value,id:parcelId},showResponse);
	}
	
	/**
	 * 显示返回结果。
	 */
	function showResponse(data){
		var obj=new com.hotent.form.ResultMessage(data);
		var url=__ctx+"/platform/form/bpmFormTable/parcellist.ht?tableId=" + tableId;
		if(obj.isSuccess()){//成功
			$.ligerDialog.confirm("操作成功,继续操作吗?","提示",function(rtn){
				if(!rtn){
					location.href=url;
				}
				else{
					location.href=location.href.getNewUrl();
				}
			});
	    }else{//失败
	    	$.ligerDialog.err("提示","添加数据包失败",obj.getMessage());
	    };
	 
	};
	
	function clickAddRow(){
		TableRow.addColumn($("input[name='isMain']:checked").val());
	};
	//根据注释生成表名
	
	function autoGetTableKey(inputObj){
	/*
		var url=__ctx + '/platform/form/bpmFormTable/getTableKey.ht' ;
		var subject=$(inputObj).val();
		if($.trim(subject).length<1) return;
		$.post(url,{'subject':subject,'tableId':'${tableId}'},function(response){
			var json=eval('('+response+')');
			if(json.result==1 ){
				if($.trim(	$('#name').val()).length<1){
					$('#name').val(json.message);
				};
			}else{
				$.ligerDialog.error(json.message,"提示");
			}
		 });
		 */
	}
	
	
	//seting
	function dialog(){
	/*
			$("#selInfo").text("");
			var id=$("#id").val();
			var istable=$("#istable").val();
			var objname=$("#objname").val();
			var dataSource=$("#dataSource").val();
			var style=$("input[name='style']:checked").val();
			
			if(id==0){
				if(objname==null){
					$("#selInfo").text("请先选择数据库表");
					return ;
				}
			}
			*/
			
		//	showSettingDialog(dataSource,objname,istable,style,id);
		}
		
		function showSettingDialog(dsName,objectname,istable,style,id){
			var settingobj=$("#settingobj").val(),
				fields={};
			
			if(settingobj==objectname){
				var displayField=$("#displayfield").val(),
					conditionField=$("#conditionfield").val(),
					sortField=$("#sortfield").val(),
					resultField=$("#resultfield").val();
				
				if(displayField)
					fields.displayField=displayField;
				if(conditionField)
					fields.conditionField=conditionField;
				if(resultField)
					fields.resultField=resultField;
				if(sortField)
					fields.sortField=sortField;
			}
			
			var url=__ctx+"/platform/form/bpmFormDialog/setting.ht?dsName=" +dsName +"&objectName=" + objectname +"&istable=" + istable +"&style=" +style +"&id=" + id;
			DialogUtil.open({
                height:560,
                width: 805,
                title : '设置列',
                url: url, 
                isResize: true,
                //自定义参数
                fields: fields,
                sucCall:function(rtn){
                	$("#settingobj").val(objectname);
           		 	$("#displayfield").val(rtn[0]);
           		 	$("#conditionfield").val(rtn[1]);
           		 	$("#resultfield").val(rtn[2]);
           		 	$("#sortfield").val(rtn[3]);
           		 	$("#styletemp").val(rtn[4]);
                }
            });
          	
		}
		function showSetDialog(){
			var fields={};
			var resultField=$("#resultfield").val();
			if(resultField!=null)
					fields.resultField=resultField;
			/*
			if(settingobj==objectname){
				var displayField=$("#displayfield").val(),
					conditionField=$("#conditionfield").val(),
					sortField=$("#sortfield").val(),
					resultField=$("#resultfield").val();
				
				if(displayField)
					fields.displayField=displayField;
				if(conditionField)
					fields.conditionField=conditionField;
				if(resultField)
					fields.resultField=resultField;
				if(sortField)
					fields.sortField=sortField;
			}
			*/
			var url=__ctx+"/platform/form/bpmFormTable/setting.ht?id=" + tableId;
			DialogUtil.open({
                height:560,
                width: 805,
                title : '设置列',
                url: url, 
                isResize: true,
                //自定义参数
                fields: fields,
                sucCall:function(rtn){
                //	$("#settingobj").val(objectname);
           		 	$("#displayfield").val(rtn[0]);
           		 	$("#conditionfield").val(rtn[1]);
           		 	$("#resultfield").val(rtn[2]);
           		 	$("#sortfield").val(rtn[3]);
           		 	$("#styletemp").val(rtn[4]);
                }
            });
          	
		}
</script>
</head>
<body>
	
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
				<c:choose>
					<c:when test="${tableId==0}">
						添加自定义表
					</c:when>
					<c:otherwise>
						编辑自定义表
					</c:otherwise>
				</c:choose> </span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="parcellist.ht?tableId=<%=request.getParameter("tableId") %>"><span></span>返回</a></div>
					<div class="l-bar-separator"></div>
					<!--
					<div class="group"><a onclick="clickAddRow()" class="link add"><span></span>添加列</a></div>
					<div class="l-bar-separator"></div>
					  
					<div class="group"><a id="moveupField" class="link moveup"><span></span>上移</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a id="movedownField" class="link movedown"><span></span>下移</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a id="delField" class="link del "><span></span>删除</a></div>
					-->
					<div class="group generate hidden"><a class="link table" href="javascript:;"><span></span>生成表</a></div>
				</div>
			</div>
		</div>
		</div>
	
		<form id="bpmTableForm" method="post" action="saveTableParcel.ht">
			<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						
						
						<th width="5%">数据包名: </th>
						<td width="15%">
							<input type="text" id="name" name="name" maxlength='25' class="inputText longInputText" value="${parcel.parcel_name }"/>							
						</td>
						<th width="5%">注释: </th>
						<td width="15%">
							<input  type="text" id="comment" name="comment"  class="inputText longInputText"  value="${parcel.parcel_alias }"  onblur="autoGetTableKey(this)"/>
						</td>
					</tr>
						
					<tr>
					<!-- 
						<th width="5%">是否主表: </th>
						<td width="15%">
							<label><input  type="radio"  name="isMain"  value="1" checked="checked"/>主表</label>
							<label><input  type="radio"  name="isMain"  value="0" />从表</label>
						</td>
						 -->
						<th width="5%">所属表:</th>
						<td width="15%">
							
							${bpmFormTable.tableName }
						</td>
						<th> 请选择列</th>
						<td>	<a href="javascript:;" id="btnSetting" class="link setting" onclick="showSetDialog()">设置列</a></td>
					</tr>
				</table>
			
				<!-- 
				
				<table id="tableColumnItem" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1" style="margin-top: 3px;">
			   		<thead>
			   			<th width="10%">列名</th>
			   			<th width="20%">注释</th>
			    		<th width="10%">类型</th>
			    		<th width="10%">必填</th>
			    		<th width="10%" name="Listth">显示到列表</th>
			    		<th width="10%" >是否流程变量</th>
			    		
						<c:if test="${!canEditTbColName}">
			    		<th width="10%">是否删除</th>
			    		</c:if>
			    		<th width="10%">管理</th>
			    	</thead>
			    	<tbody>
			    		
			    	</tbody>
			    	
			   	 </table>
			   	  -->
		   	 	</div>
		   	 	<textarea  id="resultfield"  name="resultfield" style="display: none;">${parcel.parcel_value }</textarea>
		   	 	<input type="hidden"  id="tablename" value="${bpmFormTable.tableName }"/>
		</form>
	
</div>
</body>
</html>
