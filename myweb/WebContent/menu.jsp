<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>导航</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
.mainMenu {
	FONT-WEIGHT: bold;
	FONT-SIZE: 14px;
	cursor: pointer;
	COLOR: #000000
}
A.style2:link {
	PADDING-LEFT: 4px;
	COLOR: #0055bb;
	TEXT-DECORATION: none
}
A.style2:visited {
	PADDING-LEFT: 4px;
	COLOR: #0055bb;
	TEXT-DECORATION: none
}
A.style2:hover {
	PADDING-LEFT: 4px;
	COLOR: #ff0000;
	TEXT-DECORATION: none
}
A.active {
	PADDING-LEFT: 4px;
	COLOR: #ff0000;
	TEXT-DECORATION: none
}
.span {
	COLOR: #ff0000;
}
</STYLE>
<SCRIPT language=javascript>
	function MenuDisplay(obj_id) {
		for (var i = 1; i <= 9; i++) {
			var obj = document.getElementById('table_' + i);
			if(obj){
				document.getElementById('table_' + i).style.display = 'none';
				document.getElementById('table_' + i + 'Span').innerText = '＋';
			}			
		}
		var obj = document.getElementById(obj_id);
		if(obj){
			if (obj.style.display == 'none') {
				obj.style.display = 'block';
				document.getElementById(obj_id + 'Span').innerText = '－';
			} else {
				obj.style.display = 'none';
				document.getElementById(obj_id + 'Span').innerText = '＋';
			}
		}		
	}
</SCRIPT>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY style="background-color:#FFEBCD">
	<TABLE cellSpacing=0 cellPadding=0 width=210 align=center border=0>
		<TBODY>
			<TR>
				<TD width=45></TD>
				<TD align=left width=150 height=35><B>功能菜单</B></TD>
				<TD width=15></TD>
			</TR>
		</TBODY>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width=210 align=center border=0>
		<TBODY>
			<TR>
				<TD width=15 ></TD>
				<TD vAlign=top width=180 >
					<TABLE cellSpacing=0 cellPadding=3 width=165 align=center border=0>
						<TBODY>
							<TR>
								<TD class=mainMenu onClick="MenuDisplay('table_1');"><SPAN class=span id=table_1Span>＋</SPAN> 个人中心</TD>
							</TR>
							<TR>
								<TD>
									<TABLE id=table_1 style="DISPLAY: none" cellSpacing=0 cellPadding=2 width=155 align=center border=0>
										<TBODY>
											<TR>
												<TD class=menuSmall><A class=style2 href="${ pageContext.request.contextPath }/jsp/${ userType }/personalProfile.jsp" target=main>－ 个人信息</A></TD>
											</TR>
											<TR> 
												<TD class=menuSmall><A class=style2 href="${ pageContext.request.contextPath }/jsp/curriculum/${ userType }Curriculum.jsp" target=main>－ 课程信息</A></TD>
											</TR>												
										</TBODY>
									</TABLE>
								</TD>
							</TR>
							<TR>
								<TD height=1></TD>
							</TR>
							<TR>
								<TD class=mainMenu onClick="MenuDisplay('table_2');"><SPAN
									class=span id=table_2Span>＋</SPAN> 通讯录</TD>
							</TR>
							<TR>
								<TD>
									<TABLE id=table_2 style="DISPLAY: none" cellSpacing=0 cellPadding=2 width=155 align=center border=0>
										<TBODY>
											<TR>
												<TD class=menuSmall><A class=style2 href="${ pageContext.request.contextPath }/waiting.jsp" target=main>－ 新增联系人</A></TD>
											</TR>
											<TR>
												<TD class=menuSmall><A class=style2 href="${ pageContext.request.contextPath }/waiting.jsp" target=main>－ 联系人列表</A></TD>
											</TR>
											<TR>
												<TD class=menuSmall><A class=style2 href="${ pageContext.request.contextPath }/waiting.jsp" target=main>－ 聊天室</A></TD>
											</TR>	
										</TBODY>
									</TABLE>
								</TD>
							</TR>
							<TR>
								<TD height=1></TD>
							</TR>
							<TR>
								<TD class=mainMenu onClick="MenuDisplay('table_5');"><SPAN
									class=span id=table_5Span>＋</SPAN> 通知公告</TD>
							</TR>
							<TR>
								<TD>
									<TABLE id=table_5 style="DISPLAY: none" cellSpacing=0
										cellPadding=2 width=155 align=center border=0>
										<TBODY>
											<TR>
												<TD class=menuSmall><A class=style2 href="${ pageContext.request.contextPath }/waiting.jsp"
													target=main>－校内新闻与通知</A></TD>
											</TR>												
										</TBODY>
									</TABLE>
								</TD>
							</TR>
							<TR>
								<TD height=1></TD>
							</TR>
							<% if (session.getAttribute("userPosition").equals("0")) {%>
							<TR>
								<TD class=mainMenu onClick="MenuDisplay('table_3');"><SPAN
									class=span id=table_3Span>＋</SPAN> 学生功能</TD>
							</TR>
							<TR>
								<TD>
									<TABLE id=table_3 style="DISPLAY: none" cellSpacing=0 cellPadding=2 width=155 align=center border=0>
										<TBODY>
											<TR>
												<TD class=menuSmall><A class=style2 href="${ pageContext.request.contextPath }/jsp/curriculum/studentCurriculumList.jsp"
													target=main>－ 选课</A></TD>
											</TR>
											<TR>
												<TD class=menuSmall><A class=style2 href="${ pageContext.request.contextPath }/jsp/course/viewYourScore.jsp"
													target=main>－ 查分</A></TD>
											</TR>
										</TBODY>
									</TABLE>
								</TD>
							</TR>
							<TR>
								<TD height=1></TD>
							</TR>
							<% } %>
							<% if (session.getAttribute("userPosition").equals("1")) {%>
							<TR>
								<TD class=mainMenu onClick="MenuDisplay('table_4');"><SPAN
									class=span id=table_4Span>＋</SPAN> 教师功能</TD>
							</TR>
							<TR>
								<TD>
									<TABLE id=table_4 style="DISPLAY: none" cellSpacing=0
										cellPadding=2 width=155 align=center border=0>
										<TBODY>
											<TR>
												<TD class=menuSmall><A class=style2 href="${ pageContext.request.contextPath }/jsp/curriculum/registerCurriculum.jsp"
													target=main>－课程登记</A></TD>
											</TR>
											<TR>
												<TD class=menuSmall><A class=style2 href="${ pageContext.request.contextPath }/jsp/course/courseScore.jsp"
													target=main>－分数登记</A></TD>
											</TR>
										</TBODY>
									</TABLE>
								</TD>
							</TR>
							<% } %>
							<TR>
								<TD height=1></TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
				<TD width=15 ></TD>
			</TR>
		</TBODY>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width=210 align=center border=0>
		<TBODY>
			<TR>
				<TD width=15><IMG border=0></TD>
				<TD align=center width=180 height=15></TD>
				<TD width=15><IMG border=0></TD>
			</TR>
		</TBODY>
	</TABLE>
</BODY>
</HTML>
</BODY>
</HTML>