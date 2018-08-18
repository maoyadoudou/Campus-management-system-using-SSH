<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd" >
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-3.3.0.min.js"></script>
<script type="text/javascript">
//如果原先已经修改过个人信息，则在修改页面的input中将原个人信息设置为默认信息
	$(document).ready(function(){
		url="${ pageContext.request.contextPath }/${ userType }_findByNumber.action";
		param={"teacher_number":${userNumber}};
		$.post(url,param,function(data){
			//姓名
			$("#teacher_name").html(data[0].teacher_name);
			//个人头像
			if(data[0].teacher_avatar == null){
				$("#teacher_avatar").attr("src","${pageContext.request.contextPath }/images/default.jpg");
			}else{
				$("#teacher_avatar").attr("src",data[0].teacher_avatar);	
			}
			//性别
			var sex = data[0].teacher_sex
			var sexradio = document.getElementsByName("teacher_sex");
			if(sex != null ){
				if(sex == "男"){
					sexradio[0].checked=true;
				}else{
					sexradio[1].checked=true;			
				}
			}else{
				sexradio[0].checked=true;
			}
			//职位
			if(data[0].teacher_position == "1"){
				$("#teacher_position").text("教师");
			}else{
				$("#teacher_position").text("注册故障，请联系管理员");
			}
			//个人电话
			var phone = data[0].teacher_phone;
			if(phone != null ){
				document.form1.teacher_phone.value=phone;
		    }
		    //个人邮箱
		    var mail = data[0].teacher_mail;
			if(mail!=null){
				document.form1.teacher_mail.value=mail;
			}
			//出生日期
			var date_year = 0;
			var date_month = 0;
			var date_day = 0;
			//加载填写了生日日期
			var birthday = data[0].teacher_birthday;
			//如果不为空，则提取年月日
			if(birthday != null){
				var date_component = new Array();
				date_component = birthday.split("-");
				date_year = date_component[0];
				date_month = date_component[1];
				date_day = date_component[2];
			}
		    //制作过去100年的下拉选项，并将原生日年份设为默认选项
			var y  = new Date().getFullYear(); 
			for(var i = (y-100); i < y; i++){
				if(i != date_year){
					$("#sYear").append("<option value=" + i + " >" + i + " 年</option>");
				}else{
					$("#sYear").append("<option value=" + i + " selected>" + i + " 年</option>");
				}
			}
			//添加月的下拉列表，并将原生日月份设为默认选项
			for(var i = 1; i < 13; i++){
				if(i != date_month){
					$("#sMonth").append("<option value=" + i + " >" + i + " 月</option>");
				}else{
					$("#sMonth").append("<option value=" + i + " selected>" + i + " 月</option>");
				}
			}
			var Mon_Days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
			//判定是否为闰年
			if (date_year != 0 && date_month != 0){
				if(0 == date_year%4 && (date_year%100 !=0 || date_year%400 == 0)){
					Mon_Days[1] = Mon_Days[1] + 1;
				}
				for(var i = 1; i <= Mon_Days[date_month-1]; i++){
					if(i != date_day){
						$("#sDay").append("<option value=" + i + " >" + i + " 日</option>");
					}else{
						$("#sDay").append("<option value=" + i + " selected>" + i + " 日</option>");
					}
				}				
			}
		});	
	});
	
	$(document).ready(function(){
		$("#form1").submit(function(){
	 		var year = document.getElementById("sYear")[document.getElementById("sYear").selectedIndex].value + "-";
	 		var month = document.getElementById("sMonth")[document.getElementById("sMonth").selectedIndex].value + "-";
	 		var day = document.getElementById("sDay")[document.getElementById("sDay").selectedIndex].value;
			document.getElementById("teacher_birthday").setAttribute("value",year + month + day);
		    return true;
		});
	});
</script>
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
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY style="background-color:#2A8DC8">
<FORM id=form1 name=form1 action="${ pageContext.request.contextPath }/teacher_changePersonalProfile.action" method=post target="main" enctype="multipart/form-data">
	<INPUT id = txtName type = hidden value=${userLogin.teacher_id} Name = "teacher_id" >
	<INPUT id = txtName type = hidden value=${userLogin.teacher_name} Name = "teacher_name" >
	<INPUT id = txtName type = hidden value=${userLogin.teacher_state} Name = "teacher_state" >
	<INPUT id = txtName type = hidden value=${userLogin.teacher_number} Name = "teacher_number" >
	<INPUT id = txtName type = hidden value=${userLogin.teacher_position} Name = "teacher_position" >

<TABLE id = "surroundpartern" style="background-color:#FFE87C"  width="100%" height="100%">
	<TR style = "HEIGHT: 30%"><TD style = "WIDTH: 30%"></TD><TD style = "WIDTH: 40%"></TD><TD style = "WIDTH: 30%"></TD></TR>
	<TR><TD></TD>
	<TD class = "signTable" style = "WIDTH: 450px; HEIGHT: 300px">
		<TABLE cellSpacing=0 cellPadding=0  border=2 style = "WIDTH: 450px; HEIGHT: 600px">
			<TR>
				<TD align=center>姓名：</TD>
				<TD align=center>${userLogin.teacher_name}</TD>
				<TD rowspan=3 align=center width = "170px"><img id=teacher_avatar src="" height=150px/></TD>
			</TR>
			<TR>
				<TD align=center>工号：</TD>
				<TD id=teacher_number align=center>${userNumber}</TD>				
			</TR>
			<TR>
				<TD align=center>性别：</TD>
				<TD align=center>
				<INPUT style = "WIDTH: 30px" type = radio name = "teacher_sex" id="manradio" value = "男" >男
			    <INPUT style = "WIDTH: 30px" type = radio name = "teacher_sex" id="womanradio" value = "女" >女
			    </TD>				
			</TR>
			<TR>
				<TD align=center>职位：</TD>
				<TD id = teacher_position align=center></TD>
				<TD align=left>&nbsp;&nbsp;&nbsp;<INPUT style = "WIDTH: 10px" type = file name = "upload" ></TD>
			</TR>
			<TR>
				<TD align=center>生日：</TD>
				<TD colspan=2 align=center>
				<select id="sYear" name="user_birthyear" onchange="YYYYDD(this.value)" size=1 style="FONT-SIZE:20px">
					<option value="0" >请选择 年</option>
			  	</select>
			    <select id="sMonth" name="user_birthmonth" onchange="MMDD(this.value)" size=1 style="FONT-SIZE:20px">
			   	    <option value="0" >选择 月</option>
			    </select>
			    <select id="sDay" name="user_birthday" onblur = "writeBirthday()" size=1 style="FONT-SIZE:20px">
			        <option value="0" >选择 日</option>
			    </select>
			    <span id = confBirDay></span>
			    <input type="hidden" id="teacher_birthday" name="teacher_birthday" >
			    </TD>				
			</TR>			
			<TR>
				<TD align=center>电话：</TD>
				<TD colspan=2 align=center><INPUT id ="teacher_phone" style = "WIDTH: 300px" type = text name = "teacher_phone" maxLength="20"></TD>				
			</TR>	
			<TR>
				<TD align=center>Email：</TD>
				<TD colspan=2 align=center><INPUT id = teacher_mail style = "WIDTH: 300px" type = text name = "teacher_mail" maxLength="100"></TD>				
			</TR>			
			<TR>
				<td colspan=3>
				&nbsp;&nbsp;&nbsp;<INPUT type="submit" value="提交" ></A>
				</TD>
			</tr>
		</TABLE>
	</TD>
	<TD></TD>
	</TR>
	<TR style = "HEIGHT: 30%"><TD></TD><TD></TD><TD></TD>
	</TR>
</TABLE>
<script type="text/javascript">    
Mon_Days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];   
function YYYYDD(str){ //年发生变化时日期发生变化(主要是判断闰平年)   
     var MMvalue = document.getElementById("sMonth")[document.getElementById("sMonth").selectedIndex].value;   
     if (MMvalue == ""){ 
     	 var e = document.getElementById("sDay"); 
     	 optionsClear(e); 
     	 return;
     }   
     var n = Mon_Days[MMvalue - 1];   
     if (MMvalue ==2 && IsPinYear(str)) 
    	 n++;   
         writeDay(n);
}   
function MMDD(str){   //月发生变化时日期联动   
     var YYYYvalue = document.getElementById("sYear")[document.getElementById("sYear").selectedIndex].value;   
     if (YYYYvalue == ""){ 
    	 var e = document.getElementById("sDay"); 
    	 optionsClear(e); 
    	 return;
     }   
     var n = Mon_Days[str - 1];   
     if (str == 2 && IsPinYear(YYYYvalue)) 
    	 n++;   
     writeDay(n);   
}   
function writeDay(n){   //据条件写日期的下拉框   
     var e = document.getElementById("sDay"); 
     optionsClear(e);   
     for (var i=1; i<(n+1); i++)   
          e.options.add(new Option(" "+ i + " 日", i));   
}   
function IsPinYear(year){ //判断是否闰平年   
     return(0 == year%4 && (year%100 !=0 || year%400 == 0));
}   
function optionsClear(e){  
     e.options.length = 1;   
}
//将选好的出生年月日整合后写入id为teacher_birthday的标签中
 	function writeBirthday(){
 		var birthday = "${userLogin.teacher_birthday}";
 		if(document.getElementById("sYear")[document.getElementById("sYear").selectedIndex].value == null ||
 		 document.getElementById("sMonth")[document.getElementById("sMonth").selectedIndex].value == null ||
 		  document.getElementById("sDay")[document.getElementById("sDay").selectedIndex].value == null){
 			document.getElementById("teacher_birthday").setAttribute("value",birthday);
 		}else{
 		var year = document.getElementById("sYear")[document.getElementById("sYear").selectedIndex].value + "-";
 		var month = document.getElementById("sMonth")[document.getElementById("sMonth").selectedIndex].value + "-";
 		var day = document.getElementById("sDay")[document.getElementById("sDay").selectedIndex].value;
		document.getElementById("teacher_birthday").setAttribute("value",year + month + day);
		}
	}
</script>
</BODY>
</HTML>
