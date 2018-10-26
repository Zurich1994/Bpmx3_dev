<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:fn="http://www.w3.org/2005/02/xpath-functions" 
	xmlns:bg="bpm.graphic"
	xmlns:fg="bpm.graphic"
	xmlns:activiti="http://activiti.org/bpmn" 
	xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" 
	xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC"
	xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI" 
	xmlns:ciied="com.ibm.ilog.elixir.diagram" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:calc="xalan://com.hotent.core.bpm.graph.TransformUtil" 
	xmlns:ht="http://www.jee-soft.cn/BPMN20EXT"
	extension-element-prefixes="calc">
	<xsl:param name="id" />
	<xsl:param name="name" />
	<xsl:output method="xml" version="1.0" encoding="utf-8" indent="yes" />

	<!-- comp变量的值为/dragram下的所有的子结点名组成的字符串，可用于判断是否包含泳道池 -->
	<xsl:variable name="comp">
		<xsl:for-each select="/diagram/child::*">
			<xsl:value-of select="name(.)"></xsl:value-of>
		</xsl:for-each>
	</xsl:variable>

	<!-- 根模板 -->
	<xsl:template match="/">
		<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" 
		xmlns:activiti="http://activiti.org/bpmn" 
		xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI"
		xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" 
		xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI" 
		xmlns:ht="http://www.jee-soft.cn/BPMN20EXT"
		xsi:schemaLocation="http://www.jee-soft.cn/BPMN20EXT BPMN20EXT.xsd"
		targetNamespace="http://activiti.org/bpmn20"
		>

			<!--应用所有为根节点下的节点模板 -->
			<xsl:apply-templates />

			<!-- 生成图形的坐标的描述 -->
			<bpmndi:BPMNDiagram>
				<xsl:attribute name="id">BPMNDiagram_<xsl:value-of select="$id"></xsl:value-of>
				</xsl:attribute>
				<bpmndi:BPMNPlane>
					<xsl:attribute name="bpmnElement"><xsl:value-of select="$id"></xsl:value-of>
					</xsl:attribute>
					<xsl:attribute name="id">BPMNPlane_<xsl:value-of select="$id"></xsl:value-of>
					</xsl:attribute>

					<!-- 图形组件的坐标描述 -->
					<xsl:call-template name="diagram"></xsl:call-template>
					<!-- 图形组件间连线的坐标描述 -->
					<xsl:call-template name="transition"></xsl:call-template>
				</bpmndi:BPMNPlane>
			</bpmndi:BPMNDiagram>
		</definitions>
	</xsl:template>


	<xsl:template match="/diagram">
		<!--调用模板"collaboration"，生成节点<collaboration> -->
		<xsl:call-template name="collaboration"></xsl:call-template>
		<!--调用模板"bg:HPool"，生成节点水平泳道池 -->
		<xsl:apply-templates select="bg:HPool">
		</xsl:apply-templates>

		<!--调用模板"bg:VPool"，生成节点垂直泳道池 -->
		<xsl:apply-templates select="bg:VPool">
		</xsl:apply-templates>


		<!-- 生成不在泳道池内的组件 -->
		<xsl:if test="contains($comp,'Event') or contains($comp,'Task') or contains($comp,'SequenceFlow') or contains($comp,'SubProcess') or contains($comp,'Gateway')">
			<process>
				<xsl:attribute name="id">
					<xsl:value-of select="$id"></xsl:value-of>
				</xsl:attribute>
				<xsl:attribute name="name">
					<xsl:value-of select="$name"></xsl:value-of>
				</xsl:attribute>
				<extensionElements>
					<!-- 流程开始事件监听器 -->
					<activiti:executionListener  event="start" >
						<xsl:attribute name="delegateExpression">${startEventListener}</xsl:attribute>
					</activiti:executionListener>
					<!-- 流程结束事件监听器 -->
					<activiti:executionListener  event="end" >
						<xsl:attribute name="delegateExpression">${endEventListener}</xsl:attribute>
					</activiti:executionListener>
				</extensionElements>
				<xsl:apply-templates select="bg:StartEvent">
				</xsl:apply-templates>
				<xsl:apply-templates select="bg:EndEvent">
				</xsl:apply-templates>
				<xsl:apply-templates select="bg:Task">
				</xsl:apply-templates>
				<xsl:apply-templates select="bg:SequenceFlow">
				</xsl:apply-templates>
				<xsl:apply-templates select="bg:SubProcess">
				</xsl:apply-templates>
				<xsl:apply-templates select="bg:Gateway">
				</xsl:apply-templates>
			</process>
		</xsl:if>

	</xsl:template>
	<xsl:template match="bg:HPool">
		<process>
			<xsl:attribute name="id">
				<xsl:value-of select="$id"></xsl:value-of>
			</xsl:attribute>
			<xsl:attribute name="name">
				<xsl:value-of select="$name"></xsl:value-of>
			</xsl:attribute>
			<extensionElements>
				<!-- 流程开始事件监听器 -->
				<activiti:executionListener event="start" >
					<xsl:attribute name="delegateExpression">${startEventListener}</xsl:attribute>
				</activiti:executionListener>
				<!-- 流程结束事件监听器 -->
				<activiti:executionListener  event="end" >
					<xsl:attribute name="delegateExpression">${endEventListener}</xsl:attribute>
				</activiti:executionListener>
			</extensionElements>
			<laneSet>
				<xsl:attribute name="id">laneSet_process_<xsl:value-of select="@id"></xsl:value-of>
   				</xsl:attribute>
				<xsl:for-each select="bg:HLane">
					<lane>
						<xsl:attribute name="id">
				 			<xsl:value-of select="@id"></xsl:value-of>
				 		</xsl:attribute>
						<xsl:attribute name="name">
				 			<xsl:value-of select="label"></xsl:value-of>
				 		</xsl:attribute>
						<xsl:for-each select="child::*[starts-with(name(.),'bg:') and name(.)!='bg:SequenceFlow']">
							<flowNodeRef>
								<xsl:value-of select="@id"></xsl:value-of>
							</flowNodeRef>
						</xsl:for-each>
					</lane>
				</xsl:for-each>
			</laneSet>
			<xsl:apply-templates select="bg:HLane"></xsl:apply-templates>
			<xsl:apply-templates select="bg:SequenceFlow">
			</xsl:apply-templates>
		</process>
	</xsl:template>
	<xsl:template match="bg:VPool">
		<process>
			<xsl:attribute name="name"><xsl:value-of select="$name"></xsl:value-of>
   			</xsl:attribute>
			<xsl:attribute name="id"><xsl:value-of select="$id"></xsl:value-of>
   			</xsl:attribute>
			<extensionElements>
				<!-- 流程开始事件监听器 -->
				<activiti:executionListener  event="start" >
					<xsl:attribute name="delegateExpression">${startEventListener}</xsl:attribute>
				</activiti:executionListener>
				<!-- 流程结束事件监听器 -->
				<activiti:executionListener  event="end" >
					<xsl:attribute name="delegateExpression">${endEventListener}</xsl:attribute>
				</activiti:executionListener>
			</extensionElements>
			<laneSet>
				<xsl:attribute name="id">laneSet_process_<xsl:value-of select="@id"></xsl:value-of>
   				</xsl:attribute>
				<xsl:for-each select="bg:VLane">
					<lane>
						<xsl:attribute name="id">
				 			<xsl:value-of select="@id"></xsl:value-of>
				 		</xsl:attribute>
						<xsl:attribute name="name">
				 			<xsl:value-of select="label"></xsl:value-of>
				 		</xsl:attribute>
						<xsl:for-each select="child::*[starts-with(name(.),'bg:') and name(.)!='bg:SequenceFlow']">
							<flowNodeRef>
								<xsl:value-of select="@id"></xsl:value-of>
							</flowNodeRef>
						</xsl:for-each>
					</lane>
				</xsl:for-each>
			</laneSet>
			<xsl:apply-templates select="bg:VLane"></xsl:apply-templates>
			<xsl:apply-templates select="bg:SequenceFlow">
			</xsl:apply-templates>
		</process>
	</xsl:template>
	<xsl:template match="bg:HLane">
		<xsl:apply-templates select="bg:StartEvent">
		</xsl:apply-templates>
		<xsl:apply-templates select="bg:EndEvent">
		</xsl:apply-templates>
		<xsl:apply-templates select="bg:Task">
		</xsl:apply-templates>
		<xsl:apply-templates select="bg:SubProcess">
		</xsl:apply-templates>
		<xsl:apply-templates select="bg:Gateway">
		</xsl:apply-templates>
		<xsl:apply-templates select="bg:SequenceFlow">
		</xsl:apply-templates>
	</xsl:template>
	<xsl:template match="bg:VLane">
		<xsl:apply-templates select="bg:StartEvent">
		</xsl:apply-templates>
		<xsl:apply-templates select="bg:EndEvent">
		</xsl:apply-templates>
		<xsl:apply-templates select="bg:Task">
		</xsl:apply-templates>
		<xsl:apply-templates select="bg:SubProcess">
		</xsl:apply-templates>
		<xsl:apply-templates select="bg:Gateway">
		</xsl:apply-templates>
		<xsl:apply-templates select="bg:SequenceFlow">
		</xsl:apply-templates>
	</xsl:template>
	<xsl:template match="bg:SubProcess">
		<xsl:for-each select=".">
			<subProcess>

				<xsl:call-template name="setAttrubute">
					<xsl:with-param name="obj" select="."></xsl:with-param>
				</xsl:call-template>
				<extensionElements>
					<!-- 流程开始事件监听器 -->
					<activiti:executionListener event="start">
						<xsl:attribute name="delegateExpression">${subProcessStartListener}</xsl:attribute>
					</activiti:executionListener>
					<!-- 流程结束事件监听器 -->
					<activiti:executionListener event="end">
						<xsl:attribute name="delegateExpression">${subProcessEndListener}</xsl:attribute>
					</activiti:executionListener>
				</extensionElements>
				<xsl:if test="@multiInstance='true'">

					<multiInstanceLoopCharacteristics activiti:elementVariable="assignee">
						<xsl:attribute name="isSequential">
							<xsl:choose>
								<xsl:when test="@isSequential='true'">true</xsl:when>
								<xsl:otherwise>false</xsl:otherwise>
							</xsl:choose>
						</xsl:attribute>
						<xsl:attribute name="activiti:collection">${taskUserAssignService.getMultipleUser(execution)}</xsl:attribute>
					</multiInstanceLoopCharacteristics>
				</xsl:if>
				<xsl:apply-templates select="bg:StartEvent">
				</xsl:apply-templates>
				<xsl:apply-templates select="bg:EndEvent">
				</xsl:apply-templates>
				<xsl:apply-templates select="bg:Task">
				</xsl:apply-templates>
				<xsl:apply-templates select="bg:SequenceFlow">
				</xsl:apply-templates>
				<xsl:apply-templates select="bg:Gateway">
				</xsl:apply-templates>
				<xsl:apply-templates select="bg:SubProcess">
				</xsl:apply-templates>

			</subProcess>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="bg:Gateway">
		<xsl:for-each select=".">
			<xsl:variable name="type" select="./gatewayType" />
			<xsl:choose>
				<xsl:when test="$type='AND'">
					<parallelGateway>
						<xsl:call-template name="setAttrubute">
							<xsl:with-param name="obj" select="."></xsl:with-param>
						</xsl:call-template>
					</parallelGateway>
				</xsl:when>
				<xsl:when test="$type='OR'">
					<inclusiveGateway>
						<xsl:call-template name="setAttrubute">
							<xsl:with-param name="obj" select="."></xsl:with-param>
						</xsl:call-template>
					</inclusiveGateway>
				</xsl:when>
				<xsl:otherwise>
					<exclusiveGateway>
						<xsl:call-template name="setAttrubute">
							<xsl:with-param name="obj" select="."></xsl:with-param>
						</xsl:call-template>
					</exclusiveGateway>

				</xsl:otherwise>
			</xsl:choose>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="bg:Task">
		<xsl:for-each select=".">
			<xsl:choose>
				<xsl:when test="@user='true'">
					<userTask>
						<xsl:call-template name="setAttrubute">
							<xsl:with-param name="obj" select="."></xsl:with-param>
						</xsl:call-template>
						<documentation>
							<xsl:value-of select="Description"></xsl:value-of>
						</documentation>
						<extensionElements>
							<activiti:taskListener event="create">
								<xsl:attribute name="delegateExpression">${taskCreateListener}</xsl:attribute>
							</activiti:taskListener>
							<activiti:taskListener event="assignment">
								<xsl:attribute name="delegateExpression">${taskAssignListener}</xsl:attribute>
							</activiti:taskListener>
							<activiti:taskListener event="complete">
								<xsl:attribute name="delegateExpression">${taskCompleteListener}</xsl:attribute>
							</activiti:taskListener>
							
							<xsl:call-template name="setTaskExtProperties">
								<xsl:with-param name="obj" select="."></xsl:with-param>
							</xsl:call-template>
						</extensionElements>
					</userTask>
				</xsl:when>
				<xsl:when test="@script='true'">
					<serviceTask>
						<xsl:attribute name="activiti:delegateExpression">${scriptTask}</xsl:attribute>
						<xsl:call-template name="setAttrubute">
							<xsl:with-param name="obj" select="."></xsl:with-param>
						</xsl:call-template>
						<extensionElements>
							<activiti:executionListener event="start">
								<xsl:attribute name="delegateExpression">${autoTaskListener}</xsl:attribute>
							</activiti:executionListener>
							<xsl:call-template name="setTaskExtProperties">
								<xsl:with-param name="obj" select="."></xsl:with-param>
							</xsl:call-template>
							<ht:serviceType value="script"></ht:serviceType>
						</extensionElements>
					</serviceTask>
				</xsl:when>

				<xsl:when test="@mail='true'">
					<serviceTask>
						<xsl:attribute name="activiti:delegateExpression">${messageTask}</xsl:attribute>
						<xsl:call-template name="setAttrubute">
							<xsl:with-param name="obj" select="."></xsl:with-param>
						</xsl:call-template>
						<extensionElements>
							<activiti:executionListener event="start">
								<xsl:attribute name="delegateExpression">${autoTaskListener}</xsl:attribute>
							</activiti:executionListener>
							<xsl:call-template name="setTaskExtProperties">
								<xsl:with-param name="obj" select="."></xsl:with-param>
							</xsl:call-template>
							<ht:serviceType value="email"></ht:serviceType>
						</extensionElements>
					</serviceTask>
				</xsl:when>
				<xsl:when test="@webService='true'">
					<serviceTask>
						<xsl:attribute name="activiti:delegateExpression">${webServiceTask}</xsl:attribute>
						<xsl:call-template name="setAttrubute">
							<xsl:with-param name="obj" select="."></xsl:with-param>
						</xsl:call-template>
						<extensionElements>
							<activiti:executionListener event="start">
								<xsl:attribute name="delegateExpression">${autoTaskListener}</xsl:attribute>
							</activiti:executionListener>
							<xsl:call-template name="setTaskExtProperties">
								<xsl:with-param name="obj" select="."></xsl:with-param>
							</xsl:call-template>
							<ht:serviceType value="webService"/>
						</extensionElements>
					</serviceTask>
				</xsl:when>
				<xsl:when test="@receive='true'">
					<receiveTask>
						<xsl:call-template name="setAttrubute">
							<xsl:with-param name="obj" select="."></xsl:with-param>
						</xsl:call-template>
						<extensionElements>
							<xsl:call-template name="setTaskExtProperties">
								<xsl:with-param name="obj" select="."></xsl:with-param>
							</xsl:call-template>
						</extensionElements>
					</receiveTask>
				</xsl:when>
				<xsl:when test="@businessRule='true'">
					<businessRuleTask>
						<xsl:call-template name="setAttrubute">
							<xsl:with-param name="obj" select="."></xsl:with-param>
						</xsl:call-template>
						<extensionElements>
							<xsl:call-template name="setTaskExtProperties">
								<xsl:with-param name="obj" select="."></xsl:with-param>
							</xsl:call-template>
						</extensionElements>
					</businessRuleTask>
				</xsl:when>
				<!-- 会签 -->
				<xsl:when test="@multiInstance='true'">
					<userTask>
						<!-- <xsl:attribute name="activiti:assignee">${assignee}</xsl:attribute> -->
						<xsl:call-template name="setAttrubute">
							<xsl:with-param name="obj" select="."></xsl:with-param>
						</xsl:call-template>
						<extensionElements>
							<activiti:taskListener event="create">
								<xsl:attribute name="delegateExpression">${taskSignCreateListener}</xsl:attribute>
							</activiti:taskListener>
							<activiti:taskListener event="assignment">
								<xsl:attribute name="delegateExpression">${taskAssignListener}</xsl:attribute>
							</activiti:taskListener>
							<activiti:taskListener event="complete">
								<xsl:attribute name="delegateExpression">${taskCompleteListener}</xsl:attribute>
							</activiti:taskListener>
							<xsl:call-template name="setTaskExtProperties">
								<xsl:with-param name="obj" select="."></xsl:with-param>
							</xsl:call-template>
						</extensionElements>
						<multiInstanceLoopCharacteristics activiti:elementVariable="assignee">
							<xsl:attribute name="isSequential">
								<xsl:choose>
									<xsl:when test="@isSequential='true'">true</xsl:when>
									<xsl:otherwise>false</xsl:otherwise>
								</xsl:choose>
							</xsl:attribute>
							<xsl:attribute name="activiti:collection">${taskUserAssignService.getSignUser(execution)}</xsl:attribute>
							<completionCondition>${signComplete.isComplete(execution) }</completionCondition>
						</multiInstanceLoopCharacteristics>
					</userTask>
				</xsl:when>
				<xsl:when test="@startSubFlow='true'">
					<callActivity>
						<xsl:call-template name="setAttrubute">
							<xsl:with-param name="obj" select="."></xsl:with-param>
						</xsl:call-template>
						<xsl:if test="./subDefKey">
							<xsl:attribute name="calledElement">
								<xsl:value-of select="./subDefKey"></xsl:value-of>
							</xsl:attribute>
						</xsl:if>
						<extensionElements>
							<activiti:executionListener event="start">
								<xsl:attribute name="delegateExpression">${callSubProcessStartListener}</xsl:attribute>
							</activiti:executionListener>
							<activiti:executionListener event="end">
								<xsl:attribute name="delegateExpression">${callSubProcessEndListener}</xsl:attribute>
							</activiti:executionListener>
							<activiti:in source="outPassVars" target="innerPassVars" />
							<activiti:out source="innerPassVars" target="outPassVars" />
							<xsl:call-template name="setTaskExtProperties">
								<xsl:with-param name="obj" select="."></xsl:with-param>
							</xsl:call-template>
						</extensionElements>
						<xsl:if test="@startMultiSubFlow='true'">
							<multiInstanceLoopCharacteristics activiti:elementVariable="assignee">
								<xsl:attribute name="isSequential">
									<xsl:choose>
										<xsl:when test="@isSequential='true'">true</xsl:when>
										<xsl:otherwise>false</xsl:otherwise>
									</xsl:choose>
								</xsl:attribute>
								<xsl:attribute name="activiti:collection">${taskUserAssignService.getExtSubProcessMultipleUser(execution)}</xsl:attribute>
							</multiInstanceLoopCharacteristics>
						</xsl:if>
					</callActivity>
				</xsl:when>
				<xsl:otherwise>

					<userTask>
						<xsl:call-template name="setAttrubute">
							<xsl:with-param name="obj" select="."></xsl:with-param>
						</xsl:call-template>
						<documentation>
							<xsl:value-of select="Description"></xsl:value-of>
						</documentation>
						<extensionElements>
							<activiti:taskListener event="create">
								<xsl:attribute name="delegateExpression">${taskCreateListener}</xsl:attribute>
							</activiti:taskListener>
							<activiti:taskListener event="assignment">
								<xsl:attribute name="delegateExpression">${taskAssignListener}</xsl:attribute>
							</activiti:taskListener>
							<activiti:taskListener event="complete">
								<xsl:attribute name="delegateExpression">${taskCompleteListener}</xsl:attribute>
							</activiti:taskListener>
							<xsl:call-template name="setTaskExtProperties">
								<xsl:with-param name="obj" select="."></xsl:with-param>
							</xsl:call-template>
						</extensionElements>
					</userTask>

				</xsl:otherwise>
			</xsl:choose>

		</xsl:for-each>
	</xsl:template>
	<xsl:template match="bg:StartEvent">
		<startEvent activiti:initiator="startUser">
			<xsl:call-template name="setAttrubute">
				<xsl:with-param name="obj" select="."></xsl:with-param>
			</xsl:call-template>
		</startEvent>
	</xsl:template>
	<xsl:template match="bg:EndEvent">
		<xsl:for-each select=".">
			<endEvent>
				<xsl:call-template name="setAttrubute">
					<xsl:with-param name="obj" select="."></xsl:with-param>
				</xsl:call-template>
				<xsl:if test="trigger='Error'">
					<errorEventDefinition>
						<xsl:attribute name="errorRef">
							<xsl:value-of select="'Error'"></xsl:value-of>
						</xsl:attribute>
					</errorEventDefinition>
				</xsl:if>
			</endEvent>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="bg:SequenceFlow">

		<xsl:for-each select=".">
			<xsl:variable name="sourceRef" select="@startPort"></xsl:variable>
			<xsl:variable name="targetRef" select="@endPort"></xsl:variable>
			<sequenceFlow>
				<xsl:attribute name="sourceRef">
					<xsl:value-of select="//ciied:Port[@id=$sourceRef]/parent::*/parent::*/@id"></xsl:value-of>
				</xsl:attribute>
				<xsl:attribute name="targetRef">
					<xsl:value-of select="//ciied:Port[@id=$targetRef]/parent::*/parent::*/@id"></xsl:value-of>
				</xsl:attribute>
				<xsl:call-template name="setAttrubute">
					<xsl:with-param name="obj" select="."></xsl:with-param>
				</xsl:call-template>
				<!-- 设置跳转条件 -->
				<xsl:variable name="commentPre">
					&lt;![CDATA[${
				</xsl:variable>
				<xsl:variable name="commentSuffix">}]]&gt;
				</xsl:variable>
				<xsl:variable name="condition" select="./Condition" />
				<xsl:if test="$condition!=''">
					<conditionExpression xsi:type="tFormalExpression">
						<xsl:value-of select="$commentPre" />
						<xsl:value-of select="$condition" />
						<xsl:value-of select="$commentSuffix" />
					</conditionExpression>
				</xsl:if>

			</sequenceFlow>
		</xsl:for-each>
	</xsl:template>
	<xsl:template name="collaboration">
		<xsl:if test="contains($comp,'Pool')">
			<collaboration id="Collaboration">
				<xsl:for-each select="child::*[contains(name(.),'Pool')]">
					<participant>
						<xsl:attribute name="id">
		   					<xsl:value-of select="@id"></xsl:value-of>
		   				</xsl:attribute>
						<xsl:attribute name="name">
		   					<xsl:value-of select="label"></xsl:value-of>
		   				</xsl:attribute>
						<xsl:attribute name="processRef"><xsl:value-of select="$id"></xsl:value-of>
		   				</xsl:attribute>
					</participant>
				</xsl:for-each>
			</collaboration>
		</xsl:if>
	</xsl:template>

	<xsl:template name="diagram">
		<xsl:call-template name="diagram_block">
			<xsl:with-param name="elName" select="//bg:HPool"></xsl:with-param>
		</xsl:call-template>
		<xsl:call-template name="diagram_block">
			<xsl:with-param name="elName" select="//bg:HLane"></xsl:with-param>
		</xsl:call-template>
		<xsl:call-template name="diagram_block">
			<xsl:with-param name="elName" select="//bg:VPool"></xsl:with-param>
		</xsl:call-template>
		<xsl:call-template name="diagram_block">
			<xsl:with-param name="elName" select="//bg:VLane"></xsl:with-param>
		</xsl:call-template>
		<xsl:call-template name="diagram_block">
			<xsl:with-param name="elName" select="//bg:StartEvent"></xsl:with-param>
		</xsl:call-template>
		<xsl:call-template name="diagram_block">
			<xsl:with-param name="elName" select="//bg:EndEvent"></xsl:with-param>
		</xsl:call-template>
		<xsl:call-template name="diagram_block">
			<xsl:with-param name="elName" select="//bg:Task"></xsl:with-param>
		</xsl:call-template>
		<xsl:call-template name="diagram_block">
			<xsl:with-param name="elName" select="//bg:SubProcess"></xsl:with-param>
		</xsl:call-template>
		<xsl:call-template name="diagram_block">
			<xsl:with-param name="elName" select="//bg:Gateway"></xsl:with-param>
		</xsl:call-template>
	</xsl:template>

	<xsl:template name="transition">
		<xsl:for-each select="//bg:SequenceFlow">
			<bpmndi:BPMNEdge>
				<xsl:attribute name="bpmnElement"><xsl:value-of select="@id"></xsl:value-of></xsl:attribute>
				<xsl:attribute name="id">BPMNEdge_<xsl:value-of select="@id"></xsl:value-of></xsl:attribute>

				<xsl:variable name="startPort" select="@startPort"></xsl:variable>
				<xsl:variable name="endPort" select="@endPort"></xsl:variable>

				<xsl:variable name="parentName" select="name(//ciied:Port[@id=$startPort]/parent::*/parent::*/parent::*)"></xsl:variable>
				<xsl:variable name="fport" select="//ciied:Port[@id=$startPort]"></xsl:variable>
				<xsl:variable name="fromFlowEl" select="$fport/parent::*/parent::*"></xsl:variable>
				<xsl:variable name="fx" select="$fromFlowEl/@x"></xsl:variable>
				<xsl:variable name="fy" select="$fromFlowEl/@y"></xsl:variable>
				<xsl:variable name="fW" select="$fromFlowEl/@width"></xsl:variable>
				<xsl:variable name="fH" select="$fromFlowEl/@height"></xsl:variable>
				<xsl:variable name="fName" select="name($fromFlowEl)"></xsl:variable>
				<xsl:variable name="fportType" select="$fport/@portType"></xsl:variable>
				<xsl:variable name="fDirX" select="$fport/@x"></xsl:variable>
				<xsl:variable name="fDirY" select="$fport/@y"></xsl:variable>
				<xsl:variable name="fHOffset" select="$fport/@horizontalOffset"></xsl:variable>
				<xsl:variable name="fVOffset" select="$fport/@verticalOffset"></xsl:variable>


				<xsl:variable name="tport" select="//ciied:Port[@id=$endPort]"></xsl:variable>
				<xsl:variable name="toFlowEl" select="$tport/parent::*/parent::*"></xsl:variable>
				<xsl:variable name="tx" select="$toFlowEl/@x"></xsl:variable>
				<xsl:variable name="ty" select="$toFlowEl/@y"></xsl:variable>
				<xsl:variable name="tW" select="$toFlowEl/@width"></xsl:variable>
				<xsl:variable name="tH" select="$toFlowEl/@height"></xsl:variable>
				<xsl:variable name="tName" select="name($toFlowEl)"></xsl:variable>
				<xsl:variable name="tportType" select="$tport/@portType"></xsl:variable>
				<xsl:variable name="tDirX" select="$tport/@x"></xsl:variable>
				<xsl:variable name="tDirY" select="$tport/@y"></xsl:variable>
				<xsl:variable name="tHOffset" select="$tport/@horizontalOffset"></xsl:variable>
				<xsl:variable name="tVOffset" select="$tport/@verticalOffset"></xsl:variable>
				
				<xsl:variable name="shapeType" select="./shapeType"></xsl:variable>
				
				<xsl:variable name="intermediatePoints">
					<xsl:for-each select="./intermediatePoints/child::*[name(.)='fg:Point']">
						<xsl:value-of select="concat(@x,':',@y,',')"></xsl:value-of>
					</xsl:for-each>
				</xsl:variable>
				
				<xsl:choose>
					<!--节点为子流程节点 -->
					<xsl:when test="$parentName='bg:SubProcess'">
						<xsl:variable name="fParent" select="//ciied:Port[@id=$startPort]/parent::*/parent::*/parent::*"></xsl:variable>
						<xsl:variable name="tParent" select="//ciied:Port[@id=$endPort]/parent::*/parent::*/parent::*"></xsl:variable>
						<xsl:variable name="fx1" select="calc:add(string($fx),string($fParent/@x))"></xsl:variable>
						<xsl:variable name="fy1" select="calc:add(string($fy),string($fParent/@y))"></xsl:variable>

						<xsl:variable name="tx1" select="calc:add(string($tx),string($tParent/@x))"></xsl:variable>
						<xsl:variable name="ty1" select="calc:add(string($ty),string($tParent/@y))"></xsl:variable>

						<xsl:variable name="fx" select="calc:add(string($fx1),'10')"></xsl:variable>
						<xsl:variable name="fy" select="calc:add(string($fy1),'28')"></xsl:variable>

						<xsl:variable name="tx" select="calc:add(string($tx1),'10')"></xsl:variable>
						<xsl:variable name="ty" select="calc:add(string($ty1),'28')"></xsl:variable>
						<!-- 用于解决内部子流程中分支、同步网关线条乱画的问题  这里 是解决只有一层内嵌子流程的情况-->
						<xsl:variable name="intermediatePoints">
							<xsl:for-each select="./intermediatePoints/child::*[name(.)='fg:Point']">
								<xsl:value-of select="concat(calc:add(string($fParent/@x),calc:add('10',@x)),':',calc:add(string($fParent/@y),calc:add('28',@y)),',')"></xsl:value-of>
							</xsl:for-each>
						</xsl:variable>
						<!-- 通过迭代 处理多层内嵌子流程的情况-->
						<xsl:call-template name="setSubProcessEdges">  
		                    <xsl:with-param name="startPort" select="$startPort" />  
		                    <xsl:with-param name="endPort" select="$endPort" />  
		                    <xsl:with-param name="fParent" select="$fParent" />  
		                    <xsl:with-param name="tParent" select="$tParent" />  
		                    <xsl:with-param name="fx" select="$fx" />  
		                    <xsl:with-param name="fy" select="$fy" />  
		                    <xsl:with-param name="tx" select="$tx" />  
		                    <xsl:with-param name="ty" select="$ty" />  
		                     <xsl:with-param name="shapeType" select="$shapeType" />  
		                     <xsl:with-param name="fName" select="$fName" />  
		                     <xsl:with-param name="fW" select="$fW" />  
		                     <xsl:with-param name="fH" select="$fH" />  
		                     <xsl:with-param name="fportType" select="$fportType" />  
		                     <xsl:with-param name="fDirX" select="$fDirX" />  
		                     <xsl:with-param name="fDirY" select="$fDirY" />  
		                     <xsl:with-param name="fHOffset" select="fHOffset" />  
		                     <xsl:with-param name="fVOffset" select="$fVOffset" />  
		                     <xsl:with-param name="tName" select="$tName" />  
		                     <xsl:with-param name="tW" select="$tW" />  
		                     <xsl:with-param name="tH" select="$tH" />  
		                     <xsl:with-param name="tportType" select="$tportType" />  
		                     <xsl:with-param name="tDirX" select="$tDirX" />  
		                     <xsl:with-param name="tDirY" select="$tDirY" />  
		                     <xsl:with-param name="tHOffset" select="$tHOffset" />  
		                     <xsl:with-param name="tVOffset" select="$tVOffset" />  
		                     <xsl:with-param name="intermediatePoints" select="$intermediatePoints" />  
		                 </xsl:call-template> 
					</xsl:when>
					<xsl:when test="$parentName='bg:HLane'">
						<xsl:variable name="fParent" select="$fromFlowEl/parent::*"></xsl:variable>
						<xsl:variable name="tParent" select="$toFlowEl/parent::*"></xsl:variable>
						<xsl:variable name="fGrandparent" select="$fParent/parent::*"></xsl:variable>
						<xsl:variable name="tGrandparent" select="$tParent/parent::*"></xsl:variable>
						
						<xsl:variable name="fParentXX" >
							<xsl:choose>
								<!-- 用于计算在两个泳道之间连线的x算法   -->
								<xsl:when test="string($fParent/@y)!= string($tParent/@y)">
									<xsl:value-of select="'0'"></xsl:value-of>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="string($fParent/@graphMarginLeft)"></xsl:value-of>
								</xsl:otherwise>
							</xsl:choose>
						</xsl:variable>
						
						<xsl:variable name="fParentYY" >
							<xsl:choose>
								<!-- 用于计算在两个泳道之间连线的y算法   -->
								<xsl:when test="string($fParent/@y)!='' and string($fParent/@y)=string($tParent/@y) ">
									<xsl:value-of select="string($fParent/@height)"></xsl:value-of>
								</xsl:when>
								<xsl:when test="(string($fParent/@y)!='' or string($tParent/@y)!='') and string($fParent/@y)!=string($tParent/@y) ">
									<xsl:value-of select="'2'"></xsl:value-of>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="'0'"></xsl:value-of>
								</xsl:otherwise>
							</xsl:choose>
						</xsl:variable>
						<!-- 用于横向泳道中解决内部子流程中分支、同步网关线条乱画的问题  这里 是解决只有一层内嵌子流程的情况-->
					 	<xsl:variable name="intermediatePoints">
							<xsl:for-each select="./intermediatePoints/child::*[name(.)='fg:Point']">
								<xsl:value-of select="concat(calc:add(calc:add(string($fGrandparent/@graphMarginLeft),'0'),calc:add('0',calc:add(calc:add($fParentXX,calc:add('0',string($fGrandparent/@left))),calc:add('0',@x)))),':',calc:add(string($fGrandparent/@top),calc:add('0',calc:add($fParentYY,@y))),',')"></xsl:value-of>
							</xsl:for-each>
						</xsl:variable>    
						<xsl:variable name="fx1" select="calc:add(string($fx),string($fGrandparent/@x))"></xsl:variable>
						<xsl:variable name="fx" select="calc:add(string($fx1),'40')"></xsl:variable>

						<!-- 计算连线前端组件的Y坐标 -->
						<xsl:variable name="fGrandparentY" select="$fGrandparent/@y"></xsl:variable>
						<xsl:variable name="preSibsHeight">
							<xsl:for-each select="$fParent/preceding-sibling::*[name(.)='bg:HLane']">
								<xsl:value-of select="concat(@height,',')"></xsl:value-of>
							</xsl:for-each>
						</xsl:variable>
						<xsl:variable name="fy">
							<xsl:value-of select="calc:add(calc:accumulate($preSibsHeight,$fGrandparentY),string($fromFlowEl/@y))"></xsl:value-of>
						</xsl:variable>
						<xsl:variable name="tx1" select="calc:add(string($tx),string($tGrandparent/@x))"></xsl:variable>
						<xsl:variable name="tx" select="calc:add(string($tx1),'40')"></xsl:variable>

						<!-- 计算连线末端组件的Y坐标 -->
						<xsl:variable name="tGrandparentY" select="$tGrandparent/@y"></xsl:variable>
						<xsl:variable name="tPreSibsHeight">
							<xsl:for-each select="$tParent/preceding-sibling::*[name(.)='bg:HLane']">
								<xsl:value-of select="@height"></xsl:value-of>
								,
							</xsl:for-each>
						</xsl:variable>
						<xsl:variable name="ty">
							<xsl:value-of select="calc:add(calc:accumulate($tPreSibsHeight,$tGrandparentY),string($toFlowEl/@y))"></xsl:value-of>
						</xsl:variable>
<!-- 						<xsl:value-of select="calc:calc($fName,$fx,$fy,$fW,$fH,$fHOffset,$fVOffset,$fDirX,$fDirY,$tName,$tx,$ty,$tW,$tH,$tHOffset,$tVOffset,$tDirX,$tDirY )" /> -->
						<xsl:value-of select="calc:calc($shapeType,
									$fName,$fx,$fy,$fW,$fH,
									$fportType,$fDirX,$fDirY,$fHOffset,$fVOffset,
									$tName,$tx,$ty,$tW,$tH,
									$tportType,$tDirX,$tDirY,$tHOffset,$tVOffset,
									$intermediatePoints )" />	
					</xsl:when>
					<xsl:when test="$parentName='bg:VLane'">
						<xsl:variable name="fParent" select="$fromFlowEl/parent::*"></xsl:variable>
						<xsl:variable name="tParent" select="$toFlowEl/parent::*"></xsl:variable>
						<xsl:variable name="fGrandparent" select="$fParent/parent::*"></xsl:variable>
						<xsl:variable name="tGrandparent" select="$tParent/parent::*"></xsl:variable>
						
						<xsl:variable name="fParentXX" >
							<xsl:choose>
								<!--用于计算在两个泳道之间连线的x算法   -->
								<xsl:when test="string($fParent/@x)=string($tParent/@x)">
									<xsl:value-of select="string($fParent/@width)"></xsl:value-of>
								</xsl:when>
								<xsl:when test="(string($fParent/@x)!='' or string($tParent/@x)!='') and string($tParent/@x)!=string($tParent/@x) ">
									<xsl:value-of select="'2'"></xsl:value-of>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="'0'"></xsl:value-of>
								</xsl:otherwise>
							</xsl:choose>
						</xsl:variable>
						<xsl:variable name="fParentYY" >
							<xsl:choose>
								<!--用于计算在两个泳道之间连线的y算法   -->
								<xsl:when test=" string($fParent/@x)!= string($tParent/@x) ">
									<xsl:value-of select="'0'"></xsl:value-of>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="string($fParent/@graphMarginTop)"></xsl:value-of>
								</xsl:otherwise>
							</xsl:choose>
						</xsl:variable>
						<!-- 用于纵向泳道中解决内部子流程中分支、同步网关线条乱画的问题  这里 是解决只有一层内嵌子流程的情况-->
					 	<xsl:variable name="intermediatePoints">
							<xsl:for-each select="./intermediatePoints/child::*[name(.)='fg:Point']">
								<xsl:value-of select="concat(calc:add(string($fGrandparent/@left),calc:add('0',calc:add($fParentXX,@x))),':',calc:add(calc:add(string($fGrandparent/@graphMarginTop),'0'),calc:add('0',calc:add(calc:add($fParentYY,calc:add('0',string($fGrandparent/@top))),calc:add('0',@y)))),',')"></xsl:value-of>
							</xsl:for-each>
						</xsl:variable>  
						<xsl:variable name="fy1" select="calc:add(string($fy),string($fGrandparent/@y))"></xsl:variable>
						<xsl:variable name="fy" select="calc:add(string($fy1),'40')"></xsl:variable>

						<!-- 计算连线前端组件的X坐标 -->
						<xsl:variable name="fGrandparentX" select="$fGrandparent/@x"></xsl:variable>
						<xsl:variable name="preSibsWidth">
							<xsl:for-each select="$fParent/preceding-sibling::*[name(.)='bg:VLane']">
								<xsl:value-of select="concat(@width,',')"></xsl:value-of>
							</xsl:for-each>
						</xsl:variable>
						<xsl:variable name="fx">
							<xsl:value-of select="calc:add(calc:accumulate($preSibsWidth,$fGrandparentX),string($fromFlowEl/@x))"></xsl:value-of>
						</xsl:variable>
						<xsl:variable name="ty1" select="calc:add(string($ty),string($tGrandparent/@y))"></xsl:variable>
						<xsl:variable name="ty" select="calc:add(string($ty1),'40')"></xsl:variable>
						
						<!-- 计算连线末端组件的X坐标 -->
						<xsl:variable name="tGrandparentX" select="$tGrandparent/@x"></xsl:variable>
						<xsl:variable name="tPreSibsWidth">
							<xsl:for-each select="$tParent/preceding-sibling::*[name(.)='bg:VLane']">
								<xsl:value-of select="concat(@width,',')"></xsl:value-of>
							</xsl:for-each>
						</xsl:variable>
						<xsl:variable name="tx">
							<xsl:value-of select="calc:add(calc:accumulate($tPreSibsWidth,$tGrandparentX),string($toFlowEl/@x))"></xsl:value-of>
						</xsl:variable>
<!-- 						<xsl:value-of select="calc:calc($fName,$fx,$fy,$fW,$fH,$fHOffset,$fVOffset,$fDirX,$fDirY,$tName,$tx,$ty,$tW,$tH,$tHOffset,$tVOffset,$tDirX,$tDirY )" /> -->
						<xsl:value-of select="calc:calc($shapeType,
									$fName,$fx,$fy,$fW,$fH,
									$fportType,$fDirX,$fDirY,$fHOffset,$fVOffset,
									$tName,$tx,$ty,$tW,$tH,
									$tportType,$tDirX,$tDirY,$tHOffset,$tVOffset,
									$intermediatePoints )" />	
					</xsl:when>
					<xsl:otherwise>
<!-- 						<xsl:value-of select="calc:calc($fName,$fx,$fy,$fW,$fH,$fHOffset,$fVOffset,$fDirX,$fDirY,$tName,$tx,$ty,$tW,$tH,$tHOffset,$tVOffset,$tDirX,$tDirY )" /> -->
					<xsl:value-of select="calc:calc($shapeType,
								$fName,$fx,$fy,$fW,$fH,
								$fportType,$fDirX,$fDirY,$fHOffset,$fVOffset,
								$tName,$tx,$ty,$tW,$tH,
								$tportType,$tDirX,$tDirY,$tHOffset,$tVOffset,
								$intermediatePoints )" />	
					</xsl:otherwise>
				</xsl:choose>
				<bpmndi:BPMNLabel>
					<xsl:if test="./label">
						<xsl:variable name="label" select="./label"></xsl:variable>
						<xsl:value-of select="calc:calcLabelPosition($label)" />
					</xsl:if>
				</bpmndi:BPMNLabel>
			</bpmndi:BPMNEdge>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="diagram_block">
		<xsl:param name="elName" />
		<xsl:for-each select="$elName">
			<bpmndi:BPMNShape>
				<xsl:variable name="name" select="name(.)"></xsl:variable>
				<xsl:variable name="parentName" select="name(./parent::*)"></xsl:variable>
				<xsl:variable name="grandParentName" select="name(./parent::*/parent::*)"></xsl:variable>
				<xsl:attribute name="bpmnElement">
					<xsl:value-of select="@id"></xsl:value-of>
				</xsl:attribute>
				<xsl:attribute name="id">BPMNShape_<xsl:value-of select="@id"></xsl:value-of>
				</xsl:attribute>

				<xsl:variable name="x" select="calc:nan2Zero(@x)"></xsl:variable>
				<xsl:variable name="y" select="calc:nan2Zero(@y)"></xsl:variable>
				<xsl:if test="contains($name,'HPool') or contains($name,'HLane')">
					<xsl:attribute name="isHorizontal">
						<xsl:value-of select="'true'"></xsl:value-of>
					</xsl:attribute>
				</xsl:if>
				<xsl:if test="contains($name,'VPool') or contains($name,'VLane')">
					<xsl:attribute name="isHorizontal">
						<xsl:value-of select="'false'"></xsl:value-of>
					</xsl:attribute>
				</xsl:if>

				<omgdc:Bounds>
					<!-- Height and Width -->
					<xsl:choose>
						<xsl:when test="$name='bg:StartEvent' or $name='bg:EndEvent'">
							<xsl:attribute name="width">
								<xsl:value-of select="@width"></xsl:value-of>
							</xsl:attribute>
							<xsl:attribute name="height">
								<xsl:value-of select="@width"></xsl:value-of>
							</xsl:attribute>
						</xsl:when>
						<xsl:when test="$name='bg:VLane'">
							<xsl:attribute name="width">
								<xsl:value-of select="@width"></xsl:value-of>
							</xsl:attribute>
							<xsl:attribute name="height">
								<xsl:value-of select="calc:add(string(parent::*/@height),'-20')"></xsl:value-of>
							</xsl:attribute>
						</xsl:when>
						<xsl:when test="$name='bg:HPool'">
							<xsl:attribute name="width">
								<xsl:value-of select="@width"></xsl:value-of>
							</xsl:attribute>
							<xsl:attribute name="height">
								<xsl:variable name="lanes">
									<xsl:for-each select="child::*[name(.)='bg:HLane']">
										<xsl:value-of select="concat(name(.),',')">
									</xsl:value-of></xsl:for-each>
								</xsl:variable>
								<xsl:value-of select="calc:add(string(@height),string(-@gap*(calc:splitLength($lanes,',') -1)))"></xsl:value-of>
							</xsl:attribute>
						</xsl:when>
						<xsl:when test="$name='bg:HLane'">
							<xsl:attribute name="width">
								<xsl:value-of select="calc:add(string(parent::*/@width),'-20')"></xsl:value-of>
							</xsl:attribute>
							<xsl:attribute name="height">
								<xsl:value-of select="@height"></xsl:value-of>
							</xsl:attribute>
						</xsl:when>
						<xsl:when test="$name='bg:VPool'">
							<xsl:attribute name="width">
								<xsl:variable name="lanes">
									<xsl:for-each select="child::*[name(.)='bg:VLane']">
										<xsl:value-of select="concat(name(.),',')">
									</xsl:value-of></xsl:for-each>
								</xsl:variable>
								<xsl:value-of select="calc:add(string(@width),string(-@gap*(calc:splitLength($lanes,',') -1)))"></xsl:value-of>
							</xsl:attribute>
							<xsl:attribute name="height">
								<xsl:value-of select="@height"></xsl:value-of>
							</xsl:attribute>
						</xsl:when>
						<xsl:otherwise>
							<xsl:attribute name="height">
								<xsl:value-of select="@height"></xsl:value-of>
							</xsl:attribute>
							<xsl:attribute name="width">
								<xsl:value-of select="@width"></xsl:value-of>
							</xsl:attribute>
						</xsl:otherwise>
					</xsl:choose>
					<!-- X , Y -->
					<xsl:choose>
						<xsl:when test="$name='bg:HLane'">
							<xsl:attribute name="x">
								<xsl:value-of select="calc:add(string(parent::*/@x) ,'20')"></xsl:value-of>
							</xsl:attribute>
							<xsl:attribute name="y">
								<xsl:variable name="parentY">
									<xsl:value-of select="parent::*/@y"></xsl:value-of>
								</xsl:variable>
								<xsl:variable name="preSibsHeight">
									<xsl:for-each select="preceding-sibling::*[name(.)='bg:HLane']">
										<xsl:value-of select="concat(@height,',')"></xsl:value-of>
									</xsl:for-each>
								</xsl:variable>
								<xsl:value-of select="calc:accumulate($preSibsHeight,$parentY)"></xsl:value-of>
							</xsl:attribute>
						</xsl:when>
						<xsl:when test="$name='bg:VLane'">
							<xsl:attribute name="x">
								<xsl:variable name="parentX">
									<xsl:value-of select="parent::*/@x"></xsl:value-of>
								</xsl:variable>
								<xsl:variable name="preSibsWidth">
									<xsl:for-each select="preceding-sibling::*[name(.)='bg:VLane']">
										<xsl:value-of select="concat(@width,',')"></xsl:value-of>
									</xsl:for-each>
								</xsl:variable>
								<xsl:value-of select="calc:accumulate($preSibsWidth,$parentX)"></xsl:value-of>
							</xsl:attribute>
							<xsl:attribute name="y">
								<xsl:value-of select="calc:add(string(parent::*/@y) ,'20')"></xsl:value-of>
							</xsl:attribute>
						</xsl:when>
						<xsl:when test="$parentName='bg:HLane'">
							<xsl:variable name="grandparent" select="./parent::*/parent::*"></xsl:variable>
							<xsl:variable name="x1" select="calc:add(string($x),string($grandparent/@x))"></xsl:variable>
							<xsl:variable name="x" select="calc:add(string($x1),'40')"></xsl:variable>
							<xsl:attribute name="x"><xsl:value-of select="$x"></xsl:value-of></xsl:attribute>
							<xsl:attribute name="y">
								<xsl:variable name="grandparentY" select="$grandparent/@y"></xsl:variable>
								<xsl:variable name="preSibsHeight">
									<xsl:for-each select="parent::*/preceding-sibling::*[name(.)='bg:HLane']">
										<xsl:value-of select="@height"></xsl:value-of>,</xsl:for-each>
								</xsl:variable>
								<xsl:value-of select="calc:add(calc:accumulate($preSibsHeight,$grandparentY),@y)"></xsl:value-of>
							</xsl:attribute>
						</xsl:when>
						<xsl:when test="$parentName='bg:VLane'">
							<xsl:variable name="grandparent" select="./parent::*/parent::*"></xsl:variable>
							<xsl:variable name="y1" select="calc:add(string($y),string($grandparent/@y))"></xsl:variable>
							<xsl:variable name="y" select="calc:add(string($y1),'40')"></xsl:variable>
							<xsl:attribute name="y">
								<xsl:value-of select="$y"></xsl:value-of>
							</xsl:attribute>
							<xsl:attribute name="x">
								<xsl:variable name="grandparentX" select="$grandparent/@x"></xsl:variable>
								<xsl:variable name="preSibsWidth">
									<xsl:for-each select="parent::*/preceding-sibling::*[name(.)='bg:VLane']">
										<xsl:value-of select="concat(@width,',')"></xsl:value-of>
									</xsl:for-each>
								</xsl:variable>
								<xsl:value-of select="calc:add(calc:accumulate($preSibsWidth,$grandparentX),@x)"></xsl:value-of>
							</xsl:attribute>
						</xsl:when>
						<xsl:when test="$parentName='bg:SubProcess'">
							<xsl:variable name="parent" select="./parent::*"></xsl:variable>
							<xsl:variable name="baseGrandParent" select="./parent::*/parent::*/parent::*"></xsl:variable>
							
							<xsl:call-template name="setSubProcessParam">  
			                    <xsl:with-param name="x" select="calc:add($x,'0')" />  
			                    <xsl:with-param name="y" select="$y" />  
			                    <xsl:with-param name="parent" select="$parent" />  
			                    <xsl:with-param name="grandParentName" select="$grandParentName" />  
			                    <xsl:with-param name="baseGrandParent" select="$baseGrandParent" />  
			                 </xsl:call-template> 
						</xsl:when>

						<xsl:otherwise>
							<xsl:attribute name="x"><xsl:value-of select="$x"></xsl:value-of></xsl:attribute>
							<xsl:attribute name="y"><xsl:value-of select="$y"></xsl:value-of></xsl:attribute>
						</xsl:otherwise>
					</xsl:choose>

				</omgdc:Bounds>

			</bpmndi:BPMNShape>
		</xsl:for-each>
	</xsl:template>
<!-- 迭代计算内嵌子流程连线的算法 -->
<xsl:template name="setSubProcessEdges">
	<xsl:param name="startPort"/>  
	<xsl:param name="endPort"/>  
	<xsl:param name="fParent"/>  
	<xsl:param name="tParent"/>  
	<xsl:param name="fx"/>  
	<xsl:param name="fy"/>  
	<xsl:param name="tx"/>  
	<xsl:param name="ty"/>  
	<xsl:param name="shapeType"/>  
	<xsl:param name="fName"/>  
	<xsl:param name="fW"/>  
	<xsl:param name="fH"/>  
	<xsl:param name="fportType"/>  
	<xsl:param name="fDirX"/>  
	<xsl:param name="fDirY"/>  
	<xsl:param name="fHOffset"/>  
	<xsl:param name="fVOffset"/>  
	<xsl:param name="tName"/>  
	<xsl:param name="tW"/>  
	<xsl:param name="tH"/>  
	<xsl:param name="tportType"/>  
	<xsl:param name="tDirX"/>  
	<xsl:param name="tDirY"/>  
	<xsl:param name="tHOffset"/>  
	<xsl:param name="tVOffset"/>  
	<xsl:param name="intermediatePoints"/>  
		<xsl:choose>
			<!-- 如果子过程在水平泳道内 -->
			<xsl:when test="name($fParent/parent::*)='bg:HLane'">
				<xsl:variable name="fGrandparent" select="$fParent/parent::*"></xsl:variable>
				<xsl:variable name="tGrandparent" select="$tParent/parent::*"></xsl:variable>
		
				<xsl:variable name="fGreatGrandparent" select="$fGrandparent/parent::*"></xsl:variable>
				<xsl:variable name="tGreatGrandparent" select="$tGrandparent/parent::*"></xsl:variable>
				
				<!-- 用于获取当前泳道前面的泳道的y坐标  以便于计算连线的y轴偏移值 -->
				<xsl:variable name="getAllPreVlans">
					<xsl:for-each select="$fParent/parent::*/preceding-sibling::*[name(.)='bg:HLane']">
						<xsl:choose>
							<xsl:when test="@y!='' ">
								<xsl:value-of select="concat(calc:max(@y,@height),',')"></xsl:value-of>
							</xsl:when>
						</xsl:choose>
					</xsl:for-each>
				</xsl:variable>
				<xsl:variable name="getAllAfterVlans">
					<xsl:for-each select="$fParent/parent::*/following-sibling::*[name(.)='bg:HLane']">
						<xsl:choose>
							<xsl:when test="@y!='' ">
								<xsl:value-of select="concat(@y,',')"></xsl:value-of>
							</xsl:when>
						</xsl:choose>
					</xsl:for-each>
				</xsl:variable>
				<!--计算连线的x轴偏移值 -->
				<xsl:variable name="yFloat">
					<xsl:choose>
						<xsl:when test="$getAllPreVlans!=''">
							<xsl:value-of select="calc:getPreVlanSumX($getAllPreVlans)"></xsl:value-of>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="'0'"></xsl:value-of>
						</xsl:otherwise>
					</xsl:choose>	
					
				</xsl:variable>
				
				<!--用于计算在横向泳道的x的偏移量算法   -->
				<xsl:variable name="fParentXX" >
					<xsl:choose>
						<xsl:when test="string($fGrandparent/@y)!='' and string($tGrandparent/@y)!='' and name(./parent::*/parent::*)!='bg:SubProcess'">
							<xsl:value-of select="'0'"></xsl:value-of>
						</xsl:when>
						<xsl:when test="(string($fGrandparent/@y)!='' and string($tGrandparent/@y)!='' and name(./parent::*/parent::*)='bg:SubProcess') or (string($fGrandparent/@y)='' and string($tGrandparent/@y)='' and name(./parent::*/parent::*)='bg:SubProcess')">
							<xsl:value-of select="calc:add(string($fGrandparent/@graphMarginLeft),calc:add(-string($fGrandparent/@poolMarginLeft),calc:add(string(./parent::*/@x),'-2')))"></xsl:value-of>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="'0'"></xsl:value-of>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:variable>
					<!--用于计算在横向泳道的y的偏移量算法   -->
				<xsl:variable name="fParentYY" >
					<xsl:choose>
						<xsl:when test="string($fGrandparent/@y)!='' and string($tGrandparent/@y)!='' and name(./parent::*/parent::*)!='bg:SubProcess'"><!-- string($fGrandparent/@graphMarginLeft) -->
							<xsl:value-of select="calc:add(string($fGrandparent/@y),'2')"></xsl:value-of>
						</xsl:when>
						<xsl:when test="string($fGrandparent/@y)!='' and string($tGrandparent/@y)!='' and name(./parent::*/parent::*)='bg:SubProcess'">
							<xsl:value-of select="calc:add(string(./parent::*/@y),calc:add(string(./parent::*/parent::*/@y),calc:add(string($fParent/parent::*/@poolMarginTop),calc:add(calc:min(string($fGrandparent/@y),string($fGrandparent/@height)),calc:add($yFloat,'-2')))))"></xsl:value-of>
						</xsl:when>
						<xsl:when test="string($fGrandparent/@y)='' and string($tGrandparent/@y)=''   and name(./parent::*/parent::*)='bg:SubProcess'">
							<xsl:value-of select="calc:add(string(./parent::*/@y),calc:add(string(./parent::*/parent::*/@y),calc:add(string($fParent/parent::*/@poolMarginTop),calc:add(calc:min(string($fGrandparent/@y),string($fGrandparent/@height)),calc:add($yFloat,'-2')))))"></xsl:value-of>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="'0'"></xsl:value-of>
						</xsl:otherwise>
					</xsl:choose> 
				</xsl:variable>
				
				<!-- <xsl:variable name="concatYY" select="calc:add(calc:add(string($fGreatGrandparent/@top),'0'),calc:add(string($fParent/parent::*/@poolMarginTop),calc:add(string($fParentYY),calc:add('0','0'))))"></xsl:variable>
				<xsl:variable name="concatXX" select="calc:add(calc:add(string($fGreatGrandparent/@graphMarginLeft),string($fGreatGrandparent/@poolMarginLeft)),calc:add('0',calc:add(calc:add(string($fParent/parent::*/@graphMarginLeft),calc:add(string($fParent/parent::*/@poolMarginLeft),string($fGreatGrandparent/@left))),calc:add('8',$fParentXX))))"></xsl:variable>
				<xsl:variable name="intermediatePoints">
						<xsl:for-each select="./intermediatePoints/child::*[name(.)='fg:Point']">string($fGreatGrandparent/@left)  string($fGreatGrandparent/@graphMarginTop)
							<xsl:value-of select="concat(calc:add($concatXX,calc:add(string($fParent/@x),@x)),':',calc:add($concatYY,calc:add(string($fParent/@y),@y)),',')"></xsl:value-of>
						</xsl:for-each>
				</xsl:variable>
				 -->
				
				<xsl:variable name="concatXX" select="calc:add(calc:add(string($fGreatGrandparent/@graphMarginLeft),string($fGreatGrandparent/@poolMarginLeft)),calc:add('0',calc:add(calc:add('0',calc:add(string($fParent/parent::*/@poolMarginLeft),string($fGreatGrandparent/@left))),calc:add('8',$fParentXX))))"></xsl:variable>
				<xsl:variable name="concatYY" select="calc:add(calc:add(string($fGreatGrandparent/@top),string($fGreatGrandparent/@graphMarginTop)),calc:add(string($fParent/parent::*/@poolMarginTop),calc:add(string($fParentYY),calc:add(string($fParent/@y),'-2'))))"></xsl:variable> 
				<xsl:variable name="intermediatePoints">
						<xsl:for-each select="./intermediatePoints/child::*[name(.)='fg:Point']"><!-- string($fParent/@y) -->
							<xsl:value-of select="concat(calc:add($concatXX,calc:add(string($fParent/@x),@x)),':',calc:add($concatYY,calc:add('0',@y)),',')"></xsl:value-of>
						</xsl:for-each>
				</xsl:variable>
		
				<xsl:variable name="fx1" select="calc:add(string($fx),string($fGreatGrandparent/@x))"></xsl:variable>
				<xsl:variable name="fx" select="calc:add(string($fx1),'40')"></xsl:variable>
		
				<!-- 计算连线前端组件的Y坐标 -->
				<xsl:variable name="fGreatGrandparentY" select="$fGreatGrandparent/@y"></xsl:variable>
				<xsl:variable name="preSibsHeight">
					<xsl:for-each select="$fGrandparent/preceding-sibling::*[name(.)='bg:HLane']">
						<xsl:value-of select="concat(@height,',')"></xsl:value-of>
					</xsl:for-each>
				</xsl:variable>
				<xsl:variable name="fy">
					<xsl:value-of select="calc:add(calc:accumulate($preSibsHeight,$fGreatGrandparentY),$fy)"></xsl:value-of>
				</xsl:variable>
				
				<xsl:variable name="tx1" select="calc:add(string($tx),string($tGreatGrandparent/@x))"></xsl:variable>
				<xsl:variable name="tx" select="calc:add(string($tx1),'40')"></xsl:variable>
		
				<!-- 计算连线末端组件的Y坐标 -->
				<xsl:variable name="tGreatGrandparentY" select="$tGreatGrandparent/@y"></xsl:variable>
				<xsl:variable name="tPreSibsHeight">
					<xsl:for-each select="$tGrandparent/preceding-sibling::*[name(.)='bg:HLane']">
						<xsl:value-of select="concat(@height,',')"></xsl:value-of>
					</xsl:for-each>
				</xsl:variable>
		
				<xsl:variable name="ty">
					<xsl:value-of select="calc:add(calc:accumulate($tPreSibsHeight,$tGreatGrandparentY),string($ty))"></xsl:value-of>
				</xsl:variable>
				
				<xsl:value-of select="calc:calc($shapeType,
					$fName,$fx,$fy,$fW,$fH,
					$fportType,$fDirX,$fDirY,$fHOffset,$fVOffset,
					$tName,$tx,$ty,$tW,$tH,
					$tportType,$tDirX,$tDirY,$tHOffset,$tVOffset,
					$intermediatePoints )" />										
			</xsl:when>
		
			<xsl:when test="name($fParent/parent::*)='bg:VLane'">
				<xsl:variable name="fGrandparent" select="$fParent/parent::*"></xsl:variable>
				<xsl:variable name="tGrandparent" select="$tParent/parent::*"></xsl:variable>
		
				<xsl:variable name="fGreatGrandparent" select="$fGrandparent/parent::*"></xsl:variable>
				<xsl:variable name="tGreatGrandparent" select="$tGrandparent/parent::*"></xsl:variable>
		
				<xsl:variable name="fy1" select="calc:add(string($fy),string($fGreatGrandparent/@y))"></xsl:variable>
				<xsl:variable name="fy" select="calc:add(string($fy1),'40')"></xsl:variable>
				<!-- 用于获取当前泳道前面的泳道的x坐标  以便于计算连线的x轴偏移值 -->
				<xsl:variable name="getAllPreVlans">
					<xsl:for-each select="$fParent/parent::*/preceding-sibling::*[name(.)='bg:VLane']">
						<xsl:value-of select="concat(@x,',')"></xsl:value-of>
					</xsl:for-each>
				</xsl:variable>
				<xsl:variable name="getAllAfterVlans">
					<xsl:for-each select="$fParent/parent::*/following-sibling::*[name(.)='bg:VLane']">
						<xsl:choose>
							<xsl:when test="@x!=''">
								<xsl:value-of select="concat(@x,',')"></xsl:value-of>
							</xsl:when>
						</xsl:choose>
					</xsl:for-each>
				</xsl:variable>
				<!--计算连线的x轴偏移值 -->
				<xsl:variable name="xFloat">
					<xsl:choose>
						<xsl:when test="$getAllPreVlans!=''">
							<xsl:value-of select="calc:getPreVlanSumX($getAllPreVlans)"></xsl:value-of>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="'0'"></xsl:value-of>
						</xsl:otherwise>
					</xsl:choose>	
					
				</xsl:variable>
					<!--用于计算在 纵向泳道的x的偏移量算法   -->
				<xsl:variable name="fParentXX" >
					<xsl:choose>
						<xsl:when test="string($fGrandparent/@x)!='' and string($tGrandparent/@x)!='' and name(./parent::*/parent::*)!='bg:SubProcess'"><!-- string($fGrandparent/@graphMarginLeft) -->
							<xsl:value-of select="calc:add(string($fGrandparent/@x),'2')"></xsl:value-of>
						</xsl:when>
						<xsl:when test="string($fGrandparent/@x)='' and string($tGrandparent/@x)='' and name(./parent::*/parent::*)='bg:SubProcess'">
							<xsl:value-of select="calc:add(string(./parent::*/@x),calc:add(string(./parent::*/parent::*/@x),calc:add(-string($fParent/parent::*/@poolMarginLeft),calc:add(calc:min(string($fGrandparent/@x),string($fGrandparent/@width)),$xFloat))))"></xsl:value-of>
						</xsl:when>
						<xsl:when test="string($fGrandparent/@x)!='' and string($tGrandparent/@x)!=''and name(./parent::*/parent::*)='bg:SubProcess' and $getAllAfterVlans!=''">
							<xsl:value-of select="calc:add(string(./parent::*/@x),calc:add(string(./parent::*/parent::*/@x),calc:add(string($fParent/parent::*/@poolMarginLeft),calc:add(calc:min(string($fGrandparent/@x),string($fGrandparent/@width)),$xFloat))))"></xsl:value-of>
						</xsl:when>
						<xsl:when test="string($fGrandparent/@x)!='' and string($tGrandparent/@x)!=''and name(./parent::*/parent::*)='bg:SubProcess' and $getAllAfterVlans=''">
							<xsl:value-of select="calc:add(string(./parent::*/@x),calc:add(string(./parent::*/parent::*/@x),calc:add(-string($fParent/parent::*/@poolMarginLeft),calc:add(calc:min(string($fGrandparent/@x),string($fGrandparent/@width)),$xFloat))))"></xsl:value-of>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="'0'"></xsl:value-of>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:variable>
				<!--用于计算在 纵向泳道的y的偏移量算法   -->
				<xsl:variable name="fParentYY" >
					<xsl:choose>
						<xsl:when test="string($fGrandparent/@x)!='' and string($tGrandparent/@x)!='' and name(./parent::*/parent::*)!='bg:SubProcess'">
							<xsl:value-of select="'0'"></xsl:value-of>
						</xsl:when>
						<xsl:when test="(string($fGrandparent/@x)!='' and string($tGrandparent/@x)!='' and name(./parent::*/parent::*)='bg:SubProcess') or (string($fGrandparent/@x)='' and string($tGrandparent/@x)='' and name(./parent::*/parent::*)='bg:SubProcess')">
							<xsl:value-of select="calc:add(string($fGrandparent/@graphMarginTop),calc:add(string($fGrandparent/@poolMarginTop),calc:add(string(./parent::*/@y),'-2')))"></xsl:value-of>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="'0'"></xsl:value-of>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:variable>
				<xsl:variable name="concatXX" select="calc:add(calc:add(string($fGreatGrandparent/@left),'0'),calc:add(string($fParent/parent::*/@poolMarginLeft),calc:add(string($fParentXX),calc:add('0','0'))))"></xsl:variable>
				<xsl:variable name="concatYY" select="calc:add(calc:add(string($fGreatGrandparent/@graphMarginTop),string($fGreatGrandparent/@poolMarginTop)),calc:add('0',calc:add(calc:add(string($fParent/parent::*/@graphMarginTop),calc:add(string($fParent/parent::*/@poolMarginTop),string($fGreatGrandparent/@top))),calc:add('8',$fParentYY))))"></xsl:variable>
				<xsl:variable name="intermediatePoints">
						<xsl:for-each select="./intermediatePoints/child::*[name(.)='fg:Point']"><!-- string($fGreatGrandparent/@left)  string($fGreatGrandparent/@graphMarginTop) -->
							<xsl:value-of select="concat(calc:add($concatXX,calc:add(string($fParent/@x),@x)),':',calc:add($concatYY,calc:add(string($fParent/@y),@y)),',')"></xsl:value-of>
						</xsl:for-each>
				</xsl:variable>
				<!-- 计算连线前端组件的X坐标 -->
				<xsl:variable name="fGreatGrandparentX" select="$fGreatGrandparent/@x"></xsl:variable>
				<xsl:variable name="preSibsWidth">
					<xsl:for-each select="$fGrandparent/preceding-sibling::*[name(.)='bg:VLane']">
						<xsl:value-of select="concat(@width,',')"></xsl:value-of>
					</xsl:for-each>
				</xsl:variable>
				<xsl:variable name="fx">
					<xsl:value-of select="calc:add(calc:accumulate($preSibsWidth,$fGreatGrandparentX),string($fx))"></xsl:value-of>
				</xsl:variable>
				<xsl:variable name="ty1" select="calc:add(string($ty),string($tGreatGrandparent/@y))"></xsl:variable>
				<xsl:variable name="ty" select="calc:add(string($ty1),'40')"></xsl:variable>
		
				<!-- 计算连线末端组件的X坐标 -->
				<xsl:variable name="tGreatGrandparentX" select="$tGreatGrandparent/@x"></xsl:variable>
				<xsl:variable name="tPreSibsWidth">
					<xsl:for-each select="$tGrandparent/preceding-sibling::*[name(.)='bg:VLane']">
						<xsl:value-of select="concat(@width,',')"></xsl:value-of>
					</xsl:for-each>
				</xsl:variable>
		
				<xsl:variable name="tx">
					<xsl:value-of select="calc:add(calc:accumulate($tPreSibsWidth,$tGreatGrandparentX),$tx)"></xsl:value-of>
				</xsl:variable>
		<!-- 								<xsl:value-of select="calc:calc($fName,$fx,$fy,$fW,$fH,$fHOffset,$fVOffset,$fDirX,$fDirY,$tName,$tx,$ty,$tW,$tH,$tHOffset,$tVOffset,$tDirX,$tDirY )" /> -->
				<xsl:value-of select="calc:calc($shapeType,
					$fName,$fx,$fy,$fW,$fH,
					$fportType,$fDirX,$fDirY,$fHOffset,$fVOffset,
					$tName,$tx,$ty,$tW,$tH,
					$tportType,$tDirX,$tDirY,$tHOffset,$tVOffset,
					$intermediatePoints )" />	
			</xsl:when>
		
			<xsl:when test="name($fParent/parent::*)='bg:SubProcess'">
				<xsl:variable name="startPort" select="@startPort"></xsl:variable>
				<xsl:variable name="endPort" select="@endPort"></xsl:variable>
				<!-- 计算所有分级内嵌子流程的x或者y的偏移值 -->
				<xsl:variable name="parentFloatX">
					<xsl:call-template name="setFloatXorY">
						<xsl:with-param name="fParent" select="$fParent"></xsl:with-param>
						<xsl:with-param name="number" select="'0'"></xsl:with-param>
						<xsl:with-param name="XorY" select="'x'"></xsl:with-param>
					</xsl:call-template>
				</xsl:variable>
				<xsl:variable name="parentFloatY">
					<xsl:call-template name="setFloatXorY">
						<xsl:with-param name="fParent" select="$fParent"></xsl:with-param>
						<xsl:with-param name="number" select="'0'"></xsl:with-param>
						<xsl:with-param name="XorY" select="'y'"></xsl:with-param>
					</xsl:call-template>
				</xsl:variable>
				<xsl:variable name="intermediatePoints">
					<xsl:for-each select="./intermediatePoints/child::*[name(.)='fg:Point']">
						<xsl:value-of select="concat(calc:add($parentFloatX,calc:add('160',@x)),':',calc:add($parentFloatY,calc:add('56',@y)),',')"></xsl:value-of>
					</xsl:for-each>
				</xsl:variable> 
				<xsl:variable name="fParent" select="$fParent/parent::*"></xsl:variable>
				<xsl:variable name="tParent" select="$tParent/parent::*"></xsl:variable>
		
				<xsl:variable name="fx1" select="calc:add(string($fx),string($fParent/@x))"></xsl:variable>
				<xsl:variable name="fy1" select="calc:add(string($fy),string($fParent/@y))"></xsl:variable>
		
				<xsl:variable name="tx1" select="calc:add(string($tx),string($tParent/@x))"></xsl:variable>
				<xsl:variable name="ty1" select="calc:add(string($ty),string($tParent/@y))"></xsl:variable>
		
				<xsl:variable name="fx" select="calc:add(string($fx1),'10')"></xsl:variable>
				<xsl:variable name="fy" select="calc:add(string($fy1),'28')"></xsl:variable>
		
				<xsl:variable name="tx" select="calc:add(string($tx1),'10')"></xsl:variable>
				<xsl:variable name="ty" select="calc:add(string($ty1),'28')"></xsl:variable>
				  <xsl:call-template name="setSubProcessEdges">  
		                    <xsl:with-param name="startPort" select="$startPort" />  
		                    <xsl:with-param name="endPort" select="$endPort" />  
		                    <xsl:with-param name="fParent" select="$fParent" />  
		                    <xsl:with-param name="tParent" select="$tParent" />  
		                    <xsl:with-param name="fx" select="$fx" />  
		                    <xsl:with-param name="fy" select="$fy" />  
		                    <xsl:with-param name="tx" select="$tx" />  
		                    <xsl:with-param name="ty" select="$ty" />  
		                     <xsl:with-param name="shapeType" select="$shapeType" />  
		                     <xsl:with-param name="fName" select="$fName" />  
		                     <xsl:with-param name="fW" select="$fW" />  
		                     <xsl:with-param name="fH" select="$fH" />  
		                     <xsl:with-param name="fportType" select="$fportType" />  
		                     <xsl:with-param name="fDirX" select="$fDirX" />  
		                     <xsl:with-param name="fDirY" select="$fDirY" />  
		                     <xsl:with-param name="fHOffset" select="fHOffset" />  
		                     <xsl:with-param name="fVOffset" select="$fVOffset" />  
		                     <xsl:with-param name="tName" select="$tName" />  
		                     <xsl:with-param name="tW" select="$tW" />  
		                     <xsl:with-param name="tH" select="$tH" />  
		                     <xsl:with-param name="tportType" select="$tportType" />  
		                     <xsl:with-param name="tDirX" select="$tDirX" />  
		                     <xsl:with-param name="tDirY" select="$tDirY" />  
		                     <xsl:with-param name="tHOffset" select="$tHOffset" />  
		                     <xsl:with-param name="tVOffset" select="$tVOffset" />  
		                     <xsl:with-param name="intermediatePoints" select="$intermediatePoints" /> 
		          </xsl:call-template>  
			</xsl:when>
		
			<xsl:otherwise>
		<!--<xsl:value-of select="calc:calc($fName,$fx,$fy,$fW,$fH,$fHOffset,$fVOffset,$fDirX,$fDirY,$tName,$tx,$ty,$tW,$tH,$tHOffset,$tVOffset,$tDirX,$tDirY )" /> -->
				<xsl:value-of select="calc:calc($shapeType,
					$fName,$fx,$fy,$fW,$fH,
					$fportType,$fDirX,$fDirY,$fHOffset,$fVOffset,
					$tName,$tx,$ty,$tW,$tH,
					$tportType,$tDirX,$tDirY,$tHOffset,$tVOffset,
					$intermediatePoints )" />	
			</xsl:otherwise>
		</xsl:choose>


</xsl:template>

<!-- 迭代计算内嵌子流程的形状的算法 -->
<xsl:template name="setSubProcessParam">  
	<xsl:param name="x"/>  
	<xsl:param name="y"/>  
	<xsl:param name="parent"/>  
	<xsl:param name="baseGrandParent"/>  
	<xsl:param name="grandParentName"/>  
	
	<xsl:variable name="x1" select="calc:add(string($x),string($parent/@x))"></xsl:variable>
	<xsl:variable name="y1" select="calc:add(string($y),string($parent/@y))"></xsl:variable>

	<xsl:variable name="x" select="calc:add(string($x1),'10')"></xsl:variable>
	<xsl:variable name="y" select="calc:add(string($y1),'28')"></xsl:variable>
	<xsl:choose>
		<xsl:when test="$grandParentName='bg:HLane'">
			<xsl:variable name="grandparent" select="./parent::*/parent::*"></xsl:variable>
			<xsl:variable name="greatGrandparent" select="$grandparent/parent::*"></xsl:variable>
			<xsl:variable name="x1" select="calc:add(string($x),string($greatGrandparent/@x))"></xsl:variable>
			<xsl:variable name="x" select="calc:add(string($x1),'40')"></xsl:variable>
			<xsl:attribute name="x"><xsl:value-of select="$x"></xsl:value-of></xsl:attribute>
			<xsl:attribute name="y">
				<xsl:variable name="grandparentY" select="$greatGrandparent/@y"></xsl:variable>
				<xsl:variable name="preSibsHeight">
					<xsl:for-each select="$grandparent/preceding-sibling::*[name(.)='bg:HLane']">
						<xsl:value-of select="concat(@height,',')"></xsl:value-of>
					</xsl:for-each>
				</xsl:variable>
				<xsl:value-of select="calc:add(calc:accumulate($preSibsHeight,$grandparentY),$y)"></xsl:value-of>
			</xsl:attribute>
		</xsl:when>
		<xsl:when test="$grandParentName='bg:VLane'">
			<xsl:variable name="grandparent" select="./parent::*/parent::*"></xsl:variable>
			<xsl:variable name="greatGrandparent" select="$grandparent/parent::*"></xsl:variable>
			<xsl:variable name="y1" select="calc:add(string($y),string($greatGrandparent/@y))"></xsl:variable>
			<xsl:variable name="y" select="calc:add(string($y1),'40')"></xsl:variable>
			<xsl:attribute name="y"><xsl:value-of select="$y"></xsl:value-of></xsl:attribute>
			<xsl:attribute name="x">
				<xsl:variable name="grandparentX" select="$greatGrandparent/@x"></xsl:variable>
				<xsl:variable name="preSibsWidth">
					<xsl:for-each select="$grandparent/preceding-sibling::*[name(.)='bg:VLane']">
						<xsl:value-of select="concat(@width,',')"></xsl:value-of>
					</xsl:for-each>
				</xsl:variable>
				<xsl:value-of select="calc:add(calc:accumulate($preSibsWidth,$grandparentX),calc:add('0',$x))"></xsl:value-of>
			</xsl:attribute>
		</xsl:when>
		
		<xsl:when test="$grandParentName='bg:SubProcess'">
				<xsl:variable name="parent" select="$parent/parent::*"></xsl:variable>
					<xsl:variable name="grandGreatParent" select="$baseGrandParent"></xsl:variable>
					<xsl:variable name="fParentXX" >
						<xsl:choose>
							<!--用于计算在两个泳道之间连线的x算法   -->
							<xsl:when test="name($grandGreatParent)='bg:VLane'">
							 	 <xsl:value-of select="calc:add(string($grandGreatParent/@x),string($grandGreatParent/parent::*/@left))"></xsl:value-of> 
							</xsl:when>
							<xsl:when test="name($grandGreatParent)='bg:HLane'">
							 	 <xsl:value-of select="calc:add(string($grandGreatParent/parent::*/@graphMarginLeft),calc:add(string($grandGreatParent/parent::*/@left),string($grandGreatParent/@graphMarginLeft)))"></xsl:value-of> 
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="'0'"></xsl:value-of>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>
					
					<xsl:variable name="fParentYY" >
						<xsl:choose>
							<!--用于计算在两个泳道之间连线的y算法   -->
							<xsl:when test="name($grandGreatParent)='bg:VLane'">
							 	 <xsl:value-of select="string($grandGreatParent/parent::*/@graphMarginTop)"></xsl:value-of> 
							</xsl:when>
							<xsl:when test="name($grandGreatParent)='bg:HLane'">
							 	 <xsl:value-of select="calc:add(string($grandGreatParent/@y),string($grandGreatParent/parent::*/@top))"></xsl:value-of> 
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="'0'"></xsl:value-of>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>
				
	 			<xsl:call-template name="setSubProcessParam">  
	                    <xsl:with-param name="x" select="calc:add($fParentXX,calc:add('0',$x))" />  
	                    <xsl:with-param name="y" select="calc:add($fParentYY,$y)" />  
	                    <xsl:with-param name="parent" select="$parent" />  
	                    <xsl:with-param name="baseGrandParent" select="$baseGrandParent" />  
                		<xsl:with-param name="grandParentName" select="$parent/parent::*" />  
				</xsl:call-template>   
		</xsl:when>
		<xsl:when test="name($grandParentName)='bg:SubProcess'">
				<xsl:variable name="parent" select="$parent/parent::*"></xsl:variable>
				
				
				<xsl:variable name="grandGreatParent" select="$baseGrandParent"></xsl:variable>
					<xsl:variable name="fParentXX" >
						<xsl:choose>
							<!--用于计算在两个泳道之间连线的x算法   -->
							<xsl:when test="name($grandGreatParent/parent::*)='bg:VLane'">
							 	 <xsl:value-of select="calc:add(string($grandGreatParent/parent::*/@x),string($grandGreatParent/parent::*/parent::*/@left))"></xsl:value-of> 
							</xsl:when>
							<xsl:when test="name($grandGreatParent/parent::*)='bg:HLane'">
							 	 <xsl:value-of select="calc:add(string($grandGreatParent/parent::*/parent::*/@graphMarginLeft),calc:add(string($grandGreatParent/parent::*/parent::*/@left),string($grandGreatParent/parent::*/@graphMarginLeft)))"></xsl:value-of> 
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="'0'"></xsl:value-of>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>
					
					<xsl:variable name="fParentYY" >
						<xsl:choose>
							<!--用于计算在两个泳道之间连线的y算法   -->
							<xsl:when test="name($grandGreatParent/parent::*)='bg:VLane'">
							 	 <xsl:value-of select="string($grandGreatParent/parent::*/parent::*/@graphMarginTop)"></xsl:value-of> 
							</xsl:when>
							<xsl:when test="name($grandGreatParent/parent::*)='bg:HLane'">
							 	 <xsl:value-of select="calc:add(string($grandGreatParent/parent::*/@y),string($grandGreatParent/parent::*/parent::*/@top))"></xsl:value-of> 
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="'0'"></xsl:value-of>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>
				
	 			<xsl:call-template name="setSubProcessParam">  
	                    <xsl:with-param name="x" select="calc:add($fParentXX,$x)" />  
	                    <xsl:with-param name="y" select="calc:add($fParentYY,$y)" />  
	                    <xsl:with-param name="parent" select="$parent" />  
	                    <xsl:with-param name="baseGrandParent" select="$baseGrandParent" />  
                		<xsl:with-param name="grandParentName" select="$parent/parent::*" />  
				</xsl:call-template>   
		</xsl:when>
		<xsl:otherwise>
			<xsl:attribute name="x"><xsl:value-of select="$x"></xsl:value-of></xsl:attribute>
			<xsl:attribute name="y"><xsl:value-of select="$y"></xsl:value-of></xsl:attribute>
		</xsl:otherwise>
	</xsl:choose>

</xsl:template> 

<!-- 计算内部子流程中的网关中点的父级内部子流程的x、y坐标的偏移 -->
	<xsl:template name="setFloatXorY">
		<xsl:param name="fParent" />
		<xsl:param name="number" />
		<xsl:param name="XorY" />
		<xsl:variable name="parent" select="$fParent"></xsl:variable>
		<xsl:choose>
			<xsl:when test="$XorY='x'">
				<xsl:variable name="number" select="calc:add(calc:add($number,'10'),string($parent/@x))"></xsl:variable>
				<xsl:variable name="grandParentX" select="$parent/parent::*/@x"></xsl:variable>
				<xsl:choose>
					<xsl:when test="$grandParentX!=''">
							<xsl:call-template name="setFloatXorY">  
				                    <xsl:with-param name="fParent" select="$parent/parent::*" />  
				                    <xsl:with-param name="number" select="$number" />  
				                    <xsl:with-param name="XorY" select="$XorY" />  
							</xsl:call-template>  
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$number"></xsl:value-of>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:variable name="number" select="calc:add(calc:add($number,'28'),string($parent/@y))"></xsl:variable>
				<xsl:variable name="grandParentY" select="$parent/parent::*/@y"></xsl:variable>
				<xsl:choose>
					<xsl:when test="$grandParentY!=''">
							<xsl:call-template name="setFloatXorY">  
				                    <xsl:with-param name="fParent" select="$parent/parent::*" />  
				                    <xsl:with-param name="number" select="$number" />  
				                    <xsl:with-param name="XorY" select="$XorY" />  
							</xsl:call-template>  
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$number"></xsl:value-of>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="setAttrubute">
		<xsl:param name="obj" />
		<xsl:attribute name="id">
			<xsl:value-of select="$obj/@id"></xsl:value-of>
		</xsl:attribute>
		<xsl:attribute name="name">
			<xsl:value-of select="$obj/label"></xsl:value-of>
		</xsl:attribute>
	</xsl:template>
	
	<xsl:template name="setTaskExtProperties">
		<xsl:param name="obj"></xsl:param>
		<ht:order>
			<xsl:value-of select="calc:nan2Zero($obj/@order)"></xsl:value-of>
		</ht:order>		
	</xsl:template>
	<xsl:template name="setServiceTaskType">
		<ht:serviceType></ht:serviceType>	
	</xsl:template>
</xsl:stylesheet>
