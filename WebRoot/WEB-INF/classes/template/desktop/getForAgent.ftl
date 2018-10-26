<div class="desktop">
<ul>
<#list model as data>
<li>
<span align="right">事项名称</span>
<a   href='#'  onclick="javascript:jQuery.openFullWindow('${ctxPath}/platform/bpm/task/toStart.ht?taskId=${data.id}')"
 class='contenttw'> ${data.subject}</a>
</li>
</#list>
</ul>
</div>