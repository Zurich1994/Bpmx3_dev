<div class="infobox-container">
	<#list data as d>
      <div class="infobox infobox-${d.color}" <#if d.url !=''> data-url="${d.url}"</#if>>
      		<#if d.type  ==0>
				<div class="infobox-icon">
					<i class="ht-icon fa ${d.icon}"></i>
				</div>
			<#elseif d.type  ==1>
              <div class="infobox-progress">
                <div class="easy-pie-chart percentage" data-percent="${d.data}" data-size="46">
                  <span class="percent">${d.data}</span>%
                </div>
              </div>
              <#elseif d.type  ==2>
                <div class="infobox-chart">
                  <span class="sparkline" data-values="${d.data}"></span>
                </div>
			</#if>

			<div class="infobox-data">
				<span class="infobox-data-number">${d.dataText}</span>
				<div class="infobox-content">${d.dataContent}</div>
			</div>
			<!-- #infobox  -->
			<#if d.statType ?exists>
				<#if d.statType == 1>
					<div class="stat <#if d.statStatus == 1>stat-success<#else>stat-important</#if>">${d.statData}</div>
				<#elseif d.statType == 2>
					<div class="badge <#if d.statStatus == 1>badge-success<#else>badge-important</#if>">
						${d.statData}
						<i class="ht-icon fa fa-arrow-up <#if d.statStatus == 1>fa-arrow-up<#else>fa-arrow-down</#if>"></i>
					</div>
				</#if>
			</#if>
		</div>
	</#list>
</div>
