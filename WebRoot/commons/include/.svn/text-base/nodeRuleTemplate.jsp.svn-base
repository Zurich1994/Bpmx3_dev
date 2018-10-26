<!-- 规则模板 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="hidden">
		<input id="normal-input" class="short-input" name="judgeValue" type="text" />
		<select id="judgeCon-1" name="judgeCondition">
			<option value="==">等于</option>
			<option value="!=">不等于</option>
			<option value=">">大于</option>
			<option value="&lt;">小于</option>
			<option value=">=">大于等于</option>
			<option value="&lt;=">小于等于</option>
		</select>
		<select id="judgeCon-2" name="judgeCondition">
			<option value="equals()">完全等于</option>
			<option value="!=">不等于</option>
			<option value="equalsIgnoreCase()">等于(忽略大小写)</option>
			<option value="contains()">包含</option>
			<option value="notContains()">不包含</option>
		</select>
		<select id="judgeCon-4" name="judgeCondition">
			<option value="equals()">等于</option>
			<option value="!=">不等于</option>
			<option value="contains()">包含</option>
			<option value="notContains()">不包含</option>
		</select>
		<select id="judgeCon-5" name="judgeCondition">
			<option value="contains()">包含</option>
			<option value="notContains()">不包含</option>
		</select>
		<input id="date-input" type="text" class="Wdate" />
		<div id="role-div">
			<input type="hidden" value="">
			<input type="text" readonly="readonly" />
			<a href="javascript:;" class="link role">选择</a>
		</div>
		<div id="position-div">
			<input type="hidden" value="">
			<input type="text" readonly="readonly" />
			<a href="javascript:;" class="link position">选择</a>
		</div>
		<div id="org-div">
			<input type="hidden" value="">
			<input type="text" readonly="readonly" />
			<a href="javascript:;" class="link org">选择</a>
		</div>
		<div id="user-div">
			<input type="hidden" value="">
			<input type="text" readonly="readonly" />
			<a href="javascript:;" class="link users">选择</a>
		</div>
		<textarea id="dic-select">
			<select>					
				<#list data as obj>
					<option value="\${obj.option}">\${obj.memo}</option>
				</#list>	
			</select>
		</textarea>
		<textarea id="dic-radio-checkbox">
			<#list data as obj>
				<label><input type="\${obj.type}" name="\${obj.name}" value="\${obj.option}"/>\${obj.memo}</label>
			</#list>
		</textarea>
		
		<select id="flowVarsSelect" class="left margin-set" name="flowVars" onchange="flowVarChange.apply(this)">
			<option value="">--请选择--</option>
			<optgroup label="表单变量"></optgroup>
			<c:forEach items="${flowVars}" var="flowVar">
				<option class="flowvar-item"  value="${flowVar.fieldName}" chosenopt="${flowVar.jsonOptions}" ctltype="${flowVar.controlType}" ftype="${flowVar.fieldType}" datefmt='${flowVar.datefmt}'>${flowVar.fieldDesc}</option>
			</c:forEach>
			<c:if test="${not empty defVars}">
			<optgroup label="自定义变量"></optgroup>
			<c:forEach items="${defVars}" var="defVars">
				<option class="flowvar-item"  value="${defVars.varKey}" chosenopt="" ctltype="0" ftype="${defVars.varDataType}" datefmt='yyyy-MM-dd'>${defVars.varName}</option>
			</c:forEach>
			</c:if>
		</select>
		<!-- 属性模板 -->
		<select id="paramKey" name="paramKey" class="left margin-set" onchange="changeCondition(this)">
			<c:forEach items="${sysParamList}" var="p" >
				<option title="${p.dataType }" value="${p.paramKey }">${p.paramName }</option>
			</c:forEach>
		</select>			
		
		<select id="paramCondition" name="paramCondition" class="left margin-set">
			<option value="=">=</option>
			<option value="!=">!=</option>
			<option value=">">></option>
			<option value="<"><</option>
			<option value=">=">>=</option>
			<option value="<="><=</option>
		</select>
		
		<input type="text" id="paramValue" name="paramValue" class="left margin-set" onchange="validateVal(this)"/>
		
		<div >
			<table id="condition-script-rule">
				<tr class="script-tr">
					<td>
						<input name="pk" type="checkbox" />
					</td>
					<td>
						条件脚本
					</td>
					<td name="conComType">
						<select name="comTypeSelect">
							<option value='0'>或</option>
							<option value='1'>与</option>
						</select>
					</td>
					<td>
						<a name="script" href="javascript:;" onclick="editConditionScript(this)">脚本</a>
					</td>
				</tr>
			</table>
		</div>
</div>	