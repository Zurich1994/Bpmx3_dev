<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20141222" releaseVersion="8.0.0">
<TableDataMap>
<TableData name="ds2" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="saleman"/>
<O>
<![CDATA[1]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[select a.month1,实际-预算 as 差额 from
(SELECT month1,num as 预算 FROM sale_month
where saleman='${saleman}' and type='预算') a
inner join
(SELECT month1,num as 实际 FROM sale_month
where saleman='${saleman}' and type='实际') b
on
a.month1=b.month1]]></Query>
</TableData>
<TableData name="ds1" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="saleman"/>
<O>
<![CDATA[1]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM sale_month
where saleman='${saleman}' ]]></Query>
</TableData>
<TableData name="ds3" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="saleman"/>
<O>
<![CDATA[1]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT distinct'销售贡献率' as a,contri FROM sale_month
where saleman='${saleman}' ]]></Query>
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
<Margin top="10" left="10" bottom="5" right="10"/>
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
<LCAttr vgap="0" hgap="0" compInterval="5"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart0"/>
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
<WidgetName name="chart0"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-2434342" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"   与预算的差额\"]]></O>
<FRFont name="微软雅黑" style="0" size="80"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="GradientBackground" color1="-1" color2="-52" direction="1" useCell="true" begin="0.0" finish="0.0" cyclic="false"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="GradientBackground" color1="-1" color2="-52" direction="1" useCell="true" begin="0.0" finish="0.0" cyclic="false"/>
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
<Plot class="com.fr.chart.chartattr.Bar2DPlot">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="-1" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
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
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
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
<Attr position="4" visible="false"/>
<FRFont name="微软雅黑" style="0" size="72"/>
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
<AxisPosition value="2"/>
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
<AxisPosition value="3"/>
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
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
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
<BarAttr isHorizontal="true" overlap="-0.25" interval="0.0"/>
<Bar2DAttr isSimulation3D="false"/>
</CategoryPlot>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<CategoryName value="a.month1"/>
<ChartSummaryColumn name="差额" function="com.fr.data.util.function.SumFunction" customName="差额"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
</InnerWidget>
<BoundsAttr x="0" y="38" width="291" height="229"/>
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
<![CDATA[="   与预算的差额"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="80"/>
<border style="1" color="-2434342"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="291" height="38"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="title"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[="   与预算的差额"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="80"/>
<border style="1" color="-2434342"/>
</title>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart0"/>
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
<Plot class="com.fr.chart.chartattr.Bar2DPlot">
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
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
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
<AxisPosition value="2"/>
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
<AxisPosition value="3"/>
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
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
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
<BarAttr isHorizontal="true" overlap="-0.25" interval="1.0"/>
<Bar2DAttr isSimulation3D="false"/>
</CategoryPlot>
</Plot>
</Chart>
</Chart>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="291" height="267"/>
</Widget>
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
<![CDATA[=\"   销售贡献率\"]]></O>
<FRFont name="微软雅黑" style="0" size="80"/>
<Position pos="0"/>
<Background name="ColorBackground" color="-52"/>
</WidgetTitle>
<Background name="GradientBackground" color1="-51" color2="-1" direction="1" useCell="true" begin="0.0" finish="0.0" cyclic="false"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="GradientBackground" color1="-51" color2="-1" direction="1" useCell="true" begin="0.0" finish="0.0" cyclic="false"/>
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
<FRFont name="微软雅黑" style="0" size="88"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Plot class="com.fr.chart.chartattr.MeterBluePlot">
<MeterPlot>
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
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
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
<MeterStyle>
<Attr meterAngle="360" maxArrowAngle="300" units="%" order="0" designType="0" tickLabelsVisible="true" dialShape="1" isShowTitle="false" meterType="1" startValue="=0.0" endValue="=180.0" tickSize="=20.0"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80"/>
</Attr>
</TextAttr>
<valueTextAttr>
<TextAttr>
<Attr alignText="0">
<FRFont name="Century Gothic" style="1" size="144" foreground="-1"/>
</Attr>
</TextAttr>
</valueTextAttr>
<unitTextAttr>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
</Attr>
</TextAttr>
</unitTextAttr>
<IntervalList>
<MeterInterval label="分段区域" backgroudColor="-1620162" beginValue="=0.0" endValue="=60.0"/>
<MeterInterval label="分段区域" backgroudColor="-208375" beginValue="=60.0" endValue="=120.0"/>
<MeterInterval label="分段区域" backgroudColor="-11683767" beginValue="=120.0" endValue="=180.0"/>
</IntervalList>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
</MapHotAreaColor>
</MeterStyle>
</MeterPlot>
</Plot>
<ChartDefinition>
<MeterTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds3]]></Name>
</TableData>
<MeterTable201109 meterType="1" name="a" value="contri"/>
</MeterTableDefinition>
</ChartDefinition>
</Chart>
</Chart>
</InnerWidget>
<BoundsAttr x="0" y="38" width="291" height="205"/>
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
<![CDATA[="   销售贡献率"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="80"/>
<Background name="ColorBackground" color="-52"/>
<border style="1" color="-2368549"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="291" height="38"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="title"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[="   销售贡献率"]]></Attributes>
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
<Plot class="com.fr.chart.chartattr.MeterPlot">
<MeterPlot>
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
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
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
<MeterStyle>
<Attr meterAngle="180" maxArrowAngle="180" units="元" order="0" designType="0" tickLabelsVisible="true" dialShape="180" isShowTitle="true" meterType="0" startValue="=0.0" endValue="=180.0" tickSize="=20.0"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft Yahei" style="0" size="80"/>
</Attr>
</TextAttr>
<valueTextAttr>
<TextAttr>
<Attr alignText="0">
<FRFont name="Century Gothic" style="1" size="144" foreground="-11683767"/>
</Attr>
</TextAttr>
</valueTextAttr>
<unitTextAttr>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft Yahei" style="0" size="96"/>
</Attr>
</TextAttr>
</unitTextAttr>
<IntervalList>
<MeterInterval label="分段区域" backgroudColor="-1620162" beginValue="=0.0" endValue="=60.0"/>
<MeterInterval label="分段区域" backgroudColor="-208375" beginValue="=60.0" endValue="=120.0"/>
<MeterInterval label="分段区域" backgroudColor="-11683767" beginValue="=120.0" endValue="=180.0"/>
</IntervalList>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-16776961"/>
</MapHotAreaColor>
</MeterStyle>
</MeterPlot>
</Plot>
</Chart>
</Chart>
</body>
</InnerWidget>
<BoundsAttr x="0" y="267" width="291" height="243"/>
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
<border style="1" color="-2434342" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"   销售代表每月销售\"]]></O>
<FRFont name="微软雅黑" style="0" size="80"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="GradientBackground" color1="-1" color2="-52" direction="1" useCell="true" begin="0.0" finish="0.0" cyclic="false"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="GradientBackground" color1="-1" color2="-52" direction="1" useCell="true" begin="0.0" finish="0.0" cyclic="false"/>
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,7886700,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[4572000,1219200,4114800,5486400,7277100,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="5" rs="13">
<O t="CC">
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
<Attr position="3" visible="true"/>
<FRFont name="微软雅黑" style="0" size="72"/>
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
<Attr class="com.fr.chart.base.AttrLineSeries">
<AttrLineSeries>
<Attr isCurve="false" isNullValueBreak="true" lineStyle="5" markerType="NullMarker" axisPosition="LEFT"/>
</AttrLineSeries>
</Attr>
</AttrList>
</ConditionAttr>
<ctattr renderer="2"/>
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
<List index="2">
<CustomAttr>
<ConditionAttr name="条件属性3">
<AttrList>
<Attr class="com.fr.chart.base.AttrLineSeries">
<AttrLineSeries>
<Attr isCurve="false" isNullValueBreak="true" lineStyle="5" markerType="NullMarker" axisPosition="LEFT"/>
</AttrLineSeries>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[系列序号]]></CNAME>
<Compare op="0">
<O>
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
<OneValueCDDefinition seriesName="type" valueName="num" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="month1"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
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
<StyleList/>
<showToolbar showtoolbar="false"/>
<IM>
<![CDATA[eU)R(<.FeS(($\9ajsp!el#&IqNcf1`)t#3$"MleP,0S@ac\4fPo.M!G-Voq?cF-*qnA)%bk
!h^d=&dDpN-@`Im&kEq."$^3M4X*^>IObcK3`MgN#,Ma/I)#aCsXQ?<89pgW*#X[\rQO?LS=
,=!p7&FtlSfqGgPYY,P_GA>hl;khKm@'0b!1I7kq4'JK8]Ai`sGPO(ME62*;`7F&^4;Y;5R"c
dBLFIc7pf`U<,CpqG$r>!`#S;g`C]AmgN]AGTD(%/j('#05O.U5L0p)!s4BMf\[,<k(K!krR&p
$Adj!RF^[>D@q=e!$`2_>(]A_e..M[?S#<P.eF@X+ak-Gg8-)-[c\Aj<IRqc[R=a/.j5e[Mt*
hTV6dOp5LV$qQ^hPaJqFg1]ALr1'AMMV+6h+,:PADMVthM!-Hk>$O!(R@h*q37tVLXNITDR_2
K']A$a0lmNu7R[Kmrmnn]AV!^2;,p01:[9#Cb>'SP1'aHbPQmn=Oh0*YRDT#S^;[Q`R^(-^fbo
XDEjk0:J4ZqIKgHG1D2WDV@kYXKB94C/'c$T@JhU0=PeUVAEW_k$6,/m'P?o*G+UFfVe7h`m
1Qicntn!nr1g:@WH.c:al#QoPY0PcK*X:o,+n)7LnWO`aRA@QJEX(dCJN^DB0^WPP!^TOi.K
`"Cb6'-d953UA0JRZ\ek2`*EL4X6gfr.n(u7/;/;1hE#1;m0-1'QSE5Z5dH>I,%SY9850M)7
CoRSG<CQ5$k]A/d%=Glc"`n'Zc"l(mYCn1`dgME[<@B*uqWCI'C&<<Ze0WKR-N5CbH:REC8;Y
0g!aI)jum=6MI+i@2F7uVnfOTLsk2XE$2G5rJj/0+S$)i3L7PoStQ&)kc<lRe+S:.Ff_0u`7
X*C\,+m"_a/%nSAL%O?hOHOJIp;Id5u^$4pO\1t.-Sf4-E+l$1.(1\&]A!&fM";W1iI!0@sC:
ETFZqnfXXDL$IK=D$k]A3?j.RlEbkl]Aa,sgm44,U&[Z/_s61;a!E#o3,'%F1qu#.$0Y[5AWG]A
(/`H0T<Vj3uUO,QNL%Hh]Ad&5l9/jI5H^FtCTi<]AH@\QJ8<%]A8s9h'/"3QO"K:NeQ9\'dS1c.
dN6$qE3So$h=.md<JDakZ%64g#qPs0(1#Un?g+=Y>o&/D\D5IGkL:-Pa,_[<AWD*03d+_peA
p3th<?aq<3X&-J\WNp?ce0MUo'spH>^/>bJ[#"\C-(Sk1L/#`a;(U&GMW2%;n#9PHu`A*M1!
u&L`/`[<O\qr@0ih>4246!SZQ>bW"Q=o/@Ho'Pj9$K%O3P*%-pa,J(IuB1gaQrVZSj;scJ%<
Y:Yn0Yo;?N(au6_T_A6nGP"*mIEgG$7250_Llc7j'Jt^1HrDmT-*B&F_9(X-!;nnSa1WfM"e
A&L!A@N<A-*uaV\#BJ&*JOH.i1*lc6Vuc$=3IjK8'b0a-\(Q9I"CeV4MeGTUqslWWO)HGG;n
K5%nbK7Hh#4]AE-Tq:^:rA]A;%;!FN9<gS]AT0Ild9redKCQPL"V1+]AE]A8mY#r_B[]A%aF7U1-Hd
,8JHU)n/E0(m>JeL'LYB9Z1Ra+-6I&lPX:g0*U^KSUN(F/0"+7%FCDi:Y:C\b1id304!QhB9
`j962u7tf?,$0r\*;`1o(=/F]A<DafRZiTGq4G]A#b'Cil-q?MX0_PSg0HYqZZ?1pC>ZI\#4BL
#dZ0B<P[.OK^2OHS9V'J)lUDm5Sg?[hh.cn2'gM1REWaONiXQAK#.3T%*l!`-EnXIer[C5JW
gj$4gYZH6[62$NbjZpAhOM`d%#GT;)54.qRs?S-&f18>M32VpicNEHJ=!ddEI1p<Clj&qOik
c$s>EL=O=MZ:V"4,W#Rr_Z^UpM_3(H/i\(X!]A/JE;oA-YK'nVoMsZRc$9FTHh@<;#gRi26do
8*4FY;KX)7.nT*[$jI9>+BhEK=Dm7d=?*FX>*5-Kn#`q"D;)^(To3f^P?e+j0]A.omj`m-8Lt
:WdtbRE#:!tN<LJ,UG,aIS;XS2hn2?^m^D[n&.oM"-?+t8ks\Is"(h??=b(#k.qO-(FZStYI
XXJ:=6@:ICrVPeEiLpRk77Nee@IV_=tXO2^E4*>QHHl$cZcb)g+RgiX3k?"q!BfJWdbaD&;%
`+7NJE@WF#fL4[1>q\uLE"MfHlDplYS.li>%j7f]AUL,3gl=MLQ^pl!F08l\,T3`!XeI`0s1$
M$QgMf)9biXs&s_7)r<cH'e3'g`#2<3=lRR%M[RT'Gg'W1q\sV)6EHSY'A3MN]Au!/mcuA(qS
mCMj:Mq7B_e;p7qD4"fj%[LdF6L"`==X8iYBB,.J<6sZ<hs"5X5F"gr9N4ESdWT<@`jVPG=Y
..ZRNk<XKP"lE\Tij9XW16#T[pbM,HhP0AJ"'rG80/rGJ3E(]A,f$YN)OhFDX5iWl?lF5s&Z0
'gF;-t?asVdrucgR/B0f():"7ZG[QD(5?Cb/NRN:XGO>KG`<p6gds??G4,DKsmUeXjIJd+a9
,R`Z"*;C!7(,?h5@uE*jR]An>p-A?RR",)iml2@nB%/P%"U@$o[.1p8^+a:Io#NPc)O"@U?9s
$^2HXI^J8U_2h>0Y<PRDfIs_UDmQl3<b=%)le8b(Sp\/=+:\^>:7E9JrG!mW$oN6bC^s>[OQ
lD_X\T#H8e't/QKM0%o1=GjH2G+;[O6S@at)fp*o-U=nFP[gl)"%]AVf,_-*(EEKBesnG@ReM
H$3eA7\-I#G3Nlb$^isT&nnYcjm&d92i('(R%a_E.7W3pg^1%B4"s`_d8=f8)J/EOMORhs.j
8*lu\M7WWI";RIej[pe!/(LVgW7i2gS.9pOR`M0(+=@DBD]Aa1Os:I]A$X9P(p0l!UFgjRS@5H
U)Ou?gq0SU@X@jniU$>N,ur*C38&5Yc.<l2ag$0N9UZ*Q9@g3Khoj!V#*q:)N"9rP+nO2UY3
f4oVFT*KD?q4RLC>-mNW0)*;6\/j>SeTItM?c"u;iV$2)`)]AeIId<#&g<%\.!+`LE[-5'YE4
@E!0kC84.XV%,4St[S0^sXlX1X/*<;Xp<,%7?5K_oS%H,6H]A?:shYP18)K,2)--O>:lE2.E;
SbHj22<sQ^iafB+J>5'pD0JNS#+/7NdqK19;*%HX>"lrZ7d:%Y.&:&0Kc8gdp<;`D)A6p4$[
_o,_(S!(.c@4<oK#o9EMmqo/P&bVC;Gt*BGqObsM$&m1rYi=;=FFLBmAba55e/N]A-q6N9,n9
ZHUn&!.KXFZ>kEaHGG.L$2*%22R\(smcmkj+KRK:qI+E;=Gm.d#e.uEA=]An\D29L*uYkYlD>
2TYOk.KlR[i+>mK(0L_]A,Y*fY+<f#72$FnbIKTk')!3tRWW5Sj@*lEmmn&G$8H&;.="09H(4
nZ.N(gdG6.28.GW1QjPK6I,LGjm?h2/$-#fugGJhul0S[=T36-<Tep/Pg^N9\8OgMFhY<`n1
t0SO*:Yeg&/Cb\YNmsjcoN.ssB/UbMFOKs0LSB."!T_`3jf\Prn61A<sD&5QP*^^YL<2>Za(
BH'?&nqr.Wb\I7?ZCMrB>:%r5^#K&'=]Af&3uG\Qop-\K/ORYnBOb<;&X%r`2,UHld;@&:f:E
*[Bdi4@4p3;X84k!6;OlP+[oD(>(n97%VB(rbo]AtMI[T=$YCksA'eot=$E8rP_TT-5//k/5s
&d)%'[m<jBE\fH-Z_8fq@jQi0hm;>]AS#<9Hh8Gu%]A*d#`qXsEae[7rAWOOM7'A8llb*/-,95
\ag4gQd=mU)0qTBPCV(0HWo)J^$q6UKFpJJI7dNP%18_QG0'E_.u+A@fJh/CYX8Z6eIVY5c^
d9#7hhP>Vp4/c>*<X"ol\0&=)7%mp.,6t1M24=qALL)rRll>1_W<).1H8HD,"?/7jP-5p7a%
(#jUR52VQ*arr>rjp%Jg,0[mROp0=><=O4kpPZ[/7`GNO0M;?Q>#JqE$rj6*02G)Wgi;2hg&
'pP-[RUP))1IodD,VR]AL;B?`ne<:`MPPH_\htJ==Gtc"9"qg%-B$aWPq[;n6Ud)/S55F`%9$
jW'BkVQ'-^XOquWcW`\sXB]A]A^Ck(Q$<WU`>b*/'9d4ZjWCG=Nb0_:]A@9k?lmen6g:*fSnpBT
@*On`++[AOJ;Nl/Kb%MYo<C!gk&M9rj*8<>1g[mPSV?b-835Xnsq;fm'Q;J0d#*0**;cNd\3
VXS+L^_>:On4/sCaalCeJC-`G/.0dgMR1+JA?k3.jrhBGVq;,@:&/)^V.??eZ^Vm:aQ#VY7a
t<,q(L@aErJI2C%p+BiV"EVqoIl,_9J-fC6t@(;LTL$dP_W*7V3P'%b9+chrZ\lEfOR+(bW#
i>0-5eFIL6V66$sau,Tu,a5c[84F\?\m#=;\q7LN'<drG"ZqIZo>a-Vgmc7*\"NCKEtk&b))
0V#N%,oihtq_]A5T4C'Qml+CbW2dsjL9a$'!Uj.u"Wn.Wi*3J>C$`LEc=khfNQfFY-b[p6_7]A
O/AncDk>-@^TP:BE4,T9+(>M<oZSntGcKA'tFOIc6>`&Hm&<$@@(>Jss9<jMS`W$_SgZ?k17
k>[#;IX?uB_p6,#?IM@`>^1j8)JarfJ'eW0d.:k5D<1#8%6c6d2/^t@uZ#1@Ia$FBs[An)B9
sM,^+\B4WKseU8:L8+tX=![o(2_11(j3`<CrXAHWFC6sfd,)?OdNFH*U"MfZ(ogt=gkS0.$p
#Y13A"J6QKtKZ<!e?DIHsNl:Dho8Z"9]A+!9*9jF,F@L=ra4/@)(]A5*G2IiSHh.m!O<HMb%:!
HkINe(P,9&TnReF5"pRW[.S/-K*[a/iReN.h+B>UWTR)?-MIb]A\-+"<>i["_N"4=<70*f0^k
<P\GW+h/()DXZ1$M)JQZcia&cH=uY2GWS1%+8QiW`\&Vh$!/,#4g_BHI<M"I7el(!(XI3K(g
Bd[$G'HIB5d^Pif[gY4<<0tH(5)F>GGlQ]A7s3a0N.BS[-&aMh4KEEp6S#dG_tX.(e*;'k!S.
`XFhKGn3dq<9%dM']AP#j]AIKR>$%jBUrnrm_5\LC"RoQbrAqtoAQB%[ca%LK=V]A$FaaEUV)!J
"/a!NM7)CAaM;l.($+/4U*'pYAS\+;Rf<L@LE(Zre9r-ANZ]AccA.G8obF:er5!"1M,ZU`\jq
>b?>^^pC\^kLA1@*$#E[X@sjW=fNOKGQF9eVJ(&hbKKS%P%'fWkieJuX=_]AAHU3"hdUscR5j
Y&tT>fJ@q[Om8p(SQhs*tX&[K7L(E?+Jk&YkSlXG&TNe)L(rGA_Xt]AF6Bpf>0NN'MMW,WULu
Qg*]Aj#qk,^fiT&\ES+dZ.#aO<W8J`bN3P$FNVL(f0fq^E@mT5?XeB_B#YgU>Xnfa2hBX_)ua
fFjNcQqJ;`ak</hq\lOO0%b+9^$^5=q(s11QDZZf-gq1=@=;m0I")IUNg1FH\OJBV7^7f!N7
`07;]A(dEW?:W]AW@.EGDJlK<=n`k-fkDi!TA8q!S>6\IAmqHC1@4=rIQ!OR;B.:a3aDn46Wr'
b+(g_+Za)CiE=:]Ac%p8`0'BXN9W`rC^]AdjZ)RfARBBLT"2;P-I)/UtZFW,niRH.J[8Yu[>@E
tQ.H!^C#<ig*&f9p+]AKuY+Enl<9kf,_T#RL"@_orVX1_N20:3f"@A[(;8,Z.3XSe-X?I*b<s
V"i,-)A"P4B&.!B@A`9Y%FRndb5rpp/Pc$TICj\rA`m9Ouk,`Ro_Iu^T&"YL9d@_O>UlKiE*
%b,2O9i#?Pc*">YLZo<mkViOHbIdM^__]A'5NmU=TE"`=!s7=tZnX_&@(#MQ`0?XM!4K>D(9u
]AbcU37tm\hbM_NrbqI]AT]Ac,5`!m(UM`5j7d(3b]A"60<555cX$JZ35`c5N:-r86HU&&aX^Rht
?rK2W)fV*6Sn,T6kBUoK,p;.+bG>joAN$q4@hVF3!;D"TQrZEDrcKRM[V+>GaPR+D+"pP:4%
Y40d_)S<'p&d8UM"GLO*/HPH%&a?(4Bs>gD4=21nu\4_C!n_>k"k&rYY8U#hiOc-Y5<YlIH?
dX-=^u/D%q.korN*HZ;s0mBhYgeDF+[;Z1\@9PMb[1l1Y:j-!qGM8H)2]AR!js\">&4Vt;7Bi
SH'KghmeIQ.D.%!.]A[Sa4K_+kt4PVC74+,@'nof^NB1)?e;'=0QLt6eCuq6k#+_!$[OgH0>a
kAR5e?Nl8OtYakZl[G3(HW%b`,\8jh;u/4YNh;2SW_nuUks04TLd'UNCO,->'[Ci;:51,@Q`
q+3&**)OieRY>r"NoQP21,*dqPbTR_hj;QKX#c&$Jkm$!T,52PLb/Fg.eE$>.";F.`e3D(:=
:3RKaet);m#`/DmYB<8k'gG.SZCe]ACiaG7Qu<%dd:cWX='$.>4kB?lb"+6(_3XXYXBUJ6LnS
f*2!_Pbo&_e7p9<:YZc/t@Y$6r90=<S6P:\I4%deV\9l7[5J+Qd^siKsSRhU5e]AG3(Z(5(T.
*5Ld2.Zp<rIi=KK)Pe41A("Xc(!]Ae`IBhSUlV-c(R9GP-?'RJDW9dG<+S8c:6C%Zdm8(sP77
&PcgiYRWV3FgEM-upT+`5j$Fr4.%STU1;+&)2AA+aWFeNl*IbgT3:/K)aio7dV%9,l:'r64s
c*``g'h^,)e@[^1gE^AF$XVKdPtJBrDMrSX,V0W6("]Ah1QM^S`MU_U%2b'!0Kc,)Obm1&L(X
lRu3l8:<rT!)<.k,-+CH(`Q7KhOoN/.6GQ7gg`pZq:8/C<--_.t)Ah"!Oj=ZgJNJDISaA(+"
1F$jt&B&X`j28OQ$Rn=7q56+b05\i)/)3.uAC36=sSNg=t'db1?B3+"CRJ3\"859e*CRu]A"R
&+LC;_$"K[%"S+1DXMB`EutkcdBKe)I$t`GWrrZ$+!5?r6p9mV4f72+<t@/\eXG-;q.SuW[R
gnp"LFCKQj@i*J`nM)Y&u#]AC%Q[V/63FK_r?q,5(^j/;WWR--Z,s=JI*j1l1J=n0\Tf_`keM
n%,Fn<Yp1BcEZ`Wh)V&Q;c3mYL1a.#F;.N)HqKJu78lE;7LLk1ML/\0?>.BV>cNJ_CT.[X@(
?7VSQ"\sOtl<,+TstWZtH1:Gd/t`61YI\A=4+9!e_#N^4.QSLZgd_'d9B$)=apu;EN!2V>dH
32fNCMJV5_ld`6;t@?G@9PVLBLN1_ktHr:+FCk(h)9C4!DXUp)%K^d(q6j^r:;&_e,oq.rhD
cY<TLqu+mX/pMd,JP=B",]AH&G1*7%r;aDhK=EZl$oT8Y$0>%,jOWu]AN(fTmf;G_=b*T<C1.<
SH+RR@U]A*4r@3CYLC6ZV)>N(9l!+,GIVMXJ!R1PL%SUBjQWi'n*b=V^<Ke\G#]ALiS8i6"<Zm
h_I>V\=q5'-YX64#;m!L_4(c1=]A@d9%;';T7J.a0+q$k!^X"[rHKA_%l'9]AaUt[i(TdK:&kZ
j(`AE-lKqhAk#e_]A;%o@9)!]Aha=OjZ*<HJcr"@7olT0:S$8ANO>ph]A'i?.iLA"=;dV2%++B,
cl3-&4KK@'1)o5bsTFpa60icgj6``\cnF_%R4ruGUGs`4bP0b3j#C7TTU@Ae.W>+kp#O]Ah:K
QISodfH[/*Q0hu:AFN21##Qm;e"iS`![4\;jL/?lT:,2.KP\=rnLSsne]AB2efkF*Z)cp^WqL
m]A'I+<N`'1c$]AuL988Im&P5.\5a3uUmUcDUH.3DQtJ2Fj?ABuUqOm(TVI<LV06@!XH&+##&j
#QXkP]A(..ppYQ;ME@%9[hZ`SqbkSq"#'%PNZmo=Ie3G0ub%,m<;8F%SKU+QTNr3>hC(++j[1
AYRU$$%)iW6,rSh?T7p$uASo0"#_@SX4NDjNMqq?<m%G=@-:f]A'\<HgubEX-HfH$oK&CA[)r
p,u@=]A`m5>_(!ohF,d(uB:pe4.A`8^q1A`b$'O@PtVTug`lR`W)P*hR7.KP9@C]Ad\^l]Ap0nd
-p%R+-e&+5rru\T['YCnKk+-4a+*q)EOtK=Ej5IcbC'2U)J^?EhjBiG@HS8:#`PJHQ%S3No+
)a/;^91+5u+SEFSK&L[Ti#)_?TG0,2V2(q=i<M/L"DfufFfa%^-:"TOce$hlqO]AtM%E;fE;_
Z2qO&PuZ\C[pN>?AdR"#$XR;*2V@.m]A#/X%DJ?$E"DWP)]AVrm)3j]A0mXYZc9#!ULQB5b&R[+
656]AR+uS-I<m*5flK"aUgZjN-S=fhC568A.a>2L1\fuN=Y%F,gTNXf'9i4H2m3/^Y?*M9GZ5
d)!WX>"@^.RcoH#lZ8DJP$ZuD#$O2Im>A"Pp=bRh\m$i_CZ';,&72!-tc/&L`1c%GaE1b#Ma
C&]AL>&Ck6&c.9u?;j)=BbkenO.!dLZ06\lpq_gCQ<kcJ]A\mb\e<fU?;QR]AQGu1q,m;?p_0pf
&Q+'/\Y,jWJ>p00qH0lTj^M5:PCqW>2cG)9lUcA'FUruH)o=V*SHPdtpN90oUc/:fpqrMkfU
:WWi\*gaU/11d=2l:LYQ8t7P,K7>nmJ)gL:N2;Y7D&7j(8Y9gt\[5WYdp1AWg02=_@m=dsf+
JD'(tudOWMaI&2*mD+U\\6fhU?AS'uehAEAm1j=L@>slI2Y,TNO7"W\g$h'X3KGaA,?+:__`
>-]Ar=QD;PBeAgaY6NImu\*p_"+6o>+AE2fn![gFqY\f#.0UG>_X-&J"'\_b6<d0R-e"B53:G
;R%t%AF,?-:2BVFcjk`C;`JEZG8`/DhaiC/*(imVso#(afrfk+1[/T&Jb&m(;(j\lRimgZg;
QF#2_cP0-=0gVKNSfp@.$<;']A16XB5)_h55;-dXm&Pg[7ak)kZ4%-E#s`A5T^!/XiM?pA(,l
_6A=h/pan#\"J2+SVD6bqF+eWiS;;#0'Dk[YqHI6BO$'m5d\iUe9bNWcILh>/h\;RHG1sE'5
1*1&TdkUmd&!R:\$Ha[\=3OekVA\'cUP;&P*L!/ChZBEZX*@Lal`:pYW.<cfam^'/MOR:o.(
?%cDurb8FZ"+nNBncertH3oN"0[+,3s<l*[7SuY`iXaJU3b&gG_j:$/IBesS?SL0%kZ2n=h3
!h7(!,B-hs$At!QIX3`<,&bMh^am?DIXb&80"SEs%<_27'*!E06Det;1ScL*"U$Qco9R3'u>
FNeIQh"m9Z"a]A9^!<4bui7oer`C4n[k#LN]A:r`*NX*YYR1,_eO66kPPM++IpsdZ"A5P\;m,*
#o%jC[K1KmCQ'h<FI!pmS%G\c069SQ(LR</9t?n4PSf.YW5,57e^aVt9%#NFG;!Us'.B&Ua[
X(]AkL+XamtB%6jU3"!Tf&KL%OV:u`i>+]AeT+ae/o:\=&YSQB8D#<EE(58/J54f6$:5==I9F1
cU.Ic;A\f$.cjm#5SM':f]AoE!oBYZmNGanYt3@K2bD>^#BSL%\jdiJp2P7C5/`u`!Q%`$IiC
bH2o+g4%l")obE,(S@@\,TnH9;DhA5Q6D9:`<<m2SB4-L^cZnha#]AF:]Al[ME2dp7BXoD(Md=
mpPE>stEI.nhQiGQHYbE.g3i+9)8qR'=A9#S+[o:u3_)G24EP3"R519O7h^+3_dMMPNWiaP&
]A61`A;)l;5(<jjL32"7bmo')8,6)gOcfXnEq%io>#IFd8NlJkecmsMB?")&_%<'cNFkQ`RI`
s]Akr$%2m"/Sm!?PM2,"`1\L&jXHSd>XX]AG%h,3/+]A`j^ZVBJ=&:,Tc);2c2u.lMj=X<&VjDm
jqg6uVh^jB@<CV<,fe-:)]A1D6icM6`iUP/:4\*T$:0#)UPa4?m_'QWq;Ip*Q[Y87(L\*X)dW
W<%7eUR"bn:0ud++F6N_6^HrM%BXFpsZ4ZIu*ombQ89>Pl,oqY&)R8'XXh]A5E9o_lfmVPr42
oH\HCXk,B)AS"VC&q9h4LIh-BO!.t1QB#G@uF&;:=]A"P)H"o%tgR`=*\V:Z@h%;MpAqb.Pi'
nL+Y,C61Drc'*nGg%W!I`^;N%VB<(3k":^8FS1[;_E!<dc/3=0=s$Df419F<Lk?]AqB'N.`0`
,$D**i;?,SVqZnGBfa_$3nYq#G3hbS,?J?lp!"XbVSO"t9ibL7]ARTOUL<q0s1U&6NdZYT\-A
-'oY^AJLf0bhO\)P?u`bs`p]A8IC<pBh1q+p>_N-%jF7aZ_KO*6(_fii-jrJ1/p%:*DOd!O:A
_o/X*<ufSi[/*4k*#XGJaDhn6gF9:*"Ib<:3i9?Q:u;^[u&#W3W:<-rs/qp/_tmm&pO'KF%H
4SS9Z[T^Bq`YqXAtHK)ZumrWeke?^p0'"%'d&s"a3G=hk[F?^g(@7Ha[VY]A`ZFbDqB:SB/);
]Ab3.(d:'>u<m[W#9XZ2G\A5TR?rpY=Ume<`rTZDg5K$\,+I=uM/c!GJ_J%tZ:6a^enPH>%`m
!4cST&4#dL'T>X_f:7Q.,AsI0Wp^G;]As0)\@dq8*Y?4ohX[S40]ArV`&$LecWGo$n"'$mi*%B
;4IqDKc+e[6AhE,):$O<PZ3oVU)iLub%Y%'%+F\F\2p7grm/0h6jgJe;AG8>E\fAoHq>hd`P
]AZ-l^,bZ)^+#b@*Wtpc$0X2Crd=hK-0Yu`aZ`$kE]An/SDtgm5N.naLjWdP#Xp/D22%5CZ5H.
$H\b-1(hTUVM7,b!LUR4jk<ASV(7V6#_h_/Q1C)e-(.a:EqN32%?nfc)\!4=C>T`LL^GN0iT
\WGE7ZO3Lr"gHumO&P)@rSGT'J+\U:A@oJ5:5OYgA^Yl2,o?)OI(0Lt[mI-(&/\UE98BPo>o
+St9%?qYF>PjV?c?k]AqL.I9^0BkoR*He!e+,dbU>eg?0NXd?bP\+uqpne[A3;g?e^ZUKSmX5
h?YYn"UZYGH0U+dVDKmB@0&Q#FJ2B:8G-C#@;Tbn5Y*0_\[5<\jI_Z%_;,IE1rWk1Sgo$Jl?
Y8HP3+k$L)%sDVq2F8="eBP^"/VdP4m*81Y`82)U8+Gt=/E%>RZfLcCA]A?[f\+s:cgec3Is'
K,+h,M0cf+[B,^YqB"fP)SHq,)$VXrXbb$\D(#5M(NXr>n$P#Q)s*tEdY"N2(S429FBh!k1<
]A9o"7HPdMf)M3+rV[mbgPNOtrZ5c!Yf;*\0W5ejh;nn*OTsEWmf:!hB;fY1MB5WPf:&!!?"i
m$7=Fe,PMC;oaqN8"r_Qqj(r-/$G<T(s-8-VDlLj=?ni\`Vkj@si&IV-$JCXHTloj:C[RuZH
B_tZLA0Y)N+G/5%-cMhojS:^Y>/lqeXE:>dnZ`pZYEe%cW15=26TGSjIrJd$Es6!N!*2TL/9
c&9>e;Y;b]AD%#5EkKoW@i'#&Oh.$lfm$HmW1*Ql3Ei2J_e?D3hB:7emB8ViH^A^D]AI4.Clnk
Tpm'u,;MY*<f%Br!J)gt.j>?dSJ&qd)^["OX>]AnD;B":p4kfopVD`Y:8Q;Qf_SATXrO)o?]A=
dkTDUjibije^`3VWitqWdFNs9lkSPCVrBQ/bsSUSp>)\BhRfEp@t/]A4@ml&h,s78XLq2Qu74
f'*"`-;$#T<VnZ'Gq"ku>JrZ`pGUrT)JXU<O@Tg@ran4hl>LR5%k,U?,;fXtG=H;-?ElI6=A
kdoc-XWS\bNDf';]AfGb"G]AYmEaNpG-g3LpVa%L/NZgrnK%e-lu>jcQ<RmgJH99ZkgK4nE7YL
_BV7J1-=jdeRQQL%ZdnBn?nR'S`_qB9gcB)ugHhVo`TnH7)M1jP/uF2b"$I:Ku[eUs9nNeW\
JaPngU2!#LY$$$s"6ik!3"-m*P"CR<m-..eoU*1)^J+#*QXjH>!M9h&6V'mFYfL.cX2Essh*
ql5fa&A+g!`LMWm'2RPf]A.:^;IThi962K8i2jIP:"'3r2;tp5J<;hE[oW;,C$)'djAnn_0$O
DrPE"2'4'[pZY]ArPS*VO]AC,qDCm`r'>a)UjYq;'u>49bfWQRdCVM9IQD3@%qr&."4O>o]AZK7
18_8WbK_UmZk6=,?dqW!10b_&/V@2@%JPA)C,$NF6FtnZ5iH4/g[pKTlS".&Pi.RoMm:]A@JK
%M9g?IPah9scK/^PK@<?<\D5bqM%_)K.F3#u]A3;f)P[3GeLMC7a9r4p!0!1ralark%MD7@)A
>T+Obh)4m>3T=GZ+)9+r=fCnd,E67"QDh]AC,"oN:>mF'G76JO2;E8kB>Z.&6ga)#j2l>"\V=
+YM'`p)a[SEsp)W(0TNXI<3F$0ks`$WMH>3.1=fX$o(nP"K's(JVJoV8TB$QT;t2JR?Q8G]Af
u6tXIn<i:FsSc,fL&c#3oM8:)R`ZQn30a[)/FF$5P$jSIBmbLS^TBDTJ+\<7A(.ri>N,QYh5
$!Q_QA]AVD@$p-[Y-n^J2'fTU7JlEh2S8iFa@O:j6E2o`EV0Rb,9)=OAA&\81X\d0Hjo>fW-e
RoM>!d+,22E3#NJ,0!,P68Oi"p!4>U$eohl^(Es$MAf/nML69>VL5_hTbpROk7's^qBos)F9
S,T(cHMUC*n,TIDfVN6fX!9Gdm1bJBRG@BPcW*85jBq/!7p<0ml@P?BH;\F%TRQt!1cA&D/G
Ye\qq)5LE:YKY&SN,@jIs7:4,1u$iB%C]A7CUhN@09UZd@q"3MIcod\77"027SF5u,@t9S@X8
1@`@rWAK@-<Hkj.as@Tqn*$-=daJ2#ILdbVndX3rN=.`aX9-;AkoQ3?,E[?-6J1OSk5mrPF3
M#gKJ3mbH(O380tWetn=jg]AN=lShhehJZMe"BgA^MS0WRSBEjYo94)$4kVKfP8u5@(YLNp4_
#dhnP+\;##:-QG=D%s2f2j4LWd5Oei&isNi3g,2HJq/d$uu`/Q]AD0RJI<@T9#93UC]A894gi6
=<X@Jo/7fF#f@+>9ej7)eV[uu'#G/Te>]AC"QQX9)-"&J#?64$$CM`JN=Y=2/.DM"L<1CO.@0
,8;B_"blVA'oUr.O8.s8(@Hf[H3MD8/`n^N'8'+=S8S8Yh13L1oIO1d-I#VHL]A56?@Z^.54d
g/8IF95Y\h+;f!9VSWXfose?-t>a2VoheiKJo1/5O:+)"8!!=10Qh`kk86oqg)[jl)nRaK"G
s2M6NR(p$HVbld!jdAOBZ2eqtr3"UK!e$HL'e:##-_o'A>Y5-%AZ8id)14%cP5)/`=>?BEfO
0qD=O!O_FPC\_DKopf'iIX,2<kbp(\MqXqZb#DXA3g't$tL>!jYuQ!qsXAf-r\gEWXZ0"Y--
a[=D4]ATe5cEm-'I!SRl#+$S7d?@)l1uOcam,oRj\!S8DL6iSH8UkgT?GFg\Cd,SsRiIk]A'6T
Bo#p.l?Y"VDVnAEKuL%"HF>d10[^ftg#&W!B2s3F;poc*k#9WMrr,+hkZMLd36N,WmD7X9RD
pW+A8[RE*W<1]AHsc/WNVQc"LN&#o0_O<.b:Dou[Zmh.S:YnO]Aq6V\$*h@ca4YFXf'R6h%J3o
EQL3uPb__N_Y_VgWbDk_*Jc=V:]Ap)\=`dd[Ako;JJ-SKiDHJ2a&hZ!f~
]]></IM>
</InnerWidget>
<BoundsAttr x="425" y="38" width="540" height="472"/>
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
<![CDATA[="   销售代表每月销售"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="80"/>
<border style="1" color="-2434342"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="540" height="38"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="title"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[="   销售代表每月销售"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="80"/>
<border style="1" color="-2434342"/>
</title>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="1" left="1" bottom="1" right="1"/>
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[266700,2590800,2133600,266700,2400300,2209800,190500,2209800,2171700,457200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="1" r="0" cs="2" s="1">
<O>
<![CDATA[本年迄今销售]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" cs="2" s="1">
<O>
<![CDATA[本年迄今预算]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="6" r="0" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="0" cs="2" s="1">
<O>
<![CDATA[差额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" cs="2" s="3">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="factyear"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" cs="2" s="3">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="preyear"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="6" r="1" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="1" cs="2" s="5">
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=B2 - E2]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="1" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" cs="10" rs="22">
<O t="CC">
<LayoutAttr selectedIndex="0"/>
<Chart name="Default">
<Chart class="com.fr.chart.chartattr.Chart" options="{}" customChartID="" wrapperName="FineChart">
<GI>
<AttrBackground>
<Background name="GradientBackground" color1="-52" color2="-1" direction="1" useCell="true" begin="0.0" finish="0.0" cyclic="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="true"/>
<newColor borderColor="-71254"/>
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
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[销售代表每月销售]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="88"/>
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
<Attr lineStyle="0" isRoundBorder="true"/>
<newColor borderColor="-203360"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${SERIES}${BR}${CATEGORY}${BR}${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.00]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.00%]]></Format>
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
<newColor borderColor="-8355712"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="3" visible="true"/>
<FRFont name="微软雅黑" style="0" size="72"/>
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
<FRFont name="SimSun" style="0" size="72"/>
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
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
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
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor mainGridColor="-1184275" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
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
</ValueAxis>
</yAxis>
<secondAxis>
<ValueAxis class="com.fr.chart.chartattr.ValueAxis">
<ValueAxisAttr201108 alignZeroValue="false"/>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor mainGridColor="-1184275" lineColor="-5197648"/>
<AxisPosition value="4"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
<ConditionAttr name="条件属性1">
<AttrList>
<Attr class="com.fr.chart.base.AttrBarSeries">
<AttrBarSeries>
<Attr seriesOverlapPercent="-0.25" categoryIntervalPercent="1.0" axisPosition="LEFT"/>
</AttrBarSeries>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[4]]></CNUMBER>
<CNAME>
<![CDATA[系列名称]]></CNAME>
<Compare op="0">
<O>
<![CDATA[实际]]></O>
</Compare>
</Condition>
</ConditionAttr>
<ctattr renderer="1"/>
</CustomAttr>
</List>
<List index="1">
<CustomAttr>
<ConditionAttr name="条件属性2">
<AttrList>
<Attr class="com.fr.chart.base.AttrLineSeries">
<AttrLineSeries>
<Attr isCurve="false" isNullValueBreak="true" lineStyle="5" markerType="NullMarker" axisPosition="LEFT"/>
</AttrLineSeries>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[4]]></CNUMBER>
<CNAME>
<![CDATA[系列名称]]></CNAME>
<Compare op="0">
<O>
<![CDATA[预算]]></O>
</Compare>
</Condition>
</ConditionAttr>
<ctattr renderer="2"/>
</CustomAttr>
</List>
<List index="2">
<CustomAttr>
<ConditionAttr name="条件属性3">
<AttrList>
<Attr class="com.fr.chart.base.AttrLineSeries">
<AttrLineSeries>
<Attr isCurve="false" isNullValueBreak="true" lineStyle="5" markerType="NullMarker" axisPosition="LEFT"/>
</AttrLineSeries>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[4]]></CNUMBER>
<CNAME>
<![CDATA[系列名称]]></CNAME>
<Compare op="0">
<O>
<![CDATA[计划]]></O>
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
<OneValueCDDefinition seriesName="type" valueName="num" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="month1"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
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
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-13312"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-13312"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-13312"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[$#,##0;($#,##0)]]></Format>
<FRFont name="微软雅黑" style="1" size="96"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="96"/>
<Background name="ColorBackground" color="-13312"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0.00]]></Format>
<FRFont name="微软雅黑" style="1" size="96"/>
<Background name="ColorBackground" color="-3342388"/>
<Border/>
</Style>
</StyleList>
<showToolbar showtoolbar="false"/>
<IM>
<![CDATA[[<i3>P\^;KS^N64Q'B-);43LQg!=pWKi6$K@;HB-K?CG/k#=[#,9[Ak;`GY)`\uD9YpT?6`n
6V/1%sE?1]AQX+6(IP\^[(Xo?e<%cb<4*6ci(PlbNJKmhTt$LRl>HbpPJ7k[VZu`[B&#j[r'l
>Ene%i6[O7^YI&IQCtkg9FA;Yq4.bj">lq36gu@E;,_S"DMrEJQ?G*3Hnu]A&HEpdV'V7m<-/
^f2_Kq^0[@j>aHXfU0,fCT.T)<OO-cHANlXqlXK!hMqNPgr@9)E^7V_p7;OIM12ERJcu-9V-
RnAF8I!`VRO+ai2eT$GL0(cZ?jL4`dQ8=P6\QSkLS3Z&R%kXd9D@$H9]A#2V3)=Ja^XFN=r0k
n.4M0Q2?bTi/16""8=DuIJLYoCU^q,2,dJ&H.pK,PDi]A!IL,4P\+^.=9T4?>Z#Q8"#Rm:@Ka
%krq-6I31>?6D]A84ZJlcg5EKi"I50-gc1\D$@kia8(Y=E_oD02m=F-@d)3/%ag\akdc-!gGE
+nsEmukb#i/ebD%G%XpIer=lAY5YhSO88Z5tBE\`WK\j+%nWS1d.n,.Cn$cq'Bsa@Sgnj2RP
aTMa[&)')9YZ<c%1MTT8b,[B;'4g8X"%h)e^XKTkJbO7_8h7Q.??+)h1]A<4=Rr5t6Gt4bI(c
DJ6n8NiB3.*X<HY@GfdZQ&PVonQ@DB*!;.O_6*(mJ_7#CU?f<-$<=-Al;M*TQi6`4WB&ScbK
CZ(g$XKXL3Br,!ar.UQY?1tbNTOh=&.46m0UYs=PHbKNE=9/oK-6#R3/c>7KYN+Pk..tj"5E
tPfKhdsFm'FZ#Y[S0P[TE]A#ahu?l:,q:)?Xi3M,]ASsMYJV-E"H:eVPJf9-eBGU&MG/H#4%i.
,XWZ.'eiP_UCMNQhcg,YhJ>GQaPRtU4TK'H*bLMSk+gVs1(X4o/'%MEiW]A>&ONUF[jUIq9>U
]A69b$SQM&$/7kK5)jY^MQ=),C4"ub/97Oj!&&53:Tko$NlDeg0I`_'/u>]A43g=!<-TQVe7Qp
crG2l.P1*L3a"GA.n#Gr>q]A,0Im2%Vq7gY^a?]A_q&JGb'PUO)MnXbm3T3Kdcd#6"f8$>D1H-
<9Q[Hn`dR96+d3mQkJUK>O9cq(%ZoJBj;A9%UM=A$(&9re/Fgh+*e<]Aclh=Y64aL?%>paNJW
#0I,CXrY[.u7I`c'aCB"8@RYG?eH)6.)?;U]AeFL6fudQ?=&SpRKDVq8LnhHUgNGr#@F)YV=0
eLSi[O$gUJ??9%HCl)-s=Z-#aU%JHh`=-8GJ&VI8HP[^o*X:U=<7`?:?qU`!/:'oE@;G7cW=
PY@E]A06,9m_Q1oIBb9*?*5a9A<lCK/#.L_IX2Q/0!Dq05;6`7<m;[YpJq[W!s;gRjL$8R3e$
\F.Ec/.%$-8-N'Wq-]AtIlT23]A@]AD:D5t44RU</QZbq8N::EW,GF%MLcG"oo8N,lOKj0<bk.g
(J(m9f$C';UeQ@6=BK09$fjGr3F*gU7n;qod,dj9-m?@`LXYUb\>"WR%kS[f4Ak%sG9!Np,T
mY#)8+<h?&SB</#.GIOrnt';j(7h"^bNR:",`iEKsE#A=\ouSAdZ=aK=16`(s&klnKVLG@U#
ZIss-J;:`^+8>YlPP"A=3]A8u*=5dtdBJKk5cI,eS]AN1G:MLbPj&)PfSfm^)B'Y*^+N&r4tan
cfRF*fS]A:"Y@5FHmgd45YON_o>'5+C3VrllGJ0GrK?ho\,<@0"]Arib-:GdQ30\nBL6`jXp*]A
]A%?_"BU'FGN>>harmAi[_*=&f$62]A[<.o,r'5qOsUYdcc7_nR>Ie#>h\CerWnQYD:U:IIla_
XbVFkjs?&aaJ=(.l6s8YNkK"99f5mYobnMGqgtAaEu/iGeWcC^b$@JANhK"H@3m+]A&fM_lRY
1CkmtP3OgA:MWf]A(4&A/S0kNqP(bb([AK*5X/c6F@]A/;Gb<m*_-G<dOJ#FV:uo#_-AFqG3U_
2?qkorN4I]AoUbA>aGTB)n/;Z"A<1rbV=I$H71Bk6N3>6pp+(,,[J.F8"@'b<HUZg(kbX%n$\
O0R8LlWl/mn!./Rh]A\_LZ"tpDlhhOSg"L/-5u*n]A`&fBECG9Qi,23::TR]Ak`p.%@Osj0fqG*
OGU2:&SeV1@$@5nE^Ve)4YH'9%;3E?,r@nSQkBm6DdY^@k;`L"4rW&C*#V,Lg#EN0ep+<r&W
_70Qkbdt!fJ9'q9h[]A%u4A?2iiHu7,e.1,=kgYI?E2?_gCbVu;mW0hab8S5uo[8-)K'ZjY?]A
O:M=2Z28S,!UA9&(^he+Y]Ab[Hlq3n,\A2dfdT^Ru,iJ<A2mrjTAT<D3jIL4(4#<pNX<TNT<J
\Z8o\P?UH-dkrX4M\3*Djd+rVhJ0jGG,Z01t]AC-.JHl*im1KaCZJ'u]Ai,W%0:q-LJfVYgj-8
1Pja)!WBt<d*T,qq2i]AiE+l&!r":JYY.IbQd`#/F3DM?!#9VXGEreO?[G>_UAA!Bqi?7#-Kq
J/(s@a!4Y^ZW,R)Xp6R6)fiA:%j/S!P+cCoo'O+0::3&=KC]AVmRf<p'<iE+*C8q[t*53dnS\
"Em5ZmR&9WU1+loDcR:S38X@>GWW+]A)`+nZq;"IRW\IT$?Fpr[g^js!d*bQ#.;Ck=ggB;[B;
gmt@obs"Z^M/lq0m\BO-ZU*$tCMf,n6U/pod:[LbnU7KK^`Seq_*[,leV=c$Wd=k9TYWkd-+
<H5Bo%:I>G6V9:^nm^*GRdN0QY!%-52lR;V]A0"''<-]A+8)(>%Srns:ZI4i-N"BWK.K-@N/N(
1PG`Rp$u5'FDdr]A#;q@UhHaZ;&)ZW$?S9*kAG5OeM:ms,Be]ABJr/47Oc)03ki*-+]AJ'NdpG8
1^O`Jb<H:Dc:RFIqt:5%aP.?16omtDKD=_m9\39Wi]A'=l"j.?LqoHq@R\<TnRE;/Sg>rU#?D
,^(@e$58I6;9;LpZ\ciO2Mp/8?;-QN4?CNB:6mJ4LY.X?>rLgTIr+Zp5$g"I$d!(ahsSF<g5
Gh'3F"<658514gTf=6Di/!(MGm5jCTPp1%ej#*F3k]Ad!U:'*`[)qJ?sgqppUd.rg-9fomTgG
GW++6\YUT>?'ANn\n3-*upe8+)-;Tf/71qB$qa`bcH1'TZ$'a&);UgHTWSRrn+mBG@RD1?!\
WE5i4u"bNGAVl'ce3pRN<O&=XSfoJ1Z1Bk07fX-A:XMP&dOEfr[7aVn2B_7q]A&CB^Y8a/`Lq
APq?4TN/+2-B^YU/#]AV2_oV_G)a%P[]AGdA/8!$95Yc0T"b/_<qI4_5>dSduODD1QC@(;f-AA
XV:dMTEARo*Nq,?*4C>S`To<YA\5"bH_q.RZ92NA6Z[NZ6K7\n't`<.'m7djN(h$E[WE3-'^
%qm^r'?P,U@D>,7QO)JZ9nWULj)&JbB=X`k4748KV=/M2G:4m<K6Z`t3e]Ag[Cbfihed)8eBs
F^__07(5'<d:_FeULEeRKp(@ngD1(>o8P&aZ]AD75=Kf-gJ/F@uG@u^XIc.]A1g#Z;9T>kPi%.
6:(%:ll?3liBAhXkuB+__kSG3CMBJ[T_.bg^+#E$49g*J4"jTK'q`r7D[[tM^i752e#Jf;Ra
t0Vbh2"RVFYqo.Q=;ShZDrH;HN>B(`B6o6`#V9tG`hL!-&b(g*QNZAa?s'T,lhl>E`#0QC^n
,^H@54Q0/FIoU#T[T,n9LND8;4+[0Jm48T[hR$(X3LQ[V^->K9fr'KN`NT="j@]A0@k%mLHaW
nE+?S7tI##h@h^XU,Pba^5*W`PfuH6R+]Ai.oEA,Rd@OU\=1IQigN^,id3&O^a[!6LB1gDii9
A[)-('cL-VT=X9J7QKqd0MhOsG=]A<4LcQ;lDrQ&H,AMm:ba+mIQZJ-;9Wn<H7/o:93jj&16?
$5^m=t/:oC6Jc4Q?VqC%tBJoR&p[/_Q?rI72(m&N$ECNGiHigFlNDZ!F!'g*Bbq/nN<12c/g
F#.HUNFV3[<j?b?29g'VmsI5H@7)=Y32\0gg`UV=8[(GNUIFhAc:kD.kOJ56Ch-(lWW^#f,W
\pL88SGc0+Og:'YF:_ccdP\\[*2[u,`bE-SAFWjcBpsB#FtF1Vr4)?+HE>OS-7k30"CWb5a8
C,)V+?n4IkYla-BC0%P"<$^Hg64N#V6p2/[Ei`@-d18[P+!G=]A^VJ`e?7QH\He>!6c.PXK>D
*U^0f,%hK;Il=W9S,+,XXP:i\uREXb\&_=Sg@B,B7)qb:pB.a)%?V:s,oS95`/INAX#MuPb9
H'c]A;fgF`0Vm)q?Jb+/&:'R7?Z]AXBT6S0tH1JqZ<jO7<2"UXoMeDnISg!E"L0)P6h<,^0$9k
BVe1_5*S6+kfYZ,;1CP.?nJZlccn;Des73pQg7b6"k!I]ASR-H-O+kq)>q\LQFN0pQUsrpm`P
.+([dIn>AN'D:Hgmk3#^SmkGJ%qAB3oUe]A(]A'qAbOeu\)$_\Rqk_]A5O01P;ZY";>^o.9?UjY
B:%K]AQV^%Y$G7gWY1sU)VXVmrG&VAUk<a#uOtLQVSN?/&5Qn2#?q[9E2RKR;7!8jM`FAVq\,
;ZYZt3cfL%]AhKCApFF//l4iEW$;rSTkqXBWqUqWN^r*E#WH%eB8+!U3S+>`:nKR'Gc4+"A*<
/VJDD;aYh@Y2&):VFq;]A#_B)+GH![HN7%tN4LntSS,W+?3aM02>tS]AS@?0R[f-1_*)k>@*41
(-qB;1!QWq&uo,hkNHehK1G:i4R0uLL)q^NAQZnP]Am[Jh#,laFm\n't:eb'0H>@uV0R%j@G3
KDsn]A4^!dsb$mP56cJRA[!aD31ttnsoE+VjG2Yk8[I&78R9A1=nSG>nl()?lYGLfS7+T8Dee
TguW3beVRo/FCmg:p;+TC=I["'"bb"SQ:BiN8?Hl$X=+nf`<P^i@)g_fjS+4*R\:e/bq+S6]A
1&J.Wd/\Pi+T!,QNDoG,RBJi6%f/[nI896I"/&rG^8b/6D^7H8ncIREt'r`8;3)hC>:t,0Q.
]A_*bf;-Pe5h&J#q^PBN<n+e&jddBB\s'X"?2ok@^hX?0BCqQ-+j/t.YIs]A\F%st0@I%?(DRZ
8YAZk93rc!b)mi9>4;q5[G)An\S]AH8>9#0;CCQ!mNi2os4ZO>975Mi3bAoQUnV0MBrm]AB:HZ
aiV#dQ`ZG6daLC1OdE[A'moq7U^kik5\5I28fYtaABY9a/a7@o#jp*;>>0@HHqY^4(HNrQ1n
<7.X/[WS86JeMnOnOl7CKA&^P1/>e!s9)V]AqrMW]ATb5EhM=\-Y9Sgk=ed4R.jQOgqgEU9?Ne
)6.Vmi7ot!bW[1K*ei-.t.eD@CP&+dNK0$3pGa0R]AU#k<Z,*AeldfoUfL+EpWp&2kkH8Q$L7
$8Yr&CD@P)0Aj&jk#834.K`_hr_h6Q;Rs'-qaKU,gt[$q`uOk,,]AMQ1[0MoA^IKm?-f"^9%9
4:3(Eo7m;6o2mfd.0B75BOU3#'6FCo'</_a_`.qp]AYGK%mTGu.`G_r&.'>kY?!6t]A`]A_,H)W
B9qf(f<l$]AF*?AA=P`CDodjDYo?-7PG_aD3hSKpjE*BrP(T."h)S^Yn)=&WK>2M`r+a/!e#C
/aUoU,q6-`mqI[3J\F460a8F?<_)pYu"fbt*eD:;n]A='n6P>k0;M^\0DCHpSqd'A_6Q?)1]AZ
#-*$T<q7;W:B$I3^A[*<XjhKH]AdSNDQ#!=4MIQRi(ed7tgP-_eOH[0eA5AKj%QWDsm'I^K`_
MQKO+(]A;N'LmFL?lP>4@G*R1p,aY!#[2/D^;mPFIfj4RdgO+a"&/_pJKpSi\`6-i_eRm9gE-
JFpS0DLOC;Wk&:fZFLa6h_dT`80gdaSkh4S;/><-;7rW_,<Ns+JL[&!::_$H0B:k,iO6L4)X
(l/I72&T#4+$-oDKZnm1;?_+U![q'Q72MPRQ*0_eSm(>!cK(XK'X^91//4tF)?#[V<$ql!;a
#J3Za5]AgVgFMQ^O?gihKaqK8:5G4oW\8Y"6O^d:i3:qqk^.SOli7d[euQ.hW[M%NE%U?QJgI
0q?$DIXk\u0Z/5c'/C@+^L(DAeAfncFeU0g_UbAVhPs>dj]A$]AP^5[\%_YH/Ua^6j5rhLI^*N
B-E*QYPl<4-JF]AenQ@(6Rfu7=jcKt!urU8ZQ0e`B2#oqXNE23.<sEWl!2V2"*H64pcNPpY42
GS(F-HaM"%ug6@\*9]AA?Cg_*WL.Zlpr?F[c5pdW=@G!C<)NS89@o&H]A:mP6]AQ6%OuthbR7\Y
>[C_W*H_@<Mh.?g``OsoT"l4saN1m_e_>mPc\&AUNc=(/qD8>H%>T8W6tYktDZg^"^n5m154
p;gpoiim"G2Mofb/:B8u:79SE`o=TXCDWO;P7alp%QZ\<=b.kA*A++;e$EhN\$2`eGr"XlIJ
X&=8?"rB1UHBdQ7-]AiWrc:4e'0EuDIeLk326M?s2/NONdSmE%gDr.58]A?JW#ooQr!8-0Vc)p
%t6,H$'$ps8/RID6WNf2;]A:\6N2W3^+\$;rqhkACpBPJMU2:t==`5;6NaEO8=cNp<2cJJ2=W
9mYg++HBhf1]A*m4m[!1p-qX)ZGEBNh@k>Z6W7p-70J/R+<(esHDK+)I%?geW<;r`>VRKZ$MX
:7!+6HQ$Ys13\/n`f0cOTB-nD]AWn7E+`S?@I0^m*o%gk,b_pthq#._2Wq5MLJ=n:DK2E3bU;
B?^s"qoM#a*j/eO;^3q2]A/n.iFTIG^hu#F2mKM2/*mb>ckj!*eH!#=QJ8Q*o?D)A)&D4!<1m
F`FE&dG0h0PHXYk=^]AY*\M&*c7L9,X9FMt6XiC;Wo;FMJQaZ%4X`J(Vil&EK6=sl@(Q+l7^(
;Hnm#j[ad'0e_=C*.VUChP6"S%Q@IOYJn3[(cRR'AV^2C^UQe/p@\6Hr.N]APQ``q\!&A6eC'
F,$D%GWT_L517'Opm,%LaXncN\[9qLf>!BCZk-#aUo]Aohde*c;@0SuAYrSY.-F4\dFJ+K1>j
4bYLO%/&E9`R'jB3tU6YA_g64]A=Yl5n+^3kcFh-[pkmZp'EYDU&!ikF0nAiHjlc382r!G^bJ
T1>/]AJY"\%`[sr"d#dR*Y-Tn.YebAXhQ9+3@*TV7B!7IrC1mCA:M$\A=XpktSkp3o%OL:+d#
+2H,0'S6:=@?jEkJCt>RoI!jFV[-q#LlI`u/ideb``78h^0u(p?D&q,N_4*6Skk]A'X8Gh!+5
]AM`&,/MaR,r=/i!nP"t7X1_1;V9O2G`do4GNpi0opm,oPr=,o`GXBV&!4urVRG$QF(Hjh@C'
[HctFk*=OU`q`2P^9-P_Wue(XD!`rY_'E=P"VBmBUDd]AQ:K#=$7s;fRN*6a^(J)poQ=j.OGn
o7WF4e_-q\BN]A/tI(BQ_1[b<@)]A0dj<&Um3\gr*@Reg"k;<gA_>8a<S>1/#c3*p+2p05od7b
Ht[002N5U)"BK9<%B\!;B8OIuG%3RM6BbXeJ/aE@=M,g8f-`-5MG![A33!'01,<n`k,`B>_A
0)p-O83LrPJ_TI1JAUIP0q4:;TfKYZ$W5nCGUf%U@b-BNTZ[]A_Q,=h;fT+s-NC6m?M,"n8Rc
6W2MT1$q"iijjM#Dc(^AkgbKq+si<(8r73<dZ<Y4?X:^N\B=#rgqPR[QOXZ60\af+/=T[^7.
ltk2m%;$:na"FmrU.?r$N:&fZLGlAm?QLg.IsoDVgtP-S*ifA/p0k@1-9Pd#m%iIRXo>%99>
,[Z*QV,3nP#m]Ah3Q]A4S'%;2$e#L4"`Ee<hd6ZA"gr>a9+[)J4(f=O2k(1`KPT?4C.2=IMZS>
do92I%_s2]AC,(b:QpO`Y,4=o)%,6\[('a$KK7h_#8YMMBO+odOe*ML)`\M?!GF,nLe99&\lT
^aj?l2+@\l8e.dnO%!deapn#pa<sP:eKf2)N8fH#!X>V,n[7?;:Yf#g'`Dk:uoG(,qGcsq(.
nnq&0,WkbhA?,L:D2cLi8l9nE=H:b,nJ1ODZB/%&njZHQq/1,nb1JqQl1dSp%gU-gF$^Qi%q
o.S0oD5?&!j8Y,tR`#'TBJ:$JT%oW@8#OOhpWI/C$]AP[+NEpt/ViWtr:"5K:[&TE1j!GP?/G
eKMa<'d^pC896qC/u``7,&OZ#]A^)QCje5BQMX[^03+lg%)fcQb+u#(fP1"jYd9O]AHj9:qf3"
+*q_NV^6Wd#WnPf<FXZ6j:m"$9o\<Es=3NPfS7OQapDoRoX";J8-SJrlb["p5G/.]An"?ji\R
_I@nC(*6%@m:u:)N6kCc$N>VT@*TX?i.C?&FA^*8RU)@$6Y</+UP&3*7%csI/'4'Q'bB9LkZ
=]A`"&ig.(f?.[Q(CdOf-9B&QXaSgU[*>kK2K-NE,d8QJhW(=C;/)!Jb^el^qe\6"_YVUIg3B
/$k_S294n1cdjV4^7g^>o##pN)*6OnoI48Jr54S1A6,ZsELJN+FeD8,Vo?C1guG(_3N"4m"*
1r$8@IM_ND%:cXE>a1umEf!lXeP_UHQ:478!IAQ\hNVh^il9V.WnR*qbsOFH1p1mu^C.H&C-
WP\VlF"sRXACl0Rk`32ln'-=\L]Au=g2Qed*]AY:NrY.B$\\\Zo+as:(uT:Ce"R_>/A$fgVS>B
5RDf9'/-,rhR_Hsr\_P:j0W'aCi$NEd4J2`SQBE1Ar`^FID(W6O"NCA#IiVk_%b_idDkVZ8j
<*#0F:SLTA\#A6k#-J$)IS*XIrU`;5]Ai>^e%_:mip\_-Y*F.j$bPR7hZ_s]Ahc8C/!O/"D,u2
tfET800LcktnbEDJ4'<"4-Z7T)3;@Rbt\p6;to>F=I)pfD^J,Qac@N4GU]A.%,7?YtqY=41D?
M:?VT-+D:?aW?=X%p=)IiiZp)7SR-uY(*YQKn*,!gqf4t+Q7K#-.IlGEZ]ApoGB!,TR'*6pdA
\k7N)X;C8WaP[SS>5lcOjqg/]A)be4dY\Kc3@I"d9:T_F!U<KZUH&NI24!g=/=EuYmQCaSog_
dBZ?-.nk;Ni>FTd`kUe@8<;O9P[pEEps$gmcRHTSP\+4=B`Jut+lP!2DkIJIgek5!/g(uILp
oUcNQopdH2>HHn7j/q'N8%"I4>o(%oJOl<=Hu8l,o<si6]AH&`3jP5="0R>imY(XU2%B&qX)B
S6B\piQJ@=t+\%dmurk3EI(t'P=RkSd=.P4,#/0<XU@fp>%_8J_>JbfBY4/I5-^nu\j7&^E7
&f%$0DCo-2O!(f+E]An,_*QW$-p>"GE\I</SPs9-$mbsfr*TuF_mkRI^d+;K2!Q5,S.2D@VQ.
/K:`R,qpP_Jj&O85P#b<TCc@JG-"+j79nK-CSWXL?=_7jJ/XAlU/sZ@C<+&N!*g/LP4.et3'
GV=#Q?"_hGM^]Ar#tqR3dS\(#/Un.n&]APU>gmm-]A?Dj)W*G$N[U:((1mgSH9r>dK\/@<aNsFn
+1B:!>]ALZ[0/YcP2tc^EtlP-^cu0ll/O.s"(n/\.0Kon.<LA1XuX\o%VL^^%8GnTWm._ON%e
ChCrHh.4-%Pc'9Q^.49+2HV.IJc9DQltL#DeGrgJ,EV'LeqT`ni)[>@"bHl;N(OLDL\@^"56
j2Y[@&;QE7Zdg6k:,Fn2OLTj;L=.3'69(Ej`o;RXG*mZB'Ca/JSEP0\6\/\fIinT<LPU`b'-
dF.o$XtAgub#7mmMM?`"%-(p2>ecj9OSqQ4`YFP8u3nc&2t(NIoFKB_oDcAP7On*%%k[6to&
KiSMm932S@e5UnV4be6VY(bjkJ:BKbj1'[[?%[ab7&$TI9PA'gVZK[f"i^#!`"9-]AF8`ma/f
t=W;&DRSYVg&$cP$>EtqG-hsZ2O5t"@bTs-00#gbGFLHkiSP_=TPA@N#6BV"/&T`!rDZ9E1:
T8cNbE@@2U2:"`gX^Yn*>!O3kO>3U(9o71WN7.KG[PJ89mY+;j%XCslad#J_Vj3D(<R1]Af+i
3?N'[f8A/c/D_[7cjn#'7HQB\4eIkm>Nhi.lH488s2l3`l"-n4g,'Lk7IubMNO*P?Te3oFPS
21\7YSY)i/Z$uSb(l]APiFi5QA-5D$#=kR4dTkL'[bteCAc'QdCN<ni4QKY;Yo2XNEnqr.54Q
J2UVIV*/UGoRLo*.<uD=k]Ai,b5,iUuQSV[=G`_(+jYdqP[J?0tGfL18Y75lUjZ^r<.91?[GP
)`ao-0'u8YbQ4?E2O<rpO0Oiq_U"cl)'kZJoMDpMGk92E<&cQpF;$Wbs&K1F=<'s`RSd_Sfl
\N%eD1^']Ajc5D7RrBZqGZN2XBc"Bs@Bt-]AAcGDEfr8]AH,us/5J]AP]A%9X\I&&/j=P7feDrnGT
k._SoVL/TDrFMG=['A8LFU&R?[.qCr4736r>8%.f@OE:Aq#DAR;#i0ddc;#.O%\@9V+1BM;c
u.XN-*Y`ZpdH6`Xrc@E*4^&&`<Y30&A]As_ja0sO!R*2qEBKbmusjB&,EN9-2ToZMZ<]A36as-
.H]A=]A>$l'2H^pZT%TG93fX&Gt"8q_Zu5Y's&)uuq"A$TR^-MPH6c0p3?7=h3$(pHR0NM86C=
^qo9%<\gc@th;QW,k5^WN;*O?6STu;L)b:JOM5$f!\#Z@?YVG7@`S?/[pcREpW'Z,GhT=*#r
G<QXB!7RIY7;>(mBoM1"j%/Gk]AVQdIO#9b2[*%,]Atl%2L&2+>M&VWn4ZD=YGe-Kl3dV[=+3(
B"Ri7WNdX#=1Nq[>E`m-P"NG/Gr@;3ZX9i-0NWekf90=YO\1,61NEU)3Gtr*pKSij/&g#c5[
i)ZhCDNuJqWr/U4":9a!KqNgn92d)..bN7em95<.k!>B'r9oFtj`0[:7F3-R(p,`?bhh7u`M
KKEG"P(JZW4rP%/U=I2bGSZ$\]A,[lBXWBS3Ul#,1lMbrj`i[>OCCWJd2isDpBTsrmRka>1-.
g3<5:BH:i^V2rZHhk@2_RY'`qT7ZG!s5M-Jo!&Y054[2KfZ@:,pll$3:)0g^BF)*nL-'o"/o
D)D&UPu_A@\:!\4D<_S#^W#6^r?<!.#O7#V.[$i:KhoAp0X\s3'ZEl5+dDK^3n()AVi`Hu^m
/rk4JrJa!uV*&F8Ltl6iRs&bL$h<]A'rN7B-o_`.4H_37(!CVq%DOC7L6PUYl(JVXGU.Ojh@p
LPm2%ORL$ftY!.Q%gf#jI(,)@)')q?m:1RW)et728?J=qM_H$l!V+p^'cPV`<M;Ka,&L!Fi>
2Pu;Kll]AsjHs$QU>J3/Ou1<LEB<[dCn0%t)fLKc_MgcbS]AP4%Mrn9O`^Xd.^\>>r53G8RN<S
tRmo!#5Wbgu2bn35e\&.mo[?rj_;Iqp'#s?,Qn3"ajgkTB&JQd`..S#lA-"3EN`ho=R@9JA>
ikE1m]AESH]A_EUnQ]APNH#^2#W?aSB#M37-XGb:gOW$t3'3ZV^89qCPl4=HrU8EQdnnumHpmI3
oBA-!(sCA`gJD`^VKgV'h0Kc,^Q%3"B:f/>g+ap6,QXhKBjX!D1Oud]A!m%YjA^[\)\C)-(_J
LuLmf#oqa#0@t+)F+8:u_c<c5A#8m6i'pi5U8(i;]APt#lXrIrHl0rP8"76VNChq;Yu2GKBC1
R7))8FE<<k@k?9CE(P[qq\c!]AWd![eY83f'#FWp1=J9*^E1ArH-is'DUA<Zl#HT:O\`8V3@Q
STsc)2]AVk^C0?@Ir#-m8EA[:P_?t`:NJ>NN9sj/Fl);IdJm_N?#>S>E3g5)15;4-$[$:,/u%
r;:'hgk:`G`?!-/sI,MDas"1km+$Je"@'9CKI0)FQNcnQZ/!/e@uj.AQU)$5dA_:P9>;kR6>
?d?C@LT_d#\clVamc64?=c!WYf,"BN.J,#SZ!Zn:iT3j>p(P)m4=*Mp@R;ccUQ6eW>qQ-:Dd
V\SNY#ZF0bkc:[[s0;Kai;;0Kq1?6"1"e0+AE\=K`&'/oYHVm;d4ld\Ftk51D'6!D:-]AY_/K
R(^E1oYtZHHRa[?1$-)qo>7j<F7j3<#n8BNM9[hRsM\(#[&_bs7-"_"2l[2P,h(qJiG&A1Od
'SRMrG=J")F3mp=BA'rjYd*eSq5ON#"5UX&7@.95*W5b[lf55gEN*A.Ki<K!ggfeH+Y.='ue
Z1TTU*qbBKaP-6W3djJ>opf`%YnrNH*"8MP1X$;ND&ZPKcB*mUh9\d]AaLSnS>RnG`/Ca7$@R
b&:rWjf34V:Y843/gkq^lD6JA0EqW%.L?NTg#4dtj9c)%"3<jgj>BO1f(0kd:\6=*#<A@.3;
WOR,3+(75C1P>hZ8j[,=m]An]AX;5VQ,B/K$+/LX?Pm5tab4$E*gqf^T#r%9Yem+S[T'Z/[$jA
3A)*+#IN#YeFNn3t'EaWjjl+..bo<M,_r-)L+F3XV&1f0Z^7L1p*!h%pYn@pal$"]A[[NFuFi
!grd;oFIb_W6nq?c3O:Dl\E3IN5fcairj">fS4f+bs2G\^I^Do0r]Asdl=gk>pNc78S`Vb(Tr
9Xc1BT[RloCDojs+mb3?RU8krucro]AK7X437lbh:`go'<=b4m);%Y7P[%a1U9:An>F_A06&\
PTT#7jX.Y6p?C%aUQL8$9hd;([>#[I<M\#3P2=!f/&i1VI3!-!:<r#NfgqKX$b2VLfm>ae48
!bH9GSt`?aqpIjm>a9[6F=;LZYChklmiuD-:%4jii3g**]A#R#KW(r1^!@M=I4n7aWb/3DcIT
P=f^bNlZ&rmA$i!I1`oHbZD>[CWQ$!$E-gQW<jT<IkX"]Ad']AjVee^2J3*R8Vns71o7qnG;Fh
UWTP,R.*?D5LTFHp94Q)!KBiGqCIo5:fr,]AeAeaL>jkDIBVrbpi02U#FHI0m'jJ<l&Ms:KBT
F@56dmo$8VOtiK:PUJ>EKhGZM99+Q(uOn-kYJ1mm*HUPlK7>u8W1aE:@VVi?*&'T.!QMEa=m
*I8.CkZN2'bo4S<4fU/bm]Ao(H%.ddaH;^MUlLH"Ar))F_7W[V8C?f<8HEZ:PoKo%\L$uZn9m
9`[),_&ed\HQ?G;7KcUh-!3c^h7&;aF7^@Qk=ghps&[O#+-7n>ct=qH?Hn@*g?3':;FQ:/8>
(YGW5C0Vs\%gV'L8&r^_,W#UkjQq/[6]ANf]ATIOW3QksQHMrQfCAn*mNU"pKuX[`HDod6@)2l
N;'TBu6ZlTVT/M#<@X7Uu&"Fa>CPL.q('>nr\\/NZF/#Y\J;[Iju%kZ",GQ[/*G(n7h]A>?/%
Q%&t1tQc7"C\hJJMTp9:&Q!j`gN_aL3iMM;e',Gdt0.M^2[3S<3aWb9$r"V$nNBiC!u_pBDc
"IRju&=Dc;C\<SSC0e#n"_P0m;N]A?!W+OFWfbmqK?nShkFS`*Fr6^oMloL7CiuP9do9n:L(<
#4VgkIU7a_/glQdmd2NOi;0T@R?0_Bc#IchCilj('=:qf&/9qh6c]A1(VL3d/lA^X@p4gq8"-
7>dIk+qcN?U9Dt&d3IV^Zlk?cB.X*J9iqZ!cDT/14Rt#'nae6t;IsI=.s%$3fHZ<aSIXcNH&
FGCC!.==<VA3E^c9kcDY$g1!KE&52X+W3:a<'e5\F[C&+tOsWS=%f->2U`R.Uf;_:\3<iQtL
\3KL/\'8\N7K/,#l;HJ&esM,3R[*n;bXPb65qUV>j_OR,,[4G:$r$_M,k?XRKKLPo!e>]A4e/
p'^Vd@.fG+/*C/m]A_\p+)sintNO%u,M5n*>2:u9rd:HKhWVF*S>5gW%3E@FQO+tQ,C\,l@_o
^+f`CC;!+DAr3EHm=dRWqr#@HKSIcWVXJ<Y@K(b9%iR?KpUCo@t/Emec2No;S1aaY&.S?lY'
edXFo1&\Aehb"@:?r"At1CV:SVJ[i&/4c/5DK^k-aX]A1_1S@&kUHo8R2AX8O+%W^rD/@'3GA
LK9,POV@d&iLaYI1nbd$G'@8aKr17]A_l+"pp::ON+,`%rpaqfFJ:bEo1/5j!YKkuO=,k1"W!
M=0.u'ADt'jse/k/I:%bb70iqG-pHn@*<M<"!)&]AU&6L\p"*Y$EEfB^^?r;%m%/Y_6q+GJ'm
2:f-8Z:'b?T!1`>f+hDQY<\NEp'.g[+0V^BIV5p+jp>rh3sB,;:5#PnF(o@EN1omtjYNG:bh
_OO#pW^O\5EjE@X_;*k1u=>gdYW[(ta*,`a'G,4I9CNSdCX]Ag="90qlQcLm:qu&cCMH:ri"&
"o,F^#Q(_aNgi%#BTe\US3=fKuY7FuU?q(R::NK>JH*9DRBiY-bDPiE[41d>/[jYDe(r/]A=/
JbX[T9RIK_tE[BLY4EE0%`+EcZqj)G0AtX>\I9bi-Vh,'ONhNm@sn_1hSugCj%)@$GqHVO#4
h@4Th@CHOAlZ+]Ar)I1S*e8<:8f&IW+(,jV@:4O7l^j_>Cbpg!;epXp5!%F%8I)MM3c<eST=j
EraEpq^`+?HrMD[qPpaQ8a+=S:'Cd#q:*LZ1pA&Ic9s1S+4al+K[ZSkW:md$C^23;DIQY!-=
dIOm8tP2-9^2%rp@(3?_pbP\M>4$3hMme)^es5;s"R.oY/;.9KUq>;c,4BKe4$+EH7`.XqS!
Ui>u?Dkrr=hBHG$<+euVnLZRCDO>$XjIsf@P@YTbuq\1Z#8[2[#)bY/\b_<-8-pGBjD3RB,e
3o'UcgIB!cmkQbL03^+<NI4$REsqEd^8.!M4/&Ag\K3M="=U$Z0ig[T6**.ehg4he2W]A]AWr2
`;qb:#k?F!^Dhf_97Y'(&,<NMftdYX[SNsYVMrE2tdj"q!A@5L-Xif-;mj!q:=QLF"[NhZeQ
HT$m66%k%T5*!ka%;u;,:1_rW$=ec6W"H]A4?Ne$R!rM2*^Rm[#eA0ar&Z[nUUe1@4puE[MG@
bkqi/g5rI*kP&B(Ro5=)tas(`8)cZdfM0q:?u,?c'ZJQ(:"OI%9OPE-'Q4*&h?QUcpg(f+VP
@iE+^gAuS;2kLcl?pJ=c)qi?5bdpgp.MIV%:=_;o]AC4f"q\?bZDaTaNif=RUo+u+C=-tMj<e
D<&A21;0XNd#bB=('CVAT<-!JP/d_<W`_:!j<=2khI?.R96uQREn'0?0\%j>If7:n3g4^FQ1
PtG;uL_V8qU(*LPGpc&gr&YJ(l)e'+c/9CIe6n#WP)XF\N`gb6gSGdNoORKt#dnUYga_/;X5
l1h8,5^0W\NJMf@]AB\NDM7[TgUY+O<ke^[rA.P9C;<sZqjU^)a]A.?@QC"pf2M`0)j^Ul%U;>
fX^*_)>Wja+LrP>g9De/0d4.S<dlm0hS`NV+!2G`MPiY-s66311Kq,n'Oj>:"A%lo(Z5m>]AQ
h%Y1N2n!@6]AA?.>OVftV0KGoccb4*HL\X%lJ,j,JO@_t<&Aa$P#RM]At%II5HA_!XA=mC=j5[
BXPir":&Z+*I2CXPpU[@c]A>fUZKX5*YV$h#`dKWns-Q:oG]A'c>s4#M/u.!2,(>]Ak/CDLZjji
R/!mc,=$bli8QJAVW6]A]AJ#HCP]A<U/2Vp'3SZdFN%4L47d]AA=UO9r=a[bM[JYDE<hRKi!St:@
>.`GhMsaMLobM"eV<(s-/O_?/+j*3F#>^J403T'&3[>75jD&`G12qe4YDd4=6RjEn3tV24[#
X6-0\t*:-D0jVh6ZQgLDPqul`!1>Nn4L8$HS]A&Q&XiXJ[<=4G2sSTjc!ZX!PbdC\Y5SnDlY8
c#6~
]]></IM>
</body>
</InnerWidget>
<BoundsAttr x="425" y="0" width="540" height="510"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WScaleLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[this.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="textEditor0"/>
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
<InnerWidget class="com.fr.form.ui.TextEditor">
<WidgetName name="saleman"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<TextAttr/>
<widgetValue>
<O>
<![CDATA[1]]></O>
</widgetValue>
</InnerWidget>
<BoundsAttr x="385" y="0" width="40" height="21"/>
</Widget>
</InnerWidget>
<BoundsAttr x="385" y="0" width="40" height="33"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(6);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button1"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员6]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="198" width="134" height="34"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(1);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button2"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员1]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="33" width="134" height="31"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(2);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button3"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员2]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="64" width="134" height="36"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(4);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button4"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员4]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="132" width="134" height="33"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(3);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button5"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员3]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="100" width="134" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(10);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button7"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员10]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="321" width="134" height="37"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(8);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button8"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员8]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="265" width="134" height="22"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(11);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button9"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员11]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="358" width="134" height="36"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(7);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button10"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员7]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="232" width="134" height="33"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(9);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button11"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员9]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="287" width="134" height="34"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(13);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button12"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员13]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="427" width="134" height="36"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(12);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button0"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员12]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="394" width="134" height="33"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(14);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button13"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员14]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="463" width="134" height="47"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var w=this.options.form.getWidgetByName("saleman");
w.setValue(5);
w.fireEvent('afteredit');
w.invisible();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button6"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[销售员5]]></Text>
</InnerWidget>
<BoundsAttr x="291" y="165" width="134" height="33"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="label0"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[选择销售员]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="80" foreground="-6697984"/>
<border style="0" color="-723724"/>
</InnerWidget>
<BoundsAttr x="291" y="0" width="94" height="33"/>
</Widget>
<Sorted sorted="false"/>
<WidgetZoomAttr compState="0"/>
<Size width="965" height="510"/>
<MobileWidgetList>
<Widget widgetName="chart0"/>
<Widget widgetName="label0"/>
<Widget widgetName="saleman"/>
<Widget widgetName="report0"/>
<Widget widgetName="button2"/>
<Widget widgetName="button3"/>
<Widget widgetName="button5"/>
<Widget widgetName="button4"/>
<Widget widgetName="button6"/>
<Widget widgetName="button1"/>
<Widget widgetName="button10"/>
<Widget widgetName="button8"/>
<Widget widgetName="chart1"/>
<Widget widgetName="button11"/>
<Widget widgetName="button7"/>
<Widget widgetName="button9"/>
<Widget widgetName="button0"/>
<Widget widgetName="button12"/>
<Widget widgetName="button13"/>
</MobileWidgetList>
</Center>
</Layout>
<DesignerVersion DesignerVersion="IAA"/>
<PreviewType PreviewType="0"/>
</Form>
