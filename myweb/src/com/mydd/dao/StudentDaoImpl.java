package com.mydd.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.mydd.domain.Student;

import Utils.PasswordUtils;


public class StudentDaoImpl extends HibernateDaoSupport implements StudentDao {
	/**
	 * 保存注册账号对应的信息，如果数据库中已经存在学号，则注册行为就是账号激活，只需将学号、密码、salt值、状态、职业补充完全；
	 * 如果数据库中没有对应学号的记录，则创建新的条目并保存
	 */
	@Override
	public void save(Student student) {
		this.getHibernateTemplate().save(student);
	}
	/**
	 * 检查数据库中是否有对应学号的记录
	 */
	@Override
	public Student checkNumber(String student_number) {
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) this.getHibernateTemplate().find("from com.mydd.domain.Student where student_number = ?0", student_number);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	// 向StudentServiceImpl层返回对应学号的Student对象
	@Override
	public Student login(Student student) {
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) this.getHibernateTemplate().find("from com.mydd.domain.Student where student_number = ?0", student.getStudent_number());
		if(list != null && list.size() > 0) {
			if(PasswordUtils.verifyUserPassword(student.getStudent_password(),list.get(0).getStudent_password(),list.get(0).getStudent_salt())) {
				return list.get(0);
			}else {
				return null;
			}
		}
		return null;
	}
	
	@Override
	public void update(Student student) {
		this.getHibernateTemplate().update(student);
	}
	
	@Override
	public List<Student> findByNumber(String userNumber) {
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) this.getHibernateTemplate().find("from com.mydd.domain.Student where student_number = ?0", userNumber);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
