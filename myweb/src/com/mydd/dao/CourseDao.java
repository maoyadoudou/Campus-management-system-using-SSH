package com.mydd.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mydd.domain.Course;
import com.mydd.domain.Curriculum;

public interface CourseDao {
	String saveCourse(Course course, String curriculumId);

	String deleteCourse(Course course, String curriculumId);

	List<Course> findAllCourse(String studentNumber);

	List<Course> findById(DetachedCriteria criteria);

	String submitScore(long course_id, long course_score);
}
