<#setting number_format="#">
<#escape x as x!"">
<div class="desktop">
<ul>
<#list model as data>
<li>
<span align="right"><b>
<#if data.messageType == "1">
个人信息
</#if>
<#if data.messageType == "2">
日程安排
</#if>
<#if data.messageType == "3">
计划任务
</#if>
<#if data.messageType == "4">
系统信息
</#if>
<#if data.messageType == "5">
代办提醒
</#if>
</b>
</span>
<a href='javascript:window.top.showReadMsgDlg(${data.id})' class='contenttw'>
${data.subject}</a>
</li>
</#list>
</ul>
</div>
</#escape>