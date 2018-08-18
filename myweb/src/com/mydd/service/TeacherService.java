package com.mydd.service;

import java.util.List;

import com.mydd.domain.Teacher;

public interface TeacherService {
	void save(Teacher teacher);
	
	Teacher checkNumber(String string);

	Teacher login(Teacher teacher);

	void changePassword(Teacher changingTeacher, String changedPassword);

	void update(Teacher teacher);

	List<Teacher> findByNumber(String userNumber);
}
