package com.mydd.dao;

import java.util.List;

import com.mydd.domain.Student;

public interface StudentDao extends BaseDao<Student>{

	Student checkNumber(String student_Id);

	Student login(Student student);

	List<Student> findByNumber(String userNumber);
}
