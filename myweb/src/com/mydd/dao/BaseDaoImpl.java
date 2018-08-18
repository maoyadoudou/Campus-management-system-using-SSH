package com.mydd.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	//�����Ա����
	private Class<T> clazz;
	
	public BaseDaoImpl() {
		// this��ʾ���࣬clazz1��ʾ�̳�BaseDaoImpl��XXXDaoImpl��Ӧ��XXX���� ����ȡT
		Class<T> clazz1 = (Class<T>) this.getClass();
		// ��ȡBaseDaoImpl<T>
		Type type = clazz1.getGenericSuperclass();
		// ��type�ӿ�ת��Ϊ�ӽӿ�
		if(type instanceof ParameterizedType){
			ParameterizedType pType = (ParameterizedType) type;
			// ��ȡXXX���󣬼�T
			Type[] types = pType.getActualTypeArguments();
			this.clazz = (Class<T>) types[0];
		}
	}
	
	// ���
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}
	
	// �޸�
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

}
