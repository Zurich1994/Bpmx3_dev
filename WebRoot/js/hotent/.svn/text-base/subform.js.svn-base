$(function(){
	var subform={
			tables:{},
			memu:null,
			//初始化页面
			init:function(){
				//初始化select checkbox radio
				this.initMulValCtrl();
				
				var self=this;
				$("[type='subtable']").each(function(){
					
					var table=$(this);
					
					var formType=$(table).attr("formtype");
					//添加一行数据
					var row=$(table).find("[type='append']");
					
					//子表Id
					var tableId=$(table).attr('id');
					//弹框模式
					if(formType=="form"){
						isPage=false;
						var id=this.id+"Form",
							form=$("#"+id),
							width=form.attr('width')?form.attr('width'):form.width()+80,
							height=form.attr('height')?form.attr('height'):form.height()+70,
							title=form.attr('title')?form.attr('title'):''; 
						
						$(table).data('form', '<form>' + $('<div></div>').append(form.clone()).html() + '</form>');
						$(table).data("formProperty",{title:title,width:width ,height: height});
						form.remove();
					}
					//页内编辑模式
					if(formType="page"){
						
					}
					$(this).data('row',$('<div></div>').append(row.clone()).html());
					
					self.addBind($(this));
					row.css('display', 'none').html('');
					self.tables[tableId]=$(table);
					//self.handButton($(this));
				});
			},
			/**
			 * 处理页面多值的控件类型 如select checkbox radio,把字段信息放到那些组件的data属性中
			 * eg:<select name="m:zxdmsckjcs:zbzdw" data="${zxdmsckjcs.zbzdw}"><option value="1">苹果</option> <option value="2">HTC</option> </select>
			 */
			initMulValCtrl:function(){
				//处理select
				$("select[data]").each(function(){
					var data = $(this).attr("data");
					$(this).val(data);
				});
				//处理checkbox
				$("input[type=checkbox][data]").each(function(){
					var data = $(this).attr("data");
					var strs=data.split(",");//解释数据
					for(i=0;str=strs[i];i++){
						if(str==$(this).val()){//数据中有相等value
							$(this).attr("checked","true");
						}
					}
				});
				//处理radio
				$("input[type=radio][data]").each(function(){
					var data = $(this).attr("data");
					if(data==$(this).val()){
						$(this).attr("checked","true");
					}
				});
			},
			/**
			 * 添加行数据的右键事件绑定。
			 * @param row
			 * @param table
			 */
			addBind: function( table) {
				var self=this;
				//是否需要编辑菜单项目
				//如果该表弹出窗口定义，那么就不需要编辑菜单项目。
				var needEdit = (table.data('form') != null);
				var menu = self.getMenu(needEdit);
				$('[type="subdata"]').live("contextmenu", function(e) {
					menu.target = e.target;
					menu.show({top : e.pageY,left : e.pageX});
					return false;
				});
				$(".add",table).click(function(){
					self.add(table,null);
				});
			},
			
			//添加一行记录  包括 弹框 和 页内两种编辑模式
			add:function(table,row){	
				var self=this, f=table.data("form");
				//弹框模式
				if(f){
					var form=$(f).clone(),formProperty = table.data("formProperty");
					$(form).find("table,div").show();
					var frm=$(form).form({errorPlacement:function(element, msg){
						$(element).parent().append($('<label class="initerror" >*</label>'));
					}});
					this.openWin({
						title:'添加'+formProperty.title,
						width:formProperty.width,
						height:formProperty.height, 
						form:form,
						callback:function(){
							if(frm.valid()){
								var data=self.getFormData(form);
								var showData=self.getFormShowData(form);
								self.addRow(data,showData,table);
							}
						}
					});
				//页内编辑模式
				}else{
					self._addRow(table,row);
				}
			},
			
			// 弹框模式 根据表单数据 增加一行
			addRow:function(data,showData,table){
				var row=table.data('row');
				var tr=$(row).clone();
				tr.attr('type','subdata');
				tr.removeAttr('style');
				for(var name in data){
					tr.find("td,input:hidden").each(function(){
						if($(this).is('td')){
							var tdname=$(this).attr('name');
							if(name==tdname){
								$(this).text(showData[name]);
							}
						}
						if($(this).is('input:hidden')){
							var inputname=$(this).attr('name');
							if(name==inputname){
								$(this).val(data[name]);
							}
						}
					});
				}
				var rows=table.find("[type='subdata']:visible");
				if(rows.length!=0){
					$(rows.get(rows.length-1)).after(tr);
				}else{
					$(table.find("tbody")[0]).append(tr);
				}
				//编号
				tr.find(".tdNo").text(rows.length+1);
				
				$.ligerDialog.hide();
			},
			
			/**
			 * 获取右键菜单。
			 * @param needEdit
			 * @returns
			 */
			getMenu : function(needEdit) {
				var self=this;
				
				var menu;
				var items = [ {
					text : '在前面插入记录',
					click : function() {
						var row = $(menu.target).closest('[type="subdata"]');
						var table = row.closest('[type=subtable]');
						self.add(table, row);
					}
				}, {
					text : '在后面插入记录',
					click : function() {
						var row = $(menu.target).closest('[type="subdata"]');
						var table = row.closest('[type=subtable]');
						row = row.next('[type="subdata"]:visible');
						if (row.length == 0) {
							row = null;
						}
						self.add(table, row);
					}
				}, {
					line : true
				}, {
					text : '编辑',
					click : function() {
						var row = $(menu.target).closest('[type="subdata"]');
						var table = row.closest('[type=subtable]');
						self.edit(table, row);
					}
				}, {
					text : '删除此记录',
					click : function() {
						var t = $(menu.target).closest('[type="subdata"]'),
							brother = t.next('[type="subdata"]').length?t.next('[type="subdata"]'):t.prev('[type="subdata"]');
						t.remove();
						if(brother)
							FormUtil.InitMathfunction(brother);
					}
				}, {
					line : true
				}, {
					text : '向上移动',
					click : function() {
						var t = $(menu.target).closest('[type="subdata"]');
						var prev = t.prev('[type="subdata"]:visible');
						if (prev.length > 0) {
							prev.before(t);
						}
					}
				}, {
					text : '向下移动',
					click : function() {
						var t = $(menu.target).closest('[type="subdata"]');
						var next = t.next('[type="subdata"]:visible');
						if (next.length > 0) {
							next.after(t);
						}
					}
				} ];
					//如果不需要编辑，删除编辑菜单。
					if (!needEdit) {
						items.splice(3, 1);
					}
					menu = $.ligerMenu({top : 100,left : 100,width : 130,items : items});
					if (needEdit) {
						this.menuWithEdit = menu;
					}
					else{
						this.menu = menu;
					}
					return menu;
			},
			
			// 弹框模式 触发 编辑点击事件
			edit:function(table,row){
				var self=this, form=self.setFormData(row,table),formProperty = table.data("formProperty");
				$(form).find("table,div").show();
				this.openWin({
					title:'编辑'+formProperty.title,
					width:formProperty.width,
					height:formProperty.height, 
					form:form,
					callback:function(){
						var frm=$(form).form();
						if(frm.valid()){
							self.editRow(row, form);
						}
					}
				});
			},
			
			//页内编辑模式 \块模式 添加一行
			_addRow:function (table,row){
				var appendRow=$(table.data('row')).clone();
				appendRow.attr('type','subdata');
				appendRow.removeAttr('style');
				
				if(row){
					row.before(appendRow);
				}else{
					var rows=table.find("[type='subdata']:visible");
					if(rows.length!=0){
						$(rows.get(rows.length-1)).after(appendRow);
					}else{
						if($(appendRow).is('div')){
							$(table).append(appendRow);
						}else{
							$($(table).find('tbody')).append(appendRow);
						}
					}
					
					try{
						//编号
						appendRow.find(".tdNo").text(rows.length+1);
					}catch(e){
						
					}
				}
				
				//初始化数据字典
				appendRow.find('.dicComboBox,.dicComboTree,.dicCombo').each(function() {				
					$(this).htDicCombo();
				});
				
				//添加选择器
				appendRow.find('[ctlType="selector"]').each(function() {				
					var type = $(this).attr('class');
					buildSelector($(this), type);
				});
				
				this.initSubQuery();
			},
			
			//初始化子表级联
			initSubQuery:function(){
			  	$("select[selectquery]",$("[type=subtable],[formtype='window']")).each(function(){
				  	var me = $(this);
				  	var selected=me.attr("selectqueryed");
				  	//selectqueryed标识这个子表是否已经执行过了change绑定，存在就是已经绑定了，不存在就是没用被绑定过
				  	if(selected)return true;
					var selectquery = me.attr("selectquery");
					if (!selectquery)
						return true;
					selectquery = selectquery.replaceAll("'", "\"");
					var queryJson = JSON2.parse(selectquery);
					var query = queryJson.query;
					for (var i = 0; i < query.length; i++) {
						var field;
						// isMain是true 就是绑定主表的字段
						if (query[i].isMain=="true") {
							field = $(".formTable [name$=':" + query[i].trigger + "']");
						} else {
							field = me.parents('table.listTable,[formtype="window"]').find("[name='" + query[i].trigger + "']");
							//添加新一个子表时，执行了多次绑定change事件，把之前的change事件都解绑
							field.unbind("change");
						}
						if (field != "")
							QueryUI.change(field, me, queryJson);
					}
					//添加一个属性，标识已经被绑定过，
					me.attr("selectqueryed","selectqueryed");
					QueryUI.getvalue(me, queryJson);
			  	});
			},
			
			//删除一行
			delRow:function(){
				$.ligerMessageBox.confirm('提示信息','确认删除吗？',function(rtn) {
					if(rtn) {
						$('.pk:checked').each(function(){
							$(this).closest('tr').remove();
						});
					}
				});
				
			},
			//弹框模式 根据表单数据 编辑对应的行数据
			editRow:function(obj,form){
				var self=this;
				var data=self.getFormData(form);
				var showData = self.getFormShowData(form);
				for(var name in data){
					$(obj).find("td,input:hidden").each(function(){
						if($(this).is('td')){
							var tdname=$(this).attr('name');
							if(name==tdname){
								$(this).text(showData[name]);
							}
						}
						if($(this).is('input:hidden')){
							var inputname=$(this).attr('name');
							if(name==inputname){
								$(this).val(data[name]);
							}
						}
					});
				}
				$.ligerDialog.hide();
			},
			
			/**
			 * 打开操作数据窗口。
			 * @param conf 窗口配置参数
			 * <pre>
			 * 	conf.title {string}(可选)窗口标题 默认‘编辑’ 
			 * 	conf.width  {number}(可选)窗口宽度 默认400
			 * 	conf.height {number}(可选)窗口高度 默认250
			 * 	conf.form	 {Object}当前窗口对象
			 * 	conf.callback {Object}(必须) 回调函数
			 * </pre>
			 */
			openWin:function(conf){	
				$.ligerDialog.open({ 
					width: conf.width?conf.width:400,
					height:conf.height?conf.height:250,
					title: conf.title?conf.title:'编辑', 
					isResize:true,
					showMax : true,
					showToggle : true,
					onClose : true,
					target:conf.form,
					buttons: [{ text: '确定', onclick:function(item,dialog){
						conf.callback(item,dialog);
					}}]
				});
			},
			
			//回填数据至表单
			setFormData:function(obj,table){
				var form=$(table.data('form')).clone().show();
				var json={};
				//根据当前行 取得数据
				
				$("input:hidden",obj).each(function(){
						var value=$(this).val().trim();
						var name=$(this).attr('name');
						json[name]=value;
				});
				
				for(var name in json){
					form.find('input:text,textarea,select').each(function() {
						var attrname=$(this).attr('name');
						if(name==attrname){
							if($(this).is('select')){
								$(this).find('option').each(function(){
									if($(this).val()==json[name]){
										$(this).attr('selected','selected');
									}
								});
							}else{
								$(this).val(json[name]);
							}
						}
					});
					form.find('input:checkbox,input:radio').each(function(){
						var attrname=$(this).attr("name");
						var value = '';
						if(name==attrname){
							value=json[name];
						}
						var ary=value.split(',');
						$(this).find('[name='+name+']').each(function(){
							for(var i=0;i<ary.length;i++){
								var v=ary[i];
								if($(this).val()==v){
									$(this).attr("checked","checked");
								}
							}
						});
							
					});
				}
				return form;
			},
			
			/**
			 * 取得表单中的数据
			 */
			getFormData:function(form){
				var data={};
				$(form).find('input:text,textarea,select').each(function() {
					var name=$(this).attr('name');
					var value=$(this).val();
					data[name]=value;
				});
				$(form).find(":checkbox,:radio").each(function(){
					var name=$(this).attr('name');
					var value="";
					$(":checked[name="+name+"]").each(function(){
						if(value==""){
							value=$(this).val();
						}else{
							value+=","+$(this).val();
						}
					});
					data[name]=value;
				});
				return data;
			},
			/**
			 * 取得表单中的展示的数据
			 */
			getFormShowData:function(form){
				var data={};
				$(form).find('input:text,textarea').each(function() {
					var name=$(this).attr('name');
					var value=$(this).val();
					data[name]=value;
				});
				$(form).find('select').each(function() {
						var me = $(this);
						var name= me.attr('name');
						var value= me.find("option:selected").text(); 
						data[name]=value;
				});
				
				$(form).find(":checkbox,:radio").each(function(){
					var name=$(this).attr('name');
					var value="";
					$(":checked[name="+name+"]").each(function(){
						if(value==""){
							value=$(this).val();
						}else{
							value+=","+$(this).val();
						}
					});
					data[name]=value;
				});
				return data;
			}
			
	};
	subform.init();
});
