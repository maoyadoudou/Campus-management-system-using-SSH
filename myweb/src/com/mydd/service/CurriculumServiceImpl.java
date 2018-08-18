package com.mydd.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mydd.dao.CurriculumDao;
import com.mydd.domain.Curriculum;

@Transactional
public class CurriculumServiceImpl implements CurriculumService {
	
	private CurriculumDao curriculumDao;
	public void setCurriculumDao(CurriculumDao curriculumDao) {
		this.curriculumDao = curriculumDao;
	}
	
	@Override
	public String save(Curriculum curriculum) {
		return curriculumDao.save(curriculum);		
	}

	@Override
	public String delete(Long curriculum_id) {
		return curriculumDao.delete(curriculum_id);
	}

	@Override
	public List<Curriculum> findByTeacherCurriculum(String userNumber) {
		return curriculumDao.findByTeacherCurriculum(userNumber);
	}

	@Override
	public List<Curriculum> findAllCurriculum() {
		return curriculumDao.findAllCurriculum();
	}

}
