package com.mydd.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mydd.dao.TeacherDao;
import com.mydd.domain.Teacher;

import Utils.PasswordUtils;
@Transactional
public class TeacherServiceImpl implements TeacherService {
	
	private TeacherDao teacherDao;
	public void setteacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}
	
	@Override
	public void save(Teacher teacher) {
		Teacher teacherCheckId = teacherDao.checkNumber(teacher.getTeacher_number());
		String password = teacher.getTeacher_password();
		String salt = PasswordUtils.getSalt(30);
		String securePassword = PasswordUtils.generateSecurePassword(password, salt);
		if(teacherCheckId != null) {
			teacherCheckId.setTeacher_salt(salt);
			teacherCheckId.setTeacher_password(securePassword);
			teacherCheckId.setTeacher_state("1");
			teacherCheckId.setTeacher_position("1");
			teacherDao.update(teacherCheckId);
		}else{
			teacher.setTeacher_salt(salt);
			teacher.setTeacher_password(securePassword);
			teacher.setTeacher_state("1");
			teacher.setTeacher_position("1");
			teacherDao.save(teacher);	
		}		
	}

	@Override
	public Teacher checkNumber(String teacher_number) {
		return teacherDao.checkNumber(teacher_number);
	}

	@Override
	public Teacher login(Teacher teacher) {
		return teacherDao.login(teacher);
	}
	//将修改密码后的对象update到原对象中
	@Override
	public void changePassword(Teacher teacher, String changedPassword) {
		String salt = PasswordUtils.getSalt(30);
		String securePassword = PasswordUtils.generateSecurePassword(changedPassword, salt);
		teacher.setTeacher_salt(salt);
		teacher.setTeacher_password(securePassword);
		teacherDao.update(teacher);
	}

	@Override
	public void update(Teacher teacher) {
		teacherDao.update(teacher);
	}

	@Override
	public List<Teacher> findByNumber(String userNumber) {
		return teacherDao.findByNumber(userNumber);
	}

}
