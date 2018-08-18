package com.mydd.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.mydd.domain.Student;
import com.mydd.service.StudentService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import Utils.FastJsonUtil;
import Utils.PasswordUtils;
import Utils.UploadUtils;

public class StudentAction extends ActionSupport implements ModelDriven<Student>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -441331585542451534L;
	
	private Student student = new Student();

	@Override
	public Student getModel() {
		return student;
	}

	//注入UserService对象
	private StudentService studentService;
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	//学生注册action
	public String studentSignUp() {
		Student studentUser = student;
//		System.out.println(student);
		studentService.save(studentUser);
		return LOGIN;
	}
	
	//检查该学号或工号是否已经被注册
	@SuppressWarnings("null")
	public String checkNumber() {
		// 调用业务层，查询
		Student studentCheck = studentService.checkNumber(student.getStudent_number());
		// 创建response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			// 获取输出流
			PrintWriter writer = response.getWriter();
			// 进行判断，如果存在对应的账号，但是密码为空，那么注册行为相当于是激活账号
			if(studentCheck == null || studentCheck.getStudent_password() == null){
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
	//登陆系统
	public String login() {
		// 调用业务层，查询
		String number = (String) ServletActionContext.getRequest().getParameter("user_number");
		student.setStudent_number(number);
		String password = (String) ServletActionContext.getRequest().getParameter("user_password");
		student.setStudent_password(password);
		Student studentLogin = studentService.login(student);
		// 如果没有这个用户，则页面返回登陆页面
		if(studentLogin == null) {
			return LOGIN;
		}else {
			HttpSession session = ServletActionContext.getRequest().getSession();
		//  在开始学习编写前端时，不会盗用对象中的属性，所以设置了很多session属性，此项目中便不再修改，下一个项目（SSM）再改进
			session.setAttribute("userType", "student");
			session.setAttribute("userLogin", studentLogin);
			session.setAttribute("userLoginName", studentLogin.getStudent_name());
			session.setAttribute("userPosition", studentLogin.getStudent_position());
			session.setAttribute("userNumber", studentLogin.getStudent_number());
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
		Student changingStudent = null;
		changingStudent = (Student) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
//		System.out.println(changingStudent);
		if(changingStudent != null ) {
			// 验证输入的原始密码， 如果正确，便可以更改密码，反之返回原始密码错误
			if(PasswordUtils.verifyUserPassword(originalPassword,changingStudent.getStudent_password(),changingStudent.getStudent_salt())) {
				studentService.changePassword(changingStudent, changedPassword);
				return "passwordEditOK";
			}else {
				return "The original password is error!";
			}
		}else {
			return LOGIN;
		}		
	}
	//根据用户学号查找Student对象
	public String findByNumber() {
		String userNumber = ServletActionContext.getRequest().getParameter("student_number");
//		System.out.println(userNumber);
		List<Student> list = (List<Student>) studentService.findByNumber(userNumber);
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
		Student originalStudent = (Student) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		student.setStudent_password(originalStudent.getStudent_password());
		student.setStudent_salt(originalStudent.getStudent_salt());
		if(uploadFileName != null) {
			// 如果头像路径不为空，则先删除原头像
			if(originalStudent.getStudent_avatar() != null) {
				File originalFile = new File(originalStudent.getStudent_avatar());
				originalFile.delete();
			}
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			String fileSavePath = "D:\\JAVA\\image\\";
			File file = new File(fileSavePath + uuidName);
			FileUtils.copyFile(upload,file);
			student.setStudent_avatar(fileSavePath + uuidName);
		}
//		System.out.println(userAvatarName);
		studentService.update(student);
		return "updateOK";
	}
}


