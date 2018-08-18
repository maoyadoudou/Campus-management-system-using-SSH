package com.mydd.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mydd.domain.Course;
import com.mydd.domain.Curriculum;
import com.mydd.domain.Student;
import com.mydd.service.CourseService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import Utils.FastJsonUtil;

/**
 * ��ѡ�γ��嵥������
 * @author XPS15
 * Controller(value="courseAction") �൱����'Class="com.mydd.web.action.CourseAction"' ǰ������  'id="courseAction"'
 * scope(value="prototype") �൱����'Class="com.mydd.web.action.CourseAction"' �������  'scope="prototype"'  ������Ϊ����
 * ������ע��ȡ����spring��applicationContext.xml �� <bean id="courseAction" class="com.mydd.web.action.CourseAction" scope="prototype"> ������
 */
@Controller(value="courseAction")
@Scope(value="prototype")
public class CourseAction extends ActionSupport implements ModelDriven<Course>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 238872084506624034L;

	private Course course;
	
	@Override
	public Course getModel() {
		return course;
	}
	
	@Resource(name="courseService")
	private CourseService courseService;
	
	// ��Course���д洢ѧ��ѡ�Ŀγ�
	public String saveCourse() {
		course = new Course();
		//��ȡ���û���Student����
		Student student = (Student) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		course.setStudent(student);
		System.out.println("=================");
		course.setCourse_name(ServletActionContext.getRequest().getParameter("curriculum_name"));
		course.setCourse_teacher(ServletActionContext.getRequest().getParameter("curriculum_teacher"));
		course.setCourse_time(ServletActionContext.getRequest().getParameter("curriculum_time"));
		course.setCourse_state("1");
		course.setCourse_score(-1L);
		String curriculumId = ServletActionContext.getRequest().getParameter("curriculum_id");
//		System.out.println(course.getCourse_name());
		//��ý��
		String saveCourseResult = courseService.saveCourse(course,curriculumId);
		//��ҳ�淵�ش洢�Ƿ�ɹ�
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			if (saveCourseResult.equals("1")) {
				writer.print("success");;
			}else if (saveCourseResult.equals("-1")) {
				writer.print("conflict");
			}else {
				writer.print("fail");
			} 	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;		
	}
	
	// ��Course���д洢ѧ��ѡ�Ŀγ�
	public String deleteCourse() {
		course = new Course();
		//��ȡ���û���Student����
		Student student = (Student) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		System.out.println(student);
		course.setStudent(student);
		course.setCourse_name(ServletActionContext.getRequest().getParameter("curriculum_name"));
		course.setCourse_teacher(ServletActionContext.getRequest().getParameter("curriculum_teacher"));
		course.setCourse_score(-1L);
		String curriculumId = ServletActionContext.getRequest().getParameter("curriculum_id");
		//��ý��
		String deleteCourseResult = courseService.deleteCourse(course,curriculumId);
		System.out.println(course);
		//��ҳ�淵�ش洢�Ƿ�ɹ�
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			if (deleteCourseResult.equals("1")) {
				writer.print("success");;
			}else {
				writer.print("fail");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;		
	}
	// ��ȡ����course_state = 1 �Ŀγ��嵥
	public String findAllCourse() {
		Student student = (Student) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		List<Course> list = (List<Course>) courseService.findAllCourse(student.getStudent_number());
		String jsonString = FastJsonUtil.toJSONString(list);
//		System.out.println(jsonString);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}
	// ���ݾ������ʦ�����ѧ���γ��嵥  .eqAll(sub1)
	public String findByTeacher(){
		// ��ȡ��ʦ��id����ת��Long����
		String teacherId = ServletActionContext.getRequest().getParameter("teacher_id");
		long teacher_id = Long.parseLong(teacherId);
//		System.out.println(teacher_id);
		// criteriaΪ����ѯ��sub1Ϊ�Ӳ�ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Course.class);
		criteria.add(Restrictions.eq("course_state","1"));
		DetachedCriteria sub1 = DetachedCriteria.forClass(Curriculum.class);
		// sub1 Ҫ��curriculum���ҵ���Ӧ��ʦ�Ŀγ̣��ҿγ̿�ѡ��״̬Ϊ1��������ȡ��Щ�γ̵�id
		sub1.add(Restrictions.eq("teacher.teacher_id", teacher_id));
		sub1.add(Restrictions.eq("curriculum_state", "1"));
		sub1.setProjection( Property.forName("curriculum_id"));
		criteria.add(Property.forName("curriculum.curriculum_id").in(sub1));
		HttpServletResponse response = ServletActionContext.getResponse();
		// ����������
		List<Course> list = courseService.findById(criteria);
//		System.out.println(list.get(0));
		String jsonString = FastJsonUtil.toJSONString(list);
//		System.out.println(jsonString);
		// ����JSON���ͽ��
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}
	// ���ݾ������ʦ�����ѧ���γ��嵥  .eqAll(sub1)
		public String findByStudent(){
			// ��ȡѧ����id����ת��Long����
			String studentId = ServletActionContext.getRequest().getParameter("student_id");
			long student_id = Long.parseLong(studentId);
//			System.out.println(student_id);
			// ��ѯ��ѡ�γ��б��У��ض�ѧ��ѡ��Ŀγ�
			DetachedCriteria criteria = DetachedCriteria.forClass(Course.class);
			criteria.add(Restrictions.eq("course_state","1"));
			criteria.add(Restrictions.eq("student.student_id",student_id));
			HttpServletResponse response = ServletActionContext.getResponse();
			List<Course> list = courseService.findById(criteria);
//			System.out.println(list.get(0));
			String jsonString = FastJsonUtil.toJSONString(list);
//			System.out.println(jsonString);
			// ����JSON ���
			FastJsonUtil.write_json(response, jsonString);
			return NONE;
		}
	
	public String submitScore() {
		// ��ÿγ�id �Ͷ�Ӧ�ķ���
		long course_id = Long.parseLong(ServletActionContext.getRequest().getParameter("course_id"));
		long course_score = Long.parseLong(ServletActionContext.getRequest().getParameter("course_score"));
		String submitResult = courseService.submitScore(course_id,course_score);
		//��ҳ�淵�ش洢�Ƿ�ɹ�
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			if (submitResult.equals("1")) {
				writer.print("success");;
			}else {
				writer.print("fail");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
}
