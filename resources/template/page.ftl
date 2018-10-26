<#setting number_format="0">
<div class="panel-page">		
	<div class="l-panel-bbar-inner">
		<#if (showPageSize)>
		<div class="l-bar-group l-bar-selectpagesize">
			每页记录&nbsp;<select id="pageSize" name="pageSize" onchange="changePageSize(this,'${tableIdCode}');" class="select_short">
				<option value="5" <#if pageBean.pageSize==5> selected="selected" </#if>>5</option>
				<option value="10" <#if pageBean.pageSize==10> selected="selected" </#if>>10</option>
				<option value="15" <#if pageBean.pageSize==15> selected="selected" </#if>>15</option>
				<option value="20" <#if pageBean.pageSize==20> selected="selected" </#if>>20</option>
				<option value="50" <#if pageBean.pageSize==50> selected="selected" </#if>>50</option>
				<option value="100" <#if pageBean.pageSize==100> selected="selected" </#if>>100</option>
			</select>
		</div>
		</#if>
		<div class="l-bar-separator"></div>
		<div class="l-bar-group">
			<div class="l-bar-button l-bar-btnload">
				<a href="javascript:window.location.reload()">
					<span class=""></span>
				</a>
			</div>
		</div>
		<#if (showExplain) >
			<div class="l-bar-separator"></div>
			<div class="l-bar-group" >
				<span class="l-bar-text">显示记录从${pageBean.first+1}到${pageBean.last}，总数 ${pageBean.totalCount}条</span>
			</div>
			
		</#if>
	<div class="l-bar-group-right">
		<div class="l-bar-separator"></div>
		<div class="l-bar-group">
			<div class="l-bar-button l-bar-btnfirst">
				<a href="javascript:;" onclick="first('${tableIdCode}')" title="首页">
					<span class=""></span>
				</a> 
			</div>
			<div class="l-bar-button l-bar-btnprev">
				<a href="javascript:;" onclick="previous('${tableIdCode}');" title="上一页">
					<span class=""></span>
				</a>
			</div>
		</div>
		<div class="l-bar-separator"></div>
		<div class="l-bar-group">
			<span class="pcontrol"> 
				<input size="4" value="${pageBean.currentPage}" style="width: 20px;text-align:center" maxlength="3" class="inputText pageInput" type="text" tableidcode="${tableIdCode}" id="navNum${tableIdCode}" name="navNum"/>/ <span>${pageBean.totalPage}</span>
			</span>
		</div>
		<div class="l-bar-separator"></div>
		<div class="l-bar-group">
			<div class="l-bar-button l-bar-btnnext">
				<a href="javascript:;" onclick="next('${tableIdCode}')" title="下一页">
					<span></span>
				</a>
			</div>
			<div class="l-bar-button l-bar-btnlast">
				<a href="javascript:;" onclick="last('${tableIdCode}')" title="尾页">
					<span></span>
				</a>
			</div>
		</div>
		<div class="l-bar-separator"></div>
		<div class="l-bar-group">
			<span>
				<input type="button" id="btnGo" value="GO" class="btn-go" onclick="jumpTo('${tableIdCode}');"/>
			</span>
		</div>
	</div>		
	<div class="l-clear"></div>
		<input type="hidden" id="currentPage${tableIdCode}" name="currentPage" value="${pageBean.currentPage}"/>
		<input type="hidden" id="totalPage${tableIdCode}" name="totalPage" value="${pageBean.totalPage}"/>
		<input type="hidden" id="oldPageSize${tableIdCode}" name="oldPageSize" value="${pageBean.pageSize}"/>
		<a id="_nav${tableIdCode}" href="${baseHref}" style="display:none" ></a>
	</div>
</div>
