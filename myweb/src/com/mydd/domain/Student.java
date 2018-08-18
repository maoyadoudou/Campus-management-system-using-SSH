package com.mydd.domain;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class Student {
	//主键
	private Long student_id;
	//加密后的登陆密码
	private String student_password;
	//设置密码特定salt值
	private String student_salt;
	//登陆人职位
	private String student_position;
	//用户状态，0表示账户已不可用，1表示可用
	private String student_state;
	//姓名
	private String student_name;
	//工号
	private String student_number;
	//出生日期
	private String student_birthday;
	//性别
	private String student_sex;
	//联系电话
	private String student_phone;
	//电子邮箱
	private String student_mail;
	//头像图片保存路径
	private String student_avatar;
	
	// Hibernate框架默认集合是set集合。
	// @JSONField 注解不把set集合进行json的转换
	@JSONField(serialize=false)
	private Set<Course> courses = new HashSet<Course>();
	
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public Long getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}
	public String getStudent_password() {
		return student_password;
	}
	public void setStudent_password(String student_password) {
		this.student_password = student_password;
	}
	public String getStudent_salt() {
		return student_salt;
	}
	public void setStudent_salt(String student_salt) {
		this.student_salt = student_salt;
	}
	public String getStudent_position() {
		return student_position;
	}
	public void setStudent_position(String student_position) {
		this.student_position = student_position;
	}
	public String getStudent_state() {
		return student_state;
	}
	public void setStudent_state(String student_state) {
		this.student_state = student_state;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getStudent_number() {
		return student_number;
	}
	public void setStudent_number(String student_number) {
		this.student_number = student_number;
	}
	public String getStudent_birthday() {
		return student_birthday;
	}
	public void setStudent_birthday(String student_birthday) {
		this.student_birthday = student_birthday;
	}
	public String getStudent_sex() {
		return student_sex;
	}
	public void setStudent_sex(String student_sex) {
		this.student_sex = student_sex;
	}
	public String getStudent_phone() {
		return student_phone;
	}
	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}
	public String getStudent_mail() {
		return student_mail;
	}
	public void setStudent_mail(String student_mail) {
		this.student_mail = student_mail;
	}
	public String getStudent_avatar() {
		return student_avatar;
	}
	public void setStudent_avatar(String student_avatar) {
		this.student_avatar = student_avatar;
	}
	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", student_password=" + student_password + ", student_salt="
				+ student_salt + ", student_position=" + student_position + ", student_state=" + student_state
				+ ", student_name=" + student_name + ", student_number=" + student_number + ", student_birthday="
				+ student_birthday + ", student_sex=" + student_sex + ", student_phone=" + student_phone
				+ ", student_mail=" + student_mail + ", student_avatar=" + student_avatar + "]";
	}

}
