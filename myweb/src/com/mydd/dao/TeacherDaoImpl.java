package com.mydd.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.mydd.domain.Teacher;

import Utils.PasswordUtils;


public class TeacherDaoImpl extends HibernateDaoSupport implements TeacherDao {

	@Override
	public void save(Teacher teacher) {
		this.getHibernateTemplate().save(teacher);
	}

	@Override
	public Teacher checkNumber(String teacher_number) {
		@SuppressWarnings("unchecked")
		List<Teacher> list = (List<Teacher>) this.getHibernateTemplate().find("from com.mydd.domain.Teacher where teacher_number = ?0", teacher_number);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Teacher login(Teacher teacher) {
		System.out.println(teacher);
		@SuppressWarnings("unchecked")
		List<Teacher> list = (List<Teacher>) this.getHibernateTemplate().find("from com.mydd.domain.Teacher where teacher_number = ?0", teacher.getTeacher_number());
		if(list != null && list.size() > 0) {
			if(PasswordUtils.verifyUserPassword(teacher.getTeacher_password(),list.get(0).getTeacher_password(),list.get(0).getTeacher_salt())) {
				return list.get(0);
			}else {
				return null;
			}
		}
		return null;
	}
	
	@Override
	public void update(Teacher teacher) {
		this.getHibernateTemplate().update(teacher);
	}

	@Override
	public List<Teacher> findByNumber(String userNumber) {
		@SuppressWarnings("unchecked")
		List<Teacher> list = (List<Teacher>) this.getHibernateTemplate().find("from com.mydd.domain.Teacher where teacher_number = ?0", userNumber);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
