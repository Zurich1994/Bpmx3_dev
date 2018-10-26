<div class="widget-box border " >
	<div class="widget-header">
		<h4 class="widget-title"><i class="ht-icon fa fa-bars"></i>${model.title}</h4>
		<div class="widget-toolbar">
			<a href="javascript:void(0);" data-action="more" data-url="${model.url}" data-title="${model.title}"  data-placement="bottom" title="更多">
				<i class="ht-icon fa fa-list-alt"></i>
			</a>
			<a href="javascript:void(0);" data-action="reload" data-placement="bottom" title="刷新">
				<i class="ht-icon fa fa-refresh"></i>
			</a>
			<a href="javascript:void(0);" data-action="collapse"  data-placement="bottom" title="折叠">
				<i class="ht-icon fa fa-chevron-up"></i>
			</a>
		</div>
	</div>
	<div class="widget-body">
		<div  class="widget-scroller" data-height="${model.height}px">
			<#if data?exists> 
			<ul class="widget-list list-unstyled"  >
					<#list data as data>
							<li class="clearfix" onclick="javascript:jQuery.openFullWindow('${ctx}/platform/bpm/task/toStart.ht?taskId=${data.id}')">
								<div class="pull-left">
									<p>
										</p><h5><strong> ${data.subject}</strong></h5>
								<p></p>
                                  <p><i class="fa fa-clock-o"></i> <abbr class="timeago" title="">${data.createTime?string("yyyy-MM-dd HH:mm:ss")}</abbr></p>
																								
							</div>
							<div class="text-right pull-right">
								<h4 class="cost"> ${data.creator}</h4>
								<p>
									<span class="label label-success arrow-in-right"><i class="fa fa-check"></i> 待办</span>
								</p>
							</div>
						</li>
				</#list>
			</ul>
			<#else>
				<div class="alert alert-info">当前没有记录。</div>
			</#if>
		</div>
	</div>
</div>