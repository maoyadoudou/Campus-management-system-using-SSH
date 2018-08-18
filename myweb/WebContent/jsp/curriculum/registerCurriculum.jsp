<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd" >
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-3.3.0.min.js"></SCRIPT>
<SCRIPT type="text/javascript">
	//设置添加或删除课表填写栏的按键
	$(document).ready(function(){
		$(document).on('click','.addcurriculum',function(){
			// 获取课表中对应星期几和第几大节数值
			var time = this.id.match(/\d+/g).map(Number);
			// 获取第几大节数字信息
			var time_number = time[0];
			// 获取星期几的数字信息，其中1表示星期一, ... ,7表示星期日
			var week_day_number = time[1];
			// 获取星期几的汉字名称
			var week_day_name;
			var week_name_collector = ["一","二","三","四","五","六","日"];
			week_day_name = week_name_collector[week_day_number-1];
			var table_id = "#curriculum_" + week_day_number + time_number + "Table";
			var td_table_id = "#curriculum_" + week_day_number + time_number + "Td";
//			alert(table_id);
//			alert($(table_id).attr("style"));
			// 设置课程填写栏变量
			var largecontent = '<FORM id=form1_' + week_day_number + time_number + ' class=forms action="${ pageContext.request.contextPath }/curriculum_submitCurriculum.action" method=post target="main">\
                                  <li id="l' + week_day_number + time_number + '">\
                                    <table alige=left id="curriculum_' + week_day_number + time_number + 'Table" >\
                                      <TR>\
                                        <TD style="width:103px" class=titlename>\周' + week_day_name + '第' + time_number + '大节\
                                          <input type="hidden" name="curriculum_time" value="周' + week_day_name + '第' + time_number + '大节">\
										  <input type="hidden" name="teacher_name" value=${userLogin.teacher_name}>\
										  <input type="hidden" name="teacher_number" value=${userLogin.teacher_number}>\
                                        </TD>\
                                        <TD>&nbsp;课程名称：<input type=text name="curriculum_name" size="22"></TD>\
								        <TD colspan=2 align=center>&nbsp;授课地点：\
                                          <select id="building' + week_day_number + time_number + '" name="building" class=buildings size=1 style="FONT-SIZE:18px">\
								            <option value="0" >教学楼号</option>\
								            <option value="1" >1号楼</option>\
								            <option value="2" >2号楼</option></select>\
								          <select id="class_room' + week_day_number + time_number + '" name="class_room" class=class_rooms size=1 style="FONT-SIZE:18px">\
								            <option value="0" >教室号-教室容量</option>\
								          </select><span id = "class_loction"></span>\
								          <input type="hidden" id="curriculum_location_' + week_day_number + time_number + '" name="curriculum_location"></TD>\
								        <TD style="width:300px">&nbsp;授课人数：<input id="student_number' + week_day_number + time_number + '" type=text class=student_numbers name="curriculum_max_number" size="2">\
                                          <span id = "studentNumSpan' + week_day_number + time_number + '"></span>\
                                        </TD>\
                                        <TD align=right><INPUT id="button' + week_day_number + time_number + '" type=button class=button_submit value="提交"></TD>\
                                      </TR>\
                                    </table>\
                                  </li>\
                                </FORM>';
			if($(this).attr("value")=="添加"){				
//				$(table_id).attr("style","display: inline");
				// 按照时间顺序插入
				var li_id_value;
				var li_id_time_max=0;
				$("#list li").each(function(){
					var combine_time = parseInt(week_day_number + "" + time_number);
					li_id_value = $(this).attr("id");
					// 去除非数字信息
					var li_id_time = li_id_value.match(/\d+/g).map(Number);
					// 获取星期几和第几大节数字信息
					var li_time_number = parseInt(li_id_time[0]);
					if(combine_time > li_time_number){
						li_id_time_max = li_id_time[0];
					}
				});
				if(li_id_time_max != 0){
					$('#l' + li_id_time_max).after(largecontent);
				}else{
					$(largecontent).prependTo("#list");
				}				
//				$('#curriculum_' + week_day_number + time_number + 'Td').append(largecontent);
//				按键变为清除，颜色变为红色
				$(this).attr("value","清除");
				this.style.color="red";
//				alert($(this).attr("value"));
			}else{
				$(table_id).remove();
				$(this).attr("value","添加");
				this.style.color="black";
//				alert($(this).attr("value"));
			}
		});
	});
	//设置楼编号和教师编号
	var first_building={101:30,102:60,103:60,104:120,201:30,202:60,203:60,204:120,301:30,302:60,303:60,304:120};
	var second_building={101:40,102:80,103:80,104:150,105:40,201:40,202:80,203:80,204:150,205:40,301:40,302:80,303:80,304:150,305:40};
	$(document).ready(function(){
		$(document).on('change','.buildings',function(){
			// 获得该change事件对应的父类table对象中week_day_number + time_number值
			var select_parent_number = $(this).parents("table").attr("id").match(/\d+/g).map(Number);
			var a = select_parent_number[0];
			// 如果building选择变为0，即不选任何教学楼，则class_room下拉框中教室列表需要清空
			if($("#building" + a).children('option:selected').attr("value")==0){
				$("#class_room" + a + " option").remove();
				$("#class_room" + a).append("<option value='0' >教室号-教室容量</option>");
			}
			// 如果选择一号教学楼，则在class_room下拉列表中列出该楼所有教室信息
			if($("#building" + a).children('option:selected').attr("value")==1){
				// 每次选择前先清空原选择信息，以免反复选择后选项重复堆积
				$("#class_room" + a + " option").remove();
				$("#class_room" + a).append("<option value='0' >教室号-教室容量</option>");
				var arr1 = first_building;
				for(var key in arr1){
					$("#class_room" + a).append("<option value=" + key + "> " +key + " (" + arr1[key] + "人)</option>")
				}
			}
			if($("#building" + a).children('option:selected').attr("value")==2){
				// 每次选择前先清空原选择信息，以免反复选择后选项重复堆积
				$("#class_room" + a + " option").remove();
				$("#class_room" + a).append("<option value='0' >教室号-教室容量</option>");
				var arr2 = second_building;
				for(var key in arr2){
					$("#class_room" + a).append("<option value=" + key + "> " +key + " (" + arr2[key] + "人)</option>");
				}
			}
		});		
	});
	// 
	$(document).ready(function(){
		var arr3;
		// 先根据教师容量初步设置最大人数
		$(document).on('blur','.student_numbers',function(){
			// 获得该change事件对应的父类table对象中week_day_number + time_number值
			var select_parent_number = $(this).parents("table").attr("id").match(/\d+/g).map(Number);
			var a = select_parent_number[0];
//			alert(a);
			// 获得教室可以容纳最大人数
			if($("#class_room" + a).children('option:selected').text().match(/\d+/g)){
				var class_information = $("#class_room" + a).children('option:selected').text().match(/\d+/g).map(Number);
//	     		alert(class_information);
				var student_max_number = class_information[1];
			}else{
				student_max_number = null;
			}
			// 教师填写的授课人数
			var student_number = $(this).val();
			var student_number_int = parseInt(student_number);
			var checkNumber = new RegExp(/^[0-9]*$/);
			if(!checkNumber.test(student_number_int)){
				$("#studentNumSpan" + select_parent_number).text("请输数字");
				$("#studentNumSpan" + select_parent_number).attr("style","color:yellow");
				return false;
			}
			if( student_max_number == null || student_max_number.length < 1){
				$("#studentNumSpan" + select_parent_number).text("请先选择教室");
				$("#studentNumSpan" + select_parent_number).attr("style","color:yellow");
				return false;
			}else{
				if(student_max_number >= student_number){
					$("#studentNumSpan" + select_parent_number).text("OK!");
					$("#studentNumSpan" + select_parent_number).attr("style","color:blue");
					return true;
				}else{
					$("#studentNumSpan" + select_parent_number).text("人数超过教室容量");
					$("#studentNumSpan" + select_parent_number).attr("style","color:yellow");
					return false;
				}
			}
		});
	});
	// 提交授课信息
    $(document).ready(function(){
		var arr3;
		// 捕获.button_sumbit点击事件后，先根据教师容量初步设置最大人数
		$(document).on('click','.button_submit',function(){			
			// 获得该change事件对应的父类table对象中week_day_number + time_number值
			var select_table_number = $(this).attr("id").match(/\d+/g).map(Number);
			// 这里偷懒，用变量a表示生成的数字
			var a = select_table_number[0];			
//			alert(a);
			// 获得教室可以容纳最大人数
			if($("#class_room" + a).children('option:selected').text().match(/\d+/g)){
				var class_information = $("#class_room" + a).children('option:selected').text().match(/\d+/g).map(Number);
//	     		alert(class_information);
				var student_max_number = class_information[1];
			}else{
				student_max_number = null;
				return false;
			}
			// 教师填写的授课人数
			var student_number = $("#student_number" + a).val();
			var student_number_int = parseInt(student_number);
//			alert(student_number_int);
			// 检查授课人数是否是数字
			var checkNumber = new RegExp(/^[0-9]*$/);
			if(!checkNumber.test(student_number_int)){
				$("#studentNumSpan" + a).text("请输数字");
				$("#studentNumSpan" + a).attr("style","color:yellow");
			}else{
				// 检查授课人数是否超过了教室容量
				if( student_max_number == null || student_max_number.length < 1){
					$("#studentNumSpan" + a).text("请先选择教室");
					$("#studentNumSpan" + a).attr("style","color:yellow");
				}else{
					$("#studentNumSpan" + a).text("OK!");
					$("#studentNumSpan" + a).attr("style","color:blue");
					if(student_max_number >= student_number){
						// 将授课地址的楼号和教室号合并
						var specific_course_location = $("#building" + a).children('option:selected').text() + $("#class_room" + a).children('option:selected').text();
	//					alert("输入");
						// 将授课地址写入 #curriculum_location_a 中，a为根据授课时间生成的数字
						$("#curriculum_location_" + a).attr("value",specific_course_location);
	//					alert(specific_course_location);
	//					alert($("#curriculum_location").attr("value"));
						var form = $("#form1_" + a);
						$.post(form.attr("action"),form.serialize(),function(data){
							// 操作data，进行判断
							if(data && data == "success1"){
								// 提示
								alert("成功");
							}else{
								alert("失败");
							}
						});
					}else{
						$("#studentNumSpan" + a).text("人数超过教室容量");
						$("#studentNumSpan" + a).attr("style","color:yellow");
					}
				}
			}
		});
	});
	// 显示成功提交的授课信息
    $(document).ready(function(){
		// 捕获.curriculum_show点击事件
		$(document).on('click','.curriculum_show',function(){
			url="${ pageContext.request.contextPath }/curriculum_findByTeacherCurriculum.action";
			param={"teacher_number":${userNumber}};
			$(".curriculum_list_body").remove();
			$.post(url,param,function(data){
				if(data != -1){
					$.each(data,function(k,v){
						var curriculumName = v.curriculum_name;
						var curriculumLocation = v.curriculum_location;
						var curriculumTime = v.curriculum_time;
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
						var curriculum_content = '<TR id=curriculum_tr_' + week_number + time_number + ' class=curriculum_list_body><TD id=curriculumId_' + week_number + time_number + '>' + curriculumId + '</TD><TD>' + curriculumTime + '</TD><TD>' + curriculumLocation + '</TD><TD>' + curriculumName + '</TD><TD><input type=button class=deleteCurriculum value="删除"></TD></TR>';
						$('#curriculum_list').append(curriculum_content);
					});
				}
			});
		});
	});
	

</SCRIPT>
<STYLE type=text/css>
.blue {
        color:blue;
}
.red {
        color:red;
}
TD {
	FONT-SIZE: 18px;
	COLOR: black;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}
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

#curriculum_list th 
  {
  font-size:1em;
  border:1px solid #98bf21;
  padding:3px 7px 2px 7px;
  }  
#curriculum_list td
  {
  font-size:1em;
  border:1px solid #98bf21;
  padding:3px 7px 2px 7px;
  background-color:#FFE87C;
  }
#curriculum_list th 
  {
  font-size:1.1em;
  text-align:left;
  padding-top:5px;
  padding-bottom:4px;
  background-color:#A7C942;
  color:#ffffff;
  }
</STYLE>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY style="background-color:#2A8DC8">
<table style="background-color:#FFE87C"  width="100%" height="300px">
  <TR>
    <TD style = "WIDTH: 10%"></TD>
    <TD>
		<table>
		  <TR style = "HEIGHT: 50px">
		    <TD style = "WIDTH: 10%"></TD>
		    <TD style = "WIDTH: 10%">星期一</TD>
		    <TD style = "WIDTH: 10%">星期二</TD>
		    <TD style = "WIDTH: 10%">星期三</TD>
		    <TD style = "WIDTH: 10%">星期四</TD>
		    <TD style = "WIDTH: 10%">星期五</TD>
		    <TD style = "WIDTH: 10%">星期六</TD>
		    <TD style = "WIDTH: 10%">星期日</TD>
		  </TR>
		  <TR style = "HEIGHT: 50px">
		    <TD align=center>第一大节（8:00~9:40）</TD>
		    <TD><INPUT type=button id=table_1z1span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_1z2span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_1z3span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_1z4span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_1z5span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_1z6span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_1z7span class="addcurriculum" value="添加"></TD>
		  </TR>
		  <TR style = "HEIGHT: 50px">
		    <TD align=center>第二大节（10:00~11:40）</TD>
		    <TD><INPUT type=button id=table_2z1span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_2z2span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_2z3span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_2z4span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_2z5span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_2z6span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_2z7span class="addcurriculum" value="添加"></TD>
		  </TR>
		  <TR style = "HEIGHT: 50px">
		    <TD align=center>第三大节（13:30~15:10）</TD>
		    <TD><INPUT type=button id=table_3z1span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_3z2span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_3z3span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_3z4span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_3z5span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_3z6span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_3z7span class="addcurriculum" value="添加"></TD>
		  </TR>
		  <TR style = "HEIGHT: 50px">
		    <TD align=center>第四大节（15:30~17:10）</TD>
		    <TD><INPUT type=button id=table_4z1span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_4z2span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_4z3span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_4z4span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_4z5span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_4z6span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_4z7span class="addcurriculum" value="添加"></TD>
		  </TR>
		  <TR style = "HEIGHT: 50px">
		    <TD align=center>第五大节（19:00~21:35）</TD>
		    <TD><INPUT type=button id=table_5z1span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_5z2span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_5z3span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_5z4span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_5z5span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_5z6span class="addcurriculum" value="添加"></TD>
		    <TD><INPUT type=button id=table_5z7span class="addcurriculum" value="添加"></TD>
		  </TR>
		  <TR style = "HEIGHT: 20px">
		    <TD ></TD>
		    <TD></TD>
			<TD></TD>
		    <TD></TD>
		    <TD></TD>
		    <TD></TD>
		    <TD></TD>
		  </TR>
		</table>
    </TD>
    <TD style = "WIDTH: 10%"></TD>
  </TR>
</table>
<DIV id="seleted_curriculum_time">
  <ul id="list" style="list-style-type:none">  
  </ul>
</div>
<p><input type="button" class=curriculum_show value="刷新">：显示最新的课程登记清单</p>
<table id="curriculum_list" style="border-collapse:collapse background-color: yellow">
	<TR id="curriculum_list_1" class=curriculum_list>
		<TH>课程编号</TH><TH>时间</TH><TH>地点</TH><TH>课程名称</TH><TH>操作</TH>
	<TR>
</table>
<SCRIPT type="text/javascript">
	// 删除指定授课信息
    $(document).ready(function(){
		// 捕获.deleteCurriculum点击事件
		$(document).on('click','.deleteCurriculum',function(){
			// 获得该click事件对应的父类tr对象中week_day_number + time_number值
			var select_tr_number = $(this).parents("tr").attr("id").match(/\d+/g).map(Number);
			// 这里偷懒，用变量a表示生成的数字
			var a = select_tr_number[0];
			var select_tr_id = "#curriculum_tr_" + a;
			var curriculum_id = $("#curriculumId_" + a).text();
			alert(curriculum_id + " " + select_tr_id);
			url="${ pageContext.request.contextPath }/curriculum_deleteCurriculum.action";
			param={"curriculum_id":curriculum_id};
			$.post(url,param,function(data){
				if(data == "success")
					$(select_tr_id).remove();
			});
		});
	});
</SCRIPT>
</BODY>
</HTML>
