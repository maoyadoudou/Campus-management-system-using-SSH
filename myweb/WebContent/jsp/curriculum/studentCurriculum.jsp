<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd" >
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-3.3.0.min.js"></SCRIPT>
<SCRIPT type="text/javascript">
	// 在课表上显示授课信息
    $(document).ready(function(){
		url="${ pageContext.request.contextPath }/course_findByStudent.action";
		param={"student_id":${userLogin.student_id}};
//		$(".curriculum_list_body").remove();
		$.post(url,param,function(data){
			// 遍历该老师的所有可选课程
			$.each(data,function(k,v){
				// 已选人数
				var courseState = v.course_state;
//				alert(courseState)
				// 提取已选人数大于0的课程
				if(courseState == "1"){
					// 课程名称
					var courseName = v.course_name;
					// 授课地点
					var tempCourseLocation = v.curriculum.curriculum_location.split("(");
					var courseLocation = tempCourseLocation[0];
					// 授课时间
					var courseTime = v.course_time;
					// 课程ID
					var courseId = v.course_id;
					var week_name_collector = ["一","二","三","四","五","六","日"];
					var week_number_word = courseTime.substr(1,1);
					var time_number = courseTime.substr(3,1);
					var week_number;
					for (var i = 0; i < 7; i++){
						if(week_name_collector[i]==week_number_word){
							week_number = i + 1;
						}
					}
//						alert(week_number_word);
					// 填入课程表中
					var course_content = courseName + "<br/>" + courseLocation;
					$('#td_' + week_number + time_number).html(course_content);
				}
			});
		});
	});
</SCRIPT>
<STYLE type=text/css>
INPUT {
	FONT-SIZE: 18px;
	COLOR: black;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}
#curriculum_list
  {
  font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
  width:100%;
  border-collapse:collapse;
  }  
#curriculum_list td
  {
  font-size:1em;
  border:2px solid #98bf21;
  padding-top:5px;
  padding-bottom:4px;
  background-color:#F0FFFF;
  }	
#curriculum_list th 
  {
  border:2px solid #98bf21;
  font-size:1.1em;
  text-align:center;
  padding-top:5px;
  padding-bottom:4px;
  background-color:#A7C942;
  color:#ffffff;
  }
</STYLE>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY style="background-color:#2A8DC8">
<TABLE style="background-color:#FFE87C"  width="100%" height="700px">
  <TR style = "HEIGHT:10px"><TD></TD><TD></TD><TD></TD></TR>
  <TR>
    <TD style = "WIDTH: 10px"></TD>
    <TD>
		<TABLE id=curriculum_list width="80%">
		  <TR style = "HEIGHT: 70px">
		    <TH style = "WIDTH: 9%">课程表</TH>
		    <TH style = "WIDTH: 13%">星期一</TH>
		    <TH style = "WIDTH: 13%">星期二</TH>
		    <TH style = "WIDTH: 13%">星期三</TH>
		    <TH style = "WIDTH: 13%">星期四</TH>
		    <TH style = "WIDTH: 13%">星期五</TH>
		    <TH style = "WIDTH: 13%">星期六</TH>
		    <TH style = "WIDTH: 13%">星期日</TH>
		  </TR>
		  <TR style = "HEIGHT: 70px">
		    <TD align=center>第一大节（8:00~9:40）</TD>
		    <TD id=td_11 align=center></TD>
		    <TD id=td_21 align=center></TD>
		    <TD id=td_31 align=center></TD>
		    <TD id=td_41 align=center></TD>
		    <TD id=td_51 align=center></TD>
		    <TD id=td_61 align=center></TD>
		    <TD id=td_71 align=center></TD>
		  </TR>
		  <TR style = "HEIGHT: 70px">
		    <TD align=center>第二大节（10:00~11:40）</TD>
		    <TD id=td_12 align=center></TD>
		    <TD id=td_22 align=center></TD>
		    <TD id=td_32 align=center></TD>
		    <TD id=td_42 align=center></TD>
		    <TD id=td_52 align=center></TD>
		    <TD id=td_62 align=center></TD>
		    <TD id=td_72 align=center></TD>
		  </TR>
		  <TR style = "HEIGHT: 70px">
		    <TD align=center>第三大节（13:30~15:10）</TD>
		    <TD id=td_13 align=center></TD>
		    <TD id=td_23 align=center></TD>
		    <TD id=td_33 align=center></TD>
		    <TD id=td_43 align=center></TD>
		    <TD id=td_53 align=center></TD>
		    <TD id=td_63 align=center></TD>
		    <TD id=td_73 align=center></TD>
		  </TR>
		  <TR style = "HEIGHT: 70px">
		    <TD align=center>第四大节（15:30~17:10）</TD>
		    <TD id=td_14 align=center></TD>
		    <TD id=td_24 align=center></TD>
		    <TD id=td_34 align=center></TD>
		    <TD id=td_44 align=center></TD>
		    <TD id=td_54 align=center></TD>
		    <TD id=td_64 align=center></TD>
		    <TD id=td_74 align=center></TD>
		  </TR>
		  <TR style = "HEIGHT: 70px">
		    <TD align=center>第五大节（19:00~21:35）</TD>
		    <TD id=td_15 align=center></TD>
		    <TD id=td_25 align=center></TD>
		    <TD id=td_35 align=center></TD>
		    <TD id=td_45 align=center></TD>
		    <TD id=td_55 align=center></TD>
		    <TD id=td_65 align=center></TD>
		    <TD id=td_75 align=center></TD>
		  </TR>
		</TABLE>
    </TD>
    <TD style = "WIDTH:10px"></TD>
  </TR>
  <TR style = "HEIGHT:270px"><TD></TD><TD></TD><TD></TD></TR>
</TABLE>
</BODY>
</HTML>
