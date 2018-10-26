<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20141222" releaseVersion="8.0.0">
<TableDataMap>
<TableData name="ds1" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT
     货主地区,
     round(sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣)),2) as 营业额,
     round(sum(订单明细.进价*订单明细.数量),2) as 成本,
     round((sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣))- sum(订单明细.进价*订单明细.数量)),2) as 利润,
     round((sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣))- sum(订单明细.进价*订单明细.数量))/sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣)),2) as 利润率
FROM
     S订单 订单,S订单明细 订单明细 ,S产品 产品,S类别 类别
where strftime('%Y',订单.订购日期) in ('2011','2012') and 订单.订单ID = 订单明细.订单ID  and 产品.产品ID=订单明细.产品ID and 产品.类别ID=类别.类别ID
group by 货主地区]]></Query>
</TableData>
</TableDataMap>
<Layout class="com.fr.form.ui.container.WBorderLayout">
<WidgetName name="form"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Center class="com.fr.form.ui.container.WFitLayout">
<WidgetName name="body"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="8" left="10" bottom="1" right="10"/>
<Border>
<border style="0" color="-723724" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="8"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart1"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart1"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-2368549" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"   各地区分公司营业额&利润图\"]]></O>
<FRFont name="微软雅黑" style="0" size="80"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-2818092"/>
<Alpha alpha="0.5"/>
</Border>
<Background name="ColorBackground" color="-2818092"/>
<LayoutAttr selectedIndex="0"/>
<Chart name="默认">
<Chart class="com.fr.chart.chartattr.Chart" options="{}" customChartID="" wrapperName="FineChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="88"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Plot class="com.fr.chart.chartattr.CustomPlot">
<CategoryPlot>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false"/>
<newColor/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="-1" seriesDragEnable="true" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${SERIES}${BR}${CATEGORY}${BR}${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.CustomAttr">
<CustomAttr>
<ConditionAttr name=""/>
<ctattr renderer="1"/>
</CustomAttr>
</DefaultAttr>
<ConditionAttrList>
<List index="0">
<CustomAttr>
<ConditionAttr name="条件属性11">
<AttrList>
<Attr class="com.fr.chart.base.AttrBackground">
<AttrBackground>
<Background name="ColorBackground" color="-2719274"/>
</AttrBackground>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[系列序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[3]]></O>
</Compare>
</Condition>
</ConditionAttr>
<ctattr renderer="1"/>
</CustomAttr>
</List>
</ConditionAttrList>
</ConditionCollection>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="true"/>
<FRFont name="Microsoft YaHei" style="0" size="72"/>
</Legend>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<ColorList>
<OColor colvalue="-16737844"/>
<OColor colvalue="-10066279"/>
<OColor colvalue="-10066279"/>
<OColor colvalue="-16750485"/>
<OColor colvalue="-3658447"/>
<OColor colvalue="-10331231"/>
<OColor colvalue="-7763575"/>
<OColor colvalue="-6514688"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<RectanglePlotAttr interactiveAxisTooltip="false"/>
<xAxis>
<CategoryAxis class="com.fr.chart.chartattr.CategoryAxis">
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="0"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="0" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="72"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</CategoryAxis>
</xAxis>
<yAxis>
<ValueAxis class="com.fr.chart.chartattr.ValueAxis">
<ValueAxisAttr201108 alignZeroValue="false"/>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="0"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Century Gothic" style="0" size="72"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</ValueAxis>
</yAxis>
<secondAxis>
<ValueAxis class="com.fr.chart.chartattr.ValueAxis">
<ValueAxisAttr201108 alignZeroValue="false"/>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="0"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="4"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Century Gothic" style="0" size="72"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</ValueAxis>
</secondAxis>
<CateAttr isStacked="false"/>
<CustomTypeConditionCollection>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.CustomAttr">
<CustomAttr>
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.chart.base.AttrBarSeries">
<AttrBarSeries>
<Attr seriesOverlapPercent="-0.25" categoryIntervalPercent="1.0" axisPosition="LEFT"/>
</AttrBarSeries>
</Attr>
</AttrList>
</ConditionAttr>
<ctattr renderer="1"/>
</CustomAttr>
</DefaultAttr>
<ConditionAttrList>
<List index="0">
<CustomAttr>
<ConditionAttr name="条件属性01">
<AttrList>
<Attr class="com.fr.chart.base.AttrBarSeries">
<AttrBarSeries>
<Attr seriesOverlapPercent="-0.25" categoryIntervalPercent="1.0" axisPosition="LEFT"/>
</AttrBarSeries>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[系列序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[1]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
<ctattr renderer="1"/>
</CustomAttr>
</List>
<List index="1">
<CustomAttr>
<ConditionAttr name="条件属性11">
<AttrList>
<Attr class="com.fr.chart.base.AttrLineSeries">
<AttrLineSeries>
<Attr isCurve="false" isNullValueBreak="true" lineStyle="5" markerType="SquareFilledMarker" axisPosition="RIGHT"/>
</AttrLineSeries>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[系列序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[3]]></O>
</Compare>
</Condition>
</ConditionAttr>
<ctattr renderer="2"/>
</CustomAttr>
</List>
</ConditionAttrList>
</ConditionCollection>
</CustomTypeConditionCollection>
</CategoryPlot>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="货主地区"/>
<ChartSummaryColumn name="营业额" function="com.fr.data.util.function.NoneFunction" customName="营业额"/>
<ChartSummaryColumn name="成本" function="com.fr.data.util.function.NoneFunction" customName="成本"/>
<ChartSummaryColumn name="利润率" function="com.fr.data.util.function.NoneFunction" customName="利润率"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
</InnerWidget>
<BoundsAttr x="480" y="38" width="480" height="200"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="title"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[="   各地区分公司营业额&利润图"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="80"/>
<border style="1" color="-2368549"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="480" height="38"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="title"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[="   各产品营业额利润"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="80"/>
<border style="1" color="-2368549"/>
</title>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart1"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LayoutAttr selectedIndex="0"/>
<Chart name="默认">
<Chart class="com.fr.chart.chartattr.Chart" options="{}" customChartID="" wrapperName="FineChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="88"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Plot class="com.fr.chart.chartattr.CustomPlot">
<CategoryPlot>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false"/>
<newColor/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="true" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.CustomAttr">
<CustomAttr>
<ConditionAttr name=""/>
<ctattr renderer="1"/>
</CustomAttr>
</DefaultAttr>
</ConditionCollection>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="true"/>
<FRFont name="Microsoft YaHei" style="0" size="72"/>
</Legend>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="0"/>
<FillStyleName fillStyleName=""/>
</AttrFillStyle>
</newPlotFillStyle>
<RectanglePlotAttr interactiveAxisTooltip="false"/>
<xAxis>
<CategoryAxis class="com.fr.chart.chartattr.CategoryAxis">
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="0"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="72"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</CategoryAxis>
</xAxis>
<yAxis>
<ValueAxis class="com.fr.chart.chartattr.ValueAxis">
<ValueAxisAttr201108 alignZeroValue="false"/>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Century Gothic" style="0" size="72"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</ValueAxis>
</yAxis>
<secondAxis>
<ValueAxis class="com.fr.chart.chartattr.ValueAxis">
<ValueAxisAttr201108 alignZeroValue="false"/>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="0"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="4"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Century Gothic" style="0" size="72"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</ValueAxis>
</secondAxis>
<CateAttr isStacked="false"/>
<CustomTypeConditionCollection>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.CustomAttr">
<CustomAttr>
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.chart.base.AttrBarSeries">
<AttrBarSeries>
<Attr seriesOverlapPercent="-0.25" categoryIntervalPercent="1.0" axisPosition="LEFT"/>
</AttrBarSeries>
</Attr>
</AttrList>
</ConditionAttr>
<ctattr renderer="1"/>
</CustomAttr>
</DefaultAttr>
<ConditionAttrList>
<List index="0">
<CustomAttr>
<ConditionAttr name="条件属性01">
<AttrList>
<Attr class="com.fr.chart.base.AttrBarSeries">
<AttrBarSeries>
<Attr seriesOverlapPercent="-0.25" categoryIntervalPercent="1.0" axisPosition="LEFT"/>
</AttrBarSeries>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[系列序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[1]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
<ctattr renderer="1"/>
</CustomAttr>
</List>
<List index="1">
<CustomAttr>
<ConditionAttr name="条件属性11">
<AttrList>
<Attr class="com.fr.chart.base.AttrLineSeries">
<AttrLineSeries>
<Attr isCurve="false" isNullValueBreak="true" lineStyle="5" markerType="NullMarker" axisPosition="RIGHT"/>
</AttrLineSeries>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[系列序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[2]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
<ctattr renderer="2"/>
</CustomAttr>
</List>
</ConditionAttrList>
</ConditionCollection>
</CustomTypeConditionCollection>
</CategoryPlot>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<CategoryName value="类别名称"/>
<ChartSummaryColumn name="收入" function="com.fr.data.util.function.NoneFunction" customName="收入"/>
<ChartSummaryColumn name="利润" function="com.fr.data.util.function.NoneFunction" customName="利润"/>
<ChartSummaryColumn name="利润率" function="com.fr.data.util.function.NoneFunction" customName="利润率"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
</body>
</InnerWidget>
<BoundsAttr x="0" y="32" width="960" height="238"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report0"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-2368549" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"   地区销售效果明细\"]]></O>
<FRFont name="微软雅黑" style="0" size="80"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-2752555"/>
<Alpha alpha="0.5"/>
</Border>
<Background name="ColorBackground" color="-2752555"/>
<FormElementCase>
<ReportPageAttr>
<HR/>
<FR/>
<HC/>
<FC/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[1028700,990600,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[4114800,5295900,4724400,4838700,4800600,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[分公司]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[营业额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[成本]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[利润]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="0">
<O>
<![CDATA[利润率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="货主地区"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="营业额"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="成本"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="利润"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="1" s="3">
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=D2 / B2]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="27">
<FRFont name="微软雅黑" style="1" size="88"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-4144960"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="27">
<FRFont name="微软雅黑" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="27">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[¤#,##0.00]]></Format>
<FRFont name="Century Gothic" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="27">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.00%]]></Format>
<FRFont name="Century Gothic" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
</Border>
</Style>
</StyleList>
<showToolbar showtoolbar="false"/>
<IM>
<![CDATA[buf<Te,0/h;KA72Ti=RB%ZQ]AGE]A]A.>;Uh[8I7-^K`Cb+]A(<7:i;PDLWV)mGZeoLR;U=CSr(:
l1e-uG3Z:9Ll#5+Ag<PCC:(p\X08oABGO^#/;)qt5>"T?l-TrAhF-FTXs:93t4_$K:o`j)7g
ndS$F4IVP.=+oJ1FnVNKpY*-,d_tIU5SW+,TO6&5H0V(jdH&k#[:8+T>V:R%M@TIpLT*IR)3
eDESGpI_HGt^7[n1?uC053F-Bu`D[DV3O6f0u=HSF+L)fD`SM<6;b&g-@8jOCs,B14Rl<NoR
hoc18Q><G]An4)!nN)nDLi)<l:nI,ZYCo4d7F;oPfs"1o-sc]A6qL^UQ)AU%A?"g,kI*P6uT(D
5G-0!^+#R,5%4%'#,e:9]A\IC4(K#\nP)_Rf)D]AmGZE_M+@l\^pQTI'!23t`^S$bAo]A_75pJ<
me7:E5+I8h(_UYRm"FU:@_4/k>DE%VU$ThJA'b9(?4,a__Je5X%F[+/^hL3O/W^42P&?0O>q
6fQG6/g9P&3R@1JU0uQ9EolEFS)d+<>H=dD(oW\mBkBpefei\VS6g!HJ@&\_'/-i8G)ua5WT
E_Z%]AlJX/`&h6<Cm*O?E_S?Ak?E7YW;,Y<iRZKfp-Tij"YVL*7sq#fj2DXP%^YF(YEc`bnm!
:!Mr4R@h:S/@987Y2e\LhRjbT4HHJ[!V@SjNb>k#oeT5-b\N!h/_n#^Io&5P9U12d-n`%nC[
"Vl=+)0pE=\I<["1RH/<O!?0IT!e=(K1]A7$aBNN.4L/[&)M<*QNSV$U+&ANk)JXnJG1t16ri
.Aqge?Z8C)^'@,amLZe#%%UEi]AUlC6_LPNB,AcerAt;&*!gU*96ZL$O5fjJo>N/:8cslU=dK
mlEnbj5N]AFTq!E,ahK66QL,lFblL5&9jQ97U6<qhb!Wd;12f2t47K7@^XFB^&[`Zs1Z8u@AF
oDdq7DRE`[V]Aj=m')]A3,JI_2F_cP*<.C%rpq!O8Oo`#NGcI8,Wp&JePj$=i#jQQ<qODHY!*d
0rL46gHYDj[(cCL6,J^5+5pcZRu>,NVRUtsk0#.E5+e/7i*YUl\`q;%29S9<I55P^@pdaFp@
7i$"rE(Ki9I4"VNALL5bM)T40-_Dtl;WK;4KEsLjI8H'3TmhMR#a))Fb1@=H44pP.pP#Wkl_
AO=k4b0I"sU^1XKNp_8!/Y'>&:[l9D'NhDW?LIU,'oro?%gYZmbt]A%EFtn7QB^914d@/C=23
n[dPoMo/rucan%`pZhii/#;9a&F5]ARNB'2luQ>RjGkg@8;$eB4fY6d$t3E\L9VtYgj.:BoLV
YRahYSpuXI4bI$0.#%pY)l"?lWC'dUdO9qHgR,6d*_:u,gap"-JHHA\)YI6MsS$O22S^:=CO
ETm]AI9AGd[`IXn`r5cU9ZZ*<ai(0?NKc0kfHKQZ%aE^>*m$B:3s16_@X]A:[9H!I)Y]AnF;-&\
0joi%LDWcRNs=j.a8t'iZ8sk8n7D3m/DFu"oh,li2^k;(bkeQX]AZ><Z9[Z*b5lHCU*Fmo;jn
m<G5$P"p4`oWZe8-r6ajekOW/)D&oAI46YjHsqanb4nf_[OY#JOC.2<&`cH%nM&KfjsoE=P,
QHp=6r"ce8,a1VtUR2e+SYIr9&?gB_JH*U!\ePM2<.0fFMoc01;]A^?Z)f<BBhQ%/8SSg;L)_
]A@$\(sgWlZ@J..8OD!9#\]AS`:A,^a#TcD!LL[a`GiKEIA66FkX[M\ga46Pq(*B@Nm/IWp:rM
("6ApNrnQUW_%&dIT?%kF:WkuLckr"j_Qh!Qn=ba@B5TR0JW[[7/db;_JmOHug^(X^P(H?oF
BQk9Dar[8/e<L%!.$#&(MB.)THMB/Y,B!b%'%<ejljDXVl::X.)c_e1Kpf!B/&l/C;i+1^k#
4b;k9Y0TOE!E@A='s#P7UH>%\7s!>WB+V`:Ys(R:J.rEZ+NSV'f\u7QkY)K9e!U_&\C5Baqg
(A0:$HoR<FkV$>5$m)'gDd441Z'W]A+0NdL6cpV.N!]AS:&&5YCh=mU0b.DkupHMQrcPW0NM?d
VY>O[)K91(h"*T?EBIP+Fp!u%Jl@oY&ci;UKpB&=kp[T*+_D'5;lE)WJ5X0B^J``2&M><knt
l^Gmb'e7j\X.M#M@",OFKKTBR5:fF"ZRS)7c.qAS:;Cr$^M9]AhV3&#nLmm[S(ldPf)d#gifI
q'bU($QGa<q/9aXO#k6hr-L<EY'6IaEk-]A\.o/@e>k8L>+.T\hIHO,uG+=]A%jbH4sk+N5cSj
Z`O"VEbiQSLHep?@p6,SYWS(mMpiI&4]AJ*AB!pdRW;p6/1O2=lC):]Aj^n?jMI4:F7ZG3qq;[
bJZ"q/(6Qr;1nn2XI,.=2`(,WVm*D9u(-uh.W!CDKM3Ko*#aRF9$#7bb>D;DDo@8VeDKPju\
L12oN$7?1d6i!!>$KjmA-[5lguhM&Jdl$WgUmLJk*K!C3>j[&1H@7TPlL;6B2r_M]AdIS)fcb
]A-,?/BD*3f%=$pl(dY9g<88Op,V&9@`iWmHPYeNlL#+4'W&onK%$XC[@N,ci-aEVf'r#FfC]A
H@lA>=_k)rM[YjOK&qR$GEG#p)bJTp^Q?[MbNMoX069C:_^_H*`6q]A"]A?lRr1r/0_1`,2%<6
(rPr;;ajp4,lrro%CCQOb$j3`>--^-^6UBNu*kU1PS$*jB3TdgeEA"GC9Gm"OUm3``\ZC,X?
N0VJ8>p_L[r[.S!H4*5'cfn"S;i-U0M(5.:`6NhH^d*I3bbMP$%X$)@:Q:@[WH`m,09oQ63-
mdl)\$F_71EgAe_>&NH:,9648f2mMCT54h7e2Q[pq:il5>o1E42`o@Xgn:FrVb4>>8B19SVb
tM<a1LlSdTkDK!%WA)YcjN:Z$6,DC)LCIF6SU_AM):Dtgm;)scI]A2JpF8+9'gtiO;jp<!8Vd
WEP(*=iVk(p0(o3&[1Ug(tcD0'Ph3!/+:b8MgE"0p$*E4f1Mkj?ra#c_,i5)PSuIsX6@:_6r
l27cDJ1'@/WjAQ0/@t9FaRg\)-iY'NQqqkPEt]A..9#JDnPtYnf<ZS\PeTA52Z/)jfmQY=[E3
NK=cP.REAYqhB7Ztm39W-ZP;7ts*de@9k4)K69+uZH,&Ecs*_K3a&7f)(4/!I)k<5u#Yk*tD
QNlVNupW!O=qDo),_2?-6+,fJ$j4SO,g"M;<pC`Z<rIdNU';KL=)Au+fGH+T<;GLcS:;n1%6
3I>9&dSqY3o90-s6;q_YF%r[[q(qh3g#/KT^-XdPI#]A2Jmhn[O+H8p%3n^nEK,,./C$W3igX
F@6nT11':X6[FW1CCLn<?hfCo<`lRGE!5a?,Hc2E6kZ'D(MSe9kcVn.2UH`;CD8V.0F$[4MR
fg%+TVh9iQCps\s:TSpU(s?JY)2P-D0FO*TR%&n\T]Ac<2'$HRBP4[@P#[mfn,%sW[e/)@s_1
X3i(eUXYi0kY[P#I*F`Q>]A%Y9>b]AOf\gqg.s>n)/lFZ]A'@EO&+@h#.loTYBU:(%L!S<cKoEQ
3m6:YA?SAl]AL0c&Dks:JVnbMFZ<H&;B1=X4jONt]Ak@ZTkGKL2%ecb>1MiH?[IH%XE-KVTF.B
7!LEGC5[JL/f8\'AA2Qk<07,N%TRs9ueMKs3[2?_r33FoN;ED7c`oG_\aCE]A4SLL93+hB_Ra
SHLlq4,^RZ)dX9\<MgLTNQ4m4:30kB"Z:hWlGX<[BW8jY.If%%Vi`u;q!k%>&]A9TUTWq-n9'
Va/<aG:;"I=VQ;t+#$kERa+_I3i[Ip7`jPW.]AeaMGdJ;t?KG0*42RD2*#[,_kuL5^'8%m,TL
hVl(<7)oZlrnu6R<kTqh+Q\[@sf[;+.PDP_6Pr+A,:fJ#Nd$u80i??LXAV$5r`a6,J<Hqtd_
*[iFiJE&I)'>$!kos-K84id5FY=ii^J5aT]ACHnu5R/#6TmU^r58'ejng=*=%-%BT$rE*gq1p
gWS2lM!b(tgM@-SEsPgN&KQUra\N;2q6L$VBp*2CN=`VVK=+pq6)^EDn!(bX-8E`i':ErWpk
(]AaW&eh,o2K\3eS(Grm1aJ13#rBFUX'R#at$$!`_;fFlF[&^\gH<?Q)BW9F;ih:Z<nS@<j!s
hHNNBY'/m;_,;T4ahT64*S=624XB&0gFnj<SXf[F:K_P8FW!?=LG,5#9m/EAsa$.JOESi-^A
bpIpBO4C"7^Y)!mcIWceb2*rLL&-SZnk;SrV@Sfl=&Xq#!PN41i.'4$kCDMJ,'`Y5r:q2"Fb
s5k+Lg`Q'I_uDH-GlItbXI;;^F;YRBM(B@Gm6WYcta@7f+dGBjX=@ne\+@i6X)fC"!umn+;V
us@<u[C(c=1,-BH:I00N^M8Kr4^/&UH\GA4_:UceJa'-7"'&q,YWD"t%$d6%.;fk^_Wij+`G
n<M$jXfuoZf2oi%C"b>@LAAhW774gifL28`mpUe!9a:TXI)7<fD<7eno`nL"A_9Chr4KbWNf
q/fWl'/M4`1nQ-P8%kb3A0sA3Aj3-JqD@/a-qEPoY"3Y\i!>d&+C#C1\g8Q5F/Wn!MaRg5ds
C-OdK54FVnG_@hL<-8r'8eYL#j-<^L*_EK<IB,+*+`(9nND"k9PQI!9K>_<W9lMM*-MJC`?E
UU![<>4="?#.@^r#^JV[%H!<p!7H7&%1JK_1og.>#>'RT2qgi.uP!3[f3/^6VJ:+KhVGG_Wn
Bo8uV1SL\%rH&esd/0HO\OQtkSJ1pp#_]A'PHVnm-MML(147JT8jDKe7=YROeWV/1p`pO8me!
luqr/;9J%`dg/AOg`p\QA%rWGGfGrg_:79J`1Nj$Y!dA>S]AAYV%?'eln]A0/aU>`I:BZF0),o
koJkRt"Sk%qd`OOB[`T]A\8upbiuhP*>j8p@8i;K2H>%L-+`0X*7FBn0YGe:dZ`2c%MT6MRG3
WB,#lhb8qgiNc/LQ^AroUj0MrL&L+Y9(&_EbPME@(UESp>-Mhm#Yl(,N#XcC@ZV_#_[F^r">
=WAj2e)ihiOKu5`-#ff-Rn`g@!W5rSs["EF4I+]A0/ENg'L$58"2ENI(N/TV:1(&8Yc(r#m?e
8>H/UtF`l$T"3J=D-OkU#f#.D)1e8l-,O:Y</.:eXmUQDZ*\",kbb%oI,M]AE2AEole^na9/*
!;><#JA1dBH%"Y3Y]A<g9E;Sm07`'"qKX&H>:Y9fE+:ji#:Z"loVu6=2.&tO1#d=fr@,_Of+4
ljtQlfB.LRuG6U`ec-SmGoI/Q:%I@ES6Xb_O.Ih*d5Kh>odUPl7.*-F*GJ@$O,K+IR<Dk&c1
%1a\7YSX.T,mDlS]AL@RUF?&((g*FVY6+4Y$X;ULhqN'iSIC=@l+ZCj"a[W#I/:V!4f@>4;38
F4a]AT;CXdqW2)'qAM5q"Eqc@c3e3/UtD_sa2jXKh-jUW768oh&I]ANG"Hh[Lj2ZTPi8\S)1Ii
%eHgD,h'!07U3r@8Mq1iM<:C]Ao%=F3*QoU5oQILhr`s%l]An^f:gM$Hs?O[3Y5q)Ul#0(3j.:
nRqt$6UhLS3SYmQZ5Rh-UZB&5$g<U(B;;sPk,cV]A%PVmp'n(Q2h`l+V>Q-;tPR":DlbE_B7J
Uc^4&-/g]A)iQ2$+Kn-EhW#fNpsmeFtF=1Dl]AY5d!fn@rf]Are)GQnDqS[m>%:oC0DSUt&7?oU
h4c+sE31jNTl@::"!=Xf$)IP';J-C.8,a1ce!XM=T:WnH?cT?d[U>b)k,S,:t=qCf)!P%E-,
?IQZjM/GXK`%L$!XduF[2FEj-oAu1lK;"1'dQ[rU+n+jZBAjNNn+Z*K1cB]A-V>XJ`u,P1Vbk
O:)U1u%recXXrfWj#q&Dk+N32=9WN2L&9YD2]A%"Fo*!pNe<49PC7Jg#sF;U/%?Nhj(0rq[]A
~
]]></IM>
</InnerWidget>
<BoundsAttr x="0" y="38" width="960" height="232"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="title"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[="   地区销售效果明细"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="80"/>
<border style="1" color="-2368549"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="38"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="title"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[="   产品销售明细"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="80"/>
<border style="1" color="-2368549"/>
</title>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="1" left="1" bottom="1" right="1"/>
<Border>
<border style="1" color="-2368549" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"   产品销售明细\"]]></O>
<FRFont name="微软雅黑" style="0" size="80"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-2752555"/>
<Alpha alpha="0.5"/>
</Border>
<Background name="ColorBackground" color="-2752555"/>
<FormElementCase>
<ReportPageAttr>
<HR/>
<FR/>
<HC/>
<FC/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[723900,723900,952500,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2590800,2743200,2743200,2743200,2743200,2057400,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" rs="2" s="0">
<O>
<![CDATA[年份]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="1" r="0" rs="2" s="0">
<O>
<![CDATA[地区]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="2" r="0" cs="4" s="1">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="类别名称"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<SelectCount count="4"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand dir="1"/>
</C>
<C c="2" r="1" s="2">
<O>
<![CDATA[营业额]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="3" r="1" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" s="3">
<O>
<![CDATA[利润]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="5" r="1" s="4">
<O>
<![CDATA[排名]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="2" s="5">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="订单_订购日期"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand dir="0"/>
</C>
<C c="1" r="2" s="6">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="订单_货主城市"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand dir="0"/>
</C>
<C c="2" r="2" s="7">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="营业额"/>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[订单明细_产品ID]]></CNAME>
<Compare op="0">
<SimpleDSColumn dsName="ds2" columnName="产品id"/>
</Compare>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="3" r="2" s="8">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="成本"/>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[订单明细_产品ID]]></CNAME>
<Compare op="0">
<SimpleDSColumn dsName="ds2" columnName="产品id"/>
</Compare>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ListCondition"/>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ColWidthHighlightAction"/>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="2" s="9">
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=C3 - D3]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="5" r="2" s="10">
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=count(E3[!0;!0]A{A3 = $A3 && E3 > $E3 && C1 = $C1}) + 1]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="88"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-4144960"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-4144960"/>
<Left style="2" color="-1"/>
<Right style="2" color="-1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-4144960"/>
<Bottom style="1" color="-4144960"/>
<Left style="2" color="-1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-4144960"/>
<Bottom style="1" color="-4144960"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-4144960"/>
<Bottom style="1" color="-4144960"/>
<Right style="2" color="-1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="8">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[¤#,##0.00]]></Format>
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
<Left style="2" color="-1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[¤#,##0.00]]></Format>
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="8">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[¤#,##0.00]]></Format>
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-236032"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
<Right style="2" color="-1"/>
</Border>
</Style>
</StyleList>
<showToolbar showtoolbar="false"/>
<IM>
<![CDATA[buoBU<-eN4Bn*6Z>FlNe6:aL3D-<"Ee)jP]AV2TG"C>?8[`$062Rp?pGP/l3#ZY=,H6Us0)AB
cB&M:S%;e3kqL3fNt*4(lVSear\*maE9DeK8F9cM;iPQhT\AH%/SbYH6"#MT^m\=Ku?&Tn=\
8.oq&)FiB5f]A`)FfqFu#MA&SciR6r=uNs<!#Zo,e052,YEG@g6o6?1jnF579ia.G@7hm;4IV
a5Is2[:WKrjD1R.mpIdioZ'!?GC=9l2Ga8[EHeH9H\C;o]AOHX<kdBtT^cOjb<M)p^MHrP^k^
5=8jjS=OM@+HVW7ia3%Z^6W,$lFR;<06&&76t!X,guqU-nd\i+<09F=E*E,ot3?HY0tB2)0f
2]A>>!^,kMEbq6(RGSFQ'4X&3:o.6t*YAekIbi;Mhb.\>eBK.tu;P>mPYG<>g?[c[Q0AVddbr
n!^"]ANYS)P\X&GFu,$N&'s.CR@%'a,@<BFZidTKT^Cim'uRM>*8r^_ca<'9VV_FKi5&VJXTf
3F3USSpCQ6=fV/QT7^/Zh!]ALSJCH5?[q:u:)I:?+thg]AB&jInQC$"-[BC]AlXn.7b`>ZC,-6C
2PkKW&pruk"Y[5"n7[g;.]ANX2&)?`?5%@8p\b%b]AYMA7PGtoS[qs'G=]AiV?_l5Ud'rL#^D,^
^0=YD9!eZ'>)>kTPm2OKVg`/L>4[S\bWr-I:j_CiHcF_)XZl6_g-[ojX,&%tHEk)/=Y9#s$5
PTHr-Ei[GaCcSA8\VAL"YG%%VnY(-@2EC__15pmMn3_6,Pk4#4f3O\loNIrpYV7qpp16OHrB
t"H[f>/(Y1kBH;U0.F+qMefOK\JmfZ;,^Uc-O;Nh=mfPArRWK`j*]A7r,2?$CJ"V:?h?F64<b
7H6]A("AZlW*;*:_Y`Z\t=0eo&]A!QbSQDdDJ:IHB6eXj-6*M2q5Xe`Bb3Ri&DT>H/PL<>bXbQ
C2Z*^ZX&8HX46'%IPZ?nLEja7sV9V8p33U*4baS<Go\_FY:`Jo_%ktO9%2mea)^KUEP!#[DS
kR43\^OI\(;'`;%tSi5gS+D.rCP)[1=t"@j&#rh/k^1TRf3Q.&:28*8RE4Z*ij]Ae#4h*>ON5
Z@9PXRT6+]A=9s(;%\P,<i4]A&E3;0\Imff7/jhuKW4oBo#2+PgJ?DQWRQ+ES)4=#4u@sLsr4J
640)oqV:jf=?)WnSmbc#df(#c@AmG+6!pqk1bMdBgf[S48F/m5iS@ibA$B0?n^A$hb-j-VE3
[[2?bZ,m!d'5Fj*<oSPEW1qDb[Eb,2IE"uAso9hZPrF`5tP-C?:1llplEE.qB$PJF5*QS"'a
6]A':eUUt,?]ApCI86i.@IiHaRY$D6>._07;?lLnO3<'r0,Z4Nl3(R\jSqM_5%#2GE=>l>)75!
38IeqFm>Kb<T)CL#!C3uRWDIMn_UVj?><VrA&LFN*TM"('+Rc/g'kYhMgXB5_V:22GSNh:0:
G39(bf"]AKWXQmdP2</Igi5UVrD&Z>/FiVockO!Z(?k1[B13B7l9>M4Z'qO#M+SJMU^,WP#@8
,ICO:FMQj:0(7gS]A<YKHSh;!pe!M4Shr,%$^+)rp>+u40-?pG1ho04G-/<^@q+_!B9U!c4Vs
.2s^L/3rg]A)?-mSMl#MoC/D-XVS7%)%Q,l2giY;>[D,@RF=D9P60<;/6O;"J1h`!bF0Ap'#`
TC/]A)hCW;J-M\ECse9=DR9k/iF#r<A@Lcl9n[=*c]A`\pB#QgaKoMG#-hbpNqpar@]AfrIa'b"
BK\;[%UHJN^>Dg#V_d*rFb#L7Brh?YEO,>t34IbU(HlG*+9lMuP"E_WEl4_#Nm:7$CW98plE
fZ:T=G@a0W^h-T86Uj.JJbF"4*Q2M/G!kt'Tdh.rA^a<32\p`]A>KNU0<K8HS*W]A/;NO\E+\@
Gg6Ec`D_LZO[c*?Is$s2o#lK8-t]AN:n#s=PWB\I]AW;/,@HE35Bh3i6OAT\;7bTPm`X;EUR^.
eAWMsODb[cX6>ZB"%m;cDq#?R@flYYZeIcc+_T5a-WRnu5=s1:K5UY2O3`7EghhPinj4j2D^
pUBLjnHX?iO*#.(RllE)0f.PCAtBO9ljhU#G`Co[js#ZLneNnWl"'_J<A7C0o<"**tNtFrcR
<`h*#'umH/_uq3*J_YgFkiU&9lHFQQ4!Q/NO>82QIQ_kHu1+MVOPA,1rPdFUnCAKKZL,EB7m
(oX>m`<;q,.2#BS!=3=YT2'0>&,Q4]AHO4C%PhRhbF@n%I_ODqVDD2C+3kpGuKh[M4Q$[jke:
+Jr6JL.;VJ*1k=On7XZ?XJO]A`lt8qW/L[.sm?i\QoiPF<0YU/9lWmDsXK'UD5s0%'Ef,c6hJ
@QU3"oq`:L@T$_ocZmlG-N`V44?0-AcGqS5@((Wu$6;sq"]A<bc_`pH7j?(+!Nr.^F1fZ;UbB
j3`1iFgb0CNFO+j^D)Iq02_d[Bst;Tn!aln[:`2)]A+[LK4e6/29>C/1C*`r*BPH?LcJB(]A`T
Hk6aiTr2+WW6$eq1_,B6&R[K\rnh[I_JD:PX6pD?q0P&P0q:/9q@Cio#[KECT*la_ZATuILo
j;aU2rfq?no<!Y+g-&K_8Jt<.%0[SoB+DIK8d)F"D1@k;q_pb=(:&F0.M/6&0WPk_VRRbV@a
@a2%sp45XDnq59J^V%E0o0in[p2)_5fM'Fuq8PrScfo<f=_#U8+jK.S;Jm8/m;24[*h!joI\
BL;E6N\Mu[4(.>kt8TbrXh^3Q$llrm-YI2POCrQM*=h/j]AL-V8I'[E/TFM)'riu(KD;j$@5H
Vbl:G26Z`86`hS[b>9eY\%rVQ.bKU$]A!8tkDP;TZK2T\<JMYhflO`Y&UjjSl2=@,<BN6f9in
lWkR<3l:F:;?O;0*RY(\4Kc/"6j$T^V6+"MZSA=XK;L5frHaeN8`KQE-5:M7e8b6%sQZ8pn#
b<0?<E^J(]A<4.04Y/bF*ZFMSBU5#&*8&ogZH9#IR5=aC$"llC]A[Ijaq*@ZkM,DPV=N07!=GG
NnALLp#Smhlf/<lX=9lgl&tZA+@Z_W15I41qL`ZlQn=f\WF,H_LN0%n(VhWW\f2]Amdn47lJp
p`-bBpF,M/9=oL$U1=na7-Tk\q[JQB15]A9/$Ntc5jU1E+U=!R4CX\2(]A=g7/N9l[OkP_as]Aj
h15?T(C8*GQVC"4bW(Q6c)-ls/:[?%OLQa\mgft6Kr^s/(NpO'6T'6*Gj_=GN7kI)/4hUptY
+Wenss#PB#7jEk[an?=C+TIHoYtC2&_jd1$MV4!rHi[rl*eK=K7ElBj`s=k]AjsnMJ_MT8&ML
fs:!"-EE`*Gq%Vj2eG;Rp$h>4=h@H\j%.F&e*9Za]AaaLekfb*9K0's3IgF2*+[^o7)pp[9[Y
(J9VBpJ;%$$6;[#!t0oj4D^!^JLaEPtMpbDd@&5<*\=(\@T`%9\Mm_hV.&>n0W9r+9^5@9$b
CVG[$$H![9Q;TPhd^:s/1E+%m47Dg1e0pC0<Sj8?Sm,QR*,E?B`DPp_)EIp1'=LVnM_W@q&R
[FW?5'QDr$;q<n5h0G(CQmLh/1WBTGHg)Km%+Vo!s]A7@1jjX[U<U0`@D,>PTJu8F?kn`!Mss
^;G!\+Ce>bA9=.A2$J=:9uF1q0D,5qnACkjDAa74IA`"]Aa-.eicQ^V/"d\*gH"qhP%kJa4(Q
0/2;OdZTp-::h4Vgc"uY2:X(=bQlDLj(!a^T(>+#_k.7d4nBWNc.=m5k=NU/g85=/;9^p9b#
!j$;3XZR8sAN+OdP*3jWrZc`n#j+0B[*4SlNEo1RaK&8m^J%J`4+h)TX$m#)H_k\F,FA]ABjc
P'jOuWXgeftO\2?ZqKO92P9W&Z-^,:RR*Pj9QDL.Mp<se^p9j`_2UZ0,]AiF7D_Tcha4`6@\#
R@PiQ.GDkeq@P:q$d&%-#Z=EGV<dtld_j8iqSp4+mcBWQaEi,C'Tc@MihJ'fb=,/*uCfuS'7
IuRm<hX(LP9`rMBk1b#CWDSN()cNP'J&Ai^%"s/CP54Ku^Y-*2N#dW'$</QO@hH8pKQ!0s2$
^'%p"^'XFri&!>B<M/Y,&'3:j9&=Y.foX70V"SrLVY+N_^7nBZ<JHZLVW\VE*l.s^Z`<9LBk
qViJYj$QCE5SW8&V/PS,q_m_MGWCIm_8ST3\7Pa`TC#6I_&B<@6+2Z"+%;4?E*0'Ro6@P`47
0I!PU+-$W_RbXG\u^VFl=@!jHhV8KW@%d*@MrRD]A/_Dh^06uqB+/?nj'BWFoqX'k(s^an8Im
pd[#p3dh]A4,laEpZL[R[1c_BL9`I2VZJ.YNCTn@8j&+iGte>VnCk*1b'9B+i`$ll[UVW:XkN
:D%AZ>71h9tohiZ$L%HeNa3?)E"V;Ztt4U-4(Vr4.#(`kg#\"S_e*3Se;.XX4H9[5g-L6ii"
"kakp^3:>-(@nJn?Clu+1rS/aK*?CTS0biD(+Wo+KpB\,66M6.+lBF*>4ie9\dT;<BVDrclA
5=@o,D%DoMGiDW6;kT)HB/':\G^2e*k%)I9^k+&PK'=?&>-u<3QMt$-q_M$VL)O.GEe:UNdb
KrnV\(L<o-/O.]Af))mC+fTUuuT;JXq,=kZj3%Kdl@#T(la%^2#Vb(K&A4Gn+_[?\K5YXJ4AF
LOPR-YL&OX:3BcM\<7=(n.oL'!;$3o'B]AD.+J'_h]AVo7*Y*O@<rrlRcMJ)TYd9]Ac#kc.Qj7M
pFLq-tRDID/;i\k%Y*hm5J/(24>n&=p5"R!Ifbg8ksdS%b7qL_LS!7Y#WrerN,*=@Zt!k7,*
!E5qfYQ;a>%4NFUm/Blsag!\%qQ1-@3<Cj'ro>LAkl'c#!,)[6M!W1`j`1f[^A^PULlVSebQ
1,^PQ4#M>`6s=2mt;Ih4Eg/0(LABObJ*RlV3Io>5X9^ZcM(KSdEF7FN'6o-&05<b`]Apu3eLU
0eq9f_(Y:18AX9P1&f.PB<XtR/!NjhKs*W(F5io5-S[Et:4sqoWSS-Yk5k3An\65V[)">p%1
h8jp.JO3d$(DtnJ4Y[41DjMYm9Pct>5,qeM>,#TF0:sP'*F=rDRD>GcM10l;sLnm\K#7k>#Q
r6JTk##OF?20hfhq5Ih$;<8boZb7+geKhNT9j>MUKQWS5,qZ!trR8I.*p.Mr@m\$b-lh$anO
*CVk-HtgD`>AuRj.>X'GoY1D9.m,DMd39d'QUoMtngu2Q,2lEDmUCJF-_ln*5+5UC?<&t[(C
V!eE]AU.PfP^S:<0tE.#r\YV?H)PPPA,ASaIZ;U:_@F)Gc1Y1rr7&`Hkk'YI2Z[2bZ`.h*eQd
SD;d%da<0d?3;SEro2l'6nr!m4\n2c`QRH2_0^U0\7:`)qQr.eB[@"pe4,(?H/kSg!T#,R*Z
BgXGp:Ys[mOB<gmW'XA(`%TP%9/n1SfYLB6k;[m:j"(=[N9>k7M`E_@BPD-TW&)!,S=m/3l?
**QXWsLUpLr[#!',2qSbH0,MD2&HBk^*"'U2'i0b0@gqY_"pcO6<Q#@mus)h/Bb3:3j!B89r
p6[k%aPuS!KmOjAb'S3#cf2&8(/_s_QE3_TAYpZ]A`.%'f9bqiJ^UVWCQad`<1eTa>rfiI8h&
Amug43rb+J\bfpTj$t%9VT6HhBh0phCP*Qu[qg`%G=Ald*5S4iA)BOsfIu7?^\b;$$ZMAim[
@p27=OhV*_H@0^ubR7Mt6(E<<.8md'W.,/YClA*)`#tcqCCBa5SmI,J4fV.M7BCskZ4a.s1$
<q-nC'Oqg2&6&g=mHFS63N[tH@<kNF#]AZ;/97WLN@]A=$jtE0+5bV#/^GFhk,_b\@#J<p&TEI
r4\QbY01uqJsKR5)I.#uYW]A+c*:Q8E&b.0]AUhg+S9A"<j<iRD+E@)ang\d[?C2VQuS"[VS>g
@4+R@loE67i>a$JhjYKjE38idMae$>EISeEPRVi35AF11Kcq2*dW]A^L6YmqXDeEQF@g>SBa&
(^o02;$3_Ig?k/YZEjOVX3KiVt4+0H2"OYUkX^b.$[C/3l:k<aXdOQW^V-.(cg?nd_VFS1X\
@N@;b4ZVh4oE8:;W+c<kP2]A@O=P*7p9@GK`E94D,D,9CKmd]At_)`?/PXdXOUV>1`(G3l'Vb!
InNi/mihfF4l^YH@/pQcFRI9&]A(m)?"\N'7!HEt5MIE$[61>M$`4_>l.[+)47NF@$C5)(O3*
P,((@(:>MS#QpBteS38/XDa1kcSL`1Il/OG*bG"4:H1)U<elX:m^F5mH@NC>F5FA4Emka,Ll
iVa:i%SbLrE-5!2brSnoi]AXrY!#MHl^AN=A-AJk@_p6"NQ8T#h%=g;_S7%!oE\R#.FF%%fP\
+IA`W[]AqYTkkQ_ZHUL&.iRPmeUH3ir1S+L`ZE,#Q*L'R)$t"bTb<g^\Q"PU`?Id14\q:mYa!
,4r*HXXq$JZjGl'L3#mZJ?R*WoL:dB0rsSdup>PX2P9`t)YlE2C>oB@S0Beb/XdBQdgqE&C6
L(rVZ:\:(,i;j6Zp^Nsg8u@04=heW``ap_ZG"M\0R=Z4qt<W_qs&o4'gIYmWAir1gdKPbY8Z
U'[7gXaqQOd-a)f+`98jhDER^)I;ULV$\Y4/1+jA-.Xp!H1ruGhu/+CHk^3^@f\%APXadcJ:
B4):on#XhBjR820[7SZ1-dM`F~
]]></IM>
</body>
</InnerWidget>
<BoundsAttr x="0" y="270" width="960" height="270"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[window.location="/WebReport/ReportServer?formlet=demo/analytics/multi_report/all.frm";]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button0"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[总体概况]]></Text>
</InnerWidget>
<BoundsAttr x="126" y="0" width="140" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[window.location="/WebReport/ReportServer?formlet=demo/analytics/multi_report/subcompany.frm";]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button1"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[按分公司统计]]></Text>
</InnerWidget>
<BoundsAttr x="691" y="0" width="140" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[window.location="/WebReport/ReportServer?formlet=demo/analytics/multi_report/datetime.frm";]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button2"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[按时间统计]]></Text>
</InnerWidget>
<BoundsAttr x="266" y="0" width="148" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[window.location="/WebReport/ReportServer?formlet=demo/analytics/multi_report/district.frm";]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button3"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[按地区统计]]></Text>
</InnerWidget>
<BoundsAttr x="554" y="0" width="137" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[window.location="/WebReport/ReportServer?formlet=demo/analytics/multi_report/product.frm";]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button4"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[按产品统计]]></Text>
</InnerWidget>
<BoundsAttr x="414" y="0" width="140" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="label0"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue/>
<LabelAttr verticalcenter="true" textalign="0" autoline="true"/>
<FRFont name="SimSun" style="0" size="72"/>
<border style="0" color="-723724"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="126" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="label1"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue/>
<LabelAttr verticalcenter="true" textalign="0" autoline="true"/>
<FRFont name="SimSun" style="0" size="72"/>
<border style="0" color="-723724"/>
</InnerWidget>
<BoundsAttr x="831" y="0" width="129" height="32"/>
</Widget>
<Sorted sorted="false"/>
<WidgetZoomAttr compState="0"/>
<Size width="960" height="540"/>
<MobileWidgetList>
<Widget widgetName="button0"/>
<Widget widgetName="button2"/>
<Widget widgetName="button4"/>
<Widget widgetName="button3"/>
<Widget widgetName="button1"/>
<Widget widgetName="chart1"/>
<Widget widgetName="report0"/>
</MobileWidgetList>
</Center>
</Layout>
<DesignerVersion DesignerVersion="IAA"/>
<PreviewType PreviewType="0"/>
</Form>
