<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-3.3.0.min.js"></script>
<STYLE type=text/css>
.blue {
        color:blue;
}
.red {
        color:red;
}
TD {
	FONT-SIZE: 20px;
	COLOR: black;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}
INPUT {
	FONT-SIZE: 20px;
	COLOR: black;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}
</STYLE>
<script type="text/javascript">
	$(document).ready(function(){
		url="${ pageContext.request.contextPath }/${ userType }_findByNumber.action";
		param={"student_number":${userNumber}};
		$.post(url,param,function(data){
			//姓名
			$("#student_name").html(data[0].student_name);
			//性别
			$("#student_sex").html(data[0].student_sex);
			//个人头像
			if(data[0].student_avatar == null){
				$("#student_avatar").attr("src","/image/default.jpg");
			}else{
				var path = data[0].student_avatar.split("JAVA");
				$("#student_avatar").attr("src",path[1]);	
			}
			//职位
			if(data[0].student_position == "0"){
				$("#student_position").text("学生");
			}else{
				$("#student_position").text("注册故障，请联系管理员");
			}
			$("#student_birthday").html(data[0].student_birthday);
			$("#student_phone").html(data[0].student_phone);
			$("#student_mail").html(data[0].student_mail);
		});
	});
/* 早先是使用的是这样方式，加载失败，我认为原因是上面的jquery是页面加载完才运行，因此下面这条语句中data[0].student_avatar 为空
 * <TD rowspan=3 align=center><img id=studentAvatar src= <c:if test="${data[0].student_avatar == null}">"${pageContext.request.contextPath }/images/default.jpg"</c:if> <c:if test="${data[0].student_avatar != null}">"data[0].student_avatar"</c:if> height=150px/></TD>  */
</script>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY style="background-color:#2A8DC8">
<TABLE id = "surroundpartern" style="background-color:#FFE87C"  width="100%" height="100%">
	<TR style = "HEIGHT: 30%"><TD style = "WIDTH: 30%"></TD><TD style = "WIDTH: 40%"></TD><TD style = "WIDTH: 30%"></TD></TR>
	<TR><TD></TD>
	<TD class = "signTable" style = "WIDTH: 450px; HEIGHT: 600px">
		<TABLE cellSpacing=0 cellPadding=0  border=2 style = "WIDTH: 450px; HEIGHT: 600px">
			<TR>
				<TD align=center>姓名：</TD>
				<TD align=center>${userLogin.student_name}</TD>
				<TD rowspan=3 align=center width = "170px"><img id=student_avatar src="" height=150px/></TD>
			</TR>
			<TR>
				<TD align=center>学号：</TD>
				<TD id=student_number align=center>${userNumber}</TD>				
			</TR>
			<TR>
				<TD align=center>性别：</TD>
				<TD id=student_sex align=center></TD>				
			</TR>
			<TR>
				<TD align=center>职位：</TD>
				<TD id=student_position align=center></TD>
				<TD align=center>头像</TD>
			</TR>
			<TR>
				<TD align=center>生日：</TD>
				<TD id=student_birthday colspan=2 align=center></TD>				
			</TR>			
			<TR>
				<TD align=center>电话：</TD>
				<TD id=student_phone colspan=2 align=center></TD>				
			</TR>	
			<TR>
				<TD align=center>Email：</TD>
				<TD id=student_mail colspan=2 align=center></TD>				
			</TR>			
			<TR>
				<td colspan=3>
				&nbsp;&nbsp;&nbsp;<A href="${ pageContext.request.contextPath }/jsp/${ userType }/changProfile.jsp" target="main">修改</A>
				</TD>
			</tr>
		</TABLE>
	</TD>
	<TD></TD>
	</TR>
	<TR style = "HEIGHT: 30%"><TD></TD><TD></TD><TD></TD>
	</TR>
</TABLE>
</BODY>
</HTML>
