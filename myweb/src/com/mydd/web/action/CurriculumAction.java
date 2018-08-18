package com.mydd.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mydd.domain.Curriculum;
import com.mydd.domain.Teacher;
import com.mydd.service.CurriculumService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import Utils.FastJsonUtil;

public class CurriculumAction extends ActionSupport implements ModelDriven<Curriculum>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8517779299389687620L;
	
	private Curriculum curriculum = new Curriculum();

	@Override
	public Curriculum getModel() {
		return curriculum;
	}
	//注入curriculumService对象
	private CurriculumService curriculumService;
	public void setCurriculumService(CurriculumService curriculumService) {
		this.curriculumService = curriculumService;
	}
	// 教师提交课程
	public String submitCurriculum() {
		Teacher teacher = (Teacher) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
//		System.out.println(teacher);
		curriculum.setCurriculum_state("1");
		curriculum.setTeacher(teacher);
		curriculum.setCurriculum_selected_number(0L);
//		System.out.println(curriculum);
		String submitResult = curriculumService.save(curriculum);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// 返回结果
		try {
			PrintWriter writer = response.getWriter();
			if (submitResult.equals("1")) {
				writer.print("success1");;
			}else {
				writer.print("fail");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	// 根据教师工号查找已登记的课程对象
	public String findByTeacherCurriculum() {
		// 获得教师的工号
		String userNumber = ServletActionContext.getRequest().getParameter("teacher_number");
//		System.out.println(userNumber);
		// 根据工号查找他或她提交的课程清单
		List<Curriculum> list = (List<Curriculum>) curriculumService.findByTeacherCurriculum(userNumber);
		String jsonString = FastJsonUtil.toJSONString(list);
//		System.out.println(jsonString);
		HttpServletResponse response = ServletActionContext.getResponse();
		// 返回结果
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}
	// 根据教师工号查找已登记的课程对象
	public String findAllCurriculum() {
		List<Curriculum> list = (List<Curriculum>) curriculumService.findAllCurriculum();
		String jsonString = FastJsonUtil.toJSONString(list);
//		System.out.println(jsonString);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}
	// 删除提交的课程
	public String deleteCurriculum() {
		// 获得要删除的课程id
		String curriculumIdStr = ServletActionContext.getRequest().getParameter("curriculum_id");
		long curriculumId = Long.parseLong(curriculumIdStr);
//		System.out.println(curriculumId);
		// 获得删除结果
		String deleteResult = curriculumService.delete(curriculumId);
		// 返回结果
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			if (deleteResult.equals("1")) {
				writer.print("success");;
			}else {
				writer.print("fail");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
}


