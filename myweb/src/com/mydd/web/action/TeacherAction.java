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

	//ע��UserService����
	private TeacherService teacherService;
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	// ��ʦע��action
	public String teacherSignUp() {
		Teacher teacherUser = teacher;
//		System.out.println("==========");
//		System.out.println(teacher);
//		System.out.println("==========");
		teacherService.save(teacherUser);
		return LOGIN;
	}
	
	// ����ѧ�Ż򹤺��Ƿ��Ѿ���ע��
	@SuppressWarnings("null")
	public String checkNumber() {
		// ����ҵ��㣬��ѯ
		Teacher teacherCheck = teacherService.checkNumber(teacher.getTeacher_number());
		// ��ȡresponse����
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			// ��ȡ�����
			PrintWriter writer = response.getWriter();
			// �����жϣ�������ڶ�Ӧ���˺ţ���������Ϊ�գ���ôע����Ϊ�൱���Ǽ����˺�
			if(teacherCheck == null || teacherCheck.getTeacher_password() == null){
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
	// ��½ϵͳ
	public String login() {
		// ����ҵ��㣬��ѯ
		String number = (String) ServletActionContext.getRequest().getParameter("user_number");
		teacher.setTeacher_number(number);
		String password = (String) ServletActionContext.getRequest().getParameter("user_password");
		teacher.setTeacher_password(password);
		Teacher teacherLogin = teacherService.login(teacher);
		// ���û������û�����ҳ�淵�ص�½ҳ��
		if(teacherLogin == null) {
			return LOGIN;
		}else {
			HttpSession session = ServletActionContext.getRequest().getSession();
			//  �ڿ�ʼѧϰ��дǰ��ʱ��������ö����е����ԣ����������˺ܶ�session���ԣ�����Ŀ�б㲻���޸ģ���һ����Ŀ��SSM���ٸĽ�
			session.setAttribute("userType", "teacher");
			session.setAttribute("userLogin", teacherLogin);
			session.setAttribute("userLoginName", teacherLogin.getTeacher_name());
			session.setAttribute("userPosition", teacherLogin.getTeacher_position());
			session.setAttribute("userNumber", teacherLogin.getTeacher_number());
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
		Teacher changingTeacher = null;
		changingTeacher = (Teacher) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		System.out.println(changingTeacher);
		if(changingTeacher != null ) {
			// ��֤�����ԭʼ���룬 �����ȷ������Ը������룬��֮����ԭʼ�������
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
	//�����û����Ų���Teacher����
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
		Teacher originalTeacher = (Teacher) ServletActionContext.getRequest().getSession().getAttribute("userLogin");
		teacher.setTeacher_password(originalTeacher.getTeacher_password());
		teacher.setTeacher_salt(originalTeacher.getTeacher_salt());
		if(uploadFileName != null) {
			// ���ͷ��·����Ϊ�գ�����ɾ��ԭͷ��
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




