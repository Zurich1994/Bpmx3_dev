<#setting number_format="#">
<div class="desktop">
<ul>
<#list model as data>
<li>
<span align="right"><b>(${data_index+1})</b></span>
  <a href="javascript:;" onclick="javascript:jQuery.openFullWindow('${ctxPath}/platform/bpm/task/startFlowForm.ht?defId=${data.defId}')" class='contenttw'>${data.subject}</a>
</li>
</#list>
</ul>
</div>
