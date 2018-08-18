package com.mydd.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mydd.dao.CourseDao;
import com.mydd.domain.Course;
/**
 * 
 * @author XPS15
 *
 */
@Service(value="courseService")
@Transactional
public class CourseServiceImpl implements CourseService {	
	
	@Resource(name="courseDao")
	private CourseDao courseDao;

	@Override
	public String saveCourse(Course course, String curriculumId) {
		return courseDao.saveCourse(course,curriculumId);
	}

	@Override
	public String deleteCourse(Course course, String curriculumId) {
		return courseDao.deleteCourse(course,curriculumId);
	}

	@Override
	public List<Course> findAllCourse(String studentNumber) {
		return courseDao.findAllCourse(studentNumber);
	}

	@Override
	public List<Course> findById(DetachedCriteria criteria) {
		return courseDao.findById(criteria);
	}

	@Override
	public String submitScore(long course_id, long course_score) {
		return courseDao.submitScore(course_id,course_score);
	}
	

	
}
