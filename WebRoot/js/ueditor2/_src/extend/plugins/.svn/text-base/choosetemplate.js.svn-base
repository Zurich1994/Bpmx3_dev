///import core
///import plugins\addinput.js
///commands 添加按钮
///commandsName  addinput
///commandsTitle  添加按钮
/**
 * @description 添加按钮（按钮、单选按钮、复选框、单行文本、多行文本、列表菜单等）
 * @name baidu.editor.execCommand
 * @param {String}
 *            cmdName 添加按钮
 * @author heyifan
 */
UE.plugins['choosetemplate'] = function() {
	var me = this;
	me.commands['choosetemplate'] = {
		execCommand : function(cmdName) {
			//编辑器设计表单模式
			if(typeof FormDef == 'undefined' || typeof tableId  == 'undefined'){
				//重新选择模板（该方法的定义在bpmFormDefDesignEdit.jsp页面）
				showSelectTemplate(__ctx+'/platform/form/bpmFormDef/chooseDesignTemplate.ht?&isSimple=1');	
			}
			else{
				FormDef.showSelectTemplate(__ctx+'/platform/form/bpmFormDef/selectTemplate.ht?tableId=' + tableId +"&isSimple=1");
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};	
};