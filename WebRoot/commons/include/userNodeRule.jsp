<!-- 流程规则 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
<div class="table-top">
			<div class="table-top-right">	
						<div class="toolBar" style="margin:0;">
							<div class="group"><a class="link add" onclick="addCondition.apply(this)">添加规则</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link add" onclick="addConditionScript(this)">添加脚本规则</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link switchuser" onclick="">组合规则</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del" onclick="delCondition()">删除</a></div>
						</div>					
		    </div>
</div>
	<table id="condition-table" class="table-grid" cellpadding="1" cellspacing="1" border="0" >
		<thead>
			<tr>
				<th style="width:60px;"><input type="checkbox" onclick="checkAll.apply(this)"/></th>
				<th width="15%">变量</th>
				<th width="10%">运算类型</th>
				<th colSpan="4" width="65%">条件</th>
			</tr>
		</thead>
		<tbody>
			<tr class="condition-tr hidden">
				<td>
					<input name="pk" type="checkbox" />
				</td>
				<td name="flowvar-td">
					<select name="flowVars" onchange="flowVarChange.apply(this)">
						<c:forEach items="${flowVars}" var="flowVar">
							<option class="flowvar-item"  value="${flowVar.fieldName}" chosenopt="${flowVar.jsonOptions}" ctltype="${flowVar.controlType}" ftype="${flowVar.fieldType}" datefmt='${flowVar.datefmt}'>${flowVar.fieldDesc}</option>
						</c:forEach>
					</select>
				</td>
				<td name="conComType">
					<select name="comTypeSelect">
						<option value='0'>或</option>
						<option value='1'>与</option>
					</select>
				</td>
				<td name="judgeCon-td" class="judgeCon-td"></td>
				<td name="judgeVal-td" class="judgeVal-td"></td>
				<td name="judgeCon-td2" class="judgeCon-td"></td>
				<td name="judgeVal-td2" class="judgeVal-td"></td>
			</tr>
		</tbody>
	</table>
</div>

			