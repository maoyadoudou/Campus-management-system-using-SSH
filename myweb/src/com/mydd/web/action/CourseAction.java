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
 * 可选课程清单控制器
 * @author XPS15
 * Controller(value="courseAction") 相当于在'Class="com.mydd.web.action.CourseAction"' 前加上了  'id="courseAction"'
 * scope(value="prototype") 相当于在'Class="com.mydd.web.action.CourseAction"' 后加上了  'scope="prototype"'  即设置为多例
 * 这两个注解取代了spring的applicationContext.xml 中 <bean id="courseAction" class="com.mydd.web.action.CourseAction" scope="prototype"> 的设置
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
	
	// 在Course表中存储学生选的课程
	public String saveCourse() {
		course = new Course();
		//调取该用户的Student对象
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
		//获得结果
		String saveCourseResult = courseService.saveCourse(course,curriculumId);
		//给页面返回存储是否成功
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
	
	// 在Course表中存储学生选的课程
	public String deleteCourse() {
		course = new Course();
		//调取该用户的Student对象
		Student student = (Student) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		System.out.println(student);
		course.setStudent(student);
		course.setCourse_name(ServletActionContext.getRequest().getParameter("curriculum_name"));
		course.setCourse_teacher(ServletActionContext.getRequest().getParameter("curriculum_teacher"));
		course.setCourse_score(-1L);
		String curriculumId = ServletActionContext.getRequest().getParameter("curriculum_id");
		//获得结果
		String deleteCourseResult = courseService.deleteCourse(course,curriculumId);
		System.out.println(course);
		//给页面返回存储是否成功
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
	// 提取所有course_state = 1 的课程清单
	public String findAllCourse() {
		Student student = (Student) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		List<Course> list = (List<Course>) courseService.findAllCourse(student.getStudent_number());
		String jsonString = FastJsonUtil.toJSONString(list);
//		System.out.println(jsonString);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}
	// 根据具体的老师下面的学生课程清单  .eqAll(sub1)
	public String findByTeacher(){
		// 获取老师的id，并转成Long类型
		String teacherId = ServletActionContext.getRequest().getParameter("teacher_id");
		long teacher_id = Long.parseLong(teacherId);
//		System.out.println(teacher_id);
		// criteria为主查询，sub1为子查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Course.class);
		criteria.add(Restrictions.eq("course_state","1"));
		DetachedCriteria sub1 = DetachedCriteria.forClass(Curriculum.class);
		// sub1 要在curriculum中找到对应老师的课程，且课程可选（状态为1），并提取这些课程的id
		sub1.add(Restrictions.eq("teacher.teacher_id", teacher_id));
		sub1.add(Restrictions.eq("curriculum_state", "1"));
		sub1.setProjection( Property.forName("curriculum_id"));
		criteria.add(Property.forName("curriculum.curriculum_id").in(sub1));
		HttpServletResponse response = ServletActionContext.getResponse();
		// 按条件搜索
		List<Course> list = courseService.findById(criteria);
//		System.out.println(list.get(0));
		String jsonString = FastJsonUtil.toJSONString(list);
//		System.out.println(jsonString);
		// 返回JSON类型结果
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}
	// 根据具体的老师下面的学生课程清单  .eqAll(sub1)
		public String findByStudent(){
			// 获取学生的id，并转成Long类型
			String studentId = ServletActionContext.getRequest().getParameter("student_id");
			long student_id = Long.parseLong(studentId);
//			System.out.println(student_id);
			// 查询已选课程列表中，特定学生选择的课程
			DetachedCriteria criteria = DetachedCriteria.forClass(Course.class);
			criteria.add(Restrictions.eq("course_state","1"));
			criteria.add(Restrictions.eq("student.student_id",student_id));
			HttpServletResponse response = ServletActionContext.getResponse();
			List<Course> list = courseService.findById(criteria);
//			System.out.println(list.get(0));
			String jsonString = FastJsonUtil.toJSONString(list);
//			System.out.println(jsonString);
			// 返回JSON 结果
			FastJsonUtil.write_json(response, jsonString);
			return NONE;
		}
	
	public String submitScore() {
		// 获得课程id 和对应的分数
		long course_id = Long.parseLong(ServletActionContext.getRequest().getParameter("course_id"));
		long course_score = Long.parseLong(ServletActionContext.getRequest().getParameter("course_score"));
		String submitResult = courseService.submitScore(course_id,course_score);
		//给页面返回存储是否成功
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
