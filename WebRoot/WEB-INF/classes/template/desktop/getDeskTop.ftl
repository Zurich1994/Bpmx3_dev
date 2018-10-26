<#setting number_format="#">
<#if columns??>
<table style="width:100%;">	
	<tr layoutId="${layoutId}">	
	  <#list columns as column>
	  	<td  width="${widthMap[column]}%" valign="top">
	  	  	<div id="columns">
		  	  	<ul class="column" id="column${column}">
	  	  		<#if datas[column]??>
			  	  	<#list datas[column] as data>
				  	  	<li class="widget color-blue" columnId="${data.columnId}">
					  	    <div class="widget-head">
					  	       <h3 style="display:inline">${data.columnName}</h3>
						  	       <span id="more">
							  	       <#if data.columnUrl??>						  	       
							  	        	<a class="more" href="javascript:getMore('${data.columnUrl!"#"}');">更多</a>
							  	       </#if>						  	       
						  	      </span>
					  	    </div>
					  	    <div class="widget-content">
					  	    	${data.columnHtml}
					  	    </div>
				  	    </li>
			  	    </#list>
		  	    </#if>
		  	    </ul>
	  	    </div>
	  	</td>
	  </#list>
	</tr>
</table>
<#else>
没有设置栏目数据
</#if>