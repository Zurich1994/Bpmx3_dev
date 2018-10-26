/**
 * 当前应用的配置信息
 * 
 * @author zxh
 * @date 2014-04-23
 */
var __version="V0.6.11",
	//访问服务设置的地址
	__ctx="http://192.168.1.222:8080/bpm",
	//__ctx="http://10.0.24.49:8080/ecp",
	//__ctx="https://mecp.st.sf-express.com:443/ecp",
	// 当前语言信息 默认简体中文
	 __lang  = "",
	//每个页面的当前语言
	__pageLang = localStorage.getItem("__pageLang"),
	//当前用户
	__curUserInfo = null,
	__curUserId = null,
	
	__scxs = "",
	__scxmainwindow="",
	//判断是否登录
	__hasLogin = false;


//====访问服务设置的地址
if(localStorage.getItem("__ctx")!=null)
	__ctx =localStorage.getItem("__ctx");

//===== 当前语言信息
if (localStorage.getItem("__lang") != null) 
	__lang = localStorage.getItem("__lang");

//====每个页面的当前语言
if(!__pageLang){
	__pageLang = {};
	localStorage.setItem("__pageLang", JSON.stringify(__pageLang));
}
else{
	__pageLang = JSON.parse(__pageLang);
}

/**
 * 当前用户
 * 
 */
function __getCurUserInfo(){
	var curUserInfo = localStorage.getItem("__curUserInfo");
	if(curUserInfo == null || curUserInfo == ''){
		//返回登录页面
	//	HT.goToHtml("login");
		return ;
	}
	__curUserInfo = JSON.parse(curUserInfo);
	__curUserId = __curUserInfo.userId;
};
//
__getCurUserInfo();
