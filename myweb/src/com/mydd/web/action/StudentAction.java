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

	//ע��UserService����
	private StudentService studentService;
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	//ѧ��ע��action
	public String studentSignUp() {
		Student studentUser = student;
//		System.out.println(student);
		studentService.save(studentUser);
		return LOGIN;
	}
	
	//����ѧ�Ż򹤺��Ƿ��Ѿ���ע��
	@SuppressWarnings("null")
	public String checkNumber() {
		// ����ҵ��㣬��ѯ
		Student studentCheck = studentService.checkNumber(student.getStudent_number());
		// ����response����
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			// ��ȡ�����
			PrintWriter writer = response.getWriter();
			// �����жϣ�������ڶ�Ӧ���˺ţ���������Ϊ�գ���ôע����Ϊ�൱���Ǽ����˺�
			if(studentCheck == null || studentCheck.getStudent_password() == null){
				// ˵���������ڵ�¼��������ע��
				writer.print("yes");
			}else{
				// ˵������¼����ѯ���û��ˣ�˵����¼�Ѿ������ˣ�����ע��
				writer.print("no");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return NONE;
	}
	//��½ϵͳ
	public String login() {
		// ����ҵ��㣬��ѯ
		String number = (String) ServletActionContext.getRequest().getParameter("user_number");
		student.setStudent_number(number);
		String password = (String) ServletActionContext.getRequest().getParameter("user_password");
		student.setStudent_password(password);
		Student studentLogin = studentService.login(student);
		// ���û������û�����ҳ�淵�ص�½ҳ��
		if(studentLogin == null) {
			return LOGIN;
		}else {
			HttpSession session = ServletActionContext.getRequest().getSession();
		//  �ڿ�ʼѧϰ��дǰ��ʱ��������ö����е����ԣ����������˺ܶ�session���ԣ�����Ŀ�б㲻���޸ģ���һ����Ŀ��SSM���ٸĽ�
			session.setAttribute("userType", "student");
			session.setAttribute("userLogin", studentLogin);
			session.setAttribute("userLoginName", studentLogin.getStudent_name());
			session.setAttribute("userPosition", studentLogin.getStudent_position());
			session.setAttribute("userNumber", studentLogin.getStudent_number());
			return "loginOK";
		}
	}
	//�˳�ϵͳ
	public String exit() {
		ServletActionContext.getRequest().getSession().removeAttribute("userLogin");
		return LOGIN;
	}
	//�޸�����
	public String changePassword() {
		// �޸�������Ҫ������ԭʼ���룬������������
		String originalPassword = ServletActionContext.getRequest().getParameter("originalPassword");
		String changedPassword = ServletActionContext.getRequest().getParameter("changedPassword");		
		// ��õ�½����
		Student changingStudent = null;
		changingStudent = (Student) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
//		System.out.println(changingStudent);
		if(changingStudent != null ) {
			// ��֤�����ԭʼ���룬 �����ȷ������Ը������룬��֮����ԭʼ�������
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
	//�����û�ѧ�Ų���Student����
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
	
	private File upload;              // Ҫ�ϴ����ļ�
	private String uploadFileName;    // �ϴ��ļ����ļ���
	@SuppressWarnings("unused")
	private String uploadContentType; //�ϴ��ļ����ļ�����
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	//�ϴ�����ͷ�񣬲����������
	public String changePersonalProfile() throws IOException {
		Student originalStudent = (Student) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		student.setStudent_password(originalStudent.getStudent_password());
		student.setStudent_salt(originalStudent.getStudent_salt());
		if(uploadFileName != null) {
			// ���ͷ��·����Ϊ�գ�����ɾ��ԭͷ��
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


