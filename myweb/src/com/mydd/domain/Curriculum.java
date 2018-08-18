package com.mydd.domain;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 教师提交课程信息
 * 相对于Teacher为多方
 * 相对于Course为一方
 * @author XPS15 *
 */
public class Curriculum {
	//主键
	private Long curriculum_id;
	//课程状态，0表示已删除不可用，1表示可选
	private String curriculum_state;
	//课程名称
	private String curriculum_name;
	//课程时间
	private String curriculum_time;
	//课程地点
	private String curriculum_location;
	//课程最大人数
	private Long curriculum_max_number;
	//课程目前认数
	private Long curriculum_selected_number;
	//这里是专门这么命名的，初学，想看看这样命名会不会出错。没出错。
	//姓名
	private String teacher_name;
	//工号
	private String teacher_number;
	
	//创建教师对象
	private Teacher teacher;
	
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
	
	public Long getCurriculum_id() {
		return curriculum_id;
	}

	public void setCurriculum_id(Long curriculum_id) {
		this.curriculum_id = curriculum_id;
	}

	public String getCurriculum_state() {
		return curriculum_state;
	}

	public void setCurriculum_state(String curriculum_state) {
		this.curriculum_state = curriculum_state;
	}

	public String getCurriculum_name() {
		return curriculum_name;
	}

	public void setCurriculum_name(String curriculum_name) {
		this.curriculum_name = curriculum_name;
	}

	public String getCurriculum_time() {
		return curriculum_time;
	}

	public void setCurriculum_time(String curriculum_time) {
		this.curriculum_time = curriculum_time;
	}

	public String getCurriculum_location() {
		return curriculum_location;
	}

	public void setCurriculum_location(String curriculum_location) {
		this.curriculum_location = curriculum_location;
	}

	public Long getCurriculum_max_number() {
		return curriculum_max_number;
	}

	public void setCurriculum_max_number(Long curriculum_max_number) {
		this.curriculum_max_number = curriculum_max_number;
	}

	public Long getCurriculum_selected_number() {
		return curriculum_selected_number;
	}

	public void setCurriculum_selected_number(Long curriculum_selected_number) {
		this.curriculum_selected_number = curriculum_selected_number;
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
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	//这里没有打印Teacher对象
	@Override
	public String toString() {
		return "Curriculum [curriculum_id=" + curriculum_id + ", curriculum_state=" + curriculum_state
				+ ", curriculum_name=" + curriculum_name + ", curriculum_time=" + curriculum_time
				+ ", curriculum_location=" + curriculum_location + ", curriculum_max_number=" + curriculum_max_number
				+ ", curriculum_selected_number=" + curriculum_selected_number + ", teacher_name=" + teacher_name
				+ ", teacher_number=" + teacher_number + "]";
	}
}
