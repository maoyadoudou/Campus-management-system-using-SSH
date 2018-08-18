package com.mydd.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.mydd.domain.Curriculum;
/**
 * 
 * @author XPS15
 *
 */
public class CurriculumDaoImpl extends HibernateDaoSupport implements CurriculumDao {

	@Override
	public String save(Curriculum curriculum) {
//		System.out.println(curriculum.getCurriculum_time());
		@SuppressWarnings("unchecked")
		List<Curriculum> list = (List<Curriculum>) this.getHibernateTemplate().find("from com.mydd.domain.Curriculum where curriculum_time = ?0 and curriculum_state = 1", curriculum.getCurriculum_time());
//		System.out.println("++++++++++");
		Curriculum tempCurriculum = null;
		// 遍历该时段curriculum对象
		if(list != null && list.size() > 0) {
//			System.out.println("============");
			for (Curriculum curriculum_original : list) {
				// 首先检查在该时段的同一个教室是否被别人占用
				if( curriculum_original.getCurriculum_location().equals(curriculum.getCurriculum_location()) && !curriculum_original.getTeacher_number().equals(curriculum.getTeacher_number())){
					return "0";
				}
				// 如果该时段该登陆老师在其他教室也登记了课程，由于无法分身，需将原课程变为不可选状态，这里先记录原课程对象
				if( curriculum_original.getTeacher_number().equals(curriculum.getTeacher_number())) {
					tempCurriculum = curriculum_original;
//					System.out.println("测试" + tempCurriculum);
				}
			}
		}
//		System.out.println("最后测试" + tempCurriculum);
		// 将原课程对象改为不可选状态
		if(tempCurriculum != null) {
			tempCurriculum.setCurriculum_state("0");
		}
//		System.out.println("--------------");
		// 保存新的课程登记记录
		this.getHibernateTemplate().save(curriculum);
//		System.out.println("wwwwwwwwwwwwwww");
		return "1";
	}
	// 所谓删除，只是将课程的可选状态设为0，并非真的删除
	@Override
	public String delete(Long curriculum_id) {
		@SuppressWarnings("unchecked")
		List<Curriculum> list = (List<Curriculum>) this.getHibernateTemplate().find("from com.mydd.domain.Curriculum where curriculum_id = ?0 and curriculum_state = 1", curriculum_id);
		if(list != null && list.size() > 0) {
			list.get(0).setCurriculum_state("0");
			return "1";
		}
		return "0";
	}
	// 根据老师的工号寻找可选状态为1的课程
	@Override
	public List<Curriculum> findByTeacherCurriculum(String userNumber) {
		@SuppressWarnings("unchecked")
		List<Curriculum> list = (List<Curriculum>) this.getHibernateTemplate().find("from com.mydd.domain.Curriculum where teacher_number = ?0 and curriculum_state = 1 order by curriculum_time", userNumber);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	// 向学生罗列所有可选状态为1的课程
	@SuppressWarnings("unchecked")
	@Override
	public List<Curriculum> findAllCurriculum() {
		return (List<Curriculum>) this.getHibernateTemplate().find("from com.mydd.domain.Curriculum where curriculum_state = 1 order by curriculum_id");
	}
}
