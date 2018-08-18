<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd" >
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-3.3.0.min.js"></SCRIPT>
<STYLE type=text/css>
TD {
	FONT-SIZE: 20px; COLOR: black; FONT-FAMILY: 宋体;
}
#cName {
	FONT-SIZE: 20px; COLOR: black; FONT-FAMILY: 宋体; WIDTH: 120px; text-align: left;
}
.s1 {
	FONT-SIZE: 20px; COLOR: black; FONT-FAMILY: 宋体;
}
#surroundpartern{
	WIDTH: 1700px; HEIGHT: 500px;
}
#cName{
	WIDTH: 110px;
}
.error{
	color:red;
}
.right{
	color:blue;
}
</STYLE>
<META content="MSHTML 6.00.6000.16809" name=GENERATOR>
<SCRIPT type="text/javascript">
//校验是否是中文名字，至少两个汉字
function comfirmUserName(){
	var userName = document.getElementById("txtName").value;
	userName = userName.replace(/\s+/,"")
	var userNameReg = new RegExp(/^[\u4e00-\u9fa5]{2,}$/);
	if(!userNameReg.test(userName)){
		document.getElementById("confName").innerHTML=" 请输入中文名字！ ";
		document.getElementById("confName").style.color = "red";
		$("#confName").addClass("error");
	} else {
		document.getElementById("confName").innerHTML=" 满足条件！ </font>";
		document.getElementById("confName").style.color = "blue";
		$("#confName").removeClass("error");
	}
}

//检验学号
function comfirmUserID(){
	var userCodeNumber = document.getElementById("txtId").value;
	//去除了空格后的user_number值
	var ucN = userCodeNumber.replace(/\s+/,"");
	//校验全部由数字组成的正则表达式，取自www.runoob.com。
	var ucNnumber = new RegExp(/^[0-9]*$/);
	//首先校验是否全部由数字组成
//	alert(ucN);
	if(!ucNnumber.test(ucN)){
		document.getElementById("checkUID").innerHTML="请输入阿拉伯数字 ";
		document.getElementById("checkUID").style.color = "red";
		$("#checkUID").addClass("error");
	}else{ 
		//校验学号长度是否正确
		if(ucN.length <= 0 && ucN.length >= 11){
			document.getElementById("checkUID").innerHTML=" 请重新输入 ";
			document.getElementById("checkUID").style.color = "red";
			$("#checkUID").addClass("error");
		}else{
			//通过ajax，校验该Id是否已经被注册
			var url = "${pageContext.request.contextPath}/student_checkNumber.action";
			var param = {"student_number":ucN};
			$.post(url,param,function(data){
				// 操作data，进行判断
				if(data && data == "no"){
					// 提示
					$("#checkUID").addClass("error");
					$("#checkUID").html("学号已经存在");
				}else{
					$("#checkUID").removeClass("right");
					$("#checkUID").html("可以注册");
				}		
		    });
	    }
    }
}

//检验密码位数
function checkFunction(){
	var digitNumber = document.getElementById("txtPassword").value
	var dN = digitNumber.replace(/\s+/,"");
	if(dN.length >3 && dN.length < 17){
		document.getElementById("checkPswd").innerHTML=" 可行 ";
		document.getElementById("checkPswd").style.color = "blue";
		$("#checkPswd").removeClass("error");
	}else{
		document.getElementById("checkPswd").innerHTML=" 请重新输入 ";
		document.getElementById("checkPswd").style.color = "red";
		$("#checkPswd").addClass("error");
	}
}

//校验两次输入的密码是否一致
function comfirmFunction(){
	var password = document.getElementById("txtPassword").value;
	var comfPassword = document.getElementById("txtConfirmPassword").value;
	if(comfPassword != password){
		document.getElementById("confPswd").innerHTML=" 密码不一致，请重新输入! ";
		document.getElementById("confPswd").style.color = "red";
		$("#confPswd").addClass("error");
	}
	else{
		document.getElementById("confPswd").innerHTML=" OK! ";
		document.getElementById("confPswd").style.color = "blue";
		$("#confPswd").removeClass("error");
	}
}

//校验注册信息
function validateSignUp(){
	comfirmUserName();
	comfirmUserID();
	checkFunction();
	comfirmFunction();
	if($(".error").size>0){
		document.getElementById("submitSpan").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;注册信息有误，请仔细检查！";
		document.getElementById("submitSpan").style.color="red";
		return false;
	}else{
		return true;
	}
}
</SCRIPT>
</HEAD>
<BODY style="background-color:#FFE87C">
<FORM id=form1 name=form1 action="${ pageContext.request.contextPath }/student_studentSignUp.action" method=post target=_self onsubmit="return validateSignUp()">
<TABLE id = "surroundpartern" >
	<TR style = "HEIGHT: 30%"><TD style = "WIDTH: 500px"></TD><TD ></TD><TD></TD>
	</TR>
	<TR><TD></TD>
	<TD class = "signTable" style = "WIDTH: 450px; HEIGHT: 300px">
		<TABLE cellSpacing=0 cellPadding=5 width=450px align=left border=0>	
		  <TR>
		    <TD id = cName >姓名：</TD>
		    <TD><INPUT id = txtName style = "WIDTH: 130px" type = text name = "student_name" onblur = "comfirmUserName(this.value)" maxLength="8"><span id = confName ></span></TD>
		  </TR>
		  <TR>
		    <TD id = cName >学号：</TD>
		    <TD><INPUT id = txtId style = "WIDTH: 130px" type = text name = "student_number" maxLength="10" onblur = "comfirmUserID(this.value)"><span id = checkUID ></span></TD>
		  </TR>
		  <TR>
		    <TD id = cName >密码：</TD>
		    <TD><INPUT id = txtPassword style = "WIDTH: 130px" type = password name = "student_password" onblur = "checkFunction(this.value)" maxLength="16"><span id = checkPswd >&nbsp;密码长度：4-16位</span></TD>
		  </TR>
		  <TR>
		    <TD id = cName >密码确认：</TD>
		    <TD><INPUT id = txtConfirmPassword style = "WIDTH: 130px" type = password name = "student_recheckpassword" onblur = "comfirmFunction()" maxLength="16"><span id = confPswd ></span></TD>
		  <TR>
		    <TD colspan = "2" ><INPUT type="submit" value="注册" ><span id = submitSpan></span></TD>
		  </TR>
		</TABLE>
	</TD>
	<TD></TD>
	</TR>
	<TR><TD></TD><TD></TD><TD></TD>
	</TR>
</TABLE>
</FORM>
</BODY>
</HTML>