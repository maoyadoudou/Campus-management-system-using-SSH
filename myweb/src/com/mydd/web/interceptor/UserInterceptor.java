package com.mydd.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * 用户有两种身份：学生和老师。在用户登陆均创建了“userType”，它用户对象一同创建
 * 因此调取“userType”，如果为空则返回登陆界面，不为空则可以继续下一个拦截器
 * @author XPS15
 */
public class UserInterceptor extends MethodFilterInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6485170474271853853L;
	/**
	 * 拦截Action方法
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
