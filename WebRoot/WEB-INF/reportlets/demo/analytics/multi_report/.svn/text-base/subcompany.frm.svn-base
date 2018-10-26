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
     货主城市,
     round(sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣)),2) as 营业额,
     round(sum(订单明细.进价*订单明细.数量),2) as 成本,
     round((sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣))- sum(订单明细.进价*订单明细.数量)),2) as 利润,
     round((sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣))- sum(订单明细.进价*订单明细.数量))/sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣)),2) as 利润率
FROM
     S订单 订单,S订单明细 订单明细 ,S产品 产品,S类别 类别
where strftime('%Y',订单.订购日期) in ('2011','2012') and 订单.订单ID = 订单明细.订单ID  and 产品.产品ID=订单明细.产品ID and 产品.类别ID=类别.类别ID and 订单.货主城市 in ('北京','成都','大连','昆明' ,'南京','南昌','上海','深圳') 
group by 货主城市]]></Query>
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
<![CDATA[=\"   各城市分公司营业额利润\"]]></O>
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
<CategoryName value="货主城市"/>
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
<![CDATA[="   各城市分公司营业额利润"]]></Attributes>
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
<Margin top="1" left="1" bottom="1" right="1"/>
<Border>
<border style="1" color="-2368549" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"   城市销售效果明细\"]]></O>
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
<![CDATA[1066800,1143000,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[4343400,5067300,3924300,4686300,5067300,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[城市]]></O>
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
<Attributes dsName="ds1" columnName="货主城市"/>
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
<![CDATA[=D2/ B2]]></Attributes>
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
<![CDATA[buf<T<;HE`W)/E%U8;iI>;js72Vf'*\"lHWR5chRAmA,6;D(D?6mJ%\R7cJ^<g%(Ya<7rqaY
;UK17CPk;DFjW`lR7c7P8?IVtRU'?[q_*S2npYF.U_L]A\i2@IEq*5eiT[o-nR`Y:sqEW9^PY
)naB>R"g92jK`<m<<hW?)J*o/L^,Ph$DsKboa8PI%m6A6gc$sGQRof[BcKS).M-,kn*N_Ppl
$&02WN@dV@'\lcV#2g'a(COHh<R/[runq841&U_5G%4N]ABt`1M+\@__t#t$a!a:3EQ_j7]Ab=
TP\0E))c=?0`l_m*]AN,<Q=:9O,Ri;BCl_#Q5K#E2^u7=o%iZj>sfqScCq`*"'"`)PY'ke1/c
EX2!OEr$7a)H,_._Df'=r[Zcc8M+Q6cg5#\A/eRa4.15tnPi/fN9[TQZL@hbqjH_/o]A=BK[j
#VW=b.a=A?^9j5qoMWZI`cbY3>J:%WccW<RNFPLA]A/484?o@Zlr]AS-dFtSaH)kQ.9?q@5]Ah4
cLp%h@Oo/5^#`CWM>^'XeJ.EB1?Q:nV1Kt]A&-P=s-eniV[NO*#/o-<9Na>a7=M5).'p1>]A"6
-<'XRsuJ^?:2ucKns(.+nZ0^DZQ`/Nkf>MDsF(DijF3V31C#aM1rEd'/]Ag8A6NQ6>/Wt*>e5
sY:r)P$*Cdk;h!r@@WoIjnWoO)(b)G<$2\LLWLu0%Zq"XC&%SY@js(X;0*"(.+fY5?*DQK1D
iP^a%$4HSa>[OH8lM_l@L]APrlYF6s1Kikk)d94$u;D$W@9QiF,hlUK#,J^+S@QaXPKg"-Lnk
RY_HMK'"TV0d:(Ht25OGjQbfc/b?jr\\O\9]ASu7agRUg1iU#'Who?n62ZPgJX;2:$R%f:?Yc
r7#Z1\,`PlAI_9#,\e9D8`)A3js1`.SBYm6iUm#^P3VZq2Ca(qjc2:cbE28MZgC_iFlt:Eae
"O(m%r^@i79^`b1Kq8&lT;[-`o;)[r&T@p2+G'#Wg#*A`N9>/k4bSiE5P`PfZ6;%/;b39mE>
+ZDd$C0qN;_AW`TH-W*X:7!HDi$eQ]A_;,&"jr,Agh*k\D70dR4_MMJ$s%ko9^BC:iL\=d=pt
;5nri<!XP?Is=HTl*5;#8>(S?NujT=qsfjH<_=ki\t^jjkrP=#C"lgWlIf<V`*7?2,Bd<Sh(
UKAb$CLPBKJiY)8=)If\U,Ci]AmPQ5M"\_8]AoUt4VHm6@.8Q#X8tQ/(!Qu/e2n_O"u>WOc6Od
cqOX1+@h>C22K&(H<%sI>;S`Tm09U#>KVc]A!",c>PcCUrCnMU3HHc-t.U_;+]A41^g$%\nZd>
5T<q,7_i:\#loPBp"EsB78uH>BIjiJmBJQnQSPshg`k#b@K^_GU+4Fb]ApGmh2QK,4[1E:68*
pfBn]A6.]A?0C4r45SsBrak!!b6t)HA^K+iG$9>r5ug2:?`_6^2>r.G<5T0P`L5OeSA?bp)uJ1
ee/A=&o#QYVhR,NX]AKCW=*3eE1OlInhrd@6;8YTEW_B9E'678+NS^*[fZn<R0bc2W$]An@I#H
5/ai86@[G[@WmBT.dB=Nij)^r1#YplLC)3BW6(XH$TXlJ,;<Bn)=;9E%5nDn0BB)sGNZ("[W
rOqFMm]AI_#)Zt$"P)U>3=%HmKu?2]A@S=p+)*fMbBQ?.DET(ntDh*TK?iXrHb.D:V9[X6A8Fd
U""0b4()3:Xk,Rl?"de`en)kUttUBJ1+i:,9Bi0kao:`/)Qkj:i.E_.3)2*#'k&>mbqhpNkV
B]AI<:*c"_m99^>75:BBD)D/5o_C(K(8XAn8tn8?ENU2'CBC,@@gO75-5I[_ECQMZKo.qPNhR
NpC/f80L-6>`29N[:]A1ln%*PTeB>)aO__Jb>ieWYc2%Y+7k5N=S<Xb>pNGB/,;5L,?(I1eJr
&1lVfBQ=]A+:;In[OPJ;6HYFP=PF^3d>AC<e8_0bEs.2kjs0^1<uMVg)oBY0tu&@P+'ss#.Wc
V^gl6fpCVn*+\F8VB"8jSAlV`mE-B50[OZ!4oMXnkc&6MK9k"=&?+VHjJWn8c-%5Z#`j*eTf
BUNZ988-7V[$TX<(LI&$Cn?^@DCPpg`nX[$^j<nlcQj!-H&;T5DS=2P8*n5&8UToHbdG)Q4s
qsrJ#I/"/8I#KUUK4$$]Ao,<$(`NYkNQQ)i=GbXInF%98MV1b%QFb)Z:%G=HH:l>.,;$Q5fQ!
,[Ypi:]AB/-VBO,`$&(t&1JV2q>Iqm9E1M]A4;c`7L:"ncT>F]Al&f)7_9ID6`%nU/5(0hhXCCC
r;aKm/ci'5aPB]AV3*h7@A;Q_e7U\DZhFQL6]A;)N,.hNN:ouB=Y6Zh"HoZ?C=j+3mt\Ig1Q2r
2.'AuJ'H?SB6a=B%B)6pR1N,P!Ht'&/8A?`qnkbQ4JOf(DHWUXj<W<8:@%+?VP>[]A5$$Omg3
4tjdd_\$HL4@q/Bg'J>`HSK@_YXhlC!^_'5/Dk16N.#,LsW9(Dk#*`>GKI8ki3->L3f(81C5
SJqW^IPOd14%dC;nXG;bt&Y80qV:[%s0DVVDV&,6(Sc@<?6ig7rA-T=)#,t$Y/3`7j%'&UY(
gUlR?$f>DFos/lM%/9BK4-`*^8h5::F/2rc_If#&e+WJcI]AkLOf1]AFG+^uN'X>$n#;k=m4>s
ob@]A2ER*2;upmOfIGeUSkNp!g#pdopA)3mNPX_F`qBUYNX`;X\l6O$?YTl_T\0@$Q`<bD2<M
"\In"aKf@[^mZA/_.*(N"Y"n35;eV]A&'8+h(h&l?lI?cp7R53k&?k@tZK^]AeOVI.`ioca.^/
\=+cpg^AJ%@l7aN\KS`mW$ZqAluJS/Jq/D(SVH]AAN?O_Y.ka%:s0o]AnZ%JQ,$2:Z_A8_\^?)
MmH*XV6A3sP4(7u[jfer/K<=uF.eqHK^*9Y[7@[ZW&n7Sf[X<^EILC`'O,gh@'fRV%(;KQnY
ehG,mYE'UZ&6I1.M,u`.UN.ZOeW,rGnkqG+_<D656\:&*0uX:e_U:\"(q"C4S>R4^NAUh<r6
H)mil=OAD>1;#nRS>/g:L0:9qc3,BlJN?]AbN&MfP@*^&>lPP[;T%5O4=A>.7&]A9F`*shBS?]A
W=cDqC_V/u7>-DacnKqjocbXsO62,ES09;B_SYQJI?,p/##V;#u+7=pQ90gC)4/c)g',]AE9#
ASQ74]A$MYgWn/9mEjO@p8ej&Y^bRrMctf@3b)6%,M+N^s3_*_a'*2@i_@JBh]A9A]AYf(@g7Bm
*L=]AK2Q>Y5q`9&%T<?*QrRa@D5Nmi&1dhg\qcB=PVp7.W$Sap=dI*$BM.aX$eQ=*DIHbrr9I
^oKd@;El^Vd"+^8mIa]AnQ=;JV3aAAhI>?3"Hc?]Alc1[!P*QO[eC3u#6DMneYke@fag-f>u]A`
lPgDAQ9h/6gc)-=kf:_f1d,qH\"42_Tb:HY/4:;aM&nVo2b]AiR0;sW7?fNa)_D6"@bM'SN5J
c"[j+WKs9dLO96,0q(-]A-B_J,SIVsj3on[]AY548cYW=b>HN?'5GdM7akoh1a"`pLR4jA49J/
/Vk;0i't2ZJU">Tg4IM:rt"@L^@o-q6/E0,CsMhl4Ooa<2a5LFMkt?ZjUbDD:[qXrMk]ANq5s
C'?T7L[<[@;j9PP8J!Z(FHjag\pljdm-\QM2McNua^j3[CJ;h/N-c^nSYCNTs$cXL90A:gC.
6?FfXl_*K?koeV>_=Lp4QAS*L/5<7e+5KkV'r+4BDg#>@cG2g#fsi@9:=\+rTBFOJ)g_Z/-d
Y0oC9mp]A$lMB+VrI*\q0--uY*Rd[IVYrm1-t)EW1X.N4aSmO)KqE.]APcl#8R3K(4Ze9s"1$I
57o@RZWhf`O_Ll&BEOYSjoM.e$T4chj3-n==`hk#c(f=]A]AB-?7"HmegSnP$t:\VJB%lGH*cG
[j5qPor/Pr%!ZGMEe,CJg@23#]ASWe;"P`_U[3FsR+N`8-uEKW#8""[!$-1NS@,l+f62Wp?B,
Jmp6[BK^,HLVk$qE>"+AWhP7^i>Mf$JSQ4]A(KTlp5D=SW=C3r4dRL3SXe&?bYEk.Wb'AK%Cp
Eq+u+U%)0&SAE"uJ:$Z.DOVJN/,buL[3Q+@+6pc^kZKTlVGbeVmu"C_&9:1V;;3\]A1OUc8h9
2%h\E@k'Lk^S#<gYsF:*pgP4I[SO;$?>A/4`9s?[=`E/S0;Sk#97_qGVMpXm=WCiT*duBJ4S
FoKP3-JLD_O9+fRUhk1D^g*19.qi_Rn9afnFbP.Oh6=H`<>XY</2ImVBVNCNO&#>io@Rs8uV
4Xg7YuRAfo;K+Yj#i3271_cV2B:(ud"G(<F$HL"!VLL;/ZGShDCCkW%WJ:BG_@-_UFQL-TTF
N^OffN.PmS%]Ada7%'Rr6:^=eW(EG<RH'j/Q_Fpr\S=A57X@G;7Omf[dphQO$.+LRR?=7'7XA
;-lQp-\+13%MCVZ^CG6b?<#F<^'7H$Y$n[3l;o3RB!-&,c4PgFLYoa?-24=8alf+LN3tRds6
1P;hVknuEE./N"bPT7CB28mO$?3SG_aVlqt-5L[o*j@]A34Od;-hBc($K)nMRM%Id0Kob7!A7
\jVfDd;(>or5U-Pq-*j7D*9S1[Z/FH;%a4oqL*LT;dn?PuX&#TU)m\d9\H%>h:;?o`#!YmN/
VP=QipU5cVYeN3[a/@/"<&32&6"T#DHP*%'m^Kg,&gFQV8`@iT[FGgrjEX&^>iC)m8`r>1K0
fJhR>J_#Vdgi3L6.r@`Q6MDLQ[`qjdrN;\@gml8#W]Abu6_\`ii;kB9"]AB#;.a/9]A6j'$C+Uc
_<%]ApBDT^\Ye\!e?Zf,B5j$od*V8a_TFtckL?W^9%@B;M7J/qR/S6Z1,hJI66Fke1YQ+)@>H
iqt&6V'KT[cH&dI"5oUN[=#s#Q%!19P/F*;0cuJ*O[O9b5PKo,T3tjkf3'/op-9dmHu':'tY
GDC+:M&sO8W^<[?gE\;cXA8mSqo!`,?C?NCEaR(M:`u$k71*KMNG4gp0hH7[Uh2>KhR%*_di
*mftcT[b?O2%c.DTWMnptG_R8El\)/^OV?pjNJ^N:"oRoC.*]AS<(He/J\iU)KTF<0P@n[C/s
(XN7`bS3'WP9qWRb#6ZY5#*\EVW5pS4@_u6*in:o#;n@Vn:E?5h\hD:c3+QN/im5O'=k&H]AU
:f*",(0Ufo]A$pU>Od..GPL021OVN(%Y0I<"m%!u(EpU5/KA#"2iEice[u2blFL6[4O8"HDKR
=sqOatdami"=1M``Ws4uK$BjlP-JW$,K9aIdS_n>afn?dg[l('\:1ZCXuHk!\)S%P/!rJ('@
,%\&;6bnIA,XCj6nNo137%a-<#RmCNDelsa%F5BL_ZecO2j(H$0[G.&=`ee*V;<^d8>OZpbW
Zk(8qu)?"6UWJ;hu?_IiVE]A(i-E&)LcQaBKtT'*AWefm?!u!gg:q\#NPmB282[$Gb<jd.g+E
V=^6DpCs4o<>XR[5m14SYpRno*Ya=FFkEeGfX`cFIh[30"P;gQ/H]ANebI%6BTbEU?!e`pK(a
dK-*rV?:G7%S5i-`QB?g^/_E^-?PK!cS&dfLaY;-:3-VuH:pR]Ar"m@QD^6;ZJ:&0o[2P^0NV
mf`VKlQK(N5oj*u86(]Ap:gRqD/jM#3A6"-[pX`iEqN[,a\a5&!<M=s#_^6mInWq`\S`S]AnfH
X<pje<J/kq)UBWt<dVJ\=0[_S4s%?j=+n[:@h!q3dP/_#CqCGiJYu9X%&23aGCUepd\lEY#5
oW\!^s"5r=>*t*Oq7u[CSB+$g*2oqYm*F)L_Jmur>eB)Ap[q[Q!bi:>Qc"MXJNo/F>\u(,TR
LK'fcS7TAgi/YYgVDmWjr`*j"8))E,.)SS$HMFQ6a.p`B~
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
<![CDATA[="   城市销售效果明细"]]></Attributes>
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
