package com.mydd.service;

import java.util.List;

import com.mydd.domain.Student;

public interface StudentService {
	void save(Student student);
	
	Student checkNumber(String string);

	Student login(Student student);

	void changePassword(Student changingStudent, String changedPassword);

	void update(Student student);

	List<Student> findByNumber(String userNumber);
}
