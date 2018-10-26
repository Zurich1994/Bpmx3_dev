<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="hotent" uri="http://www.jee-soft.cn/paging" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" /> -->

<f:link href="Aqua/css/easyui-all.css"></f:link> 
<f:link href="easyui/easyui.css"></f:link> 
<f:link href="easyui/icon.css"></f:link>
<f:link href="jquery/plugins/rowOps.css"></f:link>
<f:js pre="js/lang/common" ></f:js>
<f:js pre="js/lang/js" ></f:js>
<script type="text/javascript" src="${ctx}/js/dynamic.jsp"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/util/util.js"></script>
<script type="text/javascript" src="${ctx}/js/util/json2.js"></script>
<script type="text/javascript" src="${ctx}/js/util/form.js"></script>

<script type="text/javascript" src="${ctx}/js/easyui/jquery.easyui.min.js"></script>
<f:js pre="js/lang/easyui" ></f:js>
<script type="text/javascript" src="${ctx}/js/easyui/extend/jquery.easyui.topCall.js"></script>
<script type="text/javascript" src="${ctx}/js/easyui/extend/jquery.easyui.fixTreeFrame.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.htselect.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.rowOps.js"></script>
<script type="text/javascript" src="${ctx}/js/easyui/extend/DialogUtil.js" ></script>
<script type="text/javascript" src="${ctx}/js/easyui/extend/formtterExt.js" ></script>
<script type="text/javascript" src="${ctx}/js/easyui/extend/jquery.easyui.topCall.js"></script>

<%@include file="/js/msg.jsp"%>

<style type="text/css">
.pannel-common{
	padding: 5px !important;
}
</style>