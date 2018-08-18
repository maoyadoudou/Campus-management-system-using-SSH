<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd" >
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-3.3.0.min.js"></script>
<script type="text/javascript">
	// 在课表上显示授课信息
    $(document).ready(function(){
//    	alert(1);
		url="${ pageContext.request.contextPath }/curriculum_findByTeacherCurriculum.action";
		param={"teacher_number":${userNumber}};
//		$(".curriculum_list_body").remove();
		$.post(url,param,function(data){
			if(data != null){
//			alert(data);
//				var data_new = JSON.parse(data);
				// 遍历该老师的所有可选课程
				$.each(data,function(k,v){
					// 已选人数
					var curriculum_selectedNumber = v.curriculum_selected_number;
					// 提取已选人数大于0的课程
//					alert(v.curriculum_selected_number);
					if(curriculum_selectedNumber > 0){
						// 课程名称
						var curriculumName = v.curriculum_name;
						// 授课地点
						var tempCurriculumLocation = v.curriculum_location.split("(");
						var curriculumLocation = tempCurriculumLocation[0];
						// 授课时间
						var curriculumTime = v.curriculum_time;
						// 课程ID
						var curriculumId = v.curriculum_id;
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
						// 填入课程表中
						var curriculum_content = curriculumName + "<br/>" + curriculumLocation + "(" + curriculum_selectedNumber + ")" ;
//						alert(curriculum);
						$('#td_' + week_number + time_number).html(curriculum_content);
					}
				});
			}
		});
	});
</script>
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
<table style="background-color:#FFE87C"  width="100%" height="700px">
  <tr style = "HEIGHT:10px"><td></td><td></td><td></td></tr>
  <tr>
    <td style = "WIDTH: 10px"></td>
    <td>
		<table id=curriculum_list width="80%">
		  <tr style = "HEIGHT: 70px">
		    <th style = "WIDTH: 9%">课程表</th>
		    <th style = "WIDTH: 13%">星期一</th>
		    <th style = "WIDTH: 13%">星期二</th>
		    <th style = "WIDTH: 13%">星期三</th>
		    <th style = "WIDTH: 13%">星期四</th>
		    <th style = "WIDTH: 13%">星期五</th>
		    <th style = "WIDTH: 13%">星期六</th>
		    <th style = "WIDTH: 13%">星期日</th>
		  </tr>
		  <tr style = "HEIGHT: 70px">
		    <td align=center>第一大节（8:00~9:40）</td>
		    <td id=td_11 align=center></td>
		    <td id=td_21 align=center></td>
		    <td id=td_31 align=center></td>
		    <td id=td_41 align=center></td>
		    <td id=td_51 align=center></td>
		    <td id=td_61 align=center></td>
		    <td id=td_71 align=center></td>
		  </tr>
		  <tr style = "HEIGHT: 70px">
		    <td align=center>第二大节（10:00~11:40）</td>
		    <td id=td_12 align=center></td>
		    <td id=td_22 align=center></td>
		    <td id=td_32 align=center></td>
		    <td id=td_42 align=center></td>
		    <td id=td_52 align=center></td>
		    <td id=td_62 align=center></td>
		    <td id=td_72 align=center></td>
		  </tr>
		  <tr style = "HEIGHT: 70px">
		    <td align=center>第三大节（13:30~15:10）</td>
		    <td id=td_13 align=center></td>
		    <td id=td_23 align=center></td>
		    <td id=td_33 align=center></td>
		    <td id=td_43 align=center></td>
		    <td id=td_53 align=center></td>
		    <td id=td_63 align=center></td>
		    <td id=td_73 align=center></td>
		  </tr>
		  <tr style = "HEIGHT: 70px">
		    <td align=center>第四大节（15:30~17:10）</td>
		    <td id=td_14 align=center></td>
		    <td id=td_24 align=center></td>
		    <td id=td_34 align=center></td>
		    <td id=td_44 align=center></td>
		    <td id=td_54 align=center></td>
		    <td id=td_64 align=center></td>
		    <td id=td_74 align=center></td>
		  </tr>
		  <tr style = "HEIGHT: 70px">
		    <td align=center>第五大节（19:00~21:35）</td>
		    <td id=td_15 align=center></td>
		    <td id=td_25 align=center></td>
		    <td id=td_35 align=center></td>
		    <td id=td_45 align=center></td>
		    <td id=td_55 align=center></td>
		    <td id=td_65 align=center></td>
		    <td id=td_75 align=center></td>
		  </tr>
		</table>
    </td>
    <td style = "WIDTH:10px"></td>
  </tr>
  <tr style = "HEIGHT:270px"><td></td><td></td><td></td></tr>
</table>
</BODY>
</HTML>
