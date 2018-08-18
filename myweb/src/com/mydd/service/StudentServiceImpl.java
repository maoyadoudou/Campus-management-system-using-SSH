package com.mydd.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mydd.dao.StudentDao;
import com.mydd.domain.Student;

import Utils.PasswordUtils;
@Transactional
public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	@Override
	public void save(Student student) {
		Student studentCheckId = studentDao.checkNumber(student.getStudent_number());
		String password = student.getStudent_password();
		String salt = PasswordUtils.getSalt(30);
		String securePassword = PasswordUtils.generateSecurePassword(password, salt);
		if(studentCheckId != null) {
			studentCheckId.setStudent_salt(salt);
			studentCheckId.setStudent_password(securePassword);
			studentCheckId.setStudent_state("1");
			studentCheckId.setStudent_position("0");
			studentDao.update(studentCheckId);
		}else{
			student.setStudent_salt(salt);
			student.setStudent_password(securePassword);
			student.setStudent_state("1");
			student.setStudent_position("0");
			studentDao.save(student);	
		}
	}

	@Override
	public Student checkNumber(String student_number) {
		return studentDao.checkNumber(student_number);
	}

	@Override
	public Student login(Student student) {
		return studentDao.login(student);
	}
	//将修改密码后的对象update到原对象中
	@Override
	public void changePassword(Student student, String changedPassword) {
		String salt = PasswordUtils.getSalt(30);
		String securePassword = PasswordUtils.generateSecurePassword(changedPassword, salt);
		student.setStudent_salt(salt);
		student.setStudent_password(securePassword);
		studentDao.update(student);
	}

	@Override
	public void update(Student student) {
		studentDao.update(student);
	}

	@Override
	public List<Student> findByNumber(String userNumber) {
		return studentDao.findByNumber(userNumber);
	}

}
