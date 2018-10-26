/**
 * @author Raise
 */

NtkoSignManage = function() {
	this.ntkoSignObj;
	/**
	 * 加载NTKO电子印章控件
	 * arg targetId 加载控件存放的目标Dom元素。 
	 * arg attachmentId NTKO电子印章文件的ID。如果不为空，加载对应的文件
	 */
	this.load = function(targetId,attachmentId) {
		var cabPath=__ctx +"/media/office/ntkosigntool.cab#version=4,0,0,2";
		var tags="";
		tags+='<object id="ntkosignctl" classid="clsid:97D0031E-4C58-4bc7-A9BA-872D5D572896" codebase="'+cabPath+'"    ';
		tags+='  width=450 height=100>   ';
		tags+='<param name="BackColor" value="16744576">   ';
		tags+='<param name="ForeColor" value="16777215">   ';
		tags+='<param name="IsShowStatus" value="-1">   ';
		tags+='<param name="EkeyType" value="1">   '; // HT
		tags+='<SPAN STYLE="color:red">不能装载印章管理控件，必须使用IE内核浏览器。可能需要在浏览器的Internet选项安全设置中修改ActiveX配置。按提示加载控件。</SPAN>   ';
		tags+='</object>   ';

		$('<div style="display: none;" id="divntko"></div>').prependTo('body');
		$('#'+targetId).append(tags);
		this.ntkoSignObj = document.getElementById("ntkosignctl");
		if('undefined' == typeof (this.ntkoSignObj.StatusCode)){
			$.ligerDialog.warn("不能装载印章管理控件，必须使用IE内核浏览器。可能需要在浏览器的Internet选项安全设置中修改ActiveX配置。",'提示信息',function(){
				window.history.back();
			});
			return false;
		}
		
		if((attachmentId!="")||('undefined' == typeof (attachmentId))){
			this.openFormURL(attachmentId);
			if (0 != this.ntkoSignObj.StatusCode) {
				window.history.back();
			}
		}
		
	};
	
	/**
	 * 获取ntkoSignObj，代表当前的电子印章对象
	 */
	this.getntkoSignObject = function() {
		this.ntkoSignObj.IsShowRect = false;
		return this.ntkoSignObj;
	};
	
	/**
	 * 从指定的文件ID，打开电子印章。
	 */
	this.openFormURL = function(fileId) {
		var url=__ctx + "/platform/system/sysFile/getFileById.ht?fileId=" + fileId;
		this.ntkoSignObj.OpenFromURL(url);
		if (0 != this.ntkoSignObj.StatusCode) {
			alert("打开印章出错！");
		}
	};
	
	
	/**
	 * 从本地打开或导入电子印章。
	 */
	this.openFromLocal = function() {
		this.ntkoSignObj.OpenFromLocal('',true);
	};
	
	
	/**
	 * 从导出到本地电子印章。
	 */
	this.saveToLocal = function() {
		var spassowrd = this.ntkoSignObj.Password;
		if('undefined'==typeof (spassowrd)||spassowrd==null||spassowrd==""){
			alert("没有印章！");
			return false;
		}
		this.ntkoSignObj.SaveToLocal('',true);
		if(0 == this.ntkoSignObj.StatusCode){
			alert("保存印章到本地文件成功！");
		}else{
			alert("保存印章到本地文件失败！");
		}
	};

	
	/**
	 * 创建新的电子印章
	 */
	this.newSign = function() {
		this.ntkoSignObj.IsShowRect = false;
		var url=__ctx+ "/platform/system/seal/addSign.ht";
		var winArgs="dialogWidth=600px;dialogHeight=400px;help=0;status=0;scroll=0;center=1";
		url=url.getNewUrl();
		var params={
		};
		
		/*KILLDIALOG*/
		/*var rtn = window.showModalDialog(url,params,winArgs);
		if(!(rtn&&rtn.status)){
			return false;
		}
		this.ntkoSignObj.CreateNew(rtn.signname,rtn.username,rtn.password,rtn.filename);
		if (0 != this.ntkoSignObj.StatusCode) {
			return -1;
		}else {
			return rtn.showImageId;
		}
		*/
		var that =this;
		//TODO 是错的- -应该把调用这个方法的上一级也以回调方法那样调回去……
		DialogUtil.open({
			height:400,
			width: 600,
			title : '电子印章',
			url: url, 
			isResize: true,
			//自定义参数
			params: params,
			sucCall:function(rtn){
				that.ntkoSignObj.CreateNew(rtn.signname,rtn.username,rtn.password,rtn.filename);
			}
		});
	};
	
	/**
	 * 将电子印章保存到服务器上。
	 */
	this.saveSign=function(){
		var sname = this.ntkoSignObj.SignSN;
		if('undefined'==typeof (sname)||sname==null||sname==""){
			sname = "导入:"+this.ntkoSignObj.SignName;
		}
		var path= __ctx + "/platform/system/sysFile/saveFile.ht";
		var result = this.ntkoSignObj.SaveToURL(path,"EspFileName","file_cat=sealSign&uploadName=EspFileName",sname+'.esp',0);
		if (0 != this.ntkoSignObj.StatusCode) {
            alert("创建印章错误.");
            return false;
        }
		if(result==-1){
			return false;
		}
		return result;
	};
	
	
	/**
	 * 创建并保存电子印章。
	 */
	this.createSign=function(name, user, password, file){
		var ntkoSignObj = this.getntkoSignObject();
		ntkoSignObj.IsShowRect = false;
		ntkoSignObj.CreateNew(name, user, password, file);
		if (0 != ntkoSignObj.StatusCode) {
             alert("创建印章错误.");
             return false;
		}
		var path= __ctx + "/platform/system/sysFile/saveFile.ht";
		var result = ntkoSignObj.SaveToURL(path,"EspFileName","file_cat=sealSign&uploadName=EspFileName",ntkoSignObj.SignSN+'.esp',0);
		if (0 != ntkoSignObj.StatusCode) {
            alert("创建印章错误.");
            return false;
        }
		return result;
	};
};