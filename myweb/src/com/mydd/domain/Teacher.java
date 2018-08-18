package com.mydd.domain;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class Teacher {
	//主键
	private Long teacher_id;
	//加密后的登陆密码
	private String teacher_password;
	//设置密码特定salt值
	private String teacher_salt;
	//登陆人职位
	private String teacher_position;
	//登陆状态，0表示未登录，1表示处于登陆状态
	private String teacher_state;
	//姓名
	private String teacher_name;
	//工号
	private String teacher_number;
	//出生日期
	private String teacher_birthday;
	//性别
	private String teacher_sex;
	//联系电话
	private String teacher_phone;
	//电子邮箱
	private String teacher_mail;
	//头像图片保存路径
	private String teacher_avatar;
	
	// Hibernate框架默认集合是set集合。
	// @JSONField 注解不把set集合进行json的转换
	@JSONField(serialize=false)
	private Set<Curriculum> curriculums = new HashSet<Curriculum>();
	
	public String getTeacher_avatar() {
		return teacher_avatar;
	}
	public void setTeacher_avatar(String teacher_avatar) {
		this.teacher_avatar = teacher_avatar;
	}
	public Long getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(Long teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getTeacher_password() {
		return teacher_password;
	}
	public void setTeacher_password(String teacher_password) {
		this.teacher_password = teacher_password;
	}
	public String getTeacher_salt() {
		return teacher_salt;
	}
	public void setTeacher_salt(String teacher_salt) {
		this.teacher_salt = teacher_salt;
	}
	public String getTeacher_position() {
		return teacher_position;
	}
	public void setTeacher_position(String teacher_position) {
		this.teacher_position = teacher_position;
	}
	public String getTeacher_state() {
		return teacher_state;
	}
	public void setTeacher_state(String teacher_state) {
		this.teacher_state = teacher_state;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getTeacher_number() {
		return teacher_number;
	}
	public void setTeacher_number(String teacher_number) {
		this.teacher_number = teacher_number;
	}
	public String getTeacher_birthday() {
		return teacher_birthday;
	}
	public void setTeacher_birthday(String teacher_birthday) {
		this.teacher_birthday = teacher_birthday;
	}
	public String getTeacher_sex() {
		return teacher_sex;
	}
	public void setTeacher_sex(String teacher_sex) {
		this.teacher_sex = teacher_sex;
	}
	public String getTeacher_phone() {
		return teacher_phone;
	}
	public void setTeacher_phone(String teacher_phone) {
		this.teacher_phone = teacher_phone;
	}
	public String getTeacher_mail() {
		return teacher_mail;
	}
	public void setTeacher_mail(String teacher_mail) {
		this.teacher_mail = teacher_mail;
	}
	public Set<Curriculum> getCurriculums() {
		return curriculums;
	}
	public void setCurriculums(Set<Curriculum> curriculums) {
		this.curriculums = curriculums;
	}
	//打印中没有Set<Curriculum>对象
	@Override
	public String toString() {
		return "Teacher [teacher_id=" + teacher_id + ", teacher_password=" + teacher_password + ", teacher_salt="
				+ teacher_salt + ", teacher_position=" + teacher_position + ", teacher_state=" + teacher_state
				+ ", teacher_name=" + teacher_name + ", teacher_number=" + teacher_number + ", teacher_birthday="
				+ teacher_birthday + ", teacher_sex=" + teacher_sex + ", teacher_phone=" + teacher_phone
				+ ", teacher_mail=" + teacher_mail + ", teacher_avatar=" + teacher_avatar + "]";
	}
}
