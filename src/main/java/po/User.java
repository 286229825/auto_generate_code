package po;

import java.sql.Date;

public class User {

	//编号
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	//姓名
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//年龄
	private Integer age;
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	//性别
	private String sex;
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	//职业
	private String job;
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	//生日
	private Date birthday;
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	//权限
	private Integer authorityId;
	public Integer getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}
	
	
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
	public User(Integer id, String name, Integer age, String sex, String job, Date birthday, Integer authorityId,
			Integer startRow, Integer rows) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.job = job;
		this.birthday = birthday;
		this.authorityId = authorityId;
		this.startRow = startRow;
		this.rows = rows;
	}
	public User() {
		super();
	}
	
}
