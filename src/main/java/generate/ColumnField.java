package generate;

/** 表字段封装类
 * @author Administrator
 *
 */
public class ColumnField {
	//字段名
	private String name;
	//去掉下划线，并将下划线右边的字母大写后的字段名
	private String changedName;
	//数据类型
	private String type;
	//字段注释
	private String annotation;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChangedName() {
		return changedName;
	}
	public void setChangedName(String changedName) {
		this.changedName = changedName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
}
