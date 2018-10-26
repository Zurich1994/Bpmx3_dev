
<%--
	time:2015-06-03 14:46:19
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>考勤情况</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
<style type="text/css">

.bigTitle{
	 margin-bottom: 10px;
	 margin-top: 20px;
	 margin-right: 30px;
	 margin-left: 20px;
	height : 20px;
	 clear :both ;
	 	background-color: #f4f4f4;
}

.bigTitle span a , .smallTitle span a{
   color :bule ;	
	text-decoration:underline;
	color: blue;
}
.status{
	  margin-left: 20px;
	  color : #999999 ;
	 margin-bottom: 6px;	
}

.bigTitle span {
	font-weight: bold;

}
.smallTitle , .smallTitle1 {
	margin-bottom:2px;
	color : #999999 ;
	width:30%;
	height : 30px;
	float:left ;
}
.detailInfo{
	display: none;
	border: 1px solid gray;
	position: absolute;
	background-color: white;
	top: -30px;
	left: 100px;
	-moz-border-radius: 10px ;
	-webkit-border-radius:5px;
	padding: 0px;
	margin : 0px ;
}
.detailInfo div {
		margin : 0px 15px 5px 15px 
	}
.detaiInfo span {
	 margin-left : 5px; 	
}

.attendTime {
	width: 90% ;
	clear :both ;
	margin-left: 45px; 
	color: #999999; 
}
.punchCard{
	display: none;
	border: 1px solid gray;
	position: absolute;
	background-color: white;
	top: 0px ;
	left: 0px ;
	-moz-border-radius: 10px ;
	-webkit-border-radius:5px;
	padding: 2px;
	color: gray;	
}
.punchCard div{
	float :left ;
	width:55px;
}
.smallTitle {
		margin-left: 5px;
}
.smallTitle1 {
		margin-left: 45px;
}
.smallTitle   .spanRight , .smallTitle1  .spanRight{
	float:left ;
	width : 65%;
	margin:0px;
}
.smallTitle  .spanLeft, .smallTitle1   .spanLeft{
	width:35% ;
	float:right ;
	margin:0px;
}
.smallTitle   .spanRight1 , .smallTitle1  .spanRight1{
	float:left ;
	width : 55%;
	margin:0px;
}
.smallTitle  .spanLeft1, .smallTitle1   .spanLeft1{
	width:45% ;
	float:right ;
	margin:0px ;
}

.leftSmallTitle {
	width:2% ;
	margin-left:30px;
	margin-bottom:2px;
	color : #999999 ;
	height : 30px;
	float:left ;
	}

.left{
	margin-left:30px;
	}
.more{
	float: right;
	margin-right: 80px;
	cursor:pointer;
	color:blue;
}
</style>
</head>

<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">考勤计算设置详细信息</span>
			</div>
		</div>
		<div class="status">当前状态 ：已计算</div>
		<div class="smallTitle1">
			<span class="spanRight">应出勤时数</span>
			<span class="spanLeft">${calculate.shouldAttenceHours}</span>
		</div>
		
		<c:choose>
			<c:when test="${calculate.isCardRecord == 1 }">
				<div class="smallTitle1">
					<span class="spanRight">实际出勤时数</span>
					<span class="spanLeft">${calculate.actualAttenceHours}</span>
				</div>
				<div class="smallTitle1">
						<span class="spanRight">
							打卡记录
						</span>
						<span class="spanLeft">${calculate.cardRecord}</span>
				</div>
			</c:when>
			<c:otherwise>
				<div class="smallTitle">
					<span class="spanRight">无打卡记录</span>
					<span class="spanLeft"></span>
				</div>
			</c:otherwise>
		</c:choose>
		
		<!--  旷工情况-->
		<div class="bigTitle"><span>旷工情况</span></div>
		<c:choose>
			<c:when test="${calculate.absentNumber > 0 }">
				<div class="smallTitle1">
					<span class="spanRight">旷工次数</span>
					<span class="spanLeft">${calculate.absentNumber}</span>
				</div>
				<div class="smallTitle1">
					<span class="spanRight">旷工时数</span>
					<span class="spanLeft">${calculate.absentTime}</span>
				</div>
				<div class="smallTitle1">
					<span class="spanRight">应出勤时间</span>
					<span class="spanLeft">${calculate.shiftTime1}</span>
				</div>
				<div class="smallTitle1">
					<span class="spanRight">${calculate.shiftTime2}</span>
					<span class="spanLeft">${calculate.shiftTime3}</span>
				</div>
				<div class="smallTitle1">
					<span class="spanRight">实际出勤时间</span>
					<span class="spanLeft">${calculate.absentRecord1 }</span>
				</div>
				<div class="smallTitle1">
					<span class="spanRight">${calculate.absentRecord2 }</span>
					<span class="spanLeft">${calculate.absentRecord3 }</span>
				</div>
			</c:when>
			<c:otherwise>
				<div class="smallTitle1" ><span class="spanRight">无</span><span class="spanLeft"></span></div>
			</c:otherwise>
		</c:choose>
		<!-- 迟到情况-->
		<div class="bigTitle"><span>迟到情况</span></div>
		<c:choose>
			<c:when test="${calculate.lateNumber > 0 }">
				<div class="smallTitle1">
					<span class="spanRight">迟到次数</span>
					<span class="spanLeft">${calculate.lateNumber}</span>
				</div>
				<div class="smallTitle1">
					<span class="spanRight">迟到分钟</span>
					<span class="spanLeft">${calculate.lateTime}</span>
				</div>
			</c:when>
			<c:otherwise>
				<div class="smallTitle1" ><span class="spanRight">无</span><span class="spanLeft"></span></div>
			</c:otherwise>
		</c:choose>
		</div>
		
		<!-- 早退情况-->
		<div class="bigTitle"><span>早退情况</span></div>
		<c:choose>
			<c:when test="${calculate.leaveNumber > 0 }">
				<div class="smallTitle1">
					<span class="spanRight">早退次数</span>
					<span class="spanLeft">${calculate.leaveNumber}</span>
				</div>
				<div class="smallTitle1">
					<span class="spanRight">早退分钟</span>
					<span class="spanLeft">${calculate.leaveTime}</span>
				</div>
			</c:when>
			<c:otherwise>
				<div class="smallTitle1" ><span class="spanRight">无</span><span class="spanLeft"></span></div>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
</body>
</html>

