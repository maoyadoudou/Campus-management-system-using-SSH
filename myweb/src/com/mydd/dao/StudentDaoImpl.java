package com.mydd.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.mydd.domain.Student;

import Utils.PasswordUtils;


public class StudentDaoImpl extends HibernateDaoSupport implements StudentDao {
	/**
	 * ����ע���˺Ŷ�Ӧ����Ϣ��������ݿ����Ѿ�����ѧ�ţ���ע����Ϊ�����˺ż��ֻ�轫ѧ�š����롢saltֵ��״̬��ְҵ������ȫ��
	 * ������ݿ���û�ж�Ӧѧ�ŵļ�¼���򴴽��µ���Ŀ������
	 */
	@Override
	public void save(Student student) {
		this.getHibernateTemplate().save(student);
	}
	/**
	 * ������ݿ����Ƿ��ж�Ӧѧ�ŵļ�¼
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
	// ��StudentServiceImpl�㷵�ض�Ӧѧ�ŵ�Student����
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
