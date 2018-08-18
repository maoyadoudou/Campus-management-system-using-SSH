<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd" >
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-3.3.0.min.js"></SCRIPT>
<SCRIPT type="text/javascript">
    //	var curriculumList;
	// 显示可选课程信息
    $(document).ready(function(){
		// 捕获.curriculum_show点击事件
		url="${ pageContext.request.contextPath }/course_findByTeacher.action";
		param={"teacher_id": ${userLogin.teacher_id}};
		$.post(url,param,function(data){
			if(data != null){
				$.each(data,function(k,v){
					var courseScore;
					if (v.course_score == -1){
						courseScore = "未打分"
					}else{
						courseScore = v.course_score;
					}
					//加载课程名称和ID、上课时间地点、学生姓名、已选人数和最大人数
					var courseName = v.course_name;
					var courseStudentName = v.student.student_name;
					var courseStudentNnumber = v.student.student_number;
					var courseTime = v.course_time;
					var courseId = v.course_id;
//					var courseTeacher = v.course_teacher;
					//将表中周X第Y节中的X汉字提取出来转换成阿拉伯数字，为了避免学生同一时间选了两门课，需要利用时间进行判定
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
					var course_content = '<TR id=course_tr_' + courseId + ' class=coursescore_list_body>\
                                            <td id=courseId name="course_curriculum" name=course_id>' + courseId + '</td>\
                                            <td id=courseTime_' + courseId + 'name=course_time>' + courseTime + '</td>\
                                            <td id=courseName_' + courseId + 'name=course_name>' + courseName + '</td>\
											<td id=courseName_' + courseId + 'name=student_name>' + courseStudentName + '(' + courseStudentNnumber + ')' + '</td>\
											<td><input id=score_' + courseId + ' type=text name=course_score class=select_deleteCurriculum value=' + courseScore + '></td>\
                                            <td><input id=botton_' + courseId + ' type=button class=submitScore value="提交"></td>\
											<td><span id=span_' + courseId + '></span></td>\
                                          </TR>';
					$('#course_list').append(course_content);
				});
			}
		});
	});
	
	// 提交成绩信息
    $(document).ready(function(){
		// 捕获.submitScore点击事件
		$(document).on('click','.submitScore',function(){			
			// 获得该change事件对应的父类table对象中courseId值
			var courseId = $(this).attr("id").match(/\d+/g).map(Number);
			// 这里偷懒，用变量a表示生成的数字
			var a = courseId[0];			
//			alert(a);
			// 判断成绩的内容是否为空，是否为数字
			var score = parseInt($("#score_" + a).val().trim());
//			alert(score);			
			if(score != null && score >= 0 && score <= 100 && !isNaN(score)){
				url="${ pageContext.request.contextPath }/course_submitScore.action";
				param={"course_id":a,"course_score":score};
				$.post(url,param,function(data){
				    if(data == "success"){
					    $("#span_" + courseId).text("提交成功");
					}else{
					    $("#span_" + courseId).text("提交失败");						
					}
			    });
			}else{
				$("#span_" + courseId).text("成绩内容有问题");	
			}
		});
	});
</SCRIPT>
<STYLE type=text/css>
INPUT {
	FONT-SIZE: 18px;
	COLOR: black;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}
#course_list
  {
  font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
  width:100%;
  border-collapse:collapse;
  }  
#course_list td
  {
  font-size:1em;
  border:2px solid #98bf21;
  padding-top:5px;
  padding-bottom:4px;
  background-color:#F0FFFF;
  }	
#course_list th 
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
<TABLE id="course_list" style="border-collapse:collapse background-color: yellow">
	<TR id="course_list_1" class=curriculum_list>
		<th>课程编号</th>
		<th>时间</th>
		<th>课程名称</th>
		<th>学生姓名</th>
		<th>成绩</th>
		<th>操作</th>
		<th>结果</th>
	</TR>
</TABLE>
</BODY>
</HTML>
