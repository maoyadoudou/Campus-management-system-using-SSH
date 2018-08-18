<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-3.3.0.min.js"></SCRIPT>
<SCRIPT>
	$(document).ready(function(){
		//给注册添加onclick事件，学生和老师进入不同的jsp页面
		$("#signup").click(function(){
			if($('input:radio:checked').val()=="student"){
				window.location.href="${ pageContext.request.contextPath }/studentsignup.jsp";
			}else{
				window.location.href="${ pageContext.request.contextPath }/teachersignup.jsp";
			}
		});
	});
	// 根据登录者选择 学生 或 老师 来确定进入不同的action
	$(document).ready(function(){$("FORM").submit(function(){
		if ($('input:radio:checked').val()=="teacher"){
		   $("FORM").attr("action","${ pageContext.request.contextPath }/teacher_login.action");
		   $("FORM").submit();
		}
		else
		{
		   $("FORM").attr("action","${ pageContext.request.contextPath }/student_login.action");
		   $("FORM").submit();
		}
		});
	});
</SCRIPT>
<STYLE type=text/css>
TD {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体;
}
</STYLE>
<META content="MSHTML 6.00.6000.16809" name=GENERATOR></HEAD>
<BODY>
<FORM id="form1" name="form1" action="" method=POST target="_parent" >
<DIV>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  <TBODY>
  <TR>
    <TD style="HEIGHT: 105px"><IMG src="" border=0></TD></TR>
  <TR>
    <TD background=images/sqlogin2.jpg height=300>
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=300></TD>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              <TR>
                <TD style="HEIGHT: 28px" width=80>学号/工号：</TD>
                <TD style="HEIGHT: 28px" width=150><INPUT id=txtName style="WIDTH: 130px" type=text name="user_number"></TD>
                <TD style="HEIGHT: 28px" width=370><SPAN id=RequiredFieldValidator3 style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入登录名</SPAN></TD></TR>
              <TR>
                <TD style="HEIGHT: 28px">登录密码：</TD>
                <TD style="HEIGHT: 28px"><INPUT id=txtPwd style="WIDTH: 130px" type=password name="user_password"></TD>
                <TD style="HEIGHT: 28px"><SPAN id=RequiredFieldValidator4 style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入密码</SPAN></TD>
              </TR>
	          <TR>
			    <TD style="HEIGHT: 28px" id = cName >职位：</TD>
			    <TD style="HEIGHT: 28px"><INPUT style = "WIDTH: 30px" type = radio name = "position" id="studentradio" value = "student" checked="checked">学生
			    <INPUT style = "WIDTH: 30px" type = radio name = "position" id="teacherradio" value = "teacher" >教师
			    </TD>
			  </TR> 
              <TR>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD></TR>
              <TR>
              	<TABLE>
              		<TR>
	              		<TD width = 20></TD>
		                <TD width = 50><INPUT type="submit" id="login" value="登陆系统"></TD>
		                <TD width = 20></TD>
		                <TD width = 50><INPUT type="button" id="signup" value="注册新账号" ></TD>
	            	</TR>
              	</TABLE>                
              </TR>
              </TBODY>
            </TABLE>
          </TD>
        </TR>
        </TBODY>
      </TABLE>
    </TD>
  </TR>
  </TBODY>
</TABLE>
</DIV>
</FORM>
</BODY>
</HTML>