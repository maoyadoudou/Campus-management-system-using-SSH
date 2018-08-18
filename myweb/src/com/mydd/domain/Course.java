package com.mydd.domain;
/**
 * 将Curriculum与Student的多对多变为两个一对多
 * 其中Course为多方
 * @author XPS15
 */
public class Course {
	//主键
	private Long course_id;
	//成绩
	private Long course_score;
	//已选课程状态，1表示仍处于选择状态，0表示选择后又弃选
	private String course_state;
	//选择的课程名称
	private String course_name;
	//课程的代课老师
	private String course_teacher;
	//课程的时间
	private String course_time;
	
	//创建Curriculum对象
	private Curriculum curriculum;
	//创建Student对象
	private Student student;
	
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	public Long getCourse_score() {
		return course_score;
	}
	public void setCourse_score(Long course_score) {
		this.course_score = course_score;
	}
	public String getCourse_state() {
		return course_state;
	}
	public void setCourse_state(String course_state) {
		this.course_state = course_state;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_teacher() {
		return course_teacher;
	}
	public void setCourse_teacher(String course_teacher) {
		this.course_teacher = course_teacher;
	}
	public String getCourse_time() {
		return course_time;
	}
	public void setCourse_time(String course_time) {
		this.course_time = course_time;
	}
	
	public Curriculum getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", course_score=" + course_score + ", course_state=" + course_state + ", course_name=" + course_name
				+ ", course_teacher=" + course_teacher + ", course_time=" + course_time + ", curriculum=" + curriculum + ", student=" + student + "]";
	}	
}
