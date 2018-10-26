<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

	<!-- bootstrap-->
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/font-awesome/font-awesome.min.css"></link>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/bootstrap/bootstrap.min.css"></link>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/bootstrap/bootstrap-dialog.min.css"></link>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/fullcalendar/fullcalendar.min.css"></link>
	
	<f:link href="hotent/index.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/dynamic.jsp"></script>
		<script type="text/javascript" src="${ctx}/js/fullcalendar/moment.min.js"></script>
	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery || document.write("<script src='${ctx}/js/bootstrap/jquery.min.js'>"+"<"+"/script>");
	</script>
	<!-- <![endif]-->
	<!--[if IE]>
	<script type="text/javascript">
		 window.jQuery || document.write("<script src='${ctx}/js/bootstrap/jquery1x.min.js'>"+"<"+"/script>");
	</script>
	<![endif]-->
  	<script type="text/javascript" src="${ctx}/js/bootstrap/jquery.easypiechart.min.js"></script>
  	<script type="text/javascript" src="${ctx}/js/bootstrap/jquery.sparkline.min.js"></script>
	 <script type="text/javascript" src="${ctx}/js/fullcalendar/fullcalendar.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/lang/fullcalendar/zh_CN.js"></script>
	<script type="text/javascript" src="${ctx }/js/bootstrap/bootstrap.min.js"></script>
	<!--[if lte IE 8]>
		<script type="text/javascript" src="${ctx}/js/bootstrap/html5shiv.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/bootstrap/respond.min.js"></script>
	<![endif]-->
	<script type="text/javascript" src="${ctx}/js/bootstrap/bootstrap-dialog.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.blockUI.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.carouFredSel.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/echarts/echarts.js"></script>
		 <!-- BEGIN LayerSlider -->
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/slider-layer-slider/layerslider.css">
	<link  rel="stylesheet" type="text/css" href="${ctx}/styles/common/css/slider-layer-slider/style-layer-slider.css">
    <script src="${ctx}/js/bootstrap/slider-layer-slider/greensock.js" type="text/javascript"></script>
    <script src="${ctx}/js/bootstrap/slider-layer-slider/layerslider.transitions.js" type="text/javascript"></script>
    <script src="${ctx}/js/bootstrap/slider-layer-slider/layerslider.kreaturamedia.jquery.js" type="text/javascript"></script>
    <!-- END LayerSlider -->
    	<script type="text/javascript" src="${ctx}/js/util/util.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/index/indexPage.js"></script>

  


	
	