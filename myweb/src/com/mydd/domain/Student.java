package com.mydd.domain;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class Student {
	//����
	private Long student_id;
	//���ܺ�ĵ�½����
	private String student_password;
	//���������ض�saltֵ
	private String student_salt;
	//��½��ְλ
	private String student_position;
	//�û�״̬��0��ʾ�˻��Ѳ����ã�1��ʾ����
	private String student_state;
	//����
	private String student_name;
	//����
	private String student_number;
	//��������
	private String student_birthday;
	//�Ա�
	private String student_sex;
	//��ϵ�绰
	private String student_phone;
	//��������
	private String student_mail;
	//ͷ��ͼƬ����·��
	private String student_avatar;
	
	// Hibernate���Ĭ�ϼ�����set���ϡ�
	// @JSONField ע�ⲻ��set���Ͻ���json��ת��
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
