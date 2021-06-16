package cn.edu.usts.cs2019.entity;

import java.util.Date;

public class User {

	private int id;
	private String name;
	private String password;
	private String telphone;
	private String address;
	private Date birthday;
	private String dept;            // 部门

	//省略setter/getter方法


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public User() {
	}

	public User(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public User(int id, String name, String password, String telphone,
                String address, Date birthday, String dept) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.telphone = telphone;
		this.address = address;
		this.birthday = birthday;
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "[" + id + ", " + name + ", " + password + ", " +
				telphone + ", " + address + ", " + birthday + ", " +
				dept + "]";
	}
}
 
