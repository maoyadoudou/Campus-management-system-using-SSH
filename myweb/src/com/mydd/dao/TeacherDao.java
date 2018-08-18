package com.mydd.dao;

import java.util.List;

import com.mydd.domain.Teacher;

public interface TeacherDao extends BaseDao<Teacher> {

	Teacher checkNumber(String teacher_Id);

	Teacher login(Teacher teacher);

	List<Teacher> findByNumber(String userNumber);
}
