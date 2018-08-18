package com.mydd.test;

import org.junit.Test;

import com.mydd.web.action.CourseAction;

public class MyTest {
	@Test
	public void run1() {
//		long teacher_id = 3L;
		CourseAction a = new CourseAction();
		a.findByTeacher();
	}

}
