<!-- 用户模板 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<table class="hidden">
		<tbody id="selectUserRow">
			<tr>
				<td nowrap='nowrap' height='28'>
					<input type='checkbox' name='nodeUserCk'/>
					<input type='hidden' name='nodeUserId'/>
					
				</td>
				<td>
					<select name='assignType' class='select' onchange='assignTypeChange(this);'>
						<c:forEach items="${assignUserTypes}" var="item">
							<option value="${item.key}" <c:if test="${item.key eq 'users'}">selected="selected"</c:if> >${item.value["title"]}</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<textarea style="display: none;" name='cmpIds'></textarea>
					<textarea name='cmpNames' readonly='readonly' style='width:73%' rows='2' class='textarea'></textarea>
					<a class='button' onclick='selectCmp(this);'><span>选择...</span></a>
				</td>
				<c:if test="${nodeId != '' }">
					<td>
						<c:choose>
							<c:when test="${isMultiInstance}">
								<select name="extractUser">
									<option value="0">不抽取</option>
									<option value="1">抽取</option>
									<option value="2">二级抽取</option>
									<option value="3">用户组合</option>
								</select>
							</c:when>
							<c:otherwise>
								<select name="extractUser">
									<option value="0">不抽取</option>
									<option value="1">抽取</option>
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
					<select name='compType'>
						<option value='0'>或</option>
						<option value='1'>与</option>
						<option value='2'>排除</option>
					</select>
				</td>
			</tr>
			
		</tbody>
	</table>