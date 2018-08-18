package com.mydd.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mydd.domain.Course;
import com.mydd.domain.Curriculum;

/**
 * 
 * @author XPS15
 *
 */
@Repository(value="courseDao")
public class CourseDaoImpl extends HibernateDaoSupport implements CourseDao {

	@Resource(name="sessionFactory")
	public void setSessionFactory0(SessionFactory sessionFactory) {
		// 调用了父类方法
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public String saveCourse(Course course, String curriculumId) {
		Long curriculum_id = Long.parseLong(curriculumId);
		@SuppressWarnings("unchecked")
		List<Curriculum> listCurriculum = (List<Curriculum>) this.getHibernateTemplate().find("from com.mydd.domain.Curriculum where curriculum_id = ?0 and curriculum_state = 1", curriculum_id);
		// 注意要校验选课人数是否已经达到最大值
		if(listCurriculum != null && listCurriculum.size() > 0 && listCurriculum.get(0).getCurriculum_selected_number() < listCurriculum.get(0).getCurriculum_max_number()) {
			// 选择对应学生id的课程
			@SuppressWarnings("unchecked")
			List<Course> listCourse = (List<Course>) this.getHibernateTemplate().find("from com.mydd.domain.Course where course_time = ?0 and student.student_id = ?1 and course_state = 1", course.getCourse_time(), course.getStudent().getStudent_id());
			if( listCourse == null || listCourse.size() < 1) {
				listCurriculum.get(0).setCurriculum_selected_number(listCurriculum.get(0).getCurriculum_selected_number() + 1L); 
				course.setCurriculum(listCurriculum.get(0));
//				System.out.println(listCurriculum.get(0));
				this.getHibernateTemplate().save(course);
				return "1";				
			}else {
				return "-1";
			}
		}
		return "0";
	}

	@Override
	public String deleteCourse(Course course, String curriculumId) {
		Long curriculum_id = Long.parseLong(curriculumId);
		@SuppressWarnings("unchecked")
		List<Curriculum> listCurriculum = (List<Curriculum>) this.getHibernateTemplate().find("from com.mydd.domain.Curriculum where curriculum_id = ?0 and curriculum_state = 1", curriculum_id);
		// 注意要校验选课人数是否已经为0
		if(listCurriculum != null && listCurriculum.size() > 0 && listCurriculum.get(0).getCurriculum_selected_number() > 0) {
			listCurriculum.get(0).setCurriculum_selected_number(listCurriculum.get(0).getCurriculum_selected_number() - 1L); 
			List<Course> listCourse = (List<Course>) this.getHibernateTemplate().find("from com.mydd.domain.Course where curriculum.curriculum_id = ?0 and student.student_id = ?1 and course_state = 1", curriculum_id, course.getStudent().getStudent_id());
			this.getHibernateTemplate().delete(listCourse.get(0));
			return "1";
		}
		return "0";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findAllCourse(String studentNumber) {
		return (List<Course>) this.getHibernateTemplate().find("from com.mydd.domain.Course where course_state = 1 and student.student_number = ?0", studentNumber);
	}

	@Override
	public List<Course> findById(DetachedCriteria criteria) {
		@SuppressWarnings("unchecked")
		List<Course> list = (List<Course>) this.getHibernateTemplate().findByCriteria(criteria);
//		System.out.println(list.get(0));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public String submitScore(long course_id, long course_score) {
		@SuppressWarnings("unchecked")
		List<Course> list = (List<Course>) this.getHibernateTemplate().find("from com.mydd.domain.Course where course_id = ?0", course_id);
		if(list != null && list.size() > 0) {
			list.get(0).setCourse_score(course_score);
			return "1";
		}
		return "0";
	}
}
