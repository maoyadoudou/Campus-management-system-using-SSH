package com.mydd.domain;
/**
 * ��Curriculum��Student�Ķ�Զ��Ϊ����һ�Զ�
 * ����CourseΪ�෽
 * @author XPS15
 */
public class Course {
	//����
	private Long course_id;
	//�ɼ�
	private Long course_score;
	//��ѡ�γ�״̬��1��ʾ�Դ���ѡ��״̬��0��ʾѡ�������ѡ
	private String course_state;
	//ѡ��Ŀγ�����
	private String course_name;
	//�γ̵Ĵ�����ʦ
	private String course_teacher;
	//�γ̵�ʱ��
	private String course_time;
	
	//����Curriculum����
	private Curriculum curriculum;
	//����Student����
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
