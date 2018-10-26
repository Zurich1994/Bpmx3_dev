<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="table-top">
			<div class="table-top-right">	
								<div class="toolBar" style="margin:0;">
									<div class="group"><a class="link add" id="btnSearch"  onclick="addUserSet()">添加</a></div>
									<div class="l-bar-separator"></div>
									<div class="group"><a class="link del " id="btnSearch"  onclick="delRows('${nodeId}');">删除</a></div>
									<div class="l-bar-separator"></div>
									<div class="group"><a class="link preview " id="btnPreview"  onclick="previewUserSetting(${defId})">预览</a></div>
								</div>						
		    </div>
</div>	
<table style="width:100%" id="table_${nodeId}" class="table-grid">								
	<thead>
	<tr>
		<th width="80" nowrap="nowrap">序号</th>
		<th width="70" nowrap="nowrap">用户类型</th>
		<th width="*" nowrap="nowrap">用户来自</th>
		<c:if test="${nodeId != '' }">
			<th width="70" nowrap="nowrap">抽取用户</th>
		</c:if>
		
		<th width="*" nowrap="nowrap">排序</th>
		<th nowrap="nowrap" width="80">运算类型</th>
	</tr>
	</thead>
	<tbody id="tbodyUserSet">
	<c:choose>
		<c:when test="${fn:length(userList)==0}">
			<tr>
				<td nowrap="nowrap" height="28">
					<input type='checkbox' name='nodeUserCk'/>
					<input type="hidden" name="nodeUserId" value=""/>
					
				</td>
				<td>
					<select name="assignType" class="select" onchange="assignTypeChange(this);">
						<c:forEach items="${assignUserTypes}" var="item">
							<option value="${item.key}"  <c:if test="${item.key=='users'}">selected="selected"</c:if> >${item.value["title"]}</option>
						</c:forEach>							
					</select>
				</td>
				<td>
					<textarea style="display: none;" name="cmpIds"></textarea>
					<textarea name="cmpNames" style="width:73%" rows="2" class="textarea" readonly="readonly"></textarea>
					<a class="button" onclick="selectCmp(this);"><span>选择...</span></a>
				</td>
				<c:if test="${nodeId != '' }">
					<td>
						<c:choose>
							<c:when test="${isMultiInstance}">
								<select name="extractUser">
									<option value="0" <c:if test="${userNode.extractUser eq 0}">selected="selected"</c:if> >不抽取</option>
									<option value="1" <c:if test="${userNode.extractUser eq 1}">selected="selected"</c:if>>抽取</option>
									<option value="2" <c:if test="${userNode.extractUser eq 2}">selected="selected"</c:if>>二级抽取</option>
									<option value="3" <c:if test="${userNode.extractUser eq 3}">selected="selected"</c:if>>用户组合</option>
								</select>
							</c:when>
							<c:otherwise>
								<select name="extractUser">
									<option value="0" <c:if test="${userNode.extractUser eq 0}">selected="selected"</c:if>>不抽取</option>
									<option value="1" <c:if test="${userNode.extractUser eq 1}">selected="selected"</c:if>>抽取</option>
								</select>
							</c:otherwise>
						</c:choose>
						
					</td>
				</c:if>
				
				<td>
					<a id='moveupField' class='link moveup'></a>
					<a id='movedownField' class='link movedown'></a>
				</td>
				<td>
					<select name="compType">
						<option value='0'>或</option>
						<option value='1'>与</option>
						<option value='2'>排除</option>
					</select>
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${userList}" var="userNode" varStatus="cnt">
				<tr id="${nodeUserMap.nodeId}_${cnt.count}">
					<td  nowrap="nowrap" height="28">
						<input type='checkbox' name='nodeUserCk'/>
						<input type="hidden" name="nodeUserId" value="${userNode.nodeUserId}"/>
					</td>
					<td>
						<input type="hidden" name="assignType" value="${userNode.assignType}"/>
						<span>
							${assignUserTypes[userNode.assignType]["title"]}
						</span>
					</td>
					<td>
						<textarea style="display: none;" name="cmpIds">${userNode.cmpIds}</textarea>
						<c:choose>
							<c:when test="${userNode.assignType eq 'startUser' or userNode.assignType eq 'sameDepart'
								or userNode.assignType eq 'directLeader' or userNode.assignType eq 'prevUserOrgLeader'  }">
									<span>${assignUserTypes[userNode.assignType]["title"]}</span>
									<textarea name="cmpNames" style="width:73%;display:none;" rows="3" class="textarea">${userNode.cmpNames}</textarea>
									<a class="button" onclick="selectCmp(this);" style="display:none;">
										<span>选择...</span>
									</a>
							</c:when>
							
							<c:otherwise>
								<textarea name="cmpNames" readonly="readonly" style="width:73%;visibility:visible" rows="2" class="textarea">${userNode.cmpNames}</textarea>
								<a class="button" onclick="selectCmp(this);" style="visibility:visible"><span>选择...</span></a>
							</c:otherwise>
						</c:choose>
					</td>
					
					
					<c:if test="${nodeId != '' }">
					<td>
						<c:choose>
							<c:when test="${isMultiInstance}">
								<select name="extractUser">
									<option value="0" <c:if test="${userNode.extractUser eq 0}">selected="selected"</c:if> >不抽取</option>
									<option value="1" <c:if test="${userNode.extractUser eq 1}">selected="selected"</c:if>>抽取</option>
									<option value="2" <c:if test="${userNode.extractUser eq 2}">selected="selected"</c:if>>二级抽取</option>
									<option value="3" <c:if test="${userNode.extractUser eq 3}">selected="selected"</c:if>>用户组合</option>
								</select>
							</c:when>
							<c:otherwise>
								<select name="extractUser">
									<option value="0" <c:if test="${userNode.extractUser eq 0}">selected="selected"</c:if>>不抽取</option>
									<option value="1" <c:if test="${userNode.extractUser eq 1}">selected="selected"</c:if>>抽取</option>
								</select>
							</c:otherwise>
						</c:choose>
						
					</td>
					</c:if>
					<td>
						<a class="link moveup" ></a>
						<a class="link movedown" ></a>
					</td>
					<td>
						<select name="compType">
							<option value="0" <c:if test="${userNode.compType==0}">selected</c:if> >或</option>
							<option value="1" <c:if test="${userNode.compType==1}">selected</c:if> >与</option>
							<option value="2" <c:if test="${userNode.compType==2}">selected</c:if> >排除</option>
						</select>
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</tbody>
</table>					