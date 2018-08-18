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
		// ������ʱ��curriculum����
		if(list != null && list.size() > 0) {
//			System.out.println("============");
			for (Curriculum curriculum_original : list) {
				// ���ȼ���ڸ�ʱ�ε�ͬһ�������Ƿ񱻱���ռ��
				if( curriculum_original.getCurriculum_location().equals(curriculum.getCurriculum_location()) && !curriculum_original.getTeacher_number().equals(curriculum.getTeacher_number())){
					return "0";
				}
				// �����ʱ�θõ�½��ʦ����������Ҳ�Ǽ��˿γ̣������޷������轫ԭ�γ̱�Ϊ����ѡ״̬�������ȼ�¼ԭ�γ̶���
				if( curriculum_original.getTeacher_number().equals(curriculum.getTeacher_number())) {
					tempCurriculum = curriculum_original;
//					System.out.println("����" + tempCurriculum);
				}
			}
		}
//		System.out.println("������" + tempCurriculum);
		// ��ԭ�γ̶����Ϊ����ѡ״̬
		if(tempCurriculum != null) {
			tempCurriculum.setCurriculum_state("0");
		}
//		System.out.println("--------------");
		// �����µĿγ̵ǼǼ�¼
		this.getHibernateTemplate().save(curriculum);
//		System.out.println("wwwwwwwwwwwwwww");
		return "1";
	}
	// ��νɾ����ֻ�ǽ��γ̵Ŀ�ѡ״̬��Ϊ0���������ɾ��
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
	// ������ʦ�Ĺ���Ѱ�ҿ�ѡ״̬Ϊ1�Ŀγ�
	@Override
	public List<Curriculum> findByTeacherCurriculum(String userNumber) {
		@SuppressWarnings("unchecked")
		List<Curriculum> list = (List<Curriculum>) this.getHibernateTemplate().find("from com.mydd.domain.Curriculum where teacher_number = ?0 and curriculum_state = 1 order by curriculum_time", userNumber);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	// ��ѧ���������п�ѡ״̬Ϊ1�Ŀγ�
	@SuppressWarnings("unchecked")
	@Override
	public List<Curriculum> findAllCurriculum() {
		return (List<Curriculum>) this.getHibernateTemplate().find("from com.mydd.domain.Curriculum where curriculum_state = 1 order by curriculum_id");
	}
}
