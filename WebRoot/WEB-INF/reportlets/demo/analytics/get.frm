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
<![CDATA[SELECT * FROM `get`]]></Query>
</TableData>
<TableData name="ds2" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O>
<![CDATA[纯收入]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[select type,ROUND((金额/纯收入)*100,2) as 百分比 from
(SELECT type,sum(sum) as 金额 FROM `get`
where type='${type}' 
AND country='China' and year1='2004' and cate='现实' group by type) a,
(select sum(sum) as 纯收入 from `get` where type='纯收入' and year1='2004'
AND cate='现实'  and country='China') b]]></Query>
</TableData>
<TableData name="ds3" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O>
<![CDATA[纯收入]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM `get`
where type='${type}'
AND country='China']]></Query>
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
<Margin top="10" left="10" bottom="1" right="10"/>
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
<![CDATA[1333500,1409700,1368000,114300,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[5295900,3200400,2438400,2057400,1028700,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="5" s="0">
<O>
<![CDATA[标准收益综述]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O>
<![CDATA[ABC公司（单位 千$）]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="2">
<O>
<![CDATA[2012年]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="2">
<O>
<![CDATA[2014年]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" cs="2" s="3">
<O>
<![CDATA[变化率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="type"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$type = A3]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Scope val="1"/>
<Background name="ColorBackground" color="-4144960"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0"/>
</C>
<C c="1" r="2" s="5">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="sum"/>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[2]]></CNUMBER>
<CNAME>
<![CDATA[type]]></CNAME>
<Compare op="0">
<ColumnRow column="0" row="2"/>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[1]]></CNUMBER>
<CNAME>
<![CDATA[year1]]></CNAME>
<Compare op="0">
<O>
<![CDATA[2004]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[cate]]></CNAME>
<Compare op="0">
<O>
<![CDATA[现实]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[3]]></CNUMBER>
<CNAME>
<![CDATA[country]]></CNAME>
<Compare op="0">
<O>
<![CDATA[China]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[season]]></CNAME>
<Compare op="0">
<O>
<![CDATA[Q1]]></O>
</Compare>
</Condition>
</JoinCondition>
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
<C c="2" r="2" s="5">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="sum"/>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[2]]></CNUMBER>
<CNAME>
<![CDATA[type]]></CNAME>
<Compare op="0">
<ColumnRow column="0" row="2"/>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[1]]></CNUMBER>
<CNAME>
<![CDATA[year1]]></CNAME>
<Compare op="0">
<O>
<![CDATA[2005]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[5]]></CNUMBER>
<CNAME>
<![CDATA[cate]]></CNAME>
<Compare op="0">
<O>
<![CDATA[现实]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[country]]></CNAME>
<Compare op="0">
<O>
<![CDATA[China]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[season]]></CNAME>
<Compare op="0">
<O>
<![CDATA[Q1]]></O>
</Compare>
</Condition>
</JoinCondition>
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
<Expand leftParentDefault="false" left="A3"/>
</C>
<C c="3" r="2" s="6">
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=(C3 - B3) / B3]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand leftParentDefault="false" left="A3"/>
</C>
<C c="4" r="2" s="6">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart0"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象3">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart1"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[D3 >= 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" specifiedImageWidth="-1.0" specifiedImageHeight="-1.0" layout="1">
<IM>
<![CDATA[!T4,'qMA$D7h#eD$31&+%7s)Y;?-[s('"=7('k*E!!'sECc_Us"fFp[5u`*_Fd7/M;g/qrms
>A0<8#IG(;tp:(")#S$P#S*i';+\#E"BML0K`h"LXmFJceirWi2k(77e1J8/Y;qXiCGW'1D@
EUE-)LC2Nb>pOWi`:]AL4N]A\MDXTA84G?UBfqQft-GP\OMTl/CMGFQ[d!e`on.$h@"2E5^r%T
@$fAQ:83,%m;#t4W=3n5W_8;p2ol^)p\S5Y@q!@'Kt5:(%mu:n9PqBW_B.mRWDH*3D9"Q(b!
s6O5jTIlCodRniRJJm=M9gM28o'$h9Qr,I6r?";VD2\B5k,[te$8*sBZf1`d27FoNt05e1UZ
X8SH%Nm3)iZ40jkYX$bI#NW]A-V_M"D*X:TmX1h]A:mXZf7Xfto9K0;.VPm=LJp,lWGnufK]Al>
YV>f$b%ma4=\':(gu8@ojp>kHX81K)A2Q@Xf\?D<2=8-\Z'=.Ae-SNcc.mgc/5t@TRQaONKH
D"E=Uq[oMpA?g^#B&O-C49:5MgLX9cW9C<()#cWt0[rDS[f=E-(dRYGnc@d0u1*4kc,>:>l*
`ep1V"b1;^8+/f:Xl>T_J[k+s&D(NTQ!6dj'm8"Lu;FS@<rO%]A@[DN9o%,nCLcRIVCiIQ?Yh
rr<lAKK$',T-Ej4EV"`o\A@uXscl93`W&X-NT.rO-=@hpNps"0#bffWM[3B3WRN6W$oeG[m?
Bq#+pnf9Yj@+5K+LVp&q_Q]Adt.#,Y7/7WoFYclqc]A%`[BE7B)Wc32K,6FSod*&9MGg;>u/b@
1"'iHgV/e1nL/f-(Ya2cZIt+OkLuo<=(8_"1BHCf3Y(OBRuq`$ND='Ze45%]A5'cP6Ab<oUZ8
$8&lVaesq<Q2A+.G=\TMp'#T>4'4F?G]A:#W3:=>u:9:DtL\q0thi[>kB=:V&B_C,4dgVo-/?
YJ1YJA(m9:hGLohW4mf"qG*##&t=^qO5=O9QQ0Lf"6d'9cdJp+.LXZ!k3mh-R>c,'#b]ASr\5
r_RR?tRaWOVaT1/3^#7Qn/KIE5</L?L;!uJo@Ao_idZ%A1p<(dSc(p2;S-$ZX]AQH1`B^oqF,
QBNA,CPV[OV%R#UV]AXa0#k'd'jqpm5htR5BU+EZNqV84#HANIqO`g!1&si*ZM?L:^h\'!>=m
R/K&?GJ:0mCLaKO,K1dBAsU]AUYV]AZA$QUS%o99IjKLJ!^WXF5@PDoN%eU^O*jB`P;WDNQ]AKE
:orD3p^p@Vqeh+Y$K@)G4hf&TOnFe80GEn`EoIOMC`<<ek/2:Y('B[IS"B#,)$JWdO_s?^@)
sK:KZBg,"OqGO5hpBg*?k=,TT/"9<*$JC^&NZNVSMN_>p1n!B1[9Db!u`Y&#["J_h]A,O^&Ob
So2q^BaYSApB_i@B0D]A\.$puT^X`/UPF%`bl%'#O?kZTYt1N564]AjHu1%*CQ@Z79&*Cm$;t`
ZZ<6:VN;"?+^lm6T"p.p`,Y"'fEEc5(1Z=F)6hpHAoVR@j`H>)Hk^.EYP(]A/&>+MT1VQX@8^
#pbUjdKYeB]Ae066kq>WH,)3IFo5s+si>V#K8fZ@$s]Am8eU%j==1@1(7/h]A$qdaZhu61a2K-D
0[%,WpA[dT^r)JFn/u35/=))1nmc*k&@>YY`^GoX3[r`;Uh'HXI>!1#Q'$PO(%c:Zbc='P(2
8$5"0K3E&qKqtI2CWUNA$&d+%j'>!X]AEmocI^"us-=."hI%=p4#LVVQ:%uS[Pd4/=hprN$F:
(V)><F.5C@O,_/$Xj>\/PWa"A>ZQYd3i&S`i^DN^m-RdN'4dTIQ6d?F9*XJ=:N23_@YhTd]Aa
KZlU4=Z%t;kMr8`<\6Z`D-Matkkj!i`*(X2f.gh'c?4;=$"4O\=[l>A-Zp(%"#L9'&inZYKE
(uP!(fUS7'8jaJc~
]]></IM>
</Background>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[D3 < 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" specifiedImageWidth="-1.0" specifiedImageHeight="-1.0" layout="1">
<IM>
<![CDATA[!FH&(q2%pC7h#eD$31&+%7s)Y;?-[s('"=7('k*E!!'sECc_Us"suo05u`*_Fd!,.P=H>-(C
5eL_ZQ/s0jJH)<!_c>Js%<%ZrI@NH3$@K$Unp_.b'K9JO)`0:.L)%U*?F7JZ),Q:TU:bS18c
6&."@#0F8mGI/e(Te_6F?1V_U]Ap?hkAmG!$l!7B1I<t\5M/OB1WU:l&Ki(b0+XYPCVl+FCk]A
kOm;`'d2kngK?07FV]AFI/Mt&*Qa69;?]AF7dLHi]AR0(gI5)F@f"@Q+W:ZR4TA5K:k.u9q&SnP
.(#qDP3i3aQN/M)rhU&Aau,\k*ON5gebLsjc"T!SOXY[Fh6pP(6sX.&e;P1OBmSh]AY,g@VpX
gT)Mj.[<r"QOTbbrkj`)ia]AT<qf:&l.05mdH%8`dM=X&I$hC"F\dk:F:(VbHKJY0UP\0:/Q(
ItHXEsbu-n:i$mIPWJ]A2P#<JNjP8m\[en3$;-"AoBGleEpXeDpso]A",pV8R*7Bp>:^INCP^f
e5W!8"7<_5@(r_3dojVLW;kg:k+KD^PRtU]A)1lS-Yq)0)B&dr<h'$=*+lQX,\UuR2J^a)D7s
-%,49-!f'plK_<ULbVSU@9gS]AK_`q/h]A=]A`O1t!>?;heG?'7'CcF_!O<^,05+#n;JS"gGKFd
RqDD7(C?lEeD8Rd3@hI(/=9lpqWJ[T7QR\S!\"g-0&[jk,f&>H>/,4-cL0,?QMT\e3!RX#kq
g]AboL8uOO"B/FS6(_")h*B92j"HM!P.0t-O3lD=^D?R.+*V%^uX0jbLLb6I'a4&4u/^]Aeb'/
Xs$!!1YYAm4Hh[fka9!`OFAUR90U!+q*#NTo"r%Y=[2e4jK4J\)*B\rK.!@aZ0h'adkmESXG
bbL#Ul!iF@>4JKlO_PLKsnO^AP*m@#u&(:pf?UHN]A#';]A=4"^n-HUBpY3oc3,?nkM!3rG^k'
H=g?&;WJlMs3PX*5n>@PPe&^5s,A8Yr,jBd!3pc9erB>18<+?&:)dqE"WDZgr>7Dn)j)7q,Y
MVVAW#R)gs?iiB\p7eP#-Shm<a8/:p>)()g=FBVMjD@H"&j&[KUeY'R[PC=MGWU/$N.jO.GI
0eJ*;0pnq0)"=4hoH_fK1)ia(U(d=k8_:,a)qqNUPU7.R9r96K)4R@hAVrWmY*dDO>mItO19
bXjrE1ptm%fg[k%o,LA#%<&KYC/>a?o#SA7P[/:<lA7/:Jm5es>M.SVL7Xd1ER<5\t"_8i@X
#WT0_6%k_oNL^CEdLLYOFm0:P=/_H/D(lhWa"8&Cb%#Xna?%r.&6bSanTIMOEVRfBlI;fbOb
`!^AHgXaNWhCT=cOR`.5Q='E3LRSQh;g$+U>d@Q1W\p!d6;q+3^"g_^S*s^UC9u`^]A<s\F&g
UJ[#_LYqoBB5[^Ut;_ur*/p*^4U5Z)>k!mqbt%95kc]AXhupKmY?dg"SgTjG8h)erU%=J<<G/
769Z<Z0'/<8=Z6t1"li[Z&TAUfR``7mY9$-;6S)YQa\?s&UDY.,6.22Ffh7,\3VdCg<($kZo
)DI:6=*:KfgslN*?2M<G5U*d5>tLUf8gb)2FgR/uUZe_ikC3S>G58n--q!a%m8pIIRrHe5R>
,F/@!HK/tKKd-c0DK81N:.Hks:5aAlUDscqG+taeiDU.J9R0*0m+*;NCd-NS)&#*)ii'"KL4
?M<Wc.O9j/h&)0=0<.g^&qk5I""eZM_em%2Gd/@$>%j+:]Aq"i3QDZq_["PTZ5(Mbe"@-.6P1
4>(!d89oG*TD?]AK!Kc7cK`<cXe&3_:4srqK"]Ak7o2qT1Qu%'02etoBs"]A(_<IT)mnRFToP"`
C=sg'IQ\LIKd#Ya\e+fj,(\j6+^Z4G9+)OYeXLc$QS6ZBB6=9$ReuE#`#8Gp@NrqjRJf$QEQ
W(cWGQ+I#C;!t:b>r]Ap$+N8'3F5mqDDrHO2^Lj0P#16/\AYX;i1`mCr*3@jEQ(-s+h?>&kV4
Od^!LE&KNsopgQ-GZ&\-q^1Nm6>D91ra%^pX%\]AA->Mc/IKh$Q:X,_qY[6HW8PR,ada^0J3c
bt;8A':6459"M#z8OZBBY!QNJ~
]]></IM>
</Background>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="0" r="3" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="3" s="9">
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
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="128" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Top style="1" color="-398635"/>
<Left style="1" color="-398635"/>
<Right style="1" color="-398635"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="13">
<FRFont name="微软雅黑" style="0" size="80" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Left style="1" color="-398635"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Right style="1" color="-398635"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="13">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[$#,##0;($#,##0)]]></Format>
<FRFont name="微软雅黑" style="0" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Left style="1" color="-398635"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[$#,##0;($#,##0)]]></Format>
<FRFont name="微软雅黑" style="0" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.0%]]></Format>
<FRFont name="微软雅黑" style="0" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-398635"/>
<Left style="1" color="-398635"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-398635"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-398635"/>
<Right style="1" color="-398635"/>
</Border>
</Style>
</StyleList>
<showToolbar showtoolbar="false"/>
<IM>
<![CDATA[buf<P<,)8Kf.J#]AW_c+F<fE^[h)9)'MU9UA3i"N0M^b!e1+ni7%o&6b(1#<sW)J^<\\p2VNJ
pVc>hWphjAr5:Hfi1BgM_T=r18>.p%W%BH,&hFI]AoC4kBk(TfPHp-XXlVHb*;.[2O\KP1.=N
Nb*6gNrpVLaZf5+rp67#ka<n7$4,uJkIeJ<>^0E\:1D*3gUO@4KnB4IGmqc%-q,rU/)B$d^O
Zp6:amWAo<ML*'WU&5U9i%8rQ7C#0P3EXD!^6$7<?+'W/1ECCE'\E-Hf[1d&CnHBjhRO!.lf
<%h=U)rL8:!H6WV7mm.FNn-1]AubKi%\PI>;&;CG!D#7+Iu9O6>ei,e?e5D)FsJ<(72,_MKk@
nG,hK!j.#iisAr.E0Wen#D8#C+'Hiro2Z`9>Mlr2G/-l[rQEdehm]A8g^!2Z#F[T:?KY:T)Qr
WR(Gu`?``2=.fGbds''qK2b\<$+2^*;r5f<RW\M@A8cK+);V3]AsujU1<cQ!D>c]A`29=D>1=]A
^%<&FE/Xlb53dmrP;06L)'7-E3:lD1hBHKerlJ&Se4]AT4bjG;C^.MN=28tf%Q\`gik8:lOB7
)I0"iG=n$/g+6H@)'Ag%Ym%kDUO1dr$c_uq'&APbq!DL+H8YiHX:nb08(`Yal=ikeKh\BlOX
Q,/hHs/e=-Q3guN`&R^.;N^C%)B3?a<(aQ=r-9:k>,]AD=J?*/`K5c`M?78XCY\[?Z#5$[7Db
">;")1P98'oO7#&,4`uIRYr!O(O"%:8O#2FqKZ3t?7`FVL30i4jO6WNfc`;04D2gkZaL[olS
a4'ppA!(&c$X'MGZVecBCOF?T_&)md-%CJhR0TQVIo$>Q=gBZ?J4Gl<P]AZHH=r!6u,pJC5tt
<T.c5uhV"'*D5$.afB\QLp[:Ij[dmE]A7dJPu8&sDE9tWq/qje-bl70*VesqCHd_rH'&/.ct,
r:Y>iUbSGgDl@0R*DM%rE8=(,1iRd*6!es&=P?FI-HNVREOI=7?EYc'(n\_o<,/=*7(+8.3h
BOW=6t4%2I_U,$<$Pck_o'b=g4g4b&0iX$CZ4Q\-!)*ELR]AY@C>$DHeVc+Uf0oPn`URe?q4X
+tL:m&=_0177qko%qUd'!pXR+ou<WDr#?GF%sNfa_$*YMagS=-o]AZNJ`e7TsDGag<Ys:D%Fh
@CS[=PLam\`t3T,tc=X6V;hWO1hL)Ijrdm/$4YnRZcP2\EWj.Ndj*Dc"q*9<ShL5\o`;QS7L
*?:;nsS`&idb0&OR6t#WGUXd<K^O-P2^#-_V"I;@<OCMZ[T2!/PbbO(9E@j2HNnUK+WIIO(\
.JpbDn`h-%ZA`HP84$'F7te\'l->\[f"r'/g"S`F</)fJ%AbCN8L#h\fna_L2@s7GP$Q;a/-
<ATYL)Ahs>hmV?JK7h#:<u"b`@J*pe'#!6H(f.bc>)B@)H[f3Kf5FraB#N:^lkMp8dF-d%<g
))T'!27?Z6W5'X)4'GS4fljF8U`e&7aA?PoI2oB\WEnbcnht!69dRHZ%@:"t0_t]Al%:tV-*E
[oYB\UaWE@A@EQJKK'[#8G'2%C/S\1G)1:*!Lg(#cNF_,0O+#<k:jM-5/D;793DMToEHOL`K
RgWSjqBq;m/DKjnt=Xp=7b%!I_a@0]AG=[Eh!.#"<#oid'2in,6AP0TZO9/`m[9kZ2L_oB#6&
+&1WoWuiBTq(-Z1<c'!i;8J:D$6gj$j)/j+X/jC\;Ned,:jsGKS?g1(+_gZL?@s=+'%"hgLG
"Tb(F"6D0<!;Q-3#\<m#]AcjE#=.1s=p)=f:5DbO,0nL"OFSiI6C\4Q=`\[TZQ.MhP?\S)rZq
.pVV[m[g;UoTS1B*mp+M-3Ep:X8kb=?)g)L2V.Ut0$'\`DW>H5OsOW4Wns6bbH,rd7t1R4jK
c[AA;+<RSGU@;ej?W?h]As9)"jFL6]A*QQ#0+G*GO<*%>;s%ThV&q8<*aWPVZ2/H>l.Fpu@e\i
S/nX$YSO&ml3=0T,)E'glW\"3!"U`$%fUBr)46YAYSlF2fgUq64i6=@,V#@ZTUHb<VkGdg'#
!,GKc!-EF%G=NO+qcF@4"AFF?B7,=X"b2afpefJafhogjdVGNCge3"6e#'A4\uoHXd/acp<<
RP<]AHZto35q\bXd@Q]A/R9sbNB#"AU_:;00(bsX.B0IpEVn[Aklh(T4b0FTJZ+MTdR$39(cM-
bdcB^g6:CS*EI0CmRO^]A:t$*6!GX13%fK[^Und%>1>Nq02rs,4V6!KrP;sElq!i'h98,a(m]A
l0,$0Gr@fdo?*I3uRUmI>=QV]A7SgH+?q=7_d^K0_W;7SBLupS'pr;dIplcNVi7:?!SDU\h*8
V@hS70-tRSROB=..Z0Vng?hHob7TogTcum3YHW".a^45agFXu2^qR*5m39mL03L*q/+55@h9
Au)fhR=G07YcbA%Bp7W#b_eJDL8d]A$i5i,@j70pE2"#+*FYsIo?Q'-B6cEr=G=;@2\skuHXl
tUD0o1BgS&d"o#;&Q4OOQH@eDD'5bOq1\IJ'!W010,#O>^L-MS/JL=\")<-Rl?i/;r*nnG^b
)FTQ'L_n=hhMerH=WQ_4"De#XK$BDlrXu\T?C+r"c#W3+ePWkq-+^WVqDWO=mQbM\<:(&`kh
?D?p(J=09h`;p"_sCL1"k2%FQ/Z\71'(,p!`>smdZ8(GMu&]A:E__m;r]AYegfo4k5;$>RU[&0
:MbIg&l0W.ZAb.JlFj+^##Y\4g*Gn#CY:3d`^B7&KHJD;e:*40@Tc_#/9KNo/rg/Z"IGBnB,
-IV/DpEjZ'QsBM;'Y$=iqf[eZJs/ers_n6k!"(9,^pm>-]AcmApkl9hYL`Fc('#f7OiOM?@R\
pHEDlNlSN_I\n`9>nfpW\9`JsJbFg-'eJkFNhVV[&Sf%5K\#q9;QZO%_/]A:46uXQI4mfR;-8
I:)/>UhOM/WI)e'?jf,8eSEJYHf';C$V?m:<F3S#S`,Z'g5WZV+nmocS4Wk&7N?7MkCFsDW!
1jf\2NMrW&c'?i04CAi(#PXPp8;*E/mI*=;>ZQV2jVI[B/7<Gb9,=W7#*"J?(IopZY,KT)`W
E30hFH8lW5YW'_n/f.kK^d#,hR;p:@jU]A/`>MqPcQ&(J&O62G)XYXKmUi9Ap05l*+=d_[WT)
R"&j`GQ]AXT0*!M]A4U@_D,3rr!nq9i.<$A*Ps_i:"l";d:rLJI@6\Rpfl:10M?Qh_Z>A/4_^j
fEqI9EuXYC$nMa-@u!)=npE8BP?X<J-'[]AJ]AAR00EcBjG!s]Af_?cKDX"$LMbfl@F*%BU@J6;
PL_!C>1^Lb*7bt=;IX1dK!H/(7BJoJ<+9om,-lCoZ9/fR8qg7RCf=qC%IY#PoGCQ&Q,@(,9F
n%D.NU'UrtADE`7]AY)[DMI]AP>p1]Ag)rPH^gW,Sotap-VlXCN]Ar8kuj3oi.0'qt)B^V@IM2g0
[!.p+t?k<n%Dc'0BGb'@Yc!PY-g:fOYAH/VuCL7j(="le6/BH"Qbu/fd*;d:hmS(e]Ah5.Ga7
jJG`S#\_`D6%+9dnh%#/j)PD1hSdXW0"H&mfqDsh1Jj@qJ99RUg^JXX+EFA1>(2]AP]A27Dn3B
I/VGI28,PDpe<+<AH5%H%S@eoibD3AnAlB-g#qIN7.?K!8?lgWbi7N@73f8rTLbV-i<:i>O`
qmX5Ap')RW43(^HV=Lg6#r5b?]AH6_tQ$eYDc0;?0gmWW/C%Ic$MK+!4$>=TD%W*d]AUo!t;Rr
"2aI3LhGB$uQ?CZUp.JO/AMQ><iL.F/DB;eqt3o"ePB+dT5L9N^uq-*PKVAT*iU9;R9TVduL
c]AJJa>&(TD%Wsf\#[ah9hWpN!iV=.13pN>!.H-=D]A4BCQ>Nsf(#B&jltD@+*ma6:dbhTE1o:
Z%!gX:=sL^/MTroKWhOfAWf86d#F3<1sOQQXps\bFs>HNuau*F/1T2)8n'5[du8n$C"&:(*&
g7Raqff+/Bq/_V2,"%!(El=<!*N(7lObc3aZW7H$QG?tFPi!(4,2_#qt_82%T/4E/kPUY0pa
XaT(g;8SoIXsV-Dn'.R%hmtP,o<Mh=LI0Hq:6M!"kJshl-FD)d?h95XhSg9j1.4'S1>2C8g-
M^OMH<qU'Qs>694a0OL\q)W39kCI>S2pcc9#?AqI67TQ[5!H.)C0Lid9;r:-Jo'#LG2S-\*$
dCTdA"$@h-8Y<tI&V1/pb&/onbmJoVI5>C-Io>SnX(bFD.6q'Nn*'ZdJqB!P&"*a^6#C<d"`
fusI4Y$2f1c&Vf=c=-LYBH)=;0Z"3j#9Pr'ttLQhWMKU7/e,knX`f$LKbA/Sst;Y0!!6;+Ie
\Q_/u;H-NR&O1\S0aGm/#KgH5S.JaL<*,?)EtVY+^9!"YcsQnZ+r':d^g*V"H[i2d3J0iR\/
BP2>q=E,$DcQZA2UOaFq*8]A4/(q>)6G$A0RE&pZK51BkuUsf_Pl7MkUWu/c$`]A!m%ZO@Y3a)
rnN#V4d5E$*t;/!O,VbKD\fS>fP1,PU97SK&BK6mNuYo;c$k:LI+h<f.T)&H^5q8UH^Q2GeK
im5+iTWT_)!)@r-T!+#1s>kq54UkZZpC^RrT-3f4H68_#9!:?;$,[laYnatDWl6;B?&Ep,%U
O:<D/1]A0kT&6dbDEUPPVTK-jJG=UH.5o%8`tQ+_=T'B(,A`,!VeD(pIp03pX/i;QqIe0lS`R
ISaBZM[K@;ouI/TmuJe#r=`U9qX#-5jn#.s;!//J?)h.DM?(lM\"7'RAHBH8ZISQ=!^OtYh1
@L9_/F\(/JJZ?R[D%K&@>UTbWif=ZT.g"r$Q4A+F8QiM8<5%3T_&aq#TkYcU0qGS,rtS0=9S
OrHZR.KtQQHl6o[GtqXG3PPL/*eGL]A:^YfYZJee,\(@9\?hJ.UkdX(L:H[i8co#5l624Qh"0
CCbWo@/qQX8Y9&$;$(<*K=CC8i5g*CdpPBH^1?3+OOn$lGk<kI2[puRC8RW':PK/`iihl4YE
)NtGp0SclM9`%tJ&8(blDk%\gfPaq^IVQ'X-4Z,A\it-qVT2D+mXY*e:T8F1'.&2P*,)0GM*
'n;;I)-%FOZF?VIdrB9]AM%RL:/ie,3:!oRssq,sf2J'Z>aX>9XQ<GPiQeeAQ^E9mi]AaEkn4I
#7J,6/r)CDFia%`-jB9V<AgkCMPZ*,>fI,TZn%=e8u6l5-<r!e%DNZPZ*Q+gPqIhA2p!WX@%
-&\-C$BQ]A'ujA0*bhcSSWl-QBO,jY30F`O/,Jj0703IK/ui@N,``bcl&Q+1j7G:8,2#t/KS$
/4AWbeld,NF"YgNG.\=AXUG<6b^HJDC>>n2G,D(2F(u6c7`a9Z5^(I.SL-jpWO%8H]A1[M[rc
/R%Zr,6j(GPW&cL,=3K,[!o_o2Bs`@"nh[B=CqZrkceUU5[r\ZiN?TaX6[P0DN/A[EX56.uM
NUhX%R#I'lhh[SZ-TP>GL+CJ[O7[Vcn+&sJ__jBk6NJ8j4Z7l'*7?hKoq]A&tRW&lN`@#9O!]A
?&7>$72;<mpoicAT#Tsh?^)33)]AS]A8]AN2GE;AG0_oK^RTbZJNP+Z&hFF02Bo"a9YtMT<Hu;I
m^m"R*s)r!]A;&84[BF&Ls0F@k:R_7(P<Zc/HRo#st<pl+?5@GDJh]Ak:>KL>4XD!7slJG0!E;
_"s?"AnB]Ai=>FEG"Cl"rW.G\h>'_`n@&TTKs`2$KSLY6iGLrj\+3H>[l`dKIO'8l@]AH+2#j#
l$Pus''%kE(mD9itrF6m*^P4IZIRW_sa3")snD;j&V<:et^YblSW[*`=a?6)s155'!`(S+.7
f0_'W#0o+$AdMn<'b^QgOr9p1QpjF_0C+qEiEJ6-@@g.?[ro*q5:Lm@`;1X,q:<Cb6N-+0eB
1ch%_p=4kFNI$kB.innG-B(;LY.8]AEA-*u"dU2#Yf)Hd-=TC0X=Ia8,A)Jhir6jT0F0Cpbl\
&?fk!KXd,OdGk1ueGj\bumnm"Sdq:-(.LoXCDX<&.?""3<I\3cV`\AOnPMrbFkn-nWU?oZCW
V;KV0:;:ki.O\DZn?-FWYekF:G?fbE[c'PC?=T;Z-o$@1f^7gLU@ojODRRph,=g9sW$J=XB!
T);IY`Xe6b?DF_:bql#F^\8[$b0]A:J?^P@XZMq&$be/JJbb:!p.[;+7q/Iq$[W_SiDJSa#/T
Jc`^de7WVE/H*Zg[h(\$Gf_,$cd?2n11$p'uP9qOc(?j6TjS:O\)i*0ES*n8!MH^#Hj()D((
*W^$1q-?9;EC2NO#4g]A%1.pP!MG'>3PUT7Fla$n-I)\c"1l(meHGA![N'G1B+t*_:U2N;[:^
-c0&L178IF8L'1qm<5W$b&b9PJ`ZEuM9!*/7gAGg8+0K')+KmsT<Dp+Jm>/=HMN$jBKPE<!=
p,Cq>R9V'[O<l7BJ):`ZNI+_F%+"k.*8S.,m(69gM@Pqu2J(LKqJ2+`uqAFWs\J=Eg8a$>C^
LV@kg+E0ANEGr4Fc>CEA'U;#^*-HeX'Do5+J+7=[.#)0SRhb+3[2+]A;EG(OZRcL(*sG4)hAu
AsMUa!0Mi*^\k5SUeD?UNBUeXNgJ,_;NC?$#t\&mFF/9BX=;uZq~
]]></IM>
</InnerWidget>
<BoundsAttr x="0" y="0" width="400" height="540"/>
</Widget>
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
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="5" s="0">
<O>
<![CDATA[标准收益综述]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O>
<![CDATA[ABC公司（单位 千$）]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="2">
<O>
<![CDATA[Q2/04]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="2">
<O>
<![CDATA[Q2/05]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" cs="2" s="3">
<O>
<![CDATA[变化率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="type"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<JSImport>
<![CDATA[null]]></JSImport>
<Content>
<![CDATA[var pa=parent.FR.SessionMgr.getContentPane();
pa.getWidgetByName("iframeEditor1").setValue("ReportServer?reportlet=demo/analytics/get_1.cpt&__showtoolbar__=false&type="+FR.cjkEncode(type)+"");]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$type = A3]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Scope val="1"/>
<Background name="ColorBackground" color="-4144960"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0"/>
</C>
<C c="1" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="sum"/>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[2]]></CNUMBER>
<CNAME>
<![CDATA[type]]></CNAME>
<Compare op="0">
<ColumnRow column="0" row="2"/>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[1]]></CNUMBER>
<CNAME>
<![CDATA[year1]]></CNAME>
<Compare op="0">
<O>
<![CDATA[2004]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[4]]></CNUMBER>
<CNAME>
<![CDATA[season]]></CNAME>
<Compare op="0">
<O>
<![CDATA[Q2]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[5]]></CNUMBER>
<CNAME>
<![CDATA[cate]]></CNAME>
<Compare op="0">
<O>
<![CDATA[现实]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[3]]></CNUMBER>
<CNAME>
<![CDATA[country]]></CNAME>
<Compare op="0">
<O>
<![CDATA[China]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<Content>
<![CDATA[var pa=parent.FR.SessionMgr.getContentPane();
pa.getWidgetByName("iframeEditor1").setValue("ReportServer?reportlet=demo/analytics/get_1.cpt&__showtoolbar__=false&type="+FR.cjkEncode(type)+"");]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<Expand dir="0"/>
</C>
<C c="2" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="sum"/>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[2]]></CNUMBER>
<CNAME>
<![CDATA[type]]></CNAME>
<Compare op="0">
<ColumnRow column="0" row="2"/>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[1]]></CNUMBER>
<CNAME>
<![CDATA[year1]]></CNAME>
<Compare op="0">
<O>
<![CDATA[2005]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[5]]></CNUMBER>
<CNAME>
<![CDATA[cate]]></CNAME>
<Compare op="0">
<O>
<![CDATA[现实]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[4]]></CNUMBER>
<CNAME>
<![CDATA[season]]></CNAME>
<Compare op="0">
<O>
<![CDATA[Q2]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[3]]></CNUMBER>
<CNAME>
<![CDATA[country]]></CNAME>
<Compare op="0">
<O>
<![CDATA[China]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<Content>
<![CDATA[var pa=parent.FR.SessionMgr.getContentPane();
pa.getWidgetByName("iframeEditor1").setValue("ReportServer?reportlet=demo/analytics/get_1.cpt&__showtoolbar__=false&type="+FR.cjkEncode(type)+"");]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<Expand dir="0" leftParentDefault="false" left="A3"/>
</C>
<C c="3" r="2" s="4">
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=(C3 - B3) / B3]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<Content>
<![CDATA[var pa=parent.FR.SessionMgr.getContentPane();
pa.getWidgetByName("iframeEditor1").setValue("ReportServer?reportlet=demo/analytics/get_1.cpt&__showtoolbar__=false&type="+FR.cjkEncode(type)+"");]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<Expand leftParentDefault="false" left="A3"/>
</C>
<C c="4" r="2" s="5">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart0"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象3">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart1"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[D3 >= 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" specifiedImageWidth="-1.0" specifiedImageHeight="-1.0" layout="1">
<IM>
<![CDATA[!T4,'qMA$D7h#eD$31&+%7s)Y;?-[s('"=7('k*E!!'sECc_Us"fFp[5u`*_Fd7/M;g/qrms
>A0<8#IG(;tp:(")#S$P#S*i';+\#E"BML0K`h"LXmFJceirWi2k(77e1J8/Y;qXiCGW'1D@
EUE-)LC2Nb>pOWi`:]AL4N]A\MDXTA84G?UBfqQft-GP\OMTl/CMGFQ[d!e`on.$h@"2E5^r%T
@$fAQ:83,%m;#t4W=3n5W_8;p2ol^)p\S5Y@q!@'Kt5:(%mu:n9PqBW_B.mRWDH*3D9"Q(b!
s6O5jTIlCodRniRJJm=M9gM28o'$h9Qr,I6r?";VD2\B5k,[te$8*sBZf1`d27FoNt05e1UZ
X8SH%Nm3)iZ40jkYX$bI#NW]A-V_M"D*X:TmX1h]A:mXZf7Xfto9K0;.VPm=LJp,lWGnufK]Al>
YV>f$b%ma4=\':(gu8@ojp>kHX81K)A2Q@Xf\?D<2=8-\Z'=.Ae-SNcc.mgc/5t@TRQaONKH
D"E=Uq[oMpA?g^#B&O-C49:5MgLX9cW9C<()#cWt0[rDS[f=E-(dRYGnc@d0u1*4kc,>:>l*
`ep1V"b1;^8+/f:Xl>T_J[k+s&D(NTQ!6dj'm8"Lu;FS@<rO%]A@[DN9o%,nCLcRIVCiIQ?Yh
rr<lAKK$',T-Ej4EV"`o\A@uXscl93`W&X-NT.rO-=@hpNps"0#bffWM[3B3WRN6W$oeG[m?
Bq#+pnf9Yj@+5K+LVp&q_Q]Adt.#,Y7/7WoFYclqc]A%`[BE7B)Wc32K,6FSod*&9MGg;>u/b@
1"'iHgV/e1nL/f-(Ya2cZIt+OkLuo<=(8_"1BHCf3Y(OBRuq`$ND='Ze45%]A5'cP6Ab<oUZ8
$8&lVaesq<Q2A+.G=\TMp'#T>4'4F?G]A:#W3:=>u:9:DtL\q0thi[>kB=:V&B_C,4dgVo-/?
YJ1YJA(m9:hGLohW4mf"qG*##&t=^qO5=O9QQ0Lf"6d'9cdJp+.LXZ!k3mh-R>c,'#b]ASr\5
r_RR?tRaWOVaT1/3^#7Qn/KIE5</L?L;!uJo@Ao_idZ%A1p<(dSc(p2;S-$ZX]AQH1`B^oqF,
QBNA,CPV[OV%R#UV]AXa0#k'd'jqpm5htR5BU+EZNqV84#HANIqO`g!1&si*ZM?L:^h\'!>=m
R/K&?GJ:0mCLaKO,K1dBAsU]AUYV]AZA$QUS%o99IjKLJ!^WXF5@PDoN%eU^O*jB`P;WDNQ]AKE
:orD3p^p@Vqeh+Y$K@)G4hf&TOnFe80GEn`EoIOMC`<<ek/2:Y('B[IS"B#,)$JWdO_s?^@)
sK:KZBg,"OqGO5hpBg*?k=,TT/"9<*$JC^&NZNVSMN_>p1n!B1[9Db!u`Y&#["J_h]A,O^&Ob
So2q^BaYSApB_i@B0D]A\.$puT^X`/UPF%`bl%'#O?kZTYt1N564]AjHu1%*CQ@Z79&*Cm$;t`
ZZ<6:VN;"?+^lm6T"p.p`,Y"'fEEc5(1Z=F)6hpHAoVR@j`H>)Hk^.EYP(]A/&>+MT1VQX@8^
#pbUjdKYeB]Ae066kq>WH,)3IFo5s+si>V#K8fZ@$s]Am8eU%j==1@1(7/h]A$qdaZhu61a2K-D
0[%,WpA[dT^r)JFn/u35/=))1nmc*k&@>YY`^GoX3[r`;Uh'HXI>!1#Q'$PO(%c:Zbc='P(2
8$5"0K3E&qKqtI2CWUNA$&d+%j'>!X]AEmocI^"us-=."hI%=p4#LVVQ:%uS[Pd4/=hprN$F:
(V)><F.5C@O,_/$Xj>\/PWa"A>ZQYd3i&S`i^DN^m-RdN'4dTIQ6d?F9*XJ=:N23_@YhTd]Aa
KZlU4=Z%t;kMr8`<\6Z`D-Matkkj!i`*(X2f.gh'c?4;=$"4O\=[l>A-Zp(%"#L9'&inZYKE
(uP!(fUS7'8jaJc~
]]></IM>
</Background>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[D3 < 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" specifiedImageWidth="-1.0" specifiedImageHeight="-1.0" layout="1">
<IM>
<![CDATA[!FH&(q2%pC7h#eD$31&+%7s)Y;?-[s('"=7('k*E!!'sECc_Us"suo05u`*_Fd!,.P=H>-(C
5eL_ZQ/s0jJH)<!_c>Js%<%ZrI@NH3$@K$Unp_.b'K9JO)`0:.L)%U*?F7JZ),Q:TU:bS18c
6&."@#0F8mGI/e(Te_6F?1V_U]Ap?hkAmG!$l!7B1I<t\5M/OB1WU:l&Ki(b0+XYPCVl+FCk]A
kOm;`'d2kngK?07FV]AFI/Mt&*Qa69;?]AF7dLHi]AR0(gI5)F@f"@Q+W:ZR4TA5K:k.u9q&SnP
.(#qDP3i3aQN/M)rhU&Aau,\k*ON5gebLsjc"T!SOXY[Fh6pP(6sX.&e;P1OBmSh]AY,g@VpX
gT)Mj.[<r"QOTbbrkj`)ia]AT<qf:&l.05mdH%8`dM=X&I$hC"F\dk:F:(VbHKJY0UP\0:/Q(
ItHXEsbu-n:i$mIPWJ]A2P#<JNjP8m\[en3$;-"AoBGleEpXeDpso]A",pV8R*7Bp>:^INCP^f
e5W!8"7<_5@(r_3dojVLW;kg:k+KD^PRtU]A)1lS-Yq)0)B&dr<h'$=*+lQX,\UuR2J^a)D7s
-%,49-!f'plK_<ULbVSU@9gS]AK_`q/h]A=]A`O1t!>?;heG?'7'CcF_!O<^,05+#n;JS"gGKFd
RqDD7(C?lEeD8Rd3@hI(/=9lpqWJ[T7QR\S!\"g-0&[jk,f&>H>/,4-cL0,?QMT\e3!RX#kq
g]AboL8uOO"B/FS6(_")h*B92j"HM!P.0t-O3lD=^D?R.+*V%^uX0jbLLb6I'a4&4u/^]Aeb'/
Xs$!!1YYAm4Hh[fka9!`OFAUR90U!+q*#NTo"r%Y=[2e4jK4J\)*B\rK.!@aZ0h'adkmESXG
bbL#Ul!iF@>4JKlO_PLKsnO^AP*m@#u&(:pf?UHN]A#';]A=4"^n-HUBpY3oc3,?nkM!3rG^k'
H=g?&;WJlMs3PX*5n>@PPe&^5s,A8Yr,jBd!3pc9erB>18<+?&:)dqE"WDZgr>7Dn)j)7q,Y
MVVAW#R)gs?iiB\p7eP#-Shm<a8/:p>)()g=FBVMjD@H"&j&[KUeY'R[PC=MGWU/$N.jO.GI
0eJ*;0pnq0)"=4hoH_fK1)ia(U(d=k8_:,a)qqNUPU7.R9r96K)4R@hAVrWmY*dDO>mItO19
bXjrE1ptm%fg[k%o,LA#%<&KYC/>a?o#SA7P[/:<lA7/:Jm5es>M.SVL7Xd1ER<5\t"_8i@X
#WT0_6%k_oNL^CEdLLYOFm0:P=/_H/D(lhWa"8&Cb%#Xna?%r.&6bSanTIMOEVRfBlI;fbOb
`!^AHgXaNWhCT=cOR`.5Q='E3LRSQh;g$+U>d@Q1W\p!d6;q+3^"g_^S*s^UC9u`^]A<s\F&g
UJ[#_LYqoBB5[^Ut;_ur*/p*^4U5Z)>k!mqbt%95kc]AXhupKmY?dg"SgTjG8h)erU%=J<<G/
769Z<Z0'/<8=Z6t1"li[Z&TAUfR``7mY9$-;6S)YQa\?s&UDY.,6.22Ffh7,\3VdCg<($kZo
)DI:6=*:KfgslN*?2M<G5U*d5>tLUf8gb)2FgR/uUZe_ikC3S>G58n--q!a%m8pIIRrHe5R>
,F/@!HK/tKKd-c0DK81N:.Hks:5aAlUDscqG+taeiDU.J9R0*0m+*;NCd-NS)&#*)ii'"KL4
?M<Wc.O9j/h&)0=0<.g^&qk5I""eZM_em%2Gd/@$>%j+:]Aq"i3QDZq_["PTZ5(Mbe"@-.6P1
4>(!d89oG*TD?]AK!Kc7cK`<cXe&3_:4srqK"]Ak7o2qT1Qu%'02etoBs"]A(_<IT)mnRFToP"`
C=sg'IQ\LIKd#Ya\e+fj,(\j6+^Z4G9+)OYeXLc$QS6ZBB6=9$ReuE#`#8Gp@NrqjRJf$QEQ
W(cWGQ+I#C;!t:b>r]Ap$+N8'3F5mqDDrHO2^Lj0P#16/\AYX;i1`mCr*3@jEQ(-s+h?>&kV4
Od^!LE&KNsopgQ-GZ&\-q^1Nm6>D91ra%^pX%\]AA->Mc/IKh$Q:X,_qY[6HW8PR,ada^0J3c
bt;8A':6459"M#z8OZBBY!QNJ~
]]></IM>
</Background>
</HighlightAction>
</Highlight>
</HighlightList>
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
<FRFont name="微软雅黑" style="1" size="152" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Top style="1" color="-398635"/>
<Left style="1" color="-398635"/>
<Right style="1" color="-398635"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Left style="1" color="-398635"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Right style="1" color="-398635"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[$#,##0;($#,##0)]]></Format>
<FRFont name="SimSun" style="0" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-16776961" underline="1"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Right style="1" color="-398635"/>
</Border>
</Style>
</StyleList>
<showToolbar showtoolbar="false"/>
<IM>
<![CDATA[bue[Z'A:r*I;5MYJhdT;U>c.WSmkO9[#-9[MA)WsL^&ur'I#??8=n8S'F'g,T@etZaMS&eU^
/b7KHL:0AI,W$.L9H(K6\1eqO.>/4dTr,h]AFD4WdF1pHgeSm\Ht?FBV33U`?eu&@M3;M9ob*
f/QaYQ2Qu;Uq<W%b>:<ri.Zg(?Y2atjr@PP/cI163mZuP<>UDX*gqU@DIgeO;l8irBWBXe)R
f&W'o:.t^]A]AV.*hLDjd[V=jbUrj:/J<%L9mE;`VJUqU><1_:*1%%%`<>ujYDjTLPh2,WmK5W
h41jZ&A:tdulo[E\t&"%]ALlg3hs!OOt*D/JgM]Ahp%r0O*Lf]A6bAa?;F3%m`$&?.-a?[S@nb/
\d/,gn5+qWg+e2C"*cUm.784UKL:lF?&/!A5Bc*,<DJg'1_'c3Z2dAWG+Ym$^8u_fRV#)clt
mXNGQ8K%[@2^$aRnN@UpDip4TYL]AR?a#G*u7r3G*.46WR-0_X'#dL[;L:[D3s>B-Fc.1^FXG
^<N%uhScP%HQel\8eWHe1J9,H5[E`#m&Q$hP7rLGGVs-EKO!"H3;p[6!2un\!or5nV6m%0<3
aH=4#Ph*K!<)d5+kl!ZQWD)Qdk`QLJsj?lR<4Jk(JjBI!0)JSdf^MB6'o(J.Z#*1o/6oMb5h
E7L<KcM_1d(h)g^N;M3I$On#85ANUDXccecIi47YSRB]A,k-[\VF>qo)Gf>ogh<hirnXqUVGd
p(d=)"R.p_nMJD'88N+ck+H"QKjq*CONHRH30H$->Ejk2.&aUIXmU@-\.s>Y</u:[\YF,PV5
^Gn<l%'Vr^pBpIsV!GSMKt(0E%43>;L+eQ<X]APUj4kg>1]A'p1JjCm=/Nu?QL;"VR7!#qW2D2
g@o?GbA.Rma"p)EAOHp"$T@bm/G=On_>9q;l-_gl8hG)dPcbM8Bd'X`5<6Zc=pB1tKUn)&a=
]A"n##ktj^?^_Y+%oQ<'l&_(<;eP!:,K&2pA>5b%Jg\PPq4)!,mePB1(q6KRr]A=u,HS(MrF-I
;VD!\-@Cc^*]As8I_gS^VA?7dDPWnR;oZZKY#<3a-/TDLi4C<c%E3f,/>1r7H*]Ae+XrbnKcC1
2N!.hEK?ELZE7n6f;*=ngP<'u,p!JK#48i`@'dku]A[#>!1HR_3eEWh,nq)cY`ZJ32%!A;@,U
X)TM3cL5q<Ed3!oR<c4&'';Q/L>99,ljZ@%Eg<Oi"QNgf$pc:0jh#>\<%X7RP;OAg!/AmSNJ
7+"Q6I/tpe6"*`f=B4NA?K3#OR#Gr]A`6T?G]A"u*Qcj*h;fRZEcX!:=u?`a=FA&KU?\p/1\F=
qKKT)R1uXG36mV6R:!)'/t"`>1O(Zm'N#3oE2WY7fDfon[<Cj);lda!K"$%d;$dPjj(Fso&c
'=+EPV118J%+UR>+SVUf`EU1aO+;eN;Kl?YGi7WDG`l@iouDX2?@4Sc;+1]Ao&ZPWX\HA,r2N
?0)+(q6RP5fVG-RC7JC2U7n=#Rk]AK.5\$'Zfpg_Ciq5qP=kgRLT+Dsm+L]AJ?=*hcX]AYqm^.k
GcTeWkob6O]A_cgV]AEgA(OWjWTV#.DdMa.PNV:o&l.oO@HH[,.BWIZpIj@sk\80O"$j!XANX\
$A'q8I\^TS&alTd3V1"qHA<K_,N%MU?lM>O]A.:%mcW`MDYVR[1tSN@)K=i/hUg8Ytp=??qS8
WC6,Qj?<g<4?Hl<>oI_0R[#AV!4ElW;tR1_m5J0AfoP4R2Q6.HF`c^=[9UG9XDq!U!2E2Do.
h#FI(@jOU%9]AJ<"bO&^C!YY,)a2mmu_F8$$a>[@XEJNF\.aDfmZ&B;:5cNYUcjDua-FCA#4-
\q_!#C8J'Eqq"MA729sQ2TP0:@(1pOQJh\R8;0iJOX-7;mHLLJ379p7q0GWFk1sY\nH?UpRY
le<]A!A/%E%<QTXBPg;4j=M#h0Vm+>>qVNlJ;!!(X/TYR$`JT2q/e"Z'F5@$Ed+)2m1gtf[RE
D+K]AMOm_aD&jb%8.j"3:i^<!aQD1T<W::6N[9FkCa')bRkO22-ge%4K"LtM.8QjIKsA,53-P
n,:(S3OH,(L9Gi?<hA0nQ(_B1;<4j`HqEoO83'QAX7\g<O-L2r)=Dt^_;-o4dd;1.#@Wl$ui
*bi)^1C/ZfS1#@$^sGg14N[5Ym(lN!>g4.9r=eN.Y:5GYgf41tZh6aK-eIt,4+`)__IcP)t>
[TdU@qCQZmmFjP^Km_I`1o`uF^?UCImk[iFjRJR_;(6H3WGdT[Oqs428^#:(PG?->48+;810
UpD(*kR(4knZ&9Bah,\QSepXJ^<:I9.q2QIR8`Ud\`>374o&Fm9@9%)aSQD/ORF7WMf^g4-+
3-;)M14bE7Wr7msgW7to^lgB$l>GV6TI5=n>bIh@XHrkuJ0+AUD<R9q))EiKAF:#!",I:\?W
5%eULOiV,$s/qDn>=IUNNspJ/9#pZRq).:*=V&g`VJ#nI5j!/o?<>&2>;'fU*)CQ@eg>TZoc
<sQDq>G:n0BMO_9p58K4TMbe8EdX$?]A!+9_C?R#s'U)a(R'[G#31[0-kIM6c.?O3[EE`/^>d
cmX7_ld8i]AlY8#b?Sr:FG!FM[PsnJnTru3lP_p("Y?B9`GX<_"`Y3IdZBPN5I41p?PoThI$q
c&5DabBs_Sl'D?8f8HT.RR-:^=[g>$GdKQ4]Aa\HZ\0<7[>lB::&M.(n4S=EU(nuD/YU1/B-D
T9PQMX?s[1`RmS_J)slhdRI3sW9:$LXSFaId78l/0MCiF5g8mY_S-V9;\qQ_1bCKnA3oNYLD
6Tn/6sm`C&:H1?*<4FG!:UfOUFTtYi$T%FU=3cnAL=O]AYQW.D3/(\JZAUQB3R2':`N[?toJV
r8j?DHq4MK5%]AB1[mR0?A8g2H:9IIM]AZRlU^Yq]ApZ6]AZ6J,C5P[?a&OO0NF<`%S,3*P;.\"!
%^t3Z,2@E$1AK;?%aXH]ATHrjNX0*!oRcn\\_TFLERbIP<<sG&>q_UBdRfg$nXpUf\pZo0;lB
p@i\jK#cM?2)mH@E#Y6W`4"efi0Q'o,_q`TAZ(KeU/HQd-=RRjcA5=3E+4Q,=.)Al`XHla[V
%Jocb#l<<`:#2YQo2dFg_#2klIS]A3M3aW'Y6a`_\sYm'f`dGlWL>SW"PoE\[&ZoA0U^@1#E2
o76f@nCaZ*0b^2V*F]A(H_dfu`rbT,_`j6)UKeOAg_=)3&`t^nYCWW?$D+%KiA'm@c!H(<15Y
/*cK9s8B4*0eIX$2ZKtHe;Pgbkh)`R!F-dWMuSng>qashB6m2+e2r;V7.cCJY/d.]AS0l"XuJ
K[P>d8#(ib).t;l^]A7PF8c5+@+CG_E-?jZ?cFi<hWHA)3atSN2@eGhDi(77NGa_bo-hg(XOA
ZXq$/obf4tjXF/D#`J*4cH+b"NWdGZk)U"^G9q8i$OY:Mm!LKTQhI0*<3Bi*d`1#k7=Cb/L-
BUB'&iXec@7OuOO/DqeLA</p7'5N%g!Pn5gdGaag=J-Yk`l4\C\Sf<B<!mk]AYN(:\b8mIZ=`
emPkm,4Q_*[qfV=kt@;1'A3@#NBIl<;F/&_qhm$-2p/N,.[9Aeib^Kf2"i!NS#/DC&jc-Fb)
>+[^e[L5\Q5TAN87aYkRcb4HCja:XEeKHbKh,8^J>n@BO,mD=A`/'rFRPo?>XmOSH"&<TBR2
g,J<A^/]A09mc!e.Hn]AC@%0,AtqC>PRna5p\QB:LPTA6ps0s%@jMo\Y\"VF8WdMG=?N-@A2.U
B(43=0>?a3N_bq>r5pHlr/h(0Y)?N28XE//5cK0O),A+YXdrPe$-KVC)MIU#+ko,V8`S;AoJ
uh#!Las,T#DjnL4SPk0m9.>e&QZsh(LPaN>+C[rXV7%BD!.8i@%.sFVcB5<B*/aZ3^lAZeiP
BRd4#JT<q]A,[L'iZa6fiAa8oUY.T#G9p'*]Akc<NBbS5%]ApCM+oM-]A)o-k"[VXK"r@''a&:f(
0^16aGF')M+VA"pL.1Y`K!^E'up-Adjr]A\8!(JeN>Iads+>Ps05+^0n*R'K#Sg7L0@SUB83q
Mt77tA!hFZaj`TEM@\2?p$-R%jQ)@thG=AHVVl08%XsBr)h`VYf*]ARmIF)-2!2jdhT4,oj>j
+u<o)b4f\R&=o8<4;1i6Si:O*9_L]AIa)>f'4o.-+LeIMu7jm1n?l<e^:kV;S9@Urq`u.UCPI
#l)s.$FYI*@[_c1tUlqX.NC`Q@'+C2'W8t&=Yt8'--&YejG+W^4X.LB9,Dg&)@la2V`ffs&I
WM:XQ2[<l6dP:K\!pREY/WVW%Sek*"l*ps)i-U%m"4tCp%co\A307dT27s'=Fs"hC[j_3;^b
pk5fg+3TXSeR6)P;9"^Oie(U^V.Ou1N4JDj+OP5?YZhUcuh6BN*6Co40)P3:SLcl`"'Ij`(<
HP&Cf]Aj\GK?ec=M<>G65*@n</$[&fpiR"@`?)%k&![SI+2qQhh\:K-%O5g&";g'Yu:-95NEE
gI>qUfb#crVQF(N&FcHEe*j:DD;"Ugc<3R:d]ADkl3W1+sL<$To(O<_FE2T6e*he.;Zh%Zr=$
bX;>kD=8*G4q7+1+EoGq>[la^]A#6#9C7U;q<mmqW$RM[X*(=Ki6nhB5,5ArC/X04r@8@676@
I3WiMZliQ`kPjC_5\)/REl"i/62tZYeuJ]A#_/Oh=TI&3Wc5:mjmsrLWX5XDPS@$),O+ZrmUO
ZHFXK0\2EV^3GC0QJHVq#Iod@>dZVZie&TU2d&o'?2<gbNPeBG[pC3:deQg_GM)TZba_PiZs
/:5e!L:+h=dC[K9Kn;CEN=oX(=/\`A0XbGsi)Fki5bIsDK6[:3l?mI&M;Kk$;k3X6Xrr9!e[
l:p4V#;"10;$mMqj*6s):QcEBA`XUDa'(.=>`%Es2d+(^_QR(2W>u:Vl9d"YCNOnR't=c^&s
JM^8SI1=lmmD]AA^`k&7+,$OZlMq>j<L:lCgVb8>QFT3.._hM15\mRMnF'<=?5Jc>qb/!L`>R
BGVkPXU^,$Ci.U']Aa1ld,V%kq<G3X!Y9UtAkDUZF#p/c\91/kOSj7p$Ja!c;THEqk\UX/i##
JXB(k!:!.cl2:h->;6336q5>_.fVsFZ[=l0VAf$.N03>9[MKr[=8%<OcPL_hm+dP*TAR,I1E
/#fR((AcQ4o</C,D"N"O:1BaO-a=//9&_Q0maSNoU:T2-Gesd!G(s0[5DM>mpg+Cq"iF:OkD
`h%D'jXX+X`!SZb;,f;KnbInm;~
]]></IM>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="400" height="540"/>
</Widget>
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
<border style="1" color="-723724" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[收益和损失]]></O>
<FRFont name="微软雅黑" style="1" size="112" foreground="-4492536"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-398634"/>
</WidgetTitle>
<Background name="ColorBackground" color="-398634"/>
<Alpha alpha="0.99"/>
</Border>
<Background name="ColorBackground" color="-398634"/>
<LayoutAttr selectedIndex="0"/>
<Chart name="默认">
<Chart class="com.fr.chart.chartattr.Chart" options="{}" customChartID="" wrapperName="FineChart" requiredJS="" chartImagePath="">
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
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=if(len($type)=0,"纯收入",$type)+"的收益和损失"]]></Attributes>
</O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="96"/>
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
<AxisLineStyle AxisStyle="1" MainGridStyle="0"/>
<newLineColor mainGridColor="-921103" lineColor="-5197648"/>
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
<OneValueCDDefinition seriesName="cate" valueName="sum" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds3]]></Name>
</TableData>
<CategoryName value="season"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
</InnerWidget>
<BoundsAttr x="400" y="36" width="560" height="196"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_chart0"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[收益和损失]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="1" size="112" foreground="-4492536"/>
<Background name="ColorBackground" color="-398634"/>
<border style="1" color="-723724"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="80" height="21"/>
</Widget>
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
<Chart class="com.fr.chart.chartattr.Chart" options="{}" customChartID="" wrapperName="FineChart" requiredJS="" chartImagePath="">
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
<Plot class="com.fr.chart.chartattr.PiePlot">
<Plot>
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
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}${PERCENT}"/>
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
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
</AttrList>
</ConditionAttr>
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
<PieAttr subType="1" smallPercent="0.05"/>
</Plot>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="country" valueName="sum" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
</body>
</InnerWidget>
<BoundsAttr x="400" y="0" width="560" height="270"/>
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
<border style="1" color="-723724" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[百分比]]></O>
<FRFont name="微软雅黑" style="1" size="112" foreground="-4492536"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-398634"/>
</WidgetTitle>
<Background name="ColorBackground" color="-398634"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-398634"/>
<LayoutAttr selectedIndex="0"/>
<Chart name="默认">
<Chart class="com.fr.chart.chartattr.Chart" options="{}" customChartID="" wrapperName="FineChart" requiredJS="" chartImagePath="">
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
<O t="Formula" class="Formula">
<Attributes>
<![CDATA[=if(len($type)=0,"纯收入",$type)+"的百分比"]]></Attributes>
</O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="96"/>
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
<Attr meterAngle="360" maxArrowAngle="360" units="" order="0" designType="1" tickLabelsVisible="true" dialShape="1" isShowTitle="false" meterType="0" startValue="=0.0" endValue="=180.0" tickSize="=30.0"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80"/>
</Attr>
</TextAttr>
<valueTextAttr>
<TextAttr>
<Attr alignText="0">
<FRFont name="Century Gothic" style="1" size="96" foreground="-11612081"/>
</Attr>
</TextAttr>
</valueTextAttr>
<unitTextAttr>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96"/>
</Attr>
</TextAttr>
</unitTextAttr>
<IntervalList>
<MeterInterval label="" backgroudColor="-420752" beginValue="=0.0" endValue="=50.0"/>
<MeterInterval label="" backgroudColor="-1183654" beginValue="=50.0" endValue="=100.0"/>
<MeterInterval label="" backgroudColor="-11612081" beginValue="=100.0" endValue="=150.0"/>
<MeterInterval label="" backgroudColor="-10502712" beginValue="=150.0" endValue="=200.0"/>
</IntervalList>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="1" areaNumber="4" mainColor="-16776961"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=200.0" maxValue="=150.0" color="-10502712"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=150.0" maxValue="=100.0" color="-11612081"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=100.0" maxValue="=50.0" color="-1183654"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=50.0" maxValue="=0.0" color="-420752"/>
</AreaColor>
</ColorList>
</MapHotAreaColor>
</MeterStyle>
</MeterPlot>
</Plot>
<ChartDefinition>
<MeterTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<MeterTable201109 meterType="1" name="type" value="百分比"/>
</MeterTableDefinition>
</ChartDefinition>
</Chart>
</Chart>
</InnerWidget>
<BoundsAttr x="400" y="36" width="560" height="196"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_chart1"/>
<WidgetAttr>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[百分比]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="1" size="112" foreground="-4492536"/>
<Background name="ColorBackground" color="-398634"/>
<border style="1" color="-723724"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="80" height="21"/>
</Widget>
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
<Chart class="com.fr.chart.chartattr.Chart" options="{}" customChartID="" wrapperName="FineChart" requiredJS="" chartImagePath="">
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
<BarAttr isHorizontal="false" overlap="-0.25" interval="1.0"/>
<Bar2DAttr isSimulation3D="false"/>
</CategoryPlot>
</Plot>
</Chart>
</Chart>
</body>
</InnerWidget>
<BoundsAttr x="400" y="270" width="560" height="270"/>
</Widget>
<Sorted sorted="false"/>
<WidgetZoomAttr compState="0"/>
<Size width="960" height="540"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
<Widget widgetName="chart0"/>
<Widget widgetName="chart1"/>
</MobileWidgetList>
</Center>
</Layout>
<DesignerVersion DesignerVersion="IAA"/>
<PreviewType PreviewType="0"/>
</Form>
