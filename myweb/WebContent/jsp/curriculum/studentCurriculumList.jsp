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
		url="${ pageContext.request.contextPath }/curriculum_findAllCurriculum.action";
		$.post(url,function(data){
			if(data != "fail"){
				// 给可选课程按照加载顺序编号
//				var curriculm_serial_number = 1;
				$.each(data,function(k,v){
					//加载课程名称和ID、上课时间地点、授课老师、已选人数和最大人数
					var curriculumName = v.curriculum_name;
					var curriculumLocation = v.curriculum_location;
					var curriculumTime = v.curriculum_time;
					var curriculumId = v.curriculum_id;
					var curriculumTeacher = v.teacher_name;
					var curriculumMaxNumber = v.curriculum_max_number;
					var curriculumSelectedNumber = v.curriculum_selected_number;
					//将表中周X第Y节中的X汉字提取出来转换成阿拉伯数字，为了避免学生同一时间选了两门课，需要利用时间进行判定
					var week_name_collector = ["一","二","三","四","五","六","日"];
					var week_number_word = curriculumTime.substr(1,1);
					var time_number = curriculumTime.substr(3,1);
					var week_number;
					for (var i = 0; i < 7; i++){
						if(week_name_collector[i]==week_number_word){
							week_number = i + 1;
						}
					}
//						alert(week_number_word);
					var curriculum_content = '<TR id=curriculum_tr_' + curriculumId + ' class=curriculum_list_body>\
                                                <td id=curriculumId name="course_curriculum">' + curriculumId + '</td>\
                                                <td id=curriculumTime_' + curriculumId + '>' + curriculumTime + '</td>\
                                                <td id=curriculumLocation_' + curriculumId + '>' + curriculumLocation + '</td>\
                                                <td id=curriculumName_' + curriculumId + '>' + curriculumName + '</td>\
                                                <td id=curriculumTeacher_' + curriculumId + '>' + curriculumTeacher + '</td>\
                                                <td id=curriculumSelectedNumber_curriculumMaxNumber_' + curriculumId + '>' + curriculumSelectedNumber+ '/' + curriculumMaxNumber + '</td>\
                                                <td><input id=botton_' + curriculumId + ' type=button class=select_deleteCurriculum value="选择"</td>\
												<td><span id=span_' + curriculumId + '></span></td>\
                                              </TR>';
					$('#curriculum_list').append(curriculum_content);
					//将json逐个导入curriculumList中
//					curriculumList[curriculm_serial_number]=v;
//					curriculm_serial_number = curriculm_serial_number + 1;
				});
			}
		});
    	var allSelectedCourse;
    	url1="${ pageContext.request.contextPath }/course_findAllCourse.action";
    	$.post(url1,function(data1){
//    		alert(data1);
			allSelectedCourse = data1;
			if(allSelectedCourse != null && allSelectedCourse.length > 0){
				for(var i = 0; i < allSelectedCourse.length; i++){
					$("#botton_" + allSelectedCourse[i].curriculum.curriculum_id).attr("value","弃选");
				}
			}
    	});
	});
	
		// 删除指定授课信息
    $(document).ready(function(){
		// 捕获.deleteCurriculum点击事件
		$(document).on('click','.select_deleteCurriculum',function(){
			// 获得该click事件对应的父类tr对象中curriculumId值
			var select_tr_number = $(this).parents("tr").attr("id").match(/\d+/g).map(Number);
			// 这里偷懒，用变量a表示curriculum_id
			var a = select_tr_number[0];
			var curriculumName = $("#curriculumName_" + a).text();
			var curriculumTeacher = $("#curriculumTeacher_" + a).text();
			var curriculumTime = $("#curriculumTime_" + a).text();
			// 获取已选人数/最大人数的数值
			var curriculumSelectedSlashMax = $("#curriculumSelectedNumber_curriculumMaxNumber_" + a).text().match(/\d+/g).map(Number);
			// 已选人数
			var curriculumSelectedNumber = curriculumSelectedSlashMax[0];
			// 最大人数
			var curriculumMaxNumber = curriculumSelectedSlashMax[1];
//			alert(curriculumName + " " + curriculumTeacher + " " + a);
			// 如果选项为选择，则先判断已选人数是否达到最大人数，没有达到才能执行选课操作，操作成功后将选项名称变为弃选。如果选项为弃选，则执行弃选操作，成功后将选项名称变为选择
			// 这个判断是否达到最大选课人数的功能有些之后，在Dao层也同样有这样的判断，前端的判断是为了避免同时过多请求行为。
			if($("#botton_" + a).attr("value") == "选择"){
				if(curriculumSelectedNumber < curriculumMaxNumber){
					url="${ pageContext.request.contextPath }/course_saveCourse.action";
					param={"curriculum_id":a,"curriculum_name":curriculumName,"curriculum_teacher":curriculumTeacher,"curriculum_time":curriculumTime};
					$.post(url,param,function(data){
						if(data == "success"){
							$("#botton_" + a).attr("value","弃选");
							$("#span_" + a).html('<font style="color:blue">选课成功</font>');
						}else if(data == "conflict"){
							$("#span_" + a).html('<font style="color:red">该时段你以选了其他课</font>');
						}else{
							$("#span_" + a).html('<font style="color:red">该课人数已达上限</font>');
						}
					});
				}
			}else{
				url="${ pageContext.request.contextPath }/course_deleteCourse.action";
				param={"curriculum_id":a,"curriculum_name":curriculumName,"curriculum_teacher":curriculumTeacher};
//				alert(a);
				$.post(url,param,function(data){
					if(data == "success")
						$("#botton_" + a).attr("value","选择");
						$("#span_" + a).html('<font style="color:blue">弃选成功</font>');
				});
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
<TABLE id="curriculum_list" style="border-collapse:collapse background-color: yellow">
	<TR id="curriculum_list_1" class=curriculum_list>
		<TH>课程编号</TH>
		<TH>时间</TH>
		<TH>地点</TH>
		<TH>课程名称</TH>
		<TH>老师姓名</TH>
		<TH>已选人数/最大人数</TH>
		<TH>操作</TH>
		<TH>结果</TH>
	</TR>
</TABLE>
</BODY>
</HTML>
