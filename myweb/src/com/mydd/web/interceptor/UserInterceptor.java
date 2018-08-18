package com.mydd.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * �û���������ݣ�ѧ������ʦ�����û���½�������ˡ�userType�������û�����һͬ����
 * ��˵�ȡ��userType�������Ϊ���򷵻ص�½���棬��Ϊ������Լ�����һ��������
 * @author XPS15
 */
public class UserInterceptor extends MethodFilterInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6485170474271853853L;
	/**
	 * ����Action����
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		String position = (String) ServletActionContext.getRequest().getSession().getAttribute("userType");
		if(position == null || position.length() < 1) {
			return "login";
		}else {
			return invocation.invoke();			
		}
	}
}
