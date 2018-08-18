<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD id=Head1>
<TITLE>顶部</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
BODY {
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-TOP: 0px;
	BACKGROUND-COLOR: #2a8dc8
}
TD {
	FONT-SIZE: 12px;
	COLOR: #003366;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}
</STYLE>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1 action="" method=post>
		<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
			<TBODY>
				<TR>
					<TD style="background-color:#F0FFFF" width=10></TD>
					<TD style="background-color:#F0FFFF"><FONT size=5><B>校园系统</B></FONT></TD>
					<TD style="background-color:#F0FFFF">
						<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
							<TBODY>
								<TR>
									<TD align=right height=35></TD>
								</TR>
								<TR>
									<TD height=35 align="right"><FONT size=3>当前用户：${ userLoginName }
										&nbsp;&nbsp;&nbsp;&nbsp;</FONT><A href="${ pageContext.request.contextPath }/jsp/${ userType }/changePassword.jsp" target="main">
										<FONT color=red size=3>修改密码</FONT></A>
										&nbsp;&nbsp;&nbsp;&nbsp;<A href="${ pageContext.request.contextPath }/${ userType }_exit.action" target="_top">
										<FONT color=red size=3>安全退出	</FONT>
										</A>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD style="background-color:#F0FFFF" width=10></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>