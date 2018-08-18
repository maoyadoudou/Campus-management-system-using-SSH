<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<STYLE type="text/javascript">
    //检验密码位数
	$(document).ready(function(){
		$("changed_password").blur(function checkFunction(){
			var changedPassword = document.getElementById("changed_password").value;
			if(changedPassword.indexOf(" ") >= 0){
				$("#checkPswd").addClass("error");
				$("#chgpswd_span").html("密码里不能有空格！");
				$("#chgpswd_span").addclass("red");
			}else{
				if(changedPassword.length >3 && changedPassword.length < 17 ){
				$("#checkPswd").removeClass("error");
				$("#chgpswd_span").html("可行");
				$("#chgpswd_span").addclass("blue");
				}else{
				$("#checkPswd").addClass("error");
				$("#chgpswd_span").html("密码长度有问题！");
				$("#chgpswd_span").addclass("red");
			    }			
			}
		});
	});
	
	//校验两次输入的密码是否一致
	$(document).ready(function(){
		$("repeatchanged_password").blur(function comfirmFunction(){
			var password = document.getElementById("changed_password").value;
			var comfPassword = document.getElementById("repeatchanged_password").value;
			if(comfPassword != password){
				$("#rechgpswd_span").removeClass("error");
				$("#repeatchanged_password").html("可行");
				$("#repeatchanged_password").addclass("blue");
			}else{
				$("#rechgpswd_span").addClass("error");
				$("#repeatchanged_password").html("密码长度有问题！");
				$("#repeatchanged_password").addclass("red");
			}
		});
	});
	//校验修改信息是否正确
	$(document).ready(function(){
		$("submit").blur(function validateSignUp(){
			var originalPassword = document.getElementById("original_password");
			var changedPassword = document.getElementById("changed_password").value;
			var comfPassword = document.getElementById("repeatchanged_password").value;
			if(digitNumber.indexOf(" ") >= 0){
				$("#submitSpan").html("&nbsp;&nbsp;&nbsp;&nbsp;密码里不能有空格！");
				$("#submitSpan").addclass("red");
				return false;
			} else {
				if(originalPassword.trim() != null && originalPassword.length > 0 && changedPassword != null && comfPassword != null){
					if($(".error").size>0){
						$("#submitSpan").html("&nbsp;&nbsp;&nbsp;&nbsp;新密码有误！");
						$("#submitSpan").addclass("red");
						return false；
					}else{
						return true；
					}
				}				
			}
		});
	});

</STYLE>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY style="background-color:#2A8DC8">
<FORM id=form1 name=form1 action="${ pageContext.request.contextPath }/teacher_changePassword.action" method=post target="main" onsubmit="return validateSignUp()">
<TABLE id = "surroundpartern" style="background-color:#FFE87C"  width="100%" height="100%">
	<TR style = "HEIGHT: 30%"><TD style = "WIDTH: 30%"></TD><TD style = "WIDTH: 40%"></TD><TD style = "WIDTH: 30%"></TD></TR>
	<TR><TD></TD>
	<TD class = "signTable" style = "WIDTH: 450px; HEIGHT: 300px">
		<TABLE cellSpacing=0 cellPadding=5 width=450px align=left border=0>
		<TBODY>
			<TR>
				<TD width=100px > 原密码：</TD>
				<TD align="left"><INPUT id=original_password type=text maxlength="16" name="originalPassword"><span id=oripswd_span></span></TD>
			</TR>
			<TR>
				<TD width=100px > 新密码：</TD>
				<TD align="left"><INPUT id=changed_password type=text maxlength="16" name="changedPassword"><span id=chgpswd_span></span></TD>
			</TR>
			<TR>	
				<TD width=100px > 再次输入：</TD>
				<TD align="left"><INPUT id=repeatchanged_password type=text maxlength="16" name="repeatChangedPassword"><span id=rechgpswd_span></span></TD>
			</TR>
			<TR>
				<TD align="left"><INPUT id=submit type="submit" value="提交修改" ><span id = submitSpan></span></TD>
			</tr>
		</TBODY>
	</table>
	</TD>
	<TD></TD>
	</TR>
	<TR style = "HEIGHT: 30%"><TD></TD><TD></TD><TD></TD>
	</TR>
</TABLE>
</BODY>
</HTML>
