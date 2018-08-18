package com.mydd.dao;

import java.util.List;

import com.mydd.domain.Curriculum;

public interface CurriculumDao {

	String save(Curriculum curriculum);

	String delete(Long curriculum_id);

	List<Curriculum> findByTeacherCurriculum(String userNumber);

	List<Curriculum> findAllCurriculum();

}
