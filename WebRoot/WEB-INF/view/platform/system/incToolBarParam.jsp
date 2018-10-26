<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="com.hotent.platform.model.system.SysBaseParam,
		com.hotent.core.util.StringUtil,
		java.util.*,
		net.sf.json.JSONObject"
%>
<%
List params = (List) request.getAttribute("paramList");
for(Object obj: params) {
  	SysBaseParam param=(SysBaseParam)obj;
	String sourceType = param.getSourceType();
	String paramValue=param.getParamValue();
	Long paramId=param.getParamId();
	String sourceKey=param.getSourceKey();
%>
<tr>
	<td class="paramWidth" align="center"><%=param.getParamName()%></td>
	<td>
<%
	if (sourceType.equals("input")) {
%>
	<input title='paramValue' <%if(param.getDataType().equals("Date")){ %>class="date" <%}else{%>class="inputText "<%}%>type="text" name="<%=paramId%>" value="<%=paramValue%>">
<%
}
else if (sourceType.equals("radio")) {
		Iterator<?> it = getIterator(sourceKey);
 		while (it.hasNext()) {
			Map.Entry<String, String> mapEntry = (Map.Entry<String, String>) it.next();
 %>
	<input  type="radio" class="inputText " <%if(mapEntry.getKey().equals(paramValue)){ %>checked="checked" <% }%> name="<%=paramId%>" value="<%=mapEntry.getKey()%>"><%=mapEntry.getValue()%>
<%
		}
}
else if (sourceType.equals("select")) {
	Iterator<?> it = getIterator(sourceKey);
    %>
   <select name="<%=paramId %>">
	<%
	while (it.hasNext()) {
		Map.Entry<String, String> mapEntry = (Map.Entry<String, String>) it.next();
   %>
          <option class="inputText " <%if(mapEntry.getKey().equals(paramValue)){ %>selected="selected" <% }%> value="<%=mapEntry.getKey()%>"><%=mapEntry.getValue()%></option>
<%
	}
}
else if (sourceType.equals("checkbox")) {
	List<String> valueList = StringUtil.stringToList(param.getParamValue());
	Iterator<?> it = getIterator(sourceKey);
	while (it.hasNext()) {
		Map.Entry<String, String> mapEntry = (Map.Entry<String, String>) it.next();
    %>
       <input  type="checkbox" <% for(String str:valueList){if(mapEntry.getKey().equals(str.trim())){ %>checked="checked" <% }}%>class="test" name="<%=paramId%>" value="<%=mapEntry.getKey()%>"><%=mapEntry.getValue()%>					      
<%
	}
}
else if (sourceType.equals("dict")) {
 %>
     <div id="dictionary"> 
		<input value="<%=paramValue%>" nodeKey="<%=sourceKey%>" id="dictTypeName" class="dicCombo" catKey="DIC" valueField="dictType" isNodeKey="true" name="<%=paramId%>" height="150" width="150"/>
	</div>
<%} %>
	</td>
	<td class="paramWidth"><%=param.getDescription()%></td>
	<td class="paramWidth">
		<a onclick="javascript:delRow(this);" class="link del" >删除</a>
	</td>
</tr>
  <%
}
%>
<%!
public Iterator getIterator(String source){
	JSONObject json = JSONObject.fromObject(source);
	Iterator<?> iter = json.keySet().iterator();
	Map map = new HashMap<String, String>();
	while (iter.hasNext()) {
		String key = (String) iter.next();
		String value = json.getString(key);
		map.put(key, value);
	}
	Set<?> set = map.entrySet();
	Iterator<?> it = set.iterator();
	return it;
} 
%>

