package ${packageName};

<#list needPackage as np>
${np}
</#list>

public class ${tableName} {

	<#list fields as field>
	//${field.annotation}
	private ${field.type} ${field.changedName};
	public ${field.type} get${field.changedName?cap_first}() {
		return ${field.changedName};
	}
	public void set${field.changedName?cap_first}(${field.type} ${field.changedName}) {
		this.${field.changedName} = ${field.changedName};
	}
	
	</#list>
	
	//分页条件
	private Integer startRow;
	private Integer rows;
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
}
