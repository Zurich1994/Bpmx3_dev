
/**
 * 任务会签规则设置。
 * conf:参数如下：
 * deployId:流程定义ID
 * actDefId：act流程节点Id
 * nodeId：流程节点Id
 * dialogWidth：窗口宽度，默认值500
 * dialogWidth：窗口宽度，默认值300
 * @param conf
 */

function UserSetWindow(conf)
{	
	var url=__ctx + "/platform/bpm/bpmDefinition/userSet.ht?defId=" + conf.defId +"&nodeId=" + conf.nodeId;
	var winArgs="dialogWidth=800px;dialogHeight=600px;help=0;status=0;scroll=1;center=0;resizable=1;";
	url=url.getNewUrl();
	/*var rtn=window.showModalDialog(url,"",winArgs);
	if (conf.callback) {
		conf.callback.call(this,rtn);
	}*/
	
	/*KILLDIALOG*/
	//TODO jsp没处理
	DialogUtil.open({
		height:600,
		width: 800,
		title : '任务会签规则设置',
		url: url, 
		isResize: true,
		//自定义参数
		sucCall:function(rtn){
			if (conf.callback) {
				conf.callback.call(this,rtn);
			}
		}
	});
}