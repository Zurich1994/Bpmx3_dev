<#assign fields=table.columnList>

<div style="display:none">
&lt;%@ Page language="c#" %&gt;
&lt;%@ Import Namespace="System.Data" %&gt;
&lt;%@ Import Namespace="System.Data.SqlClient" %&gt;
&lt;%
String pk=Request["${pkName}"];
String sql="Select * from ${table.name} where ${pkName}='" + pk +"'"; 
SqlConnection conn = new SqlConnection(@"Data Source=192.168.1.112;Initial Catalog=bpm;UID=sa;PWD=sasa;Max Pool Size=512;");
SqlDataAdapter  myAdapter= new SqlDataAdapter(sql, conn);
SqlCommandBuilder myBuilder = new SqlCommandBuilder(myAdapter);
DataSet dataSet = new DataSet();
myAdapter.Fill(dataSet);
int count=dataSet.Tables[0].Rows.Count;
if(count>0)
{
	DataRow row = dataSet.Tables[0].Rows[0];
%&gt;
</div>

		
		<table cellpadding="0" cellspacing="0" border="0" style="width:800px">
			<tr>
				<td colspan="4" align="center"><h1>${table.comment }</h1></td>
			</tr>
			<#assign index=0>
			
			<#list fields as field>
			
				<#if index % 2 == 0>
				<tr>
				</#if>
					<td align="right" width="15%" style="padding:2px;">${field.comment}:</td>
					<td width="35%" style="padding:2px;">
						&lt;%=row["${field.name}"]%&gt;
					</td>
					<#if index % 2 == 0 && !field_has_next>
					<td width="15%"></td>
					<td width="35%"></td>
					</#if>
				<#if index % 2 == 1 || !field_has_next>
				</tr>
				</#if>
				<#assign index=index+1>
			
			</#list>
		</table>
	 
<div style="display:none">
&lt;%}%&gt;
</div>
