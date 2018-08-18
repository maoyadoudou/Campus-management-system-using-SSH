package com.mydd.dao;

public interface BaseDao<T> {
	
	void save(T t);
	
	void update(T t);
}
