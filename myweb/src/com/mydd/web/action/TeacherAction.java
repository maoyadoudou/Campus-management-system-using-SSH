package com.mydd.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.mydd.domain.Teacher;
import com.mydd.service.TeacherService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import Utils.FastJsonUtil;
import Utils.PasswordUtils;
import Utils.UploadUtils;

public class TeacherAction extends ActionSupport implements ModelDriven<Teacher>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8517779299389687620L;
	
	private Teacher teacher = new Teacher();

	@Override
	public Teacher getModel() {
		return teacher;
	}

	//注入UserService对象
	private TeacherService teacherService;
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	// 老师注册action
	public String teacherSignUp() {
		Teacher teacherUser = teacher;
//		System.out.println("==========");
//		System.out.println(teacher);
//		System.out.println("==========");
		teacherService.save(teacherUser);
		return LOGIN;
	}
	
	// 检查该学号或工号是否已经被注册
	@SuppressWarnings("null")
	public String checkNumber() {
		// 调用业务层，查询
		Teacher teacherCheck = teacherService.checkNumber(teacher.getTeacher_number());
		// 获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			// 获取输出流
			PrintWriter writer = response.getWriter();
			// 进行判断，如果存在对应的账号，但是密码为空，那么注册行为相当于是激活账号
			if(teacherCheck == null || teacherCheck.getTeacher_password() == null){
				// 说明，不存在登录名，可以注册
				writer.print("yes");
			}else{
				// 说明：登录名查询到用户了，说明登录已经存在了，不能注册
				writer.print("no");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
	}
	// 登陆系统
	public String login() {
		// 调用业务层，查询
		String number = (String) ServletActionContext.getRequest().getParameter("user_number");
		teacher.setTeacher_number(number);
		String password = (String) ServletActionContext.getRequest().getParameter("user_password");
		teacher.setTeacher_password(password);
		Teacher teacherLogin = teacherService.login(teacher);
		// 如果没有这个用户，则页面返回登陆页面
		if(teacherLogin == null) {
			return LOGIN;
		}else {
			HttpSession session = ServletActionContext.getRequest().getSession();
			//  在开始学习编写前端时，不会盗用对象中的属性，所以设置了很多session属性，此项目中便不再修改，下一个项目（SSM）再改进
			session.setAttribute("userType", "teacher");
			session.setAttribute("userLogin", teacherLogin);
			session.setAttribute("userLoginName", teacherLogin.getTeacher_name());
			session.setAttribute("userPosition", teacherLogin.getTeacher_position());
			session.setAttribute("userNumber", teacherLogin.getTeacher_number());
			return "loginOK";
		}
	}
	//退出系统
	public String exit() {
		ServletActionContext.getRequest().getSession().removeAttribute("userLogin");
		return LOGIN;
	}
	//修改密码
	public String changePassword() {
		// 修改密码需要先输入原始密码，再输入新密码
		String originalPassword = ServletActionContext.getRequest().getParameter("originalPassword");
		String changedPassword = ServletActionContext.getRequest().getParameter("changedPassword");		
		// 获得登陆对象
		Teacher changingTeacher = null;
		changingTeacher = (Teacher) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		System.out.println(changingTeacher);
		if(changingTeacher != null ) {
			// 验证输入的原始密码， 如果正确，便可以更改密码，反之返回原始密码错误
			if(PasswordUtils.verifyUserPassword(originalPassword,changingTeacher.getTeacher_password(),changingTeacher.getTeacher_salt())) {
				teacherService.changePassword(changingTeacher, changedPassword);
				return "passwordEditOK";
			}else {
				return "The original password is error!";
			}
		}else {
			return LOGIN;
		}		
	}
	//根据用户工号查找Teacher对象
	public String findByNumber() {
		String userNumber = ServletActionContext.getRequest().getParameter("teacher_number");
//		System.out.println(userNumber);
		List<Teacher> list = (List<Teacher>) teacherService.findByNumber(userNumber);
		String jsonString = FastJsonUtil.toJSONString(list);
//		System.out.println(jsonString);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}
	
	private File upload;              // 要上传的文件
	private String uploadFileName;    // 上传文件的文件名
	@SuppressWarnings("unused")
	private String uploadContentType; //上传文件的文件类型
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	//上传个人头像，补充个人形象
	public String changePersonalProfile() throws IOException {
		Teacher originalTeacher = (Teacher) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		teacher.setTeacher_password(originalTeacher.getTeacher_password());
		teacher.setTeacher_salt(originalTeacher.getTeacher_salt());
		if(uploadFileName != null) {
			// 如果头像路径不为空，则先删除原头像
			if(originalTeacher.getTeacher_avatar() != null) {
				File originalFile = new File(originalTeacher.getTeacher_avatar());
				originalFile.delete();
			}
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			String fileSavePath = "D:\\JAVA\\image\\";
			File file = new File(fileSavePath + uuidName);
			FileUtils.copyFile(upload,file);
			teacher.setTeacher_avatar(fileSavePath + uuidName);
		}
//		System.out.println(userAvatarName);
		teacherService.update(teacher);
		return "updateOK";
	}
}




