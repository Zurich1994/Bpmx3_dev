<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<table style="width:100%" id="table_${nodeUserMap.nodeId}" class="table-grid">
	<thead>
	<tr>
		<th width="80" nowrap="nowrap">序号</th>
		<th width="*" nowrap="nowrap">条件</th>
		<th nowrap="nowrap" width="90px">批次号</th>
	</tr>
	</thead>
	<tbody class="data">
	       <input type="hidden" name="setId" value="${nodeUserMap.setId}"/>
			<input type="hidden" name="defId" value="${defId}"/>
			<input type="hidden" name="nodeId" value="${nodeUserMap.nodeId}"/>
	<c:choose>
		<c:when test="${fn:length(nodeUserMap.bpmUserConditionList)>0}">
			<c:forEach items="${nodeUserMap.bpmUserConditionList}" var="conditionNode" varStatus="cnt">
				<tr <c:if test="${cnt.index%2=='0' }">class="odd"</c:if>>
					<td>
						<input type='checkbox' name='nodeUserCk' onchange="changeCheck(this)"/>&nbsp;${cnt.count}						
						<input type="hidden" name="conditionId" value="${conditionNode.id}"/>		
					<input type="hidden" name="sn" value="${conditionNode.sn}"/>				
						<c:if test="${nodeId!=null}">
							<input type="hidden" name="nodeId" value="${nodeId}"/>
						</c:if>
					</td>
					<td>
						<div name="conditionShow">
							<textarea class="hidden">${conditionNode.condition}</textarea>							
						</div>
						<div style="margin:8px 0 0 0;">
							${conditionNode.conditionShow}
						</div>
					</td>
					<td name="groupNoTd">
						<div style="width: 80px">
							<input name="groupNo" style="width:70px;" class="inputText" ivalue ="${conditionNode.groupNo}"  value="${conditionNode.groupNo}" />
<!--							<div name="groupNo"  style="width:70px;border: 2px;border: 1 solid silver;"><span>${conditionNode.groupNo}</span></div>-->
						</div>
					</td>
				</tr>
			</c:forEach>
		</c:when>
	</c:choose>
	</tbody>
</table>