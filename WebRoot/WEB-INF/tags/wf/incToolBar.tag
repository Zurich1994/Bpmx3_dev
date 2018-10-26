<%@ tag language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${! empty flowKey }">
	<script type="text/javascript">
	function showTaskOpinions(){
		var url=__ctx + "/platform/bpm/taskOpinion/list.ht?runId=${processRun.runId}";
		url=url.getNewUrl();
		DialogUtil.open({
			url:url,
			title:'审批历史',
			height:'600',
			width:'800'
		});
	}
	</script>
</c:if>

<c:choose>
	<c:when test="${! empty flowKey }">
		<c:choose>
			<c:when test="${taskId==0}">
		
				<div class="group"><a class="link run"  href="#"><span></span>提交</a></div>
				<div class="l-bar-separator"></div>
				
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				
				
				<div class="group"><a class="link flowDesign"  href="#" onclick="BpmImageDialog({flowKey:'${flowKey}'})"><span></span>流程图</a></div>
				<div class="l-bar-separator"></div>
				<input type="hidden" name="flowKey" value="${flowKey}" />
				<input type="hidden" name="executeType"  value="start" />
				<input type="hidden" name="runId"  value="${processRun.runId}" />
		
		
			</c:when>
			<c:otherwise>
		
				<div class="group"><a class="link run"  href="#"><span></span>审批</a></div>
				<div class="l-bar-separator"></div>
			
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
			
				<div class="group"><a class="link search"  href="#" onclick="showTaskOpinions()"><span></span>审批历史</a></div>
				<div class="l-bar-separator"></div>
				
				<div class="group"><a class="link flowDesign"  href="#" onclick="BpmImageDialog({actInstId:${processRun.actInstId}});"><span></span>流程图</a></div>
				<div class="l-bar-separator"></div>
				
				<input type="hidden" name="executeType"  value="doNext" />
				<input type="hidden" name="taskId"  value="${taskId}" />
		
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span>保存</a></div>
		<div class="l-bar-separator"></div>
	</c:otherwise>
</c:choose>
<c:if test="${! empty flowKey }">
	<input type="hidden"  id="saveData" name="saveData" value="1" />
</c:if>
