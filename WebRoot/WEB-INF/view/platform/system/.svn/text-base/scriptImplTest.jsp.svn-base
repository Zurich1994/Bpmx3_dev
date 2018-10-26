<!-- 脚本测试页  -->
<%@page import="com.hotent.core.util.AppUtil"%>
<%@page import="com.hotent.platform.service.bpm.impl.ScriptImpl"%>
<%@page import="com.hotent.core.util.*"%>
<%@page import="com.hotent.platform.dao.system.SysRoleDao"%>
<%@page import="com.hotent.platform.model.system.*"%>
<%@page import="com.hotent.platform.service.system.*"%>
<%@page import="com.hotent.core.db.BaseDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript">
    function validata(){
		alert(111);
			 if($("#receiver_arg").val()==""){
				 alert(222);
				 $.ligerDialog.warn('请输入',"提示信息");
				 return false;}
			 alert(333);
			  return true;
		 
    }
    function argSubmit(){
		 var valRes=validata();
		 if(!valRes) return;
		
	
		 var receiver_arg=$("#receiver_arg").val();
		 //没有控制器，无法向服务器传值
		 var url=__ctx+ "/platform/bpm/impl/getUserRoles.ht";
		 $.post(url,receiver_arg,function(data){

				$.ligerDialog.closeWaitting();
				var obj=new com.hotent.form.ResultMessage(data);
				if(obj.isSuccess()){
					$.ligerDialog.success(obj.getMessage(),"提示信息",function(rtn){
						
					});
				}else{
					$.ligerDialog.err('提示信息',"页面出错了",obj.getMessage());
				}
			 
		 });
    }
	</script>
<%
//根据类名从spring上下文获取bean。
ScriptImpl   si=(ScriptImpl)AppUtil.getBean("scriptImpl");
%>


<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td class="formHead" colspan="5" align="center">ScriptImpl类方法测试</td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" align="center"><b>方法</b></td>
   <td style="width:30%" class="formTitle" align="center"><b>功能</b></td>
   <td style="width:10%" class="formTitle" align="center"><b>参数</b></td>
    <td style="width:30%" class="formTitle" align="center"><b>函数返回</b></td>
   <td style="width:10%" class="formTitle" align="center"><b>测试结果</b></td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" align="right">long getCurrentUserId()</td>
   <td style="width:30%" class="formTitle">取得当前登录用户id:
   <td style="width:10%" class="formTitle" align="right"> &nbsp;</td>
   <td style="width:30%" class="formTitle"><%=si.getCurrentUserId()  %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" align="right">String getCurrentName()</td>
   <td style="width:30%" class="formTitle">取得当前登录用户名称</td>
   <td style="width:10%" class="formTitle" align="right"> &nbsp;</td>
   <td style="width:30%" class="formTitle"><%=si.getCurrentName() %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" align="right">ISysUser getCurrentUser()</td>
   <td style="width:30%" class="formTitle"> 获取当前系统的用户,return	用户对象</td>
   <td style="width:10%" class="formTitle" align="right"> &nbsp;</td>
   <td style="width:30%" class="formTitle"><%=si.getCurrentUser() %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  <tr>
   <td style="width:20%" class="formTitle" align="right">String getCurrentDate()</td>
   <td style="width:30%" class="formTitle">获取当前日期:返回日期类型如下2002-11-06</td>
   <td style="width:10%" class="formTitle" align="right"> &nbsp;</td>
   <td style="width:30%" class="formTitle"><%=si.getCurrentDate() %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
  <tr>
   <td style="width:20%" class="formTitle" align="right">ISysOrg getCurrentOrg()</td>
   <td style="width:30%" class="formTitle">获取当前组织</td>
   <td style="width:10%" class="formTitle" align="right"> &nbsp;</td>
   <td style="width:30%" class="formTitle"><%=si.getCurrentOrg() %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
   <tr>
   <td style="width:20%" class="formTitle" align="right"> Long getCurrentOrgId()</td>
   <td style="width:30%" class="formTitle">获取当前用户的组织ID</td>
   <td style="width:10%" class="formTitle" align="right"> &nbsp;</td>
   <td style="width:30%" class="formTitle"><%=si.getCurrentOrgId() %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
   <tr>
   <td style="width:20%" class="formTitle" align="right"> String getCurrentOrgName()</td>
   <td style="width:30%" class="formTitle">取得当前组织的的名称</td>
   <td style="width:10%" class="formTitle" align="right"> &nbsp;</td>
   <td style="width:30%" class="formTitle"><%=si.getCurrentOrgName() %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
   <tr>
   <td style="width:20%" class="formTitle" align="right">SysOrgType getCurrentOrgType()</td>
   <td style="width:30%" class="formTitle">返回当前组织的类型。</td>
   <td style="width:10%" class="formTitle" align="right"> &nbsp;</td>
   <td style="width:30%" class="formTitle"><%=si.getCurrentOrgType() %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
   <tr>
   <td style="width:20%" class="formTitle" align="right"> String getCurrentOrgTypeName()</td>
   <td style="width:30%" class="formTitle"> 返回当前组织类型的名称。</td>
   <td style="width:10%" class="formTitle" align="right"> &nbsp;</td>
   <td style="width:30%" class="formTitle"><%=si.getCurrentOrgTypeName() %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
   <tr>
   <td style="width:20%" class="formTitle" align="right"> boolean hasRole(String alias)</td>
   <td style="width:30%" class="formTitle">判断当前用户是否属于该角色。alias 角色别名</td>
   <td style="width:10%" class="formTitle" align="right"> bpm_manager</td>
   <td style="width:30%" class="formTitle"><%=si.hasRole("bpm_manager") %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
  
   <tr>
   <td style="width:20%" class="formTitle" align="right"> List&lt;ISysRole&gt; getCurrentUserRoles()</td>
   <td style="width:30%" class="formTitle">获取当前用户所属角色。return		返回角色列表。</td>
   <td style="width:10%" class="formTitle" align="right"> &nbsp;</td>
   <td style="width:30%" class="formTitle"><%=si.getCurrentUserRoles() %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
   <tr>
   <td style="width:20%" class="formTitle" align="right">  List&lt;ISysRole&gt; getUserRoles(String strUserId)</td>
   <td style="width:30%" class="formTitle">获取发起用户所属角色。返回角色列表。</td>
   <td style="width:10%" class="formTitle" align="right"> "1"
   <textarea id="receiver_arg" name="receiver_arg"  rows="2"  style="width:328px !important">
   </textarea>
	<a href="javascript:;" onclick="argSubmit()" class="link get">运行</a>
	</td>
   <td style="width:30%" class="formTitle"><%=si.getUserRoles("1") %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
   <tr>
   <td style="width:20%" class="formTitle" align="right"> boolean isUserInRole(String userId,String role)</td>
   <td style="width:30%" class="formTitle">判断用户是否属于某角色。role	角色别名</td>
   <td style="width:10%" class="formTitle" align="right"> ("1","bpm_manager")</td>
   <td style="width:30%" class="formTitle"><%=si.isUserInRole("1","bpm_manager") %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
  <tr>
   <td style="width:20%" class="formTitle" align="right"> String getUserPos(Long userId)</td>
   <td style="width:30%" class="formTitle">获取流程发起用户的主岗位名称。</td>
   <td style="width:10%" class="formTitle" align="right"> 1L</td>
   <td style="width:30%" class="formTitle"><%=si.getUserPos(1L) %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
  
  <tr>
   <td style="width:20%" class="formTitle" align="right"> Object getParaValue(String paramKey)</td>
   <td style="width:30%" class="formTitle">根据当前用户取得指定参数key的参数值。</td>
   <td style="width:10%" class="formTitle" align="right"> 参数3</td>
   <td style="width:30%" class="formTitle"><%=si.getParaValue("参数3") %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
    <tr>
   <td style="width:20%" class="formTitle" align="right"> Object getParaValueByUser(Long userId,String paramKey)</td>
   <td style="width:30%" class="formTitle">根据用户ID获取参数值。</td>
   <td style="width:10%" class="formTitle" align="right"> 1L,"参数3"</td>
   <td style="width:30%" class="formTitle"><%=si.getParaValueByUser(1L,"参数3") %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
  <tr>
   <td style="width:20%" class="formTitle" align="right"> String getCurUserPos()</td>
   <td style="width:30%" class="formTitle">获取当前用户的主岗位名称。</td>
   <td style="width:10%" class="formTitle" align="right"> &nbsp;</td>
   <td style="width:30%" class="formTitle"><%=si.getCurUserPos() %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
    <tr>
   <td style="width:20%" class="formTitle" align="right">  String getCurDirectLeaderPos()</td>
   <td style="width:30%" class="formTitle">获取流程当前用户直属领导的主岗位名称。</td>
   <td style="width:10%" class="formTitle" align="right"> &nbsp;</td>
   <td style="width:30%" class="formTitle"><%=si.getCurDirectLeaderPos() %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
    <tr>
   <td style="width:20%" class="formTitle" align="right"> String getDirectLeaderPosByUserId(String userId)</td>
   <td style="width:30%" class="formTitle">获取用户的组织的直属领导岗位。1.当前人是普通员工，则获取部门负责人，如果找不到，往上级查询负责人岗位。
	 * 2.当前人员是部门负责人，则获取上级部门负责人，如果找不到则往上级查询负责人岗位。</td>
   <td style="width:10%" class="formTitle" align="right"> 1</td>
   <td style="width:30%" class="formTitle"><%=si.getDirectLeaderPosByUserId("1") %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
    <tr>
   <td style="width:20%" class="formTitle" align="right"> Set&lt;TaskExecutor&gt; getDirectLeaderByUserId(String userId)</td>
   <td style="width:30%" class="formTitle">获取用户的组织的直属领导。</td>
   <td style="width:10%" class="formTitle" align="right"> 1</td>
   <td style="width:30%" class="formTitle"><%=si.getDirectLeaderByUserId("1") %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
    <tr>
   <td style="width:20%" class="formTitle" align="right"> boolean isDepartmentLeader(String userId,String orgId)</td>
   <td style="width:30%" class="formTitle">判断用户是否该部门的负责人</td>
   <td style="width:10%" class="formTitle" align="right"> ("1","10000000290535")</td>
   <td style="width:30%" class="formTitle"><%=si.isDepartmentLeader("1","10000000290535") %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
    <tr>
   <td style="width:20%" class="formTitle" align="right">  Set&lt;String&gt; getMyLeader(Long userId)</td>
   <td style="width:30%" class="formTitle">获取我的领导id集合。</td>
   <td style="width:10%" class="formTitle" align="right"> 1L</td>
   <td style="width:30%" class="formTitle"><%=si.getMyLeader() %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
  
    <tr>
   <td style="width:20%" class="formTitle" align="right"> Set&lt;String&gt; getMyUnderUserId(Long userId)</td>
   <td style="width:30%" class="formTitle">获取我的下属Id用户ID集合。</td>
   <td style="width:10%" class="formTitle" align="right"> 1L</td>
   <td style="width:30%" class="formTitle"><%=si.getMyUnderUserId() %></td>
   <td style="width:10%" class="formTitle" > √</td>
  </tr>
 </tbody>
</table>
</html>