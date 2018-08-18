package com.mydd.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	//定义成员属性
	private Class<T> clazz;
	
	public BaseDaoImpl() {
		// this表示子类，clazz1表示继承BaseDaoImpl的XXXDaoImpl对应的XXX对象， 即获取T
		Class<T> clazz1 = (Class<T>) this.getClass();
		// 获取BaseDaoImpl<T>
		Type type = clazz1.getGenericSuperclass();
		// 把type接口转换为子接口
		if(type instanceof ParameterizedType){
			ParameterizedType pType = (ParameterizedType) type;
			// 获取XXX对象，即T
			Type[] types = pType.getActualTypeArguments();
			this.clazz = (Class<T>) types[0];
		}
	}
	
	// 添加
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}
	
	// 修改
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

}
