/**
 * @description 输入框（按钮、单选按钮、复选框、单行文本、多行文本、下拉框等）
 * @name baidu.editor.execCommand
 * @param {String}
 *            cmdName 输入框
 * @author heyifan
 */
UE.plugins['input'] = function() {
	var me = this,
	    dataType = [
			          {name:'文字',value:'varchar',display:1},
			          {name:'数字',value:'number',display:1},
			          {name:'日期',value:'date',display:1},
			          {name:'大文本',value:'clob',display:1}
		            ],
      valueFrom = [
					{name:'表单输入',value:'0',display:1},
					{name:'脚本运算(显示)',value:'1',display:1},
					{name:'脚本运算(不显示)',value:'2',display:1},
					{name:'流水号',value:'3',display:1}
	              ],
     optionHtml = ['<div id="panel" class="s">',
                   '<table id="option-table">',
                   '<thead><tr><td colspan="2"><a href="###" class="link add">添加</a></td></tr></thead>',
                   '<tbody></tbody></table>',
                   '<div class="hidden"><table id="hiddenTable"><tbody><tr class="editable-tr">',
                   '<td><div class="editable-left-div"><label>值: <input name="optionKey" type="text" class="short"/></label>',
                   '<label style="margin:0 0 0 10px;">选项: <input name="optionValue" class="long" type="text"/></label></div></td>',
                   '<td><div class="editable-right-div"><a href="###" class="remove-bnt" tabindex="-1">移除</a></div></td>',
                   '</tr></tbody></table></div></div>'].join('');
	//添加按钮
	me.commands['addinput'] = {
		execCommand : function(cmdName) {
			me.curInput=null;
			this.ui._dialogs['addinputbutton'].open();
			return true;
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	//编辑按钮
	me.commands['editinput'] = {
		execCommand : function(cmdName) {
			me.curInput=this.selection.getRange().getClosedNode();
			this.ui._dialogs['addinputbutton'].open();
			return true;
		},
		queryCommandState : function() {
			 var el = this.selection.getRange().getClosedNode();
			 if(!el){
				 return -1;
			 }
			 else if(el.tagName.toLowerCase()=='input'||el.tagName.toLowerCase()=='textarea'||el.tagName.toLowerCase()=='select'){
				 return this.highlight ? -1 : 0;
			 }
			 return -1;
		}
	};
	//隐藏域
	me.commands['hidedomain'] = {
			//cmdName 命令名称 ，如：此处为hidedomian
			//data 编辑界面点击确定时的内容
			//old 编辑的input对象 
			execCommand : function(cmdName,data,old) {
				if(data){
					if(old){
						old.setAttribute("external",getExternal(data));
					}
					else{						
						insertControl(cmdName,data,'<input type="text" />');
					}
				}
				else{
					me.curInputHtml=null;
					var html='';
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};

	//单行文本
	me.commands['textinput'] = {
			execCommand : function(cmdName,data,old) {
				if(data){					
					if(old){
						var previewstyle=setStyle(data,cmdName);
						old.setAttribute("external",getExternal(data));
						old.firstElementChild.setAttribute("style",previewstyle);
					}
					else{
						var previewstyle='style="'+setStyle(data,cmdName)+' "  ';
						insertControl(cmdName,data,'<input type="text"  name=m:c:"'+$("input[eid=\'name\']").val()+'" '+previewstyle+'/>');
					}
				}
				else{	
					dataType=[
					          {name:'文字',value:'varchar',display:1},
					          {name:'数字',value:'number',display:1},
					          {name:'日期',value:'date',display:1},
					          {name:'大文本',value:'clob',display:0}
				          ];
					me.curInputHtml=null;
					var html=getStyleHtml();
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}				
			},
		
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	//多行文本
	me.commands['textarea'] = {
			execCommand : function(cmdName,data,old) {
				if(data){					
					if(old){
						old.setAttribute("external",getExternal(data));
					}
					else{
						var colNum;
						var rowNum;
						for(var i=0;i<data.length;i++){
							if(data[i].id=='cols'){
								colNum=data[i].val;
							}
							if(data[i].id=='rows'){
								rowNum=data[i].val;
							}
						}
						insertControl(cmdName,data,'<textarea cols="'+colNum+'" rows="'+rowNum+'"></textarea>');
					}
				}
				else{	
					dataType=[
					          {name:'文字',value:'varchar',display:0},
					          {name:'数字',value:'number',display:0},
					          {name:'日期',value:'date',display:0},
					          {name:'大文本',value:'clob',display:1}
				          ];
					valueFrom=[
								{name:'表单输入',value:'0',display:1},
								{name:'脚本运算(显示)',value:'1',display:0},
								{name:'脚本运算(不显示)',value:'2',display:0},
								{name:'流水号',value:'3',display:0}
				             ];
					me.curInputHtml=null;
					var html='<tr class="style_tr"><th>列数:</th><td><input type="text" eid="cols"  style="width:120px; "/></td><th>行数:</th><td><input type="text" eid="rows" style="width:120px; "/></tr>';
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}				
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	//复选框
	me.commands['checkbox'] = {
			execCommand : function(cmdName,data,old) {				
				if(data){
					var text=[''];
					for(var i=0,c;c=data[i++];){
						if(c.id=='options'){
							var options = c.val;
							if(typeof options ==='string'){
								var optAry = options.split(/\n/g),
									opt;
								
								options = [];
								while(opt = optAry.shift()){
									options.push({key:opt,value:opt});
								}
							}
							c.val = options;
							for(var j=0,m;m=options[j++];){
								text.push('<label><input type="checkbox" value="'+m.key+'"/>'+m.value+'</label>');
							}
						}
					}
					if(old){
						old.setAttribute("external",getExternal(data));
						old.innerHTML = text.join('');
					}
					else{
						insertControl(cmdName,data,text.join(''));
					}
				}
				else{					
					me.curInputHtml=null;
					var html='<tr><th>复选框选项:</th><td colspan="3">'+optionHtml+'</td></tr>';
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}				
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	//单选按钮
	me.commands['radioinput'] = {
			execCommand : function(cmdName,data,old) {
				if(data){
					var text=[''];
					for(var i=0,c;c=data[i++];){
						if(c.id=='options'){
							var options = c.val;
							if(typeof options ==='string'){
								var optAry = options.split(/\n/g),
									opt;
								
								options = [];
								while(opt = optAry.shift()){
									options.push({key:opt,value:opt});
								}
							}
							c.val = options;
							for(var j=0,m;m=options[j++];){
								text.push('<label><input type="radio" value="'+m.key+'"/>'+m.value+'</label>');
							}
						}
					}
					if(old){
						old.setAttribute("external",getExternal(data));
						old.innerHTML = text.join('');
					}
					else{
						insertControl(cmdName,data,text.join(''));
					}
				}
				else{					
					me.curInputHtml=null;
					var html='<tr><th>单选按钮选项:</th><td colspan="3">'+optionHtml+'</td></tr>';
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	//下拉框
	me.commands['selectinput'] = {
			execCommand : function(cmdName,data,old) {				
				if(data){
					var previewstyle='style="'+setStyle(data,cmdName)+'"';
					
					var text=['<select  '+previewstyle+'>'];
					for(var i=0,c;c=data[i++];){
						if(c.id=='options'){
							var options = c.val;
							if(typeof options ==='string'){
								var optAry = options.split(/\n/g),
									opt;
								
								options = [];
								while(opt = optAry.shift()){
									options.push({key:opt,value:opt});
								}
							}
							c.val = options;
							for(var j=0,m;m=options[j++];){
								text.push('<option value="'+m.key+'">'+m.value+'</option>');
							}
						}
					}
					text.push('</select>');
					if(old){
						var previewstyle=setStyle(data,cmdName);
						old.firstElementChild.setAttribute("style",previewstyle);						
						old.setAttribute("external",getExternal(data));
						old.innerHTML = text.join('');
					}
					else{
						insertControl(cmdName,data,text.join(''));
					}
				}
				else{					
					me.curInputHtml=null;
					var html=getStyleHtml();
//					html+='<tr><th>下拉框选项</th><td colspan="3"><span style="color:green;">每行对应一个选项<br /></span><textarea eid="options" cols="50" rows="5"></textarea></td></tr>';
					html += '<tr><th>下拉框选项:</th><td colspan="3">'+optionHtml+'</td></tr>';
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};	
	//数据字典
	me.commands['dictionary'] = {
			execCommand : function(cmdName,data,old) {				
				if(data){
					if(old){
						var previewstyle=setStyle(data,cmdName);
						old.firstElementChild.setAttribute("style",previewstyle);			
						old.setAttribute("external",getExternal(data));
					}
					else{
						var previewstyle='style="'+setStyle(data,cmdName)+'"';
						var text='<input type="text"  '+previewstyle+'/>';
						data = changeStyle(data,cmdName);   //把在编辑区域中默认的高宽 变成实际 展示的默认高宽
						insertControl(cmdName,data,text);
					}
				}
				else{					
					me.curInputHtml=null;
					var html='<tr><th>选择数据字典</th><td colspan="3"><input eid="dictTypeName" id="dictTypeName" class="catComBo" catKey="DIC" valueField="dictType" isNodeKey="true" name="dictTypeName" height="100" width="200"/></td></tr>';
					html+=getStyleHtml();
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	//人员选择器
	me.commands['personpicker'] = {
			execCommand : function(cmdName,data,old) {
				if(data){	
					var content="选择";
					for(var i=0,c;c=data[i++];){
						if(c.id=="buttoncontent"){
							content=c.val;
						}
					}
					var text='<input type="text" /><a href="javascript:;" class="link user">'+content+'</a>';
					if(old){
						old.setAttribute("external",getExternal(data));
						old.innerHTML = text;
					}
					else{
						insertControl(cmdName,data,text);
					}
				}
				else{					
					me.curInputHtml=null;
					var html='<tr><th>按钮文本</th><td><input eid="buttoncontent" type="text" value="选择" /></td><th>其他设定:</th><td><label><input eid="singleselect" type="checkbox" onclick="handCurUser(this)"/>单选</label><label style="display:none"><input eid="showCurUser" type="checkbox" />显示当前用户</label></td></tr>';
					html+='<tr id="fromTypeTr" ><th>限定类型:</th><td colspan="3"><select id="fromType" name="fromType"></select><div class="tipbox"><a class="tipinfo"><span>根据当前组织，限定选择器的可选择范围</span></a></div></td></tr>';
					html+='<tr id="fromTypeScriptTr" style="display:none;" ><th>脚本:</th><td colspan="3"><a href="javascript:;" onclick="addScript(this)" id="btnScript" class="link var" title="常用脚本">常用脚本</a><br/>返回组织ID<br /> <textarea id="fromScript" codemirror="true" name="fromScript" rows="6" cols="60"></textarea></td></tr>';
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	//角色选择器
	me.commands['rolepicker'] = {
			execCommand : function(cmdName,data,old) {
				if(data){	
					var content="选择";
					for(var i=0,c;c=data[i++];){
						if(c.id=="buttoncontent"){
							content=c.val;
						}
					}
					var text='<input type="text" /><a href="javascript:;" class="link role">'+content+'</a>';
					if(old){
						old.setAttribute("external",getExternal(data));
						old.innerHTML = text;
					}
					else{
						insertControl(cmdName,data,text);
					}
				}
				else{					
					me.curInputHtml=null;
					var html='<tr><th>按钮文本</th><td ><input eid="buttoncontent" type="text" value="选择" /></td><th>其他设定:</th><td><label><input eid="singleselect" type="checkbox" />单选</label></td></tr>';
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	//岗位选择器
	me.commands['positionpicker'] = {
			execCommand : function(cmdName,data,old) {
				if(data){	
					var content="选择";
					for(var i=0,c;c=data[i++];){
						if(c.id=="buttoncontent"){
							content=c.val;
						}
					}
					var text='<input type="text" /><a href="javascript:;" class="link position">'+content+'</a>';
					if(old){
						old.setAttribute("external",getExternal(data));
						old.innerHTML = text;
					}
					else{
						insertControl(cmdName,data,text);
					}
				}
				else{					
					me.curInputHtml=null;
					var html='<tr><th>按钮文本</th><td><input eid="buttoncontent" type="text" value="选择" /><th>其他设定:</th><td><label><input eid="singleselect" type="checkbox" />单选</label></td></td></tr>';
					html+='<tr id="fromTypeTr" ><th>限定类型:</th><td colspan="3"><select id="fromType" name="fromType"></select><div class="tipbox"><a class="tipinfo"><span>根据当前组织，限定选择器的可选择范围</span></a></div></td></tr>';
					html+='<tr id="fromTypeScriptTr" style="display:none;" ><th>脚本:</th><td colspan="3"><a href="javascript:;" onclick="addScript(this)" id="btnScript" class="link var" title="常用脚本">常用脚本</a><br/>返回组织ID<br /> <textarea id="fromScript" codemirror="true" name="fromScript" rows="6" cols="60"></textarea></td></tr>';
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	//附件上传工具
	me.commands['attachement'] = {
			execCommand : function(cmdName,data,old) {
				if(data){	
					var content="选择";
					for(var i=0,c;c=data[i++];){
						if(c.id=="buttoncontent"){
							content=c.val;
						}
					}
					var text='<input type="text" /><a href="javascript:;" class="link attachement">'+content+'</a>';
					if(old){
						old.setAttribute("external",getExternal(data));
						old.innerHTML = text;
					}
					else{
						insertControl(cmdName,data,text);
					}
				}
				else{	
					dataType=[
					          {name:'文字',value:'varchar',display:1},
					          {name:'数字',value:'number',display:0},
					          {name:'日期',value:'date',display:0},
					          {name:'大文本',value:'clob',display:0}
				          ];
					valueFrom=[
								{name:'表单输入',value:'0',display:1},
								{name:'脚本运算(显示)',value:'1',display:0},
								{name:'脚本运算(不显示)',value:'2',display:0},
								{name:'流水号',value:'3',display:0}
				             ];
					me.curInputHtml=null;
					var html='<tr><th>按钮文本</th><td colspan="3"><input eid="buttoncontent" type="text" value="选择" /></td></tr>';
					html+='<tr><th>直接附件上传</th><td colspan="3"><input eid="directUpLoad" type="checkbox" /></td></tr>';
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	//部门选择器
	me.commands['departmentpicker'] = {
			execCommand : function(cmdName,data,old) {				
				if(data){
					var content="选择";
					for(var i=0,c;c=data[i++];){
						if(c.id=="buttoncontent"){
							content=c.val;
						}
					}
					var text='<input type="text" /><a href="javascript:;" class="link org">'+content+'</a>';
					if(old){
						old.setAttribute("external",getExternal(data));
						old.innerHTML = text;
					}
					else{
						insertControl(cmdName,data,text);
					}
				}
				else{					
					me.curInputHtml=null;
					var html='<tr><th>按钮文本</th><td><input eid="buttoncontent" type="text" value="选择" /></td><th>其他设定:</th><td><label><input eid="singleselect" type="checkbox" />单选</label><label><input eid="showCurOrg" type="checkbox" />显示当前部门</label></td></tr>';
					html+='<tr id="fromTypeTr" ><th>限定类型:</th><td colspan="3"><select id="fromType" name="fromType"></select><div class="tipbox"><a class="tipinfo"><span>根据当前组织，限定选择器的可选择范围</span></a></div></td></tr>';
					html+='<tr id="fromTypeScriptTr" style="display:none;" ><th>脚本:</th><td colspan="3"><a href="javascript:;" onclick="addScript(this)" id="btnScript" class="link var" title="常用脚本">常用脚本</a><br/>返回组织ID<br /> <textarea id="fromScript" codemirror="true" name="fromScript" rows="6" cols="60"></textarea></td></tr>';
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	//日期选择器
	me.commands['datepicker'] = {
			execCommand : function(cmdName,data,old) {	
				if(data){					
					if(old){
						old.setAttribute("external",getExternal(data));
					}
					else{
						var text='<input type="text" />';
						insertControl(cmdName,data,text);
					}
				}
				else{	
					dataType=[
					          {name:'文字',value:'varchar',display:0},
					          {name:'数字',value:'number',display:0},
					          {name:'日期',value:'date',display:1},
					          {name:'大文本',value:'clob',display:0}
					          ];
			      valueFrom=[
							{name:'表单输入',value:'0',display:1},
							{name:'脚本运算(显示)',value:'1',display:0},
							{name:'脚本运算(不显示)',value:'2',display:0},
							{name:'流水号',value:'3',display:0}
			             ];
					me.curInputHtml=null;
					var html=getStyleHtml();
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	//office控件
	me.commands['officecontrol'] = {
			execCommand : function(cmdName,data,old) {
				if(data){
					if(old){
						var previewstyle=setStyle(data,cmdName);
						old.firstElementChild.setAttribute("style",previewstyle);			
						old.setAttribute("external",getExternal(data));
					}
					else{
						var previewstyle='style="'+setStyle(data,cmdName)+'"';
						
						insertControl(cmdName,data,'<input type="text" '+previewstyle+' />');
					}
				}
				else{					
					dataType=[
					          {name:'文字',value:'varchar',display:1},
					          {name:'数字',value:'number',display:0},
					          {name:'日期',value:'date',display:0},
					          {name:'大文本',value:'clob',display:0}
				          ];
					valueFrom=[
								{name:'表单输入',value:'0',display:1},
								{name:'脚本运算(显示)',value:'1',display:0},
								{name:'脚本运算(不显示)',value:'2',display:0},
								{name:'流水号',value:'3',display:0}
				             ];		
					me.curInputHtml=null;
					var html=getStyleHtml();
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	//明细表
	me.commands['subtable'] = {
			execCommand : function(cmdName,data,old) {
				//执行插入html到编辑器的动作
				if(data){
					var num=0,tableName='',tableDesc='',model=0,show='1';
					for(var i=0,c;c=data[i++];){
						if(c.id=="tablerows")
							num=c.val;
						if(c.id=='tablename')
							tableName=c.val;
						if(c.id=='tablememo')
							tableDesc=c.val;
						if(c.id=='show'){
							if(c.val==1){
								show='true';
							}else{
								show='false';
							}
						}
						if(c.val==1){
							if(c.id=='inlinemodel')
								model=0;
							if(c.id=='blockmodel')
								model=1;
							if(c.id=='windowmodel')
								model=2;
							if(c.id=='inlinemodelNO')
								model=3;
							if(c.id=='windowmodelNO')
								model=4;
						}							
					}
					var external=getExternal(data);
					var html=['<div tablename="'+tableName+'" tabledesc="'+tableDesc
					          +'" type="subtable" name="editable-input" class="'+cmdName
					          +'" show="'+show+'" external="'+external+'">'];
					html.push(getSubtableContent(num,model));
					html.push('</div>');
					//对已有进行修改
					if(old){
						var externalStr=old.getAttribute("external").replace(/\&quot\;/g, '\"').replace(/\&\#39\;/g, '\'');						
						var externalObj=eval("("+externalStr+")");
						if(externalObj){
							var tablerows=externalObj.tablerows,
								oldModel;
							if(externalObj.inlinemodel==1)
								oldModel=0;
							if(externalObj.blockmodel==1)
								oldModel=1;
							if(externalObj.windowmodel==1)
								oldModel=2;
							if(tablerows==num&&oldModel==model){//不修改明细表模式和列数时，不会清空明细表中的字段
								old.setAttribute("external",external);
								old.setAttribute("tablename",tableName);
								old.setAttribute("tabledesc",tableDesc);
								old.setAttribute("show",show);
							}
							else
								old.outerHTML = html.join('');
						}							
						else
							old.outerHTML = html.join('');
					}
					//新添加
					else{					
						me.execCommand('insertHtml',html.join(''));
					}
				}
				//打开对话框
				else{
					me.curInputHtml=null;
					var html=['<table class="edit_table"><tr><th>明细表名称</th><td><input validate="{required:true ,variable:true,noDigitsStart:true}" eid="tablename" type="text" /></td><th>列数</th><td><input eid="tablerows" type="text" style="width:60px;" value="3"/></td></tr>'];
					html.push('<tr><th>明细表注释</th><td colspan="3"><input eid="tablememo" validate="{required:true}" type="text" style="width:300px;"/></td></tr>');
					html.push('<tr><th>添加数据模式</th><td colspan="3"><label for="inlinemodel"><input name="models" id="inlinemodel" eid="inlinemodel" type="radio" checked="checked"/>行模式</label>');
					html.push('<label for="inlinemodelNO"><input name="models" id="inlinemodelNO" eid="inlinemodelNO" type="radio" checked="checked"/>(序号)行模式</label>');
					html.push('<label for="blockmodel"><input name="models" id="blockmodel" eid="blockmodel" type="radio" />块模式</label><br>');
					html.push('<label for="windowmodel"><input name="models" id="windowmodel" eid="windowmodel" type="radio" />弹窗模式</label>');
					html.push('<label for="windowmodelNO"><input name="models" id="windowmodelNO" eid="windowmodelNO" type="radio" />(序号)弹窗模式</label></td></tr>');
					html.push('<tr><th>默认显示子表</th><td colspan="3" ><label><input eid="show" type="checkbox" checked="checked"/></label></td></tr>');
					html.push('</table><script type="text/javascript">bindData()</script>');
					me.curInputHtml = html.join('');
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return isInSubTable() ? -1 : 0;
			}
	};
	
		
	//web印章控件
	me.commands['websigncontrol'] = {
			execCommand : function(cmdName,data,old) {
				//alert(cmdName+"  "+ data +"  "+old );		
				//检验是否有web印章控件存在(页面中只能有一个web印章控件)
				if('undefined' == typeof (data)){       //新增 时 data 为 undefined 其它(修改的情况下不用检验)
					var obj = me.document.getElementsByTagName("span"); //先得到所有的SPAN标记
					for( var int = 0; int < obj.length; int++){
					  if(obj[int].className == 'websigncontrol'){  //找出span标记中有class=websigncontrol的那个标记
					     alert("注意:页面只允许有一个web印章控件!");
					     return; 
					  }
					}
				}
				
				if(data){
					if(old){
						var previewstyle=setStyle(data,cmdName);
						old.firstElementChild.setAttribute("style",previewstyle);			
						old.setAttribute("external",getExternal(data));
					}
					else{
						var previewstyle='style="'+setStyle(data,cmdName)+'"';
						
						insertControl(cmdName,data,'<input type="text" '+previewstyle+' />');
					}
				}
				else{					
					dataType=[
					          {name:'文字',value:'varchar',display:1},
					          {name:'数字',value:'number',display:0},
					          {name:'日期',value:'date',display:0},
					          {name:'大文本',value:'clob',display:0}
				          ];
					valueFrom=[
								{name:'表单输入',value:'0',display:1},
								{name:'脚本运算(显示)',value:'1',display:0},
								{name:'脚本运算(不显示)',value:'2',display:0},
								{name:'流水号',value:'3',display:0}
				             ];		
					me.curInputHtml=null;
					var html=getStyleHtml();
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	

	//获取对应模式子表内容
	function getSubtableContent(num,modelIndex){
		var text=[''];
		switch(modelIndex){
			case 0:
				//行模式
				text.push('<table class="listTable" border="0" cellpadding="2" cellspacing="0"><tbody>');
				text.push('<tr class="toolBar"><td colspan="'+num+'"><a class="link add" href="javascript:;">添加</a></td></tr>');
				text.push('<tr class="headRow">');
				for(var i=0;i<num;i++){
					text.push('<th>列'+(i+1)+'</th>');
				}
				text.push('</tr><tr class="listRow" formtype="edit">');
				for(var i=0;i<num;i++){
					text.push('<td></td>');
				}
				text.push('</tr></table>');
				break;
			case 1:
				//块模式
				text.push('<div class="subTableToolBar l-tab-links"><a class="link add">添加</a></div><div formtype="edit"><table class="blocktable"><tr>');
				for(var i=0;i<num;i++){
					text.push('<th style="width:'+(100*0.3)/num+'%;">列'+(i+1)+'</th><td style="width:'+(100*0.7)/num+'%;"></td>');
				}
				text.push('</tr></table></div>');
				break;
			case 2:
				//窗口模式
				text.push('<table class="listTable" border="0" cellpadding="2" cellspacing="0"><tbody>');
				text.push('<tr class="toolBar"><td colspan="'+num+'"><a class="link add" href="javascript:;">添加</a></td></tr>');
				text.push('<tr class="headRow">');
				for(var i=0;i<num;i++){
					text.push('<th>列'+(i+1)+'</th>');
				}
				text.push('</tr><tr class="listRow" formtype="form">');
				for(var i=0;i<num;i++){
					text.push('<td></td>');
				}
				text.push('</tr></table><div formtype="window"><table class="window-table">');
				for(var i=0;i<num;i++){
					text.push('<tr><th>列'+(i+1)+'</th><td></td></tr>');
				}
				text.push('</table></div>');
				break;
			case 3:
				//(序号)行模式
				var colNum=Number(num)+1;
				text.push('<table class="listTable" border="0" cellpadding="2" cellspacing="0"><tbody>');
				text.push('<tr class="toolBar"><td colspan="'+colNum+'"><a class="link add" href="javascript:;">添加</a></td></tr>');
				text.push('<tr class="headRow">');
				text.push('<th>序号</th>');
				for(var i=0;i<num;i++){
					text.push('<th>列'+(i+1)+'</th>');
				}
				text.push('</tr><tr class="listRow" formtype="edit">');
				text.push('<td class="tdNo"></td>');
				for(var i=0;i<num;i++){
					text.push('<td></td>');
				}
				text.push('</tr></table>');
				break;
			case 4:
				//(序号)窗口模式
				var colNum=Number(num)+1;
				text.push('<table class="listTable" border="0" cellpadding="2" cellspacing="0"><tbody>');
				text.push('<tr class="toolBar"><td colspan="'+colNum+'"><a class="link add" href="javascript:;">添加</a></td></tr>');
				text.push('<tr class="headRow">');
				text.push('<th>序号</th>');
				for(var i=0;i<num;i++){
					text.push('<th>列'+(i+1)+'</th>');
				}
				text.push('</tr><tr class="listRow" formtype="form">');
				text.push('<td class="tdNo"></td>');
				for(var i=0;i<num;i++){
					text.push('<td></td>');
				}
				text.push('</tr></table><div formtype="window"><table class="window-table">');
				for(var i=0;i<num;i++){
					text.push('<tr><th>列'+(i+1)+'</th><td></td></tr>');
				}
				text.push('</table></div>');
				break;
		}
		return text.join('');		
	};
	//ckeditor编辑器
	me.commands['ckeditor'] = {			
			execCommand : function(cmdName,data,old) {
				if(data){
					if(old){
						old.setAttribute("external",getExternal(data));
					}
					else{						
						insertControl(cmdName,data,'<textarea></textarea>');
					}
				}
				else{
					dataType=[
					          {name:'文字',value:'varchar',display:0},
					          {name:'数字',value:'number',display:0},
					          {name:'日期',value:'date',display:0},
					          {name:'大文本',value:'clob',display:1}
					          ];
			      valueFrom=[
							{name:'表单输入',value:'0',display:1},
							{name:'脚本运算(显示)',value:'1',display:0},
							{name:'脚本运算(不显示)',value:'2',display:0},
							{name:'流水号',value:'3',display:0}
			             ];
					me.curInputHtml=null;
					var html=getStyleHtml();
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return this.highlight ? -1 : 0;
			}
	};
	
	
	
	//图片展示控件
	me.commands['pictureshowcontrol'] = {
			execCommand : function(cmdName,data,old) {
				if(data){
					if(old){
						var previewstyle=setStyle(data,cmdName);
						old.firstElementChild.setAttribute("style",previewstyle);			
						old.setAttribute("external",getExternal(data));
					}
					else{
						var previewstyle='style="'+setStyle(data,cmdName)+'"';
						
						insertControl(cmdName,data,'<input type="text" '+previewstyle+' />');
					}
				}
				else{					
					dataType=[
					          {name:'文字',value:'varchar',display:1},
					          {name:'数字',value:'number',display:0},
					          {name:'日期',value:'date',display:0},
					          {name:'大文本',value:'clob',display:0}
				          ];
					valueFrom=[
								{name:'表单输入',value:'0',display:1},
								{name:'脚本运算(显示)',value:'1',display:0},
								{name:'脚本运算(不显示)',value:'2',display:0},
								{name:'流水号',value:'3',display:0}
				             ];					
					me.curInputHtml=null;
					var html=getStyleHtml();
					me.curInputHtml=getContentHtml(html);
					initDialog(cmdName);
				}
			},
			queryCommandState : function() {
				return isInSubTable() ? -1 : 0;
			}
	};
	
	
	me.commands['processinstance'] = {
		execCommand : function(cmdName,data,old) {
			if(data){					
				if(old){
					var previewstyle=setStyle(data,cmdName);
					old.setAttribute("external",getExternal(data));
					old.firstElementChild.setAttribute("style",previewstyle);
				}
				else{
					var previewstyle='style="'+setStyle(data,cmdName)+' "  ';
					insertControl(cmdName,data,'<input type="text" readonly '+previewstyle+' ctlType="selector" class="actInsts"/>');
				}
			}
			else{	
				dataType=[
		            {name:'文字',value:'varchar',display:1}
	            ];
				valueFrom=[
					{name:'表单输入',value:'0',display:1},
					{name:'脚本运算(显示)',value:'1',display:1},
					{name:'脚本运算(不显示)',value:'2',display:1}
	            ];
				me.curInputHtml=null;
				var html=getStyleHtml();
				me.curInputHtml=getContentHtml(html);
				initDialog(cmdName);
			}				
		},
	
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};

	/**
	 * 检查当前位置是否在子表中
	 */
	function isInSubTable(){
		var start = me.selection.getStartElementPath();
		for(var i=0,tag;tag=start[i++];){
			if(tag.className=='subtable'){
				return true;
			}
		}
		return false;
	}

	function initDialog(t){
		me.curInputType = t;
		var title = editor.options.labelMap[t] || editor.getLang("labelMap." + t) || '';
        className="edui-for-inputeditdialog edui-for-" + t;
		if(!me.ui._dialogs['inputeditdialog']){
			iframeUrl ="~/dialogs/extend/input/edit.jsp";
			var dialog = new baidu.editor.ui.Dialog( utils.extend({
               iframeUrl: me.ui.mapUrl(iframeUrl),
               editor: me,
               className: 'edui-for-inputeditdialog',
               title: title
           },{
               buttons: [{
                   className: 'edui-okbutton',
                   label: '确认',
                   onclick: function (){
                       dialog.close(true);
                   }
               }, {
                   className: 'edui-cancelbutton',
                   label: '取消',
                   onclick: function (){
                       dialog.close(false);
                   }
               }]
           }));
			me.ui._dialogs["inputeditdialog"] = dialog;
			dialog.render();			
		}				
		me.ui._dialogs['inputeditdialog'].title = title;
		me.ui._dialogs['inputeditdialog'].className = className;
		me.ui._dialogs['inputeditdialog'].clearContent();
		me.ui._dialogs['inputeditdialog'].open();
	};
	/**
	 * 添加标签
	 * @param cmdName 
	 * @param data 编辑界面提交的数据
	 * @param text span标签所包含的内容
	 */	 
	function insertControl(cmdName,data,text){
		alert(data);
		var html=['<span name="editable-input" style="display:inline-block;padding:2px;" class="'+cmdName+'" external="'];					
		html.push(getExternal(data));
		html.push('">');
		html.push(text);
		html.push('</span>');
		var child = utils.parseDomByString(html.join(''));
		var start = me.selection.getStart();
		if(!start||!child)return;
		if(start.tagName=='TD'){
			if(start.children.length==1&&start.children[0].tagName=="BR"){
				start.innerHTML = '';
			}
			start.appendChild(child);
		}
		else{
			start = domUtils.findEditableInput(start);
			utils.insertAfter(child, start);
		}
		me.execCommand('insertHtml',html.join(''));
	};
	//构建external json字符
	function getExternal(d){
		if(me.curInputElement)
			me.curInputElement=null;
		var external={};
		for(var i=0,c;c=d[i++];){
			if(c.prenode){
				if(typeof external[c.prenode] == 'undefined')
					external[c.prenode]={};
				(external[c.prenode])[c.id]=c.val;
			}
			else{
				external[c.id]=c.val;
			}
		}
		external = JSON.stringify(external);
		return htmlEncode(external);
	};
	//数据类型下拉框
	
	function getDbtypeHtml(){
		
		var html=['<select eid="type" prenode="dbType">'];
		for(var i=0,c;c=dataType[i++];){
			if(c.display)
				html.push('<option value="'+c.value+'">'+c.name+'</option>');
			else
				c.display = 1;
		}
		html.push('</select>');
	
	
		//var html = '';
		return html.join('');
	};
	
	
	//处理单引号和双引号
	function htmlEncode(str) {
		return str.replace(/\"/g, "&#39;").replace(/\'/g, "&#39;").replace(/\n/g,'#newline#');
	};	
	
	//值来源下拉框
	function getValuefromHtml(){
		var html=['<select eid="value" prenode="valueFrom">'];
		for(var i=0,c;c=valueFrom[i++];){
			if(c.display)
				html.push('<option value="'+c.value+'">'+c.name+'</option>');
			else
				c.display = 1;
		}
		html.push('</select>');
		return html.join('');
	};	
	
	function getStyleHtml(){		
		//var	 html=	'<tr class="style_tr"><th>控件宽度:</th><td><input type="text" eid="width"  style="width:120px; "/><select style="width:40px;" eid="widthUnit"><option value="px">px</option><option value="%">%</option></select></td><th>控件高度:</th><td><input type="text" eid="height" style="width:120px; "/><select style="width:40px;" eid="heightUnit"><option value="px">px</option><option value="%">%</option></select></tr>';
		var html = '';
		return html;
	};
	
	
	
	
	//检查样式，目前针对的控件的高宽
	function setStyle(data,cmdName){
		var tmpArry=new Array('width','widthUnit','height','heightUnit');
		var tmpJon={};
		var width="150px",height="18px";    //编辑区域时的默认高宽时
		
		if(cmdName=="pictureshowcontrol"){       //是图片控件时默认一个大小
			width="420px",height="350px";    //编辑区域时的默认高宽时
		}
		
		if(cmdName=="officecontrol"){       //是office控件时默认一个大小
			width="700px",height="700px";    //编辑区域时的默认高宽时
		}
		
		for(var i=0;i<data.length;i++){
			for(index in tmpArry){
				if(data[i].id==tmpArry[index]){
					tmpJon[tmpArry[index]]=data[i].val;
					delete current;
					break;
				}
			}
		}
		if(tmpJon['width']){
			width=tmpJon['width']+tmpJon['widthUnit'];
		}
		if(cmdName!="dictionary"){        //数据字典高度 永远是默认的 ，数据字典中 高度改变的是预览或者实际页面时下拉区域的高度
			if(tmpJon['height']){
				height=tmpJon['height']+tmpJon['heightUnit'];
			}	
		}	
		return 'width:'+width+';height:'+height+';';
	};
	
	
	//检查样式    目前针对 实际时展示样式的控件的高宽 进行默认修改
	function changeStyle(data,cmdName){
		if(cmdName=="dictionary"){             
			var wMark = true;
			var hMark = true;
			for(var i=0;i<data.length;i++){
				if(data[i].id=="width" && data[i].val!=""){      //有定义的宽度了
					wMark = false;
					continue;
				}else if(data[i].id=="height" && data[i].val!=""){  //有定义的高度了
					hMark = false;
					continue;
				}				
				if(data[i].id=="width"||data[i].id=="widthUnit"){   //没有定义宽度时，就把值为空的对象给删除
					if(wMark){
						data.splice(i,1);         //从i的位置开始向后删除1个元素;
						i-1;
					}					
				}else if(data[i].id=="height"||data[i].id=="heightUnit"){ //没有定义高度时，就把值为空的对象给删除
					if(hMark){
						data.splice(i,1);         //从i的位置开始向后删除1个元素;
						i-1;
					}	
				}				
			}
			if(wMark){            //没有设定宽度时 ，就改变实际展示的默认大小
				var widthObj={"id":"width","val":"150"};   //实际时的默认宽时
				var widthUnitObj={"id":"widthUnit","val":"px"};   //实际时的默认宽单位时
				data.push(widthObj);
				data.push(widthUnitObj);
			}
			if(hMark){            //没有设定高度时 ，就改变实际展示的默认大小
				var heightObj={"id":"height","val":"200"};   //实际时的默认高时
				var heightUnitObj={"id":"heightUnit","val":"px"};   //实际时的默认高单位时
				data.push(heightObj);
				data.push(heightUnitObj);
			}
		}		
		return data;
	};
	
	//构建html内容
	function getContentHtml(html){
		var content=['<table class="edit_table"><tr><th>字段注释:</th><td><input eid="comment" type="text" validate="{required:true}"  onblur="getKeyName(this)"/></td><th>字段名称:</th><td><input id="name" eid="name" type="text" validate="{required:true,varirule:true}"/></td></tr>'];
		//content.push(getDbtypeHtml());
		//<tr><th>数据类型:</th><td>
		//content.push('</td><th>数据格式:</th><td class="dbformat_td"></td></tr>');
		//content.push('<tr class="style_tr hidden"><th>控件宽度:</th><td><input type="text" eid="width"  style="width:120px; "/><select style="width:40px;" eid="widthUnit"><option value="px">px</option><option value="%">%</option></select></td><th>控件高度:</th><td><input type="text" eid="height" style="width:120px; "/><select style="width:40px;" eid="heightUnit"><option value="px">px</option><option value="%">%</option></select></tr>');
		///content.push('<tr><th>选项:</th><td colspan="4"><label for="isRequired"><input type="checkbox" id="isRequired" eid="isRequired"/>必填&nbsp;</label><label name="Listlabel" for="isList"><input type="checkbox" id="isList" eid="isList"/>显示到列表&nbsp;</label><label name="QueryLabel" for="isQuery"><input type="checkbox" id="isQuery" eid="isQuery"/>作为查询条件&nbsp;</label><label for="isFlowVar"><input type="checkbox" id="isFlowVar" eid="isFlowVar"/>是否流程变量&nbsp;</label><label for="isReference"><input type="checkbox" id="isReference" eid="isReference"/>作为超连接&nbsp;</label><label for="isWebSign"><input type="checkbox" id="isWebSign" eid="isWebSign"/>是否支持Web印章验证</label></td></tr>');
		//content.push('<tr class="condition_tr hidden"><th>查询条件:</th><td colspan="3"><table class="edit_table"><tbody><tr><th>条件:</th><td><select prenode="search" eid="condition"><option value="等于">等于</option><option value="LIKE">LIKE</option><option value="LIKEEND">LIKEEND</option></select></td></tr><tr><th>值来源:</th><td><select prenode="search" eid="searchFrom"><option value="fromForm">表单输入</option><option value="fromStatic">固定值</option><option value="fromScript">脚本</option></select></td></tr><tr class="searchValue-td hidden"><th>值:</th><td><textarea prenode="search" eid="searchValue" cols="40" rows="3"></textarea></td></tr></tbody></table></td>');
		//content.push('<tr class=" defaultValue_tr"><th>日期相关:</th><td colspan="3"><label for="isDateString"><input type="checkbox" id="isDateString" eid="isDateString" />日期字符</label><span class="hidden"> 格式: <select eid="dateStrFormat" id="dateStrFormat"  prenode="dbType"><option value="yyyy-MM-dd">yyyy-MM-dd</option><option value="yyyy-MM-dd HH:mm:ss">yyyy-MM-dd HH:mm:ss</option><option value="yyyy-MM-dd HH:mm:00">yyyy-MM-dd HH:mm:00</option><option value="HH:mm:ss">HH:mm:ss</option></select> <label for="isCurrentDateStr" class="currentDateStr_label "><input type="checkbox" id="displayDate" eid="displayDate"  prenode="dbType"/>当前日期字符</label>    </span></td></tr>');
		//content.push('<tr><th>值来源:</th><td  colspan="3" >');
		//content.push(getValuefromHtml());
		content.push('</td></tr>');
		//content.push('<tr class="valuefrom0"><th>验证规则:</th><td  colspan="3">#validrule#</td></tr>');
		//content.push('<tr class="valuefrom12 hidden"><th>脚本(显示):</th><td colspan="3"><div><a href="javascript:;" onclick="addScript(this)" class="link var" title="常用脚本">常用脚本</a></div><textarea eid="content" prenode="valueFrom" cols="50" rows="5"></textarea></td></tr>');
		//content.push('<tr class="trScriptID hidden"><th>ID脚本(不显示):</th><td colspan="3"><div><a href="javascript:;" onclick="addScript(this)" class="link var" title="常用脚本">常用脚本</a></div><textarea eid="scriptID" prenode="valueFrom" cols="50" rows="5"></textarea></td></tr>');
		//content.push('<tr class="valuefrom3 hidden"><th>流水号:</th><td colspan="3">#serialnum#</td></tr>');
		content.push(html);
		//content.push('</table><script type="text/javascript">initComplete();</script>');
		return content.join('');
	};	
};